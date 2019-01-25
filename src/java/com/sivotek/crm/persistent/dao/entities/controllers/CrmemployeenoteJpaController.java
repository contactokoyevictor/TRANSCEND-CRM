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
import com.sivotek.crm.persistent.dao.entities.Crmemployeenote;
import com.sivotek.crm.persistent.dao.entities.CrmemployeenotePK;
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
public class CrmemployeenoteJpaController implements Serializable {

    public CrmemployeenoteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmemployeenoteJpaController(){
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

    public void create(Crmemployeenote crmemployeenote) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmemployeenote.getCrmemployeenotePK() == null) {
            crmemployeenote.setCrmemployeenotePK(new CrmemployeenotePK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = crmemployeenote.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmemployeenote.setCompanyemployee(companyemployee);
            }
            em.persist(crmemployeenote);
            if (companyemployee != null) {
                companyemployee.getCrmemployeenoteCollection().add(crmemployeenote);
                companyemployee = em.merge(companyemployee);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmemployeenote(crmemployeenote.getCrmemployeenotePK()) != null) {
                throw new PreexistingEntityException("Crmemployeenote " + crmemployeenote + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmemployeenote crmemployeenote) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmemployeenote persistentCrmemployeenote = em.find(Crmemployeenote.class, crmemployeenote.getCrmemployeenotePK());
            Companyemployee companyemployeeOld = persistentCrmemployeenote.getCompanyemployee();
            Companyemployee companyemployeeNew = crmemployeenote.getCompanyemployee();
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmemployeenote.setCompanyemployee(companyemployeeNew);
            }
            crmemployeenote = em.merge(crmemployeenote);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmemployeenoteCollection().remove(crmemployeenote);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmemployeenoteCollection().add(crmemployeenote);
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
                CrmemployeenotePK id = crmemployeenote.getCrmemployeenotePK();
                if (findCrmemployeenote(id) == null) {
                    throw new NonexistentEntityException("The crmemployeenote with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmemployeenotePK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmemployeenote crmemployeenote;
            try {
                crmemployeenote = em.getReference(Crmemployeenote.class, id);
                crmemployeenote.getCrmemployeenotePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmemployeenote with id " + id + " no longer exists.", enfe);
            }
            Companyemployee companyemployee = crmemployeenote.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmemployeenoteCollection().remove(crmemployeenote);
                companyemployee = em.merge(companyemployee);
            }
            em.remove(crmemployeenote);
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

    public List<Crmemployeenote> findCrmemployeenoteEntities() {
        return findCrmemployeenoteEntities(true, -1, -1);
    }

    public List<Crmemployeenote> findCrmemployeenoteEntities(int maxResults, int firstResult) {
        return findCrmemployeenoteEntities(false, maxResults, firstResult);
    }

    private List<Crmemployeenote> findCrmemployeenoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmemployeenote.class));
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

    public Crmemployeenote findCrmemployeenote(CrmemployeenotePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmemployeenote.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmemployeenoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmemployeenote> rt = cq.from(Crmemployeenote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
