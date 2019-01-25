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
import com.sivotek.crm.persistent.dao.entities.Companycontacts;
import com.sivotek.crm.persistent.dao.entities.CompanycontactsPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Companycontactsaddress;
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
public class CompanycontactsJpaController implements Serializable {

    public CompanycontactsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanycontactsJpaController(){
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

    public void create(Companycontacts companycontacts) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companycontacts.getCompanycontactsPK() == null) {
            companycontacts.setCompanycontactsPK(new CompanycontactsPK());
        }
        if (companycontacts.getCompanycontactsaddressCollection() == null) {
            companycontacts.setCompanycontactsaddressCollection(new ArrayList<Companycontactsaddress>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companycontacts.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companycontacts.setCompany(company);
            }
            Companyemployee companyemployee = companycontacts.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companycontacts.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companycontacts.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companycontacts.setCompanyemployee1(companyemployee1);
            }
            Collection<Companycontactsaddress> attachedCompanycontactsaddressCollection = new ArrayList<Companycontactsaddress>();
            for (Companycontactsaddress companycontactsaddressCollectionCompanycontactsaddressToAttach : companycontacts.getCompanycontactsaddressCollection()) {
                companycontactsaddressCollectionCompanycontactsaddressToAttach = em.getReference(companycontactsaddressCollectionCompanycontactsaddressToAttach.getClass(), companycontactsaddressCollectionCompanycontactsaddressToAttach.getCompanycontactsaddressPK());
                attachedCompanycontactsaddressCollection.add(companycontactsaddressCollectionCompanycontactsaddressToAttach);
            }
            companycontacts.setCompanycontactsaddressCollection(attachedCompanycontactsaddressCollection);
            em.persist(companycontacts);
            if (company != null) {
                company.getCompanycontactsCollection().add(companycontacts);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanycontactsCollection().add(companycontacts);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanycontactsCollection().add(companycontacts);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Companycontactsaddress companycontactsaddressCollectionCompanycontactsaddress : companycontacts.getCompanycontactsaddressCollection()) {
                Companycontacts oldCompanycontactsOfCompanycontactsaddressCollectionCompanycontactsaddress = companycontactsaddressCollectionCompanycontactsaddress.getCompanycontacts();
                companycontactsaddressCollectionCompanycontactsaddress.setCompanycontacts(companycontacts);
                companycontactsaddressCollectionCompanycontactsaddress = em.merge(companycontactsaddressCollectionCompanycontactsaddress);
                if (oldCompanycontactsOfCompanycontactsaddressCollectionCompanycontactsaddress != null) {
                    oldCompanycontactsOfCompanycontactsaddressCollectionCompanycontactsaddress.getCompanycontactsaddressCollection().remove(companycontactsaddressCollectionCompanycontactsaddress);
                    oldCompanycontactsOfCompanycontactsaddressCollectionCompanycontactsaddress = em.merge(oldCompanycontactsOfCompanycontactsaddressCollectionCompanycontactsaddress);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanycontacts(companycontacts.getCompanycontactsPK()) != null) {
                throw new PreexistingEntityException("Companycontacts " + companycontacts + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companycontacts companycontacts) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companycontacts persistentCompanycontacts = em.find(Companycontacts.class, companycontacts.getCompanycontactsPK());
            Company companyOld = persistentCompanycontacts.getCompany();
            Company companyNew = companycontacts.getCompany();
            Companyemployee companyemployeeOld = persistentCompanycontacts.getCompanyemployee();
            Companyemployee companyemployeeNew = companycontacts.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanycontacts.getCompanyemployee1();
            Companyemployee companyemployee1New = companycontacts.getCompanyemployee1();
            Collection<Companycontactsaddress> companycontactsaddressCollectionOld = persistentCompanycontacts.getCompanycontactsaddressCollection();
            Collection<Companycontactsaddress> companycontactsaddressCollectionNew = companycontacts.getCompanycontactsaddressCollection();
            List<String> illegalOrphanMessages = null;
            for (Companycontactsaddress companycontactsaddressCollectionOldCompanycontactsaddress : companycontactsaddressCollectionOld) {
                if (!companycontactsaddressCollectionNew.contains(companycontactsaddressCollectionOldCompanycontactsaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companycontactsaddress " + companycontactsaddressCollectionOldCompanycontactsaddress + " since its companycontacts field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companycontacts.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companycontacts.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companycontacts.setCompanyemployee1(companyemployee1New);
            }
            Collection<Companycontactsaddress> attachedCompanycontactsaddressCollectionNew = new ArrayList<Companycontactsaddress>();
            for (Companycontactsaddress companycontactsaddressCollectionNewCompanycontactsaddressToAttach : companycontactsaddressCollectionNew) {
                companycontactsaddressCollectionNewCompanycontactsaddressToAttach = em.getReference(companycontactsaddressCollectionNewCompanycontactsaddressToAttach.getClass(), companycontactsaddressCollectionNewCompanycontactsaddressToAttach.getCompanycontactsaddressPK());
                attachedCompanycontactsaddressCollectionNew.add(companycontactsaddressCollectionNewCompanycontactsaddressToAttach);
            }
            companycontactsaddressCollectionNew = attachedCompanycontactsaddressCollectionNew;
            companycontacts.setCompanycontactsaddressCollection(companycontactsaddressCollectionNew);
            companycontacts = em.merge(companycontacts);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanycontactsCollection().remove(companycontacts);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanycontactsCollection().add(companycontacts);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanycontactsCollection().remove(companycontacts);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanycontactsCollection().add(companycontacts);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanycontactsCollection().remove(companycontacts);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanycontactsCollection().add(companycontacts);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Companycontactsaddress companycontactsaddressCollectionNewCompanycontactsaddress : companycontactsaddressCollectionNew) {
                if (!companycontactsaddressCollectionOld.contains(companycontactsaddressCollectionNewCompanycontactsaddress)) {
                    Companycontacts oldCompanycontactsOfCompanycontactsaddressCollectionNewCompanycontactsaddress = companycontactsaddressCollectionNewCompanycontactsaddress.getCompanycontacts();
                    companycontactsaddressCollectionNewCompanycontactsaddress.setCompanycontacts(companycontacts);
                    companycontactsaddressCollectionNewCompanycontactsaddress = em.merge(companycontactsaddressCollectionNewCompanycontactsaddress);
                    if (oldCompanycontactsOfCompanycontactsaddressCollectionNewCompanycontactsaddress != null && !oldCompanycontactsOfCompanycontactsaddressCollectionNewCompanycontactsaddress.equals(companycontacts)) {
                        oldCompanycontactsOfCompanycontactsaddressCollectionNewCompanycontactsaddress.getCompanycontactsaddressCollection().remove(companycontactsaddressCollectionNewCompanycontactsaddress);
                        oldCompanycontactsOfCompanycontactsaddressCollectionNewCompanycontactsaddress = em.merge(oldCompanycontactsOfCompanycontactsaddressCollectionNewCompanycontactsaddress);
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
                CompanycontactsPK id = companycontacts.getCompanycontactsPK();
                if (findCompanycontacts(id) == null) {
                    throw new NonexistentEntityException("The companycontacts with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanycontactsPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companycontacts companycontacts;
            try {
                companycontacts = em.getReference(Companycontacts.class, id);
                companycontacts.getCompanycontactsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companycontacts with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Companycontactsaddress> companycontactsaddressCollectionOrphanCheck = companycontacts.getCompanycontactsaddressCollection();
            for (Companycontactsaddress companycontactsaddressCollectionOrphanCheckCompanycontactsaddress : companycontactsaddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companycontacts (" + companycontacts + ") cannot be destroyed since the Companycontactsaddress " + companycontactsaddressCollectionOrphanCheckCompanycontactsaddress + " in its companycontactsaddressCollection field has a non-nullable companycontacts field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companycontacts.getCompany();
            if (company != null) {
                company.getCompanycontactsCollection().remove(companycontacts);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companycontacts.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanycontactsCollection().remove(companycontacts);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companycontacts.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanycontactsCollection().remove(companycontacts);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companycontacts);
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

    public List<Companycontacts> findCompanycontactsEntities() {
        return findCompanycontactsEntities(true, -1, -1);
    }

    public List<Companycontacts> findCompanycontactsEntities(int maxResults, int firstResult) {
        return findCompanycontactsEntities(false, maxResults, firstResult);
    }

    private List<Companycontacts> findCompanycontactsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companycontacts.class));
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

    public Companycontacts findCompanycontacts(CompanycontactsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companycontacts.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanycontactsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companycontacts> rt = cq.from(Companycontacts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
