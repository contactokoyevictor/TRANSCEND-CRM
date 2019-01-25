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
import com.sivotek.crm.persistent.dao.entities.Crmschedulerevnttype;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmvisitor;
import com.sivotek.crm.persistent.dao.entities.CrmvisitorPK;
import com.sivotek.crm.persistent.dao.entities.Crmvisitorcontactsaddress;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmvisitorcontacts;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
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
public class CrmvisitorJpaController implements Serializable {

    public CrmvisitorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmvisitorJpaController(){
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

    public void create(Crmvisitor crmvisitor) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmvisitor.getCrmvisitorPK() == null) {
            crmvisitor.setCrmvisitorPK(new CrmvisitorPK());
        }
        if (crmvisitor.getCrmvisitorcontactsaddressCollection() == null) {
            crmvisitor.setCrmvisitorcontactsaddressCollection(new ArrayList<Crmvisitorcontactsaddress>());
        }
        if (crmvisitor.getCrmvisitorcontactsCollection() == null) {
            crmvisitor.setCrmvisitorcontactsCollection(new ArrayList<Crmvisitorcontacts>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmschedulerevnttype crmschedulerevnttype = crmvisitor.getCrmschedulerevnttype();
            if (crmschedulerevnttype != null) {
                crmschedulerevnttype = em.getReference(crmschedulerevnttype.getClass(), crmschedulerevnttype.getCrmschedulerevnttypePK());
                crmvisitor.setCrmschedulerevnttype(crmschedulerevnttype);
            }
            Companyemployee companyemployee = crmvisitor.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmvisitor.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmvisitor.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmvisitor.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmvisitorcontactsaddress> attachedCrmvisitorcontactsaddressCollection = new ArrayList<Crmvisitorcontactsaddress>();
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach : crmvisitor.getCrmvisitorcontactsaddressCollection()) {
                crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach = em.getReference(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach.getClass(), crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach.getCrmvisitorcontactsaddressPK());
                attachedCrmvisitorcontactsaddressCollection.add(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach);
            }
            crmvisitor.setCrmvisitorcontactsaddressCollection(attachedCrmvisitorcontactsaddressCollection);
            Collection<Crmvisitorcontacts> attachedCrmvisitorcontactsCollection = new ArrayList<Crmvisitorcontacts>();
            for (Crmvisitorcontacts crmvisitorcontactsCollectionCrmvisitorcontactsToAttach : crmvisitor.getCrmvisitorcontactsCollection()) {
                crmvisitorcontactsCollectionCrmvisitorcontactsToAttach = em.getReference(crmvisitorcontactsCollectionCrmvisitorcontactsToAttach.getClass(), crmvisitorcontactsCollectionCrmvisitorcontactsToAttach.getCrmvisitorcontactsPK());
                attachedCrmvisitorcontactsCollection.add(crmvisitorcontactsCollectionCrmvisitorcontactsToAttach);
            }
            crmvisitor.setCrmvisitorcontactsCollection(attachedCrmvisitorcontactsCollection);
            em.persist(crmvisitor);
            if (crmschedulerevnttype != null) {
                crmschedulerevnttype.getCrmvisitorCollection().add(crmvisitor);
                crmschedulerevnttype = em.merge(crmschedulerevnttype);
            }
            if (companyemployee != null) {
                companyemployee.getCrmvisitorCollection().add(crmvisitor);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmvisitorCollection().add(crmvisitor);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress : crmvisitor.getCrmvisitorcontactsaddressCollection()) {
                Crmvisitor oldCrmvisitorOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress = crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress.getCrmvisitor();
                crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress.setCrmvisitor(crmvisitor);
                crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress = em.merge(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress);
                if (oldCrmvisitorOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress != null) {
                    oldCrmvisitorOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress);
                    oldCrmvisitorOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress = em.merge(oldCrmvisitorOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress);
                }
            }
            for (Crmvisitorcontacts crmvisitorcontactsCollectionCrmvisitorcontacts : crmvisitor.getCrmvisitorcontactsCollection()) {
                Crmvisitor oldCrmvisitorOfCrmvisitorcontactsCollectionCrmvisitorcontacts = crmvisitorcontactsCollectionCrmvisitorcontacts.getCrmvisitor();
                crmvisitorcontactsCollectionCrmvisitorcontacts.setCrmvisitor(crmvisitor);
                crmvisitorcontactsCollectionCrmvisitorcontacts = em.merge(crmvisitorcontactsCollectionCrmvisitorcontacts);
                if (oldCrmvisitorOfCrmvisitorcontactsCollectionCrmvisitorcontacts != null) {
                    oldCrmvisitorOfCrmvisitorcontactsCollectionCrmvisitorcontacts.getCrmvisitorcontactsCollection().remove(crmvisitorcontactsCollectionCrmvisitorcontacts);
                    oldCrmvisitorOfCrmvisitorcontactsCollectionCrmvisitorcontacts = em.merge(oldCrmvisitorOfCrmvisitorcontactsCollectionCrmvisitorcontacts);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmvisitor(crmvisitor.getCrmvisitorPK()) != null) {
                throw new PreexistingEntityException("Crmvisitor " + crmvisitor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmvisitor crmvisitor) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmvisitor persistentCrmvisitor = em.find(Crmvisitor.class, crmvisitor.getCrmvisitorPK());
            Crmschedulerevnttype crmschedulerevnttypeOld = persistentCrmvisitor.getCrmschedulerevnttype();
            Crmschedulerevnttype crmschedulerevnttypeNew = crmvisitor.getCrmschedulerevnttype();
            Companyemployee companyemployeeOld = persistentCrmvisitor.getCompanyemployee();
            Companyemployee companyemployeeNew = crmvisitor.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmvisitor.getCompanyemployee1();
            Companyemployee companyemployee1New = crmvisitor.getCompanyemployee1();
            Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollectionOld = persistentCrmvisitor.getCrmvisitorcontactsaddressCollection();
            Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollectionNew = crmvisitor.getCrmvisitorcontactsaddressCollection();
            Collection<Crmvisitorcontacts> crmvisitorcontactsCollectionOld = persistentCrmvisitor.getCrmvisitorcontactsCollection();
            Collection<Crmvisitorcontacts> crmvisitorcontactsCollectionNew = crmvisitor.getCrmvisitorcontactsCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionOldCrmvisitorcontactsaddress : crmvisitorcontactsaddressCollectionOld) {
                if (!crmvisitorcontactsaddressCollectionNew.contains(crmvisitorcontactsaddressCollectionOldCrmvisitorcontactsaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmvisitorcontactsaddress " + crmvisitorcontactsaddressCollectionOldCrmvisitorcontactsaddress + " since its crmvisitor field is not nullable.");
                }
            }
            for (Crmvisitorcontacts crmvisitorcontactsCollectionOldCrmvisitorcontacts : crmvisitorcontactsCollectionOld) {
                if (!crmvisitorcontactsCollectionNew.contains(crmvisitorcontactsCollectionOldCrmvisitorcontacts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmvisitorcontacts " + crmvisitorcontactsCollectionOldCrmvisitorcontacts + " since its crmvisitor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (crmschedulerevnttypeNew != null) {
                crmschedulerevnttypeNew = em.getReference(crmschedulerevnttypeNew.getClass(), crmschedulerevnttypeNew.getCrmschedulerevnttypePK());
                crmvisitor.setCrmschedulerevnttype(crmschedulerevnttypeNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmvisitor.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmvisitor.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmvisitorcontactsaddress> attachedCrmvisitorcontactsaddressCollectionNew = new ArrayList<Crmvisitorcontactsaddress>();
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach : crmvisitorcontactsaddressCollectionNew) {
                crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach = em.getReference(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach.getClass(), crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach.getCrmvisitorcontactsaddressPK());
                attachedCrmvisitorcontactsaddressCollectionNew.add(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach);
            }
            crmvisitorcontactsaddressCollectionNew = attachedCrmvisitorcontactsaddressCollectionNew;
            crmvisitor.setCrmvisitorcontactsaddressCollection(crmvisitorcontactsaddressCollectionNew);
            Collection<Crmvisitorcontacts> attachedCrmvisitorcontactsCollectionNew = new ArrayList<Crmvisitorcontacts>();
            for (Crmvisitorcontacts crmvisitorcontactsCollectionNewCrmvisitorcontactsToAttach : crmvisitorcontactsCollectionNew) {
                crmvisitorcontactsCollectionNewCrmvisitorcontactsToAttach = em.getReference(crmvisitorcontactsCollectionNewCrmvisitorcontactsToAttach.getClass(), crmvisitorcontactsCollectionNewCrmvisitorcontactsToAttach.getCrmvisitorcontactsPK());
                attachedCrmvisitorcontactsCollectionNew.add(crmvisitorcontactsCollectionNewCrmvisitorcontactsToAttach);
            }
            crmvisitorcontactsCollectionNew = attachedCrmvisitorcontactsCollectionNew;
            crmvisitor.setCrmvisitorcontactsCollection(crmvisitorcontactsCollectionNew);
            crmvisitor = em.merge(crmvisitor);
            if (crmschedulerevnttypeOld != null && !crmschedulerevnttypeOld.equals(crmschedulerevnttypeNew)) {
                crmschedulerevnttypeOld.getCrmvisitorCollection().remove(crmvisitor);
                crmschedulerevnttypeOld = em.merge(crmschedulerevnttypeOld);
            }
            if (crmschedulerevnttypeNew != null && !crmschedulerevnttypeNew.equals(crmschedulerevnttypeOld)) {
                crmschedulerevnttypeNew.getCrmvisitorCollection().add(crmvisitor);
                crmschedulerevnttypeNew = em.merge(crmschedulerevnttypeNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmvisitorCollection().remove(crmvisitor);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmvisitorCollection().add(crmvisitor);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmvisitorCollection().remove(crmvisitor);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmvisitorCollection().add(crmvisitor);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress : crmvisitorcontactsaddressCollectionNew) {
                if (!crmvisitorcontactsaddressCollectionOld.contains(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress)) {
                    Crmvisitor oldCrmvisitorOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress = crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.getCrmvisitor();
                    crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.setCrmvisitor(crmvisitor);
                    crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress = em.merge(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress);
                    if (oldCrmvisitorOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress != null && !oldCrmvisitorOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.equals(crmvisitor)) {
                        oldCrmvisitorOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress);
                        oldCrmvisitorOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress = em.merge(oldCrmvisitorOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress);
                    }
                }
            }
            for (Crmvisitorcontacts crmvisitorcontactsCollectionNewCrmvisitorcontacts : crmvisitorcontactsCollectionNew) {
                if (!crmvisitorcontactsCollectionOld.contains(crmvisitorcontactsCollectionNewCrmvisitorcontacts)) {
                    Crmvisitor oldCrmvisitorOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts = crmvisitorcontactsCollectionNewCrmvisitorcontacts.getCrmvisitor();
                    crmvisitorcontactsCollectionNewCrmvisitorcontacts.setCrmvisitor(crmvisitor);
                    crmvisitorcontactsCollectionNewCrmvisitorcontacts = em.merge(crmvisitorcontactsCollectionNewCrmvisitorcontacts);
                    if (oldCrmvisitorOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts != null && !oldCrmvisitorOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts.equals(crmvisitor)) {
                        oldCrmvisitorOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts.getCrmvisitorcontactsCollection().remove(crmvisitorcontactsCollectionNewCrmvisitorcontacts);
                        oldCrmvisitorOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts = em.merge(oldCrmvisitorOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts);
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
                CrmvisitorPK id = crmvisitor.getCrmvisitorPK();
                if (findCrmvisitor(id) == null) {
                    throw new NonexistentEntityException("The crmvisitor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmvisitorPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmvisitor crmvisitor;
            try {
                crmvisitor = em.getReference(Crmvisitor.class, id);
                crmvisitor.getCrmvisitorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmvisitor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollectionOrphanCheck = crmvisitor.getCrmvisitorcontactsaddressCollection();
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionOrphanCheckCrmvisitorcontactsaddress : crmvisitorcontactsaddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmvisitor (" + crmvisitor + ") cannot be destroyed since the Crmvisitorcontactsaddress " + crmvisitorcontactsaddressCollectionOrphanCheckCrmvisitorcontactsaddress + " in its crmvisitorcontactsaddressCollection field has a non-nullable crmvisitor field.");
            }
            Collection<Crmvisitorcontacts> crmvisitorcontactsCollectionOrphanCheck = crmvisitor.getCrmvisitorcontactsCollection();
            for (Crmvisitorcontacts crmvisitorcontactsCollectionOrphanCheckCrmvisitorcontacts : crmvisitorcontactsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmvisitor (" + crmvisitor + ") cannot be destroyed since the Crmvisitorcontacts " + crmvisitorcontactsCollectionOrphanCheckCrmvisitorcontacts + " in its crmvisitorcontactsCollection field has a non-nullable crmvisitor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Crmschedulerevnttype crmschedulerevnttype = crmvisitor.getCrmschedulerevnttype();
            if (crmschedulerevnttype != null) {
                crmschedulerevnttype.getCrmvisitorCollection().remove(crmvisitor);
                crmschedulerevnttype = em.merge(crmschedulerevnttype);
            }
            Companyemployee companyemployee = crmvisitor.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmvisitorCollection().remove(crmvisitor);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmvisitor.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmvisitorCollection().remove(crmvisitor);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmvisitor);
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

    public List<Crmvisitor> findCrmvisitorEntities() {
        return findCrmvisitorEntities(true, -1, -1);
    }

    public List<Crmvisitor> findCrmvisitorEntities(int maxResults, int firstResult) {
        return findCrmvisitorEntities(false, maxResults, firstResult);
    }

    private List<Crmvisitor> findCrmvisitorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmvisitor.class));
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

    public Crmvisitor findCrmvisitor(CrmvisitorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmvisitor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmvisitorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmvisitor> rt = cq.from(Crmvisitor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
