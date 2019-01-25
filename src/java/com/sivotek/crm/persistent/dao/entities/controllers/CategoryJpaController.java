/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import com.sivotek.crm.persistent.dao.entities.Category;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
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
public class CategoryJpaController implements Serializable {

    public CategoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CategoryJpaController(){
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

    public void create(Category category) throws RollbackFailureException, Exception {
        if (category.getCompanyCollection() == null) {
            category.setCompanyCollection(new ArrayList<Company>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Company> attachedCompanyCollection = new ArrayList<Company>();
            for (Company companyCollectionCompanyToAttach : category.getCompanyCollection()) {
                companyCollectionCompanyToAttach = em.getReference(companyCollectionCompanyToAttach.getClass(), companyCollectionCompanyToAttach.getCompanyPK());
                attachedCompanyCollection.add(companyCollectionCompanyToAttach);
            }
            category.setCompanyCollection(attachedCompanyCollection);
            em.persist(category);
            for (Company companyCollectionCompany : category.getCompanyCollection()) {
                Category oldCategoryOfCompanyCollectionCompany = companyCollectionCompany.getCategory();
                companyCollectionCompany.setCategory(category);
                companyCollectionCompany = em.merge(companyCollectionCompany);
                if (oldCategoryOfCompanyCollectionCompany != null) {
                    oldCategoryOfCompanyCollectionCompany.getCompanyCollection().remove(companyCollectionCompany);
                    oldCategoryOfCompanyCollectionCompany = em.merge(oldCategoryOfCompanyCollectionCompany);
                }
            }
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

    public void edit(Category category) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Category persistentCategory = em.find(Category.class, category.getId());
            Collection<Company> companyCollectionOld = persistentCategory.getCompanyCollection();
            Collection<Company> companyCollectionNew = category.getCompanyCollection();
            Collection<Company> attachedCompanyCollectionNew = new ArrayList<Company>();
            for (Company companyCollectionNewCompanyToAttach : companyCollectionNew) {
                companyCollectionNewCompanyToAttach = em.getReference(companyCollectionNewCompanyToAttach.getClass(), companyCollectionNewCompanyToAttach.getCompanyPK());
                attachedCompanyCollectionNew.add(companyCollectionNewCompanyToAttach);
            }
            companyCollectionNew = attachedCompanyCollectionNew;
            category.setCompanyCollection(companyCollectionNew);
            category = em.merge(category);
            for (Company companyCollectionOldCompany : companyCollectionOld) {
                if (!companyCollectionNew.contains(companyCollectionOldCompany)) {
                    companyCollectionOldCompany.setCategory(null);
                    companyCollectionOldCompany = em.merge(companyCollectionOldCompany);
                }
            }
            for (Company companyCollectionNewCompany : companyCollectionNew) {
                if (!companyCollectionOld.contains(companyCollectionNewCompany)) {
                    Category oldCategoryOfCompanyCollectionNewCompany = companyCollectionNewCompany.getCategory();
                    companyCollectionNewCompany.setCategory(category);
                    companyCollectionNewCompany = em.merge(companyCollectionNewCompany);
                    if (oldCategoryOfCompanyCollectionNewCompany != null && !oldCategoryOfCompanyCollectionNewCompany.equals(category)) {
                        oldCategoryOfCompanyCollectionNewCompany.getCompanyCollection().remove(companyCollectionNewCompany);
                        oldCategoryOfCompanyCollectionNewCompany = em.merge(oldCategoryOfCompanyCollectionNewCompany);
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
                Integer id = category.getId();
                if (findCategory(id) == null) {
                    throw new NonexistentEntityException("The category with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Category category;
            try {
                category = em.getReference(Category.class, id);
                category.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The category with id " + id + " no longer exists.", enfe);
            }
            Collection<Company> companyCollection = category.getCompanyCollection();
            for (Company companyCollectionCompany : companyCollection) {
                companyCollectionCompany.setCategory(null);
                companyCollectionCompany = em.merge(companyCollectionCompany);
            }
            em.remove(category);
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

    public List<Category> findCategoryEntities() {
        return findCategoryEntities(true, -1, -1);
    }

    public List<Category> findCategoryEntities(int maxResults, int firstResult) {
        return findCategoryEntities(false, maxResults, firstResult);
    }

    private List<Category> findCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Category.class));
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

    public Category findCategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Category> rt = cq.from(Category.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
