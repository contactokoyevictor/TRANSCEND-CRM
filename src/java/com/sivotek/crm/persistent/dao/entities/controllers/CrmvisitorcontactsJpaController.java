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
import com.sivotek.crm.persistent.dao.entities.Crmvisitor;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmvisitorcontacts;
import com.sivotek.crm.persistent.dao.entities.CrmvisitorcontactsPK;
import com.sivotek.crm.persistent.dao.entities.Crmvisitorcontactsaddress;
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
public class CrmvisitorcontactsJpaController implements Serializable {

    public CrmvisitorcontactsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmvisitorcontactsJpaController(){
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


    public void create(Crmvisitorcontacts crmvisitorcontacts) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmvisitorcontacts.getCrmvisitorcontactsPK() == null) {
            crmvisitorcontacts.setCrmvisitorcontactsPK(new CrmvisitorcontactsPK());
        }
        if (crmvisitorcontacts.getCrmvisitorcontactsaddressCollection() == null) {
            crmvisitorcontacts.setCrmvisitorcontactsaddressCollection(new ArrayList<Crmvisitorcontactsaddress>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmvisitor crmvisitor = crmvisitorcontacts.getCrmvisitor();
            if (crmvisitor != null) {
                crmvisitor = em.getReference(crmvisitor.getClass(), crmvisitor.getCrmvisitorPK());
                crmvisitorcontacts.setCrmvisitor(crmvisitor);
            }
            Companyemployee companyemployee = crmvisitorcontacts.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmvisitorcontacts.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmvisitorcontacts.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmvisitorcontacts.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmvisitorcontactsaddress> attachedCrmvisitorcontactsaddressCollection = new ArrayList<Crmvisitorcontactsaddress>();
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach : crmvisitorcontacts.getCrmvisitorcontactsaddressCollection()) {
                crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach = em.getReference(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach.getClass(), crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach.getCrmvisitorcontactsaddressPK());
                attachedCrmvisitorcontactsaddressCollection.add(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach);
            }
            crmvisitorcontacts.setCrmvisitorcontactsaddressCollection(attachedCrmvisitorcontactsaddressCollection);
            em.persist(crmvisitorcontacts);
            if (crmvisitor != null) {
                crmvisitor.getCrmvisitorcontactsCollection().add(crmvisitorcontacts);
                crmvisitor = em.merge(crmvisitor);
            }
            if (companyemployee != null) {
                companyemployee.getCrmvisitorcontactsCollection().add(crmvisitorcontacts);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmvisitorcontactsCollection().add(crmvisitorcontacts);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress : crmvisitorcontacts.getCrmvisitorcontactsaddressCollection()) {
                Crmvisitorcontacts oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress = crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress.getCrmvisitorcontacts();
                crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress.setCrmvisitorcontacts(crmvisitorcontacts);
                crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress = em.merge(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress);
                if (oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress != null) {
                    oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress);
                    oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress = em.merge(oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmvisitorcontacts(crmvisitorcontacts.getCrmvisitorcontactsPK()) != null) {
                throw new PreexistingEntityException("Crmvisitorcontacts " + crmvisitorcontacts + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmvisitorcontacts crmvisitorcontacts) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmvisitorcontacts persistentCrmvisitorcontacts = em.find(Crmvisitorcontacts.class, crmvisitorcontacts.getCrmvisitorcontactsPK());
            Crmvisitor crmvisitorOld = persistentCrmvisitorcontacts.getCrmvisitor();
            Crmvisitor crmvisitorNew = crmvisitorcontacts.getCrmvisitor();
            Companyemployee companyemployeeOld = persistentCrmvisitorcontacts.getCompanyemployee();
            Companyemployee companyemployeeNew = crmvisitorcontacts.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmvisitorcontacts.getCompanyemployee1();
            Companyemployee companyemployee1New = crmvisitorcontacts.getCompanyemployee1();
            Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollectionOld = persistentCrmvisitorcontacts.getCrmvisitorcontactsaddressCollection();
            Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollectionNew = crmvisitorcontacts.getCrmvisitorcontactsaddressCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionOldCrmvisitorcontactsaddress : crmvisitorcontactsaddressCollectionOld) {
                if (!crmvisitorcontactsaddressCollectionNew.contains(crmvisitorcontactsaddressCollectionOldCrmvisitorcontactsaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmvisitorcontactsaddress " + crmvisitorcontactsaddressCollectionOldCrmvisitorcontactsaddress + " since its crmvisitorcontacts field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (crmvisitorNew != null) {
                crmvisitorNew = em.getReference(crmvisitorNew.getClass(), crmvisitorNew.getCrmvisitorPK());
                crmvisitorcontacts.setCrmvisitor(crmvisitorNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmvisitorcontacts.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmvisitorcontacts.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmvisitorcontactsaddress> attachedCrmvisitorcontactsaddressCollectionNew = new ArrayList<Crmvisitorcontactsaddress>();
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach : crmvisitorcontactsaddressCollectionNew) {
                crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach = em.getReference(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach.getClass(), crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach.getCrmvisitorcontactsaddressPK());
                attachedCrmvisitorcontactsaddressCollectionNew.add(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach);
            }
            crmvisitorcontactsaddressCollectionNew = attachedCrmvisitorcontactsaddressCollectionNew;
            crmvisitorcontacts.setCrmvisitorcontactsaddressCollection(crmvisitorcontactsaddressCollectionNew);
            crmvisitorcontacts = em.merge(crmvisitorcontacts);
            if (crmvisitorOld != null && !crmvisitorOld.equals(crmvisitorNew)) {
                crmvisitorOld.getCrmvisitorcontactsCollection().remove(crmvisitorcontacts);
                crmvisitorOld = em.merge(crmvisitorOld);
            }
            if (crmvisitorNew != null && !crmvisitorNew.equals(crmvisitorOld)) {
                crmvisitorNew.getCrmvisitorcontactsCollection().add(crmvisitorcontacts);
                crmvisitorNew = em.merge(crmvisitorNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmvisitorcontactsCollection().remove(crmvisitorcontacts);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmvisitorcontactsCollection().add(crmvisitorcontacts);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmvisitorcontactsCollection().remove(crmvisitorcontacts);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmvisitorcontactsCollection().add(crmvisitorcontacts);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress : crmvisitorcontactsaddressCollectionNew) {
                if (!crmvisitorcontactsaddressCollectionOld.contains(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress)) {
                    Crmvisitorcontacts oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress = crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.getCrmvisitorcontacts();
                    crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.setCrmvisitorcontacts(crmvisitorcontacts);
                    crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress = em.merge(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress);
                    if (oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress != null && !oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.equals(crmvisitorcontacts)) {
                        oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress);
                        oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress = em.merge(oldCrmvisitorcontactsOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress);
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
                CrmvisitorcontactsPK id = crmvisitorcontacts.getCrmvisitorcontactsPK();
                if (findCrmvisitorcontacts(id) == null) {
                    throw new NonexistentEntityException("The crmvisitorcontacts with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmvisitorcontactsPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmvisitorcontacts crmvisitorcontacts;
            try {
                crmvisitorcontacts = em.getReference(Crmvisitorcontacts.class, id);
                crmvisitorcontacts.getCrmvisitorcontactsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmvisitorcontacts with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollectionOrphanCheck = crmvisitorcontacts.getCrmvisitorcontactsaddressCollection();
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionOrphanCheckCrmvisitorcontactsaddress : crmvisitorcontactsaddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmvisitorcontacts (" + crmvisitorcontacts + ") cannot be destroyed since the Crmvisitorcontactsaddress " + crmvisitorcontactsaddressCollectionOrphanCheckCrmvisitorcontactsaddress + " in its crmvisitorcontactsaddressCollection field has a non-nullable crmvisitorcontacts field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Crmvisitor crmvisitor = crmvisitorcontacts.getCrmvisitor();
            if (crmvisitor != null) {
                crmvisitor.getCrmvisitorcontactsCollection().remove(crmvisitorcontacts);
                crmvisitor = em.merge(crmvisitor);
            }
            Companyemployee companyemployee = crmvisitorcontacts.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmvisitorcontactsCollection().remove(crmvisitorcontacts);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmvisitorcontacts.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmvisitorcontactsCollection().remove(crmvisitorcontacts);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmvisitorcontacts);
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

    public List<Crmvisitorcontacts> findCrmvisitorcontactsEntities() {
        return findCrmvisitorcontactsEntities(true, -1, -1);
    }

    public List<Crmvisitorcontacts> findCrmvisitorcontactsEntities(int maxResults, int firstResult) {
        return findCrmvisitorcontactsEntities(false, maxResults, firstResult);
    }

    private List<Crmvisitorcontacts> findCrmvisitorcontactsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmvisitorcontacts.class));
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

    public Crmvisitorcontacts findCrmvisitorcontacts(CrmvisitorcontactsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmvisitorcontacts.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmvisitorcontactsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmvisitorcontacts> rt = cq.from(Crmvisitorcontacts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
