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
import com.sivotek.crm.persistent.dao.entities.Crmcampaign;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmcampaigndocs;
import com.sivotek.crm.persistent.dao.entities.CrmcampaigndocsPK;
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
public class CrmcampaigndocsJpaController implements Serializable {

    public CrmcampaigndocsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmcampaigndocsJpaController(){
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

    public void create(Crmcampaigndocs crmcampaigndocs) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmcampaigndocs.getCrmcampaigndocsPK() == null) {
            crmcampaigndocs.setCrmcampaigndocsPK(new CrmcampaigndocsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaign crmcampaign = crmcampaigndocs.getCrmcampaign();
            if (crmcampaign != null) {
                crmcampaign = em.getReference(crmcampaign.getClass(), crmcampaign.getCrmcampaignPK());
                crmcampaigndocs.setCrmcampaign(crmcampaign);
            }
            Companyemployee companyemployee = crmcampaigndocs.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmcampaigndocs.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmcampaigndocs.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmcampaigndocs.setCompanyemployee1(companyemployee1);
            }
            em.persist(crmcampaigndocs);
            if (crmcampaign != null) {
                crmcampaign.getCrmcampaigndocsCollection().add(crmcampaigndocs);
                crmcampaign = em.merge(crmcampaign);
            }
            if (companyemployee != null) {
                companyemployee.getCrmcampaigndocsCollection().add(crmcampaigndocs);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmcampaigndocsCollection().add(crmcampaigndocs);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmcampaigndocs(crmcampaigndocs.getCrmcampaigndocsPK()) != null) {
                throw new PreexistingEntityException("Crmcampaigndocs " + crmcampaigndocs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmcampaigndocs crmcampaigndocs) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaigndocs persistentCrmcampaigndocs = em.find(Crmcampaigndocs.class, crmcampaigndocs.getCrmcampaigndocsPK());
            Crmcampaign crmcampaignOld = persistentCrmcampaigndocs.getCrmcampaign();
            Crmcampaign crmcampaignNew = crmcampaigndocs.getCrmcampaign();
            Companyemployee companyemployeeOld = persistentCrmcampaigndocs.getCompanyemployee();
            Companyemployee companyemployeeNew = crmcampaigndocs.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmcampaigndocs.getCompanyemployee1();
            Companyemployee companyemployee1New = crmcampaigndocs.getCompanyemployee1();
            if (crmcampaignNew != null) {
                crmcampaignNew = em.getReference(crmcampaignNew.getClass(), crmcampaignNew.getCrmcampaignPK());
                crmcampaigndocs.setCrmcampaign(crmcampaignNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmcampaigndocs.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmcampaigndocs.setCompanyemployee1(companyemployee1New);
            }
            crmcampaigndocs = em.merge(crmcampaigndocs);
            if (crmcampaignOld != null && !crmcampaignOld.equals(crmcampaignNew)) {
                crmcampaignOld.getCrmcampaigndocsCollection().remove(crmcampaigndocs);
                crmcampaignOld = em.merge(crmcampaignOld);
            }
            if (crmcampaignNew != null && !crmcampaignNew.equals(crmcampaignOld)) {
                crmcampaignNew.getCrmcampaigndocsCollection().add(crmcampaigndocs);
                crmcampaignNew = em.merge(crmcampaignNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmcampaigndocsCollection().remove(crmcampaigndocs);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmcampaigndocsCollection().add(crmcampaigndocs);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmcampaigndocsCollection().remove(crmcampaigndocs);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmcampaigndocsCollection().add(crmcampaigndocs);
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
                CrmcampaigndocsPK id = crmcampaigndocs.getCrmcampaigndocsPK();
                if (findCrmcampaigndocs(id) == null) {
                    throw new NonexistentEntityException("The crmcampaigndocs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmcampaigndocsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaigndocs crmcampaigndocs;
            try {
                crmcampaigndocs = em.getReference(Crmcampaigndocs.class, id);
                crmcampaigndocs.getCrmcampaigndocsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmcampaigndocs with id " + id + " no longer exists.", enfe);
            }
            Crmcampaign crmcampaign = crmcampaigndocs.getCrmcampaign();
            if (crmcampaign != null) {
                crmcampaign.getCrmcampaigndocsCollection().remove(crmcampaigndocs);
                crmcampaign = em.merge(crmcampaign);
            }
            Companyemployee companyemployee = crmcampaigndocs.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmcampaigndocsCollection().remove(crmcampaigndocs);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmcampaigndocs.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmcampaigndocsCollection().remove(crmcampaigndocs);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmcampaigndocs);
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

    public List<Crmcampaigndocs> findCrmcampaigndocsEntities() {
        return findCrmcampaigndocsEntities(true, -1, -1);
    }

    public List<Crmcampaigndocs> findCrmcampaigndocsEntities(int maxResults, int firstResult) {
        return findCrmcampaigndocsEntities(false, maxResults, firstResult);
    }

    private List<Crmcampaigndocs> findCrmcampaigndocsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmcampaigndocs.class));
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

    public Crmcampaigndocs findCrmcampaigndocs(CrmcampaigndocsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmcampaigndocs.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmcampaigndocsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmcampaigndocs> rt = cq.from(Crmcampaigndocs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
