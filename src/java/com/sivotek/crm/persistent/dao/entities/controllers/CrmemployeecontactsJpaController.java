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
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmemployeecontacts;
import com.sivotek.crm.persistent.dao.entities.CrmemployeecontactsPK;
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
public class CrmemployeecontactsJpaController implements Serializable {

    public CrmemployeecontactsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmemployeecontactsJpaController(){
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


    public void create(Crmemployeecontacts crmemployeecontacts) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmemployeecontacts.getCrmemployeecontactsPK() == null) {
            crmemployeecontacts.setCrmemployeecontactsPK(new CrmemployeecontactsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = crmemployeecontacts.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmemployeecontacts.setCompanyemployee(companyemployee);
            }
            em.persist(crmemployeecontacts);
            if (companyemployee != null) {
                companyemployee.getCrmemployeecontactsCollection().add(crmemployeecontacts);
                companyemployee = em.merge(companyemployee);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmemployeecontacts(crmemployeecontacts.getCrmemployeecontactsPK()) != null) {
                throw new PreexistingEntityException("Crmemployeecontacts " + crmemployeecontacts + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmemployeecontacts crmemployeecontacts) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmemployeecontacts persistentCrmemployeecontacts = em.find(Crmemployeecontacts.class, crmemployeecontacts.getCrmemployeecontactsPK());
            Companyemployee companyemployeeOld = persistentCrmemployeecontacts.getCompanyemployee();
            Companyemployee companyemployeeNew = crmemployeecontacts.getCompanyemployee();
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmemployeecontacts.setCompanyemployee(companyemployeeNew);
            }
            crmemployeecontacts = em.merge(crmemployeecontacts);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmemployeecontactsCollection().remove(crmemployeecontacts);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmemployeecontactsCollection().add(crmemployeecontacts);
                companyemployeeNew = em.merge(companyemployeeNew);
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
                CrmemployeecontactsPK id = crmemployeecontacts.getCrmemployeecontactsPK();
                if (findCrmemployeecontacts(id) == null) {
                    throw new NonexistentEntityException("The crmemployeecontacts with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmemployeecontactsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmemployeecontacts crmemployeecontacts;
            try {
                crmemployeecontacts = em.getReference(Crmemployeecontacts.class, id);
                crmemployeecontacts.getCrmemployeecontactsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmemployeecontacts with id " + id + " no longer exists.", enfe);
            }
            Companyemployee companyemployee = crmemployeecontacts.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmemployeecontactsCollection().remove(crmemployeecontacts);
                companyemployee = em.merge(companyemployee);
            }
            em.remove(crmemployeecontacts);
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

    public List<Crmemployeecontacts> findCrmemployeecontactsEntities() {
        return findCrmemployeecontactsEntities(true, -1, -1);
    }

    public List<Crmemployeecontacts> findCrmemployeecontactsEntities(int maxResults, int firstResult) {
        return findCrmemployeecontactsEntities(false, maxResults, firstResult);
    }

    private List<Crmemployeecontacts> findCrmemployeecontactsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmemployeecontacts.class));
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

    public Crmemployeecontacts findCrmemployeecontacts(CrmemployeecontactsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmemployeecontacts.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmemployeecontactsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmemployeecontacts> rt = cq.from(Crmemployeecontacts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
