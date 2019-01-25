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
import com.sivotek.crm.persistent.dao.entities.Customercategory;
import com.sivotek.crm.persistent.dao.entities.CustomercategoryPK;
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
public class CustomercategoryJpaController implements Serializable {

    public CustomercategoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomercategoryJpaController(){
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

    public void create(Customercategory customercategory) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (customercategory.getCustomercategoryPK() == null) {
            customercategory.setCustomercategoryPK(new CustomercategoryPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = customercategory.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                customercategory.setCompany(company);
            }
            Companyemployee companyemployee = customercategory.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                customercategory.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = customercategory.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                customercategory.setCompanyemployee1(companyemployee1);
            }
            em.persist(customercategory);
            if (company != null) {
                company.getCustomercategoryCollection().add(customercategory);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCustomercategoryCollection().add(customercategory);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCustomercategoryCollection().add(customercategory);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCustomercategory(customercategory.getCustomercategoryPK()) != null) {
                throw new PreexistingEntityException("Customercategory " + customercategory + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customercategory customercategory) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customercategory persistentCustomercategory = em.find(Customercategory.class, customercategory.getCustomercategoryPK());
            Company companyOld = persistentCustomercategory.getCompany();
            Company companyNew = customercategory.getCompany();
            Companyemployee companyemployeeOld = persistentCustomercategory.getCompanyemployee();
            Companyemployee companyemployeeNew = customercategory.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCustomercategory.getCompanyemployee1();
            Companyemployee companyemployee1New = customercategory.getCompanyemployee1();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                customercategory.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                customercategory.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                customercategory.setCompanyemployee1(companyemployee1New);
            }
            customercategory = em.merge(customercategory);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCustomercategoryCollection().remove(customercategory);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCustomercategoryCollection().add(customercategory);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCustomercategoryCollection().remove(customercategory);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCustomercategoryCollection().add(customercategory);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCustomercategoryCollection().remove(customercategory);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCustomercategoryCollection().add(customercategory);
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
                CustomercategoryPK id = customercategory.getCustomercategoryPK();
                if (findCustomercategory(id) == null) {
                    throw new NonexistentEntityException("The customercategory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CustomercategoryPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customercategory customercategory;
            try {
                customercategory = em.getReference(Customercategory.class, id);
                customercategory.getCustomercategoryPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customercategory with id " + id + " no longer exists.", enfe);
            }
            Company company = customercategory.getCompany();
            if (company != null) {
                company.getCustomercategoryCollection().remove(customercategory);
                company = em.merge(company);
            }
            Companyemployee companyemployee = customercategory.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCustomercategoryCollection().remove(customercategory);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = customercategory.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCustomercategoryCollection().remove(customercategory);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(customercategory);
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

    public List<Customercategory> findCustomercategoryEntities() {
        return findCustomercategoryEntities(true, -1, -1);
    }

    public List<Customercategory> findCustomercategoryEntities(int maxResults, int firstResult) {
        return findCustomercategoryEntities(false, maxResults, firstResult);
    }

    private List<Customercategory> findCustomercategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customercategory.class));
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

    public Customercategory findCustomercategory(CustomercategoryPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customercategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomercategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customercategory> rt = cq.from(Customercategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
