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
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmproject;
import com.sivotek.crm.persistent.dao.entities.Crmprojectstatus;
import com.sivotek.crm.persistent.dao.entities.CrmprojectstatusPK;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
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
public class CrmprojectstatusJpaController implements Serializable {

    public CrmprojectstatusJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmprojectstatusJpaController(){
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

    public void create(Crmprojectstatus crmprojectstatus) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmprojectstatus.getCrmprojectstatusPK() == null) {
            crmprojectstatus.setCrmprojectstatusPK(new CrmprojectstatusPK());
        }
        if (crmprojectstatus.getCrmprojectCollection() == null) {
            crmprojectstatus.setCrmprojectCollection(new ArrayList<Crmproject>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmprojectstatus.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmprojectstatus.setCompany(company);
            }
            Companyemployee companyemployee = crmprojectstatus.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmprojectstatus.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmprojectstatus.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmprojectstatus.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmproject> attachedCrmprojectCollection = new ArrayList<Crmproject>();
            for (Crmproject crmprojectCollectionCrmprojectToAttach : crmprojectstatus.getCrmprojectCollection()) {
                crmprojectCollectionCrmprojectToAttach = em.getReference(crmprojectCollectionCrmprojectToAttach.getClass(), crmprojectCollectionCrmprojectToAttach.getCrmprojectPK());
                attachedCrmprojectCollection.add(crmprojectCollectionCrmprojectToAttach);
            }
            crmprojectstatus.setCrmprojectCollection(attachedCrmprojectCollection);
            em.persist(crmprojectstatus);
            if (company != null) {
                company.getCrmprojectstatusCollection().add(crmprojectstatus);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCrmprojectstatusCollection().add(crmprojectstatus);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectstatusCollection().add(crmprojectstatus);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmproject crmprojectCollectionCrmproject : crmprojectstatus.getCrmprojectCollection()) {
                Crmprojectstatus oldCrmprojectstatusOfCrmprojectCollectionCrmproject = crmprojectCollectionCrmproject.getCrmprojectstatus();
                crmprojectCollectionCrmproject.setCrmprojectstatus(crmprojectstatus);
                crmprojectCollectionCrmproject = em.merge(crmprojectCollectionCrmproject);
                if (oldCrmprojectstatusOfCrmprojectCollectionCrmproject != null) {
                    oldCrmprojectstatusOfCrmprojectCollectionCrmproject.getCrmprojectCollection().remove(crmprojectCollectionCrmproject);
                    oldCrmprojectstatusOfCrmprojectCollectionCrmproject = em.merge(oldCrmprojectstatusOfCrmprojectCollectionCrmproject);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmprojectstatus(crmprojectstatus.getCrmprojectstatusPK()) != null) {
                throw new PreexistingEntityException("Crmprojectstatus " + crmprojectstatus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmprojectstatus crmprojectstatus) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectstatus persistentCrmprojectstatus = em.find(Crmprojectstatus.class, crmprojectstatus.getCrmprojectstatusPK());
            Company companyOld = persistentCrmprojectstatus.getCompany();
            Company companyNew = crmprojectstatus.getCompany();
            Companyemployee companyemployeeOld = persistentCrmprojectstatus.getCompanyemployee();
            Companyemployee companyemployeeNew = crmprojectstatus.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmprojectstatus.getCompanyemployee1();
            Companyemployee companyemployee1New = crmprojectstatus.getCompanyemployee1();
            Collection<Crmproject> crmprojectCollectionOld = persistentCrmprojectstatus.getCrmprojectCollection();
            Collection<Crmproject> crmprojectCollectionNew = crmprojectstatus.getCrmprojectCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmproject crmprojectCollectionOldCrmproject : crmprojectCollectionOld) {
                if (!crmprojectCollectionNew.contains(crmprojectCollectionOldCrmproject)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmproject " + crmprojectCollectionOldCrmproject + " since its crmprojectstatus field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmprojectstatus.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmprojectstatus.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmprojectstatus.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmproject> attachedCrmprojectCollectionNew = new ArrayList<Crmproject>();
            for (Crmproject crmprojectCollectionNewCrmprojectToAttach : crmprojectCollectionNew) {
                crmprojectCollectionNewCrmprojectToAttach = em.getReference(crmprojectCollectionNewCrmprojectToAttach.getClass(), crmprojectCollectionNewCrmprojectToAttach.getCrmprojectPK());
                attachedCrmprojectCollectionNew.add(crmprojectCollectionNewCrmprojectToAttach);
            }
            crmprojectCollectionNew = attachedCrmprojectCollectionNew;
            crmprojectstatus.setCrmprojectCollection(crmprojectCollectionNew);
            crmprojectstatus = em.merge(crmprojectstatus);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmprojectstatusCollection().remove(crmprojectstatus);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmprojectstatusCollection().add(crmprojectstatus);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmprojectstatusCollection().remove(crmprojectstatus);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmprojectstatusCollection().add(crmprojectstatus);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmprojectstatusCollection().remove(crmprojectstatus);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmprojectstatusCollection().add(crmprojectstatus);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmproject crmprojectCollectionNewCrmproject : crmprojectCollectionNew) {
                if (!crmprojectCollectionOld.contains(crmprojectCollectionNewCrmproject)) {
                    Crmprojectstatus oldCrmprojectstatusOfCrmprojectCollectionNewCrmproject = crmprojectCollectionNewCrmproject.getCrmprojectstatus();
                    crmprojectCollectionNewCrmproject.setCrmprojectstatus(crmprojectstatus);
                    crmprojectCollectionNewCrmproject = em.merge(crmprojectCollectionNewCrmproject);
                    if (oldCrmprojectstatusOfCrmprojectCollectionNewCrmproject != null && !oldCrmprojectstatusOfCrmprojectCollectionNewCrmproject.equals(crmprojectstatus)) {
                        oldCrmprojectstatusOfCrmprojectCollectionNewCrmproject.getCrmprojectCollection().remove(crmprojectCollectionNewCrmproject);
                        oldCrmprojectstatusOfCrmprojectCollectionNewCrmproject = em.merge(oldCrmprojectstatusOfCrmprojectCollectionNewCrmproject);
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
                CrmprojectstatusPK id = crmprojectstatus.getCrmprojectstatusPK();
                if (findCrmprojectstatus(id) == null) {
                    throw new NonexistentEntityException("The crmprojectstatus with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmprojectstatusPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectstatus crmprojectstatus;
            try {
                crmprojectstatus = em.getReference(Crmprojectstatus.class, id);
                crmprojectstatus.getCrmprojectstatusPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmprojectstatus with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmproject> crmprojectCollectionOrphanCheck = crmprojectstatus.getCrmprojectCollection();
            for (Crmproject crmprojectCollectionOrphanCheckCrmproject : crmprojectCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmprojectstatus (" + crmprojectstatus + ") cannot be destroyed since the Crmproject " + crmprojectCollectionOrphanCheckCrmproject + " in its crmprojectCollection field has a non-nullable crmprojectstatus field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = crmprojectstatus.getCompany();
            if (company != null) {
                company.getCrmprojectstatusCollection().remove(crmprojectstatus);
                company = em.merge(company);
            }
            Companyemployee companyemployee = crmprojectstatus.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmprojectstatusCollection().remove(crmprojectstatus);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmprojectstatus.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectstatusCollection().remove(crmprojectstatus);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmprojectstatus);
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

    public List<Crmprojectstatus> findCrmprojectstatusEntities() {
        return findCrmprojectstatusEntities(true, -1, -1);
    }

    public List<Crmprojectstatus> findCrmprojectstatusEntities(int maxResults, int firstResult) {
        return findCrmprojectstatusEntities(false, maxResults, firstResult);
    }

    private List<Crmprojectstatus> findCrmprojectstatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmprojectstatus.class));
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

    public Crmprojectstatus findCrmprojectstatus(CrmprojectstatusPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmprojectstatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmprojectstatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmprojectstatus> rt = cq.from(Crmprojectstatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
