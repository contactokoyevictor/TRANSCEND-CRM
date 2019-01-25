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
import com.sivotek.crm.persistent.dao.entities.Companypayments;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Employeedesignation;
import com.sivotek.crm.persistent.dao.entities.EmployeedesignationPK;
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
public class EmployeedesignationJpaController implements Serializable {

    public EmployeedesignationJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EmployeedesignationJpaController(){
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

    public void create(Employeedesignation employeedesignation) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (employeedesignation.getEmployeedesignationPK() == null) {
            employeedesignation.setEmployeedesignationPK(new EmployeedesignationPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = employeedesignation.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                employeedesignation.setCompany(company);
            }
            Companypayments companypayments = employeedesignation.getCompanypayments();
            if (companypayments != null) {
                companypayments = em.getReference(companypayments.getClass(), companypayments.getCompanypaymentsPK());
                employeedesignation.setCompanypayments(companypayments);
            }
            Companyemployee companyemployee = employeedesignation.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                employeedesignation.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = employeedesignation.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                employeedesignation.setCompanyemployee1(companyemployee1);
            }
            em.persist(employeedesignation);
            if (company != null) {
                company.getEmployeedesignationCollection().add(employeedesignation);
                company = em.merge(company);
            }
            if (companypayments != null) {
                companypayments.getEmployeedesignationCollection().add(employeedesignation);
                companypayments = em.merge(companypayments);
            }
            if (companyemployee != null) {
                companyemployee.getEmployeedesignationCollection().add(employeedesignation);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getEmployeedesignationCollection().add(employeedesignation);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEmployeedesignation(employeedesignation.getEmployeedesignationPK()) != null) {
                throw new PreexistingEntityException("Employeedesignation " + employeedesignation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Employeedesignation employeedesignation) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employeedesignation persistentEmployeedesignation = em.find(Employeedesignation.class, employeedesignation.getEmployeedesignationPK());
            Company companyOld = persistentEmployeedesignation.getCompany();
            Company companyNew = employeedesignation.getCompany();
            Companypayments companypaymentsOld = persistentEmployeedesignation.getCompanypayments();
            Companypayments companypaymentsNew = employeedesignation.getCompanypayments();
            Companyemployee companyemployeeOld = persistentEmployeedesignation.getCompanyemployee();
            Companyemployee companyemployeeNew = employeedesignation.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentEmployeedesignation.getCompanyemployee1();
            Companyemployee companyemployee1New = employeedesignation.getCompanyemployee1();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                employeedesignation.setCompany(companyNew);
            }
            if (companypaymentsNew != null) {
                companypaymentsNew = em.getReference(companypaymentsNew.getClass(), companypaymentsNew.getCompanypaymentsPK());
                employeedesignation.setCompanypayments(companypaymentsNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                employeedesignation.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                employeedesignation.setCompanyemployee1(companyemployee1New);
            }
            employeedesignation = em.merge(employeedesignation);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getEmployeedesignationCollection().remove(employeedesignation);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getEmployeedesignationCollection().add(employeedesignation);
                companyNew = em.merge(companyNew);
            }
            if (companypaymentsOld != null && !companypaymentsOld.equals(companypaymentsNew)) {
                companypaymentsOld.getEmployeedesignationCollection().remove(employeedesignation);
                companypaymentsOld = em.merge(companypaymentsOld);
            }
            if (companypaymentsNew != null && !companypaymentsNew.equals(companypaymentsOld)) {
                companypaymentsNew.getEmployeedesignationCollection().add(employeedesignation);
                companypaymentsNew = em.merge(companypaymentsNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getEmployeedesignationCollection().remove(employeedesignation);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getEmployeedesignationCollection().add(employeedesignation);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getEmployeedesignationCollection().remove(employeedesignation);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getEmployeedesignationCollection().add(employeedesignation);
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
                EmployeedesignationPK id = employeedesignation.getEmployeedesignationPK();
                if (findEmployeedesignation(id) == null) {
                    throw new NonexistentEntityException("The employeedesignation with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EmployeedesignationPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employeedesignation employeedesignation;
            try {
                employeedesignation = em.getReference(Employeedesignation.class, id);
                employeedesignation.getEmployeedesignationPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employeedesignation with id " + id + " no longer exists.", enfe);
            }
            Company company = employeedesignation.getCompany();
            if (company != null) {
                company.getEmployeedesignationCollection().remove(employeedesignation);
                company = em.merge(company);
            }
            Companypayments companypayments = employeedesignation.getCompanypayments();
            if (companypayments != null) {
                companypayments.getEmployeedesignationCollection().remove(employeedesignation);
                companypayments = em.merge(companypayments);
            }
            Companyemployee companyemployee = employeedesignation.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getEmployeedesignationCollection().remove(employeedesignation);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = employeedesignation.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getEmployeedesignationCollection().remove(employeedesignation);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(employeedesignation);
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

    public List<Employeedesignation> findEmployeedesignationEntities() {
        return findEmployeedesignationEntities(true, -1, -1);
    }

    public List<Employeedesignation> findEmployeedesignationEntities(int maxResults, int firstResult) {
        return findEmployeedesignationEntities(false, maxResults, firstResult);
    }

    private List<Employeedesignation> findEmployeedesignationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employeedesignation.class));
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

    public Employeedesignation findEmployeedesignation(EmployeedesignationPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employeedesignation.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeedesignationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employeedesignation> rt = cq.from(Employeedesignation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
