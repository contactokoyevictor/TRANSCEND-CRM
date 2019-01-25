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
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketmanagement;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketnotification;
import com.sivotek.crm.persistent.dao.entities.CrmprojectticketnotificationPK;
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
public class CrmprojectticketnotificationJpaController implements Serializable {

    public CrmprojectticketnotificationJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmprojectticketnotificationJpaController(){
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

    public void create(Crmprojectticketnotification crmprojectticketnotification) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmprojectticketnotification.getCrmprojectticketnotificationPK() == null) {
            crmprojectticketnotification.setCrmprojectticketnotificationPK(new CrmprojectticketnotificationPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectticketmanagement crmprojectticketmanagement = crmprojectticketnotification.getCrmprojectticketmanagement();
            if (crmprojectticketmanagement != null) {
                crmprojectticketmanagement = em.getReference(crmprojectticketmanagement.getClass(), crmprojectticketmanagement.getCrmprojectticketmanagementPK());
                crmprojectticketnotification.setCrmprojectticketmanagement(crmprojectticketmanagement);
            }
            Companyemployee companyemployee = crmprojectticketnotification.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmprojectticketnotification.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmprojectticketnotification.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmprojectticketnotification.setCompanyemployee1(companyemployee1);
            }
            Companyemployee companyemployee2 = crmprojectticketnotification.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2 = em.getReference(companyemployee2.getClass(), companyemployee2.getCompanyemployeePK());
                crmprojectticketnotification.setCompanyemployee2(companyemployee2);
            }
            em.persist(crmprojectticketnotification);
            if (crmprojectticketmanagement != null) {
                crmprojectticketmanagement.getCrmprojectticketnotificationCollection().add(crmprojectticketnotification);
                crmprojectticketmanagement = em.merge(crmprojectticketmanagement);
            }
            if (companyemployee != null) {
                companyemployee.getCrmprojectticketnotificationCollection().add(crmprojectticketnotification);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectticketnotificationCollection().add(crmprojectticketnotification);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (companyemployee2 != null) {
                companyemployee2.getCrmprojectticketnotificationCollection().add(crmprojectticketnotification);
                companyemployee2 = em.merge(companyemployee2);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmprojectticketnotification(crmprojectticketnotification.getCrmprojectticketnotificationPK()) != null) {
                throw new PreexistingEntityException("Crmprojectticketnotification " + crmprojectticketnotification + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmprojectticketnotification crmprojectticketnotification) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectticketnotification persistentCrmprojectticketnotification = em.find(Crmprojectticketnotification.class, crmprojectticketnotification.getCrmprojectticketnotificationPK());
            Crmprojectticketmanagement crmprojectticketmanagementOld = persistentCrmprojectticketnotification.getCrmprojectticketmanagement();
            Crmprojectticketmanagement crmprojectticketmanagementNew = crmprojectticketnotification.getCrmprojectticketmanagement();
            Companyemployee companyemployeeOld = persistentCrmprojectticketnotification.getCompanyemployee();
            Companyemployee companyemployeeNew = crmprojectticketnotification.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmprojectticketnotification.getCompanyemployee1();
            Companyemployee companyemployee1New = crmprojectticketnotification.getCompanyemployee1();
            Companyemployee companyemployee2Old = persistentCrmprojectticketnotification.getCompanyemployee2();
            Companyemployee companyemployee2New = crmprojectticketnotification.getCompanyemployee2();
            if (crmprojectticketmanagementNew != null) {
                crmprojectticketmanagementNew = em.getReference(crmprojectticketmanagementNew.getClass(), crmprojectticketmanagementNew.getCrmprojectticketmanagementPK());
                crmprojectticketnotification.setCrmprojectticketmanagement(crmprojectticketmanagementNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmprojectticketnotification.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmprojectticketnotification.setCompanyemployee1(companyemployee1New);
            }
            if (companyemployee2New != null) {
                companyemployee2New = em.getReference(companyemployee2New.getClass(), companyemployee2New.getCompanyemployeePK());
                crmprojectticketnotification.setCompanyemployee2(companyemployee2New);
            }
            crmprojectticketnotification = em.merge(crmprojectticketnotification);
            if (crmprojectticketmanagementOld != null && !crmprojectticketmanagementOld.equals(crmprojectticketmanagementNew)) {
                crmprojectticketmanagementOld.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotification);
                crmprojectticketmanagementOld = em.merge(crmprojectticketmanagementOld);
            }
            if (crmprojectticketmanagementNew != null && !crmprojectticketmanagementNew.equals(crmprojectticketmanagementOld)) {
                crmprojectticketmanagementNew.getCrmprojectticketnotificationCollection().add(crmprojectticketnotification);
                crmprojectticketmanagementNew = em.merge(crmprojectticketmanagementNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotification);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmprojectticketnotificationCollection().add(crmprojectticketnotification);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotification);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmprojectticketnotificationCollection().add(crmprojectticketnotification);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (companyemployee2Old != null && !companyemployee2Old.equals(companyemployee2New)) {
                companyemployee2Old.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotification);
                companyemployee2Old = em.merge(companyemployee2Old);
            }
            if (companyemployee2New != null && !companyemployee2New.equals(companyemployee2Old)) {
                companyemployee2New.getCrmprojectticketnotificationCollection().add(crmprojectticketnotification);
                companyemployee2New = em.merge(companyemployee2New);
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
                CrmprojectticketnotificationPK id = crmprojectticketnotification.getCrmprojectticketnotificationPK();
                if (findCrmprojectticketnotification(id) == null) {
                    throw new NonexistentEntityException("The crmprojectticketnotification with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmprojectticketnotificationPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectticketnotification crmprojectticketnotification;
            try {
                crmprojectticketnotification = em.getReference(Crmprojectticketnotification.class, id);
                crmprojectticketnotification.getCrmprojectticketnotificationPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmprojectticketnotification with id " + id + " no longer exists.", enfe);
            }
            Crmprojectticketmanagement crmprojectticketmanagement = crmprojectticketnotification.getCrmprojectticketmanagement();
            if (crmprojectticketmanagement != null) {
                crmprojectticketmanagement.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotification);
                crmprojectticketmanagement = em.merge(crmprojectticketmanagement);
            }
            Companyemployee companyemployee = crmprojectticketnotification.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotification);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmprojectticketnotification.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotification);
                companyemployee1 = em.merge(companyemployee1);
            }
            Companyemployee companyemployee2 = crmprojectticketnotification.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotification);
                companyemployee2 = em.merge(companyemployee2);
            }
            em.remove(crmprojectticketnotification);
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

    public List<Crmprojectticketnotification> findCrmprojectticketnotificationEntities() {
        return findCrmprojectticketnotificationEntities(true, -1, -1);
    }

    public List<Crmprojectticketnotification> findCrmprojectticketnotificationEntities(int maxResults, int firstResult) {
        return findCrmprojectticketnotificationEntities(false, maxResults, firstResult);
    }

    private List<Crmprojectticketnotification> findCrmprojectticketnotificationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmprojectticketnotification.class));
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

    public Crmprojectticketnotification findCrmprojectticketnotification(CrmprojectticketnotificationPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmprojectticketnotification.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmprojectticketnotificationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmprojectticketnotification> rt = cq.from(Crmprojectticketnotification.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
