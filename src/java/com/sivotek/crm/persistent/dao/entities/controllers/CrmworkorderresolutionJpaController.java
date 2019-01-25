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
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.Crmbillingtype;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmworkorderresolution;
import com.sivotek.crm.persistent.dao.entities.CrmworkorderresolutionPK;
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
public class CrmworkorderresolutionJpaController implements Serializable {

    public CrmworkorderresolutionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public CrmworkorderresolutionJpaController(){
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

    public void create(Crmworkorderresolution crmworkorderresolution) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmworkorderresolution.getCrmworkorderresolutionPK() == null) {
            crmworkorderresolution.setCrmworkorderresolutionPK(new CrmworkorderresolutionPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmworkorderresolution.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmworkorderresolution.setCompany(company);
            }
            Crmworkorder crmworkorder = crmworkorderresolution.getCrmworkorder();
            if (crmworkorder != null) {
                crmworkorder = em.getReference(crmworkorder.getClass(), crmworkorder.getCrmworkorderPK());
                crmworkorderresolution.setCrmworkorder(crmworkorder);
            }
            Crmbillingtype crmbillingtype = crmworkorderresolution.getCrmbillingtype();
            if (crmbillingtype != null) {
                crmbillingtype = em.getReference(crmbillingtype.getClass(), crmbillingtype.getCrmbillingtypePK());
                crmworkorderresolution.setCrmbillingtype(crmbillingtype);
            }
            Companyemployee companyemployee = crmworkorderresolution.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmworkorderresolution.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmworkorderresolution.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmworkorderresolution.setCompanyemployee1(companyemployee1);
            }
            em.persist(crmworkorderresolution);
            if (company != null) {
                company.getCrmworkorderresolutionCollection().add(crmworkorderresolution);
                company = em.merge(company);
            }
            if (crmworkorder != null) {
                crmworkorder.getCrmworkorderresolutionCollection().add(crmworkorderresolution);
                crmworkorder = em.merge(crmworkorder);
            }
            if (crmbillingtype != null) {
                crmbillingtype.getCrmworkorderresolutionCollection().add(crmworkorderresolution);
                crmbillingtype = em.merge(crmbillingtype);
            }
            if (companyemployee != null) {
                companyemployee.getCrmworkorderresolutionCollection().add(crmworkorderresolution);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmworkorderresolutionCollection().add(crmworkorderresolution);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmworkorderresolution(crmworkorderresolution.getCrmworkorderresolutionPK()) != null) {
                throw new PreexistingEntityException("Crmworkorderresolution " + crmworkorderresolution + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmworkorderresolution crmworkorderresolution) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmworkorderresolution persistentCrmworkorderresolution = em.find(Crmworkorderresolution.class, crmworkorderresolution.getCrmworkorderresolutionPK());
            Company companyOld = persistentCrmworkorderresolution.getCompany();
            Company companyNew = crmworkorderresolution.getCompany();
            Crmworkorder crmworkorderOld = persistentCrmworkorderresolution.getCrmworkorder();
            Crmworkorder crmworkorderNew = crmworkorderresolution.getCrmworkorder();
            Crmbillingtype crmbillingtypeOld = persistentCrmworkorderresolution.getCrmbillingtype();
            Crmbillingtype crmbillingtypeNew = crmworkorderresolution.getCrmbillingtype();
            Companyemployee companyemployeeOld = persistentCrmworkorderresolution.getCompanyemployee();
            Companyemployee companyemployeeNew = crmworkorderresolution.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmworkorderresolution.getCompanyemployee1();
            Companyemployee companyemployee1New = crmworkorderresolution.getCompanyemployee1();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmworkorderresolution.setCompany(companyNew);
            }
            if (crmworkorderNew != null) {
                crmworkorderNew = em.getReference(crmworkorderNew.getClass(), crmworkorderNew.getCrmworkorderPK());
                crmworkorderresolution.setCrmworkorder(crmworkorderNew);
            }
            if (crmbillingtypeNew != null) {
                crmbillingtypeNew = em.getReference(crmbillingtypeNew.getClass(), crmbillingtypeNew.getCrmbillingtypePK());
                crmworkorderresolution.setCrmbillingtype(crmbillingtypeNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmworkorderresolution.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmworkorderresolution.setCompanyemployee1(companyemployee1New);
            }
            crmworkorderresolution = em.merge(crmworkorderresolution);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmworkorderresolutionCollection().remove(crmworkorderresolution);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmworkorderresolutionCollection().add(crmworkorderresolution);
                companyNew = em.merge(companyNew);
            }
            if (crmworkorderOld != null && !crmworkorderOld.equals(crmworkorderNew)) {
                crmworkorderOld.getCrmworkorderresolutionCollection().remove(crmworkorderresolution);
                crmworkorderOld = em.merge(crmworkorderOld);
            }
            if (crmworkorderNew != null && !crmworkorderNew.equals(crmworkorderOld)) {
                crmworkorderNew.getCrmworkorderresolutionCollection().add(crmworkorderresolution);
                crmworkorderNew = em.merge(crmworkorderNew);
            }
            if (crmbillingtypeOld != null && !crmbillingtypeOld.equals(crmbillingtypeNew)) {
                crmbillingtypeOld.getCrmworkorderresolutionCollection().remove(crmworkorderresolution);
                crmbillingtypeOld = em.merge(crmbillingtypeOld);
            }
            if (crmbillingtypeNew != null && !crmbillingtypeNew.equals(crmbillingtypeOld)) {
                crmbillingtypeNew.getCrmworkorderresolutionCollection().add(crmworkorderresolution);
                crmbillingtypeNew = em.merge(crmbillingtypeNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmworkorderresolutionCollection().remove(crmworkorderresolution);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmworkorderresolutionCollection().add(crmworkorderresolution);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmworkorderresolutionCollection().remove(crmworkorderresolution);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmworkorderresolutionCollection().add(crmworkorderresolution);
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
                CrmworkorderresolutionPK id = crmworkorderresolution.getCrmworkorderresolutionPK();
                if (findCrmworkorderresolution(id) == null) {
                    throw new NonexistentEntityException("The crmworkorderresolution with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmworkorderresolutionPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmworkorderresolution crmworkorderresolution;
            try {
                crmworkorderresolution = em.getReference(Crmworkorderresolution.class, id);
                crmworkorderresolution.getCrmworkorderresolutionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmworkorderresolution with id " + id + " no longer exists.", enfe);
            }
            Company company = crmworkorderresolution.getCompany();
            if (company != null) {
                company.getCrmworkorderresolutionCollection().remove(crmworkorderresolution);
                company = em.merge(company);
            }
            Crmworkorder crmworkorder = crmworkorderresolution.getCrmworkorder();
            if (crmworkorder != null) {
                crmworkorder.getCrmworkorderresolutionCollection().remove(crmworkorderresolution);
                crmworkorder = em.merge(crmworkorder);
            }
            Crmbillingtype crmbillingtype = crmworkorderresolution.getCrmbillingtype();
            if (crmbillingtype != null) {
                crmbillingtype.getCrmworkorderresolutionCollection().remove(crmworkorderresolution);
                crmbillingtype = em.merge(crmbillingtype);
            }
            Companyemployee companyemployee = crmworkorderresolution.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmworkorderresolutionCollection().remove(crmworkorderresolution);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmworkorderresolution.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmworkorderresolutionCollection().remove(crmworkorderresolution);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmworkorderresolution);
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

    public List<Crmworkorderresolution> findCrmworkorderresolutionEntities() {
        return findCrmworkorderresolutionEntities(true, -1, -1);
    }

    public List<Crmworkorderresolution> findCrmworkorderresolutionEntities(int maxResults, int firstResult) {
        return findCrmworkorderresolutionEntities(false, maxResults, firstResult);
    }

    private List<Crmworkorderresolution> findCrmworkorderresolutionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmworkorderresolution.class));
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

    public Crmworkorderresolution findCrmworkorderresolution(CrmworkorderresolutionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmworkorderresolution.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmworkorderresolutionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmworkorderresolution> rt = cq.from(Crmworkorderresolution.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
