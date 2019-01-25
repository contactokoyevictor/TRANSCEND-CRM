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
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.Companycontacts;
import com.sivotek.crm.persistent.dao.entities.Companycontactsaddress;
import com.sivotek.crm.persistent.dao.entities.CompanycontactsaddressPK;
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
public class CompanycontactsaddressJpaController implements Serializable {

    public CompanycontactsaddressJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanycontactsaddressJpaController(){
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

    public void create(Companycontactsaddress companycontactsaddress) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companycontactsaddress.getCompanycontactsaddressPK() == null) {
            companycontactsaddress.setCompanycontactsaddressPK(new CompanycontactsaddressPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = companycontactsaddress.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companycontactsaddress.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companycontactsaddress.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companycontactsaddress.setCompanyemployee1(companyemployee1);
            }
            Company company = companycontactsaddress.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companycontactsaddress.setCompany(company);
            }
            Companycontacts companycontacts = companycontactsaddress.getCompanycontacts();
            if (companycontacts != null) {
                companycontacts = em.getReference(companycontacts.getClass(), companycontacts.getCompanycontactsPK());
                companycontactsaddress.setCompanycontacts(companycontacts);
            }
            em.persist(companycontactsaddress);
            if (companyemployee != null) {
                companyemployee.getCompanycontactsaddressCollection().add(companycontactsaddress);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanycontactsaddressCollection().add(companycontactsaddress);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (company != null) {
                company.getCompanycontactsaddressCollection().add(companycontactsaddress);
                company = em.merge(company);
            }
            if (companycontacts != null) {
                companycontacts.getCompanycontactsaddressCollection().add(companycontactsaddress);
                companycontacts = em.merge(companycontacts);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanycontactsaddress(companycontactsaddress.getCompanycontactsaddressPK()) != null) {
                throw new PreexistingEntityException("Companycontactsaddress " + companycontactsaddress + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companycontactsaddress companycontactsaddress) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companycontactsaddress persistentCompanycontactsaddress = em.find(Companycontactsaddress.class, companycontactsaddress.getCompanycontactsaddressPK());
            Companyemployee companyemployeeOld = persistentCompanycontactsaddress.getCompanyemployee();
            Companyemployee companyemployeeNew = companycontactsaddress.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanycontactsaddress.getCompanyemployee1();
            Companyemployee companyemployee1New = companycontactsaddress.getCompanyemployee1();
            Company companyOld = persistentCompanycontactsaddress.getCompany();
            Company companyNew = companycontactsaddress.getCompany();
            Companycontacts companycontactsOld = persistentCompanycontactsaddress.getCompanycontacts();
            Companycontacts companycontactsNew = companycontactsaddress.getCompanycontacts();
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companycontactsaddress.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companycontactsaddress.setCompanyemployee1(companyemployee1New);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companycontactsaddress.setCompany(companyNew);
            }
            if (companycontactsNew != null) {
                companycontactsNew = em.getReference(companycontactsNew.getClass(), companycontactsNew.getCompanycontactsPK());
                companycontactsaddress.setCompanycontacts(companycontactsNew);
            }
            companycontactsaddress = em.merge(companycontactsaddress);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanycontactsaddressCollection().remove(companycontactsaddress);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanycontactsaddressCollection().add(companycontactsaddress);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanycontactsaddressCollection().remove(companycontactsaddress);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanycontactsaddressCollection().add(companycontactsaddress);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanycontactsaddressCollection().remove(companycontactsaddress);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanycontactsaddressCollection().add(companycontactsaddress);
                companyNew = em.merge(companyNew);
            }
            if (companycontactsOld != null && !companycontactsOld.equals(companycontactsNew)) {
                companycontactsOld.getCompanycontactsaddressCollection().remove(companycontactsaddress);
                companycontactsOld = em.merge(companycontactsOld);
            }
            if (companycontactsNew != null && !companycontactsNew.equals(companycontactsOld)) {
                companycontactsNew.getCompanycontactsaddressCollection().add(companycontactsaddress);
                companycontactsNew = em.merge(companycontactsNew);
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
                CompanycontactsaddressPK id = companycontactsaddress.getCompanycontactsaddressPK();
                if (findCompanycontactsaddress(id) == null) {
                    throw new NonexistentEntityException("The companycontactsaddress with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanycontactsaddressPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companycontactsaddress companycontactsaddress;
            try {
                companycontactsaddress = em.getReference(Companycontactsaddress.class, id);
                companycontactsaddress.getCompanycontactsaddressPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companycontactsaddress with id " + id + " no longer exists.", enfe);
            }
            Companyemployee companyemployee = companycontactsaddress.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanycontactsaddressCollection().remove(companycontactsaddress);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companycontactsaddress.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanycontactsaddressCollection().remove(companycontactsaddress);
                companyemployee1 = em.merge(companyemployee1);
            }
            Company company = companycontactsaddress.getCompany();
            if (company != null) {
                company.getCompanycontactsaddressCollection().remove(companycontactsaddress);
                company = em.merge(company);
            }
            Companycontacts companycontacts = companycontactsaddress.getCompanycontacts();
            if (companycontacts != null) {
                companycontacts.getCompanycontactsaddressCollection().remove(companycontactsaddress);
                companycontacts = em.merge(companycontacts);
            }
            em.remove(companycontactsaddress);
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

    public List<Companycontactsaddress> findCompanycontactsaddressEntities() {
        return findCompanycontactsaddressEntities(true, -1, -1);
    }

    public List<Companycontactsaddress> findCompanycontactsaddressEntities(int maxResults, int firstResult) {
        return findCompanycontactsaddressEntities(false, maxResults, firstResult);
    }

    private List<Companycontactsaddress> findCompanycontactsaddressEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companycontactsaddress.class));
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

    public Companycontactsaddress findCompanycontactsaddress(CompanycontactsaddressPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companycontactsaddress.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanycontactsaddressCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companycontactsaddress> rt = cq.from(Companycontactsaddress.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
