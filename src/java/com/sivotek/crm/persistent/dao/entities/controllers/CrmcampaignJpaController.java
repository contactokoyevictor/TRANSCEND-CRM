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
import com.sivotek.crm.persistent.dao.entities.Crmcampaignstatus;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmcampaign;
import com.sivotek.crm.persistent.dao.entities.CrmcampaignPK;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignreceiver;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignposition;
import com.sivotek.crm.persistent.dao.entities.Crmcampaigndocs;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignprops;
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
public class CrmcampaignJpaController implements Serializable {

    public CrmcampaignJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CrmcampaignJpaController(){
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

    public void create(Crmcampaign crmcampaign) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmcampaign.getCrmcampaignPK() == null) {
            crmcampaign.setCrmcampaignPK(new CrmcampaignPK());
        }
        if (crmcampaign.getCrmcampaignreceiverCollection() == null) {
            crmcampaign.setCrmcampaignreceiverCollection(new ArrayList<Crmcampaignreceiver>());
        }
        if (crmcampaign.getCrmcampaignpositionCollection() == null) {
            crmcampaign.setCrmcampaignpositionCollection(new ArrayList<Crmcampaignposition>());
        }
        if (crmcampaign.getCrmcampaigndocsCollection() == null) {
            crmcampaign.setCrmcampaigndocsCollection(new ArrayList<Crmcampaigndocs>());
        }
        if (crmcampaign.getCrmcampaignpropsCollection() == null) {
            crmcampaign.setCrmcampaignpropsCollection(new ArrayList<Crmcampaignprops>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmcampaign.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmcampaign.setCompany(company);
            }
            Crmcampaignstatus crmcampaignstatus = crmcampaign.getCrmcampaignstatus();
            if (crmcampaignstatus != null) {
                crmcampaignstatus = em.getReference(crmcampaignstatus.getClass(), crmcampaignstatus.getCrmcampaignstatusPK());
                crmcampaign.setCrmcampaignstatus(crmcampaignstatus);
            }
            Companyemployee companyemployee = crmcampaign.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmcampaign.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmcampaign.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmcampaign.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmcampaignreceiver> attachedCrmcampaignreceiverCollection = new ArrayList<Crmcampaignreceiver>();
            for (Crmcampaignreceiver crmcampaignreceiverCollectionCrmcampaignreceiverToAttach : crmcampaign.getCrmcampaignreceiverCollection()) {
                crmcampaignreceiverCollectionCrmcampaignreceiverToAttach = em.getReference(crmcampaignreceiverCollectionCrmcampaignreceiverToAttach.getClass(), crmcampaignreceiverCollectionCrmcampaignreceiverToAttach.getCrmcampaignreceiverPK());
                attachedCrmcampaignreceiverCollection.add(crmcampaignreceiverCollectionCrmcampaignreceiverToAttach);
            }
            crmcampaign.setCrmcampaignreceiverCollection(attachedCrmcampaignreceiverCollection);
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollection = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollectionCrmcampaignpositionToAttach : crmcampaign.getCrmcampaignpositionCollection()) {
                crmcampaignpositionCollectionCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollectionCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollectionCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollection.add(crmcampaignpositionCollectionCrmcampaignpositionToAttach);
            }
            crmcampaign.setCrmcampaignpositionCollection(attachedCrmcampaignpositionCollection);
            Collection<Crmcampaigndocs> attachedCrmcampaigndocsCollection = new ArrayList<Crmcampaigndocs>();
            for (Crmcampaigndocs crmcampaigndocsCollectionCrmcampaigndocsToAttach : crmcampaign.getCrmcampaigndocsCollection()) {
                crmcampaigndocsCollectionCrmcampaigndocsToAttach = em.getReference(crmcampaigndocsCollectionCrmcampaigndocsToAttach.getClass(), crmcampaigndocsCollectionCrmcampaigndocsToAttach.getCrmcampaigndocsPK());
                attachedCrmcampaigndocsCollection.add(crmcampaigndocsCollectionCrmcampaigndocsToAttach);
            }
            crmcampaign.setCrmcampaigndocsCollection(attachedCrmcampaigndocsCollection);
            Collection<Crmcampaignprops> attachedCrmcampaignpropsCollection = new ArrayList<Crmcampaignprops>();
            for (Crmcampaignprops crmcampaignpropsCollectionCrmcampaignpropsToAttach : crmcampaign.getCrmcampaignpropsCollection()) {
                crmcampaignpropsCollectionCrmcampaignpropsToAttach = em.getReference(crmcampaignpropsCollectionCrmcampaignpropsToAttach.getClass(), crmcampaignpropsCollectionCrmcampaignpropsToAttach.getCrmcampaignpropsPK());
                attachedCrmcampaignpropsCollection.add(crmcampaignpropsCollectionCrmcampaignpropsToAttach);
            }
            crmcampaign.setCrmcampaignpropsCollection(attachedCrmcampaignpropsCollection);
            em.persist(crmcampaign);
            if (company != null) {
                company.getCrmcampaignCollection().add(crmcampaign);
                company = em.merge(company);
            }
            if (crmcampaignstatus != null) {
                crmcampaignstatus.getCrmcampaignCollection().add(crmcampaign);
                crmcampaignstatus = em.merge(crmcampaignstatus);
            }
            if (companyemployee != null) {
                companyemployee.getCrmcampaignCollection().add(crmcampaign);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmcampaignCollection().add(crmcampaign);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollectionCrmcampaignreceiver : crmcampaign.getCrmcampaignreceiverCollection()) {
                Crmcampaign oldCrmcampaignOfCrmcampaignreceiverCollectionCrmcampaignreceiver = crmcampaignreceiverCollectionCrmcampaignreceiver.getCrmcampaign();
                crmcampaignreceiverCollectionCrmcampaignreceiver.setCrmcampaign(crmcampaign);
                crmcampaignreceiverCollectionCrmcampaignreceiver = em.merge(crmcampaignreceiverCollectionCrmcampaignreceiver);
                if (oldCrmcampaignOfCrmcampaignreceiverCollectionCrmcampaignreceiver != null) {
                    oldCrmcampaignOfCrmcampaignreceiverCollectionCrmcampaignreceiver.getCrmcampaignreceiverCollection().remove(crmcampaignreceiverCollectionCrmcampaignreceiver);
                    oldCrmcampaignOfCrmcampaignreceiverCollectionCrmcampaignreceiver = em.merge(oldCrmcampaignOfCrmcampaignreceiverCollectionCrmcampaignreceiver);
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollectionCrmcampaignposition : crmcampaign.getCrmcampaignpositionCollection()) {
                Crmcampaign oldCrmcampaignOfCrmcampaignpositionCollectionCrmcampaignposition = crmcampaignpositionCollectionCrmcampaignposition.getCrmcampaign();
                crmcampaignpositionCollectionCrmcampaignposition.setCrmcampaign(crmcampaign);
                crmcampaignpositionCollectionCrmcampaignposition = em.merge(crmcampaignpositionCollectionCrmcampaignposition);
                if (oldCrmcampaignOfCrmcampaignpositionCollectionCrmcampaignposition != null) {
                    oldCrmcampaignOfCrmcampaignpositionCollectionCrmcampaignposition.getCrmcampaignpositionCollection().remove(crmcampaignpositionCollectionCrmcampaignposition);
                    oldCrmcampaignOfCrmcampaignpositionCollectionCrmcampaignposition = em.merge(oldCrmcampaignOfCrmcampaignpositionCollectionCrmcampaignposition);
                }
            }
            for (Crmcampaigndocs crmcampaigndocsCollectionCrmcampaigndocs : crmcampaign.getCrmcampaigndocsCollection()) {
                Crmcampaign oldCrmcampaignOfCrmcampaigndocsCollectionCrmcampaigndocs = crmcampaigndocsCollectionCrmcampaigndocs.getCrmcampaign();
                crmcampaigndocsCollectionCrmcampaigndocs.setCrmcampaign(crmcampaign);
                crmcampaigndocsCollectionCrmcampaigndocs = em.merge(crmcampaigndocsCollectionCrmcampaigndocs);
                if (oldCrmcampaignOfCrmcampaigndocsCollectionCrmcampaigndocs != null) {
                    oldCrmcampaignOfCrmcampaigndocsCollectionCrmcampaigndocs.getCrmcampaigndocsCollection().remove(crmcampaigndocsCollectionCrmcampaigndocs);
                    oldCrmcampaignOfCrmcampaigndocsCollectionCrmcampaigndocs = em.merge(oldCrmcampaignOfCrmcampaigndocsCollectionCrmcampaigndocs);
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollectionCrmcampaignprops : crmcampaign.getCrmcampaignpropsCollection()) {
                Crmcampaign oldCrmcampaignOfCrmcampaignpropsCollectionCrmcampaignprops = crmcampaignpropsCollectionCrmcampaignprops.getCrmcampaign();
                crmcampaignpropsCollectionCrmcampaignprops.setCrmcampaign(crmcampaign);
                crmcampaignpropsCollectionCrmcampaignprops = em.merge(crmcampaignpropsCollectionCrmcampaignprops);
                if (oldCrmcampaignOfCrmcampaignpropsCollectionCrmcampaignprops != null) {
                    oldCrmcampaignOfCrmcampaignpropsCollectionCrmcampaignprops.getCrmcampaignpropsCollection().remove(crmcampaignpropsCollectionCrmcampaignprops);
                    oldCrmcampaignOfCrmcampaignpropsCollectionCrmcampaignprops = em.merge(oldCrmcampaignOfCrmcampaignpropsCollectionCrmcampaignprops);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmcampaign(crmcampaign.getCrmcampaignPK()) != null) {
                throw new PreexistingEntityException("Crmcampaign " + crmcampaign + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmcampaign crmcampaign) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaign persistentCrmcampaign = em.find(Crmcampaign.class, crmcampaign.getCrmcampaignPK());
            Company companyOld = persistentCrmcampaign.getCompany();
            Company companyNew = crmcampaign.getCompany();
            Crmcampaignstatus crmcampaignstatusOld = persistentCrmcampaign.getCrmcampaignstatus();
            Crmcampaignstatus crmcampaignstatusNew = crmcampaign.getCrmcampaignstatus();
            Companyemployee companyemployeeOld = persistentCrmcampaign.getCompanyemployee();
            Companyemployee companyemployeeNew = crmcampaign.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmcampaign.getCompanyemployee1();
            Companyemployee companyemployee1New = crmcampaign.getCompanyemployee1();
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollectionOld = persistentCrmcampaign.getCrmcampaignreceiverCollection();
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollectionNew = crmcampaign.getCrmcampaignreceiverCollection();
            Collection<Crmcampaignposition> crmcampaignpositionCollectionOld = persistentCrmcampaign.getCrmcampaignpositionCollection();
            Collection<Crmcampaignposition> crmcampaignpositionCollectionNew = crmcampaign.getCrmcampaignpositionCollection();
            Collection<Crmcampaigndocs> crmcampaigndocsCollectionOld = persistentCrmcampaign.getCrmcampaigndocsCollection();
            Collection<Crmcampaigndocs> crmcampaigndocsCollectionNew = crmcampaign.getCrmcampaigndocsCollection();
            Collection<Crmcampaignprops> crmcampaignpropsCollectionOld = persistentCrmcampaign.getCrmcampaignpropsCollection();
            Collection<Crmcampaignprops> crmcampaignpropsCollectionNew = crmcampaign.getCrmcampaignpropsCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmcampaignreceiver crmcampaignreceiverCollectionOldCrmcampaignreceiver : crmcampaignreceiverCollectionOld) {
                if (!crmcampaignreceiverCollectionNew.contains(crmcampaignreceiverCollectionOldCrmcampaignreceiver)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignreceiver " + crmcampaignreceiverCollectionOldCrmcampaignreceiver + " since its crmcampaign field is not nullable.");
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollectionOldCrmcampaignposition : crmcampaignpositionCollectionOld) {
                if (!crmcampaignpositionCollectionNew.contains(crmcampaignpositionCollectionOldCrmcampaignposition)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignposition " + crmcampaignpositionCollectionOldCrmcampaignposition + " since its crmcampaign field is not nullable.");
                }
            }
            for (Crmcampaigndocs crmcampaigndocsCollectionOldCrmcampaigndocs : crmcampaigndocsCollectionOld) {
                if (!crmcampaigndocsCollectionNew.contains(crmcampaigndocsCollectionOldCrmcampaigndocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaigndocs " + crmcampaigndocsCollectionOldCrmcampaigndocs + " since its crmcampaign field is not nullable.");
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollectionOldCrmcampaignprops : crmcampaignpropsCollectionOld) {
                if (!crmcampaignpropsCollectionNew.contains(crmcampaignpropsCollectionOldCrmcampaignprops)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignprops " + crmcampaignpropsCollectionOldCrmcampaignprops + " since its crmcampaign field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmcampaign.setCompany(companyNew);
            }
            if (crmcampaignstatusNew != null) {
                crmcampaignstatusNew = em.getReference(crmcampaignstatusNew.getClass(), crmcampaignstatusNew.getCrmcampaignstatusPK());
                crmcampaign.setCrmcampaignstatus(crmcampaignstatusNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmcampaign.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmcampaign.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmcampaignreceiver> attachedCrmcampaignreceiverCollectionNew = new ArrayList<Crmcampaignreceiver>();
            for (Crmcampaignreceiver crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach : crmcampaignreceiverCollectionNew) {
                crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach = em.getReference(crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach.getClass(), crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach.getCrmcampaignreceiverPK());
                attachedCrmcampaignreceiverCollectionNew.add(crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach);
            }
            crmcampaignreceiverCollectionNew = attachedCrmcampaignreceiverCollectionNew;
            crmcampaign.setCrmcampaignreceiverCollection(crmcampaignreceiverCollectionNew);
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollectionNew = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollectionNewCrmcampaignpositionToAttach : crmcampaignpositionCollectionNew) {
                crmcampaignpositionCollectionNewCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollectionNewCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollectionNewCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollectionNew.add(crmcampaignpositionCollectionNewCrmcampaignpositionToAttach);
            }
            crmcampaignpositionCollectionNew = attachedCrmcampaignpositionCollectionNew;
            crmcampaign.setCrmcampaignpositionCollection(crmcampaignpositionCollectionNew);
            Collection<Crmcampaigndocs> attachedCrmcampaigndocsCollectionNew = new ArrayList<Crmcampaigndocs>();
            for (Crmcampaigndocs crmcampaigndocsCollectionNewCrmcampaigndocsToAttach : crmcampaigndocsCollectionNew) {
                crmcampaigndocsCollectionNewCrmcampaigndocsToAttach = em.getReference(crmcampaigndocsCollectionNewCrmcampaigndocsToAttach.getClass(), crmcampaigndocsCollectionNewCrmcampaigndocsToAttach.getCrmcampaigndocsPK());
                attachedCrmcampaigndocsCollectionNew.add(crmcampaigndocsCollectionNewCrmcampaigndocsToAttach);
            }
            crmcampaigndocsCollectionNew = attachedCrmcampaigndocsCollectionNew;
            crmcampaign.setCrmcampaigndocsCollection(crmcampaigndocsCollectionNew);
            Collection<Crmcampaignprops> attachedCrmcampaignpropsCollectionNew = new ArrayList<Crmcampaignprops>();
            for (Crmcampaignprops crmcampaignpropsCollectionNewCrmcampaignpropsToAttach : crmcampaignpropsCollectionNew) {
                crmcampaignpropsCollectionNewCrmcampaignpropsToAttach = em.getReference(crmcampaignpropsCollectionNewCrmcampaignpropsToAttach.getClass(), crmcampaignpropsCollectionNewCrmcampaignpropsToAttach.getCrmcampaignpropsPK());
                attachedCrmcampaignpropsCollectionNew.add(crmcampaignpropsCollectionNewCrmcampaignpropsToAttach);
            }
            crmcampaignpropsCollectionNew = attachedCrmcampaignpropsCollectionNew;
            crmcampaign.setCrmcampaignpropsCollection(crmcampaignpropsCollectionNew);
            crmcampaign = em.merge(crmcampaign);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmcampaignCollection().remove(crmcampaign);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmcampaignCollection().add(crmcampaign);
                companyNew = em.merge(companyNew);
            }
            if (crmcampaignstatusOld != null && !crmcampaignstatusOld.equals(crmcampaignstatusNew)) {
                crmcampaignstatusOld.getCrmcampaignCollection().remove(crmcampaign);
                crmcampaignstatusOld = em.merge(crmcampaignstatusOld);
            }
            if (crmcampaignstatusNew != null && !crmcampaignstatusNew.equals(crmcampaignstatusOld)) {
                crmcampaignstatusNew.getCrmcampaignCollection().add(crmcampaign);
                crmcampaignstatusNew = em.merge(crmcampaignstatusNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmcampaignCollection().remove(crmcampaign);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmcampaignCollection().add(crmcampaign);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmcampaignCollection().remove(crmcampaign);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmcampaignCollection().add(crmcampaign);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollectionNewCrmcampaignreceiver : crmcampaignreceiverCollectionNew) {
                if (!crmcampaignreceiverCollectionOld.contains(crmcampaignreceiverCollectionNewCrmcampaignreceiver)) {
                    Crmcampaign oldCrmcampaignOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver = crmcampaignreceiverCollectionNewCrmcampaignreceiver.getCrmcampaign();
                    crmcampaignreceiverCollectionNewCrmcampaignreceiver.setCrmcampaign(crmcampaign);
                    crmcampaignreceiverCollectionNewCrmcampaignreceiver = em.merge(crmcampaignreceiverCollectionNewCrmcampaignreceiver);
                    if (oldCrmcampaignOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver != null && !oldCrmcampaignOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver.equals(crmcampaign)) {
                        oldCrmcampaignOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver.getCrmcampaignreceiverCollection().remove(crmcampaignreceiverCollectionNewCrmcampaignreceiver);
                        oldCrmcampaignOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver = em.merge(oldCrmcampaignOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver);
                    }
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollectionNewCrmcampaignposition : crmcampaignpositionCollectionNew) {
                if (!crmcampaignpositionCollectionOld.contains(crmcampaignpositionCollectionNewCrmcampaignposition)) {
                    Crmcampaign oldCrmcampaignOfCrmcampaignpositionCollectionNewCrmcampaignposition = crmcampaignpositionCollectionNewCrmcampaignposition.getCrmcampaign();
                    crmcampaignpositionCollectionNewCrmcampaignposition.setCrmcampaign(crmcampaign);
                    crmcampaignpositionCollectionNewCrmcampaignposition = em.merge(crmcampaignpositionCollectionNewCrmcampaignposition);
                    if (oldCrmcampaignOfCrmcampaignpositionCollectionNewCrmcampaignposition != null && !oldCrmcampaignOfCrmcampaignpositionCollectionNewCrmcampaignposition.equals(crmcampaign)) {
                        oldCrmcampaignOfCrmcampaignpositionCollectionNewCrmcampaignposition.getCrmcampaignpositionCollection().remove(crmcampaignpositionCollectionNewCrmcampaignposition);
                        oldCrmcampaignOfCrmcampaignpositionCollectionNewCrmcampaignposition = em.merge(oldCrmcampaignOfCrmcampaignpositionCollectionNewCrmcampaignposition);
                    }
                }
            }
            for (Crmcampaigndocs crmcampaigndocsCollectionNewCrmcampaigndocs : crmcampaigndocsCollectionNew) {
                if (!crmcampaigndocsCollectionOld.contains(crmcampaigndocsCollectionNewCrmcampaigndocs)) {
                    Crmcampaign oldCrmcampaignOfCrmcampaigndocsCollectionNewCrmcampaigndocs = crmcampaigndocsCollectionNewCrmcampaigndocs.getCrmcampaign();
                    crmcampaigndocsCollectionNewCrmcampaigndocs.setCrmcampaign(crmcampaign);
                    crmcampaigndocsCollectionNewCrmcampaigndocs = em.merge(crmcampaigndocsCollectionNewCrmcampaigndocs);
                    if (oldCrmcampaignOfCrmcampaigndocsCollectionNewCrmcampaigndocs != null && !oldCrmcampaignOfCrmcampaigndocsCollectionNewCrmcampaigndocs.equals(crmcampaign)) {
                        oldCrmcampaignOfCrmcampaigndocsCollectionNewCrmcampaigndocs.getCrmcampaigndocsCollection().remove(crmcampaigndocsCollectionNewCrmcampaigndocs);
                        oldCrmcampaignOfCrmcampaigndocsCollectionNewCrmcampaigndocs = em.merge(oldCrmcampaignOfCrmcampaigndocsCollectionNewCrmcampaigndocs);
                    }
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollectionNewCrmcampaignprops : crmcampaignpropsCollectionNew) {
                if (!crmcampaignpropsCollectionOld.contains(crmcampaignpropsCollectionNewCrmcampaignprops)) {
                    Crmcampaign oldCrmcampaignOfCrmcampaignpropsCollectionNewCrmcampaignprops = crmcampaignpropsCollectionNewCrmcampaignprops.getCrmcampaign();
                    crmcampaignpropsCollectionNewCrmcampaignprops.setCrmcampaign(crmcampaign);
                    crmcampaignpropsCollectionNewCrmcampaignprops = em.merge(crmcampaignpropsCollectionNewCrmcampaignprops);
                    if (oldCrmcampaignOfCrmcampaignpropsCollectionNewCrmcampaignprops != null && !oldCrmcampaignOfCrmcampaignpropsCollectionNewCrmcampaignprops.equals(crmcampaign)) {
                        oldCrmcampaignOfCrmcampaignpropsCollectionNewCrmcampaignprops.getCrmcampaignpropsCollection().remove(crmcampaignpropsCollectionNewCrmcampaignprops);
                        oldCrmcampaignOfCrmcampaignpropsCollectionNewCrmcampaignprops = em.merge(oldCrmcampaignOfCrmcampaignpropsCollectionNewCrmcampaignprops);
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
                CrmcampaignPK id = crmcampaign.getCrmcampaignPK();
                if (findCrmcampaign(id) == null) {
                    throw new NonexistentEntityException("The crmcampaign with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmcampaignPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmcampaign crmcampaign;
            try {
                crmcampaign = em.getReference(Crmcampaign.class, id);
                crmcampaign.getCrmcampaignPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmcampaign with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollectionOrphanCheck = crmcampaign.getCrmcampaignreceiverCollection();
            for (Crmcampaignreceiver crmcampaignreceiverCollectionOrphanCheckCrmcampaignreceiver : crmcampaignreceiverCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmcampaign (" + crmcampaign + ") cannot be destroyed since the Crmcampaignreceiver " + crmcampaignreceiverCollectionOrphanCheckCrmcampaignreceiver + " in its crmcampaignreceiverCollection field has a non-nullable crmcampaign field.");
            }
            Collection<Crmcampaignposition> crmcampaignpositionCollectionOrphanCheck = crmcampaign.getCrmcampaignpositionCollection();
            for (Crmcampaignposition crmcampaignpositionCollectionOrphanCheckCrmcampaignposition : crmcampaignpositionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmcampaign (" + crmcampaign + ") cannot be destroyed since the Crmcampaignposition " + crmcampaignpositionCollectionOrphanCheckCrmcampaignposition + " in its crmcampaignpositionCollection field has a non-nullable crmcampaign field.");
            }
            Collection<Crmcampaigndocs> crmcampaigndocsCollectionOrphanCheck = crmcampaign.getCrmcampaigndocsCollection();
            for (Crmcampaigndocs crmcampaigndocsCollectionOrphanCheckCrmcampaigndocs : crmcampaigndocsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmcampaign (" + crmcampaign + ") cannot be destroyed since the Crmcampaigndocs " + crmcampaigndocsCollectionOrphanCheckCrmcampaigndocs + " in its crmcampaigndocsCollection field has a non-nullable crmcampaign field.");
            }
            Collection<Crmcampaignprops> crmcampaignpropsCollectionOrphanCheck = crmcampaign.getCrmcampaignpropsCollection();
            for (Crmcampaignprops crmcampaignpropsCollectionOrphanCheckCrmcampaignprops : crmcampaignpropsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmcampaign (" + crmcampaign + ") cannot be destroyed since the Crmcampaignprops " + crmcampaignpropsCollectionOrphanCheckCrmcampaignprops + " in its crmcampaignpropsCollection field has a non-nullable crmcampaign field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = crmcampaign.getCompany();
            if (company != null) {
                company.getCrmcampaignCollection().remove(crmcampaign);
                company = em.merge(company);
            }
            Crmcampaignstatus crmcampaignstatus = crmcampaign.getCrmcampaignstatus();
            if (crmcampaignstatus != null) {
                crmcampaignstatus.getCrmcampaignCollection().remove(crmcampaign);
                crmcampaignstatus = em.merge(crmcampaignstatus);
            }
            Companyemployee companyemployee = crmcampaign.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmcampaignCollection().remove(crmcampaign);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmcampaign.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmcampaignCollection().remove(crmcampaign);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmcampaign);
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

    public List<Crmcampaign> findCrmcampaignEntities() {
        return findCrmcampaignEntities(true, -1, -1);
    }

    public List<Crmcampaign> findCrmcampaignEntities(int maxResults, int firstResult) {
        return findCrmcampaignEntities(false, maxResults, firstResult);
    }

    private List<Crmcampaign> findCrmcampaignEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmcampaign.class));
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

    public Crmcampaign findCrmcampaign(CrmcampaignPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmcampaign.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmcampaignCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmcampaign> rt = cq.from(Crmcampaign.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
