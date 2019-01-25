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
import com.sivotek.crm.persistent.dao.entities.Crmprofilesettings;
import com.sivotek.crm.persistent.dao.entities.CrmprofilesettingsPK;
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
public class CrmprofilesettingsJpaController implements Serializable {

    public CrmprofilesettingsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmprofilesettingsJpaController(){
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

    public void create(Crmprofilesettings crmprofilesettings) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmprofilesettings.getCrmprofilesettingsPK() == null) {
            crmprofilesettings.setCrmprofilesettingsPK(new CrmprofilesettingsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = crmprofilesettings.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmprofilesettings.setCompanyemployee(companyemployee);
            }
            em.persist(crmprofilesettings);
            if (companyemployee != null) {
                companyemployee.getCrmprofilesettingsCollection().add(crmprofilesettings);
                companyemployee = em.merge(companyemployee);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmprofilesettings(crmprofilesettings.getCrmprofilesettingsPK()) != null) {
                throw new PreexistingEntityException("Crmprofilesettings " + crmprofilesettings + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmprofilesettings crmprofilesettings) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprofilesettings persistentCrmprofilesettings = em.find(Crmprofilesettings.class, crmprofilesettings.getCrmprofilesettingsPK());
            Companyemployee companyemployeeOld = persistentCrmprofilesettings.getCompanyemployee();
            Companyemployee companyemployeeNew = crmprofilesettings.getCompanyemployee();
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmprofilesettings.setCompanyemployee(companyemployeeNew);
            }
            crmprofilesettings = em.merge(crmprofilesettings);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmprofilesettingsCollection().remove(crmprofilesettings);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmprofilesettingsCollection().add(crmprofilesettings);
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
                CrmprofilesettingsPK id = crmprofilesettings.getCrmprofilesettingsPK();
                if (findCrmprofilesettings(id) == null) {
                    throw new NonexistentEntityException("The crmprofilesettings with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmprofilesettingsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprofilesettings crmprofilesettings;
            try {
                crmprofilesettings = em.getReference(Crmprofilesettings.class, id);
                crmprofilesettings.getCrmprofilesettingsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmprofilesettings with id " + id + " no longer exists.", enfe);
            }
            Companyemployee companyemployee = crmprofilesettings.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmprofilesettingsCollection().remove(crmprofilesettings);
                companyemployee = em.merge(companyemployee);
            }
            em.remove(crmprofilesettings);
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

    public List<Crmprofilesettings> findCrmprofilesettingsEntities() {
        return findCrmprofilesettingsEntities(true, -1, -1);
    }

    public List<Crmprofilesettings> findCrmprofilesettingsEntities(int maxResults, int firstResult) {
        return findCrmprofilesettingsEntities(false, maxResults, firstResult);
    }

    private List<Crmprofilesettings> findCrmprofilesettingsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmprofilesettings.class));
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

    public Crmprofilesettings findCrmprofilesettings(CrmprofilesettingsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmprofilesettings.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmprofilesettingsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmprofilesettings> rt = cq.from(Crmprofilesettings.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
