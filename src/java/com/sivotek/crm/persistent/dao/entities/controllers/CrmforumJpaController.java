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
import com.sivotek.crm.persistent.dao.entities.Crmforum;
import com.sivotek.crm.persistent.dao.entities.CrmforumPK;
import com.sivotek.crm.persistent.dao.entities.Crmforumteammembers;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmforumdocs;
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
public class CrmforumJpaController implements Serializable {

    public CrmforumJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmforumJpaController(){
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

    public void create(Crmforum crmforum) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmforum.getCrmforumPK() == null) {
            crmforum.setCrmforumPK(new CrmforumPK());
        }
        if (crmforum.getCrmforumteammembersCollection() == null) {
            crmforum.setCrmforumteammembersCollection(new ArrayList<Crmforumteammembers>());
        }
        if (crmforum.getCrmforumdocsCollection() == null) {
            crmforum.setCrmforumdocsCollection(new ArrayList<Crmforumdocs>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = crmforum.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmforum.setCompanyemployee(companyemployee);
            }
            Collection<Crmforumteammembers> attachedCrmforumteammembersCollection = new ArrayList<Crmforumteammembers>();
            for (Crmforumteammembers crmforumteammembersCollectionCrmforumteammembersToAttach : crmforum.getCrmforumteammembersCollection()) {
                crmforumteammembersCollectionCrmforumteammembersToAttach = em.getReference(crmforumteammembersCollectionCrmforumteammembersToAttach.getClass(), crmforumteammembersCollectionCrmforumteammembersToAttach.getCrmforumteammembersPK());
                attachedCrmforumteammembersCollection.add(crmforumteammembersCollectionCrmforumteammembersToAttach);
            }
            crmforum.setCrmforumteammembersCollection(attachedCrmforumteammembersCollection);
            Collection<Crmforumdocs> attachedCrmforumdocsCollection = new ArrayList<Crmforumdocs>();
            for (Crmforumdocs crmforumdocsCollectionCrmforumdocsToAttach : crmforum.getCrmforumdocsCollection()) {
                crmforumdocsCollectionCrmforumdocsToAttach = em.getReference(crmforumdocsCollectionCrmforumdocsToAttach.getClass(), crmforumdocsCollectionCrmforumdocsToAttach.getCrmforumdocsPK());
                attachedCrmforumdocsCollection.add(crmforumdocsCollectionCrmforumdocsToAttach);
            }
            crmforum.setCrmforumdocsCollection(attachedCrmforumdocsCollection);
            em.persist(crmforum);
            if (companyemployee != null) {
                companyemployee.getCrmforumCollection().add(crmforum);
                companyemployee = em.merge(companyemployee);
            }
            for (Crmforumteammembers crmforumteammembersCollectionCrmforumteammembers : crmforum.getCrmforumteammembersCollection()) {
                Crmforum oldCrmforumOfCrmforumteammembersCollectionCrmforumteammembers = crmforumteammembersCollectionCrmforumteammembers.getCrmforum();
                crmforumteammembersCollectionCrmforumteammembers.setCrmforum(crmforum);
                crmforumteammembersCollectionCrmforumteammembers = em.merge(crmforumteammembersCollectionCrmforumteammembers);
                if (oldCrmforumOfCrmforumteammembersCollectionCrmforumteammembers != null) {
                    oldCrmforumOfCrmforumteammembersCollectionCrmforumteammembers.getCrmforumteammembersCollection().remove(crmforumteammembersCollectionCrmforumteammembers);
                    oldCrmforumOfCrmforumteammembersCollectionCrmforumteammembers = em.merge(oldCrmforumOfCrmforumteammembersCollectionCrmforumteammembers);
                }
            }
            for (Crmforumdocs crmforumdocsCollectionCrmforumdocs : crmforum.getCrmforumdocsCollection()) {
                Crmforum oldCrmforumOfCrmforumdocsCollectionCrmforumdocs = crmforumdocsCollectionCrmforumdocs.getCrmforum();
                crmforumdocsCollectionCrmforumdocs.setCrmforum(crmforum);
                crmforumdocsCollectionCrmforumdocs = em.merge(crmforumdocsCollectionCrmforumdocs);
                if (oldCrmforumOfCrmforumdocsCollectionCrmforumdocs != null) {
                    oldCrmforumOfCrmforumdocsCollectionCrmforumdocs.getCrmforumdocsCollection().remove(crmforumdocsCollectionCrmforumdocs);
                    oldCrmforumOfCrmforumdocsCollectionCrmforumdocs = em.merge(oldCrmforumOfCrmforumdocsCollectionCrmforumdocs);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmforum(crmforum.getCrmforumPK()) != null) {
                throw new PreexistingEntityException("Crmforum " + crmforum + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmforum crmforum) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmforum persistentCrmforum = em.find(Crmforum.class, crmforum.getCrmforumPK());
            Companyemployee companyemployeeOld = persistentCrmforum.getCompanyemployee();
            Companyemployee companyemployeeNew = crmforum.getCompanyemployee();
            Collection<Crmforumteammembers> crmforumteammembersCollectionOld = persistentCrmforum.getCrmforumteammembersCollection();
            Collection<Crmforumteammembers> crmforumteammembersCollectionNew = crmforum.getCrmforumteammembersCollection();
            Collection<Crmforumdocs> crmforumdocsCollectionOld = persistentCrmforum.getCrmforumdocsCollection();
            Collection<Crmforumdocs> crmforumdocsCollectionNew = crmforum.getCrmforumdocsCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmforumteammembers crmforumteammembersCollectionOldCrmforumteammembers : crmforumteammembersCollectionOld) {
                if (!crmforumteammembersCollectionNew.contains(crmforumteammembersCollectionOldCrmforumteammembers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmforumteammembers " + crmforumteammembersCollectionOldCrmforumteammembers + " since its crmforum field is not nullable.");
                }
            }
            for (Crmforumdocs crmforumdocsCollectionOldCrmforumdocs : crmforumdocsCollectionOld) {
                if (!crmforumdocsCollectionNew.contains(crmforumdocsCollectionOldCrmforumdocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmforumdocs " + crmforumdocsCollectionOldCrmforumdocs + " since its crmforum field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmforum.setCompanyemployee(companyemployeeNew);
            }
            Collection<Crmforumteammembers> attachedCrmforumteammembersCollectionNew = new ArrayList<Crmforumteammembers>();
            for (Crmforumteammembers crmforumteammembersCollectionNewCrmforumteammembersToAttach : crmforumteammembersCollectionNew) {
                crmforumteammembersCollectionNewCrmforumteammembersToAttach = em.getReference(crmforumteammembersCollectionNewCrmforumteammembersToAttach.getClass(), crmforumteammembersCollectionNewCrmforumteammembersToAttach.getCrmforumteammembersPK());
                attachedCrmforumteammembersCollectionNew.add(crmforumteammembersCollectionNewCrmforumteammembersToAttach);
            }
            crmforumteammembersCollectionNew = attachedCrmforumteammembersCollectionNew;
            crmforum.setCrmforumteammembersCollection(crmforumteammembersCollectionNew);
            Collection<Crmforumdocs> attachedCrmforumdocsCollectionNew = new ArrayList<Crmforumdocs>();
            for (Crmforumdocs crmforumdocsCollectionNewCrmforumdocsToAttach : crmforumdocsCollectionNew) {
                crmforumdocsCollectionNewCrmforumdocsToAttach = em.getReference(crmforumdocsCollectionNewCrmforumdocsToAttach.getClass(), crmforumdocsCollectionNewCrmforumdocsToAttach.getCrmforumdocsPK());
                attachedCrmforumdocsCollectionNew.add(crmforumdocsCollectionNewCrmforumdocsToAttach);
            }
            crmforumdocsCollectionNew = attachedCrmforumdocsCollectionNew;
            crmforum.setCrmforumdocsCollection(crmforumdocsCollectionNew);
            crmforum = em.merge(crmforum);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmforumCollection().remove(crmforum);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmforumCollection().add(crmforum);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            for (Crmforumteammembers crmforumteammembersCollectionNewCrmforumteammembers : crmforumteammembersCollectionNew) {
                if (!crmforumteammembersCollectionOld.contains(crmforumteammembersCollectionNewCrmforumteammembers)) {
                    Crmforum oldCrmforumOfCrmforumteammembersCollectionNewCrmforumteammembers = crmforumteammembersCollectionNewCrmforumteammembers.getCrmforum();
                    crmforumteammembersCollectionNewCrmforumteammembers.setCrmforum(crmforum);
                    crmforumteammembersCollectionNewCrmforumteammembers = em.merge(crmforumteammembersCollectionNewCrmforumteammembers);
                    if (oldCrmforumOfCrmforumteammembersCollectionNewCrmforumteammembers != null && !oldCrmforumOfCrmforumteammembersCollectionNewCrmforumteammembers.equals(crmforum)) {
                        oldCrmforumOfCrmforumteammembersCollectionNewCrmforumteammembers.getCrmforumteammembersCollection().remove(crmforumteammembersCollectionNewCrmforumteammembers);
                        oldCrmforumOfCrmforumteammembersCollectionNewCrmforumteammembers = em.merge(oldCrmforumOfCrmforumteammembersCollectionNewCrmforumteammembers);
                    }
                }
            }
            for (Crmforumdocs crmforumdocsCollectionNewCrmforumdocs : crmforumdocsCollectionNew) {
                if (!crmforumdocsCollectionOld.contains(crmforumdocsCollectionNewCrmforumdocs)) {
                    Crmforum oldCrmforumOfCrmforumdocsCollectionNewCrmforumdocs = crmforumdocsCollectionNewCrmforumdocs.getCrmforum();
                    crmforumdocsCollectionNewCrmforumdocs.setCrmforum(crmforum);
                    crmforumdocsCollectionNewCrmforumdocs = em.merge(crmforumdocsCollectionNewCrmforumdocs);
                    if (oldCrmforumOfCrmforumdocsCollectionNewCrmforumdocs != null && !oldCrmforumOfCrmforumdocsCollectionNewCrmforumdocs.equals(crmforum)) {
                        oldCrmforumOfCrmforumdocsCollectionNewCrmforumdocs.getCrmforumdocsCollection().remove(crmforumdocsCollectionNewCrmforumdocs);
                        oldCrmforumOfCrmforumdocsCollectionNewCrmforumdocs = em.merge(oldCrmforumOfCrmforumdocsCollectionNewCrmforumdocs);
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
                CrmforumPK id = crmforum.getCrmforumPK();
                if (findCrmforum(id) == null) {
                    throw new NonexistentEntityException("The crmforum with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmforumPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmforum crmforum;
            try {
                crmforum = em.getReference(Crmforum.class, id);
                crmforum.getCrmforumPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmforum with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmforumteammembers> crmforumteammembersCollectionOrphanCheck = crmforum.getCrmforumteammembersCollection();
            for (Crmforumteammembers crmforumteammembersCollectionOrphanCheckCrmforumteammembers : crmforumteammembersCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmforum (" + crmforum + ") cannot be destroyed since the Crmforumteammembers " + crmforumteammembersCollectionOrphanCheckCrmforumteammembers + " in its crmforumteammembersCollection field has a non-nullable crmforum field.");
            }
            Collection<Crmforumdocs> crmforumdocsCollectionOrphanCheck = crmforum.getCrmforumdocsCollection();
            for (Crmforumdocs crmforumdocsCollectionOrphanCheckCrmforumdocs : crmforumdocsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmforum (" + crmforum + ") cannot be destroyed since the Crmforumdocs " + crmforumdocsCollectionOrphanCheckCrmforumdocs + " in its crmforumdocsCollection field has a non-nullable crmforum field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Companyemployee companyemployee = crmforum.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmforumCollection().remove(crmforum);
                companyemployee = em.merge(companyemployee);
            }
            em.remove(crmforum);
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

    public List<Crmforum> findCrmforumEntities() {
        return findCrmforumEntities(true, -1, -1);
    }

    public List<Crmforum> findCrmforumEntities(int maxResults, int firstResult) {
        return findCrmforumEntities(false, maxResults, firstResult);
    }

    private List<Crmforum> findCrmforumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmforum.class));
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

    public Crmforum findCrmforum(CrmforumPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmforum.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmforumCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmforum> rt = cq.from(Crmforum.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
