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
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.Crmlabor;
import com.sivotek.crm.persistent.dao.entities.CrmlaborPK;
import com.sivotek.crm.persistent.dao.entities.Crmlabortype;
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
public class CrmlaborJpaController implements Serializable {

    public CrmlaborJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmlaborJpaController(){
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

    public void create(Crmlabor crmlabor) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmlabor.getCrmlaborPK() == null) {
            crmlabor.setCrmlaborPK(new CrmlaborPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = crmlabor.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmlabor.setCompanyemployee(companyemployee);
            }
            Company company = crmlabor.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmlabor.setCompany(company);
            }
            Companycustomer companycustomer = crmlabor.getCompanycustomer();
            if (companycustomer != null) {
                companycustomer = em.getReference(companycustomer.getClass(), companycustomer.getCompanycustomerPK());
                crmlabor.setCompanycustomer(companycustomer);
            }
            Crmlabortype crmlabortype = crmlabor.getCrmlabortype();
            if (crmlabortype != null) {
                crmlabortype = em.getReference(crmlabortype.getClass(), crmlabortype.getCrmlabortypePK());
                crmlabor.setCrmlabortype(crmlabortype);
            }
            Companyemployee companyemployee1 = crmlabor.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmlabor.setCompanyemployee1(companyemployee1);
            }
            em.persist(crmlabor);
            if (companyemployee != null) {
                companyemployee.getCrmlaborCollection().add(crmlabor);
                companyemployee = em.merge(companyemployee);
            }
            if (company != null) {
                company.getCrmlaborCollection().add(crmlabor);
                company = em.merge(company);
            }
            if (companycustomer != null) {
                companycustomer.getCrmlaborCollection().add(crmlabor);
                companycustomer = em.merge(companycustomer);
            }
            if (crmlabortype != null) {
                crmlabortype.getCrmlaborCollection().add(crmlabor);
                crmlabortype = em.merge(crmlabortype);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmlaborCollection().add(crmlabor);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmlabor(crmlabor.getCrmlaborPK()) != null) {
                throw new PreexistingEntityException("Crmlabor " + crmlabor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmlabor crmlabor) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlabor persistentCrmlabor = em.find(Crmlabor.class, crmlabor.getCrmlaborPK());
            Companyemployee companyemployeeOld = persistentCrmlabor.getCompanyemployee();
            Companyemployee companyemployeeNew = crmlabor.getCompanyemployee();
            Company companyOld = persistentCrmlabor.getCompany();
            Company companyNew = crmlabor.getCompany();
            Companycustomer companycustomerOld = persistentCrmlabor.getCompanycustomer();
            Companycustomer companycustomerNew = crmlabor.getCompanycustomer();
            Crmlabortype crmlabortypeOld = persistentCrmlabor.getCrmlabortype();
            Crmlabortype crmlabortypeNew = crmlabor.getCrmlabortype();
            Companyemployee companyemployee1Old = persistentCrmlabor.getCompanyemployee1();
            Companyemployee companyemployee1New = crmlabor.getCompanyemployee1();
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmlabor.setCompanyemployee(companyemployeeNew);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmlabor.setCompany(companyNew);
            }
            if (companycustomerNew != null) {
                companycustomerNew = em.getReference(companycustomerNew.getClass(), companycustomerNew.getCompanycustomerPK());
                crmlabor.setCompanycustomer(companycustomerNew);
            }
            if (crmlabortypeNew != null) {
                crmlabortypeNew = em.getReference(crmlabortypeNew.getClass(), crmlabortypeNew.getCrmlabortypePK());
                crmlabor.setCrmlabortype(crmlabortypeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmlabor.setCompanyemployee1(companyemployee1New);
            }
            crmlabor = em.merge(crmlabor);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmlaborCollection().remove(crmlabor);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmlaborCollection().add(crmlabor);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmlaborCollection().remove(crmlabor);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmlaborCollection().add(crmlabor);
                companyNew = em.merge(companyNew);
            }
            if (companycustomerOld != null && !companycustomerOld.equals(companycustomerNew)) {
                companycustomerOld.getCrmlaborCollection().remove(crmlabor);
                companycustomerOld = em.merge(companycustomerOld);
            }
            if (companycustomerNew != null && !companycustomerNew.equals(companycustomerOld)) {
                companycustomerNew.getCrmlaborCollection().add(crmlabor);
                companycustomerNew = em.merge(companycustomerNew);
            }
            if (crmlabortypeOld != null && !crmlabortypeOld.equals(crmlabortypeNew)) {
                crmlabortypeOld.getCrmlaborCollection().remove(crmlabor);
                crmlabortypeOld = em.merge(crmlabortypeOld);
            }
            if (crmlabortypeNew != null && !crmlabortypeNew.equals(crmlabortypeOld)) {
                crmlabortypeNew.getCrmlaborCollection().add(crmlabor);
                crmlabortypeNew = em.merge(crmlabortypeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmlaborCollection().remove(crmlabor);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmlaborCollection().add(crmlabor);
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
                CrmlaborPK id = crmlabor.getCrmlaborPK();
                if (findCrmlabor(id) == null) {
                    throw new NonexistentEntityException("The crmlabor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmlaborPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlabor crmlabor;
            try {
                crmlabor = em.getReference(Crmlabor.class, id);
                crmlabor.getCrmlaborPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmlabor with id " + id + " no longer exists.", enfe);
            }
            Companyemployee companyemployee = crmlabor.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmlaborCollection().remove(crmlabor);
                companyemployee = em.merge(companyemployee);
            }
            Company company = crmlabor.getCompany();
            if (company != null) {
                company.getCrmlaborCollection().remove(crmlabor);
                company = em.merge(company);
            }
            Companycustomer companycustomer = crmlabor.getCompanycustomer();
            if (companycustomer != null) {
                companycustomer.getCrmlaborCollection().remove(crmlabor);
                companycustomer = em.merge(companycustomer);
            }
            Crmlabortype crmlabortype = crmlabor.getCrmlabortype();
            if (crmlabortype != null) {
                crmlabortype.getCrmlaborCollection().remove(crmlabor);
                crmlabortype = em.merge(crmlabortype);
            }
            Companyemployee companyemployee1 = crmlabor.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmlaborCollection().remove(crmlabor);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmlabor);
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

    public List<Crmlabor> findCrmlaborEntities() {
        return findCrmlaborEntities(true, -1, -1);
    }

    public List<Crmlabor> findCrmlaborEntities(int maxResults, int firstResult) {
        return findCrmlaborEntities(false, maxResults, firstResult);
    }

    private List<Crmlabor> findCrmlaborEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmlabor.class));
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

    public Crmlabor findCrmlabor(CrmlaborPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmlabor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmlaborCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmlabor> rt = cq.from(Crmlabor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
