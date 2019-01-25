/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.crm.persistent.dao.entities.Customercontacts;
import com.sivotek.crm.persistent.dao.entities.Customercontactsaddress;
import com.sivotek.crm.persistent.dao.entities.CustomercontactsaddressPK;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author okoyevictor
 */
public class CustomercontactsaddressJpaController implements Serializable {

    public CustomercontactsaddressJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CustomercontactsaddressJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("TRANSCEND-CRM_CLOUD_TOMEEPU");
             getUserTransaction();
        }
        catch(Exception ex){
        System.out.println("Error occoured during JNDI Lookup : "+ex.getMessage());
       }
        
    }
    private UserTransaction getUserTransaction() throws NamingException{
        Context context = new InitialContext ();
        if(utx == null){
            utx = (UserTransaction) context.lookup ("java:comp/TransactionManager");
         }
        return utx;
      }

    public void create(Customercontactsaddress customercontactsaddress) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (customercontactsaddress.getCustomercontactsaddressPK() == null) {
            customercontactsaddress.setCustomercontactsaddressPK(new CustomercontactsaddressPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customercontacts customercontacts = customercontactsaddress.getCustomercontacts();
            if (customercontacts != null) {
                customercontacts = em.getReference(customercontacts.getClass(), customercontacts.getCustomercontactsPK());
                customercontactsaddress.setCustomercontacts(customercontacts);
            }
            em.persist(customercontactsaddress);
            if (customercontacts != null) {
                customercontacts.getCustomercontactsaddressCollection().add(customercontactsaddress);
                customercontacts = em.merge(customercontacts);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCustomercontactsaddress(customercontactsaddress.getCustomercontactsaddressPK()) != null) {
                throw new PreexistingEntityException("Customercontactsaddress " + customercontactsaddress + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customercontactsaddress customercontactsaddress) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customercontactsaddress persistentCustomercontactsaddress = em.find(Customercontactsaddress.class, customercontactsaddress.getCustomercontactsaddressPK());
            Customercontacts customercontactsOld = persistentCustomercontactsaddress.getCustomercontacts();
            Customercontacts customercontactsNew = customercontactsaddress.getCustomercontacts();
            if (customercontactsNew != null) {
                customercontactsNew = em.getReference(customercontactsNew.getClass(), customercontactsNew.getCustomercontactsPK());
                customercontactsaddress.setCustomercontacts(customercontactsNew);
            }
            customercontactsaddress = em.merge(customercontactsaddress);
            if (customercontactsOld != null && !customercontactsOld.equals(customercontactsNew)) {
                customercontactsOld.getCustomercontactsaddressCollection().remove(customercontactsaddress);
                customercontactsOld = em.merge(customercontactsOld);
            }
            if (customercontactsNew != null && !customercontactsNew.equals(customercontactsOld)) {
                customercontactsNew.getCustomercontactsaddressCollection().add(customercontactsaddress);
                customercontactsNew = em.merge(customercontactsNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CustomercontactsaddressPK id = customercontactsaddress.getCustomercontactsaddressPK();
                if (findCustomercontactsaddress(id) == null) {
                    throw new NonexistentEntityException("The customercontactsaddress with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CustomercontactsaddressPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customercontactsaddress customercontactsaddress;
            try {
                customercontactsaddress = em.getReference(Customercontactsaddress.class, id);
                customercontactsaddress.getCustomercontactsaddressPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customercontactsaddress with id " + id + " no longer exists.", enfe);
            }
            Customercontacts customercontacts = customercontactsaddress.getCustomercontacts();
            if (customercontacts != null) {
                customercontacts.getCustomercontactsaddressCollection().remove(customercontactsaddress);
                customercontacts = em.merge(customercontacts);
            }
            em.remove(customercontactsaddress);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customercontactsaddress> findCustomercontactsaddressEntities() {
        return findCustomercontactsaddressEntities(true, -1, -1);
    }

    public List<Customercontactsaddress> findCustomercontactsaddressEntities(int maxResults, int firstResult) {
        return findCustomercontactsaddressEntities(false, maxResults, firstResult);
    }

    private List<Customercontactsaddress> findCustomercontactsaddressEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customercontactsaddress.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Customercontactsaddress findCustomercontactsaddress(CustomercontactsaddressPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customercontactsaddress.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomercontactsaddressCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customercontactsaddress> rt = cq.from(Customercontactsaddress.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
