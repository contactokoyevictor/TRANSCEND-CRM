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
import com.sivotek.crm.persistent.dao.entities.Crmlicenseperiodity;
import com.sivotek.crm.persistent.dao.entities.Crmlicensetype;
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
public class CrmlicensetypeJpaController implements Serializable {

    public CrmlicensetypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmlicensetypeJpaController(){
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

    public void create(Crmlicensetype crmlicensetype) throws RollbackFailureException, Exception {
        if (crmlicensetype.getCrmlicenseperiodityCollection() == null) {
            crmlicensetype.setCrmlicenseperiodityCollection(new ArrayList<Crmlicenseperiodity>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Crmlicenseperiodity> attachedCrmlicenseperiodityCollection = new ArrayList<Crmlicenseperiodity>();
            for (Crmlicenseperiodity crmlicenseperiodityCollectionCrmlicenseperiodityToAttach : crmlicensetype.getCrmlicenseperiodityCollection()) {
                crmlicenseperiodityCollectionCrmlicenseperiodityToAttach = em.getReference(crmlicenseperiodityCollectionCrmlicenseperiodityToAttach.getClass(), crmlicenseperiodityCollectionCrmlicenseperiodityToAttach.getPeriodityid());
                attachedCrmlicenseperiodityCollection.add(crmlicenseperiodityCollectionCrmlicenseperiodityToAttach);
            }
            crmlicensetype.setCrmlicenseperiodityCollection(attachedCrmlicenseperiodityCollection);
            em.persist(crmlicensetype);
            for (Crmlicenseperiodity crmlicenseperiodityCollectionCrmlicenseperiodity : crmlicensetype.getCrmlicenseperiodityCollection()) {
                Crmlicensetype oldTypeidOfCrmlicenseperiodityCollectionCrmlicenseperiodity = crmlicenseperiodityCollectionCrmlicenseperiodity.getTypeid();
                crmlicenseperiodityCollectionCrmlicenseperiodity.setTypeid(crmlicensetype);
                crmlicenseperiodityCollectionCrmlicenseperiodity = em.merge(crmlicenseperiodityCollectionCrmlicenseperiodity);
                if (oldTypeidOfCrmlicenseperiodityCollectionCrmlicenseperiodity != null) {
                    oldTypeidOfCrmlicenseperiodityCollectionCrmlicenseperiodity.getCrmlicenseperiodityCollection().remove(crmlicenseperiodityCollectionCrmlicenseperiodity);
                    oldTypeidOfCrmlicenseperiodityCollectionCrmlicenseperiodity = em.merge(oldTypeidOfCrmlicenseperiodityCollectionCrmlicenseperiodity);
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

    public void edit(Crmlicensetype crmlicensetype) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlicensetype persistentCrmlicensetype = em.find(Crmlicensetype.class, crmlicensetype.getTypeid());
            Collection<Crmlicenseperiodity> crmlicenseperiodityCollectionOld = persistentCrmlicensetype.getCrmlicenseperiodityCollection();
            Collection<Crmlicenseperiodity> crmlicenseperiodityCollectionNew = crmlicensetype.getCrmlicenseperiodityCollection();
            Collection<Crmlicenseperiodity> attachedCrmlicenseperiodityCollectionNew = new ArrayList<Crmlicenseperiodity>();
            for (Crmlicenseperiodity crmlicenseperiodityCollectionNewCrmlicenseperiodityToAttach : crmlicenseperiodityCollectionNew) {
                crmlicenseperiodityCollectionNewCrmlicenseperiodityToAttach = em.getReference(crmlicenseperiodityCollectionNewCrmlicenseperiodityToAttach.getClass(), crmlicenseperiodityCollectionNewCrmlicenseperiodityToAttach.getPeriodityid());
                attachedCrmlicenseperiodityCollectionNew.add(crmlicenseperiodityCollectionNewCrmlicenseperiodityToAttach);
            }
            crmlicenseperiodityCollectionNew = attachedCrmlicenseperiodityCollectionNew;
            crmlicensetype.setCrmlicenseperiodityCollection(crmlicenseperiodityCollectionNew);
            crmlicensetype = em.merge(crmlicensetype);
            for (Crmlicenseperiodity crmlicenseperiodityCollectionOldCrmlicenseperiodity : crmlicenseperiodityCollectionOld) {
                if (!crmlicenseperiodityCollectionNew.contains(crmlicenseperiodityCollectionOldCrmlicenseperiodity)) {
                    crmlicenseperiodityCollectionOldCrmlicenseperiodity.setTypeid(null);
                    crmlicenseperiodityCollectionOldCrmlicenseperiodity = em.merge(crmlicenseperiodityCollectionOldCrmlicenseperiodity);
                }
            }
            for (Crmlicenseperiodity crmlicenseperiodityCollectionNewCrmlicenseperiodity : crmlicenseperiodityCollectionNew) {
                if (!crmlicenseperiodityCollectionOld.contains(crmlicenseperiodityCollectionNewCrmlicenseperiodity)) {
                    Crmlicensetype oldTypeidOfCrmlicenseperiodityCollectionNewCrmlicenseperiodity = crmlicenseperiodityCollectionNewCrmlicenseperiodity.getTypeid();
                    crmlicenseperiodityCollectionNewCrmlicenseperiodity.setTypeid(crmlicensetype);
                    crmlicenseperiodityCollectionNewCrmlicenseperiodity = em.merge(crmlicenseperiodityCollectionNewCrmlicenseperiodity);
                    if (oldTypeidOfCrmlicenseperiodityCollectionNewCrmlicenseperiodity != null && !oldTypeidOfCrmlicenseperiodityCollectionNewCrmlicenseperiodity.equals(crmlicensetype)) {
                        oldTypeidOfCrmlicenseperiodityCollectionNewCrmlicenseperiodity.getCrmlicenseperiodityCollection().remove(crmlicenseperiodityCollectionNewCrmlicenseperiodity);
                        oldTypeidOfCrmlicenseperiodityCollectionNewCrmlicenseperiodity = em.merge(oldTypeidOfCrmlicenseperiodityCollectionNewCrmlicenseperiodity);
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
                Integer id = crmlicensetype.getTypeid();
                if (findCrmlicensetype(id) == null) {
                    throw new NonexistentEntityException("The crmlicensetype with id " + id + " no longer exists.");
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
            Crmlicensetype crmlicensetype;
            try {
                crmlicensetype = em.getReference(Crmlicensetype.class, id);
                crmlicensetype.getTypeid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmlicensetype with id " + id + " no longer exists.", enfe);
            }
            Collection<Crmlicenseperiodity> crmlicenseperiodityCollection = crmlicensetype.getCrmlicenseperiodityCollection();
            for (Crmlicenseperiodity crmlicenseperiodityCollectionCrmlicenseperiodity : crmlicenseperiodityCollection) {
                crmlicenseperiodityCollectionCrmlicenseperiodity.setTypeid(null);
                crmlicenseperiodityCollectionCrmlicenseperiodity = em.merge(crmlicenseperiodityCollectionCrmlicenseperiodity);
            }
            em.remove(crmlicensetype);
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

    public List<Crmlicensetype> findCrmlicensetypeEntities() {
        return findCrmlicensetypeEntities(true, -1, -1);
    }

    public List<Crmlicensetype> findCrmlicensetypeEntities(int maxResults, int firstResult) {
        return findCrmlicensetypeEntities(false, maxResults, firstResult);
    }

    private List<Crmlicensetype> findCrmlicensetypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmlicensetype.class));
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

    public Crmlicensetype findCrmlicensetype(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmlicensetype.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmlicensetypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmlicensetype> rt = cq.from(Crmlicensetype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
