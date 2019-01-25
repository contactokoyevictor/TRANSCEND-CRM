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
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.CompanycustomerPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmexpense;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.Crmlabor;
import com.sivotek.crm.persistent.dao.entities.Customerbank;
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
public class CompanycustomerJpaController implements Serializable {

    public CompanycustomerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CompanycustomerJpaController(){
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

    public void create(Companycustomer companycustomer) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companycustomer.getCompanycustomerPK() == null) {
            companycustomer.setCompanycustomerPK(new CompanycustomerPK());
        }
        if (companycustomer.getCrmexpenseCollection() == null) {
            companycustomer.setCrmexpenseCollection(new ArrayList<Crmexpense>());
        }
        if (companycustomer.getCrmworkorderCollection() == null) {
            companycustomer.setCrmworkorderCollection(new ArrayList<Crmworkorder>());
        }
        if (companycustomer.getCrmlaborCollection() == null) {
            companycustomer.setCrmlaborCollection(new ArrayList<Crmlabor>());
        }
        if (companycustomer.getCustomerbankCollection() == null) {
            companycustomer.setCustomerbankCollection(new ArrayList<Customerbank>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companycustomer.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companycustomer.setCompany(company);
            }
            Companyemployee companyemployee = companycustomer.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companycustomer.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companycustomer.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companycustomer.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmexpense> attachedCrmexpenseCollection = new ArrayList<Crmexpense>();
            for (Crmexpense crmexpenseCollectionCrmexpenseToAttach : companycustomer.getCrmexpenseCollection()) {
                crmexpenseCollectionCrmexpenseToAttach = em.getReference(crmexpenseCollectionCrmexpenseToAttach.getClass(), crmexpenseCollectionCrmexpenseToAttach.getCrmexpensePK());
                attachedCrmexpenseCollection.add(crmexpenseCollectionCrmexpenseToAttach);
            }
            companycustomer.setCrmexpenseCollection(attachedCrmexpenseCollection);
            Collection<Crmworkorder> attachedCrmworkorderCollection = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionCrmworkorderToAttach : companycustomer.getCrmworkorderCollection()) {
                crmworkorderCollectionCrmworkorderToAttach = em.getReference(crmworkorderCollectionCrmworkorderToAttach.getClass(), crmworkorderCollectionCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollection.add(crmworkorderCollectionCrmworkorderToAttach);
            }
            companycustomer.setCrmworkorderCollection(attachedCrmworkorderCollection);
            Collection<Crmlabor> attachedCrmlaborCollection = new ArrayList<Crmlabor>();
            for (Crmlabor crmlaborCollectionCrmlaborToAttach : companycustomer.getCrmlaborCollection()) {
                crmlaborCollectionCrmlaborToAttach = em.getReference(crmlaborCollectionCrmlaborToAttach.getClass(), crmlaborCollectionCrmlaborToAttach.getCrmlaborPK());
                attachedCrmlaborCollection.add(crmlaborCollectionCrmlaborToAttach);
            }
            companycustomer.setCrmlaborCollection(attachedCrmlaborCollection);
            Collection<Customerbank> attachedCustomerbankCollection = new ArrayList<Customerbank>();
            for (Customerbank customerbankCollectionCustomerbankToAttach : companycustomer.getCustomerbankCollection()) {
                customerbankCollectionCustomerbankToAttach = em.getReference(customerbankCollectionCustomerbankToAttach.getClass(), customerbankCollectionCustomerbankToAttach.getCustomerbankPK());
                attachedCustomerbankCollection.add(customerbankCollectionCustomerbankToAttach);
            }
            companycustomer.setCustomerbankCollection(attachedCustomerbankCollection);
            em.persist(companycustomer);
            if (company != null) {
                company.getCompanycustomerCollection().add(companycustomer);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getCompanycustomerCollection().add(companycustomer);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanycustomerCollection().add(companycustomer);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmexpense crmexpenseCollectionCrmexpense : companycustomer.getCrmexpenseCollection()) {
                Companycustomer oldCompanycustomerOfCrmexpenseCollectionCrmexpense = crmexpenseCollectionCrmexpense.getCompanycustomer();
                crmexpenseCollectionCrmexpense.setCompanycustomer(companycustomer);
                crmexpenseCollectionCrmexpense = em.merge(crmexpenseCollectionCrmexpense);
                if (oldCompanycustomerOfCrmexpenseCollectionCrmexpense != null) {
                    oldCompanycustomerOfCrmexpenseCollectionCrmexpense.getCrmexpenseCollection().remove(crmexpenseCollectionCrmexpense);
                    oldCompanycustomerOfCrmexpenseCollectionCrmexpense = em.merge(oldCompanycustomerOfCrmexpenseCollectionCrmexpense);
                }
            }
            for (Crmworkorder crmworkorderCollectionCrmworkorder : companycustomer.getCrmworkorderCollection()) {
                Companycustomer oldCompanycustomerOfCrmworkorderCollectionCrmworkorder = crmworkorderCollectionCrmworkorder.getCompanycustomer();
                crmworkorderCollectionCrmworkorder.setCompanycustomer(companycustomer);
                crmworkorderCollectionCrmworkorder = em.merge(crmworkorderCollectionCrmworkorder);
                if (oldCompanycustomerOfCrmworkorderCollectionCrmworkorder != null) {
                    oldCompanycustomerOfCrmworkorderCollectionCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionCrmworkorder);
                    oldCompanycustomerOfCrmworkorderCollectionCrmworkorder = em.merge(oldCompanycustomerOfCrmworkorderCollectionCrmworkorder);
                }
            }
            for (Crmlabor crmlaborCollectionCrmlabor : companycustomer.getCrmlaborCollection()) {
                Companycustomer oldCompanycustomerOfCrmlaborCollectionCrmlabor = crmlaborCollectionCrmlabor.getCompanycustomer();
                crmlaborCollectionCrmlabor.setCompanycustomer(companycustomer);
                crmlaborCollectionCrmlabor = em.merge(crmlaborCollectionCrmlabor);
                if (oldCompanycustomerOfCrmlaborCollectionCrmlabor != null) {
                    oldCompanycustomerOfCrmlaborCollectionCrmlabor.getCrmlaborCollection().remove(crmlaborCollectionCrmlabor);
                    oldCompanycustomerOfCrmlaborCollectionCrmlabor = em.merge(oldCompanycustomerOfCrmlaborCollectionCrmlabor);
                }
            }
            for (Customerbank customerbankCollectionCustomerbank : companycustomer.getCustomerbankCollection()) {
                Companycustomer oldCompanycustomerOfCustomerbankCollectionCustomerbank = customerbankCollectionCustomerbank.getCompanycustomer();
                customerbankCollectionCustomerbank.setCompanycustomer(companycustomer);
                customerbankCollectionCustomerbank = em.merge(customerbankCollectionCustomerbank);
                if (oldCompanycustomerOfCustomerbankCollectionCustomerbank != null) {
                    oldCompanycustomerOfCustomerbankCollectionCustomerbank.getCustomerbankCollection().remove(customerbankCollectionCustomerbank);
                    oldCompanycustomerOfCustomerbankCollectionCustomerbank = em.merge(oldCompanycustomerOfCustomerbankCollectionCustomerbank);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanycustomer(companycustomer.getCompanycustomerPK()) != null) {
                throw new PreexistingEntityException("Companycustomer " + companycustomer + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companycustomer companycustomer) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companycustomer persistentCompanycustomer = em.find(Companycustomer.class, companycustomer.getCompanycustomerPK());
            Company companyOld = persistentCompanycustomer.getCompany();
            Company companyNew = companycustomer.getCompany();
            Companyemployee companyemployeeOld = persistentCompanycustomer.getCompanyemployee();
            Companyemployee companyemployeeNew = companycustomer.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanycustomer.getCompanyemployee1();
            Companyemployee companyemployee1New = companycustomer.getCompanyemployee1();
            Collection<Crmexpense> crmexpenseCollectionOld = persistentCompanycustomer.getCrmexpenseCollection();
            Collection<Crmexpense> crmexpenseCollectionNew = companycustomer.getCrmexpenseCollection();
            Collection<Crmworkorder> crmworkorderCollectionOld = persistentCompanycustomer.getCrmworkorderCollection();
            Collection<Crmworkorder> crmworkorderCollectionNew = companycustomer.getCrmworkorderCollection();
            Collection<Crmlabor> crmlaborCollectionOld = persistentCompanycustomer.getCrmlaborCollection();
            Collection<Crmlabor> crmlaborCollectionNew = companycustomer.getCrmlaborCollection();
            Collection<Customerbank> customerbankCollectionOld = persistentCompanycustomer.getCustomerbankCollection();
            Collection<Customerbank> customerbankCollectionNew = companycustomer.getCustomerbankCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmexpense crmexpenseCollectionOldCrmexpense : crmexpenseCollectionOld) {
                if (!crmexpenseCollectionNew.contains(crmexpenseCollectionOldCrmexpense)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmexpense " + crmexpenseCollectionOldCrmexpense + " since its companycustomer field is not nullable.");
                }
            }
            for (Crmworkorder crmworkorderCollectionOldCrmworkorder : crmworkorderCollectionOld) {
                if (!crmworkorderCollectionNew.contains(crmworkorderCollectionOldCrmworkorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorder " + crmworkorderCollectionOldCrmworkorder + " since its companycustomer field is not nullable.");
                }
            }
            for (Crmlabor crmlaborCollectionOldCrmlabor : crmlaborCollectionOld) {
                if (!crmlaborCollectionNew.contains(crmlaborCollectionOldCrmlabor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmlabor " + crmlaborCollectionOldCrmlabor + " since its companycustomer field is not nullable.");
                }
            }
            for (Customerbank customerbankCollectionOldCustomerbank : customerbankCollectionOld) {
                if (!customerbankCollectionNew.contains(customerbankCollectionOldCustomerbank)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customerbank " + customerbankCollectionOldCustomerbank + " since its companycustomer field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companycustomer.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companycustomer.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companycustomer.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmexpense> attachedCrmexpenseCollectionNew = new ArrayList<Crmexpense>();
            for (Crmexpense crmexpenseCollectionNewCrmexpenseToAttach : crmexpenseCollectionNew) {
                crmexpenseCollectionNewCrmexpenseToAttach = em.getReference(crmexpenseCollectionNewCrmexpenseToAttach.getClass(), crmexpenseCollectionNewCrmexpenseToAttach.getCrmexpensePK());
                attachedCrmexpenseCollectionNew.add(crmexpenseCollectionNewCrmexpenseToAttach);
            }
            crmexpenseCollectionNew = attachedCrmexpenseCollectionNew;
            companycustomer.setCrmexpenseCollection(crmexpenseCollectionNew);
            Collection<Crmworkorder> attachedCrmworkorderCollectionNew = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionNewCrmworkorderToAttach : crmworkorderCollectionNew) {
                crmworkorderCollectionNewCrmworkorderToAttach = em.getReference(crmworkorderCollectionNewCrmworkorderToAttach.getClass(), crmworkorderCollectionNewCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollectionNew.add(crmworkorderCollectionNewCrmworkorderToAttach);
            }
            crmworkorderCollectionNew = attachedCrmworkorderCollectionNew;
            companycustomer.setCrmworkorderCollection(crmworkorderCollectionNew);
            Collection<Crmlabor> attachedCrmlaborCollectionNew = new ArrayList<Crmlabor>();
            for (Crmlabor crmlaborCollectionNewCrmlaborToAttach : crmlaborCollectionNew) {
                crmlaborCollectionNewCrmlaborToAttach = em.getReference(crmlaborCollectionNewCrmlaborToAttach.getClass(), crmlaborCollectionNewCrmlaborToAttach.getCrmlaborPK());
                attachedCrmlaborCollectionNew.add(crmlaborCollectionNewCrmlaborToAttach);
            }
            crmlaborCollectionNew = attachedCrmlaborCollectionNew;
            companycustomer.setCrmlaborCollection(crmlaborCollectionNew);
            Collection<Customerbank> attachedCustomerbankCollectionNew = new ArrayList<Customerbank>();
            for (Customerbank customerbankCollectionNewCustomerbankToAttach : customerbankCollectionNew) {
                customerbankCollectionNewCustomerbankToAttach = em.getReference(customerbankCollectionNewCustomerbankToAttach.getClass(), customerbankCollectionNewCustomerbankToAttach.getCustomerbankPK());
                attachedCustomerbankCollectionNew.add(customerbankCollectionNewCustomerbankToAttach);
            }
            customerbankCollectionNew = attachedCustomerbankCollectionNew;
            companycustomer.setCustomerbankCollection(customerbankCollectionNew);
            companycustomer = em.merge(companycustomer);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanycustomerCollection().remove(companycustomer);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanycustomerCollection().add(companycustomer);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanycustomerCollection().remove(companycustomer);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanycustomerCollection().add(companycustomer);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanycustomerCollection().remove(companycustomer);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanycustomerCollection().add(companycustomer);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmexpense crmexpenseCollectionNewCrmexpense : crmexpenseCollectionNew) {
                if (!crmexpenseCollectionOld.contains(crmexpenseCollectionNewCrmexpense)) {
                    Companycustomer oldCompanycustomerOfCrmexpenseCollectionNewCrmexpense = crmexpenseCollectionNewCrmexpense.getCompanycustomer();
                    crmexpenseCollectionNewCrmexpense.setCompanycustomer(companycustomer);
                    crmexpenseCollectionNewCrmexpense = em.merge(crmexpenseCollectionNewCrmexpense);
                    if (oldCompanycustomerOfCrmexpenseCollectionNewCrmexpense != null && !oldCompanycustomerOfCrmexpenseCollectionNewCrmexpense.equals(companycustomer)) {
                        oldCompanycustomerOfCrmexpenseCollectionNewCrmexpense.getCrmexpenseCollection().remove(crmexpenseCollectionNewCrmexpense);
                        oldCompanycustomerOfCrmexpenseCollectionNewCrmexpense = em.merge(oldCompanycustomerOfCrmexpenseCollectionNewCrmexpense);
                    }
                }
            }
            for (Crmworkorder crmworkorderCollectionNewCrmworkorder : crmworkorderCollectionNew) {
                if (!crmworkorderCollectionOld.contains(crmworkorderCollectionNewCrmworkorder)) {
                    Companycustomer oldCompanycustomerOfCrmworkorderCollectionNewCrmworkorder = crmworkorderCollectionNewCrmworkorder.getCompanycustomer();
                    crmworkorderCollectionNewCrmworkorder.setCompanycustomer(companycustomer);
                    crmworkorderCollectionNewCrmworkorder = em.merge(crmworkorderCollectionNewCrmworkorder);
                    if (oldCompanycustomerOfCrmworkorderCollectionNewCrmworkorder != null && !oldCompanycustomerOfCrmworkorderCollectionNewCrmworkorder.equals(companycustomer)) {
                        oldCompanycustomerOfCrmworkorderCollectionNewCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionNewCrmworkorder);
                        oldCompanycustomerOfCrmworkorderCollectionNewCrmworkorder = em.merge(oldCompanycustomerOfCrmworkorderCollectionNewCrmworkorder);
                    }
                }
            }
            for (Crmlabor crmlaborCollectionNewCrmlabor : crmlaborCollectionNew) {
                if (!crmlaborCollectionOld.contains(crmlaborCollectionNewCrmlabor)) {
                    Companycustomer oldCompanycustomerOfCrmlaborCollectionNewCrmlabor = crmlaborCollectionNewCrmlabor.getCompanycustomer();
                    crmlaborCollectionNewCrmlabor.setCompanycustomer(companycustomer);
                    crmlaborCollectionNewCrmlabor = em.merge(crmlaborCollectionNewCrmlabor);
                    if (oldCompanycustomerOfCrmlaborCollectionNewCrmlabor != null && !oldCompanycustomerOfCrmlaborCollectionNewCrmlabor.equals(companycustomer)) {
                        oldCompanycustomerOfCrmlaborCollectionNewCrmlabor.getCrmlaborCollection().remove(crmlaborCollectionNewCrmlabor);
                        oldCompanycustomerOfCrmlaborCollectionNewCrmlabor = em.merge(oldCompanycustomerOfCrmlaborCollectionNewCrmlabor);
                    }
                }
            }
            for (Customerbank customerbankCollectionNewCustomerbank : customerbankCollectionNew) {
                if (!customerbankCollectionOld.contains(customerbankCollectionNewCustomerbank)) {
                    Companycustomer oldCompanycustomerOfCustomerbankCollectionNewCustomerbank = customerbankCollectionNewCustomerbank.getCompanycustomer();
                    customerbankCollectionNewCustomerbank.setCompanycustomer(companycustomer);
                    customerbankCollectionNewCustomerbank = em.merge(customerbankCollectionNewCustomerbank);
                    if (oldCompanycustomerOfCustomerbankCollectionNewCustomerbank != null && !oldCompanycustomerOfCustomerbankCollectionNewCustomerbank.equals(companycustomer)) {
                        oldCompanycustomerOfCustomerbankCollectionNewCustomerbank.getCustomerbankCollection().remove(customerbankCollectionNewCustomerbank);
                        oldCompanycustomerOfCustomerbankCollectionNewCustomerbank = em.merge(oldCompanycustomerOfCustomerbankCollectionNewCustomerbank);
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
                CompanycustomerPK id = companycustomer.getCompanycustomerPK();
                if (findCompanycustomer(id) == null) {
                    throw new NonexistentEntityException("The companycustomer with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanycustomerPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companycustomer companycustomer;
            try {
                companycustomer = em.getReference(Companycustomer.class, id);
                companycustomer.getCompanycustomerPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companycustomer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmexpense> crmexpenseCollectionOrphanCheck = companycustomer.getCrmexpenseCollection();
            for (Crmexpense crmexpenseCollectionOrphanCheckCrmexpense : crmexpenseCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companycustomer (" + companycustomer + ") cannot be destroyed since the Crmexpense " + crmexpenseCollectionOrphanCheckCrmexpense + " in its crmexpenseCollection field has a non-nullable companycustomer field.");
            }
            Collection<Crmworkorder> crmworkorderCollectionOrphanCheck = companycustomer.getCrmworkorderCollection();
            for (Crmworkorder crmworkorderCollectionOrphanCheckCrmworkorder : crmworkorderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companycustomer (" + companycustomer + ") cannot be destroyed since the Crmworkorder " + crmworkorderCollectionOrphanCheckCrmworkorder + " in its crmworkorderCollection field has a non-nullable companycustomer field.");
            }
            Collection<Crmlabor> crmlaborCollectionOrphanCheck = companycustomer.getCrmlaborCollection();
            for (Crmlabor crmlaborCollectionOrphanCheckCrmlabor : crmlaborCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companycustomer (" + companycustomer + ") cannot be destroyed since the Crmlabor " + crmlaborCollectionOrphanCheckCrmlabor + " in its crmlaborCollection field has a non-nullable companycustomer field.");
            }
            Collection<Customerbank> customerbankCollectionOrphanCheck = companycustomer.getCustomerbankCollection();
            for (Customerbank customerbankCollectionOrphanCheckCustomerbank : customerbankCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companycustomer (" + companycustomer + ") cannot be destroyed since the Customerbank " + customerbankCollectionOrphanCheckCustomerbank + " in its customerbankCollection field has a non-nullable companycustomer field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companycustomer.getCompany();
            if (company != null) {
                company.getCompanycustomerCollection().remove(companycustomer);
                company = em.merge(company);
            }
            Companyemployee companyemployee = companycustomer.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanycustomerCollection().remove(companycustomer);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companycustomer.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanycustomerCollection().remove(companycustomer);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companycustomer);
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

    public List<Companycustomer> findCompanycustomerEntities() {
        return findCompanycustomerEntities(true, -1, -1);
    }

    public List<Companycustomer> findCompanycustomerEntities(int maxResults, int firstResult) {
        return findCompanycustomerEntities(false, maxResults, firstResult);
    }

    private List<Companycustomer> findCompanycustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companycustomer.class));
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

    public Companycustomer findCompanycustomer(CompanycustomerPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companycustomer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanycustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companycustomer> rt = cq.from(Companycustomer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
