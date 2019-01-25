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
import com.sivotek.crm.persistent.dao.entities.Companypaymentscheme;
import com.sivotek.crm.persistent.dao.entities.Compnaypaymentcurrency;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Companypayments;
import com.sivotek.crm.persistent.dao.entities.CompanypaymentsPK;
import com.sivotek.crm.persistent.dao.entities.Employeedesignation;
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
public class CompanypaymentsJpaController implements Serializable {

    public CompanypaymentsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanypaymentsJpaController(){
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

    public void create(Companypayments companypayments) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companypayments.getCompanypaymentsPK() == null) {
            companypayments.setCompanypaymentsPK(new CompanypaymentsPK());
        }
        if (companypayments.getEmployeedesignationCollection() == null) {
            companypayments.setEmployeedesignationCollection(new ArrayList<Employeedesignation>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companypayments.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companypayments.setCompany(company);
            }
            Companypaymentscheme companypaymentscheme = companypayments.getCompanypaymentscheme();
            if (companypaymentscheme != null) {
                companypaymentscheme = em.getReference(companypaymentscheme.getClass(), companypaymentscheme.getCompanypaymentschemePK());
                companypayments.setCompanypaymentscheme(companypaymentscheme);
            }
            Compnaypaymentcurrency compnaypaymentcurrency = companypayments.getCompnaypaymentcurrency();
            if (compnaypaymentcurrency != null) {
                compnaypaymentcurrency = em.getReference(compnaypaymentcurrency.getClass(), compnaypaymentcurrency.getCompnaypaymentcurrencyPK());
                companypayments.setCompnaypaymentcurrency(compnaypaymentcurrency);
            }
            Companyemployee companyemployee = companypayments.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companypayments.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companypayments.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companypayments.setCompanyemployee1(companyemployee1);
            }
            Collection<Employeedesignation> attachedEmployeedesignationCollection = new ArrayList<Employeedesignation>();
            for (Employeedesignation employeedesignationCollectionEmployeedesignationToAttach : companypayments.getEmployeedesignationCollection()) {
                employeedesignationCollectionEmployeedesignationToAttach = em.getReference(employeedesignationCollectionEmployeedesignationToAttach.getClass(), employeedesignationCollectionEmployeedesignationToAttach.getEmployeedesignationPK());
                attachedEmployeedesignationCollection.add(employeedesignationCollectionEmployeedesignationToAttach);
            }
            companypayments.setEmployeedesignationCollection(attachedEmployeedesignationCollection);
            em.persist(companypayments);
            if (company != null) {
                company.getCompanypaymentsCollection().add(companypayments);
                company = em.merge(company);
            }
            if (companypaymentscheme != null) {
                companypaymentscheme.getCompanypaymentsCollection().add(companypayments);
                companypaymentscheme = em.merge(companypaymentscheme);
            }
            if (compnaypaymentcurrency != null) {
                compnaypaymentcurrency.getCompanypaymentsCollection().add(companypayments);
                compnaypaymentcurrency = em.merge(compnaypaymentcurrency);
            }
            if (companyemployee != null) {
                companyemployee.getCompanypaymentsCollection().add(companypayments);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanypaymentsCollection().add(companypayments);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Employeedesignation employeedesignationCollectionEmployeedesignation : companypayments.getEmployeedesignationCollection()) {
                Companypayments oldCompanypaymentsOfEmployeedesignationCollectionEmployeedesignation = employeedesignationCollectionEmployeedesignation.getCompanypayments();
                employeedesignationCollectionEmployeedesignation.setCompanypayments(companypayments);
                employeedesignationCollectionEmployeedesignation = em.merge(employeedesignationCollectionEmployeedesignation);
                if (oldCompanypaymentsOfEmployeedesignationCollectionEmployeedesignation != null) {
                    oldCompanypaymentsOfEmployeedesignationCollectionEmployeedesignation.getEmployeedesignationCollection().remove(employeedesignationCollectionEmployeedesignation);
                    oldCompanypaymentsOfEmployeedesignationCollectionEmployeedesignation = em.merge(oldCompanypaymentsOfEmployeedesignationCollectionEmployeedesignation);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanypayments(companypayments.getCompanypaymentsPK()) != null) {
                throw new PreexistingEntityException("Companypayments " + companypayments + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companypayments companypayments) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companypayments persistentCompanypayments = em.find(Companypayments.class, companypayments.getCompanypaymentsPK());
            Company companyOld = persistentCompanypayments.getCompany();
            Company companyNew = companypayments.getCompany();
            Companypaymentscheme companypaymentschemeOld = persistentCompanypayments.getCompanypaymentscheme();
            Companypaymentscheme companypaymentschemeNew = companypayments.getCompanypaymentscheme();
            Compnaypaymentcurrency compnaypaymentcurrencyOld = persistentCompanypayments.getCompnaypaymentcurrency();
            Compnaypaymentcurrency compnaypaymentcurrencyNew = companypayments.getCompnaypaymentcurrency();
            Companyemployee companyemployeeOld = persistentCompanypayments.getCompanyemployee();
            Companyemployee companyemployeeNew = companypayments.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanypayments.getCompanyemployee1();
            Companyemployee companyemployee1New = companypayments.getCompanyemployee1();
            Collection<Employeedesignation> employeedesignationCollectionOld = persistentCompanypayments.getEmployeedesignationCollection();
            Collection<Employeedesignation> employeedesignationCollectionNew = companypayments.getEmployeedesignationCollection();
            List<String> illegalOrphanMessages = null;
            for (Employeedesignation employeedesignationCollectionOldEmployeedesignation : employeedesignationCollectionOld) {
                if (!employeedesignationCollectionNew.contains(employeedesignationCollectionOldEmployeedesignation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employeedesignation " + employeedesignationCollectionOldEmployeedesignation + " since its companypayments field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companypayments.setCompany(companyNew);
            }
            if (companypaymentschemeNew != null) {
                companypaymentschemeNew = em.getReference(companypaymentschemeNew.getClass(), companypaymentschemeNew.getCompanypaymentschemePK());
                companypayments.setCompanypaymentscheme(companypaymentschemeNew);
            }
            if (compnaypaymentcurrencyNew != null) {
                compnaypaymentcurrencyNew = em.getReference(compnaypaymentcurrencyNew.getClass(), compnaypaymentcurrencyNew.getCompnaypaymentcurrencyPK());
                companypayments.setCompnaypaymentcurrency(compnaypaymentcurrencyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companypayments.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companypayments.setCompanyemployee1(companyemployee1New);
            }
            Collection<Employeedesignation> attachedEmployeedesignationCollectionNew = new ArrayList<Employeedesignation>();
            for (Employeedesignation employeedesignationCollectionNewEmployeedesignationToAttach : employeedesignationCollectionNew) {
                employeedesignationCollectionNewEmployeedesignationToAttach = em.getReference(employeedesignationCollectionNewEmployeedesignationToAttach.getClass(), employeedesignationCollectionNewEmployeedesignationToAttach.getEmployeedesignationPK());
                attachedEmployeedesignationCollectionNew.add(employeedesignationCollectionNewEmployeedesignationToAttach);
            }
            employeedesignationCollectionNew = attachedEmployeedesignationCollectionNew;
            companypayments.setEmployeedesignationCollection(employeedesignationCollectionNew);
            companypayments = em.merge(companypayments);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanypaymentsCollection().remove(companypayments);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanypaymentsCollection().add(companypayments);
                companyNew = em.merge(companyNew);
            }
            if (companypaymentschemeOld != null && !companypaymentschemeOld.equals(companypaymentschemeNew)) {
                companypaymentschemeOld.getCompanypaymentsCollection().remove(companypayments);
                companypaymentschemeOld = em.merge(companypaymentschemeOld);
            }
            if (companypaymentschemeNew != null && !companypaymentschemeNew.equals(companypaymentschemeOld)) {
                companypaymentschemeNew.getCompanypaymentsCollection().add(companypayments);
                companypaymentschemeNew = em.merge(companypaymentschemeNew);
            }
            if (compnaypaymentcurrencyOld != null && !compnaypaymentcurrencyOld.equals(compnaypaymentcurrencyNew)) {
                compnaypaymentcurrencyOld.getCompanypaymentsCollection().remove(companypayments);
                compnaypaymentcurrencyOld = em.merge(compnaypaymentcurrencyOld);
            }
            if (compnaypaymentcurrencyNew != null && !compnaypaymentcurrencyNew.equals(compnaypaymentcurrencyOld)) {
                compnaypaymentcurrencyNew.getCompanypaymentsCollection().add(companypayments);
                compnaypaymentcurrencyNew = em.merge(compnaypaymentcurrencyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanypaymentsCollection().remove(companypayments);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanypaymentsCollection().add(companypayments);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanypaymentsCollection().remove(companypayments);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanypaymentsCollection().add(companypayments);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Employeedesignation employeedesignationCollectionNewEmployeedesignation : employeedesignationCollectionNew) {
                if (!employeedesignationCollectionOld.contains(employeedesignationCollectionNewEmployeedesignation)) {
                    Companypayments oldCompanypaymentsOfEmployeedesignationCollectionNewEmployeedesignation = employeedesignationCollectionNewEmployeedesignation.getCompanypayments();
                    employeedesignationCollectionNewEmployeedesignation.setCompanypayments(companypayments);
                    employeedesignationCollectionNewEmployeedesignation = em.merge(employeedesignationCollectionNewEmployeedesignation);
                    if (oldCompanypaymentsOfEmployeedesignationCollectionNewEmployeedesignation != null && !oldCompanypaymentsOfEmployeedesignationCollectionNewEmployeedesignation.equals(companypayments)) {
                        oldCompanypaymentsOfEmployeedesignationCollectionNewEmployeedesignation.getEmployeedesignationCollection().remove(employeedesignationCollectionNewEmployeedesignation);
                        oldCompanypaymentsOfEmployeedesignationCollectionNewEmployeedesignation = em.merge(oldCompanypaymentsOfEmployeedesignationCollectionNewEmployeedesignation);
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
                CompanypaymentsPK id = companypayments.getCompanypaymentsPK();
                if (findCompanypayments(id) == null) {
                    throw new NonexistentEntityException("The companypayments with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanypaymentsPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companypayments companypayments;
            try {
                companypayments = em.getReference(Companypayments.class, id);
                companypayments.getCompanypaymentsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companypayments with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Employeedesignation> employeedesignationCollectionOrphanCheck = companypayments.getEmployeedesignationCollection();
            for (Employeedesignation employeedesignationCollectionOrphanCheckEmployeedesignation : employeedesignationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companypayments (" + companypayments + ") cannot be destroyed since the Employeedesignation " + employeedesignationCollectionOrphanCheckEmployeedesignation + " in its employeedesignationCollection field has a non-nullable companypayments field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companypayments.getCompany();
            if (company != null) {
                company.getCompanypaymentsCollection().remove(companypayments);
                company = em.merge(company);
            }
            Companypaymentscheme companypaymentscheme = companypayments.getCompanypaymentscheme();
            if (companypaymentscheme != null) {
                companypaymentscheme.getCompanypaymentsCollection().remove(companypayments);
                companypaymentscheme = em.merge(companypaymentscheme);
            }
            Compnaypaymentcurrency compnaypaymentcurrency = companypayments.getCompnaypaymentcurrency();
            if (compnaypaymentcurrency != null) {
                compnaypaymentcurrency.getCompanypaymentsCollection().remove(companypayments);
                compnaypaymentcurrency = em.merge(compnaypaymentcurrency);
            }
            Companyemployee companyemployee = companypayments.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanypaymentsCollection().remove(companypayments);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companypayments.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanypaymentsCollection().remove(companypayments);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companypayments);
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

    public List<Companypayments> findCompanypaymentsEntities() {
        return findCompanypaymentsEntities(true, -1, -1);
    }

    public List<Companypayments> findCompanypaymentsEntities(int maxResults, int firstResult) {
        return findCompanypaymentsEntities(false, maxResults, firstResult);
    }

    private List<Companypayments> findCompanypaymentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companypayments.class));
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

    public Companypayments findCompanypayments(CompanypaymentsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companypayments.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanypaymentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companypayments> rt = cq.from(Companypayments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
