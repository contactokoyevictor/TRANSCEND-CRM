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
import com.sivotek.crm.persistent.dao.entities.Crmmodules;
import com.sivotek.crm.persistent.dao.entities.Crmsubmodules;
import com.sivotek.crm.persistent.dao.entities.CrmsubmodulesPK;
import com.sivotek.crm.persistent.dao.entities.Crmusermodules;
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
import javax.transaction.UserTransaction;
import javax.persistence.Persistence;
/**
 *
 * @author okoyevictor
 */
public class CrmsubmodulesJpaController implements Serializable {

    public CrmsubmodulesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    
    public CrmsubmodulesJpaController(){
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

    public void create(Crmsubmodules crmsubmodules) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmsubmodules.getCrmsubmodulesPK() == null) {
            crmsubmodules.setCrmsubmodulesPK(new CrmsubmodulesPK());
        }
        if (crmsubmodules.getCrmusermodulesCollection() == null) {
            crmsubmodules.setCrmusermodulesCollection(new ArrayList<Crmusermodules>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmmodules crmmodules = crmsubmodules.getCrmmodules();
            if (crmmodules != null) {
                crmmodules = em.getReference(crmmodules.getClass(), crmmodules.getCrmmodulesPK());
                crmsubmodules.setCrmmodules(crmmodules);
            }
            Collection<Crmusermodules> attachedCrmusermodulesCollection = new ArrayList<Crmusermodules>();
            for (Crmusermodules crmusermodulesCollectionCrmusermodulesToAttach : crmsubmodules.getCrmusermodulesCollection()) {
                crmusermodulesCollectionCrmusermodulesToAttach = em.getReference(crmusermodulesCollectionCrmusermodulesToAttach.getClass(), crmusermodulesCollectionCrmusermodulesToAttach.getCrmusermodulesPK());
                attachedCrmusermodulesCollection.add(crmusermodulesCollectionCrmusermodulesToAttach);
            }
            crmsubmodules.setCrmusermodulesCollection(attachedCrmusermodulesCollection);
            em.persist(crmsubmodules);
            if (crmmodules != null) {
                crmmodules.getCrmsubmodulesCollection().add(crmsubmodules);
                crmmodules = em.merge(crmmodules);
            }
            for (Crmusermodules crmusermodulesCollectionCrmusermodules : crmsubmodules.getCrmusermodulesCollection()) {
                Crmsubmodules oldCrmsubmodulesOfCrmusermodulesCollectionCrmusermodules = crmusermodulesCollectionCrmusermodules.getCrmsubmodules();
                crmusermodulesCollectionCrmusermodules.setCrmsubmodules(crmsubmodules);
                crmusermodulesCollectionCrmusermodules = em.merge(crmusermodulesCollectionCrmusermodules);
                if (oldCrmsubmodulesOfCrmusermodulesCollectionCrmusermodules != null) {
                    oldCrmsubmodulesOfCrmusermodulesCollectionCrmusermodules.getCrmusermodulesCollection().remove(crmusermodulesCollectionCrmusermodules);
                    oldCrmsubmodulesOfCrmusermodulesCollectionCrmusermodules = em.merge(oldCrmsubmodulesOfCrmusermodulesCollectionCrmusermodules);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmsubmodules(crmsubmodules.getCrmsubmodulesPK()) != null) {
                throw new PreexistingEntityException("Crmsubmodules " + crmsubmodules + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmsubmodules crmsubmodules) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmsubmodules persistentCrmsubmodules = em.find(Crmsubmodules.class, crmsubmodules.getCrmsubmodulesPK());
            Crmmodules crmmodulesOld = persistentCrmsubmodules.getCrmmodules();
            Crmmodules crmmodulesNew = crmsubmodules.getCrmmodules();
            Collection<Crmusermodules> crmusermodulesCollectionOld = persistentCrmsubmodules.getCrmusermodulesCollection();
            Collection<Crmusermodules> crmusermodulesCollectionNew = crmsubmodules.getCrmusermodulesCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmusermodules crmusermodulesCollectionOldCrmusermodules : crmusermodulesCollectionOld) {
                if (!crmusermodulesCollectionNew.contains(crmusermodulesCollectionOldCrmusermodules)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmusermodules " + crmusermodulesCollectionOldCrmusermodules + " since its crmsubmodules field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (crmmodulesNew != null) {
                crmmodulesNew = em.getReference(crmmodulesNew.getClass(), crmmodulesNew.getCrmmodulesPK());
                crmsubmodules.setCrmmodules(crmmodulesNew);
            }
            Collection<Crmusermodules> attachedCrmusermodulesCollectionNew = new ArrayList<Crmusermodules>();
            for (Crmusermodules crmusermodulesCollectionNewCrmusermodulesToAttach : crmusermodulesCollectionNew) {
                crmusermodulesCollectionNewCrmusermodulesToAttach = em.getReference(crmusermodulesCollectionNewCrmusermodulesToAttach.getClass(), crmusermodulesCollectionNewCrmusermodulesToAttach.getCrmusermodulesPK());
                attachedCrmusermodulesCollectionNew.add(crmusermodulesCollectionNewCrmusermodulesToAttach);
            }
            crmusermodulesCollectionNew = attachedCrmusermodulesCollectionNew;
            crmsubmodules.setCrmusermodulesCollection(crmusermodulesCollectionNew);
            crmsubmodules = em.merge(crmsubmodules);
            if (crmmodulesOld != null && !crmmodulesOld.equals(crmmodulesNew)) {
                crmmodulesOld.getCrmsubmodulesCollection().remove(crmsubmodules);
                crmmodulesOld = em.merge(crmmodulesOld);
            }
            if (crmmodulesNew != null && !crmmodulesNew.equals(crmmodulesOld)) {
                crmmodulesNew.getCrmsubmodulesCollection().add(crmsubmodules);
                crmmodulesNew = em.merge(crmmodulesNew);
            }
            for (Crmusermodules crmusermodulesCollectionNewCrmusermodules : crmusermodulesCollectionNew) {
                if (!crmusermodulesCollectionOld.contains(crmusermodulesCollectionNewCrmusermodules)) {
                    Crmsubmodules oldCrmsubmodulesOfCrmusermodulesCollectionNewCrmusermodules = crmusermodulesCollectionNewCrmusermodules.getCrmsubmodules();
                    crmusermodulesCollectionNewCrmusermodules.setCrmsubmodules(crmsubmodules);
                    crmusermodulesCollectionNewCrmusermodules = em.merge(crmusermodulesCollectionNewCrmusermodules);
                    if (oldCrmsubmodulesOfCrmusermodulesCollectionNewCrmusermodules != null && !oldCrmsubmodulesOfCrmusermodulesCollectionNewCrmusermodules.equals(crmsubmodules)) {
                        oldCrmsubmodulesOfCrmusermodulesCollectionNewCrmusermodules.getCrmusermodulesCollection().remove(crmusermodulesCollectionNewCrmusermodules);
                        oldCrmsubmodulesOfCrmusermodulesCollectionNewCrmusermodules = em.merge(oldCrmsubmodulesOfCrmusermodulesCollectionNewCrmusermodules);
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
                CrmsubmodulesPK id = crmsubmodules.getCrmsubmodulesPK();
                if (findCrmsubmodules(id) == null) {
                    throw new NonexistentEntityException("The crmsubmodules with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmsubmodulesPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmsubmodules crmsubmodules;
            try {
                crmsubmodules = em.getReference(Crmsubmodules.class, id);
                crmsubmodules.getCrmsubmodulesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmsubmodules with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmusermodules> crmusermodulesCollectionOrphanCheck = crmsubmodules.getCrmusermodulesCollection();
            for (Crmusermodules crmusermodulesCollectionOrphanCheckCrmusermodules : crmusermodulesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmsubmodules (" + crmsubmodules + ") cannot be destroyed since the Crmusermodules " + crmusermodulesCollectionOrphanCheckCrmusermodules + " in its crmusermodulesCollection field has a non-nullable crmsubmodules field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Crmmodules crmmodules = crmsubmodules.getCrmmodules();
            if (crmmodules != null) {
                crmmodules.getCrmsubmodulesCollection().remove(crmsubmodules);
                crmmodules = em.merge(crmmodules);
            }
            em.remove(crmsubmodules);
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

    public List<Crmsubmodules> findCrmsubmodulesEntities() {
        return findCrmsubmodulesEntities(true, -1, -1);
    }

    public List<Crmsubmodules> findCrmsubmodulesEntities(int maxResults, int firstResult) {
        return findCrmsubmodulesEntities(false, maxResults, firstResult);
    }

    private List<Crmsubmodules> findCrmsubmodulesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmsubmodules.class));
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

    public Crmsubmodules findCrmsubmodules(CrmsubmodulesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmsubmodules.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmsubmodulesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmsubmodules> rt = cq.from(Crmsubmodules.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
