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
import com.sivotek.crm.persistent.dao.entities.Crmprojectprops;
import com.sivotek.crm.persistent.dao.entities.CrmprojectpropsPK;
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
public class CrmprojectpropsJpaController implements Serializable {

    public CrmprojectpropsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmprojectpropsJpaController(){
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

    public void create(Crmprojectprops crmprojectprops) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmprojectprops.getCrmprojectpropsPK() == null) {
            crmprojectprops.setCrmprojectpropsPK(new CrmprojectpropsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmprojectprops.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmprojectprops.setCompany(company);
            }
            Companyemployee companyemployee = crmprojectprops.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmprojectprops.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmprojectprops.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmprojectprops.setCompanyemployee1(companyemployee1);
            }
            em.persist(crmprojectprops);
            if (company != null) {
                company.getCrmprojectpropsCollection().add(crmprojectprops);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCrmprojectpropsCollection().add(crmprojectprops);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectpropsCollection().add(crmprojectprops);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmprojectprops(crmprojectprops.getCrmprojectpropsPK()) != null) {
                throw new PreexistingEntityException("Crmprojectprops " + crmprojectprops + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmprojectprops crmprojectprops) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectprops persistentCrmprojectprops = em.find(Crmprojectprops.class, crmprojectprops.getCrmprojectpropsPK());
            Company companyOld = persistentCrmprojectprops.getCompany();
            Company companyNew = crmprojectprops.getCompany();
            Companyemployee companyemployeeOld = persistentCrmprojectprops.getCompanyemployee();
            Companyemployee companyemployeeNew = crmprojectprops.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmprojectprops.getCompanyemployee1();
            Companyemployee companyemployee1New = crmprojectprops.getCompanyemployee1();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmprojectprops.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmprojectprops.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmprojectprops.setCompanyemployee1(companyemployee1New);
            }
            crmprojectprops = em.merge(crmprojectprops);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmprojectpropsCollection().remove(crmprojectprops);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmprojectpropsCollection().add(crmprojectprops);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmprojectpropsCollection().remove(crmprojectprops);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmprojectpropsCollection().add(crmprojectprops);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmprojectpropsCollection().remove(crmprojectprops);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmprojectpropsCollection().add(crmprojectprops);
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
                CrmprojectpropsPK id = crmprojectprops.getCrmprojectpropsPK();
                if (findCrmprojectprops(id) == null) {
                    throw new NonexistentEntityException("The crmprojectprops with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmprojectpropsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectprops crmprojectprops;
            try {
                crmprojectprops = em.getReference(Crmprojectprops.class, id);
                crmprojectprops.getCrmprojectpropsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmprojectprops with id " + id + " no longer exists.", enfe);
            }
            Company company = crmprojectprops.getCompany();
            if (company != null) {
                company.getCrmprojectpropsCollection().remove(crmprojectprops);
                company = em.merge(company);
            }
            Companyemployee companyemployee = crmprojectprops.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmprojectpropsCollection().remove(crmprojectprops);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmprojectprops.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectpropsCollection().remove(crmprojectprops);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmprojectprops);
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

    public List<Crmprojectprops> findCrmprojectpropsEntities() {
        return findCrmprojectpropsEntities(true, -1, -1);
    }

    public List<Crmprojectprops> findCrmprojectpropsEntities(int maxResults, int firstResult) {
        return findCrmprojectpropsEntities(false, maxResults, firstResult);
    }

    private List<Crmprojectprops> findCrmprojectpropsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmprojectprops.class));
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

    public Crmprojectprops findCrmprojectprops(CrmprojectpropsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmprojectprops.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmprojectpropsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmprojectprops> rt = cq.from(Crmprojectprops.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
