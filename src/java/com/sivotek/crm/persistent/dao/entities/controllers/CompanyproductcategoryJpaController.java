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
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.Companyproductcategory;
import com.sivotek.crm.persistent.dao.entities.CompanyproductcategoryPK;
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
public class CompanyproductcategoryJpaController implements Serializable {

    public CompanyproductcategoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanyproductcategoryJpaController(){
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

    public void create(Companyproductcategory companyproductcategory) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyproductcategory.getCompanyproductcategoryPK() == null) {
            companyproductcategory.setCompanyproductcategoryPK(new CompanyproductcategoryPK());
        }
        if (companyproductcategory.getCompanyproductCollection() == null) {
            companyproductcategory.setCompanyproductCollection(new ArrayList<Companyproduct>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companyproductcategory.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companyproductcategory.setCompany(company);
            }
            Companyemployee companyemployee = companyproductcategory.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companyproductcategory.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companyproductcategory.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companyproductcategory.setCompanyemployee1(companyemployee1);
            }
            Collection<Companyproduct> attachedCompanyproductCollection = new ArrayList<Companyproduct>();
            for (Companyproduct companyproductCollectionCompanyproductToAttach : companyproductcategory.getCompanyproductCollection()) {
                companyproductCollectionCompanyproductToAttach = em.getReference(companyproductCollectionCompanyproductToAttach.getClass(), companyproductCollectionCompanyproductToAttach.getCompanyproductPK());
                attachedCompanyproductCollection.add(companyproductCollectionCompanyproductToAttach);
            }
            companyproductcategory.setCompanyproductCollection(attachedCompanyproductCollection);
            em.persist(companyproductcategory);
            if (company != null) {
                company.getCompanyproductcategoryCollection().add(companyproductcategory);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanyproductcategoryCollection().add(companyproductcategory);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanyproductcategoryCollection().add(companyproductcategory);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Companyproduct companyproductCollectionCompanyproduct : companyproductcategory.getCompanyproductCollection()) {
                Companyproductcategory oldCompanyproductcategoryOfCompanyproductCollectionCompanyproduct = companyproductCollectionCompanyproduct.getCompanyproductcategory();
                companyproductCollectionCompanyproduct.setCompanyproductcategory(companyproductcategory);
                companyproductCollectionCompanyproduct = em.merge(companyproductCollectionCompanyproduct);
                if (oldCompanyproductcategoryOfCompanyproductCollectionCompanyproduct != null) {
                    oldCompanyproductcategoryOfCompanyproductCollectionCompanyproduct.getCompanyproductCollection().remove(companyproductCollectionCompanyproduct);
                    oldCompanyproductcategoryOfCompanyproductCollectionCompanyproduct = em.merge(oldCompanyproductcategoryOfCompanyproductCollectionCompanyproduct);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyproductcategory(companyproductcategory.getCompanyproductcategoryPK()) != null) {
                throw new PreexistingEntityException("Companyproductcategory " + companyproductcategory + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companyproductcategory companyproductcategory) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyproductcategory persistentCompanyproductcategory = em.find(Companyproductcategory.class, companyproductcategory.getCompanyproductcategoryPK());
            Company companyOld = persistentCompanyproductcategory.getCompany();
            Company companyNew = companyproductcategory.getCompany();
            Companyemployee companyemployeeOld = persistentCompanyproductcategory.getCompanyemployee();
            Companyemployee companyemployeeNew = companyproductcategory.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanyproductcategory.getCompanyemployee1();
            Companyemployee companyemployee1New = companyproductcategory.getCompanyemployee1();
            Collection<Companyproduct> companyproductCollectionOld = persistentCompanyproductcategory.getCompanyproductCollection();
            Collection<Companyproduct> companyproductCollectionNew = companyproductcategory.getCompanyproductCollection();
            List<String> illegalOrphanMessages = null;
            for (Companyproduct companyproductCollectionOldCompanyproduct : companyproductCollectionOld) {
                if (!companyproductCollectionNew.contains(companyproductCollectionOldCompanyproduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproduct " + companyproductCollectionOldCompanyproduct + " since its companyproductcategory field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companyproductcategory.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companyproductcategory.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companyproductcategory.setCompanyemployee1(companyemployee1New);
            }
            Collection<Companyproduct> attachedCompanyproductCollectionNew = new ArrayList<Companyproduct>();
            for (Companyproduct companyproductCollectionNewCompanyproductToAttach : companyproductCollectionNew) {
                companyproductCollectionNewCompanyproductToAttach = em.getReference(companyproductCollectionNewCompanyproductToAttach.getClass(), companyproductCollectionNewCompanyproductToAttach.getCompanyproductPK());
                attachedCompanyproductCollectionNew.add(companyproductCollectionNewCompanyproductToAttach);
            }
            companyproductCollectionNew = attachedCompanyproductCollectionNew;
            companyproductcategory.setCompanyproductCollection(companyproductCollectionNew);
            companyproductcategory = em.merge(companyproductcategory);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanyproductcategoryCollection().remove(companyproductcategory);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanyproductcategoryCollection().add(companyproductcategory);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanyproductcategoryCollection().remove(companyproductcategory);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanyproductcategoryCollection().add(companyproductcategory);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanyproductcategoryCollection().remove(companyproductcategory);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanyproductcategoryCollection().add(companyproductcategory);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Companyproduct companyproductCollectionNewCompanyproduct : companyproductCollectionNew) {
                if (!companyproductCollectionOld.contains(companyproductCollectionNewCompanyproduct)) {
                    Companyproductcategory oldCompanyproductcategoryOfCompanyproductCollectionNewCompanyproduct = companyproductCollectionNewCompanyproduct.getCompanyproductcategory();
                    companyproductCollectionNewCompanyproduct.setCompanyproductcategory(companyproductcategory);
                    companyproductCollectionNewCompanyproduct = em.merge(companyproductCollectionNewCompanyproduct);
                    if (oldCompanyproductcategoryOfCompanyproductCollectionNewCompanyproduct != null && !oldCompanyproductcategoryOfCompanyproductCollectionNewCompanyproduct.equals(companyproductcategory)) {
                        oldCompanyproductcategoryOfCompanyproductCollectionNewCompanyproduct.getCompanyproductCollection().remove(companyproductCollectionNewCompanyproduct);
                        oldCompanyproductcategoryOfCompanyproductCollectionNewCompanyproduct = em.merge(oldCompanyproductcategoryOfCompanyproductCollectionNewCompanyproduct);
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
                CompanyproductcategoryPK id = companyproductcategory.getCompanyproductcategoryPK();
                if (findCompanyproductcategory(id) == null) {
                    throw new NonexistentEntityException("The companyproductcategory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyproductcategoryPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyproductcategory companyproductcategory;
            try {
                companyproductcategory = em.getReference(Companyproductcategory.class, id);
                companyproductcategory.getCompanyproductcategoryPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyproductcategory with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Companyproduct> companyproductCollectionOrphanCheck = companyproductcategory.getCompanyproductCollection();
            for (Companyproduct companyproductCollectionOrphanCheckCompanyproduct : companyproductCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyproductcategory (" + companyproductcategory + ") cannot be destroyed since the Companyproduct " + companyproductCollectionOrphanCheckCompanyproduct + " in its companyproductCollection field has a non-nullable companyproductcategory field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companyproductcategory.getCompany();
            if (company != null) {
                company.getCompanyproductcategoryCollection().remove(companyproductcategory);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companyproductcategory.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanyproductcategoryCollection().remove(companyproductcategory);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companyproductcategory.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanyproductcategoryCollection().remove(companyproductcategory);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companyproductcategory);
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

    public List<Companyproductcategory> findCompanyproductcategoryEntities() {
        return findCompanyproductcategoryEntities(true, -1, -1);
    }

    public List<Companyproductcategory> findCompanyproductcategoryEntities(int maxResults, int firstResult) {
        return findCompanyproductcategoryEntities(false, maxResults, firstResult);
    }

    private List<Companyproductcategory> findCompanyproductcategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companyproductcategory.class));
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

    public Companyproductcategory findCompanyproductcategory(CompanyproductcategoryPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companyproductcategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyproductcategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companyproductcategory> rt = cq.from(Companyproductcategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
