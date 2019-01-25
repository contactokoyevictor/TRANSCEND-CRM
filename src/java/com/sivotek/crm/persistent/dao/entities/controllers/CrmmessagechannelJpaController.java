/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import com.sivotek.crm.persistent.dao.entities.Crmmessagechannel;
import com.sivotek.crm.persistent.dao.entities.CrmmessagechannelPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.crm.persistent.dao.entities.Crmmessagechanneltemplate;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmmessagehistory;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.IllegalOrphanException;
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
public class CrmmessagechannelJpaController implements Serializable {

    public CrmmessagechannelJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmmessagechannelJpaController(){
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

    public void create(Crmmessagechannel crmmessagechannel) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmmessagechannel.getCrmmessagechannelPK() == null) {
            crmmessagechannel.setCrmmessagechannelPK(new CrmmessagechannelPK());
        }
        if (crmmessagechannel.getCrmmessagechanneltemplateCollection() == null) {
            crmmessagechannel.setCrmmessagechanneltemplateCollection(new ArrayList<Crmmessagechanneltemplate>());
        }
        if (crmmessagechannel.getCrmmessagehistoryCollection() == null) {
            crmmessagechannel.setCrmmessagehistoryCollection(new ArrayList<Crmmessagehistory>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Crmmessagechanneltemplate> attachedCrmmessagechanneltemplateCollection = new ArrayList<Crmmessagechanneltemplate>();
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionCrmmessagechanneltemplateToAttach : crmmessagechannel.getCrmmessagechanneltemplateCollection()) {
                crmmessagechanneltemplateCollectionCrmmessagechanneltemplateToAttach = em.getReference(crmmessagechanneltemplateCollectionCrmmessagechanneltemplateToAttach.getClass(), crmmessagechanneltemplateCollectionCrmmessagechanneltemplateToAttach.getCrmmessagechanneltemplatePK());
                attachedCrmmessagechanneltemplateCollection.add(crmmessagechanneltemplateCollectionCrmmessagechanneltemplateToAttach);
            }
            crmmessagechannel.setCrmmessagechanneltemplateCollection(attachedCrmmessagechanneltemplateCollection);
            Collection<Crmmessagehistory> attachedCrmmessagehistoryCollection = new ArrayList<Crmmessagehistory>();
            for (Crmmessagehistory crmmessagehistoryCollectionCrmmessagehistoryToAttach : crmmessagechannel.getCrmmessagehistoryCollection()) {
                crmmessagehistoryCollectionCrmmessagehistoryToAttach = em.getReference(crmmessagehistoryCollectionCrmmessagehistoryToAttach.getClass(), crmmessagehistoryCollectionCrmmessagehistoryToAttach.getCrmmessagehistoryPK());
                attachedCrmmessagehistoryCollection.add(crmmessagehistoryCollectionCrmmessagehistoryToAttach);
            }
            crmmessagechannel.setCrmmessagehistoryCollection(attachedCrmmessagehistoryCollection);
            em.persist(crmmessagechannel);
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionCrmmessagechanneltemplate : crmmessagechannel.getCrmmessagechanneltemplateCollection()) {
                Crmmessagechannel oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionCrmmessagechanneltemplate = crmmessagechanneltemplateCollectionCrmmessagechanneltemplate.getCrmmessagechannel();
                crmmessagechanneltemplateCollectionCrmmessagechanneltemplate.setCrmmessagechannel(crmmessagechannel);
                crmmessagechanneltemplateCollectionCrmmessagechanneltemplate = em.merge(crmmessagechanneltemplateCollectionCrmmessagechanneltemplate);
                if (oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionCrmmessagechanneltemplate != null) {
                    oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionCrmmessagechanneltemplate.getCrmmessagechanneltemplateCollection().remove(crmmessagechanneltemplateCollectionCrmmessagechanneltemplate);
                    oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionCrmmessagechanneltemplate = em.merge(oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionCrmmessagechanneltemplate);
                }
            }
            for (Crmmessagehistory crmmessagehistoryCollectionCrmmessagehistory : crmmessagechannel.getCrmmessagehistoryCollection()) {
                Crmmessagechannel oldCrmmessagechannelOfCrmmessagehistoryCollectionCrmmessagehistory = crmmessagehistoryCollectionCrmmessagehistory.getCrmmessagechannel();
                crmmessagehistoryCollectionCrmmessagehistory.setCrmmessagechannel(crmmessagechannel);
                crmmessagehistoryCollectionCrmmessagehistory = em.merge(crmmessagehistoryCollectionCrmmessagehistory);
                if (oldCrmmessagechannelOfCrmmessagehistoryCollectionCrmmessagehistory != null) {
                    oldCrmmessagechannelOfCrmmessagehistoryCollectionCrmmessagehistory.getCrmmessagehistoryCollection().remove(crmmessagehistoryCollectionCrmmessagehistory);
                    oldCrmmessagechannelOfCrmmessagehistoryCollectionCrmmessagehistory = em.merge(oldCrmmessagechannelOfCrmmessagehistoryCollectionCrmmessagehistory);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmmessagechannel(crmmessagechannel.getCrmmessagechannelPK()) != null) {
                throw new PreexistingEntityException("Crmmessagechannel " + crmmessagechannel + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmmessagechannel crmmessagechannel) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmessagechannel persistentCrmmessagechannel = em.find(Crmmessagechannel.class, crmmessagechannel.getCrmmessagechannelPK());
            Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollectionOld = persistentCrmmessagechannel.getCrmmessagechanneltemplateCollection();
            Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollectionNew = crmmessagechannel.getCrmmessagechanneltemplateCollection();
            Collection<Crmmessagehistory> crmmessagehistoryCollectionOld = persistentCrmmessagechannel.getCrmmessagehistoryCollection();
            Collection<Crmmessagehistory> crmmessagehistoryCollectionNew = crmmessagechannel.getCrmmessagehistoryCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionOldCrmmessagechanneltemplate : crmmessagechanneltemplateCollectionOld) {
                if (!crmmessagechanneltemplateCollectionNew.contains(crmmessagechanneltemplateCollectionOldCrmmessagechanneltemplate)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagechanneltemplate " + crmmessagechanneltemplateCollectionOldCrmmessagechanneltemplate + " since its crmmessagechannel field is not nullable.");
                }
            }
            for (Crmmessagehistory crmmessagehistoryCollectionOldCrmmessagehistory : crmmessagehistoryCollectionOld) {
                if (!crmmessagehistoryCollectionNew.contains(crmmessagehistoryCollectionOldCrmmessagehistory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagehistory " + crmmessagehistoryCollectionOldCrmmessagehistory + " since its crmmessagechannel field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Crmmessagechanneltemplate> attachedCrmmessagechanneltemplateCollectionNew = new ArrayList<Crmmessagechanneltemplate>();
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplateToAttach : crmmessagechanneltemplateCollectionNew) {
                crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplateToAttach = em.getReference(crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplateToAttach.getClass(), crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplateToAttach.getCrmmessagechanneltemplatePK());
                attachedCrmmessagechanneltemplateCollectionNew.add(crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplateToAttach);
            }
            crmmessagechanneltemplateCollectionNew = attachedCrmmessagechanneltemplateCollectionNew;
            crmmessagechannel.setCrmmessagechanneltemplateCollection(crmmessagechanneltemplateCollectionNew);
            Collection<Crmmessagehistory> attachedCrmmessagehistoryCollectionNew = new ArrayList<Crmmessagehistory>();
            for (Crmmessagehistory crmmessagehistoryCollectionNewCrmmessagehistoryToAttach : crmmessagehistoryCollectionNew) {
                crmmessagehistoryCollectionNewCrmmessagehistoryToAttach = em.getReference(crmmessagehistoryCollectionNewCrmmessagehistoryToAttach.getClass(), crmmessagehistoryCollectionNewCrmmessagehistoryToAttach.getCrmmessagehistoryPK());
                attachedCrmmessagehistoryCollectionNew.add(crmmessagehistoryCollectionNewCrmmessagehistoryToAttach);
            }
            crmmessagehistoryCollectionNew = attachedCrmmessagehistoryCollectionNew;
            crmmessagechannel.setCrmmessagehistoryCollection(crmmessagehistoryCollectionNew);
            crmmessagechannel = em.merge(crmmessagechannel);
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate : crmmessagechanneltemplateCollectionNew) {
                if (!crmmessagechanneltemplateCollectionOld.contains(crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate)) {
                    Crmmessagechannel oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate = crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate.getCrmmessagechannel();
                    crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate.setCrmmessagechannel(crmmessagechannel);
                    crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate = em.merge(crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate);
                    if (oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate != null && !oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate.equals(crmmessagechannel)) {
                        oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate.getCrmmessagechanneltemplateCollection().remove(crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate);
                        oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate = em.merge(oldCrmmessagechannelOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate);
                    }
                }
            }
            for (Crmmessagehistory crmmessagehistoryCollectionNewCrmmessagehistory : crmmessagehistoryCollectionNew) {
                if (!crmmessagehistoryCollectionOld.contains(crmmessagehistoryCollectionNewCrmmessagehistory)) {
                    Crmmessagechannel oldCrmmessagechannelOfCrmmessagehistoryCollectionNewCrmmessagehistory = crmmessagehistoryCollectionNewCrmmessagehistory.getCrmmessagechannel();
                    crmmessagehistoryCollectionNewCrmmessagehistory.setCrmmessagechannel(crmmessagechannel);
                    crmmessagehistoryCollectionNewCrmmessagehistory = em.merge(crmmessagehistoryCollectionNewCrmmessagehistory);
                    if (oldCrmmessagechannelOfCrmmessagehistoryCollectionNewCrmmessagehistory != null && !oldCrmmessagechannelOfCrmmessagehistoryCollectionNewCrmmessagehistory.equals(crmmessagechannel)) {
                        oldCrmmessagechannelOfCrmmessagehistoryCollectionNewCrmmessagehistory.getCrmmessagehistoryCollection().remove(crmmessagehistoryCollectionNewCrmmessagehistory);
                        oldCrmmessagechannelOfCrmmessagehistoryCollectionNewCrmmessagehistory = em.merge(oldCrmmessagechannelOfCrmmessagehistoryCollectionNewCrmmessagehistory);
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
                CrmmessagechannelPK id = crmmessagechannel.getCrmmessagechannelPK();
                if (findCrmmessagechannel(id) == null) {
                    throw new NonexistentEntityException("The crmmessagechannel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmmessagechannelPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmessagechannel crmmessagechannel;
            try {
                crmmessagechannel = em.getReference(Crmmessagechannel.class, id);
                crmmessagechannel.getCrmmessagechannelPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmmessagechannel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollectionOrphanCheck = crmmessagechannel.getCrmmessagechanneltemplateCollection();
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionOrphanCheckCrmmessagechanneltemplate : crmmessagechanneltemplateCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmmessagechannel (" + crmmessagechannel + ") cannot be destroyed since the Crmmessagechanneltemplate " + crmmessagechanneltemplateCollectionOrphanCheckCrmmessagechanneltemplate + " in its crmmessagechanneltemplateCollection field has a non-nullable crmmessagechannel field.");
            }
            Collection<Crmmessagehistory> crmmessagehistoryCollectionOrphanCheck = crmmessagechannel.getCrmmessagehistoryCollection();
            for (Crmmessagehistory crmmessagehistoryCollectionOrphanCheckCrmmessagehistory : crmmessagehistoryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmmessagechannel (" + crmmessagechannel + ") cannot be destroyed since the Crmmessagehistory " + crmmessagehistoryCollectionOrphanCheckCrmmessagehistory + " in its crmmessagehistoryCollection field has a non-nullable crmmessagechannel field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(crmmessagechannel);
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

    public List<Crmmessagechannel> findCrmmessagechannelEntities() {
        return findCrmmessagechannelEntities(true, -1, -1);
    }

    public List<Crmmessagechannel> findCrmmessagechannelEntities(int maxResults, int firstResult) {
        return findCrmmessagechannelEntities(false, maxResults, firstResult);
    }

    private List<Crmmessagechannel> findCrmmessagechannelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmmessagechannel.class));
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

    public Crmmessagechannel findCrmmessagechannel(CrmmessagechannelPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmmessagechannel.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmmessagechannelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmmessagechannel> rt = cq.from(Crmmessagechannel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
