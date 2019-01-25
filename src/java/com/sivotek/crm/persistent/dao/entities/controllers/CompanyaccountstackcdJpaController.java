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
import com.sivotek.crm.persistent.dao.entities.Companyaccountstack;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackcd;
import com.sivotek.crm.persistent.dao.entities.CompanyaccountstackcdPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackdocs;
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
public class CompanyaccountstackcdJpaController implements Serializable {

    public CompanyaccountstackcdJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanyaccountstackcdJpaController(){
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

    public void create(Companyaccountstackcd companyaccountstackcd) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyaccountstackcd.getCompanyaccountstackcdPK() == null) {
            companyaccountstackcd.setCompanyaccountstackcdPK(new CompanyaccountstackcdPK());
        }
        if (companyaccountstackcd.getCompanyaccountstackdocsCollection() == null) {
            companyaccountstackcd.setCompanyaccountstackdocsCollection(new ArrayList<Companyaccountstackdocs>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyaccountstack companyaccountstack = companyaccountstackcd.getCompanyaccountstack();
            if (companyaccountstack != null) {
                companyaccountstack = em.getReference(companyaccountstack.getClass(), companyaccountstack.getCompanyaccountstackPK());
                companyaccountstackcd.setCompanyaccountstack(companyaccountstack);
            }
            Company company = companyaccountstackcd.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companyaccountstackcd.setCompany(company);
            }
            Companyemployee companyemployee = companyaccountstackcd.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companyaccountstackcd.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companyaccountstackcd.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companyaccountstackcd.setCompanyemployee1(companyemployee1);
            }
            Collection<Companyaccountstackdocs> attachedCompanyaccountstackdocsCollection = new ArrayList<Companyaccountstackdocs>();
            for (Companyaccountstackdocs companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach : companyaccountstackcd.getCompanyaccountstackdocsCollection()) {
                companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach = em.getReference(companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach.getClass(), companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach.getCompanyaccountstackdocsPK());
                attachedCompanyaccountstackdocsCollection.add(companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach);
            }
            companyaccountstackcd.setCompanyaccountstackdocsCollection(attachedCompanyaccountstackdocsCollection);
            em.persist(companyaccountstackcd);
            if (companyaccountstack != null) {
                companyaccountstack.getCompanyaccountstackcdCollection().add(companyaccountstackcd);
                companyaccountstack = em.merge(companyaccountstack);
            }
            if (company != null) {
                company.getCompanyaccountstackcdCollection().add(companyaccountstackcd);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanyaccountstackcdCollection().add(companyaccountstackcd);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanyaccountstackcdCollection().add(companyaccountstackcd);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollectionCompanyaccountstackdocs : companyaccountstackcd.getCompanyaccountstackdocsCollection()) {
                Companyaccountstackcd oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs = companyaccountstackdocsCollectionCompanyaccountstackdocs.getCompanyaccountstackcd();
                companyaccountstackdocsCollectionCompanyaccountstackdocs.setCompanyaccountstackcd(companyaccountstackcd);
                companyaccountstackdocsCollectionCompanyaccountstackdocs = em.merge(companyaccountstackdocsCollectionCompanyaccountstackdocs);
                if (oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs != null) {
                    oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocsCollectionCompanyaccountstackdocs);
                    oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs = em.merge(oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyaccountstackcd(companyaccountstackcd.getCompanyaccountstackcdPK()) != null) {
                throw new PreexistingEntityException("Companyaccountstackcd " + companyaccountstackcd + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companyaccountstackcd companyaccountstackcd) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyaccountstackcd persistentCompanyaccountstackcd = em.find(Companyaccountstackcd.class, companyaccountstackcd.getCompanyaccountstackcdPK());
            Companyaccountstack companyaccountstackOld = persistentCompanyaccountstackcd.getCompanyaccountstack();
            Companyaccountstack companyaccountstackNew = companyaccountstackcd.getCompanyaccountstack();
            Company companyOld = persistentCompanyaccountstackcd.getCompany();
            Company companyNew = companyaccountstackcd.getCompany();
            Companyemployee companyemployeeOld = persistentCompanyaccountstackcd.getCompanyemployee();
            Companyemployee companyemployeeNew = companyaccountstackcd.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanyaccountstackcd.getCompanyemployee1();
            Companyemployee companyemployee1New = companyaccountstackcd.getCompanyemployee1();
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollectionOld = persistentCompanyaccountstackcd.getCompanyaccountstackdocsCollection();
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollectionNew = companyaccountstackcd.getCompanyaccountstackdocsCollection();
            List<String> illegalOrphanMessages = null;
            for (Companyaccountstackdocs companyaccountstackdocsCollectionOldCompanyaccountstackdocs : companyaccountstackdocsCollectionOld) {
                if (!companyaccountstackdocsCollectionNew.contains(companyaccountstackdocsCollectionOldCompanyaccountstackdocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstackdocs " + companyaccountstackdocsCollectionOldCompanyaccountstackdocs + " since its companyaccountstackcd field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyaccountstackNew != null) {
                companyaccountstackNew = em.getReference(companyaccountstackNew.getClass(), companyaccountstackNew.getCompanyaccountstackPK());
                companyaccountstackcd.setCompanyaccountstack(companyaccountstackNew);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companyaccountstackcd.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companyaccountstackcd.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companyaccountstackcd.setCompanyemployee1(companyemployee1New);
            }
            Collection<Companyaccountstackdocs> attachedCompanyaccountstackdocsCollectionNew = new ArrayList<Companyaccountstackdocs>();
            for (Companyaccountstackdocs companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach : companyaccountstackdocsCollectionNew) {
                companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach = em.getReference(companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach.getClass(), companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach.getCompanyaccountstackdocsPK());
                attachedCompanyaccountstackdocsCollectionNew.add(companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach);
            }
            companyaccountstackdocsCollectionNew = attachedCompanyaccountstackdocsCollectionNew;
            companyaccountstackcd.setCompanyaccountstackdocsCollection(companyaccountstackdocsCollectionNew);
            companyaccountstackcd = em.merge(companyaccountstackcd);
            if (companyaccountstackOld != null && !companyaccountstackOld.equals(companyaccountstackNew)) {
                companyaccountstackOld.getCompanyaccountstackcdCollection().remove(companyaccountstackcd);
                companyaccountstackOld = em.merge(companyaccountstackOld);
            }
            if (companyaccountstackNew != null && !companyaccountstackNew.equals(companyaccountstackOld)) {
                companyaccountstackNew.getCompanyaccountstackcdCollection().add(companyaccountstackcd);
                companyaccountstackNew = em.merge(companyaccountstackNew);
            }
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanyaccountstackcdCollection().remove(companyaccountstackcd);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanyaccountstackcdCollection().add(companyaccountstackcd);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanyaccountstackcdCollection().remove(companyaccountstackcd);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanyaccountstackcdCollection().add(companyaccountstackcd);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanyaccountstackcdCollection().remove(companyaccountstackcd);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanyaccountstackcdCollection().add(companyaccountstackcd);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollectionNewCompanyaccountstackdocs : companyaccountstackdocsCollectionNew) {
                if (!companyaccountstackdocsCollectionOld.contains(companyaccountstackdocsCollectionNewCompanyaccountstackdocs)) {
                    Companyaccountstackcd oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs = companyaccountstackdocsCollectionNewCompanyaccountstackdocs.getCompanyaccountstackcd();
                    companyaccountstackdocsCollectionNewCompanyaccountstackdocs.setCompanyaccountstackcd(companyaccountstackcd);
                    companyaccountstackdocsCollectionNewCompanyaccountstackdocs = em.merge(companyaccountstackdocsCollectionNewCompanyaccountstackdocs);
                    if (oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs != null && !oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs.equals(companyaccountstackcd)) {
                        oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocsCollectionNewCompanyaccountstackdocs);
                        oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs = em.merge(oldCompanyaccountstackcdOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs);
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
                CompanyaccountstackcdPK id = companyaccountstackcd.getCompanyaccountstackcdPK();
                if (findCompanyaccountstackcd(id) == null) {
                    throw new NonexistentEntityException("The companyaccountstackcd with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyaccountstackcdPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyaccountstackcd companyaccountstackcd;
            try {
                companyaccountstackcd = em.getReference(Companyaccountstackcd.class, id);
                companyaccountstackcd.getCompanyaccountstackcdPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyaccountstackcd with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollectionOrphanCheck = companyaccountstackcd.getCompanyaccountstackdocsCollection();
            for (Companyaccountstackdocs companyaccountstackdocsCollectionOrphanCheckCompanyaccountstackdocs : companyaccountstackdocsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyaccountstackcd (" + companyaccountstackcd + ") cannot be destroyed since the Companyaccountstackdocs " + companyaccountstackdocsCollectionOrphanCheckCompanyaccountstackdocs + " in its companyaccountstackdocsCollection field has a non-nullable companyaccountstackcd field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Companyaccountstack companyaccountstack = companyaccountstackcd.getCompanyaccountstack();
            if (companyaccountstack != null) {
                companyaccountstack.getCompanyaccountstackcdCollection().remove(companyaccountstackcd);
                companyaccountstack = em.merge(companyaccountstack);
            }
            Company company = companyaccountstackcd.getCompany();
            if (company != null) {
                company.getCompanyaccountstackcdCollection().remove(companyaccountstackcd);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companyaccountstackcd.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanyaccountstackcdCollection().remove(companyaccountstackcd);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companyaccountstackcd.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanyaccountstackcdCollection().remove(companyaccountstackcd);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companyaccountstackcd);
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

    public List<Companyaccountstackcd> findCompanyaccountstackcdEntities() {
        return findCompanyaccountstackcdEntities(true, -1, -1);
    }

    public List<Companyaccountstackcd> findCompanyaccountstackcdEntities(int maxResults, int firstResult) {
        return findCompanyaccountstackcdEntities(false, maxResults, firstResult);
    }

    private List<Companyaccountstackcd> findCompanyaccountstackcdEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companyaccountstackcd.class));
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

    public Companyaccountstackcd findCompanyaccountstackcd(CompanyaccountstackcdPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companyaccountstackcd.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyaccountstackcdCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companyaccountstackcd> rt = cq.from(Companyaccountstackcd.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
