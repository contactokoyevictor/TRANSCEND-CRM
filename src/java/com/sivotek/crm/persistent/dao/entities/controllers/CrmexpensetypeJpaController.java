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
import com.sivotek.crm.persistent.dao.entities.Crmexpense;
import com.sivotek.crm.persistent.dao.entities.Crmexpensetype;
import com.sivotek.crm.persistent.dao.entities.CrmexpensetypePK;
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
public class CrmexpensetypeJpaController implements Serializable {

    public CrmexpensetypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmexpensetypeJpaController(){
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

    public void create(Crmexpensetype crmexpensetype) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmexpensetype.getCrmexpensetypePK() == null) {
            crmexpensetype.setCrmexpensetypePK(new CrmexpensetypePK());
        }
        if (crmexpensetype.getCrmexpenseCollection() == null) {
            crmexpensetype.setCrmexpenseCollection(new ArrayList<Crmexpense>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmexpensetype.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmexpensetype.setCompany(company);
            }
            Companyemployee companyemployee = crmexpensetype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmexpensetype.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmexpensetype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmexpensetype.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmexpense> attachedCrmexpenseCollection = new ArrayList<Crmexpense>();
            for (Crmexpense crmexpenseCollectionCrmexpenseToAttach : crmexpensetype.getCrmexpenseCollection()) {
                crmexpenseCollectionCrmexpenseToAttach = em.getReference(crmexpenseCollectionCrmexpenseToAttach.getClass(), crmexpenseCollectionCrmexpenseToAttach.getCrmexpensePK());
                attachedCrmexpenseCollection.add(crmexpenseCollectionCrmexpenseToAttach);
            }
            crmexpensetype.setCrmexpenseCollection(attachedCrmexpenseCollection);
            em.persist(crmexpensetype);
            if (company != null) {
                company.getCrmexpensetypeCollection().add(crmexpensetype);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCrmexpensetypeCollection().add(crmexpensetype);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmexpensetypeCollection().add(crmexpensetype);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmexpense crmexpenseCollectionCrmexpense : crmexpensetype.getCrmexpenseCollection()) {
                Crmexpensetype oldCrmexpensetypeOfCrmexpenseCollectionCrmexpense = crmexpenseCollectionCrmexpense.getCrmexpensetype();
                crmexpenseCollectionCrmexpense.setCrmexpensetype(crmexpensetype);
                crmexpenseCollectionCrmexpense = em.merge(crmexpenseCollectionCrmexpense);
                if (oldCrmexpensetypeOfCrmexpenseCollectionCrmexpense != null) {
                    oldCrmexpensetypeOfCrmexpenseCollectionCrmexpense.getCrmexpenseCollection().remove(crmexpenseCollectionCrmexpense);
                    oldCrmexpensetypeOfCrmexpenseCollectionCrmexpense = em.merge(oldCrmexpensetypeOfCrmexpenseCollectionCrmexpense);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmexpensetype(crmexpensetype.getCrmexpensetypePK()) != null) {
                throw new PreexistingEntityException("Crmexpensetype " + crmexpensetype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmexpensetype crmexpensetype) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmexpensetype persistentCrmexpensetype = em.find(Crmexpensetype.class, crmexpensetype.getCrmexpensetypePK());
            Company companyOld = persistentCrmexpensetype.getCompany();
            Company companyNew = crmexpensetype.getCompany();
            Companyemployee companyemployeeOld = persistentCrmexpensetype.getCompanyemployee();
            Companyemployee companyemployeeNew = crmexpensetype.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmexpensetype.getCompanyemployee1();
            Companyemployee companyemployee1New = crmexpensetype.getCompanyemployee1();
            Collection<Crmexpense> crmexpenseCollectionOld = persistentCrmexpensetype.getCrmexpenseCollection();
            Collection<Crmexpense> crmexpenseCollectionNew = crmexpensetype.getCrmexpenseCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmexpense crmexpenseCollectionOldCrmexpense : crmexpenseCollectionOld) {
                if (!crmexpenseCollectionNew.contains(crmexpenseCollectionOldCrmexpense)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmexpense " + crmexpenseCollectionOldCrmexpense + " since its crmexpensetype field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmexpensetype.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmexpensetype.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmexpensetype.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmexpense> attachedCrmexpenseCollectionNew = new ArrayList<Crmexpense>();
            for (Crmexpense crmexpenseCollectionNewCrmexpenseToAttach : crmexpenseCollectionNew) {
                crmexpenseCollectionNewCrmexpenseToAttach = em.getReference(crmexpenseCollectionNewCrmexpenseToAttach.getClass(), crmexpenseCollectionNewCrmexpenseToAttach.getCrmexpensePK());
                attachedCrmexpenseCollectionNew.add(crmexpenseCollectionNewCrmexpenseToAttach);
            }
            crmexpenseCollectionNew = attachedCrmexpenseCollectionNew;
            crmexpensetype.setCrmexpenseCollection(crmexpenseCollectionNew);
            crmexpensetype = em.merge(crmexpensetype);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmexpensetypeCollection().remove(crmexpensetype);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmexpensetypeCollection().add(crmexpensetype);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmexpensetypeCollection().remove(crmexpensetype);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmexpensetypeCollection().add(crmexpensetype);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmexpensetypeCollection().remove(crmexpensetype);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmexpensetypeCollection().add(crmexpensetype);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmexpense crmexpenseCollectionNewCrmexpense : crmexpenseCollectionNew) {
                if (!crmexpenseCollectionOld.contains(crmexpenseCollectionNewCrmexpense)) {
                    Crmexpensetype oldCrmexpensetypeOfCrmexpenseCollectionNewCrmexpense = crmexpenseCollectionNewCrmexpense.getCrmexpensetype();
                    crmexpenseCollectionNewCrmexpense.setCrmexpensetype(crmexpensetype);
                    crmexpenseCollectionNewCrmexpense = em.merge(crmexpenseCollectionNewCrmexpense);
                    if (oldCrmexpensetypeOfCrmexpenseCollectionNewCrmexpense != null && !oldCrmexpensetypeOfCrmexpenseCollectionNewCrmexpense.equals(crmexpensetype)) {
                        oldCrmexpensetypeOfCrmexpenseCollectionNewCrmexpense.getCrmexpenseCollection().remove(crmexpenseCollectionNewCrmexpense);
                        oldCrmexpensetypeOfCrmexpenseCollectionNewCrmexpense = em.merge(oldCrmexpensetypeOfCrmexpenseCollectionNewCrmexpense);
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
                CrmexpensetypePK id = crmexpensetype.getCrmexpensetypePK();
                if (findCrmexpensetype(id) == null) {
                    throw new NonexistentEntityException("The crmexpensetype with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmexpensetypePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmexpensetype crmexpensetype;
            try {
                crmexpensetype = em.getReference(Crmexpensetype.class, id);
                crmexpensetype.getCrmexpensetypePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmexpensetype with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmexpense> crmexpenseCollectionOrphanCheck = crmexpensetype.getCrmexpenseCollection();
            for (Crmexpense crmexpenseCollectionOrphanCheckCrmexpense : crmexpenseCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmexpensetype (" + crmexpensetype + ") cannot be destroyed since the Crmexpense " + crmexpenseCollectionOrphanCheckCrmexpense + " in its crmexpenseCollection field has a non-nullable crmexpensetype field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = crmexpensetype.getCompany();
            if (company != null) {
                company.getCrmexpensetypeCollection().remove(crmexpensetype);
                company = em.merge(company);
            }
            Companyemployee companyemployee = crmexpensetype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmexpensetypeCollection().remove(crmexpensetype);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmexpensetype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmexpensetypeCollection().remove(crmexpensetype);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmexpensetype);
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

    public List<Crmexpensetype> findCrmexpensetypeEntities() {
        return findCrmexpensetypeEntities(true, -1, -1);
    }

    public List<Crmexpensetype> findCrmexpensetypeEntities(int maxResults, int firstResult) {
        return findCrmexpensetypeEntities(false, maxResults, firstResult);
    }

    private List<Crmexpensetype> findCrmexpensetypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmexpensetype.class));
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

    public Crmexpensetype findCrmexpensetype(CrmexpensetypePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmexpensetype.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmexpensetypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmexpensetype> rt = cq.from(Crmexpensetype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
