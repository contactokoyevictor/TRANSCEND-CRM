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
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignprops;
import com.sivotek.crm.persistent.dao.entities.CrmcampaignpropsPK;
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
public class CrmcampaignpropsJpaController implements Serializable {

    public CrmcampaignpropsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmcampaignpropsJpaController(){
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

    public void create(Crmcampaignprops crmcampaignprops) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmcampaignprops.getCrmcampaignpropsPK() == null) {
            crmcampaignprops.setCrmcampaignpropsPK(new CrmcampaignpropsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmcampaignprops.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmcampaignprops.setCompany(company);
            }
            Crmcampaign crmcampaign = crmcampaignprops.getCrmcampaign();
            if (crmcampaign != null) {
                crmcampaign = em.getReference(crmcampaign.getClass(), crmcampaign.getCrmcampaignPK());
                crmcampaignprops.setCrmcampaign(crmcampaign);
            }
            Companyemployee companyemployee = crmcampaignprops.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmcampaignprops.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmcampaignprops.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmcampaignprops.setCompanyemployee1(companyemployee1);
            }
            em.persist(crmcampaignprops);
            if (company != null) {
                company.getCrmcampaignpropsCollection().add(crmcampaignprops);
                company = em.merge(company);
            }
            if (crmcampaign != null) {
                crmcampaign.getCrmcampaignpropsCollection().add(crmcampaignprops);
                crmcampaign = em.merge(crmcampaign);
            }
            if (companyemployee != null) {
                companyemployee.getCrmcampaignpropsCollection().add(crmcampaignprops);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmcampaignpropsCollection().add(crmcampaignprops);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmcampaignprops(crmcampaignprops.getCrmcampaignpropsPK()) != null) {
                throw new PreexistingEntityException("Crmcampaignprops " + crmcampaignprops + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmcampaignprops crmcampaignprops) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaignprops persistentCrmcampaignprops = em.find(Crmcampaignprops.class, crmcampaignprops.getCrmcampaignpropsPK());
            Company companyOld = persistentCrmcampaignprops.getCompany();
            Company companyNew = crmcampaignprops.getCompany();
            Crmcampaign crmcampaignOld = persistentCrmcampaignprops.getCrmcampaign();
            Crmcampaign crmcampaignNew = crmcampaignprops.getCrmcampaign();
            Companyemployee companyemployeeOld = persistentCrmcampaignprops.getCompanyemployee();
            Companyemployee companyemployeeNew = crmcampaignprops.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmcampaignprops.getCompanyemployee1();
            Companyemployee companyemployee1New = crmcampaignprops.getCompanyemployee1();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmcampaignprops.setCompany(companyNew);
            }
            if (crmcampaignNew != null) {
                crmcampaignNew = em.getReference(crmcampaignNew.getClass(), crmcampaignNew.getCrmcampaignPK());
                crmcampaignprops.setCrmcampaign(crmcampaignNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmcampaignprops.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmcampaignprops.setCompanyemployee1(companyemployee1New);
            }
            crmcampaignprops = em.merge(crmcampaignprops);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmcampaignpropsCollection().remove(crmcampaignprops);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmcampaignpropsCollection().add(crmcampaignprops);
                companyNew = em.merge(companyNew);
            }
            if (crmcampaignOld != null && !crmcampaignOld.equals(crmcampaignNew)) {
                crmcampaignOld.getCrmcampaignpropsCollection().remove(crmcampaignprops);
                crmcampaignOld = em.merge(crmcampaignOld);
            }
            if (crmcampaignNew != null && !crmcampaignNew.equals(crmcampaignOld)) {
                crmcampaignNew.getCrmcampaignpropsCollection().add(crmcampaignprops);
                crmcampaignNew = em.merge(crmcampaignNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmcampaignpropsCollection().remove(crmcampaignprops);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmcampaignpropsCollection().add(crmcampaignprops);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmcampaignpropsCollection().remove(crmcampaignprops);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmcampaignpropsCollection().add(crmcampaignprops);
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
                CrmcampaignpropsPK id = crmcampaignprops.getCrmcampaignpropsPK();
                if (findCrmcampaignprops(id) == null) {
                    throw new NonexistentEntityException("The crmcampaignprops with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmcampaignpropsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaignprops crmcampaignprops;
            try {
                crmcampaignprops = em.getReference(Crmcampaignprops.class, id);
                crmcampaignprops.getCrmcampaignpropsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmcampaignprops with id " + id + " no longer exists.", enfe);
            }
            Company company = crmcampaignprops.getCompany();
            if (company != null) {
                company.getCrmcampaignpropsCollection().remove(crmcampaignprops);
                company = em.merge(company);
            }
            Crmcampaign crmcampaign = crmcampaignprops.getCrmcampaign();
            if (crmcampaign != null) {
                crmcampaign.getCrmcampaignpropsCollection().remove(crmcampaignprops);
                crmcampaign = em.merge(crmcampaign);
            }
            Companyemployee companyemployee = crmcampaignprops.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmcampaignpropsCollection().remove(crmcampaignprops);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmcampaignprops.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmcampaignpropsCollection().remove(crmcampaignprops);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmcampaignprops);
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

    public List<Crmcampaignprops> findCrmcampaignpropsEntities() {
        return findCrmcampaignpropsEntities(true, -1, -1);
    }

    public List<Crmcampaignprops> findCrmcampaignpropsEntities(int maxResults, int firstResult) {
        return findCrmcampaignpropsEntities(false, maxResults, firstResult);
    }

    private List<Crmcampaignprops> findCrmcampaignpropsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmcampaignprops.class));
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

    public Crmcampaignprops findCrmcampaignprops(CrmcampaignpropsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmcampaignprops.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmcampaignpropsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmcampaignprops> rt = cq.from(Crmcampaignprops.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
