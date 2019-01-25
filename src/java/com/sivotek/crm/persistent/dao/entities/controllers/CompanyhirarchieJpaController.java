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
import com.sivotek.crm.persistent.dao.entities.Companyhirarchie;
import com.sivotek.crm.persistent.dao.entities.CompanyhirarchiePK;
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
public class CompanyhirarchieJpaController implements Serializable {

    public CompanyhirarchieJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanyhirarchieJpaController(){
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

    public void create(Companyhirarchie companyhirarchie) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyhirarchie.getCompanyhirarchiePK() == null) {
            companyhirarchie.setCompanyhirarchiePK(new CompanyhirarchiePK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companyhirarchie.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companyhirarchie.setCompany(company);
            }
            Company company1 = companyhirarchie.getCompany1();
            if (company1 != null) {
                company1 = em.getReference(company1.getClass(), company1.getCompanyPK());
                companyhirarchie.setCompany1(company1);
            }
            Company company2 = companyhirarchie.getCompany2();
            if (company2 != null) {
                company2 = em.getReference(company2.getClass(), company2.getCompanyPK());
                companyhirarchie.setCompany2(company2);
            }
            em.persist(companyhirarchie);
            if (company != null) {
                company.getCompanyhirarchieCollection().add(companyhirarchie);
                company = em.merge(company);
            }
            if (company1 != null) {
                company1.getCompanyhirarchieCollection().add(companyhirarchie);
                company1 = em.merge(company1);
            }
            if (company2 != null) {
                company2.getCompanyhirarchieCollection().add(companyhirarchie);
                company2 = em.merge(company2);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyhirarchie(companyhirarchie.getCompanyhirarchiePK()) != null) {
                throw new PreexistingEntityException("Companyhirarchie " + companyhirarchie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companyhirarchie companyhirarchie) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyhirarchie persistentCompanyhirarchie = em.find(Companyhirarchie.class, companyhirarchie.getCompanyhirarchiePK());
            Company companyOld = persistentCompanyhirarchie.getCompany();
            Company companyNew = companyhirarchie.getCompany();
            Company company1Old = persistentCompanyhirarchie.getCompany1();
            Company company1New = companyhirarchie.getCompany1();
            Company company2Old = persistentCompanyhirarchie.getCompany2();
            Company company2New = companyhirarchie.getCompany2();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companyhirarchie.setCompany(companyNew);
            }
            if (company1New != null) {
                company1New = em.getReference(company1New.getClass(), company1New.getCompanyPK());
                companyhirarchie.setCompany1(company1New);
            }
            if (company2New != null) {
                company2New = em.getReference(company2New.getClass(), company2New.getCompanyPK());
                companyhirarchie.setCompany2(company2New);
            }
            companyhirarchie = em.merge(companyhirarchie);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanyhirarchieCollection().remove(companyhirarchie);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanyhirarchieCollection().add(companyhirarchie);
                companyNew = em.merge(companyNew);
            }
            if (company1Old != null && !company1Old.equals(company1New)) {
                company1Old.getCompanyhirarchieCollection().remove(companyhirarchie);
                company1Old = em.merge(company1Old);
            }
            if (company1New != null && !company1New.equals(company1Old)) {
                company1New.getCompanyhirarchieCollection().add(companyhirarchie);
                company1New = em.merge(company1New);
            }
            if (company2Old != null && !company2Old.equals(company2New)) {
                company2Old.getCompanyhirarchieCollection().remove(companyhirarchie);
                company2Old = em.merge(company2Old);
            }
            if (company2New != null && !company2New.equals(company2Old)) {
                company2New.getCompanyhirarchieCollection().add(companyhirarchie);
                company2New = em.merge(company2New);
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
                CompanyhirarchiePK id = companyhirarchie.getCompanyhirarchiePK();
                if (findCompanyhirarchie(id) == null) {
                    throw new NonexistentEntityException("The companyhirarchie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyhirarchiePK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyhirarchie companyhirarchie;
            try {
                companyhirarchie = em.getReference(Companyhirarchie.class, id);
                companyhirarchie.getCompanyhirarchiePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyhirarchie with id " + id + " no longer exists.", enfe);
            }
            Company company = companyhirarchie.getCompany();
            if (company != null) {
                company.getCompanyhirarchieCollection().remove(companyhirarchie);
                company = em.merge(company);
            }
            Company company1 = companyhirarchie.getCompany1();
            if (company1 != null) {
                company1.getCompanyhirarchieCollection().remove(companyhirarchie);
                company1 = em.merge(company1);
            }
            Company company2 = companyhirarchie.getCompany2();
            if (company2 != null) {
                company2.getCompanyhirarchieCollection().remove(companyhirarchie);
                company2 = em.merge(company2);
            }
            em.remove(companyhirarchie);
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

    public List<Companyhirarchie> findCompanyhirarchieEntities() {
        return findCompanyhirarchieEntities(true, -1, -1);
    }

    public List<Companyhirarchie> findCompanyhirarchieEntities(int maxResults, int firstResult) {
        return findCompanyhirarchieEntities(false, maxResults, firstResult);
    }

    private List<Companyhirarchie> findCompanyhirarchieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companyhirarchie.class));
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

    public Companyhirarchie findCompanyhirarchie(CompanyhirarchiePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companyhirarchie.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyhirarchieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companyhirarchie> rt = cq.from(Companyhirarchie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
