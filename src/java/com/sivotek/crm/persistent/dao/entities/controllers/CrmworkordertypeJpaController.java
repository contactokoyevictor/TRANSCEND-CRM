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
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.Crmworkordertype;
import com.sivotek.crm.persistent.dao.entities.CrmworkordertypePK;
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
public class CrmworkordertypeJpaController implements Serializable {

    public CrmworkordertypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmworkordertypeJpaController(){
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

    public void create(Crmworkordertype crmworkordertype) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmworkordertype.getCrmworkordertypePK() == null) {
            crmworkordertype.setCrmworkordertypePK(new CrmworkordertypePK());
        }
        if (crmworkordertype.getCrmworkorderCollection() == null) {
            crmworkordertype.setCrmworkorderCollection(new ArrayList<Crmworkorder>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmworkordertype.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmworkordertype.setCompany(company);
            }
            Companyemployee companyemployee = crmworkordertype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmworkordertype.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmworkordertype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmworkordertype.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmworkorder> attachedCrmworkorderCollection = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionCrmworkorderToAttach : crmworkordertype.getCrmworkorderCollection()) {
                crmworkorderCollectionCrmworkorderToAttach = em.getReference(crmworkorderCollectionCrmworkorderToAttach.getClass(), crmworkorderCollectionCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollection.add(crmworkorderCollectionCrmworkorderToAttach);
            }
            crmworkordertype.setCrmworkorderCollection(attachedCrmworkorderCollection);
            em.persist(crmworkordertype);
            if (company != null) {
                company.getCrmworkordertypeCollection().add(crmworkordertype);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCrmworkordertypeCollection().add(crmworkordertype);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmworkordertypeCollection().add(crmworkordertype);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmworkorder crmworkorderCollectionCrmworkorder : crmworkordertype.getCrmworkorderCollection()) {
                Crmworkordertype oldCrmworkordertypeOfCrmworkorderCollectionCrmworkorder = crmworkorderCollectionCrmworkorder.getCrmworkordertype();
                crmworkorderCollectionCrmworkorder.setCrmworkordertype(crmworkordertype);
                crmworkorderCollectionCrmworkorder = em.merge(crmworkorderCollectionCrmworkorder);
                if (oldCrmworkordertypeOfCrmworkorderCollectionCrmworkorder != null) {
                    oldCrmworkordertypeOfCrmworkorderCollectionCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionCrmworkorder);
                    oldCrmworkordertypeOfCrmworkorderCollectionCrmworkorder = em.merge(oldCrmworkordertypeOfCrmworkorderCollectionCrmworkorder);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmworkordertype(crmworkordertype.getCrmworkordertypePK()) != null) {
                throw new PreexistingEntityException("Crmworkordertype " + crmworkordertype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmworkordertype crmworkordertype) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmworkordertype persistentCrmworkordertype = em.find(Crmworkordertype.class, crmworkordertype.getCrmworkordertypePK());
            Company companyOld = persistentCrmworkordertype.getCompany();
            Company companyNew = crmworkordertype.getCompany();
            Companyemployee companyemployeeOld = persistentCrmworkordertype.getCompanyemployee();
            Companyemployee companyemployeeNew = crmworkordertype.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmworkordertype.getCompanyemployee1();
            Companyemployee companyemployee1New = crmworkordertype.getCompanyemployee1();
            Collection<Crmworkorder> crmworkorderCollectionOld = persistentCrmworkordertype.getCrmworkorderCollection();
            Collection<Crmworkorder> crmworkorderCollectionNew = crmworkordertype.getCrmworkorderCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmworkorder crmworkorderCollectionOldCrmworkorder : crmworkorderCollectionOld) {
                if (!crmworkorderCollectionNew.contains(crmworkorderCollectionOldCrmworkorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorder " + crmworkorderCollectionOldCrmworkorder + " since its crmworkordertype field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmworkordertype.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmworkordertype.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmworkordertype.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmworkorder> attachedCrmworkorderCollectionNew = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionNewCrmworkorderToAttach : crmworkorderCollectionNew) {
                crmworkorderCollectionNewCrmworkorderToAttach = em.getReference(crmworkorderCollectionNewCrmworkorderToAttach.getClass(), crmworkorderCollectionNewCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollectionNew.add(crmworkorderCollectionNewCrmworkorderToAttach);
            }
            crmworkorderCollectionNew = attachedCrmworkorderCollectionNew;
            crmworkordertype.setCrmworkorderCollection(crmworkorderCollectionNew);
            crmworkordertype = em.merge(crmworkordertype);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmworkordertypeCollection().remove(crmworkordertype);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmworkordertypeCollection().add(crmworkordertype);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmworkordertypeCollection().remove(crmworkordertype);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmworkordertypeCollection().add(crmworkordertype);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmworkordertypeCollection().remove(crmworkordertype);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmworkordertypeCollection().add(crmworkordertype);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmworkorder crmworkorderCollectionNewCrmworkorder : crmworkorderCollectionNew) {
                if (!crmworkorderCollectionOld.contains(crmworkorderCollectionNewCrmworkorder)) {
                    Crmworkordertype oldCrmworkordertypeOfCrmworkorderCollectionNewCrmworkorder = crmworkorderCollectionNewCrmworkorder.getCrmworkordertype();
                    crmworkorderCollectionNewCrmworkorder.setCrmworkordertype(crmworkordertype);
                    crmworkorderCollectionNewCrmworkorder = em.merge(crmworkorderCollectionNewCrmworkorder);
                    if (oldCrmworkordertypeOfCrmworkorderCollectionNewCrmworkorder != null && !oldCrmworkordertypeOfCrmworkorderCollectionNewCrmworkorder.equals(crmworkordertype)) {
                        oldCrmworkordertypeOfCrmworkorderCollectionNewCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionNewCrmworkorder);
                        oldCrmworkordertypeOfCrmworkorderCollectionNewCrmworkorder = em.merge(oldCrmworkordertypeOfCrmworkorderCollectionNewCrmworkorder);
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
                CrmworkordertypePK id = crmworkordertype.getCrmworkordertypePK();
                if (findCrmworkordertype(id) == null) {
                    throw new NonexistentEntityException("The crmworkordertype with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmworkordertypePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmworkordertype crmworkordertype;
            try {
                crmworkordertype = em.getReference(Crmworkordertype.class, id);
                crmworkordertype.getCrmworkordertypePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmworkordertype with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmworkorder> crmworkorderCollectionOrphanCheck = crmworkordertype.getCrmworkorderCollection();
            for (Crmworkorder crmworkorderCollectionOrphanCheckCrmworkorder : crmworkorderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmworkordertype (" + crmworkordertype + ") cannot be destroyed since the Crmworkorder " + crmworkorderCollectionOrphanCheckCrmworkorder + " in its crmworkorderCollection field has a non-nullable crmworkordertype field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = crmworkordertype.getCompany();
            if (company != null) {
                company.getCrmworkordertypeCollection().remove(crmworkordertype);
                company = em.merge(company);
            }
            Companyemployee companyemployee = crmworkordertype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmworkordertypeCollection().remove(crmworkordertype);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmworkordertype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmworkordertypeCollection().remove(crmworkordertype);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmworkordertype);
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

    public List<Crmworkordertype> findCrmworkordertypeEntities() {
        return findCrmworkordertypeEntities(true, -1, -1);
    }

    public List<Crmworkordertype> findCrmworkordertypeEntities(int maxResults, int firstResult) {
        return findCrmworkordertypeEntities(false, maxResults, firstResult);
    }

    private List<Crmworkordertype> findCrmworkordertypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmworkordertype.class));
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

    public Crmworkordertype findCrmworkordertype(CrmworkordertypePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmworkordertype.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmworkordertypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmworkordertype> rt = cq.from(Crmworkordertype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
