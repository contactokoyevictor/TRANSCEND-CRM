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
import com.sivotek.crm.persistent.dao.entities.Crmmessagechannel;
import com.sivotek.crm.persistent.dao.entities.Crmmessagechanneltemplate;
import com.sivotek.crm.persistent.dao.entities.Crmmessagehistory;
import com.sivotek.crm.persistent.dao.entities.CrmmessagehistoryPK;
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
public class CrmmessagehistoryJpaController implements Serializable {

    public CrmmessagehistoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmmessagehistoryJpaController(){
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

    public void create(Crmmessagehistory crmmessagehistory) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmmessagehistory.getCrmmessagehistoryPK() == null) {
            crmmessagehistory.setCrmmessagehistoryPK(new CrmmessagehistoryPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmessagechannel crmmessagechannel = crmmessagehistory.getCrmmessagechannel();
            if (crmmessagechannel != null) {
                crmmessagechannel = em.getReference(crmmessagechannel.getClass(), crmmessagechannel.getCrmmessagechannelPK());
                crmmessagehistory.setCrmmessagechannel(crmmessagechannel);
            }
            Crmmessagechanneltemplate crmmessagechanneltemplate = crmmessagehistory.getCrmmessagechanneltemplate();
            if (crmmessagechanneltemplate != null) {
                crmmessagechanneltemplate = em.getReference(crmmessagechanneltemplate.getClass(), crmmessagechanneltemplate.getCrmmessagechanneltemplatePK());
                crmmessagehistory.setCrmmessagechanneltemplate(crmmessagechanneltemplate);
            }
            em.persist(crmmessagehistory);
            if (crmmessagechannel != null) {
                crmmessagechannel.getCrmmessagehistoryCollection().add(crmmessagehistory);
                crmmessagechannel = em.merge(crmmessagechannel);
            }
            if (crmmessagechanneltemplate != null) {
                crmmessagechanneltemplate.getCrmmessagehistoryCollection().add(crmmessagehistory);
                crmmessagechanneltemplate = em.merge(crmmessagechanneltemplate);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmmessagehistory(crmmessagehistory.getCrmmessagehistoryPK()) != null) {
                throw new PreexistingEntityException("Crmmessagehistory " + crmmessagehistory + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmmessagehistory crmmessagehistory) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmessagehistory persistentCrmmessagehistory = em.find(Crmmessagehistory.class, crmmessagehistory.getCrmmessagehistoryPK());
            Crmmessagechannel crmmessagechannelOld = persistentCrmmessagehistory.getCrmmessagechannel();
            Crmmessagechannel crmmessagechannelNew = crmmessagehistory.getCrmmessagechannel();
            Crmmessagechanneltemplate crmmessagechanneltemplateOld = persistentCrmmessagehistory.getCrmmessagechanneltemplate();
            Crmmessagechanneltemplate crmmessagechanneltemplateNew = crmmessagehistory.getCrmmessagechanneltemplate();
            if (crmmessagechannelNew != null) {
                crmmessagechannelNew = em.getReference(crmmessagechannelNew.getClass(), crmmessagechannelNew.getCrmmessagechannelPK());
                crmmessagehistory.setCrmmessagechannel(crmmessagechannelNew);
            }
            if (crmmessagechanneltemplateNew != null) {
                crmmessagechanneltemplateNew = em.getReference(crmmessagechanneltemplateNew.getClass(), crmmessagechanneltemplateNew.getCrmmessagechanneltemplatePK());
                crmmessagehistory.setCrmmessagechanneltemplate(crmmessagechanneltemplateNew);
            }
            crmmessagehistory = em.merge(crmmessagehistory);
            if (crmmessagechannelOld != null && !crmmessagechannelOld.equals(crmmessagechannelNew)) {
                crmmessagechannelOld.getCrmmessagehistoryCollection().remove(crmmessagehistory);
                crmmessagechannelOld = em.merge(crmmessagechannelOld);
            }
            if (crmmessagechannelNew != null && !crmmessagechannelNew.equals(crmmessagechannelOld)) {
                crmmessagechannelNew.getCrmmessagehistoryCollection().add(crmmessagehistory);
                crmmessagechannelNew = em.merge(crmmessagechannelNew);
            }
            if (crmmessagechanneltemplateOld != null && !crmmessagechanneltemplateOld.equals(crmmessagechanneltemplateNew)) {
                crmmessagechanneltemplateOld.getCrmmessagehistoryCollection().remove(crmmessagehistory);
                crmmessagechanneltemplateOld = em.merge(crmmessagechanneltemplateOld);
            }
            if (crmmessagechanneltemplateNew != null && !crmmessagechanneltemplateNew.equals(crmmessagechanneltemplateOld)) {
                crmmessagechanneltemplateNew.getCrmmessagehistoryCollection().add(crmmessagehistory);
                crmmessagechanneltemplateNew = em.merge(crmmessagechanneltemplateNew);
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
                CrmmessagehistoryPK id = crmmessagehistory.getCrmmessagehistoryPK();
                if (findCrmmessagehistory(id) == null) {
                    throw new NonexistentEntityException("The crmmessagehistory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmmessagehistoryPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmessagehistory crmmessagehistory;
            try {
                crmmessagehistory = em.getReference(Crmmessagehistory.class, id);
                crmmessagehistory.getCrmmessagehistoryPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmmessagehistory with id " + id + " no longer exists.", enfe);
            }
            Crmmessagechannel crmmessagechannel = crmmessagehistory.getCrmmessagechannel();
            if (crmmessagechannel != null) {
                crmmessagechannel.getCrmmessagehistoryCollection().remove(crmmessagehistory);
                crmmessagechannel = em.merge(crmmessagechannel);
            }
            Crmmessagechanneltemplate crmmessagechanneltemplate = crmmessagehistory.getCrmmessagechanneltemplate();
            if (crmmessagechanneltemplate != null) {
                crmmessagechanneltemplate.getCrmmessagehistoryCollection().remove(crmmessagehistory);
                crmmessagechanneltemplate = em.merge(crmmessagechanneltemplate);
            }
            em.remove(crmmessagehistory);
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

    public List<Crmmessagehistory> findCrmmessagehistoryEntities() {
        return findCrmmessagehistoryEntities(true, -1, -1);
    }

    public List<Crmmessagehistory> findCrmmessagehistoryEntities(int maxResults, int firstResult) {
        return findCrmmessagehistoryEntities(false, maxResults, firstResult);
    }

    private List<Crmmessagehistory> findCrmmessagehistoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmmessagehistory.class));
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

    public Crmmessagehistory findCrmmessagehistory(CrmmessagehistoryPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmmessagehistory.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmmessagehistoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmmessagehistory> rt = cq.from(Crmmessagehistory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
