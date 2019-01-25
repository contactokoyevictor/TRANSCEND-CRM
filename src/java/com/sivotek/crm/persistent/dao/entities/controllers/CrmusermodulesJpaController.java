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
import com.sivotek.crm.persistent.dao.entities.Crmsubmodules;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.Crmmodules;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmusermodules;
import com.sivotek.crm.persistent.dao.entities.CrmusermodulesPK;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author okoyevictor
 */
public class CrmusermodulesJpaController implements Serializable {

    public CrmusermodulesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmusermodulesJpaController(){
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

    public void create(Crmusermodules crmusermodules) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmusermodules.getCrmusermodulesPK() == null) {
            crmusermodules.setCrmusermodulesPK(new CrmusermodulesPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmsubmodules crmsubmodules = crmusermodules.getCrmsubmodules();
            if (crmsubmodules != null) {
                crmsubmodules = em.getReference(crmsubmodules.getClass(), crmsubmodules.getCrmsubmodulesPK());
                crmusermodules.setCrmsubmodules(crmsubmodules);
            }
            Company company = crmusermodules.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmusermodules.setCompany(company);
            }
            Crmmodules crmmodules = crmusermodules.getCrmmodules();
            if (crmmodules != null) {
                crmmodules = em.getReference(crmmodules.getClass(), crmmodules.getCrmmodulesPK());
                crmusermodules.setCrmmodules(crmmodules);
            }
            Companyemployee companyemployee = crmusermodules.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmusermodules.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmusermodules.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmusermodules.setCompanyemployee1(companyemployee1);
            }
            em.persist(crmusermodules);
            if (crmsubmodules != null) {
                crmsubmodules.getCrmusermodulesCollection().add(crmusermodules);
                crmsubmodules = em.merge(crmsubmodules);
            }
            if (company != null) {
                company.getCrmusermodulesCollection().add(crmusermodules);
                company = em.merge(company);
            }
            if (crmmodules != null) {
                crmmodules.getCrmusermodulesCollection().add(crmusermodules);
                crmmodules = em.merge(crmmodules);
            }
            if (companyemployee != null) {
                companyemployee.getCrmusermodulesCollection().add(crmusermodules);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmusermodulesCollection().add(crmusermodules);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmusermodules(crmusermodules.getCrmusermodulesPK()) != null) {
                throw new PreexistingEntityException("Crmusermodules " + crmusermodules + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmusermodules crmusermodules) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmusermodules persistentCrmusermodules = em.find(Crmusermodules.class, crmusermodules.getCrmusermodulesPK());
            Crmsubmodules crmsubmodulesOld = persistentCrmusermodules.getCrmsubmodules();
            Crmsubmodules crmsubmodulesNew = crmusermodules.getCrmsubmodules();
            Company companyOld = persistentCrmusermodules.getCompany();
            Company companyNew = crmusermodules.getCompany();
            Crmmodules crmmodulesOld = persistentCrmusermodules.getCrmmodules();
            Crmmodules crmmodulesNew = crmusermodules.getCrmmodules();
            Companyemployee companyemployeeOld = persistentCrmusermodules.getCompanyemployee();
            Companyemployee companyemployeeNew = crmusermodules.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmusermodules.getCompanyemployee1();
            Companyemployee companyemployee1New = crmusermodules.getCompanyemployee1();
            if (crmsubmodulesNew != null) {
                crmsubmodulesNew = em.getReference(crmsubmodulesNew.getClass(), crmsubmodulesNew.getCrmsubmodulesPK());
                crmusermodules.setCrmsubmodules(crmsubmodulesNew);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmusermodules.setCompany(companyNew);
            }
            if (crmmodulesNew != null) {
                crmmodulesNew = em.getReference(crmmodulesNew.getClass(), crmmodulesNew.getCrmmodulesPK());
                crmusermodules.setCrmmodules(crmmodulesNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmusermodules.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmusermodules.setCompanyemployee1(companyemployee1New);
            }
            crmusermodules = em.merge(crmusermodules);
            if (crmsubmodulesOld != null && !crmsubmodulesOld.equals(crmsubmodulesNew)) {
                crmsubmodulesOld.getCrmusermodulesCollection().remove(crmusermodules);
                crmsubmodulesOld = em.merge(crmsubmodulesOld);
            }
            if (crmsubmodulesNew != null && !crmsubmodulesNew.equals(crmsubmodulesOld)) {
                crmsubmodulesNew.getCrmusermodulesCollection().add(crmusermodules);
                crmsubmodulesNew = em.merge(crmsubmodulesNew);
            }
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmusermodulesCollection().remove(crmusermodules);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmusermodulesCollection().add(crmusermodules);
                companyNew = em.merge(companyNew);
            }
            if (crmmodulesOld != null && !crmmodulesOld.equals(crmmodulesNew)) {
                crmmodulesOld.getCrmusermodulesCollection().remove(crmusermodules);
                crmmodulesOld = em.merge(crmmodulesOld);
            }
            if (crmmodulesNew != null && !crmmodulesNew.equals(crmmodulesOld)) {
                crmmodulesNew.getCrmusermodulesCollection().add(crmusermodules);
                crmmodulesNew = em.merge(crmmodulesNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmusermodulesCollection().remove(crmusermodules);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmusermodulesCollection().add(crmusermodules);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmusermodulesCollection().remove(crmusermodules);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmusermodulesCollection().add(crmusermodules);
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
                CrmusermodulesPK id = crmusermodules.getCrmusermodulesPK();
                if (findCrmusermodules(id) == null) {
                    throw new NonexistentEntityException("The crmusermodules with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmusermodulesPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmusermodules crmusermodules;
            try {
                crmusermodules = em.getReference(Crmusermodules.class, id);
                crmusermodules.getCrmusermodulesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmusermodules with id " + id + " no longer exists.", enfe);
            }
            Crmsubmodules crmsubmodules = crmusermodules.getCrmsubmodules();
            if (crmsubmodules != null) {
                crmsubmodules.getCrmusermodulesCollection().remove(crmusermodules);
                crmsubmodules = em.merge(crmsubmodules);
            }
            Company company = crmusermodules.getCompany();
            if (company != null) {
                company.getCrmusermodulesCollection().remove(crmusermodules);
                company = em.merge(company);
            }
            Crmmodules crmmodules = crmusermodules.getCrmmodules();
            if (crmmodules != null) {
                crmmodules.getCrmusermodulesCollection().remove(crmusermodules);
                crmmodules = em.merge(crmmodules);
            }
            Companyemployee companyemployee = crmusermodules.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmusermodulesCollection().remove(crmusermodules);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmusermodules.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmusermodulesCollection().remove(crmusermodules);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmusermodules);
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

    public List<Crmusermodules> findCrmusermodulesEntities() {
        return findCrmusermodulesEntities(true, -1, -1);
    }

    public List<Crmusermodules> findCrmusermodulesEntities(int maxResults, int firstResult) {
        return findCrmusermodulesEntities(false, maxResults, firstResult);
    }

    private List<Crmusermodules> findCrmusermodulesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmusermodules.class));
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

    public Crmusermodules findCrmusermodules(CrmusermodulesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmusermodules.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmusermodulesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmusermodules> rt = cq.from(Crmusermodules.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
