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
import com.sivotek.crm.persistent.dao.entities.Crmproject;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmprojecttask;
import com.sivotek.crm.persistent.dao.entities.CrmprojecttaskPK;
import com.sivotek.crm.persistent.dao.entities.Crmprojectteammembers;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketmanagement;
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
public class CrmprojecttaskJpaController implements Serializable {

    public CrmprojecttaskJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmprojecttaskJpaController(){
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

    public void create(Crmprojecttask crmprojecttask) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmprojecttask.getCrmprojecttaskPK() == null) {
            crmprojecttask.setCrmprojecttaskPK(new CrmprojecttaskPK());
        }
        if (crmprojecttask.getCrmprojectteammembersCollection() == null) {
            crmprojecttask.setCrmprojectteammembersCollection(new ArrayList<Crmprojectteammembers>());
        }
        if (crmprojecttask.getCrmprojectticketmanagementCollection() == null) {
            crmprojecttask.setCrmprojectticketmanagementCollection(new ArrayList<Crmprojectticketmanagement>());
        }
        if (crmprojecttask.getCrmprojectticketmanagementCollection1() == null) {
            crmprojecttask.setCrmprojectticketmanagementCollection1(new ArrayList<Crmprojectticketmanagement>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmproject crmproject = crmprojecttask.getCrmproject();
            if (crmproject != null) {
                crmproject = em.getReference(crmproject.getClass(), crmproject.getCrmprojectPK());
                crmprojecttask.setCrmproject(crmproject);
            }
            Companyemployee companyemployee = crmprojecttask.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmprojecttask.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmprojecttask.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmprojecttask.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmprojectteammembers> attachedCrmprojectteammembersCollection = new ArrayList<Crmprojectteammembers>();
            for (Crmprojectteammembers crmprojectteammembersCollectionCrmprojectteammembersToAttach : crmprojecttask.getCrmprojectteammembersCollection()) {
                crmprojectteammembersCollectionCrmprojectteammembersToAttach = em.getReference(crmprojectteammembersCollectionCrmprojectteammembersToAttach.getClass(), crmprojectteammembersCollectionCrmprojectteammembersToAttach.getCrmprojectteammembersPK());
                attachedCrmprojectteammembersCollection.add(crmprojectteammembersCollectionCrmprojectteammembersToAttach);
            }
            crmprojecttask.setCrmprojectteammembersCollection(attachedCrmprojectteammembersCollection);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollection = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionCrmprojectticketmanagementToAttach : crmprojecttask.getCrmprojectticketmanagementCollection()) {
                crmprojectticketmanagementCollectionCrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollectionCrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollectionCrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollection.add(crmprojectticketmanagementCollectionCrmprojectticketmanagementToAttach);
            }
            crmprojecttask.setCrmprojectticketmanagementCollection(attachedCrmprojectticketmanagementCollection);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollection1 = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1CrmprojectticketmanagementToAttach : crmprojecttask.getCrmprojectticketmanagementCollection1()) {
                crmprojectticketmanagementCollection1CrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollection1CrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollection1CrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollection1.add(crmprojectticketmanagementCollection1CrmprojectticketmanagementToAttach);
            }
            crmprojecttask.setCrmprojectticketmanagementCollection1(attachedCrmprojectticketmanagementCollection1);
            em.persist(crmprojecttask);
            if (crmproject != null) {
                crmproject.getCrmprojecttaskCollection().add(crmprojecttask);
                crmproject = em.merge(crmproject);
            }
            if (companyemployee != null) {
                companyemployee.getCrmprojecttaskCollection().add(crmprojecttask);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojecttaskCollection().add(crmprojecttask);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmprojectteammembers crmprojectteammembersCollectionCrmprojectteammembers : crmprojecttask.getCrmprojectteammembersCollection()) {
                Crmprojecttask oldCrmprojecttaskOfCrmprojectteammembersCollectionCrmprojectteammembers = crmprojectteammembersCollectionCrmprojectteammembers.getCrmprojecttask();
                crmprojectteammembersCollectionCrmprojectteammembers.setCrmprojecttask(crmprojecttask);
                crmprojectteammembersCollectionCrmprojectteammembers = em.merge(crmprojectteammembersCollectionCrmprojectteammembers);
                if (oldCrmprojecttaskOfCrmprojectteammembersCollectionCrmprojectteammembers != null) {
                    oldCrmprojecttaskOfCrmprojectteammembersCollectionCrmprojectteammembers.getCrmprojectteammembersCollection().remove(crmprojectteammembersCollectionCrmprojectteammembers);
                    oldCrmprojecttaskOfCrmprojectteammembersCollectionCrmprojectteammembers = em.merge(oldCrmprojecttaskOfCrmprojectteammembersCollectionCrmprojectteammembers);
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionCrmprojectticketmanagement : crmprojecttask.getCrmprojectticketmanagementCollection()) {
                Crmprojecttask oldCrmprojecttaskOfCrmprojectticketmanagementCollectionCrmprojectticketmanagement = crmprojectticketmanagementCollectionCrmprojectticketmanagement.getCrmprojecttask();
                crmprojectticketmanagementCollectionCrmprojectticketmanagement.setCrmprojecttask(crmprojecttask);
                crmprojectticketmanagementCollectionCrmprojectticketmanagement = em.merge(crmprojectticketmanagementCollectionCrmprojectticketmanagement);
                if (oldCrmprojecttaskOfCrmprojectticketmanagementCollectionCrmprojectticketmanagement != null) {
                    oldCrmprojecttaskOfCrmprojectticketmanagementCollectionCrmprojectticketmanagement.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagementCollectionCrmprojectticketmanagement);
                    oldCrmprojecttaskOfCrmprojectticketmanagementCollectionCrmprojectticketmanagement = em.merge(oldCrmprojecttaskOfCrmprojectticketmanagementCollectionCrmprojectticketmanagement);
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1Crmprojectticketmanagement : crmprojecttask.getCrmprojectticketmanagementCollection1()) {
                Crmprojecttask oldCrmprojecttask1OfCrmprojectticketmanagementCollection1Crmprojectticketmanagement = crmprojectticketmanagementCollection1Crmprojectticketmanagement.getCrmprojecttask1();
                crmprojectticketmanagementCollection1Crmprojectticketmanagement.setCrmprojecttask1(crmprojecttask);
                crmprojectticketmanagementCollection1Crmprojectticketmanagement = em.merge(crmprojectticketmanagementCollection1Crmprojectticketmanagement);
                if (oldCrmprojecttask1OfCrmprojectticketmanagementCollection1Crmprojectticketmanagement != null) {
                    oldCrmprojecttask1OfCrmprojectticketmanagementCollection1Crmprojectticketmanagement.getCrmprojectticketmanagementCollection1().remove(crmprojectticketmanagementCollection1Crmprojectticketmanagement);
                    oldCrmprojecttask1OfCrmprojectticketmanagementCollection1Crmprojectticketmanagement = em.merge(oldCrmprojecttask1OfCrmprojectticketmanagementCollection1Crmprojectticketmanagement);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmprojecttask(crmprojecttask.getCrmprojecttaskPK()) != null) {
                throw new PreexistingEntityException("Crmprojecttask " + crmprojecttask + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmprojecttask crmprojecttask) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojecttask persistentCrmprojecttask = em.find(Crmprojecttask.class, crmprojecttask.getCrmprojecttaskPK());
            Crmproject crmprojectOld = persistentCrmprojecttask.getCrmproject();
            Crmproject crmprojectNew = crmprojecttask.getCrmproject();
            Companyemployee companyemployeeOld = persistentCrmprojecttask.getCompanyemployee();
            Companyemployee companyemployeeNew = crmprojecttask.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmprojecttask.getCompanyemployee1();
            Companyemployee companyemployee1New = crmprojecttask.getCompanyemployee1();
            Collection<Crmprojectteammembers> crmprojectteammembersCollectionOld = persistentCrmprojecttask.getCrmprojectteammembersCollection();
            Collection<Crmprojectteammembers> crmprojectteammembersCollectionNew = crmprojecttask.getCrmprojectteammembersCollection();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollectionOld = persistentCrmprojecttask.getCrmprojectticketmanagementCollection();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollectionNew = crmprojecttask.getCrmprojectticketmanagementCollection();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection1Old = persistentCrmprojecttask.getCrmprojectticketmanagementCollection1();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection1New = crmprojecttask.getCrmprojectticketmanagementCollection1();
            List<String> illegalOrphanMessages = null;
            for (Crmprojectteammembers crmprojectteammembersCollectionOldCrmprojectteammembers : crmprojectteammembersCollectionOld) {
                if (!crmprojectteammembersCollectionNew.contains(crmprojectteammembersCollectionOldCrmprojectteammembers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectteammembers " + crmprojectteammembersCollectionOldCrmprojectteammembers + " since its crmprojecttask field is not nullable.");
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionOldCrmprojectticketmanagement : crmprojectticketmanagementCollectionOld) {
                if (!crmprojectticketmanagementCollectionNew.contains(crmprojectticketmanagementCollectionOldCrmprojectticketmanagement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectticketmanagement " + crmprojectticketmanagementCollectionOldCrmprojectticketmanagement + " since its crmprojecttask field is not nullable.");
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1OldCrmprojectticketmanagement : crmprojectticketmanagementCollection1Old) {
                if (!crmprojectticketmanagementCollection1New.contains(crmprojectticketmanagementCollection1OldCrmprojectticketmanagement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectticketmanagement " + crmprojectticketmanagementCollection1OldCrmprojectticketmanagement + " since its crmprojecttask1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (crmprojectNew != null) {
                crmprojectNew = em.getReference(crmprojectNew.getClass(), crmprojectNew.getCrmprojectPK());
                crmprojecttask.setCrmproject(crmprojectNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmprojecttask.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmprojecttask.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmprojectteammembers> attachedCrmprojectteammembersCollectionNew = new ArrayList<Crmprojectteammembers>();
            for (Crmprojectteammembers crmprojectteammembersCollectionNewCrmprojectteammembersToAttach : crmprojectteammembersCollectionNew) {
                crmprojectteammembersCollectionNewCrmprojectteammembersToAttach = em.getReference(crmprojectteammembersCollectionNewCrmprojectteammembersToAttach.getClass(), crmprojectteammembersCollectionNewCrmprojectteammembersToAttach.getCrmprojectteammembersPK());
                attachedCrmprojectteammembersCollectionNew.add(crmprojectteammembersCollectionNewCrmprojectteammembersToAttach);
            }
            crmprojectteammembersCollectionNew = attachedCrmprojectteammembersCollectionNew;
            crmprojecttask.setCrmprojectteammembersCollection(crmprojectteammembersCollectionNew);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollectionNew = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionNewCrmprojectticketmanagementToAttach : crmprojectticketmanagementCollectionNew) {
                crmprojectticketmanagementCollectionNewCrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollectionNewCrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollectionNewCrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollectionNew.add(crmprojectticketmanagementCollectionNewCrmprojectticketmanagementToAttach);
            }
            crmprojectticketmanagementCollectionNew = attachedCrmprojectticketmanagementCollectionNew;
            crmprojecttask.setCrmprojectticketmanagementCollection(crmprojectticketmanagementCollectionNew);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollection1New = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1NewCrmprojectticketmanagementToAttach : crmprojectticketmanagementCollection1New) {
                crmprojectticketmanagementCollection1NewCrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollection1NewCrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollection1NewCrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollection1New.add(crmprojectticketmanagementCollection1NewCrmprojectticketmanagementToAttach);
            }
            crmprojectticketmanagementCollection1New = attachedCrmprojectticketmanagementCollection1New;
            crmprojecttask.setCrmprojectticketmanagementCollection1(crmprojectticketmanagementCollection1New);
            crmprojecttask = em.merge(crmprojecttask);
            if (crmprojectOld != null && !crmprojectOld.equals(crmprojectNew)) {
                crmprojectOld.getCrmprojecttaskCollection().remove(crmprojecttask);
                crmprojectOld = em.merge(crmprojectOld);
            }
            if (crmprojectNew != null && !crmprojectNew.equals(crmprojectOld)) {
                crmprojectNew.getCrmprojecttaskCollection().add(crmprojecttask);
                crmprojectNew = em.merge(crmprojectNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmprojecttaskCollection().remove(crmprojecttask);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmprojecttaskCollection().add(crmprojecttask);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmprojecttaskCollection().remove(crmprojecttask);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmprojecttaskCollection().add(crmprojecttask);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmprojectteammembers crmprojectteammembersCollectionNewCrmprojectteammembers : crmprojectteammembersCollectionNew) {
                if (!crmprojectteammembersCollectionOld.contains(crmprojectteammembersCollectionNewCrmprojectteammembers)) {
                    Crmprojecttask oldCrmprojecttaskOfCrmprojectteammembersCollectionNewCrmprojectteammembers = crmprojectteammembersCollectionNewCrmprojectteammembers.getCrmprojecttask();
                    crmprojectteammembersCollectionNewCrmprojectteammembers.setCrmprojecttask(crmprojecttask);
                    crmprojectteammembersCollectionNewCrmprojectteammembers = em.merge(crmprojectteammembersCollectionNewCrmprojectteammembers);
                    if (oldCrmprojecttaskOfCrmprojectteammembersCollectionNewCrmprojectteammembers != null && !oldCrmprojecttaskOfCrmprojectteammembersCollectionNewCrmprojectteammembers.equals(crmprojecttask)) {
                        oldCrmprojecttaskOfCrmprojectteammembersCollectionNewCrmprojectteammembers.getCrmprojectteammembersCollection().remove(crmprojectteammembersCollectionNewCrmprojectteammembers);
                        oldCrmprojecttaskOfCrmprojectteammembersCollectionNewCrmprojectteammembers = em.merge(oldCrmprojecttaskOfCrmprojectteammembersCollectionNewCrmprojectteammembers);
                    }
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionNewCrmprojectticketmanagement : crmprojectticketmanagementCollectionNew) {
                if (!crmprojectticketmanagementCollectionOld.contains(crmprojectticketmanagementCollectionNewCrmprojectticketmanagement)) {
                    Crmprojecttask oldCrmprojecttaskOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement = crmprojectticketmanagementCollectionNewCrmprojectticketmanagement.getCrmprojecttask();
                    crmprojectticketmanagementCollectionNewCrmprojectticketmanagement.setCrmprojecttask(crmprojecttask);
                    crmprojectticketmanagementCollectionNewCrmprojectticketmanagement = em.merge(crmprojectticketmanagementCollectionNewCrmprojectticketmanagement);
                    if (oldCrmprojecttaskOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement != null && !oldCrmprojecttaskOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement.equals(crmprojecttask)) {
                        oldCrmprojecttaskOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagementCollectionNewCrmprojectticketmanagement);
                        oldCrmprojecttaskOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement = em.merge(oldCrmprojecttaskOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement);
                    }
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1NewCrmprojectticketmanagement : crmprojectticketmanagementCollection1New) {
                if (!crmprojectticketmanagementCollection1Old.contains(crmprojectticketmanagementCollection1NewCrmprojectticketmanagement)) {
                    Crmprojecttask oldCrmprojecttask1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement = crmprojectticketmanagementCollection1NewCrmprojectticketmanagement.getCrmprojecttask1();
                    crmprojectticketmanagementCollection1NewCrmprojectticketmanagement.setCrmprojecttask1(crmprojecttask);
                    crmprojectticketmanagementCollection1NewCrmprojectticketmanagement = em.merge(crmprojectticketmanagementCollection1NewCrmprojectticketmanagement);
                    if (oldCrmprojecttask1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement != null && !oldCrmprojecttask1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement.equals(crmprojecttask)) {
                        oldCrmprojecttask1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement.getCrmprojectticketmanagementCollection1().remove(crmprojectticketmanagementCollection1NewCrmprojectticketmanagement);
                        oldCrmprojecttask1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement = em.merge(oldCrmprojecttask1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement);
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
                CrmprojecttaskPK id = crmprojecttask.getCrmprojecttaskPK();
                if (findCrmprojecttask(id) == null) {
                    throw new NonexistentEntityException("The crmprojecttask with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmprojecttaskPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojecttask crmprojecttask;
            try {
                crmprojecttask = em.getReference(Crmprojecttask.class, id);
                crmprojecttask.getCrmprojecttaskPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmprojecttask with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmprojectteammembers> crmprojectteammembersCollectionOrphanCheck = crmprojecttask.getCrmprojectteammembersCollection();
            for (Crmprojectteammembers crmprojectteammembersCollectionOrphanCheckCrmprojectteammembers : crmprojectteammembersCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmprojecttask (" + crmprojecttask + ") cannot be destroyed since the Crmprojectteammembers " + crmprojectteammembersCollectionOrphanCheckCrmprojectteammembers + " in its crmprojectteammembersCollection field has a non-nullable crmprojecttask field.");
            }
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollectionOrphanCheck = crmprojecttask.getCrmprojectticketmanagementCollection();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionOrphanCheckCrmprojectticketmanagement : crmprojectticketmanagementCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmprojecttask (" + crmprojecttask + ") cannot be destroyed since the Crmprojectticketmanagement " + crmprojectticketmanagementCollectionOrphanCheckCrmprojectticketmanagement + " in its crmprojectticketmanagementCollection field has a non-nullable crmprojecttask field.");
            }
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection1OrphanCheck = crmprojecttask.getCrmprojectticketmanagementCollection1();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1OrphanCheckCrmprojectticketmanagement : crmprojectticketmanagementCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmprojecttask (" + crmprojecttask + ") cannot be destroyed since the Crmprojectticketmanagement " + crmprojectticketmanagementCollection1OrphanCheckCrmprojectticketmanagement + " in its crmprojectticketmanagementCollection1 field has a non-nullable crmprojecttask1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Crmproject crmproject = crmprojecttask.getCrmproject();
            if (crmproject != null) {
                crmproject.getCrmprojecttaskCollection().remove(crmprojecttask);
                crmproject = em.merge(crmproject);
            }
            Companyemployee companyemployee = crmprojecttask.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmprojecttaskCollection().remove(crmprojecttask);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmprojecttask.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojecttaskCollection().remove(crmprojecttask);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmprojecttask);
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

    public List<Crmprojecttask> findCrmprojecttaskEntities() {
        return findCrmprojecttaskEntities(true, -1, -1);
    }

    public List<Crmprojecttask> findCrmprojecttaskEntities(int maxResults, int firstResult) {
        return findCrmprojecttaskEntities(false, maxResults, firstResult);
    }

    private List<Crmprojecttask> findCrmprojecttaskEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmprojecttask.class));
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

    public Crmprojecttask findCrmprojecttask(CrmprojecttaskPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmprojecttask.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmprojecttaskCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmprojecttask> rt = cq.from(Crmprojecttask.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
