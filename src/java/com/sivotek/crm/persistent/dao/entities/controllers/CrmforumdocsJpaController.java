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
import com.sivotek.crm.persistent.dao.entities.Crmforum;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmforumdocs;
import com.sivotek.crm.persistent.dao.entities.CrmforumdocsPK;
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
public class CrmforumdocsJpaController implements Serializable {

    public CrmforumdocsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmforumdocsJpaController(){
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


    public void create(Crmforumdocs crmforumdocs) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmforumdocs.getCrmforumdocsPK() == null) {
            crmforumdocs.setCrmforumdocsPK(new CrmforumdocsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmforum crmforum = crmforumdocs.getCrmforum();
            if (crmforum != null) {
                crmforum = em.getReference(crmforum.getClass(), crmforum.getCrmforumPK());
                crmforumdocs.setCrmforum(crmforum);
            }
            Companyemployee companyemployee = crmforumdocs.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmforumdocs.setCompanyemployee(companyemployee);
            }
            em.persist(crmforumdocs);
            if (crmforum != null) {
                crmforum.getCrmforumdocsCollection().add(crmforumdocs);
                crmforum = em.merge(crmforum);
            }
            if (companyemployee != null) {
                companyemployee.getCrmforumdocsCollection().add(crmforumdocs);
                companyemployee = em.merge(companyemployee);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmforumdocs(crmforumdocs.getCrmforumdocsPK()) != null) {
                throw new PreexistingEntityException("Crmforumdocs " + crmforumdocs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmforumdocs crmforumdocs) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmforumdocs persistentCrmforumdocs = em.find(Crmforumdocs.class, crmforumdocs.getCrmforumdocsPK());
            Crmforum crmforumOld = persistentCrmforumdocs.getCrmforum();
            Crmforum crmforumNew = crmforumdocs.getCrmforum();
            Companyemployee companyemployeeOld = persistentCrmforumdocs.getCompanyemployee();
            Companyemployee companyemployeeNew = crmforumdocs.getCompanyemployee();
            if (crmforumNew != null) {
                crmforumNew = em.getReference(crmforumNew.getClass(), crmforumNew.getCrmforumPK());
                crmforumdocs.setCrmforum(crmforumNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmforumdocs.setCompanyemployee(companyemployeeNew);
            }
            crmforumdocs = em.merge(crmforumdocs);
            if (crmforumOld != null && !crmforumOld.equals(crmforumNew)) {
                crmforumOld.getCrmforumdocsCollection().remove(crmforumdocs);
                crmforumOld = em.merge(crmforumOld);
            }
            if (crmforumNew != null && !crmforumNew.equals(crmforumOld)) {
                crmforumNew.getCrmforumdocsCollection().add(crmforumdocs);
                crmforumNew = em.merge(crmforumNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmforumdocsCollection().remove(crmforumdocs);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmforumdocsCollection().add(crmforumdocs);
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
                CrmforumdocsPK id = crmforumdocs.getCrmforumdocsPK();
                if (findCrmforumdocs(id) == null) {
                    throw new NonexistentEntityException("The crmforumdocs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmforumdocsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmforumdocs crmforumdocs;
            try {
                crmforumdocs = em.getReference(Crmforumdocs.class, id);
                crmforumdocs.getCrmforumdocsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmforumdocs with id " + id + " no longer exists.", enfe);
            }
            Crmforum crmforum = crmforumdocs.getCrmforum();
            if (crmforum != null) {
                crmforum.getCrmforumdocsCollection().remove(crmforumdocs);
                crmforum = em.merge(crmforum);
            }
            Companyemployee companyemployee = crmforumdocs.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmforumdocsCollection().remove(crmforumdocs);
                companyemployee = em.merge(companyemployee);
            }
            em.remove(crmforumdocs);
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

    public List<Crmforumdocs> findCrmforumdocsEntities() {
        return findCrmforumdocsEntities(true, -1, -1);
    }

    public List<Crmforumdocs> findCrmforumdocsEntities(int maxResults, int firstResult) {
        return findCrmforumdocsEntities(false, maxResults, firstResult);
    }

    private List<Crmforumdocs> findCrmforumdocsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmforumdocs.class));
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

    public Crmforumdocs findCrmforumdocs(CrmforumdocsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmforumdocs.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmforumdocsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmforumdocs> rt = cq.from(Crmforumdocs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
