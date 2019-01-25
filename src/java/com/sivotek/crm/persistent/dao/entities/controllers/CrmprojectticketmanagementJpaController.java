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
import com.sivotek.crm.persistent.dao.entities.Crmprojecttask;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketmanagement;
import com.sivotek.crm.persistent.dao.entities.CrmprojectticketmanagementPK;
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketnotification;
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
import javax.transaction.UserTransaction;
import javax.persistence.Persistence;
/**
 *
 * @author okoyevictor
 */
public class CrmprojectticketmanagementJpaController implements Serializable {

    public CrmprojectticketmanagementJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmprojectticketmanagementJpaController(){
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

    public void create(Crmprojectticketmanagement crmprojectticketmanagement) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmprojectticketmanagement.getCrmprojectticketmanagementPK() == null) {
            crmprojectticketmanagement.setCrmprojectticketmanagementPK(new CrmprojectticketmanagementPK());
        }
        if (crmprojectticketmanagement.getCrmworkorderCollection() == null) {
            crmprojectticketmanagement.setCrmworkorderCollection(new ArrayList<Crmworkorder>());
        }
        if (crmprojectticketmanagement.getCrmprojectticketnotificationCollection() == null) {
            crmprojectticketmanagement.setCrmprojectticketnotificationCollection(new ArrayList<Crmprojectticketnotification>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojecttask crmprojecttask = crmprojectticketmanagement.getCrmprojecttask();
            if (crmprojecttask != null) {
                crmprojecttask = em.getReference(crmprojecttask.getClass(), crmprojecttask.getCrmprojecttaskPK());
                crmprojectticketmanagement.setCrmprojecttask(crmprojecttask);
            }
            Companyemployee companyemployee = crmprojectticketmanagement.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmprojectticketmanagement.setCompanyemployee(companyemployee);
            }
            Crmprojecttask crmprojecttask1 = crmprojectticketmanagement.getCrmprojecttask1();
            if (crmprojecttask1 != null) {
                crmprojecttask1 = em.getReference(crmprojecttask1.getClass(), crmprojecttask1.getCrmprojecttaskPK());
                crmprojectticketmanagement.setCrmprojecttask1(crmprojecttask1);
            }
            Companyemployee companyemployee1 = crmprojectticketmanagement.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmprojectticketmanagement.setCompanyemployee1(companyemployee1);
            }
            Companyemployee companyemployee2 = crmprojectticketmanagement.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2 = em.getReference(companyemployee2.getClass(), companyemployee2.getCompanyemployeePK());
                crmprojectticketmanagement.setCompanyemployee2(companyemployee2);
            }
            Companyemployee companyemployee3 = crmprojectticketmanagement.getCompanyemployee3();
            if (companyemployee3 != null) {
                companyemployee3 = em.getReference(companyemployee3.getClass(), companyemployee3.getCompanyemployeePK());
                crmprojectticketmanagement.setCompanyemployee3(companyemployee3);
            }
            Collection<Crmworkorder> attachedCrmworkorderCollection = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionCrmworkorderToAttach : crmprojectticketmanagement.getCrmworkorderCollection()) {
                crmworkorderCollectionCrmworkorderToAttach = em.getReference(crmworkorderCollectionCrmworkorderToAttach.getClass(), crmworkorderCollectionCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollection.add(crmworkorderCollectionCrmworkorderToAttach);
            }
            crmprojectticketmanagement.setCrmworkorderCollection(attachedCrmworkorderCollection);
            Collection<Crmprojectticketnotification> attachedCrmprojectticketnotificationCollection = new ArrayList<Crmprojectticketnotification>();
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionCrmprojectticketnotificationToAttach : crmprojectticketmanagement.getCrmprojectticketnotificationCollection()) {
                crmprojectticketnotificationCollectionCrmprojectticketnotificationToAttach = em.getReference(crmprojectticketnotificationCollectionCrmprojectticketnotificationToAttach.getClass(), crmprojectticketnotificationCollectionCrmprojectticketnotificationToAttach.getCrmprojectticketnotificationPK());
                attachedCrmprojectticketnotificationCollection.add(crmprojectticketnotificationCollectionCrmprojectticketnotificationToAttach);
            }
            crmprojectticketmanagement.setCrmprojectticketnotificationCollection(attachedCrmprojectticketnotificationCollection);
            em.persist(crmprojectticketmanagement);
            if (crmprojecttask != null) {
                crmprojecttask.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                crmprojecttask = em.merge(crmprojecttask);
            }
            if (companyemployee != null) {
                companyemployee.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                companyemployee = em.merge(companyemployee);
            }
            if (crmprojecttask1 != null) {
                crmprojecttask1.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                crmprojecttask1 = em.merge(crmprojecttask1);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (companyemployee2 != null) {
                companyemployee2.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                companyemployee2 = em.merge(companyemployee2);
            }
            if (companyemployee3 != null) {
                companyemployee3.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                companyemployee3 = em.merge(companyemployee3);
            }
            for (Crmworkorder crmworkorderCollectionCrmworkorder : crmprojectticketmanagement.getCrmworkorderCollection()) {
                Crmprojectticketmanagement oldCrmprojectticketmanagementOfCrmworkorderCollectionCrmworkorder = crmworkorderCollectionCrmworkorder.getCrmprojectticketmanagement();
                crmworkorderCollectionCrmworkorder.setCrmprojectticketmanagement(crmprojectticketmanagement);
                crmworkorderCollectionCrmworkorder = em.merge(crmworkorderCollectionCrmworkorder);
                if (oldCrmprojectticketmanagementOfCrmworkorderCollectionCrmworkorder != null) {
                    oldCrmprojectticketmanagementOfCrmworkorderCollectionCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionCrmworkorder);
                    oldCrmprojectticketmanagementOfCrmworkorderCollectionCrmworkorder = em.merge(oldCrmprojectticketmanagementOfCrmworkorderCollectionCrmworkorder);
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionCrmprojectticketnotification : crmprojectticketmanagement.getCrmprojectticketnotificationCollection()) {
                Crmprojectticketmanagement oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionCrmprojectticketnotification = crmprojectticketnotificationCollectionCrmprojectticketnotification.getCrmprojectticketmanagement();
                crmprojectticketnotificationCollectionCrmprojectticketnotification.setCrmprojectticketmanagement(crmprojectticketmanagement);
                crmprojectticketnotificationCollectionCrmprojectticketnotification = em.merge(crmprojectticketnotificationCollectionCrmprojectticketnotification);
                if (oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionCrmprojectticketnotification != null) {
                    oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionCrmprojectticketnotification.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotificationCollectionCrmprojectticketnotification);
                    oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionCrmprojectticketnotification = em.merge(oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionCrmprojectticketnotification);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmprojectticketmanagement(crmprojectticketmanagement.getCrmprojectticketmanagementPK()) != null) {
                throw new PreexistingEntityException("Crmprojectticketmanagement " + crmprojectticketmanagement + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmprojectticketmanagement crmprojectticketmanagement) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectticketmanagement persistentCrmprojectticketmanagement = em.find(Crmprojectticketmanagement.class, crmprojectticketmanagement.getCrmprojectticketmanagementPK());
            Crmprojecttask crmprojecttaskOld = persistentCrmprojectticketmanagement.getCrmprojecttask();
            Crmprojecttask crmprojecttaskNew = crmprojectticketmanagement.getCrmprojecttask();
            Companyemployee companyemployeeOld = persistentCrmprojectticketmanagement.getCompanyemployee();
            Companyemployee companyemployeeNew = crmprojectticketmanagement.getCompanyemployee();
            Crmprojecttask crmprojecttask1Old = persistentCrmprojectticketmanagement.getCrmprojecttask1();
            Crmprojecttask crmprojecttask1New = crmprojectticketmanagement.getCrmprojecttask1();
            Companyemployee companyemployee1Old = persistentCrmprojectticketmanagement.getCompanyemployee1();
            Companyemployee companyemployee1New = crmprojectticketmanagement.getCompanyemployee1();
            Companyemployee companyemployee2Old = persistentCrmprojectticketmanagement.getCompanyemployee2();
            Companyemployee companyemployee2New = crmprojectticketmanagement.getCompanyemployee2();
            Companyemployee companyemployee3Old = persistentCrmprojectticketmanagement.getCompanyemployee3();
            Companyemployee companyemployee3New = crmprojectticketmanagement.getCompanyemployee3();
            Collection<Crmworkorder> crmworkorderCollectionOld = persistentCrmprojectticketmanagement.getCrmworkorderCollection();
            Collection<Crmworkorder> crmworkorderCollectionNew = crmprojectticketmanagement.getCrmworkorderCollection();
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollectionOld = persistentCrmprojectticketmanagement.getCrmprojectticketnotificationCollection();
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollectionNew = crmprojectticketmanagement.getCrmprojectticketnotificationCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmworkorder crmworkorderCollectionOldCrmworkorder : crmworkorderCollectionOld) {
                if (!crmworkorderCollectionNew.contains(crmworkorderCollectionOldCrmworkorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorder " + crmworkorderCollectionOldCrmworkorder + " since its crmprojectticketmanagement field is not nullable.");
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionOldCrmprojectticketnotification : crmprojectticketnotificationCollectionOld) {
                if (!crmprojectticketnotificationCollectionNew.contains(crmprojectticketnotificationCollectionOldCrmprojectticketnotification)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectticketnotification " + crmprojectticketnotificationCollectionOldCrmprojectticketnotification + " since its crmprojectticketmanagement field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (crmprojecttaskNew != null) {
                crmprojecttaskNew = em.getReference(crmprojecttaskNew.getClass(), crmprojecttaskNew.getCrmprojecttaskPK());
                crmprojectticketmanagement.setCrmprojecttask(crmprojecttaskNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmprojectticketmanagement.setCompanyemployee(companyemployeeNew);
            }
            if (crmprojecttask1New != null) {
                crmprojecttask1New = em.getReference(crmprojecttask1New.getClass(), crmprojecttask1New.getCrmprojecttaskPK());
                crmprojectticketmanagement.setCrmprojecttask1(crmprojecttask1New);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmprojectticketmanagement.setCompanyemployee1(companyemployee1New);
            }
            if (companyemployee2New != null) {
                companyemployee2New = em.getReference(companyemployee2New.getClass(), companyemployee2New.getCompanyemployeePK());
                crmprojectticketmanagement.setCompanyemployee2(companyemployee2New);
            }
            if (companyemployee3New != null) {
                companyemployee3New = em.getReference(companyemployee3New.getClass(), companyemployee3New.getCompanyemployeePK());
                crmprojectticketmanagement.setCompanyemployee3(companyemployee3New);
            }
            Collection<Crmworkorder> attachedCrmworkorderCollectionNew = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionNewCrmworkorderToAttach : crmworkorderCollectionNew) {
                crmworkorderCollectionNewCrmworkorderToAttach = em.getReference(crmworkorderCollectionNewCrmworkorderToAttach.getClass(), crmworkorderCollectionNewCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollectionNew.add(crmworkorderCollectionNewCrmworkorderToAttach);
            }
            crmworkorderCollectionNew = attachedCrmworkorderCollectionNew;
            crmprojectticketmanagement.setCrmworkorderCollection(crmworkorderCollectionNew);
            Collection<Crmprojectticketnotification> attachedCrmprojectticketnotificationCollectionNew = new ArrayList<Crmprojectticketnotification>();
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionNewCrmprojectticketnotificationToAttach : crmprojectticketnotificationCollectionNew) {
                crmprojectticketnotificationCollectionNewCrmprojectticketnotificationToAttach = em.getReference(crmprojectticketnotificationCollectionNewCrmprojectticketnotificationToAttach.getClass(), crmprojectticketnotificationCollectionNewCrmprojectticketnotificationToAttach.getCrmprojectticketnotificationPK());
                attachedCrmprojectticketnotificationCollectionNew.add(crmprojectticketnotificationCollectionNewCrmprojectticketnotificationToAttach);
            }
            crmprojectticketnotificationCollectionNew = attachedCrmprojectticketnotificationCollectionNew;
            crmprojectticketmanagement.setCrmprojectticketnotificationCollection(crmprojectticketnotificationCollectionNew);
            crmprojectticketmanagement = em.merge(crmprojectticketmanagement);
            if (crmprojecttaskOld != null && !crmprojecttaskOld.equals(crmprojecttaskNew)) {
                crmprojecttaskOld.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                crmprojecttaskOld = em.merge(crmprojecttaskOld);
            }
            if (crmprojecttaskNew != null && !crmprojecttaskNew.equals(crmprojecttaskOld)) {
                crmprojecttaskNew.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                crmprojecttaskNew = em.merge(crmprojecttaskNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (crmprojecttask1Old != null && !crmprojecttask1Old.equals(crmprojecttask1New)) {
                crmprojecttask1Old.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                crmprojecttask1Old = em.merge(crmprojecttask1Old);
            }
            if (crmprojecttask1New != null && !crmprojecttask1New.equals(crmprojecttask1Old)) {
                crmprojecttask1New.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                crmprojecttask1New = em.merge(crmprojecttask1New);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (companyemployee2Old != null && !companyemployee2Old.equals(companyemployee2New)) {
                companyemployee2Old.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                companyemployee2Old = em.merge(companyemployee2Old);
            }
            if (companyemployee2New != null && !companyemployee2New.equals(companyemployee2Old)) {
                companyemployee2New.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                companyemployee2New = em.merge(companyemployee2New);
            }
            if (companyemployee3Old != null && !companyemployee3Old.equals(companyemployee3New)) {
                companyemployee3Old.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                companyemployee3Old = em.merge(companyemployee3Old);
            }
            if (companyemployee3New != null && !companyemployee3New.equals(companyemployee3Old)) {
                companyemployee3New.getCrmprojectticketmanagementCollection().add(crmprojectticketmanagement);
                companyemployee3New = em.merge(companyemployee3New);
            }
            for (Crmworkorder crmworkorderCollectionNewCrmworkorder : crmworkorderCollectionNew) {
                if (!crmworkorderCollectionOld.contains(crmworkorderCollectionNewCrmworkorder)) {
                    Crmprojectticketmanagement oldCrmprojectticketmanagementOfCrmworkorderCollectionNewCrmworkorder = crmworkorderCollectionNewCrmworkorder.getCrmprojectticketmanagement();
                    crmworkorderCollectionNewCrmworkorder.setCrmprojectticketmanagement(crmprojectticketmanagement);
                    crmworkorderCollectionNewCrmworkorder = em.merge(crmworkorderCollectionNewCrmworkorder);
                    if (oldCrmprojectticketmanagementOfCrmworkorderCollectionNewCrmworkorder != null && !oldCrmprojectticketmanagementOfCrmworkorderCollectionNewCrmworkorder.equals(crmprojectticketmanagement)) {
                        oldCrmprojectticketmanagementOfCrmworkorderCollectionNewCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionNewCrmworkorder);
                        oldCrmprojectticketmanagementOfCrmworkorderCollectionNewCrmworkorder = em.merge(oldCrmprojectticketmanagementOfCrmworkorderCollectionNewCrmworkorder);
                    }
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionNewCrmprojectticketnotification : crmprojectticketnotificationCollectionNew) {
                if (!crmprojectticketnotificationCollectionOld.contains(crmprojectticketnotificationCollectionNewCrmprojectticketnotification)) {
                    Crmprojectticketmanagement oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification = crmprojectticketnotificationCollectionNewCrmprojectticketnotification.getCrmprojectticketmanagement();
                    crmprojectticketnotificationCollectionNewCrmprojectticketnotification.setCrmprojectticketmanagement(crmprojectticketmanagement);
                    crmprojectticketnotificationCollectionNewCrmprojectticketnotification = em.merge(crmprojectticketnotificationCollectionNewCrmprojectticketnotification);
                    if (oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification != null && !oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification.equals(crmprojectticketmanagement)) {
                        oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotificationCollectionNewCrmprojectticketnotification);
                        oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification = em.merge(oldCrmprojectticketmanagementOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification);
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
                CrmprojectticketmanagementPK id = crmprojectticketmanagement.getCrmprojectticketmanagementPK();
                if (findCrmprojectticketmanagement(id) == null) {
                    throw new NonexistentEntityException("The crmprojectticketmanagement with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmprojectticketmanagementPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmprojectticketmanagement crmprojectticketmanagement;
            try {
                crmprojectticketmanagement = em.getReference(Crmprojectticketmanagement.class, id);
                crmprojectticketmanagement.getCrmprojectticketmanagementPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmprojectticketmanagement with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmworkorder> crmworkorderCollectionOrphanCheck = crmprojectticketmanagement.getCrmworkorderCollection();
            for (Crmworkorder crmworkorderCollectionOrphanCheckCrmworkorder : crmworkorderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmprojectticketmanagement (" + crmprojectticketmanagement + ") cannot be destroyed since the Crmworkorder " + crmworkorderCollectionOrphanCheckCrmworkorder + " in its crmworkorderCollection field has a non-nullable crmprojectticketmanagement field.");
            }
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollectionOrphanCheck = crmprojectticketmanagement.getCrmprojectticketnotificationCollection();
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionOrphanCheckCrmprojectticketnotification : crmprojectticketnotificationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmprojectticketmanagement (" + crmprojectticketmanagement + ") cannot be destroyed since the Crmprojectticketnotification " + crmprojectticketnotificationCollectionOrphanCheckCrmprojectticketnotification + " in its crmprojectticketnotificationCollection field has a non-nullable crmprojectticketmanagement field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Crmprojecttask crmprojecttask = crmprojectticketmanagement.getCrmprojecttask();
            if (crmprojecttask != null) {
                crmprojecttask.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                crmprojecttask = em.merge(crmprojecttask);
            }
            Companyemployee companyemployee = crmprojectticketmanagement.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                companyemployee = em.merge(companyemployee);
            }
            Crmprojecttask crmprojecttask1 = crmprojectticketmanagement.getCrmprojecttask1();
            if (crmprojecttask1 != null) {
                crmprojecttask1.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                crmprojecttask1 = em.merge(crmprojecttask1);
            }
            Companyemployee companyemployee1 = crmprojectticketmanagement.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                companyemployee1 = em.merge(companyemployee1);
            }
            Companyemployee companyemployee2 = crmprojectticketmanagement.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                companyemployee2 = em.merge(companyemployee2);
            }
            Companyemployee companyemployee3 = crmprojectticketmanagement.getCompanyemployee3();
            if (companyemployee3 != null) {
                companyemployee3.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagement);
                companyemployee3 = em.merge(companyemployee3);
            }
            em.remove(crmprojectticketmanagement);
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

    public List<Crmprojectticketmanagement> findCrmprojectticketmanagementEntities() {
        return findCrmprojectticketmanagementEntities(true, -1, -1);
    }

    public List<Crmprojectticketmanagement> findCrmprojectticketmanagementEntities(int maxResults, int firstResult) {
        return findCrmprojectticketmanagementEntities(false, maxResults, firstResult);
    }

    private List<Crmprojectticketmanagement> findCrmprojectticketmanagementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmprojectticketmanagement.class));
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

    public Crmprojectticketmanagement findCrmprojectticketmanagement(CrmprojectticketmanagementPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmprojectticketmanagement.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmprojectticketmanagementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmprojectticketmanagement> rt = cq.from(Crmprojectticketmanagement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
