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
import com.sivotek.crm.persistent.dao.entities.Crmschedulerefcode;
import com.sivotek.crm.persistent.dao.entities.CrmschedulerefcodePK;
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
public class CrmschedulerefcodeJpaController implements Serializable {

    public CrmschedulerefcodeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     public CrmschedulerefcodeJpaController(){
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

    public void create(Crmschedulerefcode crmschedulerefcode) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmschedulerefcode.getCrmschedulerefcodePK() == null) {
            crmschedulerefcode.setCrmschedulerefcodePK(new CrmschedulerefcodePK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = crmschedulerefcode.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmschedulerefcode.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmschedulerefcode.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmschedulerefcode.setCompanyemployee1(companyemployee1);
            }
            em.persist(crmschedulerefcode);
            if (companyemployee != null) {
                companyemployee.getCrmschedulerefcodeCollection().add(crmschedulerefcode);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmschedulerefcodeCollection().add(crmschedulerefcode);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmschedulerefcode(crmschedulerefcode.getCrmschedulerefcodePK()) != null) {
                throw new PreexistingEntityException("Crmschedulerefcode " + crmschedulerefcode + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmschedulerefcode crmschedulerefcode) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmschedulerefcode persistentCrmschedulerefcode = em.find(Crmschedulerefcode.class, crmschedulerefcode.getCrmschedulerefcodePK());
            Companyemployee companyemployeeOld = persistentCrmschedulerefcode.getCompanyemployee();
            Companyemployee companyemployeeNew = crmschedulerefcode.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmschedulerefcode.getCompanyemployee1();
            Companyemployee companyemployee1New = crmschedulerefcode.getCompanyemployee1();
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmschedulerefcode.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmschedulerefcode.setCompanyemployee1(companyemployee1New);
            }
            crmschedulerefcode = em.merge(crmschedulerefcode);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmschedulerefcodeCollection().remove(crmschedulerefcode);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmschedulerefcodeCollection().add(crmschedulerefcode);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmschedulerefcodeCollection().remove(crmschedulerefcode);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmschedulerefcodeCollection().add(crmschedulerefcode);
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
                CrmschedulerefcodePK id = crmschedulerefcode.getCrmschedulerefcodePK();
                if (findCrmschedulerefcode(id) == null) {
                    throw new NonexistentEntityException("The crmschedulerefcode with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmschedulerefcodePK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmschedulerefcode crmschedulerefcode;
            try {
                crmschedulerefcode = em.getReference(Crmschedulerefcode.class, id);
                crmschedulerefcode.getCrmschedulerefcodePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmschedulerefcode with id " + id + " no longer exists.", enfe);
            }
            Companyemployee companyemployee = crmschedulerefcode.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmschedulerefcodeCollection().remove(crmschedulerefcode);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmschedulerefcode.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmschedulerefcodeCollection().remove(crmschedulerefcode);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmschedulerefcode);
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

    public List<Crmschedulerefcode> findCrmschedulerefcodeEntities() {
        return findCrmschedulerefcodeEntities(true, -1, -1);
    }

    public List<Crmschedulerefcode> findCrmschedulerefcodeEntities(int maxResults, int firstResult) {
        return findCrmschedulerefcodeEntities(false, maxResults, firstResult);
    }

    private List<Crmschedulerefcode> findCrmschedulerefcodeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmschedulerefcode.class));
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

    public Crmschedulerefcode findCrmschedulerefcode(CrmschedulerefcodePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmschedulerefcode.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmschedulerefcodeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmschedulerefcode> rt = cq.from(Crmschedulerefcode.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
