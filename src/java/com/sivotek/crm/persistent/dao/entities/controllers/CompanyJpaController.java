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
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Category;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Crmmessagechannel;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Companybank;
import com.sivotek.crm.persistent.dao.entities.Crmprojectprops;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignreceiver;
import com.sivotek.crm.persistent.dao.entities.Productattachments;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignstatus;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignposition;
import com.sivotek.crm.persistent.dao.entities.Companydepartment;
import com.sivotek.crm.persistent.dao.entities.Crmusermodules;
import com.sivotek.crm.persistent.dao.entities.Crmexpense;
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.Companycontacts;
import com.sivotek.crm.persistent.dao.entities.Companyproducttype;
import com.sivotek.crm.persistent.dao.entities.Crmcampaign;
import com.sivotek.crm.persistent.dao.entities.Companyhirarchie;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackdocs;
import com.sivotek.crm.persistent.dao.entities.Crmlabor;
import com.sivotek.crm.persistent.dao.entities.Compnaypaymentcurrency;
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.Employeedesignation;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstack;
import com.sivotek.crm.persistent.dao.entities.Componentattachments;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackcd;
import com.sivotek.crm.persistent.dao.entities.Crmworkordertype;
import com.sivotek.crm.persistent.dao.entities.Crmlabortype;
import com.sivotek.crm.persistent.dao.entities.Crmbillingtype;
import com.sivotek.crm.persistent.dao.entities.Companyproductcategory;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignprops;
import com.sivotek.crm.persistent.dao.entities.Customercategory;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.Crmexpensetype;
import com.sivotek.crm.persistent.dao.entities.Companypayments;
import com.sivotek.crm.persistent.dao.entities.Crmworkorderresolution;
import com.sivotek.crm.persistent.dao.entities.Companyaddresstype;
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.Crmprojectstatus;
import com.sivotek.crm.persistent.dao.entities.Crmschedulerevnttype;
import com.sivotek.crm.persistent.dao.entities.Componenttype;
import com.sivotek.crm.persistent.dao.entities.Companypaymentscheme;
import com.sivotek.crm.persistent.dao.entities.Companycontactsaddress;
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
public class CompanyJpaController implements Serializable {

