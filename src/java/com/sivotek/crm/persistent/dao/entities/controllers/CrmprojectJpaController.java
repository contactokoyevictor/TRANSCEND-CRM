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
import com.sivotek.crm.persistent.dao.entities.Crmproject;
import com.sivotek.crm.persistent.dao.entities.CrmprojectPK;
import com.sivotek.crm.persistent.dao.entities.Crmprojectstatus;
import com.sivotek.crm.persistent.dao.entities.Crmprojectteammembers;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmprojecttask;
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
public class CrmprojectJpaController implements Serializable {

    public CrmprojectJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmprojectJpaController(){
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

    public void create(Crmproject crmproject) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmproject.getCrmprojectPK() == null) {
            crmproject.setCrmprojectPK(new CrmprojectPK());
        }
        if (crmproject.getCrmprojectteammembersCollection() == null) {
            crmproject.setCrmprojectteammembersCollection(new ArrayList<Crmprojectteammembers>());
        }
        if (crmproject.getCrmprojecttaskCollection() == null) {
            crmproject.setCrmprojecttaskCollection(new ArrayList<Crmprojecttask>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = crmproject.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmproject.setCompanyemployee(companyemployee);
            }
            Crmprojectstatus crmprojectstatus = crmproject.getCrmprojectstatus();
            if (crmprojectstatus != null) {
                crmprojectstatus = em.getReference(crmprojectstatus.getClass(), crmprojectstatus.getCrmprojectstatusPK());
                crmproject.setCrmprojectstatus(crmprojectstatus);
            }
            Companyemployee companyemployee1 = crmproject.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmproject.setCompanyemployee1(companyemployee1);
            }
            Companyemployee companyemployee2 = crmproject.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2 = em.getReference(companyemployee2.getClass(), companyemployee2.getCompanyemployeePK());
                crmproject.setCompanyemployee2(companyemployee2);
            }
            Collection<Crmprojectteammembers> attachedCrmprojectteammembersCollection = new ArrayList<Crmprojectteammembers>();
            for (Crmprojectteammembers crmprojectteammembersCollectionCrmprojectteammembersToAttach : crmproject.getCrmprojectteammembersCollection()) {
                crmprojectteammembersCollectionCrmprojectteammembersToAttach = em.getReference(crmprojectteammembersCollectionCrmprojectteammembersToAttach.getClass(), crmprojectteammembersCollectionCrmprojectteammembersToAttach.getCrmprojectteammembersPK());
                attachedCrmprojectteammembersCollection.add(crmprojectteammembersCollectionCrmprojectteammembersToAttach);
            }
            crmproject.setCrmprojectteammembersCollection(attachedCrmprojectteammembersCollection);
            Collection<Crmprojecttask> attachedCrmprojecttaskCollection = new ArrayList<Crmprojecttask>();
            for (Crmprojecttask crmprojecttaskCollectionCrmprojecttaskToAttach : crmproject.getCrmprojecttaskCollection()) {
                crmprojecttaskCollectionCrmprojecttaskToAttach = em.getReference(crmprojecttaskCollectionCrmprojecttaskToAttach.getClass(), crmprojecttaskCollectionCrmprojecttaskToAttach.getCrmprojecttaskPK());
                attachedCrmprojecttaskCollection.add(crmprojecttaskCollectionCrmprojecttaskToAttach);
            }
            crmproject.setCrmprojecttaskCollection(attachedCrmprojecttaskCollection);
            em.persist(crmproject);
            if (companyemployee != null) {
                companyemployee.getCrmprojectCollection().add(crmproject);
                companyemployee = em.merge(companyemployee);
            }
            if (crmprojectstatus != null) {
                crmprojectstatus.getCrmprojectCollection().add(crmproject);
                crmprojectstatus = em.merge(crmprojectstatus);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectCollection().add(crmproject);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (companyemployee2 != null) {
                companyemployee2.getCrmprojectCollection().add(crmproject);
                companyemployee2 = em.merge(companyemployee2);
            }
            for (Crmprojectteammembers crmprojectteammembersCollectionCrmprojectteammembers : crmproject.getCrmprojectteammembersCollection()) {
                Crmproject oldCrmprojectOfCrmprojectteammembersCollectionCrmprojectteammembers = crmprojectteammembersCollectionCrmprojectteammembers.getCrmproject();
                crmprojectteammembersCollectionCrmprojectteammembers.setCrmproject(crmproject);
                crmprojectteammembersCollectionCrmprojectteammembers = em.merge(crmprojectteammembersCollectionCrmprojectteammembers);
                if (oldCrmprojectOfCrmprojectteammembersCollectionCrmprojectteammembers != null) {
                    oldCrmprojectOfCrmprojectteammembersCollectionCrmprojectteammembers.getCrmprojectteammembersCollection().remove(crmprojectteammembersCollectionCrmprojectteammembers);
                    oldCrmprojectOfCrmprojectteammembersCollectionCrmprojectteammembers = em.merge(oldCrmprojectOfCrmprojectteammembersCollectionCrmprojectteammembers);
                }
            }
            for (Crmprojecttask crmprojecttaskCollectionCrmprojecttask : crmproject.getCrmprojecttaskCollection()) {
                Crmproject oldCrmprojectOfCrmprojecttaskCollectionCrmprojecttask = crmprojecttaskCollectionCrmprojecttask.getCrmproject();
                crmprojecttaskCollectionCrmprojecttask.setCrmproject(crmproject);
                crmprojecttaskCollectionCrmprojecttask = em.merge(crmprojecttaskCollectionCrmprojecttask);
                if (oldCrmprojectOfCrmprojecttaskCollectionCrmprojecttask != null) {
                    oldCrmprojectOfCrmprojecttaskCollectionCrmprojecttask.getCrmprojecttaskCollection().remove(crmprojecttaskCollectionCrmprojecttask);
                    oldCrmprojectOfCrmprojecttaskCollectionCrmprojecttask = em.merge(oldCrmprojectOfCrmprojecttaskCollectionCrmprojecttask);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmproject(crmproject.getCrmprojectPK()) != null) {
                throw new PreexistingEntityException("Crmproject " + crmproject + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmproject crmproject) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmproject persistentCrmproject = em.find(Crmproject.class, crmproject.getCrmprojectPK());
            Companyemployee companyemployeeOld = persistentCrmproject.getCompanyemployee();
            Companyemployee companyemployeeNew = crmproject.getCompanyemployee();
            Crmprojectstatus crmprojectstatusOld = persistentCrmproject.getCrmprojectstatus();
            Crmprojectstatus crmprojectstatusNew = crmproject.getCrmprojectstatus();
            Companyemployee companyemployee1Old = persistentCrmproject.getCompanyemployee1();
            Companyemployee companyemployee1New = crmproject.getCompanyemployee1();
            Companyemployee companyemployee2Old = persistentCrmproject.getCompanyemployee2();
            Companyemployee companyemployee2New = crmproject.getCompanyemployee2();
            Collection<Crmprojectteammembers> crmprojectteammembersCollectionOld = persistentCrmproject.getCrmprojectteammembersCollection();
            Collection<Crmprojectteammembers> crmprojectteammembersCollectionNew = crmproject.getCrmprojectteammembersCollection();
            Collection<Crmprojecttask> crmprojecttaskCollectionOld = persistentCrmproject.getCrmprojecttaskCollection();
            Collection<Crmprojecttask> crmprojecttaskCollectionNew = crmproject.getCrmprojecttaskCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmprojectteammembers crmprojectteammembersCollectionOldCrmprojectteammembers : crmprojectteammembersCollectionOld) {
                if (!crmprojectteammembersCollectionNew.contains(crmprojectteammembersCollectionOldCrmprojectteammembers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectteammembers " + crmprojectteammembersCollectionOldCrmprojectteammembers + " since its crmproject field is not nullable.");
                }
            }
            for (Crmprojecttask crmprojecttaskCollectionOldCrmprojecttask : crmprojecttaskCollectionOld) {
                if (!crmprojecttaskCollectionNew.contains(crmprojecttaskCollectionOldCrmprojecttask)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojecttask " + crmprojecttaskCollectionOldCrmprojecttask + " since its crmproject field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmproject.setCompanyemployee(companyemployeeNew);
            }
            if (crmprojectstatusNew != null) {
                crmprojectstatusNew = em.getReference(crmprojectstatusNew.getClass(), crmprojectstatusNew.getCrmprojectstatusPK());
                crmproject.setCrmprojectstatus(crmprojectstatusNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmproject.setCompanyemployee1(companyemployee1New);
            }
            if (companyemployee2New != null) {
                companyemployee2New = em.getReference(companyemployee2New.getClass(), companyemployee2New.getCompanyemployeePK());
                crmproject.setCompanyemployee2(companyemployee2New);
            }
            Collection<Crmprojectteammembers> attachedCrmprojectteammembersCollectionNew = new ArrayList<Crmprojectteammembers>();
            for (Crmprojectteammembers crmprojectteammembersCollectionNewCrmprojectteammembersToAttach : crmprojectteammembersCollectionNew) {
                crmprojectteammembersCollectionNewCrmprojectteammembersToAttach = em.getReference(crmprojectteammembersCollectionNewCrmprojectteammembersToAttach.getClass(), crmprojectteammembersCollectionNewCrmprojectteammembersToAttach.getCrmprojectteammembersPK());
                attachedCrmprojectteammembersCollectionNew.add(crmprojectteammembersCollectionNewCrmprojectteammembersToAttach);
            }
            crmprojectteammembersCollectionNew = attachedCrmprojectteammembersCollectionNew;
            crmproject.setCrmprojectteammembersCollection(crmprojectteammembersCollectionNew);
            Collection<Crmprojecttask> attachedCrmprojecttaskCollectionNew = new ArrayList<Crmprojecttask>();
            for (Crmprojecttask crmprojecttaskCollectionNewCrmprojecttaskToAttach : crmprojecttaskCollectionNew) {
                crmprojecttaskCollectionNewCrmprojecttaskToAttach = em.getReference(crmprojecttaskCollectionNewCrmprojecttaskToAttach.getClass(), crmprojecttaskCollectionNewCrmprojecttaskToAttach.getCrmprojecttaskPK());
                attachedCrmprojecttaskCollectionNew.add(crmprojecttaskCollectionNewCrmprojecttaskToAttach);
            }
            crmprojecttaskCollectionNew = attachedCrmprojecttaskCollectionNew;
            crmproject.setCrmprojecttaskCollection(crmprojecttaskCollectionNew);
            crmproject = em.merge(crmproject);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmprojectCollection().remove(crmproject);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmprojectCollection().add(crmproject);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (crmprojectstatusOld != null && !crmprojectstatusOld.equals(crmprojectstatusNew)) {
                crmprojectstatusOld.getCrmprojectCollection().remove(crmproject);
                crmprojectstatusOld = em.merge(crmprojectstatusOld);
            }
            if (crmprojectstatusNew != null && !crmprojectstatusNew.equals(crmprojectstatusOld)) {
                crmprojectstatusNew.getCrmprojectCollection().add(crmproject);
                crmprojectstatusNew = em.merge(crmprojectstatusNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmprojectCollection().remove(crmproject);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmprojectCollection().add(crmproject);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (companyemployee2Old != null && !companyemployee2Old.equals(companyemployee2New)) {
                companyemployee2Old.getCrmprojectCollection().remove(crmproject);
                companyemployee2Old = em.merge(companyemployee2Old);
            }
            if (companyemployee2New != null && !companyemployee2New.equals(companyemployee2Old)) {
                companyemployee2New.getCrmprojectCollection().add(crmproject);
                companyemployee2New = em.merge(companyemployee2New);
            }
            for (Crmprojectteammembers crmprojectteammembersCollectionNewCrmprojectteammembers : crmprojectteammembersCollectionNew) {
                if (!crmprojectteammembersCollectionOld.contains(crmprojectteammembersCollectionNewCrmprojectteammembers)) {
                    Crmproject oldCrmprojectOfCrmprojectteammembersCollectionNewCrmprojectteammembers = crmprojectteammembersCollectionNewCrmprojectteammembers.getCrmproject();
                    crmprojectteammembersCollectionNewCrmprojectteammembers.setCrmproject(crmproject);
                    crmprojectteammembersCollectionNewCrmprojectteammembers = em.merge(crmprojectteammembersCollectionNewCrmprojectteammembers);
                    if (oldCrmprojectOfCrmprojectteammembersCollectionNewCrmprojectteammembers != null && !oldCrmprojectOfCrmprojectteammembersCollectionNewCrmprojectteammembers.equals(crmproject)) {
                        oldCrmprojectOfCrmprojectteammembersCollectionNewCrmprojectteammembers.getCrmprojectteammembersCollection().remove(crmprojectteammembersCollectionNewCrmprojectteammembers);
                        oldCrmprojectOfCrmprojectteammembersCollectionNewCrmprojectteammembers = em.merge(oldCrmprojectOfCrmprojectteammembersCollectionNewCrmprojectteammembers);
                    }
                }
            }
            for (Crmprojecttask crmprojecttaskCollectionNewCrmprojecttask : crmprojecttaskCollectionNew) {
                if (!crmprojecttaskCollectionOld.contains(crmprojecttaskCollectionNewCrmprojecttask)) {
                    Crmproject oldCrmprojectOfCrmprojecttaskCollectionNewCrmprojecttask = crmprojecttaskCollectionNewCrmprojecttask.getCrmproject();
                    crmprojecttaskCollectionNewCrmprojecttask.setCrmproject(crmproject);
                    crmprojecttaskCollectionNewCrmprojecttask = em.merge(crmprojecttaskCollectionNewCrmprojecttask);
                    if (oldCrmprojectOfCrmprojecttaskCollectionNewCrmprojecttask != null && !oldCrmprojectOfCrmprojecttaskCollectionNewCrmprojecttask.equals(crmproject)) {
                        oldCrmprojectOfCrmprojecttaskCollectionNewCrmprojecttask.getCrmprojecttaskCollection().remove(crmprojecttaskCollectionNewCrmprojecttask);
                        oldCrmprojectOfCrmprojecttaskCollectionNewCrmprojecttask = em.merge(oldCrmprojectOfCrmprojecttaskCollectionNewCrmprojecttask);
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
                CrmprojectPK id = crmproject.getCrmprojectPK();
                if (findCrmproject(id) == null) {
                    throw new NonexistentEntityException("The crmproject with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmprojectPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmproject crmproject;
            try {
                crmproject = em.getReference(Crmproject.class, id);
                crmproject.getCrmprojectPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmproject with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmprojectteammembers> crmprojectteammembersCollectionOrphanCheck = crmproject.getCrmprojectteammembersCollection();
            for (Crmprojectteammembers crmprojectteammembersCollectionOrphanCheckCrmprojectteammembers : crmprojectteammembersCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmproject (" + crmproject + ") cannot be destroyed since the Crmprojectteammembers " + crmprojectteammembersCollectionOrphanCheckCrmprojectteammembers + " in its crmprojectteammembersCollection field has a non-nullable crmproject field.");
            }
            Collection<Crmprojecttask> crmprojecttaskCollectionOrphanCheck = crmproject.getCrmprojecttaskCollection();
            for (Crmprojecttask crmprojecttaskCollectionOrphanCheckCrmprojecttask : crmprojecttaskCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmproject (" + crmproject + ") cannot be destroyed since the Crmprojecttask " + crmprojecttaskCollectionOrphanCheckCrmprojecttask + " in its crmprojecttaskCollection field has a non-nullable crmproject field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Companyemployee companyemployee = crmproject.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmprojectCollection().remove(crmproject);
                companyemployee = em.merge(companyemployee);
            }
            Crmprojectstatus crmprojectstatus = crmproject.getCrmprojectstatus();
            if (crmprojectstatus != null) {
                crmprojectstatus.getCrmprojectCollection().remove(crmproject);
                crmprojectstatus = em.merge(crmprojectstatus);
            }
            Companyemployee companyemployee1 = crmproject.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectCollection().remove(crmproject);
                companyemployee1 = em.merge(companyemployee1);
            }
            Companyemployee companyemployee2 = crmproject.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2.getCrmprojectCollection().remove(crmproject);
                companyemployee2 = em.merge(companyemployee2);
            }
            em.remove(crmproject);
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

    public List<Crmproject> findCrmprojectEntities() {
        return findCrmprojectEntities(true, -1, -1);
    }

    public List<Crmproject> findCrmprojectEntities(int maxResults, int firstResult) {
        return findCrmprojectEntities(false, maxResults, firstResult);
    }

    private List<Crmproject> findCrmprojectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmproject.class));
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

    public Crmproject findCrmproject(CrmprojectPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmproject.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmprojectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmproject> rt = cq.from(Crmproject.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
