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
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.Workorderinstructions;
import com.sivotek.crm.persistent.dao.entities.WorkorderinstructionsPK;
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
import javax.transaction.UserTransaction;

/**
 *
 * @author okoyevictor
 */
public class WorkorderinstructionsJpaController implements Serializable {

    public WorkorderinstructionsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public WorkorderinstructionsJpaController(){
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
    
    public void create(Workorderinstructions workorderinstructions) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (workorderinstructions.getWorkorderinstructionsPK() == null) {
            workorderinstructions.setWorkorderinstructionsPK(new WorkorderinstructionsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = workorderinstructions.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                workorderinstructions.setCompanyemployee(companyemployee);
            }
            Crmworkorder crmworkorder = workorderinstructions.getCrmworkorder();
            if (crmworkorder != null) {
                crmworkorder = em.getReference(crmworkorder.getClass(), crmworkorder.getCrmworkorderPK());
                workorderinstructions.setCrmworkorder(crmworkorder);
            }
            Companyemployee companyemployee1 = workorderinstructions.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                workorderinstructions.setCompanyemployee1(companyemployee1);
            }
            em.persist(workorderinstructions);
            if (companyemployee != null) {
                companyemployee.getWorkorderinstructionsCollection().add(workorderinstructions);
                companyemployee = em.merge(companyemployee);
            }
            if (crmworkorder != null) {
                crmworkorder.getWorkorderinstructionsCollection().add(workorderinstructions);
                crmworkorder = em.merge(crmworkorder);
            }
            if (companyemployee1 != null) {
                companyemployee1.getWorkorderinstructionsCollection().add(workorderinstructions);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findWorkorderinstructions(workorderinstructions.getWorkorderinstructionsPK()) != null) {
                throw new PreexistingEntityException("Workorderinstructions " + workorderinstructions + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Workorderinstructions workorderinstructions) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Workorderinstructions persistentWorkorderinstructions = em.find(Workorderinstructions.class, workorderinstructions.getWorkorderinstructionsPK());
            Companyemployee companyemployeeOld = persistentWorkorderinstructions.getCompanyemployee();
            Companyemployee companyemployeeNew = workorderinstructions.getCompanyemployee();
            Crmworkorder crmworkorderOld = persistentWorkorderinstructions.getCrmworkorder();
            Crmworkorder crmworkorderNew = workorderinstructions.getCrmworkorder();
            Companyemployee companyemployee1Old = persistentWorkorderinstructions.getCompanyemployee1();
            Companyemployee companyemployee1New = workorderinstructions.getCompanyemployee1();
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                workorderinstructions.setCompanyemployee(companyemployeeNew);
            }
            if (crmworkorderNew != null) {
                crmworkorderNew = em.getReference(crmworkorderNew.getClass(), crmworkorderNew.getCrmworkorderPK());
                workorderinstructions.setCrmworkorder(crmworkorderNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                workorderinstructions.setCompanyemployee1(companyemployee1New);
            }
            workorderinstructions = em.merge(workorderinstructions);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getWorkorderinstructionsCollection().remove(workorderinstructions);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getWorkorderinstructionsCollection().add(workorderinstructions);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (crmworkorderOld != null && !crmworkorderOld.equals(crmworkorderNew)) {
                crmworkorderOld.getWorkorderinstructionsCollection().remove(workorderinstructions);
                crmworkorderOld = em.merge(crmworkorderOld);
            }
            if (crmworkorderNew != null && !crmworkorderNew.equals(crmworkorderOld)) {
                crmworkorderNew.getWorkorderinstructionsCollection().add(workorderinstructions);
                crmworkorderNew = em.merge(crmworkorderNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getWorkorderinstructionsCollection().remove(workorderinstructions);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getWorkorderinstructionsCollection().add(workorderinstructions);
                companyemployee1New = em.merge(companyemployee1New);
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
                WorkorderinstructionsPK id = workorderinstructions.getWorkorderinstructionsPK();
                if (findWorkorderinstructions(id) == null) {
                    throw new NonexistentEntityException("The workorderinstructions with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(WorkorderinstructionsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Workorderinstructions workorderinstructions;
            try {
                workorderinstructions = em.getReference(Workorderinstructions.class, id);
                workorderinstructions.getWorkorderinstructionsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The workorderinstructions with id " + id + " no longer exists.", enfe);
            }
            Companyemployee companyemployee = workorderinstructions.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getWorkorderinstructionsCollection().remove(workorderinstructions);
                companyemployee = em.merge(companyemployee);
            }
            Crmworkorder crmworkorder = workorderinstructions.getCrmworkorder();
            if (crmworkorder != null) {
                crmworkorder.getWorkorderinstructionsCollection().remove(workorderinstructions);
                crmworkorder = em.merge(crmworkorder);
            }
            Companyemployee companyemployee1 = workorderinstructions.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getWorkorderinstructionsCollection().remove(workorderinstructions);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(workorderinstructions);
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

    public List<Workorderinstructions> findWorkorderinstructionsEntities() {
        return findWorkorderinstructionsEntities(true, -1, -1);
    }

    public List<Workorderinstructions> findWorkorderinstructionsEntities(int maxResults, int firstResult) {
        return findWorkorderinstructionsEntities(false, maxResults, firstResult);
    }

    private List<Workorderinstructions> findWorkorderinstructionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Workorderinstructions.class));
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

    public Workorderinstructions findWorkorderinstructions(WorkorderinstructionsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Workorderinstructions.class, id);
        } finally {
            em.close();
        }
    }

    public int getWorkorderinstructionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Workorderinstructions> rt = cq.from(Workorderinstructions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
