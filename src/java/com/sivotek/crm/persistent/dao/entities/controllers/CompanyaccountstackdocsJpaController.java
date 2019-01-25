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
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackcd;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackdocs;
import com.sivotek.crm.persistent.dao.entities.CompanyaccountstackdocsPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
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
public class CompanyaccountstackdocsJpaController implements Serializable {

    public CompanyaccountstackdocsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanyaccountstackdocsJpaController(){
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

    public void create(Companyaccountstackdocs companyaccountstackdocs) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyaccountstackdocs.getCompanyaccountstackdocsPK() == null) {
            companyaccountstackdocs.setCompanyaccountstackdocsPK(new CompanyaccountstackdocsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyaccountstackcd companyaccountstackcd = companyaccountstackdocs.getCompanyaccountstackcd();
            if (companyaccountstackcd != null) {
                companyaccountstackcd = em.getReference(companyaccountstackcd.getClass(), companyaccountstackcd.getCompanyaccountstackcdPK());
                companyaccountstackdocs.setCompanyaccountstackcd(companyaccountstackcd);
            }
            Company company = companyaccountstackdocs.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companyaccountstackdocs.setCompany(company);
            }
            Companyemployee companyemployee = companyaccountstackdocs.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companyaccountstackdocs.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companyaccountstackdocs.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companyaccountstackdocs.setCompanyemployee1(companyemployee1);
            }
            em.persist(companyaccountstackdocs);
            if (companyaccountstackcd != null) {
                companyaccountstackcd.getCompanyaccountstackdocsCollection().add(companyaccountstackdocs);
                companyaccountstackcd = em.merge(companyaccountstackcd);
            }
            if (company != null) {
                company.getCompanyaccountstackdocsCollection().add(companyaccountstackdocs);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanyaccountstackdocsCollection().add(companyaccountstackdocs);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanyaccountstackdocsCollection().add(companyaccountstackdocs);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyaccountstackdocs(companyaccountstackdocs.getCompanyaccountstackdocsPK()) != null) {
                throw new PreexistingEntityException("Companyaccountstackdocs " + companyaccountstackdocs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companyaccountstackdocs companyaccountstackdocs) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyaccountstackdocs persistentCompanyaccountstackdocs = em.find(Companyaccountstackdocs.class, companyaccountstackdocs.getCompanyaccountstackdocsPK());
            Companyaccountstackcd companyaccountstackcdOld = persistentCompanyaccountstackdocs.getCompanyaccountstackcd();
            Companyaccountstackcd companyaccountstackcdNew = companyaccountstackdocs.getCompanyaccountstackcd();
            Company companyOld = persistentCompanyaccountstackdocs.getCompany();
            Company companyNew = companyaccountstackdocs.getCompany();
            Companyemployee companyemployeeOld = persistentCompanyaccountstackdocs.getCompanyemployee();
            Companyemployee companyemployeeNew = companyaccountstackdocs.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanyaccountstackdocs.getCompanyemployee1();
            Companyemployee companyemployee1New = companyaccountstackdocs.getCompanyemployee1();
            if (companyaccountstackcdNew != null) {
                companyaccountstackcdNew = em.getReference(companyaccountstackcdNew.getClass(), companyaccountstackcdNew.getCompanyaccountstackcdPK());
                companyaccountstackdocs.setCompanyaccountstackcd(companyaccountstackcdNew);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companyaccountstackdocs.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companyaccountstackdocs.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companyaccountstackdocs.setCompanyemployee1(companyemployee1New);
            }
            companyaccountstackdocs = em.merge(companyaccountstackdocs);
            if (companyaccountstackcdOld != null && !companyaccountstackcdOld.equals(companyaccountstackcdNew)) {
                companyaccountstackcdOld.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocs);
                companyaccountstackcdOld = em.merge(companyaccountstackcdOld);
            }
            if (companyaccountstackcdNew != null && !companyaccountstackcdNew.equals(companyaccountstackcdOld)) {
                companyaccountstackcdNew.getCompanyaccountstackdocsCollection().add(companyaccountstackdocs);
                companyaccountstackcdNew = em.merge(companyaccountstackcdNew);
            }
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocs);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanyaccountstackdocsCollection().add(companyaccountstackdocs);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocs);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanyaccountstackdocsCollection().add(companyaccountstackdocs);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocs);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanyaccountstackdocsCollection().add(companyaccountstackdocs);
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
                CompanyaccountstackdocsPK id = companyaccountstackdocs.getCompanyaccountstackdocsPK();
                if (findCompanyaccountstackdocs(id) == null) {
                    throw new NonexistentEntityException("The companyaccountstackdocs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyaccountstackdocsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyaccountstackdocs companyaccountstackdocs;
            try {
                companyaccountstackdocs = em.getReference(Companyaccountstackdocs.class, id);
                companyaccountstackdocs.getCompanyaccountstackdocsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyaccountstackdocs with id " + id + " no longer exists.", enfe);
            }
            Companyaccountstackcd companyaccountstackcd = companyaccountstackdocs.getCompanyaccountstackcd();
            if (companyaccountstackcd != null) {
                companyaccountstackcd.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocs);
                companyaccountstackcd = em.merge(companyaccountstackcd);
            }
            Company company = companyaccountstackdocs.getCompany();
            if (company != null) {
                company.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocs);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companyaccountstackdocs.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocs);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companyaccountstackdocs.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocs);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companyaccountstackdocs);
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

    public List<Companyaccountstackdocs> findCompanyaccountstackdocsEntities() {
        return findCompanyaccountstackdocsEntities(true, -1, -1);
    }

    public List<Companyaccountstackdocs> findCompanyaccountstackdocsEntities(int maxResults, int firstResult) {
        return findCompanyaccountstackdocsEntities(false, maxResults, firstResult);
    }

    private List<Companyaccountstackdocs> findCompanyaccountstackdocsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companyaccountstackdocs.class));
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

    public Companyaccountstackdocs findCompanyaccountstackdocs(CompanyaccountstackdocsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companyaccountstackdocs.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyaccountstackdocsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companyaccountstackdocs> rt = cq.from(Companyaccountstackdocs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
