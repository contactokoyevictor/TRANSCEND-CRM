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
import com.sivotek.crm.persistent.dao.entities.Companypayments;
import com.sivotek.crm.persistent.dao.entities.Compnaypaymentcurrency;
import com.sivotek.crm.persistent.dao.entities.CompnaypaymentcurrencyPK;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
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
public class CompnaypaymentcurrencyJpaController implements Serializable {

    public CompnaypaymentcurrencyJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompnaypaymentcurrencyJpaController(){
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

    public void create(Compnaypaymentcurrency compnaypaymentcurrency) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (compnaypaymentcurrency.getCompnaypaymentcurrencyPK() == null) {
            compnaypaymentcurrency.setCompnaypaymentcurrencyPK(new CompnaypaymentcurrencyPK());
        }
        if (compnaypaymentcurrency.getCompanypaymentsCollection() == null) {
            compnaypaymentcurrency.setCompanypaymentsCollection(new ArrayList<Companypayments>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = compnaypaymentcurrency.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                compnaypaymentcurrency.setCompany(company);
            }
            Companyemployee companyemployee = compnaypaymentcurrency.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                compnaypaymentcurrency.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = compnaypaymentcurrency.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                compnaypaymentcurrency.setCompanyemployee1(companyemployee1);
            }
            Collection<Companypayments> attachedCompanypaymentsCollection = new ArrayList<Companypayments>();
            for (Companypayments companypaymentsCollectionCompanypaymentsToAttach : compnaypaymentcurrency.getCompanypaymentsCollection()) {
                companypaymentsCollectionCompanypaymentsToAttach = em.getReference(companypaymentsCollectionCompanypaymentsToAttach.getClass(), companypaymentsCollectionCompanypaymentsToAttach.getCompanypaymentsPK());
                attachedCompanypaymentsCollection.add(companypaymentsCollectionCompanypaymentsToAttach);
            }
            compnaypaymentcurrency.setCompanypaymentsCollection(attachedCompanypaymentsCollection);
            em.persist(compnaypaymentcurrency);
            if (company != null) {
                company.getCompnaypaymentcurrencyCollection().add(compnaypaymentcurrency);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompnaypaymentcurrencyCollection().add(compnaypaymentcurrency);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompnaypaymentcurrencyCollection().add(compnaypaymentcurrency);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Companypayments companypaymentsCollectionCompanypayments : compnaypaymentcurrency.getCompanypaymentsCollection()) {
                Compnaypaymentcurrency oldCompnaypaymentcurrencyOfCompanypaymentsCollectionCompanypayments = companypaymentsCollectionCompanypayments.getCompnaypaymentcurrency();
                companypaymentsCollectionCompanypayments.setCompnaypaymentcurrency(compnaypaymentcurrency);
                companypaymentsCollectionCompanypayments = em.merge(companypaymentsCollectionCompanypayments);
                if (oldCompnaypaymentcurrencyOfCompanypaymentsCollectionCompanypayments != null) {
                    oldCompnaypaymentcurrencyOfCompanypaymentsCollectionCompanypayments.getCompanypaymentsCollection().remove(companypaymentsCollectionCompanypayments);
                    oldCompnaypaymentcurrencyOfCompanypaymentsCollectionCompanypayments = em.merge(oldCompnaypaymentcurrencyOfCompanypaymentsCollectionCompanypayments);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompnaypaymentcurrency(compnaypaymentcurrency.getCompnaypaymentcurrencyPK()) != null) {
                throw new PreexistingEntityException("Compnaypaymentcurrency " + compnaypaymentcurrency + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compnaypaymentcurrency compnaypaymentcurrency) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Compnaypaymentcurrency persistentCompnaypaymentcurrency = em.find(Compnaypaymentcurrency.class, compnaypaymentcurrency.getCompnaypaymentcurrencyPK());
            Company companyOld = persistentCompnaypaymentcurrency.getCompany();
            Company companyNew = compnaypaymentcurrency.getCompany();
            Companyemployee companyemployeeOld = persistentCompnaypaymentcurrency.getCompanyemployee();
            Companyemployee companyemployeeNew = compnaypaymentcurrency.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompnaypaymentcurrency.getCompanyemployee1();
            Companyemployee companyemployee1New = compnaypaymentcurrency.getCompanyemployee1();
            Collection<Companypayments> companypaymentsCollectionOld = persistentCompnaypaymentcurrency.getCompanypaymentsCollection();
            Collection<Companypayments> companypaymentsCollectionNew = compnaypaymentcurrency.getCompanypaymentsCollection();
            List<String> illegalOrphanMessages = null;
            for (Companypayments companypaymentsCollectionOldCompanypayments : companypaymentsCollectionOld) {
                if (!companypaymentsCollectionNew.contains(companypaymentsCollectionOldCompanypayments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companypayments " + companypaymentsCollectionOldCompanypayments + " since its compnaypaymentcurrency field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                compnaypaymentcurrency.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                compnaypaymentcurrency.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                compnaypaymentcurrency.setCompanyemployee1(companyemployee1New);
            }
            Collection<Companypayments> attachedCompanypaymentsCollectionNew = new ArrayList<Companypayments>();
            for (Companypayments companypaymentsCollectionNewCompanypaymentsToAttach : companypaymentsCollectionNew) {
                companypaymentsCollectionNewCompanypaymentsToAttach = em.getReference(companypaymentsCollectionNewCompanypaymentsToAttach.getClass(), companypaymentsCollectionNewCompanypaymentsToAttach.getCompanypaymentsPK());
                attachedCompanypaymentsCollectionNew.add(companypaymentsCollectionNewCompanypaymentsToAttach);
            }
            companypaymentsCollectionNew = attachedCompanypaymentsCollectionNew;
            compnaypaymentcurrency.setCompanypaymentsCollection(companypaymentsCollectionNew);
            compnaypaymentcurrency = em.merge(compnaypaymentcurrency);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompnaypaymentcurrencyCollection().remove(compnaypaymentcurrency);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompnaypaymentcurrencyCollection().add(compnaypaymentcurrency);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompnaypaymentcurrencyCollection().remove(compnaypaymentcurrency);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompnaypaymentcurrencyCollection().add(compnaypaymentcurrency);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompnaypaymentcurrencyCollection().remove(compnaypaymentcurrency);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompnaypaymentcurrencyCollection().add(compnaypaymentcurrency);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Companypayments companypaymentsCollectionNewCompanypayments : companypaymentsCollectionNew) {
                if (!companypaymentsCollectionOld.contains(companypaymentsCollectionNewCompanypayments)) {
                    Compnaypaymentcurrency oldCompnaypaymentcurrencyOfCompanypaymentsCollectionNewCompanypayments = companypaymentsCollectionNewCompanypayments.getCompnaypaymentcurrency();
                    companypaymentsCollectionNewCompanypayments.setCompnaypaymentcurrency(compnaypaymentcurrency);
                    companypaymentsCollectionNewCompanypayments = em.merge(companypaymentsCollectionNewCompanypayments);
                    if (oldCompnaypaymentcurrencyOfCompanypaymentsCollectionNewCompanypayments != null && !oldCompnaypaymentcurrencyOfCompanypaymentsCollectionNewCompanypayments.equals(compnaypaymentcurrency)) {
                        oldCompnaypaymentcurrencyOfCompanypaymentsCollectionNewCompanypayments.getCompanypaymentsCollection().remove(companypaymentsCollectionNewCompanypayments);
                        oldCompnaypaymentcurrencyOfCompanypaymentsCollectionNewCompanypayments = em.merge(oldCompnaypaymentcurrencyOfCompanypaymentsCollectionNewCompanypayments);
                    }
                }
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
                CompnaypaymentcurrencyPK id = compnaypaymentcurrency.getCompnaypaymentcurrencyPK();
                if (findCompnaypaymentcurrency(id) == null) {
                    throw new NonexistentEntityException("The compnaypaymentcurrency with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompnaypaymentcurrencyPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Compnaypaymentcurrency compnaypaymentcurrency;
            try {
                compnaypaymentcurrency = em.getReference(Compnaypaymentcurrency.class, id);
                compnaypaymentcurrency.getCompnaypaymentcurrencyPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compnaypaymentcurrency with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Companypayments> companypaymentsCollectionOrphanCheck = compnaypaymentcurrency.getCompanypaymentsCollection();
            for (Companypayments companypaymentsCollectionOrphanCheckCompanypayments : companypaymentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compnaypaymentcurrency (" + compnaypaymentcurrency + ") cannot be destroyed since the Companypayments " + companypaymentsCollectionOrphanCheckCompanypayments + " in its companypaymentsCollection field has a non-nullable compnaypaymentcurrency field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = compnaypaymentcurrency.getCompany();
            if (company != null) {
                company.getCompnaypaymentcurrencyCollection().remove(compnaypaymentcurrency);
                company = em.merge(company);
            }
            Companyemployee companyemployee = compnaypaymentcurrency.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompnaypaymentcurrencyCollection().remove(compnaypaymentcurrency);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = compnaypaymentcurrency.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompnaypaymentcurrencyCollection().remove(compnaypaymentcurrency);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(compnaypaymentcurrency);
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

    public List<Compnaypaymentcurrency> findCompnaypaymentcurrencyEntities() {
        return findCompnaypaymentcurrencyEntities(true, -1, -1);
    }

    public List<Compnaypaymentcurrency> findCompnaypaymentcurrencyEntities(int maxResults, int firstResult) {
        return findCompnaypaymentcurrencyEntities(false, maxResults, firstResult);
    }

    private List<Compnaypaymentcurrency> findCompnaypaymentcurrencyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compnaypaymentcurrency.class));
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

    public Compnaypaymentcurrency findCompnaypaymentcurrency(CompnaypaymentcurrencyPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compnaypaymentcurrency.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompnaypaymentcurrencyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compnaypaymentcurrency> rt = cq.from(Compnaypaymentcurrency.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
