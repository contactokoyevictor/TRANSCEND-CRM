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
import com.sivotek.crm.persistent.dao.entities.CrmmessagechanneltemplatePK;
import com.sivotek.crm.persistent.dao.entities.Crmmessagehistory;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
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
public class CrmmessagechanneltemplateJpaController implements Serializable {

    public CrmmessagechanneltemplateJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmmessagechanneltemplateJpaController(){
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

    public void create(Crmmessagechanneltemplate crmmessagechanneltemplate) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmmessagechanneltemplate.getCrmmessagechanneltemplatePK() == null) {
            crmmessagechanneltemplate.setCrmmessagechanneltemplatePK(new CrmmessagechanneltemplatePK());
        }
        if (crmmessagechanneltemplate.getCrmmessagehistoryCollection() == null) {
            crmmessagechanneltemplate.setCrmmessagehistoryCollection(new ArrayList<Crmmessagehistory>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmessagechannel crmmessagechannel = crmmessagechanneltemplate.getCrmmessagechannel();
            if (crmmessagechannel != null) {
                crmmessagechannel = em.getReference(crmmessagechannel.getClass(), crmmessagechannel.getCrmmessagechannelPK());
                crmmessagechanneltemplate.setCrmmessagechannel(crmmessagechannel);
            }
            Collection<Crmmessagehistory> attachedCrmmessagehistoryCollection = new ArrayList<Crmmessagehistory>();
            for (Crmmessagehistory crmmessagehistoryCollectionCrmmessagehistoryToAttach : crmmessagechanneltemplate.getCrmmessagehistoryCollection()) {
                crmmessagehistoryCollectionCrmmessagehistoryToAttach = em.getReference(crmmessagehistoryCollectionCrmmessagehistoryToAttach.getClass(), crmmessagehistoryCollectionCrmmessagehistoryToAttach.getCrmmessagehistoryPK());
                attachedCrmmessagehistoryCollection.add(crmmessagehistoryCollectionCrmmessagehistoryToAttach);
            }
            crmmessagechanneltemplate.setCrmmessagehistoryCollection(attachedCrmmessagehistoryCollection);
            em.persist(crmmessagechanneltemplate);
            if (crmmessagechannel != null) {
                crmmessagechannel.getCrmmessagechanneltemplateCollection().add(crmmessagechanneltemplate);
                crmmessagechannel = em.merge(crmmessagechannel);
            }
            for (Crmmessagehistory crmmessagehistoryCollectionCrmmessagehistory : crmmessagechanneltemplate.getCrmmessagehistoryCollection()) {
                Crmmessagechanneltemplate oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionCrmmessagehistory = crmmessagehistoryCollectionCrmmessagehistory.getCrmmessagechanneltemplate();
                crmmessagehistoryCollectionCrmmessagehistory.setCrmmessagechanneltemplate(crmmessagechanneltemplate);
                crmmessagehistoryCollectionCrmmessagehistory = em.merge(crmmessagehistoryCollectionCrmmessagehistory);
                if (oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionCrmmessagehistory != null) {
                    oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionCrmmessagehistory.getCrmmessagehistoryCollection().remove(crmmessagehistoryCollectionCrmmessagehistory);
                    oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionCrmmessagehistory = em.merge(oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionCrmmessagehistory);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmmessagechanneltemplate(crmmessagechanneltemplate.getCrmmessagechanneltemplatePK()) != null) {
                throw new PreexistingEntityException("Crmmessagechanneltemplate " + crmmessagechanneltemplate + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmmessagechanneltemplate crmmessagechanneltemplate) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmessagechanneltemplate persistentCrmmessagechanneltemplate = em.find(Crmmessagechanneltemplate.class, crmmessagechanneltemplate.getCrmmessagechanneltemplatePK());
            Crmmessagechannel crmmessagechannelOld = persistentCrmmessagechanneltemplate.getCrmmessagechannel();
            Crmmessagechannel crmmessagechannelNew = crmmessagechanneltemplate.getCrmmessagechannel();
            Collection<Crmmessagehistory> crmmessagehistoryCollectionOld = persistentCrmmessagechanneltemplate.getCrmmessagehistoryCollection();
            Collection<Crmmessagehistory> crmmessagehistoryCollectionNew = crmmessagechanneltemplate.getCrmmessagehistoryCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmmessagehistory crmmessagehistoryCollectionOldCrmmessagehistory : crmmessagehistoryCollectionOld) {
                if (!crmmessagehistoryCollectionNew.contains(crmmessagehistoryCollectionOldCrmmessagehistory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagehistory " + crmmessagehistoryCollectionOldCrmmessagehistory + " since its crmmessagechanneltemplate field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (crmmessagechannelNew != null) {
                crmmessagechannelNew = em.getReference(crmmessagechannelNew.getClass(), crmmessagechannelNew.getCrmmessagechannelPK());
                crmmessagechanneltemplate.setCrmmessagechannel(crmmessagechannelNew);
            }
            Collection<Crmmessagehistory> attachedCrmmessagehistoryCollectionNew = new ArrayList<Crmmessagehistory>();
            for (Crmmessagehistory crmmessagehistoryCollectionNewCrmmessagehistoryToAttach : crmmessagehistoryCollectionNew) {
                crmmessagehistoryCollectionNewCrmmessagehistoryToAttach = em.getReference(crmmessagehistoryCollectionNewCrmmessagehistoryToAttach.getClass(), crmmessagehistoryCollectionNewCrmmessagehistoryToAttach.getCrmmessagehistoryPK());
                attachedCrmmessagehistoryCollectionNew.add(crmmessagehistoryCollectionNewCrmmessagehistoryToAttach);
            }
            crmmessagehistoryCollectionNew = attachedCrmmessagehistoryCollectionNew;
            crmmessagechanneltemplate.setCrmmessagehistoryCollection(crmmessagehistoryCollectionNew);
            crmmessagechanneltemplate = em.merge(crmmessagechanneltemplate);
            if (crmmessagechannelOld != null && !crmmessagechannelOld.equals(crmmessagechannelNew)) {
                crmmessagechannelOld.getCrmmessagechanneltemplateCollection().remove(crmmessagechanneltemplate);
                crmmessagechannelOld = em.merge(crmmessagechannelOld);
            }
            if (crmmessagechannelNew != null && !crmmessagechannelNew.equals(crmmessagechannelOld)) {
                crmmessagechannelNew.getCrmmessagechanneltemplateCollection().add(crmmessagechanneltemplate);
                crmmessagechannelNew = em.merge(crmmessagechannelNew);
            }
            for (Crmmessagehistory crmmessagehistoryCollectionNewCrmmessagehistory : crmmessagehistoryCollectionNew) {
                if (!crmmessagehistoryCollectionOld.contains(crmmessagehistoryCollectionNewCrmmessagehistory)) {
                    Crmmessagechanneltemplate oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionNewCrmmessagehistory = crmmessagehistoryCollectionNewCrmmessagehistory.getCrmmessagechanneltemplate();
                    crmmessagehistoryCollectionNewCrmmessagehistory.setCrmmessagechanneltemplate(crmmessagechanneltemplate);
                    crmmessagehistoryCollectionNewCrmmessagehistory = em.merge(crmmessagehistoryCollectionNewCrmmessagehistory);
                    if (oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionNewCrmmessagehistory != null && !oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionNewCrmmessagehistory.equals(crmmessagechanneltemplate)) {
                        oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionNewCrmmessagehistory.getCrmmessagehistoryCollection().remove(crmmessagehistoryCollectionNewCrmmessagehistory);
                        oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionNewCrmmessagehistory = em.merge(oldCrmmessagechanneltemplateOfCrmmessagehistoryCollectionNewCrmmessagehistory);
                    }
                }
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
                CrmmessagechanneltemplatePK id = crmmessagechanneltemplate.getCrmmessagechanneltemplatePK();
                if (findCrmmessagechanneltemplate(id) == null) {
                    throw new NonexistentEntityException("The crmmessagechanneltemplate with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmmessagechanneltemplatePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmessagechanneltemplate crmmessagechanneltemplate;
            try {
                crmmessagechanneltemplate = em.getReference(Crmmessagechanneltemplate.class, id);
                crmmessagechanneltemplate.getCrmmessagechanneltemplatePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmmessagechanneltemplate with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmmessagehistory> crmmessagehistoryCollectionOrphanCheck = crmmessagechanneltemplate.getCrmmessagehistoryCollection();
            for (Crmmessagehistory crmmessagehistoryCollectionOrphanCheckCrmmessagehistory : crmmessagehistoryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmmessagechanneltemplate (" + crmmessagechanneltemplate + ") cannot be destroyed since the Crmmessagehistory " + crmmessagehistoryCollectionOrphanCheckCrmmessagehistory + " in its crmmessagehistoryCollection field has a non-nullable crmmessagechanneltemplate field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Crmmessagechannel crmmessagechannel = crmmessagechanneltemplate.getCrmmessagechannel();
            if (crmmessagechannel != null) {
                crmmessagechannel.getCrmmessagechanneltemplateCollection().remove(crmmessagechanneltemplate);
                crmmessagechannel = em.merge(crmmessagechannel);
            }
            em.remove(crmmessagechanneltemplate);
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

    public List<Crmmessagechanneltemplate> findCrmmessagechanneltemplateEntities() {
        return findCrmmessagechanneltemplateEntities(true, -1, -1);
    }

    public List<Crmmessagechanneltemplate> findCrmmessagechanneltemplateEntities(int maxResults, int firstResult) {
        return findCrmmessagechanneltemplateEntities(false, maxResults, firstResult);
    }

    private List<Crmmessagechanneltemplate> findCrmmessagechanneltemplateEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmmessagechanneltemplate.class));
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

    public Crmmessagechanneltemplate findCrmmessagechanneltemplate(CrmmessagechanneltemplatePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmmessagechanneltemplate.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmmessagechanneltemplateCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmmessagechanneltemplate> rt = cq.from(Crmmessagechanneltemplate.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
