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
import com.sivotek.crm.persistent.dao.entities.Companyaddresstype;
import com.sivotek.crm.persistent.dao.entities.CompanyaddresstypePK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Companyemployeeaddress;
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
public class CompanyaddresstypeJpaController implements Serializable {

    public CompanyaddresstypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CompanyaddresstypeJpaController(){
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

    public void create(Companyaddresstype companyaddresstype) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyaddresstype.getCompanyaddresstypePK() == null) {
            companyaddresstype.setCompanyaddresstypePK(new CompanyaddresstypePK());
        }
        if (companyaddresstype.getCompanyemployeeaddressCollection() == null) {
            companyaddresstype.setCompanyemployeeaddressCollection(new ArrayList<Companyemployeeaddress>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companyaddresstype.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companyaddresstype.setCompany(company);
            }
            Companyemployee companyemployee = companyaddresstype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companyaddresstype.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companyaddresstype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companyaddresstype.setCompanyemployee1(companyemployee1);
            }
            Collection<Companyemployeeaddress> attachedCompanyemployeeaddressCollection = new ArrayList<Companyemployeeaddress>();
            for (Companyemployeeaddress companyemployeeaddressCollectionCompanyemployeeaddressToAttach : companyaddresstype.getCompanyemployeeaddressCollection()) {
                companyemployeeaddressCollectionCompanyemployeeaddressToAttach = em.getReference(companyemployeeaddressCollectionCompanyemployeeaddressToAttach.getClass(), companyemployeeaddressCollectionCompanyemployeeaddressToAttach.getCompanyemployeeaddressPK());
                attachedCompanyemployeeaddressCollection.add(companyemployeeaddressCollectionCompanyemployeeaddressToAttach);
            }
            companyaddresstype.setCompanyemployeeaddressCollection(attachedCompanyemployeeaddressCollection);
            em.persist(companyaddresstype);
            if (company != null) {
                company.getCompanyaddresstypeCollection().add(companyaddresstype);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanyaddresstypeCollection().add(companyaddresstype);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanyaddresstypeCollection().add(companyaddresstype);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Companyemployeeaddress companyemployeeaddressCollectionCompanyemployeeaddress : companyaddresstype.getCompanyemployeeaddressCollection()) {
                Companyaddresstype oldCompanyaddresstypeOfCompanyemployeeaddressCollectionCompanyemployeeaddress = companyemployeeaddressCollectionCompanyemployeeaddress.getCompanyaddresstype();
                companyemployeeaddressCollectionCompanyemployeeaddress.setCompanyaddresstype(companyaddresstype);
                companyemployeeaddressCollectionCompanyemployeeaddress = em.merge(companyemployeeaddressCollectionCompanyemployeeaddress);
                if (oldCompanyaddresstypeOfCompanyemployeeaddressCollectionCompanyemployeeaddress != null) {
                    oldCompanyaddresstypeOfCompanyemployeeaddressCollectionCompanyemployeeaddress.getCompanyemployeeaddressCollection().remove(companyemployeeaddressCollectionCompanyemployeeaddress);
                    oldCompanyaddresstypeOfCompanyemployeeaddressCollectionCompanyemployeeaddress = em.merge(oldCompanyaddresstypeOfCompanyemployeeaddressCollectionCompanyemployeeaddress);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyaddresstype(companyaddresstype.getCompanyaddresstypePK()) != null) {
                throw new PreexistingEntityException("Companyaddresstype " + companyaddresstype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companyaddresstype companyaddresstype) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyaddresstype persistentCompanyaddresstype = em.find(Companyaddresstype.class, companyaddresstype.getCompanyaddresstypePK());
            Company companyOld = persistentCompanyaddresstype.getCompany();
            Company companyNew = companyaddresstype.getCompany();
            Companyemployee companyemployeeOld = persistentCompanyaddresstype.getCompanyemployee();
            Companyemployee companyemployeeNew = companyaddresstype.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanyaddresstype.getCompanyemployee1();
            Companyemployee companyemployee1New = companyaddresstype.getCompanyemployee1();
            Collection<Companyemployeeaddress> companyemployeeaddressCollectionOld = persistentCompanyaddresstype.getCompanyemployeeaddressCollection();
            Collection<Companyemployeeaddress> companyemployeeaddressCollectionNew = companyaddresstype.getCompanyemployeeaddressCollection();
            List<String> illegalOrphanMessages = null;
            for (Companyemployeeaddress companyemployeeaddressCollectionOldCompanyemployeeaddress : companyemployeeaddressCollectionOld) {
                if (!companyemployeeaddressCollectionNew.contains(companyemployeeaddressCollectionOldCompanyemployeeaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyemployeeaddress " + companyemployeeaddressCollectionOldCompanyemployeeaddress + " since its companyaddresstype field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companyaddresstype.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companyaddresstype.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companyaddresstype.setCompanyemployee1(companyemployee1New);
            }
            Collection<Companyemployeeaddress> attachedCompanyemployeeaddressCollectionNew = new ArrayList<Companyemployeeaddress>();
            for (Companyemployeeaddress companyemployeeaddressCollectionNewCompanyemployeeaddressToAttach : companyemployeeaddressCollectionNew) {
                companyemployeeaddressCollectionNewCompanyemployeeaddressToAttach = em.getReference(companyemployeeaddressCollectionNewCompanyemployeeaddressToAttach.getClass(), companyemployeeaddressCollectionNewCompanyemployeeaddressToAttach.getCompanyemployeeaddressPK());
                attachedCompanyemployeeaddressCollectionNew.add(companyemployeeaddressCollectionNewCompanyemployeeaddressToAttach);
            }
            companyemployeeaddressCollectionNew = attachedCompanyemployeeaddressCollectionNew;
            companyaddresstype.setCompanyemployeeaddressCollection(companyemployeeaddressCollectionNew);
            companyaddresstype = em.merge(companyaddresstype);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanyaddresstypeCollection().remove(companyaddresstype);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanyaddresstypeCollection().add(companyaddresstype);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanyaddresstypeCollection().remove(companyaddresstype);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanyaddresstypeCollection().add(companyaddresstype);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanyaddresstypeCollection().remove(companyaddresstype);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanyaddresstypeCollection().add(companyaddresstype);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Companyemployeeaddress companyemployeeaddressCollectionNewCompanyemployeeaddress : companyemployeeaddressCollectionNew) {
                if (!companyemployeeaddressCollectionOld.contains(companyemployeeaddressCollectionNewCompanyemployeeaddress)) {
                    Companyaddresstype oldCompanyaddresstypeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress = companyemployeeaddressCollectionNewCompanyemployeeaddress.getCompanyaddresstype();
                    companyemployeeaddressCollectionNewCompanyemployeeaddress.setCompanyaddresstype(companyaddresstype);
                    companyemployeeaddressCollectionNewCompanyemployeeaddress = em.merge(companyemployeeaddressCollectionNewCompanyemployeeaddress);
                    if (oldCompanyaddresstypeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress != null && !oldCompanyaddresstypeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress.equals(companyaddresstype)) {
                        oldCompanyaddresstypeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress.getCompanyemployeeaddressCollection().remove(companyemployeeaddressCollectionNewCompanyemployeeaddress);
                        oldCompanyaddresstypeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress = em.merge(oldCompanyaddresstypeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress);
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
                CompanyaddresstypePK id = companyaddresstype.getCompanyaddresstypePK();
                if (findCompanyaddresstype(id) == null) {
                    throw new NonexistentEntityException("The companyaddresstype with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyaddresstypePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyaddresstype companyaddresstype;
            try {
                companyaddresstype = em.getReference(Companyaddresstype.class, id);
                companyaddresstype.getCompanyaddresstypePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyaddresstype with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Companyemployeeaddress> companyemployeeaddressCollectionOrphanCheck = companyaddresstype.getCompanyemployeeaddressCollection();
            for (Companyemployeeaddress companyemployeeaddressCollectionOrphanCheckCompanyemployeeaddress : companyemployeeaddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyaddresstype (" + companyaddresstype + ") cannot be destroyed since the Companyemployeeaddress " + companyemployeeaddressCollectionOrphanCheckCompanyemployeeaddress + " in its companyemployeeaddressCollection field has a non-nullable companyaddresstype field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companyaddresstype.getCompany();
            if (company != null) {
                company.getCompanyaddresstypeCollection().remove(companyaddresstype);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companyaddresstype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanyaddresstypeCollection().remove(companyaddresstype);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companyaddresstype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanyaddresstypeCollection().remove(companyaddresstype);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companyaddresstype);
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

    public List<Companyaddresstype> findCompanyaddresstypeEntities() {
        return findCompanyaddresstypeEntities(true, -1, -1);
    }

    public List<Companyaddresstype> findCompanyaddresstypeEntities(int maxResults, int firstResult) {
        return findCompanyaddresstypeEntities(false, maxResults, firstResult);
    }

    private List<Companyaddresstype> findCompanyaddresstypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companyaddresstype.class));
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

    public Companyaddresstype findCompanyaddresstype(CompanyaddresstypePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companyaddresstype.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyaddresstypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companyaddresstype> rt = cq.from(Companyaddresstype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
