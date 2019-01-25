/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import com.sivotek.crm.persistent.dao.entities.Crmlicense;
import com.sivotek.crm.persistent.dao.entities.CrmlicensePK;
import com.sivotek.crm.persistent.dao.entities.Crmlicensecode;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.crm.persistent.dao.entities.Crmlicenseperiodity;
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author okoyevictor
 */
public class CrmlicenseJpaController implements Serializable {

    public CrmlicenseJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmlicenseJpaController(){
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

    public void create(Crmlicense crmlicense) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmlicense.getCrmlicensePK() == null) {
            crmlicense.setCrmlicensePK(new CrmlicensePK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlicenseperiodity periodityid = crmlicense.getPeriodityid();
            if (periodityid != null) {
                periodityid = em.getReference(periodityid.getClass(), periodityid.getPeriodityid());
                crmlicense.setPeriodityid(periodityid);
            }
            em.persist(crmlicense);
            if (periodityid != null) {
                periodityid.getCrmlicenseCollection().add(crmlicense);
                periodityid = em.merge(periodityid);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmlicense(crmlicense.getCrmlicensePK()) != null) {
                throw new PreexistingEntityException("Crmlicense " + crmlicense + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmlicense crmlicense) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlicense persistentCrmlicense = em.find(Crmlicense.class, crmlicense.getCrmlicensePK());
            Crmlicenseperiodity periodityidOld = persistentCrmlicense.getPeriodityid();
            Crmlicenseperiodity periodityidNew = crmlicense.getPeriodityid();
            if (periodityidNew != null) {
                periodityidNew = em.getReference(periodityidNew.getClass(), periodityidNew.getPeriodityid());
                crmlicense.setPeriodityid(periodityidNew);
            }
            crmlicense = em.merge(crmlicense);
            if (periodityidOld != null && !periodityidOld.equals(periodityidNew)) {
                periodityidOld.getCrmlicenseCollection().remove(crmlicense);
                periodityidOld = em.merge(periodityidOld);
            }
            if (periodityidNew != null && !periodityidNew.equals(periodityidOld)) {
                periodityidNew.getCrmlicenseCollection().add(crmlicense);
                periodityidNew = em.merge(periodityidNew);
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
                CrmlicensePK id = crmlicense.getCrmlicensePK();
                if (findCrmlicense(id) == null) {
                    throw new NonexistentEntityException("The crmlicense with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmlicensePK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlicense crmlicense;
            try {
                crmlicense = em.getReference(Crmlicense.class, id);
                crmlicense.getCrmlicensePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmlicense with id " + id + " no longer exists.", enfe);
            }
            Crmlicenseperiodity periodityid = crmlicense.getPeriodityid();
            if (periodityid != null) {
                periodityid.getCrmlicenseCollection().remove(crmlicense);
                periodityid = em.merge(periodityid);
            }
            em.remove(crmlicense);
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

    public List<Crmlicense> findCrmlicenseEntities() {
        return findCrmlicenseEntities(true, -1, -1);
    }

    public List<Crmlicense> findCrmlicenseEntities(int maxResults, int firstResult) {
        return findCrmlicenseEntities(false, maxResults, firstResult);
    }

    private List<Crmlicense> findCrmlicenseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmlicense.class));
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

    public Crmlicense findCrmlicense(CrmlicensePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmlicense.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmlicenseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmlicense> rt = cq.from(Crmlicense.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
 public Crmlicense findByLicensecode(Crmlicensecode crmlicensecode) {
        EntityManager em = getEntityManager();
        try {
            Query findByLicensecode = em.createNamedQuery("Crmlicense.findByLicensecode");
            findByLicensecode.setParameter("licensecode", crmlicensecode.getLicensecode());
            Crmlicense crmlicense = new Crmlicense();
            crmlicense = (Crmlicense) findByLicensecode.getSingleResult();
            return crmlicense;
        } finally {
            em.close();
        }
      
    }
      
   public Crmlicense findByPublicKey(String publickey) {
        EntityManager em = getEntityManager();
        try {
            Query findByPublicKey = em.createNamedQuery("Crmlicense.findByPublicKey");
            findByPublicKey.setParameter("publicKey", publickey);
            Crmlicense crmlicense = new Crmlicense();
            crmlicense = (Crmlicense) findByPublicKey.getSingleResult();
            return crmlicense;
        } finally {
            em.close();
        }
   }
 
  //
  public int updateByPublickey(Crmlicensecode crmlicensecode) throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException, IllegalStateException, javax.transaction.RollbackException {
        EntityManager em = null;
        int update = 0;
        try {
            utx.begin();
            em = getEntityManager();
            Query updateByPublickey = em.createNamedQuery("Crmlicense.updateByPublickey");
            updateByPublickey.setParameter("publicKey", crmlicensecode.getLicensekey());
            updateByPublickey.setParameter("licensecode", crmlicensecode.getLicensecode());
            update = updateByPublickey.executeUpdate();
            utx.commit();
            
            return update;
        } finally {
            em.close();
        }
    }
}
