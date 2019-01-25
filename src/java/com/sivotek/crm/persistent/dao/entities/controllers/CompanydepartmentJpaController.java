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
import com.sivotek.crm.persistent.dao.entities.Companydepartment;
import com.sivotek.crm.persistent.dao.entities.CompanydepartmentPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
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
public class CompanydepartmentJpaController implements Serializable {

    public CompanydepartmentJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public CompanydepartmentJpaController(){
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

    public void create(Companydepartment companydepartment) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companydepartment.getCompanydepartmentPK() == null) {
            companydepartment.setCompanydepartmentPK(new CompanydepartmentPK());
        }
        if (companydepartment.getCompanyemployeeCollection() == null) {
            companydepartment.setCompanyemployeeCollection(new ArrayList<Companyemployee>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companydepartment.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companydepartment.setCompany(company);
            }
            Companyemployee companyemployee = companydepartment.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companydepartment.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companydepartment.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companydepartment.setCompanyemployee1(companyemployee1);
            }
            Collection<Companyemployee> attachedCompanyemployeeCollection = new ArrayList<Companyemployee>();
            for (Companyemployee companyemployeeCollectionCompanyemployeeToAttach : companydepartment.getCompanyemployeeCollection()) {
                companyemployeeCollectionCompanyemployeeToAttach = em.getReference(companyemployeeCollectionCompanyemployeeToAttach.getClass(), companyemployeeCollectionCompanyemployeeToAttach.getCompanyemployeePK());
                attachedCompanyemployeeCollection.add(companyemployeeCollectionCompanyemployeeToAttach);
            }
            companydepartment.setCompanyemployeeCollection(attachedCompanyemployeeCollection);
            em.persist(companydepartment);
            if (company != null) {
                company.getCompanydepartmentCollection().add(companydepartment);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanydepartmentCollection().add(companydepartment);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanydepartmentCollection().add(companydepartment);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Companyemployee companyemployeeCollectionCompanyemployee : companydepartment.getCompanyemployeeCollection()) {
                Companydepartment oldCompanydepartmentOfCompanyemployeeCollectionCompanyemployee = companyemployeeCollectionCompanyemployee.getCompanydepartment();
                companyemployeeCollectionCompanyemployee.setCompanydepartment(companydepartment);
                companyemployeeCollectionCompanyemployee = em.merge(companyemployeeCollectionCompanyemployee);
                if (oldCompanydepartmentOfCompanyemployeeCollectionCompanyemployee != null) {
                    oldCompanydepartmentOfCompanyemployeeCollectionCompanyemployee.getCompanyemployeeCollection().remove(companyemployeeCollectionCompanyemployee);
                    oldCompanydepartmentOfCompanyemployeeCollectionCompanyemployee = em.merge(oldCompanydepartmentOfCompanyemployeeCollectionCompanyemployee);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanydepartment(companydepartment.getCompanydepartmentPK()) != null) {
                throw new PreexistingEntityException("Companydepartment " + companydepartment + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companydepartment companydepartment) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companydepartment persistentCompanydepartment = em.find(Companydepartment.class, companydepartment.getCompanydepartmentPK());
            Company companyOld = persistentCompanydepartment.getCompany();
            Company companyNew = companydepartment.getCompany();
            Companyemployee companyemployeeOld = persistentCompanydepartment.getCompanyemployee();
            Companyemployee companyemployeeNew = companydepartment.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanydepartment.getCompanyemployee1();
            Companyemployee companyemployee1New = companydepartment.getCompanyemployee1();
            Collection<Companyemployee> companyemployeeCollectionOld = persistentCompanydepartment.getCompanyemployeeCollection();
            Collection<Companyemployee> companyemployeeCollectionNew = companydepartment.getCompanyemployeeCollection();
            List<String> illegalOrphanMessages = null;
            for (Companyemployee companyemployeeCollectionOldCompanyemployee : companyemployeeCollectionOld) {
                if (!companyemployeeCollectionNew.contains(companyemployeeCollectionOldCompanyemployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyemployee " + companyemployeeCollectionOldCompanyemployee + " since its companydepartment field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companydepartment.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companydepartment.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companydepartment.setCompanyemployee1(companyemployee1New);
            }
            Collection<Companyemployee> attachedCompanyemployeeCollectionNew = new ArrayList<Companyemployee>();
            for (Companyemployee companyemployeeCollectionNewCompanyemployeeToAttach : companyemployeeCollectionNew) {
                companyemployeeCollectionNewCompanyemployeeToAttach = em.getReference(companyemployeeCollectionNewCompanyemployeeToAttach.getClass(), companyemployeeCollectionNewCompanyemployeeToAttach.getCompanyemployeePK());
                attachedCompanyemployeeCollectionNew.add(companyemployeeCollectionNewCompanyemployeeToAttach);
            }
            companyemployeeCollectionNew = attachedCompanyemployeeCollectionNew;
            companydepartment.setCompanyemployeeCollection(companyemployeeCollectionNew);
            companydepartment = em.merge(companydepartment);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanydepartmentCollection().remove(companydepartment);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanydepartmentCollection().add(companydepartment);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanydepartmentCollection().remove(companydepartment);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanydepartmentCollection().add(companydepartment);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanydepartmentCollection().remove(companydepartment);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanydepartmentCollection().add(companydepartment);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Companyemployee companyemployeeCollectionNewCompanyemployee : companyemployeeCollectionNew) {
                if (!companyemployeeCollectionOld.contains(companyemployeeCollectionNewCompanyemployee)) {
                    Companydepartment oldCompanydepartmentOfCompanyemployeeCollectionNewCompanyemployee = companyemployeeCollectionNewCompanyemployee.getCompanydepartment();
                    companyemployeeCollectionNewCompanyemployee.setCompanydepartment(companydepartment);
                    companyemployeeCollectionNewCompanyemployee = em.merge(companyemployeeCollectionNewCompanyemployee);
                    if (oldCompanydepartmentOfCompanyemployeeCollectionNewCompanyemployee != null && !oldCompanydepartmentOfCompanyemployeeCollectionNewCompanyemployee.equals(companydepartment)) {
                        oldCompanydepartmentOfCompanyemployeeCollectionNewCompanyemployee.getCompanyemployeeCollection().remove(companyemployeeCollectionNewCompanyemployee);
                        oldCompanydepartmentOfCompanyemployeeCollectionNewCompanyemployee = em.merge(oldCompanydepartmentOfCompanyemployeeCollectionNewCompanyemployee);
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
                CompanydepartmentPK id = companydepartment.getCompanydepartmentPK();
                if (findCompanydepartment(id) == null) {
                    throw new NonexistentEntityException("The companydepartment with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanydepartmentPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companydepartment companydepartment;
            try {
                companydepartment = em.getReference(Companydepartment.class, id);
                companydepartment.getCompanydepartmentPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companydepartment with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Companyemployee> companyemployeeCollectionOrphanCheck = companydepartment.getCompanyemployeeCollection();
            for (Companyemployee companyemployeeCollectionOrphanCheckCompanyemployee : companyemployeeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companydepartment (" + companydepartment + ") cannot be destroyed since the Companyemployee " + companyemployeeCollectionOrphanCheckCompanyemployee + " in its companyemployeeCollection field has a non-nullable companydepartment field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companydepartment.getCompany();
            if (company != null) {
                company.getCompanydepartmentCollection().remove(companydepartment);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companydepartment.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanydepartmentCollection().remove(companydepartment);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companydepartment.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanydepartmentCollection().remove(companydepartment);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companydepartment);
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

    public List<Companydepartment> findCompanydepartmentEntities() {
        return findCompanydepartmentEntities(true, -1, -1);
    }

    public List<Companydepartment> findCompanydepartmentEntities(int maxResults, int firstResult) {
        return findCompanydepartmentEntities(false, maxResults, firstResult);
    }

    private List<Companydepartment> findCompanydepartmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companydepartment.class));
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

    public Companydepartment findCompanydepartment(CompanydepartmentPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companydepartment.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanydepartmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companydepartment> rt = cq.from(Companydepartment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
