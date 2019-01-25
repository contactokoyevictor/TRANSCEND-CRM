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
import com.sivotek.crm.persistent.dao.entities.Companybank;
import com.sivotek.crm.persistent.dao.entities.CompanybankPK;
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
public class CompanybankJpaController implements Serializable {

    public CompanybankJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanybankJpaController(){
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

    public void create(Companybank companybank) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companybank.getCompanybankPK() == null) {
            companybank.setCompanybankPK(new CompanybankPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companybank.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companybank.setCompany(company);
            }
            Companyemployee companyemployee = companybank.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companybank.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companybank.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companybank.setCompanyemployee1(companyemployee1);
            }
            em.persist(companybank);
            if (company != null) {
                company.getCompanybankCollection().add(companybank);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanybankCollection().add(companybank);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanybankCollection().add(companybank);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanybank(companybank.getCompanybankPK()) != null) {
                throw new PreexistingEntityException("Companybank " + companybank + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companybank companybank) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companybank persistentCompanybank = em.find(Companybank.class, companybank.getCompanybankPK());
            Company companyOld = persistentCompanybank.getCompany();
            Company companyNew = companybank.getCompany();
            Companyemployee companyemployeeOld = persistentCompanybank.getCompanyemployee();
            Companyemployee companyemployeeNew = companybank.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanybank.getCompanyemployee1();
            Companyemployee companyemployee1New = companybank.getCompanyemployee1();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companybank.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companybank.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companybank.setCompanyemployee1(companyemployee1New);
            }
            companybank = em.merge(companybank);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanybankCollection().remove(companybank);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanybankCollection().add(companybank);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanybankCollection().remove(companybank);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanybankCollection().add(companybank);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanybankCollection().remove(companybank);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanybankCollection().add(companybank);
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
                CompanybankPK id = companybank.getCompanybankPK();
                if (findCompanybank(id) == null) {
                    throw new NonexistentEntityException("The companybank with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanybankPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companybank companybank;
            try {
                companybank = em.getReference(Companybank.class, id);
                companybank.getCompanybankPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companybank with id " + id + " no longer exists.", enfe);
            }
            Company company = companybank.getCompany();
            if (company != null) {
                company.getCompanybankCollection().remove(companybank);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companybank.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanybankCollection().remove(companybank);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companybank.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanybankCollection().remove(companybank);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companybank);
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

    public List<Companybank> findCompanybankEntities() {
        return findCompanybankEntities(true, -1, -1);
    }

    public List<Companybank> findCompanybankEntities(int maxResults, int firstResult) {
        return findCompanybankEntities(false, maxResults, firstResult);
    }

    private List<Companybank> findCompanybankEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companybank.class));
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

    public Companybank findCompanybank(CompanybankPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companybank.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanybankCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companybank> rt = cq.from(Companybank.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
