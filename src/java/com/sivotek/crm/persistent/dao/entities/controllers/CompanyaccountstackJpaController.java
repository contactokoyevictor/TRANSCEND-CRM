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
import com.sivotek.crm.persistent.dao.entities.Companyaccountstack;
import com.sivotek.crm.persistent.dao.entities.CompanyaccountstackPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackcd;
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
public class CompanyaccountstackJpaController implements Serializable {

    public CompanyaccountstackJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanyaccountstackJpaController(){
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

    public void create(Companyaccountstack companyaccountstack) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyaccountstack.getCompanyaccountstackPK() == null) {
            companyaccountstack.setCompanyaccountstackPK(new CompanyaccountstackPK());
        }
        if (companyaccountstack.getCompanyaccountstackcdCollection() == null) {
            companyaccountstack.setCompanyaccountstackcdCollection(new ArrayList<Companyaccountstackcd>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companyaccountstack.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companyaccountstack.setCompany(company);
            }
            Companyemployee companyemployee = companyaccountstack.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companyaccountstack.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companyaccountstack.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companyaccountstack.setCompanyemployee1(companyemployee1);
            }
            Collection<Companyaccountstackcd> attachedCompanyaccountstackcdCollection = new ArrayList<Companyaccountstackcd>();
            for (Companyaccountstackcd companyaccountstackcdCollectionCompanyaccountstackcdToAttach : companyaccountstack.getCompanyaccountstackcdCollection()) {
                companyaccountstackcdCollectionCompanyaccountstackcdToAttach = em.getReference(companyaccountstackcdCollectionCompanyaccountstackcdToAttach.getClass(), companyaccountstackcdCollectionCompanyaccountstackcdToAttach.getCompanyaccountstackcdPK());
                attachedCompanyaccountstackcdCollection.add(companyaccountstackcdCollectionCompanyaccountstackcdToAttach);
            }
            companyaccountstack.setCompanyaccountstackcdCollection(attachedCompanyaccountstackcdCollection);
            em.persist(companyaccountstack);
            if (company != null) {
                company.getCompanyaccountstackCollection().add(companyaccountstack);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanyaccountstackCollection().add(companyaccountstack);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanyaccountstackCollection().add(companyaccountstack);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Companyaccountstackcd companyaccountstackcdCollectionCompanyaccountstackcd : companyaccountstack.getCompanyaccountstackcdCollection()) {
                Companyaccountstack oldCompanyaccountstackOfCompanyaccountstackcdCollectionCompanyaccountstackcd = companyaccountstackcdCollectionCompanyaccountstackcd.getCompanyaccountstack();
                companyaccountstackcdCollectionCompanyaccountstackcd.setCompanyaccountstack(companyaccountstack);
                companyaccountstackcdCollectionCompanyaccountstackcd = em.merge(companyaccountstackcdCollectionCompanyaccountstackcd);
                if (oldCompanyaccountstackOfCompanyaccountstackcdCollectionCompanyaccountstackcd != null) {
                    oldCompanyaccountstackOfCompanyaccountstackcdCollectionCompanyaccountstackcd.getCompanyaccountstackcdCollection().remove(companyaccountstackcdCollectionCompanyaccountstackcd);
                    oldCompanyaccountstackOfCompanyaccountstackcdCollectionCompanyaccountstackcd = em.merge(oldCompanyaccountstackOfCompanyaccountstackcdCollectionCompanyaccountstackcd);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyaccountstack(companyaccountstack.getCompanyaccountstackPK()) != null) {
                throw new PreexistingEntityException("Companyaccountstack " + companyaccountstack + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companyaccountstack companyaccountstack) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyaccountstack persistentCompanyaccountstack = em.find(Companyaccountstack.class, companyaccountstack.getCompanyaccountstackPK());
            Company companyOld = persistentCompanyaccountstack.getCompany();
            Company companyNew = companyaccountstack.getCompany();
            Companyemployee companyemployeeOld = persistentCompanyaccountstack.getCompanyemployee();
            Companyemployee companyemployeeNew = companyaccountstack.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanyaccountstack.getCompanyemployee1();
            Companyemployee companyemployee1New = companyaccountstack.getCompanyemployee1();
            Collection<Companyaccountstackcd> companyaccountstackcdCollectionOld = persistentCompanyaccountstack.getCompanyaccountstackcdCollection();
            Collection<Companyaccountstackcd> companyaccountstackcdCollectionNew = companyaccountstack.getCompanyaccountstackcdCollection();
            List<String> illegalOrphanMessages = null;
            for (Companyaccountstackcd companyaccountstackcdCollectionOldCompanyaccountstackcd : companyaccountstackcdCollectionOld) {
                if (!companyaccountstackcdCollectionNew.contains(companyaccountstackcdCollectionOldCompanyaccountstackcd)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstackcd " + companyaccountstackcdCollectionOldCompanyaccountstackcd + " since its companyaccountstack field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companyaccountstack.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companyaccountstack.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companyaccountstack.setCompanyemployee1(companyemployee1New);
            }
            Collection<Companyaccountstackcd> attachedCompanyaccountstackcdCollectionNew = new ArrayList<Companyaccountstackcd>();
            for (Companyaccountstackcd companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach : companyaccountstackcdCollectionNew) {
                companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach = em.getReference(companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach.getClass(), companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach.getCompanyaccountstackcdPK());
                attachedCompanyaccountstackcdCollectionNew.add(companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach);
            }
            companyaccountstackcdCollectionNew = attachedCompanyaccountstackcdCollectionNew;
            companyaccountstack.setCompanyaccountstackcdCollection(companyaccountstackcdCollectionNew);
            companyaccountstack = em.merge(companyaccountstack);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanyaccountstackCollection().remove(companyaccountstack);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanyaccountstackCollection().add(companyaccountstack);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanyaccountstackCollection().remove(companyaccountstack);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanyaccountstackCollection().add(companyaccountstack);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanyaccountstackCollection().remove(companyaccountstack);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanyaccountstackCollection().add(companyaccountstack);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Companyaccountstackcd companyaccountstackcdCollectionNewCompanyaccountstackcd : companyaccountstackcdCollectionNew) {
                if (!companyaccountstackcdCollectionOld.contains(companyaccountstackcdCollectionNewCompanyaccountstackcd)) {
                    Companyaccountstack oldCompanyaccountstackOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd = companyaccountstackcdCollectionNewCompanyaccountstackcd.getCompanyaccountstack();
                    companyaccountstackcdCollectionNewCompanyaccountstackcd.setCompanyaccountstack(companyaccountstack);
                    companyaccountstackcdCollectionNewCompanyaccountstackcd = em.merge(companyaccountstackcdCollectionNewCompanyaccountstackcd);
                    if (oldCompanyaccountstackOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd != null && !oldCompanyaccountstackOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd.equals(companyaccountstack)) {
                        oldCompanyaccountstackOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd.getCompanyaccountstackcdCollection().remove(companyaccountstackcdCollectionNewCompanyaccountstackcd);
                        oldCompanyaccountstackOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd = em.merge(oldCompanyaccountstackOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd);
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
                CompanyaccountstackPK id = companyaccountstack.getCompanyaccountstackPK();
                if (findCompanyaccountstack(id) == null) {
                    throw new NonexistentEntityException("The companyaccountstack with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyaccountstackPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyaccountstack companyaccountstack;
            try {
                companyaccountstack = em.getReference(Companyaccountstack.class, id);
                companyaccountstack.getCompanyaccountstackPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyaccountstack with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Companyaccountstackcd> companyaccountstackcdCollectionOrphanCheck = companyaccountstack.getCompanyaccountstackcdCollection();
            for (Companyaccountstackcd companyaccountstackcdCollectionOrphanCheckCompanyaccountstackcd : companyaccountstackcdCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyaccountstack (" + companyaccountstack + ") cannot be destroyed since the Companyaccountstackcd " + companyaccountstackcdCollectionOrphanCheckCompanyaccountstackcd + " in its companyaccountstackcdCollection field has a non-nullable companyaccountstack field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companyaccountstack.getCompany();
            if (company != null) {
                company.getCompanyaccountstackCollection().remove(companyaccountstack);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companyaccountstack.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanyaccountstackCollection().remove(companyaccountstack);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companyaccountstack.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanyaccountstackCollection().remove(companyaccountstack);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companyaccountstack);
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

    public List<Companyaccountstack> findCompanyaccountstackEntities() {
        return findCompanyaccountstackEntities(true, -1, -1);
    }

    public List<Companyaccountstack> findCompanyaccountstackEntities(int maxResults, int firstResult) {
        return findCompanyaccountstackEntities(false, maxResults, firstResult);
    }

    private List<Companyaccountstack> findCompanyaccountstackEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companyaccountstack.class));
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

    public Companyaccountstack findCompanyaccountstack(CompanyaccountstackPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companyaccountstack.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyaccountstackCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companyaccountstack> rt = cq.from(Companyaccountstack.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
