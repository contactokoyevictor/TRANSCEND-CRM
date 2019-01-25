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
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Customerbank;
import com.sivotek.crm.persistent.dao.entities.CustomerbankPK;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import javax.persistence.Persistence;
/**
 *
 * @author okoyevictor
 */
public class CustomerbankJpaController implements Serializable {

    public CustomerbankJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerbankJpaController(){
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

    public void create(Customerbank customerbank) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (customerbank.getCustomerbankPK() == null) {
            customerbank.setCustomerbankPK(new CustomerbankPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companycustomer companycustomer = customerbank.getCompanycustomer();
            if (companycustomer != null) {
                companycustomer = em.getReference(companycustomer.getClass(), companycustomer.getCompanycustomerPK());
                customerbank.setCompanycustomer(companycustomer);
            }
            Companyemployee companyemployee = customerbank.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                customerbank.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = customerbank.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                customerbank.setCompanyemployee1(companyemployee1);
            }
            em.persist(customerbank);
            if (companycustomer != null) {
                companycustomer.getCustomerbankCollection().add(customerbank);
                companycustomer = em.merge(companycustomer);
            }
            if (companyemployee != null) {
                companyemployee.getCustomerbankCollection().add(customerbank);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCustomerbankCollection().add(customerbank);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCustomerbank(customerbank.getCustomerbankPK()) != null) {
                throw new PreexistingEntityException("Customerbank " + customerbank + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customerbank customerbank) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customerbank persistentCustomerbank = em.find(Customerbank.class, customerbank.getCustomerbankPK());
            Companycustomer companycustomerOld = persistentCustomerbank.getCompanycustomer();
            Companycustomer companycustomerNew = customerbank.getCompanycustomer();
            Companyemployee companyemployeeOld = persistentCustomerbank.getCompanyemployee();
            Companyemployee companyemployeeNew = customerbank.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCustomerbank.getCompanyemployee1();
            Companyemployee companyemployee1New = customerbank.getCompanyemployee1();
            if (companycustomerNew != null) {
                companycustomerNew = em.getReference(companycustomerNew.getClass(), companycustomerNew.getCompanycustomerPK());
                customerbank.setCompanycustomer(companycustomerNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                customerbank.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                customerbank.setCompanyemployee1(companyemployee1New);
            }
            customerbank = em.merge(customerbank);
            if (companycustomerOld != null && !companycustomerOld.equals(companycustomerNew)) {
                companycustomerOld.getCustomerbankCollection().remove(customerbank);
                companycustomerOld = em.merge(companycustomerOld);
            }
            if (companycustomerNew != null && !companycustomerNew.equals(companycustomerOld)) {
                companycustomerNew.getCustomerbankCollection().add(customerbank);
                companycustomerNew = em.merge(companycustomerNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCustomerbankCollection().remove(customerbank);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCustomerbankCollection().add(customerbank);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCustomerbankCollection().remove(customerbank);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCustomerbankCollection().add(customerbank);
                companyemployee1New = em.merge(companyemployee1New);
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
                CustomerbankPK id = customerbank.getCustomerbankPK();
                if (findCustomerbank(id) == null) {
                    throw new NonexistentEntityException("The customerbank with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CustomerbankPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customerbank customerbank;
            try {
                customerbank = em.getReference(Customerbank.class, id);
                customerbank.getCustomerbankPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customerbank with id " + id + " no longer exists.", enfe);
            }
            Companycustomer companycustomer = customerbank.getCompanycustomer();
            if (companycustomer != null) {
                companycustomer.getCustomerbankCollection().remove(customerbank);
                companycustomer = em.merge(companycustomer);
            }
            Companyemployee companyemployee = customerbank.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCustomerbankCollection().remove(customerbank);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = customerbank.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCustomerbankCollection().remove(customerbank);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(customerbank);
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

    public List<Customerbank> findCustomerbankEntities() {
        return findCustomerbankEntities(true, -1, -1);
    }

    public List<Customerbank> findCustomerbankEntities(int maxResults, int firstResult) {
        return findCustomerbankEntities(false, maxResults, firstResult);
    }

    private List<Customerbank> findCustomerbankEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customerbank.class));
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

    public Customerbank findCustomerbank(CustomerbankPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customerbank.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerbankCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customerbank> rt = cq.from(Customerbank.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
