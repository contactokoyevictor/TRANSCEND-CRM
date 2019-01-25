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
import com.sivotek.crm.persistent.dao.entities.Crmlabor;
import com.sivotek.crm.persistent.dao.entities.Crmlabortype;
import com.sivotek.crm.persistent.dao.entities.CrmlabortypePK;
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
public class CrmlabortypeJpaController implements Serializable {

    public CrmlabortypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public CrmlabortypeJpaController(){
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


    public void create(Crmlabortype crmlabortype) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmlabortype.getCrmlabortypePK() == null) {
            crmlabortype.setCrmlabortypePK(new CrmlabortypePK());
        }
        if (crmlabortype.getCrmlaborCollection() == null) {
            crmlabortype.setCrmlaborCollection(new ArrayList<Crmlabor>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmlabortype.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmlabortype.setCompany(company);
            }
            Companyemployee companyemployee = crmlabortype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmlabortype.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmlabortype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmlabortype.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmlabor> attachedCrmlaborCollection = new ArrayList<Crmlabor>();
            for (Crmlabor crmlaborCollectionCrmlaborToAttach : crmlabortype.getCrmlaborCollection()) {
                crmlaborCollectionCrmlaborToAttach = em.getReference(crmlaborCollectionCrmlaborToAttach.getClass(), crmlaborCollectionCrmlaborToAttach.getCrmlaborPK());
                attachedCrmlaborCollection.add(crmlaborCollectionCrmlaborToAttach);
            }
            crmlabortype.setCrmlaborCollection(attachedCrmlaborCollection);
            em.persist(crmlabortype);
            if (company != null) {
                company.getCrmlabortypeCollection().add(crmlabortype);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCrmlabortypeCollection().add(crmlabortype);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmlabortypeCollection().add(crmlabortype);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmlabor crmlaborCollectionCrmlabor : crmlabortype.getCrmlaborCollection()) {
                Crmlabortype oldCrmlabortypeOfCrmlaborCollectionCrmlabor = crmlaborCollectionCrmlabor.getCrmlabortype();
                crmlaborCollectionCrmlabor.setCrmlabortype(crmlabortype);
                crmlaborCollectionCrmlabor = em.merge(crmlaborCollectionCrmlabor);
                if (oldCrmlabortypeOfCrmlaborCollectionCrmlabor != null) {
                    oldCrmlabortypeOfCrmlaborCollectionCrmlabor.getCrmlaborCollection().remove(crmlaborCollectionCrmlabor);
                    oldCrmlabortypeOfCrmlaborCollectionCrmlabor = em.merge(oldCrmlabortypeOfCrmlaborCollectionCrmlabor);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmlabortype(crmlabortype.getCrmlabortypePK()) != null) {
                throw new PreexistingEntityException("Crmlabortype " + crmlabortype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmlabortype crmlabortype) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlabortype persistentCrmlabortype = em.find(Crmlabortype.class, crmlabortype.getCrmlabortypePK());
            Company companyOld = persistentCrmlabortype.getCompany();
            Company companyNew = crmlabortype.getCompany();
            Companyemployee companyemployeeOld = persistentCrmlabortype.getCompanyemployee();
            Companyemployee companyemployeeNew = crmlabortype.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmlabortype.getCompanyemployee1();
            Companyemployee companyemployee1New = crmlabortype.getCompanyemployee1();
            Collection<Crmlabor> crmlaborCollectionOld = persistentCrmlabortype.getCrmlaborCollection();
            Collection<Crmlabor> crmlaborCollectionNew = crmlabortype.getCrmlaborCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmlabor crmlaborCollectionOldCrmlabor : crmlaborCollectionOld) {
                if (!crmlaborCollectionNew.contains(crmlaborCollectionOldCrmlabor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmlabor " + crmlaborCollectionOldCrmlabor + " since its crmlabortype field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmlabortype.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmlabortype.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmlabortype.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmlabor> attachedCrmlaborCollectionNew = new ArrayList<Crmlabor>();
            for (Crmlabor crmlaborCollectionNewCrmlaborToAttach : crmlaborCollectionNew) {
                crmlaborCollectionNewCrmlaborToAttach = em.getReference(crmlaborCollectionNewCrmlaborToAttach.getClass(), crmlaborCollectionNewCrmlaborToAttach.getCrmlaborPK());
                attachedCrmlaborCollectionNew.add(crmlaborCollectionNewCrmlaborToAttach);
            }
            crmlaborCollectionNew = attachedCrmlaborCollectionNew;
            crmlabortype.setCrmlaborCollection(crmlaborCollectionNew);
            crmlabortype = em.merge(crmlabortype);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmlabortypeCollection().remove(crmlabortype);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmlabortypeCollection().add(crmlabortype);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmlabortypeCollection().remove(crmlabortype);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmlabortypeCollection().add(crmlabortype);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmlabortypeCollection().remove(crmlabortype);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmlabortypeCollection().add(crmlabortype);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmlabor crmlaborCollectionNewCrmlabor : crmlaborCollectionNew) {
                if (!crmlaborCollectionOld.contains(crmlaborCollectionNewCrmlabor)) {
                    Crmlabortype oldCrmlabortypeOfCrmlaborCollectionNewCrmlabor = crmlaborCollectionNewCrmlabor.getCrmlabortype();
                    crmlaborCollectionNewCrmlabor.setCrmlabortype(crmlabortype);
                    crmlaborCollectionNewCrmlabor = em.merge(crmlaborCollectionNewCrmlabor);
                    if (oldCrmlabortypeOfCrmlaborCollectionNewCrmlabor != null && !oldCrmlabortypeOfCrmlaborCollectionNewCrmlabor.equals(crmlabortype)) {
                        oldCrmlabortypeOfCrmlaborCollectionNewCrmlabor.getCrmlaborCollection().remove(crmlaborCollectionNewCrmlabor);
                        oldCrmlabortypeOfCrmlaborCollectionNewCrmlabor = em.merge(oldCrmlabortypeOfCrmlaborCollectionNewCrmlabor);
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
                CrmlabortypePK id = crmlabortype.getCrmlabortypePK();
                if (findCrmlabortype(id) == null) {
                    throw new NonexistentEntityException("The crmlabortype with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmlabortypePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmlabortype crmlabortype;
            try {
                crmlabortype = em.getReference(Crmlabortype.class, id);
                crmlabortype.getCrmlabortypePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmlabortype with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmlabor> crmlaborCollectionOrphanCheck = crmlabortype.getCrmlaborCollection();
            for (Crmlabor crmlaborCollectionOrphanCheckCrmlabor : crmlaborCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmlabortype (" + crmlabortype + ") cannot be destroyed since the Crmlabor " + crmlaborCollectionOrphanCheckCrmlabor + " in its crmlaborCollection field has a non-nullable crmlabortype field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = crmlabortype.getCompany();
            if (company != null) {
                company.getCrmlabortypeCollection().remove(crmlabortype);
                company = em.merge(company);
            }
            Companyemployee companyemployee = crmlabortype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmlabortypeCollection().remove(crmlabortype);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmlabortype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmlabortypeCollection().remove(crmlabortype);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmlabortype);
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

    public List<Crmlabortype> findCrmlabortypeEntities() {
        return findCrmlabortypeEntities(true, -1, -1);
    }

    public List<Crmlabortype> findCrmlabortypeEntities(int maxResults, int firstResult) {
        return findCrmlabortypeEntities(false, maxResults, firstResult);
    }

    private List<Crmlabortype> findCrmlabortypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmlabortype.class));
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

    public Crmlabortype findCrmlabortype(CrmlabortypePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmlabortype.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmlabortypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmlabortype> rt = cq.from(Crmlabortype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
