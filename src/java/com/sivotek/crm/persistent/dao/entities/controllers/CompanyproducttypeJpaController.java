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
import com.sivotek.crm.persistent.dao.entities.Companyproducttype;
import com.sivotek.crm.persistent.dao.entities.CompanyproducttypePK;
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
public class CompanyproducttypeJpaController implements Serializable {

    public CompanyproducttypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanyproducttypeJpaController(){
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

    public void create(Companyproducttype companyproducttype) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyproducttype.getCompanyproducttypePK() == null) {
            companyproducttype.setCompanyproducttypePK(new CompanyproducttypePK());
        }
        if (companyproducttype.getCompanyproductCollection() == null) {
            companyproducttype.setCompanyproductCollection(new ArrayList<Companyproduct>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companyproducttype.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companyproducttype.setCompany(company);
            }
            Companyemployee companyemployee = companyproducttype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companyproducttype.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companyproducttype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companyproducttype.setCompanyemployee1(companyemployee1);
            }
            Collection<Companyproduct> attachedCompanyproductCollection = new ArrayList<Companyproduct>();
            for (Companyproduct companyproductCollectionCompanyproductToAttach : companyproducttype.getCompanyproductCollection()) {
                companyproductCollectionCompanyproductToAttach = em.getReference(companyproductCollectionCompanyproductToAttach.getClass(), companyproductCollectionCompanyproductToAttach.getCompanyproductPK());
                attachedCompanyproductCollection.add(companyproductCollectionCompanyproductToAttach);
            }
            companyproducttype.setCompanyproductCollection(attachedCompanyproductCollection);
            em.persist(companyproducttype);
            if (company != null) {
                company.getCompanyproducttypeCollection().add(companyproducttype);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanyproducttypeCollection().add(companyproducttype);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanyproducttypeCollection().add(companyproducttype);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Companyproduct companyproductCollectionCompanyproduct : companyproducttype.getCompanyproductCollection()) {
                Companyproducttype oldCompanyproducttypeOfCompanyproductCollectionCompanyproduct = companyproductCollectionCompanyproduct.getCompanyproducttype();
                companyproductCollectionCompanyproduct.setCompanyproducttype(companyproducttype);
                companyproductCollectionCompanyproduct = em.merge(companyproductCollectionCompanyproduct);
                if (oldCompanyproducttypeOfCompanyproductCollectionCompanyproduct != null) {
                    oldCompanyproducttypeOfCompanyproductCollectionCompanyproduct.getCompanyproductCollection().remove(companyproductCollectionCompanyproduct);
                    oldCompanyproducttypeOfCompanyproductCollectionCompanyproduct = em.merge(oldCompanyproducttypeOfCompanyproductCollectionCompanyproduct);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyproducttype(companyproducttype.getCompanyproducttypePK()) != null) {
                throw new PreexistingEntityException("Companyproducttype " + companyproducttype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companyproducttype companyproducttype) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyproducttype persistentCompanyproducttype = em.find(Companyproducttype.class, companyproducttype.getCompanyproducttypePK());
            Company companyOld = persistentCompanyproducttype.getCompany();
            Company companyNew = companyproducttype.getCompany();
            Companyemployee companyemployeeOld = persistentCompanyproducttype.getCompanyemployee();
            Companyemployee companyemployeeNew = companyproducttype.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanyproducttype.getCompanyemployee1();
            Companyemployee companyemployee1New = companyproducttype.getCompanyemployee1();
            Collection<Companyproduct> companyproductCollectionOld = persistentCompanyproducttype.getCompanyproductCollection();
            Collection<Companyproduct> companyproductCollectionNew = companyproducttype.getCompanyproductCollection();
            List<String> illegalOrphanMessages = null;
            for (Companyproduct companyproductCollectionOldCompanyproduct : companyproductCollectionOld) {
                if (!companyproductCollectionNew.contains(companyproductCollectionOldCompanyproduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproduct " + companyproductCollectionOldCompanyproduct + " since its companyproducttype field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companyproducttype.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companyproducttype.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companyproducttype.setCompanyemployee1(companyemployee1New);
            }
            Collection<Companyproduct> attachedCompanyproductCollectionNew = new ArrayList<Companyproduct>();
            for (Companyproduct companyproductCollectionNewCompanyproductToAttach : companyproductCollectionNew) {
                companyproductCollectionNewCompanyproductToAttach = em.getReference(companyproductCollectionNewCompanyproductToAttach.getClass(), companyproductCollectionNewCompanyproductToAttach.getCompanyproductPK());
                attachedCompanyproductCollectionNew.add(companyproductCollectionNewCompanyproductToAttach);
            }
            companyproductCollectionNew = attachedCompanyproductCollectionNew;
            companyproducttype.setCompanyproductCollection(companyproductCollectionNew);
            companyproducttype = em.merge(companyproducttype);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanyproducttypeCollection().remove(companyproducttype);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanyproducttypeCollection().add(companyproducttype);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanyproducttypeCollection().remove(companyproducttype);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanyproducttypeCollection().add(companyproducttype);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanyproducttypeCollection().remove(companyproducttype);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanyproducttypeCollection().add(companyproducttype);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Companyproduct companyproductCollectionNewCompanyproduct : companyproductCollectionNew) {
                if (!companyproductCollectionOld.contains(companyproductCollectionNewCompanyproduct)) {
                    Companyproducttype oldCompanyproducttypeOfCompanyproductCollectionNewCompanyproduct = companyproductCollectionNewCompanyproduct.getCompanyproducttype();
                    companyproductCollectionNewCompanyproduct.setCompanyproducttype(companyproducttype);
                    companyproductCollectionNewCompanyproduct = em.merge(companyproductCollectionNewCompanyproduct);
                    if (oldCompanyproducttypeOfCompanyproductCollectionNewCompanyproduct != null && !oldCompanyproducttypeOfCompanyproductCollectionNewCompanyproduct.equals(companyproducttype)) {
                        oldCompanyproducttypeOfCompanyproductCollectionNewCompanyproduct.getCompanyproductCollection().remove(companyproductCollectionNewCompanyproduct);
                        oldCompanyproducttypeOfCompanyproductCollectionNewCompanyproduct = em.merge(oldCompanyproducttypeOfCompanyproductCollectionNewCompanyproduct);
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
                CompanyproducttypePK id = companyproducttype.getCompanyproducttypePK();
                if (findCompanyproducttype(id) == null) {
                    throw new NonexistentEntityException("The companyproducttype with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyproducttypePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyproducttype companyproducttype;
            try {
                companyproducttype = em.getReference(Companyproducttype.class, id);
                companyproducttype.getCompanyproducttypePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyproducttype with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Companyproduct> companyproductCollectionOrphanCheck = companyproducttype.getCompanyproductCollection();
            for (Companyproduct companyproductCollectionOrphanCheckCompanyproduct : companyproductCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyproducttype (" + companyproducttype + ") cannot be destroyed since the Companyproduct " + companyproductCollectionOrphanCheckCompanyproduct + " in its companyproductCollection field has a non-nullable companyproducttype field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companyproducttype.getCompany();
            if (company != null) {
                company.getCompanyproducttypeCollection().remove(companyproducttype);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companyproducttype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanyproducttypeCollection().remove(companyproducttype);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companyproducttype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanyproducttypeCollection().remove(companyproducttype);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companyproducttype);
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

    public List<Companyproducttype> findCompanyproducttypeEntities() {
        return findCompanyproducttypeEntities(true, -1, -1);
    }

    public List<Companyproducttype> findCompanyproducttypeEntities(int maxResults, int firstResult) {
        return findCompanyproducttypeEntities(false, maxResults, firstResult);
    }

    private List<Companyproducttype> findCompanyproducttypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companyproducttype.class));
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

    public Companyproducttype findCompanyproducttype(CompanyproducttypePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companyproducttype.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyproducttypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companyproducttype> rt = cq.from(Companyproducttype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
