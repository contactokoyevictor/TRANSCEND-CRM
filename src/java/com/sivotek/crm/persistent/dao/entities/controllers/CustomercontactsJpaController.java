/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import com.sivotek.crm.persistent.dao.entities.Customercontacts;
import com.sivotek.crm.persistent.dao.entities.CustomercontactsPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.crm.persistent.dao.entities.Customercontactsaddress;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
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
public class CustomercontactsJpaController implements Serializable {

    public CustomercontactsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CustomercontactsJpaController(){
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

    public void create(Customercontacts customercontacts) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (customercontacts.getCustomercontactsPK() == null) {
            customercontacts.setCustomercontactsPK(new CustomercontactsPK());
        }
        if (customercontacts.getCustomercontactsaddressCollection() == null) {
            customercontacts.setCustomercontactsaddressCollection(new ArrayList<Customercontactsaddress>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Customercontactsaddress> attachedCustomercontactsaddressCollection = new ArrayList<Customercontactsaddress>();
            for (Customercontactsaddress customercontactsaddressCollectionCustomercontactsaddressToAttach : customercontacts.getCustomercontactsaddressCollection()) {
                customercontactsaddressCollectionCustomercontactsaddressToAttach = em.getReference(customercontactsaddressCollectionCustomercontactsaddressToAttach.getClass(), customercontactsaddressCollectionCustomercontactsaddressToAttach.getCustomercontactsaddressPK());
                attachedCustomercontactsaddressCollection.add(customercontactsaddressCollectionCustomercontactsaddressToAttach);
            }
            customercontacts.setCustomercontactsaddressCollection(attachedCustomercontactsaddressCollection);
            em.persist(customercontacts);
            for (Customercontactsaddress customercontactsaddressCollectionCustomercontactsaddress : customercontacts.getCustomercontactsaddressCollection()) {
                Customercontacts oldCustomercontactsOfCustomercontactsaddressCollectionCustomercontactsaddress = customercontactsaddressCollectionCustomercontactsaddress.getCustomercontacts();
                customercontactsaddressCollectionCustomercontactsaddress.setCustomercontacts(customercontacts);
                customercontactsaddressCollectionCustomercontactsaddress = em.merge(customercontactsaddressCollectionCustomercontactsaddress);
                if (oldCustomercontactsOfCustomercontactsaddressCollectionCustomercontactsaddress != null) {
                    oldCustomercontactsOfCustomercontactsaddressCollectionCustomercontactsaddress.getCustomercontactsaddressCollection().remove(customercontactsaddressCollectionCustomercontactsaddress);
                    oldCustomercontactsOfCustomercontactsaddressCollectionCustomercontactsaddress = em.merge(oldCustomercontactsOfCustomercontactsaddressCollectionCustomercontactsaddress);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCustomercontacts(customercontacts.getCustomercontactsPK()) != null) {
                throw new PreexistingEntityException("Customercontacts " + customercontacts + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customercontacts customercontacts) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customercontacts persistentCustomercontacts = em.find(Customercontacts.class, customercontacts.getCustomercontactsPK());
            Collection<Customercontactsaddress> customercontactsaddressCollectionOld = persistentCustomercontacts.getCustomercontactsaddressCollection();
            Collection<Customercontactsaddress> customercontactsaddressCollectionNew = customercontacts.getCustomercontactsaddressCollection();
            List<String> illegalOrphanMessages = null;
            for (Customercontactsaddress customercontactsaddressCollectionOldCustomercontactsaddress : customercontactsaddressCollectionOld) {
                if (!customercontactsaddressCollectionNew.contains(customercontactsaddressCollectionOldCustomercontactsaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customercontactsaddress " + customercontactsaddressCollectionOldCustomercontactsaddress + " since its customercontacts field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Customercontactsaddress> attachedCustomercontactsaddressCollectionNew = new ArrayList<Customercontactsaddress>();
            for (Customercontactsaddress customercontactsaddressCollectionNewCustomercontactsaddressToAttach : customercontactsaddressCollectionNew) {
                customercontactsaddressCollectionNewCustomercontactsaddressToAttach = em.getReference(customercontactsaddressCollectionNewCustomercontactsaddressToAttach.getClass(), customercontactsaddressCollectionNewCustomercontactsaddressToAttach.getCustomercontactsaddressPK());
                attachedCustomercontactsaddressCollectionNew.add(customercontactsaddressCollectionNewCustomercontactsaddressToAttach);
            }
            customercontactsaddressCollectionNew = attachedCustomercontactsaddressCollectionNew;
            customercontacts.setCustomercontactsaddressCollection(customercontactsaddressCollectionNew);
            customercontacts = em.merge(customercontacts);
            for (Customercontactsaddress customercontactsaddressCollectionNewCustomercontactsaddress : customercontactsaddressCollectionNew) {
                if (!customercontactsaddressCollectionOld.contains(customercontactsaddressCollectionNewCustomercontactsaddress)) {
                    Customercontacts oldCustomercontactsOfCustomercontactsaddressCollectionNewCustomercontactsaddress = customercontactsaddressCollectionNewCustomercontactsaddress.getCustomercontacts();
                    customercontactsaddressCollectionNewCustomercontactsaddress.setCustomercontacts(customercontacts);
                    customercontactsaddressCollectionNewCustomercontactsaddress = em.merge(customercontactsaddressCollectionNewCustomercontactsaddress);
                    if (oldCustomercontactsOfCustomercontactsaddressCollectionNewCustomercontactsaddress != null && !oldCustomercontactsOfCustomercontactsaddressCollectionNewCustomercontactsaddress.equals(customercontacts)) {
                        oldCustomercontactsOfCustomercontactsaddressCollectionNewCustomercontactsaddress.getCustomercontactsaddressCollection().remove(customercontactsaddressCollectionNewCustomercontactsaddress);
                        oldCustomercontactsOfCustomercontactsaddressCollectionNewCustomercontactsaddress = em.merge(oldCustomercontactsOfCustomercontactsaddressCollectionNewCustomercontactsaddress);
                    }
                }
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
                CustomercontactsPK id = customercontacts.getCustomercontactsPK();
                if (findCustomercontacts(id) == null) {
                    throw new NonexistentEntityException("The customercontacts with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CustomercontactsPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customercontacts customercontacts;
            try {
                customercontacts = em.getReference(Customercontacts.class, id);
                customercontacts.getCustomercontactsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customercontacts with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Customercontactsaddress> customercontactsaddressCollectionOrphanCheck = customercontacts.getCustomercontactsaddressCollection();
            for (Customercontactsaddress customercontactsaddressCollectionOrphanCheckCustomercontactsaddress : customercontactsaddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customercontacts (" + customercontacts + ") cannot be destroyed since the Customercontactsaddress " + customercontactsaddressCollectionOrphanCheckCustomercontactsaddress + " in its customercontactsaddressCollection field has a non-nullable customercontacts field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(customercontacts);
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

    public List<Customercontacts> findCustomercontactsEntities() {
        return findCustomercontactsEntities(true, -1, -1);
    }

    public List<Customercontacts> findCustomercontactsEntities(int maxResults, int firstResult) {
        return findCustomercontactsEntities(false, maxResults, firstResult);
    }

    private List<Customercontacts> findCustomercontactsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customercontacts.class));
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

    public Customercontacts findCustomercontacts(CustomercontactsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customercontacts.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomercontactsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customercontacts> rt = cq.from(Customercontacts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
