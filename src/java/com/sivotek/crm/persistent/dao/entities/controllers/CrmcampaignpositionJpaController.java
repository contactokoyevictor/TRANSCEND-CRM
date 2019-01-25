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
import com.sivotek.crm.persistent.dao.entities.Crmcampaign;
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignposition;
import com.sivotek.crm.persistent.dao.entities.CrmcampaignpositionPK;
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
public class CrmcampaignpositionJpaController implements Serializable {

    public CrmcampaignpositionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmcampaignpositionJpaController(){
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

    public void create(Crmcampaignposition crmcampaignposition) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmcampaignposition.getCrmcampaignpositionPK() == null) {
            crmcampaignposition.setCrmcampaignpositionPK(new CrmcampaignpositionPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmcampaignposition.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmcampaignposition.setCompany(company);
            }
            Crmcampaign crmcampaign = crmcampaignposition.getCrmcampaign();
            if (crmcampaign != null) {
                crmcampaign = em.getReference(crmcampaign.getClass(), crmcampaign.getCrmcampaignPK());
                crmcampaignposition.setCrmcampaign(crmcampaign);
            }
            Companyproduct companyproduct = crmcampaignposition.getCompanyproduct();
            if (companyproduct != null) {
                companyproduct = em.getReference(companyproduct.getClass(), companyproduct.getCompanyproductPK());
                crmcampaignposition.setCompanyproduct(companyproduct);
            }
            Productcomponents productcomponents = crmcampaignposition.getProductcomponents();
            if (productcomponents != null) {
                productcomponents = em.getReference(productcomponents.getClass(), productcomponents.getProductcomponentsPK());
                crmcampaignposition.setProductcomponents(productcomponents);
            }
            Companyemployee companyemployee = crmcampaignposition.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmcampaignposition.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmcampaignposition.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmcampaignposition.setCompanyemployee1(companyemployee1);
            }
            em.persist(crmcampaignposition);
            if (company != null) {
                company.getCrmcampaignpositionCollection().add(crmcampaignposition);
                company = em.merge(company);
            }
            if (crmcampaign != null) {
                crmcampaign.getCrmcampaignpositionCollection().add(crmcampaignposition);
                crmcampaign = em.merge(crmcampaign);
            }
            if (companyproduct != null) {
                companyproduct.getCrmcampaignpositionCollection().add(crmcampaignposition);
                companyproduct = em.merge(companyproduct);
            }
            if (productcomponents != null) {
                productcomponents.getCrmcampaignpositionCollection().add(crmcampaignposition);
                productcomponents = em.merge(productcomponents);
            }
            if (companyemployee != null) {
                companyemployee.getCrmcampaignpositionCollection().add(crmcampaignposition);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmcampaignpositionCollection().add(crmcampaignposition);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmcampaignposition(crmcampaignposition.getCrmcampaignpositionPK()) != null) {
                throw new PreexistingEntityException("Crmcampaignposition " + crmcampaignposition + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmcampaignposition crmcampaignposition) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaignposition persistentCrmcampaignposition = em.find(Crmcampaignposition.class, crmcampaignposition.getCrmcampaignpositionPK());
            Company companyOld = persistentCrmcampaignposition.getCompany();
            Company companyNew = crmcampaignposition.getCompany();
            Crmcampaign crmcampaignOld = persistentCrmcampaignposition.getCrmcampaign();
            Crmcampaign crmcampaignNew = crmcampaignposition.getCrmcampaign();
            Companyproduct companyproductOld = persistentCrmcampaignposition.getCompanyproduct();
            Companyproduct companyproductNew = crmcampaignposition.getCompanyproduct();
            Productcomponents productcomponentsOld = persistentCrmcampaignposition.getProductcomponents();
            Productcomponents productcomponentsNew = crmcampaignposition.getProductcomponents();
            Companyemployee companyemployeeOld = persistentCrmcampaignposition.getCompanyemployee();
            Companyemployee companyemployeeNew = crmcampaignposition.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmcampaignposition.getCompanyemployee1();
            Companyemployee companyemployee1New = crmcampaignposition.getCompanyemployee1();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmcampaignposition.setCompany(companyNew);
            }
            if (crmcampaignNew != null) {
                crmcampaignNew = em.getReference(crmcampaignNew.getClass(), crmcampaignNew.getCrmcampaignPK());
                crmcampaignposition.setCrmcampaign(crmcampaignNew);
            }
            if (companyproductNew != null) {
                companyproductNew = em.getReference(companyproductNew.getClass(), companyproductNew.getCompanyproductPK());
                crmcampaignposition.setCompanyproduct(companyproductNew);
            }
            if (productcomponentsNew != null) {
                productcomponentsNew = em.getReference(productcomponentsNew.getClass(), productcomponentsNew.getProductcomponentsPK());
                crmcampaignposition.setProductcomponents(productcomponentsNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmcampaignposition.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmcampaignposition.setCompanyemployee1(companyemployee1New);
            }
            crmcampaignposition = em.merge(crmcampaignposition);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmcampaignpositionCollection().add(crmcampaignposition);
                companyNew = em.merge(companyNew);
            }
            if (crmcampaignOld != null && !crmcampaignOld.equals(crmcampaignNew)) {
                crmcampaignOld.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                crmcampaignOld = em.merge(crmcampaignOld);
            }
            if (crmcampaignNew != null && !crmcampaignNew.equals(crmcampaignOld)) {
                crmcampaignNew.getCrmcampaignpositionCollection().add(crmcampaignposition);
                crmcampaignNew = em.merge(crmcampaignNew);
            }
            if (companyproductOld != null && !companyproductOld.equals(companyproductNew)) {
                companyproductOld.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                companyproductOld = em.merge(companyproductOld);
            }
            if (companyproductNew != null && !companyproductNew.equals(companyproductOld)) {
                companyproductNew.getCrmcampaignpositionCollection().add(crmcampaignposition);
                companyproductNew = em.merge(companyproductNew);
            }
            if (productcomponentsOld != null && !productcomponentsOld.equals(productcomponentsNew)) {
                productcomponentsOld.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                productcomponentsOld = em.merge(productcomponentsOld);
            }
            if (productcomponentsNew != null && !productcomponentsNew.equals(productcomponentsOld)) {
                productcomponentsNew.getCrmcampaignpositionCollection().add(crmcampaignposition);
                productcomponentsNew = em.merge(productcomponentsNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmcampaignpositionCollection().add(crmcampaignposition);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmcampaignpositionCollection().add(crmcampaignposition);
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
                CrmcampaignpositionPK id = crmcampaignposition.getCrmcampaignpositionPK();
                if (findCrmcampaignposition(id) == null) {
                    throw new NonexistentEntityException("The crmcampaignposition with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmcampaignpositionPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaignposition crmcampaignposition;
            try {
                crmcampaignposition = em.getReference(Crmcampaignposition.class, id);
                crmcampaignposition.getCrmcampaignpositionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmcampaignposition with id " + id + " no longer exists.", enfe);
            }
            Company company = crmcampaignposition.getCompany();
            if (company != null) {
                company.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                company = em.merge(company);
            }
            Crmcampaign crmcampaign = crmcampaignposition.getCrmcampaign();
            if (crmcampaign != null) {
                crmcampaign.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                crmcampaign = em.merge(crmcampaign);
            }
            Companyproduct companyproduct = crmcampaignposition.getCompanyproduct();
            if (companyproduct != null) {
                companyproduct.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                companyproduct = em.merge(companyproduct);
            }
            Productcomponents productcomponents = crmcampaignposition.getProductcomponents();
            if (productcomponents != null) {
                productcomponents.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                productcomponents = em.merge(productcomponents);
            }
            Companyemployee companyemployee = crmcampaignposition.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmcampaignposition.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmcampaignpositionCollection().remove(crmcampaignposition);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmcampaignposition);
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

    public List<Crmcampaignposition> findCrmcampaignpositionEntities() {
        return findCrmcampaignpositionEntities(true, -1, -1);
    }

    public List<Crmcampaignposition> findCrmcampaignpositionEntities(int maxResults, int firstResult) {
        return findCrmcampaignpositionEntities(false, maxResults, firstResult);
    }

    private List<Crmcampaignposition> findCrmcampaignpositionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmcampaignposition.class));
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

    public Crmcampaignposition findCrmcampaignposition(CrmcampaignpositionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmcampaignposition.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmcampaignpositionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmcampaignposition> rt = cq.from(Crmcampaignposition.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
