/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import com.sivotek.crm.persistent.dao.entities.Approval;
import com.sivotek.crm.persistent.dao.entities.ApprovalPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
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
public class ApprovalJpaController implements Serializable {

    public ApprovalJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ApprovalJpaController(){
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

    public void create(Approval approval) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (approval.getApprovalPK() == null) {
            approval.setApprovalPK(new ApprovalPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = approval.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                approval.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = approval.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                approval.setCompanyemployee1(companyemployee1);
            }
            Companyemployee companyemployee2 = approval.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2 = em.getReference(companyemployee2.getClass(), companyemployee2.getCompanyemployeePK());
                approval.setCompanyemployee2(companyemployee2);
            }
            em.persist(approval);
            if (companyemployee != null) {
                companyemployee.getApprovalCollection().add(approval);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getApprovalCollection().add(approval);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (companyemployee2 != null) {
                companyemployee2.getApprovalCollection().add(approval);
                companyemployee2 = em.merge(companyemployee2);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findApproval(approval.getApprovalPK()) != null) {
                throw new PreexistingEntityException("Approval " + approval + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Approval approval) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Approval persistentApproval = em.find(Approval.class, approval.getApprovalPK());
            Companyemployee companyemployeeOld = persistentApproval.getCompanyemployee();
            Companyemployee companyemployeeNew = approval.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentApproval.getCompanyemployee1();
            Companyemployee companyemployee1New = approval.getCompanyemployee1();
            Companyemployee companyemployee2Old = persistentApproval.getCompanyemployee2();
            Companyemployee companyemployee2New = approval.getCompanyemployee2();
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                approval.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                approval.setCompanyemployee1(companyemployee1New);
            }
            if (companyemployee2New != null) {
                companyemployee2New = em.getReference(companyemployee2New.getClass(), companyemployee2New.getCompanyemployeePK());
                approval.setCompanyemployee2(companyemployee2New);
            }
            approval = em.merge(approval);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getApprovalCollection().remove(approval);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getApprovalCollection().add(approval);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getApprovalCollection().remove(approval);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getApprovalCollection().add(approval);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (companyemployee2Old != null && !companyemployee2Old.equals(companyemployee2New)) {
                companyemployee2Old.getApprovalCollection().remove(approval);
                companyemployee2Old = em.merge(companyemployee2Old);
            }
            if (companyemployee2New != null && !companyemployee2New.equals(companyemployee2Old)) {
                companyemployee2New.getApprovalCollection().add(approval);
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
                ApprovalPK id = approval.getApprovalPK();
                if (findApproval(id) == null) {
                    throw new NonexistentEntityException("The approval with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ApprovalPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Approval approval;
            try {
                approval = em.getReference(Approval.class, id);
                approval.getApprovalPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The approval with id " + id + " no longer exists.", enfe);
            }
            Companyemployee companyemployee = approval.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getApprovalCollection().remove(approval);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = approval.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getApprovalCollection().remove(approval);
                companyemployee1 = em.merge(companyemployee1);
            }
            Companyemployee companyemployee2 = approval.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2.getApprovalCollection().remove(approval);
                companyemployee2 = em.merge(companyemployee2);
            }
            em.remove(approval);
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

    public List<Approval> findApprovalEntities() {
        return findApprovalEntities(true, -1, -1);
    }

    public List<Approval> findApprovalEntities(int maxResults, int firstResult) {
        return findApprovalEntities(false, maxResults, firstResult);
    }

    private List<Approval> findApprovalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Approval.class));
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

    public Approval findApproval(ApprovalPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Approval.class, id);
        } finally {
            em.close();
        }
    }

    public int getApprovalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Approval> rt = cq.from(Approval.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
