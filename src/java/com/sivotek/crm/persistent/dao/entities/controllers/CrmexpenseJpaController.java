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
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.Crmexpensetype;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.Crmexpense;
import com.sivotek.crm.persistent.dao.entities.CrmexpensePK;
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
public class CrmexpenseJpaController implements Serializable {

    public CrmexpenseJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmexpenseJpaController(){
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

    public void create(Crmexpense crmexpense) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmexpense.getCrmexpensePK() == null) {
            crmexpense.setCrmexpensePK(new CrmexpensePK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companycustomer companycustomer = crmexpense.getCompanycustomer();
            if (companycustomer != null) {
                companycustomer = em.getReference(companycustomer.getClass(), companycustomer.getCompanycustomerPK());
                crmexpense.setCompanycustomer(companycustomer);
            }
            Crmexpensetype crmexpensetype = crmexpense.getCrmexpensetype();
            if (crmexpensetype != null) {
                crmexpensetype = em.getReference(crmexpensetype.getClass(), crmexpensetype.getCrmexpensetypePK());
                crmexpense.setCrmexpensetype(crmexpensetype);
            }
            Companyemployee companyemployee = crmexpense.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmexpense.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmexpense.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmexpense.setCompanyemployee1(companyemployee1);
            }
            Company company = crmexpense.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmexpense.setCompany(company);
            }
            em.persist(crmexpense);
            if (companycustomer != null) {
                companycustomer.getCrmexpenseCollection().add(crmexpense);
                companycustomer = em.merge(companycustomer);
            }
            if (crmexpensetype != null) {
                crmexpensetype.getCrmexpenseCollection().add(crmexpense);
                crmexpensetype = em.merge(crmexpensetype);
            }
            if (companyemployee != null) {
                companyemployee.getCrmexpenseCollection().add(crmexpense);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmexpenseCollection().add(crmexpense);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (company != null) {
                company.getCrmexpenseCollection().add(crmexpense);
                company = em.merge(company);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmexpense(crmexpense.getCrmexpensePK()) != null) {
                throw new PreexistingEntityException("Crmexpense " + crmexpense + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmexpense crmexpense) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmexpense persistentCrmexpense = em.find(Crmexpense.class, crmexpense.getCrmexpensePK());
            Companycustomer companycustomerOld = persistentCrmexpense.getCompanycustomer();
            Companycustomer companycustomerNew = crmexpense.getCompanycustomer();
            Crmexpensetype crmexpensetypeOld = persistentCrmexpense.getCrmexpensetype();
            Crmexpensetype crmexpensetypeNew = crmexpense.getCrmexpensetype();
            Companyemployee companyemployeeOld = persistentCrmexpense.getCompanyemployee();
            Companyemployee companyemployeeNew = crmexpense.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmexpense.getCompanyemployee1();
            Companyemployee companyemployee1New = crmexpense.getCompanyemployee1();
            Company companyOld = persistentCrmexpense.getCompany();
            Company companyNew = crmexpense.getCompany();
            if (companycustomerNew != null) {
                companycustomerNew = em.getReference(companycustomerNew.getClass(), companycustomerNew.getCompanycustomerPK());
                crmexpense.setCompanycustomer(companycustomerNew);
            }
            if (crmexpensetypeNew != null) {
                crmexpensetypeNew = em.getReference(crmexpensetypeNew.getClass(), crmexpensetypeNew.getCrmexpensetypePK());
                crmexpense.setCrmexpensetype(crmexpensetypeNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmexpense.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmexpense.setCompanyemployee1(companyemployee1New);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmexpense.setCompany(companyNew);
            }
            crmexpense = em.merge(crmexpense);
            if (companycustomerOld != null && !companycustomerOld.equals(companycustomerNew)) {
                companycustomerOld.getCrmexpenseCollection().remove(crmexpense);
                companycustomerOld = em.merge(companycustomerOld);
            }
            if (companycustomerNew != null && !companycustomerNew.equals(companycustomerOld)) {
                companycustomerNew.getCrmexpenseCollection().add(crmexpense);
                companycustomerNew = em.merge(companycustomerNew);
            }
            if (crmexpensetypeOld != null && !crmexpensetypeOld.equals(crmexpensetypeNew)) {
                crmexpensetypeOld.getCrmexpenseCollection().remove(crmexpense);
                crmexpensetypeOld = em.merge(crmexpensetypeOld);
            }
            if (crmexpensetypeNew != null && !crmexpensetypeNew.equals(crmexpensetypeOld)) {
                crmexpensetypeNew.getCrmexpenseCollection().add(crmexpense);
                crmexpensetypeNew = em.merge(crmexpensetypeNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmexpenseCollection().remove(crmexpense);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmexpenseCollection().add(crmexpense);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmexpenseCollection().remove(crmexpense);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmexpenseCollection().add(crmexpense);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmexpenseCollection().remove(crmexpense);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmexpenseCollection().add(crmexpense);
                companyNew = em.merge(companyNew);
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
                CrmexpensePK id = crmexpense.getCrmexpensePK();
                if (findCrmexpense(id) == null) {
                    throw new NonexistentEntityException("The crmexpense with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmexpensePK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmexpense crmexpense;
            try {
                crmexpense = em.getReference(Crmexpense.class, id);
                crmexpense.getCrmexpensePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmexpense with id " + id + " no longer exists.", enfe);
            }
            Companycustomer companycustomer = crmexpense.getCompanycustomer();
            if (companycustomer != null) {
                companycustomer.getCrmexpenseCollection().remove(crmexpense);
                companycustomer = em.merge(companycustomer);
            }
            Crmexpensetype crmexpensetype = crmexpense.getCrmexpensetype();
            if (crmexpensetype != null) {
                crmexpensetype.getCrmexpenseCollection().remove(crmexpense);
                crmexpensetype = em.merge(crmexpensetype);
            }
            Companyemployee companyemployee = crmexpense.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmexpenseCollection().remove(crmexpense);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmexpense.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmexpenseCollection().remove(crmexpense);
                companyemployee1 = em.merge(companyemployee1);
            }
            Company company = crmexpense.getCompany();
            if (company != null) {
                company.getCrmexpenseCollection().remove(crmexpense);
                company = em.merge(company);
            }
            em.remove(crmexpense);
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

    public List<Crmexpense> findCrmexpenseEntities() {
        return findCrmexpenseEntities(true, -1, -1);
    }

    public List<Crmexpense> findCrmexpenseEntities(int maxResults, int firstResult) {
        return findCrmexpenseEntities(false, maxResults, firstResult);
    }

    private List<Crmexpense> findCrmexpenseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmexpense.class));
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

    public Crmexpense findCrmexpense(CrmexpensePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmexpense.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmexpenseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmexpense> rt = cq.from(Crmexpense.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
