/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import com.sivotek.crm.persistent.dao.entities.Crmcampaignreceiver;
import com.sivotek.crm.persistent.dao.entities.CrmcampaignreceiverPK;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author okoyevictor
 */
public class CrmcampaignreceiverJpaController implements Serializable {

    public CrmcampaignreceiverJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmcampaignreceiverJpaController(){
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
    
    public void create(Crmcampaignreceiver crmcampaignreceiver) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmcampaignreceiver.getCrmcampaignreceiverPK() == null) {
            crmcampaignreceiver.setCrmcampaignreceiverPK(new CrmcampaignreceiverPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(crmcampaignreceiver);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmcampaignreceiver(crmcampaignreceiver.getCrmcampaignreceiverPK()) != null) {
                throw new PreexistingEntityException("Crmcampaignreceiver " + crmcampaignreceiver + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmcampaignreceiver crmcampaignreceiver) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            crmcampaignreceiver = em.merge(crmcampaignreceiver);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CrmcampaignreceiverPK id = crmcampaignreceiver.getCrmcampaignreceiverPK();
                if (findCrmcampaignreceiver(id) == null) {
                    throw new NonexistentEntityException("The crmcampaignreceiver with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmcampaignreceiverPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaignreceiver crmcampaignreceiver;
            try {
                crmcampaignreceiver = em.getReference(Crmcampaignreceiver.class, id);
                crmcampaignreceiver.getCrmcampaignreceiverPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmcampaignreceiver with id " + id + " no longer exists.", enfe);
            }
            em.remove(crmcampaignreceiver);
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

    public List<Crmcampaignreceiver> findCrmcampaignreceiverEntities() {
        return findCrmcampaignreceiverEntities(true, -1, -1);
    }

    public List<Crmcampaignreceiver> findCrmcampaignreceiverEntities(int maxResults, int firstResult) {
        return findCrmcampaignreceiverEntities(false, maxResults, firstResult);
    }

    private List<Crmcampaignreceiver> findCrmcampaignreceiverEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmcampaignreceiver.class));
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

    public Crmcampaignreceiver findCrmcampaignreceiver(CrmcampaignreceiverPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmcampaignreceiver.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmcampaignreceiverCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmcampaignreceiver> rt = cq.from(Crmcampaignreceiver.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
