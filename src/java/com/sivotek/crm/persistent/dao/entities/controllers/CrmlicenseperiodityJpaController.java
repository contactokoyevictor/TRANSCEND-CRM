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
import com.sivotek.crm.persistent.dao.entities.Crmlicensetype;
import com.sivotek.crm.persistent.dao.entities.Crmlicense;
import com.sivotek.crm.persistent.dao.entities.Crmlicenseperiodity;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
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
public class CrmlicenseperiodityJpaController implements Serializable {

    public CrmlicenseperiodityJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmlicenseperiodityJpaController(){
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

    public void create(Crmlicenseperiodity crmlicenseperiodity) throws RollbackFailureException, Exception {
        if (crmlicenseperiodity.getCrmlicenseCollection() == null) {
            crmlicenseperiodity.setCrmlicenseCollection(new ArrayList<Crmlicense>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlicensetype typeid = crmlicenseperiodity.getTypeid();
            if (typeid != null) {
                typeid = em.getReference(typeid.getClass(), typeid.getTypeid());
                crmlicenseperiodity.setTypeid(typeid);
            }
            Collection<Crmlicense> attachedCrmlicenseCollection = new ArrayList<Crmlicense>();
            for (Crmlicense crmlicenseCollectionCrmlicenseToAttach : crmlicenseperiodity.getCrmlicenseCollection()) {
                crmlicenseCollectionCrmlicenseToAttach = em.getReference(crmlicenseCollectionCrmlicenseToAttach.getClass(), crmlicenseCollectionCrmlicenseToAttach.getCrmlicensePK());
                attachedCrmlicenseCollection.add(crmlicenseCollectionCrmlicenseToAttach);
            }
            crmlicenseperiodity.setCrmlicenseCollection(attachedCrmlicenseCollection);
            em.persist(crmlicenseperiodity);
            if (typeid != null) {
                typeid.getCrmlicenseperiodityCollection().add(crmlicenseperiodity);
                typeid = em.merge(typeid);
            }
            for (Crmlicense crmlicenseCollectionCrmlicense : crmlicenseperiodity.getCrmlicenseCollection()) {
                Crmlicenseperiodity oldPeriodityidOfCrmlicenseCollectionCrmlicense = crmlicenseCollectionCrmlicense.getPeriodityid();
                crmlicenseCollectionCrmlicense.setPeriodityid(crmlicenseperiodity);
                crmlicenseCollectionCrmlicense = em.merge(crmlicenseCollectionCrmlicense);
                if (oldPeriodityidOfCrmlicenseCollectionCrmlicense != null) {
                    oldPeriodityidOfCrmlicenseCollectionCrmlicense.getCrmlicenseCollection().remove(crmlicenseCollectionCrmlicense);
                    oldPeriodityidOfCrmlicenseCollectionCrmlicense = em.merge(oldPeriodityidOfCrmlicenseCollectionCrmlicense);
                }
            }
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

    public void edit(Crmlicenseperiodity crmlicenseperiodity) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlicenseperiodity persistentCrmlicenseperiodity = em.find(Crmlicenseperiodity.class, crmlicenseperiodity.getPeriodityid());
            Crmlicensetype typeidOld = persistentCrmlicenseperiodity.getTypeid();
            Crmlicensetype typeidNew = crmlicenseperiodity.getTypeid();
            Collection<Crmlicense> crmlicenseCollectionOld = persistentCrmlicenseperiodity.getCrmlicenseCollection();
            Collection<Crmlicense> crmlicenseCollectionNew = crmlicenseperiodity.getCrmlicenseCollection();
            if (typeidNew != null) {
                typeidNew = em.getReference(typeidNew.getClass(), typeidNew.getTypeid());
                crmlicenseperiodity.setTypeid(typeidNew);
            }
            Collection<Crmlicense> attachedCrmlicenseCollectionNew = new ArrayList<Crmlicense>();
            for (Crmlicense crmlicenseCollectionNewCrmlicenseToAttach : crmlicenseCollectionNew) {
                crmlicenseCollectionNewCrmlicenseToAttach = em.getReference(crmlicenseCollectionNewCrmlicenseToAttach.getClass(), crmlicenseCollectionNewCrmlicenseToAttach.getCrmlicensePK());
                attachedCrmlicenseCollectionNew.add(crmlicenseCollectionNewCrmlicenseToAttach);
            }
            crmlicenseCollectionNew = attachedCrmlicenseCollectionNew;
            crmlicenseperiodity.setCrmlicenseCollection(crmlicenseCollectionNew);
            crmlicenseperiodity = em.merge(crmlicenseperiodity);
            if (typeidOld != null && !typeidOld.equals(typeidNew)) {
                typeidOld.getCrmlicenseperiodityCollection().remove(crmlicenseperiodity);
                typeidOld = em.merge(typeidOld);
            }
            if (typeidNew != null && !typeidNew.equals(typeidOld)) {
                typeidNew.getCrmlicenseperiodityCollection().add(crmlicenseperiodity);
                typeidNew = em.merge(typeidNew);
            }
            for (Crmlicense crmlicenseCollectionOldCrmlicense : crmlicenseCollectionOld) {
                if (!crmlicenseCollectionNew.contains(crmlicenseCollectionOldCrmlicense)) {
                    crmlicenseCollectionOldCrmlicense.setPeriodityid(null);
                    crmlicenseCollectionOldCrmlicense = em.merge(crmlicenseCollectionOldCrmlicense);
                }
            }
            for (Crmlicense crmlicenseCollectionNewCrmlicense : crmlicenseCollectionNew) {
                if (!crmlicenseCollectionOld.contains(crmlicenseCollectionNewCrmlicense)) {
                    Crmlicenseperiodity oldPeriodityidOfCrmlicenseCollectionNewCrmlicense = crmlicenseCollectionNewCrmlicense.getPeriodityid();
                    crmlicenseCollectionNewCrmlicense.setPeriodityid(crmlicenseperiodity);
                    crmlicenseCollectionNewCrmlicense = em.merge(crmlicenseCollectionNewCrmlicense);
                    if (oldPeriodityidOfCrmlicenseCollectionNewCrmlicense != null && !oldPeriodityidOfCrmlicenseCollectionNewCrmlicense.equals(crmlicenseperiodity)) {
                        oldPeriodityidOfCrmlicenseCollectionNewCrmlicense.getCrmlicenseCollection().remove(crmlicenseCollectionNewCrmlicense);
                        oldPeriodityidOfCrmlicenseCollectionNewCrmlicense = em.merge(oldPeriodityidOfCrmlicenseCollectionNewCrmlicense);
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
                Integer id = crmlicenseperiodity.getPeriodityid();
                if (findCrmlicenseperiodity(id) == null) {
                    throw new NonexistentEntityException("The crmlicenseperiodity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlicenseperiodity crmlicenseperiodity;
            try {
                crmlicenseperiodity = em.getReference(Crmlicenseperiodity.class, id);
                crmlicenseperiodity.getPeriodityid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmlicenseperiodity with id " + id + " no longer exists.", enfe);
            }
            Crmlicensetype typeid = crmlicenseperiodity.getTypeid();
            if (typeid != null) {
                typeid.getCrmlicenseperiodityCollection().remove(crmlicenseperiodity);
                typeid = em.merge(typeid);
            }
            Collection<Crmlicense> crmlicenseCollection = crmlicenseperiodity.getCrmlicenseCollection();
            for (Crmlicense crmlicenseCollectionCrmlicense : crmlicenseCollection) {
                crmlicenseCollectionCrmlicense.setPeriodityid(null);
                crmlicenseCollectionCrmlicense = em.merge(crmlicenseCollectionCrmlicense);
            }
            em.remove(crmlicenseperiodity);
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

    public List<Crmlicenseperiodity> findCrmlicenseperiodityEntities() {
        return findCrmlicenseperiodityEntities(true, -1, -1);
    }

    public List<Crmlicenseperiodity> findCrmlicenseperiodityEntities(int maxResults, int firstResult) {
        return findCrmlicenseperiodityEntities(false, maxResults, firstResult);
    }

    private List<Crmlicenseperiodity> findCrmlicenseperiodityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmlicenseperiodity.class));
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

    public Crmlicenseperiodity findCrmlicenseperiodity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmlicenseperiodity.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmlicenseperiodityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmlicenseperiodity> rt = cq.from(Crmlicenseperiodity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
