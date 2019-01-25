/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import com.sivotek.crm.persistent.dao.entities.Crmmodules;
import com.sivotek.crm.persistent.dao.entities.CrmmodulesPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.crm.persistent.dao.entities.Crmusermodules;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmsubmodules;
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
import javax.transaction.UserTransaction;
import javax.persistence.Persistence;
/**
 *
 * @author okoyevictor
 */
public class CrmmodulesJpaController implements Serializable {

    public CrmmodulesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmmodulesJpaController(){
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


    public void create(Crmmodules crmmodules) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmmodules.getCrmmodulesPK() == null) {
            crmmodules.setCrmmodulesPK(new CrmmodulesPK());
        }
        if (crmmodules.getCrmusermodulesCollection() == null) {
            crmmodules.setCrmusermodulesCollection(new ArrayList<Crmusermodules>());
        }
        if (crmmodules.getCrmsubmodulesCollection() == null) {
            crmmodules.setCrmsubmodulesCollection(new ArrayList<Crmsubmodules>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Crmusermodules> attachedCrmusermodulesCollection = new ArrayList<Crmusermodules>();
            for (Crmusermodules crmusermodulesCollectionCrmusermodulesToAttach : crmmodules.getCrmusermodulesCollection()) {
                crmusermodulesCollectionCrmusermodulesToAttach = em.getReference(crmusermodulesCollectionCrmusermodulesToAttach.getClass(), crmusermodulesCollectionCrmusermodulesToAttach.getCrmusermodulesPK());
                attachedCrmusermodulesCollection.add(crmusermodulesCollectionCrmusermodulesToAttach);
            }
            crmmodules.setCrmusermodulesCollection(attachedCrmusermodulesCollection);
            Collection<Crmsubmodules> attachedCrmsubmodulesCollection = new ArrayList<Crmsubmodules>();
            for (Crmsubmodules crmsubmodulesCollectionCrmsubmodulesToAttach : crmmodules.getCrmsubmodulesCollection()) {
                crmsubmodulesCollectionCrmsubmodulesToAttach = em.getReference(crmsubmodulesCollectionCrmsubmodulesToAttach.getClass(), crmsubmodulesCollectionCrmsubmodulesToAttach.getCrmsubmodulesPK());
                attachedCrmsubmodulesCollection.add(crmsubmodulesCollectionCrmsubmodulesToAttach);
            }
            crmmodules.setCrmsubmodulesCollection(attachedCrmsubmodulesCollection);
            em.persist(crmmodules);
            for (Crmusermodules crmusermodulesCollectionCrmusermodules : crmmodules.getCrmusermodulesCollection()) {
                Crmmodules oldCrmmodulesOfCrmusermodulesCollectionCrmusermodules = crmusermodulesCollectionCrmusermodules.getCrmmodules();
                crmusermodulesCollectionCrmusermodules.setCrmmodules(crmmodules);
                crmusermodulesCollectionCrmusermodules = em.merge(crmusermodulesCollectionCrmusermodules);
                if (oldCrmmodulesOfCrmusermodulesCollectionCrmusermodules != null) {
                    oldCrmmodulesOfCrmusermodulesCollectionCrmusermodules.getCrmusermodulesCollection().remove(crmusermodulesCollectionCrmusermodules);
                    oldCrmmodulesOfCrmusermodulesCollectionCrmusermodules = em.merge(oldCrmmodulesOfCrmusermodulesCollectionCrmusermodules);
                }
            }
            for (Crmsubmodules crmsubmodulesCollectionCrmsubmodules : crmmodules.getCrmsubmodulesCollection()) {
                Crmmodules oldCrmmodulesOfCrmsubmodulesCollectionCrmsubmodules = crmsubmodulesCollectionCrmsubmodules.getCrmmodules();
                crmsubmodulesCollectionCrmsubmodules.setCrmmodules(crmmodules);
                crmsubmodulesCollectionCrmsubmodules = em.merge(crmsubmodulesCollectionCrmsubmodules);
                if (oldCrmmodulesOfCrmsubmodulesCollectionCrmsubmodules != null) {
                    oldCrmmodulesOfCrmsubmodulesCollectionCrmsubmodules.getCrmsubmodulesCollection().remove(crmsubmodulesCollectionCrmsubmodules);
                    oldCrmmodulesOfCrmsubmodulesCollectionCrmsubmodules = em.merge(oldCrmmodulesOfCrmsubmodulesCollectionCrmsubmodules);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmmodules(crmmodules.getCrmmodulesPK()) != null) {
                throw new PreexistingEntityException("Crmmodules " + crmmodules + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmmodules crmmodules) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmodules persistentCrmmodules = em.find(Crmmodules.class, crmmodules.getCrmmodulesPK());
            Collection<Crmusermodules> crmusermodulesCollectionOld = persistentCrmmodules.getCrmusermodulesCollection();
            Collection<Crmusermodules> crmusermodulesCollectionNew = crmmodules.getCrmusermodulesCollection();
            Collection<Crmsubmodules> crmsubmodulesCollectionOld = persistentCrmmodules.getCrmsubmodulesCollection();
            Collection<Crmsubmodules> crmsubmodulesCollectionNew = crmmodules.getCrmsubmodulesCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmusermodules crmusermodulesCollectionOldCrmusermodules : crmusermodulesCollectionOld) {
                if (!crmusermodulesCollectionNew.contains(crmusermodulesCollectionOldCrmusermodules)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmusermodules " + crmusermodulesCollectionOldCrmusermodules + " since its crmmodules field is not nullable.");
                }
            }
            for (Crmsubmodules crmsubmodulesCollectionOldCrmsubmodules : crmsubmodulesCollectionOld) {
                if (!crmsubmodulesCollectionNew.contains(crmsubmodulesCollectionOldCrmsubmodules)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmsubmodules " + crmsubmodulesCollectionOldCrmsubmodules + " since its crmmodules field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Crmusermodules> attachedCrmusermodulesCollectionNew = new ArrayList<Crmusermodules>();
            for (Crmusermodules crmusermodulesCollectionNewCrmusermodulesToAttach : crmusermodulesCollectionNew) {
                crmusermodulesCollectionNewCrmusermodulesToAttach = em.getReference(crmusermodulesCollectionNewCrmusermodulesToAttach.getClass(), crmusermodulesCollectionNewCrmusermodulesToAttach.getCrmusermodulesPK());
                attachedCrmusermodulesCollectionNew.add(crmusermodulesCollectionNewCrmusermodulesToAttach);
            }
            crmusermodulesCollectionNew = attachedCrmusermodulesCollectionNew;
            crmmodules.setCrmusermodulesCollection(crmusermodulesCollectionNew);
            Collection<Crmsubmodules> attachedCrmsubmodulesCollectionNew = new ArrayList<Crmsubmodules>();
            for (Crmsubmodules crmsubmodulesCollectionNewCrmsubmodulesToAttach : crmsubmodulesCollectionNew) {
                crmsubmodulesCollectionNewCrmsubmodulesToAttach = em.getReference(crmsubmodulesCollectionNewCrmsubmodulesToAttach.getClass(), crmsubmodulesCollectionNewCrmsubmodulesToAttach.getCrmsubmodulesPK());
                attachedCrmsubmodulesCollectionNew.add(crmsubmodulesCollectionNewCrmsubmodulesToAttach);
            }
            crmsubmodulesCollectionNew = attachedCrmsubmodulesCollectionNew;
            crmmodules.setCrmsubmodulesCollection(crmsubmodulesCollectionNew);
            crmmodules = em.merge(crmmodules);
            for (Crmusermodules crmusermodulesCollectionNewCrmusermodules : crmusermodulesCollectionNew) {
                if (!crmusermodulesCollectionOld.contains(crmusermodulesCollectionNewCrmusermodules)) {
                    Crmmodules oldCrmmodulesOfCrmusermodulesCollectionNewCrmusermodules = crmusermodulesCollectionNewCrmusermodules.getCrmmodules();
                    crmusermodulesCollectionNewCrmusermodules.setCrmmodules(crmmodules);
                    crmusermodulesCollectionNewCrmusermodules = em.merge(crmusermodulesCollectionNewCrmusermodules);
                    if (oldCrmmodulesOfCrmusermodulesCollectionNewCrmusermodules != null && !oldCrmmodulesOfCrmusermodulesCollectionNewCrmusermodules.equals(crmmodules)) {
                        oldCrmmodulesOfCrmusermodulesCollectionNewCrmusermodules.getCrmusermodulesCollection().remove(crmusermodulesCollectionNewCrmusermodules);
                        oldCrmmodulesOfCrmusermodulesCollectionNewCrmusermodules = em.merge(oldCrmmodulesOfCrmusermodulesCollectionNewCrmusermodules);
                    }
                }
            }
            for (Crmsubmodules crmsubmodulesCollectionNewCrmsubmodules : crmsubmodulesCollectionNew) {
                if (!crmsubmodulesCollectionOld.contains(crmsubmodulesCollectionNewCrmsubmodules)) {
                    Crmmodules oldCrmmodulesOfCrmsubmodulesCollectionNewCrmsubmodules = crmsubmodulesCollectionNewCrmsubmodules.getCrmmodules();
                    crmsubmodulesCollectionNewCrmsubmodules.setCrmmodules(crmmodules);
                    crmsubmodulesCollectionNewCrmsubmodules = em.merge(crmsubmodulesCollectionNewCrmsubmodules);
                    if (oldCrmmodulesOfCrmsubmodulesCollectionNewCrmsubmodules != null && !oldCrmmodulesOfCrmsubmodulesCollectionNewCrmsubmodules.equals(crmmodules)) {
                        oldCrmmodulesOfCrmsubmodulesCollectionNewCrmsubmodules.getCrmsubmodulesCollection().remove(crmsubmodulesCollectionNewCrmsubmodules);
                        oldCrmmodulesOfCrmsubmodulesCollectionNewCrmsubmodules = em.merge(oldCrmmodulesOfCrmsubmodulesCollectionNewCrmsubmodules);
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
                CrmmodulesPK id = crmmodules.getCrmmodulesPK();
                if (findCrmmodules(id) == null) {
                    throw new NonexistentEntityException("The crmmodules with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmmodulesPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmodules crmmodules;
            try {
                crmmodules = em.getReference(Crmmodules.class, id);
                crmmodules.getCrmmodulesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmmodules with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmusermodules> crmusermodulesCollectionOrphanCheck = crmmodules.getCrmusermodulesCollection();
            for (Crmusermodules crmusermodulesCollectionOrphanCheckCrmusermodules : crmusermodulesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmmodules (" + crmmodules + ") cannot be destroyed since the Crmusermodules " + crmusermodulesCollectionOrphanCheckCrmusermodules + " in its crmusermodulesCollection field has a non-nullable crmmodules field.");
            }
            Collection<Crmsubmodules> crmsubmodulesCollectionOrphanCheck = crmmodules.getCrmsubmodulesCollection();
            for (Crmsubmodules crmsubmodulesCollectionOrphanCheckCrmsubmodules : crmsubmodulesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmmodules (" + crmmodules + ") cannot be destroyed since the Crmsubmodules " + crmsubmodulesCollectionOrphanCheckCrmsubmodules + " in its crmsubmodulesCollection field has a non-nullable crmmodules field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(crmmodules);
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

    public List<Crmmodules> findCrmmodulesEntities() {
        return findCrmmodulesEntities(true, -1, -1);
    }

    public List<Crmmodules> findCrmmodulesEntities(int maxResults, int firstResult) {
        return findCrmmodulesEntities(false, maxResults, firstResult);
    }

    private List<Crmmodules> findCrmmodulesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmmodules.class));
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

    public Crmmodules findCrmmodules(CrmmodulesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmmodules.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmmodulesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmmodules> rt = cq.from(Crmmodules.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
