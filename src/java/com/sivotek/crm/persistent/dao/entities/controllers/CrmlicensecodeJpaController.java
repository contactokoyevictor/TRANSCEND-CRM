/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import com.sivotek.crm.persistent.dao.entities.Crmlicensecode;
import com.sivotek.crm.persistent.dao.entities.CrmlicensecodePK;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import javax.persistence.Persistence;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
/**
 *
 * @author okoyevictor
 */
public class CrmlicensecodeJpaController implements Serializable {

    public CrmlicensecodeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public CrmlicensecodeJpaController(){
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

    public void create(Crmlicensecode crmlicensecode) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmlicensecode.getCrmlicensecodePK() == null) {
            crmlicensecode.setCrmlicensecodePK(new CrmlicensecodePK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(crmlicensecode);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmlicensecode(crmlicensecode.getCrmlicensecodePK()) != null) {
                throw new PreexistingEntityException("Crmlicensecode " + crmlicensecode + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmlicensecode crmlicensecode) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            crmlicensecode = em.merge(crmlicensecode);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CrmlicensecodePK id = crmlicensecode.getCrmlicensecodePK();
                if (findCrmlicensecode(id) == null) {
                    throw new NonexistentEntityException("The crmlicensecode with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmlicensecodePK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlicensecode crmlicensecode;
            try {
                crmlicensecode = em.getReference(Crmlicensecode.class, id);
                crmlicensecode.getCrmlicensecodePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmlicensecode with id " + id + " no longer exists.", enfe);
            }
            em.remove(crmlicensecode);
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

    public List<Crmlicensecode> findCrmlicensecodeEntities() {
        return findCrmlicensecodeEntities(true, -1, -1);
    }

    public List<Crmlicensecode> findCrmlicensecodeEntities(int maxResults, int firstResult) {
        return findCrmlicensecodeEntities(false, maxResults, firstResult);
    }

    private List<Crmlicensecode> findCrmlicensecodeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmlicensecode.class));
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

    public Crmlicensecode findCrmlicensecode(CrmlicensecodePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmlicensecode.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmlicensecodeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmlicensecode> rt = cq.from(Crmlicensecode.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Crmlicensecode findByLicensecode(String licensecode) {
        EntityManager em = getEntityManager();
        try {
            Query findByLicensecode = em.createNamedQuery("Crmlicensecode.findByLicensecode");
            findByLicensecode.setParameter("licensecode", licensecode);
            Crmlicensecode crmlicensecode = new Crmlicensecode();
            crmlicensecode = (Crmlicensecode) findByLicensecode.getSingleResult();
            return crmlicensecode;
        } finally {
            em.close();
        }
      
    }
      
   public Crmlicensecode findByPublicKey(Crmlicensecode crmlicensecode1) {
        EntityManager em = getEntityManager();
        try {
            Query findByLicensekey = em.createNamedQuery("Crmlicensecode.findByLicensekey");
            findByLicensekey.setParameter("licensekey", crmlicensecode1.getLicensekey());
            Crmlicensecode crmlicensecode = new Crmlicensecode();
            crmlicensecode = (Crmlicensecode) findByLicensekey.getSingleResult();
            return crmlicensecode;
        } finally {
            em.close();
        }
   }
 
  //
  public int updateByPublickey(Crmlicensecode crmlicensecode) throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException, javax.transaction.RollbackException {
        EntityManager em = null;
        int update = 0;
        try {
            utx.begin();
            em = getEntityManager();
            Query updateByLicensekey = em.createNamedQuery("Crmlicensecode.updateByLicensekey");
            updateByLicensekey.setParameter("licensekey", crmlicensecode.getLicensekey());
            updateByLicensekey.setParameter("licensecode", crmlicensecode.getLicensecode());
            
            update = updateByLicensekey.executeUpdate();
            
            System.out.println("licensecode is :"+crmlicensecode.getLicensecode());
            System.out.println("public key is :"+crmlicensecode.getLicensekey());
            
            utx.commit();
            
            return update;
        } finally {
            em.close();
        }
}

}
