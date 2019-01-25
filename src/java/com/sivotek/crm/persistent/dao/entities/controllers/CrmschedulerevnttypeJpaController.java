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
import com.sivotek.crm.persistent.dao.entities.Crmschedulerevnttype;
import com.sivotek.crm.persistent.dao.entities.CrmschedulerevnttypePK;
import com.sivotek.crm.persistent.dao.entities.Crmvisitor;
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
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author okoyevictor
 */
public class CrmschedulerevnttypeJpaController implements Serializable {

    public CrmschedulerevnttypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmschedulerevnttypeJpaController(){
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

    public void create(Crmschedulerevnttype crmschedulerevnttype) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmschedulerevnttype.getCrmschedulerevnttypePK() == null) {
            crmschedulerevnttype.setCrmschedulerevnttypePK(new CrmschedulerevnttypePK());
        }
        if (crmschedulerevnttype.getCrmvisitorCollection() == null) {
            crmschedulerevnttype.setCrmvisitorCollection(new ArrayList<Crmvisitor>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmschedulerevnttype.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmschedulerevnttype.setCompany(company);
            }
            Companyemployee companyemployee = crmschedulerevnttype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmschedulerevnttype.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmschedulerevnttype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmschedulerevnttype.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmvisitor> attachedCrmvisitorCollection = new ArrayList<Crmvisitor>();
            for (Crmvisitor crmvisitorCollectionCrmvisitorToAttach : crmschedulerevnttype.getCrmvisitorCollection()) {
                crmvisitorCollectionCrmvisitorToAttach = em.getReference(crmvisitorCollectionCrmvisitorToAttach.getClass(), crmvisitorCollectionCrmvisitorToAttach.getCrmvisitorPK());
                attachedCrmvisitorCollection.add(crmvisitorCollectionCrmvisitorToAttach);
            }
            crmschedulerevnttype.setCrmvisitorCollection(attachedCrmvisitorCollection);
            em.persist(crmschedulerevnttype);
            if (company != null) {
                company.getCrmschedulerevnttypeCollection().add(crmschedulerevnttype);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCrmschedulerevnttypeCollection().add(crmschedulerevnttype);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmschedulerevnttypeCollection().add(crmschedulerevnttype);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmvisitor crmvisitorCollectionCrmvisitor : crmschedulerevnttype.getCrmvisitorCollection()) {
                Crmschedulerevnttype oldCrmschedulerevnttypeOfCrmvisitorCollectionCrmvisitor = crmvisitorCollectionCrmvisitor.getCrmschedulerevnttype();
                crmvisitorCollectionCrmvisitor.setCrmschedulerevnttype(crmschedulerevnttype);
                crmvisitorCollectionCrmvisitor = em.merge(crmvisitorCollectionCrmvisitor);
                if (oldCrmschedulerevnttypeOfCrmvisitorCollectionCrmvisitor != null) {
                    oldCrmschedulerevnttypeOfCrmvisitorCollectionCrmvisitor.getCrmvisitorCollection().remove(crmvisitorCollectionCrmvisitor);
                    oldCrmschedulerevnttypeOfCrmvisitorCollectionCrmvisitor = em.merge(oldCrmschedulerevnttypeOfCrmvisitorCollectionCrmvisitor);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmschedulerevnttype(crmschedulerevnttype.getCrmschedulerevnttypePK()) != null) {
                throw new PreexistingEntityException("Crmschedulerevnttype " + crmschedulerevnttype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmschedulerevnttype crmschedulerevnttype) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmschedulerevnttype persistentCrmschedulerevnttype = em.find(Crmschedulerevnttype.class, crmschedulerevnttype.getCrmschedulerevnttypePK());
            Company companyOld = persistentCrmschedulerevnttype.getCompany();
            Company companyNew = crmschedulerevnttype.getCompany();
            Companyemployee companyemployeeOld = persistentCrmschedulerevnttype.getCompanyemployee();
            Companyemployee companyemployeeNew = crmschedulerevnttype.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmschedulerevnttype.getCompanyemployee1();
            Companyemployee companyemployee1New = crmschedulerevnttype.getCompanyemployee1();
            Collection<Crmvisitor> crmvisitorCollectionOld = persistentCrmschedulerevnttype.getCrmvisitorCollection();
            Collection<Crmvisitor> crmvisitorCollectionNew = crmschedulerevnttype.getCrmvisitorCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmvisitor crmvisitorCollectionOldCrmvisitor : crmvisitorCollectionOld) {
                if (!crmvisitorCollectionNew.contains(crmvisitorCollectionOldCrmvisitor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmvisitor " + crmvisitorCollectionOldCrmvisitor + " since its crmschedulerevnttype field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmschedulerevnttype.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmschedulerevnttype.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmschedulerevnttype.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmvisitor> attachedCrmvisitorCollectionNew = new ArrayList<Crmvisitor>();
            for (Crmvisitor crmvisitorCollectionNewCrmvisitorToAttach : crmvisitorCollectionNew) {
                crmvisitorCollectionNewCrmvisitorToAttach = em.getReference(crmvisitorCollectionNewCrmvisitorToAttach.getClass(), crmvisitorCollectionNewCrmvisitorToAttach.getCrmvisitorPK());
                attachedCrmvisitorCollectionNew.add(crmvisitorCollectionNewCrmvisitorToAttach);
            }
            crmvisitorCollectionNew = attachedCrmvisitorCollectionNew;
            crmschedulerevnttype.setCrmvisitorCollection(crmvisitorCollectionNew);
            crmschedulerevnttype = em.merge(crmschedulerevnttype);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmschedulerevnttypeCollection().remove(crmschedulerevnttype);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmschedulerevnttypeCollection().add(crmschedulerevnttype);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmschedulerevnttypeCollection().remove(crmschedulerevnttype);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmschedulerevnttypeCollection().add(crmschedulerevnttype);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmschedulerevnttypeCollection().remove(crmschedulerevnttype);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmschedulerevnttypeCollection().add(crmschedulerevnttype);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmvisitor crmvisitorCollectionNewCrmvisitor : crmvisitorCollectionNew) {
                if (!crmvisitorCollectionOld.contains(crmvisitorCollectionNewCrmvisitor)) {
                    Crmschedulerevnttype oldCrmschedulerevnttypeOfCrmvisitorCollectionNewCrmvisitor = crmvisitorCollectionNewCrmvisitor.getCrmschedulerevnttype();
                    crmvisitorCollectionNewCrmvisitor.setCrmschedulerevnttype(crmschedulerevnttype);
                    crmvisitorCollectionNewCrmvisitor = em.merge(crmvisitorCollectionNewCrmvisitor);
                    if (oldCrmschedulerevnttypeOfCrmvisitorCollectionNewCrmvisitor != null && !oldCrmschedulerevnttypeOfCrmvisitorCollectionNewCrmvisitor.equals(crmschedulerevnttype)) {
                        oldCrmschedulerevnttypeOfCrmvisitorCollectionNewCrmvisitor.getCrmvisitorCollection().remove(crmvisitorCollectionNewCrmvisitor);
                        oldCrmschedulerevnttypeOfCrmvisitorCollectionNewCrmvisitor = em.merge(oldCrmschedulerevnttypeOfCrmvisitorCollectionNewCrmvisitor);
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
                CrmschedulerevnttypePK id = crmschedulerevnttype.getCrmschedulerevnttypePK();
                if (findCrmschedulerevnttype(id) == null) {
                    throw new NonexistentEntityException("The crmschedulerevnttype with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmschedulerevnttypePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmschedulerevnttype crmschedulerevnttype;
            try {
                crmschedulerevnttype = em.getReference(Crmschedulerevnttype.class, id);
                crmschedulerevnttype.getCrmschedulerevnttypePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmschedulerevnttype with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmvisitor> crmvisitorCollectionOrphanCheck = crmschedulerevnttype.getCrmvisitorCollection();
            for (Crmvisitor crmvisitorCollectionOrphanCheckCrmvisitor : crmvisitorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmschedulerevnttype (" + crmschedulerevnttype + ") cannot be destroyed since the Crmvisitor " + crmvisitorCollectionOrphanCheckCrmvisitor + " in its crmvisitorCollection field has a non-nullable crmschedulerevnttype field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = crmschedulerevnttype.getCompany();
            if (company != null) {
                company.getCrmschedulerevnttypeCollection().remove(crmschedulerevnttype);
                company = em.merge(company);
            }
            Companyemployee companyemployee = crmschedulerevnttype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmschedulerevnttypeCollection().remove(crmschedulerevnttype);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmschedulerevnttype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmschedulerevnttypeCollection().remove(crmschedulerevnttype);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmschedulerevnttype);
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

    public List<Crmschedulerevnttype> findCrmschedulerevnttypeEntities() {
        return findCrmschedulerevnttypeEntities(true, -1, -1);
    }

    public List<Crmschedulerevnttype> findCrmschedulerevnttypeEntities(int maxResults, int firstResult) {
        return findCrmschedulerevnttypeEntities(false, maxResults, firstResult);
    }

    private List<Crmschedulerevnttype> findCrmschedulerevnttypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmschedulerevnttype.class));
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

    public Crmschedulerevnttype findCrmschedulerevnttype(CrmschedulerevnttypePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmschedulerevnttype.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmschedulerevnttypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmschedulerevnttype> rt = cq.from(Crmschedulerevnttype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