    public CompanyJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
      public CompanyJpaController(){
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

    public void create(Company company) throws IllegalOrphanException, PreexistingEntityException, RollbackFailureException, Exception {
        if (company.getCompanyPK() == null) {
            company.setCompanyPK(new CompanyPK());
        }
        if (company.getCrmmessagechannelCollection() == null) {
            company.setCrmmessagechannelCollection(new ArrayList<Crmmessagechannel>());
        }
        if (company.getCompanybankCollection() == null) {
            company.setCompanybankCollection(new ArrayList<Companybank>());
        }
        if (company.getCrmprojectpropsCollection() == null) {
            company.setCrmprojectpropsCollection(new ArrayList<Crmprojectprops>());
        }
        if (company.getCrmcampaignreceiverCollection() == null) {
            company.setCrmcampaignreceiverCollection(new ArrayList<Crmcampaignreceiver>());
        }
        if (company.getProductattachmentsCollection() == null) {
            company.setProductattachmentsCollection(new ArrayList<Productattachments>());
        }
        if (company.getCrmcampaignstatusCollection() == null) {
            company.setCrmcampaignstatusCollection(new ArrayList<Crmcampaignstatus>());
        }
        if (company.getCrmcampaignpositionCollection() == null) {
            company.setCrmcampaignpositionCollection(new ArrayList<Crmcampaignposition>());
        }
        if (company.getCompanydepartmentCollection() == null) {
            company.setCompanydepartmentCollection(new ArrayList<Companydepartment>());
        }
        if (company.getCrmusermodulesCollection() == null) {
            company.setCrmusermodulesCollection(new ArrayList<Crmusermodules>());
        }
        if (company.getCrmexpenseCollection() == null) {
            company.setCrmexpenseCollection(new ArrayList<Crmexpense>());
        }
        if (company.getCrmworkorderCollection() == null) {
            company.setCrmworkorderCollection(new ArrayList<Crmworkorder>());
        }
        if (company.getCompanycontactsCollection() == null) {
            company.setCompanycontactsCollection(new ArrayList<Companycontacts>());
        }
        if (company.getCompanyemployeeCollection() == null) {
            company.setCompanyemployeeCollection(new ArrayList<Companyemployee>());
        }
        if (company.getCompanyproducttypeCollection() == null) {
            company.setCompanyproducttypeCollection(new ArrayList<Companyproducttype>());
        }
        if (company.getCrmcampaignCollection() == null) {
            company.setCrmcampaignCollection(new ArrayList<Crmcampaign>());
        }
        if (company.getCompanyhirarchieCollection() == null) {
            company.setCompanyhirarchieCollection(new ArrayList<Companyhirarchie>());
        }
        if (company.getCompanyhirarchieCollection1() == null) {
            company.setCompanyhirarchieCollection1(new ArrayList<Companyhirarchie>());
        }
        if (company.getCompanyhirarchieCollection2() == null) {
            company.setCompanyhirarchieCollection2(new ArrayList<Companyhirarchie>());
        }
        if (company.getCompanyaccountstackdocsCollection() == null) {
            company.setCompanyaccountstackdocsCollection(new ArrayList<Companyaccountstackdocs>());
        }
        if (company.getCrmlaborCollection() == null) {
            company.setCrmlaborCollection(new ArrayList<Crmlabor>());
        }
        if (company.getCompnaypaymentcurrencyCollection() == null) {
            company.setCompnaypaymentcurrencyCollection(new ArrayList<Compnaypaymentcurrency>());
        }
        if (company.getCompanyproductCollection() == null) {
            company.setCompanyproductCollection(new ArrayList<Companyproduct>());
        }
        if (company.getEmployeedesignationCollection() == null) {
            company.setEmployeedesignationCollection(new ArrayList<Employeedesignation>());
        }
        if (company.getCompanyaccountstackCollection() == null) {
            company.setCompanyaccountstackCollection(new ArrayList<Companyaccountstack>());
        }
        if (company.getComponentattachmentsCollection() == null) {
            company.setComponentattachmentsCollection(new ArrayList<Componentattachments>());
        }
        if (company.getCompanyaccountstackcdCollection() == null) {
            company.setCompanyaccountstackcdCollection(new ArrayList<Companyaccountstackcd>());
        }
        if (company.getCrmworkordertypeCollection() == null) {
            company.setCrmworkordertypeCollection(new ArrayList<Crmworkordertype>());
        }
        if (company.getCrmlabortypeCollection() == null) {
            company.setCrmlabortypeCollection(new ArrayList<Crmlabortype>());
        }
        if (company.getCrmbillingtypeCollection() == null) {
            company.setCrmbillingtypeCollection(new ArrayList<Crmbillingtype>());
        }
        if (company.getCompanyproductcategoryCollection() == null) {
            company.setCompanyproductcategoryCollection(new ArrayList<Companyproductcategory>());
        }
        if (company.getCrmcampaignpropsCollection() == null) {
            company.setCrmcampaignpropsCollection(new ArrayList<Crmcampaignprops>());
        }
        if (company.getCustomercategoryCollection() == null) {
            company.setCustomercategoryCollection(new ArrayList<Customercategory>());
        }
        if (company.getProductcomponentsCollection() == null) {
            company.setProductcomponentsCollection(new ArrayList<Productcomponents>());
        }
        if (company.getCrmexpensetypeCollection() == null) {
            company.setCrmexpensetypeCollection(new ArrayList<Crmexpensetype>());
        }
        if (company.getCompanypaymentsCollection() == null) {
            company.setCompanypaymentsCollection(new ArrayList<Companypayments>());
        }
        if (company.getCrmworkorderresolutionCollection() == null) {
            company.setCrmworkorderresolutionCollection(new ArrayList<Crmworkorderresolution>());
        }
        if (company.getCompanyaddresstypeCollection() == null) {
            company.setCompanyaddresstypeCollection(new ArrayList<Companyaddresstype>());
        }
        if (company.getCompanycustomerCollection() == null) {
            company.setCompanycustomerCollection(new ArrayList<Companycustomer>());
        }
        if (company.getCrmprojectstatusCollection() == null) {
            company.setCrmprojectstatusCollection(new ArrayList<Crmprojectstatus>());
        }
        if (company.getCrmschedulerevnttypeCollection() == null) {
            company.setCrmschedulerevnttypeCollection(new ArrayList<Crmschedulerevnttype>());
        }
        if (company.getComponenttypeCollection() == null) {
            company.setComponenttypeCollection(new ArrayList<Componenttype>());
        }
        if (company.getCompanypaymentschemeCollection() == null) {
            company.setCompanypaymentschemeCollection(new ArrayList<Companypaymentscheme>());
        }
        if (company.getCompanycontactsaddressCollection() == null) {
            company.setCompanycontactsaddressCollection(new ArrayList<Companycontactsaddress>());
        }
        List<String> illegalOrphanMessages = null;
        Companyemployee companyemployeeOrphanCheck = company.getCompanyemployee();
        if (companyemployeeOrphanCheck != null) {
            Company oldCompanyOfCompanyemployee = companyemployeeOrphanCheck.getCompany();
            if (oldCompanyOfCompanyemployee != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Companyemployee " + companyemployeeOrphanCheck + " already has an item of type Company whose companyemployee column cannot be null. Please make another selection for the companyemployee field.");
            }
        }
        Companyemployee companyemployee1OrphanCheck = company.getCompanyemployee1();
        if (companyemployee1OrphanCheck != null) {
            Company oldCompanyOfCompanyemployee1 = companyemployee1OrphanCheck.getCompany();
            if (oldCompanyOfCompanyemployee1 != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Companyemployee " + companyemployee1OrphanCheck + " already has an item of type Company whose companyemployee1 column cannot be null. Please make another selection for the companyemployee1 field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = company.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                company.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = company.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                company.setCompanyemployee1(companyemployee1);
            }
            Category category = company.getCategory();
            if (category != null) {
                category = em.getReference(category.getClass(), category.getId());
                company.setCategory(category);
            }
            Collection<Crmmessagechannel> attachedCrmmessagechannelCollection = new ArrayList<Crmmessagechannel>();
            for (Crmmessagechannel crmmessagechannelCollectionCrmmessagechannelToAttach : company.getCrmmessagechannelCollection()) {
                crmmessagechannelCollectionCrmmessagechannelToAttach = em.getReference(crmmessagechannelCollectionCrmmessagechannelToAttach.getClass(), crmmessagechannelCollectionCrmmessagechannelToAttach.getCrmmessagechannelPK());
                attachedCrmmessagechannelCollection.add(crmmessagechannelCollectionCrmmessagechannelToAttach);
            }
            company.setCrmmessagechannelCollection(attachedCrmmessagechannelCollection);
            Collection<Companybank> attachedCompanybankCollection = new ArrayList<Companybank>();
            for (Companybank companybankCollectionCompanybankToAttach : company.getCompanybankCollection()) {
                companybankCollectionCompanybankToAttach = em.getReference(companybankCollectionCompanybankToAttach.getClass(), companybankCollectionCompanybankToAttach.getCompanybankPK());
                attachedCompanybankCollection.add(companybankCollectionCompanybankToAttach);
            }
            company.setCompanybankCollection(attachedCompanybankCollection);
            Collection<Crmprojectprops> attachedCrmprojectpropsCollection = new ArrayList<Crmprojectprops>();
            for (Crmprojectprops crmprojectpropsCollectionCrmprojectpropsToAttach : company.getCrmprojectpropsCollection()) {
                crmprojectpropsCollectionCrmprojectpropsToAttach = em.getReference(crmprojectpropsCollectionCrmprojectpropsToAttach.getClass(), crmprojectpropsCollectionCrmprojectpropsToAttach.getCrmprojectpropsPK());
                attachedCrmprojectpropsCollection.add(crmprojectpropsCollectionCrmprojectpropsToAttach);
            }
            company.setCrmprojectpropsCollection(attachedCrmprojectpropsCollection);
            Collection<Crmcampaignreceiver> attachedCrmcampaignreceiverCollection = new ArrayList<Crmcampaignreceiver>();
            for (Crmcampaignreceiver crmcampaignreceiverCollectionCrmcampaignreceiverToAttach : company.getCrmcampaignreceiverCollection()) {
                crmcampaignreceiverCollectionCrmcampaignreceiverToAttach = em.getReference(crmcampaignreceiverCollectionCrmcampaignreceiverToAttach.getClass(), crmcampaignreceiverCollectionCrmcampaignreceiverToAttach.getCrmcampaignreceiverPK());
                attachedCrmcampaignreceiverCollection.add(crmcampaignreceiverCollectionCrmcampaignreceiverToAttach);
            }
            company.setCrmcampaignreceiverCollection(attachedCrmcampaignreceiverCollection);
            Collection<Productattachments> attachedProductattachmentsCollection = new ArrayList<Productattachments>();
            for (Productattachments productattachmentsCollectionProductattachmentsToAttach : company.getProductattachmentsCollection()) {
                productattachmentsCollectionProductattachmentsToAttach = em.getReference(productattachmentsCollectionProductattachmentsToAttach.getClass(), productattachmentsCollectionProductattachmentsToAttach.getProductattachmentsPK());
                attachedProductattachmentsCollection.add(productattachmentsCollectionProductattachmentsToAttach);
            }
            company.setProductattachmentsCollection(attachedProductattachmentsCollection);
            Collection<Crmcampaignstatus> attachedCrmcampaignstatusCollection = new ArrayList<Crmcampaignstatus>();
            for (Crmcampaignstatus crmcampaignstatusCollectionCrmcampaignstatusToAttach : company.getCrmcampaignstatusCollection()) {
                crmcampaignstatusCollectionCrmcampaignstatusToAttach = em.getReference(crmcampaignstatusCollectionCrmcampaignstatusToAttach.getClass(), crmcampaignstatusCollectionCrmcampaignstatusToAttach.getCrmcampaignstatusPK());
                attachedCrmcampaignstatusCollection.add(crmcampaignstatusCollectionCrmcampaignstatusToAttach);
            }
            company.setCrmcampaignstatusCollection(attachedCrmcampaignstatusCollection);
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollection = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollectionCrmcampaignpositionToAttach : company.getCrmcampaignpositionCollection()) {
                crmcampaignpositionCollectionCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollectionCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollectionCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollection.add(crmcampaignpositionCollectionCrmcampaignpositionToAttach);
            }
            company.setCrmcampaignpositionCollection(attachedCrmcampaignpositionCollection);
            Collection<Companydepartment> attachedCompanydepartmentCollection = new ArrayList<Companydepartment>();
            for (Companydepartment companydepartmentCollectionCompanydepartmentToAttach : company.getCompanydepartmentCollection()) {
                companydepartmentCollectionCompanydepartmentToAttach = em.getReference(companydepartmentCollectionCompanydepartmentToAttach.getClass(), companydepartmentCollectionCompanydepartmentToAttach.getCompanydepartmentPK());
                attachedCompanydepartmentCollection.add(companydepartmentCollectionCompanydepartmentToAttach);
            }
            company.setCompanydepartmentCollection(attachedCompanydepartmentCollection);
            Collection<Crmusermodules> attachedCrmusermodulesCollection = new ArrayList<Crmusermodules>();
            for (Crmusermodules crmusermodulesCollectionCrmusermodulesToAttach : company.getCrmusermodulesCollection()) {
                crmusermodulesCollectionCrmusermodulesToAttach = em.getReference(crmusermodulesCollectionCrmusermodulesToAttach.getClass(), crmusermodulesCollectionCrmusermodulesToAttach.getCrmusermodulesPK());
                attachedCrmusermodulesCollection.add(crmusermodulesCollectionCrmusermodulesToAttach);
            }
            company.setCrmusermodulesCollection(attachedCrmusermodulesCollection);
            Collection<Crmexpense> attachedCrmexpenseCollection = new ArrayList<Crmexpense>();
            for (Crmexpense crmexpenseCollectionCrmexpenseToAttach : company.getCrmexpenseCollection()) {
                crmexpenseCollectionCrmexpenseToAttach = em.getReference(crmexpenseCollectionCrmexpenseToAttach.getClass(), crmexpenseCollectionCrmexpenseToAttach.getCrmexpensePK());
                attachedCrmexpenseCollection.add(crmexpenseCollectionCrmexpenseToAttach);
            }
            company.setCrmexpenseCollection(attachedCrmexpenseCollection);
            Collection<Crmworkorder> attachedCrmworkorderCollection = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionCrmworkorderToAttach : company.getCrmworkorderCollection()) {
                crmworkorderCollectionCrmworkorderToAttach = em.getReference(crmworkorderCollectionCrmworkorderToAttach.getClass(), crmworkorderCollectionCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollection.add(crmworkorderCollectionCrmworkorderToAttach);
            }
            company.setCrmworkorderCollection(attachedCrmworkorderCollection);
            Collection<Companycontacts> attachedCompanycontactsCollection = new ArrayList<Companycontacts>();
            for (Companycontacts companycontactsCollectionCompanycontactsToAttach : company.getCompanycontactsCollection()) {
                companycontactsCollectionCompanycontactsToAttach = em.getReference(companycontactsCollectionCompanycontactsToAttach.getClass(), companycontactsCollectionCompanycontactsToAttach.getCompanycontactsPK());
                attachedCompanycontactsCollection.add(companycontactsCollectionCompanycontactsToAttach);
            }
            company.setCompanycontactsCollection(attachedCompanycontactsCollection);
            Collection<Companyemployee> attachedCompanyemployeeCollection = new ArrayList<Companyemployee>();
            for (Companyemployee companyemployeeCollectionCompanyemployeeToAttach : company.getCompanyemployeeCollection()) {
                companyemployeeCollectionCompanyemployeeToAttach = em.getReference(companyemployeeCollectionCompanyemployeeToAttach.getClass(), companyemployeeCollectionCompanyemployeeToAttach.getCompanyemployeePK());
                attachedCompanyemployeeCollection.add(companyemployeeCollectionCompanyemployeeToAttach);
            }
            company.setCompanyemployeeCollection(attachedCompanyemployeeCollection);
            Collection<Companyproducttype> attachedCompanyproducttypeCollection = new ArrayList<Companyproducttype>();
            for (Companyproducttype companyproducttypeCollectionCompanyproducttypeToAttach : company.getCompanyproducttypeCollection()) {
                companyproducttypeCollectionCompanyproducttypeToAttach = em.getReference(companyproducttypeCollectionCompanyproducttypeToAttach.getClass(), companyproducttypeCollectionCompanyproducttypeToAttach.getCompanyproducttypePK());
                attachedCompanyproducttypeCollection.add(companyproducttypeCollectionCompanyproducttypeToAttach);
            }
            company.setCompanyproducttypeCollection(attachedCompanyproducttypeCollection);
            Collection<Crmcampaign> attachedCrmcampaignCollection = new ArrayList<Crmcampaign>();
            for (Crmcampaign crmcampaignCollectionCrmcampaignToAttach : company.getCrmcampaignCollection()) {
                crmcampaignCollectionCrmcampaignToAttach = em.getReference(crmcampaignCollectionCrmcampaignToAttach.getClass(), crmcampaignCollectionCrmcampaignToAttach.getCrmcampaignPK());
                attachedCrmcampaignCollection.add(crmcampaignCollectionCrmcampaignToAttach);
            }
            company.setCrmcampaignCollection(attachedCrmcampaignCollection);
            Collection<Companyhirarchie> attachedCompanyhirarchieCollection = new ArrayList<Companyhirarchie>();
            for (Companyhirarchie companyhirarchieCollectionCompanyhirarchieToAttach : company.getCompanyhirarchieCollection()) {
                companyhirarchieCollectionCompanyhirarchieToAttach = em.getReference(companyhirarchieCollectionCompanyhirarchieToAttach.getClass(), companyhirarchieCollectionCompanyhirarchieToAttach.getCompanyhirarchiePK());
                attachedCompanyhirarchieCollection.add(companyhirarchieCollectionCompanyhirarchieToAttach);
            }
            company.setCompanyhirarchieCollection(attachedCompanyhirarchieCollection);
            Collection<Companyhirarchie> attachedCompanyhirarchieCollection1 = new ArrayList<Companyhirarchie>();
            for (Companyhirarchie companyhirarchieCollection1CompanyhirarchieToAttach : company.getCompanyhirarchieCollection1()) {
                companyhirarchieCollection1CompanyhirarchieToAttach = em.getReference(companyhirarchieCollection1CompanyhirarchieToAttach.getClass(), companyhirarchieCollection1CompanyhirarchieToAttach.getCompanyhirarchiePK());
                attachedCompanyhirarchieCollection1.add(companyhirarchieCollection1CompanyhirarchieToAttach);
            }
            company.setCompanyhirarchieCollection1(attachedCompanyhirarchieCollection1);
            Collection<Companyhirarchie> attachedCompanyhirarchieCollection2 = new ArrayList<Companyhirarchie>();
            for (Companyhirarchie companyhirarchieCollection2CompanyhirarchieToAttach : company.getCompanyhirarchieCollection2()) {
                companyhirarchieCollection2CompanyhirarchieToAttach = em.getReference(companyhirarchieCollection2CompanyhirarchieToAttach.getClass(), companyhirarchieCollection2CompanyhirarchieToAttach.getCompanyhirarchiePK());
                attachedCompanyhirarchieCollection2.add(companyhirarchieCollection2CompanyhirarchieToAttach);
            }
            company.setCompanyhirarchieCollection2(attachedCompanyhirarchieCollection2);
            Collection<Companyaccountstackdocs> attachedCompanyaccountstackdocsCollection = new ArrayList<Companyaccountstackdocs>();
            for (Companyaccountstackdocs companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach : company.getCompanyaccountstackdocsCollection()) {
                companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach = em.getReference(companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach.getClass(), companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach.getCompanyaccountstackdocsPK());
                attachedCompanyaccountstackdocsCollection.add(companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach);
            }
            company.setCompanyaccountstackdocsCollection(attachedCompanyaccountstackdocsCollection);
            Collection<Crmlabor> attachedCrmlaborCollection = new ArrayList<Crmlabor>();
            for (Crmlabor crmlaborCollectionCrmlaborToAttach : company.getCrmlaborCollection()) {
                crmlaborCollectionCrmlaborToAttach = em.getReference(crmlaborCollectionCrmlaborToAttach.getClass(), crmlaborCollectionCrmlaborToAttach.getCrmlaborPK());
                attachedCrmlaborCollection.add(crmlaborCollectionCrmlaborToAttach);
            }
            company.setCrmlaborCollection(attachedCrmlaborCollection);
            Collection<Compnaypaymentcurrency> attachedCompnaypaymentcurrencyCollection = new ArrayList<Compnaypaymentcurrency>();
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionCompnaypaymentcurrencyToAttach : company.getCompnaypaymentcurrencyCollection()) {
                compnaypaymentcurrencyCollectionCompnaypaymentcurrencyToAttach = em.getReference(compnaypaymentcurrencyCollectionCompnaypaymentcurrencyToAttach.getClass(), compnaypaymentcurrencyCollectionCompnaypaymentcurrencyToAttach.getCompnaypaymentcurrencyPK());
                attachedCompnaypaymentcurrencyCollection.add(compnaypaymentcurrencyCollectionCompnaypaymentcurrencyToAttach);
            }
            company.setCompnaypaymentcurrencyCollection(attachedCompnaypaymentcurrencyCollection);
            Collection<Companyproduct> attachedCompanyproductCollection = new ArrayList<Companyproduct>();
            for (Companyproduct companyproductCollectionCompanyproductToAttach : company.getCompanyproductCollection()) {
                companyproductCollectionCompanyproductToAttach = em.getReference(companyproductCollectionCompanyproductToAttach.getClass(), companyproductCollectionCompanyproductToAttach.getCompanyproductPK());
                attachedCompanyproductCollection.add(companyproductCollectionCompanyproductToAttach);
            }
            company.setCompanyproductCollection(attachedCompanyproductCollection);
            Collection<Employeedesignation> attachedEmployeedesignationCollection = new ArrayList<Employeedesignation>();
            for (Employeedesignation employeedesignationCollectionEmployeedesignationToAttach : company.getEmployeedesignationCollection()) {
                employeedesignationCollectionEmployeedesignationToAttach = em.getReference(employeedesignationCollectionEmployeedesignationToAttach.getClass(), employeedesignationCollectionEmployeedesignationToAttach.getEmployeedesignationPK());
                attachedEmployeedesignationCollection.add(employeedesignationCollectionEmployeedesignationToAttach);
            }
            company.setEmployeedesignationCollection(attachedEmployeedesignationCollection);
            Collection<Companyaccountstack> attachedCompanyaccountstackCollection = new ArrayList<Companyaccountstack>();
            for (Companyaccountstack companyaccountstackCollectionCompanyaccountstackToAttach : company.getCompanyaccountstackCollection()) {
                companyaccountstackCollectionCompanyaccountstackToAttach = em.getReference(companyaccountstackCollectionCompanyaccountstackToAttach.getClass(), companyaccountstackCollectionCompanyaccountstackToAttach.getCompanyaccountstackPK());
                attachedCompanyaccountstackCollection.add(companyaccountstackCollectionCompanyaccountstackToAttach);
            }
            company.setCompanyaccountstackCollection(attachedCompanyaccountstackCollection);
            Collection<Componentattachments> attachedComponentattachmentsCollection = new ArrayList<Componentattachments>();
            for (Componentattachments componentattachmentsCollectionComponentattachmentsToAttach : company.getComponentattachmentsCollection()) {
                componentattachmentsCollectionComponentattachmentsToAttach = em.getReference(componentattachmentsCollectionComponentattachmentsToAttach.getClass(), componentattachmentsCollectionComponentattachmentsToAttach.getComponentattachmentsPK());
                attachedComponentattachmentsCollection.add(componentattachmentsCollectionComponentattachmentsToAttach);
            }
            company.setComponentattachmentsCollection(attachedComponentattachmentsCollection);
            Collection<Companyaccountstackcd> attachedCompanyaccountstackcdCollection = new ArrayList<Companyaccountstackcd>();
            for (Companyaccountstackcd companyaccountstackcdCollectionCompanyaccountstackcdToAttach : company.getCompanyaccountstackcdCollection()) {
                companyaccountstackcdCollectionCompanyaccountstackcdToAttach = em.getReference(companyaccountstackcdCollectionCompanyaccountstackcdToAttach.getClass(), companyaccountstackcdCollectionCompanyaccountstackcdToAttach.getCompanyaccountstackcdPK());
                attachedCompanyaccountstackcdCollection.add(companyaccountstackcdCollectionCompanyaccountstackcdToAttach);
            }
            company.setCompanyaccountstackcdCollection(attachedCompanyaccountstackcdCollection);
            Collection<Crmworkordertype> attachedCrmworkordertypeCollection = new ArrayList<Crmworkordertype>();
            for (Crmworkordertype crmworkordertypeCollectionCrmworkordertypeToAttach : company.getCrmworkordertypeCollection()) {
                crmworkordertypeCollectionCrmworkordertypeToAttach = em.getReference(crmworkordertypeCollectionCrmworkordertypeToAttach.getClass(), crmworkordertypeCollectionCrmworkordertypeToAttach.getCrmworkordertypePK());
                attachedCrmworkordertypeCollection.add(crmworkordertypeCollectionCrmworkordertypeToAttach);
            }
            company.setCrmworkordertypeCollection(attachedCrmworkordertypeCollection);
            Collection<Crmlabortype> attachedCrmlabortypeCollection = new ArrayList<Crmlabortype>();
            for (Crmlabortype crmlabortypeCollectionCrmlabortypeToAttach : company.getCrmlabortypeCollection()) {
                crmlabortypeCollectionCrmlabortypeToAttach = em.getReference(crmlabortypeCollectionCrmlabortypeToAttach.getClass(), crmlabortypeCollectionCrmlabortypeToAttach.getCrmlabortypePK());
                attachedCrmlabortypeCollection.add(crmlabortypeCollectionCrmlabortypeToAttach);
            }
            company.setCrmlabortypeCollection(attachedCrmlabortypeCollection);
            Collection<Crmbillingtype> attachedCrmbillingtypeCollection = new ArrayList<Crmbillingtype>();
            for (Crmbillingtype crmbillingtypeCollectionCrmbillingtypeToAttach : company.getCrmbillingtypeCollection()) {
                crmbillingtypeCollectionCrmbillingtypeToAttach = em.getReference(crmbillingtypeCollectionCrmbillingtypeToAttach.getClass(), crmbillingtypeCollectionCrmbillingtypeToAttach.getCrmbillingtypePK());
                attachedCrmbillingtypeCollection.add(crmbillingtypeCollectionCrmbillingtypeToAttach);
            }
            company.setCrmbillingtypeCollection(attachedCrmbillingtypeCollection);
            Collection<Companyproductcategory> attachedCompanyproductcategoryCollection = new ArrayList<Companyproductcategory>();
            for (Companyproductcategory companyproductcategoryCollectionCompanyproductcategoryToAttach : company.getCompanyproductcategoryCollection()) {
                companyproductcategoryCollectionCompanyproductcategoryToAttach = em.getReference(companyproductcategoryCollectionCompanyproductcategoryToAttach.getClass(), companyproductcategoryCollectionCompanyproductcategoryToAttach.getCompanyproductcategoryPK());
                attachedCompanyproductcategoryCollection.add(companyproductcategoryCollectionCompanyproductcategoryToAttach);
            }
            company.setCompanyproductcategoryCollection(attachedCompanyproductcategoryCollection);
            Collection<Crmcampaignprops> attachedCrmcampaignpropsCollection = new ArrayList<Crmcampaignprops>();
            for (Crmcampaignprops crmcampaignpropsCollectionCrmcampaignpropsToAttach : company.getCrmcampaignpropsCollection()) {
                crmcampaignpropsCollectionCrmcampaignpropsToAttach = em.getReference(crmcampaignpropsCollectionCrmcampaignpropsToAttach.getClass(), crmcampaignpropsCollectionCrmcampaignpropsToAttach.getCrmcampaignpropsPK());
                attachedCrmcampaignpropsCollection.add(crmcampaignpropsCollectionCrmcampaignpropsToAttach);
            }
            company.setCrmcampaignpropsCollection(attachedCrmcampaignpropsCollection);
            Collection<Customercategory> attachedCustomercategoryCollection = new ArrayList<Customercategory>();
            for (Customercategory customercategoryCollectionCustomercategoryToAttach : company.getCustomercategoryCollection()) {
                customercategoryCollectionCustomercategoryToAttach = em.getReference(customercategoryCollectionCustomercategoryToAttach.getClass(), customercategoryCollectionCustomercategoryToAttach.getCustomercategoryPK());
                attachedCustomercategoryCollection.add(customercategoryCollectionCustomercategoryToAttach);
            }
            company.setCustomercategoryCollection(attachedCustomercategoryCollection);
            Collection<Productcomponents> attachedProductcomponentsCollection = new ArrayList<Productcomponents>();
            for (Productcomponents productcomponentsCollectionProductcomponentsToAttach : company.getProductcomponentsCollection()) {
                productcomponentsCollectionProductcomponentsToAttach = em.getReference(productcomponentsCollectionProductcomponentsToAttach.getClass(), productcomponentsCollectionProductcomponentsToAttach.getProductcomponentsPK());
                attachedProductcomponentsCollection.add(productcomponentsCollectionProductcomponentsToAttach);
            }
            company.setProductcomponentsCollection(attachedProductcomponentsCollection);
            Collection<Crmexpensetype> attachedCrmexpensetypeCollection = new ArrayList<Crmexpensetype>();
            for (Crmexpensetype crmexpensetypeCollectionCrmexpensetypeToAttach : company.getCrmexpensetypeCollection()) {
                crmexpensetypeCollectionCrmexpensetypeToAttach = em.getReference(crmexpensetypeCollectionCrmexpensetypeToAttach.getClass(), crmexpensetypeCollectionCrmexpensetypeToAttach.getCrmexpensetypePK());
                attachedCrmexpensetypeCollection.add(crmexpensetypeCollectionCrmexpensetypeToAttach);
            }
            company.setCrmexpensetypeCollection(attachedCrmexpensetypeCollection);
            Collection<Companypayments> attachedCompanypaymentsCollection = new ArrayList<Companypayments>();
            for (Companypayments companypaymentsCollectionCompanypaymentsToAttach : company.getCompanypaymentsCollection()) {
                companypaymentsCollectionCompanypaymentsToAttach = em.getReference(companypaymentsCollectionCompanypaymentsToAttach.getClass(), companypaymentsCollectionCompanypaymentsToAttach.getCompanypaymentsPK());
                attachedCompanypaymentsCollection.add(companypaymentsCollectionCompanypaymentsToAttach);
            }
            company.setCompanypaymentsCollection(attachedCompanypaymentsCollection);
            Collection<Crmworkorderresolution> attachedCrmworkorderresolutionCollection = new ArrayList<Crmworkorderresolution>();
            for (Crmworkorderresolution crmworkorderresolutionCollectionCrmworkorderresolutionToAttach : company.getCrmworkorderresolutionCollection()) {
                crmworkorderresolutionCollectionCrmworkorderresolutionToAttach = em.getReference(crmworkorderresolutionCollectionCrmworkorderresolutionToAttach.getClass(), crmworkorderresolutionCollectionCrmworkorderresolutionToAttach.getCrmworkorderresolutionPK());
                attachedCrmworkorderresolutionCollection.add(crmworkorderresolutionCollectionCrmworkorderresolutionToAttach);
            }
            company.setCrmworkorderresolutionCollection(attachedCrmworkorderresolutionCollection);
            Collection<Companyaddresstype> attachedCompanyaddresstypeCollection = new ArrayList<Companyaddresstype>();
            for (Companyaddresstype companyaddresstypeCollectionCompanyaddresstypeToAttach : company.getCompanyaddresstypeCollection()) {
                companyaddresstypeCollectionCompanyaddresstypeToAttach = em.getReference(companyaddresstypeCollectionCompanyaddresstypeToAttach.getClass(), companyaddresstypeCollectionCompanyaddresstypeToAttach.getCompanyaddresstypePK());
                attachedCompanyaddresstypeCollection.add(companyaddresstypeCollectionCompanyaddresstypeToAttach);
            }
            company.setCompanyaddresstypeCollection(attachedCompanyaddresstypeCollection);
            Collection<Companycustomer> attachedCompanycustomerCollection = new ArrayList<Companycustomer>();
            for (Companycustomer companycustomerCollectionCompanycustomerToAttach : company.getCompanycustomerCollection()) {
                companycustomerCollectionCompanycustomerToAttach = em.getReference(companycustomerCollectionCompanycustomerToAttach.getClass(), companycustomerCollectionCompanycustomerToAttach.getCompanycustomerPK());
                attachedCompanycustomerCollection.add(companycustomerCollectionCompanycustomerToAttach);
            }
            company.setCompanycustomerCollection(attachedCompanycustomerCollection);
            Collection<Crmprojectstatus> attachedCrmprojectstatusCollection = new ArrayList<Crmprojectstatus>();
            for (Crmprojectstatus crmprojectstatusCollectionCrmprojectstatusToAttach : company.getCrmprojectstatusCollection()) {
                crmprojectstatusCollectionCrmprojectstatusToAttach = em.getReference(crmprojectstatusCollectionCrmprojectstatusToAttach.getClass(), crmprojectstatusCollectionCrmprojectstatusToAttach.getCrmprojectstatusPK());
                attachedCrmprojectstatusCollection.add(crmprojectstatusCollectionCrmprojectstatusToAttach);
            }
            company.setCrmprojectstatusCollection(attachedCrmprojectstatusCollection);
            Collection<Crmschedulerevnttype> attachedCrmschedulerevnttypeCollection = new ArrayList<Crmschedulerevnttype>();
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionCrmschedulerevnttypeToAttach : company.getCrmschedulerevnttypeCollection()) {
                crmschedulerevnttypeCollectionCrmschedulerevnttypeToAttach = em.getReference(crmschedulerevnttypeCollectionCrmschedulerevnttypeToAttach.getClass(), crmschedulerevnttypeCollectionCrmschedulerevnttypeToAttach.getCrmschedulerevnttypePK());
                attachedCrmschedulerevnttypeCollection.add(crmschedulerevnttypeCollectionCrmschedulerevnttypeToAttach);
            }
            company.setCrmschedulerevnttypeCollection(attachedCrmschedulerevnttypeCollection);
            Collection<Componenttype> attachedComponenttypeCollection = new ArrayList<Componenttype>();
            for (Componenttype componenttypeCollectionComponenttypeToAttach : company.getComponenttypeCollection()) {
                componenttypeCollectionComponenttypeToAttach = em.getReference(componenttypeCollectionComponenttypeToAttach.getClass(), componenttypeCollectionComponenttypeToAttach.getComponenttypePK());
                attachedComponenttypeCollection.add(componenttypeCollectionComponenttypeToAttach);
            }
            company.setComponenttypeCollection(attachedComponenttypeCollection);
            Collection<Companypaymentscheme> attachedCompanypaymentschemeCollection = new ArrayList<Companypaymentscheme>();
            for (Companypaymentscheme companypaymentschemeCollectionCompanypaymentschemeToAttach : company.getCompanypaymentschemeCollection()) {
                companypaymentschemeCollectionCompanypaymentschemeToAttach = em.getReference(companypaymentschemeCollectionCompanypaymentschemeToAttach.getClass(), companypaymentschemeCollectionCompanypaymentschemeToAttach.getCompanypaymentschemePK());
                attachedCompanypaymentschemeCollection.add(companypaymentschemeCollectionCompanypaymentschemeToAttach);
            }
            company.setCompanypaymentschemeCollection(attachedCompanypaymentschemeCollection);
            Collection<Companycontactsaddress> attachedCompanycontactsaddressCollection = new ArrayList<Companycontactsaddress>();
            for (Companycontactsaddress companycontactsaddressCollectionCompanycontactsaddressToAttach : company.getCompanycontactsaddressCollection()) {
                companycontactsaddressCollectionCompanycontactsaddressToAttach = em.getReference(companycontactsaddressCollectionCompanycontactsaddressToAttach.getClass(), companycontactsaddressCollectionCompanycontactsaddressToAttach.getCompanycontactsaddressPK());
                attachedCompanycontactsaddressCollection.add(companycontactsaddressCollectionCompanycontactsaddressToAttach);
            }
            company.setCompanycontactsaddressCollection(attachedCompanycontactsaddressCollection);
            em.persist(company);
            if (companyemployee != null) {
                companyemployee.setCompany(company);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.setCompany(company);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (category != null) {
                category.getCompanyCollection().add(company);
                category = em.merge(category);
            }
            for (Crmmessagechannel crmmessagechannelCollectionCrmmessagechannel : company.getCrmmessagechannelCollection()) {
                Company oldCompanyOfCrmmessagechannelCollectionCrmmessagechannel = crmmessagechannelCollectionCrmmessagechannel.getCompany();
                crmmessagechannelCollectionCrmmessagechannel.setCompany(company);
                crmmessagechannelCollectionCrmmessagechannel = em.merge(crmmessagechannelCollectionCrmmessagechannel);
                if (oldCompanyOfCrmmessagechannelCollectionCrmmessagechannel != null) {
                    oldCompanyOfCrmmessagechannelCollectionCrmmessagechannel.getCrmmessagechannelCollection().remove(crmmessagechannelCollectionCrmmessagechannel);
                    oldCompanyOfCrmmessagechannelCollectionCrmmessagechannel = em.merge(oldCompanyOfCrmmessagechannelCollectionCrmmessagechannel);
                }
            }
            for (Companybank companybankCollectionCompanybank : company.getCompanybankCollection()) {
                Company oldCompanyOfCompanybankCollectionCompanybank = companybankCollectionCompanybank.getCompany();
                companybankCollectionCompanybank.setCompany(company);
                companybankCollectionCompanybank = em.merge(companybankCollectionCompanybank);
                if (oldCompanyOfCompanybankCollectionCompanybank != null) {
                    oldCompanyOfCompanybankCollectionCompanybank.getCompanybankCollection().remove(companybankCollectionCompanybank);
                    oldCompanyOfCompanybankCollectionCompanybank = em.merge(oldCompanyOfCompanybankCollectionCompanybank);
                }
            }
            for (Crmprojectprops crmprojectpropsCollectionCrmprojectprops : company.getCrmprojectpropsCollection()) {
                Company oldCompanyOfCrmprojectpropsCollectionCrmprojectprops = crmprojectpropsCollectionCrmprojectprops.getCompany();
                crmprojectpropsCollectionCrmprojectprops.setCompany(company);
                crmprojectpropsCollectionCrmprojectprops = em.merge(crmprojectpropsCollectionCrmprojectprops);
                if (oldCompanyOfCrmprojectpropsCollectionCrmprojectprops != null) {
                    oldCompanyOfCrmprojectpropsCollectionCrmprojectprops.getCrmprojectpropsCollection().remove(crmprojectpropsCollectionCrmprojectprops);
                    oldCompanyOfCrmprojectpropsCollectionCrmprojectprops = em.merge(oldCompanyOfCrmprojectpropsCollectionCrmprojectprops);
                }
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollectionCrmcampaignreceiver : company.getCrmcampaignreceiverCollection()) {
                Company oldCompanyOfCrmcampaignreceiverCollectionCrmcampaignreceiver = crmcampaignreceiverCollectionCrmcampaignreceiver.getCompany();
                crmcampaignreceiverCollectionCrmcampaignreceiver.setCompany(company);
                crmcampaignreceiverCollectionCrmcampaignreceiver = em.merge(crmcampaignreceiverCollectionCrmcampaignreceiver);
                if (oldCompanyOfCrmcampaignreceiverCollectionCrmcampaignreceiver != null) {
                    oldCompanyOfCrmcampaignreceiverCollectionCrmcampaignreceiver.getCrmcampaignreceiverCollection().remove(crmcampaignreceiverCollectionCrmcampaignreceiver);
                    oldCompanyOfCrmcampaignreceiverCollectionCrmcampaignreceiver = em.merge(oldCompanyOfCrmcampaignreceiverCollectionCrmcampaignreceiver);
                }
            }
            for (Productattachments productattachmentsCollectionProductattachments : company.getProductattachmentsCollection()) {
                Company oldCompanyOfProductattachmentsCollectionProductattachments = productattachmentsCollectionProductattachments.getCompany();
                productattachmentsCollectionProductattachments.setCompany(company);
                productattachmentsCollectionProductattachments = em.merge(productattachmentsCollectionProductattachments);
                if (oldCompanyOfProductattachmentsCollectionProductattachments != null) {
                    oldCompanyOfProductattachmentsCollectionProductattachments.getProductattachmentsCollection().remove(productattachmentsCollectionProductattachments);
                    oldCompanyOfProductattachmentsCollectionProductattachments = em.merge(oldCompanyOfProductattachmentsCollectionProductattachments);
                }
            }
            for (Crmcampaignstatus crmcampaignstatusCollectionCrmcampaignstatus : company.getCrmcampaignstatusCollection()) {
                Company oldCompanyOfCrmcampaignstatusCollectionCrmcampaignstatus = crmcampaignstatusCollectionCrmcampaignstatus.getCompany();
                crmcampaignstatusCollectionCrmcampaignstatus.setCompany(company);
                crmcampaignstatusCollectionCrmcampaignstatus = em.merge(crmcampaignstatusCollectionCrmcampaignstatus);
                if (oldCompanyOfCrmcampaignstatusCollectionCrmcampaignstatus != null) {
                    oldCompanyOfCrmcampaignstatusCollectionCrmcampaignstatus.getCrmcampaignstatusCollection().remove(crmcampaignstatusCollectionCrmcampaignstatus);
                    oldCompanyOfCrmcampaignstatusCollectionCrmcampaignstatus = em.merge(oldCompanyOfCrmcampaignstatusCollectionCrmcampaignstatus);
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollectionCrmcampaignposition : company.getCrmcampaignpositionCollection()) {
                Company oldCompanyOfCrmcampaignpositionCollectionCrmcampaignposition = crmcampaignpositionCollectionCrmcampaignposition.getCompany();
                crmcampaignpositionCollectionCrmcampaignposition.setCompany(company);
                crmcampaignpositionCollectionCrmcampaignposition = em.merge(crmcampaignpositionCollectionCrmcampaignposition);
                if (oldCompanyOfCrmcampaignpositionCollectionCrmcampaignposition != null) {
                    oldCompanyOfCrmcampaignpositionCollectionCrmcampaignposition.getCrmcampaignpositionCollection().remove(crmcampaignpositionCollectionCrmcampaignposition);
                    oldCompanyOfCrmcampaignpositionCollectionCrmcampaignposition = em.merge(oldCompanyOfCrmcampaignpositionCollectionCrmcampaignposition);
                }
            }
            for (Companydepartment companydepartmentCollectionCompanydepartment : company.getCompanydepartmentCollection()) {
                Company oldCompanyOfCompanydepartmentCollectionCompanydepartment = companydepartmentCollectionCompanydepartment.getCompany();
                companydepartmentCollectionCompanydepartment.setCompany(company);
                companydepartmentCollectionCompanydepartment = em.merge(companydepartmentCollectionCompanydepartment);
                if (oldCompanyOfCompanydepartmentCollectionCompanydepartment != null) {
                    oldCompanyOfCompanydepartmentCollectionCompanydepartment.getCompanydepartmentCollection().remove(companydepartmentCollectionCompanydepartment);
                    oldCompanyOfCompanydepartmentCollectionCompanydepartment = em.merge(oldCompanyOfCompanydepartmentCollectionCompanydepartment);
                }
            }
            for (Crmusermodules crmusermodulesCollectionCrmusermodules : company.getCrmusermodulesCollection()) {
                Company oldCompanyOfCrmusermodulesCollectionCrmusermodules = crmusermodulesCollectionCrmusermodules.getCompany();
                crmusermodulesCollectionCrmusermodules.setCompany(company);
                crmusermodulesCollectionCrmusermodules = em.merge(crmusermodulesCollectionCrmusermodules);
                if (oldCompanyOfCrmusermodulesCollectionCrmusermodules != null) {
                    oldCompanyOfCrmusermodulesCollectionCrmusermodules.getCrmusermodulesCollection().remove(crmusermodulesCollectionCrmusermodules);
                    oldCompanyOfCrmusermodulesCollectionCrmusermodules = em.merge(oldCompanyOfCrmusermodulesCollectionCrmusermodules);
                }
            }
            for (Crmexpense crmexpenseCollectionCrmexpense : company.getCrmexpenseCollection()) {
                Company oldCompanyOfCrmexpenseCollectionCrmexpense = crmexpenseCollectionCrmexpense.getCompany();
                crmexpenseCollectionCrmexpense.setCompany(company);
                crmexpenseCollectionCrmexpense = em.merge(crmexpenseCollectionCrmexpense);
                if (oldCompanyOfCrmexpenseCollectionCrmexpense != null) {
                    oldCompanyOfCrmexpenseCollectionCrmexpense.getCrmexpenseCollection().remove(crmexpenseCollectionCrmexpense);
                    oldCompanyOfCrmexpenseCollectionCrmexpense = em.merge(oldCompanyOfCrmexpenseCollectionCrmexpense);
                }
            }
            for (Crmworkorder crmworkorderCollectionCrmworkorder : company.getCrmworkorderCollection()) {
                Company oldCompanyOfCrmworkorderCollectionCrmworkorder = crmworkorderCollectionCrmworkorder.getCompany();
                crmworkorderCollectionCrmworkorder.setCompany(company);
                crmworkorderCollectionCrmworkorder = em.merge(crmworkorderCollectionCrmworkorder);
                if (oldCompanyOfCrmworkorderCollectionCrmworkorder != null) {
                    oldCompanyOfCrmworkorderCollectionCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionCrmworkorder);
                    oldCompanyOfCrmworkorderCollectionCrmworkorder = em.merge(oldCompanyOfCrmworkorderCollectionCrmworkorder);
                }
            }
            for (Companycontacts companycontactsCollectionCompanycontacts : company.getCompanycontactsCollection()) {
                Company oldCompanyOfCompanycontactsCollectionCompanycontacts = companycontactsCollectionCompanycontacts.getCompany();
                companycontactsCollectionCompanycontacts.setCompany(company);
                companycontactsCollectionCompanycontacts = em.merge(companycontactsCollectionCompanycontacts);
                if (oldCompanyOfCompanycontactsCollectionCompanycontacts != null) {
                    oldCompanyOfCompanycontactsCollectionCompanycontacts.getCompanycontactsCollection().remove(companycontactsCollectionCompanycontacts);
                    oldCompanyOfCompanycontactsCollectionCompanycontacts = em.merge(oldCompanyOfCompanycontactsCollectionCompanycontacts);
                }
            }
            for (Companyemployee companyemployeeCollectionCompanyemployee : company.getCompanyemployeeCollection()) {
                Company oldCompanyOfCompanyemployeeCollectionCompanyemployee = companyemployeeCollectionCompanyemployee.getCompany();
                companyemployeeCollectionCompanyemployee.setCompany(company);
                companyemployeeCollectionCompanyemployee = em.merge(companyemployeeCollectionCompanyemployee);
                if (oldCompanyOfCompanyemployeeCollectionCompanyemployee != null) {
                    oldCompanyOfCompanyemployeeCollectionCompanyemployee.getCompanyemployeeCollection().remove(companyemployeeCollectionCompanyemployee);
                    oldCompanyOfCompanyemployeeCollectionCompanyemployee = em.merge(oldCompanyOfCompanyemployeeCollectionCompanyemployee);
                }
            }
            for (Companyproducttype companyproducttypeCollectionCompanyproducttype : company.getCompanyproducttypeCollection()) {
                Company oldCompanyOfCompanyproducttypeCollectionCompanyproducttype = companyproducttypeCollectionCompanyproducttype.getCompany();
                companyproducttypeCollectionCompanyproducttype.setCompany(company);
                companyproducttypeCollectionCompanyproducttype = em.merge(companyproducttypeCollectionCompanyproducttype);
                if (oldCompanyOfCompanyproducttypeCollectionCompanyproducttype != null) {
                    oldCompanyOfCompanyproducttypeCollectionCompanyproducttype.getCompanyproducttypeCollection().remove(companyproducttypeCollectionCompanyproducttype);
                    oldCompanyOfCompanyproducttypeCollectionCompanyproducttype = em.merge(oldCompanyOfCompanyproducttypeCollectionCompanyproducttype);
                }
            }
            for (Crmcampaign crmcampaignCollectionCrmcampaign : company.getCrmcampaignCollection()) {
                Company oldCompanyOfCrmcampaignCollectionCrmcampaign = crmcampaignCollectionCrmcampaign.getCompany();
                crmcampaignCollectionCrmcampaign.setCompany(company);
                crmcampaignCollectionCrmcampaign = em.merge(crmcampaignCollectionCrmcampaign);
                if (oldCompanyOfCrmcampaignCollectionCrmcampaign != null) {
                    oldCompanyOfCrmcampaignCollectionCrmcampaign.getCrmcampaignCollection().remove(crmcampaignCollectionCrmcampaign);
                    oldCompanyOfCrmcampaignCollectionCrmcampaign = em.merge(oldCompanyOfCrmcampaignCollectionCrmcampaign);
                }
            }
            for (Companyhirarchie companyhirarchieCollectionCompanyhirarchie : company.getCompanyhirarchieCollection()) {
                Company oldCompanyOfCompanyhirarchieCollectionCompanyhirarchie = companyhirarchieCollectionCompanyhirarchie.getCompany();
                companyhirarchieCollectionCompanyhirarchie.setCompany(company);
                companyhirarchieCollectionCompanyhirarchie = em.merge(companyhirarchieCollectionCompanyhirarchie);
                if (oldCompanyOfCompanyhirarchieCollectionCompanyhirarchie != null) {
                    oldCompanyOfCompanyhirarchieCollectionCompanyhirarchie.getCompanyhirarchieCollection().remove(companyhirarchieCollectionCompanyhirarchie);
                    oldCompanyOfCompanyhirarchieCollectionCompanyhirarchie = em.merge(oldCompanyOfCompanyhirarchieCollectionCompanyhirarchie);
                }
            }
            for (Companyhirarchie companyhirarchieCollection1Companyhirarchie : company.getCompanyhirarchieCollection1()) {
                Company oldCompany1OfCompanyhirarchieCollection1Companyhirarchie = companyhirarchieCollection1Companyhirarchie.getCompany1();
                companyhirarchieCollection1Companyhirarchie.setCompany1(company);
                companyhirarchieCollection1Companyhirarchie = em.merge(companyhirarchieCollection1Companyhirarchie);
                if (oldCompany1OfCompanyhirarchieCollection1Companyhirarchie != null) {
                    oldCompany1OfCompanyhirarchieCollection1Companyhirarchie.getCompanyhirarchieCollection1().remove(companyhirarchieCollection1Companyhirarchie);
                    oldCompany1OfCompanyhirarchieCollection1Companyhirarchie = em.merge(oldCompany1OfCompanyhirarchieCollection1Companyhirarchie);
                }
            }
            for (Companyhirarchie companyhirarchieCollection2Companyhirarchie : company.getCompanyhirarchieCollection2()) {
                Company oldCompany2OfCompanyhirarchieCollection2Companyhirarchie = companyhirarchieCollection2Companyhirarchie.getCompany2();
                companyhirarchieCollection2Companyhirarchie.setCompany2(company);
                companyhirarchieCollection2Companyhirarchie = em.merge(companyhirarchieCollection2Companyhirarchie);
                if (oldCompany2OfCompanyhirarchieCollection2Companyhirarchie != null) {
                    oldCompany2OfCompanyhirarchieCollection2Companyhirarchie.getCompanyhirarchieCollection2().remove(companyhirarchieCollection2Companyhirarchie);
                    oldCompany2OfCompanyhirarchieCollection2Companyhirarchie = em.merge(oldCompany2OfCompanyhirarchieCollection2Companyhirarchie);
                }
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollectionCompanyaccountstackdocs : company.getCompanyaccountstackdocsCollection()) {
                Company oldCompanyOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs = companyaccountstackdocsCollectionCompanyaccountstackdocs.getCompany();
                companyaccountstackdocsCollectionCompanyaccountstackdocs.setCompany(company);
                companyaccountstackdocsCollectionCompanyaccountstackdocs = em.merge(companyaccountstackdocsCollectionCompanyaccountstackdocs);
                if (oldCompanyOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs != null) {
                    oldCompanyOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocsCollectionCompanyaccountstackdocs);
                    oldCompanyOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs = em.merge(oldCompanyOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs);
                }
            }
            for (Crmlabor crmlaborCollectionCrmlabor : company.getCrmlaborCollection()) {
                Company oldCompanyOfCrmlaborCollectionCrmlabor = crmlaborCollectionCrmlabor.getCompany();
                crmlaborCollectionCrmlabor.setCompany(company);
                crmlaborCollectionCrmlabor = em.merge(crmlaborCollectionCrmlabor);
                if (oldCompanyOfCrmlaborCollectionCrmlabor != null) {
                    oldCompanyOfCrmlaborCollectionCrmlabor.getCrmlaborCollection().remove(crmlaborCollectionCrmlabor);
                    oldCompanyOfCrmlaborCollectionCrmlabor = em.merge(oldCompanyOfCrmlaborCollectionCrmlabor);
                }
            }
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionCompnaypaymentcurrency : company.getCompnaypaymentcurrencyCollection()) {
                Company oldCompanyOfCompnaypaymentcurrencyCollectionCompnaypaymentcurrency = compnaypaymentcurrencyCollectionCompnaypaymentcurrency.getCompany();
                compnaypaymentcurrencyCollectionCompnaypaymentcurrency.setCompany(company);
                compnaypaymentcurrencyCollectionCompnaypaymentcurrency = em.merge(compnaypaymentcurrencyCollectionCompnaypaymentcurrency);
                if (oldCompanyOfCompnaypaymentcurrencyCollectionCompnaypaymentcurrency != null) {
                    oldCompanyOfCompnaypaymentcurrencyCollectionCompnaypaymentcurrency.getCompnaypaymentcurrencyCollection().remove(compnaypaymentcurrencyCollectionCompnaypaymentcurrency);
                    oldCompanyOfCompnaypaymentcurrencyCollectionCompnaypaymentcurrency = em.merge(oldCompanyOfCompnaypaymentcurrencyCollectionCompnaypaymentcurrency);
                }
            }
            for (Companyproduct companyproductCollectionCompanyproduct : company.getCompanyproductCollection()) {
                Company oldCompanyOfCompanyproductCollectionCompanyproduct = companyproductCollectionCompanyproduct.getCompany();
                companyproductCollectionCompanyproduct.setCompany(company);
                companyproductCollectionCompanyproduct = em.merge(companyproductCollectionCompanyproduct);
                if (oldCompanyOfCompanyproductCollectionCompanyproduct != null) {
                    oldCompanyOfCompanyproductCollectionCompanyproduct.getCompanyproductCollection().remove(companyproductCollectionCompanyproduct);
                    oldCompanyOfCompanyproductCollectionCompanyproduct = em.merge(oldCompanyOfCompanyproductCollectionCompanyproduct);
                }
            }
            for (Employeedesignation employeedesignationCollectionEmployeedesignation : company.getEmployeedesignationCollection()) {
                Company oldCompanyOfEmployeedesignationCollectionEmployeedesignation = employeedesignationCollectionEmployeedesignation.getCompany();
                employeedesignationCollectionEmployeedesignation.setCompany(company);
                employeedesignationCollectionEmployeedesignation = em.merge(employeedesignationCollectionEmployeedesignation);
                if (oldCompanyOfEmployeedesignationCollectionEmployeedesignation != null) {
                    oldCompanyOfEmployeedesignationCollectionEmployeedesignation.getEmployeedesignationCollection().remove(employeedesignationCollectionEmployeedesignation);
                    oldCompanyOfEmployeedesignationCollectionEmployeedesignation = em.merge(oldCompanyOfEmployeedesignationCollectionEmployeedesignation);
                }
            }
            for (Companyaccountstack companyaccountstackCollectionCompanyaccountstack : company.getCompanyaccountstackCollection()) {
                Company oldCompanyOfCompanyaccountstackCollectionCompanyaccountstack = companyaccountstackCollectionCompanyaccountstack.getCompany();
                companyaccountstackCollectionCompanyaccountstack.setCompany(company);
                companyaccountstackCollectionCompanyaccountstack = em.merge(companyaccountstackCollectionCompanyaccountstack);
                if (oldCompanyOfCompanyaccountstackCollectionCompanyaccountstack != null) {
                    oldCompanyOfCompanyaccountstackCollectionCompanyaccountstack.getCompanyaccountstackCollection().remove(companyaccountstackCollectionCompanyaccountstack);
                    oldCompanyOfCompanyaccountstackCollectionCompanyaccountstack = em.merge(oldCompanyOfCompanyaccountstackCollectionCompanyaccountstack);
                }
            }
            for (Componentattachments componentattachmentsCollectionComponentattachments : company.getComponentattachmentsCollection()) {
                Company oldCompanyOfComponentattachmentsCollectionComponentattachments = componentattachmentsCollectionComponentattachments.getCompany();
                componentattachmentsCollectionComponentattachments.setCompany(company);
                componentattachmentsCollectionComponentattachments = em.merge(componentattachmentsCollectionComponentattachments);
                if (oldCompanyOfComponentattachmentsCollectionComponentattachments != null) {
                    oldCompanyOfComponentattachmentsCollectionComponentattachments.getComponentattachmentsCollection().remove(componentattachmentsCollectionComponentattachments);
                    oldCompanyOfComponentattachmentsCollectionComponentattachments = em.merge(oldCompanyOfComponentattachmentsCollectionComponentattachments);
                }
            }
            for (Companyaccountstackcd companyaccountstackcdCollectionCompanyaccountstackcd : company.getCompanyaccountstackcdCollection()) {
                Company oldCompanyOfCompanyaccountstackcdCollectionCompanyaccountstackcd = companyaccountstackcdCollectionCompanyaccountstackcd.getCompany();
                companyaccountstackcdCollectionCompanyaccountstackcd.setCompany(company);
                companyaccountstackcdCollectionCompanyaccountstackcd = em.merge(companyaccountstackcdCollectionCompanyaccountstackcd);
                if (oldCompanyOfCompanyaccountstackcdCollectionCompanyaccountstackcd != null) {
                    oldCompanyOfCompanyaccountstackcdCollectionCompanyaccountstackcd.getCompanyaccountstackcdCollection().remove(companyaccountstackcdCollectionCompanyaccountstackcd);
                    oldCompanyOfCompanyaccountstackcdCollectionCompanyaccountstackcd = em.merge(oldCompanyOfCompanyaccountstackcdCollectionCompanyaccountstackcd);
                }
            }
            for (Crmworkordertype crmworkordertypeCollectionCrmworkordertype : company.getCrmworkordertypeCollection()) {
                Company oldCompanyOfCrmworkordertypeCollectionCrmworkordertype = crmworkordertypeCollectionCrmworkordertype.getCompany();
                crmworkordertypeCollectionCrmworkordertype.setCompany(company);
                crmworkordertypeCollectionCrmworkordertype = em.merge(crmworkordertypeCollectionCrmworkordertype);
                if (oldCompanyOfCrmworkordertypeCollectionCrmworkordertype != null) {
                    oldCompanyOfCrmworkordertypeCollectionCrmworkordertype.getCrmworkordertypeCollection().remove(crmworkordertypeCollectionCrmworkordertype);
                    oldCompanyOfCrmworkordertypeCollectionCrmworkordertype = em.merge(oldCompanyOfCrmworkordertypeCollectionCrmworkordertype);
                }
            }
            for (Crmlabortype crmlabortypeCollectionCrmlabortype : company.getCrmlabortypeCollection()) {
                Company oldCompanyOfCrmlabortypeCollectionCrmlabortype = crmlabortypeCollectionCrmlabortype.getCompany();
                crmlabortypeCollectionCrmlabortype.setCompany(company);
                crmlabortypeCollectionCrmlabortype = em.merge(crmlabortypeCollectionCrmlabortype);
                if (oldCompanyOfCrmlabortypeCollectionCrmlabortype != null) {
                    oldCompanyOfCrmlabortypeCollectionCrmlabortype.getCrmlabortypeCollection().remove(crmlabortypeCollectionCrmlabortype);
                    oldCompanyOfCrmlabortypeCollectionCrmlabortype = em.merge(oldCompanyOfCrmlabortypeCollectionCrmlabortype);
                }
            }
            for (Crmbillingtype crmbillingtypeCollectionCrmbillingtype : company.getCrmbillingtypeCollection()) {
                Company oldCompanyOfCrmbillingtypeCollectionCrmbillingtype = crmbillingtypeCollectionCrmbillingtype.getCompany();
                crmbillingtypeCollectionCrmbillingtype.setCompany(company);
                crmbillingtypeCollectionCrmbillingtype = em.merge(crmbillingtypeCollectionCrmbillingtype);
                if (oldCompanyOfCrmbillingtypeCollectionCrmbillingtype != null) {
                    oldCompanyOfCrmbillingtypeCollectionCrmbillingtype.getCrmbillingtypeCollection().remove(crmbillingtypeCollectionCrmbillingtype);
                    oldCompanyOfCrmbillingtypeCollectionCrmbillingtype = em.merge(oldCompanyOfCrmbillingtypeCollectionCrmbillingtype);
                }
            }
            for (Companyproductcategory companyproductcategoryCollectionCompanyproductcategory : company.getCompanyproductcategoryCollection()) {
                Company oldCompanyOfCompanyproductcategoryCollectionCompanyproductcategory = companyproductcategoryCollectionCompanyproductcategory.getCompany();
                companyproductcategoryCollectionCompanyproductcategory.setCompany(company);
                companyproductcategoryCollectionCompanyproductcategory = em.merge(companyproductcategoryCollectionCompanyproductcategory);
                if (oldCompanyOfCompanyproductcategoryCollectionCompanyproductcategory != null) {
                    oldCompanyOfCompanyproductcategoryCollectionCompanyproductcategory.getCompanyproductcategoryCollection().remove(companyproductcategoryCollectionCompanyproductcategory);
                    oldCompanyOfCompanyproductcategoryCollectionCompanyproductcategory = em.merge(oldCompanyOfCompanyproductcategoryCollectionCompanyproductcategory);
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollectionCrmcampaignprops : company.getCrmcampaignpropsCollection()) {
                Company oldCompanyOfCrmcampaignpropsCollectionCrmcampaignprops = crmcampaignpropsCollectionCrmcampaignprops.getCompany();
                crmcampaignpropsCollectionCrmcampaignprops.setCompany(company);
                crmcampaignpropsCollectionCrmcampaignprops = em.merge(crmcampaignpropsCollectionCrmcampaignprops);
                if (oldCompanyOfCrmcampaignpropsCollectionCrmcampaignprops != null) {
                    oldCompanyOfCrmcampaignpropsCollectionCrmcampaignprops.getCrmcampaignpropsCollection().remove(crmcampaignpropsCollectionCrmcampaignprops);
                    oldCompanyOfCrmcampaignpropsCollectionCrmcampaignprops = em.merge(oldCompanyOfCrmcampaignpropsCollectionCrmcampaignprops);
                }
            }
            for (Customercategory customercategoryCollectionCustomercategory : company.getCustomercategoryCollection()) {
                Company oldCompanyOfCustomercategoryCollectionCustomercategory = customercategoryCollectionCustomercategory.getCompany();
                customercategoryCollectionCustomercategory.setCompany(company);
                customercategoryCollectionCustomercategory = em.merge(customercategoryCollectionCustomercategory);
                if (oldCompanyOfCustomercategoryCollectionCustomercategory != null) {
                    oldCompanyOfCustomercategoryCollectionCustomercategory.getCustomercategoryCollection().remove(customercategoryCollectionCustomercategory);
                    oldCompanyOfCustomercategoryCollectionCustomercategory = em.merge(oldCompanyOfCustomercategoryCollectionCustomercategory);
                }
            }
            for (Productcomponents productcomponentsCollectionProductcomponents : company.getProductcomponentsCollection()) {
                Company oldCompanyOfProductcomponentsCollectionProductcomponents = productcomponentsCollectionProductcomponents.getCompany();
                productcomponentsCollectionProductcomponents.setCompany(company);
                productcomponentsCollectionProductcomponents = em.merge(productcomponentsCollectionProductcomponents);
                if (oldCompanyOfProductcomponentsCollectionProductcomponents != null) {
                    oldCompanyOfProductcomponentsCollectionProductcomponents.getProductcomponentsCollection().remove(productcomponentsCollectionProductcomponents);
                    oldCompanyOfProductcomponentsCollectionProductcomponents = em.merge(oldCompanyOfProductcomponentsCollectionProductcomponents);
                }
            }
            for (Crmexpensetype crmexpensetypeCollectionCrmexpensetype : company.getCrmexpensetypeCollection()) {
                Company oldCompanyOfCrmexpensetypeCollectionCrmexpensetype = crmexpensetypeCollectionCrmexpensetype.getCompany();
                crmexpensetypeCollectionCrmexpensetype.setCompany(company);
                crmexpensetypeCollectionCrmexpensetype = em.merge(crmexpensetypeCollectionCrmexpensetype);
                if (oldCompanyOfCrmexpensetypeCollectionCrmexpensetype != null) {
                    oldCompanyOfCrmexpensetypeCollectionCrmexpensetype.getCrmexpensetypeCollection().remove(crmexpensetypeCollectionCrmexpensetype);
                    oldCompanyOfCrmexpensetypeCollectionCrmexpensetype = em.merge(oldCompanyOfCrmexpensetypeCollectionCrmexpensetype);
                }
            }
            for (Companypayments companypaymentsCollectionCompanypayments : company.getCompanypaymentsCollection()) {
                Company oldCompanyOfCompanypaymentsCollectionCompanypayments = companypaymentsCollectionCompanypayments.getCompany();
                companypaymentsCollectionCompanypayments.setCompany(company);
                companypaymentsCollectionCompanypayments = em.merge(companypaymentsCollectionCompanypayments);
                if (oldCompanyOfCompanypaymentsCollectionCompanypayments != null) {
                    oldCompanyOfCompanypaymentsCollectionCompanypayments.getCompanypaymentsCollection().remove(companypaymentsCollectionCompanypayments);
                    oldCompanyOfCompanypaymentsCollectionCompanypayments = em.merge(oldCompanyOfCompanypaymentsCollectionCompanypayments);
                }
            }
            for (Crmworkorderresolution crmworkorderresolutionCollectionCrmworkorderresolution : company.getCrmworkorderresolutionCollection()) {
                Company oldCompanyOfCrmworkorderresolutionCollectionCrmworkorderresolution = crmworkorderresolutionCollectionCrmworkorderresolution.getCompany();
                crmworkorderresolutionCollectionCrmworkorderresolution.setCompany(company);
                crmworkorderresolutionCollectionCrmworkorderresolution = em.merge(crmworkorderresolutionCollectionCrmworkorderresolution);
                if (oldCompanyOfCrmworkorderresolutionCollectionCrmworkorderresolution != null) {
                    oldCompanyOfCrmworkorderresolutionCollectionCrmworkorderresolution.getCrmworkorderresolutionCollection().remove(crmworkorderresolutionCollectionCrmworkorderresolution);
                    oldCompanyOfCrmworkorderresolutionCollectionCrmworkorderresolution = em.merge(oldCompanyOfCrmworkorderresolutionCollectionCrmworkorderresolution);
                }
            }
            for (Companyaddresstype companyaddresstypeCollectionCompanyaddresstype : company.getCompanyaddresstypeCollection()) {
                Company oldCompanyOfCompanyaddresstypeCollectionCompanyaddresstype = companyaddresstypeCollectionCompanyaddresstype.getCompany();
                companyaddresstypeCollectionCompanyaddresstype.setCompany(company);
                companyaddresstypeCollectionCompanyaddresstype = em.merge(companyaddresstypeCollectionCompanyaddresstype);
                if (oldCompanyOfCompanyaddresstypeCollectionCompanyaddresstype != null) {
                    oldCompanyOfCompanyaddresstypeCollectionCompanyaddresstype.getCompanyaddresstypeCollection().remove(companyaddresstypeCollectionCompanyaddresstype);
                    oldCompanyOfCompanyaddresstypeCollectionCompanyaddresstype = em.merge(oldCompanyOfCompanyaddresstypeCollectionCompanyaddresstype);
                }
            }
            for (Companycustomer companycustomerCollectionCompanycustomer : company.getCompanycustomerCollection()) {
                Company oldCompanyOfCompanycustomerCollectionCompanycustomer = companycustomerCollectionCompanycustomer.getCompany();
                companycustomerCollectionCompanycustomer.setCompany(company);
                companycustomerCollectionCompanycustomer = em.merge(companycustomerCollectionCompanycustomer);
                if (oldCompanyOfCompanycustomerCollectionCompanycustomer != null) {
                    oldCompanyOfCompanycustomerCollectionCompanycustomer.getCompanycustomerCollection().remove(companycustomerCollectionCompanycustomer);
                    oldCompanyOfCompanycustomerCollectionCompanycustomer = em.merge(oldCompanyOfCompanycustomerCollectionCompanycustomer);
                }
            }
            for (Crmprojectstatus crmprojectstatusCollectionCrmprojectstatus : company.getCrmprojectstatusCollection()) {
                Company oldCompanyOfCrmprojectstatusCollectionCrmprojectstatus = crmprojectstatusCollectionCrmprojectstatus.getCompany();
                crmprojectstatusCollectionCrmprojectstatus.setCompany(company);
                crmprojectstatusCollectionCrmprojectstatus = em.merge(crmprojectstatusCollectionCrmprojectstatus);
                if (oldCompanyOfCrmprojectstatusCollectionCrmprojectstatus != null) {
                    oldCompanyOfCrmprojectstatusCollectionCrmprojectstatus.getCrmprojectstatusCollection().remove(crmprojectstatusCollectionCrmprojectstatus);
                    oldCompanyOfCrmprojectstatusCollectionCrmprojectstatus = em.merge(oldCompanyOfCrmprojectstatusCollectionCrmprojectstatus);
                }
            }
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionCrmschedulerevnttype : company.getCrmschedulerevnttypeCollection()) {
                Company oldCompanyOfCrmschedulerevnttypeCollectionCrmschedulerevnttype = crmschedulerevnttypeCollectionCrmschedulerevnttype.getCompany();
                crmschedulerevnttypeCollectionCrmschedulerevnttype.setCompany(company);
                crmschedulerevnttypeCollectionCrmschedulerevnttype = em.merge(crmschedulerevnttypeCollectionCrmschedulerevnttype);
                if (oldCompanyOfCrmschedulerevnttypeCollectionCrmschedulerevnttype != null) {
                    oldCompanyOfCrmschedulerevnttypeCollectionCrmschedulerevnttype.getCrmschedulerevnttypeCollection().remove(crmschedulerevnttypeCollectionCrmschedulerevnttype);
                    oldCompanyOfCrmschedulerevnttypeCollectionCrmschedulerevnttype = em.merge(oldCompanyOfCrmschedulerevnttypeCollectionCrmschedulerevnttype);
                }
            }
            for (Componenttype componenttypeCollectionComponenttype : company.getComponenttypeCollection()) {
                Company oldCompanyOfComponenttypeCollectionComponenttype = componenttypeCollectionComponenttype.getCompany();
                componenttypeCollectionComponenttype.setCompany(company);
                componenttypeCollectionComponenttype = em.merge(componenttypeCollectionComponenttype);
                if (oldCompanyOfComponenttypeCollectionComponenttype != null) {
                    oldCompanyOfComponenttypeCollectionComponenttype.getComponenttypeCollection().remove(componenttypeCollectionComponenttype);
                    oldCompanyOfComponenttypeCollectionComponenttype = em.merge(oldCompanyOfComponenttypeCollectionComponenttype);
                }
            }
            for (Companypaymentscheme companypaymentschemeCollectionCompanypaymentscheme : company.getCompanypaymentschemeCollection()) {
                Company oldCompanyOfCompanypaymentschemeCollectionCompanypaymentscheme = companypaymentschemeCollectionCompanypaymentscheme.getCompany();
                companypaymentschemeCollectionCompanypaymentscheme.setCompany(company);
                companypaymentschemeCollectionCompanypaymentscheme = em.merge(companypaymentschemeCollectionCompanypaymentscheme);
                if (oldCompanyOfCompanypaymentschemeCollectionCompanypaymentscheme != null) {
                    oldCompanyOfCompanypaymentschemeCollectionCompanypaymentscheme.getCompanypaymentschemeCollection().remove(companypaymentschemeCollectionCompanypaymentscheme);
                    oldCompanyOfCompanypaymentschemeCollectionCompanypaymentscheme = em.merge(oldCompanyOfCompanypaymentschemeCollectionCompanypaymentscheme);
                }
            }
            for (Companycontactsaddress companycontactsaddressCollectionCompanycontactsaddress : company.getCompanycontactsaddressCollection()) {
                Company oldCompanyOfCompanycontactsaddressCollectionCompanycontactsaddress = companycontactsaddressCollectionCompanycontactsaddress.getCompany();
                companycontactsaddressCollectionCompanycontactsaddress.setCompany(company);
                companycontactsaddressCollectionCompanycontactsaddress = em.merge(companycontactsaddressCollectionCompanycontactsaddress);
                if (oldCompanyOfCompanycontactsaddressCollectionCompanycontactsaddress != null) {
                    oldCompanyOfCompanycontactsaddressCollectionCompanycontactsaddress.getCompanycontactsaddressCollection().remove(companycontactsaddressCollectionCompanycontactsaddress);
                    oldCompanyOfCompanycontactsaddressCollectionCompanycontactsaddress = em.merge(oldCompanyOfCompanycontactsaddressCollectionCompanycontactsaddress);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompany(company.getCompanyPK()) != null) {
                throw new PreexistingEntityException("Company " + company + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Company company) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company persistentCompany = em.find(Company.class, company.getCompanyPK());
            Companyemployee companyemployeeOld = persistentCompany.getCompanyemployee();
            Companyemployee companyemployeeNew = company.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompany.getCompanyemployee1();
            Companyemployee companyemployee1New = company.getCompanyemployee1();
            Category categoryOld = persistentCompany.getCategory();
            Category categoryNew = company.getCategory();
            Collection<Crmmessagechannel> crmmessagechannelCollectionOld = persistentCompany.getCrmmessagechannelCollection();
            Collection<Crmmessagechannel> crmmessagechannelCollectionNew = company.getCrmmessagechannelCollection();
            Collection<Companybank> companybankCollectionOld = persistentCompany.getCompanybankCollection();
            Collection<Companybank> companybankCollectionNew = company.getCompanybankCollection();
            Collection<Crmprojectprops> crmprojectpropsCollectionOld = persistentCompany.getCrmprojectpropsCollection();
            Collection<Crmprojectprops> crmprojectpropsCollectionNew = company.getCrmprojectpropsCollection();
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollectionOld = persistentCompany.getCrmcampaignreceiverCollection();
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollectionNew = company.getCrmcampaignreceiverCollection();
            Collection<Productattachments> productattachmentsCollectionOld = persistentCompany.getProductattachmentsCollection();
            Collection<Productattachments> productattachmentsCollectionNew = company.getProductattachmentsCollection();
            Collection<Crmcampaignstatus> crmcampaignstatusCollectionOld = persistentCompany.getCrmcampaignstatusCollection();
            Collection<Crmcampaignstatus> crmcampaignstatusCollectionNew = company.getCrmcampaignstatusCollection();
            Collection<Crmcampaignposition> crmcampaignpositionCollectionOld = persistentCompany.getCrmcampaignpositionCollection();
            Collection<Crmcampaignposition> crmcampaignpositionCollectionNew = company.getCrmcampaignpositionCollection();
            Collection<Companydepartment> companydepartmentCollectionOld = persistentCompany.getCompanydepartmentCollection();
            Collection<Companydepartment> companydepartmentCollectionNew = company.getCompanydepartmentCollection();
            Collection<Crmusermodules> crmusermodulesCollectionOld = persistentCompany.getCrmusermodulesCollection();
            Collection<Crmusermodules> crmusermodulesCollectionNew = company.getCrmusermodulesCollection();
            Collection<Crmexpense> crmexpenseCollectionOld = persistentCompany.getCrmexpenseCollection();
            Collection<Crmexpense> crmexpenseCollectionNew = company.getCrmexpenseCollection();
            Collection<Crmworkorder> crmworkorderCollectionOld = persistentCompany.getCrmworkorderCollection();
            Collection<Crmworkorder> crmworkorderCollectionNew = company.getCrmworkorderCollection();
            Collection<Companycontacts> companycontactsCollectionOld = persistentCompany.getCompanycontactsCollection();
            Collection<Companycontacts> companycontactsCollectionNew = company.getCompanycontactsCollection();
            Collection<Companyemployee> companyemployeeCollectionOld = persistentCompany.getCompanyemployeeCollection();
            Collection<Companyemployee> companyemployeeCollectionNew = company.getCompanyemployeeCollection();
            Collection<Companyproducttype> companyproducttypeCollectionOld = persistentCompany.getCompanyproducttypeCollection();
            Collection<Companyproducttype> companyproducttypeCollectionNew = company.getCompanyproducttypeCollection();
            Collection<Crmcampaign> crmcampaignCollectionOld = persistentCompany.getCrmcampaignCollection();
            Collection<Crmcampaign> crmcampaignCollectionNew = company.getCrmcampaignCollection();
            Collection<Companyhirarchie> companyhirarchieCollectionOld = persistentCompany.getCompanyhirarchieCollection();
            Collection<Companyhirarchie> companyhirarchieCollectionNew = company.getCompanyhirarchieCollection();
            Collection<Companyhirarchie> companyhirarchieCollection1Old = persistentCompany.getCompanyhirarchieCollection1();
            Collection<Companyhirarchie> companyhirarchieCollection1New = company.getCompanyhirarchieCollection1();
            Collection<Companyhirarchie> companyhirarchieCollection2Old = persistentCompany.getCompanyhirarchieCollection2();
            Collection<Companyhirarchie> companyhirarchieCollection2New = company.getCompanyhirarchieCollection2();
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollectionOld = persistentCompany.getCompanyaccountstackdocsCollection();
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollectionNew = company.getCompanyaccountstackdocsCollection();
            Collection<Crmlabor> crmlaborCollectionOld = persistentCompany.getCrmlaborCollection();
            Collection<Crmlabor> crmlaborCollectionNew = company.getCrmlaborCollection();
            Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollectionOld = persistentCompany.getCompnaypaymentcurrencyCollection();
            Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollectionNew = company.getCompnaypaymentcurrencyCollection();
            Collection<Companyproduct> companyproductCollectionOld = persistentCompany.getCompanyproductCollection();
            Collection<Companyproduct> companyproductCollectionNew = company.getCompanyproductCollection();
            Collection<Employeedesignation> employeedesignationCollectionOld = persistentCompany.getEmployeedesignationCollection();
            Collection<Employeedesignation> employeedesignationCollectionNew = company.getEmployeedesignationCollection();
            Collection<Companyaccountstack> companyaccountstackCollectionOld = persistentCompany.getCompanyaccountstackCollection();
            Collection<Companyaccountstack> companyaccountstackCollectionNew = company.getCompanyaccountstackCollection();
            Collection<Componentattachments> componentattachmentsCollectionOld = persistentCompany.getComponentattachmentsCollection();
            Collection<Componentattachments> componentattachmentsCollectionNew = company.getComponentattachmentsCollection();
            Collection<Companyaccountstackcd> companyaccountstackcdCollectionOld = persistentCompany.getCompanyaccountstackcdCollection();
            Collection<Companyaccountstackcd> companyaccountstackcdCollectionNew = company.getCompanyaccountstackcdCollection();
            Collection<Crmworkordertype> crmworkordertypeCollectionOld = persistentCompany.getCrmworkordertypeCollection();
            Collection<Crmworkordertype> crmworkordertypeCollectionNew = company.getCrmworkordertypeCollection();
            Collection<Crmlabortype> crmlabortypeCollectionOld = persistentCompany.getCrmlabortypeCollection();
            Collection<Crmlabortype> crmlabortypeCollectionNew = company.getCrmlabortypeCollection();
            Collection<Crmbillingtype> crmbillingtypeCollectionOld = persistentCompany.getCrmbillingtypeCollection();
            Collection<Crmbillingtype> crmbillingtypeCollectionNew = company.getCrmbillingtypeCollection();
            Collection<Companyproductcategory> companyproductcategoryCollectionOld = persistentCompany.getCompanyproductcategoryCollection();
            Collection<Companyproductcategory> companyproductcategoryCollectionNew = company.getCompanyproductcategoryCollection();
            Collection<Crmcampaignprops> crmcampaignpropsCollectionOld = persistentCompany.getCrmcampaignpropsCollection();
            Collection<Crmcampaignprops> crmcampaignpropsCollectionNew = company.getCrmcampaignpropsCollection();
            Collection<Customercategory> customercategoryCollectionOld = persistentCompany.getCustomercategoryCollection();
            Collection<Customercategory> customercategoryCollectionNew = company.getCustomercategoryCollection();
            Collection<Productcomponents> productcomponentsCollectionOld = persistentCompany.getProductcomponentsCollection();
            Collection<Productcomponents> productcomponentsCollectionNew = company.getProductcomponentsCollection();
            Collection<Crmexpensetype> crmexpensetypeCollectionOld = persistentCompany.getCrmexpensetypeCollection();
            Collection<Crmexpensetype> crmexpensetypeCollectionNew = company.getCrmexpensetypeCollection();
            Collection<Companypayments> companypaymentsCollectionOld = persistentCompany.getCompanypaymentsCollection();
            Collection<Companypayments> companypaymentsCollectionNew = company.getCompanypaymentsCollection();
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionOld = persistentCompany.getCrmworkorderresolutionCollection();
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionNew = company.getCrmworkorderresolutionCollection();
            Collection<Companyaddresstype> companyaddresstypeCollectionOld = persistentCompany.getCompanyaddresstypeCollection();
            Collection<Companyaddresstype> companyaddresstypeCollectionNew = company.getCompanyaddresstypeCollection();
            Collection<Companycustomer> companycustomerCollectionOld = persistentCompany.getCompanycustomerCollection();
            Collection<Companycustomer> companycustomerCollectionNew = company.getCompanycustomerCollection();
            Collection<Crmprojectstatus> crmprojectstatusCollectionOld = persistentCompany.getCrmprojectstatusCollection();
            Collection<Crmprojectstatus> crmprojectstatusCollectionNew = company.getCrmprojectstatusCollection();
            Collection<Crmschedulerevnttype> crmschedulerevnttypeCollectionOld = persistentCompany.getCrmschedulerevnttypeCollection();
            Collection<Crmschedulerevnttype> crmschedulerevnttypeCollectionNew = company.getCrmschedulerevnttypeCollection();
            Collection<Componenttype> componenttypeCollectionOld = persistentCompany.getComponenttypeCollection();
            Collection<Componenttype> componenttypeCollectionNew = company.getComponenttypeCollection();
            Collection<Companypaymentscheme> companypaymentschemeCollectionOld = persistentCompany.getCompanypaymentschemeCollection();
            Collection<Companypaymentscheme> companypaymentschemeCollectionNew = company.getCompanypaymentschemeCollection();
            Collection<Companycontactsaddress> companycontactsaddressCollectionOld = persistentCompany.getCompanycontactsaddressCollection();
            Collection<Companycontactsaddress> companycontactsaddressCollectionNew = company.getCompanycontactsaddressCollection();
            List<String> illegalOrphanMessages = null;
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Companyemployee " + companyemployeeOld + " since its company field is not nullable.");
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                Company oldCompanyOfCompanyemployee = companyemployeeNew.getCompany();
                if (oldCompanyOfCompanyemployee != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Companyemployee " + companyemployeeNew + " already has an item of type Company whose companyemployee column cannot be null. Please make another selection for the companyemployee field.");
                }
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Companyemployee " + companyemployee1Old + " since its company field is not nullable.");
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                Company oldCompanyOfCompanyemployee1 = companyemployee1New.getCompany();
                if (oldCompanyOfCompanyemployee1 != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Companyemployee " + companyemployee1New + " already has an item of type Company whose companyemployee1 column cannot be null. Please make another selection for the companyemployee1 field.");
                }
            }
            for (Crmmessagechannel crmmessagechannelCollectionOldCrmmessagechannel : crmmessagechannelCollectionOld) {
                if (!crmmessagechannelCollectionNew.contains(crmmessagechannelCollectionOldCrmmessagechannel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagechannel " + crmmessagechannelCollectionOldCrmmessagechannel + " since its company field is not nullable.");
                }
            }
            for (Companybank companybankCollectionOldCompanybank : companybankCollectionOld) {
                if (!companybankCollectionNew.contains(companybankCollectionOldCompanybank)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companybank " + companybankCollectionOldCompanybank + " since its company field is not nullable.");
                }
            }
            for (Crmprojectprops crmprojectpropsCollectionOldCrmprojectprops : crmprojectpropsCollectionOld) {
                if (!crmprojectpropsCollectionNew.contains(crmprojectpropsCollectionOldCrmprojectprops)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectprops " + crmprojectpropsCollectionOldCrmprojectprops + " since its company field is not nullable.");
                }
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollectionOldCrmcampaignreceiver : crmcampaignreceiverCollectionOld) {
                if (!crmcampaignreceiverCollectionNew.contains(crmcampaignreceiverCollectionOldCrmcampaignreceiver)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignreceiver " + crmcampaignreceiverCollectionOldCrmcampaignreceiver + " since its company field is not nullable.");
                }
            }
            for (Productattachments productattachmentsCollectionOldProductattachments : productattachmentsCollectionOld) {
                if (!productattachmentsCollectionNew.contains(productattachmentsCollectionOldProductattachments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productattachments " + productattachmentsCollectionOldProductattachments + " since its company field is not nullable.");
                }
            }
            for (Crmcampaignstatus crmcampaignstatusCollectionOldCrmcampaignstatus : crmcampaignstatusCollectionOld) {
                if (!crmcampaignstatusCollectionNew.contains(crmcampaignstatusCollectionOldCrmcampaignstatus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignstatus " + crmcampaignstatusCollectionOldCrmcampaignstatus + " since its company field is not nullable.");
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollectionOldCrmcampaignposition : crmcampaignpositionCollectionOld) {
                if (!crmcampaignpositionCollectionNew.contains(crmcampaignpositionCollectionOldCrmcampaignposition)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignposition " + crmcampaignpositionCollectionOldCrmcampaignposition + " since its company field is not nullable.");
                }
            }
            for (Companydepartment companydepartmentCollectionOldCompanydepartment : companydepartmentCollectionOld) {
                if (!companydepartmentCollectionNew.contains(companydepartmentCollectionOldCompanydepartment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companydepartment " + companydepartmentCollectionOldCompanydepartment + " since its company field is not nullable.");
                }
            }
            for (Crmusermodules crmusermodulesCollectionOldCrmusermodules : crmusermodulesCollectionOld) {
                if (!crmusermodulesCollectionNew.contains(crmusermodulesCollectionOldCrmusermodules)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmusermodules " + crmusermodulesCollectionOldCrmusermodules + " since its company field is not nullable.");
                }
            }
            for (Crmexpense crmexpenseCollectionOldCrmexpense : crmexpenseCollectionOld) {
                if (!crmexpenseCollectionNew.contains(crmexpenseCollectionOldCrmexpense)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmexpense " + crmexpenseCollectionOldCrmexpense + " since its company field is not nullable.");
                }
            }
            for (Crmworkorder crmworkorderCollectionOldCrmworkorder : crmworkorderCollectionOld) {
                if (!crmworkorderCollectionNew.contains(crmworkorderCollectionOldCrmworkorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorder " + crmworkorderCollectionOldCrmworkorder + " since its company field is not nullable.");
                }
            }
            for (Companycontacts companycontactsCollectionOldCompanycontacts : companycontactsCollectionOld) {
                if (!companycontactsCollectionNew.contains(companycontactsCollectionOldCompanycontacts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companycontacts " + companycontactsCollectionOldCompanycontacts + " since its company field is not nullable.");
                }
            }
            for (Companyemployee companyemployeeCollectionOldCompanyemployee : companyemployeeCollectionOld) {
                if (!companyemployeeCollectionNew.contains(companyemployeeCollectionOldCompanyemployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyemployee " + companyemployeeCollectionOldCompanyemployee + " since its company field is not nullable.");
                }
            }
            for (Companyproducttype companyproducttypeCollectionOldCompanyproducttype : companyproducttypeCollectionOld) {
                if (!companyproducttypeCollectionNew.contains(companyproducttypeCollectionOldCompanyproducttype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproducttype " + companyproducttypeCollectionOldCompanyproducttype + " since its company field is not nullable.");
                }
            }
            for (Crmcampaign crmcampaignCollectionOldCrmcampaign : crmcampaignCollectionOld) {
                if (!crmcampaignCollectionNew.contains(crmcampaignCollectionOldCrmcampaign)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaign " + crmcampaignCollectionOldCrmcampaign + " since its company field is not nullable.");
                }
            }
            for (Companyhirarchie companyhirarchieCollectionOldCompanyhirarchie : companyhirarchieCollectionOld) {
                if (!companyhirarchieCollectionNew.contains(companyhirarchieCollectionOldCompanyhirarchie)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyhirarchie " + companyhirarchieCollectionOldCompanyhirarchie + " since its company field is not nullable.");
                }
            }
            for (Companyhirarchie companyhirarchieCollection1OldCompanyhirarchie : companyhirarchieCollection1Old) {
                if (!companyhirarchieCollection1New.contains(companyhirarchieCollection1OldCompanyhirarchie)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyhirarchie " + companyhirarchieCollection1OldCompanyhirarchie + " since its company1 field is not nullable.");
                }
            }
            for (Companyhirarchie companyhirarchieCollection2OldCompanyhirarchie : companyhirarchieCollection2Old) {
                if (!companyhirarchieCollection2New.contains(companyhirarchieCollection2OldCompanyhirarchie)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyhirarchie " + companyhirarchieCollection2OldCompanyhirarchie + " since its company2 field is not nullable.");
                }
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollectionOldCompanyaccountstackdocs : companyaccountstackdocsCollectionOld) {
                if (!companyaccountstackdocsCollectionNew.contains(companyaccountstackdocsCollectionOldCompanyaccountstackdocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstackdocs " + companyaccountstackdocsCollectionOldCompanyaccountstackdocs + " since its company field is not nullable.");
                }
            }
            for (Crmlabor crmlaborCollectionOldCrmlabor : crmlaborCollectionOld) {
                if (!crmlaborCollectionNew.contains(crmlaborCollectionOldCrmlabor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmlabor " + crmlaborCollectionOldCrmlabor + " since its company field is not nullable.");
                }
            }
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionOldCompnaypaymentcurrency : compnaypaymentcurrencyCollectionOld) {
                if (!compnaypaymentcurrencyCollectionNew.contains(compnaypaymentcurrencyCollectionOldCompnaypaymentcurrency)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compnaypaymentcurrency " + compnaypaymentcurrencyCollectionOldCompnaypaymentcurrency + " since its company field is not nullable.");
                }
            }
            for (Companyproduct companyproductCollectionOldCompanyproduct : companyproductCollectionOld) {
                if (!companyproductCollectionNew.contains(companyproductCollectionOldCompanyproduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproduct " + companyproductCollectionOldCompanyproduct + " since its company field is not nullable.");
                }
            }
            for (Employeedesignation employeedesignationCollectionOldEmployeedesignation : employeedesignationCollectionOld) {
                if (!employeedesignationCollectionNew.contains(employeedesignationCollectionOldEmployeedesignation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employeedesignation " + employeedesignationCollectionOldEmployeedesignation + " since its company field is not nullable.");
                }
            }
            for (Companyaccountstack companyaccountstackCollectionOldCompanyaccountstack : companyaccountstackCollectionOld) {
                if (!companyaccountstackCollectionNew.contains(companyaccountstackCollectionOldCompanyaccountstack)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstack " + companyaccountstackCollectionOldCompanyaccountstack + " since its company field is not nullable.");
                }
            }
            for (Componentattachments componentattachmentsCollectionOldComponentattachments : componentattachmentsCollectionOld) {
                if (!componentattachmentsCollectionNew.contains(componentattachmentsCollectionOldComponentattachments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Componentattachments " + componentattachmentsCollectionOldComponentattachments + " since its company field is not nullable.");
                }
            }
            for (Companyaccountstackcd companyaccountstackcdCollectionOldCompanyaccountstackcd : companyaccountstackcdCollectionOld) {
                if (!companyaccountstackcdCollectionNew.contains(companyaccountstackcdCollectionOldCompanyaccountstackcd)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstackcd " + companyaccountstackcdCollectionOldCompanyaccountstackcd + " since its company field is not nullable.");
                }
            }
            for (Crmworkordertype crmworkordertypeCollectionOldCrmworkordertype : crmworkordertypeCollectionOld) {
                if (!crmworkordertypeCollectionNew.contains(crmworkordertypeCollectionOldCrmworkordertype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkordertype " + crmworkordertypeCollectionOldCrmworkordertype + " since its company field is not nullable.");
                }
            }
            for (Crmlabortype crmlabortypeCollectionOldCrmlabortype : crmlabortypeCollectionOld) {
                if (!crmlabortypeCollectionNew.contains(crmlabortypeCollectionOldCrmlabortype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmlabortype " + crmlabortypeCollectionOldCrmlabortype + " since its company field is not nullable.");
                }
            }
            for (Crmbillingtype crmbillingtypeCollectionOldCrmbillingtype : crmbillingtypeCollectionOld) {
                if (!crmbillingtypeCollectionNew.contains(crmbillingtypeCollectionOldCrmbillingtype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmbillingtype " + crmbillingtypeCollectionOldCrmbillingtype + " since its company field is not nullable.");
                }
            }
            for (Companyproductcategory companyproductcategoryCollectionOldCompanyproductcategory : companyproductcategoryCollectionOld) {
                if (!companyproductcategoryCollectionNew.contains(companyproductcategoryCollectionOldCompanyproductcategory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproductcategory " + companyproductcategoryCollectionOldCompanyproductcategory + " since its company field is not nullable.");
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollectionOldCrmcampaignprops : crmcampaignpropsCollectionOld) {
                if (!crmcampaignpropsCollectionNew.contains(crmcampaignpropsCollectionOldCrmcampaignprops)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignprops " + crmcampaignpropsCollectionOldCrmcampaignprops + " since its company field is not nullable.");
                }
            }
            for (Customercategory customercategoryCollectionOldCustomercategory : customercategoryCollectionOld) {
                if (!customercategoryCollectionNew.contains(customercategoryCollectionOldCustomercategory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customercategory " + customercategoryCollectionOldCustomercategory + " since its company field is not nullable.");
                }
            }
            for (Productcomponents productcomponentsCollectionOldProductcomponents : productcomponentsCollectionOld) {
                if (!productcomponentsCollectionNew.contains(productcomponentsCollectionOldProductcomponents)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productcomponents " + productcomponentsCollectionOldProductcomponents + " since its company field is not nullable.");
                }
            }
            for (Crmexpensetype crmexpensetypeCollectionOldCrmexpensetype : crmexpensetypeCollectionOld) {
                if (!crmexpensetypeCollectionNew.contains(crmexpensetypeCollectionOldCrmexpensetype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmexpensetype " + crmexpensetypeCollectionOldCrmexpensetype + " since its company field is not nullable.");
                }
            }
            for (Companypayments companypaymentsCollectionOldCompanypayments : companypaymentsCollectionOld) {
                if (!companypaymentsCollectionNew.contains(companypaymentsCollectionOldCompanypayments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companypayments " + companypaymentsCollectionOldCompanypayments + " since its company field is not nullable.");
                }
            }
            for (Crmworkorderresolution crmworkorderresolutionCollectionOldCrmworkorderresolution : crmworkorderresolutionCollectionOld) {
                if (!crmworkorderresolutionCollectionNew.contains(crmworkorderresolutionCollectionOldCrmworkorderresolution)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorderresolution " + crmworkorderresolutionCollectionOldCrmworkorderresolution + " since its company field is not nullable.");
                }
            }
            for (Companyaddresstype companyaddresstypeCollectionOldCompanyaddresstype : companyaddresstypeCollectionOld) {
                if (!companyaddresstypeCollectionNew.contains(companyaddresstypeCollectionOldCompanyaddresstype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaddresstype " + companyaddresstypeCollectionOldCompanyaddresstype + " since its company field is not nullable.");
                }
            }
            for (Companycustomer companycustomerCollectionOldCompanycustomer : companycustomerCollectionOld) {
                if (!companycustomerCollectionNew.contains(companycustomerCollectionOldCompanycustomer)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companycustomer " + companycustomerCollectionOldCompanycustomer + " since its company field is not nullable.");
                }
            }
            for (Crmprojectstatus crmprojectstatusCollectionOldCrmprojectstatus : crmprojectstatusCollectionOld) {
                if (!crmprojectstatusCollectionNew.contains(crmprojectstatusCollectionOldCrmprojectstatus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectstatus " + crmprojectstatusCollectionOldCrmprojectstatus + " since its company field is not nullable.");
                }
            }
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionOldCrmschedulerevnttype : crmschedulerevnttypeCollectionOld) {
                if (!crmschedulerevnttypeCollectionNew.contains(crmschedulerevnttypeCollectionOldCrmschedulerevnttype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmschedulerevnttype " + crmschedulerevnttypeCollectionOldCrmschedulerevnttype + " since its company field is not nullable.");
                }
            }
            for (Componenttype componenttypeCollectionOldComponenttype : componenttypeCollectionOld) {
                if (!componenttypeCollectionNew.contains(componenttypeCollectionOldComponenttype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Componenttype " + componenttypeCollectionOldComponenttype + " since its company field is not nullable.");
                }
            }
            for (Companypaymentscheme companypaymentschemeCollectionOldCompanypaymentscheme : companypaymentschemeCollectionOld) {
                if (!companypaymentschemeCollectionNew.contains(companypaymentschemeCollectionOldCompanypaymentscheme)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companypaymentscheme " + companypaymentschemeCollectionOldCompanypaymentscheme + " since its company field is not nullable.");
                }
            }
            for (Companycontactsaddress companycontactsaddressCollectionOldCompanycontactsaddress : companycontactsaddressCollectionOld) {
                if (!companycontactsaddressCollectionNew.contains(companycontactsaddressCollectionOldCompanycontactsaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companycontactsaddress " + companycontactsaddressCollectionOldCompanycontactsaddress + " since its company field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                company.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                company.setCompanyemployee1(companyemployee1New);
            }
            if (categoryNew != null) {
                categoryNew = em.getReference(categoryNew.getClass(), categoryNew.getId());
                company.setCategory(categoryNew);
            }
            Collection<Crmmessagechannel> attachedCrmmessagechannelCollectionNew = new ArrayList<Crmmessagechannel>();
            for (Crmmessagechannel crmmessagechannelCollectionNewCrmmessagechannelToAttach : crmmessagechannelCollectionNew) {
                crmmessagechannelCollectionNewCrmmessagechannelToAttach = em.getReference(crmmessagechannelCollectionNewCrmmessagechannelToAttach.getClass(), crmmessagechannelCollectionNewCrmmessagechannelToAttach.getCrmmessagechannelPK());
                attachedCrmmessagechannelCollectionNew.add(crmmessagechannelCollectionNewCrmmessagechannelToAttach);
            }
            crmmessagechannelCollectionNew = attachedCrmmessagechannelCollectionNew;
            company.setCrmmessagechannelCollection(crmmessagechannelCollectionNew);
            Collection<Companybank> attachedCompanybankCollectionNew = new ArrayList<Companybank>();
            for (Companybank companybankCollectionNewCompanybankToAttach : companybankCollectionNew) {
                companybankCollectionNewCompanybankToAttach = em.getReference(companybankCollectionNewCompanybankToAttach.getClass(), companybankCollectionNewCompanybankToAttach.getCompanybankPK());
                attachedCompanybankCollectionNew.add(companybankCollectionNewCompanybankToAttach);
            }
            companybankCollectionNew = attachedCompanybankCollectionNew;
            company.setCompanybankCollection(companybankCollectionNew);
            Collection<Crmprojectprops> attachedCrmprojectpropsCollectionNew = new ArrayList<Crmprojectprops>();
            for (Crmprojectprops crmprojectpropsCollectionNewCrmprojectpropsToAttach : crmprojectpropsCollectionNew) {
                crmprojectpropsCollectionNewCrmprojectpropsToAttach = em.getReference(crmprojectpropsCollectionNewCrmprojectpropsToAttach.getClass(), crmprojectpropsCollectionNewCrmprojectpropsToAttach.getCrmprojectpropsPK());
                attachedCrmprojectpropsCollectionNew.add(crmprojectpropsCollectionNewCrmprojectpropsToAttach);
            }
            crmprojectpropsCollectionNew = attachedCrmprojectpropsCollectionNew;
            company.setCrmprojectpropsCollection(crmprojectpropsCollectionNew);
            Collection<Crmcampaignreceiver> attachedCrmcampaignreceiverCollectionNew = new ArrayList<Crmcampaignreceiver>();
            for (Crmcampaignreceiver crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach : crmcampaignreceiverCollectionNew) {
                crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach = em.getReference(crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach.getClass(), crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach.getCrmcampaignreceiverPK());
                attachedCrmcampaignreceiverCollectionNew.add(crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach);
            }
            crmcampaignreceiverCollectionNew = attachedCrmcampaignreceiverCollectionNew;
            company.setCrmcampaignreceiverCollection(crmcampaignreceiverCollectionNew);
            Collection<Productattachments> attachedProductattachmentsCollectionNew = new ArrayList<Productattachments>();
            for (Productattachments productattachmentsCollectionNewProductattachmentsToAttach : productattachmentsCollectionNew) {
                productattachmentsCollectionNewProductattachmentsToAttach = em.getReference(productattachmentsCollectionNewProductattachmentsToAttach.getClass(), productattachmentsCollectionNewProductattachmentsToAttach.getProductattachmentsPK());
                attachedProductattachmentsCollectionNew.add(productattachmentsCollectionNewProductattachmentsToAttach);
            }
            productattachmentsCollectionNew = attachedProductattachmentsCollectionNew;
            company.setProductattachmentsCollection(productattachmentsCollectionNew);
            Collection<Crmcampaignstatus> attachedCrmcampaignstatusCollectionNew = new ArrayList<Crmcampaignstatus>();
            for (Crmcampaignstatus crmcampaignstatusCollectionNewCrmcampaignstatusToAttach : crmcampaignstatusCollectionNew) {
                crmcampaignstatusCollectionNewCrmcampaignstatusToAttach = em.getReference(crmcampaignstatusCollectionNewCrmcampaignstatusToAttach.getClass(), crmcampaignstatusCollectionNewCrmcampaignstatusToAttach.getCrmcampaignstatusPK());
                attachedCrmcampaignstatusCollectionNew.add(crmcampaignstatusCollectionNewCrmcampaignstatusToAttach);
            }
            crmcampaignstatusCollectionNew = attachedCrmcampaignstatusCollectionNew;
            company.setCrmcampaignstatusCollection(crmcampaignstatusCollectionNew);
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollectionNew = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollectionNewCrmcampaignpositionToAttach : crmcampaignpositionCollectionNew) {
                crmcampaignpositionCollectionNewCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollectionNewCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollectionNewCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollectionNew.add(crmcampaignpositionCollectionNewCrmcampaignpositionToAttach);
            }
            crmcampaignpositionCollectionNew = attachedCrmcampaignpositionCollectionNew;
            company.setCrmcampaignpositionCollection(crmcampaignpositionCollectionNew);
            Collection<Companydepartment> attachedCompanydepartmentCollectionNew = new ArrayList<Companydepartment>();
            for (Companydepartment companydepartmentCollectionNewCompanydepartmentToAttach : companydepartmentCollectionNew) {
                companydepartmentCollectionNewCompanydepartmentToAttach = em.getReference(companydepartmentCollectionNewCompanydepartmentToAttach.getClass(), companydepartmentCollectionNewCompanydepartmentToAttach.getCompanydepartmentPK());
                attachedCompanydepartmentCollectionNew.add(companydepartmentCollectionNewCompanydepartmentToAttach);
            }
            companydepartmentCollectionNew = attachedCompanydepartmentCollectionNew;
            company.setCompanydepartmentCollection(companydepartmentCollectionNew);
            Collection<Crmusermodules> attachedCrmusermodulesCollectionNew = new ArrayList<Crmusermodules>();
            for (Crmusermodules crmusermodulesCollectionNewCrmusermodulesToAttach : crmusermodulesCollectionNew) {
                crmusermodulesCollectionNewCrmusermodulesToAttach = em.getReference(crmusermodulesCollectionNewCrmusermodulesToAttach.getClass(), crmusermodulesCollectionNewCrmusermodulesToAttach.getCrmusermodulesPK());
                attachedCrmusermodulesCollectionNew.add(crmusermodulesCollectionNewCrmusermodulesToAttach);
            }
            crmusermodulesCollectionNew = attachedCrmusermodulesCollectionNew;
            company.setCrmusermodulesCollection(crmusermodulesCollectionNew);
            Collection<Crmexpense> attachedCrmexpenseCollectionNew = new ArrayList<Crmexpense>();
            for (Crmexpense crmexpenseCollectionNewCrmexpenseToAttach : crmexpenseCollectionNew) {
                crmexpenseCollectionNewCrmexpenseToAttach = em.getReference(crmexpenseCollectionNewCrmexpenseToAttach.getClass(), crmexpenseCollectionNewCrmexpenseToAttach.getCrmexpensePK());
                attachedCrmexpenseCollectionNew.add(crmexpenseCollectionNewCrmexpenseToAttach);
            }
            crmexpenseCollectionNew = attachedCrmexpenseCollectionNew;
            company.setCrmexpenseCollection(crmexpenseCollectionNew);
            Collection<Crmworkorder> attachedCrmworkorderCollectionNew = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionNewCrmworkorderToAttach : crmworkorderCollectionNew) {
                crmworkorderCollectionNewCrmworkorderToAttach = em.getReference(crmworkorderCollectionNewCrmworkorderToAttach.getClass(), crmworkorderCollectionNewCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollectionNew.add(crmworkorderCollectionNewCrmworkorderToAttach);
            }
            crmworkorderCollectionNew = attachedCrmworkorderCollectionNew;
            company.setCrmworkorderCollection(crmworkorderCollectionNew);
            Collection<Companycontacts> attachedCompanycontactsCollectionNew = new ArrayList<Companycontacts>();
            for (Companycontacts companycontactsCollectionNewCompanycontactsToAttach : companycontactsCollectionNew) {
                companycontactsCollectionNewCompanycontactsToAttach = em.getReference(companycontactsCollectionNewCompanycontactsToAttach.getClass(), companycontactsCollectionNewCompanycontactsToAttach.getCompanycontactsPK());
                attachedCompanycontactsCollectionNew.add(companycontactsCollectionNewCompanycontactsToAttach);
            }
            companycontactsCollectionNew = attachedCompanycontactsCollectionNew;
            company.setCompanycontactsCollection(companycontactsCollectionNew);
            Collection<Companyemployee> attachedCompanyemployeeCollectionNew = new ArrayList<Companyemployee>();
            for (Companyemployee companyemployeeCollectionNewCompanyemployeeToAttach : companyemployeeCollectionNew) {
                companyemployeeCollectionNewCompanyemployeeToAttach = em.getReference(companyemployeeCollectionNewCompanyemployeeToAttach.getClass(), companyemployeeCollectionNewCompanyemployeeToAttach.getCompanyemployeePK());
                attachedCompanyemployeeCollectionNew.add(companyemployeeCollectionNewCompanyemployeeToAttach);
            }
            companyemployeeCollectionNew = attachedCompanyemployeeCollectionNew;
            company.setCompanyemployeeCollection(companyemployeeCollectionNew);
            Collection<Companyproducttype> attachedCompanyproducttypeCollectionNew = new ArrayList<Companyproducttype>();
            for (Companyproducttype companyproducttypeCollectionNewCompanyproducttypeToAttach : companyproducttypeCollectionNew) {
                companyproducttypeCollectionNewCompanyproducttypeToAttach = em.getReference(companyproducttypeCollectionNewCompanyproducttypeToAttach.getClass(), companyproducttypeCollectionNewCompanyproducttypeToAttach.getCompanyproducttypePK());
                attachedCompanyproducttypeCollectionNew.add(companyproducttypeCollectionNewCompanyproducttypeToAttach);
            }
            companyproducttypeCollectionNew = attachedCompanyproducttypeCollectionNew;
            company.setCompanyproducttypeCollection(companyproducttypeCollectionNew);
            Collection<Crmcampaign> attachedCrmcampaignCollectionNew = new ArrayList<Crmcampaign>();
            for (Crmcampaign crmcampaignCollectionNewCrmcampaignToAttach : crmcampaignCollectionNew) {
                crmcampaignCollectionNewCrmcampaignToAttach = em.getReference(crmcampaignCollectionNewCrmcampaignToAttach.getClass(), crmcampaignCollectionNewCrmcampaignToAttach.getCrmcampaignPK());
                attachedCrmcampaignCollectionNew.add(crmcampaignCollectionNewCrmcampaignToAttach);
            }
            crmcampaignCollectionNew = attachedCrmcampaignCollectionNew;
            company.setCrmcampaignCollection(crmcampaignCollectionNew);
            Collection<Companyhirarchie> attachedCompanyhirarchieCollectionNew = new ArrayList<Companyhirarchie>();
            for (Companyhirarchie companyhirarchieCollectionNewCompanyhirarchieToAttach : companyhirarchieCollectionNew) {
                companyhirarchieCollectionNewCompanyhirarchieToAttach = em.getReference(companyhirarchieCollectionNewCompanyhirarchieToAttach.getClass(), companyhirarchieCollectionNewCompanyhirarchieToAttach.getCompanyhirarchiePK());
                attachedCompanyhirarchieCollectionNew.add(companyhirarchieCollectionNewCompanyhirarchieToAttach);
            }
            companyhirarchieCollectionNew = attachedCompanyhirarchieCollectionNew;
            company.setCompanyhirarchieCollection(companyhirarchieCollectionNew);
            Collection<Companyhirarchie> attachedCompanyhirarchieCollection1New = new ArrayList<Companyhirarchie>();
            for (Companyhirarchie companyhirarchieCollection1NewCompanyhirarchieToAttach : companyhirarchieCollection1New) {
                companyhirarchieCollection1NewCompanyhirarchieToAttach = em.getReference(companyhirarchieCollection1NewCompanyhirarchieToAttach.getClass(), companyhirarchieCollection1NewCompanyhirarchieToAttach.getCompanyhirarchiePK());
                attachedCompanyhirarchieCollection1New.add(companyhirarchieCollection1NewCompanyhirarchieToAttach);
            }
            companyhirarchieCollection1New = attachedCompanyhirarchieCollection1New;
            company.setCompanyhirarchieCollection1(companyhirarchieCollection1New);
            Collection<Companyhirarchie> attachedCompanyhirarchieCollection2New = new ArrayList<Companyhirarchie>();
            for (Companyhirarchie companyhirarchieCollection2NewCompanyhirarchieToAttach : companyhirarchieCollection2New) {
                companyhirarchieCollection2NewCompanyhirarchieToAttach = em.getReference(companyhirarchieCollection2NewCompanyhirarchieToAttach.getClass(), companyhirarchieCollection2NewCompanyhirarchieToAttach.getCompanyhirarchiePK());
                attachedCompanyhirarchieCollection2New.add(companyhirarchieCollection2NewCompanyhirarchieToAttach);
            }
            companyhirarchieCollection2New = attachedCompanyhirarchieCollection2New;
            company.setCompanyhirarchieCollection2(companyhirarchieCollection2New);
            Collection<Companyaccountstackdocs> attachedCompanyaccountstackdocsCollectionNew = new ArrayList<Companyaccountstackdocs>();
            for (Companyaccountstackdocs companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach : companyaccountstackdocsCollectionNew) {
                companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach = em.getReference(companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach.getClass(), companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach.getCompanyaccountstackdocsPK());
                attachedCompanyaccountstackdocsCollectionNew.add(companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach);
            }
            companyaccountstackdocsCollectionNew = attachedCompanyaccountstackdocsCollectionNew;
            company.setCompanyaccountstackdocsCollection(companyaccountstackdocsCollectionNew);
            Collection<Crmlabor> attachedCrmlaborCollectionNew = new ArrayList<Crmlabor>();
            for (Crmlabor crmlaborCollectionNewCrmlaborToAttach : crmlaborCollectionNew) {
                crmlaborCollectionNewCrmlaborToAttach = em.getReference(crmlaborCollectionNewCrmlaborToAttach.getClass(), crmlaborCollectionNewCrmlaborToAttach.getCrmlaborPK());
                attachedCrmlaborCollectionNew.add(crmlaborCollectionNewCrmlaborToAttach);
            }
            crmlaborCollectionNew = attachedCrmlaborCollectionNew;
            company.setCrmlaborCollection(crmlaborCollectionNew);
            Collection<Compnaypaymentcurrency> attachedCompnaypaymentcurrencyCollectionNew = new ArrayList<Compnaypaymentcurrency>();
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionNewCompnaypaymentcurrencyToAttach : compnaypaymentcurrencyCollectionNew) {
                compnaypaymentcurrencyCollectionNewCompnaypaymentcurrencyToAttach = em.getReference(compnaypaymentcurrencyCollectionNewCompnaypaymentcurrencyToAttach.getClass(), compnaypaymentcurrencyCollectionNewCompnaypaymentcurrencyToAttach.getCompnaypaymentcurrencyPK());
                attachedCompnaypaymentcurrencyCollectionNew.add(compnaypaymentcurrencyCollectionNewCompnaypaymentcurrencyToAttach);
            }
            compnaypaymentcurrencyCollectionNew = attachedCompnaypaymentcurrencyCollectionNew;
            company.setCompnaypaymentcurrencyCollection(compnaypaymentcurrencyCollectionNew);
            Collection<Companyproduct> attachedCompanyproductCollectionNew = new ArrayList<Companyproduct>();
            for (Companyproduct companyproductCollectionNewCompanyproductToAttach : companyproductCollectionNew) {
                companyproductCollectionNewCompanyproductToAttach = em.getReference(companyproductCollectionNewCompanyproductToAttach.getClass(), companyproductCollectionNewCompanyproductToAttach.getCompanyproductPK());
                attachedCompanyproductCollectionNew.add(companyproductCollectionNewCompanyproductToAttach);
            }
            companyproductCollectionNew = attachedCompanyproductCollectionNew;
            company.setCompanyproductCollection(companyproductCollectionNew);
            Collection<Employeedesignation> attachedEmployeedesignationCollectionNew = new ArrayList<Employeedesignation>();
            for (Employeedesignation employeedesignationCollectionNewEmployeedesignationToAttach : employeedesignationCollectionNew) {
                employeedesignationCollectionNewEmployeedesignationToAttach = em.getReference(employeedesignationCollectionNewEmployeedesignationToAttach.getClass(), employeedesignationCollectionNewEmployeedesignationToAttach.getEmployeedesignationPK());
                attachedEmployeedesignationCollectionNew.add(employeedesignationCollectionNewEmployeedesignationToAttach);
            }
            employeedesignationCollectionNew = attachedEmployeedesignationCollectionNew;
            company.setEmployeedesignationCollection(employeedesignationCollectionNew);
            Collection<Companyaccountstack> attachedCompanyaccountstackCollectionNew = new ArrayList<Companyaccountstack>();
            for (Companyaccountstack companyaccountstackCollectionNewCompanyaccountstackToAttach : companyaccountstackCollectionNew) {
                companyaccountstackCollectionNewCompanyaccountstackToAttach = em.getReference(companyaccountstackCollectionNewCompanyaccountstackToAttach.getClass(), companyaccountstackCollectionNewCompanyaccountstackToAttach.getCompanyaccountstackPK());
                attachedCompanyaccountstackCollectionNew.add(companyaccountstackCollectionNewCompanyaccountstackToAttach);
            }
            companyaccountstackCollectionNew = attachedCompanyaccountstackCollectionNew;
            company.setCompanyaccountstackCollection(companyaccountstackCollectionNew);
            Collection<Componentattachments> attachedComponentattachmentsCollectionNew = new ArrayList<Componentattachments>();
            for (Componentattachments componentattachmentsCollectionNewComponentattachmentsToAttach : componentattachmentsCollectionNew) {
                componentattachmentsCollectionNewComponentattachmentsToAttach = em.getReference(componentattachmentsCollectionNewComponentattachmentsToAttach.getClass(), componentattachmentsCollectionNewComponentattachmentsToAttach.getComponentattachmentsPK());
                attachedComponentattachmentsCollectionNew.add(componentattachmentsCollectionNewComponentattachmentsToAttach);
            }
            componentattachmentsCollectionNew = attachedComponentattachmentsCollectionNew;
            company.setComponentattachmentsCollection(componentattachmentsCollectionNew);
            Collection<Companyaccountstackcd> attachedCompanyaccountstackcdCollectionNew = new ArrayList<Companyaccountstackcd>();
            for (Companyaccountstackcd companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach : companyaccountstackcdCollectionNew) {
                companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach = em.getReference(companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach.getClass(), companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach.getCompanyaccountstackcdPK());
                attachedCompanyaccountstackcdCollectionNew.add(companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach);
            }
            companyaccountstackcdCollectionNew = attachedCompanyaccountstackcdCollectionNew;
            company.setCompanyaccountstackcdCollection(companyaccountstackcdCollectionNew);
            Collection<Crmworkordertype> attachedCrmworkordertypeCollectionNew = new ArrayList<Crmworkordertype>();
            for (Crmworkordertype crmworkordertypeCollectionNewCrmworkordertypeToAttach : crmworkordertypeCollectionNew) {
                crmworkordertypeCollectionNewCrmworkordertypeToAttach = em.getReference(crmworkordertypeCollectionNewCrmworkordertypeToAttach.getClass(), crmworkordertypeCollectionNewCrmworkordertypeToAttach.getCrmworkordertypePK());
                attachedCrmworkordertypeCollectionNew.add(crmworkordertypeCollectionNewCrmworkordertypeToAttach);
            }
            crmworkordertypeCollectionNew = attachedCrmworkordertypeCollectionNew;
            company.setCrmworkordertypeCollection(crmworkordertypeCollectionNew);
            Collection<Crmlabortype> attachedCrmlabortypeCollectionNew = new ArrayList<Crmlabortype>();
            for (Crmlabortype crmlabortypeCollectionNewCrmlabortypeToAttach : crmlabortypeCollectionNew) {
                crmlabortypeCollectionNewCrmlabortypeToAttach = em.getReference(crmlabortypeCollectionNewCrmlabortypeToAttach.getClass(), crmlabortypeCollectionNewCrmlabortypeToAttach.getCrmlabortypePK());
                attachedCrmlabortypeCollectionNew.add(crmlabortypeCollectionNewCrmlabortypeToAttach);
            }
            crmlabortypeCollectionNew = attachedCrmlabortypeCollectionNew;
            company.setCrmlabortypeCollection(crmlabortypeCollectionNew);
            Collection<Crmbillingtype> attachedCrmbillingtypeCollectionNew = new ArrayList<Crmbillingtype>();
            for (Crmbillingtype crmbillingtypeCollectionNewCrmbillingtypeToAttach : crmbillingtypeCollectionNew) {
                crmbillingtypeCollectionNewCrmbillingtypeToAttach = em.getReference(crmbillingtypeCollectionNewCrmbillingtypeToAttach.getClass(), crmbillingtypeCollectionNewCrmbillingtypeToAttach.getCrmbillingtypePK());
                attachedCrmbillingtypeCollectionNew.add(crmbillingtypeCollectionNewCrmbillingtypeToAttach);
            }
            crmbillingtypeCollectionNew = attachedCrmbillingtypeCollectionNew;
            company.setCrmbillingtypeCollection(crmbillingtypeCollectionNew);
            Collection<Companyproductcategory> attachedCompanyproductcategoryCollectionNew = new ArrayList<Companyproductcategory>();
            for (Companyproductcategory companyproductcategoryCollectionNewCompanyproductcategoryToAttach : companyproductcategoryCollectionNew) {
                companyproductcategoryCollectionNewCompanyproductcategoryToAttach = em.getReference(companyproductcategoryCollectionNewCompanyproductcategoryToAttach.getClass(), companyproductcategoryCollectionNewCompanyproductcategoryToAttach.getCompanyproductcategoryPK());
                attachedCompanyproductcategoryCollectionNew.add(companyproductcategoryCollectionNewCompanyproductcategoryToAttach);
            }
            companyproductcategoryCollectionNew = attachedCompanyproductcategoryCollectionNew;
            company.setCompanyproductcategoryCollection(companyproductcategoryCollectionNew);
            Collection<Crmcampaignprops> attachedCrmcampaignpropsCollectionNew = new ArrayList<Crmcampaignprops>();
            for (Crmcampaignprops crmcampaignpropsCollectionNewCrmcampaignpropsToAttach : crmcampaignpropsCollectionNew) {
                crmcampaignpropsCollectionNewCrmcampaignpropsToAttach = em.getReference(crmcampaignpropsCollectionNewCrmcampaignpropsToAttach.getClass(), crmcampaignpropsCollectionNewCrmcampaignpropsToAttach.getCrmcampaignpropsPK());
                attachedCrmcampaignpropsCollectionNew.add(crmcampaignpropsCollectionNewCrmcampaignpropsToAttach);
            }
            crmcampaignpropsCollectionNew = attachedCrmcampaignpropsCollectionNew;
            company.setCrmcampaignpropsCollection(crmcampaignpropsCollectionNew);
            Collection<Customercategory> attachedCustomercategoryCollectionNew = new ArrayList<Customercategory>();
            for (Customercategory customercategoryCollectionNewCustomercategoryToAttach : customercategoryCollectionNew) {
                customercategoryCollectionNewCustomercategoryToAttach = em.getReference(customercategoryCollectionNewCustomercategoryToAttach.getClass(), customercategoryCollectionNewCustomercategoryToAttach.getCustomercategoryPK());
                attachedCustomercategoryCollectionNew.add(customercategoryCollectionNewCustomercategoryToAttach);
            }
            customercategoryCollectionNew = attachedCustomercategoryCollectionNew;
            company.setCustomercategoryCollection(customercategoryCollectionNew);
            Collection<Productcomponents> attachedProductcomponentsCollectionNew = new ArrayList<Productcomponents>();
            for (Productcomponents productcomponentsCollectionNewProductcomponentsToAttach : productcomponentsCollectionNew) {
                productcomponentsCollectionNewProductcomponentsToAttach = em.getReference(productcomponentsCollectionNewProductcomponentsToAttach.getClass(), productcomponentsCollectionNewProductcomponentsToAttach.getProductcomponentsPK());
                attachedProductcomponentsCollectionNew.add(productcomponentsCollectionNewProductcomponentsToAttach);
            }
            productcomponentsCollectionNew = attachedProductcomponentsCollectionNew;
            company.setProductcomponentsCollection(productcomponentsCollectionNew);
            Collection<Crmexpensetype> attachedCrmexpensetypeCollectionNew = new ArrayList<Crmexpensetype>();
            for (Crmexpensetype crmexpensetypeCollectionNewCrmexpensetypeToAttach : crmexpensetypeCollectionNew) {
                crmexpensetypeCollectionNewCrmexpensetypeToAttach = em.getReference(crmexpensetypeCollectionNewCrmexpensetypeToAttach.getClass(), crmexpensetypeCollectionNewCrmexpensetypeToAttach.getCrmexpensetypePK());
                attachedCrmexpensetypeCollectionNew.add(crmexpensetypeCollectionNewCrmexpensetypeToAttach);
            }
            crmexpensetypeCollectionNew = attachedCrmexpensetypeCollectionNew;
            company.setCrmexpensetypeCollection(crmexpensetypeCollectionNew);
            Collection<Companypayments> attachedCompanypaymentsCollectionNew = new ArrayList<Companypayments>();
            for (Companypayments companypaymentsCollectionNewCompanypaymentsToAttach : companypaymentsCollectionNew) {
                companypaymentsCollectionNewCompanypaymentsToAttach = em.getReference(companypaymentsCollectionNewCompanypaymentsToAttach.getClass(), companypaymentsCollectionNewCompanypaymentsToAttach.getCompanypaymentsPK());
                attachedCompanypaymentsCollectionNew.add(companypaymentsCollectionNewCompanypaymentsToAttach);
            }
            companypaymentsCollectionNew = attachedCompanypaymentsCollectionNew;
            company.setCompanypaymentsCollection(companypaymentsCollectionNew);
            Collection<Crmworkorderresolution> attachedCrmworkorderresolutionCollectionNew = new ArrayList<Crmworkorderresolution>();
            for (Crmworkorderresolution crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach : crmworkorderresolutionCollectionNew) {
                crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach = em.getReference(crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach.getClass(), crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach.getCrmworkorderresolutionPK());
                attachedCrmworkorderresolutionCollectionNew.add(crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach);
            }
            crmworkorderresolutionCollectionNew = attachedCrmworkorderresolutionCollectionNew;
            company.setCrmworkorderresolutionCollection(crmworkorderresolutionCollectionNew);
            Collection<Companyaddresstype> attachedCompanyaddresstypeCollectionNew = new ArrayList<Companyaddresstype>();
            for (Companyaddresstype companyaddresstypeCollectionNewCompanyaddresstypeToAttach : companyaddresstypeCollectionNew) {
                companyaddresstypeCollectionNewCompanyaddresstypeToAttach = em.getReference(companyaddresstypeCollectionNewCompanyaddresstypeToAttach.getClass(), companyaddresstypeCollectionNewCompanyaddresstypeToAttach.getCompanyaddresstypePK());
                attachedCompanyaddresstypeCollectionNew.add(companyaddresstypeCollectionNewCompanyaddresstypeToAttach);
            }
            companyaddresstypeCollectionNew = attachedCompanyaddresstypeCollectionNew;
            company.setCompanyaddresstypeCollection(companyaddresstypeCollectionNew);
            Collection<Companycustomer> attachedCompanycustomerCollectionNew = new ArrayList<Companycustomer>();
            for (Companycustomer companycustomerCollectionNewCompanycustomerToAttach : companycustomerCollectionNew) {
                companycustomerCollectionNewCompanycustomerToAttach = em.getReference(companycustomerCollectionNewCompanycustomerToAttach.getClass(), companycustomerCollectionNewCompanycustomerToAttach.getCompanycustomerPK());
                attachedCompanycustomerCollectionNew.add(companycustomerCollectionNewCompanycustomerToAttach);
            }
            companycustomerCollectionNew = attachedCompanycustomerCollectionNew;
            company.setCompanycustomerCollection(companycustomerCollectionNew);
            Collection<Crmprojectstatus> attachedCrmprojectstatusCollectionNew = new ArrayList<Crmprojectstatus>();
            for (Crmprojectstatus crmprojectstatusCollectionNewCrmprojectstatusToAttach : crmprojectstatusCollectionNew) {
                crmprojectstatusCollectionNewCrmprojectstatusToAttach = em.getReference(crmprojectstatusCollectionNewCrmprojectstatusToAttach.getClass(), crmprojectstatusCollectionNewCrmprojectstatusToAttach.getCrmprojectstatusPK());
                attachedCrmprojectstatusCollectionNew.add(crmprojectstatusCollectionNewCrmprojectstatusToAttach);
            }
            crmprojectstatusCollectionNew = attachedCrmprojectstatusCollectionNew;
            company.setCrmprojectstatusCollection(crmprojectstatusCollectionNew);
            Collection<Crmschedulerevnttype> attachedCrmschedulerevnttypeCollectionNew = new ArrayList<Crmschedulerevnttype>();
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionNewCrmschedulerevnttypeToAttach : crmschedulerevnttypeCollectionNew) {
                crmschedulerevnttypeCollectionNewCrmschedulerevnttypeToAttach = em.getReference(crmschedulerevnttypeCollectionNewCrmschedulerevnttypeToAttach.getClass(), crmschedulerevnttypeCollectionNewCrmschedulerevnttypeToAttach.getCrmschedulerevnttypePK());
                attachedCrmschedulerevnttypeCollectionNew.add(crmschedulerevnttypeCollectionNewCrmschedulerevnttypeToAttach);
            }
            crmschedulerevnttypeCollectionNew = attachedCrmschedulerevnttypeCollectionNew;
            company.setCrmschedulerevnttypeCollection(crmschedulerevnttypeCollectionNew);
            Collection<Componenttype> attachedComponenttypeCollectionNew = new ArrayList<Componenttype>();
            for (Componenttype componenttypeCollectionNewComponenttypeToAttach : componenttypeCollectionNew) {
                componenttypeCollectionNewComponenttypeToAttach = em.getReference(componenttypeCollectionNewComponenttypeToAttach.getClass(), componenttypeCollectionNewComponenttypeToAttach.getComponenttypePK());
                attachedComponenttypeCollectionNew.add(componenttypeCollectionNewComponenttypeToAttach);
            }
            componenttypeCollectionNew = attachedComponenttypeCollectionNew;
            company.setComponenttypeCollection(componenttypeCollectionNew);
            Collection<Companypaymentscheme> attachedCompanypaymentschemeCollectionNew = new ArrayList<Companypaymentscheme>();
            for (Companypaymentscheme companypaymentschemeCollectionNewCompanypaymentschemeToAttach : companypaymentschemeCollectionNew) {
                companypaymentschemeCollectionNewCompanypaymentschemeToAttach = em.getReference(companypaymentschemeCollectionNewCompanypaymentschemeToAttach.getClass(), companypaymentschemeCollectionNewCompanypaymentschemeToAttach.getCompanypaymentschemePK());
                attachedCompanypaymentschemeCollectionNew.add(companypaymentschemeCollectionNewCompanypaymentschemeToAttach);
            }
            companypaymentschemeCollectionNew = attachedCompanypaymentschemeCollectionNew;
            company.setCompanypaymentschemeCollection(companypaymentschemeCollectionNew);
            Collection<Companycontactsaddress> attachedCompanycontactsaddressCollectionNew = new ArrayList<Companycontactsaddress>();
            for (Companycontactsaddress companycontactsaddressCollectionNewCompanycontactsaddressToAttach : companycontactsaddressCollectionNew) {
                companycontactsaddressCollectionNewCompanycontactsaddressToAttach = em.getReference(companycontactsaddressCollectionNewCompanycontactsaddressToAttach.getClass(), companycontactsaddressCollectionNewCompanycontactsaddressToAttach.getCompanycontactsaddressPK());
                attachedCompanycontactsaddressCollectionNew.add(companycontactsaddressCollectionNewCompanycontactsaddressToAttach);
            }
            companycontactsaddressCollectionNew = attachedCompanycontactsaddressCollectionNew;
            company.setCompanycontactsaddressCollection(companycontactsaddressCollectionNew);
            company = em.merge(company);
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.setCompany(company);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.setCompany(company);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (categoryOld != null && !categoryOld.equals(categoryNew)) {
                categoryOld.getCompanyCollection().remove(company);
                categoryOld = em.merge(categoryOld);
            }
            if (categoryNew != null && !categoryNew.equals(categoryOld)) {
                categoryNew.getCompanyCollection().add(company);
                categoryNew = em.merge(categoryNew);
            }
            for (Crmmessagechannel crmmessagechannelCollectionNewCrmmessagechannel : crmmessagechannelCollectionNew) {
                if (!crmmessagechannelCollectionOld.contains(crmmessagechannelCollectionNewCrmmessagechannel)) {
                    Company oldCompanyOfCrmmessagechannelCollectionNewCrmmessagechannel = crmmessagechannelCollectionNewCrmmessagechannel.getCompany();
                    crmmessagechannelCollectionNewCrmmessagechannel.setCompany(company);
                    crmmessagechannelCollectionNewCrmmessagechannel = em.merge(crmmessagechannelCollectionNewCrmmessagechannel);
                    if (oldCompanyOfCrmmessagechannelCollectionNewCrmmessagechannel != null && !oldCompanyOfCrmmessagechannelCollectionNewCrmmessagechannel.equals(company)) {
                        oldCompanyOfCrmmessagechannelCollectionNewCrmmessagechannel.getCrmmessagechannelCollection().remove(crmmessagechannelCollectionNewCrmmessagechannel);
                        oldCompanyOfCrmmessagechannelCollectionNewCrmmessagechannel = em.merge(oldCompanyOfCrmmessagechannelCollectionNewCrmmessagechannel);
                    }
                }
            }
            for (Companybank companybankCollectionNewCompanybank : companybankCollectionNew) {
                if (!companybankCollectionOld.contains(companybankCollectionNewCompanybank)) {
                    Company oldCompanyOfCompanybankCollectionNewCompanybank = companybankCollectionNewCompanybank.getCompany();
                    companybankCollectionNewCompanybank.setCompany(company);
                    companybankCollectionNewCompanybank = em.merge(companybankCollectionNewCompanybank);
                    if (oldCompanyOfCompanybankCollectionNewCompanybank != null && !oldCompanyOfCompanybankCollectionNewCompanybank.equals(company)) {
                        oldCompanyOfCompanybankCollectionNewCompanybank.getCompanybankCollection().remove(companybankCollectionNewCompanybank);
                        oldCompanyOfCompanybankCollectionNewCompanybank = em.merge(oldCompanyOfCompanybankCollectionNewCompanybank);
                    }
                }
            }
            for (Crmprojectprops crmprojectpropsCollectionNewCrmprojectprops : crmprojectpropsCollectionNew) {
                if (!crmprojectpropsCollectionOld.contains(crmprojectpropsCollectionNewCrmprojectprops)) {
                    Company oldCompanyOfCrmprojectpropsCollectionNewCrmprojectprops = crmprojectpropsCollectionNewCrmprojectprops.getCompany();
                    crmprojectpropsCollectionNewCrmprojectprops.setCompany(company);
                    crmprojectpropsCollectionNewCrmprojectprops = em.merge(crmprojectpropsCollectionNewCrmprojectprops);
                    if (oldCompanyOfCrmprojectpropsCollectionNewCrmprojectprops != null && !oldCompanyOfCrmprojectpropsCollectionNewCrmprojectprops.equals(company)) {
                        oldCompanyOfCrmprojectpropsCollectionNewCrmprojectprops.getCrmprojectpropsCollection().remove(crmprojectpropsCollectionNewCrmprojectprops);
                        oldCompanyOfCrmprojectpropsCollectionNewCrmprojectprops = em.merge(oldCompanyOfCrmprojectpropsCollectionNewCrmprojectprops);
                    }
                }
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollectionNewCrmcampaignreceiver : crmcampaignreceiverCollectionNew) {
                if (!crmcampaignreceiverCollectionOld.contains(crmcampaignreceiverCollectionNewCrmcampaignreceiver)) {
                    Company oldCompanyOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver = crmcampaignreceiverCollectionNewCrmcampaignreceiver.getCompany();
                    crmcampaignreceiverCollectionNewCrmcampaignreceiver.setCompany(company);
                    crmcampaignreceiverCollectionNewCrmcampaignreceiver = em.merge(crmcampaignreceiverCollectionNewCrmcampaignreceiver);
                    if (oldCompanyOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver != null && !oldCompanyOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver.equals(company)) {
                        oldCompanyOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver.getCrmcampaignreceiverCollection().remove(crmcampaignreceiverCollectionNewCrmcampaignreceiver);
                        oldCompanyOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver = em.merge(oldCompanyOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver);
                    }
                }
            }
            for (Productattachments productattachmentsCollectionNewProductattachments : productattachmentsCollectionNew) {
                if (!productattachmentsCollectionOld.contains(productattachmentsCollectionNewProductattachments)) {
                    Company oldCompanyOfProductattachmentsCollectionNewProductattachments = productattachmentsCollectionNewProductattachments.getCompany();
                    productattachmentsCollectionNewProductattachments.setCompany(company);
                    productattachmentsCollectionNewProductattachments = em.merge(productattachmentsCollectionNewProductattachments);
                    if (oldCompanyOfProductattachmentsCollectionNewProductattachments != null && !oldCompanyOfProductattachmentsCollectionNewProductattachments.equals(company)) {
                        oldCompanyOfProductattachmentsCollectionNewProductattachments.getProductattachmentsCollection().remove(productattachmentsCollectionNewProductattachments);
                        oldCompanyOfProductattachmentsCollectionNewProductattachments = em.merge(oldCompanyOfProductattachmentsCollectionNewProductattachments);
                    }
                }
            }
            for (Crmcampaignstatus crmcampaignstatusCollectionNewCrmcampaignstatus : crmcampaignstatusCollectionNew) {
                if (!crmcampaignstatusCollectionOld.contains(crmcampaignstatusCollectionNewCrmcampaignstatus)) {
                    Company oldCompanyOfCrmcampaignstatusCollectionNewCrmcampaignstatus = crmcampaignstatusCollectionNewCrmcampaignstatus.getCompany();
                    crmcampaignstatusCollectionNewCrmcampaignstatus.setCompany(company);
                    crmcampaignstatusCollectionNewCrmcampaignstatus = em.merge(crmcampaignstatusCollectionNewCrmcampaignstatus);
                    if (oldCompanyOfCrmcampaignstatusCollectionNewCrmcampaignstatus != null && !oldCompanyOfCrmcampaignstatusCollectionNewCrmcampaignstatus.equals(company)) {
                        oldCompanyOfCrmcampaignstatusCollectionNewCrmcampaignstatus.getCrmcampaignstatusCollection().remove(crmcampaignstatusCollectionNewCrmcampaignstatus);
                        oldCompanyOfCrmcampaignstatusCollectionNewCrmcampaignstatus = em.merge(oldCompanyOfCrmcampaignstatusCollectionNewCrmcampaignstatus);
                    }
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollectionNewCrmcampaignposition : crmcampaignpositionCollectionNew) {
                if (!crmcampaignpositionCollectionOld.contains(crmcampaignpositionCollectionNewCrmcampaignposition)) {
                    Company oldCompanyOfCrmcampaignpositionCollectionNewCrmcampaignposition = crmcampaignpositionCollectionNewCrmcampaignposition.getCompany();
                    crmcampaignpositionCollectionNewCrmcampaignposition.setCompany(company);
                    crmcampaignpositionCollectionNewCrmcampaignposition = em.merge(crmcampaignpositionCollectionNewCrmcampaignposition);
                    if (oldCompanyOfCrmcampaignpositionCollectionNewCrmcampaignposition != null && !oldCompanyOfCrmcampaignpositionCollectionNewCrmcampaignposition.equals(company)) {
                        oldCompanyOfCrmcampaignpositionCollectionNewCrmcampaignposition.getCrmcampaignpositionCollection().remove(crmcampaignpositionCollectionNewCrmcampaignposition);
                        oldCompanyOfCrmcampaignpositionCollectionNewCrmcampaignposition = em.merge(oldCompanyOfCrmcampaignpositionCollectionNewCrmcampaignposition);
                    }
                }
            }
            for (Companydepartment companydepartmentCollectionNewCompanydepartment : companydepartmentCollectionNew) {
                if (!companydepartmentCollectionOld.contains(companydepartmentCollectionNewCompanydepartment)) {
                    Company oldCompanyOfCompanydepartmentCollectionNewCompanydepartment = companydepartmentCollectionNewCompanydepartment.getCompany();
                    companydepartmentCollectionNewCompanydepartment.setCompany(company);
                    companydepartmentCollectionNewCompanydepartment = em.merge(companydepartmentCollectionNewCompanydepartment);
                    if (oldCompanyOfCompanydepartmentCollectionNewCompanydepartment != null && !oldCompanyOfCompanydepartmentCollectionNewCompanydepartment.equals(company)) {
                        oldCompanyOfCompanydepartmentCollectionNewCompanydepartment.getCompanydepartmentCollection().remove(companydepartmentCollectionNewCompanydepartment);
                        oldCompanyOfCompanydepartmentCollectionNewCompanydepartment = em.merge(oldCompanyOfCompanydepartmentCollectionNewCompanydepartment);
                    }
                }
            }
            for (Crmusermodules crmusermodulesCollectionNewCrmusermodules : crmusermodulesCollectionNew) {
                if (!crmusermodulesCollectionOld.contains(crmusermodulesCollectionNewCrmusermodules)) {
                    Company oldCompanyOfCrmusermodulesCollectionNewCrmusermodules = crmusermodulesCollectionNewCrmusermodules.getCompany();
                    crmusermodulesCollectionNewCrmusermodules.setCompany(company);
                    crmusermodulesCollectionNewCrmusermodules = em.merge(crmusermodulesCollectionNewCrmusermodules);
                    if (oldCompanyOfCrmusermodulesCollectionNewCrmusermodules != null && !oldCompanyOfCrmusermodulesCollectionNewCrmusermodules.equals(company)) {
                        oldCompanyOfCrmusermodulesCollectionNewCrmusermodules.getCrmusermodulesCollection().remove(crmusermodulesCollectionNewCrmusermodules);
                        oldCompanyOfCrmusermodulesCollectionNewCrmusermodules = em.merge(oldCompanyOfCrmusermodulesCollectionNewCrmusermodules);
                    }
                }
            }
            for (Crmexpense crmexpenseCollectionNewCrmexpense : crmexpenseCollectionNew) {
                if (!crmexpenseCollectionOld.contains(crmexpenseCollectionNewCrmexpense)) {
                    Company oldCompanyOfCrmexpenseCollectionNewCrmexpense = crmexpenseCollectionNewCrmexpense.getCompany();
                    crmexpenseCollectionNewCrmexpense.setCompany(company);
                    crmexpenseCollectionNewCrmexpense = em.merge(crmexpenseCollectionNewCrmexpense);
                    if (oldCompanyOfCrmexpenseCollectionNewCrmexpense != null && !oldCompanyOfCrmexpenseCollectionNewCrmexpense.equals(company)) {
                        oldCompanyOfCrmexpenseCollectionNewCrmexpense.getCrmexpenseCollection().remove(crmexpenseCollectionNewCrmexpense);
                        oldCompanyOfCrmexpenseCollectionNewCrmexpense = em.merge(oldCompanyOfCrmexpenseCollectionNewCrmexpense);
                    }
                }
            }
            for (Crmworkorder crmworkorderCollectionNewCrmworkorder : crmworkorderCollectionNew) {
                if (!crmworkorderCollectionOld.contains(crmworkorderCollectionNewCrmworkorder)) {
                    Company oldCompanyOfCrmworkorderCollectionNewCrmworkorder = crmworkorderCollectionNewCrmworkorder.getCompany();
                    crmworkorderCollectionNewCrmworkorder.setCompany(company);
                    crmworkorderCollectionNewCrmworkorder = em.merge(crmworkorderCollectionNewCrmworkorder);
                    if (oldCompanyOfCrmworkorderCollectionNewCrmworkorder != null && !oldCompanyOfCrmworkorderCollectionNewCrmworkorder.equals(company)) {
                        oldCompanyOfCrmworkorderCollectionNewCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionNewCrmworkorder);
                        oldCompanyOfCrmworkorderCollectionNewCrmworkorder = em.merge(oldCompanyOfCrmworkorderCollectionNewCrmworkorder);
                    }
                }
            }
            for (Companycontacts companycontactsCollectionNewCompanycontacts : companycontactsCollectionNew) {
                if (!companycontactsCollectionOld.contains(companycontactsCollectionNewCompanycontacts)) {
                    Company oldCompanyOfCompanycontactsCollectionNewCompanycontacts = companycontactsCollectionNewCompanycontacts.getCompany();
                    companycontactsCollectionNewCompanycontacts.setCompany(company);
                    companycontactsCollectionNewCompanycontacts = em.merge(companycontactsCollectionNewCompanycontacts);
                    if (oldCompanyOfCompanycontactsCollectionNewCompanycontacts != null && !oldCompanyOfCompanycontactsCollectionNewCompanycontacts.equals(company)) {
                        oldCompanyOfCompanycontactsCollectionNewCompanycontacts.getCompanycontactsCollection().remove(companycontactsCollectionNewCompanycontacts);
                        oldCompanyOfCompanycontactsCollectionNewCompanycontacts = em.merge(oldCompanyOfCompanycontactsCollectionNewCompanycontacts);
                    }
                }
            }
            for (Companyemployee companyemployeeCollectionNewCompanyemployee : companyemployeeCollectionNew) {
                if (!companyemployeeCollectionOld.contains(companyemployeeCollectionNewCompanyemployee)) {
                    Company oldCompanyOfCompanyemployeeCollectionNewCompanyemployee = companyemployeeCollectionNewCompanyemployee.getCompany();
                    companyemployeeCollectionNewCompanyemployee.setCompany(company);
                    companyemployeeCollectionNewCompanyemployee = em.merge(companyemployeeCollectionNewCompanyemployee);
                    if (oldCompanyOfCompanyemployeeCollectionNewCompanyemployee != null && !oldCompanyOfCompanyemployeeCollectionNewCompanyemployee.equals(company)) {
                        oldCompanyOfCompanyemployeeCollectionNewCompanyemployee.getCompanyemployeeCollection().remove(companyemployeeCollectionNewCompanyemployee);
                        oldCompanyOfCompanyemployeeCollectionNewCompanyemployee = em.merge(oldCompanyOfCompanyemployeeCollectionNewCompanyemployee);
                    }
                }
            }
            for (Companyproducttype companyproducttypeCollectionNewCompanyproducttype : companyproducttypeCollectionNew) {
                if (!companyproducttypeCollectionOld.contains(companyproducttypeCollectionNewCompanyproducttype)) {
                    Company oldCompanyOfCompanyproducttypeCollectionNewCompanyproducttype = companyproducttypeCollectionNewCompanyproducttype.getCompany();
                    companyproducttypeCollectionNewCompanyproducttype.setCompany(company);
                    companyproducttypeCollectionNewCompanyproducttype = em.merge(companyproducttypeCollectionNewCompanyproducttype);
                    if (oldCompanyOfCompanyproducttypeCollectionNewCompanyproducttype != null && !oldCompanyOfCompanyproducttypeCollectionNewCompanyproducttype.equals(company)) {
                        oldCompanyOfCompanyproducttypeCollectionNewCompanyproducttype.getCompanyproducttypeCollection().remove(companyproducttypeCollectionNewCompanyproducttype);
                        oldCompanyOfCompanyproducttypeCollectionNewCompanyproducttype = em.merge(oldCompanyOfCompanyproducttypeCollectionNewCompanyproducttype);
                    }
                }
            }
            for (Crmcampaign crmcampaignCollectionNewCrmcampaign : crmcampaignCollectionNew) {
                if (!crmcampaignCollectionOld.contains(crmcampaignCollectionNewCrmcampaign)) {
                    Company oldCompanyOfCrmcampaignCollectionNewCrmcampaign = crmcampaignCollectionNewCrmcampaign.getCompany();
                    crmcampaignCollectionNewCrmcampaign.setCompany(company);
                    crmcampaignCollectionNewCrmcampaign = em.merge(crmcampaignCollectionNewCrmcampaign);
                    if (oldCompanyOfCrmcampaignCollectionNewCrmcampaign != null && !oldCompanyOfCrmcampaignCollectionNewCrmcampaign.equals(company)) {
                        oldCompanyOfCrmcampaignCollectionNewCrmcampaign.getCrmcampaignCollection().remove(crmcampaignCollectionNewCrmcampaign);
                        oldCompanyOfCrmcampaignCollectionNewCrmcampaign = em.merge(oldCompanyOfCrmcampaignCollectionNewCrmcampaign);
                    }
                }
            }
            for (Companyhirarchie companyhirarchieCollectionNewCompanyhirarchie : companyhirarchieCollectionNew) {
                if (!companyhirarchieCollectionOld.contains(companyhirarchieCollectionNewCompanyhirarchie)) {
                    Company oldCompanyOfCompanyhirarchieCollectionNewCompanyhirarchie = companyhirarchieCollectionNewCompanyhirarchie.getCompany();
                    companyhirarchieCollectionNewCompanyhirarchie.setCompany(company);
                    companyhirarchieCollectionNewCompanyhirarchie = em.merge(companyhirarchieCollectionNewCompanyhirarchie);
                    if (oldCompanyOfCompanyhirarchieCollectionNewCompanyhirarchie != null && !oldCompanyOfCompanyhirarchieCollectionNewCompanyhirarchie.equals(company)) {
                        oldCompanyOfCompanyhirarchieCollectionNewCompanyhirarchie.getCompanyhirarchieCollection().remove(companyhirarchieCollectionNewCompanyhirarchie);
                        oldCompanyOfCompanyhirarchieCollectionNewCompanyhirarchie = em.merge(oldCompanyOfCompanyhirarchieCollectionNewCompanyhirarchie);
                    }
                }
            }
            for (Companyhirarchie companyhirarchieCollection1NewCompanyhirarchie : companyhirarchieCollection1New) {
                if (!companyhirarchieCollection1Old.contains(companyhirarchieCollection1NewCompanyhirarchie)) {
                    Company oldCompany1OfCompanyhirarchieCollection1NewCompanyhirarchie = companyhirarchieCollection1NewCompanyhirarchie.getCompany1();
                    companyhirarchieCollection1NewCompanyhirarchie.setCompany1(company);
                    companyhirarchieCollection1NewCompanyhirarchie = em.merge(companyhirarchieCollection1NewCompanyhirarchie);
                    if (oldCompany1OfCompanyhirarchieCollection1NewCompanyhirarchie != null && !oldCompany1OfCompanyhirarchieCollection1NewCompanyhirarchie.equals(company)) {
                        oldCompany1OfCompanyhirarchieCollection1NewCompanyhirarchie.getCompanyhirarchieCollection1().remove(companyhirarchieCollection1NewCompanyhirarchie);
                        oldCompany1OfCompanyhirarchieCollection1NewCompanyhirarchie = em.merge(oldCompany1OfCompanyhirarchieCollection1NewCompanyhirarchie);
                    }
                }
            }
            for (Companyhirarchie companyhirarchieCollection2NewCompanyhirarchie : companyhirarchieCollection2New) {
                if (!companyhirarchieCollection2Old.contains(companyhirarchieCollection2NewCompanyhirarchie)) {
                    Company oldCompany2OfCompanyhirarchieCollection2NewCompanyhirarchie = companyhirarchieCollection2NewCompanyhirarchie.getCompany2();
                    companyhirarchieCollection2NewCompanyhirarchie.setCompany2(company);
                    companyhirarchieCollection2NewCompanyhirarchie = em.merge(companyhirarchieCollection2NewCompanyhirarchie);
                    if (oldCompany2OfCompanyhirarchieCollection2NewCompanyhirarchie != null && !oldCompany2OfCompanyhirarchieCollection2NewCompanyhirarchie.equals(company)) {
                        oldCompany2OfCompanyhirarchieCollection2NewCompanyhirarchie.getCompanyhirarchieCollection2().remove(companyhirarchieCollection2NewCompanyhirarchie);
                        oldCompany2OfCompanyhirarchieCollection2NewCompanyhirarchie = em.merge(oldCompany2OfCompanyhirarchieCollection2NewCompanyhirarchie);
                    }
                }
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollectionNewCompanyaccountstackdocs : companyaccountstackdocsCollectionNew) {
                if (!companyaccountstackdocsCollectionOld.contains(companyaccountstackdocsCollectionNewCompanyaccountstackdocs)) {
                    Company oldCompanyOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs = companyaccountstackdocsCollectionNewCompanyaccountstackdocs.getCompany();
                    companyaccountstackdocsCollectionNewCompanyaccountstackdocs.setCompany(company);
                    companyaccountstackdocsCollectionNewCompanyaccountstackdocs = em.merge(companyaccountstackdocsCollectionNewCompanyaccountstackdocs);
                    if (oldCompanyOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs != null && !oldCompanyOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs.equals(company)) {
                        oldCompanyOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocsCollectionNewCompanyaccountstackdocs);
                        oldCompanyOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs = em.merge(oldCompanyOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs);
                    }
                }
            }
            for (Crmlabor crmlaborCollectionNewCrmlabor : crmlaborCollectionNew) {
                if (!crmlaborCollectionOld.contains(crmlaborCollectionNewCrmlabor)) {
                    Company oldCompanyOfCrmlaborCollectionNewCrmlabor = crmlaborCollectionNewCrmlabor.getCompany();
                    crmlaborCollectionNewCrmlabor.setCompany(company);
                    crmlaborCollectionNewCrmlabor = em.merge(crmlaborCollectionNewCrmlabor);
                    if (oldCompanyOfCrmlaborCollectionNewCrmlabor != null && !oldCompanyOfCrmlaborCollectionNewCrmlabor.equals(company)) {
                        oldCompanyOfCrmlaborCollectionNewCrmlabor.getCrmlaborCollection().remove(crmlaborCollectionNewCrmlabor);
                        oldCompanyOfCrmlaborCollectionNewCrmlabor = em.merge(oldCompanyOfCrmlaborCollectionNewCrmlabor);
                    }
                }
            }
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency : compnaypaymentcurrencyCollectionNew) {
                if (!compnaypaymentcurrencyCollectionOld.contains(compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency)) {
                    Company oldCompanyOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency = compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency.getCompany();
                    compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency.setCompany(company);
                    compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency = em.merge(compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency);
                    if (oldCompanyOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency != null && !oldCompanyOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency.equals(company)) {
                        oldCompanyOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency.getCompnaypaymentcurrencyCollection().remove(compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency);
                        oldCompanyOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency = em.merge(oldCompanyOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency);
                    }
                }
            }
            for (Companyproduct companyproductCollectionNewCompanyproduct : companyproductCollectionNew) {
                if (!companyproductCollectionOld.contains(companyproductCollectionNewCompanyproduct)) {
                    Company oldCompanyOfCompanyproductCollectionNewCompanyproduct = companyproductCollectionNewCompanyproduct.getCompany();
                    companyproductCollectionNewCompanyproduct.setCompany(company);
                    companyproductCollectionNewCompanyproduct = em.merge(companyproductCollectionNewCompanyproduct);
                    if (oldCompanyOfCompanyproductCollectionNewCompanyproduct != null && !oldCompanyOfCompanyproductCollectionNewCompanyproduct.equals(company)) {
                        oldCompanyOfCompanyproductCollectionNewCompanyproduct.getCompanyproductCollection().remove(companyproductCollectionNewCompanyproduct);
                        oldCompanyOfCompanyproductCollectionNewCompanyproduct = em.merge(oldCompanyOfCompanyproductCollectionNewCompanyproduct);
                    }
                }
            }
            for (Employeedesignation employeedesignationCollectionNewEmployeedesignation : employeedesignationCollectionNew) {
                if (!employeedesignationCollectionOld.contains(employeedesignationCollectionNewEmployeedesignation)) {
                    Company oldCompanyOfEmployeedesignationCollectionNewEmployeedesignation = employeedesignationCollectionNewEmployeedesignation.getCompany();
                    employeedesignationCollectionNewEmployeedesignation.setCompany(company);
                    employeedesignationCollectionNewEmployeedesignation = em.merge(employeedesignationCollectionNewEmployeedesignation);
                    if (oldCompanyOfEmployeedesignationCollectionNewEmployeedesignation != null && !oldCompanyOfEmployeedesignationCollectionNewEmployeedesignation.equals(company)) {
                        oldCompanyOfEmployeedesignationCollectionNewEmployeedesignation.getEmployeedesignationCollection().remove(employeedesignationCollectionNewEmployeedesignation);
                        oldCompanyOfEmployeedesignationCollectionNewEmployeedesignation = em.merge(oldCompanyOfEmployeedesignationCollectionNewEmployeedesignation);
                    }
                }
            }
            for (Companyaccountstack companyaccountstackCollectionNewCompanyaccountstack : companyaccountstackCollectionNew) {
                if (!companyaccountstackCollectionOld.contains(companyaccountstackCollectionNewCompanyaccountstack)) {
                    Company oldCompanyOfCompanyaccountstackCollectionNewCompanyaccountstack = companyaccountstackCollectionNewCompanyaccountstack.getCompany();
                    companyaccountstackCollectionNewCompanyaccountstack.setCompany(company);
                    companyaccountstackCollectionNewCompanyaccountstack = em.merge(companyaccountstackCollectionNewCompanyaccountstack);
                    if (oldCompanyOfCompanyaccountstackCollectionNewCompanyaccountstack != null && !oldCompanyOfCompanyaccountstackCollectionNewCompanyaccountstack.equals(company)) {
                        oldCompanyOfCompanyaccountstackCollectionNewCompanyaccountstack.getCompanyaccountstackCollection().remove(companyaccountstackCollectionNewCompanyaccountstack);
                        oldCompanyOfCompanyaccountstackCollectionNewCompanyaccountstack = em.merge(oldCompanyOfCompanyaccountstackCollectionNewCompanyaccountstack);
                    }
                }
            }
            for (Componentattachments componentattachmentsCollectionNewComponentattachments : componentattachmentsCollectionNew) {
                if (!componentattachmentsCollectionOld.contains(componentattachmentsCollectionNewComponentattachments)) {
                    Company oldCompanyOfComponentattachmentsCollectionNewComponentattachments = componentattachmentsCollectionNewComponentattachments.getCompany();
                    componentattachmentsCollectionNewComponentattachments.setCompany(company);
                    componentattachmentsCollectionNewComponentattachments = em.merge(componentattachmentsCollectionNewComponentattachments);
                    if (oldCompanyOfComponentattachmentsCollectionNewComponentattachments != null && !oldCompanyOfComponentattachmentsCollectionNewComponentattachments.equals(company)) {
                        oldCompanyOfComponentattachmentsCollectionNewComponentattachments.getComponentattachmentsCollection().remove(componentattachmentsCollectionNewComponentattachments);
                        oldCompanyOfComponentattachmentsCollectionNewComponentattachments = em.merge(oldCompanyOfComponentattachmentsCollectionNewComponentattachments);
                    }
                }
            }
            for (Companyaccountstackcd companyaccountstackcdCollectionNewCompanyaccountstackcd : companyaccountstackcdCollectionNew) {
                if (!companyaccountstackcdCollectionOld.contains(companyaccountstackcdCollectionNewCompanyaccountstackcd)) {
                    Company oldCompanyOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd = companyaccountstackcdCollectionNewCompanyaccountstackcd.getCompany();
                    companyaccountstackcdCollectionNewCompanyaccountstackcd.setCompany(company);
                    companyaccountstackcdCollectionNewCompanyaccountstackcd = em.merge(companyaccountstackcdCollectionNewCompanyaccountstackcd);
                    if (oldCompanyOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd != null && !oldCompanyOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd.equals(company)) {
                        oldCompanyOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd.getCompanyaccountstackcdCollection().remove(companyaccountstackcdCollectionNewCompanyaccountstackcd);
                        oldCompanyOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd = em.merge(oldCompanyOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd);
                    }
                }
            }
            for (Crmworkordertype crmworkordertypeCollectionNewCrmworkordertype : crmworkordertypeCollectionNew) {
                if (!crmworkordertypeCollectionOld.contains(crmworkordertypeCollectionNewCrmworkordertype)) {
                    Company oldCompanyOfCrmworkordertypeCollectionNewCrmworkordertype = crmworkordertypeCollectionNewCrmworkordertype.getCompany();
                    crmworkordertypeCollectionNewCrmworkordertype.setCompany(company);
                    crmworkordertypeCollectionNewCrmworkordertype = em.merge(crmworkordertypeCollectionNewCrmworkordertype);
                    if (oldCompanyOfCrmworkordertypeCollectionNewCrmworkordertype != null && !oldCompanyOfCrmworkordertypeCollectionNewCrmworkordertype.equals(company)) {
                        oldCompanyOfCrmworkordertypeCollectionNewCrmworkordertype.getCrmworkordertypeCollection().remove(crmworkordertypeCollectionNewCrmworkordertype);
                        oldCompanyOfCrmworkordertypeCollectionNewCrmworkordertype = em.merge(oldCompanyOfCrmworkordertypeCollectionNewCrmworkordertype);
                    }
                }
            }
            for (Crmlabortype crmlabortypeCollectionNewCrmlabortype : crmlabortypeCollectionNew) {
                if (!crmlabortypeCollectionOld.contains(crmlabortypeCollectionNewCrmlabortype)) {
                    Company oldCompanyOfCrmlabortypeCollectionNewCrmlabortype = crmlabortypeCollectionNewCrmlabortype.getCompany();
                    crmlabortypeCollectionNewCrmlabortype.setCompany(company);
                    crmlabortypeCollectionNewCrmlabortype = em.merge(crmlabortypeCollectionNewCrmlabortype);
                    if (oldCompanyOfCrmlabortypeCollectionNewCrmlabortype != null && !oldCompanyOfCrmlabortypeCollectionNewCrmlabortype.equals(company)) {
                        oldCompanyOfCrmlabortypeCollectionNewCrmlabortype.getCrmlabortypeCollection().remove(crmlabortypeCollectionNewCrmlabortype);
                        oldCompanyOfCrmlabortypeCollectionNewCrmlabortype = em.merge(oldCompanyOfCrmlabortypeCollectionNewCrmlabortype);
                    }
                }
            }
            for (Crmbillingtype crmbillingtypeCollectionNewCrmbillingtype : crmbillingtypeCollectionNew) {
                if (!crmbillingtypeCollectionOld.contains(crmbillingtypeCollectionNewCrmbillingtype)) {
                    Company oldCompanyOfCrmbillingtypeCollectionNewCrmbillingtype = crmbillingtypeCollectionNewCrmbillingtype.getCompany();
                    crmbillingtypeCollectionNewCrmbillingtype.setCompany(company);
                    crmbillingtypeCollectionNewCrmbillingtype = em.merge(crmbillingtypeCollectionNewCrmbillingtype);
                    if (oldCompanyOfCrmbillingtypeCollectionNewCrmbillingtype != null && !oldCompanyOfCrmbillingtypeCollectionNewCrmbillingtype.equals(company)) {
                        oldCompanyOfCrmbillingtypeCollectionNewCrmbillingtype.getCrmbillingtypeCollection().remove(crmbillingtypeCollectionNewCrmbillingtype);
                        oldCompanyOfCrmbillingtypeCollectionNewCrmbillingtype = em.merge(oldCompanyOfCrmbillingtypeCollectionNewCrmbillingtype);
                    }
                }
            }
            for (Companyproductcategory companyproductcategoryCollectionNewCompanyproductcategory : companyproductcategoryCollectionNew) {
                if (!companyproductcategoryCollectionOld.contains(companyproductcategoryCollectionNewCompanyproductcategory)) {
                    Company oldCompanyOfCompanyproductcategoryCollectionNewCompanyproductcategory = companyproductcategoryCollectionNewCompanyproductcategory.getCompany();
                    companyproductcategoryCollectionNewCompanyproductcategory.setCompany(company);
                    companyproductcategoryCollectionNewCompanyproductcategory = em.merge(companyproductcategoryCollectionNewCompanyproductcategory);
                    if (oldCompanyOfCompanyproductcategoryCollectionNewCompanyproductcategory != null && !oldCompanyOfCompanyproductcategoryCollectionNewCompanyproductcategory.equals(company)) {
                        oldCompanyOfCompanyproductcategoryCollectionNewCompanyproductcategory.getCompanyproductcategoryCollection().remove(companyproductcategoryCollectionNewCompanyproductcategory);
                        oldCompanyOfCompanyproductcategoryCollectionNewCompanyproductcategory = em.merge(oldCompanyOfCompanyproductcategoryCollectionNewCompanyproductcategory);
                    }
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollectionNewCrmcampaignprops : crmcampaignpropsCollectionNew) {
                if (!crmcampaignpropsCollectionOld.contains(crmcampaignpropsCollectionNewCrmcampaignprops)) {
                    Company oldCompanyOfCrmcampaignpropsCollectionNewCrmcampaignprops = crmcampaignpropsCollectionNewCrmcampaignprops.getCompany();
                    crmcampaignpropsCollectionNewCrmcampaignprops.setCompany(company);
                    crmcampaignpropsCollectionNewCrmcampaignprops = em.merge(crmcampaignpropsCollectionNewCrmcampaignprops);
                    if (oldCompanyOfCrmcampaignpropsCollectionNewCrmcampaignprops != null && !oldCompanyOfCrmcampaignpropsCollectionNewCrmcampaignprops.equals(company)) {
                        oldCompanyOfCrmcampaignpropsCollectionNewCrmcampaignprops.getCrmcampaignpropsCollection().remove(crmcampaignpropsCollectionNewCrmcampaignprops);
                        oldCompanyOfCrmcampaignpropsCollectionNewCrmcampaignprops = em.merge(oldCompanyOfCrmcampaignpropsCollectionNewCrmcampaignprops);
                    }
                }
            }
            for (Customercategory customercategoryCollectionNewCustomercategory : customercategoryCollectionNew) {
                if (!customercategoryCollectionOld.contains(customercategoryCollectionNewCustomercategory)) {
                    Company oldCompanyOfCustomercategoryCollectionNewCustomercategory = customercategoryCollectionNewCustomercategory.getCompany();
                    customercategoryCollectionNewCustomercategory.setCompany(company);
                    customercategoryCollectionNewCustomercategory = em.merge(customercategoryCollectionNewCustomercategory);
                    if (oldCompanyOfCustomercategoryCollectionNewCustomercategory != null && !oldCompanyOfCustomercategoryCollectionNewCustomercategory.equals(company)) {
                        oldCompanyOfCustomercategoryCollectionNewCustomercategory.getCustomercategoryCollection().remove(customercategoryCollectionNewCustomercategory);
                        oldCompanyOfCustomercategoryCollectionNewCustomercategory = em.merge(oldCompanyOfCustomercategoryCollectionNewCustomercategory);
                    }
                }
            }
            for (Productcomponents productcomponentsCollectionNewProductcomponents : productcomponentsCollectionNew) {
                if (!productcomponentsCollectionOld.contains(productcomponentsCollectionNewProductcomponents)) {
                    Company oldCompanyOfProductcomponentsCollectionNewProductcomponents = productcomponentsCollectionNewProductcomponents.getCompany();
                    productcomponentsCollectionNewProductcomponents.setCompany(company);
                    productcomponentsCollectionNewProductcomponents = em.merge(productcomponentsCollectionNewProductcomponents);
                    if (oldCompanyOfProductcomponentsCollectionNewProductcomponents != null && !oldCompanyOfProductcomponentsCollectionNewProductcomponents.equals(company)) {
                        oldCompanyOfProductcomponentsCollectionNewProductcomponents.getProductcomponentsCollection().remove(productcomponentsCollectionNewProductcomponents);
                        oldCompanyOfProductcomponentsCollectionNewProductcomponents = em.merge(oldCompanyOfProductcomponentsCollectionNewProductcomponents);
                    }
                }
            }
            for (Crmexpensetype crmexpensetypeCollectionNewCrmexpensetype : crmexpensetypeCollectionNew) {
                if (!crmexpensetypeCollectionOld.contains(crmexpensetypeCollectionNewCrmexpensetype)) {
                    Company oldCompanyOfCrmexpensetypeCollectionNewCrmexpensetype = crmexpensetypeCollectionNewCrmexpensetype.getCompany();
                    crmexpensetypeCollectionNewCrmexpensetype.setCompany(company);
                    crmexpensetypeCollectionNewCrmexpensetype = em.merge(crmexpensetypeCollectionNewCrmexpensetype);
                    if (oldCompanyOfCrmexpensetypeCollectionNewCrmexpensetype != null && !oldCompanyOfCrmexpensetypeCollectionNewCrmexpensetype.equals(company)) {
                        oldCompanyOfCrmexpensetypeCollectionNewCrmexpensetype.getCrmexpensetypeCollection().remove(crmexpensetypeCollectionNewCrmexpensetype);
                        oldCompanyOfCrmexpensetypeCollectionNewCrmexpensetype = em.merge(oldCompanyOfCrmexpensetypeCollectionNewCrmexpensetype);
                    }
                }
            }
            for (Companypayments companypaymentsCollectionNewCompanypayments : companypaymentsCollectionNew) {
                if (!companypaymentsCollectionOld.contains(companypaymentsCollectionNewCompanypayments)) {
                    Company oldCompanyOfCompanypaymentsCollectionNewCompanypayments = companypaymentsCollectionNewCompanypayments.getCompany();
                    companypaymentsCollectionNewCompanypayments.setCompany(company);
                    companypaymentsCollectionNewCompanypayments = em.merge(companypaymentsCollectionNewCompanypayments);
                    if (oldCompanyOfCompanypaymentsCollectionNewCompanypayments != null && !oldCompanyOfCompanypaymentsCollectionNewCompanypayments.equals(company)) {
                        oldCompanyOfCompanypaymentsCollectionNewCompanypayments.getCompanypaymentsCollection().remove(companypaymentsCollectionNewCompanypayments);
                        oldCompanyOfCompanypaymentsCollectionNewCompanypayments = em.merge(oldCompanyOfCompanypaymentsCollectionNewCompanypayments);
                    }
                }
            }
            for (Crmworkorderresolution crmworkorderresolutionCollectionNewCrmworkorderresolution : crmworkorderresolutionCollectionNew) {
                if (!crmworkorderresolutionCollectionOld.contains(crmworkorderresolutionCollectionNewCrmworkorderresolution)) {
                    Company oldCompanyOfCrmworkorderresolutionCollectionNewCrmworkorderresolution = crmworkorderresolutionCollectionNewCrmworkorderresolution.getCompany();
                    crmworkorderresolutionCollectionNewCrmworkorderresolution.setCompany(company);
                    crmworkorderresolutionCollectionNewCrmworkorderresolution = em.merge(crmworkorderresolutionCollectionNewCrmworkorderresolution);
                    if (oldCompanyOfCrmworkorderresolutionCollectionNewCrmworkorderresolution != null && !oldCompanyOfCrmworkorderresolutionCollectionNewCrmworkorderresolution.equals(company)) {
                        oldCompanyOfCrmworkorderresolutionCollectionNewCrmworkorderresolution.getCrmworkorderresolutionCollection().remove(crmworkorderresolutionCollectionNewCrmworkorderresolution);
                        oldCompanyOfCrmworkorderresolutionCollectionNewCrmworkorderresolution = em.merge(oldCompanyOfCrmworkorderresolutionCollectionNewCrmworkorderresolution);
                    }
                }
            }
            for (Companyaddresstype companyaddresstypeCollectionNewCompanyaddresstype : companyaddresstypeCollectionNew) {
                if (!companyaddresstypeCollectionOld.contains(companyaddresstypeCollectionNewCompanyaddresstype)) {
                    Company oldCompanyOfCompanyaddresstypeCollectionNewCompanyaddresstype = companyaddresstypeCollectionNewCompanyaddresstype.getCompany();
                    companyaddresstypeCollectionNewCompanyaddresstype.setCompany(company);
                    companyaddresstypeCollectionNewCompanyaddresstype = em.merge(companyaddresstypeCollectionNewCompanyaddresstype);
                    if (oldCompanyOfCompanyaddresstypeCollectionNewCompanyaddresstype != null && !oldCompanyOfCompanyaddresstypeCollectionNewCompanyaddresstype.equals(company)) {
                        oldCompanyOfCompanyaddresstypeCollectionNewCompanyaddresstype.getCompanyaddresstypeCollection().remove(companyaddresstypeCollectionNewCompanyaddresstype);
                        oldCompanyOfCompanyaddresstypeCollectionNewCompanyaddresstype = em.merge(oldCompanyOfCompanyaddresstypeCollectionNewCompanyaddresstype);
                    }
                }
            }
            for (Companycustomer companycustomerCollectionNewCompanycustomer : companycustomerCollectionNew) {
                if (!companycustomerCollectionOld.contains(companycustomerCollectionNewCompanycustomer)) {
                    Company oldCompanyOfCompanycustomerCollectionNewCompanycustomer = companycustomerCollectionNewCompanycustomer.getCompany();
                    companycustomerCollectionNewCompanycustomer.setCompany(company);
                    companycustomerCollectionNewCompanycustomer = em.merge(companycustomerCollectionNewCompanycustomer);
                    if (oldCompanyOfCompanycustomerCollectionNewCompanycustomer != null && !oldCompanyOfCompanycustomerCollectionNewCompanycustomer.equals(company)) {
                        oldCompanyOfCompanycustomerCollectionNewCompanycustomer.getCompanycustomerCollection().remove(companycustomerCollectionNewCompanycustomer);
                        oldCompanyOfCompanycustomerCollectionNewCompanycustomer = em.merge(oldCompanyOfCompanycustomerCollectionNewCompanycustomer);
                    }
                }
            }
            for (Crmprojectstatus crmprojectstatusCollectionNewCrmprojectstatus : crmprojectstatusCollectionNew) {
                if (!crmprojectstatusCollectionOld.contains(crmprojectstatusCollectionNewCrmprojectstatus)) {
                    Company oldCompanyOfCrmprojectstatusCollectionNewCrmprojectstatus = crmprojectstatusCollectionNewCrmprojectstatus.getCompany();
                    crmprojectstatusCollectionNewCrmprojectstatus.setCompany(company);
                    crmprojectstatusCollectionNewCrmprojectstatus = em.merge(crmprojectstatusCollectionNewCrmprojectstatus);
                    if (oldCompanyOfCrmprojectstatusCollectionNewCrmprojectstatus != null && !oldCompanyOfCrmprojectstatusCollectionNewCrmprojectstatus.equals(company)) {
                        oldCompanyOfCrmprojectstatusCollectionNewCrmprojectstatus.getCrmprojectstatusCollection().remove(crmprojectstatusCollectionNewCrmprojectstatus);
                        oldCompanyOfCrmprojectstatusCollectionNewCrmprojectstatus = em.merge(oldCompanyOfCrmprojectstatusCollectionNewCrmprojectstatus);
                    }
                }
            }
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionNewCrmschedulerevnttype : crmschedulerevnttypeCollectionNew) {
                if (!crmschedulerevnttypeCollectionOld.contains(crmschedulerevnttypeCollectionNewCrmschedulerevnttype)) {
                    Company oldCompanyOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype = crmschedulerevnttypeCollectionNewCrmschedulerevnttype.getCompany();
                    crmschedulerevnttypeCollectionNewCrmschedulerevnttype.setCompany(company);
                    crmschedulerevnttypeCollectionNewCrmschedulerevnttype = em.merge(crmschedulerevnttypeCollectionNewCrmschedulerevnttype);
                    if (oldCompanyOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype != null && !oldCompanyOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype.equals(company)) {
                        oldCompanyOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype.getCrmschedulerevnttypeCollection().remove(crmschedulerevnttypeCollectionNewCrmschedulerevnttype);
                        oldCompanyOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype = em.merge(oldCompanyOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype);
                    }
                }
            }
            for (Componenttype componenttypeCollectionNewComponenttype : componenttypeCollectionNew) {
                if (!componenttypeCollectionOld.contains(componenttypeCollectionNewComponenttype)) {
                    Company oldCompanyOfComponenttypeCollectionNewComponenttype = componenttypeCollectionNewComponenttype.getCompany();
                    componenttypeCollectionNewComponenttype.setCompany(company);
                    componenttypeCollectionNewComponenttype = em.merge(componenttypeCollectionNewComponenttype);
                    if (oldCompanyOfComponenttypeCollectionNewComponenttype != null && !oldCompanyOfComponenttypeCollectionNewComponenttype.equals(company)) {
                        oldCompanyOfComponenttypeCollectionNewComponenttype.getComponenttypeCollection().remove(componenttypeCollectionNewComponenttype);
                        oldCompanyOfComponenttypeCollectionNewComponenttype = em.merge(oldCompanyOfComponenttypeCollectionNewComponenttype);
                    }
                }
            }
            for (Companypaymentscheme companypaymentschemeCollectionNewCompanypaymentscheme : companypaymentschemeCollectionNew) {
                if (!companypaymentschemeCollectionOld.contains(companypaymentschemeCollectionNewCompanypaymentscheme)) {
                    Company oldCompanyOfCompanypaymentschemeCollectionNewCompanypaymentscheme = companypaymentschemeCollectionNewCompanypaymentscheme.getCompany();
                    companypaymentschemeCollectionNewCompanypaymentscheme.setCompany(company);
                    companypaymentschemeCollectionNewCompanypaymentscheme = em.merge(companypaymentschemeCollectionNewCompanypaymentscheme);
                    if (oldCompanyOfCompanypaymentschemeCollectionNewCompanypaymentscheme != null && !oldCompanyOfCompanypaymentschemeCollectionNewCompanypaymentscheme.equals(company)) {
                        oldCompanyOfCompanypaymentschemeCollectionNewCompanypaymentscheme.getCompanypaymentschemeCollection().remove(companypaymentschemeCollectionNewCompanypaymentscheme);
                        oldCompanyOfCompanypaymentschemeCollectionNewCompanypaymentscheme = em.merge(oldCompanyOfCompanypaymentschemeCollectionNewCompanypaymentscheme);
                    }
                }
            }
            for (Companycontactsaddress companycontactsaddressCollectionNewCompanycontactsaddress : companycontactsaddressCollectionNew) {
                if (!companycontactsaddressCollectionOld.contains(companycontactsaddressCollectionNewCompanycontactsaddress)) {
                    Company oldCompanyOfCompanycontactsaddressCollectionNewCompanycontactsaddress = companycontactsaddressCollectionNewCompanycontactsaddress.getCompany();
                    companycontactsaddressCollectionNewCompanycontactsaddress.setCompany(company);
                    companycontactsaddressCollectionNewCompanycontactsaddress = em.merge(companycontactsaddressCollectionNewCompanycontactsaddress);
                    if (oldCompanyOfCompanycontactsaddressCollectionNewCompanycontactsaddress != null && !oldCompanyOfCompanycontactsaddressCollectionNewCompanycontactsaddress.equals(company)) {
                        oldCompanyOfCompanycontactsaddressCollectionNewCompanycontactsaddress.getCompanycontactsaddressCollection().remove(companycontactsaddressCollectionNewCompanycontactsaddress);
                        oldCompanyOfCompanycontactsaddressCollectionNewCompanycontactsaddress = em.merge(oldCompanyOfCompanycontactsaddressCollectionNewCompanycontactsaddress);
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
                CompanyPK id = company.getCompanyPK();
                if (findCompany(id) == null) {
                    throw new NonexistentEntityException("The company with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company;
            try {
                company = em.getReference(Company.class, id);
                company.getCompanyPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The company with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Companyemployee companyemployeeOrphanCheck = company.getCompanyemployee();
            if (companyemployeeOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyemployee " + companyemployeeOrphanCheck + " in its companyemployee field has a non-nullable company field.");
            }
            Companyemployee companyemployee1OrphanCheck = company.getCompanyemployee1();
            if (companyemployee1OrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyemployee " + companyemployee1OrphanCheck + " in its companyemployee1 field has a non-nullable company field.");
            }
            Collection<Crmmessagechannel> crmmessagechannelCollectionOrphanCheck = company.getCrmmessagechannelCollection();
            for (Crmmessagechannel crmmessagechannelCollectionOrphanCheckCrmmessagechannel : crmmessagechannelCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmmessagechannel " + crmmessagechannelCollectionOrphanCheckCrmmessagechannel + " in its crmmessagechannelCollection field has a non-nullable company field.");
            }
            Collection<Companybank> companybankCollectionOrphanCheck = company.getCompanybankCollection();
            for (Companybank companybankCollectionOrphanCheckCompanybank : companybankCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companybank " + companybankCollectionOrphanCheckCompanybank + " in its companybankCollection field has a non-nullable company field.");
            }
            Collection<Crmprojectprops> crmprojectpropsCollectionOrphanCheck = company.getCrmprojectpropsCollection();
            for (Crmprojectprops crmprojectpropsCollectionOrphanCheckCrmprojectprops : crmprojectpropsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmprojectprops " + crmprojectpropsCollectionOrphanCheckCrmprojectprops + " in its crmprojectpropsCollection field has a non-nullable company field.");
            }
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollectionOrphanCheck = company.getCrmcampaignreceiverCollection();
            for (Crmcampaignreceiver crmcampaignreceiverCollectionOrphanCheckCrmcampaignreceiver : crmcampaignreceiverCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmcampaignreceiver " + crmcampaignreceiverCollectionOrphanCheckCrmcampaignreceiver + " in its crmcampaignreceiverCollection field has a non-nullable company field.");
            }
            Collection<Productattachments> productattachmentsCollectionOrphanCheck = company.getProductattachmentsCollection();
            for (Productattachments productattachmentsCollectionOrphanCheckProductattachments : productattachmentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Productattachments " + productattachmentsCollectionOrphanCheckProductattachments + " in its productattachmentsCollection field has a non-nullable company field.");
            }
            Collection<Crmcampaignstatus> crmcampaignstatusCollectionOrphanCheck = company.getCrmcampaignstatusCollection();
            for (Crmcampaignstatus crmcampaignstatusCollectionOrphanCheckCrmcampaignstatus : crmcampaignstatusCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmcampaignstatus " + crmcampaignstatusCollectionOrphanCheckCrmcampaignstatus + " in its crmcampaignstatusCollection field has a non-nullable company field.");
            }
            Collection<Crmcampaignposition> crmcampaignpositionCollectionOrphanCheck = company.getCrmcampaignpositionCollection();
            for (Crmcampaignposition crmcampaignpositionCollectionOrphanCheckCrmcampaignposition : crmcampaignpositionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmcampaignposition " + crmcampaignpositionCollectionOrphanCheckCrmcampaignposition + " in its crmcampaignpositionCollection field has a non-nullable company field.");
            }
            Collection<Companydepartment> companydepartmentCollectionOrphanCheck = company.getCompanydepartmentCollection();
            for (Companydepartment companydepartmentCollectionOrphanCheckCompanydepartment : companydepartmentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companydepartment " + companydepartmentCollectionOrphanCheckCompanydepartment + " in its companydepartmentCollection field has a non-nullable company field.");
            }
            Collection<Crmusermodules> crmusermodulesCollectionOrphanCheck = company.getCrmusermodulesCollection();
            for (Crmusermodules crmusermodulesCollectionOrphanCheckCrmusermodules : crmusermodulesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmusermodules " + crmusermodulesCollectionOrphanCheckCrmusermodules + " in its crmusermodulesCollection field has a non-nullable company field.");
            }
            Collection<Crmexpense> crmexpenseCollectionOrphanCheck = company.getCrmexpenseCollection();
            for (Crmexpense crmexpenseCollectionOrphanCheckCrmexpense : crmexpenseCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmexpense " + crmexpenseCollectionOrphanCheckCrmexpense + " in its crmexpenseCollection field has a non-nullable company field.");
            }
            Collection<Crmworkorder> crmworkorderCollectionOrphanCheck = company.getCrmworkorderCollection();
            for (Crmworkorder crmworkorderCollectionOrphanCheckCrmworkorder : crmworkorderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmworkorder " + crmworkorderCollectionOrphanCheckCrmworkorder + " in its crmworkorderCollection field has a non-nullable company field.");
            }
            Collection<Companycontacts> companycontactsCollectionOrphanCheck = company.getCompanycontactsCollection();
            for (Companycontacts companycontactsCollectionOrphanCheckCompanycontacts : companycontactsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companycontacts " + companycontactsCollectionOrphanCheckCompanycontacts + " in its companycontactsCollection field has a non-nullable company field.");
            }
            Collection<Companyemployee> companyemployeeCollectionOrphanCheck = company.getCompanyemployeeCollection();
            for (Companyemployee companyemployeeCollectionOrphanCheckCompanyemployee : companyemployeeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyemployee " + companyemployeeCollectionOrphanCheckCompanyemployee + " in its companyemployeeCollection field has a non-nullable company field.");
            }
            Collection<Companyproducttype> companyproducttypeCollectionOrphanCheck = company.getCompanyproducttypeCollection();
            for (Companyproducttype companyproducttypeCollectionOrphanCheckCompanyproducttype : companyproducttypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyproducttype " + companyproducttypeCollectionOrphanCheckCompanyproducttype + " in its companyproducttypeCollection field has a non-nullable company field.");
            }
            Collection<Crmcampaign> crmcampaignCollectionOrphanCheck = company.getCrmcampaignCollection();
            for (Crmcampaign crmcampaignCollectionOrphanCheckCrmcampaign : crmcampaignCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmcampaign " + crmcampaignCollectionOrphanCheckCrmcampaign + " in its crmcampaignCollection field has a non-nullable company field.");
            }
            Collection<Companyhirarchie> companyhirarchieCollectionOrphanCheck = company.getCompanyhirarchieCollection();
            for (Companyhirarchie companyhirarchieCollectionOrphanCheckCompanyhirarchie : companyhirarchieCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyhirarchie " + companyhirarchieCollectionOrphanCheckCompanyhirarchie + " in its companyhirarchieCollection field has a non-nullable company field.");
            }
            Collection<Companyhirarchie> companyhirarchieCollection1OrphanCheck = company.getCompanyhirarchieCollection1();
            for (Companyhirarchie companyhirarchieCollection1OrphanCheckCompanyhirarchie : companyhirarchieCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyhirarchie " + companyhirarchieCollection1OrphanCheckCompanyhirarchie + " in its companyhirarchieCollection1 field has a non-nullable company1 field.");
            }
            Collection<Companyhirarchie> companyhirarchieCollection2OrphanCheck = company.getCompanyhirarchieCollection2();
            for (Companyhirarchie companyhirarchieCollection2OrphanCheckCompanyhirarchie : companyhirarchieCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyhirarchie " + companyhirarchieCollection2OrphanCheckCompanyhirarchie + " in its companyhirarchieCollection2 field has a non-nullable company2 field.");
            }
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollectionOrphanCheck = company.getCompanyaccountstackdocsCollection();
            for (Companyaccountstackdocs companyaccountstackdocsCollectionOrphanCheckCompanyaccountstackdocs : companyaccountstackdocsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyaccountstackdocs " + companyaccountstackdocsCollectionOrphanCheckCompanyaccountstackdocs + " in its companyaccountstackdocsCollection field has a non-nullable company field.");
            }
            Collection<Crmlabor> crmlaborCollectionOrphanCheck = company.getCrmlaborCollection();
            for (Crmlabor crmlaborCollectionOrphanCheckCrmlabor : crmlaborCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmlabor " + crmlaborCollectionOrphanCheckCrmlabor + " in its crmlaborCollection field has a non-nullable company field.");
            }
            Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollectionOrphanCheck = company.getCompnaypaymentcurrencyCollection();
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionOrphanCheckCompnaypaymentcurrency : compnaypaymentcurrencyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Compnaypaymentcurrency " + compnaypaymentcurrencyCollectionOrphanCheckCompnaypaymentcurrency + " in its compnaypaymentcurrencyCollection field has a non-nullable company field.");
            }
            Collection<Companyproduct> companyproductCollectionOrphanCheck = company.getCompanyproductCollection();
            for (Companyproduct companyproductCollectionOrphanCheckCompanyproduct : companyproductCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyproduct " + companyproductCollectionOrphanCheckCompanyproduct + " in its companyproductCollection field has a non-nullable company field.");
            }
            Collection<Employeedesignation> employeedesignationCollectionOrphanCheck = company.getEmployeedesignationCollection();
            for (Employeedesignation employeedesignationCollectionOrphanCheckEmployeedesignation : employeedesignationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Employeedesignation " + employeedesignationCollectionOrphanCheckEmployeedesignation + " in its employeedesignationCollection field has a non-nullable company field.");
            }
            Collection<Companyaccountstack> companyaccountstackCollectionOrphanCheck = company.getCompanyaccountstackCollection();
            for (Companyaccountstack companyaccountstackCollectionOrphanCheckCompanyaccountstack : companyaccountstackCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyaccountstack " + companyaccountstackCollectionOrphanCheckCompanyaccountstack + " in its companyaccountstackCollection field has a non-nullable company field.");
            }
            Collection<Componentattachments> componentattachmentsCollectionOrphanCheck = company.getComponentattachmentsCollection();
            for (Componentattachments componentattachmentsCollectionOrphanCheckComponentattachments : componentattachmentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Componentattachments " + componentattachmentsCollectionOrphanCheckComponentattachments + " in its componentattachmentsCollection field has a non-nullable company field.");
            }
            Collection<Companyaccountstackcd> companyaccountstackcdCollectionOrphanCheck = company.getCompanyaccountstackcdCollection();
            for (Companyaccountstackcd companyaccountstackcdCollectionOrphanCheckCompanyaccountstackcd : companyaccountstackcdCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyaccountstackcd " + companyaccountstackcdCollectionOrphanCheckCompanyaccountstackcd + " in its companyaccountstackcdCollection field has a non-nullable company field.");
            }
            Collection<Crmworkordertype> crmworkordertypeCollectionOrphanCheck = company.getCrmworkordertypeCollection();
            for (Crmworkordertype crmworkordertypeCollectionOrphanCheckCrmworkordertype : crmworkordertypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmworkordertype " + crmworkordertypeCollectionOrphanCheckCrmworkordertype + " in its crmworkordertypeCollection field has a non-nullable company field.");
            }
            Collection<Crmlabortype> crmlabortypeCollectionOrphanCheck = company.getCrmlabortypeCollection();
            for (Crmlabortype crmlabortypeCollectionOrphanCheckCrmlabortype : crmlabortypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmlabortype " + crmlabortypeCollectionOrphanCheckCrmlabortype + " in its crmlabortypeCollection field has a non-nullable company field.");
            }
            Collection<Crmbillingtype> crmbillingtypeCollectionOrphanCheck = company.getCrmbillingtypeCollection();
            for (Crmbillingtype crmbillingtypeCollectionOrphanCheckCrmbillingtype : crmbillingtypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmbillingtype " + crmbillingtypeCollectionOrphanCheckCrmbillingtype + " in its crmbillingtypeCollection field has a non-nullable company field.");
            }
            Collection<Companyproductcategory> companyproductcategoryCollectionOrphanCheck = company.getCompanyproductcategoryCollection();
            for (Companyproductcategory companyproductcategoryCollectionOrphanCheckCompanyproductcategory : companyproductcategoryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyproductcategory " + companyproductcategoryCollectionOrphanCheckCompanyproductcategory + " in its companyproductcategoryCollection field has a non-nullable company field.");
            }
            Collection<Crmcampaignprops> crmcampaignpropsCollectionOrphanCheck = company.getCrmcampaignpropsCollection();
            for (Crmcampaignprops crmcampaignpropsCollectionOrphanCheckCrmcampaignprops : crmcampaignpropsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmcampaignprops " + crmcampaignpropsCollectionOrphanCheckCrmcampaignprops + " in its crmcampaignpropsCollection field has a non-nullable company field.");
            }
            Collection<Customercategory> customercategoryCollectionOrphanCheck = company.getCustomercategoryCollection();
            for (Customercategory customercategoryCollectionOrphanCheckCustomercategory : customercategoryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Customercategory " + customercategoryCollectionOrphanCheckCustomercategory + " in its customercategoryCollection field has a non-nullable company field.");
            }
            Collection<Productcomponents> productcomponentsCollectionOrphanCheck = company.getProductcomponentsCollection();
            for (Productcomponents productcomponentsCollectionOrphanCheckProductcomponents : productcomponentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Productcomponents " + productcomponentsCollectionOrphanCheckProductcomponents + " in its productcomponentsCollection field has a non-nullable company field.");
            }
            Collection<Crmexpensetype> crmexpensetypeCollectionOrphanCheck = company.getCrmexpensetypeCollection();
            for (Crmexpensetype crmexpensetypeCollectionOrphanCheckCrmexpensetype : crmexpensetypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmexpensetype " + crmexpensetypeCollectionOrphanCheckCrmexpensetype + " in its crmexpensetypeCollection field has a non-nullable company field.");
            }
            Collection<Companypayments> companypaymentsCollectionOrphanCheck = company.getCompanypaymentsCollection();
            for (Companypayments companypaymentsCollectionOrphanCheckCompanypayments : companypaymentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companypayments " + companypaymentsCollectionOrphanCheckCompanypayments + " in its companypaymentsCollection field has a non-nullable company field.");
            }
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionOrphanCheck = company.getCrmworkorderresolutionCollection();
            for (Crmworkorderresolution crmworkorderresolutionCollectionOrphanCheckCrmworkorderresolution : crmworkorderresolutionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmworkorderresolution " + crmworkorderresolutionCollectionOrphanCheckCrmworkorderresolution + " in its crmworkorderresolutionCollection field has a non-nullable company field.");
            }
            Collection<Companyaddresstype> companyaddresstypeCollectionOrphanCheck = company.getCompanyaddresstypeCollection();
            for (Companyaddresstype companyaddresstypeCollectionOrphanCheckCompanyaddresstype : companyaddresstypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companyaddresstype " + companyaddresstypeCollectionOrphanCheckCompanyaddresstype + " in its companyaddresstypeCollection field has a non-nullable company field.");
            }
            Collection<Companycustomer> companycustomerCollectionOrphanCheck = company.getCompanycustomerCollection();
            for (Companycustomer companycustomerCollectionOrphanCheckCompanycustomer : companycustomerCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companycustomer " + companycustomerCollectionOrphanCheckCompanycustomer + " in its companycustomerCollection field has a non-nullable company field.");
            }
            Collection<Crmprojectstatus> crmprojectstatusCollectionOrphanCheck = company.getCrmprojectstatusCollection();
            for (Crmprojectstatus crmprojectstatusCollectionOrphanCheckCrmprojectstatus : crmprojectstatusCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmprojectstatus " + crmprojectstatusCollectionOrphanCheckCrmprojectstatus + " in its crmprojectstatusCollection field has a non-nullable company field.");
            }
            Collection<Crmschedulerevnttype> crmschedulerevnttypeCollectionOrphanCheck = company.getCrmschedulerevnttypeCollection();
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionOrphanCheckCrmschedulerevnttype : crmschedulerevnttypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Crmschedulerevnttype " + crmschedulerevnttypeCollectionOrphanCheckCrmschedulerevnttype + " in its crmschedulerevnttypeCollection field has a non-nullable company field.");
            }
            Collection<Componenttype> componenttypeCollectionOrphanCheck = company.getComponenttypeCollection();
            for (Componenttype componenttypeCollectionOrphanCheckComponenttype : componenttypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Componenttype " + componenttypeCollectionOrphanCheckComponenttype + " in its componenttypeCollection field has a non-nullable company field.");
            }
            Collection<Companypaymentscheme> companypaymentschemeCollectionOrphanCheck = company.getCompanypaymentschemeCollection();
            for (Companypaymentscheme companypaymentschemeCollectionOrphanCheckCompanypaymentscheme : companypaymentschemeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companypaymentscheme " + companypaymentschemeCollectionOrphanCheckCompanypaymentscheme + " in its companypaymentschemeCollection field has a non-nullable company field.");
            }
            Collection<Companycontactsaddress> companycontactsaddressCollectionOrphanCheck = company.getCompanycontactsaddressCollection();
            for (Companycontactsaddress companycontactsaddressCollectionOrphanCheckCompanycontactsaddress : companycontactsaddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Company (" + company + ") cannot be destroyed since the Companycontactsaddress " + companycontactsaddressCollectionOrphanCheckCompanycontactsaddress + " in its companycontactsaddressCollection field has a non-nullable company field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Category category = company.getCategory();
            if (category != null) {
                category.getCompanyCollection().remove(company);
                category = em.merge(category);
            }
            em.remove(company);
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

    public List<Company> findCompanyEntities() {
        return findCompanyEntities(true, -1, -1);
    }

    public List<Company> findCompanyEntities(int maxResults, int firstResult) {
        return findCompanyEntities(false, maxResults, firstResult);
    }

    private List<Company> findCompanyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Company.class));
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

    public Company findCompany(CompanyPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Company.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Company> rt = cq.from(Company.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
