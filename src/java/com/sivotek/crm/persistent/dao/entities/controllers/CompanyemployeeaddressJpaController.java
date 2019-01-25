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
import com.sivotek.crm.persistent.dao.entities.Companyaddresstype;
import com.sivotek.crm.persistent.dao.entities.Companyemployeeaddress;
import com.sivotek.crm.persistent.dao.entities.CompanyemployeeaddressPK;
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
public class CompanyemployeeaddressJpaController implements Serializable {

    public CompanyemployeeaddressJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanyemployeeaddressJpaController(){
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

    public void create(Companyemployeeaddress companyemployeeaddress) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyemployeeaddress.getCompanyemployeeaddressPK() == null) {
            companyemployeeaddress.setCompanyemployeeaddressPK(new CompanyemployeeaddressPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = companyemployeeaddress.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companyemployeeaddress.setCompanyemployee(companyemployee);
            }
            Companyaddresstype companyaddresstype = companyemployeeaddress.getCompanyaddresstype();
            if (companyaddresstype != null) {
                companyaddresstype = em.getReference(companyaddresstype.getClass(), companyaddresstype.getCompanyaddresstypePK());
                companyemployeeaddress.setCompanyaddresstype(companyaddresstype);
            }
            Companyemployee companyemployee1 = companyemployeeaddress.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companyemployeeaddress.setCompanyemployee1(companyemployee1);
            }
            Companyemployee companyemployee2 = companyemployeeaddress.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2 = em.getReference(companyemployee2.getClass(), companyemployee2.getCompanyemployeePK());
                companyemployeeaddress.setCompanyemployee2(companyemployee2);
            }
            em.persist(companyemployeeaddress);
            if (companyemployee != null) {
                companyemployee.getCompanyemployeeaddressCollection().add(companyemployeeaddress);
                companyemployee = em.merge(companyemployee);
            }
            if (companyaddresstype != null) {
                companyaddresstype.getCompanyemployeeaddressCollection().add(companyemployeeaddress);
                companyaddresstype = em.merge(companyaddresstype);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanyemployeeaddressCollection().add(companyemployeeaddress);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (companyemployee2 != null) {
                companyemployee2.getCompanyemployeeaddressCollection().add(companyemployeeaddress);
                companyemployee2 = em.merge(companyemployee2);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyemployeeaddress(companyemployeeaddress.getCompanyemployeeaddressPK()) != null) {
                throw new PreexistingEntityException("Companyemployeeaddress " + companyemployeeaddress + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companyemployeeaddress companyemployeeaddress) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployeeaddress persistentCompanyemployeeaddress = em.find(Companyemployeeaddress.class, companyemployeeaddress.getCompanyemployeeaddressPK());
            Companyemployee companyemployeeOld = persistentCompanyemployeeaddress.getCompanyemployee();
            Companyemployee companyemployeeNew = companyemployeeaddress.getCompanyemployee();
            Companyaddresstype companyaddresstypeOld = persistentCompanyemployeeaddress.getCompanyaddresstype();
            Companyaddresstype companyaddresstypeNew = companyemployeeaddress.getCompanyaddresstype();
            Companyemployee companyemployee1Old = persistentCompanyemployeeaddress.getCompanyemployee1();
            Companyemployee companyemployee1New = companyemployeeaddress.getCompanyemployee1();
            Companyemployee companyemployee2Old = persistentCompanyemployeeaddress.getCompanyemployee2();
            Companyemployee companyemployee2New = companyemployeeaddress.getCompanyemployee2();
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companyemployeeaddress.setCompanyemployee(companyemployeeNew);
            }
            if (companyaddresstypeNew != null) {
                companyaddresstypeNew = em.getReference(companyaddresstypeNew.getClass(), companyaddresstypeNew.getCompanyaddresstypePK());
                companyemployeeaddress.setCompanyaddresstype(companyaddresstypeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companyemployeeaddress.setCompanyemployee1(companyemployee1New);
            }
            if (companyemployee2New != null) {
                companyemployee2New = em.getReference(companyemployee2New.getClass(), companyemployee2New.getCompanyemployeePK());
                companyemployeeaddress.setCompanyemployee2(companyemployee2New);
            }
            companyemployeeaddress = em.merge(companyemployeeaddress);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanyemployeeaddressCollection().remove(companyemployeeaddress);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanyemployeeaddressCollection().add(companyemployeeaddress);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyaddresstypeOld != null && !companyaddresstypeOld.equals(companyaddresstypeNew)) {
                companyaddresstypeOld.getCompanyemployeeaddressCollection().remove(companyemployeeaddress);
                companyaddresstypeOld = em.merge(companyaddresstypeOld);
            }
            if (companyaddresstypeNew != null && !companyaddresstypeNew.equals(companyaddresstypeOld)) {
                companyaddresstypeNew.getCompanyemployeeaddressCollection().add(companyemployeeaddress);
                companyaddresstypeNew = em.merge(companyaddresstypeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanyemployeeaddressCollection().remove(companyemployeeaddress);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanyemployeeaddressCollection().add(companyemployeeaddress);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (companyemployee2Old != null && !companyemployee2Old.equals(companyemployee2New)) {
                companyemployee2Old.getCompanyemployeeaddressCollection().remove(companyemployeeaddress);
                companyemployee2Old = em.merge(companyemployee2Old);
            }
            if (companyemployee2New != null && !companyemployee2New.equals(companyemployee2Old)) {
                companyemployee2New.getCompanyemployeeaddressCollection().add(companyemployeeaddress);
                companyemployee2New = em.merge(companyemployee2New);
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
                CompanyemployeeaddressPK id = companyemployeeaddress.getCompanyemployeeaddressPK();
                if (findCompanyemployeeaddress(id) == null) {
                    throw new NonexistentEntityException("The companyemployeeaddress with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyemployeeaddressPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployeeaddress companyemployeeaddress;
            try {
                companyemployeeaddress = em.getReference(Companyemployeeaddress.class, id);
                companyemployeeaddress.getCompanyemployeeaddressPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyemployeeaddress with id " + id + " no longer exists.", enfe);
            }
            Companyemployee companyemployee = companyemployeeaddress.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanyemployeeaddressCollection().remove(companyemployeeaddress);
                companyemployee = em.merge(companyemployee);
            }
            Companyaddresstype companyaddresstype = companyemployeeaddress.getCompanyaddresstype();
            if (companyaddresstype != null) {
                companyaddresstype.getCompanyemployeeaddressCollection().remove(companyemployeeaddress);
                companyaddresstype = em.merge(companyaddresstype);
            }
            Companyemployee companyemployee1 = companyemployeeaddress.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanyemployeeaddressCollection().remove(companyemployeeaddress);
                companyemployee1 = em.merge(companyemployee1);
            }
            Companyemployee companyemployee2 = companyemployeeaddress.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2.getCompanyemployeeaddressCollection().remove(companyemployeeaddress);
                companyemployee2 = em.merge(companyemployee2);
            }
            em.remove(companyemployeeaddress);
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

    public List<Companyemployeeaddress> findCompanyemployeeaddressEntities() {
        return findCompanyemployeeaddressEntities(true, -1, -1);
    }

    public List<Companyemployeeaddress> findCompanyemployeeaddressEntities(int maxResults, int firstResult) {
        return findCompanyemployeeaddressEntities(false, maxResults, firstResult);
    }

    private List<Companyemployeeaddress> findCompanyemployeeaddressEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companyemployeeaddress.class));
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

    public Companyemployeeaddress findCompanyemployeeaddress(CompanyemployeeaddressPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companyemployeeaddress.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyemployeeaddressCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companyemployeeaddress> rt = cq.from(Companyemployeeaddress.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
