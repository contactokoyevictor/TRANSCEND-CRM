/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import com.sivotek.crm.persistent.dao.entities.Crmusers;
import com.sivotek.crm.persistent.dao.entities.CrmusersPK;
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
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author okoyevictor
 */
public class CrmusersJpaController implements Serializable {

    public CrmusersJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmusersJpaController(){
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

    public void create(Crmusers crmusers) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmusers.getCrmusersPK() == null) {
            crmusers.setCrmusersPK(new CrmusersPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(crmusers);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmusers(crmusers.getCrmusersPK()) != null) {
                throw new PreexistingEntityException("Crmusers " + crmusers + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmusers crmusers) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            crmusers = em.merge(crmusers);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CrmusersPK id = crmusers.getCrmusersPK();
                if (findCrmusers(id) == null) {
                    throw new NonexistentEntityException("The crmusers with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmusersPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmusers crmusers;
            try {
                crmusers = em.getReference(Crmusers.class, id);
                crmusers.getCrmusersPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmusers with id " + id + " no longer exists.", enfe);
            }
            em.remove(crmusers);
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

    public List<Crmusers> findCrmusersEntities() {
        return findCrmusersEntities(true, -1, -1);
    }

    public List<Crmusers> findCrmusersEntities(int maxResults, int firstResult) {
        return findCrmusersEntities(false, maxResults, firstResult);
    }

    private List<Crmusers> findCrmusersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmusers.class));
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

    public Crmusers findCrmusers(CrmusersPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmusers.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmusersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmusers> rt = cq.from(Crmusers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Crmusers findByCrmuserPasswd(String username, String password) {
        EntityManager em = getEntityManager();
        try {
            Query findByCrmuserPasswd = em.createNamedQuery("Crmusers.findByCrmuserPasswd");
            findByCrmuserPasswd.setParameter("crmuser", username);
            findByCrmuserPasswd.setParameter("passwd", password);
            Crmusers crmusers = new Crmusers();
            crmusers = (Crmusers) findByCrmuserPasswd.getSingleResult();
//            System.out.println("companyid and pubkey :"+crmusers.getCompany().getCompanyPK().getCompanyid() +" : "+crmusers.getCompany().getCompanyPK().getPubkey()
//            +" :"+crmusers.getIsAdmin());
            return crmusers;
        } finally {
            em.close();
        }
    }

}
