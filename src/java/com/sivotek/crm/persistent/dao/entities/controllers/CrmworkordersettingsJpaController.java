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
import com.sivotek.crm.persistent.dao.entities.Crmworkordersettings;
import com.sivotek.crm.persistent.dao.entities.CrmworkordersettingsPK;
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
public class CrmworkordersettingsJpaController implements Serializable {

    public CrmworkordersettingsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmworkordersettingsJpaController(){
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

    public void create(Crmworkordersettings crmworkordersettings) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmworkordersettings.getCrmworkordersettingsPK() == null) {
            crmworkordersettings.setCrmworkordersettingsPK(new CrmworkordersettingsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = crmworkordersettings.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmworkordersettings.setCompanyemployee(companyemployee);
            }
            em.persist(crmworkordersettings);
            if (companyemployee != null) {
                companyemployee.getCrmworkordersettingsCollection().add(crmworkordersettings);
                companyemployee = em.merge(companyemployee);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmworkordersettings(crmworkordersettings.getCrmworkordersettingsPK()) != null) {
                throw new PreexistingEntityException("Crmworkordersettings " + crmworkordersettings + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmworkordersettings crmworkordersettings) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmworkordersettings persistentCrmworkordersettings = em.find(Crmworkordersettings.class, crmworkordersettings.getCrmworkordersettingsPK());
            Companyemployee companyemployeeOld = persistentCrmworkordersettings.getCompanyemployee();
            Companyemployee companyemployeeNew = crmworkordersettings.getCompanyemployee();
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmworkordersettings.setCompanyemployee(companyemployeeNew);
            }
            crmworkordersettings = em.merge(crmworkordersettings);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmworkordersettingsCollection().remove(crmworkordersettings);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmworkordersettingsCollection().add(crmworkordersettings);
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
                CrmworkordersettingsPK id = crmworkordersettings.getCrmworkordersettingsPK();
                if (findCrmworkordersettings(id) == null) {
                    throw new NonexistentEntityException("The crmworkordersettings with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmworkordersettingsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmworkordersettings crmworkordersettings;
            try {
                crmworkordersettings = em.getReference(Crmworkordersettings.class, id);
                crmworkordersettings.getCrmworkordersettingsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmworkordersettings with id " + id + " no longer exists.", enfe);
            }
            Companyemployee companyemployee = crmworkordersettings.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmworkordersettingsCollection().remove(crmworkordersettings);
                companyemployee = em.merge(companyemployee);
            }
            em.remove(crmworkordersettings);
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

    public List<Crmworkordersettings> findCrmworkordersettingsEntities() {
        return findCrmworkordersettingsEntities(true, -1, -1);
    }

    public List<Crmworkordersettings> findCrmworkordersettingsEntities(int maxResults, int firstResult) {
        return findCrmworkordersettingsEntities(false, maxResults, firstResult);
    }

    private List<Crmworkordersettings> findCrmworkordersettingsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmworkordersettings.class));
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

    public Crmworkordersettings findCrmworkordersettings(CrmworkordersettingsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmworkordersettings.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmworkordersettingsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmworkordersettings> rt = cq.from(Crmworkordersettings.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
