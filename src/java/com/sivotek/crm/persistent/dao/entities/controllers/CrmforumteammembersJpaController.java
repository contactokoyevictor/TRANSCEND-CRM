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
import com.sivotek.crm.persistent.dao.entities.Crmforum;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmforumteammembers;
import com.sivotek.crm.persistent.dao.entities.CrmforumteammembersPK;
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
public class CrmforumteammembersJpaController implements Serializable {

    public CrmforumteammembersJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmforumteammembersJpaController(){
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

    public void create(Crmforumteammembers crmforumteammembers) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmforumteammembers.getCrmforumteammembersPK() == null) {
            crmforumteammembers.setCrmforumteammembersPK(new CrmforumteammembersPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmforum crmforum = crmforumteammembers.getCrmforum();
            if (crmforum != null) {
                crmforum = em.getReference(crmforum.getClass(), crmforum.getCrmforumPK());
                crmforumteammembers.setCrmforum(crmforum);
            }
            Companyemployee companyemployee = crmforumteammembers.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmforumteammembers.setCompanyemployee(companyemployee);
            }
            em.persist(crmforumteammembers);
            if (crmforum != null) {
                crmforum.getCrmforumteammembersCollection().add(crmforumteammembers);
                crmforum = em.merge(crmforum);
            }
            if (companyemployee != null) {
                companyemployee.getCrmforumteammembersCollection().add(crmforumteammembers);
                companyemployee = em.merge(companyemployee);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmforumteammembers(crmforumteammembers.getCrmforumteammembersPK()) != null) {
                throw new PreexistingEntityException("Crmforumteammembers " + crmforumteammembers + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmforumteammembers crmforumteammembers) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmforumteammembers persistentCrmforumteammembers = em.find(Crmforumteammembers.class, crmforumteammembers.getCrmforumteammembersPK());
            Crmforum crmforumOld = persistentCrmforumteammembers.getCrmforum();
            Crmforum crmforumNew = crmforumteammembers.getCrmforum();
            Companyemployee companyemployeeOld = persistentCrmforumteammembers.getCompanyemployee();
            Companyemployee companyemployeeNew = crmforumteammembers.getCompanyemployee();
            if (crmforumNew != null) {
                crmforumNew = em.getReference(crmforumNew.getClass(), crmforumNew.getCrmforumPK());
                crmforumteammembers.setCrmforum(crmforumNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmforumteammembers.setCompanyemployee(companyemployeeNew);
            }
            crmforumteammembers = em.merge(crmforumteammembers);
            if (crmforumOld != null && !crmforumOld.equals(crmforumNew)) {
                crmforumOld.getCrmforumteammembersCollection().remove(crmforumteammembers);
                crmforumOld = em.merge(crmforumOld);
            }
            if (crmforumNew != null && !crmforumNew.equals(crmforumOld)) {
                crmforumNew.getCrmforumteammembersCollection().add(crmforumteammembers);
                crmforumNew = em.merge(crmforumNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmforumteammembersCollection().remove(crmforumteammembers);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmforumteammembersCollection().add(crmforumteammembers);
                companyemployeeNew = em.merge(companyemployeeNew);
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
                CrmforumteammembersPK id = crmforumteammembers.getCrmforumteammembersPK();
                if (findCrmforumteammembers(id) == null) {
                    throw new NonexistentEntityException("The crmforumteammembers with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmforumteammembersPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmforumteammembers crmforumteammembers;
            try {
                crmforumteammembers = em.getReference(Crmforumteammembers.class, id);
                crmforumteammembers.getCrmforumteammembersPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmforumteammembers with id " + id + " no longer exists.", enfe);
            }
            Crmforum crmforum = crmforumteammembers.getCrmforum();
            if (crmforum != null) {
                crmforum.getCrmforumteammembersCollection().remove(crmforumteammembers);
                crmforum = em.merge(crmforum);
            }
            Companyemployee companyemployee = crmforumteammembers.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmforumteammembersCollection().remove(crmforumteammembers);
                companyemployee = em.merge(companyemployee);
            }
            em.remove(crmforumteammembers);
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

    public List<Crmforumteammembers> findCrmforumteammembersEntities() {
        return findCrmforumteammembersEntities(true, -1, -1);
    }

    public List<Crmforumteammembers> findCrmforumteammembersEntities(int maxResults, int firstResult) {
        return findCrmforumteammembersEntities(false, maxResults, firstResult);
    }

    private List<Crmforumteammembers> findCrmforumteammembersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmforumteammembers.class));
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

    public Crmforumteammembers findCrmforumteammembers(CrmforumteammembersPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmforumteammembers.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmforumteammembersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmforumteammembers> rt = cq.from(Crmforumteammembers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
