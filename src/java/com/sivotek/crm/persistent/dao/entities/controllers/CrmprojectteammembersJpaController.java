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
import com.sivotek.crm.persistent.dao.entities.Crmproject;
import com.sivotek.crm.persistent.dao.entities.Crmprojecttask;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmprojectteammembers;
import com.sivotek.crm.persistent.dao.entities.CrmprojectteammembersPK;
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
public class CrmprojectteammembersJpaController implements Serializable {

    public CrmprojectteammembersJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmprojectteammembersJpaController(){
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

    public void create(Crmprojectteammembers crmprojectteammembers) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmprojectteammembers.getCrmprojectteammembersPK() == null) {
            crmprojectteammembers.setCrmprojectteammembersPK(new CrmprojectteammembersPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmproject crmproject = crmprojectteammembers.getCrmproject();
            if (crmproject != null) {
                crmproject = em.getReference(crmproject.getClass(), crmproject.getCrmprojectPK());
                crmprojectteammembers.setCrmproject(crmproject);
            }
            Crmprojecttask crmprojecttask = crmprojectteammembers.getCrmprojecttask();
            if (crmprojecttask != null) {
                crmprojecttask = em.getReference(crmprojecttask.getClass(), crmprojecttask.getCrmprojecttaskPK());
                crmprojectteammembers.setCrmprojecttask(crmprojecttask);
            }
            Companyemployee companyemployee = crmprojectteammembers.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmprojectteammembers.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmprojectteammembers.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmprojectteammembers.setCompanyemployee1(companyemployee1);
            }
            Companyemployee companyemployee2 = crmprojectteammembers.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2 = em.getReference(companyemployee2.getClass(), companyemployee2.getCompanyemployeePK());
                crmprojectteammembers.setCompanyemployee2(companyemployee2);
            }
            em.persist(crmprojectteammembers);
            if (crmproject != null) {
                crmproject.getCrmprojectteammembersCollection().add(crmprojectteammembers);
                crmproject = em.merge(crmproject);
            }
            if (crmprojecttask != null) {
                crmprojecttask.getCrmprojectteammembersCollection().add(crmprojectteammembers);
                crmprojecttask = em.merge(crmprojecttask);
            }
            if (companyemployee != null) {
                companyemployee.getCrmprojectteammembersCollection().add(crmprojectteammembers);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectteammembersCollection().add(crmprojectteammembers);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (companyemployee2 != null) {
                companyemployee2.getCrmprojectteammembersCollection().add(crmprojectteammembers);
                companyemployee2 = em.merge(companyemployee2);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmprojectteammembers(crmprojectteammembers.getCrmprojectteammembersPK()) != null) {
                throw new PreexistingEntityException("Crmprojectteammembers " + crmprojectteammembers + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmprojectteammembers crmprojectteammembers) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectteammembers persistentCrmprojectteammembers = em.find(Crmprojectteammembers.class, crmprojectteammembers.getCrmprojectteammembersPK());
            Crmproject crmprojectOld = persistentCrmprojectteammembers.getCrmproject();
            Crmproject crmprojectNew = crmprojectteammembers.getCrmproject();
            Crmprojecttask crmprojecttaskOld = persistentCrmprojectteammembers.getCrmprojecttask();
            Crmprojecttask crmprojecttaskNew = crmprojectteammembers.getCrmprojecttask();
            Companyemployee companyemployeeOld = persistentCrmprojectteammembers.getCompanyemployee();
            Companyemployee companyemployeeNew = crmprojectteammembers.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmprojectteammembers.getCompanyemployee1();
            Companyemployee companyemployee1New = crmprojectteammembers.getCompanyemployee1();
            Companyemployee companyemployee2Old = persistentCrmprojectteammembers.getCompanyemployee2();
            Companyemployee companyemployee2New = crmprojectteammembers.getCompanyemployee2();
            if (crmprojectNew != null) {
                crmprojectNew = em.getReference(crmprojectNew.getClass(), crmprojectNew.getCrmprojectPK());
                crmprojectteammembers.setCrmproject(crmprojectNew);
            }
            if (crmprojecttaskNew != null) {
                crmprojecttaskNew = em.getReference(crmprojecttaskNew.getClass(), crmprojecttaskNew.getCrmprojecttaskPK());
                crmprojectteammembers.setCrmprojecttask(crmprojecttaskNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmprojectteammembers.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmprojectteammembers.setCompanyemployee1(companyemployee1New);
            }
            if (companyemployee2New != null) {
                companyemployee2New = em.getReference(companyemployee2New.getClass(), companyemployee2New.getCompanyemployeePK());
                crmprojectteammembers.setCompanyemployee2(companyemployee2New);
            }
            crmprojectteammembers = em.merge(crmprojectteammembers);
            if (crmprojectOld != null && !crmprojectOld.equals(crmprojectNew)) {
                crmprojectOld.getCrmprojectteammembersCollection().remove(crmprojectteammembers);
                crmprojectOld = em.merge(crmprojectOld);
            }
            if (crmprojectNew != null && !crmprojectNew.equals(crmprojectOld)) {
                crmprojectNew.getCrmprojectteammembersCollection().add(crmprojectteammembers);
                crmprojectNew = em.merge(crmprojectNew);
            }
            if (crmprojecttaskOld != null && !crmprojecttaskOld.equals(crmprojecttaskNew)) {
                crmprojecttaskOld.getCrmprojectteammembersCollection().remove(crmprojectteammembers);
                crmprojecttaskOld = em.merge(crmprojecttaskOld);
            }
            if (crmprojecttaskNew != null && !crmprojecttaskNew.equals(crmprojecttaskOld)) {
                crmprojecttaskNew.getCrmprojectteammembersCollection().add(crmprojectteammembers);
                crmprojecttaskNew = em.merge(crmprojecttaskNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmprojectteammembersCollection().remove(crmprojectteammembers);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmprojectteammembersCollection().add(crmprojectteammembers);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmprojectteammembersCollection().remove(crmprojectteammembers);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmprojectteammembersCollection().add(crmprojectteammembers);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (companyemployee2Old != null && !companyemployee2Old.equals(companyemployee2New)) {
                companyemployee2Old.getCrmprojectteammembersCollection().remove(crmprojectteammembers);
                companyemployee2Old = em.merge(companyemployee2Old);
            }
            if (companyemployee2New != null && !companyemployee2New.equals(companyemployee2Old)) {
                companyemployee2New.getCrmprojectteammembersCollection().add(crmprojectteammembers);
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
                CrmprojectteammembersPK id = crmprojectteammembers.getCrmprojectteammembersPK();
                if (findCrmprojectteammembers(id) == null) {
                    throw new NonexistentEntityException("The crmprojectteammembers with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmprojectteammembersPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectteammembers crmprojectteammembers;
            try {
                crmprojectteammembers = em.getReference(Crmprojectteammembers.class, id);
                crmprojectteammembers.getCrmprojectteammembersPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmprojectteammembers with id " + id + " no longer exists.", enfe);
            }
            Crmproject crmproject = crmprojectteammembers.getCrmproject();
            if (crmproject != null) {
                crmproject.getCrmprojectteammembersCollection().remove(crmprojectteammembers);
                crmproject = em.merge(crmproject);
            }
            Crmprojecttask crmprojecttask = crmprojectteammembers.getCrmprojecttask();
            if (crmprojecttask != null) {
                crmprojecttask.getCrmprojectteammembersCollection().remove(crmprojectteammembers);
                crmprojecttask = em.merge(crmprojecttask);
            }
            Companyemployee companyemployee = crmprojectteammembers.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmprojectteammembersCollection().remove(crmprojectteammembers);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmprojectteammembers.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectteammembersCollection().remove(crmprojectteammembers);
                companyemployee1 = em.merge(companyemployee1);
            }
            Companyemployee companyemployee2 = crmprojectteammembers.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2.getCrmprojectteammembersCollection().remove(crmprojectteammembers);
                companyemployee2 = em.merge(companyemployee2);
            }
            em.remove(crmprojectteammembers);
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

    public List<Crmprojectteammembers> findCrmprojectteammembersEntities() {
        return findCrmprojectteammembersEntities(true, -1, -1);
    }

    public List<Crmprojectteammembers> findCrmprojectteammembersEntities(int maxResults, int firstResult) {
        return findCrmprojectteammembersEntities(false, maxResults, firstResult);
    }

    private List<Crmprojectteammembers> findCrmprojectteammembersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmprojectteammembers.class));
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

    public Crmprojectteammembers findCrmprojectteammembers(CrmprojectteammembersPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmprojectteammembers.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmprojectteammembersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmprojectteammembers> rt = cq.from(Crmprojectteammembers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
