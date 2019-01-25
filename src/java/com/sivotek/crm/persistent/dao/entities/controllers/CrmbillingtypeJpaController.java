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
import com.sivotek.crm.persistent.dao.entities.Crmbillingtype;
import com.sivotek.crm.persistent.dao.entities.CrmbillingtypePK;
import com.sivotek.crm.persistent.dao.entities.Crmworkorderresolution;
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
public class CrmbillingtypeJpaController implements Serializable {

    public CrmbillingtypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmbillingtypeJpaController(){
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

    public void create(Crmbillingtype crmbillingtype) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmbillingtype.getCrmbillingtypePK() == null) {
            crmbillingtype.setCrmbillingtypePK(new CrmbillingtypePK());
        }
        if (crmbillingtype.getCrmworkorderresolutionCollection() == null) {
            crmbillingtype.setCrmworkorderresolutionCollection(new ArrayList<Crmworkorderresolution>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmbillingtype.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmbillingtype.setCompany(company);
            }
            Companyemployee companyemployee = crmbillingtype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmbillingtype.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmbillingtype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmbillingtype.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmworkorderresolution> attachedCrmworkorderresolutionCollection = new ArrayList<Crmworkorderresolution>();
            for (Crmworkorderresolution crmworkorderresolutionCollectionCrmworkorderresolutionToAttach : crmbillingtype.getCrmworkorderresolutionCollection()) {
                crmworkorderresolutionCollectionCrmworkorderresolutionToAttach = em.getReference(crmworkorderresolutionCollectionCrmworkorderresolutionToAttach.getClass(), crmworkorderresolutionCollectionCrmworkorderresolutionToAttach.getCrmworkorderresolutionPK());
                attachedCrmworkorderresolutionCollection.add(crmworkorderresolutionCollectionCrmworkorderresolutionToAttach);
            }
            crmbillingtype.setCrmworkorderresolutionCollection(attachedCrmworkorderresolutionCollection);
            em.persist(crmbillingtype);
            if (company != null) {
                company.getCrmbillingtypeCollection().add(crmbillingtype);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCrmbillingtypeCollection().add(crmbillingtype);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmbillingtypeCollection().add(crmbillingtype);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmworkorderresolution crmworkorderresolutionCollectionCrmworkorderresolution : crmbillingtype.getCrmworkorderresolutionCollection()) {
                Crmbillingtype oldCrmbillingtypeOfCrmworkorderresolutionCollectionCrmworkorderresolution = crmworkorderresolutionCollectionCrmworkorderresolution.getCrmbillingtype();
                crmworkorderresolutionCollectionCrmworkorderresolution.setCrmbillingtype(crmbillingtype);
                crmworkorderresolutionCollectionCrmworkorderresolution = em.merge(crmworkorderresolutionCollectionCrmworkorderresolution);
                if (oldCrmbillingtypeOfCrmworkorderresolutionCollectionCrmworkorderresolution != null) {
                    oldCrmbillingtypeOfCrmworkorderresolutionCollectionCrmworkorderresolution.getCrmworkorderresolutionCollection().remove(crmworkorderresolutionCollectionCrmworkorderresolution);
                    oldCrmbillingtypeOfCrmworkorderresolutionCollectionCrmworkorderresolution = em.merge(oldCrmbillingtypeOfCrmworkorderresolutionCollectionCrmworkorderresolution);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmbillingtype(crmbillingtype.getCrmbillingtypePK()) != null) {
                throw new PreexistingEntityException("Crmbillingtype " + crmbillingtype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmbillingtype crmbillingtype) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmbillingtype persistentCrmbillingtype = em.find(Crmbillingtype.class, crmbillingtype.getCrmbillingtypePK());
            Company companyOld = persistentCrmbillingtype.getCompany();
            Company companyNew = crmbillingtype.getCompany();
            Companyemployee companyemployeeOld = persistentCrmbillingtype.getCompanyemployee();
            Companyemployee companyemployeeNew = crmbillingtype.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmbillingtype.getCompanyemployee1();
            Companyemployee companyemployee1New = crmbillingtype.getCompanyemployee1();
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionOld = persistentCrmbillingtype.getCrmworkorderresolutionCollection();
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionNew = crmbillingtype.getCrmworkorderresolutionCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmworkorderresolution crmworkorderresolutionCollectionOldCrmworkorderresolution : crmworkorderresolutionCollectionOld) {
                if (!crmworkorderresolutionCollectionNew.contains(crmworkorderresolutionCollectionOldCrmworkorderresolution)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorderresolution " + crmworkorderresolutionCollectionOldCrmworkorderresolution + " since its crmbillingtype field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmbillingtype.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmbillingtype.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmbillingtype.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmworkorderresolution> attachedCrmworkorderresolutionCollectionNew = new ArrayList<Crmworkorderresolution>();
            for (Crmworkorderresolution crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach : crmworkorderresolutionCollectionNew) {
                crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach = em.getReference(crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach.getClass(), crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach.getCrmworkorderresolutionPK());
                attachedCrmworkorderresolutionCollectionNew.add(crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach);
            }
            crmworkorderresolutionCollectionNew = attachedCrmworkorderresolutionCollectionNew;
            crmbillingtype.setCrmworkorderresolutionCollection(crmworkorderresolutionCollectionNew);
            crmbillingtype = em.merge(crmbillingtype);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmbillingtypeCollection().remove(crmbillingtype);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmbillingtypeCollection().add(crmbillingtype);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmbillingtypeCollection().remove(crmbillingtype);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmbillingtypeCollection().add(crmbillingtype);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmbillingtypeCollection().remove(crmbillingtype);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmbillingtypeCollection().add(crmbillingtype);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmworkorderresolution crmworkorderresolutionCollectionNewCrmworkorderresolution : crmworkorderresolutionCollectionNew) {
                if (!crmworkorderresolutionCollectionOld.contains(crmworkorderresolutionCollectionNewCrmworkorderresolution)) {
                    Crmbillingtype oldCrmbillingtypeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution = crmworkorderresolutionCollectionNewCrmworkorderresolution.getCrmbillingtype();
                    crmworkorderresolutionCollectionNewCrmworkorderresolution.setCrmbillingtype(crmbillingtype);
                    crmworkorderresolutionCollectionNewCrmworkorderresolution = em.merge(crmworkorderresolutionCollectionNewCrmworkorderresolution);
                    if (oldCrmbillingtypeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution != null && !oldCrmbillingtypeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution.equals(crmbillingtype)) {
                        oldCrmbillingtypeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution.getCrmworkorderresolutionCollection().remove(crmworkorderresolutionCollectionNewCrmworkorderresolution);
                        oldCrmbillingtypeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution = em.merge(oldCrmbillingtypeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution);
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
                CrmbillingtypePK id = crmbillingtype.getCrmbillingtypePK();
                if (findCrmbillingtype(id) == null) {
                    throw new NonexistentEntityException("The crmbillingtype with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmbillingtypePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmbillingtype crmbillingtype;
            try {
                crmbillingtype = em.getReference(Crmbillingtype.class, id);
                crmbillingtype.getCrmbillingtypePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmbillingtype with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionOrphanCheck = crmbillingtype.getCrmworkorderresolutionCollection();
            for (Crmworkorderresolution crmworkorderresolutionCollectionOrphanCheckCrmworkorderresolution : crmworkorderresolutionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmbillingtype (" + crmbillingtype + ") cannot be destroyed since the Crmworkorderresolution " + crmworkorderresolutionCollectionOrphanCheckCrmworkorderresolution + " in its crmworkorderresolutionCollection field has a non-nullable crmbillingtype field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = crmbillingtype.getCompany();
            if (company != null) {
                company.getCrmbillingtypeCollection().remove(crmbillingtype);
                company = em.merge(company);
            }
            Companyemployee companyemployee = crmbillingtype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmbillingtypeCollection().remove(crmbillingtype);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmbillingtype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmbillingtypeCollection().remove(crmbillingtype);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmbillingtype);
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

    public List<Crmbillingtype> findCrmbillingtypeEntities() {
        return findCrmbillingtypeEntities(true, -1, -1);
    }

    public List<Crmbillingtype> findCrmbillingtypeEntities(int maxResults, int firstResult) {
        return findCrmbillingtypeEntities(false, maxResults, firstResult);
    }

    private List<Crmbillingtype> findCrmbillingtypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmbillingtype.class));
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

    public Crmbillingtype findCrmbillingtype(CrmbillingtypePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmbillingtype.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmbillingtypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmbillingtype> rt = cq.from(Crmbillingtype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
