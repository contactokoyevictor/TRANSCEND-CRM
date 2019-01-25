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
import com.sivotek.crm.persistent.dao.entities.Crmcampaign;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignstatus;
import com.sivotek.crm.persistent.dao.entities.CrmcampaignstatusPK;
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
public class CrmcampaignstatusJpaController implements Serializable {

    public CrmcampaignstatusJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     public CrmcampaignstatusJpaController(){
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

    public void create(Crmcampaignstatus crmcampaignstatus) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmcampaignstatus.getCrmcampaignstatusPK() == null) {
            crmcampaignstatus.setCrmcampaignstatusPK(new CrmcampaignstatusPK());
        }
        if (crmcampaignstatus.getCrmcampaignCollection() == null) {
            crmcampaignstatus.setCrmcampaignCollection(new ArrayList<Crmcampaign>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmcampaignstatus.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmcampaignstatus.setCompany(company);
            }
            Companyemployee companyemployee = crmcampaignstatus.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmcampaignstatus.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmcampaignstatus.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmcampaignstatus.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmcampaign> attachedCrmcampaignCollection = new ArrayList<Crmcampaign>();
            for (Crmcampaign crmcampaignCollectionCrmcampaignToAttach : crmcampaignstatus.getCrmcampaignCollection()) {
                crmcampaignCollectionCrmcampaignToAttach = em.getReference(crmcampaignCollectionCrmcampaignToAttach.getClass(), crmcampaignCollectionCrmcampaignToAttach.getCrmcampaignPK());
                attachedCrmcampaignCollection.add(crmcampaignCollectionCrmcampaignToAttach);
            }
            crmcampaignstatus.setCrmcampaignCollection(attachedCrmcampaignCollection);
            em.persist(crmcampaignstatus);
            if (company != null) {
                company.getCrmcampaignstatusCollection().add(crmcampaignstatus);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCrmcampaignstatusCollection().add(crmcampaignstatus);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmcampaignstatusCollection().add(crmcampaignstatus);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmcampaign crmcampaignCollectionCrmcampaign : crmcampaignstatus.getCrmcampaignCollection()) {
                Crmcampaignstatus oldCrmcampaignstatusOfCrmcampaignCollectionCrmcampaign = crmcampaignCollectionCrmcampaign.getCrmcampaignstatus();
                crmcampaignCollectionCrmcampaign.setCrmcampaignstatus(crmcampaignstatus);
                crmcampaignCollectionCrmcampaign = em.merge(crmcampaignCollectionCrmcampaign);
                if (oldCrmcampaignstatusOfCrmcampaignCollectionCrmcampaign != null) {
                    oldCrmcampaignstatusOfCrmcampaignCollectionCrmcampaign.getCrmcampaignCollection().remove(crmcampaignCollectionCrmcampaign);
                    oldCrmcampaignstatusOfCrmcampaignCollectionCrmcampaign = em.merge(oldCrmcampaignstatusOfCrmcampaignCollectionCrmcampaign);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmcampaignstatus(crmcampaignstatus.getCrmcampaignstatusPK()) != null) {
                throw new PreexistingEntityException("Crmcampaignstatus " + crmcampaignstatus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmcampaignstatus crmcampaignstatus) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaignstatus persistentCrmcampaignstatus = em.find(Crmcampaignstatus.class, crmcampaignstatus.getCrmcampaignstatusPK());
            Company companyOld = persistentCrmcampaignstatus.getCompany();
            Company companyNew = crmcampaignstatus.getCompany();
            Companyemployee companyemployeeOld = persistentCrmcampaignstatus.getCompanyemployee();
            Companyemployee companyemployeeNew = crmcampaignstatus.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmcampaignstatus.getCompanyemployee1();
            Companyemployee companyemployee1New = crmcampaignstatus.getCompanyemployee1();
            Collection<Crmcampaign> crmcampaignCollectionOld = persistentCrmcampaignstatus.getCrmcampaignCollection();
            Collection<Crmcampaign> crmcampaignCollectionNew = crmcampaignstatus.getCrmcampaignCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmcampaign crmcampaignCollectionOldCrmcampaign : crmcampaignCollectionOld) {
                if (!crmcampaignCollectionNew.contains(crmcampaignCollectionOldCrmcampaign)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaign " + crmcampaignCollectionOldCrmcampaign + " since its crmcampaignstatus field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmcampaignstatus.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmcampaignstatus.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmcampaignstatus.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmcampaign> attachedCrmcampaignCollectionNew = new ArrayList<Crmcampaign>();
            for (Crmcampaign crmcampaignCollectionNewCrmcampaignToAttach : crmcampaignCollectionNew) {
                crmcampaignCollectionNewCrmcampaignToAttach = em.getReference(crmcampaignCollectionNewCrmcampaignToAttach.getClass(), crmcampaignCollectionNewCrmcampaignToAttach.getCrmcampaignPK());
                attachedCrmcampaignCollectionNew.add(crmcampaignCollectionNewCrmcampaignToAttach);
            }
            crmcampaignCollectionNew = attachedCrmcampaignCollectionNew;
            crmcampaignstatus.setCrmcampaignCollection(crmcampaignCollectionNew);
            crmcampaignstatus = em.merge(crmcampaignstatus);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmcampaignstatusCollection().remove(crmcampaignstatus);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmcampaignstatusCollection().add(crmcampaignstatus);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmcampaignstatusCollection().remove(crmcampaignstatus);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmcampaignstatusCollection().add(crmcampaignstatus);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmcampaignstatusCollection().remove(crmcampaignstatus);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmcampaignstatusCollection().add(crmcampaignstatus);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmcampaign crmcampaignCollectionNewCrmcampaign : crmcampaignCollectionNew) {
                if (!crmcampaignCollectionOld.contains(crmcampaignCollectionNewCrmcampaign)) {
                    Crmcampaignstatus oldCrmcampaignstatusOfCrmcampaignCollectionNewCrmcampaign = crmcampaignCollectionNewCrmcampaign.getCrmcampaignstatus();
                    crmcampaignCollectionNewCrmcampaign.setCrmcampaignstatus(crmcampaignstatus);
                    crmcampaignCollectionNewCrmcampaign = em.merge(crmcampaignCollectionNewCrmcampaign);
                    if (oldCrmcampaignstatusOfCrmcampaignCollectionNewCrmcampaign != null && !oldCrmcampaignstatusOfCrmcampaignCollectionNewCrmcampaign.equals(crmcampaignstatus)) {
                        oldCrmcampaignstatusOfCrmcampaignCollectionNewCrmcampaign.getCrmcampaignCollection().remove(crmcampaignCollectionNewCrmcampaign);
                        oldCrmcampaignstatusOfCrmcampaignCollectionNewCrmcampaign = em.merge(oldCrmcampaignstatusOfCrmcampaignCollectionNewCrmcampaign);
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
                CrmcampaignstatusPK id = crmcampaignstatus.getCrmcampaignstatusPK();
                if (findCrmcampaignstatus(id) == null) {
                    throw new NonexistentEntityException("The crmcampaignstatus with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmcampaignstatusPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaignstatus crmcampaignstatus;
            try {
                crmcampaignstatus = em.getReference(Crmcampaignstatus.class, id);
                crmcampaignstatus.getCrmcampaignstatusPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmcampaignstatus with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmcampaign> crmcampaignCollectionOrphanCheck = crmcampaignstatus.getCrmcampaignCollection();
            for (Crmcampaign crmcampaignCollectionOrphanCheckCrmcampaign : crmcampaignCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmcampaignstatus (" + crmcampaignstatus + ") cannot be destroyed since the Crmcampaign " + crmcampaignCollectionOrphanCheckCrmcampaign + " in its crmcampaignCollection field has a non-nullable crmcampaignstatus field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = crmcampaignstatus.getCompany();
            if (company != null) {
                company.getCrmcampaignstatusCollection().remove(crmcampaignstatus);
                company = em.merge(company);
            }
            Companyemployee companyemployee = crmcampaignstatus.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmcampaignstatusCollection().remove(crmcampaignstatus);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmcampaignstatus.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmcampaignstatusCollection().remove(crmcampaignstatus);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmcampaignstatus);
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

    public List<Crmcampaignstatus> findCrmcampaignstatusEntities() {
        return findCrmcampaignstatusEntities(true, -1, -1);
    }

    public List<Crmcampaignstatus> findCrmcampaignstatusEntities(int maxResults, int firstResult) {
        return findCrmcampaignstatusEntities(false, maxResults, firstResult);
    }

    private List<Crmcampaignstatus> findCrmcampaignstatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmcampaignstatus.class));
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

    public Crmcampaignstatus findCrmcampaignstatus(CrmcampaignstatusPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmcampaignstatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmcampaignstatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmcampaignstatus> rt = cq.from(Crmcampaignstatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
