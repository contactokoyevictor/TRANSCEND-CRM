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
import com.sivotek.crm.persistent.dao.entities.Companypaymentscheme;
import com.sivotek.crm.persistent.dao.entities.CompanypaymentschemePK;
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
import javax.transaction.UserTransaction;
import javax.persistence.Persistence;
/**
 *
 * @author okoyevictor
 */
public class CompanypaymentschemeJpaController implements Serializable {

    public CompanypaymentschemeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanypaymentschemeJpaController(){
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

    public void create(Companypaymentscheme companypaymentscheme) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companypaymentscheme.getCompanypaymentschemePK() == null) {
            companypaymentscheme.setCompanypaymentschemePK(new CompanypaymentschemePK());
        }
        if (companypaymentscheme.getCompanypaymentsCollection() == null) {
            companypaymentscheme.setCompanypaymentsCollection(new ArrayList<Companypayments>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companypaymentscheme.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companypaymentscheme.setCompany(company);
            }
            Companyemployee companyemployee = companypaymentscheme.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companypaymentscheme.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companypaymentscheme.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companypaymentscheme.setCompanyemployee1(companyemployee1);
            }
            Collection<Companypayments> attachedCompanypaymentsCollection = new ArrayList<Companypayments>();
            for (Companypayments companypaymentsCollectionCompanypaymentsToAttach : companypaymentscheme.getCompanypaymentsCollection()) {
                companypaymentsCollectionCompanypaymentsToAttach = em.getReference(companypaymentsCollectionCompanypaymentsToAttach.getClass(), companypaymentsCollectionCompanypaymentsToAttach.getCompanypaymentsPK());
                attachedCompanypaymentsCollection.add(companypaymentsCollectionCompanypaymentsToAttach);
            }
            companypaymentscheme.setCompanypaymentsCollection(attachedCompanypaymentsCollection);
            em.persist(companypaymentscheme);
            if (company != null) {
                company.getCompanypaymentschemeCollection().add(companypaymentscheme);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanypaymentschemeCollection().add(companypaymentscheme);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanypaymentschemeCollection().add(companypaymentscheme);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Companypayments companypaymentsCollectionCompanypayments : companypaymentscheme.getCompanypaymentsCollection()) {
                Companypaymentscheme oldCompanypaymentschemeOfCompanypaymentsCollectionCompanypayments = companypaymentsCollectionCompanypayments.getCompanypaymentscheme();
                companypaymentsCollectionCompanypayments.setCompanypaymentscheme(companypaymentscheme);
                companypaymentsCollectionCompanypayments = em.merge(companypaymentsCollectionCompanypayments);
                if (oldCompanypaymentschemeOfCompanypaymentsCollectionCompanypayments != null) {
                    oldCompanypaymentschemeOfCompanypaymentsCollectionCompanypayments.getCompanypaymentsCollection().remove(companypaymentsCollectionCompanypayments);
                    oldCompanypaymentschemeOfCompanypaymentsCollectionCompanypayments = em.merge(oldCompanypaymentschemeOfCompanypaymentsCollectionCompanypayments);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanypaymentscheme(companypaymentscheme.getCompanypaymentschemePK()) != null) {
                throw new PreexistingEntityException("Companypaymentscheme " + companypaymentscheme + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companypaymentscheme companypaymentscheme) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companypaymentscheme persistentCompanypaymentscheme = em.find(Companypaymentscheme.class, companypaymentscheme.getCompanypaymentschemePK());
            Company companyOld = persistentCompanypaymentscheme.getCompany();
            Company companyNew = companypaymentscheme.getCompany();
            Companyemployee companyemployeeOld = persistentCompanypaymentscheme.getCompanyemployee();
            Companyemployee companyemployeeNew = companypaymentscheme.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanypaymentscheme.getCompanyemployee1();
            Companyemployee companyemployee1New = companypaymentscheme.getCompanyemployee1();
            Collection<Companypayments> companypaymentsCollectionOld = persistentCompanypaymentscheme.getCompanypaymentsCollection();
            Collection<Companypayments> companypaymentsCollectionNew = companypaymentscheme.getCompanypaymentsCollection();
            List<String> illegalOrphanMessages = null;
            for (Companypayments companypaymentsCollectionOldCompanypayments : companypaymentsCollectionOld) {
                if (!companypaymentsCollectionNew.contains(companypaymentsCollectionOldCompanypayments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companypayments " + companypaymentsCollectionOldCompanypayments + " since its companypaymentscheme field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companypaymentscheme.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companypaymentscheme.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companypaymentscheme.setCompanyemployee1(companyemployee1New);
            }
            Collection<Companypayments> attachedCompanypaymentsCollectionNew = new ArrayList<Companypayments>();
            for (Companypayments companypaymentsCollectionNewCompanypaymentsToAttach : companypaymentsCollectionNew) {
                companypaymentsCollectionNewCompanypaymentsToAttach = em.getReference(companypaymentsCollectionNewCompanypaymentsToAttach.getClass(), companypaymentsCollectionNewCompanypaymentsToAttach.getCompanypaymentsPK());
                attachedCompanypaymentsCollectionNew.add(companypaymentsCollectionNewCompanypaymentsToAttach);
            }
            companypaymentsCollectionNew = attachedCompanypaymentsCollectionNew;
            companypaymentscheme.setCompanypaymentsCollection(companypaymentsCollectionNew);
            companypaymentscheme = em.merge(companypaymentscheme);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanypaymentschemeCollection().remove(companypaymentscheme);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanypaymentschemeCollection().add(companypaymentscheme);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanypaymentschemeCollection().remove(companypaymentscheme);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanypaymentschemeCollection().add(companypaymentscheme);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanypaymentschemeCollection().remove(companypaymentscheme);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanypaymentschemeCollection().add(companypaymentscheme);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Companypayments companypaymentsCollectionNewCompanypayments : companypaymentsCollectionNew) {
                if (!companypaymentsCollectionOld.contains(companypaymentsCollectionNewCompanypayments)) {
                    Companypaymentscheme oldCompanypaymentschemeOfCompanypaymentsCollectionNewCompanypayments = companypaymentsCollectionNewCompanypayments.getCompanypaymentscheme();
                    companypaymentsCollectionNewCompanypayments.setCompanypaymentscheme(companypaymentscheme);
                    companypaymentsCollectionNewCompanypayments = em.merge(companypaymentsCollectionNewCompanypayments);
                    if (oldCompanypaymentschemeOfCompanypaymentsCollectionNewCompanypayments != null && !oldCompanypaymentschemeOfCompanypaymentsCollectionNewCompanypayments.equals(companypaymentscheme)) {
                        oldCompanypaymentschemeOfCompanypaymentsCollectionNewCompanypayments.getCompanypaymentsCollection().remove(companypaymentsCollectionNewCompanypayments);
                        oldCompanypaymentschemeOfCompanypaymentsCollectionNewCompanypayments = em.merge(oldCompanypaymentschemeOfCompanypaymentsCollectionNewCompanypayments);
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
                CompanypaymentschemePK id = companypaymentscheme.getCompanypaymentschemePK();
                if (findCompanypaymentscheme(id) == null) {
                    throw new NonexistentEntityException("The companypaymentscheme with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanypaymentschemePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companypaymentscheme companypaymentscheme;
            try {
                companypaymentscheme = em.getReference(Companypaymentscheme.class, id);
                companypaymentscheme.getCompanypaymentschemePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companypaymentscheme with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Companypayments> companypaymentsCollectionOrphanCheck = companypaymentscheme.getCompanypaymentsCollection();
            for (Companypayments companypaymentsCollectionOrphanCheckCompanypayments : companypaymentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companypaymentscheme (" + companypaymentscheme + ") cannot be destroyed since the Companypayments " + companypaymentsCollectionOrphanCheckCompanypayments + " in its companypaymentsCollection field has a non-nullable companypaymentscheme field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companypaymentscheme.getCompany();
            if (company != null) {
                company.getCompanypaymentschemeCollection().remove(companypaymentscheme);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companypaymentscheme.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanypaymentschemeCollection().remove(companypaymentscheme);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companypaymentscheme.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanypaymentschemeCollection().remove(companypaymentscheme);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companypaymentscheme);
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

    public List<Companypaymentscheme> findCompanypaymentschemeEntities() {
        return findCompanypaymentschemeEntities(true, -1, -1);
    }

    public List<Companypaymentscheme> findCompanypaymentschemeEntities(int maxResults, int firstResult) {
        return findCompanypaymentschemeEntities(false, maxResults, firstResult);
    }

    private List<Companypaymentscheme> findCompanypaymentschemeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companypaymentscheme.class));
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

    public Companypaymentscheme findCompanypaymentscheme(CompanypaymentschemePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companypaymentscheme.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanypaymentschemeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companypaymentscheme> rt = cq.from(Companypaymentscheme.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
