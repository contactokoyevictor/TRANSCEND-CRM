/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import com.sivotek.crm.persistent.dao.entities.Crmscheduler;
import com.sivotek.crm.persistent.dao.entities.CrmschedulerPK;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author okoyevictor
 */
public class CrmschedulerJpaController implements Serializable {

    public CrmschedulerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmschedulerJpaController(){
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

    public void create(Crmscheduler crmscheduler) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmscheduler.getCrmschedulerPK() == null) {
            crmscheduler.setCrmschedulerPK(new CrmschedulerPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(crmscheduler);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmscheduler(crmscheduler.getCrmschedulerPK()) != null) {
                throw new PreexistingEntityException("Crmscheduler " + crmscheduler + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmscheduler crmscheduler) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            crmscheduler = em.merge(crmscheduler);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CrmschedulerPK id = crmscheduler.getCrmschedulerPK();
                if (findCrmscheduler(id) == null) {
                    throw new NonexistentEntityException("The crmscheduler with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmschedulerPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmscheduler crmscheduler;
            try {
                crmscheduler = em.getReference(Crmscheduler.class, id);
                crmscheduler.getCrmschedulerPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmscheduler with id " + id + " no longer exists.", enfe);
            }
            em.remove(crmscheduler);
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

    public List<Crmscheduler> findCrmschedulerEntities() {
        return findCrmschedulerEntities(true, -1, -1);
    }

    public List<Crmscheduler> findCrmschedulerEntities(int maxResults, int firstResult) {
        return findCrmschedulerEntities(false, maxResults, firstResult);
    }

    private List<Crmscheduler> findCrmschedulerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmscheduler.class));
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

    public Crmscheduler findCrmscheduler(CrmschedulerPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmscheduler.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmschedulerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmscheduler> rt = cq.from(Crmscheduler.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
  public Crmscheduler findByQuartzscheduleId(int quartzscheduleId) {
        EntityManager em = getEntityManager();
        try {
            
            Query findByQuartzscheduleId = em.createNamedQuery("Crmscheduler.findByQuartzscheduleId");
            findByQuartzscheduleId.setParameter("quartzscheduleId", quartzscheduleId);
            Crmscheduler crmscheduler = new Crmscheduler();
            crmscheduler = (Crmscheduler) findByQuartzscheduleId.getSingleResult();
            
            return crmscheduler;
        }catch(NoResultException e){
          return new Crmscheduler();
        }
        finally {
            em.close();
        }
   }
  
  //
  
public int updateByQuartzscheduleId(int schedulerid, Date lastruntime, Date nextruntime, Date changeddate, String changedfrom) {
//      System.out.println("UPDATE DATA :"+schedulerid+"\n"+lastruntime+"\n"+nextruntime+"\n"+changeddate+"\n"+changedfrom);
      //EntityManager em = getEntityManager();
      EntityManager em = null;
        int update = 0;
        try {
            utx.begin();
            em = getEntityManager();
            Query updateByQuartzscheduleId = em.createNamedQuery("Crmscheduler.updateByQuartzscheduleId");
            updateByQuartzscheduleId.setParameter("quartzscheduleId", schedulerid);
            updateByQuartzscheduleId.setParameter("lastRun", lastruntime);
            updateByQuartzscheduleId.setParameter("nextRun", nextruntime);
            updateByQuartzscheduleId.setParameter("changedfrom", changedfrom);
            updateByQuartzscheduleId.setParameter("changeddate", changeddate);
            update = updateByQuartzscheduleId.executeUpdate();
            
            utx.commit();
            em.close();
            
        } catch (Exception ex) 
        
        {
            //Logger.getLogger(CrmschedulerJpaController.class.getName()).log(Level.WARNING, "Exception Occoured :::{0}", ex.getMessage());
           
            //System.out.println("Exception Occoured :::"+ex.getMessage());
        }
       
        finally {
            em.close();
        }
        
       
        
        return update;
   } 

}
