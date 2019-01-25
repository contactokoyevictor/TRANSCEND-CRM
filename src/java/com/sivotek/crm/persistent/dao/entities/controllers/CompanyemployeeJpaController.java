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
import com.sivotek.crm.persistent.dao.entities.Companydepartment;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmmessagechannel;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Companybank;
import com.sivotek.crm.persistent.dao.entities.Crmmessagechanneltemplate;
import com.sivotek.crm.persistent.dao.entities.Crmprojectprops;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignreceiver;
import com.sivotek.crm.persistent.dao.entities.Crmworkordersettings;
import com.sivotek.crm.persistent.dao.entities.Productattachments;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignstatus;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignposition;
import com.sivotek.crm.persistent.dao.entities.Crmforumteammembers;
import com.sivotek.crm.persistent.dao.entities.Customercontacts;
import com.sivotek.crm.persistent.dao.entities.Appointment;
import com.sivotek.crm.persistent.dao.entities.Crmusermodules;
import com.sivotek.crm.persistent.dao.entities.Crmexpense;
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.Crmcampaigndocs;
import com.sivotek.crm.persistent.dao.entities.Crmemployeenote;
import com.sivotek.crm.persistent.dao.entities.Companycontacts;
import com.sivotek.crm.persistent.dao.entities.Companyproducttype;
import com.sivotek.crm.persistent.dao.entities.Crmcampaign;
import com.sivotek.crm.persistent.dao.entities.Crmmessagehistory;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackdocs;
import com.sivotek.crm.persistent.dao.entities.Crmlabor;
import com.sivotek.crm.persistent.dao.entities.Customerbank;
import com.sivotek.crm.persistent.dao.entities.Compnaypaymentcurrency;
import com.sivotek.crm.persistent.dao.entities.Crmproject;
import com.sivotek.crm.persistent.dao.entities.Companyemployeeaddress;
import com.sivotek.crm.persistent.dao.entities.Crmforumdocs;
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.Employeedesignation;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstack;
import com.sivotek.crm.persistent.dao.entities.Crmprojectteammembers;
import com.sivotek.crm.persistent.dao.entities.Componentattachments;
import com.sivotek.crm.persistent.dao.entities.Crmvisitorcontactsaddress;
import com.sivotek.crm.persistent.dao.entities.Crmvisitorcontacts;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackcd;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketmanagement;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketnotification;
import com.sivotek.crm.persistent.dao.entities.Crmvisitor;
import com.sivotek.crm.persistent.dao.entities.Crmworkordertype;
import com.sivotek.crm.persistent.dao.entities.Crmlabortype;
import com.sivotek.crm.persistent.dao.entities.Crmbillingtype;
import com.sivotek.crm.persistent.dao.entities.Companyproductcategory;
import com.sivotek.crm.persistent.dao.entities.Crmprojecttask;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignprops;
import com.sivotek.crm.persistent.dao.entities.Customercontactsaddress;
import com.sivotek.crm.persistent.dao.entities.Customercategory;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.Crmexpensetype;
import com.sivotek.crm.persistent.dao.entities.Companypayments;
import com.sivotek.crm.persistent.dao.entities.Crmworkorderresolution;
import com.sivotek.crm.persistent.dao.entities.Companyaddresstype;
import com.sivotek.crm.persistent.dao.entities.Crmemployeecontacts;
import com.sivotek.crm.persistent.dao.entities.Approval;
import com.sivotek.crm.persistent.dao.entities.Workorderinstructions;
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.Crmforum;
import com.sivotek.crm.persistent.dao.entities.Crmprojectstatus;
import com.sivotek.crm.persistent.dao.entities.Crmschedulerevnttype;
import com.sivotek.crm.persistent.dao.entities.Crmschedulerefcode;
import com.sivotek.crm.persistent.dao.entities.Componenttype;
import com.sivotek.crm.persistent.dao.entities.Companypaymentscheme;
import com.sivotek.crm.persistent.dao.entities.Companycontactsaddress;
import com.sivotek.crm.persistent.dao.entities.CompanyemployeePK;
import com.sivotek.crm.persistent.dao.entities.Crmprofilesettings;
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
public class CompanyemployeeJpaController implements Serializable {

    public CompanyemployeeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
     public CompanyemployeeJpaController(){
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

    public void create(Companyemployee companyemployee) throws IllegalOrphanException, PreexistingEntityException, RollbackFailureException, Exception {
        if (companyemployee.getCompanyemployeePK() == null) {
            companyemployee.setCompanyemployeePK(new CompanyemployeePK());
        }
        if (companyemployee.getCrmmessagechannelCollection() == null) {
            companyemployee.setCrmmessagechannelCollection(new ArrayList<Crmmessagechannel>());
        }
        if (companyemployee.getCrmmessagechannelCollection1() == null) {
            companyemployee.setCrmmessagechannelCollection1(new ArrayList<Crmmessagechannel>());
        }
        if (companyemployee.getCrmmessagechannelCollection2() == null) {
            companyemployee.setCrmmessagechannelCollection2(new ArrayList<Crmmessagechannel>());
        }
        if (companyemployee.getCompanybankCollection() == null) {
            companyemployee.setCompanybankCollection(new ArrayList<Companybank>());
        }
        if (companyemployee.getCompanybankCollection1() == null) {
            companyemployee.setCompanybankCollection1(new ArrayList<Companybank>());
        }
        if (companyemployee.getCrmmessagechanneltemplateCollection() == null) {
            companyemployee.setCrmmessagechanneltemplateCollection(new ArrayList<Crmmessagechanneltemplate>());
        }
        if (companyemployee.getCrmmessagechanneltemplateCollection1() == null) {
            companyemployee.setCrmmessagechanneltemplateCollection1(new ArrayList<Crmmessagechanneltemplate>());
        }
        if (companyemployee.getCrmprojectpropsCollection() == null) {
            companyemployee.setCrmprojectpropsCollection(new ArrayList<Crmprojectprops>());
        }
        if (companyemployee.getCrmprojectpropsCollection1() == null) {
            companyemployee.setCrmprojectpropsCollection1(new ArrayList<Crmprojectprops>());
        }
        if (companyemployee.getCrmcampaignreceiverCollection() == null) {
            companyemployee.setCrmcampaignreceiverCollection(new ArrayList<Crmcampaignreceiver>());
        }
        if (companyemployee.getCrmcampaignreceiverCollection1() == null) {
            companyemployee.setCrmcampaignreceiverCollection1(new ArrayList<Crmcampaignreceiver>());
        }
        if (companyemployee.getCrmworkordersettingsCollection() == null) {
            companyemployee.setCrmworkordersettingsCollection(new ArrayList<Crmworkordersettings>());
        }
        if (companyemployee.getProductattachmentsCollection() == null) {
            companyemployee.setProductattachmentsCollection(new ArrayList<Productattachments>());
        }
        if (companyemployee.getProductattachmentsCollection1() == null) {
            companyemployee.setProductattachmentsCollection1(new ArrayList<Productattachments>());
        }
        if (companyemployee.getProductattachmentsCollection2() == null) {
            companyemployee.setProductattachmentsCollection2(new ArrayList<Productattachments>());
        }
        if (companyemployee.getCrmcampaignstatusCollection() == null) {
            companyemployee.setCrmcampaignstatusCollection(new ArrayList<Crmcampaignstatus>());
        }
        if (companyemployee.getCrmcampaignstatusCollection1() == null) {
            companyemployee.setCrmcampaignstatusCollection1(new ArrayList<Crmcampaignstatus>());
        }
        if (companyemployee.getCrmcampaignpositionCollection() == null) {
            companyemployee.setCrmcampaignpositionCollection(new ArrayList<Crmcampaignposition>());
        }
        if (companyemployee.getCrmcampaignpositionCollection1() == null) {
            companyemployee.setCrmcampaignpositionCollection1(new ArrayList<Crmcampaignposition>());
        }
        if (companyemployee.getCrmforumteammembersCollection() == null) {
            companyemployee.setCrmforumteammembersCollection(new ArrayList<Crmforumteammembers>());
        }
        if (companyemployee.getCustomercontactsCollection() == null) {
            companyemployee.setCustomercontactsCollection(new ArrayList<Customercontacts>());
        }
        if (companyemployee.getCustomercontactsCollection1() == null) {
            companyemployee.setCustomercontactsCollection1(new ArrayList<Customercontacts>());
        }
        if (companyemployee.getAppointmentCollection() == null) {
            companyemployee.setAppointmentCollection(new ArrayList<Appointment>());
        }
        if (companyemployee.getAppointmentCollection1() == null) {
            companyemployee.setAppointmentCollection1(new ArrayList<Appointment>());
        }
        if (companyemployee.getAppointmentCollection2() == null) {
            companyemployee.setAppointmentCollection2(new ArrayList<Appointment>());
        }
        if (companyemployee.getAppointmentCollection3() == null) {
            companyemployee.setAppointmentCollection3(new ArrayList<Appointment>());
        }
        if (companyemployee.getCompanydepartmentCollection() == null) {
            companyemployee.setCompanydepartmentCollection(new ArrayList<Companydepartment>());
        }
        if (companyemployee.getCompanydepartmentCollection1() == null) {
            companyemployee.setCompanydepartmentCollection1(new ArrayList<Companydepartment>());
        }
        if (companyemployee.getCrmusermodulesCollection() == null) {
            companyemployee.setCrmusermodulesCollection(new ArrayList<Crmusermodules>());
        }
        if (companyemployee.getCrmusermodulesCollection1() == null) {
            companyemployee.setCrmusermodulesCollection1(new ArrayList<Crmusermodules>());
        }
        if (companyemployee.getCrmexpenseCollection() == null) {
            companyemployee.setCrmexpenseCollection(new ArrayList<Crmexpense>());
        }
        if (companyemployee.getCrmexpenseCollection1() == null) {
            companyemployee.setCrmexpenseCollection1(new ArrayList<Crmexpense>());
        }
        if (companyemployee.getCrmworkorderCollection() == null) {
            companyemployee.setCrmworkorderCollection(new ArrayList<Crmworkorder>());
        }
        if (companyemployee.getCrmworkorderCollection1() == null) {
            companyemployee.setCrmworkorderCollection1(new ArrayList<Crmworkorder>());
        }
        if (companyemployee.getCrmcampaigndocsCollection() == null) {
            companyemployee.setCrmcampaigndocsCollection(new ArrayList<Crmcampaigndocs>());
        }
        if (companyemployee.getCrmcampaigndocsCollection1() == null) {
            companyemployee.setCrmcampaigndocsCollection1(new ArrayList<Crmcampaigndocs>());
        }
        if (companyemployee.getCrmemployeenoteCollection() == null) {
            companyemployee.setCrmemployeenoteCollection(new ArrayList<Crmemployeenote>());
        }
        if (companyemployee.getCompanycontactsCollection() == null) {
            companyemployee.setCompanycontactsCollection(new ArrayList<Companycontacts>());
        }
        if (companyemployee.getCompanycontactsCollection1() == null) {
            companyemployee.setCompanycontactsCollection1(new ArrayList<Companycontacts>());
        }
        if (companyemployee.getCompanyemployeeCollection() == null) {
            companyemployee.setCompanyemployeeCollection(new ArrayList<Companyemployee>());
        }
        if (companyemployee.getCompanyemployeeCollection1() == null) {
            companyemployee.setCompanyemployeeCollection1(new ArrayList<Companyemployee>());
        }
        if (companyemployee.getCompanyproducttypeCollection() == null) {
            companyemployee.setCompanyproducttypeCollection(new ArrayList<Companyproducttype>());
        }
        if (companyemployee.getCompanyproducttypeCollection1() == null) {
            companyemployee.setCompanyproducttypeCollection1(new ArrayList<Companyproducttype>());
        }
        if (companyemployee.getCrmcampaignCollection() == null) {
            companyemployee.setCrmcampaignCollection(new ArrayList<Crmcampaign>());
        }
        if (companyemployee.getCrmcampaignCollection1() == null) {
            companyemployee.setCrmcampaignCollection1(new ArrayList<Crmcampaign>());
        }
        if (companyemployee.getCrmmessagehistoryCollection() == null) {
            companyemployee.setCrmmessagehistoryCollection(new ArrayList<Crmmessagehistory>());
        }
        if (companyemployee.getCrmmessagehistoryCollection1() == null) {
            companyemployee.setCrmmessagehistoryCollection1(new ArrayList<Crmmessagehistory>());
        }
        if (companyemployee.getCompanyaccountstackdocsCollection() == null) {
            companyemployee.setCompanyaccountstackdocsCollection(new ArrayList<Companyaccountstackdocs>());
        }
        if (companyemployee.getCompanyaccountstackdocsCollection1() == null) {
            companyemployee.setCompanyaccountstackdocsCollection1(new ArrayList<Companyaccountstackdocs>());
        }
        if (companyemployee.getCrmlaborCollection() == null) {
            companyemployee.setCrmlaborCollection(new ArrayList<Crmlabor>());
        }
        if (companyemployee.getCrmlaborCollection1() == null) {
            companyemployee.setCrmlaborCollection1(new ArrayList<Crmlabor>());
        }
        if (companyemployee.getCustomerbankCollection() == null) {
            companyemployee.setCustomerbankCollection(new ArrayList<Customerbank>());
        }
        if (companyemployee.getCustomerbankCollection1() == null) {
            companyemployee.setCustomerbankCollection1(new ArrayList<Customerbank>());
        }
        if (companyemployee.getCompnaypaymentcurrencyCollection() == null) {
            companyemployee.setCompnaypaymentcurrencyCollection(new ArrayList<Compnaypaymentcurrency>());
        }
        if (companyemployee.getCompnaypaymentcurrencyCollection1() == null) {
            companyemployee.setCompnaypaymentcurrencyCollection1(new ArrayList<Compnaypaymentcurrency>());
        }
        if (companyemployee.getCrmprojectCollection() == null) {
            companyemployee.setCrmprojectCollection(new ArrayList<Crmproject>());
        }
        if (companyemployee.getCrmprojectCollection1() == null) {
            companyemployee.setCrmprojectCollection1(new ArrayList<Crmproject>());
        }
        if (companyemployee.getCrmprojectCollection2() == null) {
            companyemployee.setCrmprojectCollection2(new ArrayList<Crmproject>());
        }
        if (companyemployee.getCompanyemployeeaddressCollection() == null) {
            companyemployee.setCompanyemployeeaddressCollection(new ArrayList<Companyemployeeaddress>());
        }
        if (companyemployee.getCompanyemployeeaddressCollection1() == null) {
            companyemployee.setCompanyemployeeaddressCollection1(new ArrayList<Companyemployeeaddress>());
        }
        if (companyemployee.getCompanyemployeeaddressCollection2() == null) {
            companyemployee.setCompanyemployeeaddressCollection2(new ArrayList<Companyemployeeaddress>());
        }
        if (companyemployee.getCrmforumdocsCollection() == null) {
            companyemployee.setCrmforumdocsCollection(new ArrayList<Crmforumdocs>());
        }
        if (companyemployee.getCompanyproductCollection() == null) {
            companyemployee.setCompanyproductCollection(new ArrayList<Companyproduct>());
        }
        if (companyemployee.getCompanyproductCollection1() == null) {
            companyemployee.setCompanyproductCollection1(new ArrayList<Companyproduct>());
        }
        if (companyemployee.getEmployeedesignationCollection() == null) {
            companyemployee.setEmployeedesignationCollection(new ArrayList<Employeedesignation>());
        }
        if (companyemployee.getEmployeedesignationCollection1() == null) {
            companyemployee.setEmployeedesignationCollection1(new ArrayList<Employeedesignation>());
        }
        if (companyemployee.getCompanyaccountstackCollection() == null) {
            companyemployee.setCompanyaccountstackCollection(new ArrayList<Companyaccountstack>());
        }
        if (companyemployee.getCompanyaccountstackCollection1() == null) {
            companyemployee.setCompanyaccountstackCollection1(new ArrayList<Companyaccountstack>());
        }
        if (companyemployee.getCrmprojectteammembersCollection() == null) {
            companyemployee.setCrmprojectteammembersCollection(new ArrayList<Crmprojectteammembers>());
        }
        if (companyemployee.getCrmprojectteammembersCollection1() == null) {
            companyemployee.setCrmprojectteammembersCollection1(new ArrayList<Crmprojectteammembers>());
        }
        if (companyemployee.getCrmprojectteammembersCollection2() == null) {
            companyemployee.setCrmprojectteammembersCollection2(new ArrayList<Crmprojectteammembers>());
        }
        if (companyemployee.getComponentattachmentsCollection() == null) {
            companyemployee.setComponentattachmentsCollection(new ArrayList<Componentattachments>());
        }
        if (companyemployee.getComponentattachmentsCollection1() == null) {
            companyemployee.setComponentattachmentsCollection1(new ArrayList<Componentattachments>());
        }
        if (companyemployee.getCrmvisitorcontactsaddressCollection() == null) {
            companyemployee.setCrmvisitorcontactsaddressCollection(new ArrayList<Crmvisitorcontactsaddress>());
        }
        if (companyemployee.getCrmvisitorcontactsCollection() == null) {
            companyemployee.setCrmvisitorcontactsCollection(new ArrayList<Crmvisitorcontacts>());
        }
        if (companyemployee.getCrmvisitorcontactsCollection1() == null) {
            companyemployee.setCrmvisitorcontactsCollection1(new ArrayList<Crmvisitorcontacts>());
        }
        if (companyemployee.getCompanyaccountstackcdCollection() == null) {
            companyemployee.setCompanyaccountstackcdCollection(new ArrayList<Companyaccountstackcd>());
        }
        if (companyemployee.getCompanyaccountstackcdCollection1() == null) {
            companyemployee.setCompanyaccountstackcdCollection1(new ArrayList<Companyaccountstackcd>());
        }
        if (companyemployee.getCrmprojectticketmanagementCollection() == null) {
            companyemployee.setCrmprojectticketmanagementCollection(new ArrayList<Crmprojectticketmanagement>());
        }
        if (companyemployee.getCrmprojectticketmanagementCollection1() == null) {
            companyemployee.setCrmprojectticketmanagementCollection1(new ArrayList<Crmprojectticketmanagement>());
        }
        if (companyemployee.getCrmprojectticketmanagementCollection2() == null) {
            companyemployee.setCrmprojectticketmanagementCollection2(new ArrayList<Crmprojectticketmanagement>());
        }
        if (companyemployee.getCrmprojectticketmanagementCollection3() == null) {
            companyemployee.setCrmprojectticketmanagementCollection3(new ArrayList<Crmprojectticketmanagement>());
        }
        if (companyemployee.getCrmprojectticketnotificationCollection() == null) {
            companyemployee.setCrmprojectticketnotificationCollection(new ArrayList<Crmprojectticketnotification>());
        }
        if (companyemployee.getCrmprojectticketnotificationCollection1() == null) {
            companyemployee.setCrmprojectticketnotificationCollection1(new ArrayList<Crmprojectticketnotification>());
        }
        if (companyemployee.getCrmprojectticketnotificationCollection2() == null) {
            companyemployee.setCrmprojectticketnotificationCollection2(new ArrayList<Crmprojectticketnotification>());
        }
        if (companyemployee.getCrmvisitorCollection() == null) {
            companyemployee.setCrmvisitorCollection(new ArrayList<Crmvisitor>());
        }
        if (companyemployee.getCrmvisitorCollection1() == null) {
            companyemployee.setCrmvisitorCollection1(new ArrayList<Crmvisitor>());
        }
        if (companyemployee.getCrmworkordertypeCollection() == null) {
            companyemployee.setCrmworkordertypeCollection(new ArrayList<Crmworkordertype>());
        }
        if (companyemployee.getCrmworkordertypeCollection1() == null) {
            companyemployee.setCrmworkordertypeCollection1(new ArrayList<Crmworkordertype>());
        }
        if (companyemployee.getCrmlabortypeCollection() == null) {
            companyemployee.setCrmlabortypeCollection(new ArrayList<Crmlabortype>());
        }
        if (companyemployee.getCrmlabortypeCollection1() == null) {
            companyemployee.setCrmlabortypeCollection1(new ArrayList<Crmlabortype>());
        }
        if (companyemployee.getCrmbillingtypeCollection() == null) {
            companyemployee.setCrmbillingtypeCollection(new ArrayList<Crmbillingtype>());
        }
        if (companyemployee.getCrmbillingtypeCollection1() == null) {
            companyemployee.setCrmbillingtypeCollection1(new ArrayList<Crmbillingtype>());
        }
        if (companyemployee.getCompanyproductcategoryCollection() == null) {
            companyemployee.setCompanyproductcategoryCollection(new ArrayList<Companyproductcategory>());
        }
        if (companyemployee.getCompanyproductcategoryCollection1() == null) {
            companyemployee.setCompanyproductcategoryCollection1(new ArrayList<Companyproductcategory>());
        }
        if (companyemployee.getCrmprojecttaskCollection() == null) {
            companyemployee.setCrmprojecttaskCollection(new ArrayList<Crmprojecttask>());
        }
        if (companyemployee.getCrmprojecttaskCollection1() == null) {
            companyemployee.setCrmprojecttaskCollection1(new ArrayList<Crmprojecttask>());
        }
        if (companyemployee.getCrmcampaignpropsCollection() == null) {
            companyemployee.setCrmcampaignpropsCollection(new ArrayList<Crmcampaignprops>());
        }
        if (companyemployee.getCrmcampaignpropsCollection1() == null) {
            companyemployee.setCrmcampaignpropsCollection1(new ArrayList<Crmcampaignprops>());
        }
        if (companyemployee.getCustomercontactsaddressCollection() == null) {
            companyemployee.setCustomercontactsaddressCollection(new ArrayList<Customercontactsaddress>());
        }
        if (companyemployee.getCustomercontactsaddressCollection1() == null) {
            companyemployee.setCustomercontactsaddressCollection1(new ArrayList<Customercontactsaddress>());
        }
        if (companyemployee.getCustomercategoryCollection() == null) {
            companyemployee.setCustomercategoryCollection(new ArrayList<Customercategory>());
        }
        if (companyemployee.getCustomercategoryCollection1() == null) {
            companyemployee.setCustomercategoryCollection1(new ArrayList<Customercategory>());
        }
        if (companyemployee.getProductcomponentsCollection() == null) {
            companyemployee.setProductcomponentsCollection(new ArrayList<Productcomponents>());
        }
        if (companyemployee.getProductcomponentsCollection1() == null) {
            companyemployee.setProductcomponentsCollection1(new ArrayList<Productcomponents>());
        }
        if (companyemployee.getCrmexpensetypeCollection() == null) {
            companyemployee.setCrmexpensetypeCollection(new ArrayList<Crmexpensetype>());
        }
        if (companyemployee.getCrmexpensetypeCollection1() == null) {
            companyemployee.setCrmexpensetypeCollection1(new ArrayList<Crmexpensetype>());
        }
        if (companyemployee.getCompanypaymentsCollection() == null) {
            companyemployee.setCompanypaymentsCollection(new ArrayList<Companypayments>());
        }
        if (companyemployee.getCompanypaymentsCollection1() == null) {
            companyemployee.setCompanypaymentsCollection1(new ArrayList<Companypayments>());
        }
        if (companyemployee.getCrmworkorderresolutionCollection() == null) {
            companyemployee.setCrmworkorderresolutionCollection(new ArrayList<Crmworkorderresolution>());
        }
        if (companyemployee.getCrmworkorderresolutionCollection1() == null) {
            companyemployee.setCrmworkorderresolutionCollection1(new ArrayList<Crmworkorderresolution>());
        }
        if (companyemployee.getCompanyCollection() == null) {
            companyemployee.setCompanyCollection(new ArrayList<Company>());
        }
        if (companyemployee.getCompanyCollection1() == null) {
            companyemployee.setCompanyCollection1(new ArrayList<Company>());
        }
        if (companyemployee.getCompanyaddresstypeCollection() == null) {
            companyemployee.setCompanyaddresstypeCollection(new ArrayList<Companyaddresstype>());
        }
        if (companyemployee.getCompanyaddresstypeCollection1() == null) {
            companyemployee.setCompanyaddresstypeCollection1(new ArrayList<Companyaddresstype>());
        }
        if (companyemployee.getCrmemployeecontactsCollection() == null) {
            companyemployee.setCrmemployeecontactsCollection(new ArrayList<Crmemployeecontacts>());
        }
        if (companyemployee.getApprovalCollection() == null) {
            companyemployee.setApprovalCollection(new ArrayList<Approval>());
        }
        if (companyemployee.getApprovalCollection1() == null) {
            companyemployee.setApprovalCollection1(new ArrayList<Approval>());
        }
        if (companyemployee.getApprovalCollection2() == null) {
            companyemployee.setApprovalCollection2(new ArrayList<Approval>());
        }
        if (companyemployee.getWorkorderinstructionsCollection() == null) {
            companyemployee.setWorkorderinstructionsCollection(new ArrayList<Workorderinstructions>());
        }
        if (companyemployee.getWorkorderinstructionsCollection1() == null) {
            companyemployee.setWorkorderinstructionsCollection1(new ArrayList<Workorderinstructions>());
        }
        if (companyemployee.getCompanycustomerCollection() == null) {
            companyemployee.setCompanycustomerCollection(new ArrayList<Companycustomer>());
        }
        if (companyemployee.getCompanycustomerCollection1() == null) {
            companyemployee.setCompanycustomerCollection1(new ArrayList<Companycustomer>());
        }
        if (companyemployee.getCrmforumCollection() == null) {
            companyemployee.setCrmforumCollection(new ArrayList<Crmforum>());
        }
        if (companyemployee.getCrmprojectstatusCollection() == null) {
            companyemployee.setCrmprojectstatusCollection(new ArrayList<Crmprojectstatus>());
        }
        if (companyemployee.getCrmprojectstatusCollection1() == null) {
            companyemployee.setCrmprojectstatusCollection1(new ArrayList<Crmprojectstatus>());
        }
        if (companyemployee.getCrmschedulerevnttypeCollection() == null) {
            companyemployee.setCrmschedulerevnttypeCollection(new ArrayList<Crmschedulerevnttype>());
        }
        if (companyemployee.getCrmschedulerevnttypeCollection1() == null) {
            companyemployee.setCrmschedulerevnttypeCollection1(new ArrayList<Crmschedulerevnttype>());
        }
        if (companyemployee.getCrmschedulerefcodeCollection() == null) {
            companyemployee.setCrmschedulerefcodeCollection(new ArrayList<Crmschedulerefcode>());
        }
        if (companyemployee.getCrmschedulerefcodeCollection1() == null) {
            companyemployee.setCrmschedulerefcodeCollection1(new ArrayList<Crmschedulerefcode>());
        }
        if (companyemployee.getComponenttypeCollection() == null) {
            companyemployee.setComponenttypeCollection(new ArrayList<Componenttype>());
        }
        if (companyemployee.getComponenttypeCollection1() == null) {
            companyemployee.setComponenttypeCollection1(new ArrayList<Componenttype>());
        }
        if (companyemployee.getCompanypaymentschemeCollection() == null) {
            companyemployee.setCompanypaymentschemeCollection(new ArrayList<Companypaymentscheme>());
        }
        if (companyemployee.getCompanypaymentschemeCollection1() == null) {
            companyemployee.setCompanypaymentschemeCollection1(new ArrayList<Companypaymentscheme>());
        }
        if (companyemployee.getCompanycontactsaddressCollection() == null) {
            companyemployee.setCompanycontactsaddressCollection(new ArrayList<Companycontactsaddress>());
        }
        if (companyemployee.getCompanycontactsaddressCollection1() == null) {
            companyemployee.setCompanycontactsaddressCollection1(new ArrayList<Companycontactsaddress>());
        }
        if (companyemployee.getCrmprofilesettingsCollection() == null) {
            companyemployee.setCrmprofilesettingsCollection(new ArrayList<Crmprofilesettings>());
        }
        List<String> illegalOrphanMessages = null;
        Companydepartment companydepartmentOrphanCheck = companyemployee.getCompanydepartment();
        if (companydepartmentOrphanCheck != null) {
            Companyemployee oldCompanyemployeeOfCompanydepartment = companydepartmentOrphanCheck.getCompanyemployee();
            if (oldCompanyemployeeOfCompanydepartment != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Companydepartment " + companydepartmentOrphanCheck + " already has an item of type Companyemployee whose companydepartment column cannot be null. Please make another selection for the companydepartment field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companyemployee.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companyemployee.setCompany(company);
            }
            Companydepartment companydepartment = companyemployee.getCompanydepartment();
            if (companydepartment != null) {
                companydepartment = em.getReference(companydepartment.getClass(), companydepartment.getCompanydepartmentPK());
                companyemployee.setCompanydepartment(companydepartment);
            }
            Companyemployee companyemployeeRel = companyemployee.getCompanyemployee();
            if (companyemployeeRel != null) {
                companyemployeeRel = em.getReference(companyemployeeRel.getClass(), companyemployeeRel.getCompanyemployeePK());
                companyemployee.setCompanyemployee(companyemployeeRel);
            }
            Companyemployee companyemployee1 = companyemployee.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companyemployee.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmmessagechannel> attachedCrmmessagechannelCollection = new ArrayList<Crmmessagechannel>();
            for (Crmmessagechannel crmmessagechannelCollectionCrmmessagechannelToAttach : companyemployee.getCrmmessagechannelCollection()) {
                crmmessagechannelCollectionCrmmessagechannelToAttach = em.getReference(crmmessagechannelCollectionCrmmessagechannelToAttach.getClass(), crmmessagechannelCollectionCrmmessagechannelToAttach.getCrmmessagechannelPK());
                attachedCrmmessagechannelCollection.add(crmmessagechannelCollectionCrmmessagechannelToAttach);
            }
            companyemployee.setCrmmessagechannelCollection(attachedCrmmessagechannelCollection);
            Collection<Crmmessagechannel> attachedCrmmessagechannelCollection1 = new ArrayList<Crmmessagechannel>();
            for (Crmmessagechannel crmmessagechannelCollection1CrmmessagechannelToAttach : companyemployee.getCrmmessagechannelCollection1()) {
                crmmessagechannelCollection1CrmmessagechannelToAttach = em.getReference(crmmessagechannelCollection1CrmmessagechannelToAttach.getClass(), crmmessagechannelCollection1CrmmessagechannelToAttach.getCrmmessagechannelPK());
                attachedCrmmessagechannelCollection1.add(crmmessagechannelCollection1CrmmessagechannelToAttach);
            }
            companyemployee.setCrmmessagechannelCollection1(attachedCrmmessagechannelCollection1);
            Collection<Crmmessagechannel> attachedCrmmessagechannelCollection2 = new ArrayList<Crmmessagechannel>();
            for (Crmmessagechannel crmmessagechannelCollection2CrmmessagechannelToAttach : companyemployee.getCrmmessagechannelCollection2()) {
                crmmessagechannelCollection2CrmmessagechannelToAttach = em.getReference(crmmessagechannelCollection2CrmmessagechannelToAttach.getClass(), crmmessagechannelCollection2CrmmessagechannelToAttach.getCrmmessagechannelPK());
                attachedCrmmessagechannelCollection2.add(crmmessagechannelCollection2CrmmessagechannelToAttach);
            }
            companyemployee.setCrmmessagechannelCollection2(attachedCrmmessagechannelCollection2);
            Collection<Companybank> attachedCompanybankCollection = new ArrayList<Companybank>();
            for (Companybank companybankCollectionCompanybankToAttach : companyemployee.getCompanybankCollection()) {
                companybankCollectionCompanybankToAttach = em.getReference(companybankCollectionCompanybankToAttach.getClass(), companybankCollectionCompanybankToAttach.getCompanybankPK());
                attachedCompanybankCollection.add(companybankCollectionCompanybankToAttach);
            }
            companyemployee.setCompanybankCollection(attachedCompanybankCollection);
            Collection<Companybank> attachedCompanybankCollection1 = new ArrayList<Companybank>();
            for (Companybank companybankCollection1CompanybankToAttach : companyemployee.getCompanybankCollection1()) {
                companybankCollection1CompanybankToAttach = em.getReference(companybankCollection1CompanybankToAttach.getClass(), companybankCollection1CompanybankToAttach.getCompanybankPK());
                attachedCompanybankCollection1.add(companybankCollection1CompanybankToAttach);
            }
            companyemployee.setCompanybankCollection1(attachedCompanybankCollection1);
            Collection<Crmmessagechanneltemplate> attachedCrmmessagechanneltemplateCollection = new ArrayList<Crmmessagechanneltemplate>();
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionCrmmessagechanneltemplateToAttach : companyemployee.getCrmmessagechanneltemplateCollection()) {
                crmmessagechanneltemplateCollectionCrmmessagechanneltemplateToAttach = em.getReference(crmmessagechanneltemplateCollectionCrmmessagechanneltemplateToAttach.getClass(), crmmessagechanneltemplateCollectionCrmmessagechanneltemplateToAttach.getCrmmessagechanneltemplatePK());
                attachedCrmmessagechanneltemplateCollection.add(crmmessagechanneltemplateCollectionCrmmessagechanneltemplateToAttach);
            }
            companyemployee.setCrmmessagechanneltemplateCollection(attachedCrmmessagechanneltemplateCollection);
            Collection<Crmmessagechanneltemplate> attachedCrmmessagechanneltemplateCollection1 = new ArrayList<Crmmessagechanneltemplate>();
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollection1CrmmessagechanneltemplateToAttach : companyemployee.getCrmmessagechanneltemplateCollection1()) {
                crmmessagechanneltemplateCollection1CrmmessagechanneltemplateToAttach = em.getReference(crmmessagechanneltemplateCollection1CrmmessagechanneltemplateToAttach.getClass(), crmmessagechanneltemplateCollection1CrmmessagechanneltemplateToAttach.getCrmmessagechanneltemplatePK());
                attachedCrmmessagechanneltemplateCollection1.add(crmmessagechanneltemplateCollection1CrmmessagechanneltemplateToAttach);
            }
            companyemployee.setCrmmessagechanneltemplateCollection1(attachedCrmmessagechanneltemplateCollection1);
            Collection<Crmprojectprops> attachedCrmprojectpropsCollection = new ArrayList<Crmprojectprops>();
            for (Crmprojectprops crmprojectpropsCollectionCrmprojectpropsToAttach : companyemployee.getCrmprojectpropsCollection()) {
                crmprojectpropsCollectionCrmprojectpropsToAttach = em.getReference(crmprojectpropsCollectionCrmprojectpropsToAttach.getClass(), crmprojectpropsCollectionCrmprojectpropsToAttach.getCrmprojectpropsPK());
                attachedCrmprojectpropsCollection.add(crmprojectpropsCollectionCrmprojectpropsToAttach);
            }
            companyemployee.setCrmprojectpropsCollection(attachedCrmprojectpropsCollection);
            Collection<Crmprojectprops> attachedCrmprojectpropsCollection1 = new ArrayList<Crmprojectprops>();
            for (Crmprojectprops crmprojectpropsCollection1CrmprojectpropsToAttach : companyemployee.getCrmprojectpropsCollection1()) {
                crmprojectpropsCollection1CrmprojectpropsToAttach = em.getReference(crmprojectpropsCollection1CrmprojectpropsToAttach.getClass(), crmprojectpropsCollection1CrmprojectpropsToAttach.getCrmprojectpropsPK());
                attachedCrmprojectpropsCollection1.add(crmprojectpropsCollection1CrmprojectpropsToAttach);
            }
            companyemployee.setCrmprojectpropsCollection1(attachedCrmprojectpropsCollection1);
            Collection<Crmcampaignreceiver> attachedCrmcampaignreceiverCollection = new ArrayList<Crmcampaignreceiver>();
            for (Crmcampaignreceiver crmcampaignreceiverCollectionCrmcampaignreceiverToAttach : companyemployee.getCrmcampaignreceiverCollection()) {
                crmcampaignreceiverCollectionCrmcampaignreceiverToAttach = em.getReference(crmcampaignreceiverCollectionCrmcampaignreceiverToAttach.getClass(), crmcampaignreceiverCollectionCrmcampaignreceiverToAttach.getCrmcampaignreceiverPK());
                attachedCrmcampaignreceiverCollection.add(crmcampaignreceiverCollectionCrmcampaignreceiverToAttach);
            }
            companyemployee.setCrmcampaignreceiverCollection(attachedCrmcampaignreceiverCollection);
            Collection<Crmcampaignreceiver> attachedCrmcampaignreceiverCollection1 = new ArrayList<Crmcampaignreceiver>();
            for (Crmcampaignreceiver crmcampaignreceiverCollection1CrmcampaignreceiverToAttach : companyemployee.getCrmcampaignreceiverCollection1()) {
                crmcampaignreceiverCollection1CrmcampaignreceiverToAttach = em.getReference(crmcampaignreceiverCollection1CrmcampaignreceiverToAttach.getClass(), crmcampaignreceiverCollection1CrmcampaignreceiverToAttach.getCrmcampaignreceiverPK());
                attachedCrmcampaignreceiverCollection1.add(crmcampaignreceiverCollection1CrmcampaignreceiverToAttach);
            }
            companyemployee.setCrmcampaignreceiverCollection1(attachedCrmcampaignreceiverCollection1);
            Collection<Crmworkordersettings> attachedCrmworkordersettingsCollection = new ArrayList<Crmworkordersettings>();
            for (Crmworkordersettings crmworkordersettingsCollectionCrmworkordersettingsToAttach : companyemployee.getCrmworkordersettingsCollection()) {
                crmworkordersettingsCollectionCrmworkordersettingsToAttach = em.getReference(crmworkordersettingsCollectionCrmworkordersettingsToAttach.getClass(), crmworkordersettingsCollectionCrmworkordersettingsToAttach.getCrmworkordersettingsPK());
                attachedCrmworkordersettingsCollection.add(crmworkordersettingsCollectionCrmworkordersettingsToAttach);
            }
            companyemployee.setCrmworkordersettingsCollection(attachedCrmworkordersettingsCollection);
            Collection<Productattachments> attachedProductattachmentsCollection = new ArrayList<Productattachments>();
            for (Productattachments productattachmentsCollectionProductattachmentsToAttach : companyemployee.getProductattachmentsCollection()) {
                productattachmentsCollectionProductattachmentsToAttach = em.getReference(productattachmentsCollectionProductattachmentsToAttach.getClass(), productattachmentsCollectionProductattachmentsToAttach.getProductattachmentsPK());
                attachedProductattachmentsCollection.add(productattachmentsCollectionProductattachmentsToAttach);
            }
            companyemployee.setProductattachmentsCollection(attachedProductattachmentsCollection);
            Collection<Productattachments> attachedProductattachmentsCollection1 = new ArrayList<Productattachments>();
            for (Productattachments productattachmentsCollection1ProductattachmentsToAttach : companyemployee.getProductattachmentsCollection1()) {
                productattachmentsCollection1ProductattachmentsToAttach = em.getReference(productattachmentsCollection1ProductattachmentsToAttach.getClass(), productattachmentsCollection1ProductattachmentsToAttach.getProductattachmentsPK());
                attachedProductattachmentsCollection1.add(productattachmentsCollection1ProductattachmentsToAttach);
            }
            companyemployee.setProductattachmentsCollection1(attachedProductattachmentsCollection1);
            Collection<Productattachments> attachedProductattachmentsCollection2 = new ArrayList<Productattachments>();
            for (Productattachments productattachmentsCollection2ProductattachmentsToAttach : companyemployee.getProductattachmentsCollection2()) {
                productattachmentsCollection2ProductattachmentsToAttach = em.getReference(productattachmentsCollection2ProductattachmentsToAttach.getClass(), productattachmentsCollection2ProductattachmentsToAttach.getProductattachmentsPK());
                attachedProductattachmentsCollection2.add(productattachmentsCollection2ProductattachmentsToAttach);
            }
            companyemployee.setProductattachmentsCollection2(attachedProductattachmentsCollection2);
            Collection<Crmcampaignstatus> attachedCrmcampaignstatusCollection = new ArrayList<Crmcampaignstatus>();
            for (Crmcampaignstatus crmcampaignstatusCollectionCrmcampaignstatusToAttach : companyemployee.getCrmcampaignstatusCollection()) {
                crmcampaignstatusCollectionCrmcampaignstatusToAttach = em.getReference(crmcampaignstatusCollectionCrmcampaignstatusToAttach.getClass(), crmcampaignstatusCollectionCrmcampaignstatusToAttach.getCrmcampaignstatusPK());
                attachedCrmcampaignstatusCollection.add(crmcampaignstatusCollectionCrmcampaignstatusToAttach);
            }
            companyemployee.setCrmcampaignstatusCollection(attachedCrmcampaignstatusCollection);
            Collection<Crmcampaignstatus> attachedCrmcampaignstatusCollection1 = new ArrayList<Crmcampaignstatus>();
            for (Crmcampaignstatus crmcampaignstatusCollection1CrmcampaignstatusToAttach : companyemployee.getCrmcampaignstatusCollection1()) {
                crmcampaignstatusCollection1CrmcampaignstatusToAttach = em.getReference(crmcampaignstatusCollection1CrmcampaignstatusToAttach.getClass(), crmcampaignstatusCollection1CrmcampaignstatusToAttach.getCrmcampaignstatusPK());
                attachedCrmcampaignstatusCollection1.add(crmcampaignstatusCollection1CrmcampaignstatusToAttach);
            }
            companyemployee.setCrmcampaignstatusCollection1(attachedCrmcampaignstatusCollection1);
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollection = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollectionCrmcampaignpositionToAttach : companyemployee.getCrmcampaignpositionCollection()) {
                crmcampaignpositionCollectionCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollectionCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollectionCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollection.add(crmcampaignpositionCollectionCrmcampaignpositionToAttach);
            }
            companyemployee.setCrmcampaignpositionCollection(attachedCrmcampaignpositionCollection);
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollection1 = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollection1CrmcampaignpositionToAttach : companyemployee.getCrmcampaignpositionCollection1()) {
                crmcampaignpositionCollection1CrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollection1CrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollection1CrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollection1.add(crmcampaignpositionCollection1CrmcampaignpositionToAttach);
            }
            companyemployee.setCrmcampaignpositionCollection1(attachedCrmcampaignpositionCollection1);
            Collection<Crmforumteammembers> attachedCrmforumteammembersCollection = new ArrayList<Crmforumteammembers>();
            for (Crmforumteammembers crmforumteammembersCollectionCrmforumteammembersToAttach : companyemployee.getCrmforumteammembersCollection()) {
                crmforumteammembersCollectionCrmforumteammembersToAttach = em.getReference(crmforumteammembersCollectionCrmforumteammembersToAttach.getClass(), crmforumteammembersCollectionCrmforumteammembersToAttach.getCrmforumteammembersPK());
                attachedCrmforumteammembersCollection.add(crmforumteammembersCollectionCrmforumteammembersToAttach);
            }
            companyemployee.setCrmforumteammembersCollection(attachedCrmforumteammembersCollection);
            Collection<Customercontacts> attachedCustomercontactsCollection = new ArrayList<Customercontacts>();
            for (Customercontacts customercontactsCollectionCustomercontactsToAttach : companyemployee.getCustomercontactsCollection()) {
                customercontactsCollectionCustomercontactsToAttach = em.getReference(customercontactsCollectionCustomercontactsToAttach.getClass(), customercontactsCollectionCustomercontactsToAttach.getCustomercontactsPK());
                attachedCustomercontactsCollection.add(customercontactsCollectionCustomercontactsToAttach);
            }
            companyemployee.setCustomercontactsCollection(attachedCustomercontactsCollection);
            Collection<Customercontacts> attachedCustomercontactsCollection1 = new ArrayList<Customercontacts>();
            for (Customercontacts customercontactsCollection1CustomercontactsToAttach : companyemployee.getCustomercontactsCollection1()) {
                customercontactsCollection1CustomercontactsToAttach = em.getReference(customercontactsCollection1CustomercontactsToAttach.getClass(), customercontactsCollection1CustomercontactsToAttach.getCustomercontactsPK());
                attachedCustomercontactsCollection1.add(customercontactsCollection1CustomercontactsToAttach);
            }
            companyemployee.setCustomercontactsCollection1(attachedCustomercontactsCollection1);
            Collection<Appointment> attachedAppointmentCollection = new ArrayList<Appointment>();
            for (Appointment appointmentCollectionAppointmentToAttach : companyemployee.getAppointmentCollection()) {
                appointmentCollectionAppointmentToAttach = em.getReference(appointmentCollectionAppointmentToAttach.getClass(), appointmentCollectionAppointmentToAttach.getAppointmentPK());
                attachedAppointmentCollection.add(appointmentCollectionAppointmentToAttach);
            }
            companyemployee.setAppointmentCollection(attachedAppointmentCollection);
            Collection<Appointment> attachedAppointmentCollection1 = new ArrayList<Appointment>();
            for (Appointment appointmentCollection1AppointmentToAttach : companyemployee.getAppointmentCollection1()) {
                appointmentCollection1AppointmentToAttach = em.getReference(appointmentCollection1AppointmentToAttach.getClass(), appointmentCollection1AppointmentToAttach.getAppointmentPK());
                attachedAppointmentCollection1.add(appointmentCollection1AppointmentToAttach);
            }
            companyemployee.setAppointmentCollection1(attachedAppointmentCollection1);
            Collection<Appointment> attachedAppointmentCollection2 = new ArrayList<Appointment>();
            for (Appointment appointmentCollection2AppointmentToAttach : companyemployee.getAppointmentCollection2()) {
                appointmentCollection2AppointmentToAttach = em.getReference(appointmentCollection2AppointmentToAttach.getClass(), appointmentCollection2AppointmentToAttach.getAppointmentPK());
                attachedAppointmentCollection2.add(appointmentCollection2AppointmentToAttach);
            }
            companyemployee.setAppointmentCollection2(attachedAppointmentCollection2);
            Collection<Appointment> attachedAppointmentCollection3 = new ArrayList<Appointment>();
            for (Appointment appointmentCollection3AppointmentToAttach : companyemployee.getAppointmentCollection3()) {
                appointmentCollection3AppointmentToAttach = em.getReference(appointmentCollection3AppointmentToAttach.getClass(), appointmentCollection3AppointmentToAttach.getAppointmentPK());
                attachedAppointmentCollection3.add(appointmentCollection3AppointmentToAttach);
            }
            companyemployee.setAppointmentCollection3(attachedAppointmentCollection3);
            Collection<Companydepartment> attachedCompanydepartmentCollection = new ArrayList<Companydepartment>();
            for (Companydepartment companydepartmentCollectionCompanydepartmentToAttach : companyemployee.getCompanydepartmentCollection()) {
                companydepartmentCollectionCompanydepartmentToAttach = em.getReference(companydepartmentCollectionCompanydepartmentToAttach.getClass(), companydepartmentCollectionCompanydepartmentToAttach.getCompanydepartmentPK());
                attachedCompanydepartmentCollection.add(companydepartmentCollectionCompanydepartmentToAttach);
            }
            companyemployee.setCompanydepartmentCollection(attachedCompanydepartmentCollection);
            Collection<Companydepartment> attachedCompanydepartmentCollection1 = new ArrayList<Companydepartment>();
            for (Companydepartment companydepartmentCollection1CompanydepartmentToAttach : companyemployee.getCompanydepartmentCollection1()) {
                companydepartmentCollection1CompanydepartmentToAttach = em.getReference(companydepartmentCollection1CompanydepartmentToAttach.getClass(), companydepartmentCollection1CompanydepartmentToAttach.getCompanydepartmentPK());
                attachedCompanydepartmentCollection1.add(companydepartmentCollection1CompanydepartmentToAttach);
            }
            companyemployee.setCompanydepartmentCollection1(attachedCompanydepartmentCollection1);
            Collection<Crmusermodules> attachedCrmusermodulesCollection = new ArrayList<Crmusermodules>();
            for (Crmusermodules crmusermodulesCollectionCrmusermodulesToAttach : companyemployee.getCrmusermodulesCollection()) {
                crmusermodulesCollectionCrmusermodulesToAttach = em.getReference(crmusermodulesCollectionCrmusermodulesToAttach.getClass(), crmusermodulesCollectionCrmusermodulesToAttach.getCrmusermodulesPK());
                attachedCrmusermodulesCollection.add(crmusermodulesCollectionCrmusermodulesToAttach);
            }
            companyemployee.setCrmusermodulesCollection(attachedCrmusermodulesCollection);
            Collection<Crmusermodules> attachedCrmusermodulesCollection1 = new ArrayList<Crmusermodules>();
            for (Crmusermodules crmusermodulesCollection1CrmusermodulesToAttach : companyemployee.getCrmusermodulesCollection1()) {
                crmusermodulesCollection1CrmusermodulesToAttach = em.getReference(crmusermodulesCollection1CrmusermodulesToAttach.getClass(), crmusermodulesCollection1CrmusermodulesToAttach.getCrmusermodulesPK());
                attachedCrmusermodulesCollection1.add(crmusermodulesCollection1CrmusermodulesToAttach);
            }
            companyemployee.setCrmusermodulesCollection1(attachedCrmusermodulesCollection1);
            Collection<Crmexpense> attachedCrmexpenseCollection = new ArrayList<Crmexpense>();
            for (Crmexpense crmexpenseCollectionCrmexpenseToAttach : companyemployee.getCrmexpenseCollection()) {
                crmexpenseCollectionCrmexpenseToAttach = em.getReference(crmexpenseCollectionCrmexpenseToAttach.getClass(), crmexpenseCollectionCrmexpenseToAttach.getCrmexpensePK());
                attachedCrmexpenseCollection.add(crmexpenseCollectionCrmexpenseToAttach);
            }
            companyemployee.setCrmexpenseCollection(attachedCrmexpenseCollection);
            Collection<Crmexpense> attachedCrmexpenseCollection1 = new ArrayList<Crmexpense>();
            for (Crmexpense crmexpenseCollection1CrmexpenseToAttach : companyemployee.getCrmexpenseCollection1()) {
                crmexpenseCollection1CrmexpenseToAttach = em.getReference(crmexpenseCollection1CrmexpenseToAttach.getClass(), crmexpenseCollection1CrmexpenseToAttach.getCrmexpensePK());
                attachedCrmexpenseCollection1.add(crmexpenseCollection1CrmexpenseToAttach);
            }
            companyemployee.setCrmexpenseCollection1(attachedCrmexpenseCollection1);
            Collection<Crmworkorder> attachedCrmworkorderCollection = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionCrmworkorderToAttach : companyemployee.getCrmworkorderCollection()) {
                crmworkorderCollectionCrmworkorderToAttach = em.getReference(crmworkorderCollectionCrmworkorderToAttach.getClass(), crmworkorderCollectionCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollection.add(crmworkorderCollectionCrmworkorderToAttach);
            }
            companyemployee.setCrmworkorderCollection(attachedCrmworkorderCollection);
            Collection<Crmworkorder> attachedCrmworkorderCollection1 = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollection1CrmworkorderToAttach : companyemployee.getCrmworkorderCollection1()) {
                crmworkorderCollection1CrmworkorderToAttach = em.getReference(crmworkorderCollection1CrmworkorderToAttach.getClass(), crmworkorderCollection1CrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollection1.add(crmworkorderCollection1CrmworkorderToAttach);
            }
            companyemployee.setCrmworkorderCollection1(attachedCrmworkorderCollection1);
            Collection<Crmcampaigndocs> attachedCrmcampaigndocsCollection = new ArrayList<Crmcampaigndocs>();
            for (Crmcampaigndocs crmcampaigndocsCollectionCrmcampaigndocsToAttach : companyemployee.getCrmcampaigndocsCollection()) {
                crmcampaigndocsCollectionCrmcampaigndocsToAttach = em.getReference(crmcampaigndocsCollectionCrmcampaigndocsToAttach.getClass(), crmcampaigndocsCollectionCrmcampaigndocsToAttach.getCrmcampaigndocsPK());
                attachedCrmcampaigndocsCollection.add(crmcampaigndocsCollectionCrmcampaigndocsToAttach);
            }
            companyemployee.setCrmcampaigndocsCollection(attachedCrmcampaigndocsCollection);
            Collection<Crmcampaigndocs> attachedCrmcampaigndocsCollection1 = new ArrayList<Crmcampaigndocs>();
            for (Crmcampaigndocs crmcampaigndocsCollection1CrmcampaigndocsToAttach : companyemployee.getCrmcampaigndocsCollection1()) {
                crmcampaigndocsCollection1CrmcampaigndocsToAttach = em.getReference(crmcampaigndocsCollection1CrmcampaigndocsToAttach.getClass(), crmcampaigndocsCollection1CrmcampaigndocsToAttach.getCrmcampaigndocsPK());
                attachedCrmcampaigndocsCollection1.add(crmcampaigndocsCollection1CrmcampaigndocsToAttach);
            }
            companyemployee.setCrmcampaigndocsCollection1(attachedCrmcampaigndocsCollection1);
            Collection<Crmemployeenote> attachedCrmemployeenoteCollection = new ArrayList<Crmemployeenote>();
            for (Crmemployeenote crmemployeenoteCollectionCrmemployeenoteToAttach : companyemployee.getCrmemployeenoteCollection()) {
                crmemployeenoteCollectionCrmemployeenoteToAttach = em.getReference(crmemployeenoteCollectionCrmemployeenoteToAttach.getClass(), crmemployeenoteCollectionCrmemployeenoteToAttach.getCrmemployeenotePK());
                attachedCrmemployeenoteCollection.add(crmemployeenoteCollectionCrmemployeenoteToAttach);
            }
            companyemployee.setCrmemployeenoteCollection(attachedCrmemployeenoteCollection);
            Collection<Companycontacts> attachedCompanycontactsCollection = new ArrayList<Companycontacts>();
            for (Companycontacts companycontactsCollectionCompanycontactsToAttach : companyemployee.getCompanycontactsCollection()) {
                companycontactsCollectionCompanycontactsToAttach = em.getReference(companycontactsCollectionCompanycontactsToAttach.getClass(), companycontactsCollectionCompanycontactsToAttach.getCompanycontactsPK());
                attachedCompanycontactsCollection.add(companycontactsCollectionCompanycontactsToAttach);
            }
            companyemployee.setCompanycontactsCollection(attachedCompanycontactsCollection);
            Collection<Companycontacts> attachedCompanycontactsCollection1 = new ArrayList<Companycontacts>();
            for (Companycontacts companycontactsCollection1CompanycontactsToAttach : companyemployee.getCompanycontactsCollection1()) {
                companycontactsCollection1CompanycontactsToAttach = em.getReference(companycontactsCollection1CompanycontactsToAttach.getClass(), companycontactsCollection1CompanycontactsToAttach.getCompanycontactsPK());
                attachedCompanycontactsCollection1.add(companycontactsCollection1CompanycontactsToAttach);
            }
            companyemployee.setCompanycontactsCollection1(attachedCompanycontactsCollection1);
            Collection<Companyemployee> attachedCompanyemployeeCollection = new ArrayList<Companyemployee>();
            for (Companyemployee companyemployeeCollectionCompanyemployeeToAttach : companyemployee.getCompanyemployeeCollection()) {
                companyemployeeCollectionCompanyemployeeToAttach = em.getReference(companyemployeeCollectionCompanyemployeeToAttach.getClass(), companyemployeeCollectionCompanyemployeeToAttach.getCompanyemployeePK());
                attachedCompanyemployeeCollection.add(companyemployeeCollectionCompanyemployeeToAttach);
            }
            companyemployee.setCompanyemployeeCollection(attachedCompanyemployeeCollection);
            Collection<Companyemployee> attachedCompanyemployeeCollection1 = new ArrayList<Companyemployee>();
            for (Companyemployee companyemployeeCollection1CompanyemployeeToAttach : companyemployee.getCompanyemployeeCollection1()) {
                companyemployeeCollection1CompanyemployeeToAttach = em.getReference(companyemployeeCollection1CompanyemployeeToAttach.getClass(), companyemployeeCollection1CompanyemployeeToAttach.getCompanyemployeePK());
                attachedCompanyemployeeCollection1.add(companyemployeeCollection1CompanyemployeeToAttach);
            }
            companyemployee.setCompanyemployeeCollection1(attachedCompanyemployeeCollection1);
            Collection<Companyproducttype> attachedCompanyproducttypeCollection = new ArrayList<Companyproducttype>();
            for (Companyproducttype companyproducttypeCollectionCompanyproducttypeToAttach : companyemployee.getCompanyproducttypeCollection()) {
                companyproducttypeCollectionCompanyproducttypeToAttach = em.getReference(companyproducttypeCollectionCompanyproducttypeToAttach.getClass(), companyproducttypeCollectionCompanyproducttypeToAttach.getCompanyproducttypePK());
                attachedCompanyproducttypeCollection.add(companyproducttypeCollectionCompanyproducttypeToAttach);
            }
            companyemployee.setCompanyproducttypeCollection(attachedCompanyproducttypeCollection);
            Collection<Companyproducttype> attachedCompanyproducttypeCollection1 = new ArrayList<Companyproducttype>();
            for (Companyproducttype companyproducttypeCollection1CompanyproducttypeToAttach : companyemployee.getCompanyproducttypeCollection1()) {
                companyproducttypeCollection1CompanyproducttypeToAttach = em.getReference(companyproducttypeCollection1CompanyproducttypeToAttach.getClass(), companyproducttypeCollection1CompanyproducttypeToAttach.getCompanyproducttypePK());
                attachedCompanyproducttypeCollection1.add(companyproducttypeCollection1CompanyproducttypeToAttach);
            }
            companyemployee.setCompanyproducttypeCollection1(attachedCompanyproducttypeCollection1);
            Collection<Crmcampaign> attachedCrmcampaignCollection = new ArrayList<Crmcampaign>();
            for (Crmcampaign crmcampaignCollectionCrmcampaignToAttach : companyemployee.getCrmcampaignCollection()) {
                crmcampaignCollectionCrmcampaignToAttach = em.getReference(crmcampaignCollectionCrmcampaignToAttach.getClass(), crmcampaignCollectionCrmcampaignToAttach.getCrmcampaignPK());
                attachedCrmcampaignCollection.add(crmcampaignCollectionCrmcampaignToAttach);
            }
            companyemployee.setCrmcampaignCollection(attachedCrmcampaignCollection);
            Collection<Crmcampaign> attachedCrmcampaignCollection1 = new ArrayList<Crmcampaign>();
            for (Crmcampaign crmcampaignCollection1CrmcampaignToAttach : companyemployee.getCrmcampaignCollection1()) {
                crmcampaignCollection1CrmcampaignToAttach = em.getReference(crmcampaignCollection1CrmcampaignToAttach.getClass(), crmcampaignCollection1CrmcampaignToAttach.getCrmcampaignPK());
                attachedCrmcampaignCollection1.add(crmcampaignCollection1CrmcampaignToAttach);
            }
            companyemployee.setCrmcampaignCollection1(attachedCrmcampaignCollection1);
            Collection<Crmmessagehistory> attachedCrmmessagehistoryCollection = new ArrayList<Crmmessagehistory>();
            for (Crmmessagehistory crmmessagehistoryCollectionCrmmessagehistoryToAttach : companyemployee.getCrmmessagehistoryCollection()) {
                crmmessagehistoryCollectionCrmmessagehistoryToAttach = em.getReference(crmmessagehistoryCollectionCrmmessagehistoryToAttach.getClass(), crmmessagehistoryCollectionCrmmessagehistoryToAttach.getCrmmessagehistoryPK());
                attachedCrmmessagehistoryCollection.add(crmmessagehistoryCollectionCrmmessagehistoryToAttach);
            }
            companyemployee.setCrmmessagehistoryCollection(attachedCrmmessagehistoryCollection);
            Collection<Crmmessagehistory> attachedCrmmessagehistoryCollection1 = new ArrayList<Crmmessagehistory>();
            for (Crmmessagehistory crmmessagehistoryCollection1CrmmessagehistoryToAttach : companyemployee.getCrmmessagehistoryCollection1()) {
                crmmessagehistoryCollection1CrmmessagehistoryToAttach = em.getReference(crmmessagehistoryCollection1CrmmessagehistoryToAttach.getClass(), crmmessagehistoryCollection1CrmmessagehistoryToAttach.getCrmmessagehistoryPK());
                attachedCrmmessagehistoryCollection1.add(crmmessagehistoryCollection1CrmmessagehistoryToAttach);
            }
            companyemployee.setCrmmessagehistoryCollection1(attachedCrmmessagehistoryCollection1);
            Collection<Companyaccountstackdocs> attachedCompanyaccountstackdocsCollection = new ArrayList<Companyaccountstackdocs>();
            for (Companyaccountstackdocs companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach : companyemployee.getCompanyaccountstackdocsCollection()) {
                companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach = em.getReference(companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach.getClass(), companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach.getCompanyaccountstackdocsPK());
                attachedCompanyaccountstackdocsCollection.add(companyaccountstackdocsCollectionCompanyaccountstackdocsToAttach);
            }
            companyemployee.setCompanyaccountstackdocsCollection(attachedCompanyaccountstackdocsCollection);
            Collection<Companyaccountstackdocs> attachedCompanyaccountstackdocsCollection1 = new ArrayList<Companyaccountstackdocs>();
            for (Companyaccountstackdocs companyaccountstackdocsCollection1CompanyaccountstackdocsToAttach : companyemployee.getCompanyaccountstackdocsCollection1()) {
                companyaccountstackdocsCollection1CompanyaccountstackdocsToAttach = em.getReference(companyaccountstackdocsCollection1CompanyaccountstackdocsToAttach.getClass(), companyaccountstackdocsCollection1CompanyaccountstackdocsToAttach.getCompanyaccountstackdocsPK());
                attachedCompanyaccountstackdocsCollection1.add(companyaccountstackdocsCollection1CompanyaccountstackdocsToAttach);
            }
            companyemployee.setCompanyaccountstackdocsCollection1(attachedCompanyaccountstackdocsCollection1);
            Collection<Crmlabor> attachedCrmlaborCollection = new ArrayList<Crmlabor>();
            for (Crmlabor crmlaborCollectionCrmlaborToAttach : companyemployee.getCrmlaborCollection()) {
                crmlaborCollectionCrmlaborToAttach = em.getReference(crmlaborCollectionCrmlaborToAttach.getClass(), crmlaborCollectionCrmlaborToAttach.getCrmlaborPK());
                attachedCrmlaborCollection.add(crmlaborCollectionCrmlaborToAttach);
            }
            companyemployee.setCrmlaborCollection(attachedCrmlaborCollection);
            Collection<Crmlabor> attachedCrmlaborCollection1 = new ArrayList<Crmlabor>();
            for (Crmlabor crmlaborCollection1CrmlaborToAttach : companyemployee.getCrmlaborCollection1()) {
                crmlaborCollection1CrmlaborToAttach = em.getReference(crmlaborCollection1CrmlaborToAttach.getClass(), crmlaborCollection1CrmlaborToAttach.getCrmlaborPK());
                attachedCrmlaborCollection1.add(crmlaborCollection1CrmlaborToAttach);
            }
            companyemployee.setCrmlaborCollection1(attachedCrmlaborCollection1);
            Collection<Customerbank> attachedCustomerbankCollection = new ArrayList<Customerbank>();
            for (Customerbank customerbankCollectionCustomerbankToAttach : companyemployee.getCustomerbankCollection()) {
                customerbankCollectionCustomerbankToAttach = em.getReference(customerbankCollectionCustomerbankToAttach.getClass(), customerbankCollectionCustomerbankToAttach.getCustomerbankPK());
                attachedCustomerbankCollection.add(customerbankCollectionCustomerbankToAttach);
            }
            companyemployee.setCustomerbankCollection(attachedCustomerbankCollection);
            Collection<Customerbank> attachedCustomerbankCollection1 = new ArrayList<Customerbank>();
            for (Customerbank customerbankCollection1CustomerbankToAttach : companyemployee.getCustomerbankCollection1()) {
                customerbankCollection1CustomerbankToAttach = em.getReference(customerbankCollection1CustomerbankToAttach.getClass(), customerbankCollection1CustomerbankToAttach.getCustomerbankPK());
                attachedCustomerbankCollection1.add(customerbankCollection1CustomerbankToAttach);
            }
            companyemployee.setCustomerbankCollection1(attachedCustomerbankCollection1);
            Collection<Compnaypaymentcurrency> attachedCompnaypaymentcurrencyCollection = new ArrayList<Compnaypaymentcurrency>();
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionCompnaypaymentcurrencyToAttach : companyemployee.getCompnaypaymentcurrencyCollection()) {
                compnaypaymentcurrencyCollectionCompnaypaymentcurrencyToAttach = em.getReference(compnaypaymentcurrencyCollectionCompnaypaymentcurrencyToAttach.getClass(), compnaypaymentcurrencyCollectionCompnaypaymentcurrencyToAttach.getCompnaypaymentcurrencyPK());
                attachedCompnaypaymentcurrencyCollection.add(compnaypaymentcurrencyCollectionCompnaypaymentcurrencyToAttach);
            }
            companyemployee.setCompnaypaymentcurrencyCollection(attachedCompnaypaymentcurrencyCollection);
            Collection<Compnaypaymentcurrency> attachedCompnaypaymentcurrencyCollection1 = new ArrayList<Compnaypaymentcurrency>();
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollection1CompnaypaymentcurrencyToAttach : companyemployee.getCompnaypaymentcurrencyCollection1()) {
                compnaypaymentcurrencyCollection1CompnaypaymentcurrencyToAttach = em.getReference(compnaypaymentcurrencyCollection1CompnaypaymentcurrencyToAttach.getClass(), compnaypaymentcurrencyCollection1CompnaypaymentcurrencyToAttach.getCompnaypaymentcurrencyPK());
                attachedCompnaypaymentcurrencyCollection1.add(compnaypaymentcurrencyCollection1CompnaypaymentcurrencyToAttach);
            }
            companyemployee.setCompnaypaymentcurrencyCollection1(attachedCompnaypaymentcurrencyCollection1);
            Collection<Crmproject> attachedCrmprojectCollection = new ArrayList<Crmproject>();
            for (Crmproject crmprojectCollectionCrmprojectToAttach : companyemployee.getCrmprojectCollection()) {
                crmprojectCollectionCrmprojectToAttach = em.getReference(crmprojectCollectionCrmprojectToAttach.getClass(), crmprojectCollectionCrmprojectToAttach.getCrmprojectPK());
                attachedCrmprojectCollection.add(crmprojectCollectionCrmprojectToAttach);
            }
            companyemployee.setCrmprojectCollection(attachedCrmprojectCollection);
            Collection<Crmproject> attachedCrmprojectCollection1 = new ArrayList<Crmproject>();
            for (Crmproject crmprojectCollection1CrmprojectToAttach : companyemployee.getCrmprojectCollection1()) {
                crmprojectCollection1CrmprojectToAttach = em.getReference(crmprojectCollection1CrmprojectToAttach.getClass(), crmprojectCollection1CrmprojectToAttach.getCrmprojectPK());
                attachedCrmprojectCollection1.add(crmprojectCollection1CrmprojectToAttach);
            }
            companyemployee.setCrmprojectCollection1(attachedCrmprojectCollection1);
            Collection<Crmproject> attachedCrmprojectCollection2 = new ArrayList<Crmproject>();
            for (Crmproject crmprojectCollection2CrmprojectToAttach : companyemployee.getCrmprojectCollection2()) {
                crmprojectCollection2CrmprojectToAttach = em.getReference(crmprojectCollection2CrmprojectToAttach.getClass(), crmprojectCollection2CrmprojectToAttach.getCrmprojectPK());
                attachedCrmprojectCollection2.add(crmprojectCollection2CrmprojectToAttach);
            }
            companyemployee.setCrmprojectCollection2(attachedCrmprojectCollection2);
            Collection<Companyemployeeaddress> attachedCompanyemployeeaddressCollection = new ArrayList<Companyemployeeaddress>();
            for (Companyemployeeaddress companyemployeeaddressCollectionCompanyemployeeaddressToAttach : companyemployee.getCompanyemployeeaddressCollection()) {
                companyemployeeaddressCollectionCompanyemployeeaddressToAttach = em.getReference(companyemployeeaddressCollectionCompanyemployeeaddressToAttach.getClass(), companyemployeeaddressCollectionCompanyemployeeaddressToAttach.getCompanyemployeeaddressPK());
                attachedCompanyemployeeaddressCollection.add(companyemployeeaddressCollectionCompanyemployeeaddressToAttach);
            }
            companyemployee.setCompanyemployeeaddressCollection(attachedCompanyemployeeaddressCollection);
            Collection<Companyemployeeaddress> attachedCompanyemployeeaddressCollection1 = new ArrayList<Companyemployeeaddress>();
            for (Companyemployeeaddress companyemployeeaddressCollection1CompanyemployeeaddressToAttach : companyemployee.getCompanyemployeeaddressCollection1()) {
                companyemployeeaddressCollection1CompanyemployeeaddressToAttach = em.getReference(companyemployeeaddressCollection1CompanyemployeeaddressToAttach.getClass(), companyemployeeaddressCollection1CompanyemployeeaddressToAttach.getCompanyemployeeaddressPK());
                attachedCompanyemployeeaddressCollection1.add(companyemployeeaddressCollection1CompanyemployeeaddressToAttach);
            }
            companyemployee.setCompanyemployeeaddressCollection1(attachedCompanyemployeeaddressCollection1);
            Collection<Companyemployeeaddress> attachedCompanyemployeeaddressCollection2 = new ArrayList<Companyemployeeaddress>();
            for (Companyemployeeaddress companyemployeeaddressCollection2CompanyemployeeaddressToAttach : companyemployee.getCompanyemployeeaddressCollection2()) {
                companyemployeeaddressCollection2CompanyemployeeaddressToAttach = em.getReference(companyemployeeaddressCollection2CompanyemployeeaddressToAttach.getClass(), companyemployeeaddressCollection2CompanyemployeeaddressToAttach.getCompanyemployeeaddressPK());
                attachedCompanyemployeeaddressCollection2.add(companyemployeeaddressCollection2CompanyemployeeaddressToAttach);
            }
            companyemployee.setCompanyemployeeaddressCollection2(attachedCompanyemployeeaddressCollection2);
            Collection<Crmforumdocs> attachedCrmforumdocsCollection = new ArrayList<Crmforumdocs>();
            for (Crmforumdocs crmforumdocsCollectionCrmforumdocsToAttach : companyemployee.getCrmforumdocsCollection()) {
                crmforumdocsCollectionCrmforumdocsToAttach = em.getReference(crmforumdocsCollectionCrmforumdocsToAttach.getClass(), crmforumdocsCollectionCrmforumdocsToAttach.getCrmforumdocsPK());
                attachedCrmforumdocsCollection.add(crmforumdocsCollectionCrmforumdocsToAttach);
            }
            companyemployee.setCrmforumdocsCollection(attachedCrmforumdocsCollection);
            Collection<Companyproduct> attachedCompanyproductCollection = new ArrayList<Companyproduct>();
            for (Companyproduct companyproductCollectionCompanyproductToAttach : companyemployee.getCompanyproductCollection()) {
                companyproductCollectionCompanyproductToAttach = em.getReference(companyproductCollectionCompanyproductToAttach.getClass(), companyproductCollectionCompanyproductToAttach.getCompanyproductPK());
                attachedCompanyproductCollection.add(companyproductCollectionCompanyproductToAttach);
            }
            companyemployee.setCompanyproductCollection(attachedCompanyproductCollection);
            Collection<Companyproduct> attachedCompanyproductCollection1 = new ArrayList<Companyproduct>();
            for (Companyproduct companyproductCollection1CompanyproductToAttach : companyemployee.getCompanyproductCollection1()) {
                companyproductCollection1CompanyproductToAttach = em.getReference(companyproductCollection1CompanyproductToAttach.getClass(), companyproductCollection1CompanyproductToAttach.getCompanyproductPK());
                attachedCompanyproductCollection1.add(companyproductCollection1CompanyproductToAttach);
            }
            companyemployee.setCompanyproductCollection1(attachedCompanyproductCollection1);
            Collection<Employeedesignation> attachedEmployeedesignationCollection = new ArrayList<Employeedesignation>();
            for (Employeedesignation employeedesignationCollectionEmployeedesignationToAttach : companyemployee.getEmployeedesignationCollection()) {
                employeedesignationCollectionEmployeedesignationToAttach = em.getReference(employeedesignationCollectionEmployeedesignationToAttach.getClass(), employeedesignationCollectionEmployeedesignationToAttach.getEmployeedesignationPK());
                attachedEmployeedesignationCollection.add(employeedesignationCollectionEmployeedesignationToAttach);
            }
            companyemployee.setEmployeedesignationCollection(attachedEmployeedesignationCollection);
            Collection<Employeedesignation> attachedEmployeedesignationCollection1 = new ArrayList<Employeedesignation>();
            for (Employeedesignation employeedesignationCollection1EmployeedesignationToAttach : companyemployee.getEmployeedesignationCollection1()) {
                employeedesignationCollection1EmployeedesignationToAttach = em.getReference(employeedesignationCollection1EmployeedesignationToAttach.getClass(), employeedesignationCollection1EmployeedesignationToAttach.getEmployeedesignationPK());
                attachedEmployeedesignationCollection1.add(employeedesignationCollection1EmployeedesignationToAttach);
            }
            companyemployee.setEmployeedesignationCollection1(attachedEmployeedesignationCollection1);
            Collection<Companyaccountstack> attachedCompanyaccountstackCollection = new ArrayList<Companyaccountstack>();
            for (Companyaccountstack companyaccountstackCollectionCompanyaccountstackToAttach : companyemployee.getCompanyaccountstackCollection()) {
                companyaccountstackCollectionCompanyaccountstackToAttach = em.getReference(companyaccountstackCollectionCompanyaccountstackToAttach.getClass(), companyaccountstackCollectionCompanyaccountstackToAttach.getCompanyaccountstackPK());
                attachedCompanyaccountstackCollection.add(companyaccountstackCollectionCompanyaccountstackToAttach);
            }
            companyemployee.setCompanyaccountstackCollection(attachedCompanyaccountstackCollection);
            Collection<Companyaccountstack> attachedCompanyaccountstackCollection1 = new ArrayList<Companyaccountstack>();
            for (Companyaccountstack companyaccountstackCollection1CompanyaccountstackToAttach : companyemployee.getCompanyaccountstackCollection1()) {
                companyaccountstackCollection1CompanyaccountstackToAttach = em.getReference(companyaccountstackCollection1CompanyaccountstackToAttach.getClass(), companyaccountstackCollection1CompanyaccountstackToAttach.getCompanyaccountstackPK());
                attachedCompanyaccountstackCollection1.add(companyaccountstackCollection1CompanyaccountstackToAttach);
            }
            companyemployee.setCompanyaccountstackCollection1(attachedCompanyaccountstackCollection1);
            Collection<Crmprojectteammembers> attachedCrmprojectteammembersCollection = new ArrayList<Crmprojectteammembers>();
            for (Crmprojectteammembers crmprojectteammembersCollectionCrmprojectteammembersToAttach : companyemployee.getCrmprojectteammembersCollection()) {
                crmprojectteammembersCollectionCrmprojectteammembersToAttach = em.getReference(crmprojectteammembersCollectionCrmprojectteammembersToAttach.getClass(), crmprojectteammembersCollectionCrmprojectteammembersToAttach.getCrmprojectteammembersPK());
                attachedCrmprojectteammembersCollection.add(crmprojectteammembersCollectionCrmprojectteammembersToAttach);
            }
            companyemployee.setCrmprojectteammembersCollection(attachedCrmprojectteammembersCollection);
            Collection<Crmprojectteammembers> attachedCrmprojectteammembersCollection1 = new ArrayList<Crmprojectteammembers>();
            for (Crmprojectteammembers crmprojectteammembersCollection1CrmprojectteammembersToAttach : companyemployee.getCrmprojectteammembersCollection1()) {
                crmprojectteammembersCollection1CrmprojectteammembersToAttach = em.getReference(crmprojectteammembersCollection1CrmprojectteammembersToAttach.getClass(), crmprojectteammembersCollection1CrmprojectteammembersToAttach.getCrmprojectteammembersPK());
                attachedCrmprojectteammembersCollection1.add(crmprojectteammembersCollection1CrmprojectteammembersToAttach);
            }
            companyemployee.setCrmprojectteammembersCollection1(attachedCrmprojectteammembersCollection1);
            Collection<Crmprojectteammembers> attachedCrmprojectteammembersCollection2 = new ArrayList<Crmprojectteammembers>();
            for (Crmprojectteammembers crmprojectteammembersCollection2CrmprojectteammembersToAttach : companyemployee.getCrmprojectteammembersCollection2()) {
                crmprojectteammembersCollection2CrmprojectteammembersToAttach = em.getReference(crmprojectteammembersCollection2CrmprojectteammembersToAttach.getClass(), crmprojectteammembersCollection2CrmprojectteammembersToAttach.getCrmprojectteammembersPK());
                attachedCrmprojectteammembersCollection2.add(crmprojectteammembersCollection2CrmprojectteammembersToAttach);
            }
            companyemployee.setCrmprojectteammembersCollection2(attachedCrmprojectteammembersCollection2);
            Collection<Componentattachments> attachedComponentattachmentsCollection = new ArrayList<Componentattachments>();
            for (Componentattachments componentattachmentsCollectionComponentattachmentsToAttach : companyemployee.getComponentattachmentsCollection()) {
                componentattachmentsCollectionComponentattachmentsToAttach = em.getReference(componentattachmentsCollectionComponentattachmentsToAttach.getClass(), componentattachmentsCollectionComponentattachmentsToAttach.getComponentattachmentsPK());
                attachedComponentattachmentsCollection.add(componentattachmentsCollectionComponentattachmentsToAttach);
            }
            companyemployee.setComponentattachmentsCollection(attachedComponentattachmentsCollection);
            Collection<Componentattachments> attachedComponentattachmentsCollection1 = new ArrayList<Componentattachments>();
            for (Componentattachments componentattachmentsCollection1ComponentattachmentsToAttach : companyemployee.getComponentattachmentsCollection1()) {
                componentattachmentsCollection1ComponentattachmentsToAttach = em.getReference(componentattachmentsCollection1ComponentattachmentsToAttach.getClass(), componentattachmentsCollection1ComponentattachmentsToAttach.getComponentattachmentsPK());
                attachedComponentattachmentsCollection1.add(componentattachmentsCollection1ComponentattachmentsToAttach);
            }
            companyemployee.setComponentattachmentsCollection1(attachedComponentattachmentsCollection1);
            Collection<Crmvisitorcontactsaddress> attachedCrmvisitorcontactsaddressCollection = new ArrayList<Crmvisitorcontactsaddress>();
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach : companyemployee.getCrmvisitorcontactsaddressCollection()) {
                crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach = em.getReference(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach.getClass(), crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach.getCrmvisitorcontactsaddressPK());
                attachedCrmvisitorcontactsaddressCollection.add(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddressToAttach);
            }
            companyemployee.setCrmvisitorcontactsaddressCollection(attachedCrmvisitorcontactsaddressCollection);
            Collection<Crmvisitorcontacts> attachedCrmvisitorcontactsCollection = new ArrayList<Crmvisitorcontacts>();
            for (Crmvisitorcontacts crmvisitorcontactsCollectionCrmvisitorcontactsToAttach : companyemployee.getCrmvisitorcontactsCollection()) {
                crmvisitorcontactsCollectionCrmvisitorcontactsToAttach = em.getReference(crmvisitorcontactsCollectionCrmvisitorcontactsToAttach.getClass(), crmvisitorcontactsCollectionCrmvisitorcontactsToAttach.getCrmvisitorcontactsPK());
                attachedCrmvisitorcontactsCollection.add(crmvisitorcontactsCollectionCrmvisitorcontactsToAttach);
            }
            companyemployee.setCrmvisitorcontactsCollection(attachedCrmvisitorcontactsCollection);
            Collection<Crmvisitorcontacts> attachedCrmvisitorcontactsCollection1 = new ArrayList<Crmvisitorcontacts>();
            for (Crmvisitorcontacts crmvisitorcontactsCollection1CrmvisitorcontactsToAttach : companyemployee.getCrmvisitorcontactsCollection1()) {
                crmvisitorcontactsCollection1CrmvisitorcontactsToAttach = em.getReference(crmvisitorcontactsCollection1CrmvisitorcontactsToAttach.getClass(), crmvisitorcontactsCollection1CrmvisitorcontactsToAttach.getCrmvisitorcontactsPK());
                attachedCrmvisitorcontactsCollection1.add(crmvisitorcontactsCollection1CrmvisitorcontactsToAttach);
            }
            companyemployee.setCrmvisitorcontactsCollection1(attachedCrmvisitorcontactsCollection1);
            Collection<Companyaccountstackcd> attachedCompanyaccountstackcdCollection = new ArrayList<Companyaccountstackcd>();
            for (Companyaccountstackcd companyaccountstackcdCollectionCompanyaccountstackcdToAttach : companyemployee.getCompanyaccountstackcdCollection()) {
                companyaccountstackcdCollectionCompanyaccountstackcdToAttach = em.getReference(companyaccountstackcdCollectionCompanyaccountstackcdToAttach.getClass(), companyaccountstackcdCollectionCompanyaccountstackcdToAttach.getCompanyaccountstackcdPK());
                attachedCompanyaccountstackcdCollection.add(companyaccountstackcdCollectionCompanyaccountstackcdToAttach);
            }
            companyemployee.setCompanyaccountstackcdCollection(attachedCompanyaccountstackcdCollection);
            Collection<Companyaccountstackcd> attachedCompanyaccountstackcdCollection1 = new ArrayList<Companyaccountstackcd>();
            for (Companyaccountstackcd companyaccountstackcdCollection1CompanyaccountstackcdToAttach : companyemployee.getCompanyaccountstackcdCollection1()) {
                companyaccountstackcdCollection1CompanyaccountstackcdToAttach = em.getReference(companyaccountstackcdCollection1CompanyaccountstackcdToAttach.getClass(), companyaccountstackcdCollection1CompanyaccountstackcdToAttach.getCompanyaccountstackcdPK());
                attachedCompanyaccountstackcdCollection1.add(companyaccountstackcdCollection1CompanyaccountstackcdToAttach);
            }
            companyemployee.setCompanyaccountstackcdCollection1(attachedCompanyaccountstackcdCollection1);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollection = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionCrmprojectticketmanagementToAttach : companyemployee.getCrmprojectticketmanagementCollection()) {
                crmprojectticketmanagementCollectionCrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollectionCrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollectionCrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollection.add(crmprojectticketmanagementCollectionCrmprojectticketmanagementToAttach);
            }
            companyemployee.setCrmprojectticketmanagementCollection(attachedCrmprojectticketmanagementCollection);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollection1 = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1CrmprojectticketmanagementToAttach : companyemployee.getCrmprojectticketmanagementCollection1()) {
                crmprojectticketmanagementCollection1CrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollection1CrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollection1CrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollection1.add(crmprojectticketmanagementCollection1CrmprojectticketmanagementToAttach);
            }
            companyemployee.setCrmprojectticketmanagementCollection1(attachedCrmprojectticketmanagementCollection1);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollection2 = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection2CrmprojectticketmanagementToAttach : companyemployee.getCrmprojectticketmanagementCollection2()) {
                crmprojectticketmanagementCollection2CrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollection2CrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollection2CrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollection2.add(crmprojectticketmanagementCollection2CrmprojectticketmanagementToAttach);
            }
            companyemployee.setCrmprojectticketmanagementCollection2(attachedCrmprojectticketmanagementCollection2);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollection3 = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection3CrmprojectticketmanagementToAttach : companyemployee.getCrmprojectticketmanagementCollection3()) {
                crmprojectticketmanagementCollection3CrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollection3CrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollection3CrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollection3.add(crmprojectticketmanagementCollection3CrmprojectticketmanagementToAttach);
            }
            companyemployee.setCrmprojectticketmanagementCollection3(attachedCrmprojectticketmanagementCollection3);
            Collection<Crmprojectticketnotification> attachedCrmprojectticketnotificationCollection = new ArrayList<Crmprojectticketnotification>();
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionCrmprojectticketnotificationToAttach : companyemployee.getCrmprojectticketnotificationCollection()) {
                crmprojectticketnotificationCollectionCrmprojectticketnotificationToAttach = em.getReference(crmprojectticketnotificationCollectionCrmprojectticketnotificationToAttach.getClass(), crmprojectticketnotificationCollectionCrmprojectticketnotificationToAttach.getCrmprojectticketnotificationPK());
                attachedCrmprojectticketnotificationCollection.add(crmprojectticketnotificationCollectionCrmprojectticketnotificationToAttach);
            }
            companyemployee.setCrmprojectticketnotificationCollection(attachedCrmprojectticketnotificationCollection);
            Collection<Crmprojectticketnotification> attachedCrmprojectticketnotificationCollection1 = new ArrayList<Crmprojectticketnotification>();
            for (Crmprojectticketnotification crmprojectticketnotificationCollection1CrmprojectticketnotificationToAttach : companyemployee.getCrmprojectticketnotificationCollection1()) {
                crmprojectticketnotificationCollection1CrmprojectticketnotificationToAttach = em.getReference(crmprojectticketnotificationCollection1CrmprojectticketnotificationToAttach.getClass(), crmprojectticketnotificationCollection1CrmprojectticketnotificationToAttach.getCrmprojectticketnotificationPK());
                attachedCrmprojectticketnotificationCollection1.add(crmprojectticketnotificationCollection1CrmprojectticketnotificationToAttach);
            }
            companyemployee.setCrmprojectticketnotificationCollection1(attachedCrmprojectticketnotificationCollection1);
            Collection<Crmprojectticketnotification> attachedCrmprojectticketnotificationCollection2 = new ArrayList<Crmprojectticketnotification>();
            for (Crmprojectticketnotification crmprojectticketnotificationCollection2CrmprojectticketnotificationToAttach : companyemployee.getCrmprojectticketnotificationCollection2()) {
                crmprojectticketnotificationCollection2CrmprojectticketnotificationToAttach = em.getReference(crmprojectticketnotificationCollection2CrmprojectticketnotificationToAttach.getClass(), crmprojectticketnotificationCollection2CrmprojectticketnotificationToAttach.getCrmprojectticketnotificationPK());
                attachedCrmprojectticketnotificationCollection2.add(crmprojectticketnotificationCollection2CrmprojectticketnotificationToAttach);
            }
            companyemployee.setCrmprojectticketnotificationCollection2(attachedCrmprojectticketnotificationCollection2);
            Collection<Crmvisitor> attachedCrmvisitorCollection = new ArrayList<Crmvisitor>();
            for (Crmvisitor crmvisitorCollectionCrmvisitorToAttach : companyemployee.getCrmvisitorCollection()) {
                crmvisitorCollectionCrmvisitorToAttach = em.getReference(crmvisitorCollectionCrmvisitorToAttach.getClass(), crmvisitorCollectionCrmvisitorToAttach.getCrmvisitorPK());
                attachedCrmvisitorCollection.add(crmvisitorCollectionCrmvisitorToAttach);
            }
            companyemployee.setCrmvisitorCollection(attachedCrmvisitorCollection);
            Collection<Crmvisitor> attachedCrmvisitorCollection1 = new ArrayList<Crmvisitor>();
            for (Crmvisitor crmvisitorCollection1CrmvisitorToAttach : companyemployee.getCrmvisitorCollection1()) {
                crmvisitorCollection1CrmvisitorToAttach = em.getReference(crmvisitorCollection1CrmvisitorToAttach.getClass(), crmvisitorCollection1CrmvisitorToAttach.getCrmvisitorPK());
                attachedCrmvisitorCollection1.add(crmvisitorCollection1CrmvisitorToAttach);
            }
            companyemployee.setCrmvisitorCollection1(attachedCrmvisitorCollection1);
            Collection<Crmworkordertype> attachedCrmworkordertypeCollection = new ArrayList<Crmworkordertype>();
            for (Crmworkordertype crmworkordertypeCollectionCrmworkordertypeToAttach : companyemployee.getCrmworkordertypeCollection()) {
                crmworkordertypeCollectionCrmworkordertypeToAttach = em.getReference(crmworkordertypeCollectionCrmworkordertypeToAttach.getClass(), crmworkordertypeCollectionCrmworkordertypeToAttach.getCrmworkordertypePK());
                attachedCrmworkordertypeCollection.add(crmworkordertypeCollectionCrmworkordertypeToAttach);
            }
            companyemployee.setCrmworkordertypeCollection(attachedCrmworkordertypeCollection);
            Collection<Crmworkordertype> attachedCrmworkordertypeCollection1 = new ArrayList<Crmworkordertype>();
            for (Crmworkordertype crmworkordertypeCollection1CrmworkordertypeToAttach : companyemployee.getCrmworkordertypeCollection1()) {
                crmworkordertypeCollection1CrmworkordertypeToAttach = em.getReference(crmworkordertypeCollection1CrmworkordertypeToAttach.getClass(), crmworkordertypeCollection1CrmworkordertypeToAttach.getCrmworkordertypePK());
                attachedCrmworkordertypeCollection1.add(crmworkordertypeCollection1CrmworkordertypeToAttach);
            }
            companyemployee.setCrmworkordertypeCollection1(attachedCrmworkordertypeCollection1);
            Collection<Crmlabortype> attachedCrmlabortypeCollection = new ArrayList<Crmlabortype>();
            for (Crmlabortype crmlabortypeCollectionCrmlabortypeToAttach : companyemployee.getCrmlabortypeCollection()) {
                crmlabortypeCollectionCrmlabortypeToAttach = em.getReference(crmlabortypeCollectionCrmlabortypeToAttach.getClass(), crmlabortypeCollectionCrmlabortypeToAttach.getCrmlabortypePK());
                attachedCrmlabortypeCollection.add(crmlabortypeCollectionCrmlabortypeToAttach);
            }
            companyemployee.setCrmlabortypeCollection(attachedCrmlabortypeCollection);
            Collection<Crmlabortype> attachedCrmlabortypeCollection1 = new ArrayList<Crmlabortype>();
            for (Crmlabortype crmlabortypeCollection1CrmlabortypeToAttach : companyemployee.getCrmlabortypeCollection1()) {
                crmlabortypeCollection1CrmlabortypeToAttach = em.getReference(crmlabortypeCollection1CrmlabortypeToAttach.getClass(), crmlabortypeCollection1CrmlabortypeToAttach.getCrmlabortypePK());
                attachedCrmlabortypeCollection1.add(crmlabortypeCollection1CrmlabortypeToAttach);
            }
            companyemployee.setCrmlabortypeCollection1(attachedCrmlabortypeCollection1);
            Collection<Crmbillingtype> attachedCrmbillingtypeCollection = new ArrayList<Crmbillingtype>();
            for (Crmbillingtype crmbillingtypeCollectionCrmbillingtypeToAttach : companyemployee.getCrmbillingtypeCollection()) {
                crmbillingtypeCollectionCrmbillingtypeToAttach = em.getReference(crmbillingtypeCollectionCrmbillingtypeToAttach.getClass(), crmbillingtypeCollectionCrmbillingtypeToAttach.getCrmbillingtypePK());
                attachedCrmbillingtypeCollection.add(crmbillingtypeCollectionCrmbillingtypeToAttach);
            }
            companyemployee.setCrmbillingtypeCollection(attachedCrmbillingtypeCollection);
            Collection<Crmbillingtype> attachedCrmbillingtypeCollection1 = new ArrayList<Crmbillingtype>();
            for (Crmbillingtype crmbillingtypeCollection1CrmbillingtypeToAttach : companyemployee.getCrmbillingtypeCollection1()) {
                crmbillingtypeCollection1CrmbillingtypeToAttach = em.getReference(crmbillingtypeCollection1CrmbillingtypeToAttach.getClass(), crmbillingtypeCollection1CrmbillingtypeToAttach.getCrmbillingtypePK());
                attachedCrmbillingtypeCollection1.add(crmbillingtypeCollection1CrmbillingtypeToAttach);
            }
            companyemployee.setCrmbillingtypeCollection1(attachedCrmbillingtypeCollection1);
            Collection<Companyproductcategory> attachedCompanyproductcategoryCollection = new ArrayList<Companyproductcategory>();
            for (Companyproductcategory companyproductcategoryCollectionCompanyproductcategoryToAttach : companyemployee.getCompanyproductcategoryCollection()) {
                companyproductcategoryCollectionCompanyproductcategoryToAttach = em.getReference(companyproductcategoryCollectionCompanyproductcategoryToAttach.getClass(), companyproductcategoryCollectionCompanyproductcategoryToAttach.getCompanyproductcategoryPK());
                attachedCompanyproductcategoryCollection.add(companyproductcategoryCollectionCompanyproductcategoryToAttach);
            }
            companyemployee.setCompanyproductcategoryCollection(attachedCompanyproductcategoryCollection);
            Collection<Companyproductcategory> attachedCompanyproductcategoryCollection1 = new ArrayList<Companyproductcategory>();
            for (Companyproductcategory companyproductcategoryCollection1CompanyproductcategoryToAttach : companyemployee.getCompanyproductcategoryCollection1()) {
                companyproductcategoryCollection1CompanyproductcategoryToAttach = em.getReference(companyproductcategoryCollection1CompanyproductcategoryToAttach.getClass(), companyproductcategoryCollection1CompanyproductcategoryToAttach.getCompanyproductcategoryPK());
                attachedCompanyproductcategoryCollection1.add(companyproductcategoryCollection1CompanyproductcategoryToAttach);
            }
            companyemployee.setCompanyproductcategoryCollection1(attachedCompanyproductcategoryCollection1);
            Collection<Crmprojecttask> attachedCrmprojecttaskCollection = new ArrayList<Crmprojecttask>();
            for (Crmprojecttask crmprojecttaskCollectionCrmprojecttaskToAttach : companyemployee.getCrmprojecttaskCollection()) {
                crmprojecttaskCollectionCrmprojecttaskToAttach = em.getReference(crmprojecttaskCollectionCrmprojecttaskToAttach.getClass(), crmprojecttaskCollectionCrmprojecttaskToAttach.getCrmprojecttaskPK());
                attachedCrmprojecttaskCollection.add(crmprojecttaskCollectionCrmprojecttaskToAttach);
            }
            companyemployee.setCrmprojecttaskCollection(attachedCrmprojecttaskCollection);
            Collection<Crmprojecttask> attachedCrmprojecttaskCollection1 = new ArrayList<Crmprojecttask>();
            for (Crmprojecttask crmprojecttaskCollection1CrmprojecttaskToAttach : companyemployee.getCrmprojecttaskCollection1()) {
                crmprojecttaskCollection1CrmprojecttaskToAttach = em.getReference(crmprojecttaskCollection1CrmprojecttaskToAttach.getClass(), crmprojecttaskCollection1CrmprojecttaskToAttach.getCrmprojecttaskPK());
                attachedCrmprojecttaskCollection1.add(crmprojecttaskCollection1CrmprojecttaskToAttach);
            }
            companyemployee.setCrmprojecttaskCollection1(attachedCrmprojecttaskCollection1);
            Collection<Crmcampaignprops> attachedCrmcampaignpropsCollection = new ArrayList<Crmcampaignprops>();
            for (Crmcampaignprops crmcampaignpropsCollectionCrmcampaignpropsToAttach : companyemployee.getCrmcampaignpropsCollection()) {
                crmcampaignpropsCollectionCrmcampaignpropsToAttach = em.getReference(crmcampaignpropsCollectionCrmcampaignpropsToAttach.getClass(), crmcampaignpropsCollectionCrmcampaignpropsToAttach.getCrmcampaignpropsPK());
                attachedCrmcampaignpropsCollection.add(crmcampaignpropsCollectionCrmcampaignpropsToAttach);
            }
            companyemployee.setCrmcampaignpropsCollection(attachedCrmcampaignpropsCollection);
            Collection<Crmcampaignprops> attachedCrmcampaignpropsCollection1 = new ArrayList<Crmcampaignprops>();
            for (Crmcampaignprops crmcampaignpropsCollection1CrmcampaignpropsToAttach : companyemployee.getCrmcampaignpropsCollection1()) {
                crmcampaignpropsCollection1CrmcampaignpropsToAttach = em.getReference(crmcampaignpropsCollection1CrmcampaignpropsToAttach.getClass(), crmcampaignpropsCollection1CrmcampaignpropsToAttach.getCrmcampaignpropsPK());
                attachedCrmcampaignpropsCollection1.add(crmcampaignpropsCollection1CrmcampaignpropsToAttach);
            }
            companyemployee.setCrmcampaignpropsCollection1(attachedCrmcampaignpropsCollection1);
            Collection<Customercontactsaddress> attachedCustomercontactsaddressCollection = new ArrayList<Customercontactsaddress>();
            for (Customercontactsaddress customercontactsaddressCollectionCustomercontactsaddressToAttach : companyemployee.getCustomercontactsaddressCollection()) {
                customercontactsaddressCollectionCustomercontactsaddressToAttach = em.getReference(customercontactsaddressCollectionCustomercontactsaddressToAttach.getClass(), customercontactsaddressCollectionCustomercontactsaddressToAttach.getCustomercontactsaddressPK());
                attachedCustomercontactsaddressCollection.add(customercontactsaddressCollectionCustomercontactsaddressToAttach);
            }
            companyemployee.setCustomercontactsaddressCollection(attachedCustomercontactsaddressCollection);
            Collection<Customercontactsaddress> attachedCustomercontactsaddressCollection1 = new ArrayList<Customercontactsaddress>();
            for (Customercontactsaddress customercontactsaddressCollection1CustomercontactsaddressToAttach : companyemployee.getCustomercontactsaddressCollection1()) {
                customercontactsaddressCollection1CustomercontactsaddressToAttach = em.getReference(customercontactsaddressCollection1CustomercontactsaddressToAttach.getClass(), customercontactsaddressCollection1CustomercontactsaddressToAttach.getCustomercontactsaddressPK());
                attachedCustomercontactsaddressCollection1.add(customercontactsaddressCollection1CustomercontactsaddressToAttach);
            }
            companyemployee.setCustomercontactsaddressCollection1(attachedCustomercontactsaddressCollection1);
            Collection<Customercategory> attachedCustomercategoryCollection = new ArrayList<Customercategory>();
            for (Customercategory customercategoryCollectionCustomercategoryToAttach : companyemployee.getCustomercategoryCollection()) {
                customercategoryCollectionCustomercategoryToAttach = em.getReference(customercategoryCollectionCustomercategoryToAttach.getClass(), customercategoryCollectionCustomercategoryToAttach.getCustomercategoryPK());
                attachedCustomercategoryCollection.add(customercategoryCollectionCustomercategoryToAttach);
            }
            companyemployee.setCustomercategoryCollection(attachedCustomercategoryCollection);
            Collection<Customercategory> attachedCustomercategoryCollection1 = new ArrayList<Customercategory>();
            for (Customercategory customercategoryCollection1CustomercategoryToAttach : companyemployee.getCustomercategoryCollection1()) {
                customercategoryCollection1CustomercategoryToAttach = em.getReference(customercategoryCollection1CustomercategoryToAttach.getClass(), customercategoryCollection1CustomercategoryToAttach.getCustomercategoryPK());
                attachedCustomercategoryCollection1.add(customercategoryCollection1CustomercategoryToAttach);
            }
            companyemployee.setCustomercategoryCollection1(attachedCustomercategoryCollection1);
            Collection<Productcomponents> attachedProductcomponentsCollection = new ArrayList<Productcomponents>();
            for (Productcomponents productcomponentsCollectionProductcomponentsToAttach : companyemployee.getProductcomponentsCollection()) {
                productcomponentsCollectionProductcomponentsToAttach = em.getReference(productcomponentsCollectionProductcomponentsToAttach.getClass(), productcomponentsCollectionProductcomponentsToAttach.getProductcomponentsPK());
                attachedProductcomponentsCollection.add(productcomponentsCollectionProductcomponentsToAttach);
            }
            companyemployee.setProductcomponentsCollection(attachedProductcomponentsCollection);
            Collection<Productcomponents> attachedProductcomponentsCollection1 = new ArrayList<Productcomponents>();
            for (Productcomponents productcomponentsCollection1ProductcomponentsToAttach : companyemployee.getProductcomponentsCollection1()) {
                productcomponentsCollection1ProductcomponentsToAttach = em.getReference(productcomponentsCollection1ProductcomponentsToAttach.getClass(), productcomponentsCollection1ProductcomponentsToAttach.getProductcomponentsPK());
                attachedProductcomponentsCollection1.add(productcomponentsCollection1ProductcomponentsToAttach);
            }
            companyemployee.setProductcomponentsCollection1(attachedProductcomponentsCollection1);
            Collection<Crmexpensetype> attachedCrmexpensetypeCollection = new ArrayList<Crmexpensetype>();
            for (Crmexpensetype crmexpensetypeCollectionCrmexpensetypeToAttach : companyemployee.getCrmexpensetypeCollection()) {
                crmexpensetypeCollectionCrmexpensetypeToAttach = em.getReference(crmexpensetypeCollectionCrmexpensetypeToAttach.getClass(), crmexpensetypeCollectionCrmexpensetypeToAttach.getCrmexpensetypePK());
                attachedCrmexpensetypeCollection.add(crmexpensetypeCollectionCrmexpensetypeToAttach);
            }
            companyemployee.setCrmexpensetypeCollection(attachedCrmexpensetypeCollection);
            Collection<Crmexpensetype> attachedCrmexpensetypeCollection1 = new ArrayList<Crmexpensetype>();
            for (Crmexpensetype crmexpensetypeCollection1CrmexpensetypeToAttach : companyemployee.getCrmexpensetypeCollection1()) {
                crmexpensetypeCollection1CrmexpensetypeToAttach = em.getReference(crmexpensetypeCollection1CrmexpensetypeToAttach.getClass(), crmexpensetypeCollection1CrmexpensetypeToAttach.getCrmexpensetypePK());
                attachedCrmexpensetypeCollection1.add(crmexpensetypeCollection1CrmexpensetypeToAttach);
            }
            companyemployee.setCrmexpensetypeCollection1(attachedCrmexpensetypeCollection1);
            Collection<Companypayments> attachedCompanypaymentsCollection = new ArrayList<Companypayments>();
            for (Companypayments companypaymentsCollectionCompanypaymentsToAttach : companyemployee.getCompanypaymentsCollection()) {
                companypaymentsCollectionCompanypaymentsToAttach = em.getReference(companypaymentsCollectionCompanypaymentsToAttach.getClass(), companypaymentsCollectionCompanypaymentsToAttach.getCompanypaymentsPK());
                attachedCompanypaymentsCollection.add(companypaymentsCollectionCompanypaymentsToAttach);
            }
            companyemployee.setCompanypaymentsCollection(attachedCompanypaymentsCollection);
            Collection<Companypayments> attachedCompanypaymentsCollection1 = new ArrayList<Companypayments>();
            for (Companypayments companypaymentsCollection1CompanypaymentsToAttach : companyemployee.getCompanypaymentsCollection1()) {
                companypaymentsCollection1CompanypaymentsToAttach = em.getReference(companypaymentsCollection1CompanypaymentsToAttach.getClass(), companypaymentsCollection1CompanypaymentsToAttach.getCompanypaymentsPK());
                attachedCompanypaymentsCollection1.add(companypaymentsCollection1CompanypaymentsToAttach);
            }
            companyemployee.setCompanypaymentsCollection1(attachedCompanypaymentsCollection1);
            Collection<Crmworkorderresolution> attachedCrmworkorderresolutionCollection = new ArrayList<Crmworkorderresolution>();
            for (Crmworkorderresolution crmworkorderresolutionCollectionCrmworkorderresolutionToAttach : companyemployee.getCrmworkorderresolutionCollection()) {
                crmworkorderresolutionCollectionCrmworkorderresolutionToAttach = em.getReference(crmworkorderresolutionCollectionCrmworkorderresolutionToAttach.getClass(), crmworkorderresolutionCollectionCrmworkorderresolutionToAttach.getCrmworkorderresolutionPK());
                attachedCrmworkorderresolutionCollection.add(crmworkorderresolutionCollectionCrmworkorderresolutionToAttach);
            }
            companyemployee.setCrmworkorderresolutionCollection(attachedCrmworkorderresolutionCollection);
            Collection<Crmworkorderresolution> attachedCrmworkorderresolutionCollection1 = new ArrayList<Crmworkorderresolution>();
            for (Crmworkorderresolution crmworkorderresolutionCollection1CrmworkorderresolutionToAttach : companyemployee.getCrmworkorderresolutionCollection1()) {
                crmworkorderresolutionCollection1CrmworkorderresolutionToAttach = em.getReference(crmworkorderresolutionCollection1CrmworkorderresolutionToAttach.getClass(), crmworkorderresolutionCollection1CrmworkorderresolutionToAttach.getCrmworkorderresolutionPK());
                attachedCrmworkorderresolutionCollection1.add(crmworkorderresolutionCollection1CrmworkorderresolutionToAttach);
            }
            companyemployee.setCrmworkorderresolutionCollection1(attachedCrmworkorderresolutionCollection1);
            Collection<Company> attachedCompanyCollection = new ArrayList<Company>();
            for (Company companyCollectionCompanyToAttach : companyemployee.getCompanyCollection()) {
                companyCollectionCompanyToAttach = em.getReference(companyCollectionCompanyToAttach.getClass(), companyCollectionCompanyToAttach.getCompanyPK());
                attachedCompanyCollection.add(companyCollectionCompanyToAttach);
            }
            companyemployee.setCompanyCollection(attachedCompanyCollection);
            Collection<Company> attachedCompanyCollection1 = new ArrayList<Company>();
            for (Company companyCollection1CompanyToAttach : companyemployee.getCompanyCollection1()) {
                companyCollection1CompanyToAttach = em.getReference(companyCollection1CompanyToAttach.getClass(), companyCollection1CompanyToAttach.getCompanyPK());
                attachedCompanyCollection1.add(companyCollection1CompanyToAttach);
            }
            companyemployee.setCompanyCollection1(attachedCompanyCollection1);
            Collection<Companyaddresstype> attachedCompanyaddresstypeCollection = new ArrayList<Companyaddresstype>();
            for (Companyaddresstype companyaddresstypeCollectionCompanyaddresstypeToAttach : companyemployee.getCompanyaddresstypeCollection()) {
                companyaddresstypeCollectionCompanyaddresstypeToAttach = em.getReference(companyaddresstypeCollectionCompanyaddresstypeToAttach.getClass(), companyaddresstypeCollectionCompanyaddresstypeToAttach.getCompanyaddresstypePK());
                attachedCompanyaddresstypeCollection.add(companyaddresstypeCollectionCompanyaddresstypeToAttach);
            }
            companyemployee.setCompanyaddresstypeCollection(attachedCompanyaddresstypeCollection);
            Collection<Companyaddresstype> attachedCompanyaddresstypeCollection1 = new ArrayList<Companyaddresstype>();
            for (Companyaddresstype companyaddresstypeCollection1CompanyaddresstypeToAttach : companyemployee.getCompanyaddresstypeCollection1()) {
                companyaddresstypeCollection1CompanyaddresstypeToAttach = em.getReference(companyaddresstypeCollection1CompanyaddresstypeToAttach.getClass(), companyaddresstypeCollection1CompanyaddresstypeToAttach.getCompanyaddresstypePK());
                attachedCompanyaddresstypeCollection1.add(companyaddresstypeCollection1CompanyaddresstypeToAttach);
            }
            companyemployee.setCompanyaddresstypeCollection1(attachedCompanyaddresstypeCollection1);
            Collection<Crmemployeecontacts> attachedCrmemployeecontactsCollection = new ArrayList<Crmemployeecontacts>();
            for (Crmemployeecontacts crmemployeecontactsCollectionCrmemployeecontactsToAttach : companyemployee.getCrmemployeecontactsCollection()) {
                crmemployeecontactsCollectionCrmemployeecontactsToAttach = em.getReference(crmemployeecontactsCollectionCrmemployeecontactsToAttach.getClass(), crmemployeecontactsCollectionCrmemployeecontactsToAttach.getCrmemployeecontactsPK());
                attachedCrmemployeecontactsCollection.add(crmemployeecontactsCollectionCrmemployeecontactsToAttach);
            }
            companyemployee.setCrmemployeecontactsCollection(attachedCrmemployeecontactsCollection);
            Collection<Approval> attachedApprovalCollection = new ArrayList<Approval>();
            for (Approval approvalCollectionApprovalToAttach : companyemployee.getApprovalCollection()) {
                approvalCollectionApprovalToAttach = em.getReference(approvalCollectionApprovalToAttach.getClass(), approvalCollectionApprovalToAttach.getApprovalPK());
                attachedApprovalCollection.add(approvalCollectionApprovalToAttach);
            }
            companyemployee.setApprovalCollection(attachedApprovalCollection);
            Collection<Approval> attachedApprovalCollection1 = new ArrayList<Approval>();
            for (Approval approvalCollection1ApprovalToAttach : companyemployee.getApprovalCollection1()) {
                approvalCollection1ApprovalToAttach = em.getReference(approvalCollection1ApprovalToAttach.getClass(), approvalCollection1ApprovalToAttach.getApprovalPK());
                attachedApprovalCollection1.add(approvalCollection1ApprovalToAttach);
            }
            companyemployee.setApprovalCollection1(attachedApprovalCollection1);
            Collection<Approval> attachedApprovalCollection2 = new ArrayList<Approval>();
            for (Approval approvalCollection2ApprovalToAttach : companyemployee.getApprovalCollection2()) {
                approvalCollection2ApprovalToAttach = em.getReference(approvalCollection2ApprovalToAttach.getClass(), approvalCollection2ApprovalToAttach.getApprovalPK());
                attachedApprovalCollection2.add(approvalCollection2ApprovalToAttach);
            }
            companyemployee.setApprovalCollection2(attachedApprovalCollection2);
            Collection<Workorderinstructions> attachedWorkorderinstructionsCollection = new ArrayList<Workorderinstructions>();
            for (Workorderinstructions workorderinstructionsCollectionWorkorderinstructionsToAttach : companyemployee.getWorkorderinstructionsCollection()) {
                workorderinstructionsCollectionWorkorderinstructionsToAttach = em.getReference(workorderinstructionsCollectionWorkorderinstructionsToAttach.getClass(), workorderinstructionsCollectionWorkorderinstructionsToAttach.getWorkorderinstructionsPK());
                attachedWorkorderinstructionsCollection.add(workorderinstructionsCollectionWorkorderinstructionsToAttach);
            }
            companyemployee.setWorkorderinstructionsCollection(attachedWorkorderinstructionsCollection);
            Collection<Workorderinstructions> attachedWorkorderinstructionsCollection1 = new ArrayList<Workorderinstructions>();
            for (Workorderinstructions workorderinstructionsCollection1WorkorderinstructionsToAttach : companyemployee.getWorkorderinstructionsCollection1()) {
                workorderinstructionsCollection1WorkorderinstructionsToAttach = em.getReference(workorderinstructionsCollection1WorkorderinstructionsToAttach.getClass(), workorderinstructionsCollection1WorkorderinstructionsToAttach.getWorkorderinstructionsPK());
                attachedWorkorderinstructionsCollection1.add(workorderinstructionsCollection1WorkorderinstructionsToAttach);
            }
            companyemployee.setWorkorderinstructionsCollection1(attachedWorkorderinstructionsCollection1);
            Collection<Companycustomer> attachedCompanycustomerCollection = new ArrayList<Companycustomer>();
            for (Companycustomer companycustomerCollectionCompanycustomerToAttach : companyemployee.getCompanycustomerCollection()) {
                companycustomerCollectionCompanycustomerToAttach = em.getReference(companycustomerCollectionCompanycustomerToAttach.getClass(), companycustomerCollectionCompanycustomerToAttach.getCompanycustomerPK());
                attachedCompanycustomerCollection.add(companycustomerCollectionCompanycustomerToAttach);
            }
            companyemployee.setCompanycustomerCollection(attachedCompanycustomerCollection);
            Collection<Companycustomer> attachedCompanycustomerCollection1 = new ArrayList<Companycustomer>();
            for (Companycustomer companycustomerCollection1CompanycustomerToAttach : companyemployee.getCompanycustomerCollection1()) {
                companycustomerCollection1CompanycustomerToAttach = em.getReference(companycustomerCollection1CompanycustomerToAttach.getClass(), companycustomerCollection1CompanycustomerToAttach.getCompanycustomerPK());
                attachedCompanycustomerCollection1.add(companycustomerCollection1CompanycustomerToAttach);
            }
            companyemployee.setCompanycustomerCollection1(attachedCompanycustomerCollection1);
            Collection<Crmforum> attachedCrmforumCollection = new ArrayList<Crmforum>();
            for (Crmforum crmforumCollectionCrmforumToAttach : companyemployee.getCrmforumCollection()) {
                crmforumCollectionCrmforumToAttach = em.getReference(crmforumCollectionCrmforumToAttach.getClass(), crmforumCollectionCrmforumToAttach.getCrmforumPK());
                attachedCrmforumCollection.add(crmforumCollectionCrmforumToAttach);
            }
            companyemployee.setCrmforumCollection(attachedCrmforumCollection);
            Collection<Crmprojectstatus> attachedCrmprojectstatusCollection = new ArrayList<Crmprojectstatus>();
            for (Crmprojectstatus crmprojectstatusCollectionCrmprojectstatusToAttach : companyemployee.getCrmprojectstatusCollection()) {
                crmprojectstatusCollectionCrmprojectstatusToAttach = em.getReference(crmprojectstatusCollectionCrmprojectstatusToAttach.getClass(), crmprojectstatusCollectionCrmprojectstatusToAttach.getCrmprojectstatusPK());
                attachedCrmprojectstatusCollection.add(crmprojectstatusCollectionCrmprojectstatusToAttach);
            }
            companyemployee.setCrmprojectstatusCollection(attachedCrmprojectstatusCollection);
            Collection<Crmprojectstatus> attachedCrmprojectstatusCollection1 = new ArrayList<Crmprojectstatus>();
            for (Crmprojectstatus crmprojectstatusCollection1CrmprojectstatusToAttach : companyemployee.getCrmprojectstatusCollection1()) {
                crmprojectstatusCollection1CrmprojectstatusToAttach = em.getReference(crmprojectstatusCollection1CrmprojectstatusToAttach.getClass(), crmprojectstatusCollection1CrmprojectstatusToAttach.getCrmprojectstatusPK());
                attachedCrmprojectstatusCollection1.add(crmprojectstatusCollection1CrmprojectstatusToAttach);
            }
            companyemployee.setCrmprojectstatusCollection1(attachedCrmprojectstatusCollection1);
            Collection<Crmschedulerevnttype> attachedCrmschedulerevnttypeCollection = new ArrayList<Crmschedulerevnttype>();
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionCrmschedulerevnttypeToAttach : companyemployee.getCrmschedulerevnttypeCollection()) {
                crmschedulerevnttypeCollectionCrmschedulerevnttypeToAttach = em.getReference(crmschedulerevnttypeCollectionCrmschedulerevnttypeToAttach.getClass(), crmschedulerevnttypeCollectionCrmschedulerevnttypeToAttach.getCrmschedulerevnttypePK());
                attachedCrmschedulerevnttypeCollection.add(crmschedulerevnttypeCollectionCrmschedulerevnttypeToAttach);
            }
            companyemployee.setCrmschedulerevnttypeCollection(attachedCrmschedulerevnttypeCollection);
            Collection<Crmschedulerevnttype> attachedCrmschedulerevnttypeCollection1 = new ArrayList<Crmschedulerevnttype>();
            for (Crmschedulerevnttype crmschedulerevnttypeCollection1CrmschedulerevnttypeToAttach : companyemployee.getCrmschedulerevnttypeCollection1()) {
                crmschedulerevnttypeCollection1CrmschedulerevnttypeToAttach = em.getReference(crmschedulerevnttypeCollection1CrmschedulerevnttypeToAttach.getClass(), crmschedulerevnttypeCollection1CrmschedulerevnttypeToAttach.getCrmschedulerevnttypePK());
                attachedCrmschedulerevnttypeCollection1.add(crmschedulerevnttypeCollection1CrmschedulerevnttypeToAttach);
            }
            companyemployee.setCrmschedulerevnttypeCollection1(attachedCrmschedulerevnttypeCollection1);
            Collection<Crmschedulerefcode> attachedCrmschedulerefcodeCollection = new ArrayList<Crmschedulerefcode>();
            for (Crmschedulerefcode crmschedulerefcodeCollectionCrmschedulerefcodeToAttach : companyemployee.getCrmschedulerefcodeCollection()) {
                crmschedulerefcodeCollectionCrmschedulerefcodeToAttach = em.getReference(crmschedulerefcodeCollectionCrmschedulerefcodeToAttach.getClass(), crmschedulerefcodeCollectionCrmschedulerefcodeToAttach.getCrmschedulerefcodePK());
                attachedCrmschedulerefcodeCollection.add(crmschedulerefcodeCollectionCrmschedulerefcodeToAttach);
            }
            companyemployee.setCrmschedulerefcodeCollection(attachedCrmschedulerefcodeCollection);
            Collection<Crmschedulerefcode> attachedCrmschedulerefcodeCollection1 = new ArrayList<Crmschedulerefcode>();
            for (Crmschedulerefcode crmschedulerefcodeCollection1CrmschedulerefcodeToAttach : companyemployee.getCrmschedulerefcodeCollection1()) {
                crmschedulerefcodeCollection1CrmschedulerefcodeToAttach = em.getReference(crmschedulerefcodeCollection1CrmschedulerefcodeToAttach.getClass(), crmschedulerefcodeCollection1CrmschedulerefcodeToAttach.getCrmschedulerefcodePK());
                attachedCrmschedulerefcodeCollection1.add(crmschedulerefcodeCollection1CrmschedulerefcodeToAttach);
            }
            companyemployee.setCrmschedulerefcodeCollection1(attachedCrmschedulerefcodeCollection1);
            Collection<Componenttype> attachedComponenttypeCollection = new ArrayList<Componenttype>();
            for (Componenttype componenttypeCollectionComponenttypeToAttach : companyemployee.getComponenttypeCollection()) {
                componenttypeCollectionComponenttypeToAttach = em.getReference(componenttypeCollectionComponenttypeToAttach.getClass(), componenttypeCollectionComponenttypeToAttach.getComponenttypePK());
                attachedComponenttypeCollection.add(componenttypeCollectionComponenttypeToAttach);
            }
            companyemployee.setComponenttypeCollection(attachedComponenttypeCollection);
            Collection<Componenttype> attachedComponenttypeCollection1 = new ArrayList<Componenttype>();
            for (Componenttype componenttypeCollection1ComponenttypeToAttach : companyemployee.getComponenttypeCollection1()) {
                componenttypeCollection1ComponenttypeToAttach = em.getReference(componenttypeCollection1ComponenttypeToAttach.getClass(), componenttypeCollection1ComponenttypeToAttach.getComponenttypePK());
                attachedComponenttypeCollection1.add(componenttypeCollection1ComponenttypeToAttach);
            }
            companyemployee.setComponenttypeCollection1(attachedComponenttypeCollection1);
            Collection<Companypaymentscheme> attachedCompanypaymentschemeCollection = new ArrayList<Companypaymentscheme>();
            for (Companypaymentscheme companypaymentschemeCollectionCompanypaymentschemeToAttach : companyemployee.getCompanypaymentschemeCollection()) {
                companypaymentschemeCollectionCompanypaymentschemeToAttach = em.getReference(companypaymentschemeCollectionCompanypaymentschemeToAttach.getClass(), companypaymentschemeCollectionCompanypaymentschemeToAttach.getCompanypaymentschemePK());
                attachedCompanypaymentschemeCollection.add(companypaymentschemeCollectionCompanypaymentschemeToAttach);
            }
            companyemployee.setCompanypaymentschemeCollection(attachedCompanypaymentschemeCollection);
            Collection<Companypaymentscheme> attachedCompanypaymentschemeCollection1 = new ArrayList<Companypaymentscheme>();
            for (Companypaymentscheme companypaymentschemeCollection1CompanypaymentschemeToAttach : companyemployee.getCompanypaymentschemeCollection1()) {
                companypaymentschemeCollection1CompanypaymentschemeToAttach = em.getReference(companypaymentschemeCollection1CompanypaymentschemeToAttach.getClass(), companypaymentschemeCollection1CompanypaymentschemeToAttach.getCompanypaymentschemePK());
                attachedCompanypaymentschemeCollection1.add(companypaymentschemeCollection1CompanypaymentschemeToAttach);
            }
            companyemployee.setCompanypaymentschemeCollection1(attachedCompanypaymentschemeCollection1);
            Collection<Companycontactsaddress> attachedCompanycontactsaddressCollection = new ArrayList<Companycontactsaddress>();
            for (Companycontactsaddress companycontactsaddressCollectionCompanycontactsaddressToAttach : companyemployee.getCompanycontactsaddressCollection()) {
                companycontactsaddressCollectionCompanycontactsaddressToAttach = em.getReference(companycontactsaddressCollectionCompanycontactsaddressToAttach.getClass(), companycontactsaddressCollectionCompanycontactsaddressToAttach.getCompanycontactsaddressPK());
                attachedCompanycontactsaddressCollection.add(companycontactsaddressCollectionCompanycontactsaddressToAttach);
            }
            companyemployee.setCompanycontactsaddressCollection(attachedCompanycontactsaddressCollection);
            Collection<Companycontactsaddress> attachedCompanycontactsaddressCollection1 = new ArrayList<Companycontactsaddress>();
            for (Companycontactsaddress companycontactsaddressCollection1CompanycontactsaddressToAttach : companyemployee.getCompanycontactsaddressCollection1()) {
                companycontactsaddressCollection1CompanycontactsaddressToAttach = em.getReference(companycontactsaddressCollection1CompanycontactsaddressToAttach.getClass(), companycontactsaddressCollection1CompanycontactsaddressToAttach.getCompanycontactsaddressPK());
                attachedCompanycontactsaddressCollection1.add(companycontactsaddressCollection1CompanycontactsaddressToAttach);
            }
            companyemployee.setCompanycontactsaddressCollection1(attachedCompanycontactsaddressCollection1);
            Collection<Crmprofilesettings> attachedCrmprofilesettingsCollection = new ArrayList<Crmprofilesettings>();
            for (Crmprofilesettings crmprofilesettingsCollectionCrmprofilesettingsToAttach : companyemployee.getCrmprofilesettingsCollection()) {
                crmprofilesettingsCollectionCrmprofilesettingsToAttach = em.getReference(crmprofilesettingsCollectionCrmprofilesettingsToAttach.getClass(), crmprofilesettingsCollectionCrmprofilesettingsToAttach.getCrmprofilesettingsPK());
                attachedCrmprofilesettingsCollection.add(crmprofilesettingsCollectionCrmprofilesettingsToAttach);
            }
            companyemployee.setCrmprofilesettingsCollection(attachedCrmprofilesettingsCollection);
            em.persist(companyemployee);
            if (company != null) {
                company.getCompanyemployeeCollection().add(companyemployee);
                company = em.merge(company);
            }
            if (companydepartment != null) {
                companydepartment.setCompanyemployee(companyemployee);
                companydepartment = em.merge(companydepartment);
            }
            if (companyemployeeRel != null) {
                companyemployeeRel.getCompanyemployeeCollection().add(companyemployee);
                companyemployeeRel = em.merge(companyemployeeRel);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanyemployeeCollection().add(companyemployee);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmmessagechannel crmmessagechannelCollectionCrmmessagechannel : companyemployee.getCrmmessagechannelCollection()) {
                Companyemployee oldCompanyemployeeOfCrmmessagechannelCollectionCrmmessagechannel = crmmessagechannelCollectionCrmmessagechannel.getCompanyemployee();
                crmmessagechannelCollectionCrmmessagechannel.setCompanyemployee(companyemployee);
                crmmessagechannelCollectionCrmmessagechannel = em.merge(crmmessagechannelCollectionCrmmessagechannel);
                if (oldCompanyemployeeOfCrmmessagechannelCollectionCrmmessagechannel != null) {
                    oldCompanyemployeeOfCrmmessagechannelCollectionCrmmessagechannel.getCrmmessagechannelCollection().remove(crmmessagechannelCollectionCrmmessagechannel);
                    oldCompanyemployeeOfCrmmessagechannelCollectionCrmmessagechannel = em.merge(oldCompanyemployeeOfCrmmessagechannelCollectionCrmmessagechannel);
                }
            }
            for (Crmmessagechannel crmmessagechannelCollection1Crmmessagechannel : companyemployee.getCrmmessagechannelCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmmessagechannelCollection1Crmmessagechannel = crmmessagechannelCollection1Crmmessagechannel.getCompanyemployee1();
                crmmessagechannelCollection1Crmmessagechannel.setCompanyemployee1(companyemployee);
                crmmessagechannelCollection1Crmmessagechannel = em.merge(crmmessagechannelCollection1Crmmessagechannel);
                if (oldCompanyemployee1OfCrmmessagechannelCollection1Crmmessagechannel != null) {
                    oldCompanyemployee1OfCrmmessagechannelCollection1Crmmessagechannel.getCrmmessagechannelCollection1().remove(crmmessagechannelCollection1Crmmessagechannel);
                    oldCompanyemployee1OfCrmmessagechannelCollection1Crmmessagechannel = em.merge(oldCompanyemployee1OfCrmmessagechannelCollection1Crmmessagechannel);
                }
            }
            for (Crmmessagechannel crmmessagechannelCollection2Crmmessagechannel : companyemployee.getCrmmessagechannelCollection2()) {
                Companyemployee oldCompanyemployee2OfCrmmessagechannelCollection2Crmmessagechannel = crmmessagechannelCollection2Crmmessagechannel.getCompanyemployee2();
                crmmessagechannelCollection2Crmmessagechannel.setCompanyemployee2(companyemployee);
                crmmessagechannelCollection2Crmmessagechannel = em.merge(crmmessagechannelCollection2Crmmessagechannel);
                if (oldCompanyemployee2OfCrmmessagechannelCollection2Crmmessagechannel != null) {
                    oldCompanyemployee2OfCrmmessagechannelCollection2Crmmessagechannel.getCrmmessagechannelCollection2().remove(crmmessagechannelCollection2Crmmessagechannel);
                    oldCompanyemployee2OfCrmmessagechannelCollection2Crmmessagechannel = em.merge(oldCompanyemployee2OfCrmmessagechannelCollection2Crmmessagechannel);
                }
            }
            for (Companybank companybankCollectionCompanybank : companyemployee.getCompanybankCollection()) {
                Companyemployee oldCompanyemployeeOfCompanybankCollectionCompanybank = companybankCollectionCompanybank.getCompanyemployee();
                companybankCollectionCompanybank.setCompanyemployee(companyemployee);
                companybankCollectionCompanybank = em.merge(companybankCollectionCompanybank);
                if (oldCompanyemployeeOfCompanybankCollectionCompanybank != null) {
                    oldCompanyemployeeOfCompanybankCollectionCompanybank.getCompanybankCollection().remove(companybankCollectionCompanybank);
                    oldCompanyemployeeOfCompanybankCollectionCompanybank = em.merge(oldCompanyemployeeOfCompanybankCollectionCompanybank);
                }
            }
            for (Companybank companybankCollection1Companybank : companyemployee.getCompanybankCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanybankCollection1Companybank = companybankCollection1Companybank.getCompanyemployee1();
                companybankCollection1Companybank.setCompanyemployee1(companyemployee);
                companybankCollection1Companybank = em.merge(companybankCollection1Companybank);
                if (oldCompanyemployee1OfCompanybankCollection1Companybank != null) {
                    oldCompanyemployee1OfCompanybankCollection1Companybank.getCompanybankCollection1().remove(companybankCollection1Companybank);
                    oldCompanyemployee1OfCompanybankCollection1Companybank = em.merge(oldCompanyemployee1OfCompanybankCollection1Companybank);
                }
            }
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionCrmmessagechanneltemplate : companyemployee.getCrmmessagechanneltemplateCollection()) {
                Companyemployee oldCompanyemployeeOfCrmmessagechanneltemplateCollectionCrmmessagechanneltemplate = crmmessagechanneltemplateCollectionCrmmessagechanneltemplate.getCompanyemployee();
                crmmessagechanneltemplateCollectionCrmmessagechanneltemplate.setCompanyemployee(companyemployee);
                crmmessagechanneltemplateCollectionCrmmessagechanneltemplate = em.merge(crmmessagechanneltemplateCollectionCrmmessagechanneltemplate);
                if (oldCompanyemployeeOfCrmmessagechanneltemplateCollectionCrmmessagechanneltemplate != null) {
                    oldCompanyemployeeOfCrmmessagechanneltemplateCollectionCrmmessagechanneltemplate.getCrmmessagechanneltemplateCollection().remove(crmmessagechanneltemplateCollectionCrmmessagechanneltemplate);
                    oldCompanyemployeeOfCrmmessagechanneltemplateCollectionCrmmessagechanneltemplate = em.merge(oldCompanyemployeeOfCrmmessagechanneltemplateCollectionCrmmessagechanneltemplate);
                }
            }
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollection1Crmmessagechanneltemplate : companyemployee.getCrmmessagechanneltemplateCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmmessagechanneltemplateCollection1Crmmessagechanneltemplate = crmmessagechanneltemplateCollection1Crmmessagechanneltemplate.getCompanyemployee1();
                crmmessagechanneltemplateCollection1Crmmessagechanneltemplate.setCompanyemployee1(companyemployee);
                crmmessagechanneltemplateCollection1Crmmessagechanneltemplate = em.merge(crmmessagechanneltemplateCollection1Crmmessagechanneltemplate);
                if (oldCompanyemployee1OfCrmmessagechanneltemplateCollection1Crmmessagechanneltemplate != null) {
                    oldCompanyemployee1OfCrmmessagechanneltemplateCollection1Crmmessagechanneltemplate.getCrmmessagechanneltemplateCollection1().remove(crmmessagechanneltemplateCollection1Crmmessagechanneltemplate);
                    oldCompanyemployee1OfCrmmessagechanneltemplateCollection1Crmmessagechanneltemplate = em.merge(oldCompanyemployee1OfCrmmessagechanneltemplateCollection1Crmmessagechanneltemplate);
                }
            }
            for (Crmprojectprops crmprojectpropsCollectionCrmprojectprops : companyemployee.getCrmprojectpropsCollection()) {
                Companyemployee oldCompanyemployeeOfCrmprojectpropsCollectionCrmprojectprops = crmprojectpropsCollectionCrmprojectprops.getCompanyemployee();
                crmprojectpropsCollectionCrmprojectprops.setCompanyemployee(companyemployee);
                crmprojectpropsCollectionCrmprojectprops = em.merge(crmprojectpropsCollectionCrmprojectprops);
                if (oldCompanyemployeeOfCrmprojectpropsCollectionCrmprojectprops != null) {
                    oldCompanyemployeeOfCrmprojectpropsCollectionCrmprojectprops.getCrmprojectpropsCollection().remove(crmprojectpropsCollectionCrmprojectprops);
                    oldCompanyemployeeOfCrmprojectpropsCollectionCrmprojectprops = em.merge(oldCompanyemployeeOfCrmprojectpropsCollectionCrmprojectprops);
                }
            }
            for (Crmprojectprops crmprojectpropsCollection1Crmprojectprops : companyemployee.getCrmprojectpropsCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmprojectpropsCollection1Crmprojectprops = crmprojectpropsCollection1Crmprojectprops.getCompanyemployee1();
                crmprojectpropsCollection1Crmprojectprops.setCompanyemployee1(companyemployee);
                crmprojectpropsCollection1Crmprojectprops = em.merge(crmprojectpropsCollection1Crmprojectprops);
                if (oldCompanyemployee1OfCrmprojectpropsCollection1Crmprojectprops != null) {
                    oldCompanyemployee1OfCrmprojectpropsCollection1Crmprojectprops.getCrmprojectpropsCollection1().remove(crmprojectpropsCollection1Crmprojectprops);
                    oldCompanyemployee1OfCrmprojectpropsCollection1Crmprojectprops = em.merge(oldCompanyemployee1OfCrmprojectpropsCollection1Crmprojectprops);
                }
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollectionCrmcampaignreceiver : companyemployee.getCrmcampaignreceiverCollection()) {
                Companyemployee oldCompanyemployeeOfCrmcampaignreceiverCollectionCrmcampaignreceiver = crmcampaignreceiverCollectionCrmcampaignreceiver.getCompanyemployee();
                crmcampaignreceiverCollectionCrmcampaignreceiver.setCompanyemployee(companyemployee);
                crmcampaignreceiverCollectionCrmcampaignreceiver = em.merge(crmcampaignreceiverCollectionCrmcampaignreceiver);
                if (oldCompanyemployeeOfCrmcampaignreceiverCollectionCrmcampaignreceiver != null) {
                    oldCompanyemployeeOfCrmcampaignreceiverCollectionCrmcampaignreceiver.getCrmcampaignreceiverCollection().remove(crmcampaignreceiverCollectionCrmcampaignreceiver);
                    oldCompanyemployeeOfCrmcampaignreceiverCollectionCrmcampaignreceiver = em.merge(oldCompanyemployeeOfCrmcampaignreceiverCollectionCrmcampaignreceiver);
                }
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollection1Crmcampaignreceiver : companyemployee.getCrmcampaignreceiverCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmcampaignreceiverCollection1Crmcampaignreceiver = crmcampaignreceiverCollection1Crmcampaignreceiver.getCompanyemployee1();
                crmcampaignreceiverCollection1Crmcampaignreceiver.setCompanyemployee1(companyemployee);
                crmcampaignreceiverCollection1Crmcampaignreceiver = em.merge(crmcampaignreceiverCollection1Crmcampaignreceiver);
                if (oldCompanyemployee1OfCrmcampaignreceiverCollection1Crmcampaignreceiver != null) {
                    oldCompanyemployee1OfCrmcampaignreceiverCollection1Crmcampaignreceiver.getCrmcampaignreceiverCollection1().remove(crmcampaignreceiverCollection1Crmcampaignreceiver);
                    oldCompanyemployee1OfCrmcampaignreceiverCollection1Crmcampaignreceiver = em.merge(oldCompanyemployee1OfCrmcampaignreceiverCollection1Crmcampaignreceiver);
                }
            }
            for (Crmworkordersettings crmworkordersettingsCollectionCrmworkordersettings : companyemployee.getCrmworkordersettingsCollection()) {
                Companyemployee oldCompanyemployeeOfCrmworkordersettingsCollectionCrmworkordersettings = crmworkordersettingsCollectionCrmworkordersettings.getCompanyemployee();
                crmworkordersettingsCollectionCrmworkordersettings.setCompanyemployee(companyemployee);
                crmworkordersettingsCollectionCrmworkordersettings = em.merge(crmworkordersettingsCollectionCrmworkordersettings);
                if (oldCompanyemployeeOfCrmworkordersettingsCollectionCrmworkordersettings != null) {
                    oldCompanyemployeeOfCrmworkordersettingsCollectionCrmworkordersettings.getCrmworkordersettingsCollection().remove(crmworkordersettingsCollectionCrmworkordersettings);
                    oldCompanyemployeeOfCrmworkordersettingsCollectionCrmworkordersettings = em.merge(oldCompanyemployeeOfCrmworkordersettingsCollectionCrmworkordersettings);
                }
            }
            for (Productattachments productattachmentsCollectionProductattachments : companyemployee.getProductattachmentsCollection()) {
                Companyemployee oldCompanyemployeeOfProductattachmentsCollectionProductattachments = productattachmentsCollectionProductattachments.getCompanyemployee();
                productattachmentsCollectionProductattachments.setCompanyemployee(companyemployee);
                productattachmentsCollectionProductattachments = em.merge(productattachmentsCollectionProductattachments);
                if (oldCompanyemployeeOfProductattachmentsCollectionProductattachments != null) {
                    oldCompanyemployeeOfProductattachmentsCollectionProductattachments.getProductattachmentsCollection().remove(productattachmentsCollectionProductattachments);
                    oldCompanyemployeeOfProductattachmentsCollectionProductattachments = em.merge(oldCompanyemployeeOfProductattachmentsCollectionProductattachments);
                }
            }
            for (Productattachments productattachmentsCollection1Productattachments : companyemployee.getProductattachmentsCollection1()) {
                Companyemployee oldCompanyemployee1OfProductattachmentsCollection1Productattachments = productattachmentsCollection1Productattachments.getCompanyemployee1();
                productattachmentsCollection1Productattachments.setCompanyemployee1(companyemployee);
                productattachmentsCollection1Productattachments = em.merge(productattachmentsCollection1Productattachments);
                if (oldCompanyemployee1OfProductattachmentsCollection1Productattachments != null) {
                    oldCompanyemployee1OfProductattachmentsCollection1Productattachments.getProductattachmentsCollection1().remove(productattachmentsCollection1Productattachments);
                    oldCompanyemployee1OfProductattachmentsCollection1Productattachments = em.merge(oldCompanyemployee1OfProductattachmentsCollection1Productattachments);
                }
            }
            for (Productattachments productattachmentsCollection2Productattachments : companyemployee.getProductattachmentsCollection2()) {
                Companyemployee oldCompanyemployee2OfProductattachmentsCollection2Productattachments = productattachmentsCollection2Productattachments.getCompanyemployee2();
                productattachmentsCollection2Productattachments.setCompanyemployee2(companyemployee);
                productattachmentsCollection2Productattachments = em.merge(productattachmentsCollection2Productattachments);
                if (oldCompanyemployee2OfProductattachmentsCollection2Productattachments != null) {
                    oldCompanyemployee2OfProductattachmentsCollection2Productattachments.getProductattachmentsCollection2().remove(productattachmentsCollection2Productattachments);
                    oldCompanyemployee2OfProductattachmentsCollection2Productattachments = em.merge(oldCompanyemployee2OfProductattachmentsCollection2Productattachments);
                }
            }
            for (Crmcampaignstatus crmcampaignstatusCollectionCrmcampaignstatus : companyemployee.getCrmcampaignstatusCollection()) {
                Companyemployee oldCompanyemployeeOfCrmcampaignstatusCollectionCrmcampaignstatus = crmcampaignstatusCollectionCrmcampaignstatus.getCompanyemployee();
                crmcampaignstatusCollectionCrmcampaignstatus.setCompanyemployee(companyemployee);
                crmcampaignstatusCollectionCrmcampaignstatus = em.merge(crmcampaignstatusCollectionCrmcampaignstatus);
                if (oldCompanyemployeeOfCrmcampaignstatusCollectionCrmcampaignstatus != null) {
                    oldCompanyemployeeOfCrmcampaignstatusCollectionCrmcampaignstatus.getCrmcampaignstatusCollection().remove(crmcampaignstatusCollectionCrmcampaignstatus);
                    oldCompanyemployeeOfCrmcampaignstatusCollectionCrmcampaignstatus = em.merge(oldCompanyemployeeOfCrmcampaignstatusCollectionCrmcampaignstatus);
                }
            }
            for (Crmcampaignstatus crmcampaignstatusCollection1Crmcampaignstatus : companyemployee.getCrmcampaignstatusCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmcampaignstatusCollection1Crmcampaignstatus = crmcampaignstatusCollection1Crmcampaignstatus.getCompanyemployee1();
                crmcampaignstatusCollection1Crmcampaignstatus.setCompanyemployee1(companyemployee);
                crmcampaignstatusCollection1Crmcampaignstatus = em.merge(crmcampaignstatusCollection1Crmcampaignstatus);
                if (oldCompanyemployee1OfCrmcampaignstatusCollection1Crmcampaignstatus != null) {
                    oldCompanyemployee1OfCrmcampaignstatusCollection1Crmcampaignstatus.getCrmcampaignstatusCollection1().remove(crmcampaignstatusCollection1Crmcampaignstatus);
                    oldCompanyemployee1OfCrmcampaignstatusCollection1Crmcampaignstatus = em.merge(oldCompanyemployee1OfCrmcampaignstatusCollection1Crmcampaignstatus);
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollectionCrmcampaignposition : companyemployee.getCrmcampaignpositionCollection()) {
                Companyemployee oldCompanyemployeeOfCrmcampaignpositionCollectionCrmcampaignposition = crmcampaignpositionCollectionCrmcampaignposition.getCompanyemployee();
                crmcampaignpositionCollectionCrmcampaignposition.setCompanyemployee(companyemployee);
                crmcampaignpositionCollectionCrmcampaignposition = em.merge(crmcampaignpositionCollectionCrmcampaignposition);
                if (oldCompanyemployeeOfCrmcampaignpositionCollectionCrmcampaignposition != null) {
                    oldCompanyemployeeOfCrmcampaignpositionCollectionCrmcampaignposition.getCrmcampaignpositionCollection().remove(crmcampaignpositionCollectionCrmcampaignposition);
                    oldCompanyemployeeOfCrmcampaignpositionCollectionCrmcampaignposition = em.merge(oldCompanyemployeeOfCrmcampaignpositionCollectionCrmcampaignposition);
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollection1Crmcampaignposition : companyemployee.getCrmcampaignpositionCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmcampaignpositionCollection1Crmcampaignposition = crmcampaignpositionCollection1Crmcampaignposition.getCompanyemployee1();
                crmcampaignpositionCollection1Crmcampaignposition.setCompanyemployee1(companyemployee);
                crmcampaignpositionCollection1Crmcampaignposition = em.merge(crmcampaignpositionCollection1Crmcampaignposition);
                if (oldCompanyemployee1OfCrmcampaignpositionCollection1Crmcampaignposition != null) {
                    oldCompanyemployee1OfCrmcampaignpositionCollection1Crmcampaignposition.getCrmcampaignpositionCollection1().remove(crmcampaignpositionCollection1Crmcampaignposition);
                    oldCompanyemployee1OfCrmcampaignpositionCollection1Crmcampaignposition = em.merge(oldCompanyemployee1OfCrmcampaignpositionCollection1Crmcampaignposition);
                }
            }
            for (Crmforumteammembers crmforumteammembersCollectionCrmforumteammembers : companyemployee.getCrmforumteammembersCollection()) {
                Companyemployee oldCompanyemployeeOfCrmforumteammembersCollectionCrmforumteammembers = crmforumteammembersCollectionCrmforumteammembers.getCompanyemployee();
                crmforumteammembersCollectionCrmforumteammembers.setCompanyemployee(companyemployee);
                crmforumteammembersCollectionCrmforumteammembers = em.merge(crmforumteammembersCollectionCrmforumteammembers);
                if (oldCompanyemployeeOfCrmforumteammembersCollectionCrmforumteammembers != null) {
                    oldCompanyemployeeOfCrmforumteammembersCollectionCrmforumteammembers.getCrmforumteammembersCollection().remove(crmforumteammembersCollectionCrmforumteammembers);
                    oldCompanyemployeeOfCrmforumteammembersCollectionCrmforumteammembers = em.merge(oldCompanyemployeeOfCrmforumteammembersCollectionCrmforumteammembers);
                }
            }
            for (Customercontacts customercontactsCollectionCustomercontacts : companyemployee.getCustomercontactsCollection()) {
                Companyemployee oldCompanyemployeeOfCustomercontactsCollectionCustomercontacts = customercontactsCollectionCustomercontacts.getCompanyemployee();
                customercontactsCollectionCustomercontacts.setCompanyemployee(companyemployee);
                customercontactsCollectionCustomercontacts = em.merge(customercontactsCollectionCustomercontacts);
                if (oldCompanyemployeeOfCustomercontactsCollectionCustomercontacts != null) {
                    oldCompanyemployeeOfCustomercontactsCollectionCustomercontacts.getCustomercontactsCollection().remove(customercontactsCollectionCustomercontacts);
                    oldCompanyemployeeOfCustomercontactsCollectionCustomercontacts = em.merge(oldCompanyemployeeOfCustomercontactsCollectionCustomercontacts);
                }
            }
            for (Customercontacts customercontactsCollection1Customercontacts : companyemployee.getCustomercontactsCollection1()) {
                Companyemployee oldCompanyemployee1OfCustomercontactsCollection1Customercontacts = customercontactsCollection1Customercontacts.getCompanyemployee1();
                customercontactsCollection1Customercontacts.setCompanyemployee1(companyemployee);
                customercontactsCollection1Customercontacts = em.merge(customercontactsCollection1Customercontacts);
                if (oldCompanyemployee1OfCustomercontactsCollection1Customercontacts != null) {
                    oldCompanyemployee1OfCustomercontactsCollection1Customercontacts.getCustomercontactsCollection1().remove(customercontactsCollection1Customercontacts);
                    oldCompanyemployee1OfCustomercontactsCollection1Customercontacts = em.merge(oldCompanyemployee1OfCustomercontactsCollection1Customercontacts);
                }
            }
            for (Appointment appointmentCollectionAppointment : companyemployee.getAppointmentCollection()) {
                Companyemployee oldCompanyemployeeOfAppointmentCollectionAppointment = appointmentCollectionAppointment.getCompanyemployee();
                appointmentCollectionAppointment.setCompanyemployee(companyemployee);
                appointmentCollectionAppointment = em.merge(appointmentCollectionAppointment);
                if (oldCompanyemployeeOfAppointmentCollectionAppointment != null) {
                    oldCompanyemployeeOfAppointmentCollectionAppointment.getAppointmentCollection().remove(appointmentCollectionAppointment);
                    oldCompanyemployeeOfAppointmentCollectionAppointment = em.merge(oldCompanyemployeeOfAppointmentCollectionAppointment);
                }
            }
            for (Appointment appointmentCollection1Appointment : companyemployee.getAppointmentCollection1()) {
                Companyemployee oldCompanyemployee1OfAppointmentCollection1Appointment = appointmentCollection1Appointment.getCompanyemployee1();
                appointmentCollection1Appointment.setCompanyemployee1(companyemployee);
                appointmentCollection1Appointment = em.merge(appointmentCollection1Appointment);
                if (oldCompanyemployee1OfAppointmentCollection1Appointment != null) {
                    oldCompanyemployee1OfAppointmentCollection1Appointment.getAppointmentCollection1().remove(appointmentCollection1Appointment);
                    oldCompanyemployee1OfAppointmentCollection1Appointment = em.merge(oldCompanyemployee1OfAppointmentCollection1Appointment);
                }
            }
            for (Appointment appointmentCollection2Appointment : companyemployee.getAppointmentCollection2()) {
                Companyemployee oldCompanyemployee2OfAppointmentCollection2Appointment = appointmentCollection2Appointment.getCompanyemployee2();
                appointmentCollection2Appointment.setCompanyemployee2(companyemployee);
                appointmentCollection2Appointment = em.merge(appointmentCollection2Appointment);
                if (oldCompanyemployee2OfAppointmentCollection2Appointment != null) {
                    oldCompanyemployee2OfAppointmentCollection2Appointment.getAppointmentCollection2().remove(appointmentCollection2Appointment);
                    oldCompanyemployee2OfAppointmentCollection2Appointment = em.merge(oldCompanyemployee2OfAppointmentCollection2Appointment);
                }
            }
            for (Appointment appointmentCollection3Appointment : companyemployee.getAppointmentCollection3()) {
                Companyemployee oldCompanyemployee3OfAppointmentCollection3Appointment = appointmentCollection3Appointment.getCompanyemployee3();
                appointmentCollection3Appointment.setCompanyemployee3(companyemployee);
                appointmentCollection3Appointment = em.merge(appointmentCollection3Appointment);
                if (oldCompanyemployee3OfAppointmentCollection3Appointment != null) {
                    oldCompanyemployee3OfAppointmentCollection3Appointment.getAppointmentCollection3().remove(appointmentCollection3Appointment);
                    oldCompanyemployee3OfAppointmentCollection3Appointment = em.merge(oldCompanyemployee3OfAppointmentCollection3Appointment);
                }
            }
            for (Companydepartment companydepartmentCollectionCompanydepartment : companyemployee.getCompanydepartmentCollection()) {
                Companyemployee oldCompanyemployeeOfCompanydepartmentCollectionCompanydepartment = companydepartmentCollectionCompanydepartment.getCompanyemployee();
                companydepartmentCollectionCompanydepartment.setCompanyemployee(companyemployee);
                companydepartmentCollectionCompanydepartment = em.merge(companydepartmentCollectionCompanydepartment);
                if (oldCompanyemployeeOfCompanydepartmentCollectionCompanydepartment != null) {
                    oldCompanyemployeeOfCompanydepartmentCollectionCompanydepartment.getCompanydepartmentCollection().remove(companydepartmentCollectionCompanydepartment);
                    oldCompanyemployeeOfCompanydepartmentCollectionCompanydepartment = em.merge(oldCompanyemployeeOfCompanydepartmentCollectionCompanydepartment);
                }
            }
            for (Companydepartment companydepartmentCollection1Companydepartment : companyemployee.getCompanydepartmentCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanydepartmentCollection1Companydepartment = companydepartmentCollection1Companydepartment.getCompanyemployee1();
                companydepartmentCollection1Companydepartment.setCompanyemployee1(companyemployee);
                companydepartmentCollection1Companydepartment = em.merge(companydepartmentCollection1Companydepartment);
                if (oldCompanyemployee1OfCompanydepartmentCollection1Companydepartment != null) {
                    oldCompanyemployee1OfCompanydepartmentCollection1Companydepartment.getCompanydepartmentCollection1().remove(companydepartmentCollection1Companydepartment);
                    oldCompanyemployee1OfCompanydepartmentCollection1Companydepartment = em.merge(oldCompanyemployee1OfCompanydepartmentCollection1Companydepartment);
                }
            }
            for (Crmusermodules crmusermodulesCollectionCrmusermodules : companyemployee.getCrmusermodulesCollection()) {
                Companyemployee oldCompanyemployeeOfCrmusermodulesCollectionCrmusermodules = crmusermodulesCollectionCrmusermodules.getCompanyemployee();
                crmusermodulesCollectionCrmusermodules.setCompanyemployee(companyemployee);
                crmusermodulesCollectionCrmusermodules = em.merge(crmusermodulesCollectionCrmusermodules);
                if (oldCompanyemployeeOfCrmusermodulesCollectionCrmusermodules != null) {
                    oldCompanyemployeeOfCrmusermodulesCollectionCrmusermodules.getCrmusermodulesCollection().remove(crmusermodulesCollectionCrmusermodules);
                    oldCompanyemployeeOfCrmusermodulesCollectionCrmusermodules = em.merge(oldCompanyemployeeOfCrmusermodulesCollectionCrmusermodules);
                }
            }
            for (Crmusermodules crmusermodulesCollection1Crmusermodules : companyemployee.getCrmusermodulesCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmusermodulesCollection1Crmusermodules = crmusermodulesCollection1Crmusermodules.getCompanyemployee1();
                crmusermodulesCollection1Crmusermodules.setCompanyemployee1(companyemployee);
                crmusermodulesCollection1Crmusermodules = em.merge(crmusermodulesCollection1Crmusermodules);
                if (oldCompanyemployee1OfCrmusermodulesCollection1Crmusermodules != null) {
                    oldCompanyemployee1OfCrmusermodulesCollection1Crmusermodules.getCrmusermodulesCollection1().remove(crmusermodulesCollection1Crmusermodules);
                    oldCompanyemployee1OfCrmusermodulesCollection1Crmusermodules = em.merge(oldCompanyemployee1OfCrmusermodulesCollection1Crmusermodules);
                }
            }
            for (Crmexpense crmexpenseCollectionCrmexpense : companyemployee.getCrmexpenseCollection()) {
                Companyemployee oldCompanyemployeeOfCrmexpenseCollectionCrmexpense = crmexpenseCollectionCrmexpense.getCompanyemployee();
                crmexpenseCollectionCrmexpense.setCompanyemployee(companyemployee);
                crmexpenseCollectionCrmexpense = em.merge(crmexpenseCollectionCrmexpense);
                if (oldCompanyemployeeOfCrmexpenseCollectionCrmexpense != null) {
                    oldCompanyemployeeOfCrmexpenseCollectionCrmexpense.getCrmexpenseCollection().remove(crmexpenseCollectionCrmexpense);
                    oldCompanyemployeeOfCrmexpenseCollectionCrmexpense = em.merge(oldCompanyemployeeOfCrmexpenseCollectionCrmexpense);
                }
            }
            for (Crmexpense crmexpenseCollection1Crmexpense : companyemployee.getCrmexpenseCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmexpenseCollection1Crmexpense = crmexpenseCollection1Crmexpense.getCompanyemployee1();
                crmexpenseCollection1Crmexpense.setCompanyemployee1(companyemployee);
                crmexpenseCollection1Crmexpense = em.merge(crmexpenseCollection1Crmexpense);
                if (oldCompanyemployee1OfCrmexpenseCollection1Crmexpense != null) {
                    oldCompanyemployee1OfCrmexpenseCollection1Crmexpense.getCrmexpenseCollection1().remove(crmexpenseCollection1Crmexpense);
                    oldCompanyemployee1OfCrmexpenseCollection1Crmexpense = em.merge(oldCompanyemployee1OfCrmexpenseCollection1Crmexpense);
                }
            }
            for (Crmworkorder crmworkorderCollectionCrmworkorder : companyemployee.getCrmworkorderCollection()) {
                Companyemployee oldCompanyemployeeOfCrmworkorderCollectionCrmworkorder = crmworkorderCollectionCrmworkorder.getCompanyemployee();
                crmworkorderCollectionCrmworkorder.setCompanyemployee(companyemployee);
                crmworkorderCollectionCrmworkorder = em.merge(crmworkorderCollectionCrmworkorder);
                if (oldCompanyemployeeOfCrmworkorderCollectionCrmworkorder != null) {
                    oldCompanyemployeeOfCrmworkorderCollectionCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionCrmworkorder);
                    oldCompanyemployeeOfCrmworkorderCollectionCrmworkorder = em.merge(oldCompanyemployeeOfCrmworkorderCollectionCrmworkorder);
                }
            }
            for (Crmworkorder crmworkorderCollection1Crmworkorder : companyemployee.getCrmworkorderCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmworkorderCollection1Crmworkorder = crmworkorderCollection1Crmworkorder.getCompanyemployee1();
                crmworkorderCollection1Crmworkorder.setCompanyemployee1(companyemployee);
                crmworkorderCollection1Crmworkorder = em.merge(crmworkorderCollection1Crmworkorder);
                if (oldCompanyemployee1OfCrmworkorderCollection1Crmworkorder != null) {
                    oldCompanyemployee1OfCrmworkorderCollection1Crmworkorder.getCrmworkorderCollection1().remove(crmworkorderCollection1Crmworkorder);
                    oldCompanyemployee1OfCrmworkorderCollection1Crmworkorder = em.merge(oldCompanyemployee1OfCrmworkorderCollection1Crmworkorder);
                }
            }
            for (Crmcampaigndocs crmcampaigndocsCollectionCrmcampaigndocs : companyemployee.getCrmcampaigndocsCollection()) {
                Companyemployee oldCompanyemployeeOfCrmcampaigndocsCollectionCrmcampaigndocs = crmcampaigndocsCollectionCrmcampaigndocs.getCompanyemployee();
                crmcampaigndocsCollectionCrmcampaigndocs.setCompanyemployee(companyemployee);
                crmcampaigndocsCollectionCrmcampaigndocs = em.merge(crmcampaigndocsCollectionCrmcampaigndocs);
                if (oldCompanyemployeeOfCrmcampaigndocsCollectionCrmcampaigndocs != null) {
                    oldCompanyemployeeOfCrmcampaigndocsCollectionCrmcampaigndocs.getCrmcampaigndocsCollection().remove(crmcampaigndocsCollectionCrmcampaigndocs);
                    oldCompanyemployeeOfCrmcampaigndocsCollectionCrmcampaigndocs = em.merge(oldCompanyemployeeOfCrmcampaigndocsCollectionCrmcampaigndocs);
                }
            }
            for (Crmcampaigndocs crmcampaigndocsCollection1Crmcampaigndocs : companyemployee.getCrmcampaigndocsCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmcampaigndocsCollection1Crmcampaigndocs = crmcampaigndocsCollection1Crmcampaigndocs.getCompanyemployee1();
                crmcampaigndocsCollection1Crmcampaigndocs.setCompanyemployee1(companyemployee);
                crmcampaigndocsCollection1Crmcampaigndocs = em.merge(crmcampaigndocsCollection1Crmcampaigndocs);
                if (oldCompanyemployee1OfCrmcampaigndocsCollection1Crmcampaigndocs != null) {
                    oldCompanyemployee1OfCrmcampaigndocsCollection1Crmcampaigndocs.getCrmcampaigndocsCollection1().remove(crmcampaigndocsCollection1Crmcampaigndocs);
                    oldCompanyemployee1OfCrmcampaigndocsCollection1Crmcampaigndocs = em.merge(oldCompanyemployee1OfCrmcampaigndocsCollection1Crmcampaigndocs);
                }
            }
            for (Crmemployeenote crmemployeenoteCollectionCrmemployeenote : companyemployee.getCrmemployeenoteCollection()) {
                Companyemployee oldCompanyemployeeOfCrmemployeenoteCollectionCrmemployeenote = crmemployeenoteCollectionCrmemployeenote.getCompanyemployee();
                crmemployeenoteCollectionCrmemployeenote.setCompanyemployee(companyemployee);
                crmemployeenoteCollectionCrmemployeenote = em.merge(crmemployeenoteCollectionCrmemployeenote);
                if (oldCompanyemployeeOfCrmemployeenoteCollectionCrmemployeenote != null) {
                    oldCompanyemployeeOfCrmemployeenoteCollectionCrmemployeenote.getCrmemployeenoteCollection().remove(crmemployeenoteCollectionCrmemployeenote);
                    oldCompanyemployeeOfCrmemployeenoteCollectionCrmemployeenote = em.merge(oldCompanyemployeeOfCrmemployeenoteCollectionCrmemployeenote);
                }
            }
            for (Companycontacts companycontactsCollectionCompanycontacts : companyemployee.getCompanycontactsCollection()) {
                Companyemployee oldCompanyemployeeOfCompanycontactsCollectionCompanycontacts = companycontactsCollectionCompanycontacts.getCompanyemployee();
                companycontactsCollectionCompanycontacts.setCompanyemployee(companyemployee);
                companycontactsCollectionCompanycontacts = em.merge(companycontactsCollectionCompanycontacts);
                if (oldCompanyemployeeOfCompanycontactsCollectionCompanycontacts != null) {
                    oldCompanyemployeeOfCompanycontactsCollectionCompanycontacts.getCompanycontactsCollection().remove(companycontactsCollectionCompanycontacts);
                    oldCompanyemployeeOfCompanycontactsCollectionCompanycontacts = em.merge(oldCompanyemployeeOfCompanycontactsCollectionCompanycontacts);
                }
            }
            for (Companycontacts companycontactsCollection1Companycontacts : companyemployee.getCompanycontactsCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanycontactsCollection1Companycontacts = companycontactsCollection1Companycontacts.getCompanyemployee1();
                companycontactsCollection1Companycontacts.setCompanyemployee1(companyemployee);
                companycontactsCollection1Companycontacts = em.merge(companycontactsCollection1Companycontacts);
                if (oldCompanyemployee1OfCompanycontactsCollection1Companycontacts != null) {
                    oldCompanyemployee1OfCompanycontactsCollection1Companycontacts.getCompanycontactsCollection1().remove(companycontactsCollection1Companycontacts);
                    oldCompanyemployee1OfCompanycontactsCollection1Companycontacts = em.merge(oldCompanyemployee1OfCompanycontactsCollection1Companycontacts);
                }
            }
            for (Companyemployee companyemployeeCollectionCompanyemployee : companyemployee.getCompanyemployeeCollection()) {
                Companyemployee oldCompanyemployeeOfCompanyemployeeCollectionCompanyemployee = companyemployeeCollectionCompanyemployee.getCompanyemployee();
                companyemployeeCollectionCompanyemployee.setCompanyemployee(companyemployee);
                companyemployeeCollectionCompanyemployee = em.merge(companyemployeeCollectionCompanyemployee);
                if (oldCompanyemployeeOfCompanyemployeeCollectionCompanyemployee != null) {
                    oldCompanyemployeeOfCompanyemployeeCollectionCompanyemployee.getCompanyemployeeCollection().remove(companyemployeeCollectionCompanyemployee);
                    oldCompanyemployeeOfCompanyemployeeCollectionCompanyemployee = em.merge(oldCompanyemployeeOfCompanyemployeeCollectionCompanyemployee);
                }
            }
            for (Companyemployee companyemployeeCollection1Companyemployee : companyemployee.getCompanyemployeeCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanyemployeeCollection1Companyemployee = companyemployeeCollection1Companyemployee.getCompanyemployee1();
                companyemployeeCollection1Companyemployee.setCompanyemployee1(companyemployee);
                companyemployeeCollection1Companyemployee = em.merge(companyemployeeCollection1Companyemployee);
                if (oldCompanyemployee1OfCompanyemployeeCollection1Companyemployee != null) {
                    oldCompanyemployee1OfCompanyemployeeCollection1Companyemployee.getCompanyemployeeCollection1().remove(companyemployeeCollection1Companyemployee);
                    oldCompanyemployee1OfCompanyemployeeCollection1Companyemployee = em.merge(oldCompanyemployee1OfCompanyemployeeCollection1Companyemployee);
                }
            }
            for (Companyproducttype companyproducttypeCollectionCompanyproducttype : companyemployee.getCompanyproducttypeCollection()) {
                Companyemployee oldCompanyemployeeOfCompanyproducttypeCollectionCompanyproducttype = companyproducttypeCollectionCompanyproducttype.getCompanyemployee();
                companyproducttypeCollectionCompanyproducttype.setCompanyemployee(companyemployee);
                companyproducttypeCollectionCompanyproducttype = em.merge(companyproducttypeCollectionCompanyproducttype);
                if (oldCompanyemployeeOfCompanyproducttypeCollectionCompanyproducttype != null) {
                    oldCompanyemployeeOfCompanyproducttypeCollectionCompanyproducttype.getCompanyproducttypeCollection().remove(companyproducttypeCollectionCompanyproducttype);
                    oldCompanyemployeeOfCompanyproducttypeCollectionCompanyproducttype = em.merge(oldCompanyemployeeOfCompanyproducttypeCollectionCompanyproducttype);
                }
            }
            for (Companyproducttype companyproducttypeCollection1Companyproducttype : companyemployee.getCompanyproducttypeCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanyproducttypeCollection1Companyproducttype = companyproducttypeCollection1Companyproducttype.getCompanyemployee1();
                companyproducttypeCollection1Companyproducttype.setCompanyemployee1(companyemployee);
                companyproducttypeCollection1Companyproducttype = em.merge(companyproducttypeCollection1Companyproducttype);
                if (oldCompanyemployee1OfCompanyproducttypeCollection1Companyproducttype != null) {
                    oldCompanyemployee1OfCompanyproducttypeCollection1Companyproducttype.getCompanyproducttypeCollection1().remove(companyproducttypeCollection1Companyproducttype);
                    oldCompanyemployee1OfCompanyproducttypeCollection1Companyproducttype = em.merge(oldCompanyemployee1OfCompanyproducttypeCollection1Companyproducttype);
                }
            }
            for (Crmcampaign crmcampaignCollectionCrmcampaign : companyemployee.getCrmcampaignCollection()) {
                Companyemployee oldCompanyemployeeOfCrmcampaignCollectionCrmcampaign = crmcampaignCollectionCrmcampaign.getCompanyemployee();
                crmcampaignCollectionCrmcampaign.setCompanyemployee(companyemployee);
                crmcampaignCollectionCrmcampaign = em.merge(crmcampaignCollectionCrmcampaign);
                if (oldCompanyemployeeOfCrmcampaignCollectionCrmcampaign != null) {
                    oldCompanyemployeeOfCrmcampaignCollectionCrmcampaign.getCrmcampaignCollection().remove(crmcampaignCollectionCrmcampaign);
                    oldCompanyemployeeOfCrmcampaignCollectionCrmcampaign = em.merge(oldCompanyemployeeOfCrmcampaignCollectionCrmcampaign);
                }
            }
            for (Crmcampaign crmcampaignCollection1Crmcampaign : companyemployee.getCrmcampaignCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmcampaignCollection1Crmcampaign = crmcampaignCollection1Crmcampaign.getCompanyemployee1();
                crmcampaignCollection1Crmcampaign.setCompanyemployee1(companyemployee);
                crmcampaignCollection1Crmcampaign = em.merge(crmcampaignCollection1Crmcampaign);
                if (oldCompanyemployee1OfCrmcampaignCollection1Crmcampaign != null) {
                    oldCompanyemployee1OfCrmcampaignCollection1Crmcampaign.getCrmcampaignCollection1().remove(crmcampaignCollection1Crmcampaign);
                    oldCompanyemployee1OfCrmcampaignCollection1Crmcampaign = em.merge(oldCompanyemployee1OfCrmcampaignCollection1Crmcampaign);
                }
            }
            for (Crmmessagehistory crmmessagehistoryCollectionCrmmessagehistory : companyemployee.getCrmmessagehistoryCollection()) {
                Companyemployee oldCompanyemployeeOfCrmmessagehistoryCollectionCrmmessagehistory = crmmessagehistoryCollectionCrmmessagehistory.getCompanyemployee();
                crmmessagehistoryCollectionCrmmessagehistory.setCompanyemployee(companyemployee);
                crmmessagehistoryCollectionCrmmessagehistory = em.merge(crmmessagehistoryCollectionCrmmessagehistory);
                if (oldCompanyemployeeOfCrmmessagehistoryCollectionCrmmessagehistory != null) {
                    oldCompanyemployeeOfCrmmessagehistoryCollectionCrmmessagehistory.getCrmmessagehistoryCollection().remove(crmmessagehistoryCollectionCrmmessagehistory);
                    oldCompanyemployeeOfCrmmessagehistoryCollectionCrmmessagehistory = em.merge(oldCompanyemployeeOfCrmmessagehistoryCollectionCrmmessagehistory);
                }
            }
            for (Crmmessagehistory crmmessagehistoryCollection1Crmmessagehistory : companyemployee.getCrmmessagehistoryCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmmessagehistoryCollection1Crmmessagehistory = crmmessagehistoryCollection1Crmmessagehistory.getCompanyemployee1();
                crmmessagehistoryCollection1Crmmessagehistory.setCompanyemployee1(companyemployee);
                crmmessagehistoryCollection1Crmmessagehistory = em.merge(crmmessagehistoryCollection1Crmmessagehistory);
                if (oldCompanyemployee1OfCrmmessagehistoryCollection1Crmmessagehistory != null) {
                    oldCompanyemployee1OfCrmmessagehistoryCollection1Crmmessagehistory.getCrmmessagehistoryCollection1().remove(crmmessagehistoryCollection1Crmmessagehistory);
                    oldCompanyemployee1OfCrmmessagehistoryCollection1Crmmessagehistory = em.merge(oldCompanyemployee1OfCrmmessagehistoryCollection1Crmmessagehistory);
                }
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollectionCompanyaccountstackdocs : companyemployee.getCompanyaccountstackdocsCollection()) {
                Companyemployee oldCompanyemployeeOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs = companyaccountstackdocsCollectionCompanyaccountstackdocs.getCompanyemployee();
                companyaccountstackdocsCollectionCompanyaccountstackdocs.setCompanyemployee(companyemployee);
                companyaccountstackdocsCollectionCompanyaccountstackdocs = em.merge(companyaccountstackdocsCollectionCompanyaccountstackdocs);
                if (oldCompanyemployeeOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs != null) {
                    oldCompanyemployeeOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocsCollectionCompanyaccountstackdocs);
                    oldCompanyemployeeOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs = em.merge(oldCompanyemployeeOfCompanyaccountstackdocsCollectionCompanyaccountstackdocs);
                }
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollection1Companyaccountstackdocs : companyemployee.getCompanyaccountstackdocsCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanyaccountstackdocsCollection1Companyaccountstackdocs = companyaccountstackdocsCollection1Companyaccountstackdocs.getCompanyemployee1();
                companyaccountstackdocsCollection1Companyaccountstackdocs.setCompanyemployee1(companyemployee);
                companyaccountstackdocsCollection1Companyaccountstackdocs = em.merge(companyaccountstackdocsCollection1Companyaccountstackdocs);
                if (oldCompanyemployee1OfCompanyaccountstackdocsCollection1Companyaccountstackdocs != null) {
                    oldCompanyemployee1OfCompanyaccountstackdocsCollection1Companyaccountstackdocs.getCompanyaccountstackdocsCollection1().remove(companyaccountstackdocsCollection1Companyaccountstackdocs);
                    oldCompanyemployee1OfCompanyaccountstackdocsCollection1Companyaccountstackdocs = em.merge(oldCompanyemployee1OfCompanyaccountstackdocsCollection1Companyaccountstackdocs);
                }
            }
            for (Crmlabor crmlaborCollectionCrmlabor : companyemployee.getCrmlaborCollection()) {
                Companyemployee oldCompanyemployeeOfCrmlaborCollectionCrmlabor = crmlaborCollectionCrmlabor.getCompanyemployee();
                crmlaborCollectionCrmlabor.setCompanyemployee(companyemployee);
                crmlaborCollectionCrmlabor = em.merge(crmlaborCollectionCrmlabor);
                if (oldCompanyemployeeOfCrmlaborCollectionCrmlabor != null) {
                    oldCompanyemployeeOfCrmlaborCollectionCrmlabor.getCrmlaborCollection().remove(crmlaborCollectionCrmlabor);
                    oldCompanyemployeeOfCrmlaborCollectionCrmlabor = em.merge(oldCompanyemployeeOfCrmlaborCollectionCrmlabor);
                }
            }
            for (Crmlabor crmlaborCollection1Crmlabor : companyemployee.getCrmlaborCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmlaborCollection1Crmlabor = crmlaborCollection1Crmlabor.getCompanyemployee1();
                crmlaborCollection1Crmlabor.setCompanyemployee1(companyemployee);
                crmlaborCollection1Crmlabor = em.merge(crmlaborCollection1Crmlabor);
                if (oldCompanyemployee1OfCrmlaborCollection1Crmlabor != null) {
                    oldCompanyemployee1OfCrmlaborCollection1Crmlabor.getCrmlaborCollection1().remove(crmlaborCollection1Crmlabor);
                    oldCompanyemployee1OfCrmlaborCollection1Crmlabor = em.merge(oldCompanyemployee1OfCrmlaborCollection1Crmlabor);
                }
            }
            for (Customerbank customerbankCollectionCustomerbank : companyemployee.getCustomerbankCollection()) {
                Companyemployee oldCompanyemployeeOfCustomerbankCollectionCustomerbank = customerbankCollectionCustomerbank.getCompanyemployee();
                customerbankCollectionCustomerbank.setCompanyemployee(companyemployee);
                customerbankCollectionCustomerbank = em.merge(customerbankCollectionCustomerbank);
                if (oldCompanyemployeeOfCustomerbankCollectionCustomerbank != null) {
                    oldCompanyemployeeOfCustomerbankCollectionCustomerbank.getCustomerbankCollection().remove(customerbankCollectionCustomerbank);
                    oldCompanyemployeeOfCustomerbankCollectionCustomerbank = em.merge(oldCompanyemployeeOfCustomerbankCollectionCustomerbank);
                }
            }
            for (Customerbank customerbankCollection1Customerbank : companyemployee.getCustomerbankCollection1()) {
                Companyemployee oldCompanyemployee1OfCustomerbankCollection1Customerbank = customerbankCollection1Customerbank.getCompanyemployee1();
                customerbankCollection1Customerbank.setCompanyemployee1(companyemployee);
                customerbankCollection1Customerbank = em.merge(customerbankCollection1Customerbank);
                if (oldCompanyemployee1OfCustomerbankCollection1Customerbank != null) {
                    oldCompanyemployee1OfCustomerbankCollection1Customerbank.getCustomerbankCollection1().remove(customerbankCollection1Customerbank);
                    oldCompanyemployee1OfCustomerbankCollection1Customerbank = em.merge(oldCompanyemployee1OfCustomerbankCollection1Customerbank);
                }
            }
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionCompnaypaymentcurrency : companyemployee.getCompnaypaymentcurrencyCollection()) {
                Companyemployee oldCompanyemployeeOfCompnaypaymentcurrencyCollectionCompnaypaymentcurrency = compnaypaymentcurrencyCollectionCompnaypaymentcurrency.getCompanyemployee();
                compnaypaymentcurrencyCollectionCompnaypaymentcurrency.setCompanyemployee(companyemployee);
                compnaypaymentcurrencyCollectionCompnaypaymentcurrency = em.merge(compnaypaymentcurrencyCollectionCompnaypaymentcurrency);
                if (oldCompanyemployeeOfCompnaypaymentcurrencyCollectionCompnaypaymentcurrency != null) {
                    oldCompanyemployeeOfCompnaypaymentcurrencyCollectionCompnaypaymentcurrency.getCompnaypaymentcurrencyCollection().remove(compnaypaymentcurrencyCollectionCompnaypaymentcurrency);
                    oldCompanyemployeeOfCompnaypaymentcurrencyCollectionCompnaypaymentcurrency = em.merge(oldCompanyemployeeOfCompnaypaymentcurrencyCollectionCompnaypaymentcurrency);
                }
            }
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollection1Compnaypaymentcurrency : companyemployee.getCompnaypaymentcurrencyCollection1()) {
                Companyemployee oldCompanyemployee1OfCompnaypaymentcurrencyCollection1Compnaypaymentcurrency = compnaypaymentcurrencyCollection1Compnaypaymentcurrency.getCompanyemployee1();
                compnaypaymentcurrencyCollection1Compnaypaymentcurrency.setCompanyemployee1(companyemployee);
                compnaypaymentcurrencyCollection1Compnaypaymentcurrency = em.merge(compnaypaymentcurrencyCollection1Compnaypaymentcurrency);
                if (oldCompanyemployee1OfCompnaypaymentcurrencyCollection1Compnaypaymentcurrency != null) {
                    oldCompanyemployee1OfCompnaypaymentcurrencyCollection1Compnaypaymentcurrency.getCompnaypaymentcurrencyCollection1().remove(compnaypaymentcurrencyCollection1Compnaypaymentcurrency);
                    oldCompanyemployee1OfCompnaypaymentcurrencyCollection1Compnaypaymentcurrency = em.merge(oldCompanyemployee1OfCompnaypaymentcurrencyCollection1Compnaypaymentcurrency);
                }
            }
            for (Crmproject crmprojectCollectionCrmproject : companyemployee.getCrmprojectCollection()) {
                Companyemployee oldCompanyemployeeOfCrmprojectCollectionCrmproject = crmprojectCollectionCrmproject.getCompanyemployee();
                crmprojectCollectionCrmproject.setCompanyemployee(companyemployee);
                crmprojectCollectionCrmproject = em.merge(crmprojectCollectionCrmproject);
                if (oldCompanyemployeeOfCrmprojectCollectionCrmproject != null) {
                    oldCompanyemployeeOfCrmprojectCollectionCrmproject.getCrmprojectCollection().remove(crmprojectCollectionCrmproject);
                    oldCompanyemployeeOfCrmprojectCollectionCrmproject = em.merge(oldCompanyemployeeOfCrmprojectCollectionCrmproject);
                }
            }
            for (Crmproject crmprojectCollection1Crmproject : companyemployee.getCrmprojectCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmprojectCollection1Crmproject = crmprojectCollection1Crmproject.getCompanyemployee1();
                crmprojectCollection1Crmproject.setCompanyemployee1(companyemployee);
                crmprojectCollection1Crmproject = em.merge(crmprojectCollection1Crmproject);
                if (oldCompanyemployee1OfCrmprojectCollection1Crmproject != null) {
                    oldCompanyemployee1OfCrmprojectCollection1Crmproject.getCrmprojectCollection1().remove(crmprojectCollection1Crmproject);
                    oldCompanyemployee1OfCrmprojectCollection1Crmproject = em.merge(oldCompanyemployee1OfCrmprojectCollection1Crmproject);
                }
            }
            for (Crmproject crmprojectCollection2Crmproject : companyemployee.getCrmprojectCollection2()) {
                Companyemployee oldCompanyemployee2OfCrmprojectCollection2Crmproject = crmprojectCollection2Crmproject.getCompanyemployee2();
                crmprojectCollection2Crmproject.setCompanyemployee2(companyemployee);
                crmprojectCollection2Crmproject = em.merge(crmprojectCollection2Crmproject);
                if (oldCompanyemployee2OfCrmprojectCollection2Crmproject != null) {
                    oldCompanyemployee2OfCrmprojectCollection2Crmproject.getCrmprojectCollection2().remove(crmprojectCollection2Crmproject);
                    oldCompanyemployee2OfCrmprojectCollection2Crmproject = em.merge(oldCompanyemployee2OfCrmprojectCollection2Crmproject);
                }
            }
            for (Companyemployeeaddress companyemployeeaddressCollectionCompanyemployeeaddress : companyemployee.getCompanyemployeeaddressCollection()) {
                Companyemployee oldCompanyemployeeOfCompanyemployeeaddressCollectionCompanyemployeeaddress = companyemployeeaddressCollectionCompanyemployeeaddress.getCompanyemployee();
                companyemployeeaddressCollectionCompanyemployeeaddress.setCompanyemployee(companyemployee);
                companyemployeeaddressCollectionCompanyemployeeaddress = em.merge(companyemployeeaddressCollectionCompanyemployeeaddress);
                if (oldCompanyemployeeOfCompanyemployeeaddressCollectionCompanyemployeeaddress != null) {
                    oldCompanyemployeeOfCompanyemployeeaddressCollectionCompanyemployeeaddress.getCompanyemployeeaddressCollection().remove(companyemployeeaddressCollectionCompanyemployeeaddress);
                    oldCompanyemployeeOfCompanyemployeeaddressCollectionCompanyemployeeaddress = em.merge(oldCompanyemployeeOfCompanyemployeeaddressCollectionCompanyemployeeaddress);
                }
            }
            for (Companyemployeeaddress companyemployeeaddressCollection1Companyemployeeaddress : companyemployee.getCompanyemployeeaddressCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanyemployeeaddressCollection1Companyemployeeaddress = companyemployeeaddressCollection1Companyemployeeaddress.getCompanyemployee1();
                companyemployeeaddressCollection1Companyemployeeaddress.setCompanyemployee1(companyemployee);
                companyemployeeaddressCollection1Companyemployeeaddress = em.merge(companyemployeeaddressCollection1Companyemployeeaddress);
                if (oldCompanyemployee1OfCompanyemployeeaddressCollection1Companyemployeeaddress != null) {
                    oldCompanyemployee1OfCompanyemployeeaddressCollection1Companyemployeeaddress.getCompanyemployeeaddressCollection1().remove(companyemployeeaddressCollection1Companyemployeeaddress);
                    oldCompanyemployee1OfCompanyemployeeaddressCollection1Companyemployeeaddress = em.merge(oldCompanyemployee1OfCompanyemployeeaddressCollection1Companyemployeeaddress);
                }
            }
            for (Companyemployeeaddress companyemployeeaddressCollection2Companyemployeeaddress : companyemployee.getCompanyemployeeaddressCollection2()) {
                Companyemployee oldCompanyemployee2OfCompanyemployeeaddressCollection2Companyemployeeaddress = companyemployeeaddressCollection2Companyemployeeaddress.getCompanyemployee2();
                companyemployeeaddressCollection2Companyemployeeaddress.setCompanyemployee2(companyemployee);
                companyemployeeaddressCollection2Companyemployeeaddress = em.merge(companyemployeeaddressCollection2Companyemployeeaddress);
                if (oldCompanyemployee2OfCompanyemployeeaddressCollection2Companyemployeeaddress != null) {
                    oldCompanyemployee2OfCompanyemployeeaddressCollection2Companyemployeeaddress.getCompanyemployeeaddressCollection2().remove(companyemployeeaddressCollection2Companyemployeeaddress);
                    oldCompanyemployee2OfCompanyemployeeaddressCollection2Companyemployeeaddress = em.merge(oldCompanyemployee2OfCompanyemployeeaddressCollection2Companyemployeeaddress);
                }
            }
            for (Crmforumdocs crmforumdocsCollectionCrmforumdocs : companyemployee.getCrmforumdocsCollection()) {
                Companyemployee oldCompanyemployeeOfCrmforumdocsCollectionCrmforumdocs = crmforumdocsCollectionCrmforumdocs.getCompanyemployee();
                crmforumdocsCollectionCrmforumdocs.setCompanyemployee(companyemployee);
                crmforumdocsCollectionCrmforumdocs = em.merge(crmforumdocsCollectionCrmforumdocs);
                if (oldCompanyemployeeOfCrmforumdocsCollectionCrmforumdocs != null) {
                    oldCompanyemployeeOfCrmforumdocsCollectionCrmforumdocs.getCrmforumdocsCollection().remove(crmforumdocsCollectionCrmforumdocs);
                    oldCompanyemployeeOfCrmforumdocsCollectionCrmforumdocs = em.merge(oldCompanyemployeeOfCrmforumdocsCollectionCrmforumdocs);
                }
            }
            for (Companyproduct companyproductCollectionCompanyproduct : companyemployee.getCompanyproductCollection()) {
                Companyemployee oldCompanyemployeeOfCompanyproductCollectionCompanyproduct = companyproductCollectionCompanyproduct.getCompanyemployee();
                companyproductCollectionCompanyproduct.setCompanyemployee(companyemployee);
                companyproductCollectionCompanyproduct = em.merge(companyproductCollectionCompanyproduct);
                if (oldCompanyemployeeOfCompanyproductCollectionCompanyproduct != null) {
                    oldCompanyemployeeOfCompanyproductCollectionCompanyproduct.getCompanyproductCollection().remove(companyproductCollectionCompanyproduct);
                    oldCompanyemployeeOfCompanyproductCollectionCompanyproduct = em.merge(oldCompanyemployeeOfCompanyproductCollectionCompanyproduct);
                }
            }
            for (Companyproduct companyproductCollection1Companyproduct : companyemployee.getCompanyproductCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanyproductCollection1Companyproduct = companyproductCollection1Companyproduct.getCompanyemployee1();
                companyproductCollection1Companyproduct.setCompanyemployee1(companyemployee);
                companyproductCollection1Companyproduct = em.merge(companyproductCollection1Companyproduct);
                if (oldCompanyemployee1OfCompanyproductCollection1Companyproduct != null) {
                    oldCompanyemployee1OfCompanyproductCollection1Companyproduct.getCompanyproductCollection1().remove(companyproductCollection1Companyproduct);
                    oldCompanyemployee1OfCompanyproductCollection1Companyproduct = em.merge(oldCompanyemployee1OfCompanyproductCollection1Companyproduct);
                }
            }
            for (Employeedesignation employeedesignationCollectionEmployeedesignation : companyemployee.getEmployeedesignationCollection()) {
                Companyemployee oldCompanyemployeeOfEmployeedesignationCollectionEmployeedesignation = employeedesignationCollectionEmployeedesignation.getCompanyemployee();
                employeedesignationCollectionEmployeedesignation.setCompanyemployee(companyemployee);
                employeedesignationCollectionEmployeedesignation = em.merge(employeedesignationCollectionEmployeedesignation);
                if (oldCompanyemployeeOfEmployeedesignationCollectionEmployeedesignation != null) {
                    oldCompanyemployeeOfEmployeedesignationCollectionEmployeedesignation.getEmployeedesignationCollection().remove(employeedesignationCollectionEmployeedesignation);
                    oldCompanyemployeeOfEmployeedesignationCollectionEmployeedesignation = em.merge(oldCompanyemployeeOfEmployeedesignationCollectionEmployeedesignation);
                }
            }
            for (Employeedesignation employeedesignationCollection1Employeedesignation : companyemployee.getEmployeedesignationCollection1()) {
                Companyemployee oldCompanyemployee1OfEmployeedesignationCollection1Employeedesignation = employeedesignationCollection1Employeedesignation.getCompanyemployee1();
                employeedesignationCollection1Employeedesignation.setCompanyemployee1(companyemployee);
                employeedesignationCollection1Employeedesignation = em.merge(employeedesignationCollection1Employeedesignation);
                if (oldCompanyemployee1OfEmployeedesignationCollection1Employeedesignation != null) {
                    oldCompanyemployee1OfEmployeedesignationCollection1Employeedesignation.getEmployeedesignationCollection1().remove(employeedesignationCollection1Employeedesignation);
                    oldCompanyemployee1OfEmployeedesignationCollection1Employeedesignation = em.merge(oldCompanyemployee1OfEmployeedesignationCollection1Employeedesignation);
                }
            }
            for (Companyaccountstack companyaccountstackCollectionCompanyaccountstack : companyemployee.getCompanyaccountstackCollection()) {
                Companyemployee oldCompanyemployeeOfCompanyaccountstackCollectionCompanyaccountstack = companyaccountstackCollectionCompanyaccountstack.getCompanyemployee();
                companyaccountstackCollectionCompanyaccountstack.setCompanyemployee(companyemployee);
                companyaccountstackCollectionCompanyaccountstack = em.merge(companyaccountstackCollectionCompanyaccountstack);
                if (oldCompanyemployeeOfCompanyaccountstackCollectionCompanyaccountstack != null) {
                    oldCompanyemployeeOfCompanyaccountstackCollectionCompanyaccountstack.getCompanyaccountstackCollection().remove(companyaccountstackCollectionCompanyaccountstack);
                    oldCompanyemployeeOfCompanyaccountstackCollectionCompanyaccountstack = em.merge(oldCompanyemployeeOfCompanyaccountstackCollectionCompanyaccountstack);
                }
            }
            for (Companyaccountstack companyaccountstackCollection1Companyaccountstack : companyemployee.getCompanyaccountstackCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanyaccountstackCollection1Companyaccountstack = companyaccountstackCollection1Companyaccountstack.getCompanyemployee1();
                companyaccountstackCollection1Companyaccountstack.setCompanyemployee1(companyemployee);
                companyaccountstackCollection1Companyaccountstack = em.merge(companyaccountstackCollection1Companyaccountstack);
                if (oldCompanyemployee1OfCompanyaccountstackCollection1Companyaccountstack != null) {
                    oldCompanyemployee1OfCompanyaccountstackCollection1Companyaccountstack.getCompanyaccountstackCollection1().remove(companyaccountstackCollection1Companyaccountstack);
                    oldCompanyemployee1OfCompanyaccountstackCollection1Companyaccountstack = em.merge(oldCompanyemployee1OfCompanyaccountstackCollection1Companyaccountstack);
                }
            }
            for (Crmprojectteammembers crmprojectteammembersCollectionCrmprojectteammembers : companyemployee.getCrmprojectteammembersCollection()) {
                Companyemployee oldCompanyemployeeOfCrmprojectteammembersCollectionCrmprojectteammembers = crmprojectteammembersCollectionCrmprojectteammembers.getCompanyemployee();
                crmprojectteammembersCollectionCrmprojectteammembers.setCompanyemployee(companyemployee);
                crmprojectteammembersCollectionCrmprojectteammembers = em.merge(crmprojectteammembersCollectionCrmprojectteammembers);
                if (oldCompanyemployeeOfCrmprojectteammembersCollectionCrmprojectteammembers != null) {
                    oldCompanyemployeeOfCrmprojectteammembersCollectionCrmprojectteammembers.getCrmprojectteammembersCollection().remove(crmprojectteammembersCollectionCrmprojectteammembers);
                    oldCompanyemployeeOfCrmprojectteammembersCollectionCrmprojectteammembers = em.merge(oldCompanyemployeeOfCrmprojectteammembersCollectionCrmprojectteammembers);
                }
            }
            for (Crmprojectteammembers crmprojectteammembersCollection1Crmprojectteammembers : companyemployee.getCrmprojectteammembersCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmprojectteammembersCollection1Crmprojectteammembers = crmprojectteammembersCollection1Crmprojectteammembers.getCompanyemployee1();
                crmprojectteammembersCollection1Crmprojectteammembers.setCompanyemployee1(companyemployee);
                crmprojectteammembersCollection1Crmprojectteammembers = em.merge(crmprojectteammembersCollection1Crmprojectteammembers);
                if (oldCompanyemployee1OfCrmprojectteammembersCollection1Crmprojectteammembers != null) {
                    oldCompanyemployee1OfCrmprojectteammembersCollection1Crmprojectteammembers.getCrmprojectteammembersCollection1().remove(crmprojectteammembersCollection1Crmprojectteammembers);
                    oldCompanyemployee1OfCrmprojectteammembersCollection1Crmprojectteammembers = em.merge(oldCompanyemployee1OfCrmprojectteammembersCollection1Crmprojectteammembers);
                }
            }
            for (Crmprojectteammembers crmprojectteammembersCollection2Crmprojectteammembers : companyemployee.getCrmprojectteammembersCollection2()) {
                Companyemployee oldCompanyemployee2OfCrmprojectteammembersCollection2Crmprojectteammembers = crmprojectteammembersCollection2Crmprojectteammembers.getCompanyemployee2();
                crmprojectteammembersCollection2Crmprojectteammembers.setCompanyemployee2(companyemployee);
                crmprojectteammembersCollection2Crmprojectteammembers = em.merge(crmprojectteammembersCollection2Crmprojectteammembers);
                if (oldCompanyemployee2OfCrmprojectteammembersCollection2Crmprojectteammembers != null) {
                    oldCompanyemployee2OfCrmprojectteammembersCollection2Crmprojectteammembers.getCrmprojectteammembersCollection2().remove(crmprojectteammembersCollection2Crmprojectteammembers);
                    oldCompanyemployee2OfCrmprojectteammembersCollection2Crmprojectteammembers = em.merge(oldCompanyemployee2OfCrmprojectteammembersCollection2Crmprojectteammembers);
                }
            }
            for (Componentattachments componentattachmentsCollectionComponentattachments : companyemployee.getComponentattachmentsCollection()) {
                Companyemployee oldCompanyemployeeOfComponentattachmentsCollectionComponentattachments = componentattachmentsCollectionComponentattachments.getCompanyemployee();
                componentattachmentsCollectionComponentattachments.setCompanyemployee(companyemployee);
                componentattachmentsCollectionComponentattachments = em.merge(componentattachmentsCollectionComponentattachments);
                if (oldCompanyemployeeOfComponentattachmentsCollectionComponentattachments != null) {
                    oldCompanyemployeeOfComponentattachmentsCollectionComponentattachments.getComponentattachmentsCollection().remove(componentattachmentsCollectionComponentattachments);
                    oldCompanyemployeeOfComponentattachmentsCollectionComponentattachments = em.merge(oldCompanyemployeeOfComponentattachmentsCollectionComponentattachments);
                }
            }
            for (Componentattachments componentattachmentsCollection1Componentattachments : companyemployee.getComponentattachmentsCollection1()) {
                Companyemployee oldCompanyemployee1OfComponentattachmentsCollection1Componentattachments = componentattachmentsCollection1Componentattachments.getCompanyemployee1();
                componentattachmentsCollection1Componentattachments.setCompanyemployee1(companyemployee);
                componentattachmentsCollection1Componentattachments = em.merge(componentattachmentsCollection1Componentattachments);
                if (oldCompanyemployee1OfComponentattachmentsCollection1Componentattachments != null) {
                    oldCompanyemployee1OfComponentattachmentsCollection1Componentattachments.getComponentattachmentsCollection1().remove(componentattachmentsCollection1Componentattachments);
                    oldCompanyemployee1OfComponentattachmentsCollection1Componentattachments = em.merge(oldCompanyemployee1OfComponentattachmentsCollection1Componentattachments);
                }
            }
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress : companyemployee.getCrmvisitorcontactsaddressCollection()) {
                Companyemployee oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress = crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress.getCompanyemployee();
                crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress.setCompanyemployee(companyemployee);
                crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress = em.merge(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress);
                if (oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress != null) {
                    oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress);
                    oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress = em.merge(oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionCrmvisitorcontactsaddress);
                }
            }
            for (Crmvisitorcontacts crmvisitorcontactsCollectionCrmvisitorcontacts : companyemployee.getCrmvisitorcontactsCollection()) {
                Companyemployee oldCompanyemployeeOfCrmvisitorcontactsCollectionCrmvisitorcontacts = crmvisitorcontactsCollectionCrmvisitorcontacts.getCompanyemployee();
                crmvisitorcontactsCollectionCrmvisitorcontacts.setCompanyemployee(companyemployee);
                crmvisitorcontactsCollectionCrmvisitorcontacts = em.merge(crmvisitorcontactsCollectionCrmvisitorcontacts);
                if (oldCompanyemployeeOfCrmvisitorcontactsCollectionCrmvisitorcontacts != null) {
                    oldCompanyemployeeOfCrmvisitorcontactsCollectionCrmvisitorcontacts.getCrmvisitorcontactsCollection().remove(crmvisitorcontactsCollectionCrmvisitorcontacts);
                    oldCompanyemployeeOfCrmvisitorcontactsCollectionCrmvisitorcontacts = em.merge(oldCompanyemployeeOfCrmvisitorcontactsCollectionCrmvisitorcontacts);
                }
            }
            for (Crmvisitorcontacts crmvisitorcontactsCollection1Crmvisitorcontacts : companyemployee.getCrmvisitorcontactsCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmvisitorcontactsCollection1Crmvisitorcontacts = crmvisitorcontactsCollection1Crmvisitorcontacts.getCompanyemployee1();
                crmvisitorcontactsCollection1Crmvisitorcontacts.setCompanyemployee1(companyemployee);
                crmvisitorcontactsCollection1Crmvisitorcontacts = em.merge(crmvisitorcontactsCollection1Crmvisitorcontacts);
                if (oldCompanyemployee1OfCrmvisitorcontactsCollection1Crmvisitorcontacts != null) {
                    oldCompanyemployee1OfCrmvisitorcontactsCollection1Crmvisitorcontacts.getCrmvisitorcontactsCollection1().remove(crmvisitorcontactsCollection1Crmvisitorcontacts);
                    oldCompanyemployee1OfCrmvisitorcontactsCollection1Crmvisitorcontacts = em.merge(oldCompanyemployee1OfCrmvisitorcontactsCollection1Crmvisitorcontacts);
                }
            }
            for (Companyaccountstackcd companyaccountstackcdCollectionCompanyaccountstackcd : companyemployee.getCompanyaccountstackcdCollection()) {
                Companyemployee oldCompanyemployeeOfCompanyaccountstackcdCollectionCompanyaccountstackcd = companyaccountstackcdCollectionCompanyaccountstackcd.getCompanyemployee();
                companyaccountstackcdCollectionCompanyaccountstackcd.setCompanyemployee(companyemployee);
                companyaccountstackcdCollectionCompanyaccountstackcd = em.merge(companyaccountstackcdCollectionCompanyaccountstackcd);
                if (oldCompanyemployeeOfCompanyaccountstackcdCollectionCompanyaccountstackcd != null) {
                    oldCompanyemployeeOfCompanyaccountstackcdCollectionCompanyaccountstackcd.getCompanyaccountstackcdCollection().remove(companyaccountstackcdCollectionCompanyaccountstackcd);
                    oldCompanyemployeeOfCompanyaccountstackcdCollectionCompanyaccountstackcd = em.merge(oldCompanyemployeeOfCompanyaccountstackcdCollectionCompanyaccountstackcd);
                }
            }
            for (Companyaccountstackcd companyaccountstackcdCollection1Companyaccountstackcd : companyemployee.getCompanyaccountstackcdCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanyaccountstackcdCollection1Companyaccountstackcd = companyaccountstackcdCollection1Companyaccountstackcd.getCompanyemployee1();
                companyaccountstackcdCollection1Companyaccountstackcd.setCompanyemployee1(companyemployee);
                companyaccountstackcdCollection1Companyaccountstackcd = em.merge(companyaccountstackcdCollection1Companyaccountstackcd);
                if (oldCompanyemployee1OfCompanyaccountstackcdCollection1Companyaccountstackcd != null) {
                    oldCompanyemployee1OfCompanyaccountstackcdCollection1Companyaccountstackcd.getCompanyaccountstackcdCollection1().remove(companyaccountstackcdCollection1Companyaccountstackcd);
                    oldCompanyemployee1OfCompanyaccountstackcdCollection1Companyaccountstackcd = em.merge(oldCompanyemployee1OfCompanyaccountstackcdCollection1Companyaccountstackcd);
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionCrmprojectticketmanagement : companyemployee.getCrmprojectticketmanagementCollection()) {
                Companyemployee oldCompanyemployeeOfCrmprojectticketmanagementCollectionCrmprojectticketmanagement = crmprojectticketmanagementCollectionCrmprojectticketmanagement.getCompanyemployee();
                crmprojectticketmanagementCollectionCrmprojectticketmanagement.setCompanyemployee(companyemployee);
                crmprojectticketmanagementCollectionCrmprojectticketmanagement = em.merge(crmprojectticketmanagementCollectionCrmprojectticketmanagement);
                if (oldCompanyemployeeOfCrmprojectticketmanagementCollectionCrmprojectticketmanagement != null) {
                    oldCompanyemployeeOfCrmprojectticketmanagementCollectionCrmprojectticketmanagement.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagementCollectionCrmprojectticketmanagement);
                    oldCompanyemployeeOfCrmprojectticketmanagementCollectionCrmprojectticketmanagement = em.merge(oldCompanyemployeeOfCrmprojectticketmanagementCollectionCrmprojectticketmanagement);
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1Crmprojectticketmanagement : companyemployee.getCrmprojectticketmanagementCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmprojectticketmanagementCollection1Crmprojectticketmanagement = crmprojectticketmanagementCollection1Crmprojectticketmanagement.getCompanyemployee1();
                crmprojectticketmanagementCollection1Crmprojectticketmanagement.setCompanyemployee1(companyemployee);
                crmprojectticketmanagementCollection1Crmprojectticketmanagement = em.merge(crmprojectticketmanagementCollection1Crmprojectticketmanagement);
                if (oldCompanyemployee1OfCrmprojectticketmanagementCollection1Crmprojectticketmanagement != null) {
                    oldCompanyemployee1OfCrmprojectticketmanagementCollection1Crmprojectticketmanagement.getCrmprojectticketmanagementCollection1().remove(crmprojectticketmanagementCollection1Crmprojectticketmanagement);
                    oldCompanyemployee1OfCrmprojectticketmanagementCollection1Crmprojectticketmanagement = em.merge(oldCompanyemployee1OfCrmprojectticketmanagementCollection1Crmprojectticketmanagement);
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection2Crmprojectticketmanagement : companyemployee.getCrmprojectticketmanagementCollection2()) {
                Companyemployee oldCompanyemployee2OfCrmprojectticketmanagementCollection2Crmprojectticketmanagement = crmprojectticketmanagementCollection2Crmprojectticketmanagement.getCompanyemployee2();
                crmprojectticketmanagementCollection2Crmprojectticketmanagement.setCompanyemployee2(companyemployee);
                crmprojectticketmanagementCollection2Crmprojectticketmanagement = em.merge(crmprojectticketmanagementCollection2Crmprojectticketmanagement);
                if (oldCompanyemployee2OfCrmprojectticketmanagementCollection2Crmprojectticketmanagement != null) {
                    oldCompanyemployee2OfCrmprojectticketmanagementCollection2Crmprojectticketmanagement.getCrmprojectticketmanagementCollection2().remove(crmprojectticketmanagementCollection2Crmprojectticketmanagement);
                    oldCompanyemployee2OfCrmprojectticketmanagementCollection2Crmprojectticketmanagement = em.merge(oldCompanyemployee2OfCrmprojectticketmanagementCollection2Crmprojectticketmanagement);
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection3Crmprojectticketmanagement : companyemployee.getCrmprojectticketmanagementCollection3()) {
                Companyemployee oldCompanyemployee3OfCrmprojectticketmanagementCollection3Crmprojectticketmanagement = crmprojectticketmanagementCollection3Crmprojectticketmanagement.getCompanyemployee3();
                crmprojectticketmanagementCollection3Crmprojectticketmanagement.setCompanyemployee3(companyemployee);
                crmprojectticketmanagementCollection3Crmprojectticketmanagement = em.merge(crmprojectticketmanagementCollection3Crmprojectticketmanagement);
                if (oldCompanyemployee3OfCrmprojectticketmanagementCollection3Crmprojectticketmanagement != null) {
                    oldCompanyemployee3OfCrmprojectticketmanagementCollection3Crmprojectticketmanagement.getCrmprojectticketmanagementCollection3().remove(crmprojectticketmanagementCollection3Crmprojectticketmanagement);
                    oldCompanyemployee3OfCrmprojectticketmanagementCollection3Crmprojectticketmanagement = em.merge(oldCompanyemployee3OfCrmprojectticketmanagementCollection3Crmprojectticketmanagement);
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionCrmprojectticketnotification : companyemployee.getCrmprojectticketnotificationCollection()) {
                Companyemployee oldCompanyemployeeOfCrmprojectticketnotificationCollectionCrmprojectticketnotification = crmprojectticketnotificationCollectionCrmprojectticketnotification.getCompanyemployee();
                crmprojectticketnotificationCollectionCrmprojectticketnotification.setCompanyemployee(companyemployee);
                crmprojectticketnotificationCollectionCrmprojectticketnotification = em.merge(crmprojectticketnotificationCollectionCrmprojectticketnotification);
                if (oldCompanyemployeeOfCrmprojectticketnotificationCollectionCrmprojectticketnotification != null) {
                    oldCompanyemployeeOfCrmprojectticketnotificationCollectionCrmprojectticketnotification.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotificationCollectionCrmprojectticketnotification);
                    oldCompanyemployeeOfCrmprojectticketnotificationCollectionCrmprojectticketnotification = em.merge(oldCompanyemployeeOfCrmprojectticketnotificationCollectionCrmprojectticketnotification);
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollection1Crmprojectticketnotification : companyemployee.getCrmprojectticketnotificationCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmprojectticketnotificationCollection1Crmprojectticketnotification = crmprojectticketnotificationCollection1Crmprojectticketnotification.getCompanyemployee1();
                crmprojectticketnotificationCollection1Crmprojectticketnotification.setCompanyemployee1(companyemployee);
                crmprojectticketnotificationCollection1Crmprojectticketnotification = em.merge(crmprojectticketnotificationCollection1Crmprojectticketnotification);
                if (oldCompanyemployee1OfCrmprojectticketnotificationCollection1Crmprojectticketnotification != null) {
                    oldCompanyemployee1OfCrmprojectticketnotificationCollection1Crmprojectticketnotification.getCrmprojectticketnotificationCollection1().remove(crmprojectticketnotificationCollection1Crmprojectticketnotification);
                    oldCompanyemployee1OfCrmprojectticketnotificationCollection1Crmprojectticketnotification = em.merge(oldCompanyemployee1OfCrmprojectticketnotificationCollection1Crmprojectticketnotification);
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollection2Crmprojectticketnotification : companyemployee.getCrmprojectticketnotificationCollection2()) {
                Companyemployee oldCompanyemployee2OfCrmprojectticketnotificationCollection2Crmprojectticketnotification = crmprojectticketnotificationCollection2Crmprojectticketnotification.getCompanyemployee2();
                crmprojectticketnotificationCollection2Crmprojectticketnotification.setCompanyemployee2(companyemployee);
                crmprojectticketnotificationCollection2Crmprojectticketnotification = em.merge(crmprojectticketnotificationCollection2Crmprojectticketnotification);
                if (oldCompanyemployee2OfCrmprojectticketnotificationCollection2Crmprojectticketnotification != null) {
                    oldCompanyemployee2OfCrmprojectticketnotificationCollection2Crmprojectticketnotification.getCrmprojectticketnotificationCollection2().remove(crmprojectticketnotificationCollection2Crmprojectticketnotification);
                    oldCompanyemployee2OfCrmprojectticketnotificationCollection2Crmprojectticketnotification = em.merge(oldCompanyemployee2OfCrmprojectticketnotificationCollection2Crmprojectticketnotification);
                }
            }
            for (Crmvisitor crmvisitorCollectionCrmvisitor : companyemployee.getCrmvisitorCollection()) {
                Companyemployee oldCompanyemployeeOfCrmvisitorCollectionCrmvisitor = crmvisitorCollectionCrmvisitor.getCompanyemployee();
                crmvisitorCollectionCrmvisitor.setCompanyemployee(companyemployee);
                crmvisitorCollectionCrmvisitor = em.merge(crmvisitorCollectionCrmvisitor);
                if (oldCompanyemployeeOfCrmvisitorCollectionCrmvisitor != null) {
                    oldCompanyemployeeOfCrmvisitorCollectionCrmvisitor.getCrmvisitorCollection().remove(crmvisitorCollectionCrmvisitor);
                    oldCompanyemployeeOfCrmvisitorCollectionCrmvisitor = em.merge(oldCompanyemployeeOfCrmvisitorCollectionCrmvisitor);
                }
            }
            for (Crmvisitor crmvisitorCollection1Crmvisitor : companyemployee.getCrmvisitorCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmvisitorCollection1Crmvisitor = crmvisitorCollection1Crmvisitor.getCompanyemployee1();
                crmvisitorCollection1Crmvisitor.setCompanyemployee1(companyemployee);
                crmvisitorCollection1Crmvisitor = em.merge(crmvisitorCollection1Crmvisitor);
                if (oldCompanyemployee1OfCrmvisitorCollection1Crmvisitor != null) {
                    oldCompanyemployee1OfCrmvisitorCollection1Crmvisitor.getCrmvisitorCollection1().remove(crmvisitorCollection1Crmvisitor);
                    oldCompanyemployee1OfCrmvisitorCollection1Crmvisitor = em.merge(oldCompanyemployee1OfCrmvisitorCollection1Crmvisitor);
                }
            }
            for (Crmworkordertype crmworkordertypeCollectionCrmworkordertype : companyemployee.getCrmworkordertypeCollection()) {
                Companyemployee oldCompanyemployeeOfCrmworkordertypeCollectionCrmworkordertype = crmworkordertypeCollectionCrmworkordertype.getCompanyemployee();
                crmworkordertypeCollectionCrmworkordertype.setCompanyemployee(companyemployee);
                crmworkordertypeCollectionCrmworkordertype = em.merge(crmworkordertypeCollectionCrmworkordertype);
                if (oldCompanyemployeeOfCrmworkordertypeCollectionCrmworkordertype != null) {
                    oldCompanyemployeeOfCrmworkordertypeCollectionCrmworkordertype.getCrmworkordertypeCollection().remove(crmworkordertypeCollectionCrmworkordertype);
                    oldCompanyemployeeOfCrmworkordertypeCollectionCrmworkordertype = em.merge(oldCompanyemployeeOfCrmworkordertypeCollectionCrmworkordertype);
                }
            }
            for (Crmworkordertype crmworkordertypeCollection1Crmworkordertype : companyemployee.getCrmworkordertypeCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmworkordertypeCollection1Crmworkordertype = crmworkordertypeCollection1Crmworkordertype.getCompanyemployee1();
                crmworkordertypeCollection1Crmworkordertype.setCompanyemployee1(companyemployee);
                crmworkordertypeCollection1Crmworkordertype = em.merge(crmworkordertypeCollection1Crmworkordertype);
                if (oldCompanyemployee1OfCrmworkordertypeCollection1Crmworkordertype != null) {
                    oldCompanyemployee1OfCrmworkordertypeCollection1Crmworkordertype.getCrmworkordertypeCollection1().remove(crmworkordertypeCollection1Crmworkordertype);
                    oldCompanyemployee1OfCrmworkordertypeCollection1Crmworkordertype = em.merge(oldCompanyemployee1OfCrmworkordertypeCollection1Crmworkordertype);
                }
            }
            for (Crmlabortype crmlabortypeCollectionCrmlabortype : companyemployee.getCrmlabortypeCollection()) {
                Companyemployee oldCompanyemployeeOfCrmlabortypeCollectionCrmlabortype = crmlabortypeCollectionCrmlabortype.getCompanyemployee();
                crmlabortypeCollectionCrmlabortype.setCompanyemployee(companyemployee);
                crmlabortypeCollectionCrmlabortype = em.merge(crmlabortypeCollectionCrmlabortype);
                if (oldCompanyemployeeOfCrmlabortypeCollectionCrmlabortype != null) {
                    oldCompanyemployeeOfCrmlabortypeCollectionCrmlabortype.getCrmlabortypeCollection().remove(crmlabortypeCollectionCrmlabortype);
                    oldCompanyemployeeOfCrmlabortypeCollectionCrmlabortype = em.merge(oldCompanyemployeeOfCrmlabortypeCollectionCrmlabortype);
                }
            }
            for (Crmlabortype crmlabortypeCollection1Crmlabortype : companyemployee.getCrmlabortypeCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmlabortypeCollection1Crmlabortype = crmlabortypeCollection1Crmlabortype.getCompanyemployee1();
                crmlabortypeCollection1Crmlabortype.setCompanyemployee1(companyemployee);
                crmlabortypeCollection1Crmlabortype = em.merge(crmlabortypeCollection1Crmlabortype);
                if (oldCompanyemployee1OfCrmlabortypeCollection1Crmlabortype != null) {
                    oldCompanyemployee1OfCrmlabortypeCollection1Crmlabortype.getCrmlabortypeCollection1().remove(crmlabortypeCollection1Crmlabortype);
                    oldCompanyemployee1OfCrmlabortypeCollection1Crmlabortype = em.merge(oldCompanyemployee1OfCrmlabortypeCollection1Crmlabortype);
                }
            }
            for (Crmbillingtype crmbillingtypeCollectionCrmbillingtype : companyemployee.getCrmbillingtypeCollection()) {
                Companyemployee oldCompanyemployeeOfCrmbillingtypeCollectionCrmbillingtype = crmbillingtypeCollectionCrmbillingtype.getCompanyemployee();
                crmbillingtypeCollectionCrmbillingtype.setCompanyemployee(companyemployee);
                crmbillingtypeCollectionCrmbillingtype = em.merge(crmbillingtypeCollectionCrmbillingtype);
                if (oldCompanyemployeeOfCrmbillingtypeCollectionCrmbillingtype != null) {
                    oldCompanyemployeeOfCrmbillingtypeCollectionCrmbillingtype.getCrmbillingtypeCollection().remove(crmbillingtypeCollectionCrmbillingtype);
                    oldCompanyemployeeOfCrmbillingtypeCollectionCrmbillingtype = em.merge(oldCompanyemployeeOfCrmbillingtypeCollectionCrmbillingtype);
                }
            }
            for (Crmbillingtype crmbillingtypeCollection1Crmbillingtype : companyemployee.getCrmbillingtypeCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmbillingtypeCollection1Crmbillingtype = crmbillingtypeCollection1Crmbillingtype.getCompanyemployee1();
                crmbillingtypeCollection1Crmbillingtype.setCompanyemployee1(companyemployee);
                crmbillingtypeCollection1Crmbillingtype = em.merge(crmbillingtypeCollection1Crmbillingtype);
                if (oldCompanyemployee1OfCrmbillingtypeCollection1Crmbillingtype != null) {
                    oldCompanyemployee1OfCrmbillingtypeCollection1Crmbillingtype.getCrmbillingtypeCollection1().remove(crmbillingtypeCollection1Crmbillingtype);
                    oldCompanyemployee1OfCrmbillingtypeCollection1Crmbillingtype = em.merge(oldCompanyemployee1OfCrmbillingtypeCollection1Crmbillingtype);
                }
            }
            for (Companyproductcategory companyproductcategoryCollectionCompanyproductcategory : companyemployee.getCompanyproductcategoryCollection()) {
                Companyemployee oldCompanyemployeeOfCompanyproductcategoryCollectionCompanyproductcategory = companyproductcategoryCollectionCompanyproductcategory.getCompanyemployee();
                companyproductcategoryCollectionCompanyproductcategory.setCompanyemployee(companyemployee);
                companyproductcategoryCollectionCompanyproductcategory = em.merge(companyproductcategoryCollectionCompanyproductcategory);
                if (oldCompanyemployeeOfCompanyproductcategoryCollectionCompanyproductcategory != null) {
                    oldCompanyemployeeOfCompanyproductcategoryCollectionCompanyproductcategory.getCompanyproductcategoryCollection().remove(companyproductcategoryCollectionCompanyproductcategory);
                    oldCompanyemployeeOfCompanyproductcategoryCollectionCompanyproductcategory = em.merge(oldCompanyemployeeOfCompanyproductcategoryCollectionCompanyproductcategory);
                }
            }
            for (Companyproductcategory companyproductcategoryCollection1Companyproductcategory : companyemployee.getCompanyproductcategoryCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanyproductcategoryCollection1Companyproductcategory = companyproductcategoryCollection1Companyproductcategory.getCompanyemployee1();
                companyproductcategoryCollection1Companyproductcategory.setCompanyemployee1(companyemployee);
                companyproductcategoryCollection1Companyproductcategory = em.merge(companyproductcategoryCollection1Companyproductcategory);
                if (oldCompanyemployee1OfCompanyproductcategoryCollection1Companyproductcategory != null) {
                    oldCompanyemployee1OfCompanyproductcategoryCollection1Companyproductcategory.getCompanyproductcategoryCollection1().remove(companyproductcategoryCollection1Companyproductcategory);
                    oldCompanyemployee1OfCompanyproductcategoryCollection1Companyproductcategory = em.merge(oldCompanyemployee1OfCompanyproductcategoryCollection1Companyproductcategory);
                }
            }
            for (Crmprojecttask crmprojecttaskCollectionCrmprojecttask : companyemployee.getCrmprojecttaskCollection()) {
                Companyemployee oldCompanyemployeeOfCrmprojecttaskCollectionCrmprojecttask = crmprojecttaskCollectionCrmprojecttask.getCompanyemployee();
                crmprojecttaskCollectionCrmprojecttask.setCompanyemployee(companyemployee);
                crmprojecttaskCollectionCrmprojecttask = em.merge(crmprojecttaskCollectionCrmprojecttask);
                if (oldCompanyemployeeOfCrmprojecttaskCollectionCrmprojecttask != null) {
                    oldCompanyemployeeOfCrmprojecttaskCollectionCrmprojecttask.getCrmprojecttaskCollection().remove(crmprojecttaskCollectionCrmprojecttask);
                    oldCompanyemployeeOfCrmprojecttaskCollectionCrmprojecttask = em.merge(oldCompanyemployeeOfCrmprojecttaskCollectionCrmprojecttask);
                }
            }
            for (Crmprojecttask crmprojecttaskCollection1Crmprojecttask : companyemployee.getCrmprojecttaskCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmprojecttaskCollection1Crmprojecttask = crmprojecttaskCollection1Crmprojecttask.getCompanyemployee1();
                crmprojecttaskCollection1Crmprojecttask.setCompanyemployee1(companyemployee);
                crmprojecttaskCollection1Crmprojecttask = em.merge(crmprojecttaskCollection1Crmprojecttask);
                if (oldCompanyemployee1OfCrmprojecttaskCollection1Crmprojecttask != null) {
                    oldCompanyemployee1OfCrmprojecttaskCollection1Crmprojecttask.getCrmprojecttaskCollection1().remove(crmprojecttaskCollection1Crmprojecttask);
                    oldCompanyemployee1OfCrmprojecttaskCollection1Crmprojecttask = em.merge(oldCompanyemployee1OfCrmprojecttaskCollection1Crmprojecttask);
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollectionCrmcampaignprops : companyemployee.getCrmcampaignpropsCollection()) {
                Companyemployee oldCompanyemployeeOfCrmcampaignpropsCollectionCrmcampaignprops = crmcampaignpropsCollectionCrmcampaignprops.getCompanyemployee();
                crmcampaignpropsCollectionCrmcampaignprops.setCompanyemployee(companyemployee);
                crmcampaignpropsCollectionCrmcampaignprops = em.merge(crmcampaignpropsCollectionCrmcampaignprops);
                if (oldCompanyemployeeOfCrmcampaignpropsCollectionCrmcampaignprops != null) {
                    oldCompanyemployeeOfCrmcampaignpropsCollectionCrmcampaignprops.getCrmcampaignpropsCollection().remove(crmcampaignpropsCollectionCrmcampaignprops);
                    oldCompanyemployeeOfCrmcampaignpropsCollectionCrmcampaignprops = em.merge(oldCompanyemployeeOfCrmcampaignpropsCollectionCrmcampaignprops);
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollection1Crmcampaignprops : companyemployee.getCrmcampaignpropsCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmcampaignpropsCollection1Crmcampaignprops = crmcampaignpropsCollection1Crmcampaignprops.getCompanyemployee1();
                crmcampaignpropsCollection1Crmcampaignprops.setCompanyemployee1(companyemployee);
                crmcampaignpropsCollection1Crmcampaignprops = em.merge(crmcampaignpropsCollection1Crmcampaignprops);
                if (oldCompanyemployee1OfCrmcampaignpropsCollection1Crmcampaignprops != null) {
                    oldCompanyemployee1OfCrmcampaignpropsCollection1Crmcampaignprops.getCrmcampaignpropsCollection1().remove(crmcampaignpropsCollection1Crmcampaignprops);
                    oldCompanyemployee1OfCrmcampaignpropsCollection1Crmcampaignprops = em.merge(oldCompanyemployee1OfCrmcampaignpropsCollection1Crmcampaignprops);
                }
            }
            for (Customercontactsaddress customercontactsaddressCollectionCustomercontactsaddress : companyemployee.getCustomercontactsaddressCollection()) {
                Companyemployee oldCompanyemployeeOfCustomercontactsaddressCollectionCustomercontactsaddress = customercontactsaddressCollectionCustomercontactsaddress.getCompanyemployee();
                customercontactsaddressCollectionCustomercontactsaddress.setCompanyemployee(companyemployee);
                customercontactsaddressCollectionCustomercontactsaddress = em.merge(customercontactsaddressCollectionCustomercontactsaddress);
                if (oldCompanyemployeeOfCustomercontactsaddressCollectionCustomercontactsaddress != null) {
                    oldCompanyemployeeOfCustomercontactsaddressCollectionCustomercontactsaddress.getCustomercontactsaddressCollection().remove(customercontactsaddressCollectionCustomercontactsaddress);
                    oldCompanyemployeeOfCustomercontactsaddressCollectionCustomercontactsaddress = em.merge(oldCompanyemployeeOfCustomercontactsaddressCollectionCustomercontactsaddress);
                }
            }
            for (Customercontactsaddress customercontactsaddressCollection1Customercontactsaddress : companyemployee.getCustomercontactsaddressCollection1()) {
                Companyemployee oldCompanyemployee1OfCustomercontactsaddressCollection1Customercontactsaddress = customercontactsaddressCollection1Customercontactsaddress.getCompanyemployee1();
                customercontactsaddressCollection1Customercontactsaddress.setCompanyemployee1(companyemployee);
                customercontactsaddressCollection1Customercontactsaddress = em.merge(customercontactsaddressCollection1Customercontactsaddress);
                if (oldCompanyemployee1OfCustomercontactsaddressCollection1Customercontactsaddress != null) {
                    oldCompanyemployee1OfCustomercontactsaddressCollection1Customercontactsaddress.getCustomercontactsaddressCollection1().remove(customercontactsaddressCollection1Customercontactsaddress);
                    oldCompanyemployee1OfCustomercontactsaddressCollection1Customercontactsaddress = em.merge(oldCompanyemployee1OfCustomercontactsaddressCollection1Customercontactsaddress);
                }
            }
            for (Customercategory customercategoryCollectionCustomercategory : companyemployee.getCustomercategoryCollection()) {
                Companyemployee oldCompanyemployeeOfCustomercategoryCollectionCustomercategory = customercategoryCollectionCustomercategory.getCompanyemployee();
                customercategoryCollectionCustomercategory.setCompanyemployee(companyemployee);
                customercategoryCollectionCustomercategory = em.merge(customercategoryCollectionCustomercategory);
                if (oldCompanyemployeeOfCustomercategoryCollectionCustomercategory != null) {
                    oldCompanyemployeeOfCustomercategoryCollectionCustomercategory.getCustomercategoryCollection().remove(customercategoryCollectionCustomercategory);
                    oldCompanyemployeeOfCustomercategoryCollectionCustomercategory = em.merge(oldCompanyemployeeOfCustomercategoryCollectionCustomercategory);
                }
            }
            for (Customercategory customercategoryCollection1Customercategory : companyemployee.getCustomercategoryCollection1()) {
                Companyemployee oldCompanyemployee1OfCustomercategoryCollection1Customercategory = customercategoryCollection1Customercategory.getCompanyemployee1();
                customercategoryCollection1Customercategory.setCompanyemployee1(companyemployee);
                customercategoryCollection1Customercategory = em.merge(customercategoryCollection1Customercategory);
                if (oldCompanyemployee1OfCustomercategoryCollection1Customercategory != null) {
                    oldCompanyemployee1OfCustomercategoryCollection1Customercategory.getCustomercategoryCollection1().remove(customercategoryCollection1Customercategory);
                    oldCompanyemployee1OfCustomercategoryCollection1Customercategory = em.merge(oldCompanyemployee1OfCustomercategoryCollection1Customercategory);
                }
            }
            for (Productcomponents productcomponentsCollectionProductcomponents : companyemployee.getProductcomponentsCollection()) {
                Companyemployee oldCompanyemployeeOfProductcomponentsCollectionProductcomponents = productcomponentsCollectionProductcomponents.getCompanyemployee();
                productcomponentsCollectionProductcomponents.setCompanyemployee(companyemployee);
                productcomponentsCollectionProductcomponents = em.merge(productcomponentsCollectionProductcomponents);
                if (oldCompanyemployeeOfProductcomponentsCollectionProductcomponents != null) {
                    oldCompanyemployeeOfProductcomponentsCollectionProductcomponents.getProductcomponentsCollection().remove(productcomponentsCollectionProductcomponents);
                    oldCompanyemployeeOfProductcomponentsCollectionProductcomponents = em.merge(oldCompanyemployeeOfProductcomponentsCollectionProductcomponents);
                }
            }
            for (Productcomponents productcomponentsCollection1Productcomponents : companyemployee.getProductcomponentsCollection1()) {
                Companyemployee oldCompanyemployee1OfProductcomponentsCollection1Productcomponents = productcomponentsCollection1Productcomponents.getCompanyemployee1();
                productcomponentsCollection1Productcomponents.setCompanyemployee1(companyemployee);
                productcomponentsCollection1Productcomponents = em.merge(productcomponentsCollection1Productcomponents);
                if (oldCompanyemployee1OfProductcomponentsCollection1Productcomponents != null) {
                    oldCompanyemployee1OfProductcomponentsCollection1Productcomponents.getProductcomponentsCollection1().remove(productcomponentsCollection1Productcomponents);
                    oldCompanyemployee1OfProductcomponentsCollection1Productcomponents = em.merge(oldCompanyemployee1OfProductcomponentsCollection1Productcomponents);
                }
            }
            for (Crmexpensetype crmexpensetypeCollectionCrmexpensetype : companyemployee.getCrmexpensetypeCollection()) {
                Companyemployee oldCompanyemployeeOfCrmexpensetypeCollectionCrmexpensetype = crmexpensetypeCollectionCrmexpensetype.getCompanyemployee();
                crmexpensetypeCollectionCrmexpensetype.setCompanyemployee(companyemployee);
                crmexpensetypeCollectionCrmexpensetype = em.merge(crmexpensetypeCollectionCrmexpensetype);
                if (oldCompanyemployeeOfCrmexpensetypeCollectionCrmexpensetype != null) {
                    oldCompanyemployeeOfCrmexpensetypeCollectionCrmexpensetype.getCrmexpensetypeCollection().remove(crmexpensetypeCollectionCrmexpensetype);
                    oldCompanyemployeeOfCrmexpensetypeCollectionCrmexpensetype = em.merge(oldCompanyemployeeOfCrmexpensetypeCollectionCrmexpensetype);
                }
            }
            for (Crmexpensetype crmexpensetypeCollection1Crmexpensetype : companyemployee.getCrmexpensetypeCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmexpensetypeCollection1Crmexpensetype = crmexpensetypeCollection1Crmexpensetype.getCompanyemployee1();
                crmexpensetypeCollection1Crmexpensetype.setCompanyemployee1(companyemployee);
                crmexpensetypeCollection1Crmexpensetype = em.merge(crmexpensetypeCollection1Crmexpensetype);
                if (oldCompanyemployee1OfCrmexpensetypeCollection1Crmexpensetype != null) {
                    oldCompanyemployee1OfCrmexpensetypeCollection1Crmexpensetype.getCrmexpensetypeCollection1().remove(crmexpensetypeCollection1Crmexpensetype);
                    oldCompanyemployee1OfCrmexpensetypeCollection1Crmexpensetype = em.merge(oldCompanyemployee1OfCrmexpensetypeCollection1Crmexpensetype);
                }
            }
            for (Companypayments companypaymentsCollectionCompanypayments : companyemployee.getCompanypaymentsCollection()) {
                Companyemployee oldCompanyemployeeOfCompanypaymentsCollectionCompanypayments = companypaymentsCollectionCompanypayments.getCompanyemployee();
                companypaymentsCollectionCompanypayments.setCompanyemployee(companyemployee);
                companypaymentsCollectionCompanypayments = em.merge(companypaymentsCollectionCompanypayments);
                if (oldCompanyemployeeOfCompanypaymentsCollectionCompanypayments != null) {
                    oldCompanyemployeeOfCompanypaymentsCollectionCompanypayments.getCompanypaymentsCollection().remove(companypaymentsCollectionCompanypayments);
                    oldCompanyemployeeOfCompanypaymentsCollectionCompanypayments = em.merge(oldCompanyemployeeOfCompanypaymentsCollectionCompanypayments);
                }
            }
            for (Companypayments companypaymentsCollection1Companypayments : companyemployee.getCompanypaymentsCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanypaymentsCollection1Companypayments = companypaymentsCollection1Companypayments.getCompanyemployee1();
                companypaymentsCollection1Companypayments.setCompanyemployee1(companyemployee);
                companypaymentsCollection1Companypayments = em.merge(companypaymentsCollection1Companypayments);
                if (oldCompanyemployee1OfCompanypaymentsCollection1Companypayments != null) {
                    oldCompanyemployee1OfCompanypaymentsCollection1Companypayments.getCompanypaymentsCollection1().remove(companypaymentsCollection1Companypayments);
                    oldCompanyemployee1OfCompanypaymentsCollection1Companypayments = em.merge(oldCompanyemployee1OfCompanypaymentsCollection1Companypayments);
                }
            }
            for (Crmworkorderresolution crmworkorderresolutionCollectionCrmworkorderresolution : companyemployee.getCrmworkorderresolutionCollection()) {
                Companyemployee oldCompanyemployeeOfCrmworkorderresolutionCollectionCrmworkorderresolution = crmworkorderresolutionCollectionCrmworkorderresolution.getCompanyemployee();
                crmworkorderresolutionCollectionCrmworkorderresolution.setCompanyemployee(companyemployee);
                crmworkorderresolutionCollectionCrmworkorderresolution = em.merge(crmworkorderresolutionCollectionCrmworkorderresolution);
                if (oldCompanyemployeeOfCrmworkorderresolutionCollectionCrmworkorderresolution != null) {
                    oldCompanyemployeeOfCrmworkorderresolutionCollectionCrmworkorderresolution.getCrmworkorderresolutionCollection().remove(crmworkorderresolutionCollectionCrmworkorderresolution);
                    oldCompanyemployeeOfCrmworkorderresolutionCollectionCrmworkorderresolution = em.merge(oldCompanyemployeeOfCrmworkorderresolutionCollectionCrmworkorderresolution);
                }
            }
            for (Crmworkorderresolution crmworkorderresolutionCollection1Crmworkorderresolution : companyemployee.getCrmworkorderresolutionCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmworkorderresolutionCollection1Crmworkorderresolution = crmworkorderresolutionCollection1Crmworkorderresolution.getCompanyemployee1();
                crmworkorderresolutionCollection1Crmworkorderresolution.setCompanyemployee1(companyemployee);
                crmworkorderresolutionCollection1Crmworkorderresolution = em.merge(crmworkorderresolutionCollection1Crmworkorderresolution);
                if (oldCompanyemployee1OfCrmworkorderresolutionCollection1Crmworkorderresolution != null) {
                    oldCompanyemployee1OfCrmworkorderresolutionCollection1Crmworkorderresolution.getCrmworkorderresolutionCollection1().remove(crmworkorderresolutionCollection1Crmworkorderresolution);
                    oldCompanyemployee1OfCrmworkorderresolutionCollection1Crmworkorderresolution = em.merge(oldCompanyemployee1OfCrmworkorderresolutionCollection1Crmworkorderresolution);
                }
            }
            for (Company companyCollectionCompany : companyemployee.getCompanyCollection()) {
                Companyemployee oldCompanyemployeeOfCompanyCollectionCompany = companyCollectionCompany.getCompanyemployee();
                companyCollectionCompany.setCompanyemployee(companyemployee);
                companyCollectionCompany = em.merge(companyCollectionCompany);
                if (oldCompanyemployeeOfCompanyCollectionCompany != null) {
                    oldCompanyemployeeOfCompanyCollectionCompany.getCompanyCollection().remove(companyCollectionCompany);
                    oldCompanyemployeeOfCompanyCollectionCompany = em.merge(oldCompanyemployeeOfCompanyCollectionCompany);
                }
            }
            for (Company companyCollection1Company : companyemployee.getCompanyCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanyCollection1Company = companyCollection1Company.getCompanyemployee1();
                companyCollection1Company.setCompanyemployee1(companyemployee);
                companyCollection1Company = em.merge(companyCollection1Company);
                if (oldCompanyemployee1OfCompanyCollection1Company != null) {
                    oldCompanyemployee1OfCompanyCollection1Company.getCompanyCollection1().remove(companyCollection1Company);
                    oldCompanyemployee1OfCompanyCollection1Company = em.merge(oldCompanyemployee1OfCompanyCollection1Company);
                }
            }
            for (Companyaddresstype companyaddresstypeCollectionCompanyaddresstype : companyemployee.getCompanyaddresstypeCollection()) {
                Companyemployee oldCompanyemployeeOfCompanyaddresstypeCollectionCompanyaddresstype = companyaddresstypeCollectionCompanyaddresstype.getCompanyemployee();
                companyaddresstypeCollectionCompanyaddresstype.setCompanyemployee(companyemployee);
                companyaddresstypeCollectionCompanyaddresstype = em.merge(companyaddresstypeCollectionCompanyaddresstype);
                if (oldCompanyemployeeOfCompanyaddresstypeCollectionCompanyaddresstype != null) {
                    oldCompanyemployeeOfCompanyaddresstypeCollectionCompanyaddresstype.getCompanyaddresstypeCollection().remove(companyaddresstypeCollectionCompanyaddresstype);
                    oldCompanyemployeeOfCompanyaddresstypeCollectionCompanyaddresstype = em.merge(oldCompanyemployeeOfCompanyaddresstypeCollectionCompanyaddresstype);
                }
            }
            for (Companyaddresstype companyaddresstypeCollection1Companyaddresstype : companyemployee.getCompanyaddresstypeCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanyaddresstypeCollection1Companyaddresstype = companyaddresstypeCollection1Companyaddresstype.getCompanyemployee1();
                companyaddresstypeCollection1Companyaddresstype.setCompanyemployee1(companyemployee);
                companyaddresstypeCollection1Companyaddresstype = em.merge(companyaddresstypeCollection1Companyaddresstype);
                if (oldCompanyemployee1OfCompanyaddresstypeCollection1Companyaddresstype != null) {
                    oldCompanyemployee1OfCompanyaddresstypeCollection1Companyaddresstype.getCompanyaddresstypeCollection1().remove(companyaddresstypeCollection1Companyaddresstype);
                    oldCompanyemployee1OfCompanyaddresstypeCollection1Companyaddresstype = em.merge(oldCompanyemployee1OfCompanyaddresstypeCollection1Companyaddresstype);
                }
            }
            for (Crmemployeecontacts crmemployeecontactsCollectionCrmemployeecontacts : companyemployee.getCrmemployeecontactsCollection()) {
                Companyemployee oldCompanyemployeeOfCrmemployeecontactsCollectionCrmemployeecontacts = crmemployeecontactsCollectionCrmemployeecontacts.getCompanyemployee();
                crmemployeecontactsCollectionCrmemployeecontacts.setCompanyemployee(companyemployee);
                crmemployeecontactsCollectionCrmemployeecontacts = em.merge(crmemployeecontactsCollectionCrmemployeecontacts);
                if (oldCompanyemployeeOfCrmemployeecontactsCollectionCrmemployeecontacts != null) {
                    oldCompanyemployeeOfCrmemployeecontactsCollectionCrmemployeecontacts.getCrmemployeecontactsCollection().remove(crmemployeecontactsCollectionCrmemployeecontacts);
                    oldCompanyemployeeOfCrmemployeecontactsCollectionCrmemployeecontacts = em.merge(oldCompanyemployeeOfCrmemployeecontactsCollectionCrmemployeecontacts);
                }
            }
            for (Approval approvalCollectionApproval : companyemployee.getApprovalCollection()) {
                Companyemployee oldCompanyemployeeOfApprovalCollectionApproval = approvalCollectionApproval.getCompanyemployee();
                approvalCollectionApproval.setCompanyemployee(companyemployee);
                approvalCollectionApproval = em.merge(approvalCollectionApproval);
                if (oldCompanyemployeeOfApprovalCollectionApproval != null) {
                    oldCompanyemployeeOfApprovalCollectionApproval.getApprovalCollection().remove(approvalCollectionApproval);
                    oldCompanyemployeeOfApprovalCollectionApproval = em.merge(oldCompanyemployeeOfApprovalCollectionApproval);
                }
            }
            for (Approval approvalCollection1Approval : companyemployee.getApprovalCollection1()) {
                Companyemployee oldCompanyemployee1OfApprovalCollection1Approval = approvalCollection1Approval.getCompanyemployee1();
                approvalCollection1Approval.setCompanyemployee1(companyemployee);
                approvalCollection1Approval = em.merge(approvalCollection1Approval);
                if (oldCompanyemployee1OfApprovalCollection1Approval != null) {
                    oldCompanyemployee1OfApprovalCollection1Approval.getApprovalCollection1().remove(approvalCollection1Approval);
                    oldCompanyemployee1OfApprovalCollection1Approval = em.merge(oldCompanyemployee1OfApprovalCollection1Approval);
                }
            }
            for (Approval approvalCollection2Approval : companyemployee.getApprovalCollection2()) {
                Companyemployee oldCompanyemployee2OfApprovalCollection2Approval = approvalCollection2Approval.getCompanyemployee2();
                approvalCollection2Approval.setCompanyemployee2(companyemployee);
                approvalCollection2Approval = em.merge(approvalCollection2Approval);
                if (oldCompanyemployee2OfApprovalCollection2Approval != null) {
                    oldCompanyemployee2OfApprovalCollection2Approval.getApprovalCollection2().remove(approvalCollection2Approval);
                    oldCompanyemployee2OfApprovalCollection2Approval = em.merge(oldCompanyemployee2OfApprovalCollection2Approval);
                }
            }
            for (Workorderinstructions workorderinstructionsCollectionWorkorderinstructions : companyemployee.getWorkorderinstructionsCollection()) {
                Companyemployee oldCompanyemployeeOfWorkorderinstructionsCollectionWorkorderinstructions = workorderinstructionsCollectionWorkorderinstructions.getCompanyemployee();
                workorderinstructionsCollectionWorkorderinstructions.setCompanyemployee(companyemployee);
                workorderinstructionsCollectionWorkorderinstructions = em.merge(workorderinstructionsCollectionWorkorderinstructions);
                if (oldCompanyemployeeOfWorkorderinstructionsCollectionWorkorderinstructions != null) {
                    oldCompanyemployeeOfWorkorderinstructionsCollectionWorkorderinstructions.getWorkorderinstructionsCollection().remove(workorderinstructionsCollectionWorkorderinstructions);
                    oldCompanyemployeeOfWorkorderinstructionsCollectionWorkorderinstructions = em.merge(oldCompanyemployeeOfWorkorderinstructionsCollectionWorkorderinstructions);
                }
            }
            for (Workorderinstructions workorderinstructionsCollection1Workorderinstructions : companyemployee.getWorkorderinstructionsCollection1()) {
                Companyemployee oldCompanyemployee1OfWorkorderinstructionsCollection1Workorderinstructions = workorderinstructionsCollection1Workorderinstructions.getCompanyemployee1();
                workorderinstructionsCollection1Workorderinstructions.setCompanyemployee1(companyemployee);
                workorderinstructionsCollection1Workorderinstructions = em.merge(workorderinstructionsCollection1Workorderinstructions);
                if (oldCompanyemployee1OfWorkorderinstructionsCollection1Workorderinstructions != null) {
                    oldCompanyemployee1OfWorkorderinstructionsCollection1Workorderinstructions.getWorkorderinstructionsCollection1().remove(workorderinstructionsCollection1Workorderinstructions);
                    oldCompanyemployee1OfWorkorderinstructionsCollection1Workorderinstructions = em.merge(oldCompanyemployee1OfWorkorderinstructionsCollection1Workorderinstructions);
                }
            }
            for (Companycustomer companycustomerCollectionCompanycustomer : companyemployee.getCompanycustomerCollection()) {
                Companyemployee oldCompanyemployeeOfCompanycustomerCollectionCompanycustomer = companycustomerCollectionCompanycustomer.getCompanyemployee();
                companycustomerCollectionCompanycustomer.setCompanyemployee(companyemployee);
                companycustomerCollectionCompanycustomer = em.merge(companycustomerCollectionCompanycustomer);
                if (oldCompanyemployeeOfCompanycustomerCollectionCompanycustomer != null) {
                    oldCompanyemployeeOfCompanycustomerCollectionCompanycustomer.getCompanycustomerCollection().remove(companycustomerCollectionCompanycustomer);
                    oldCompanyemployeeOfCompanycustomerCollectionCompanycustomer = em.merge(oldCompanyemployeeOfCompanycustomerCollectionCompanycustomer);
                }
            }
            for (Companycustomer companycustomerCollection1Companycustomer : companyemployee.getCompanycustomerCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanycustomerCollection1Companycustomer = companycustomerCollection1Companycustomer.getCompanyemployee1();
                companycustomerCollection1Companycustomer.setCompanyemployee1(companyemployee);
                companycustomerCollection1Companycustomer = em.merge(companycustomerCollection1Companycustomer);
                if (oldCompanyemployee1OfCompanycustomerCollection1Companycustomer != null) {
                    oldCompanyemployee1OfCompanycustomerCollection1Companycustomer.getCompanycustomerCollection1().remove(companycustomerCollection1Companycustomer);
                    oldCompanyemployee1OfCompanycustomerCollection1Companycustomer = em.merge(oldCompanyemployee1OfCompanycustomerCollection1Companycustomer);
                }
            }
            for (Crmforum crmforumCollectionCrmforum : companyemployee.getCrmforumCollection()) {
                Companyemployee oldCompanyemployeeOfCrmforumCollectionCrmforum = crmforumCollectionCrmforum.getCompanyemployee();
                crmforumCollectionCrmforum.setCompanyemployee(companyemployee);
                crmforumCollectionCrmforum = em.merge(crmforumCollectionCrmforum);
                if (oldCompanyemployeeOfCrmforumCollectionCrmforum != null) {
                    oldCompanyemployeeOfCrmforumCollectionCrmforum.getCrmforumCollection().remove(crmforumCollectionCrmforum);
                    oldCompanyemployeeOfCrmforumCollectionCrmforum = em.merge(oldCompanyemployeeOfCrmforumCollectionCrmforum);
                }
            }
            for (Crmprojectstatus crmprojectstatusCollectionCrmprojectstatus : companyemployee.getCrmprojectstatusCollection()) {
                Companyemployee oldCompanyemployeeOfCrmprojectstatusCollectionCrmprojectstatus = crmprojectstatusCollectionCrmprojectstatus.getCompanyemployee();
                crmprojectstatusCollectionCrmprojectstatus.setCompanyemployee(companyemployee);
                crmprojectstatusCollectionCrmprojectstatus = em.merge(crmprojectstatusCollectionCrmprojectstatus);
                if (oldCompanyemployeeOfCrmprojectstatusCollectionCrmprojectstatus != null) {
                    oldCompanyemployeeOfCrmprojectstatusCollectionCrmprojectstatus.getCrmprojectstatusCollection().remove(crmprojectstatusCollectionCrmprojectstatus);
                    oldCompanyemployeeOfCrmprojectstatusCollectionCrmprojectstatus = em.merge(oldCompanyemployeeOfCrmprojectstatusCollectionCrmprojectstatus);
                }
            }
            for (Crmprojectstatus crmprojectstatusCollection1Crmprojectstatus : companyemployee.getCrmprojectstatusCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmprojectstatusCollection1Crmprojectstatus = crmprojectstatusCollection1Crmprojectstatus.getCompanyemployee1();
                crmprojectstatusCollection1Crmprojectstatus.setCompanyemployee1(companyemployee);
                crmprojectstatusCollection1Crmprojectstatus = em.merge(crmprojectstatusCollection1Crmprojectstatus);
                if (oldCompanyemployee1OfCrmprojectstatusCollection1Crmprojectstatus != null) {
                    oldCompanyemployee1OfCrmprojectstatusCollection1Crmprojectstatus.getCrmprojectstatusCollection1().remove(crmprojectstatusCollection1Crmprojectstatus);
                    oldCompanyemployee1OfCrmprojectstatusCollection1Crmprojectstatus = em.merge(oldCompanyemployee1OfCrmprojectstatusCollection1Crmprojectstatus);
                }
            }
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionCrmschedulerevnttype : companyemployee.getCrmschedulerevnttypeCollection()) {
                Companyemployee oldCompanyemployeeOfCrmschedulerevnttypeCollectionCrmschedulerevnttype = crmschedulerevnttypeCollectionCrmschedulerevnttype.getCompanyemployee();
                crmschedulerevnttypeCollectionCrmschedulerevnttype.setCompanyemployee(companyemployee);
                crmschedulerevnttypeCollectionCrmschedulerevnttype = em.merge(crmschedulerevnttypeCollectionCrmschedulerevnttype);
                if (oldCompanyemployeeOfCrmschedulerevnttypeCollectionCrmschedulerevnttype != null) {
                    oldCompanyemployeeOfCrmschedulerevnttypeCollectionCrmschedulerevnttype.getCrmschedulerevnttypeCollection().remove(crmschedulerevnttypeCollectionCrmschedulerevnttype);
                    oldCompanyemployeeOfCrmschedulerevnttypeCollectionCrmschedulerevnttype = em.merge(oldCompanyemployeeOfCrmschedulerevnttypeCollectionCrmschedulerevnttype);
                }
            }
            for (Crmschedulerevnttype crmschedulerevnttypeCollection1Crmschedulerevnttype : companyemployee.getCrmschedulerevnttypeCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmschedulerevnttypeCollection1Crmschedulerevnttype = crmschedulerevnttypeCollection1Crmschedulerevnttype.getCompanyemployee1();
                crmschedulerevnttypeCollection1Crmschedulerevnttype.setCompanyemployee1(companyemployee);
                crmschedulerevnttypeCollection1Crmschedulerevnttype = em.merge(crmschedulerevnttypeCollection1Crmschedulerevnttype);
                if (oldCompanyemployee1OfCrmschedulerevnttypeCollection1Crmschedulerevnttype != null) {
                    oldCompanyemployee1OfCrmschedulerevnttypeCollection1Crmschedulerevnttype.getCrmschedulerevnttypeCollection1().remove(crmschedulerevnttypeCollection1Crmschedulerevnttype);
                    oldCompanyemployee1OfCrmschedulerevnttypeCollection1Crmschedulerevnttype = em.merge(oldCompanyemployee1OfCrmschedulerevnttypeCollection1Crmschedulerevnttype);
                }
            }
            for (Crmschedulerefcode crmschedulerefcodeCollectionCrmschedulerefcode : companyemployee.getCrmschedulerefcodeCollection()) {
                Companyemployee oldCompanyemployeeOfCrmschedulerefcodeCollectionCrmschedulerefcode = crmschedulerefcodeCollectionCrmschedulerefcode.getCompanyemployee();
                crmschedulerefcodeCollectionCrmschedulerefcode.setCompanyemployee(companyemployee);
                crmschedulerefcodeCollectionCrmschedulerefcode = em.merge(crmschedulerefcodeCollectionCrmschedulerefcode);
                if (oldCompanyemployeeOfCrmschedulerefcodeCollectionCrmschedulerefcode != null) {
                    oldCompanyemployeeOfCrmschedulerefcodeCollectionCrmschedulerefcode.getCrmschedulerefcodeCollection().remove(crmschedulerefcodeCollectionCrmschedulerefcode);
                    oldCompanyemployeeOfCrmschedulerefcodeCollectionCrmschedulerefcode = em.merge(oldCompanyemployeeOfCrmschedulerefcodeCollectionCrmschedulerefcode);
                }
            }
            for (Crmschedulerefcode crmschedulerefcodeCollection1Crmschedulerefcode : companyemployee.getCrmschedulerefcodeCollection1()) {
                Companyemployee oldCompanyemployee1OfCrmschedulerefcodeCollection1Crmschedulerefcode = crmschedulerefcodeCollection1Crmschedulerefcode.getCompanyemployee1();
                crmschedulerefcodeCollection1Crmschedulerefcode.setCompanyemployee1(companyemployee);
                crmschedulerefcodeCollection1Crmschedulerefcode = em.merge(crmschedulerefcodeCollection1Crmschedulerefcode);
                if (oldCompanyemployee1OfCrmschedulerefcodeCollection1Crmschedulerefcode != null) {
                    oldCompanyemployee1OfCrmschedulerefcodeCollection1Crmschedulerefcode.getCrmschedulerefcodeCollection1().remove(crmschedulerefcodeCollection1Crmschedulerefcode);
                    oldCompanyemployee1OfCrmschedulerefcodeCollection1Crmschedulerefcode = em.merge(oldCompanyemployee1OfCrmschedulerefcodeCollection1Crmschedulerefcode);
                }
            }
            for (Componenttype componenttypeCollectionComponenttype : companyemployee.getComponenttypeCollection()) {
                Companyemployee oldCompanyemployeeOfComponenttypeCollectionComponenttype = componenttypeCollectionComponenttype.getCompanyemployee();
                componenttypeCollectionComponenttype.setCompanyemployee(companyemployee);
                componenttypeCollectionComponenttype = em.merge(componenttypeCollectionComponenttype);
                if (oldCompanyemployeeOfComponenttypeCollectionComponenttype != null) {
                    oldCompanyemployeeOfComponenttypeCollectionComponenttype.getComponenttypeCollection().remove(componenttypeCollectionComponenttype);
                    oldCompanyemployeeOfComponenttypeCollectionComponenttype = em.merge(oldCompanyemployeeOfComponenttypeCollectionComponenttype);
                }
            }
            for (Componenttype componenttypeCollection1Componenttype : companyemployee.getComponenttypeCollection1()) {
                Companyemployee oldCompanyemployee1OfComponenttypeCollection1Componenttype = componenttypeCollection1Componenttype.getCompanyemployee1();
                componenttypeCollection1Componenttype.setCompanyemployee1(companyemployee);
                componenttypeCollection1Componenttype = em.merge(componenttypeCollection1Componenttype);
                if (oldCompanyemployee1OfComponenttypeCollection1Componenttype != null) {
                    oldCompanyemployee1OfComponenttypeCollection1Componenttype.getComponenttypeCollection1().remove(componenttypeCollection1Componenttype);
                    oldCompanyemployee1OfComponenttypeCollection1Componenttype = em.merge(oldCompanyemployee1OfComponenttypeCollection1Componenttype);
                }
            }
            for (Companypaymentscheme companypaymentschemeCollectionCompanypaymentscheme : companyemployee.getCompanypaymentschemeCollection()) {
                Companyemployee oldCompanyemployeeOfCompanypaymentschemeCollectionCompanypaymentscheme = companypaymentschemeCollectionCompanypaymentscheme.getCompanyemployee();
                companypaymentschemeCollectionCompanypaymentscheme.setCompanyemployee(companyemployee);
                companypaymentschemeCollectionCompanypaymentscheme = em.merge(companypaymentschemeCollectionCompanypaymentscheme);
                if (oldCompanyemployeeOfCompanypaymentschemeCollectionCompanypaymentscheme != null) {
                    oldCompanyemployeeOfCompanypaymentschemeCollectionCompanypaymentscheme.getCompanypaymentschemeCollection().remove(companypaymentschemeCollectionCompanypaymentscheme);
                    oldCompanyemployeeOfCompanypaymentschemeCollectionCompanypaymentscheme = em.merge(oldCompanyemployeeOfCompanypaymentschemeCollectionCompanypaymentscheme);
                }
            }
            for (Companypaymentscheme companypaymentschemeCollection1Companypaymentscheme : companyemployee.getCompanypaymentschemeCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanypaymentschemeCollection1Companypaymentscheme = companypaymentschemeCollection1Companypaymentscheme.getCompanyemployee1();
                companypaymentschemeCollection1Companypaymentscheme.setCompanyemployee1(companyemployee);
                companypaymentschemeCollection1Companypaymentscheme = em.merge(companypaymentschemeCollection1Companypaymentscheme);
                if (oldCompanyemployee1OfCompanypaymentschemeCollection1Companypaymentscheme != null) {
                    oldCompanyemployee1OfCompanypaymentschemeCollection1Companypaymentscheme.getCompanypaymentschemeCollection1().remove(companypaymentschemeCollection1Companypaymentscheme);
                    oldCompanyemployee1OfCompanypaymentschemeCollection1Companypaymentscheme = em.merge(oldCompanyemployee1OfCompanypaymentschemeCollection1Companypaymentscheme);
                }
            }
            for (Companycontactsaddress companycontactsaddressCollectionCompanycontactsaddress : companyemployee.getCompanycontactsaddressCollection()) {
                Companyemployee oldCompanyemployeeOfCompanycontactsaddressCollectionCompanycontactsaddress = companycontactsaddressCollectionCompanycontactsaddress.getCompanyemployee();
                companycontactsaddressCollectionCompanycontactsaddress.setCompanyemployee(companyemployee);
                companycontactsaddressCollectionCompanycontactsaddress = em.merge(companycontactsaddressCollectionCompanycontactsaddress);
                if (oldCompanyemployeeOfCompanycontactsaddressCollectionCompanycontactsaddress != null) {
                    oldCompanyemployeeOfCompanycontactsaddressCollectionCompanycontactsaddress.getCompanycontactsaddressCollection().remove(companycontactsaddressCollectionCompanycontactsaddress);
                    oldCompanyemployeeOfCompanycontactsaddressCollectionCompanycontactsaddress = em.merge(oldCompanyemployeeOfCompanycontactsaddressCollectionCompanycontactsaddress);
                }
            }
            for (Companycontactsaddress companycontactsaddressCollection1Companycontactsaddress : companyemployee.getCompanycontactsaddressCollection1()) {
                Companyemployee oldCompanyemployee1OfCompanycontactsaddressCollection1Companycontactsaddress = companycontactsaddressCollection1Companycontactsaddress.getCompanyemployee1();
                companycontactsaddressCollection1Companycontactsaddress.setCompanyemployee1(companyemployee);
                companycontactsaddressCollection1Companycontactsaddress = em.merge(companycontactsaddressCollection1Companycontactsaddress);
                if (oldCompanyemployee1OfCompanycontactsaddressCollection1Companycontactsaddress != null) {
                    oldCompanyemployee1OfCompanycontactsaddressCollection1Companycontactsaddress.getCompanycontactsaddressCollection1().remove(companycontactsaddressCollection1Companycontactsaddress);
                    oldCompanyemployee1OfCompanycontactsaddressCollection1Companycontactsaddress = em.merge(oldCompanyemployee1OfCompanycontactsaddressCollection1Companycontactsaddress);
                }
            }
            for (Crmprofilesettings crmprofilesettingsCollectionCrmprofilesettings : companyemployee.getCrmprofilesettingsCollection()) {
                Companyemployee oldCompanyemployeeOfCrmprofilesettingsCollectionCrmprofilesettings = crmprofilesettingsCollectionCrmprofilesettings.getCompanyemployee();
                crmprofilesettingsCollectionCrmprofilesettings.setCompanyemployee(companyemployee);
                crmprofilesettingsCollectionCrmprofilesettings = em.merge(crmprofilesettingsCollectionCrmprofilesettings);
                if (oldCompanyemployeeOfCrmprofilesettingsCollectionCrmprofilesettings != null) {
                    oldCompanyemployeeOfCrmprofilesettingsCollectionCrmprofilesettings.getCrmprofilesettingsCollection().remove(crmprofilesettingsCollectionCrmprofilesettings);
                    oldCompanyemployeeOfCrmprofilesettingsCollectionCrmprofilesettings = em.merge(oldCompanyemployeeOfCrmprofilesettingsCollectionCrmprofilesettings);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyemployee(companyemployee.getCompanyemployeePK()) != null) {
                throw new PreexistingEntityException("Companyemployee " + companyemployee + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companyemployee companyemployee) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee persistentCompanyemployee = em.find(Companyemployee.class, companyemployee.getCompanyemployeePK());
            Company companyOld = persistentCompanyemployee.getCompany();
            Company companyNew = companyemployee.getCompany();
            Companydepartment companydepartmentOld = persistentCompanyemployee.getCompanydepartment();
            Companydepartment companydepartmentNew = companyemployee.getCompanydepartment();
            Companyemployee companyemployeeRelOld = persistentCompanyemployee.getCompanyemployee();
            Companyemployee companyemployeeRelNew = companyemployee.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanyemployee.getCompanyemployee1();
            Companyemployee companyemployee1New = companyemployee.getCompanyemployee1();
            Collection<Crmmessagechannel> crmmessagechannelCollectionOld = persistentCompanyemployee.getCrmmessagechannelCollection();
            Collection<Crmmessagechannel> crmmessagechannelCollectionNew = companyemployee.getCrmmessagechannelCollection();
            Collection<Crmmessagechannel> crmmessagechannelCollection1Old = persistentCompanyemployee.getCrmmessagechannelCollection1();
            Collection<Crmmessagechannel> crmmessagechannelCollection1New = companyemployee.getCrmmessagechannelCollection1();
            Collection<Crmmessagechannel> crmmessagechannelCollection2Old = persistentCompanyemployee.getCrmmessagechannelCollection2();
            Collection<Crmmessagechannel> crmmessagechannelCollection2New = companyemployee.getCrmmessagechannelCollection2();
            Collection<Companybank> companybankCollectionOld = persistentCompanyemployee.getCompanybankCollection();
            Collection<Companybank> companybankCollectionNew = companyemployee.getCompanybankCollection();
            Collection<Companybank> companybankCollection1Old = persistentCompanyemployee.getCompanybankCollection1();
            Collection<Companybank> companybankCollection1New = companyemployee.getCompanybankCollection1();
            Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollectionOld = persistentCompanyemployee.getCrmmessagechanneltemplateCollection();
            Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollectionNew = companyemployee.getCrmmessagechanneltemplateCollection();
            Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollection1Old = persistentCompanyemployee.getCrmmessagechanneltemplateCollection1();
            Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollection1New = companyemployee.getCrmmessagechanneltemplateCollection1();
            Collection<Crmprojectprops> crmprojectpropsCollectionOld = persistentCompanyemployee.getCrmprojectpropsCollection();
            Collection<Crmprojectprops> crmprojectpropsCollectionNew = companyemployee.getCrmprojectpropsCollection();
            Collection<Crmprojectprops> crmprojectpropsCollection1Old = persistentCompanyemployee.getCrmprojectpropsCollection1();
            Collection<Crmprojectprops> crmprojectpropsCollection1New = companyemployee.getCrmprojectpropsCollection1();
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollectionOld = persistentCompanyemployee.getCrmcampaignreceiverCollection();
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollectionNew = companyemployee.getCrmcampaignreceiverCollection();
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollection1Old = persistentCompanyemployee.getCrmcampaignreceiverCollection1();
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollection1New = companyemployee.getCrmcampaignreceiverCollection1();
            Collection<Crmworkordersettings> crmworkordersettingsCollectionOld = persistentCompanyemployee.getCrmworkordersettingsCollection();
            Collection<Crmworkordersettings> crmworkordersettingsCollectionNew = companyemployee.getCrmworkordersettingsCollection();
            Collection<Productattachments> productattachmentsCollectionOld = persistentCompanyemployee.getProductattachmentsCollection();
            Collection<Productattachments> productattachmentsCollectionNew = companyemployee.getProductattachmentsCollection();
            Collection<Productattachments> productattachmentsCollection1Old = persistentCompanyemployee.getProductattachmentsCollection1();
            Collection<Productattachments> productattachmentsCollection1New = companyemployee.getProductattachmentsCollection1();
            Collection<Productattachments> productattachmentsCollection2Old = persistentCompanyemployee.getProductattachmentsCollection2();
            Collection<Productattachments> productattachmentsCollection2New = companyemployee.getProductattachmentsCollection2();
            Collection<Crmcampaignstatus> crmcampaignstatusCollectionOld = persistentCompanyemployee.getCrmcampaignstatusCollection();
            Collection<Crmcampaignstatus> crmcampaignstatusCollectionNew = companyemployee.getCrmcampaignstatusCollection();
            Collection<Crmcampaignstatus> crmcampaignstatusCollection1Old = persistentCompanyemployee.getCrmcampaignstatusCollection1();
            Collection<Crmcampaignstatus> crmcampaignstatusCollection1New = companyemployee.getCrmcampaignstatusCollection1();
            Collection<Crmcampaignposition> crmcampaignpositionCollectionOld = persistentCompanyemployee.getCrmcampaignpositionCollection();
            Collection<Crmcampaignposition> crmcampaignpositionCollectionNew = companyemployee.getCrmcampaignpositionCollection();
            Collection<Crmcampaignposition> crmcampaignpositionCollection1Old = persistentCompanyemployee.getCrmcampaignpositionCollection1();
            Collection<Crmcampaignposition> crmcampaignpositionCollection1New = companyemployee.getCrmcampaignpositionCollection1();
            Collection<Crmforumteammembers> crmforumteammembersCollectionOld = persistentCompanyemployee.getCrmforumteammembersCollection();
            Collection<Crmforumteammembers> crmforumteammembersCollectionNew = companyemployee.getCrmforumteammembersCollection();
            Collection<Customercontacts> customercontactsCollectionOld = persistentCompanyemployee.getCustomercontactsCollection();
            Collection<Customercontacts> customercontactsCollectionNew = companyemployee.getCustomercontactsCollection();
            Collection<Customercontacts> customercontactsCollection1Old = persistentCompanyemployee.getCustomercontactsCollection1();
            Collection<Customercontacts> customercontactsCollection1New = companyemployee.getCustomercontactsCollection1();
            Collection<Appointment> appointmentCollectionOld = persistentCompanyemployee.getAppointmentCollection();
            Collection<Appointment> appointmentCollectionNew = companyemployee.getAppointmentCollection();
            Collection<Appointment> appointmentCollection1Old = persistentCompanyemployee.getAppointmentCollection1();
            Collection<Appointment> appointmentCollection1New = companyemployee.getAppointmentCollection1();
            Collection<Appointment> appointmentCollection2Old = persistentCompanyemployee.getAppointmentCollection2();
            Collection<Appointment> appointmentCollection2New = companyemployee.getAppointmentCollection2();
            Collection<Appointment> appointmentCollection3Old = persistentCompanyemployee.getAppointmentCollection3();
            Collection<Appointment> appointmentCollection3New = companyemployee.getAppointmentCollection3();
            Collection<Companydepartment> companydepartmentCollectionOld = persistentCompanyemployee.getCompanydepartmentCollection();
            Collection<Companydepartment> companydepartmentCollectionNew = companyemployee.getCompanydepartmentCollection();
            Collection<Companydepartment> companydepartmentCollection1Old = persistentCompanyemployee.getCompanydepartmentCollection1();
            Collection<Companydepartment> companydepartmentCollection1New = companyemployee.getCompanydepartmentCollection1();
            Collection<Crmusermodules> crmusermodulesCollectionOld = persistentCompanyemployee.getCrmusermodulesCollection();
            Collection<Crmusermodules> crmusermodulesCollectionNew = companyemployee.getCrmusermodulesCollection();
            Collection<Crmusermodules> crmusermodulesCollection1Old = persistentCompanyemployee.getCrmusermodulesCollection1();
            Collection<Crmusermodules> crmusermodulesCollection1New = companyemployee.getCrmusermodulesCollection1();
            Collection<Crmexpense> crmexpenseCollectionOld = persistentCompanyemployee.getCrmexpenseCollection();
            Collection<Crmexpense> crmexpenseCollectionNew = companyemployee.getCrmexpenseCollection();
            Collection<Crmexpense> crmexpenseCollection1Old = persistentCompanyemployee.getCrmexpenseCollection1();
            Collection<Crmexpense> crmexpenseCollection1New = companyemployee.getCrmexpenseCollection1();
            Collection<Crmworkorder> crmworkorderCollectionOld = persistentCompanyemployee.getCrmworkorderCollection();
            Collection<Crmworkorder> crmworkorderCollectionNew = companyemployee.getCrmworkorderCollection();
            Collection<Crmworkorder> crmworkorderCollection1Old = persistentCompanyemployee.getCrmworkorderCollection1();
            Collection<Crmworkorder> crmworkorderCollection1New = companyemployee.getCrmworkorderCollection1();
            Collection<Crmcampaigndocs> crmcampaigndocsCollectionOld = persistentCompanyemployee.getCrmcampaigndocsCollection();
            Collection<Crmcampaigndocs> crmcampaigndocsCollectionNew = companyemployee.getCrmcampaigndocsCollection();
            Collection<Crmcampaigndocs> crmcampaigndocsCollection1Old = persistentCompanyemployee.getCrmcampaigndocsCollection1();
            Collection<Crmcampaigndocs> crmcampaigndocsCollection1New = companyemployee.getCrmcampaigndocsCollection1();
            Collection<Crmemployeenote> crmemployeenoteCollectionOld = persistentCompanyemployee.getCrmemployeenoteCollection();
            Collection<Crmemployeenote> crmemployeenoteCollectionNew = companyemployee.getCrmemployeenoteCollection();
            Collection<Companycontacts> companycontactsCollectionOld = persistentCompanyemployee.getCompanycontactsCollection();
            Collection<Companycontacts> companycontactsCollectionNew = companyemployee.getCompanycontactsCollection();
            Collection<Companycontacts> companycontactsCollection1Old = persistentCompanyemployee.getCompanycontactsCollection1();
            Collection<Companycontacts> companycontactsCollection1New = companyemployee.getCompanycontactsCollection1();
            Collection<Companyemployee> companyemployeeCollectionOld = persistentCompanyemployee.getCompanyemployeeCollection();
            Collection<Companyemployee> companyemployeeCollectionNew = companyemployee.getCompanyemployeeCollection();
            Collection<Companyemployee> companyemployeeCollection1Old = persistentCompanyemployee.getCompanyemployeeCollection1();
            Collection<Companyemployee> companyemployeeCollection1New = companyemployee.getCompanyemployeeCollection1();
            Collection<Companyproducttype> companyproducttypeCollectionOld = persistentCompanyemployee.getCompanyproducttypeCollection();
            Collection<Companyproducttype> companyproducttypeCollectionNew = companyemployee.getCompanyproducttypeCollection();
            Collection<Companyproducttype> companyproducttypeCollection1Old = persistentCompanyemployee.getCompanyproducttypeCollection1();
            Collection<Companyproducttype> companyproducttypeCollection1New = companyemployee.getCompanyproducttypeCollection1();
            Collection<Crmcampaign> crmcampaignCollectionOld = persistentCompanyemployee.getCrmcampaignCollection();
            Collection<Crmcampaign> crmcampaignCollectionNew = companyemployee.getCrmcampaignCollection();
            Collection<Crmcampaign> crmcampaignCollection1Old = persistentCompanyemployee.getCrmcampaignCollection1();
            Collection<Crmcampaign> crmcampaignCollection1New = companyemployee.getCrmcampaignCollection1();
            Collection<Crmmessagehistory> crmmessagehistoryCollectionOld = persistentCompanyemployee.getCrmmessagehistoryCollection();
            Collection<Crmmessagehistory> crmmessagehistoryCollectionNew = companyemployee.getCrmmessagehistoryCollection();
            Collection<Crmmessagehistory> crmmessagehistoryCollection1Old = persistentCompanyemployee.getCrmmessagehistoryCollection1();
            Collection<Crmmessagehistory> crmmessagehistoryCollection1New = companyemployee.getCrmmessagehistoryCollection1();
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollectionOld = persistentCompanyemployee.getCompanyaccountstackdocsCollection();
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollectionNew = companyemployee.getCompanyaccountstackdocsCollection();
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollection1Old = persistentCompanyemployee.getCompanyaccountstackdocsCollection1();
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollection1New = companyemployee.getCompanyaccountstackdocsCollection1();
            Collection<Crmlabor> crmlaborCollectionOld = persistentCompanyemployee.getCrmlaborCollection();
            Collection<Crmlabor> crmlaborCollectionNew = companyemployee.getCrmlaborCollection();
            Collection<Crmlabor> crmlaborCollection1Old = persistentCompanyemployee.getCrmlaborCollection1();
            Collection<Crmlabor> crmlaborCollection1New = companyemployee.getCrmlaborCollection1();
            Collection<Customerbank> customerbankCollectionOld = persistentCompanyemployee.getCustomerbankCollection();
            Collection<Customerbank> customerbankCollectionNew = companyemployee.getCustomerbankCollection();
            Collection<Customerbank> customerbankCollection1Old = persistentCompanyemployee.getCustomerbankCollection1();
            Collection<Customerbank> customerbankCollection1New = companyemployee.getCustomerbankCollection1();
            Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollectionOld = persistentCompanyemployee.getCompnaypaymentcurrencyCollection();
            Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollectionNew = companyemployee.getCompnaypaymentcurrencyCollection();
            Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollection1Old = persistentCompanyemployee.getCompnaypaymentcurrencyCollection1();
            Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollection1New = companyemployee.getCompnaypaymentcurrencyCollection1();
            Collection<Crmproject> crmprojectCollectionOld = persistentCompanyemployee.getCrmprojectCollection();
            Collection<Crmproject> crmprojectCollectionNew = companyemployee.getCrmprojectCollection();
            Collection<Crmproject> crmprojectCollection1Old = persistentCompanyemployee.getCrmprojectCollection1();
            Collection<Crmproject> crmprojectCollection1New = companyemployee.getCrmprojectCollection1();
            Collection<Crmproject> crmprojectCollection2Old = persistentCompanyemployee.getCrmprojectCollection2();
            Collection<Crmproject> crmprojectCollection2New = companyemployee.getCrmprojectCollection2();
            Collection<Companyemployeeaddress> companyemployeeaddressCollectionOld = persistentCompanyemployee.getCompanyemployeeaddressCollection();
            Collection<Companyemployeeaddress> companyemployeeaddressCollectionNew = companyemployee.getCompanyemployeeaddressCollection();
            Collection<Companyemployeeaddress> companyemployeeaddressCollection1Old = persistentCompanyemployee.getCompanyemployeeaddressCollection1();
            Collection<Companyemployeeaddress> companyemployeeaddressCollection1New = companyemployee.getCompanyemployeeaddressCollection1();
            Collection<Companyemployeeaddress> companyemployeeaddressCollection2Old = persistentCompanyemployee.getCompanyemployeeaddressCollection2();
            Collection<Companyemployeeaddress> companyemployeeaddressCollection2New = companyemployee.getCompanyemployeeaddressCollection2();
            Collection<Crmforumdocs> crmforumdocsCollectionOld = persistentCompanyemployee.getCrmforumdocsCollection();
            Collection<Crmforumdocs> crmforumdocsCollectionNew = companyemployee.getCrmforumdocsCollection();
            Collection<Companyproduct> companyproductCollectionOld = persistentCompanyemployee.getCompanyproductCollection();
            Collection<Companyproduct> companyproductCollectionNew = companyemployee.getCompanyproductCollection();
            Collection<Companyproduct> companyproductCollection1Old = persistentCompanyemployee.getCompanyproductCollection1();
            Collection<Companyproduct> companyproductCollection1New = companyemployee.getCompanyproductCollection1();
            Collection<Employeedesignation> employeedesignationCollectionOld = persistentCompanyemployee.getEmployeedesignationCollection();
            Collection<Employeedesignation> employeedesignationCollectionNew = companyemployee.getEmployeedesignationCollection();
            Collection<Employeedesignation> employeedesignationCollection1Old = persistentCompanyemployee.getEmployeedesignationCollection1();
            Collection<Employeedesignation> employeedesignationCollection1New = companyemployee.getEmployeedesignationCollection1();
            Collection<Companyaccountstack> companyaccountstackCollectionOld = persistentCompanyemployee.getCompanyaccountstackCollection();
            Collection<Companyaccountstack> companyaccountstackCollectionNew = companyemployee.getCompanyaccountstackCollection();
            Collection<Companyaccountstack> companyaccountstackCollection1Old = persistentCompanyemployee.getCompanyaccountstackCollection1();
            Collection<Companyaccountstack> companyaccountstackCollection1New = companyemployee.getCompanyaccountstackCollection1();
            Collection<Crmprojectteammembers> crmprojectteammembersCollectionOld = persistentCompanyemployee.getCrmprojectteammembersCollection();
            Collection<Crmprojectteammembers> crmprojectteammembersCollectionNew = companyemployee.getCrmprojectteammembersCollection();
            Collection<Crmprojectteammembers> crmprojectteammembersCollection1Old = persistentCompanyemployee.getCrmprojectteammembersCollection1();
            Collection<Crmprojectteammembers> crmprojectteammembersCollection1New = companyemployee.getCrmprojectteammembersCollection1();
            Collection<Crmprojectteammembers> crmprojectteammembersCollection2Old = persistentCompanyemployee.getCrmprojectteammembersCollection2();
            Collection<Crmprojectteammembers> crmprojectteammembersCollection2New = companyemployee.getCrmprojectteammembersCollection2();
            Collection<Componentattachments> componentattachmentsCollectionOld = persistentCompanyemployee.getComponentattachmentsCollection();
            Collection<Componentattachments> componentattachmentsCollectionNew = companyemployee.getComponentattachmentsCollection();
            Collection<Componentattachments> componentattachmentsCollection1Old = persistentCompanyemployee.getComponentattachmentsCollection1();
            Collection<Componentattachments> componentattachmentsCollection1New = companyemployee.getComponentattachmentsCollection1();
            Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollectionOld = persistentCompanyemployee.getCrmvisitorcontactsaddressCollection();
            Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollectionNew = companyemployee.getCrmvisitorcontactsaddressCollection();
            Collection<Crmvisitorcontacts> crmvisitorcontactsCollectionOld = persistentCompanyemployee.getCrmvisitorcontactsCollection();
            Collection<Crmvisitorcontacts> crmvisitorcontactsCollectionNew = companyemployee.getCrmvisitorcontactsCollection();
            Collection<Crmvisitorcontacts> crmvisitorcontactsCollection1Old = persistentCompanyemployee.getCrmvisitorcontactsCollection1();
            Collection<Crmvisitorcontacts> crmvisitorcontactsCollection1New = companyemployee.getCrmvisitorcontactsCollection1();
            Collection<Companyaccountstackcd> companyaccountstackcdCollectionOld = persistentCompanyemployee.getCompanyaccountstackcdCollection();
            Collection<Companyaccountstackcd> companyaccountstackcdCollectionNew = companyemployee.getCompanyaccountstackcdCollection();
            Collection<Companyaccountstackcd> companyaccountstackcdCollection1Old = persistentCompanyemployee.getCompanyaccountstackcdCollection1();
            Collection<Companyaccountstackcd> companyaccountstackcdCollection1New = companyemployee.getCompanyaccountstackcdCollection1();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollectionOld = persistentCompanyemployee.getCrmprojectticketmanagementCollection();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollectionNew = companyemployee.getCrmprojectticketmanagementCollection();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection1Old = persistentCompanyemployee.getCrmprojectticketmanagementCollection1();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection1New = companyemployee.getCrmprojectticketmanagementCollection1();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection2Old = persistentCompanyemployee.getCrmprojectticketmanagementCollection2();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection2New = companyemployee.getCrmprojectticketmanagementCollection2();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection3Old = persistentCompanyemployee.getCrmprojectticketmanagementCollection3();
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection3New = companyemployee.getCrmprojectticketmanagementCollection3();
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollectionOld = persistentCompanyemployee.getCrmprojectticketnotificationCollection();
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollectionNew = companyemployee.getCrmprojectticketnotificationCollection();
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection1Old = persistentCompanyemployee.getCrmprojectticketnotificationCollection1();
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection1New = companyemployee.getCrmprojectticketnotificationCollection1();
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection2Old = persistentCompanyemployee.getCrmprojectticketnotificationCollection2();
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection2New = companyemployee.getCrmprojectticketnotificationCollection2();
            Collection<Crmvisitor> crmvisitorCollectionOld = persistentCompanyemployee.getCrmvisitorCollection();
            Collection<Crmvisitor> crmvisitorCollectionNew = companyemployee.getCrmvisitorCollection();
            Collection<Crmvisitor> crmvisitorCollection1Old = persistentCompanyemployee.getCrmvisitorCollection1();
            Collection<Crmvisitor> crmvisitorCollection1New = companyemployee.getCrmvisitorCollection1();
            Collection<Crmworkordertype> crmworkordertypeCollectionOld = persistentCompanyemployee.getCrmworkordertypeCollection();
            Collection<Crmworkordertype> crmworkordertypeCollectionNew = companyemployee.getCrmworkordertypeCollection();
            Collection<Crmworkordertype> crmworkordertypeCollection1Old = persistentCompanyemployee.getCrmworkordertypeCollection1();
            Collection<Crmworkordertype> crmworkordertypeCollection1New = companyemployee.getCrmworkordertypeCollection1();
            Collection<Crmlabortype> crmlabortypeCollectionOld = persistentCompanyemployee.getCrmlabortypeCollection();
            Collection<Crmlabortype> crmlabortypeCollectionNew = companyemployee.getCrmlabortypeCollection();
            Collection<Crmlabortype> crmlabortypeCollection1Old = persistentCompanyemployee.getCrmlabortypeCollection1();
            Collection<Crmlabortype> crmlabortypeCollection1New = companyemployee.getCrmlabortypeCollection1();
            Collection<Crmbillingtype> crmbillingtypeCollectionOld = persistentCompanyemployee.getCrmbillingtypeCollection();
            Collection<Crmbillingtype> crmbillingtypeCollectionNew = companyemployee.getCrmbillingtypeCollection();
            Collection<Crmbillingtype> crmbillingtypeCollection1Old = persistentCompanyemployee.getCrmbillingtypeCollection1();
            Collection<Crmbillingtype> crmbillingtypeCollection1New = companyemployee.getCrmbillingtypeCollection1();
            Collection<Companyproductcategory> companyproductcategoryCollectionOld = persistentCompanyemployee.getCompanyproductcategoryCollection();
            Collection<Companyproductcategory> companyproductcategoryCollectionNew = companyemployee.getCompanyproductcategoryCollection();
            Collection<Companyproductcategory> companyproductcategoryCollection1Old = persistentCompanyemployee.getCompanyproductcategoryCollection1();
            Collection<Companyproductcategory> companyproductcategoryCollection1New = companyemployee.getCompanyproductcategoryCollection1();
            Collection<Crmprojecttask> crmprojecttaskCollectionOld = persistentCompanyemployee.getCrmprojecttaskCollection();
            Collection<Crmprojecttask> crmprojecttaskCollectionNew = companyemployee.getCrmprojecttaskCollection();
            Collection<Crmprojecttask> crmprojecttaskCollection1Old = persistentCompanyemployee.getCrmprojecttaskCollection1();
            Collection<Crmprojecttask> crmprojecttaskCollection1New = companyemployee.getCrmprojecttaskCollection1();
            Collection<Crmcampaignprops> crmcampaignpropsCollectionOld = persistentCompanyemployee.getCrmcampaignpropsCollection();
            Collection<Crmcampaignprops> crmcampaignpropsCollectionNew = companyemployee.getCrmcampaignpropsCollection();
            Collection<Crmcampaignprops> crmcampaignpropsCollection1Old = persistentCompanyemployee.getCrmcampaignpropsCollection1();
            Collection<Crmcampaignprops> crmcampaignpropsCollection1New = companyemployee.getCrmcampaignpropsCollection1();
            Collection<Customercontactsaddress> customercontactsaddressCollectionOld = persistentCompanyemployee.getCustomercontactsaddressCollection();
            Collection<Customercontactsaddress> customercontactsaddressCollectionNew = companyemployee.getCustomercontactsaddressCollection();
            Collection<Customercontactsaddress> customercontactsaddressCollection1Old = persistentCompanyemployee.getCustomercontactsaddressCollection1();
            Collection<Customercontactsaddress> customercontactsaddressCollection1New = companyemployee.getCustomercontactsaddressCollection1();
            Collection<Customercategory> customercategoryCollectionOld = persistentCompanyemployee.getCustomercategoryCollection();
            Collection<Customercategory> customercategoryCollectionNew = companyemployee.getCustomercategoryCollection();
            Collection<Customercategory> customercategoryCollection1Old = persistentCompanyemployee.getCustomercategoryCollection1();
            Collection<Customercategory> customercategoryCollection1New = companyemployee.getCustomercategoryCollection1();
            Collection<Productcomponents> productcomponentsCollectionOld = persistentCompanyemployee.getProductcomponentsCollection();
            Collection<Productcomponents> productcomponentsCollectionNew = companyemployee.getProductcomponentsCollection();
            Collection<Productcomponents> productcomponentsCollection1Old = persistentCompanyemployee.getProductcomponentsCollection1();
            Collection<Productcomponents> productcomponentsCollection1New = companyemployee.getProductcomponentsCollection1();
            Collection<Crmexpensetype> crmexpensetypeCollectionOld = persistentCompanyemployee.getCrmexpensetypeCollection();
            Collection<Crmexpensetype> crmexpensetypeCollectionNew = companyemployee.getCrmexpensetypeCollection();
            Collection<Crmexpensetype> crmexpensetypeCollection1Old = persistentCompanyemployee.getCrmexpensetypeCollection1();
            Collection<Crmexpensetype> crmexpensetypeCollection1New = companyemployee.getCrmexpensetypeCollection1();
            Collection<Companypayments> companypaymentsCollectionOld = persistentCompanyemployee.getCompanypaymentsCollection();
            Collection<Companypayments> companypaymentsCollectionNew = companyemployee.getCompanypaymentsCollection();
            Collection<Companypayments> companypaymentsCollection1Old = persistentCompanyemployee.getCompanypaymentsCollection1();
            Collection<Companypayments> companypaymentsCollection1New = companyemployee.getCompanypaymentsCollection1();
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionOld = persistentCompanyemployee.getCrmworkorderresolutionCollection();
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionNew = companyemployee.getCrmworkorderresolutionCollection();
            Collection<Crmworkorderresolution> crmworkorderresolutionCollection1Old = persistentCompanyemployee.getCrmworkorderresolutionCollection1();
            Collection<Crmworkorderresolution> crmworkorderresolutionCollection1New = companyemployee.getCrmworkorderresolutionCollection1();
            Collection<Company> companyCollectionOld = persistentCompanyemployee.getCompanyCollection();
            Collection<Company> companyCollectionNew = companyemployee.getCompanyCollection();
            Collection<Company> companyCollection1Old = persistentCompanyemployee.getCompanyCollection1();
            Collection<Company> companyCollection1New = companyemployee.getCompanyCollection1();
            Collection<Companyaddresstype> companyaddresstypeCollectionOld = persistentCompanyemployee.getCompanyaddresstypeCollection();
            Collection<Companyaddresstype> companyaddresstypeCollectionNew = companyemployee.getCompanyaddresstypeCollection();
            Collection<Companyaddresstype> companyaddresstypeCollection1Old = persistentCompanyemployee.getCompanyaddresstypeCollection1();
            Collection<Companyaddresstype> companyaddresstypeCollection1New = companyemployee.getCompanyaddresstypeCollection1();
            Collection<Crmemployeecontacts> crmemployeecontactsCollectionOld = persistentCompanyemployee.getCrmemployeecontactsCollection();
            Collection<Crmemployeecontacts> crmemployeecontactsCollectionNew = companyemployee.getCrmemployeecontactsCollection();
            Collection<Approval> approvalCollectionOld = persistentCompanyemployee.getApprovalCollection();
            Collection<Approval> approvalCollectionNew = companyemployee.getApprovalCollection();
            Collection<Approval> approvalCollection1Old = persistentCompanyemployee.getApprovalCollection1();
            Collection<Approval> approvalCollection1New = companyemployee.getApprovalCollection1();
            Collection<Approval> approvalCollection2Old = persistentCompanyemployee.getApprovalCollection2();
            Collection<Approval> approvalCollection2New = companyemployee.getApprovalCollection2();
            Collection<Workorderinstructions> workorderinstructionsCollectionOld = persistentCompanyemployee.getWorkorderinstructionsCollection();
            Collection<Workorderinstructions> workorderinstructionsCollectionNew = companyemployee.getWorkorderinstructionsCollection();
            Collection<Workorderinstructions> workorderinstructionsCollection1Old = persistentCompanyemployee.getWorkorderinstructionsCollection1();
            Collection<Workorderinstructions> workorderinstructionsCollection1New = companyemployee.getWorkorderinstructionsCollection1();
            Collection<Companycustomer> companycustomerCollectionOld = persistentCompanyemployee.getCompanycustomerCollection();
            Collection<Companycustomer> companycustomerCollectionNew = companyemployee.getCompanycustomerCollection();
            Collection<Companycustomer> companycustomerCollection1Old = persistentCompanyemployee.getCompanycustomerCollection1();
            Collection<Companycustomer> companycustomerCollection1New = companyemployee.getCompanycustomerCollection1();
            Collection<Crmforum> crmforumCollectionOld = persistentCompanyemployee.getCrmforumCollection();
            Collection<Crmforum> crmforumCollectionNew = companyemployee.getCrmforumCollection();
            Collection<Crmprojectstatus> crmprojectstatusCollectionOld = persistentCompanyemployee.getCrmprojectstatusCollection();
            Collection<Crmprojectstatus> crmprojectstatusCollectionNew = companyemployee.getCrmprojectstatusCollection();
            Collection<Crmprojectstatus> crmprojectstatusCollection1Old = persistentCompanyemployee.getCrmprojectstatusCollection1();
            Collection<Crmprojectstatus> crmprojectstatusCollection1New = companyemployee.getCrmprojectstatusCollection1();
            Collection<Crmschedulerevnttype> crmschedulerevnttypeCollectionOld = persistentCompanyemployee.getCrmschedulerevnttypeCollection();
            Collection<Crmschedulerevnttype> crmschedulerevnttypeCollectionNew = companyemployee.getCrmschedulerevnttypeCollection();
            Collection<Crmschedulerevnttype> crmschedulerevnttypeCollection1Old = persistentCompanyemployee.getCrmschedulerevnttypeCollection1();
            Collection<Crmschedulerevnttype> crmschedulerevnttypeCollection1New = companyemployee.getCrmschedulerevnttypeCollection1();
            Collection<Crmschedulerefcode> crmschedulerefcodeCollectionOld = persistentCompanyemployee.getCrmschedulerefcodeCollection();
            Collection<Crmschedulerefcode> crmschedulerefcodeCollectionNew = companyemployee.getCrmschedulerefcodeCollection();
            Collection<Crmschedulerefcode> crmschedulerefcodeCollection1Old = persistentCompanyemployee.getCrmschedulerefcodeCollection1();
            Collection<Crmschedulerefcode> crmschedulerefcodeCollection1New = companyemployee.getCrmschedulerefcodeCollection1();
            Collection<Componenttype> componenttypeCollectionOld = persistentCompanyemployee.getComponenttypeCollection();
            Collection<Componenttype> componenttypeCollectionNew = companyemployee.getComponenttypeCollection();
            Collection<Componenttype> componenttypeCollection1Old = persistentCompanyemployee.getComponenttypeCollection1();
            Collection<Componenttype> componenttypeCollection1New = companyemployee.getComponenttypeCollection1();
            Collection<Companypaymentscheme> companypaymentschemeCollectionOld = persistentCompanyemployee.getCompanypaymentschemeCollection();
            Collection<Companypaymentscheme> companypaymentschemeCollectionNew = companyemployee.getCompanypaymentschemeCollection();
            Collection<Companypaymentscheme> companypaymentschemeCollection1Old = persistentCompanyemployee.getCompanypaymentschemeCollection1();
            Collection<Companypaymentscheme> companypaymentschemeCollection1New = companyemployee.getCompanypaymentschemeCollection1();
            Collection<Companycontactsaddress> companycontactsaddressCollectionOld = persistentCompanyemployee.getCompanycontactsaddressCollection();
            Collection<Companycontactsaddress> companycontactsaddressCollectionNew = companyemployee.getCompanycontactsaddressCollection();
            Collection<Companycontactsaddress> companycontactsaddressCollection1Old = persistentCompanyemployee.getCompanycontactsaddressCollection1();
            Collection<Companycontactsaddress> companycontactsaddressCollection1New = companyemployee.getCompanycontactsaddressCollection1();
            Collection<Crmprofilesettings> crmprofilesettingsCollectionOld = persistentCompanyemployee.getCrmprofilesettingsCollection();
            Collection<Crmprofilesettings> crmprofilesettingsCollectionNew = companyemployee.getCrmprofilesettingsCollection();
            List<String> illegalOrphanMessages = null;
            if (companydepartmentOld != null && !companydepartmentOld.equals(companydepartmentNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Companydepartment " + companydepartmentOld + " since its companyemployee field is not nullable.");
            }
            if (companydepartmentNew != null && !companydepartmentNew.equals(companydepartmentOld)) {
                Companyemployee oldCompanyemployeeOfCompanydepartment = companydepartmentNew.getCompanyemployee();
                if (oldCompanyemployeeOfCompanydepartment != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Companydepartment " + companydepartmentNew + " already has an item of type Companyemployee whose companydepartment column cannot be null. Please make another selection for the companydepartment field.");
                }
            }
            for (Crmmessagechannel crmmessagechannelCollectionOldCrmmessagechannel : crmmessagechannelCollectionOld) {
                if (!crmmessagechannelCollectionNew.contains(crmmessagechannelCollectionOldCrmmessagechannel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagechannel " + crmmessagechannelCollectionOldCrmmessagechannel + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmmessagechannel crmmessagechannelCollection1OldCrmmessagechannel : crmmessagechannelCollection1Old) {
                if (!crmmessagechannelCollection1New.contains(crmmessagechannelCollection1OldCrmmessagechannel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagechannel " + crmmessagechannelCollection1OldCrmmessagechannel + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmmessagechannel crmmessagechannelCollection2OldCrmmessagechannel : crmmessagechannelCollection2Old) {
                if (!crmmessagechannelCollection2New.contains(crmmessagechannelCollection2OldCrmmessagechannel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagechannel " + crmmessagechannelCollection2OldCrmmessagechannel + " since its companyemployee2 field is not nullable.");
                }
            }
            for (Companybank companybankCollectionOldCompanybank : companybankCollectionOld) {
                if (!companybankCollectionNew.contains(companybankCollectionOldCompanybank)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companybank " + companybankCollectionOldCompanybank + " since its companyemployee field is not nullable.");
                }
            }
            for (Companybank companybankCollection1OldCompanybank : companybankCollection1Old) {
                if (!companybankCollection1New.contains(companybankCollection1OldCompanybank)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companybank " + companybankCollection1OldCompanybank + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionOldCrmmessagechanneltemplate : crmmessagechanneltemplateCollectionOld) {
                if (!crmmessagechanneltemplateCollectionNew.contains(crmmessagechanneltemplateCollectionOldCrmmessagechanneltemplate)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagechanneltemplate " + crmmessagechanneltemplateCollectionOldCrmmessagechanneltemplate + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollection1OldCrmmessagechanneltemplate : crmmessagechanneltemplateCollection1Old) {
                if (!crmmessagechanneltemplateCollection1New.contains(crmmessagechanneltemplateCollection1OldCrmmessagechanneltemplate)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagechanneltemplate " + crmmessagechanneltemplateCollection1OldCrmmessagechanneltemplate + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmprojectprops crmprojectpropsCollectionOldCrmprojectprops : crmprojectpropsCollectionOld) {
                if (!crmprojectpropsCollectionNew.contains(crmprojectpropsCollectionOldCrmprojectprops)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectprops " + crmprojectpropsCollectionOldCrmprojectprops + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmprojectprops crmprojectpropsCollection1OldCrmprojectprops : crmprojectpropsCollection1Old) {
                if (!crmprojectpropsCollection1New.contains(crmprojectpropsCollection1OldCrmprojectprops)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectprops " + crmprojectpropsCollection1OldCrmprojectprops + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollectionOldCrmcampaignreceiver : crmcampaignreceiverCollectionOld) {
                if (!crmcampaignreceiverCollectionNew.contains(crmcampaignreceiverCollectionOldCrmcampaignreceiver)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignreceiver " + crmcampaignreceiverCollectionOldCrmcampaignreceiver + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollection1OldCrmcampaignreceiver : crmcampaignreceiverCollection1Old) {
                if (!crmcampaignreceiverCollection1New.contains(crmcampaignreceiverCollection1OldCrmcampaignreceiver)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignreceiver " + crmcampaignreceiverCollection1OldCrmcampaignreceiver + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmworkordersettings crmworkordersettingsCollectionOldCrmworkordersettings : crmworkordersettingsCollectionOld) {
                if (!crmworkordersettingsCollectionNew.contains(crmworkordersettingsCollectionOldCrmworkordersettings)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkordersettings " + crmworkordersettingsCollectionOldCrmworkordersettings + " since its companyemployee field is not nullable.");
                }
            }
            for (Productattachments productattachmentsCollectionOldProductattachments : productattachmentsCollectionOld) {
                if (!productattachmentsCollectionNew.contains(productattachmentsCollectionOldProductattachments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productattachments " + productattachmentsCollectionOldProductattachments + " since its companyemployee field is not nullable.");
                }
            }
            for (Productattachments productattachmentsCollection1OldProductattachments : productattachmentsCollection1Old) {
                if (!productattachmentsCollection1New.contains(productattachmentsCollection1OldProductattachments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productattachments " + productattachmentsCollection1OldProductattachments + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Productattachments productattachmentsCollection2OldProductattachments : productattachmentsCollection2Old) {
                if (!productattachmentsCollection2New.contains(productattachmentsCollection2OldProductattachments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productattachments " + productattachmentsCollection2OldProductattachments + " since its companyemployee2 field is not nullable.");
                }
            }
            for (Crmcampaignstatus crmcampaignstatusCollectionOldCrmcampaignstatus : crmcampaignstatusCollectionOld) {
                if (!crmcampaignstatusCollectionNew.contains(crmcampaignstatusCollectionOldCrmcampaignstatus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignstatus " + crmcampaignstatusCollectionOldCrmcampaignstatus + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmcampaignstatus crmcampaignstatusCollection1OldCrmcampaignstatus : crmcampaignstatusCollection1Old) {
                if (!crmcampaignstatusCollection1New.contains(crmcampaignstatusCollection1OldCrmcampaignstatus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignstatus " + crmcampaignstatusCollection1OldCrmcampaignstatus + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollectionOldCrmcampaignposition : crmcampaignpositionCollectionOld) {
                if (!crmcampaignpositionCollectionNew.contains(crmcampaignpositionCollectionOldCrmcampaignposition)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignposition " + crmcampaignpositionCollectionOldCrmcampaignposition + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollection1OldCrmcampaignposition : crmcampaignpositionCollection1Old) {
                if (!crmcampaignpositionCollection1New.contains(crmcampaignpositionCollection1OldCrmcampaignposition)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignposition " + crmcampaignpositionCollection1OldCrmcampaignposition + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmforumteammembers crmforumteammembersCollectionOldCrmforumteammembers : crmforumteammembersCollectionOld) {
                if (!crmforumteammembersCollectionNew.contains(crmforumteammembersCollectionOldCrmforumteammembers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmforumteammembers " + crmforumteammembersCollectionOldCrmforumteammembers + " since its companyemployee field is not nullable.");
                }
            }
            for (Customercontacts customercontactsCollectionOldCustomercontacts : customercontactsCollectionOld) {
                if (!customercontactsCollectionNew.contains(customercontactsCollectionOldCustomercontacts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customercontacts " + customercontactsCollectionOldCustomercontacts + " since its companyemployee field is not nullable.");
                }
            }
            for (Customercontacts customercontactsCollection1OldCustomercontacts : customercontactsCollection1Old) {
                if (!customercontactsCollection1New.contains(customercontactsCollection1OldCustomercontacts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customercontacts " + customercontactsCollection1OldCustomercontacts + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Appointment appointmentCollectionOldAppointment : appointmentCollectionOld) {
                if (!appointmentCollectionNew.contains(appointmentCollectionOldAppointment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Appointment " + appointmentCollectionOldAppointment + " since its companyemployee field is not nullable.");
                }
            }
            for (Appointment appointmentCollection1OldAppointment : appointmentCollection1Old) {
                if (!appointmentCollection1New.contains(appointmentCollection1OldAppointment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Appointment " + appointmentCollection1OldAppointment + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Appointment appointmentCollection2OldAppointment : appointmentCollection2Old) {
                if (!appointmentCollection2New.contains(appointmentCollection2OldAppointment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Appointment " + appointmentCollection2OldAppointment + " since its companyemployee2 field is not nullable.");
                }
            }
            for (Appointment appointmentCollection3OldAppointment : appointmentCollection3Old) {
                if (!appointmentCollection3New.contains(appointmentCollection3OldAppointment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Appointment " + appointmentCollection3OldAppointment + " since its companyemployee3 field is not nullable.");
                }
            }
            for (Companydepartment companydepartmentCollectionOldCompanydepartment : companydepartmentCollectionOld) {
                if (!companydepartmentCollectionNew.contains(companydepartmentCollectionOldCompanydepartment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companydepartment " + companydepartmentCollectionOldCompanydepartment + " since its companyemployee field is not nullable.");
                }
            }
            for (Companydepartment companydepartmentCollection1OldCompanydepartment : companydepartmentCollection1Old) {
                if (!companydepartmentCollection1New.contains(companydepartmentCollection1OldCompanydepartment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companydepartment " + companydepartmentCollection1OldCompanydepartment + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmusermodules crmusermodulesCollectionOldCrmusermodules : crmusermodulesCollectionOld) {
                if (!crmusermodulesCollectionNew.contains(crmusermodulesCollectionOldCrmusermodules)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmusermodules " + crmusermodulesCollectionOldCrmusermodules + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmusermodules crmusermodulesCollection1OldCrmusermodules : crmusermodulesCollection1Old) {
                if (!crmusermodulesCollection1New.contains(crmusermodulesCollection1OldCrmusermodules)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmusermodules " + crmusermodulesCollection1OldCrmusermodules + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmexpense crmexpenseCollectionOldCrmexpense : crmexpenseCollectionOld) {
                if (!crmexpenseCollectionNew.contains(crmexpenseCollectionOldCrmexpense)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmexpense " + crmexpenseCollectionOldCrmexpense + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmexpense crmexpenseCollection1OldCrmexpense : crmexpenseCollection1Old) {
                if (!crmexpenseCollection1New.contains(crmexpenseCollection1OldCrmexpense)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmexpense " + crmexpenseCollection1OldCrmexpense + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmworkorder crmworkorderCollectionOldCrmworkorder : crmworkorderCollectionOld) {
                if (!crmworkorderCollectionNew.contains(crmworkorderCollectionOldCrmworkorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorder " + crmworkorderCollectionOldCrmworkorder + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmworkorder crmworkorderCollection1OldCrmworkorder : crmworkorderCollection1Old) {
                if (!crmworkorderCollection1New.contains(crmworkorderCollection1OldCrmworkorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorder " + crmworkorderCollection1OldCrmworkorder + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmcampaigndocs crmcampaigndocsCollectionOldCrmcampaigndocs : crmcampaigndocsCollectionOld) {
                if (!crmcampaigndocsCollectionNew.contains(crmcampaigndocsCollectionOldCrmcampaigndocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaigndocs " + crmcampaigndocsCollectionOldCrmcampaigndocs + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmcampaigndocs crmcampaigndocsCollection1OldCrmcampaigndocs : crmcampaigndocsCollection1Old) {
                if (!crmcampaigndocsCollection1New.contains(crmcampaigndocsCollection1OldCrmcampaigndocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaigndocs " + crmcampaigndocsCollection1OldCrmcampaigndocs + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmemployeenote crmemployeenoteCollectionOldCrmemployeenote : crmemployeenoteCollectionOld) {
                if (!crmemployeenoteCollectionNew.contains(crmemployeenoteCollectionOldCrmemployeenote)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmemployeenote " + crmemployeenoteCollectionOldCrmemployeenote + " since its companyemployee field is not nullable.");
                }
            }
            for (Companycontacts companycontactsCollectionOldCompanycontacts : companycontactsCollectionOld) {
                if (!companycontactsCollectionNew.contains(companycontactsCollectionOldCompanycontacts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companycontacts " + companycontactsCollectionOldCompanycontacts + " since its companyemployee field is not nullable.");
                }
            }
            for (Companycontacts companycontactsCollection1OldCompanycontacts : companycontactsCollection1Old) {
                if (!companycontactsCollection1New.contains(companycontactsCollection1OldCompanycontacts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companycontacts " + companycontactsCollection1OldCompanycontacts + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companyemployee companyemployeeCollectionOldCompanyemployee : companyemployeeCollectionOld) {
                if (!companyemployeeCollectionNew.contains(companyemployeeCollectionOldCompanyemployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyemployee " + companyemployeeCollectionOldCompanyemployee + " since its companyemployee field is not nullable.");
                }
            }
            for (Companyemployee companyemployeeCollection1OldCompanyemployee : companyemployeeCollection1Old) {
                if (!companyemployeeCollection1New.contains(companyemployeeCollection1OldCompanyemployee)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyemployee " + companyemployeeCollection1OldCompanyemployee + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companyproducttype companyproducttypeCollectionOldCompanyproducttype : companyproducttypeCollectionOld) {
                if (!companyproducttypeCollectionNew.contains(companyproducttypeCollectionOldCompanyproducttype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproducttype " + companyproducttypeCollectionOldCompanyproducttype + " since its companyemployee field is not nullable.");
                }
            }
            for (Companyproducttype companyproducttypeCollection1OldCompanyproducttype : companyproducttypeCollection1Old) {
                if (!companyproducttypeCollection1New.contains(companyproducttypeCollection1OldCompanyproducttype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproducttype " + companyproducttypeCollection1OldCompanyproducttype + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmcampaign crmcampaignCollectionOldCrmcampaign : crmcampaignCollectionOld) {
                if (!crmcampaignCollectionNew.contains(crmcampaignCollectionOldCrmcampaign)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaign " + crmcampaignCollectionOldCrmcampaign + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmcampaign crmcampaignCollection1OldCrmcampaign : crmcampaignCollection1Old) {
                if (!crmcampaignCollection1New.contains(crmcampaignCollection1OldCrmcampaign)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaign " + crmcampaignCollection1OldCrmcampaign + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmmessagehistory crmmessagehistoryCollectionOldCrmmessagehistory : crmmessagehistoryCollectionOld) {
                if (!crmmessagehistoryCollectionNew.contains(crmmessagehistoryCollectionOldCrmmessagehistory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagehistory " + crmmessagehistoryCollectionOldCrmmessagehistory + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmmessagehistory crmmessagehistoryCollection1OldCrmmessagehistory : crmmessagehistoryCollection1Old) {
                if (!crmmessagehistoryCollection1New.contains(crmmessagehistoryCollection1OldCrmmessagehistory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmmessagehistory " + crmmessagehistoryCollection1OldCrmmessagehistory + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollectionOldCompanyaccountstackdocs : companyaccountstackdocsCollectionOld) {
                if (!companyaccountstackdocsCollectionNew.contains(companyaccountstackdocsCollectionOldCompanyaccountstackdocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstackdocs " + companyaccountstackdocsCollectionOldCompanyaccountstackdocs + " since its companyemployee field is not nullable.");
                }
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollection1OldCompanyaccountstackdocs : companyaccountstackdocsCollection1Old) {
                if (!companyaccountstackdocsCollection1New.contains(companyaccountstackdocsCollection1OldCompanyaccountstackdocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstackdocs " + companyaccountstackdocsCollection1OldCompanyaccountstackdocs + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmlabor crmlaborCollectionOldCrmlabor : crmlaborCollectionOld) {
                if (!crmlaborCollectionNew.contains(crmlaborCollectionOldCrmlabor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmlabor " + crmlaborCollectionOldCrmlabor + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmlabor crmlaborCollection1OldCrmlabor : crmlaborCollection1Old) {
                if (!crmlaborCollection1New.contains(crmlaborCollection1OldCrmlabor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmlabor " + crmlaborCollection1OldCrmlabor + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Customerbank customerbankCollectionOldCustomerbank : customerbankCollectionOld) {
                if (!customerbankCollectionNew.contains(customerbankCollectionOldCustomerbank)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customerbank " + customerbankCollectionOldCustomerbank + " since its companyemployee field is not nullable.");
                }
            }
            for (Customerbank customerbankCollection1OldCustomerbank : customerbankCollection1Old) {
                if (!customerbankCollection1New.contains(customerbankCollection1OldCustomerbank)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customerbank " + customerbankCollection1OldCustomerbank + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionOldCompnaypaymentcurrency : compnaypaymentcurrencyCollectionOld) {
                if (!compnaypaymentcurrencyCollectionNew.contains(compnaypaymentcurrencyCollectionOldCompnaypaymentcurrency)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compnaypaymentcurrency " + compnaypaymentcurrencyCollectionOldCompnaypaymentcurrency + " since its companyemployee field is not nullable.");
                }
            }
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollection1OldCompnaypaymentcurrency : compnaypaymentcurrencyCollection1Old) {
                if (!compnaypaymentcurrencyCollection1New.contains(compnaypaymentcurrencyCollection1OldCompnaypaymentcurrency)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compnaypaymentcurrency " + compnaypaymentcurrencyCollection1OldCompnaypaymentcurrency + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmproject crmprojectCollectionOldCrmproject : crmprojectCollectionOld) {
                if (!crmprojectCollectionNew.contains(crmprojectCollectionOldCrmproject)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmproject " + crmprojectCollectionOldCrmproject + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmproject crmprojectCollection1OldCrmproject : crmprojectCollection1Old) {
                if (!crmprojectCollection1New.contains(crmprojectCollection1OldCrmproject)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmproject " + crmprojectCollection1OldCrmproject + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmproject crmprojectCollection2OldCrmproject : crmprojectCollection2Old) {
                if (!crmprojectCollection2New.contains(crmprojectCollection2OldCrmproject)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmproject " + crmprojectCollection2OldCrmproject + " since its companyemployee2 field is not nullable.");
                }
            }
            for (Companyemployeeaddress companyemployeeaddressCollectionOldCompanyemployeeaddress : companyemployeeaddressCollectionOld) {
                if (!companyemployeeaddressCollectionNew.contains(companyemployeeaddressCollectionOldCompanyemployeeaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyemployeeaddress " + companyemployeeaddressCollectionOldCompanyemployeeaddress + " since its companyemployee field is not nullable.");
                }
            }
            for (Companyemployeeaddress companyemployeeaddressCollection1OldCompanyemployeeaddress : companyemployeeaddressCollection1Old) {
                if (!companyemployeeaddressCollection1New.contains(companyemployeeaddressCollection1OldCompanyemployeeaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyemployeeaddress " + companyemployeeaddressCollection1OldCompanyemployeeaddress + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companyemployeeaddress companyemployeeaddressCollection2OldCompanyemployeeaddress : companyemployeeaddressCollection2Old) {
                if (!companyemployeeaddressCollection2New.contains(companyemployeeaddressCollection2OldCompanyemployeeaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyemployeeaddress " + companyemployeeaddressCollection2OldCompanyemployeeaddress + " since its companyemployee2 field is not nullable.");
                }
            }
            for (Crmforumdocs crmforumdocsCollectionOldCrmforumdocs : crmforumdocsCollectionOld) {
                if (!crmforumdocsCollectionNew.contains(crmforumdocsCollectionOldCrmforumdocs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmforumdocs " + crmforumdocsCollectionOldCrmforumdocs + " since its companyemployee field is not nullable.");
                }
            }
            for (Companyproduct companyproductCollectionOldCompanyproduct : companyproductCollectionOld) {
                if (!companyproductCollectionNew.contains(companyproductCollectionOldCompanyproduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproduct " + companyproductCollectionOldCompanyproduct + " since its companyemployee field is not nullable.");
                }
            }
            for (Companyproduct companyproductCollection1OldCompanyproduct : companyproductCollection1Old) {
                if (!companyproductCollection1New.contains(companyproductCollection1OldCompanyproduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproduct " + companyproductCollection1OldCompanyproduct + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Employeedesignation employeedesignationCollectionOldEmployeedesignation : employeedesignationCollectionOld) {
                if (!employeedesignationCollectionNew.contains(employeedesignationCollectionOldEmployeedesignation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employeedesignation " + employeedesignationCollectionOldEmployeedesignation + " since its companyemployee field is not nullable.");
                }
            }
            for (Employeedesignation employeedesignationCollection1OldEmployeedesignation : employeedesignationCollection1Old) {
                if (!employeedesignationCollection1New.contains(employeedesignationCollection1OldEmployeedesignation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Employeedesignation " + employeedesignationCollection1OldEmployeedesignation + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companyaccountstack companyaccountstackCollectionOldCompanyaccountstack : companyaccountstackCollectionOld) {
                if (!companyaccountstackCollectionNew.contains(companyaccountstackCollectionOldCompanyaccountstack)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstack " + companyaccountstackCollectionOldCompanyaccountstack + " since its companyemployee field is not nullable.");
                }
            }
            for (Companyaccountstack companyaccountstackCollection1OldCompanyaccountstack : companyaccountstackCollection1Old) {
                if (!companyaccountstackCollection1New.contains(companyaccountstackCollection1OldCompanyaccountstack)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstack " + companyaccountstackCollection1OldCompanyaccountstack + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmprojectteammembers crmprojectteammembersCollectionOldCrmprojectteammembers : crmprojectteammembersCollectionOld) {
                if (!crmprojectteammembersCollectionNew.contains(crmprojectteammembersCollectionOldCrmprojectteammembers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectteammembers " + crmprojectteammembersCollectionOldCrmprojectteammembers + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmprojectteammembers crmprojectteammembersCollection1OldCrmprojectteammembers : crmprojectteammembersCollection1Old) {
                if (!crmprojectteammembersCollection1New.contains(crmprojectteammembersCollection1OldCrmprojectteammembers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectteammembers " + crmprojectteammembersCollection1OldCrmprojectteammembers + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmprojectteammembers crmprojectteammembersCollection2OldCrmprojectteammembers : crmprojectteammembersCollection2Old) {
                if (!crmprojectteammembersCollection2New.contains(crmprojectteammembersCollection2OldCrmprojectteammembers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectteammembers " + crmprojectteammembersCollection2OldCrmprojectteammembers + " since its companyemployee2 field is not nullable.");
                }
            }
            for (Componentattachments componentattachmentsCollectionOldComponentattachments : componentattachmentsCollectionOld) {
                if (!componentattachmentsCollectionNew.contains(componentattachmentsCollectionOldComponentattachments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Componentattachments " + componentattachmentsCollectionOldComponentattachments + " since its companyemployee field is not nullable.");
                }
            }
            for (Componentattachments componentattachmentsCollection1OldComponentattachments : componentattachmentsCollection1Old) {
                if (!componentattachmentsCollection1New.contains(componentattachmentsCollection1OldComponentattachments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Componentattachments " + componentattachmentsCollection1OldComponentattachments + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionOldCrmvisitorcontactsaddress : crmvisitorcontactsaddressCollectionOld) {
                if (!crmvisitorcontactsaddressCollectionNew.contains(crmvisitorcontactsaddressCollectionOldCrmvisitorcontactsaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmvisitorcontactsaddress " + crmvisitorcontactsaddressCollectionOldCrmvisitorcontactsaddress + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmvisitorcontacts crmvisitorcontactsCollectionOldCrmvisitorcontacts : crmvisitorcontactsCollectionOld) {
                if (!crmvisitorcontactsCollectionNew.contains(crmvisitorcontactsCollectionOldCrmvisitorcontacts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmvisitorcontacts " + crmvisitorcontactsCollectionOldCrmvisitorcontacts + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmvisitorcontacts crmvisitorcontactsCollection1OldCrmvisitorcontacts : crmvisitorcontactsCollection1Old) {
                if (!crmvisitorcontactsCollection1New.contains(crmvisitorcontactsCollection1OldCrmvisitorcontacts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmvisitorcontacts " + crmvisitorcontactsCollection1OldCrmvisitorcontacts + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companyaccountstackcd companyaccountstackcdCollectionOldCompanyaccountstackcd : companyaccountstackcdCollectionOld) {
                if (!companyaccountstackcdCollectionNew.contains(companyaccountstackcdCollectionOldCompanyaccountstackcd)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstackcd " + companyaccountstackcdCollectionOldCompanyaccountstackcd + " since its companyemployee field is not nullable.");
                }
            }
            for (Companyaccountstackcd companyaccountstackcdCollection1OldCompanyaccountstackcd : companyaccountstackcdCollection1Old) {
                if (!companyaccountstackcdCollection1New.contains(companyaccountstackcdCollection1OldCompanyaccountstackcd)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaccountstackcd " + companyaccountstackcdCollection1OldCompanyaccountstackcd + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionOldCrmprojectticketmanagement : crmprojectticketmanagementCollectionOld) {
                if (!crmprojectticketmanagementCollectionNew.contains(crmprojectticketmanagementCollectionOldCrmprojectticketmanagement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectticketmanagement " + crmprojectticketmanagementCollectionOldCrmprojectticketmanagement + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1OldCrmprojectticketmanagement : crmprojectticketmanagementCollection1Old) {
                if (!crmprojectticketmanagementCollection1New.contains(crmprojectticketmanagementCollection1OldCrmprojectticketmanagement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectticketmanagement " + crmprojectticketmanagementCollection1OldCrmprojectticketmanagement + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection2OldCrmprojectticketmanagement : crmprojectticketmanagementCollection2Old) {
                if (!crmprojectticketmanagementCollection2New.contains(crmprojectticketmanagementCollection2OldCrmprojectticketmanagement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectticketmanagement " + crmprojectticketmanagementCollection2OldCrmprojectticketmanagement + " since its companyemployee2 field is not nullable.");
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection3OldCrmprojectticketmanagement : crmprojectticketmanagementCollection3Old) {
                if (!crmprojectticketmanagementCollection3New.contains(crmprojectticketmanagementCollection3OldCrmprojectticketmanagement)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectticketmanagement " + crmprojectticketmanagementCollection3OldCrmprojectticketmanagement + " since its companyemployee3 field is not nullable.");
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionOldCrmprojectticketnotification : crmprojectticketnotificationCollectionOld) {
                if (!crmprojectticketnotificationCollectionNew.contains(crmprojectticketnotificationCollectionOldCrmprojectticketnotification)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectticketnotification " + crmprojectticketnotificationCollectionOldCrmprojectticketnotification + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollection1OldCrmprojectticketnotification : crmprojectticketnotificationCollection1Old) {
                if (!crmprojectticketnotificationCollection1New.contains(crmprojectticketnotificationCollection1OldCrmprojectticketnotification)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectticketnotification " + crmprojectticketnotificationCollection1OldCrmprojectticketnotification + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollection2OldCrmprojectticketnotification : crmprojectticketnotificationCollection2Old) {
                if (!crmprojectticketnotificationCollection2New.contains(crmprojectticketnotificationCollection2OldCrmprojectticketnotification)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectticketnotification " + crmprojectticketnotificationCollection2OldCrmprojectticketnotification + " since its companyemployee2 field is not nullable.");
                }
            }
            for (Crmvisitor crmvisitorCollectionOldCrmvisitor : crmvisitorCollectionOld) {
                if (!crmvisitorCollectionNew.contains(crmvisitorCollectionOldCrmvisitor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmvisitor " + crmvisitorCollectionOldCrmvisitor + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmvisitor crmvisitorCollection1OldCrmvisitor : crmvisitorCollection1Old) {
                if (!crmvisitorCollection1New.contains(crmvisitorCollection1OldCrmvisitor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmvisitor " + crmvisitorCollection1OldCrmvisitor + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmworkordertype crmworkordertypeCollectionOldCrmworkordertype : crmworkordertypeCollectionOld) {
                if (!crmworkordertypeCollectionNew.contains(crmworkordertypeCollectionOldCrmworkordertype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkordertype " + crmworkordertypeCollectionOldCrmworkordertype + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmworkordertype crmworkordertypeCollection1OldCrmworkordertype : crmworkordertypeCollection1Old) {
                if (!crmworkordertypeCollection1New.contains(crmworkordertypeCollection1OldCrmworkordertype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkordertype " + crmworkordertypeCollection1OldCrmworkordertype + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmlabortype crmlabortypeCollectionOldCrmlabortype : crmlabortypeCollectionOld) {
                if (!crmlabortypeCollectionNew.contains(crmlabortypeCollectionOldCrmlabortype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmlabortype " + crmlabortypeCollectionOldCrmlabortype + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmlabortype crmlabortypeCollection1OldCrmlabortype : crmlabortypeCollection1Old) {
                if (!crmlabortypeCollection1New.contains(crmlabortypeCollection1OldCrmlabortype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmlabortype " + crmlabortypeCollection1OldCrmlabortype + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmbillingtype crmbillingtypeCollectionOldCrmbillingtype : crmbillingtypeCollectionOld) {
                if (!crmbillingtypeCollectionNew.contains(crmbillingtypeCollectionOldCrmbillingtype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmbillingtype " + crmbillingtypeCollectionOldCrmbillingtype + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmbillingtype crmbillingtypeCollection1OldCrmbillingtype : crmbillingtypeCollection1Old) {
                if (!crmbillingtypeCollection1New.contains(crmbillingtypeCollection1OldCrmbillingtype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmbillingtype " + crmbillingtypeCollection1OldCrmbillingtype + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companyproductcategory companyproductcategoryCollectionOldCompanyproductcategory : companyproductcategoryCollectionOld) {
                if (!companyproductcategoryCollectionNew.contains(companyproductcategoryCollectionOldCompanyproductcategory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproductcategory " + companyproductcategoryCollectionOldCompanyproductcategory + " since its companyemployee field is not nullable.");
                }
            }
            for (Companyproductcategory companyproductcategoryCollection1OldCompanyproductcategory : companyproductcategoryCollection1Old) {
                if (!companyproductcategoryCollection1New.contains(companyproductcategoryCollection1OldCompanyproductcategory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyproductcategory " + companyproductcategoryCollection1OldCompanyproductcategory + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmprojecttask crmprojecttaskCollectionOldCrmprojecttask : crmprojecttaskCollectionOld) {
                if (!crmprojecttaskCollectionNew.contains(crmprojecttaskCollectionOldCrmprojecttask)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojecttask " + crmprojecttaskCollectionOldCrmprojecttask + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmprojecttask crmprojecttaskCollection1OldCrmprojecttask : crmprojecttaskCollection1Old) {
                if (!crmprojecttaskCollection1New.contains(crmprojecttaskCollection1OldCrmprojecttask)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojecttask " + crmprojecttaskCollection1OldCrmprojecttask + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollectionOldCrmcampaignprops : crmcampaignpropsCollectionOld) {
                if (!crmcampaignpropsCollectionNew.contains(crmcampaignpropsCollectionOldCrmcampaignprops)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignprops " + crmcampaignpropsCollectionOldCrmcampaignprops + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollection1OldCrmcampaignprops : crmcampaignpropsCollection1Old) {
                if (!crmcampaignpropsCollection1New.contains(crmcampaignpropsCollection1OldCrmcampaignprops)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignprops " + crmcampaignpropsCollection1OldCrmcampaignprops + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Customercontactsaddress customercontactsaddressCollectionOldCustomercontactsaddress : customercontactsaddressCollectionOld) {
                if (!customercontactsaddressCollectionNew.contains(customercontactsaddressCollectionOldCustomercontactsaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customercontactsaddress " + customercontactsaddressCollectionOldCustomercontactsaddress + " since its companyemployee field is not nullable.");
                }
            }
            for (Customercontactsaddress customercontactsaddressCollection1OldCustomercontactsaddress : customercontactsaddressCollection1Old) {
                if (!customercontactsaddressCollection1New.contains(customercontactsaddressCollection1OldCustomercontactsaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customercontactsaddress " + customercontactsaddressCollection1OldCustomercontactsaddress + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Customercategory customercategoryCollectionOldCustomercategory : customercategoryCollectionOld) {
                if (!customercategoryCollectionNew.contains(customercategoryCollectionOldCustomercategory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customercategory " + customercategoryCollectionOldCustomercategory + " since its companyemployee field is not nullable.");
                }
            }
            for (Customercategory customercategoryCollection1OldCustomercategory : customercategoryCollection1Old) {
                if (!customercategoryCollection1New.contains(customercategoryCollection1OldCustomercategory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Customercategory " + customercategoryCollection1OldCustomercategory + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Productcomponents productcomponentsCollectionOldProductcomponents : productcomponentsCollectionOld) {
                if (!productcomponentsCollectionNew.contains(productcomponentsCollectionOldProductcomponents)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productcomponents " + productcomponentsCollectionOldProductcomponents + " since its companyemployee field is not nullable.");
                }
            }
            for (Productcomponents productcomponentsCollection1OldProductcomponents : productcomponentsCollection1Old) {
                if (!productcomponentsCollection1New.contains(productcomponentsCollection1OldProductcomponents)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productcomponents " + productcomponentsCollection1OldProductcomponents + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmexpensetype crmexpensetypeCollectionOldCrmexpensetype : crmexpensetypeCollectionOld) {
                if (!crmexpensetypeCollectionNew.contains(crmexpensetypeCollectionOldCrmexpensetype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmexpensetype " + crmexpensetypeCollectionOldCrmexpensetype + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmexpensetype crmexpensetypeCollection1OldCrmexpensetype : crmexpensetypeCollection1Old) {
                if (!crmexpensetypeCollection1New.contains(crmexpensetypeCollection1OldCrmexpensetype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmexpensetype " + crmexpensetypeCollection1OldCrmexpensetype + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companypayments companypaymentsCollectionOldCompanypayments : companypaymentsCollectionOld) {
                if (!companypaymentsCollectionNew.contains(companypaymentsCollectionOldCompanypayments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companypayments " + companypaymentsCollectionOldCompanypayments + " since its companyemployee field is not nullable.");
                }
            }
            for (Companypayments companypaymentsCollection1OldCompanypayments : companypaymentsCollection1Old) {
                if (!companypaymentsCollection1New.contains(companypaymentsCollection1OldCompanypayments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companypayments " + companypaymentsCollection1OldCompanypayments + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmworkorderresolution crmworkorderresolutionCollectionOldCrmworkorderresolution : crmworkorderresolutionCollectionOld) {
                if (!crmworkorderresolutionCollectionNew.contains(crmworkorderresolutionCollectionOldCrmworkorderresolution)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorderresolution " + crmworkorderresolutionCollectionOldCrmworkorderresolution + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmworkorderresolution crmworkorderresolutionCollection1OldCrmworkorderresolution : crmworkorderresolutionCollection1Old) {
                if (!crmworkorderresolutionCollection1New.contains(crmworkorderresolutionCollection1OldCrmworkorderresolution)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorderresolution " + crmworkorderresolutionCollection1OldCrmworkorderresolution + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Company companyCollectionOldCompany : companyCollectionOld) {
                if (!companyCollectionNew.contains(companyCollectionOldCompany)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Company " + companyCollectionOldCompany + " since its companyemployee field is not nullable.");
                }
            }
            for (Company companyCollection1OldCompany : companyCollection1Old) {
                if (!companyCollection1New.contains(companyCollection1OldCompany)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Company " + companyCollection1OldCompany + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companyaddresstype companyaddresstypeCollectionOldCompanyaddresstype : companyaddresstypeCollectionOld) {
                if (!companyaddresstypeCollectionNew.contains(companyaddresstypeCollectionOldCompanyaddresstype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaddresstype " + companyaddresstypeCollectionOldCompanyaddresstype + " since its companyemployee field is not nullable.");
                }
            }
            for (Companyaddresstype companyaddresstypeCollection1OldCompanyaddresstype : companyaddresstypeCollection1Old) {
                if (!companyaddresstypeCollection1New.contains(companyaddresstypeCollection1OldCompanyaddresstype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companyaddresstype " + companyaddresstypeCollection1OldCompanyaddresstype + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmemployeecontacts crmemployeecontactsCollectionOldCrmemployeecontacts : crmemployeecontactsCollectionOld) {
                if (!crmemployeecontactsCollectionNew.contains(crmemployeecontactsCollectionOldCrmemployeecontacts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmemployeecontacts " + crmemployeecontactsCollectionOldCrmemployeecontacts + " since its companyemployee field is not nullable.");
                }
            }
            for (Approval approvalCollectionOldApproval : approvalCollectionOld) {
                if (!approvalCollectionNew.contains(approvalCollectionOldApproval)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Approval " + approvalCollectionOldApproval + " since its companyemployee field is not nullable.");
                }
            }
            for (Approval approvalCollection1OldApproval : approvalCollection1Old) {
                if (!approvalCollection1New.contains(approvalCollection1OldApproval)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Approval " + approvalCollection1OldApproval + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Approval approvalCollection2OldApproval : approvalCollection2Old) {
                if (!approvalCollection2New.contains(approvalCollection2OldApproval)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Approval " + approvalCollection2OldApproval + " since its companyemployee2 field is not nullable.");
                }
            }
            for (Workorderinstructions workorderinstructionsCollectionOldWorkorderinstructions : workorderinstructionsCollectionOld) {
                if (!workorderinstructionsCollectionNew.contains(workorderinstructionsCollectionOldWorkorderinstructions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Workorderinstructions " + workorderinstructionsCollectionOldWorkorderinstructions + " since its companyemployee field is not nullable.");
                }
            }
            for (Workorderinstructions workorderinstructionsCollection1OldWorkorderinstructions : workorderinstructionsCollection1Old) {
                if (!workorderinstructionsCollection1New.contains(workorderinstructionsCollection1OldWorkorderinstructions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Workorderinstructions " + workorderinstructionsCollection1OldWorkorderinstructions + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companycustomer companycustomerCollectionOldCompanycustomer : companycustomerCollectionOld) {
                if (!companycustomerCollectionNew.contains(companycustomerCollectionOldCompanycustomer)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companycustomer " + companycustomerCollectionOldCompanycustomer + " since its companyemployee field is not nullable.");
                }
            }
            for (Companycustomer companycustomerCollection1OldCompanycustomer : companycustomerCollection1Old) {
                if (!companycustomerCollection1New.contains(companycustomerCollection1OldCompanycustomer)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companycustomer " + companycustomerCollection1OldCompanycustomer + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmforum crmforumCollectionOldCrmforum : crmforumCollectionOld) {
                if (!crmforumCollectionNew.contains(crmforumCollectionOldCrmforum)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmforum " + crmforumCollectionOldCrmforum + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmprojectstatus crmprojectstatusCollectionOldCrmprojectstatus : crmprojectstatusCollectionOld) {
                if (!crmprojectstatusCollectionNew.contains(crmprojectstatusCollectionOldCrmprojectstatus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectstatus " + crmprojectstatusCollectionOldCrmprojectstatus + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmprojectstatus crmprojectstatusCollection1OldCrmprojectstatus : crmprojectstatusCollection1Old) {
                if (!crmprojectstatusCollection1New.contains(crmprojectstatusCollection1OldCrmprojectstatus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprojectstatus " + crmprojectstatusCollection1OldCrmprojectstatus + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionOldCrmschedulerevnttype : crmschedulerevnttypeCollectionOld) {
                if (!crmschedulerevnttypeCollectionNew.contains(crmschedulerevnttypeCollectionOldCrmschedulerevnttype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmschedulerevnttype " + crmschedulerevnttypeCollectionOldCrmschedulerevnttype + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmschedulerevnttype crmschedulerevnttypeCollection1OldCrmschedulerevnttype : crmschedulerevnttypeCollection1Old) {
                if (!crmschedulerevnttypeCollection1New.contains(crmschedulerevnttypeCollection1OldCrmschedulerevnttype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmschedulerevnttype " + crmschedulerevnttypeCollection1OldCrmschedulerevnttype + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmschedulerefcode crmschedulerefcodeCollectionOldCrmschedulerefcode : crmschedulerefcodeCollectionOld) {
                if (!crmschedulerefcodeCollectionNew.contains(crmschedulerefcodeCollectionOldCrmschedulerefcode)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmschedulerefcode " + crmschedulerefcodeCollectionOldCrmschedulerefcode + " since its companyemployee field is not nullable.");
                }
            }
            for (Crmschedulerefcode crmschedulerefcodeCollection1OldCrmschedulerefcode : crmschedulerefcodeCollection1Old) {
                if (!crmschedulerefcodeCollection1New.contains(crmschedulerefcodeCollection1OldCrmschedulerefcode)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmschedulerefcode " + crmschedulerefcodeCollection1OldCrmschedulerefcode + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Componenttype componenttypeCollectionOldComponenttype : componenttypeCollectionOld) {
                if (!componenttypeCollectionNew.contains(componenttypeCollectionOldComponenttype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Componenttype " + componenttypeCollectionOldComponenttype + " since its companyemployee field is not nullable.");
                }
            }
            for (Componenttype componenttypeCollection1OldComponenttype : componenttypeCollection1Old) {
                if (!componenttypeCollection1New.contains(componenttypeCollection1OldComponenttype)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Componenttype " + componenttypeCollection1OldComponenttype + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companypaymentscheme companypaymentschemeCollectionOldCompanypaymentscheme : companypaymentschemeCollectionOld) {
                if (!companypaymentschemeCollectionNew.contains(companypaymentschemeCollectionOldCompanypaymentscheme)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companypaymentscheme " + companypaymentschemeCollectionOldCompanypaymentscheme + " since its companyemployee field is not nullable.");
                }
            }
            for (Companypaymentscheme companypaymentschemeCollection1OldCompanypaymentscheme : companypaymentschemeCollection1Old) {
                if (!companypaymentschemeCollection1New.contains(companypaymentschemeCollection1OldCompanypaymentscheme)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companypaymentscheme " + companypaymentschemeCollection1OldCompanypaymentscheme + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Companycontactsaddress companycontactsaddressCollectionOldCompanycontactsaddress : companycontactsaddressCollectionOld) {
                if (!companycontactsaddressCollectionNew.contains(companycontactsaddressCollectionOldCompanycontactsaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companycontactsaddress " + companycontactsaddressCollectionOldCompanycontactsaddress + " since its companyemployee field is not nullable.");
                }
            }
            for (Companycontactsaddress companycontactsaddressCollection1OldCompanycontactsaddress : companycontactsaddressCollection1Old) {
                if (!companycontactsaddressCollection1New.contains(companycontactsaddressCollection1OldCompanycontactsaddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Companycontactsaddress " + companycontactsaddressCollection1OldCompanycontactsaddress + " since its companyemployee1 field is not nullable.");
                }
            }
            for (Crmprofilesettings crmprofilesettingsCollectionOldCrmprofilesettings : crmprofilesettingsCollectionOld) {
                if (!crmprofilesettingsCollectionNew.contains(crmprofilesettingsCollectionOldCrmprofilesettings)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmprofilesettings " + crmprofilesettingsCollectionOldCrmprofilesettings + " since its companyemployee field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companyemployee.setCompany(companyNew);
            }
            if (companydepartmentNew != null) {
                companydepartmentNew = em.getReference(companydepartmentNew.getClass(), companydepartmentNew.getCompanydepartmentPK());
                companyemployee.setCompanydepartment(companydepartmentNew);
            }
            if (companyemployeeRelNew != null) {
                companyemployeeRelNew = em.getReference(companyemployeeRelNew.getClass(), companyemployeeRelNew.getCompanyemployeePK());
                companyemployee.setCompanyemployee(companyemployeeRelNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companyemployee.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmmessagechannel> attachedCrmmessagechannelCollectionNew = new ArrayList<Crmmessagechannel>();
            for (Crmmessagechannel crmmessagechannelCollectionNewCrmmessagechannelToAttach : crmmessagechannelCollectionNew) {
                crmmessagechannelCollectionNewCrmmessagechannelToAttach = em.getReference(crmmessagechannelCollectionNewCrmmessagechannelToAttach.getClass(), crmmessagechannelCollectionNewCrmmessagechannelToAttach.getCrmmessagechannelPK());
                attachedCrmmessagechannelCollectionNew.add(crmmessagechannelCollectionNewCrmmessagechannelToAttach);
            }
            crmmessagechannelCollectionNew = attachedCrmmessagechannelCollectionNew;
            companyemployee.setCrmmessagechannelCollection(crmmessagechannelCollectionNew);
            Collection<Crmmessagechannel> attachedCrmmessagechannelCollection1New = new ArrayList<Crmmessagechannel>();
            for (Crmmessagechannel crmmessagechannelCollection1NewCrmmessagechannelToAttach : crmmessagechannelCollection1New) {
                crmmessagechannelCollection1NewCrmmessagechannelToAttach = em.getReference(crmmessagechannelCollection1NewCrmmessagechannelToAttach.getClass(), crmmessagechannelCollection1NewCrmmessagechannelToAttach.getCrmmessagechannelPK());
                attachedCrmmessagechannelCollection1New.add(crmmessagechannelCollection1NewCrmmessagechannelToAttach);
            }
            crmmessagechannelCollection1New = attachedCrmmessagechannelCollection1New;
            companyemployee.setCrmmessagechannelCollection1(crmmessagechannelCollection1New);
            Collection<Crmmessagechannel> attachedCrmmessagechannelCollection2New = new ArrayList<Crmmessagechannel>();
            for (Crmmessagechannel crmmessagechannelCollection2NewCrmmessagechannelToAttach : crmmessagechannelCollection2New) {
                crmmessagechannelCollection2NewCrmmessagechannelToAttach = em.getReference(crmmessagechannelCollection2NewCrmmessagechannelToAttach.getClass(), crmmessagechannelCollection2NewCrmmessagechannelToAttach.getCrmmessagechannelPK());
                attachedCrmmessagechannelCollection2New.add(crmmessagechannelCollection2NewCrmmessagechannelToAttach);
            }
            crmmessagechannelCollection2New = attachedCrmmessagechannelCollection2New;
            companyemployee.setCrmmessagechannelCollection2(crmmessagechannelCollection2New);
            Collection<Companybank> attachedCompanybankCollectionNew = new ArrayList<Companybank>();
            for (Companybank companybankCollectionNewCompanybankToAttach : companybankCollectionNew) {
                companybankCollectionNewCompanybankToAttach = em.getReference(companybankCollectionNewCompanybankToAttach.getClass(), companybankCollectionNewCompanybankToAttach.getCompanybankPK());
                attachedCompanybankCollectionNew.add(companybankCollectionNewCompanybankToAttach);
            }
            companybankCollectionNew = attachedCompanybankCollectionNew;
            companyemployee.setCompanybankCollection(companybankCollectionNew);
            Collection<Companybank> attachedCompanybankCollection1New = new ArrayList<Companybank>();
            for (Companybank companybankCollection1NewCompanybankToAttach : companybankCollection1New) {
                companybankCollection1NewCompanybankToAttach = em.getReference(companybankCollection1NewCompanybankToAttach.getClass(), companybankCollection1NewCompanybankToAttach.getCompanybankPK());
                attachedCompanybankCollection1New.add(companybankCollection1NewCompanybankToAttach);
            }
            companybankCollection1New = attachedCompanybankCollection1New;
            companyemployee.setCompanybankCollection1(companybankCollection1New);
            Collection<Crmmessagechanneltemplate> attachedCrmmessagechanneltemplateCollectionNew = new ArrayList<Crmmessagechanneltemplate>();
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplateToAttach : crmmessagechanneltemplateCollectionNew) {
                crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplateToAttach = em.getReference(crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplateToAttach.getClass(), crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplateToAttach.getCrmmessagechanneltemplatePK());
                attachedCrmmessagechanneltemplateCollectionNew.add(crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplateToAttach);
            }
            crmmessagechanneltemplateCollectionNew = attachedCrmmessagechanneltemplateCollectionNew;
            companyemployee.setCrmmessagechanneltemplateCollection(crmmessagechanneltemplateCollectionNew);
            Collection<Crmmessagechanneltemplate> attachedCrmmessagechanneltemplateCollection1New = new ArrayList<Crmmessagechanneltemplate>();
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplateToAttach : crmmessagechanneltemplateCollection1New) {
                crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplateToAttach = em.getReference(crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplateToAttach.getClass(), crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplateToAttach.getCrmmessagechanneltemplatePK());
                attachedCrmmessagechanneltemplateCollection1New.add(crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplateToAttach);
            }
            crmmessagechanneltemplateCollection1New = attachedCrmmessagechanneltemplateCollection1New;
            companyemployee.setCrmmessagechanneltemplateCollection1(crmmessagechanneltemplateCollection1New);
            Collection<Crmprojectprops> attachedCrmprojectpropsCollectionNew = new ArrayList<Crmprojectprops>();
            for (Crmprojectprops crmprojectpropsCollectionNewCrmprojectpropsToAttach : crmprojectpropsCollectionNew) {
                crmprojectpropsCollectionNewCrmprojectpropsToAttach = em.getReference(crmprojectpropsCollectionNewCrmprojectpropsToAttach.getClass(), crmprojectpropsCollectionNewCrmprojectpropsToAttach.getCrmprojectpropsPK());
                attachedCrmprojectpropsCollectionNew.add(crmprojectpropsCollectionNewCrmprojectpropsToAttach);
            }
            crmprojectpropsCollectionNew = attachedCrmprojectpropsCollectionNew;
            companyemployee.setCrmprojectpropsCollection(crmprojectpropsCollectionNew);
            Collection<Crmprojectprops> attachedCrmprojectpropsCollection1New = new ArrayList<Crmprojectprops>();
            for (Crmprojectprops crmprojectpropsCollection1NewCrmprojectpropsToAttach : crmprojectpropsCollection1New) {
                crmprojectpropsCollection1NewCrmprojectpropsToAttach = em.getReference(crmprojectpropsCollection1NewCrmprojectpropsToAttach.getClass(), crmprojectpropsCollection1NewCrmprojectpropsToAttach.getCrmprojectpropsPK());
                attachedCrmprojectpropsCollection1New.add(crmprojectpropsCollection1NewCrmprojectpropsToAttach);
            }
            crmprojectpropsCollection1New = attachedCrmprojectpropsCollection1New;
            companyemployee.setCrmprojectpropsCollection1(crmprojectpropsCollection1New);
            Collection<Crmcampaignreceiver> attachedCrmcampaignreceiverCollectionNew = new ArrayList<Crmcampaignreceiver>();
            for (Crmcampaignreceiver crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach : crmcampaignreceiverCollectionNew) {
                crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach = em.getReference(crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach.getClass(), crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach.getCrmcampaignreceiverPK());
                attachedCrmcampaignreceiverCollectionNew.add(crmcampaignreceiverCollectionNewCrmcampaignreceiverToAttach);
            }
            crmcampaignreceiverCollectionNew = attachedCrmcampaignreceiverCollectionNew;
            companyemployee.setCrmcampaignreceiverCollection(crmcampaignreceiverCollectionNew);
            Collection<Crmcampaignreceiver> attachedCrmcampaignreceiverCollection1New = new ArrayList<Crmcampaignreceiver>();
            for (Crmcampaignreceiver crmcampaignreceiverCollection1NewCrmcampaignreceiverToAttach : crmcampaignreceiverCollection1New) {
                crmcampaignreceiverCollection1NewCrmcampaignreceiverToAttach = em.getReference(crmcampaignreceiverCollection1NewCrmcampaignreceiverToAttach.getClass(), crmcampaignreceiverCollection1NewCrmcampaignreceiverToAttach.getCrmcampaignreceiverPK());
                attachedCrmcampaignreceiverCollection1New.add(crmcampaignreceiverCollection1NewCrmcampaignreceiverToAttach);
            }
            crmcampaignreceiverCollection1New = attachedCrmcampaignreceiverCollection1New;
            companyemployee.setCrmcampaignreceiverCollection1(crmcampaignreceiverCollection1New);
            Collection<Crmworkordersettings> attachedCrmworkordersettingsCollectionNew = new ArrayList<Crmworkordersettings>();
            for (Crmworkordersettings crmworkordersettingsCollectionNewCrmworkordersettingsToAttach : crmworkordersettingsCollectionNew) {
                crmworkordersettingsCollectionNewCrmworkordersettingsToAttach = em.getReference(crmworkordersettingsCollectionNewCrmworkordersettingsToAttach.getClass(), crmworkordersettingsCollectionNewCrmworkordersettingsToAttach.getCrmworkordersettingsPK());
                attachedCrmworkordersettingsCollectionNew.add(crmworkordersettingsCollectionNewCrmworkordersettingsToAttach);
            }
            crmworkordersettingsCollectionNew = attachedCrmworkordersettingsCollectionNew;
            companyemployee.setCrmworkordersettingsCollection(crmworkordersettingsCollectionNew);
            Collection<Productattachments> attachedProductattachmentsCollectionNew = new ArrayList<Productattachments>();
            for (Productattachments productattachmentsCollectionNewProductattachmentsToAttach : productattachmentsCollectionNew) {
                productattachmentsCollectionNewProductattachmentsToAttach = em.getReference(productattachmentsCollectionNewProductattachmentsToAttach.getClass(), productattachmentsCollectionNewProductattachmentsToAttach.getProductattachmentsPK());
                attachedProductattachmentsCollectionNew.add(productattachmentsCollectionNewProductattachmentsToAttach);
            }
            productattachmentsCollectionNew = attachedProductattachmentsCollectionNew;
            companyemployee.setProductattachmentsCollection(productattachmentsCollectionNew);
            Collection<Productattachments> attachedProductattachmentsCollection1New = new ArrayList<Productattachments>();
            for (Productattachments productattachmentsCollection1NewProductattachmentsToAttach : productattachmentsCollection1New) {
                productattachmentsCollection1NewProductattachmentsToAttach = em.getReference(productattachmentsCollection1NewProductattachmentsToAttach.getClass(), productattachmentsCollection1NewProductattachmentsToAttach.getProductattachmentsPK());
                attachedProductattachmentsCollection1New.add(productattachmentsCollection1NewProductattachmentsToAttach);
            }
            productattachmentsCollection1New = attachedProductattachmentsCollection1New;
            companyemployee.setProductattachmentsCollection1(productattachmentsCollection1New);
            Collection<Productattachments> attachedProductattachmentsCollection2New = new ArrayList<Productattachments>();
            for (Productattachments productattachmentsCollection2NewProductattachmentsToAttach : productattachmentsCollection2New) {
                productattachmentsCollection2NewProductattachmentsToAttach = em.getReference(productattachmentsCollection2NewProductattachmentsToAttach.getClass(), productattachmentsCollection2NewProductattachmentsToAttach.getProductattachmentsPK());
                attachedProductattachmentsCollection2New.add(productattachmentsCollection2NewProductattachmentsToAttach);
            }
            productattachmentsCollection2New = attachedProductattachmentsCollection2New;
            companyemployee.setProductattachmentsCollection2(productattachmentsCollection2New);
            Collection<Crmcampaignstatus> attachedCrmcampaignstatusCollectionNew = new ArrayList<Crmcampaignstatus>();
            for (Crmcampaignstatus crmcampaignstatusCollectionNewCrmcampaignstatusToAttach : crmcampaignstatusCollectionNew) {
                crmcampaignstatusCollectionNewCrmcampaignstatusToAttach = em.getReference(crmcampaignstatusCollectionNewCrmcampaignstatusToAttach.getClass(), crmcampaignstatusCollectionNewCrmcampaignstatusToAttach.getCrmcampaignstatusPK());
                attachedCrmcampaignstatusCollectionNew.add(crmcampaignstatusCollectionNewCrmcampaignstatusToAttach);
            }
            crmcampaignstatusCollectionNew = attachedCrmcampaignstatusCollectionNew;
            companyemployee.setCrmcampaignstatusCollection(crmcampaignstatusCollectionNew);
            Collection<Crmcampaignstatus> attachedCrmcampaignstatusCollection1New = new ArrayList<Crmcampaignstatus>();
            for (Crmcampaignstatus crmcampaignstatusCollection1NewCrmcampaignstatusToAttach : crmcampaignstatusCollection1New) {
                crmcampaignstatusCollection1NewCrmcampaignstatusToAttach = em.getReference(crmcampaignstatusCollection1NewCrmcampaignstatusToAttach.getClass(), crmcampaignstatusCollection1NewCrmcampaignstatusToAttach.getCrmcampaignstatusPK());
                attachedCrmcampaignstatusCollection1New.add(crmcampaignstatusCollection1NewCrmcampaignstatusToAttach);
            }
            crmcampaignstatusCollection1New = attachedCrmcampaignstatusCollection1New;
            companyemployee.setCrmcampaignstatusCollection1(crmcampaignstatusCollection1New);
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollectionNew = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollectionNewCrmcampaignpositionToAttach : crmcampaignpositionCollectionNew) {
                crmcampaignpositionCollectionNewCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollectionNewCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollectionNewCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollectionNew.add(crmcampaignpositionCollectionNewCrmcampaignpositionToAttach);
            }
            crmcampaignpositionCollectionNew = attachedCrmcampaignpositionCollectionNew;
            companyemployee.setCrmcampaignpositionCollection(crmcampaignpositionCollectionNew);
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollection1New = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollection1NewCrmcampaignpositionToAttach : crmcampaignpositionCollection1New) {
                crmcampaignpositionCollection1NewCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollection1NewCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollection1NewCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollection1New.add(crmcampaignpositionCollection1NewCrmcampaignpositionToAttach);
            }
            crmcampaignpositionCollection1New = attachedCrmcampaignpositionCollection1New;
            companyemployee.setCrmcampaignpositionCollection1(crmcampaignpositionCollection1New);
            Collection<Crmforumteammembers> attachedCrmforumteammembersCollectionNew = new ArrayList<Crmforumteammembers>();
            for (Crmforumteammembers crmforumteammembersCollectionNewCrmforumteammembersToAttach : crmforumteammembersCollectionNew) {
                crmforumteammembersCollectionNewCrmforumteammembersToAttach = em.getReference(crmforumteammembersCollectionNewCrmforumteammembersToAttach.getClass(), crmforumteammembersCollectionNewCrmforumteammembersToAttach.getCrmforumteammembersPK());
                attachedCrmforumteammembersCollectionNew.add(crmforumteammembersCollectionNewCrmforumteammembersToAttach);
            }
            crmforumteammembersCollectionNew = attachedCrmforumteammembersCollectionNew;
            companyemployee.setCrmforumteammembersCollection(crmforumteammembersCollectionNew);
            Collection<Customercontacts> attachedCustomercontactsCollectionNew = new ArrayList<Customercontacts>();
            for (Customercontacts customercontactsCollectionNewCustomercontactsToAttach : customercontactsCollectionNew) {
                customercontactsCollectionNewCustomercontactsToAttach = em.getReference(customercontactsCollectionNewCustomercontactsToAttach.getClass(), customercontactsCollectionNewCustomercontactsToAttach.getCustomercontactsPK());
                attachedCustomercontactsCollectionNew.add(customercontactsCollectionNewCustomercontactsToAttach);
            }
            customercontactsCollectionNew = attachedCustomercontactsCollectionNew;
            companyemployee.setCustomercontactsCollection(customercontactsCollectionNew);
            Collection<Customercontacts> attachedCustomercontactsCollection1New = new ArrayList<Customercontacts>();
            for (Customercontacts customercontactsCollection1NewCustomercontactsToAttach : customercontactsCollection1New) {
                customercontactsCollection1NewCustomercontactsToAttach = em.getReference(customercontactsCollection1NewCustomercontactsToAttach.getClass(), customercontactsCollection1NewCustomercontactsToAttach.getCustomercontactsPK());
                attachedCustomercontactsCollection1New.add(customercontactsCollection1NewCustomercontactsToAttach);
            }
            customercontactsCollection1New = attachedCustomercontactsCollection1New;
            companyemployee.setCustomercontactsCollection1(customercontactsCollection1New);
            Collection<Appointment> attachedAppointmentCollectionNew = new ArrayList<Appointment>();
            for (Appointment appointmentCollectionNewAppointmentToAttach : appointmentCollectionNew) {
                appointmentCollectionNewAppointmentToAttach = em.getReference(appointmentCollectionNewAppointmentToAttach.getClass(), appointmentCollectionNewAppointmentToAttach.getAppointmentPK());
                attachedAppointmentCollectionNew.add(appointmentCollectionNewAppointmentToAttach);
            }
            appointmentCollectionNew = attachedAppointmentCollectionNew;
            companyemployee.setAppointmentCollection(appointmentCollectionNew);
            Collection<Appointment> attachedAppointmentCollection1New = new ArrayList<Appointment>();
            for (Appointment appointmentCollection1NewAppointmentToAttach : appointmentCollection1New) {
                appointmentCollection1NewAppointmentToAttach = em.getReference(appointmentCollection1NewAppointmentToAttach.getClass(), appointmentCollection1NewAppointmentToAttach.getAppointmentPK());
                attachedAppointmentCollection1New.add(appointmentCollection1NewAppointmentToAttach);
            }
            appointmentCollection1New = attachedAppointmentCollection1New;
            companyemployee.setAppointmentCollection1(appointmentCollection1New);
            Collection<Appointment> attachedAppointmentCollection2New = new ArrayList<Appointment>();
            for (Appointment appointmentCollection2NewAppointmentToAttach : appointmentCollection2New) {
                appointmentCollection2NewAppointmentToAttach = em.getReference(appointmentCollection2NewAppointmentToAttach.getClass(), appointmentCollection2NewAppointmentToAttach.getAppointmentPK());
                attachedAppointmentCollection2New.add(appointmentCollection2NewAppointmentToAttach);
            }
            appointmentCollection2New = attachedAppointmentCollection2New;
            companyemployee.setAppointmentCollection2(appointmentCollection2New);
            Collection<Appointment> attachedAppointmentCollection3New = new ArrayList<Appointment>();
            for (Appointment appointmentCollection3NewAppointmentToAttach : appointmentCollection3New) {
                appointmentCollection3NewAppointmentToAttach = em.getReference(appointmentCollection3NewAppointmentToAttach.getClass(), appointmentCollection3NewAppointmentToAttach.getAppointmentPK());
                attachedAppointmentCollection3New.add(appointmentCollection3NewAppointmentToAttach);
            }
            appointmentCollection3New = attachedAppointmentCollection3New;
            companyemployee.setAppointmentCollection3(appointmentCollection3New);
            Collection<Companydepartment> attachedCompanydepartmentCollectionNew = new ArrayList<Companydepartment>();
            for (Companydepartment companydepartmentCollectionNewCompanydepartmentToAttach : companydepartmentCollectionNew) {
                companydepartmentCollectionNewCompanydepartmentToAttach = em.getReference(companydepartmentCollectionNewCompanydepartmentToAttach.getClass(), companydepartmentCollectionNewCompanydepartmentToAttach.getCompanydepartmentPK());
                attachedCompanydepartmentCollectionNew.add(companydepartmentCollectionNewCompanydepartmentToAttach);
            }
            companydepartmentCollectionNew = attachedCompanydepartmentCollectionNew;
            companyemployee.setCompanydepartmentCollection(companydepartmentCollectionNew);
            Collection<Companydepartment> attachedCompanydepartmentCollection1New = new ArrayList<Companydepartment>();
            for (Companydepartment companydepartmentCollection1NewCompanydepartmentToAttach : companydepartmentCollection1New) {
                companydepartmentCollection1NewCompanydepartmentToAttach = em.getReference(companydepartmentCollection1NewCompanydepartmentToAttach.getClass(), companydepartmentCollection1NewCompanydepartmentToAttach.getCompanydepartmentPK());
                attachedCompanydepartmentCollection1New.add(companydepartmentCollection1NewCompanydepartmentToAttach);
            }
            companydepartmentCollection1New = attachedCompanydepartmentCollection1New;
            companyemployee.setCompanydepartmentCollection1(companydepartmentCollection1New);
            Collection<Crmusermodules> attachedCrmusermodulesCollectionNew = new ArrayList<Crmusermodules>();
            for (Crmusermodules crmusermodulesCollectionNewCrmusermodulesToAttach : crmusermodulesCollectionNew) {
                crmusermodulesCollectionNewCrmusermodulesToAttach = em.getReference(crmusermodulesCollectionNewCrmusermodulesToAttach.getClass(), crmusermodulesCollectionNewCrmusermodulesToAttach.getCrmusermodulesPK());
                attachedCrmusermodulesCollectionNew.add(crmusermodulesCollectionNewCrmusermodulesToAttach);
            }
            crmusermodulesCollectionNew = attachedCrmusermodulesCollectionNew;
            companyemployee.setCrmusermodulesCollection(crmusermodulesCollectionNew);
            Collection<Crmusermodules> attachedCrmusermodulesCollection1New = new ArrayList<Crmusermodules>();
            for (Crmusermodules crmusermodulesCollection1NewCrmusermodulesToAttach : crmusermodulesCollection1New) {
                crmusermodulesCollection1NewCrmusermodulesToAttach = em.getReference(crmusermodulesCollection1NewCrmusermodulesToAttach.getClass(), crmusermodulesCollection1NewCrmusermodulesToAttach.getCrmusermodulesPK());
                attachedCrmusermodulesCollection1New.add(crmusermodulesCollection1NewCrmusermodulesToAttach);
            }
            crmusermodulesCollection1New = attachedCrmusermodulesCollection1New;
            companyemployee.setCrmusermodulesCollection1(crmusermodulesCollection1New);
            Collection<Crmexpense> attachedCrmexpenseCollectionNew = new ArrayList<Crmexpense>();
            for (Crmexpense crmexpenseCollectionNewCrmexpenseToAttach : crmexpenseCollectionNew) {
                crmexpenseCollectionNewCrmexpenseToAttach = em.getReference(crmexpenseCollectionNewCrmexpenseToAttach.getClass(), crmexpenseCollectionNewCrmexpenseToAttach.getCrmexpensePK());
                attachedCrmexpenseCollectionNew.add(crmexpenseCollectionNewCrmexpenseToAttach);
            }
            crmexpenseCollectionNew = attachedCrmexpenseCollectionNew;
            companyemployee.setCrmexpenseCollection(crmexpenseCollectionNew);
            Collection<Crmexpense> attachedCrmexpenseCollection1New = new ArrayList<Crmexpense>();
            for (Crmexpense crmexpenseCollection1NewCrmexpenseToAttach : crmexpenseCollection1New) {
                crmexpenseCollection1NewCrmexpenseToAttach = em.getReference(crmexpenseCollection1NewCrmexpenseToAttach.getClass(), crmexpenseCollection1NewCrmexpenseToAttach.getCrmexpensePK());
                attachedCrmexpenseCollection1New.add(crmexpenseCollection1NewCrmexpenseToAttach);
            }
            crmexpenseCollection1New = attachedCrmexpenseCollection1New;
            companyemployee.setCrmexpenseCollection1(crmexpenseCollection1New);
            Collection<Crmworkorder> attachedCrmworkorderCollectionNew = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionNewCrmworkorderToAttach : crmworkorderCollectionNew) {
                crmworkorderCollectionNewCrmworkorderToAttach = em.getReference(crmworkorderCollectionNewCrmworkorderToAttach.getClass(), crmworkorderCollectionNewCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollectionNew.add(crmworkorderCollectionNewCrmworkorderToAttach);
            }
            crmworkorderCollectionNew = attachedCrmworkorderCollectionNew;
            companyemployee.setCrmworkorderCollection(crmworkorderCollectionNew);
            Collection<Crmworkorder> attachedCrmworkorderCollection1New = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollection1NewCrmworkorderToAttach : crmworkorderCollection1New) {
                crmworkorderCollection1NewCrmworkorderToAttach = em.getReference(crmworkorderCollection1NewCrmworkorderToAttach.getClass(), crmworkorderCollection1NewCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollection1New.add(crmworkorderCollection1NewCrmworkorderToAttach);
            }
            crmworkorderCollection1New = attachedCrmworkorderCollection1New;
            companyemployee.setCrmworkorderCollection1(crmworkorderCollection1New);
            Collection<Crmcampaigndocs> attachedCrmcampaigndocsCollectionNew = new ArrayList<Crmcampaigndocs>();
            for (Crmcampaigndocs crmcampaigndocsCollectionNewCrmcampaigndocsToAttach : crmcampaigndocsCollectionNew) {
                crmcampaigndocsCollectionNewCrmcampaigndocsToAttach = em.getReference(crmcampaigndocsCollectionNewCrmcampaigndocsToAttach.getClass(), crmcampaigndocsCollectionNewCrmcampaigndocsToAttach.getCrmcampaigndocsPK());
                attachedCrmcampaigndocsCollectionNew.add(crmcampaigndocsCollectionNewCrmcampaigndocsToAttach);
            }
            crmcampaigndocsCollectionNew = attachedCrmcampaigndocsCollectionNew;
            companyemployee.setCrmcampaigndocsCollection(crmcampaigndocsCollectionNew);
            Collection<Crmcampaigndocs> attachedCrmcampaigndocsCollection1New = new ArrayList<Crmcampaigndocs>();
            for (Crmcampaigndocs crmcampaigndocsCollection1NewCrmcampaigndocsToAttach : crmcampaigndocsCollection1New) {
                crmcampaigndocsCollection1NewCrmcampaigndocsToAttach = em.getReference(crmcampaigndocsCollection1NewCrmcampaigndocsToAttach.getClass(), crmcampaigndocsCollection1NewCrmcampaigndocsToAttach.getCrmcampaigndocsPK());
                attachedCrmcampaigndocsCollection1New.add(crmcampaigndocsCollection1NewCrmcampaigndocsToAttach);
            }
            crmcampaigndocsCollection1New = attachedCrmcampaigndocsCollection1New;
            companyemployee.setCrmcampaigndocsCollection1(crmcampaigndocsCollection1New);
            Collection<Crmemployeenote> attachedCrmemployeenoteCollectionNew = new ArrayList<Crmemployeenote>();
            for (Crmemployeenote crmemployeenoteCollectionNewCrmemployeenoteToAttach : crmemployeenoteCollectionNew) {
                crmemployeenoteCollectionNewCrmemployeenoteToAttach = em.getReference(crmemployeenoteCollectionNewCrmemployeenoteToAttach.getClass(), crmemployeenoteCollectionNewCrmemployeenoteToAttach.getCrmemployeenotePK());
                attachedCrmemployeenoteCollectionNew.add(crmemployeenoteCollectionNewCrmemployeenoteToAttach);
            }
            crmemployeenoteCollectionNew = attachedCrmemployeenoteCollectionNew;
            companyemployee.setCrmemployeenoteCollection(crmemployeenoteCollectionNew);
            Collection<Companycontacts> attachedCompanycontactsCollectionNew = new ArrayList<Companycontacts>();
            for (Companycontacts companycontactsCollectionNewCompanycontactsToAttach : companycontactsCollectionNew) {
                companycontactsCollectionNewCompanycontactsToAttach = em.getReference(companycontactsCollectionNewCompanycontactsToAttach.getClass(), companycontactsCollectionNewCompanycontactsToAttach.getCompanycontactsPK());
                attachedCompanycontactsCollectionNew.add(companycontactsCollectionNewCompanycontactsToAttach);
            }
            companycontactsCollectionNew = attachedCompanycontactsCollectionNew;
            companyemployee.setCompanycontactsCollection(companycontactsCollectionNew);
            Collection<Companycontacts> attachedCompanycontactsCollection1New = new ArrayList<Companycontacts>();
            for (Companycontacts companycontactsCollection1NewCompanycontactsToAttach : companycontactsCollection1New) {
                companycontactsCollection1NewCompanycontactsToAttach = em.getReference(companycontactsCollection1NewCompanycontactsToAttach.getClass(), companycontactsCollection1NewCompanycontactsToAttach.getCompanycontactsPK());
                attachedCompanycontactsCollection1New.add(companycontactsCollection1NewCompanycontactsToAttach);
            }
            companycontactsCollection1New = attachedCompanycontactsCollection1New;
            companyemployee.setCompanycontactsCollection1(companycontactsCollection1New);
            Collection<Companyemployee> attachedCompanyemployeeCollectionNew = new ArrayList<Companyemployee>();
            for (Companyemployee companyemployeeCollectionNewCompanyemployeeToAttach : companyemployeeCollectionNew) {
                companyemployeeCollectionNewCompanyemployeeToAttach = em.getReference(companyemployeeCollectionNewCompanyemployeeToAttach.getClass(), companyemployeeCollectionNewCompanyemployeeToAttach.getCompanyemployeePK());
                attachedCompanyemployeeCollectionNew.add(companyemployeeCollectionNewCompanyemployeeToAttach);
            }
            companyemployeeCollectionNew = attachedCompanyemployeeCollectionNew;
            companyemployee.setCompanyemployeeCollection(companyemployeeCollectionNew);
            Collection<Companyemployee> attachedCompanyemployeeCollection1New = new ArrayList<Companyemployee>();
            for (Companyemployee companyemployeeCollection1NewCompanyemployeeToAttach : companyemployeeCollection1New) {
                companyemployeeCollection1NewCompanyemployeeToAttach = em.getReference(companyemployeeCollection1NewCompanyemployeeToAttach.getClass(), companyemployeeCollection1NewCompanyemployeeToAttach.getCompanyemployeePK());
                attachedCompanyemployeeCollection1New.add(companyemployeeCollection1NewCompanyemployeeToAttach);
            }
            companyemployeeCollection1New = attachedCompanyemployeeCollection1New;
            companyemployee.setCompanyemployeeCollection1(companyemployeeCollection1New);
            Collection<Companyproducttype> attachedCompanyproducttypeCollectionNew = new ArrayList<Companyproducttype>();
            for (Companyproducttype companyproducttypeCollectionNewCompanyproducttypeToAttach : companyproducttypeCollectionNew) {
                companyproducttypeCollectionNewCompanyproducttypeToAttach = em.getReference(companyproducttypeCollectionNewCompanyproducttypeToAttach.getClass(), companyproducttypeCollectionNewCompanyproducttypeToAttach.getCompanyproducttypePK());
                attachedCompanyproducttypeCollectionNew.add(companyproducttypeCollectionNewCompanyproducttypeToAttach);
            }
            companyproducttypeCollectionNew = attachedCompanyproducttypeCollectionNew;
            companyemployee.setCompanyproducttypeCollection(companyproducttypeCollectionNew);
            Collection<Companyproducttype> attachedCompanyproducttypeCollection1New = new ArrayList<Companyproducttype>();
            for (Companyproducttype companyproducttypeCollection1NewCompanyproducttypeToAttach : companyproducttypeCollection1New) {
                companyproducttypeCollection1NewCompanyproducttypeToAttach = em.getReference(companyproducttypeCollection1NewCompanyproducttypeToAttach.getClass(), companyproducttypeCollection1NewCompanyproducttypeToAttach.getCompanyproducttypePK());
                attachedCompanyproducttypeCollection1New.add(companyproducttypeCollection1NewCompanyproducttypeToAttach);
            }
            companyproducttypeCollection1New = attachedCompanyproducttypeCollection1New;
            companyemployee.setCompanyproducttypeCollection1(companyproducttypeCollection1New);
            Collection<Crmcampaign> attachedCrmcampaignCollectionNew = new ArrayList<Crmcampaign>();
            for (Crmcampaign crmcampaignCollectionNewCrmcampaignToAttach : crmcampaignCollectionNew) {
                crmcampaignCollectionNewCrmcampaignToAttach = em.getReference(crmcampaignCollectionNewCrmcampaignToAttach.getClass(), crmcampaignCollectionNewCrmcampaignToAttach.getCrmcampaignPK());
                attachedCrmcampaignCollectionNew.add(crmcampaignCollectionNewCrmcampaignToAttach);
            }
            crmcampaignCollectionNew = attachedCrmcampaignCollectionNew;
            companyemployee.setCrmcampaignCollection(crmcampaignCollectionNew);
            Collection<Crmcampaign> attachedCrmcampaignCollection1New = new ArrayList<Crmcampaign>();
            for (Crmcampaign crmcampaignCollection1NewCrmcampaignToAttach : crmcampaignCollection1New) {
                crmcampaignCollection1NewCrmcampaignToAttach = em.getReference(crmcampaignCollection1NewCrmcampaignToAttach.getClass(), crmcampaignCollection1NewCrmcampaignToAttach.getCrmcampaignPK());
                attachedCrmcampaignCollection1New.add(crmcampaignCollection1NewCrmcampaignToAttach);
            }
            crmcampaignCollection1New = attachedCrmcampaignCollection1New;
            companyemployee.setCrmcampaignCollection1(crmcampaignCollection1New);
            Collection<Crmmessagehistory> attachedCrmmessagehistoryCollectionNew = new ArrayList<Crmmessagehistory>();
            for (Crmmessagehistory crmmessagehistoryCollectionNewCrmmessagehistoryToAttach : crmmessagehistoryCollectionNew) {
                crmmessagehistoryCollectionNewCrmmessagehistoryToAttach = em.getReference(crmmessagehistoryCollectionNewCrmmessagehistoryToAttach.getClass(), crmmessagehistoryCollectionNewCrmmessagehistoryToAttach.getCrmmessagehistoryPK());
                attachedCrmmessagehistoryCollectionNew.add(crmmessagehistoryCollectionNewCrmmessagehistoryToAttach);
            }
            crmmessagehistoryCollectionNew = attachedCrmmessagehistoryCollectionNew;
            companyemployee.setCrmmessagehistoryCollection(crmmessagehistoryCollectionNew);
            Collection<Crmmessagehistory> attachedCrmmessagehistoryCollection1New = new ArrayList<Crmmessagehistory>();
            for (Crmmessagehistory crmmessagehistoryCollection1NewCrmmessagehistoryToAttach : crmmessagehistoryCollection1New) {
                crmmessagehistoryCollection1NewCrmmessagehistoryToAttach = em.getReference(crmmessagehistoryCollection1NewCrmmessagehistoryToAttach.getClass(), crmmessagehistoryCollection1NewCrmmessagehistoryToAttach.getCrmmessagehistoryPK());
                attachedCrmmessagehistoryCollection1New.add(crmmessagehistoryCollection1NewCrmmessagehistoryToAttach);
            }
            crmmessagehistoryCollection1New = attachedCrmmessagehistoryCollection1New;
            companyemployee.setCrmmessagehistoryCollection1(crmmessagehistoryCollection1New);
            Collection<Companyaccountstackdocs> attachedCompanyaccountstackdocsCollectionNew = new ArrayList<Companyaccountstackdocs>();
            for (Companyaccountstackdocs companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach : companyaccountstackdocsCollectionNew) {
                companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach = em.getReference(companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach.getClass(), companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach.getCompanyaccountstackdocsPK());
                attachedCompanyaccountstackdocsCollectionNew.add(companyaccountstackdocsCollectionNewCompanyaccountstackdocsToAttach);
            }
            companyaccountstackdocsCollectionNew = attachedCompanyaccountstackdocsCollectionNew;
            companyemployee.setCompanyaccountstackdocsCollection(companyaccountstackdocsCollectionNew);
            Collection<Companyaccountstackdocs> attachedCompanyaccountstackdocsCollection1New = new ArrayList<Companyaccountstackdocs>();
            for (Companyaccountstackdocs companyaccountstackdocsCollection1NewCompanyaccountstackdocsToAttach : companyaccountstackdocsCollection1New) {
                companyaccountstackdocsCollection1NewCompanyaccountstackdocsToAttach = em.getReference(companyaccountstackdocsCollection1NewCompanyaccountstackdocsToAttach.getClass(), companyaccountstackdocsCollection1NewCompanyaccountstackdocsToAttach.getCompanyaccountstackdocsPK());
                attachedCompanyaccountstackdocsCollection1New.add(companyaccountstackdocsCollection1NewCompanyaccountstackdocsToAttach);
            }
            companyaccountstackdocsCollection1New = attachedCompanyaccountstackdocsCollection1New;
            companyemployee.setCompanyaccountstackdocsCollection1(companyaccountstackdocsCollection1New);
            Collection<Crmlabor> attachedCrmlaborCollectionNew = new ArrayList<Crmlabor>();
            for (Crmlabor crmlaborCollectionNewCrmlaborToAttach : crmlaborCollectionNew) {
                crmlaborCollectionNewCrmlaborToAttach = em.getReference(crmlaborCollectionNewCrmlaborToAttach.getClass(), crmlaborCollectionNewCrmlaborToAttach.getCrmlaborPK());
                attachedCrmlaborCollectionNew.add(crmlaborCollectionNewCrmlaborToAttach);
            }
            crmlaborCollectionNew = attachedCrmlaborCollectionNew;
            companyemployee.setCrmlaborCollection(crmlaborCollectionNew);
            Collection<Crmlabor> attachedCrmlaborCollection1New = new ArrayList<Crmlabor>();
            for (Crmlabor crmlaborCollection1NewCrmlaborToAttach : crmlaborCollection1New) {
                crmlaborCollection1NewCrmlaborToAttach = em.getReference(crmlaborCollection1NewCrmlaborToAttach.getClass(), crmlaborCollection1NewCrmlaborToAttach.getCrmlaborPK());
                attachedCrmlaborCollection1New.add(crmlaborCollection1NewCrmlaborToAttach);
            }
            crmlaborCollection1New = attachedCrmlaborCollection1New;
            companyemployee.setCrmlaborCollection1(crmlaborCollection1New);
            Collection<Customerbank> attachedCustomerbankCollectionNew = new ArrayList<Customerbank>();
            for (Customerbank customerbankCollectionNewCustomerbankToAttach : customerbankCollectionNew) {
                customerbankCollectionNewCustomerbankToAttach = em.getReference(customerbankCollectionNewCustomerbankToAttach.getClass(), customerbankCollectionNewCustomerbankToAttach.getCustomerbankPK());
                attachedCustomerbankCollectionNew.add(customerbankCollectionNewCustomerbankToAttach);
            }
            customerbankCollectionNew = attachedCustomerbankCollectionNew;
            companyemployee.setCustomerbankCollection(customerbankCollectionNew);
            Collection<Customerbank> attachedCustomerbankCollection1New = new ArrayList<Customerbank>();
            for (Customerbank customerbankCollection1NewCustomerbankToAttach : customerbankCollection1New) {
                customerbankCollection1NewCustomerbankToAttach = em.getReference(customerbankCollection1NewCustomerbankToAttach.getClass(), customerbankCollection1NewCustomerbankToAttach.getCustomerbankPK());
                attachedCustomerbankCollection1New.add(customerbankCollection1NewCustomerbankToAttach);
            }
            customerbankCollection1New = attachedCustomerbankCollection1New;
            companyemployee.setCustomerbankCollection1(customerbankCollection1New);
            Collection<Compnaypaymentcurrency> attachedCompnaypaymentcurrencyCollectionNew = new ArrayList<Compnaypaymentcurrency>();
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionNewCompnaypaymentcurrencyToAttach : compnaypaymentcurrencyCollectionNew) {
                compnaypaymentcurrencyCollectionNewCompnaypaymentcurrencyToAttach = em.getReference(compnaypaymentcurrencyCollectionNewCompnaypaymentcurrencyToAttach.getClass(), compnaypaymentcurrencyCollectionNewCompnaypaymentcurrencyToAttach.getCompnaypaymentcurrencyPK());
                attachedCompnaypaymentcurrencyCollectionNew.add(compnaypaymentcurrencyCollectionNewCompnaypaymentcurrencyToAttach);
            }
            compnaypaymentcurrencyCollectionNew = attachedCompnaypaymentcurrencyCollectionNew;
            companyemployee.setCompnaypaymentcurrencyCollection(compnaypaymentcurrencyCollectionNew);
            Collection<Compnaypaymentcurrency> attachedCompnaypaymentcurrencyCollection1New = new ArrayList<Compnaypaymentcurrency>();
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollection1NewCompnaypaymentcurrencyToAttach : compnaypaymentcurrencyCollection1New) {
                compnaypaymentcurrencyCollection1NewCompnaypaymentcurrencyToAttach = em.getReference(compnaypaymentcurrencyCollection1NewCompnaypaymentcurrencyToAttach.getClass(), compnaypaymentcurrencyCollection1NewCompnaypaymentcurrencyToAttach.getCompnaypaymentcurrencyPK());
                attachedCompnaypaymentcurrencyCollection1New.add(compnaypaymentcurrencyCollection1NewCompnaypaymentcurrencyToAttach);
            }
            compnaypaymentcurrencyCollection1New = attachedCompnaypaymentcurrencyCollection1New;
            companyemployee.setCompnaypaymentcurrencyCollection1(compnaypaymentcurrencyCollection1New);
            Collection<Crmproject> attachedCrmprojectCollectionNew = new ArrayList<Crmproject>();
            for (Crmproject crmprojectCollectionNewCrmprojectToAttach : crmprojectCollectionNew) {
                crmprojectCollectionNewCrmprojectToAttach = em.getReference(crmprojectCollectionNewCrmprojectToAttach.getClass(), crmprojectCollectionNewCrmprojectToAttach.getCrmprojectPK());
                attachedCrmprojectCollectionNew.add(crmprojectCollectionNewCrmprojectToAttach);
            }
            crmprojectCollectionNew = attachedCrmprojectCollectionNew;
            companyemployee.setCrmprojectCollection(crmprojectCollectionNew);
            Collection<Crmproject> attachedCrmprojectCollection1New = new ArrayList<Crmproject>();
            for (Crmproject crmprojectCollection1NewCrmprojectToAttach : crmprojectCollection1New) {
                crmprojectCollection1NewCrmprojectToAttach = em.getReference(crmprojectCollection1NewCrmprojectToAttach.getClass(), crmprojectCollection1NewCrmprojectToAttach.getCrmprojectPK());
                attachedCrmprojectCollection1New.add(crmprojectCollection1NewCrmprojectToAttach);
            }
            crmprojectCollection1New = attachedCrmprojectCollection1New;
            companyemployee.setCrmprojectCollection1(crmprojectCollection1New);
            Collection<Crmproject> attachedCrmprojectCollection2New = new ArrayList<Crmproject>();
            for (Crmproject crmprojectCollection2NewCrmprojectToAttach : crmprojectCollection2New) {
                crmprojectCollection2NewCrmprojectToAttach = em.getReference(crmprojectCollection2NewCrmprojectToAttach.getClass(), crmprojectCollection2NewCrmprojectToAttach.getCrmprojectPK());
                attachedCrmprojectCollection2New.add(crmprojectCollection2NewCrmprojectToAttach);
            }
            crmprojectCollection2New = attachedCrmprojectCollection2New;
            companyemployee.setCrmprojectCollection2(crmprojectCollection2New);
            Collection<Companyemployeeaddress> attachedCompanyemployeeaddressCollectionNew = new ArrayList<Companyemployeeaddress>();
            for (Companyemployeeaddress companyemployeeaddressCollectionNewCompanyemployeeaddressToAttach : companyemployeeaddressCollectionNew) {
                companyemployeeaddressCollectionNewCompanyemployeeaddressToAttach = em.getReference(companyemployeeaddressCollectionNewCompanyemployeeaddressToAttach.getClass(), companyemployeeaddressCollectionNewCompanyemployeeaddressToAttach.getCompanyemployeeaddressPK());
                attachedCompanyemployeeaddressCollectionNew.add(companyemployeeaddressCollectionNewCompanyemployeeaddressToAttach);
            }
            companyemployeeaddressCollectionNew = attachedCompanyemployeeaddressCollectionNew;
            companyemployee.setCompanyemployeeaddressCollection(companyemployeeaddressCollectionNew);
            Collection<Companyemployeeaddress> attachedCompanyemployeeaddressCollection1New = new ArrayList<Companyemployeeaddress>();
            for (Companyemployeeaddress companyemployeeaddressCollection1NewCompanyemployeeaddressToAttach : companyemployeeaddressCollection1New) {
                companyemployeeaddressCollection1NewCompanyemployeeaddressToAttach = em.getReference(companyemployeeaddressCollection1NewCompanyemployeeaddressToAttach.getClass(), companyemployeeaddressCollection1NewCompanyemployeeaddressToAttach.getCompanyemployeeaddressPK());
                attachedCompanyemployeeaddressCollection1New.add(companyemployeeaddressCollection1NewCompanyemployeeaddressToAttach);
            }
            companyemployeeaddressCollection1New = attachedCompanyemployeeaddressCollection1New;
            companyemployee.setCompanyemployeeaddressCollection1(companyemployeeaddressCollection1New);
            Collection<Companyemployeeaddress> attachedCompanyemployeeaddressCollection2New = new ArrayList<Companyemployeeaddress>();
            for (Companyemployeeaddress companyemployeeaddressCollection2NewCompanyemployeeaddressToAttach : companyemployeeaddressCollection2New) {
                companyemployeeaddressCollection2NewCompanyemployeeaddressToAttach = em.getReference(companyemployeeaddressCollection2NewCompanyemployeeaddressToAttach.getClass(), companyemployeeaddressCollection2NewCompanyemployeeaddressToAttach.getCompanyemployeeaddressPK());
                attachedCompanyemployeeaddressCollection2New.add(companyemployeeaddressCollection2NewCompanyemployeeaddressToAttach);
            }
            companyemployeeaddressCollection2New = attachedCompanyemployeeaddressCollection2New;
            companyemployee.setCompanyemployeeaddressCollection2(companyemployeeaddressCollection2New);
            Collection<Crmforumdocs> attachedCrmforumdocsCollectionNew = new ArrayList<Crmforumdocs>();
            for (Crmforumdocs crmforumdocsCollectionNewCrmforumdocsToAttach : crmforumdocsCollectionNew) {
                crmforumdocsCollectionNewCrmforumdocsToAttach = em.getReference(crmforumdocsCollectionNewCrmforumdocsToAttach.getClass(), crmforumdocsCollectionNewCrmforumdocsToAttach.getCrmforumdocsPK());
                attachedCrmforumdocsCollectionNew.add(crmforumdocsCollectionNewCrmforumdocsToAttach);
            }
            crmforumdocsCollectionNew = attachedCrmforumdocsCollectionNew;
            companyemployee.setCrmforumdocsCollection(crmforumdocsCollectionNew);
            Collection<Companyproduct> attachedCompanyproductCollectionNew = new ArrayList<Companyproduct>();
            for (Companyproduct companyproductCollectionNewCompanyproductToAttach : companyproductCollectionNew) {
                companyproductCollectionNewCompanyproductToAttach = em.getReference(companyproductCollectionNewCompanyproductToAttach.getClass(), companyproductCollectionNewCompanyproductToAttach.getCompanyproductPK());
                attachedCompanyproductCollectionNew.add(companyproductCollectionNewCompanyproductToAttach);
            }
            companyproductCollectionNew = attachedCompanyproductCollectionNew;
            companyemployee.setCompanyproductCollection(companyproductCollectionNew);
            Collection<Companyproduct> attachedCompanyproductCollection1New = new ArrayList<Companyproduct>();
            for (Companyproduct companyproductCollection1NewCompanyproductToAttach : companyproductCollection1New) {
                companyproductCollection1NewCompanyproductToAttach = em.getReference(companyproductCollection1NewCompanyproductToAttach.getClass(), companyproductCollection1NewCompanyproductToAttach.getCompanyproductPK());
                attachedCompanyproductCollection1New.add(companyproductCollection1NewCompanyproductToAttach);
            }
            companyproductCollection1New = attachedCompanyproductCollection1New;
            companyemployee.setCompanyproductCollection1(companyproductCollection1New);
            Collection<Employeedesignation> attachedEmployeedesignationCollectionNew = new ArrayList<Employeedesignation>();
            for (Employeedesignation employeedesignationCollectionNewEmployeedesignationToAttach : employeedesignationCollectionNew) {
                employeedesignationCollectionNewEmployeedesignationToAttach = em.getReference(employeedesignationCollectionNewEmployeedesignationToAttach.getClass(), employeedesignationCollectionNewEmployeedesignationToAttach.getEmployeedesignationPK());
                attachedEmployeedesignationCollectionNew.add(employeedesignationCollectionNewEmployeedesignationToAttach);
            }
            employeedesignationCollectionNew = attachedEmployeedesignationCollectionNew;
            companyemployee.setEmployeedesignationCollection(employeedesignationCollectionNew);
            Collection<Employeedesignation> attachedEmployeedesignationCollection1New = new ArrayList<Employeedesignation>();
            for (Employeedesignation employeedesignationCollection1NewEmployeedesignationToAttach : employeedesignationCollection1New) {
                employeedesignationCollection1NewEmployeedesignationToAttach = em.getReference(employeedesignationCollection1NewEmployeedesignationToAttach.getClass(), employeedesignationCollection1NewEmployeedesignationToAttach.getEmployeedesignationPK());
                attachedEmployeedesignationCollection1New.add(employeedesignationCollection1NewEmployeedesignationToAttach);
            }
            employeedesignationCollection1New = attachedEmployeedesignationCollection1New;
            companyemployee.setEmployeedesignationCollection1(employeedesignationCollection1New);
            Collection<Companyaccountstack> attachedCompanyaccountstackCollectionNew = new ArrayList<Companyaccountstack>();
            for (Companyaccountstack companyaccountstackCollectionNewCompanyaccountstackToAttach : companyaccountstackCollectionNew) {
                companyaccountstackCollectionNewCompanyaccountstackToAttach = em.getReference(companyaccountstackCollectionNewCompanyaccountstackToAttach.getClass(), companyaccountstackCollectionNewCompanyaccountstackToAttach.getCompanyaccountstackPK());
                attachedCompanyaccountstackCollectionNew.add(companyaccountstackCollectionNewCompanyaccountstackToAttach);
            }
            companyaccountstackCollectionNew = attachedCompanyaccountstackCollectionNew;
            companyemployee.setCompanyaccountstackCollection(companyaccountstackCollectionNew);
            Collection<Companyaccountstack> attachedCompanyaccountstackCollection1New = new ArrayList<Companyaccountstack>();
            for (Companyaccountstack companyaccountstackCollection1NewCompanyaccountstackToAttach : companyaccountstackCollection1New) {
                companyaccountstackCollection1NewCompanyaccountstackToAttach = em.getReference(companyaccountstackCollection1NewCompanyaccountstackToAttach.getClass(), companyaccountstackCollection1NewCompanyaccountstackToAttach.getCompanyaccountstackPK());
                attachedCompanyaccountstackCollection1New.add(companyaccountstackCollection1NewCompanyaccountstackToAttach);
            }
            companyaccountstackCollection1New = attachedCompanyaccountstackCollection1New;
            companyemployee.setCompanyaccountstackCollection1(companyaccountstackCollection1New);
            Collection<Crmprojectteammembers> attachedCrmprojectteammembersCollectionNew = new ArrayList<Crmprojectteammembers>();
            for (Crmprojectteammembers crmprojectteammembersCollectionNewCrmprojectteammembersToAttach : crmprojectteammembersCollectionNew) {
                crmprojectteammembersCollectionNewCrmprojectteammembersToAttach = em.getReference(crmprojectteammembersCollectionNewCrmprojectteammembersToAttach.getClass(), crmprojectteammembersCollectionNewCrmprojectteammembersToAttach.getCrmprojectteammembersPK());
                attachedCrmprojectteammembersCollectionNew.add(crmprojectteammembersCollectionNewCrmprojectteammembersToAttach);
            }
            crmprojectteammembersCollectionNew = attachedCrmprojectteammembersCollectionNew;
            companyemployee.setCrmprojectteammembersCollection(crmprojectteammembersCollectionNew);
            Collection<Crmprojectteammembers> attachedCrmprojectteammembersCollection1New = new ArrayList<Crmprojectteammembers>();
            for (Crmprojectteammembers crmprojectteammembersCollection1NewCrmprojectteammembersToAttach : crmprojectteammembersCollection1New) {
                crmprojectteammembersCollection1NewCrmprojectteammembersToAttach = em.getReference(crmprojectteammembersCollection1NewCrmprojectteammembersToAttach.getClass(), crmprojectteammembersCollection1NewCrmprojectteammembersToAttach.getCrmprojectteammembersPK());
                attachedCrmprojectteammembersCollection1New.add(crmprojectteammembersCollection1NewCrmprojectteammembersToAttach);
            }
            crmprojectteammembersCollection1New = attachedCrmprojectteammembersCollection1New;
            companyemployee.setCrmprojectteammembersCollection1(crmprojectteammembersCollection1New);
            Collection<Crmprojectteammembers> attachedCrmprojectteammembersCollection2New = new ArrayList<Crmprojectteammembers>();
            for (Crmprojectteammembers crmprojectteammembersCollection2NewCrmprojectteammembersToAttach : crmprojectteammembersCollection2New) {
                crmprojectteammembersCollection2NewCrmprojectteammembersToAttach = em.getReference(crmprojectteammembersCollection2NewCrmprojectteammembersToAttach.getClass(), crmprojectteammembersCollection2NewCrmprojectteammembersToAttach.getCrmprojectteammembersPK());
                attachedCrmprojectteammembersCollection2New.add(crmprojectteammembersCollection2NewCrmprojectteammembersToAttach);
            }
            crmprojectteammembersCollection2New = attachedCrmprojectteammembersCollection2New;
            companyemployee.setCrmprojectteammembersCollection2(crmprojectteammembersCollection2New);
            Collection<Componentattachments> attachedComponentattachmentsCollectionNew = new ArrayList<Componentattachments>();
            for (Componentattachments componentattachmentsCollectionNewComponentattachmentsToAttach : componentattachmentsCollectionNew) {
                componentattachmentsCollectionNewComponentattachmentsToAttach = em.getReference(componentattachmentsCollectionNewComponentattachmentsToAttach.getClass(), componentattachmentsCollectionNewComponentattachmentsToAttach.getComponentattachmentsPK());
                attachedComponentattachmentsCollectionNew.add(componentattachmentsCollectionNewComponentattachmentsToAttach);
            }
            componentattachmentsCollectionNew = attachedComponentattachmentsCollectionNew;
            companyemployee.setComponentattachmentsCollection(componentattachmentsCollectionNew);
            Collection<Componentattachments> attachedComponentattachmentsCollection1New = new ArrayList<Componentattachments>();
            for (Componentattachments componentattachmentsCollection1NewComponentattachmentsToAttach : componentattachmentsCollection1New) {
                componentattachmentsCollection1NewComponentattachmentsToAttach = em.getReference(componentattachmentsCollection1NewComponentattachmentsToAttach.getClass(), componentattachmentsCollection1NewComponentattachmentsToAttach.getComponentattachmentsPK());
                attachedComponentattachmentsCollection1New.add(componentattachmentsCollection1NewComponentattachmentsToAttach);
            }
            componentattachmentsCollection1New = attachedComponentattachmentsCollection1New;
            companyemployee.setComponentattachmentsCollection1(componentattachmentsCollection1New);
            Collection<Crmvisitorcontactsaddress> attachedCrmvisitorcontactsaddressCollectionNew = new ArrayList<Crmvisitorcontactsaddress>();
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach : crmvisitorcontactsaddressCollectionNew) {
                crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach = em.getReference(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach.getClass(), crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach.getCrmvisitorcontactsaddressPK());
                attachedCrmvisitorcontactsaddressCollectionNew.add(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddressToAttach);
            }
            crmvisitorcontactsaddressCollectionNew = attachedCrmvisitorcontactsaddressCollectionNew;
            companyemployee.setCrmvisitorcontactsaddressCollection(crmvisitorcontactsaddressCollectionNew);
            Collection<Crmvisitorcontacts> attachedCrmvisitorcontactsCollectionNew = new ArrayList<Crmvisitorcontacts>();
            for (Crmvisitorcontacts crmvisitorcontactsCollectionNewCrmvisitorcontactsToAttach : crmvisitorcontactsCollectionNew) {
                crmvisitorcontactsCollectionNewCrmvisitorcontactsToAttach = em.getReference(crmvisitorcontactsCollectionNewCrmvisitorcontactsToAttach.getClass(), crmvisitorcontactsCollectionNewCrmvisitorcontactsToAttach.getCrmvisitorcontactsPK());
                attachedCrmvisitorcontactsCollectionNew.add(crmvisitorcontactsCollectionNewCrmvisitorcontactsToAttach);
            }
            crmvisitorcontactsCollectionNew = attachedCrmvisitorcontactsCollectionNew;
            companyemployee.setCrmvisitorcontactsCollection(crmvisitorcontactsCollectionNew);
            Collection<Crmvisitorcontacts> attachedCrmvisitorcontactsCollection1New = new ArrayList<Crmvisitorcontacts>();
            for (Crmvisitorcontacts crmvisitorcontactsCollection1NewCrmvisitorcontactsToAttach : crmvisitorcontactsCollection1New) {
                crmvisitorcontactsCollection1NewCrmvisitorcontactsToAttach = em.getReference(crmvisitorcontactsCollection1NewCrmvisitorcontactsToAttach.getClass(), crmvisitorcontactsCollection1NewCrmvisitorcontactsToAttach.getCrmvisitorcontactsPK());
                attachedCrmvisitorcontactsCollection1New.add(crmvisitorcontactsCollection1NewCrmvisitorcontactsToAttach);
            }
            crmvisitorcontactsCollection1New = attachedCrmvisitorcontactsCollection1New;
            companyemployee.setCrmvisitorcontactsCollection1(crmvisitorcontactsCollection1New);
            Collection<Companyaccountstackcd> attachedCompanyaccountstackcdCollectionNew = new ArrayList<Companyaccountstackcd>();
            for (Companyaccountstackcd companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach : companyaccountstackcdCollectionNew) {
                companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach = em.getReference(companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach.getClass(), companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach.getCompanyaccountstackcdPK());
                attachedCompanyaccountstackcdCollectionNew.add(companyaccountstackcdCollectionNewCompanyaccountstackcdToAttach);
            }
            companyaccountstackcdCollectionNew = attachedCompanyaccountstackcdCollectionNew;
            companyemployee.setCompanyaccountstackcdCollection(companyaccountstackcdCollectionNew);
            Collection<Companyaccountstackcd> attachedCompanyaccountstackcdCollection1New = new ArrayList<Companyaccountstackcd>();
            for (Companyaccountstackcd companyaccountstackcdCollection1NewCompanyaccountstackcdToAttach : companyaccountstackcdCollection1New) {
                companyaccountstackcdCollection1NewCompanyaccountstackcdToAttach = em.getReference(companyaccountstackcdCollection1NewCompanyaccountstackcdToAttach.getClass(), companyaccountstackcdCollection1NewCompanyaccountstackcdToAttach.getCompanyaccountstackcdPK());
                attachedCompanyaccountstackcdCollection1New.add(companyaccountstackcdCollection1NewCompanyaccountstackcdToAttach);
            }
            companyaccountstackcdCollection1New = attachedCompanyaccountstackcdCollection1New;
            companyemployee.setCompanyaccountstackcdCollection1(companyaccountstackcdCollection1New);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollectionNew = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionNewCrmprojectticketmanagementToAttach : crmprojectticketmanagementCollectionNew) {
                crmprojectticketmanagementCollectionNewCrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollectionNewCrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollectionNewCrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollectionNew.add(crmprojectticketmanagementCollectionNewCrmprojectticketmanagementToAttach);
            }
            crmprojectticketmanagementCollectionNew = attachedCrmprojectticketmanagementCollectionNew;
            companyemployee.setCrmprojectticketmanagementCollection(crmprojectticketmanagementCollectionNew);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollection1New = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1NewCrmprojectticketmanagementToAttach : crmprojectticketmanagementCollection1New) {
                crmprojectticketmanagementCollection1NewCrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollection1NewCrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollection1NewCrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollection1New.add(crmprojectticketmanagementCollection1NewCrmprojectticketmanagementToAttach);
            }
            crmprojectticketmanagementCollection1New = attachedCrmprojectticketmanagementCollection1New;
            companyemployee.setCrmprojectticketmanagementCollection1(crmprojectticketmanagementCollection1New);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollection2New = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection2NewCrmprojectticketmanagementToAttach : crmprojectticketmanagementCollection2New) {
                crmprojectticketmanagementCollection2NewCrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollection2NewCrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollection2NewCrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollection2New.add(crmprojectticketmanagementCollection2NewCrmprojectticketmanagementToAttach);
            }
            crmprojectticketmanagementCollection2New = attachedCrmprojectticketmanagementCollection2New;
            companyemployee.setCrmprojectticketmanagementCollection2(crmprojectticketmanagementCollection2New);
            Collection<Crmprojectticketmanagement> attachedCrmprojectticketmanagementCollection3New = new ArrayList<Crmprojectticketmanagement>();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection3NewCrmprojectticketmanagementToAttach : crmprojectticketmanagementCollection3New) {
                crmprojectticketmanagementCollection3NewCrmprojectticketmanagementToAttach = em.getReference(crmprojectticketmanagementCollection3NewCrmprojectticketmanagementToAttach.getClass(), crmprojectticketmanagementCollection3NewCrmprojectticketmanagementToAttach.getCrmprojectticketmanagementPK());
                attachedCrmprojectticketmanagementCollection3New.add(crmprojectticketmanagementCollection3NewCrmprojectticketmanagementToAttach);
            }
            crmprojectticketmanagementCollection3New = attachedCrmprojectticketmanagementCollection3New;
            companyemployee.setCrmprojectticketmanagementCollection3(crmprojectticketmanagementCollection3New);
            Collection<Crmprojectticketnotification> attachedCrmprojectticketnotificationCollectionNew = new ArrayList<Crmprojectticketnotification>();
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionNewCrmprojectticketnotificationToAttach : crmprojectticketnotificationCollectionNew) {
                crmprojectticketnotificationCollectionNewCrmprojectticketnotificationToAttach = em.getReference(crmprojectticketnotificationCollectionNewCrmprojectticketnotificationToAttach.getClass(), crmprojectticketnotificationCollectionNewCrmprojectticketnotificationToAttach.getCrmprojectticketnotificationPK());
                attachedCrmprojectticketnotificationCollectionNew.add(crmprojectticketnotificationCollectionNewCrmprojectticketnotificationToAttach);
            }
            crmprojectticketnotificationCollectionNew = attachedCrmprojectticketnotificationCollectionNew;
            companyemployee.setCrmprojectticketnotificationCollection(crmprojectticketnotificationCollectionNew);
            Collection<Crmprojectticketnotification> attachedCrmprojectticketnotificationCollection1New = new ArrayList<Crmprojectticketnotification>();
            for (Crmprojectticketnotification crmprojectticketnotificationCollection1NewCrmprojectticketnotificationToAttach : crmprojectticketnotificationCollection1New) {
                crmprojectticketnotificationCollection1NewCrmprojectticketnotificationToAttach = em.getReference(crmprojectticketnotificationCollection1NewCrmprojectticketnotificationToAttach.getClass(), crmprojectticketnotificationCollection1NewCrmprojectticketnotificationToAttach.getCrmprojectticketnotificationPK());
                attachedCrmprojectticketnotificationCollection1New.add(crmprojectticketnotificationCollection1NewCrmprojectticketnotificationToAttach);
            }
            crmprojectticketnotificationCollection1New = attachedCrmprojectticketnotificationCollection1New;
            companyemployee.setCrmprojectticketnotificationCollection1(crmprojectticketnotificationCollection1New);
            Collection<Crmprojectticketnotification> attachedCrmprojectticketnotificationCollection2New = new ArrayList<Crmprojectticketnotification>();
            for (Crmprojectticketnotification crmprojectticketnotificationCollection2NewCrmprojectticketnotificationToAttach : crmprojectticketnotificationCollection2New) {
                crmprojectticketnotificationCollection2NewCrmprojectticketnotificationToAttach = em.getReference(crmprojectticketnotificationCollection2NewCrmprojectticketnotificationToAttach.getClass(), crmprojectticketnotificationCollection2NewCrmprojectticketnotificationToAttach.getCrmprojectticketnotificationPK());
                attachedCrmprojectticketnotificationCollection2New.add(crmprojectticketnotificationCollection2NewCrmprojectticketnotificationToAttach);
            }
            crmprojectticketnotificationCollection2New = attachedCrmprojectticketnotificationCollection2New;
            companyemployee.setCrmprojectticketnotificationCollection2(crmprojectticketnotificationCollection2New);
            Collection<Crmvisitor> attachedCrmvisitorCollectionNew = new ArrayList<Crmvisitor>();
            for (Crmvisitor crmvisitorCollectionNewCrmvisitorToAttach : crmvisitorCollectionNew) {
                crmvisitorCollectionNewCrmvisitorToAttach = em.getReference(crmvisitorCollectionNewCrmvisitorToAttach.getClass(), crmvisitorCollectionNewCrmvisitorToAttach.getCrmvisitorPK());
                attachedCrmvisitorCollectionNew.add(crmvisitorCollectionNewCrmvisitorToAttach);
            }
            crmvisitorCollectionNew = attachedCrmvisitorCollectionNew;
            companyemployee.setCrmvisitorCollection(crmvisitorCollectionNew);
            Collection<Crmvisitor> attachedCrmvisitorCollection1New = new ArrayList<Crmvisitor>();
            for (Crmvisitor crmvisitorCollection1NewCrmvisitorToAttach : crmvisitorCollection1New) {
                crmvisitorCollection1NewCrmvisitorToAttach = em.getReference(crmvisitorCollection1NewCrmvisitorToAttach.getClass(), crmvisitorCollection1NewCrmvisitorToAttach.getCrmvisitorPK());
                attachedCrmvisitorCollection1New.add(crmvisitorCollection1NewCrmvisitorToAttach);
            }
            crmvisitorCollection1New = attachedCrmvisitorCollection1New;
            companyemployee.setCrmvisitorCollection1(crmvisitorCollection1New);
            Collection<Crmworkordertype> attachedCrmworkordertypeCollectionNew = new ArrayList<Crmworkordertype>();
            for (Crmworkordertype crmworkordertypeCollectionNewCrmworkordertypeToAttach : crmworkordertypeCollectionNew) {
                crmworkordertypeCollectionNewCrmworkordertypeToAttach = em.getReference(crmworkordertypeCollectionNewCrmworkordertypeToAttach.getClass(), crmworkordertypeCollectionNewCrmworkordertypeToAttach.getCrmworkordertypePK());
                attachedCrmworkordertypeCollectionNew.add(crmworkordertypeCollectionNewCrmworkordertypeToAttach);
            }
            crmworkordertypeCollectionNew = attachedCrmworkordertypeCollectionNew;
            companyemployee.setCrmworkordertypeCollection(crmworkordertypeCollectionNew);
            Collection<Crmworkordertype> attachedCrmworkordertypeCollection1New = new ArrayList<Crmworkordertype>();
            for (Crmworkordertype crmworkordertypeCollection1NewCrmworkordertypeToAttach : crmworkordertypeCollection1New) {
                crmworkordertypeCollection1NewCrmworkordertypeToAttach = em.getReference(crmworkordertypeCollection1NewCrmworkordertypeToAttach.getClass(), crmworkordertypeCollection1NewCrmworkordertypeToAttach.getCrmworkordertypePK());
                attachedCrmworkordertypeCollection1New.add(crmworkordertypeCollection1NewCrmworkordertypeToAttach);
            }
            crmworkordertypeCollection1New = attachedCrmworkordertypeCollection1New;
            companyemployee.setCrmworkordertypeCollection1(crmworkordertypeCollection1New);
            Collection<Crmlabortype> attachedCrmlabortypeCollectionNew = new ArrayList<Crmlabortype>();
            for (Crmlabortype crmlabortypeCollectionNewCrmlabortypeToAttach : crmlabortypeCollectionNew) {
                crmlabortypeCollectionNewCrmlabortypeToAttach = em.getReference(crmlabortypeCollectionNewCrmlabortypeToAttach.getClass(), crmlabortypeCollectionNewCrmlabortypeToAttach.getCrmlabortypePK());
                attachedCrmlabortypeCollectionNew.add(crmlabortypeCollectionNewCrmlabortypeToAttach);
            }
            crmlabortypeCollectionNew = attachedCrmlabortypeCollectionNew;
            companyemployee.setCrmlabortypeCollection(crmlabortypeCollectionNew);
            Collection<Crmlabortype> attachedCrmlabortypeCollection1New = new ArrayList<Crmlabortype>();
            for (Crmlabortype crmlabortypeCollection1NewCrmlabortypeToAttach : crmlabortypeCollection1New) {
                crmlabortypeCollection1NewCrmlabortypeToAttach = em.getReference(crmlabortypeCollection1NewCrmlabortypeToAttach.getClass(), crmlabortypeCollection1NewCrmlabortypeToAttach.getCrmlabortypePK());
                attachedCrmlabortypeCollection1New.add(crmlabortypeCollection1NewCrmlabortypeToAttach);
            }
            crmlabortypeCollection1New = attachedCrmlabortypeCollection1New;
            companyemployee.setCrmlabortypeCollection1(crmlabortypeCollection1New);
            Collection<Crmbillingtype> attachedCrmbillingtypeCollectionNew = new ArrayList<Crmbillingtype>();
            for (Crmbillingtype crmbillingtypeCollectionNewCrmbillingtypeToAttach : crmbillingtypeCollectionNew) {
                crmbillingtypeCollectionNewCrmbillingtypeToAttach = em.getReference(crmbillingtypeCollectionNewCrmbillingtypeToAttach.getClass(), crmbillingtypeCollectionNewCrmbillingtypeToAttach.getCrmbillingtypePK());
                attachedCrmbillingtypeCollectionNew.add(crmbillingtypeCollectionNewCrmbillingtypeToAttach);
            }
            crmbillingtypeCollectionNew = attachedCrmbillingtypeCollectionNew;
            companyemployee.setCrmbillingtypeCollection(crmbillingtypeCollectionNew);
            Collection<Crmbillingtype> attachedCrmbillingtypeCollection1New = new ArrayList<Crmbillingtype>();
            for (Crmbillingtype crmbillingtypeCollection1NewCrmbillingtypeToAttach : crmbillingtypeCollection1New) {
                crmbillingtypeCollection1NewCrmbillingtypeToAttach = em.getReference(crmbillingtypeCollection1NewCrmbillingtypeToAttach.getClass(), crmbillingtypeCollection1NewCrmbillingtypeToAttach.getCrmbillingtypePK());
                attachedCrmbillingtypeCollection1New.add(crmbillingtypeCollection1NewCrmbillingtypeToAttach);
            }
            crmbillingtypeCollection1New = attachedCrmbillingtypeCollection1New;
            companyemployee.setCrmbillingtypeCollection1(crmbillingtypeCollection1New);
            Collection<Companyproductcategory> attachedCompanyproductcategoryCollectionNew = new ArrayList<Companyproductcategory>();
            for (Companyproductcategory companyproductcategoryCollectionNewCompanyproductcategoryToAttach : companyproductcategoryCollectionNew) {
                companyproductcategoryCollectionNewCompanyproductcategoryToAttach = em.getReference(companyproductcategoryCollectionNewCompanyproductcategoryToAttach.getClass(), companyproductcategoryCollectionNewCompanyproductcategoryToAttach.getCompanyproductcategoryPK());
                attachedCompanyproductcategoryCollectionNew.add(companyproductcategoryCollectionNewCompanyproductcategoryToAttach);
            }
            companyproductcategoryCollectionNew = attachedCompanyproductcategoryCollectionNew;
            companyemployee.setCompanyproductcategoryCollection(companyproductcategoryCollectionNew);
            Collection<Companyproductcategory> attachedCompanyproductcategoryCollection1New = new ArrayList<Companyproductcategory>();
            for (Companyproductcategory companyproductcategoryCollection1NewCompanyproductcategoryToAttach : companyproductcategoryCollection1New) {
                companyproductcategoryCollection1NewCompanyproductcategoryToAttach = em.getReference(companyproductcategoryCollection1NewCompanyproductcategoryToAttach.getClass(), companyproductcategoryCollection1NewCompanyproductcategoryToAttach.getCompanyproductcategoryPK());
                attachedCompanyproductcategoryCollection1New.add(companyproductcategoryCollection1NewCompanyproductcategoryToAttach);
            }
            companyproductcategoryCollection1New = attachedCompanyproductcategoryCollection1New;
            companyemployee.setCompanyproductcategoryCollection1(companyproductcategoryCollection1New);
            Collection<Crmprojecttask> attachedCrmprojecttaskCollectionNew = new ArrayList<Crmprojecttask>();
            for (Crmprojecttask crmprojecttaskCollectionNewCrmprojecttaskToAttach : crmprojecttaskCollectionNew) {
                crmprojecttaskCollectionNewCrmprojecttaskToAttach = em.getReference(crmprojecttaskCollectionNewCrmprojecttaskToAttach.getClass(), crmprojecttaskCollectionNewCrmprojecttaskToAttach.getCrmprojecttaskPK());
                attachedCrmprojecttaskCollectionNew.add(crmprojecttaskCollectionNewCrmprojecttaskToAttach);
            }
            crmprojecttaskCollectionNew = attachedCrmprojecttaskCollectionNew;
            companyemployee.setCrmprojecttaskCollection(crmprojecttaskCollectionNew);
            Collection<Crmprojecttask> attachedCrmprojecttaskCollection1New = new ArrayList<Crmprojecttask>();
            for (Crmprojecttask crmprojecttaskCollection1NewCrmprojecttaskToAttach : crmprojecttaskCollection1New) {
                crmprojecttaskCollection1NewCrmprojecttaskToAttach = em.getReference(crmprojecttaskCollection1NewCrmprojecttaskToAttach.getClass(), crmprojecttaskCollection1NewCrmprojecttaskToAttach.getCrmprojecttaskPK());
                attachedCrmprojecttaskCollection1New.add(crmprojecttaskCollection1NewCrmprojecttaskToAttach);
            }
            crmprojecttaskCollection1New = attachedCrmprojecttaskCollection1New;
            companyemployee.setCrmprojecttaskCollection1(crmprojecttaskCollection1New);
            Collection<Crmcampaignprops> attachedCrmcampaignpropsCollectionNew = new ArrayList<Crmcampaignprops>();
            for (Crmcampaignprops crmcampaignpropsCollectionNewCrmcampaignpropsToAttach : crmcampaignpropsCollectionNew) {
                crmcampaignpropsCollectionNewCrmcampaignpropsToAttach = em.getReference(crmcampaignpropsCollectionNewCrmcampaignpropsToAttach.getClass(), crmcampaignpropsCollectionNewCrmcampaignpropsToAttach.getCrmcampaignpropsPK());
                attachedCrmcampaignpropsCollectionNew.add(crmcampaignpropsCollectionNewCrmcampaignpropsToAttach);
            }
            crmcampaignpropsCollectionNew = attachedCrmcampaignpropsCollectionNew;
            companyemployee.setCrmcampaignpropsCollection(crmcampaignpropsCollectionNew);
            Collection<Crmcampaignprops> attachedCrmcampaignpropsCollection1New = new ArrayList<Crmcampaignprops>();
            for (Crmcampaignprops crmcampaignpropsCollection1NewCrmcampaignpropsToAttach : crmcampaignpropsCollection1New) {
                crmcampaignpropsCollection1NewCrmcampaignpropsToAttach = em.getReference(crmcampaignpropsCollection1NewCrmcampaignpropsToAttach.getClass(), crmcampaignpropsCollection1NewCrmcampaignpropsToAttach.getCrmcampaignpropsPK());
                attachedCrmcampaignpropsCollection1New.add(crmcampaignpropsCollection1NewCrmcampaignpropsToAttach);
            }
            crmcampaignpropsCollection1New = attachedCrmcampaignpropsCollection1New;
            companyemployee.setCrmcampaignpropsCollection1(crmcampaignpropsCollection1New);
            Collection<Customercontactsaddress> attachedCustomercontactsaddressCollectionNew = new ArrayList<Customercontactsaddress>();
            for (Customercontactsaddress customercontactsaddressCollectionNewCustomercontactsaddressToAttach : customercontactsaddressCollectionNew) {
                customercontactsaddressCollectionNewCustomercontactsaddressToAttach = em.getReference(customercontactsaddressCollectionNewCustomercontactsaddressToAttach.getClass(), customercontactsaddressCollectionNewCustomercontactsaddressToAttach.getCustomercontactsaddressPK());
                attachedCustomercontactsaddressCollectionNew.add(customercontactsaddressCollectionNewCustomercontactsaddressToAttach);
            }
            customercontactsaddressCollectionNew = attachedCustomercontactsaddressCollectionNew;
            companyemployee.setCustomercontactsaddressCollection(customercontactsaddressCollectionNew);
            Collection<Customercontactsaddress> attachedCustomercontactsaddressCollection1New = new ArrayList<Customercontactsaddress>();
            for (Customercontactsaddress customercontactsaddressCollection1NewCustomercontactsaddressToAttach : customercontactsaddressCollection1New) {
                customercontactsaddressCollection1NewCustomercontactsaddressToAttach = em.getReference(customercontactsaddressCollection1NewCustomercontactsaddressToAttach.getClass(), customercontactsaddressCollection1NewCustomercontactsaddressToAttach.getCustomercontactsaddressPK());
                attachedCustomercontactsaddressCollection1New.add(customercontactsaddressCollection1NewCustomercontactsaddressToAttach);
            }
            customercontactsaddressCollection1New = attachedCustomercontactsaddressCollection1New;
            companyemployee.setCustomercontactsaddressCollection1(customercontactsaddressCollection1New);
            Collection<Customercategory> attachedCustomercategoryCollectionNew = new ArrayList<Customercategory>();
            for (Customercategory customercategoryCollectionNewCustomercategoryToAttach : customercategoryCollectionNew) {
                customercategoryCollectionNewCustomercategoryToAttach = em.getReference(customercategoryCollectionNewCustomercategoryToAttach.getClass(), customercategoryCollectionNewCustomercategoryToAttach.getCustomercategoryPK());
                attachedCustomercategoryCollectionNew.add(customercategoryCollectionNewCustomercategoryToAttach);
            }
            customercategoryCollectionNew = attachedCustomercategoryCollectionNew;
            companyemployee.setCustomercategoryCollection(customercategoryCollectionNew);
            Collection<Customercategory> attachedCustomercategoryCollection1New = new ArrayList<Customercategory>();
            for (Customercategory customercategoryCollection1NewCustomercategoryToAttach : customercategoryCollection1New) {
                customercategoryCollection1NewCustomercategoryToAttach = em.getReference(customercategoryCollection1NewCustomercategoryToAttach.getClass(), customercategoryCollection1NewCustomercategoryToAttach.getCustomercategoryPK());
                attachedCustomercategoryCollection1New.add(customercategoryCollection1NewCustomercategoryToAttach);
            }
            customercategoryCollection1New = attachedCustomercategoryCollection1New;
            companyemployee.setCustomercategoryCollection1(customercategoryCollection1New);
            Collection<Productcomponents> attachedProductcomponentsCollectionNew = new ArrayList<Productcomponents>();
            for (Productcomponents productcomponentsCollectionNewProductcomponentsToAttach : productcomponentsCollectionNew) {
                productcomponentsCollectionNewProductcomponentsToAttach = em.getReference(productcomponentsCollectionNewProductcomponentsToAttach.getClass(), productcomponentsCollectionNewProductcomponentsToAttach.getProductcomponentsPK());
                attachedProductcomponentsCollectionNew.add(productcomponentsCollectionNewProductcomponentsToAttach);
            }
            productcomponentsCollectionNew = attachedProductcomponentsCollectionNew;
            companyemployee.setProductcomponentsCollection(productcomponentsCollectionNew);
            Collection<Productcomponents> attachedProductcomponentsCollection1New = new ArrayList<Productcomponents>();
            for (Productcomponents productcomponentsCollection1NewProductcomponentsToAttach : productcomponentsCollection1New) {
                productcomponentsCollection1NewProductcomponentsToAttach = em.getReference(productcomponentsCollection1NewProductcomponentsToAttach.getClass(), productcomponentsCollection1NewProductcomponentsToAttach.getProductcomponentsPK());
                attachedProductcomponentsCollection1New.add(productcomponentsCollection1NewProductcomponentsToAttach);
            }
            productcomponentsCollection1New = attachedProductcomponentsCollection1New;
            companyemployee.setProductcomponentsCollection1(productcomponentsCollection1New);
            Collection<Crmexpensetype> attachedCrmexpensetypeCollectionNew = new ArrayList<Crmexpensetype>();
            for (Crmexpensetype crmexpensetypeCollectionNewCrmexpensetypeToAttach : crmexpensetypeCollectionNew) {
                crmexpensetypeCollectionNewCrmexpensetypeToAttach = em.getReference(crmexpensetypeCollectionNewCrmexpensetypeToAttach.getClass(), crmexpensetypeCollectionNewCrmexpensetypeToAttach.getCrmexpensetypePK());
                attachedCrmexpensetypeCollectionNew.add(crmexpensetypeCollectionNewCrmexpensetypeToAttach);
            }
            crmexpensetypeCollectionNew = attachedCrmexpensetypeCollectionNew;
            companyemployee.setCrmexpensetypeCollection(crmexpensetypeCollectionNew);
            Collection<Crmexpensetype> attachedCrmexpensetypeCollection1New = new ArrayList<Crmexpensetype>();
            for (Crmexpensetype crmexpensetypeCollection1NewCrmexpensetypeToAttach : crmexpensetypeCollection1New) {
                crmexpensetypeCollection1NewCrmexpensetypeToAttach = em.getReference(crmexpensetypeCollection1NewCrmexpensetypeToAttach.getClass(), crmexpensetypeCollection1NewCrmexpensetypeToAttach.getCrmexpensetypePK());
                attachedCrmexpensetypeCollection1New.add(crmexpensetypeCollection1NewCrmexpensetypeToAttach);
            }
            crmexpensetypeCollection1New = attachedCrmexpensetypeCollection1New;
            companyemployee.setCrmexpensetypeCollection1(crmexpensetypeCollection1New);
            Collection<Companypayments> attachedCompanypaymentsCollectionNew = new ArrayList<Companypayments>();
            for (Companypayments companypaymentsCollectionNewCompanypaymentsToAttach : companypaymentsCollectionNew) {
                companypaymentsCollectionNewCompanypaymentsToAttach = em.getReference(companypaymentsCollectionNewCompanypaymentsToAttach.getClass(), companypaymentsCollectionNewCompanypaymentsToAttach.getCompanypaymentsPK());
                attachedCompanypaymentsCollectionNew.add(companypaymentsCollectionNewCompanypaymentsToAttach);
            }
            companypaymentsCollectionNew = attachedCompanypaymentsCollectionNew;
            companyemployee.setCompanypaymentsCollection(companypaymentsCollectionNew);
            Collection<Companypayments> attachedCompanypaymentsCollection1New = new ArrayList<Companypayments>();
            for (Companypayments companypaymentsCollection1NewCompanypaymentsToAttach : companypaymentsCollection1New) {
                companypaymentsCollection1NewCompanypaymentsToAttach = em.getReference(companypaymentsCollection1NewCompanypaymentsToAttach.getClass(), companypaymentsCollection1NewCompanypaymentsToAttach.getCompanypaymentsPK());
                attachedCompanypaymentsCollection1New.add(companypaymentsCollection1NewCompanypaymentsToAttach);
            }
            companypaymentsCollection1New = attachedCompanypaymentsCollection1New;
            companyemployee.setCompanypaymentsCollection1(companypaymentsCollection1New);
            Collection<Crmworkorderresolution> attachedCrmworkorderresolutionCollectionNew = new ArrayList<Crmworkorderresolution>();
            for (Crmworkorderresolution crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach : crmworkorderresolutionCollectionNew) {
                crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach = em.getReference(crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach.getClass(), crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach.getCrmworkorderresolutionPK());
                attachedCrmworkorderresolutionCollectionNew.add(crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach);
            }
            crmworkorderresolutionCollectionNew = attachedCrmworkorderresolutionCollectionNew;
            companyemployee.setCrmworkorderresolutionCollection(crmworkorderresolutionCollectionNew);
            Collection<Crmworkorderresolution> attachedCrmworkorderresolutionCollection1New = new ArrayList<Crmworkorderresolution>();
            for (Crmworkorderresolution crmworkorderresolutionCollection1NewCrmworkorderresolutionToAttach : crmworkorderresolutionCollection1New) {
                crmworkorderresolutionCollection1NewCrmworkorderresolutionToAttach = em.getReference(crmworkorderresolutionCollection1NewCrmworkorderresolutionToAttach.getClass(), crmworkorderresolutionCollection1NewCrmworkorderresolutionToAttach.getCrmworkorderresolutionPK());
                attachedCrmworkorderresolutionCollection1New.add(crmworkorderresolutionCollection1NewCrmworkorderresolutionToAttach);
            }
            crmworkorderresolutionCollection1New = attachedCrmworkorderresolutionCollection1New;
            companyemployee.setCrmworkorderresolutionCollection1(crmworkorderresolutionCollection1New);
            Collection<Company> attachedCompanyCollectionNew = new ArrayList<Company>();
            for (Company companyCollectionNewCompanyToAttach : companyCollectionNew) {
                companyCollectionNewCompanyToAttach = em.getReference(companyCollectionNewCompanyToAttach.getClass(), companyCollectionNewCompanyToAttach.getCompanyPK());
                attachedCompanyCollectionNew.add(companyCollectionNewCompanyToAttach);
            }
            companyCollectionNew = attachedCompanyCollectionNew;
            companyemployee.setCompanyCollection(companyCollectionNew);
            Collection<Company> attachedCompanyCollection1New = new ArrayList<Company>();
            for (Company companyCollection1NewCompanyToAttach : companyCollection1New) {
                companyCollection1NewCompanyToAttach = em.getReference(companyCollection1NewCompanyToAttach.getClass(), companyCollection1NewCompanyToAttach.getCompanyPK());
                attachedCompanyCollection1New.add(companyCollection1NewCompanyToAttach);
            }
            companyCollection1New = attachedCompanyCollection1New;
            companyemployee.setCompanyCollection1(companyCollection1New);
            Collection<Companyaddresstype> attachedCompanyaddresstypeCollectionNew = new ArrayList<Companyaddresstype>();
            for (Companyaddresstype companyaddresstypeCollectionNewCompanyaddresstypeToAttach : companyaddresstypeCollectionNew) {
                companyaddresstypeCollectionNewCompanyaddresstypeToAttach = em.getReference(companyaddresstypeCollectionNewCompanyaddresstypeToAttach.getClass(), companyaddresstypeCollectionNewCompanyaddresstypeToAttach.getCompanyaddresstypePK());
                attachedCompanyaddresstypeCollectionNew.add(companyaddresstypeCollectionNewCompanyaddresstypeToAttach);
            }
            companyaddresstypeCollectionNew = attachedCompanyaddresstypeCollectionNew;
            companyemployee.setCompanyaddresstypeCollection(companyaddresstypeCollectionNew);
            Collection<Companyaddresstype> attachedCompanyaddresstypeCollection1New = new ArrayList<Companyaddresstype>();
            for (Companyaddresstype companyaddresstypeCollection1NewCompanyaddresstypeToAttach : companyaddresstypeCollection1New) {
                companyaddresstypeCollection1NewCompanyaddresstypeToAttach = em.getReference(companyaddresstypeCollection1NewCompanyaddresstypeToAttach.getClass(), companyaddresstypeCollection1NewCompanyaddresstypeToAttach.getCompanyaddresstypePK());
                attachedCompanyaddresstypeCollection1New.add(companyaddresstypeCollection1NewCompanyaddresstypeToAttach);
            }
            companyaddresstypeCollection1New = attachedCompanyaddresstypeCollection1New;
            companyemployee.setCompanyaddresstypeCollection1(companyaddresstypeCollection1New);
            Collection<Crmemployeecontacts> attachedCrmemployeecontactsCollectionNew = new ArrayList<Crmemployeecontacts>();
            for (Crmemployeecontacts crmemployeecontactsCollectionNewCrmemployeecontactsToAttach : crmemployeecontactsCollectionNew) {
                crmemployeecontactsCollectionNewCrmemployeecontactsToAttach = em.getReference(crmemployeecontactsCollectionNewCrmemployeecontactsToAttach.getClass(), crmemployeecontactsCollectionNewCrmemployeecontactsToAttach.getCrmemployeecontactsPK());
                attachedCrmemployeecontactsCollectionNew.add(crmemployeecontactsCollectionNewCrmemployeecontactsToAttach);
            }
            crmemployeecontactsCollectionNew = attachedCrmemployeecontactsCollectionNew;
            companyemployee.setCrmemployeecontactsCollection(crmemployeecontactsCollectionNew);
            Collection<Approval> attachedApprovalCollectionNew = new ArrayList<Approval>();
            for (Approval approvalCollectionNewApprovalToAttach : approvalCollectionNew) {
                approvalCollectionNewApprovalToAttach = em.getReference(approvalCollectionNewApprovalToAttach.getClass(), approvalCollectionNewApprovalToAttach.getApprovalPK());
                attachedApprovalCollectionNew.add(approvalCollectionNewApprovalToAttach);
            }
            approvalCollectionNew = attachedApprovalCollectionNew;
            companyemployee.setApprovalCollection(approvalCollectionNew);
            Collection<Approval> attachedApprovalCollection1New = new ArrayList<Approval>();
            for (Approval approvalCollection1NewApprovalToAttach : approvalCollection1New) {
                approvalCollection1NewApprovalToAttach = em.getReference(approvalCollection1NewApprovalToAttach.getClass(), approvalCollection1NewApprovalToAttach.getApprovalPK());
                attachedApprovalCollection1New.add(approvalCollection1NewApprovalToAttach);
            }
            approvalCollection1New = attachedApprovalCollection1New;
            companyemployee.setApprovalCollection1(approvalCollection1New);
            Collection<Approval> attachedApprovalCollection2New = new ArrayList<Approval>();
            for (Approval approvalCollection2NewApprovalToAttach : approvalCollection2New) {
                approvalCollection2NewApprovalToAttach = em.getReference(approvalCollection2NewApprovalToAttach.getClass(), approvalCollection2NewApprovalToAttach.getApprovalPK());
                attachedApprovalCollection2New.add(approvalCollection2NewApprovalToAttach);
            }
            approvalCollection2New = attachedApprovalCollection2New;
            companyemployee.setApprovalCollection2(approvalCollection2New);
            Collection<Workorderinstructions> attachedWorkorderinstructionsCollectionNew = new ArrayList<Workorderinstructions>();
            for (Workorderinstructions workorderinstructionsCollectionNewWorkorderinstructionsToAttach : workorderinstructionsCollectionNew) {
                workorderinstructionsCollectionNewWorkorderinstructionsToAttach = em.getReference(workorderinstructionsCollectionNewWorkorderinstructionsToAttach.getClass(), workorderinstructionsCollectionNewWorkorderinstructionsToAttach.getWorkorderinstructionsPK());
                attachedWorkorderinstructionsCollectionNew.add(workorderinstructionsCollectionNewWorkorderinstructionsToAttach);
            }
            workorderinstructionsCollectionNew = attachedWorkorderinstructionsCollectionNew;
            companyemployee.setWorkorderinstructionsCollection(workorderinstructionsCollectionNew);
            Collection<Workorderinstructions> attachedWorkorderinstructionsCollection1New = new ArrayList<Workorderinstructions>();
            for (Workorderinstructions workorderinstructionsCollection1NewWorkorderinstructionsToAttach : workorderinstructionsCollection1New) {
                workorderinstructionsCollection1NewWorkorderinstructionsToAttach = em.getReference(workorderinstructionsCollection1NewWorkorderinstructionsToAttach.getClass(), workorderinstructionsCollection1NewWorkorderinstructionsToAttach.getWorkorderinstructionsPK());
                attachedWorkorderinstructionsCollection1New.add(workorderinstructionsCollection1NewWorkorderinstructionsToAttach);
            }
            workorderinstructionsCollection1New = attachedWorkorderinstructionsCollection1New;
            companyemployee.setWorkorderinstructionsCollection1(workorderinstructionsCollection1New);
            Collection<Companycustomer> attachedCompanycustomerCollectionNew = new ArrayList<Companycustomer>();
            for (Companycustomer companycustomerCollectionNewCompanycustomerToAttach : companycustomerCollectionNew) {
                companycustomerCollectionNewCompanycustomerToAttach = em.getReference(companycustomerCollectionNewCompanycustomerToAttach.getClass(), companycustomerCollectionNewCompanycustomerToAttach.getCompanycustomerPK());
                attachedCompanycustomerCollectionNew.add(companycustomerCollectionNewCompanycustomerToAttach);
            }
            companycustomerCollectionNew = attachedCompanycustomerCollectionNew;
            companyemployee.setCompanycustomerCollection(companycustomerCollectionNew);
            Collection<Companycustomer> attachedCompanycustomerCollection1New = new ArrayList<Companycustomer>();
            for (Companycustomer companycustomerCollection1NewCompanycustomerToAttach : companycustomerCollection1New) {
                companycustomerCollection1NewCompanycustomerToAttach = em.getReference(companycustomerCollection1NewCompanycustomerToAttach.getClass(), companycustomerCollection1NewCompanycustomerToAttach.getCompanycustomerPK());
                attachedCompanycustomerCollection1New.add(companycustomerCollection1NewCompanycustomerToAttach);
            }
            companycustomerCollection1New = attachedCompanycustomerCollection1New;
            companyemployee.setCompanycustomerCollection1(companycustomerCollection1New);
            Collection<Crmforum> attachedCrmforumCollectionNew = new ArrayList<Crmforum>();
            for (Crmforum crmforumCollectionNewCrmforumToAttach : crmforumCollectionNew) {
                crmforumCollectionNewCrmforumToAttach = em.getReference(crmforumCollectionNewCrmforumToAttach.getClass(), crmforumCollectionNewCrmforumToAttach.getCrmforumPK());
                attachedCrmforumCollectionNew.add(crmforumCollectionNewCrmforumToAttach);
            }
            crmforumCollectionNew = attachedCrmforumCollectionNew;
            companyemployee.setCrmforumCollection(crmforumCollectionNew);
            Collection<Crmprojectstatus> attachedCrmprojectstatusCollectionNew = new ArrayList<Crmprojectstatus>();
            for (Crmprojectstatus crmprojectstatusCollectionNewCrmprojectstatusToAttach : crmprojectstatusCollectionNew) {
                crmprojectstatusCollectionNewCrmprojectstatusToAttach = em.getReference(crmprojectstatusCollectionNewCrmprojectstatusToAttach.getClass(), crmprojectstatusCollectionNewCrmprojectstatusToAttach.getCrmprojectstatusPK());
                attachedCrmprojectstatusCollectionNew.add(crmprojectstatusCollectionNewCrmprojectstatusToAttach);
            }
            crmprojectstatusCollectionNew = attachedCrmprojectstatusCollectionNew;
            companyemployee.setCrmprojectstatusCollection(crmprojectstatusCollectionNew);
            Collection<Crmprojectstatus> attachedCrmprojectstatusCollection1New = new ArrayList<Crmprojectstatus>();
            for (Crmprojectstatus crmprojectstatusCollection1NewCrmprojectstatusToAttach : crmprojectstatusCollection1New) {
                crmprojectstatusCollection1NewCrmprojectstatusToAttach = em.getReference(crmprojectstatusCollection1NewCrmprojectstatusToAttach.getClass(), crmprojectstatusCollection1NewCrmprojectstatusToAttach.getCrmprojectstatusPK());
                attachedCrmprojectstatusCollection1New.add(crmprojectstatusCollection1NewCrmprojectstatusToAttach);
            }
            crmprojectstatusCollection1New = attachedCrmprojectstatusCollection1New;
            companyemployee.setCrmprojectstatusCollection1(crmprojectstatusCollection1New);
            Collection<Crmschedulerevnttype> attachedCrmschedulerevnttypeCollectionNew = new ArrayList<Crmschedulerevnttype>();
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionNewCrmschedulerevnttypeToAttach : crmschedulerevnttypeCollectionNew) {
                crmschedulerevnttypeCollectionNewCrmschedulerevnttypeToAttach = em.getReference(crmschedulerevnttypeCollectionNewCrmschedulerevnttypeToAttach.getClass(), crmschedulerevnttypeCollectionNewCrmschedulerevnttypeToAttach.getCrmschedulerevnttypePK());
                attachedCrmschedulerevnttypeCollectionNew.add(crmschedulerevnttypeCollectionNewCrmschedulerevnttypeToAttach);
            }
            crmschedulerevnttypeCollectionNew = attachedCrmschedulerevnttypeCollectionNew;
            companyemployee.setCrmschedulerevnttypeCollection(crmschedulerevnttypeCollectionNew);
            Collection<Crmschedulerevnttype> attachedCrmschedulerevnttypeCollection1New = new ArrayList<Crmschedulerevnttype>();
            for (Crmschedulerevnttype crmschedulerevnttypeCollection1NewCrmschedulerevnttypeToAttach : crmschedulerevnttypeCollection1New) {
                crmschedulerevnttypeCollection1NewCrmschedulerevnttypeToAttach = em.getReference(crmschedulerevnttypeCollection1NewCrmschedulerevnttypeToAttach.getClass(), crmschedulerevnttypeCollection1NewCrmschedulerevnttypeToAttach.getCrmschedulerevnttypePK());
                attachedCrmschedulerevnttypeCollection1New.add(crmschedulerevnttypeCollection1NewCrmschedulerevnttypeToAttach);
            }
            crmschedulerevnttypeCollection1New = attachedCrmschedulerevnttypeCollection1New;
            companyemployee.setCrmschedulerevnttypeCollection1(crmschedulerevnttypeCollection1New);
            Collection<Crmschedulerefcode> attachedCrmschedulerefcodeCollectionNew = new ArrayList<Crmschedulerefcode>();
            for (Crmschedulerefcode crmschedulerefcodeCollectionNewCrmschedulerefcodeToAttach : crmschedulerefcodeCollectionNew) {
                crmschedulerefcodeCollectionNewCrmschedulerefcodeToAttach = em.getReference(crmschedulerefcodeCollectionNewCrmschedulerefcodeToAttach.getClass(), crmschedulerefcodeCollectionNewCrmschedulerefcodeToAttach.getCrmschedulerefcodePK());
                attachedCrmschedulerefcodeCollectionNew.add(crmschedulerefcodeCollectionNewCrmschedulerefcodeToAttach);
            }
            crmschedulerefcodeCollectionNew = attachedCrmschedulerefcodeCollectionNew;
            companyemployee.setCrmschedulerefcodeCollection(crmschedulerefcodeCollectionNew);
            Collection<Crmschedulerefcode> attachedCrmschedulerefcodeCollection1New = new ArrayList<Crmschedulerefcode>();
            for (Crmschedulerefcode crmschedulerefcodeCollection1NewCrmschedulerefcodeToAttach : crmschedulerefcodeCollection1New) {
                crmschedulerefcodeCollection1NewCrmschedulerefcodeToAttach = em.getReference(crmschedulerefcodeCollection1NewCrmschedulerefcodeToAttach.getClass(), crmschedulerefcodeCollection1NewCrmschedulerefcodeToAttach.getCrmschedulerefcodePK());
                attachedCrmschedulerefcodeCollection1New.add(crmschedulerefcodeCollection1NewCrmschedulerefcodeToAttach);
            }
            crmschedulerefcodeCollection1New = attachedCrmschedulerefcodeCollection1New;
            companyemployee.setCrmschedulerefcodeCollection1(crmschedulerefcodeCollection1New);
            Collection<Componenttype> attachedComponenttypeCollectionNew = new ArrayList<Componenttype>();
            for (Componenttype componenttypeCollectionNewComponenttypeToAttach : componenttypeCollectionNew) {
                componenttypeCollectionNewComponenttypeToAttach = em.getReference(componenttypeCollectionNewComponenttypeToAttach.getClass(), componenttypeCollectionNewComponenttypeToAttach.getComponenttypePK());
                attachedComponenttypeCollectionNew.add(componenttypeCollectionNewComponenttypeToAttach);
            }
            componenttypeCollectionNew = attachedComponenttypeCollectionNew;
            companyemployee.setComponenttypeCollection(componenttypeCollectionNew);
            Collection<Componenttype> attachedComponenttypeCollection1New = new ArrayList<Componenttype>();
            for (Componenttype componenttypeCollection1NewComponenttypeToAttach : componenttypeCollection1New) {
                componenttypeCollection1NewComponenttypeToAttach = em.getReference(componenttypeCollection1NewComponenttypeToAttach.getClass(), componenttypeCollection1NewComponenttypeToAttach.getComponenttypePK());
                attachedComponenttypeCollection1New.add(componenttypeCollection1NewComponenttypeToAttach);
            }
            componenttypeCollection1New = attachedComponenttypeCollection1New;
            companyemployee.setComponenttypeCollection1(componenttypeCollection1New);
            Collection<Companypaymentscheme> attachedCompanypaymentschemeCollectionNew = new ArrayList<Companypaymentscheme>();
            for (Companypaymentscheme companypaymentschemeCollectionNewCompanypaymentschemeToAttach : companypaymentschemeCollectionNew) {
                companypaymentschemeCollectionNewCompanypaymentschemeToAttach = em.getReference(companypaymentschemeCollectionNewCompanypaymentschemeToAttach.getClass(), companypaymentschemeCollectionNewCompanypaymentschemeToAttach.getCompanypaymentschemePK());
                attachedCompanypaymentschemeCollectionNew.add(companypaymentschemeCollectionNewCompanypaymentschemeToAttach);
            }
            companypaymentschemeCollectionNew = attachedCompanypaymentschemeCollectionNew;
            companyemployee.setCompanypaymentschemeCollection(companypaymentschemeCollectionNew);
            Collection<Companypaymentscheme> attachedCompanypaymentschemeCollection1New = new ArrayList<Companypaymentscheme>();
            for (Companypaymentscheme companypaymentschemeCollection1NewCompanypaymentschemeToAttach : companypaymentschemeCollection1New) {
                companypaymentschemeCollection1NewCompanypaymentschemeToAttach = em.getReference(companypaymentschemeCollection1NewCompanypaymentschemeToAttach.getClass(), companypaymentschemeCollection1NewCompanypaymentschemeToAttach.getCompanypaymentschemePK());
                attachedCompanypaymentschemeCollection1New.add(companypaymentschemeCollection1NewCompanypaymentschemeToAttach);
            }
            companypaymentschemeCollection1New = attachedCompanypaymentschemeCollection1New;
            companyemployee.setCompanypaymentschemeCollection1(companypaymentschemeCollection1New);
            Collection<Companycontactsaddress> attachedCompanycontactsaddressCollectionNew = new ArrayList<Companycontactsaddress>();
            for (Companycontactsaddress companycontactsaddressCollectionNewCompanycontactsaddressToAttach : companycontactsaddressCollectionNew) {
                companycontactsaddressCollectionNewCompanycontactsaddressToAttach = em.getReference(companycontactsaddressCollectionNewCompanycontactsaddressToAttach.getClass(), companycontactsaddressCollectionNewCompanycontactsaddressToAttach.getCompanycontactsaddressPK());
                attachedCompanycontactsaddressCollectionNew.add(companycontactsaddressCollectionNewCompanycontactsaddressToAttach);
            }
            companycontactsaddressCollectionNew = attachedCompanycontactsaddressCollectionNew;
            companyemployee.setCompanycontactsaddressCollection(companycontactsaddressCollectionNew);
            Collection<Companycontactsaddress> attachedCompanycontactsaddressCollection1New = new ArrayList<Companycontactsaddress>();
            for (Companycontactsaddress companycontactsaddressCollection1NewCompanycontactsaddressToAttach : companycontactsaddressCollection1New) {
                companycontactsaddressCollection1NewCompanycontactsaddressToAttach = em.getReference(companycontactsaddressCollection1NewCompanycontactsaddressToAttach.getClass(), companycontactsaddressCollection1NewCompanycontactsaddressToAttach.getCompanycontactsaddressPK());
                attachedCompanycontactsaddressCollection1New.add(companycontactsaddressCollection1NewCompanycontactsaddressToAttach);
            }
            companycontactsaddressCollection1New = attachedCompanycontactsaddressCollection1New;
            companyemployee.setCompanycontactsaddressCollection1(companycontactsaddressCollection1New);
            Collection<Crmprofilesettings> attachedCrmprofilesettingsCollectionNew = new ArrayList<Crmprofilesettings>();
            for (Crmprofilesettings crmprofilesettingsCollectionNewCrmprofilesettingsToAttach : crmprofilesettingsCollectionNew) {
                crmprofilesettingsCollectionNewCrmprofilesettingsToAttach = em.getReference(crmprofilesettingsCollectionNewCrmprofilesettingsToAttach.getClass(), crmprofilesettingsCollectionNewCrmprofilesettingsToAttach.getCrmprofilesettingsPK());
                attachedCrmprofilesettingsCollectionNew.add(crmprofilesettingsCollectionNewCrmprofilesettingsToAttach);
            }
            crmprofilesettingsCollectionNew = attachedCrmprofilesettingsCollectionNew;
            companyemployee.setCrmprofilesettingsCollection(crmprofilesettingsCollectionNew);
            companyemployee = em.merge(companyemployee);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanyemployeeCollection().remove(companyemployee);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanyemployeeCollection().add(companyemployee);
                companyNew = em.merge(companyNew);
            }
            if (companydepartmentNew != null && !companydepartmentNew.equals(companydepartmentOld)) {
                companydepartmentNew.setCompanyemployee(companyemployee);
                companydepartmentNew = em.merge(companydepartmentNew);
            }
            if (companyemployeeRelOld != null && !companyemployeeRelOld.equals(companyemployeeRelNew)) {
                companyemployeeRelOld.getCompanyemployeeCollection().remove(companyemployee);
                companyemployeeRelOld = em.merge(companyemployeeRelOld);
            }
            if (companyemployeeRelNew != null && !companyemployeeRelNew.equals(companyemployeeRelOld)) {
                companyemployeeRelNew.getCompanyemployeeCollection().add(companyemployee);
                companyemployeeRelNew = em.merge(companyemployeeRelNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanyemployeeCollection().remove(companyemployee);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanyemployeeCollection().add(companyemployee);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmmessagechannel crmmessagechannelCollectionNewCrmmessagechannel : crmmessagechannelCollectionNew) {
                if (!crmmessagechannelCollectionOld.contains(crmmessagechannelCollectionNewCrmmessagechannel)) {
                    Companyemployee oldCompanyemployeeOfCrmmessagechannelCollectionNewCrmmessagechannel = crmmessagechannelCollectionNewCrmmessagechannel.getCompanyemployee();
                    crmmessagechannelCollectionNewCrmmessagechannel.setCompanyemployee(companyemployee);
                    crmmessagechannelCollectionNewCrmmessagechannel = em.merge(crmmessagechannelCollectionNewCrmmessagechannel);
                    if (oldCompanyemployeeOfCrmmessagechannelCollectionNewCrmmessagechannel != null && !oldCompanyemployeeOfCrmmessagechannelCollectionNewCrmmessagechannel.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmmessagechannelCollectionNewCrmmessagechannel.getCrmmessagechannelCollection().remove(crmmessagechannelCollectionNewCrmmessagechannel);
                        oldCompanyemployeeOfCrmmessagechannelCollectionNewCrmmessagechannel = em.merge(oldCompanyemployeeOfCrmmessagechannelCollectionNewCrmmessagechannel);
                    }
                }
            }
            for (Crmmessagechannel crmmessagechannelCollection1NewCrmmessagechannel : crmmessagechannelCollection1New) {
                if (!crmmessagechannelCollection1Old.contains(crmmessagechannelCollection1NewCrmmessagechannel)) {
                    Companyemployee oldCompanyemployee1OfCrmmessagechannelCollection1NewCrmmessagechannel = crmmessagechannelCollection1NewCrmmessagechannel.getCompanyemployee1();
                    crmmessagechannelCollection1NewCrmmessagechannel.setCompanyemployee1(companyemployee);
                    crmmessagechannelCollection1NewCrmmessagechannel = em.merge(crmmessagechannelCollection1NewCrmmessagechannel);
                    if (oldCompanyemployee1OfCrmmessagechannelCollection1NewCrmmessagechannel != null && !oldCompanyemployee1OfCrmmessagechannelCollection1NewCrmmessagechannel.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmmessagechannelCollection1NewCrmmessagechannel.getCrmmessagechannelCollection1().remove(crmmessagechannelCollection1NewCrmmessagechannel);
                        oldCompanyemployee1OfCrmmessagechannelCollection1NewCrmmessagechannel = em.merge(oldCompanyemployee1OfCrmmessagechannelCollection1NewCrmmessagechannel);
                    }
                }
            }
            for (Crmmessagechannel crmmessagechannelCollection2NewCrmmessagechannel : crmmessagechannelCollection2New) {
                if (!crmmessagechannelCollection2Old.contains(crmmessagechannelCollection2NewCrmmessagechannel)) {
                    Companyemployee oldCompanyemployee2OfCrmmessagechannelCollection2NewCrmmessagechannel = crmmessagechannelCollection2NewCrmmessagechannel.getCompanyemployee2();
                    crmmessagechannelCollection2NewCrmmessagechannel.setCompanyemployee2(companyemployee);
                    crmmessagechannelCollection2NewCrmmessagechannel = em.merge(crmmessagechannelCollection2NewCrmmessagechannel);
                    if (oldCompanyemployee2OfCrmmessagechannelCollection2NewCrmmessagechannel != null && !oldCompanyemployee2OfCrmmessagechannelCollection2NewCrmmessagechannel.equals(companyemployee)) {
                        oldCompanyemployee2OfCrmmessagechannelCollection2NewCrmmessagechannel.getCrmmessagechannelCollection2().remove(crmmessagechannelCollection2NewCrmmessagechannel);
                        oldCompanyemployee2OfCrmmessagechannelCollection2NewCrmmessagechannel = em.merge(oldCompanyemployee2OfCrmmessagechannelCollection2NewCrmmessagechannel);
                    }
                }
            }
            for (Companybank companybankCollectionNewCompanybank : companybankCollectionNew) {
                if (!companybankCollectionOld.contains(companybankCollectionNewCompanybank)) {
                    Companyemployee oldCompanyemployeeOfCompanybankCollectionNewCompanybank = companybankCollectionNewCompanybank.getCompanyemployee();
                    companybankCollectionNewCompanybank.setCompanyemployee(companyemployee);
                    companybankCollectionNewCompanybank = em.merge(companybankCollectionNewCompanybank);
                    if (oldCompanyemployeeOfCompanybankCollectionNewCompanybank != null && !oldCompanyemployeeOfCompanybankCollectionNewCompanybank.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanybankCollectionNewCompanybank.getCompanybankCollection().remove(companybankCollectionNewCompanybank);
                        oldCompanyemployeeOfCompanybankCollectionNewCompanybank = em.merge(oldCompanyemployeeOfCompanybankCollectionNewCompanybank);
                    }
                }
            }
            for (Companybank companybankCollection1NewCompanybank : companybankCollection1New) {
                if (!companybankCollection1Old.contains(companybankCollection1NewCompanybank)) {
                    Companyemployee oldCompanyemployee1OfCompanybankCollection1NewCompanybank = companybankCollection1NewCompanybank.getCompanyemployee1();
                    companybankCollection1NewCompanybank.setCompanyemployee1(companyemployee);
                    companybankCollection1NewCompanybank = em.merge(companybankCollection1NewCompanybank);
                    if (oldCompanyemployee1OfCompanybankCollection1NewCompanybank != null && !oldCompanyemployee1OfCompanybankCollection1NewCompanybank.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanybankCollection1NewCompanybank.getCompanybankCollection1().remove(companybankCollection1NewCompanybank);
                        oldCompanyemployee1OfCompanybankCollection1NewCompanybank = em.merge(oldCompanyemployee1OfCompanybankCollection1NewCompanybank);
                    }
                }
            }
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate : crmmessagechanneltemplateCollectionNew) {
                if (!crmmessagechanneltemplateCollectionOld.contains(crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate)) {
                    Companyemployee oldCompanyemployeeOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate = crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate.getCompanyemployee();
                    crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate.setCompanyemployee(companyemployee);
                    crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate = em.merge(crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate);
                    if (oldCompanyemployeeOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate != null && !oldCompanyemployeeOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate.getCrmmessagechanneltemplateCollection().remove(crmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate);
                        oldCompanyemployeeOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate = em.merge(oldCompanyemployeeOfCrmmessagechanneltemplateCollectionNewCrmmessagechanneltemplate);
                    }
                }
            }
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate : crmmessagechanneltemplateCollection1New) {
                if (!crmmessagechanneltemplateCollection1Old.contains(crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate)) {
                    Companyemployee oldCompanyemployee1OfCrmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate = crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate.getCompanyemployee1();
                    crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate.setCompanyemployee1(companyemployee);
                    crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate = em.merge(crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate);
                    if (oldCompanyemployee1OfCrmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate != null && !oldCompanyemployee1OfCrmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate.getCrmmessagechanneltemplateCollection1().remove(crmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate);
                        oldCompanyemployee1OfCrmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate = em.merge(oldCompanyemployee1OfCrmmessagechanneltemplateCollection1NewCrmmessagechanneltemplate);
                    }
                }
            }
            for (Crmprojectprops crmprojectpropsCollectionNewCrmprojectprops : crmprojectpropsCollectionNew) {
                if (!crmprojectpropsCollectionOld.contains(crmprojectpropsCollectionNewCrmprojectprops)) {
                    Companyemployee oldCompanyemployeeOfCrmprojectpropsCollectionNewCrmprojectprops = crmprojectpropsCollectionNewCrmprojectprops.getCompanyemployee();
                    crmprojectpropsCollectionNewCrmprojectprops.setCompanyemployee(companyemployee);
                    crmprojectpropsCollectionNewCrmprojectprops = em.merge(crmprojectpropsCollectionNewCrmprojectprops);
                    if (oldCompanyemployeeOfCrmprojectpropsCollectionNewCrmprojectprops != null && !oldCompanyemployeeOfCrmprojectpropsCollectionNewCrmprojectprops.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmprojectpropsCollectionNewCrmprojectprops.getCrmprojectpropsCollection().remove(crmprojectpropsCollectionNewCrmprojectprops);
                        oldCompanyemployeeOfCrmprojectpropsCollectionNewCrmprojectprops = em.merge(oldCompanyemployeeOfCrmprojectpropsCollectionNewCrmprojectprops);
                    }
                }
            }
            for (Crmprojectprops crmprojectpropsCollection1NewCrmprojectprops : crmprojectpropsCollection1New) {
                if (!crmprojectpropsCollection1Old.contains(crmprojectpropsCollection1NewCrmprojectprops)) {
                    Companyemployee oldCompanyemployee1OfCrmprojectpropsCollection1NewCrmprojectprops = crmprojectpropsCollection1NewCrmprojectprops.getCompanyemployee1();
                    crmprojectpropsCollection1NewCrmprojectprops.setCompanyemployee1(companyemployee);
                    crmprojectpropsCollection1NewCrmprojectprops = em.merge(crmprojectpropsCollection1NewCrmprojectprops);
                    if (oldCompanyemployee1OfCrmprojectpropsCollection1NewCrmprojectprops != null && !oldCompanyemployee1OfCrmprojectpropsCollection1NewCrmprojectprops.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmprojectpropsCollection1NewCrmprojectprops.getCrmprojectpropsCollection1().remove(crmprojectpropsCollection1NewCrmprojectprops);
                        oldCompanyemployee1OfCrmprojectpropsCollection1NewCrmprojectprops = em.merge(oldCompanyemployee1OfCrmprojectpropsCollection1NewCrmprojectprops);
                    }
                }
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollectionNewCrmcampaignreceiver : crmcampaignreceiverCollectionNew) {
                if (!crmcampaignreceiverCollectionOld.contains(crmcampaignreceiverCollectionNewCrmcampaignreceiver)) {
                    Companyemployee oldCompanyemployeeOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver = crmcampaignreceiverCollectionNewCrmcampaignreceiver.getCompanyemployee();
                    crmcampaignreceiverCollectionNewCrmcampaignreceiver.setCompanyemployee(companyemployee);
                    crmcampaignreceiverCollectionNewCrmcampaignreceiver = em.merge(crmcampaignreceiverCollectionNewCrmcampaignreceiver);
                    if (oldCompanyemployeeOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver != null && !oldCompanyemployeeOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver.getCrmcampaignreceiverCollection().remove(crmcampaignreceiverCollectionNewCrmcampaignreceiver);
                        oldCompanyemployeeOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver = em.merge(oldCompanyemployeeOfCrmcampaignreceiverCollectionNewCrmcampaignreceiver);
                    }
                }
            }
            for (Crmcampaignreceiver crmcampaignreceiverCollection1NewCrmcampaignreceiver : crmcampaignreceiverCollection1New) {
                if (!crmcampaignreceiverCollection1Old.contains(crmcampaignreceiverCollection1NewCrmcampaignreceiver)) {
                    Companyemployee oldCompanyemployee1OfCrmcampaignreceiverCollection1NewCrmcampaignreceiver = crmcampaignreceiverCollection1NewCrmcampaignreceiver.getCompanyemployee1();
                    crmcampaignreceiverCollection1NewCrmcampaignreceiver.setCompanyemployee1(companyemployee);
                    crmcampaignreceiverCollection1NewCrmcampaignreceiver = em.merge(crmcampaignreceiverCollection1NewCrmcampaignreceiver);
                    if (oldCompanyemployee1OfCrmcampaignreceiverCollection1NewCrmcampaignreceiver != null && !oldCompanyemployee1OfCrmcampaignreceiverCollection1NewCrmcampaignreceiver.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmcampaignreceiverCollection1NewCrmcampaignreceiver.getCrmcampaignreceiverCollection1().remove(crmcampaignreceiverCollection1NewCrmcampaignreceiver);
                        oldCompanyemployee1OfCrmcampaignreceiverCollection1NewCrmcampaignreceiver = em.merge(oldCompanyemployee1OfCrmcampaignreceiverCollection1NewCrmcampaignreceiver);
                    }
                }
            }
            for (Crmworkordersettings crmworkordersettingsCollectionNewCrmworkordersettings : crmworkordersettingsCollectionNew) {
                if (!crmworkordersettingsCollectionOld.contains(crmworkordersettingsCollectionNewCrmworkordersettings)) {
                    Companyemployee oldCompanyemployeeOfCrmworkordersettingsCollectionNewCrmworkordersettings = crmworkordersettingsCollectionNewCrmworkordersettings.getCompanyemployee();
                    crmworkordersettingsCollectionNewCrmworkordersettings.setCompanyemployee(companyemployee);
                    crmworkordersettingsCollectionNewCrmworkordersettings = em.merge(crmworkordersettingsCollectionNewCrmworkordersettings);
                    if (oldCompanyemployeeOfCrmworkordersettingsCollectionNewCrmworkordersettings != null && !oldCompanyemployeeOfCrmworkordersettingsCollectionNewCrmworkordersettings.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmworkordersettingsCollectionNewCrmworkordersettings.getCrmworkordersettingsCollection().remove(crmworkordersettingsCollectionNewCrmworkordersettings);
                        oldCompanyemployeeOfCrmworkordersettingsCollectionNewCrmworkordersettings = em.merge(oldCompanyemployeeOfCrmworkordersettingsCollectionNewCrmworkordersettings);
                    }
                }
            }
            for (Productattachments productattachmentsCollectionNewProductattachments : productattachmentsCollectionNew) {
                if (!productattachmentsCollectionOld.contains(productattachmentsCollectionNewProductattachments)) {
                    Companyemployee oldCompanyemployeeOfProductattachmentsCollectionNewProductattachments = productattachmentsCollectionNewProductattachments.getCompanyemployee();
                    productattachmentsCollectionNewProductattachments.setCompanyemployee(companyemployee);
                    productattachmentsCollectionNewProductattachments = em.merge(productattachmentsCollectionNewProductattachments);
                    if (oldCompanyemployeeOfProductattachmentsCollectionNewProductattachments != null && !oldCompanyemployeeOfProductattachmentsCollectionNewProductattachments.equals(companyemployee)) {
                        oldCompanyemployeeOfProductattachmentsCollectionNewProductattachments.getProductattachmentsCollection().remove(productattachmentsCollectionNewProductattachments);
                        oldCompanyemployeeOfProductattachmentsCollectionNewProductattachments = em.merge(oldCompanyemployeeOfProductattachmentsCollectionNewProductattachments);
                    }
                }
            }
            for (Productattachments productattachmentsCollection1NewProductattachments : productattachmentsCollection1New) {
                if (!productattachmentsCollection1Old.contains(productattachmentsCollection1NewProductattachments)) {
                    Companyemployee oldCompanyemployee1OfProductattachmentsCollection1NewProductattachments = productattachmentsCollection1NewProductattachments.getCompanyemployee1();
                    productattachmentsCollection1NewProductattachments.setCompanyemployee1(companyemployee);
                    productattachmentsCollection1NewProductattachments = em.merge(productattachmentsCollection1NewProductattachments);
                    if (oldCompanyemployee1OfProductattachmentsCollection1NewProductattachments != null && !oldCompanyemployee1OfProductattachmentsCollection1NewProductattachments.equals(companyemployee)) {
                        oldCompanyemployee1OfProductattachmentsCollection1NewProductattachments.getProductattachmentsCollection1().remove(productattachmentsCollection1NewProductattachments);
                        oldCompanyemployee1OfProductattachmentsCollection1NewProductattachments = em.merge(oldCompanyemployee1OfProductattachmentsCollection1NewProductattachments);
                    }
                }
            }
            for (Productattachments productattachmentsCollection2NewProductattachments : productattachmentsCollection2New) {
                if (!productattachmentsCollection2Old.contains(productattachmentsCollection2NewProductattachments)) {
                    Companyemployee oldCompanyemployee2OfProductattachmentsCollection2NewProductattachments = productattachmentsCollection2NewProductattachments.getCompanyemployee2();
                    productattachmentsCollection2NewProductattachments.setCompanyemployee2(companyemployee);
                    productattachmentsCollection2NewProductattachments = em.merge(productattachmentsCollection2NewProductattachments);
                    if (oldCompanyemployee2OfProductattachmentsCollection2NewProductattachments != null && !oldCompanyemployee2OfProductattachmentsCollection2NewProductattachments.equals(companyemployee)) {
                        oldCompanyemployee2OfProductattachmentsCollection2NewProductattachments.getProductattachmentsCollection2().remove(productattachmentsCollection2NewProductattachments);
                        oldCompanyemployee2OfProductattachmentsCollection2NewProductattachments = em.merge(oldCompanyemployee2OfProductattachmentsCollection2NewProductattachments);
                    }
                }
            }
            for (Crmcampaignstatus crmcampaignstatusCollectionNewCrmcampaignstatus : crmcampaignstatusCollectionNew) {
                if (!crmcampaignstatusCollectionOld.contains(crmcampaignstatusCollectionNewCrmcampaignstatus)) {
                    Companyemployee oldCompanyemployeeOfCrmcampaignstatusCollectionNewCrmcampaignstatus = crmcampaignstatusCollectionNewCrmcampaignstatus.getCompanyemployee();
                    crmcampaignstatusCollectionNewCrmcampaignstatus.setCompanyemployee(companyemployee);
                    crmcampaignstatusCollectionNewCrmcampaignstatus = em.merge(crmcampaignstatusCollectionNewCrmcampaignstatus);
                    if (oldCompanyemployeeOfCrmcampaignstatusCollectionNewCrmcampaignstatus != null && !oldCompanyemployeeOfCrmcampaignstatusCollectionNewCrmcampaignstatus.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmcampaignstatusCollectionNewCrmcampaignstatus.getCrmcampaignstatusCollection().remove(crmcampaignstatusCollectionNewCrmcampaignstatus);
                        oldCompanyemployeeOfCrmcampaignstatusCollectionNewCrmcampaignstatus = em.merge(oldCompanyemployeeOfCrmcampaignstatusCollectionNewCrmcampaignstatus);
                    }
                }
            }
            for (Crmcampaignstatus crmcampaignstatusCollection1NewCrmcampaignstatus : crmcampaignstatusCollection1New) {
                if (!crmcampaignstatusCollection1Old.contains(crmcampaignstatusCollection1NewCrmcampaignstatus)) {
                    Companyemployee oldCompanyemployee1OfCrmcampaignstatusCollection1NewCrmcampaignstatus = crmcampaignstatusCollection1NewCrmcampaignstatus.getCompanyemployee1();
                    crmcampaignstatusCollection1NewCrmcampaignstatus.setCompanyemployee1(companyemployee);
                    crmcampaignstatusCollection1NewCrmcampaignstatus = em.merge(crmcampaignstatusCollection1NewCrmcampaignstatus);
                    if (oldCompanyemployee1OfCrmcampaignstatusCollection1NewCrmcampaignstatus != null && !oldCompanyemployee1OfCrmcampaignstatusCollection1NewCrmcampaignstatus.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmcampaignstatusCollection1NewCrmcampaignstatus.getCrmcampaignstatusCollection1().remove(crmcampaignstatusCollection1NewCrmcampaignstatus);
                        oldCompanyemployee1OfCrmcampaignstatusCollection1NewCrmcampaignstatus = em.merge(oldCompanyemployee1OfCrmcampaignstatusCollection1NewCrmcampaignstatus);
                    }
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollectionNewCrmcampaignposition : crmcampaignpositionCollectionNew) {
                if (!crmcampaignpositionCollectionOld.contains(crmcampaignpositionCollectionNewCrmcampaignposition)) {
                    Companyemployee oldCompanyemployeeOfCrmcampaignpositionCollectionNewCrmcampaignposition = crmcampaignpositionCollectionNewCrmcampaignposition.getCompanyemployee();
                    crmcampaignpositionCollectionNewCrmcampaignposition.setCompanyemployee(companyemployee);
                    crmcampaignpositionCollectionNewCrmcampaignposition = em.merge(crmcampaignpositionCollectionNewCrmcampaignposition);
                    if (oldCompanyemployeeOfCrmcampaignpositionCollectionNewCrmcampaignposition != null && !oldCompanyemployeeOfCrmcampaignpositionCollectionNewCrmcampaignposition.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmcampaignpositionCollectionNewCrmcampaignposition.getCrmcampaignpositionCollection().remove(crmcampaignpositionCollectionNewCrmcampaignposition);
                        oldCompanyemployeeOfCrmcampaignpositionCollectionNewCrmcampaignposition = em.merge(oldCompanyemployeeOfCrmcampaignpositionCollectionNewCrmcampaignposition);
                    }
                }
            }
            for (Crmcampaignposition crmcampaignpositionCollection1NewCrmcampaignposition : crmcampaignpositionCollection1New) {
                if (!crmcampaignpositionCollection1Old.contains(crmcampaignpositionCollection1NewCrmcampaignposition)) {
                    Companyemployee oldCompanyemployee1OfCrmcampaignpositionCollection1NewCrmcampaignposition = crmcampaignpositionCollection1NewCrmcampaignposition.getCompanyemployee1();
                    crmcampaignpositionCollection1NewCrmcampaignposition.setCompanyemployee1(companyemployee);
                    crmcampaignpositionCollection1NewCrmcampaignposition = em.merge(crmcampaignpositionCollection1NewCrmcampaignposition);
                    if (oldCompanyemployee1OfCrmcampaignpositionCollection1NewCrmcampaignposition != null && !oldCompanyemployee1OfCrmcampaignpositionCollection1NewCrmcampaignposition.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmcampaignpositionCollection1NewCrmcampaignposition.getCrmcampaignpositionCollection1().remove(crmcampaignpositionCollection1NewCrmcampaignposition);
                        oldCompanyemployee1OfCrmcampaignpositionCollection1NewCrmcampaignposition = em.merge(oldCompanyemployee1OfCrmcampaignpositionCollection1NewCrmcampaignposition);
                    }
                }
            }
            for (Crmforumteammembers crmforumteammembersCollectionNewCrmforumteammembers : crmforumteammembersCollectionNew) {
                if (!crmforumteammembersCollectionOld.contains(crmforumteammembersCollectionNewCrmforumteammembers)) {
                    Companyemployee oldCompanyemployeeOfCrmforumteammembersCollectionNewCrmforumteammembers = crmforumteammembersCollectionNewCrmforumteammembers.getCompanyemployee();
                    crmforumteammembersCollectionNewCrmforumteammembers.setCompanyemployee(companyemployee);
                    crmforumteammembersCollectionNewCrmforumteammembers = em.merge(crmforumteammembersCollectionNewCrmforumteammembers);
                    if (oldCompanyemployeeOfCrmforumteammembersCollectionNewCrmforumteammembers != null && !oldCompanyemployeeOfCrmforumteammembersCollectionNewCrmforumteammembers.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmforumteammembersCollectionNewCrmforumteammembers.getCrmforumteammembersCollection().remove(crmforumteammembersCollectionNewCrmforumteammembers);
                        oldCompanyemployeeOfCrmforumteammembersCollectionNewCrmforumteammembers = em.merge(oldCompanyemployeeOfCrmforumteammembersCollectionNewCrmforumteammembers);
                    }
                }
            }
            for (Customercontacts customercontactsCollectionNewCustomercontacts : customercontactsCollectionNew) {
                if (!customercontactsCollectionOld.contains(customercontactsCollectionNewCustomercontacts)) {
                    Companyemployee oldCompanyemployeeOfCustomercontactsCollectionNewCustomercontacts = customercontactsCollectionNewCustomercontacts.getCompanyemployee();
                    customercontactsCollectionNewCustomercontacts.setCompanyemployee(companyemployee);
                    customercontactsCollectionNewCustomercontacts = em.merge(customercontactsCollectionNewCustomercontacts);
                    if (oldCompanyemployeeOfCustomercontactsCollectionNewCustomercontacts != null && !oldCompanyemployeeOfCustomercontactsCollectionNewCustomercontacts.equals(companyemployee)) {
                        oldCompanyemployeeOfCustomercontactsCollectionNewCustomercontacts.getCustomercontactsCollection().remove(customercontactsCollectionNewCustomercontacts);
                        oldCompanyemployeeOfCustomercontactsCollectionNewCustomercontacts = em.merge(oldCompanyemployeeOfCustomercontactsCollectionNewCustomercontacts);
                    }
                }
            }
            for (Customercontacts customercontactsCollection1NewCustomercontacts : customercontactsCollection1New) {
                if (!customercontactsCollection1Old.contains(customercontactsCollection1NewCustomercontacts)) {
                    Companyemployee oldCompanyemployee1OfCustomercontactsCollection1NewCustomercontacts = customercontactsCollection1NewCustomercontacts.getCompanyemployee1();
                    customercontactsCollection1NewCustomercontacts.setCompanyemployee1(companyemployee);
                    customercontactsCollection1NewCustomercontacts = em.merge(customercontactsCollection1NewCustomercontacts);
                    if (oldCompanyemployee1OfCustomercontactsCollection1NewCustomercontacts != null && !oldCompanyemployee1OfCustomercontactsCollection1NewCustomercontacts.equals(companyemployee)) {
                        oldCompanyemployee1OfCustomercontactsCollection1NewCustomercontacts.getCustomercontactsCollection1().remove(customercontactsCollection1NewCustomercontacts);
                        oldCompanyemployee1OfCustomercontactsCollection1NewCustomercontacts = em.merge(oldCompanyemployee1OfCustomercontactsCollection1NewCustomercontacts);
                    }
                }
            }
            for (Appointment appointmentCollectionNewAppointment : appointmentCollectionNew) {
                if (!appointmentCollectionOld.contains(appointmentCollectionNewAppointment)) {
                    Companyemployee oldCompanyemployeeOfAppointmentCollectionNewAppointment = appointmentCollectionNewAppointment.getCompanyemployee();
                    appointmentCollectionNewAppointment.setCompanyemployee(companyemployee);
                    appointmentCollectionNewAppointment = em.merge(appointmentCollectionNewAppointment);
                    if (oldCompanyemployeeOfAppointmentCollectionNewAppointment != null && !oldCompanyemployeeOfAppointmentCollectionNewAppointment.equals(companyemployee)) {
                        oldCompanyemployeeOfAppointmentCollectionNewAppointment.getAppointmentCollection().remove(appointmentCollectionNewAppointment);
                        oldCompanyemployeeOfAppointmentCollectionNewAppointment = em.merge(oldCompanyemployeeOfAppointmentCollectionNewAppointment);
                    }
                }
            }
            for (Appointment appointmentCollection1NewAppointment : appointmentCollection1New) {
                if (!appointmentCollection1Old.contains(appointmentCollection1NewAppointment)) {
                    Companyemployee oldCompanyemployee1OfAppointmentCollection1NewAppointment = appointmentCollection1NewAppointment.getCompanyemployee1();
                    appointmentCollection1NewAppointment.setCompanyemployee1(companyemployee);
                    appointmentCollection1NewAppointment = em.merge(appointmentCollection1NewAppointment);
                    if (oldCompanyemployee1OfAppointmentCollection1NewAppointment != null && !oldCompanyemployee1OfAppointmentCollection1NewAppointment.equals(companyemployee)) {
                        oldCompanyemployee1OfAppointmentCollection1NewAppointment.getAppointmentCollection1().remove(appointmentCollection1NewAppointment);
                        oldCompanyemployee1OfAppointmentCollection1NewAppointment = em.merge(oldCompanyemployee1OfAppointmentCollection1NewAppointment);
                    }
                }
            }
            for (Appointment appointmentCollection2NewAppointment : appointmentCollection2New) {
                if (!appointmentCollection2Old.contains(appointmentCollection2NewAppointment)) {
                    Companyemployee oldCompanyemployee2OfAppointmentCollection2NewAppointment = appointmentCollection2NewAppointment.getCompanyemployee2();
                    appointmentCollection2NewAppointment.setCompanyemployee2(companyemployee);
                    appointmentCollection2NewAppointment = em.merge(appointmentCollection2NewAppointment);
                    if (oldCompanyemployee2OfAppointmentCollection2NewAppointment != null && !oldCompanyemployee2OfAppointmentCollection2NewAppointment.equals(companyemployee)) {
                        oldCompanyemployee2OfAppointmentCollection2NewAppointment.getAppointmentCollection2().remove(appointmentCollection2NewAppointment);
                        oldCompanyemployee2OfAppointmentCollection2NewAppointment = em.merge(oldCompanyemployee2OfAppointmentCollection2NewAppointment);
                    }
                }
            }
            for (Appointment appointmentCollection3NewAppointment : appointmentCollection3New) {
                if (!appointmentCollection3Old.contains(appointmentCollection3NewAppointment)) {
                    Companyemployee oldCompanyemployee3OfAppointmentCollection3NewAppointment = appointmentCollection3NewAppointment.getCompanyemployee3();
                    appointmentCollection3NewAppointment.setCompanyemployee3(companyemployee);
                    appointmentCollection3NewAppointment = em.merge(appointmentCollection3NewAppointment);
                    if (oldCompanyemployee3OfAppointmentCollection3NewAppointment != null && !oldCompanyemployee3OfAppointmentCollection3NewAppointment.equals(companyemployee)) {
                        oldCompanyemployee3OfAppointmentCollection3NewAppointment.getAppointmentCollection3().remove(appointmentCollection3NewAppointment);
                        oldCompanyemployee3OfAppointmentCollection3NewAppointment = em.merge(oldCompanyemployee3OfAppointmentCollection3NewAppointment);
                    }
                }
            }
            for (Companydepartment companydepartmentCollectionNewCompanydepartment : companydepartmentCollectionNew) {
                if (!companydepartmentCollectionOld.contains(companydepartmentCollectionNewCompanydepartment)) {
                    Companyemployee oldCompanyemployeeOfCompanydepartmentCollectionNewCompanydepartment = companydepartmentCollectionNewCompanydepartment.getCompanyemployee();
                    companydepartmentCollectionNewCompanydepartment.setCompanyemployee(companyemployee);
                    companydepartmentCollectionNewCompanydepartment = em.merge(companydepartmentCollectionNewCompanydepartment);
                    if (oldCompanyemployeeOfCompanydepartmentCollectionNewCompanydepartment != null && !oldCompanyemployeeOfCompanydepartmentCollectionNewCompanydepartment.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanydepartmentCollectionNewCompanydepartment.getCompanydepartmentCollection().remove(companydepartmentCollectionNewCompanydepartment);
                        oldCompanyemployeeOfCompanydepartmentCollectionNewCompanydepartment = em.merge(oldCompanyemployeeOfCompanydepartmentCollectionNewCompanydepartment);
                    }
                }
            }
            for (Companydepartment companydepartmentCollection1NewCompanydepartment : companydepartmentCollection1New) {
                if (!companydepartmentCollection1Old.contains(companydepartmentCollection1NewCompanydepartment)) {
                    Companyemployee oldCompanyemployee1OfCompanydepartmentCollection1NewCompanydepartment = companydepartmentCollection1NewCompanydepartment.getCompanyemployee1();
                    companydepartmentCollection1NewCompanydepartment.setCompanyemployee1(companyemployee);
                    companydepartmentCollection1NewCompanydepartment = em.merge(companydepartmentCollection1NewCompanydepartment);
                    if (oldCompanyemployee1OfCompanydepartmentCollection1NewCompanydepartment != null && !oldCompanyemployee1OfCompanydepartmentCollection1NewCompanydepartment.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanydepartmentCollection1NewCompanydepartment.getCompanydepartmentCollection1().remove(companydepartmentCollection1NewCompanydepartment);
                        oldCompanyemployee1OfCompanydepartmentCollection1NewCompanydepartment = em.merge(oldCompanyemployee1OfCompanydepartmentCollection1NewCompanydepartment);
                    }
                }
            }
            for (Crmusermodules crmusermodulesCollectionNewCrmusermodules : crmusermodulesCollectionNew) {
                if (!crmusermodulesCollectionOld.contains(crmusermodulesCollectionNewCrmusermodules)) {
                    Companyemployee oldCompanyemployeeOfCrmusermodulesCollectionNewCrmusermodules = crmusermodulesCollectionNewCrmusermodules.getCompanyemployee();
                    crmusermodulesCollectionNewCrmusermodules.setCompanyemployee(companyemployee);
                    crmusermodulesCollectionNewCrmusermodules = em.merge(crmusermodulesCollectionNewCrmusermodules);
                    if (oldCompanyemployeeOfCrmusermodulesCollectionNewCrmusermodules != null && !oldCompanyemployeeOfCrmusermodulesCollectionNewCrmusermodules.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmusermodulesCollectionNewCrmusermodules.getCrmusermodulesCollection().remove(crmusermodulesCollectionNewCrmusermodules);
                        oldCompanyemployeeOfCrmusermodulesCollectionNewCrmusermodules = em.merge(oldCompanyemployeeOfCrmusermodulesCollectionNewCrmusermodules);
                    }
                }
            }
            for (Crmusermodules crmusermodulesCollection1NewCrmusermodules : crmusermodulesCollection1New) {
                if (!crmusermodulesCollection1Old.contains(crmusermodulesCollection1NewCrmusermodules)) {
                    Companyemployee oldCompanyemployee1OfCrmusermodulesCollection1NewCrmusermodules = crmusermodulesCollection1NewCrmusermodules.getCompanyemployee1();
                    crmusermodulesCollection1NewCrmusermodules.setCompanyemployee1(companyemployee);
                    crmusermodulesCollection1NewCrmusermodules = em.merge(crmusermodulesCollection1NewCrmusermodules);
                    if (oldCompanyemployee1OfCrmusermodulesCollection1NewCrmusermodules != null && !oldCompanyemployee1OfCrmusermodulesCollection1NewCrmusermodules.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmusermodulesCollection1NewCrmusermodules.getCrmusermodulesCollection1().remove(crmusermodulesCollection1NewCrmusermodules);
                        oldCompanyemployee1OfCrmusermodulesCollection1NewCrmusermodules = em.merge(oldCompanyemployee1OfCrmusermodulesCollection1NewCrmusermodules);
                    }
                }
            }
            for (Crmexpense crmexpenseCollectionNewCrmexpense : crmexpenseCollectionNew) {
                if (!crmexpenseCollectionOld.contains(crmexpenseCollectionNewCrmexpense)) {
                    Companyemployee oldCompanyemployeeOfCrmexpenseCollectionNewCrmexpense = crmexpenseCollectionNewCrmexpense.getCompanyemployee();
                    crmexpenseCollectionNewCrmexpense.setCompanyemployee(companyemployee);
                    crmexpenseCollectionNewCrmexpense = em.merge(crmexpenseCollectionNewCrmexpense);
                    if (oldCompanyemployeeOfCrmexpenseCollectionNewCrmexpense != null && !oldCompanyemployeeOfCrmexpenseCollectionNewCrmexpense.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmexpenseCollectionNewCrmexpense.getCrmexpenseCollection().remove(crmexpenseCollectionNewCrmexpense);
                        oldCompanyemployeeOfCrmexpenseCollectionNewCrmexpense = em.merge(oldCompanyemployeeOfCrmexpenseCollectionNewCrmexpense);
                    }
                }
            }
            for (Crmexpense crmexpenseCollection1NewCrmexpense : crmexpenseCollection1New) {
                if (!crmexpenseCollection1Old.contains(crmexpenseCollection1NewCrmexpense)) {
                    Companyemployee oldCompanyemployee1OfCrmexpenseCollection1NewCrmexpense = crmexpenseCollection1NewCrmexpense.getCompanyemployee1();
                    crmexpenseCollection1NewCrmexpense.setCompanyemployee1(companyemployee);
                    crmexpenseCollection1NewCrmexpense = em.merge(crmexpenseCollection1NewCrmexpense);
                    if (oldCompanyemployee1OfCrmexpenseCollection1NewCrmexpense != null && !oldCompanyemployee1OfCrmexpenseCollection1NewCrmexpense.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmexpenseCollection1NewCrmexpense.getCrmexpenseCollection1().remove(crmexpenseCollection1NewCrmexpense);
                        oldCompanyemployee1OfCrmexpenseCollection1NewCrmexpense = em.merge(oldCompanyemployee1OfCrmexpenseCollection1NewCrmexpense);
                    }
                }
            }
            for (Crmworkorder crmworkorderCollectionNewCrmworkorder : crmworkorderCollectionNew) {
                if (!crmworkorderCollectionOld.contains(crmworkorderCollectionNewCrmworkorder)) {
                    Companyemployee oldCompanyemployeeOfCrmworkorderCollectionNewCrmworkorder = crmworkorderCollectionNewCrmworkorder.getCompanyemployee();
                    crmworkorderCollectionNewCrmworkorder.setCompanyemployee(companyemployee);
                    crmworkorderCollectionNewCrmworkorder = em.merge(crmworkorderCollectionNewCrmworkorder);
                    if (oldCompanyemployeeOfCrmworkorderCollectionNewCrmworkorder != null && !oldCompanyemployeeOfCrmworkorderCollectionNewCrmworkorder.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmworkorderCollectionNewCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionNewCrmworkorder);
                        oldCompanyemployeeOfCrmworkorderCollectionNewCrmworkorder = em.merge(oldCompanyemployeeOfCrmworkorderCollectionNewCrmworkorder);
                    }
                }
            }
            for (Crmworkorder crmworkorderCollection1NewCrmworkorder : crmworkorderCollection1New) {
                if (!crmworkorderCollection1Old.contains(crmworkorderCollection1NewCrmworkorder)) {
                    Companyemployee oldCompanyemployee1OfCrmworkorderCollection1NewCrmworkorder = crmworkorderCollection1NewCrmworkorder.getCompanyemployee1();
                    crmworkorderCollection1NewCrmworkorder.setCompanyemployee1(companyemployee);
                    crmworkorderCollection1NewCrmworkorder = em.merge(crmworkorderCollection1NewCrmworkorder);
                    if (oldCompanyemployee1OfCrmworkorderCollection1NewCrmworkorder != null && !oldCompanyemployee1OfCrmworkorderCollection1NewCrmworkorder.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmworkorderCollection1NewCrmworkorder.getCrmworkorderCollection1().remove(crmworkorderCollection1NewCrmworkorder);
                        oldCompanyemployee1OfCrmworkorderCollection1NewCrmworkorder = em.merge(oldCompanyemployee1OfCrmworkorderCollection1NewCrmworkorder);
                    }
                }
            }
            for (Crmcampaigndocs crmcampaigndocsCollectionNewCrmcampaigndocs : crmcampaigndocsCollectionNew) {
                if (!crmcampaigndocsCollectionOld.contains(crmcampaigndocsCollectionNewCrmcampaigndocs)) {
                    Companyemployee oldCompanyemployeeOfCrmcampaigndocsCollectionNewCrmcampaigndocs = crmcampaigndocsCollectionNewCrmcampaigndocs.getCompanyemployee();
                    crmcampaigndocsCollectionNewCrmcampaigndocs.setCompanyemployee(companyemployee);
                    crmcampaigndocsCollectionNewCrmcampaigndocs = em.merge(crmcampaigndocsCollectionNewCrmcampaigndocs);
                    if (oldCompanyemployeeOfCrmcampaigndocsCollectionNewCrmcampaigndocs != null && !oldCompanyemployeeOfCrmcampaigndocsCollectionNewCrmcampaigndocs.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmcampaigndocsCollectionNewCrmcampaigndocs.getCrmcampaigndocsCollection().remove(crmcampaigndocsCollectionNewCrmcampaigndocs);
                        oldCompanyemployeeOfCrmcampaigndocsCollectionNewCrmcampaigndocs = em.merge(oldCompanyemployeeOfCrmcampaigndocsCollectionNewCrmcampaigndocs);
                    }
                }
            }
            for (Crmcampaigndocs crmcampaigndocsCollection1NewCrmcampaigndocs : crmcampaigndocsCollection1New) {
                if (!crmcampaigndocsCollection1Old.contains(crmcampaigndocsCollection1NewCrmcampaigndocs)) {
                    Companyemployee oldCompanyemployee1OfCrmcampaigndocsCollection1NewCrmcampaigndocs = crmcampaigndocsCollection1NewCrmcampaigndocs.getCompanyemployee1();
                    crmcampaigndocsCollection1NewCrmcampaigndocs.setCompanyemployee1(companyemployee);
                    crmcampaigndocsCollection1NewCrmcampaigndocs = em.merge(crmcampaigndocsCollection1NewCrmcampaigndocs);
                    if (oldCompanyemployee1OfCrmcampaigndocsCollection1NewCrmcampaigndocs != null && !oldCompanyemployee1OfCrmcampaigndocsCollection1NewCrmcampaigndocs.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmcampaigndocsCollection1NewCrmcampaigndocs.getCrmcampaigndocsCollection1().remove(crmcampaigndocsCollection1NewCrmcampaigndocs);
                        oldCompanyemployee1OfCrmcampaigndocsCollection1NewCrmcampaigndocs = em.merge(oldCompanyemployee1OfCrmcampaigndocsCollection1NewCrmcampaigndocs);
                    }
                }
            }
            for (Crmemployeenote crmemployeenoteCollectionNewCrmemployeenote : crmemployeenoteCollectionNew) {
                if (!crmemployeenoteCollectionOld.contains(crmemployeenoteCollectionNewCrmemployeenote)) {
                    Companyemployee oldCompanyemployeeOfCrmemployeenoteCollectionNewCrmemployeenote = crmemployeenoteCollectionNewCrmemployeenote.getCompanyemployee();
                    crmemployeenoteCollectionNewCrmemployeenote.setCompanyemployee(companyemployee);
                    crmemployeenoteCollectionNewCrmemployeenote = em.merge(crmemployeenoteCollectionNewCrmemployeenote);
                    if (oldCompanyemployeeOfCrmemployeenoteCollectionNewCrmemployeenote != null && !oldCompanyemployeeOfCrmemployeenoteCollectionNewCrmemployeenote.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmemployeenoteCollectionNewCrmemployeenote.getCrmemployeenoteCollection().remove(crmemployeenoteCollectionNewCrmemployeenote);
                        oldCompanyemployeeOfCrmemployeenoteCollectionNewCrmemployeenote = em.merge(oldCompanyemployeeOfCrmemployeenoteCollectionNewCrmemployeenote);
                    }
                }
            }
            for (Companycontacts companycontactsCollectionNewCompanycontacts : companycontactsCollectionNew) {
                if (!companycontactsCollectionOld.contains(companycontactsCollectionNewCompanycontacts)) {
                    Companyemployee oldCompanyemployeeOfCompanycontactsCollectionNewCompanycontacts = companycontactsCollectionNewCompanycontacts.getCompanyemployee();
                    companycontactsCollectionNewCompanycontacts.setCompanyemployee(companyemployee);
                    companycontactsCollectionNewCompanycontacts = em.merge(companycontactsCollectionNewCompanycontacts);
                    if (oldCompanyemployeeOfCompanycontactsCollectionNewCompanycontacts != null && !oldCompanyemployeeOfCompanycontactsCollectionNewCompanycontacts.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanycontactsCollectionNewCompanycontacts.getCompanycontactsCollection().remove(companycontactsCollectionNewCompanycontacts);
                        oldCompanyemployeeOfCompanycontactsCollectionNewCompanycontacts = em.merge(oldCompanyemployeeOfCompanycontactsCollectionNewCompanycontacts);
                    }
                }
            }
            for (Companycontacts companycontactsCollection1NewCompanycontacts : companycontactsCollection1New) {
                if (!companycontactsCollection1Old.contains(companycontactsCollection1NewCompanycontacts)) {
                    Companyemployee oldCompanyemployee1OfCompanycontactsCollection1NewCompanycontacts = companycontactsCollection1NewCompanycontacts.getCompanyemployee1();
                    companycontactsCollection1NewCompanycontacts.setCompanyemployee1(companyemployee);
                    companycontactsCollection1NewCompanycontacts = em.merge(companycontactsCollection1NewCompanycontacts);
                    if (oldCompanyemployee1OfCompanycontactsCollection1NewCompanycontacts != null && !oldCompanyemployee1OfCompanycontactsCollection1NewCompanycontacts.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanycontactsCollection1NewCompanycontacts.getCompanycontactsCollection1().remove(companycontactsCollection1NewCompanycontacts);
                        oldCompanyemployee1OfCompanycontactsCollection1NewCompanycontacts = em.merge(oldCompanyemployee1OfCompanycontactsCollection1NewCompanycontacts);
                    }
                }
            }
            for (Companyemployee companyemployeeCollectionNewCompanyemployee : companyemployeeCollectionNew) {
                if (!companyemployeeCollectionOld.contains(companyemployeeCollectionNewCompanyemployee)) {
                    Companyemployee oldCompanyemployeeOfCompanyemployeeCollectionNewCompanyemployee = companyemployeeCollectionNewCompanyemployee.getCompanyemployee();
                    companyemployeeCollectionNewCompanyemployee.setCompanyemployee(companyemployee);
                    companyemployeeCollectionNewCompanyemployee = em.merge(companyemployeeCollectionNewCompanyemployee);
                    if (oldCompanyemployeeOfCompanyemployeeCollectionNewCompanyemployee != null && !oldCompanyemployeeOfCompanyemployeeCollectionNewCompanyemployee.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanyemployeeCollectionNewCompanyemployee.getCompanyemployeeCollection().remove(companyemployeeCollectionNewCompanyemployee);
                        oldCompanyemployeeOfCompanyemployeeCollectionNewCompanyemployee = em.merge(oldCompanyemployeeOfCompanyemployeeCollectionNewCompanyemployee);
                    }
                }
            }
            for (Companyemployee companyemployeeCollection1NewCompanyemployee : companyemployeeCollection1New) {
                if (!companyemployeeCollection1Old.contains(companyemployeeCollection1NewCompanyemployee)) {
                    Companyemployee oldCompanyemployee1OfCompanyemployeeCollection1NewCompanyemployee = companyemployeeCollection1NewCompanyemployee.getCompanyemployee1();
                    companyemployeeCollection1NewCompanyemployee.setCompanyemployee1(companyemployee);
                    companyemployeeCollection1NewCompanyemployee = em.merge(companyemployeeCollection1NewCompanyemployee);
                    if (oldCompanyemployee1OfCompanyemployeeCollection1NewCompanyemployee != null && !oldCompanyemployee1OfCompanyemployeeCollection1NewCompanyemployee.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanyemployeeCollection1NewCompanyemployee.getCompanyemployeeCollection1().remove(companyemployeeCollection1NewCompanyemployee);
                        oldCompanyemployee1OfCompanyemployeeCollection1NewCompanyemployee = em.merge(oldCompanyemployee1OfCompanyemployeeCollection1NewCompanyemployee);
                    }
                }
            }
            for (Companyproducttype companyproducttypeCollectionNewCompanyproducttype : companyproducttypeCollectionNew) {
                if (!companyproducttypeCollectionOld.contains(companyproducttypeCollectionNewCompanyproducttype)) {
                    Companyemployee oldCompanyemployeeOfCompanyproducttypeCollectionNewCompanyproducttype = companyproducttypeCollectionNewCompanyproducttype.getCompanyemployee();
                    companyproducttypeCollectionNewCompanyproducttype.setCompanyemployee(companyemployee);
                    companyproducttypeCollectionNewCompanyproducttype = em.merge(companyproducttypeCollectionNewCompanyproducttype);
                    if (oldCompanyemployeeOfCompanyproducttypeCollectionNewCompanyproducttype != null && !oldCompanyemployeeOfCompanyproducttypeCollectionNewCompanyproducttype.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanyproducttypeCollectionNewCompanyproducttype.getCompanyproducttypeCollection().remove(companyproducttypeCollectionNewCompanyproducttype);
                        oldCompanyemployeeOfCompanyproducttypeCollectionNewCompanyproducttype = em.merge(oldCompanyemployeeOfCompanyproducttypeCollectionNewCompanyproducttype);
                    }
                }
            }
            for (Companyproducttype companyproducttypeCollection1NewCompanyproducttype : companyproducttypeCollection1New) {
                if (!companyproducttypeCollection1Old.contains(companyproducttypeCollection1NewCompanyproducttype)) {
                    Companyemployee oldCompanyemployee1OfCompanyproducttypeCollection1NewCompanyproducttype = companyproducttypeCollection1NewCompanyproducttype.getCompanyemployee1();
                    companyproducttypeCollection1NewCompanyproducttype.setCompanyemployee1(companyemployee);
                    companyproducttypeCollection1NewCompanyproducttype = em.merge(companyproducttypeCollection1NewCompanyproducttype);
                    if (oldCompanyemployee1OfCompanyproducttypeCollection1NewCompanyproducttype != null && !oldCompanyemployee1OfCompanyproducttypeCollection1NewCompanyproducttype.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanyproducttypeCollection1NewCompanyproducttype.getCompanyproducttypeCollection1().remove(companyproducttypeCollection1NewCompanyproducttype);
                        oldCompanyemployee1OfCompanyproducttypeCollection1NewCompanyproducttype = em.merge(oldCompanyemployee1OfCompanyproducttypeCollection1NewCompanyproducttype);
                    }
                }
            }
            for (Crmcampaign crmcampaignCollectionNewCrmcampaign : crmcampaignCollectionNew) {
                if (!crmcampaignCollectionOld.contains(crmcampaignCollectionNewCrmcampaign)) {
                    Companyemployee oldCompanyemployeeOfCrmcampaignCollectionNewCrmcampaign = crmcampaignCollectionNewCrmcampaign.getCompanyemployee();
                    crmcampaignCollectionNewCrmcampaign.setCompanyemployee(companyemployee);
                    crmcampaignCollectionNewCrmcampaign = em.merge(crmcampaignCollectionNewCrmcampaign);
                    if (oldCompanyemployeeOfCrmcampaignCollectionNewCrmcampaign != null && !oldCompanyemployeeOfCrmcampaignCollectionNewCrmcampaign.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmcampaignCollectionNewCrmcampaign.getCrmcampaignCollection().remove(crmcampaignCollectionNewCrmcampaign);
                        oldCompanyemployeeOfCrmcampaignCollectionNewCrmcampaign = em.merge(oldCompanyemployeeOfCrmcampaignCollectionNewCrmcampaign);
                    }
                }
            }
            for (Crmcampaign crmcampaignCollection1NewCrmcampaign : crmcampaignCollection1New) {
                if (!crmcampaignCollection1Old.contains(crmcampaignCollection1NewCrmcampaign)) {
                    Companyemployee oldCompanyemployee1OfCrmcampaignCollection1NewCrmcampaign = crmcampaignCollection1NewCrmcampaign.getCompanyemployee1();
                    crmcampaignCollection1NewCrmcampaign.setCompanyemployee1(companyemployee);
                    crmcampaignCollection1NewCrmcampaign = em.merge(crmcampaignCollection1NewCrmcampaign);
                    if (oldCompanyemployee1OfCrmcampaignCollection1NewCrmcampaign != null && !oldCompanyemployee1OfCrmcampaignCollection1NewCrmcampaign.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmcampaignCollection1NewCrmcampaign.getCrmcampaignCollection1().remove(crmcampaignCollection1NewCrmcampaign);
                        oldCompanyemployee1OfCrmcampaignCollection1NewCrmcampaign = em.merge(oldCompanyemployee1OfCrmcampaignCollection1NewCrmcampaign);
                    }
                }
            }
            for (Crmmessagehistory crmmessagehistoryCollectionNewCrmmessagehistory : crmmessagehistoryCollectionNew) {
                if (!crmmessagehistoryCollectionOld.contains(crmmessagehistoryCollectionNewCrmmessagehistory)) {
                    Companyemployee oldCompanyemployeeOfCrmmessagehistoryCollectionNewCrmmessagehistory = crmmessagehistoryCollectionNewCrmmessagehistory.getCompanyemployee();
                    crmmessagehistoryCollectionNewCrmmessagehistory.setCompanyemployee(companyemployee);
                    crmmessagehistoryCollectionNewCrmmessagehistory = em.merge(crmmessagehistoryCollectionNewCrmmessagehistory);
                    if (oldCompanyemployeeOfCrmmessagehistoryCollectionNewCrmmessagehistory != null && !oldCompanyemployeeOfCrmmessagehistoryCollectionNewCrmmessagehistory.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmmessagehistoryCollectionNewCrmmessagehistory.getCrmmessagehistoryCollection().remove(crmmessagehistoryCollectionNewCrmmessagehistory);
                        oldCompanyemployeeOfCrmmessagehistoryCollectionNewCrmmessagehistory = em.merge(oldCompanyemployeeOfCrmmessagehistoryCollectionNewCrmmessagehistory);
                    }
                }
            }
            for (Crmmessagehistory crmmessagehistoryCollection1NewCrmmessagehistory : crmmessagehistoryCollection1New) {
                if (!crmmessagehistoryCollection1Old.contains(crmmessagehistoryCollection1NewCrmmessagehistory)) {
                    Companyemployee oldCompanyemployee1OfCrmmessagehistoryCollection1NewCrmmessagehistory = crmmessagehistoryCollection1NewCrmmessagehistory.getCompanyemployee1();
                    crmmessagehistoryCollection1NewCrmmessagehistory.setCompanyemployee1(companyemployee);
                    crmmessagehistoryCollection1NewCrmmessagehistory = em.merge(crmmessagehistoryCollection1NewCrmmessagehistory);
                    if (oldCompanyemployee1OfCrmmessagehistoryCollection1NewCrmmessagehistory != null && !oldCompanyemployee1OfCrmmessagehistoryCollection1NewCrmmessagehistory.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmmessagehistoryCollection1NewCrmmessagehistory.getCrmmessagehistoryCollection1().remove(crmmessagehistoryCollection1NewCrmmessagehistory);
                        oldCompanyemployee1OfCrmmessagehistoryCollection1NewCrmmessagehistory = em.merge(oldCompanyemployee1OfCrmmessagehistoryCollection1NewCrmmessagehistory);
                    }
                }
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollectionNewCompanyaccountstackdocs : companyaccountstackdocsCollectionNew) {
                if (!companyaccountstackdocsCollectionOld.contains(companyaccountstackdocsCollectionNewCompanyaccountstackdocs)) {
                    Companyemployee oldCompanyemployeeOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs = companyaccountstackdocsCollectionNewCompanyaccountstackdocs.getCompanyemployee();
                    companyaccountstackdocsCollectionNewCompanyaccountstackdocs.setCompanyemployee(companyemployee);
                    companyaccountstackdocsCollectionNewCompanyaccountstackdocs = em.merge(companyaccountstackdocsCollectionNewCompanyaccountstackdocs);
                    if (oldCompanyemployeeOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs != null && !oldCompanyemployeeOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs.getCompanyaccountstackdocsCollection().remove(companyaccountstackdocsCollectionNewCompanyaccountstackdocs);
                        oldCompanyemployeeOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs = em.merge(oldCompanyemployeeOfCompanyaccountstackdocsCollectionNewCompanyaccountstackdocs);
                    }
                }
            }
            for (Companyaccountstackdocs companyaccountstackdocsCollection1NewCompanyaccountstackdocs : companyaccountstackdocsCollection1New) {
                if (!companyaccountstackdocsCollection1Old.contains(companyaccountstackdocsCollection1NewCompanyaccountstackdocs)) {
                    Companyemployee oldCompanyemployee1OfCompanyaccountstackdocsCollection1NewCompanyaccountstackdocs = companyaccountstackdocsCollection1NewCompanyaccountstackdocs.getCompanyemployee1();
                    companyaccountstackdocsCollection1NewCompanyaccountstackdocs.setCompanyemployee1(companyemployee);
                    companyaccountstackdocsCollection1NewCompanyaccountstackdocs = em.merge(companyaccountstackdocsCollection1NewCompanyaccountstackdocs);
                    if (oldCompanyemployee1OfCompanyaccountstackdocsCollection1NewCompanyaccountstackdocs != null && !oldCompanyemployee1OfCompanyaccountstackdocsCollection1NewCompanyaccountstackdocs.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanyaccountstackdocsCollection1NewCompanyaccountstackdocs.getCompanyaccountstackdocsCollection1().remove(companyaccountstackdocsCollection1NewCompanyaccountstackdocs);
                        oldCompanyemployee1OfCompanyaccountstackdocsCollection1NewCompanyaccountstackdocs = em.merge(oldCompanyemployee1OfCompanyaccountstackdocsCollection1NewCompanyaccountstackdocs);
                    }
                }
            }
            for (Crmlabor crmlaborCollectionNewCrmlabor : crmlaborCollectionNew) {
                if (!crmlaborCollectionOld.contains(crmlaborCollectionNewCrmlabor)) {
                    Companyemployee oldCompanyemployeeOfCrmlaborCollectionNewCrmlabor = crmlaborCollectionNewCrmlabor.getCompanyemployee();
                    crmlaborCollectionNewCrmlabor.setCompanyemployee(companyemployee);
                    crmlaborCollectionNewCrmlabor = em.merge(crmlaborCollectionNewCrmlabor);
                    if (oldCompanyemployeeOfCrmlaborCollectionNewCrmlabor != null && !oldCompanyemployeeOfCrmlaborCollectionNewCrmlabor.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmlaborCollectionNewCrmlabor.getCrmlaborCollection().remove(crmlaborCollectionNewCrmlabor);
                        oldCompanyemployeeOfCrmlaborCollectionNewCrmlabor = em.merge(oldCompanyemployeeOfCrmlaborCollectionNewCrmlabor);
                    }
                }
            }
            for (Crmlabor crmlaborCollection1NewCrmlabor : crmlaborCollection1New) {
                if (!crmlaborCollection1Old.contains(crmlaborCollection1NewCrmlabor)) {
                    Companyemployee oldCompanyemployee1OfCrmlaborCollection1NewCrmlabor = crmlaborCollection1NewCrmlabor.getCompanyemployee1();
                    crmlaborCollection1NewCrmlabor.setCompanyemployee1(companyemployee);
                    crmlaborCollection1NewCrmlabor = em.merge(crmlaborCollection1NewCrmlabor);
                    if (oldCompanyemployee1OfCrmlaborCollection1NewCrmlabor != null && !oldCompanyemployee1OfCrmlaborCollection1NewCrmlabor.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmlaborCollection1NewCrmlabor.getCrmlaborCollection1().remove(crmlaborCollection1NewCrmlabor);
                        oldCompanyemployee1OfCrmlaborCollection1NewCrmlabor = em.merge(oldCompanyemployee1OfCrmlaborCollection1NewCrmlabor);
                    }
                }
            }
            for (Customerbank customerbankCollectionNewCustomerbank : customerbankCollectionNew) {
                if (!customerbankCollectionOld.contains(customerbankCollectionNewCustomerbank)) {
                    Companyemployee oldCompanyemployeeOfCustomerbankCollectionNewCustomerbank = customerbankCollectionNewCustomerbank.getCompanyemployee();
                    customerbankCollectionNewCustomerbank.setCompanyemployee(companyemployee);
                    customerbankCollectionNewCustomerbank = em.merge(customerbankCollectionNewCustomerbank);
                    if (oldCompanyemployeeOfCustomerbankCollectionNewCustomerbank != null && !oldCompanyemployeeOfCustomerbankCollectionNewCustomerbank.equals(companyemployee)) {
                        oldCompanyemployeeOfCustomerbankCollectionNewCustomerbank.getCustomerbankCollection().remove(customerbankCollectionNewCustomerbank);
                        oldCompanyemployeeOfCustomerbankCollectionNewCustomerbank = em.merge(oldCompanyemployeeOfCustomerbankCollectionNewCustomerbank);
                    }
                }
            }
            for (Customerbank customerbankCollection1NewCustomerbank : customerbankCollection1New) {
                if (!customerbankCollection1Old.contains(customerbankCollection1NewCustomerbank)) {
                    Companyemployee oldCompanyemployee1OfCustomerbankCollection1NewCustomerbank = customerbankCollection1NewCustomerbank.getCompanyemployee1();
                    customerbankCollection1NewCustomerbank.setCompanyemployee1(companyemployee);
                    customerbankCollection1NewCustomerbank = em.merge(customerbankCollection1NewCustomerbank);
                    if (oldCompanyemployee1OfCustomerbankCollection1NewCustomerbank != null && !oldCompanyemployee1OfCustomerbankCollection1NewCustomerbank.equals(companyemployee)) {
                        oldCompanyemployee1OfCustomerbankCollection1NewCustomerbank.getCustomerbankCollection1().remove(customerbankCollection1NewCustomerbank);
                        oldCompanyemployee1OfCustomerbankCollection1NewCustomerbank = em.merge(oldCompanyemployee1OfCustomerbankCollection1NewCustomerbank);
                    }
                }
            }
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency : compnaypaymentcurrencyCollectionNew) {
                if (!compnaypaymentcurrencyCollectionOld.contains(compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency)) {
                    Companyemployee oldCompanyemployeeOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency = compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency.getCompanyemployee();
                    compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency.setCompanyemployee(companyemployee);
                    compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency = em.merge(compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency);
                    if (oldCompanyemployeeOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency != null && !oldCompanyemployeeOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency.equals(companyemployee)) {
                        oldCompanyemployeeOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency.getCompnaypaymentcurrencyCollection().remove(compnaypaymentcurrencyCollectionNewCompnaypaymentcurrency);
                        oldCompanyemployeeOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency = em.merge(oldCompanyemployeeOfCompnaypaymentcurrencyCollectionNewCompnaypaymentcurrency);
                    }
                }
            }
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollection1NewCompnaypaymentcurrency : compnaypaymentcurrencyCollection1New) {
                if (!compnaypaymentcurrencyCollection1Old.contains(compnaypaymentcurrencyCollection1NewCompnaypaymentcurrency)) {
                    Companyemployee oldCompanyemployee1OfCompnaypaymentcurrencyCollection1NewCompnaypaymentcurrency = compnaypaymentcurrencyCollection1NewCompnaypaymentcurrency.getCompanyemployee1();
                    compnaypaymentcurrencyCollection1NewCompnaypaymentcurrency.setCompanyemployee1(companyemployee);
                    compnaypaymentcurrencyCollection1NewCompnaypaymentcurrency = em.merge(compnaypaymentcurrencyCollection1NewCompnaypaymentcurrency);
                    if (oldCompanyemployee1OfCompnaypaymentcurrencyCollection1NewCompnaypaymentcurrency != null && !oldCompanyemployee1OfCompnaypaymentcurrencyCollection1NewCompnaypaymentcurrency.equals(companyemployee)) {
                        oldCompanyemployee1OfCompnaypaymentcurrencyCollection1NewCompnaypaymentcurrency.getCompnaypaymentcurrencyCollection1().remove(compnaypaymentcurrencyCollection1NewCompnaypaymentcurrency);
                        oldCompanyemployee1OfCompnaypaymentcurrencyCollection1NewCompnaypaymentcurrency = em.merge(oldCompanyemployee1OfCompnaypaymentcurrencyCollection1NewCompnaypaymentcurrency);
                    }
                }
            }
            for (Crmproject crmprojectCollectionNewCrmproject : crmprojectCollectionNew) {
                if (!crmprojectCollectionOld.contains(crmprojectCollectionNewCrmproject)) {
                    Companyemployee oldCompanyemployeeOfCrmprojectCollectionNewCrmproject = crmprojectCollectionNewCrmproject.getCompanyemployee();
                    crmprojectCollectionNewCrmproject.setCompanyemployee(companyemployee);
                    crmprojectCollectionNewCrmproject = em.merge(crmprojectCollectionNewCrmproject);
                    if (oldCompanyemployeeOfCrmprojectCollectionNewCrmproject != null && !oldCompanyemployeeOfCrmprojectCollectionNewCrmproject.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmprojectCollectionNewCrmproject.getCrmprojectCollection().remove(crmprojectCollectionNewCrmproject);
                        oldCompanyemployeeOfCrmprojectCollectionNewCrmproject = em.merge(oldCompanyemployeeOfCrmprojectCollectionNewCrmproject);
                    }
                }
            }
            for (Crmproject crmprojectCollection1NewCrmproject : crmprojectCollection1New) {
                if (!crmprojectCollection1Old.contains(crmprojectCollection1NewCrmproject)) {
                    Companyemployee oldCompanyemployee1OfCrmprojectCollection1NewCrmproject = crmprojectCollection1NewCrmproject.getCompanyemployee1();
                    crmprojectCollection1NewCrmproject.setCompanyemployee1(companyemployee);
                    crmprojectCollection1NewCrmproject = em.merge(crmprojectCollection1NewCrmproject);
                    if (oldCompanyemployee1OfCrmprojectCollection1NewCrmproject != null && !oldCompanyemployee1OfCrmprojectCollection1NewCrmproject.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmprojectCollection1NewCrmproject.getCrmprojectCollection1().remove(crmprojectCollection1NewCrmproject);
                        oldCompanyemployee1OfCrmprojectCollection1NewCrmproject = em.merge(oldCompanyemployee1OfCrmprojectCollection1NewCrmproject);
                    }
                }
            }
            for (Crmproject crmprojectCollection2NewCrmproject : crmprojectCollection2New) {
                if (!crmprojectCollection2Old.contains(crmprojectCollection2NewCrmproject)) {
                    Companyemployee oldCompanyemployee2OfCrmprojectCollection2NewCrmproject = crmprojectCollection2NewCrmproject.getCompanyemployee2();
                    crmprojectCollection2NewCrmproject.setCompanyemployee2(companyemployee);
                    crmprojectCollection2NewCrmproject = em.merge(crmprojectCollection2NewCrmproject);
                    if (oldCompanyemployee2OfCrmprojectCollection2NewCrmproject != null && !oldCompanyemployee2OfCrmprojectCollection2NewCrmproject.equals(companyemployee)) {
                        oldCompanyemployee2OfCrmprojectCollection2NewCrmproject.getCrmprojectCollection2().remove(crmprojectCollection2NewCrmproject);
                        oldCompanyemployee2OfCrmprojectCollection2NewCrmproject = em.merge(oldCompanyemployee2OfCrmprojectCollection2NewCrmproject);
                    }
                }
            }
            for (Companyemployeeaddress companyemployeeaddressCollectionNewCompanyemployeeaddress : companyemployeeaddressCollectionNew) {
                if (!companyemployeeaddressCollectionOld.contains(companyemployeeaddressCollectionNewCompanyemployeeaddress)) {
                    Companyemployee oldCompanyemployeeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress = companyemployeeaddressCollectionNewCompanyemployeeaddress.getCompanyemployee();
                    companyemployeeaddressCollectionNewCompanyemployeeaddress.setCompanyemployee(companyemployee);
                    companyemployeeaddressCollectionNewCompanyemployeeaddress = em.merge(companyemployeeaddressCollectionNewCompanyemployeeaddress);
                    if (oldCompanyemployeeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress != null && !oldCompanyemployeeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress.getCompanyemployeeaddressCollection().remove(companyemployeeaddressCollectionNewCompanyemployeeaddress);
                        oldCompanyemployeeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress = em.merge(oldCompanyemployeeOfCompanyemployeeaddressCollectionNewCompanyemployeeaddress);
                    }
                }
            }
            for (Companyemployeeaddress companyemployeeaddressCollection1NewCompanyemployeeaddress : companyemployeeaddressCollection1New) {
                if (!companyemployeeaddressCollection1Old.contains(companyemployeeaddressCollection1NewCompanyemployeeaddress)) {
                    Companyemployee oldCompanyemployee1OfCompanyemployeeaddressCollection1NewCompanyemployeeaddress = companyemployeeaddressCollection1NewCompanyemployeeaddress.getCompanyemployee1();
                    companyemployeeaddressCollection1NewCompanyemployeeaddress.setCompanyemployee1(companyemployee);
                    companyemployeeaddressCollection1NewCompanyemployeeaddress = em.merge(companyemployeeaddressCollection1NewCompanyemployeeaddress);
                    if (oldCompanyemployee1OfCompanyemployeeaddressCollection1NewCompanyemployeeaddress != null && !oldCompanyemployee1OfCompanyemployeeaddressCollection1NewCompanyemployeeaddress.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanyemployeeaddressCollection1NewCompanyemployeeaddress.getCompanyemployeeaddressCollection1().remove(companyemployeeaddressCollection1NewCompanyemployeeaddress);
                        oldCompanyemployee1OfCompanyemployeeaddressCollection1NewCompanyemployeeaddress = em.merge(oldCompanyemployee1OfCompanyemployeeaddressCollection1NewCompanyemployeeaddress);
                    }
                }
            }
            for (Companyemployeeaddress companyemployeeaddressCollection2NewCompanyemployeeaddress : companyemployeeaddressCollection2New) {
                if (!companyemployeeaddressCollection2Old.contains(companyemployeeaddressCollection2NewCompanyemployeeaddress)) {
                    Companyemployee oldCompanyemployee2OfCompanyemployeeaddressCollection2NewCompanyemployeeaddress = companyemployeeaddressCollection2NewCompanyemployeeaddress.getCompanyemployee2();
                    companyemployeeaddressCollection2NewCompanyemployeeaddress.setCompanyemployee2(companyemployee);
                    companyemployeeaddressCollection2NewCompanyemployeeaddress = em.merge(companyemployeeaddressCollection2NewCompanyemployeeaddress);
                    if (oldCompanyemployee2OfCompanyemployeeaddressCollection2NewCompanyemployeeaddress != null && !oldCompanyemployee2OfCompanyemployeeaddressCollection2NewCompanyemployeeaddress.equals(companyemployee)) {
                        oldCompanyemployee2OfCompanyemployeeaddressCollection2NewCompanyemployeeaddress.getCompanyemployeeaddressCollection2().remove(companyemployeeaddressCollection2NewCompanyemployeeaddress);
                        oldCompanyemployee2OfCompanyemployeeaddressCollection2NewCompanyemployeeaddress = em.merge(oldCompanyemployee2OfCompanyemployeeaddressCollection2NewCompanyemployeeaddress);
                    }
                }
            }
            for (Crmforumdocs crmforumdocsCollectionNewCrmforumdocs : crmforumdocsCollectionNew) {
                if (!crmforumdocsCollectionOld.contains(crmforumdocsCollectionNewCrmforumdocs)) {
                    Companyemployee oldCompanyemployeeOfCrmforumdocsCollectionNewCrmforumdocs = crmforumdocsCollectionNewCrmforumdocs.getCompanyemployee();
                    crmforumdocsCollectionNewCrmforumdocs.setCompanyemployee(companyemployee);
                    crmforumdocsCollectionNewCrmforumdocs = em.merge(crmforumdocsCollectionNewCrmforumdocs);
                    if (oldCompanyemployeeOfCrmforumdocsCollectionNewCrmforumdocs != null && !oldCompanyemployeeOfCrmforumdocsCollectionNewCrmforumdocs.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmforumdocsCollectionNewCrmforumdocs.getCrmforumdocsCollection().remove(crmforumdocsCollectionNewCrmforumdocs);
                        oldCompanyemployeeOfCrmforumdocsCollectionNewCrmforumdocs = em.merge(oldCompanyemployeeOfCrmforumdocsCollectionNewCrmforumdocs);
                    }
                }
            }
            for (Companyproduct companyproductCollectionNewCompanyproduct : companyproductCollectionNew) {
                if (!companyproductCollectionOld.contains(companyproductCollectionNewCompanyproduct)) {
                    Companyemployee oldCompanyemployeeOfCompanyproductCollectionNewCompanyproduct = companyproductCollectionNewCompanyproduct.getCompanyemployee();
                    companyproductCollectionNewCompanyproduct.setCompanyemployee(companyemployee);
                    companyproductCollectionNewCompanyproduct = em.merge(companyproductCollectionNewCompanyproduct);
                    if (oldCompanyemployeeOfCompanyproductCollectionNewCompanyproduct != null && !oldCompanyemployeeOfCompanyproductCollectionNewCompanyproduct.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanyproductCollectionNewCompanyproduct.getCompanyproductCollection().remove(companyproductCollectionNewCompanyproduct);
                        oldCompanyemployeeOfCompanyproductCollectionNewCompanyproduct = em.merge(oldCompanyemployeeOfCompanyproductCollectionNewCompanyproduct);
                    }
                }
            }
            for (Companyproduct companyproductCollection1NewCompanyproduct : companyproductCollection1New) {
                if (!companyproductCollection1Old.contains(companyproductCollection1NewCompanyproduct)) {
                    Companyemployee oldCompanyemployee1OfCompanyproductCollection1NewCompanyproduct = companyproductCollection1NewCompanyproduct.getCompanyemployee1();
                    companyproductCollection1NewCompanyproduct.setCompanyemployee1(companyemployee);
                    companyproductCollection1NewCompanyproduct = em.merge(companyproductCollection1NewCompanyproduct);
                    if (oldCompanyemployee1OfCompanyproductCollection1NewCompanyproduct != null && !oldCompanyemployee1OfCompanyproductCollection1NewCompanyproduct.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanyproductCollection1NewCompanyproduct.getCompanyproductCollection1().remove(companyproductCollection1NewCompanyproduct);
                        oldCompanyemployee1OfCompanyproductCollection1NewCompanyproduct = em.merge(oldCompanyemployee1OfCompanyproductCollection1NewCompanyproduct);
                    }
                }
            }
            for (Employeedesignation employeedesignationCollectionNewEmployeedesignation : employeedesignationCollectionNew) {
                if (!employeedesignationCollectionOld.contains(employeedesignationCollectionNewEmployeedesignation)) {
                    Companyemployee oldCompanyemployeeOfEmployeedesignationCollectionNewEmployeedesignation = employeedesignationCollectionNewEmployeedesignation.getCompanyemployee();
                    employeedesignationCollectionNewEmployeedesignation.setCompanyemployee(companyemployee);
                    employeedesignationCollectionNewEmployeedesignation = em.merge(employeedesignationCollectionNewEmployeedesignation);
                    if (oldCompanyemployeeOfEmployeedesignationCollectionNewEmployeedesignation != null && !oldCompanyemployeeOfEmployeedesignationCollectionNewEmployeedesignation.equals(companyemployee)) {
                        oldCompanyemployeeOfEmployeedesignationCollectionNewEmployeedesignation.getEmployeedesignationCollection().remove(employeedesignationCollectionNewEmployeedesignation);
                        oldCompanyemployeeOfEmployeedesignationCollectionNewEmployeedesignation = em.merge(oldCompanyemployeeOfEmployeedesignationCollectionNewEmployeedesignation);
                    }
                }
            }
            for (Employeedesignation employeedesignationCollection1NewEmployeedesignation : employeedesignationCollection1New) {
                if (!employeedesignationCollection1Old.contains(employeedesignationCollection1NewEmployeedesignation)) {
                    Companyemployee oldCompanyemployee1OfEmployeedesignationCollection1NewEmployeedesignation = employeedesignationCollection1NewEmployeedesignation.getCompanyemployee1();
                    employeedesignationCollection1NewEmployeedesignation.setCompanyemployee1(companyemployee);
                    employeedesignationCollection1NewEmployeedesignation = em.merge(employeedesignationCollection1NewEmployeedesignation);
                    if (oldCompanyemployee1OfEmployeedesignationCollection1NewEmployeedesignation != null && !oldCompanyemployee1OfEmployeedesignationCollection1NewEmployeedesignation.equals(companyemployee)) {
                        oldCompanyemployee1OfEmployeedesignationCollection1NewEmployeedesignation.getEmployeedesignationCollection1().remove(employeedesignationCollection1NewEmployeedesignation);
                        oldCompanyemployee1OfEmployeedesignationCollection1NewEmployeedesignation = em.merge(oldCompanyemployee1OfEmployeedesignationCollection1NewEmployeedesignation);
                    }
                }
            }
            for (Companyaccountstack companyaccountstackCollectionNewCompanyaccountstack : companyaccountstackCollectionNew) {
                if (!companyaccountstackCollectionOld.contains(companyaccountstackCollectionNewCompanyaccountstack)) {
                    Companyemployee oldCompanyemployeeOfCompanyaccountstackCollectionNewCompanyaccountstack = companyaccountstackCollectionNewCompanyaccountstack.getCompanyemployee();
                    companyaccountstackCollectionNewCompanyaccountstack.setCompanyemployee(companyemployee);
                    companyaccountstackCollectionNewCompanyaccountstack = em.merge(companyaccountstackCollectionNewCompanyaccountstack);
                    if (oldCompanyemployeeOfCompanyaccountstackCollectionNewCompanyaccountstack != null && !oldCompanyemployeeOfCompanyaccountstackCollectionNewCompanyaccountstack.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanyaccountstackCollectionNewCompanyaccountstack.getCompanyaccountstackCollection().remove(companyaccountstackCollectionNewCompanyaccountstack);
                        oldCompanyemployeeOfCompanyaccountstackCollectionNewCompanyaccountstack = em.merge(oldCompanyemployeeOfCompanyaccountstackCollectionNewCompanyaccountstack);
                    }
                }
            }
            for (Companyaccountstack companyaccountstackCollection1NewCompanyaccountstack : companyaccountstackCollection1New) {
                if (!companyaccountstackCollection1Old.contains(companyaccountstackCollection1NewCompanyaccountstack)) {
                    Companyemployee oldCompanyemployee1OfCompanyaccountstackCollection1NewCompanyaccountstack = companyaccountstackCollection1NewCompanyaccountstack.getCompanyemployee1();
                    companyaccountstackCollection1NewCompanyaccountstack.setCompanyemployee1(companyemployee);
                    companyaccountstackCollection1NewCompanyaccountstack = em.merge(companyaccountstackCollection1NewCompanyaccountstack);
                    if (oldCompanyemployee1OfCompanyaccountstackCollection1NewCompanyaccountstack != null && !oldCompanyemployee1OfCompanyaccountstackCollection1NewCompanyaccountstack.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanyaccountstackCollection1NewCompanyaccountstack.getCompanyaccountstackCollection1().remove(companyaccountstackCollection1NewCompanyaccountstack);
                        oldCompanyemployee1OfCompanyaccountstackCollection1NewCompanyaccountstack = em.merge(oldCompanyemployee1OfCompanyaccountstackCollection1NewCompanyaccountstack);
                    }
                }
            }
            for (Crmprojectteammembers crmprojectteammembersCollectionNewCrmprojectteammembers : crmprojectteammembersCollectionNew) {
                if (!crmprojectteammembersCollectionOld.contains(crmprojectteammembersCollectionNewCrmprojectteammembers)) {
                    Companyemployee oldCompanyemployeeOfCrmprojectteammembersCollectionNewCrmprojectteammembers = crmprojectteammembersCollectionNewCrmprojectteammembers.getCompanyemployee();
                    crmprojectteammembersCollectionNewCrmprojectteammembers.setCompanyemployee(companyemployee);
                    crmprojectteammembersCollectionNewCrmprojectteammembers = em.merge(crmprojectteammembersCollectionNewCrmprojectteammembers);
                    if (oldCompanyemployeeOfCrmprojectteammembersCollectionNewCrmprojectteammembers != null && !oldCompanyemployeeOfCrmprojectteammembersCollectionNewCrmprojectteammembers.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmprojectteammembersCollectionNewCrmprojectteammembers.getCrmprojectteammembersCollection().remove(crmprojectteammembersCollectionNewCrmprojectteammembers);
                        oldCompanyemployeeOfCrmprojectteammembersCollectionNewCrmprojectteammembers = em.merge(oldCompanyemployeeOfCrmprojectteammembersCollectionNewCrmprojectteammembers);
                    }
                }
            }
            for (Crmprojectteammembers crmprojectteammembersCollection1NewCrmprojectteammembers : crmprojectteammembersCollection1New) {
                if (!crmprojectteammembersCollection1Old.contains(crmprojectteammembersCollection1NewCrmprojectteammembers)) {
                    Companyemployee oldCompanyemployee1OfCrmprojectteammembersCollection1NewCrmprojectteammembers = crmprojectteammembersCollection1NewCrmprojectteammembers.getCompanyemployee1();
                    crmprojectteammembersCollection1NewCrmprojectteammembers.setCompanyemployee1(companyemployee);
                    crmprojectteammembersCollection1NewCrmprojectteammembers = em.merge(crmprojectteammembersCollection1NewCrmprojectteammembers);
                    if (oldCompanyemployee1OfCrmprojectteammembersCollection1NewCrmprojectteammembers != null && !oldCompanyemployee1OfCrmprojectteammembersCollection1NewCrmprojectteammembers.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmprojectteammembersCollection1NewCrmprojectteammembers.getCrmprojectteammembersCollection1().remove(crmprojectteammembersCollection1NewCrmprojectteammembers);
                        oldCompanyemployee1OfCrmprojectteammembersCollection1NewCrmprojectteammembers = em.merge(oldCompanyemployee1OfCrmprojectteammembersCollection1NewCrmprojectteammembers);
                    }
                }
            }
            for (Crmprojectteammembers crmprojectteammembersCollection2NewCrmprojectteammembers : crmprojectteammembersCollection2New) {
                if (!crmprojectteammembersCollection2Old.contains(crmprojectteammembersCollection2NewCrmprojectteammembers)) {
                    Companyemployee oldCompanyemployee2OfCrmprojectteammembersCollection2NewCrmprojectteammembers = crmprojectteammembersCollection2NewCrmprojectteammembers.getCompanyemployee2();
                    crmprojectteammembersCollection2NewCrmprojectteammembers.setCompanyemployee2(companyemployee);
                    crmprojectteammembersCollection2NewCrmprojectteammembers = em.merge(crmprojectteammembersCollection2NewCrmprojectteammembers);
                    if (oldCompanyemployee2OfCrmprojectteammembersCollection2NewCrmprojectteammembers != null && !oldCompanyemployee2OfCrmprojectteammembersCollection2NewCrmprojectteammembers.equals(companyemployee)) {
                        oldCompanyemployee2OfCrmprojectteammembersCollection2NewCrmprojectteammembers.getCrmprojectteammembersCollection2().remove(crmprojectteammembersCollection2NewCrmprojectteammembers);
                        oldCompanyemployee2OfCrmprojectteammembersCollection2NewCrmprojectteammembers = em.merge(oldCompanyemployee2OfCrmprojectteammembersCollection2NewCrmprojectteammembers);
                    }
                }
            }
            for (Componentattachments componentattachmentsCollectionNewComponentattachments : componentattachmentsCollectionNew) {
                if (!componentattachmentsCollectionOld.contains(componentattachmentsCollectionNewComponentattachments)) {
                    Companyemployee oldCompanyemployeeOfComponentattachmentsCollectionNewComponentattachments = componentattachmentsCollectionNewComponentattachments.getCompanyemployee();
                    componentattachmentsCollectionNewComponentattachments.setCompanyemployee(companyemployee);
                    componentattachmentsCollectionNewComponentattachments = em.merge(componentattachmentsCollectionNewComponentattachments);
                    if (oldCompanyemployeeOfComponentattachmentsCollectionNewComponentattachments != null && !oldCompanyemployeeOfComponentattachmentsCollectionNewComponentattachments.equals(companyemployee)) {
                        oldCompanyemployeeOfComponentattachmentsCollectionNewComponentattachments.getComponentattachmentsCollection().remove(componentattachmentsCollectionNewComponentattachments);
                        oldCompanyemployeeOfComponentattachmentsCollectionNewComponentattachments = em.merge(oldCompanyemployeeOfComponentattachmentsCollectionNewComponentattachments);
                    }
                }
            }
            for (Componentattachments componentattachmentsCollection1NewComponentattachments : componentattachmentsCollection1New) {
                if (!componentattachmentsCollection1Old.contains(componentattachmentsCollection1NewComponentattachments)) {
                    Companyemployee oldCompanyemployee1OfComponentattachmentsCollection1NewComponentattachments = componentattachmentsCollection1NewComponentattachments.getCompanyemployee1();
                    componentattachmentsCollection1NewComponentattachments.setCompanyemployee1(companyemployee);
                    componentattachmentsCollection1NewComponentattachments = em.merge(componentattachmentsCollection1NewComponentattachments);
                    if (oldCompanyemployee1OfComponentattachmentsCollection1NewComponentattachments != null && !oldCompanyemployee1OfComponentattachmentsCollection1NewComponentattachments.equals(companyemployee)) {
                        oldCompanyemployee1OfComponentattachmentsCollection1NewComponentattachments.getComponentattachmentsCollection1().remove(componentattachmentsCollection1NewComponentattachments);
                        oldCompanyemployee1OfComponentattachmentsCollection1NewComponentattachments = em.merge(oldCompanyemployee1OfComponentattachmentsCollection1NewComponentattachments);
                    }
                }
            }
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress : crmvisitorcontactsaddressCollectionNew) {
                if (!crmvisitorcontactsaddressCollectionOld.contains(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress)) {
                    Companyemployee oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress = crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.getCompanyemployee();
                    crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.setCompanyemployee(companyemployee);
                    crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress = em.merge(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress);
                    if (oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress != null && !oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress);
                        oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress = em.merge(oldCompanyemployeeOfCrmvisitorcontactsaddressCollectionNewCrmvisitorcontactsaddress);
                    }
                }
            }
            for (Crmvisitorcontacts crmvisitorcontactsCollectionNewCrmvisitorcontacts : crmvisitorcontactsCollectionNew) {
                if (!crmvisitorcontactsCollectionOld.contains(crmvisitorcontactsCollectionNewCrmvisitorcontacts)) {
                    Companyemployee oldCompanyemployeeOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts = crmvisitorcontactsCollectionNewCrmvisitorcontacts.getCompanyemployee();
                    crmvisitorcontactsCollectionNewCrmvisitorcontacts.setCompanyemployee(companyemployee);
                    crmvisitorcontactsCollectionNewCrmvisitorcontacts = em.merge(crmvisitorcontactsCollectionNewCrmvisitorcontacts);
                    if (oldCompanyemployeeOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts != null && !oldCompanyemployeeOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts.getCrmvisitorcontactsCollection().remove(crmvisitorcontactsCollectionNewCrmvisitorcontacts);
                        oldCompanyemployeeOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts = em.merge(oldCompanyemployeeOfCrmvisitorcontactsCollectionNewCrmvisitorcontacts);
                    }
                }
            }
            for (Crmvisitorcontacts crmvisitorcontactsCollection1NewCrmvisitorcontacts : crmvisitorcontactsCollection1New) {
                if (!crmvisitorcontactsCollection1Old.contains(crmvisitorcontactsCollection1NewCrmvisitorcontacts)) {
                    Companyemployee oldCompanyemployee1OfCrmvisitorcontactsCollection1NewCrmvisitorcontacts = crmvisitorcontactsCollection1NewCrmvisitorcontacts.getCompanyemployee1();
                    crmvisitorcontactsCollection1NewCrmvisitorcontacts.setCompanyemployee1(companyemployee);
                    crmvisitorcontactsCollection1NewCrmvisitorcontacts = em.merge(crmvisitorcontactsCollection1NewCrmvisitorcontacts);
                    if (oldCompanyemployee1OfCrmvisitorcontactsCollection1NewCrmvisitorcontacts != null && !oldCompanyemployee1OfCrmvisitorcontactsCollection1NewCrmvisitorcontacts.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmvisitorcontactsCollection1NewCrmvisitorcontacts.getCrmvisitorcontactsCollection1().remove(crmvisitorcontactsCollection1NewCrmvisitorcontacts);
                        oldCompanyemployee1OfCrmvisitorcontactsCollection1NewCrmvisitorcontacts = em.merge(oldCompanyemployee1OfCrmvisitorcontactsCollection1NewCrmvisitorcontacts);
                    }
                }
            }
            for (Companyaccountstackcd companyaccountstackcdCollectionNewCompanyaccountstackcd : companyaccountstackcdCollectionNew) {
                if (!companyaccountstackcdCollectionOld.contains(companyaccountstackcdCollectionNewCompanyaccountstackcd)) {
                    Companyemployee oldCompanyemployeeOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd = companyaccountstackcdCollectionNewCompanyaccountstackcd.getCompanyemployee();
                    companyaccountstackcdCollectionNewCompanyaccountstackcd.setCompanyemployee(companyemployee);
                    companyaccountstackcdCollectionNewCompanyaccountstackcd = em.merge(companyaccountstackcdCollectionNewCompanyaccountstackcd);
                    if (oldCompanyemployeeOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd != null && !oldCompanyemployeeOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd.getCompanyaccountstackcdCollection().remove(companyaccountstackcdCollectionNewCompanyaccountstackcd);
                        oldCompanyemployeeOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd = em.merge(oldCompanyemployeeOfCompanyaccountstackcdCollectionNewCompanyaccountstackcd);
                    }
                }
            }
            for (Companyaccountstackcd companyaccountstackcdCollection1NewCompanyaccountstackcd : companyaccountstackcdCollection1New) {
                if (!companyaccountstackcdCollection1Old.contains(companyaccountstackcdCollection1NewCompanyaccountstackcd)) {
                    Companyemployee oldCompanyemployee1OfCompanyaccountstackcdCollection1NewCompanyaccountstackcd = companyaccountstackcdCollection1NewCompanyaccountstackcd.getCompanyemployee1();
                    companyaccountstackcdCollection1NewCompanyaccountstackcd.setCompanyemployee1(companyemployee);
                    companyaccountstackcdCollection1NewCompanyaccountstackcd = em.merge(companyaccountstackcdCollection1NewCompanyaccountstackcd);
                    if (oldCompanyemployee1OfCompanyaccountstackcdCollection1NewCompanyaccountstackcd != null && !oldCompanyemployee1OfCompanyaccountstackcdCollection1NewCompanyaccountstackcd.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanyaccountstackcdCollection1NewCompanyaccountstackcd.getCompanyaccountstackcdCollection1().remove(companyaccountstackcdCollection1NewCompanyaccountstackcd);
                        oldCompanyemployee1OfCompanyaccountstackcdCollection1NewCompanyaccountstackcd = em.merge(oldCompanyemployee1OfCompanyaccountstackcdCollection1NewCompanyaccountstackcd);
                    }
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionNewCrmprojectticketmanagement : crmprojectticketmanagementCollectionNew) {
                if (!crmprojectticketmanagementCollectionOld.contains(crmprojectticketmanagementCollectionNewCrmprojectticketmanagement)) {
                    Companyemployee oldCompanyemployeeOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement = crmprojectticketmanagementCollectionNewCrmprojectticketmanagement.getCompanyemployee();
                    crmprojectticketmanagementCollectionNewCrmprojectticketmanagement.setCompanyemployee(companyemployee);
                    crmprojectticketmanagementCollectionNewCrmprojectticketmanagement = em.merge(crmprojectticketmanagementCollectionNewCrmprojectticketmanagement);
                    if (oldCompanyemployeeOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement != null && !oldCompanyemployeeOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement.getCrmprojectticketmanagementCollection().remove(crmprojectticketmanagementCollectionNewCrmprojectticketmanagement);
                        oldCompanyemployeeOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement = em.merge(oldCompanyemployeeOfCrmprojectticketmanagementCollectionNewCrmprojectticketmanagement);
                    }
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1NewCrmprojectticketmanagement : crmprojectticketmanagementCollection1New) {
                if (!crmprojectticketmanagementCollection1Old.contains(crmprojectticketmanagementCollection1NewCrmprojectticketmanagement)) {
                    Companyemployee oldCompanyemployee1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement = crmprojectticketmanagementCollection1NewCrmprojectticketmanagement.getCompanyemployee1();
                    crmprojectticketmanagementCollection1NewCrmprojectticketmanagement.setCompanyemployee1(companyemployee);
                    crmprojectticketmanagementCollection1NewCrmprojectticketmanagement = em.merge(crmprojectticketmanagementCollection1NewCrmprojectticketmanagement);
                    if (oldCompanyemployee1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement != null && !oldCompanyemployee1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement.getCrmprojectticketmanagementCollection1().remove(crmprojectticketmanagementCollection1NewCrmprojectticketmanagement);
                        oldCompanyemployee1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement = em.merge(oldCompanyemployee1OfCrmprojectticketmanagementCollection1NewCrmprojectticketmanagement);
                    }
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection2NewCrmprojectticketmanagement : crmprojectticketmanagementCollection2New) {
                if (!crmprojectticketmanagementCollection2Old.contains(crmprojectticketmanagementCollection2NewCrmprojectticketmanagement)) {
                    Companyemployee oldCompanyemployee2OfCrmprojectticketmanagementCollection2NewCrmprojectticketmanagement = crmprojectticketmanagementCollection2NewCrmprojectticketmanagement.getCompanyemployee2();
                    crmprojectticketmanagementCollection2NewCrmprojectticketmanagement.setCompanyemployee2(companyemployee);
                    crmprojectticketmanagementCollection2NewCrmprojectticketmanagement = em.merge(crmprojectticketmanagementCollection2NewCrmprojectticketmanagement);
                    if (oldCompanyemployee2OfCrmprojectticketmanagementCollection2NewCrmprojectticketmanagement != null && !oldCompanyemployee2OfCrmprojectticketmanagementCollection2NewCrmprojectticketmanagement.equals(companyemployee)) {
                        oldCompanyemployee2OfCrmprojectticketmanagementCollection2NewCrmprojectticketmanagement.getCrmprojectticketmanagementCollection2().remove(crmprojectticketmanagementCollection2NewCrmprojectticketmanagement);
                        oldCompanyemployee2OfCrmprojectticketmanagementCollection2NewCrmprojectticketmanagement = em.merge(oldCompanyemployee2OfCrmprojectticketmanagementCollection2NewCrmprojectticketmanagement);
                    }
                }
            }
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection3NewCrmprojectticketmanagement : crmprojectticketmanagementCollection3New) {
                if (!crmprojectticketmanagementCollection3Old.contains(crmprojectticketmanagementCollection3NewCrmprojectticketmanagement)) {
                    Companyemployee oldCompanyemployee3OfCrmprojectticketmanagementCollection3NewCrmprojectticketmanagement = crmprojectticketmanagementCollection3NewCrmprojectticketmanagement.getCompanyemployee3();
                    crmprojectticketmanagementCollection3NewCrmprojectticketmanagement.setCompanyemployee3(companyemployee);
                    crmprojectticketmanagementCollection3NewCrmprojectticketmanagement = em.merge(crmprojectticketmanagementCollection3NewCrmprojectticketmanagement);
                    if (oldCompanyemployee3OfCrmprojectticketmanagementCollection3NewCrmprojectticketmanagement != null && !oldCompanyemployee3OfCrmprojectticketmanagementCollection3NewCrmprojectticketmanagement.equals(companyemployee)) {
                        oldCompanyemployee3OfCrmprojectticketmanagementCollection3NewCrmprojectticketmanagement.getCrmprojectticketmanagementCollection3().remove(crmprojectticketmanagementCollection3NewCrmprojectticketmanagement);
                        oldCompanyemployee3OfCrmprojectticketmanagementCollection3NewCrmprojectticketmanagement = em.merge(oldCompanyemployee3OfCrmprojectticketmanagementCollection3NewCrmprojectticketmanagement);
                    }
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionNewCrmprojectticketnotification : crmprojectticketnotificationCollectionNew) {
                if (!crmprojectticketnotificationCollectionOld.contains(crmprojectticketnotificationCollectionNewCrmprojectticketnotification)) {
                    Companyemployee oldCompanyemployeeOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification = crmprojectticketnotificationCollectionNewCrmprojectticketnotification.getCompanyemployee();
                    crmprojectticketnotificationCollectionNewCrmprojectticketnotification.setCompanyemployee(companyemployee);
                    crmprojectticketnotificationCollectionNewCrmprojectticketnotification = em.merge(crmprojectticketnotificationCollectionNewCrmprojectticketnotification);
                    if (oldCompanyemployeeOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification != null && !oldCompanyemployeeOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification.getCrmprojectticketnotificationCollection().remove(crmprojectticketnotificationCollectionNewCrmprojectticketnotification);
                        oldCompanyemployeeOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification = em.merge(oldCompanyemployeeOfCrmprojectticketnotificationCollectionNewCrmprojectticketnotification);
                    }
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollection1NewCrmprojectticketnotification : crmprojectticketnotificationCollection1New) {
                if (!crmprojectticketnotificationCollection1Old.contains(crmprojectticketnotificationCollection1NewCrmprojectticketnotification)) {
                    Companyemployee oldCompanyemployee1OfCrmprojectticketnotificationCollection1NewCrmprojectticketnotification = crmprojectticketnotificationCollection1NewCrmprojectticketnotification.getCompanyemployee1();
                    crmprojectticketnotificationCollection1NewCrmprojectticketnotification.setCompanyemployee1(companyemployee);
                    crmprojectticketnotificationCollection1NewCrmprojectticketnotification = em.merge(crmprojectticketnotificationCollection1NewCrmprojectticketnotification);
                    if (oldCompanyemployee1OfCrmprojectticketnotificationCollection1NewCrmprojectticketnotification != null && !oldCompanyemployee1OfCrmprojectticketnotificationCollection1NewCrmprojectticketnotification.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmprojectticketnotificationCollection1NewCrmprojectticketnotification.getCrmprojectticketnotificationCollection1().remove(crmprojectticketnotificationCollection1NewCrmprojectticketnotification);
                        oldCompanyemployee1OfCrmprojectticketnotificationCollection1NewCrmprojectticketnotification = em.merge(oldCompanyemployee1OfCrmprojectticketnotificationCollection1NewCrmprojectticketnotification);
                    }
                }
            }
            for (Crmprojectticketnotification crmprojectticketnotificationCollection2NewCrmprojectticketnotification : crmprojectticketnotificationCollection2New) {
                if (!crmprojectticketnotificationCollection2Old.contains(crmprojectticketnotificationCollection2NewCrmprojectticketnotification)) {
                    Companyemployee oldCompanyemployee2OfCrmprojectticketnotificationCollection2NewCrmprojectticketnotification = crmprojectticketnotificationCollection2NewCrmprojectticketnotification.getCompanyemployee2();
                    crmprojectticketnotificationCollection2NewCrmprojectticketnotification.setCompanyemployee2(companyemployee);
                    crmprojectticketnotificationCollection2NewCrmprojectticketnotification = em.merge(crmprojectticketnotificationCollection2NewCrmprojectticketnotification);
                    if (oldCompanyemployee2OfCrmprojectticketnotificationCollection2NewCrmprojectticketnotification != null && !oldCompanyemployee2OfCrmprojectticketnotificationCollection2NewCrmprojectticketnotification.equals(companyemployee)) {
                        oldCompanyemployee2OfCrmprojectticketnotificationCollection2NewCrmprojectticketnotification.getCrmprojectticketnotificationCollection2().remove(crmprojectticketnotificationCollection2NewCrmprojectticketnotification);
                        oldCompanyemployee2OfCrmprojectticketnotificationCollection2NewCrmprojectticketnotification = em.merge(oldCompanyemployee2OfCrmprojectticketnotificationCollection2NewCrmprojectticketnotification);
                    }
                }
            }
            for (Crmvisitor crmvisitorCollectionNewCrmvisitor : crmvisitorCollectionNew) {
                if (!crmvisitorCollectionOld.contains(crmvisitorCollectionNewCrmvisitor)) {
                    Companyemployee oldCompanyemployeeOfCrmvisitorCollectionNewCrmvisitor = crmvisitorCollectionNewCrmvisitor.getCompanyemployee();
                    crmvisitorCollectionNewCrmvisitor.setCompanyemployee(companyemployee);
                    crmvisitorCollectionNewCrmvisitor = em.merge(crmvisitorCollectionNewCrmvisitor);
                    if (oldCompanyemployeeOfCrmvisitorCollectionNewCrmvisitor != null && !oldCompanyemployeeOfCrmvisitorCollectionNewCrmvisitor.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmvisitorCollectionNewCrmvisitor.getCrmvisitorCollection().remove(crmvisitorCollectionNewCrmvisitor);
                        oldCompanyemployeeOfCrmvisitorCollectionNewCrmvisitor = em.merge(oldCompanyemployeeOfCrmvisitorCollectionNewCrmvisitor);
                    }
                }
            }
            for (Crmvisitor crmvisitorCollection1NewCrmvisitor : crmvisitorCollection1New) {
                if (!crmvisitorCollection1Old.contains(crmvisitorCollection1NewCrmvisitor)) {
                    Companyemployee oldCompanyemployee1OfCrmvisitorCollection1NewCrmvisitor = crmvisitorCollection1NewCrmvisitor.getCompanyemployee1();
                    crmvisitorCollection1NewCrmvisitor.setCompanyemployee1(companyemployee);
                    crmvisitorCollection1NewCrmvisitor = em.merge(crmvisitorCollection1NewCrmvisitor);
                    if (oldCompanyemployee1OfCrmvisitorCollection1NewCrmvisitor != null && !oldCompanyemployee1OfCrmvisitorCollection1NewCrmvisitor.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmvisitorCollection1NewCrmvisitor.getCrmvisitorCollection1().remove(crmvisitorCollection1NewCrmvisitor);
                        oldCompanyemployee1OfCrmvisitorCollection1NewCrmvisitor = em.merge(oldCompanyemployee1OfCrmvisitorCollection1NewCrmvisitor);
                    }
                }
            }
            for (Crmworkordertype crmworkordertypeCollectionNewCrmworkordertype : crmworkordertypeCollectionNew) {
                if (!crmworkordertypeCollectionOld.contains(crmworkordertypeCollectionNewCrmworkordertype)) {
                    Companyemployee oldCompanyemployeeOfCrmworkordertypeCollectionNewCrmworkordertype = crmworkordertypeCollectionNewCrmworkordertype.getCompanyemployee();
                    crmworkordertypeCollectionNewCrmworkordertype.setCompanyemployee(companyemployee);
                    crmworkordertypeCollectionNewCrmworkordertype = em.merge(crmworkordertypeCollectionNewCrmworkordertype);
                    if (oldCompanyemployeeOfCrmworkordertypeCollectionNewCrmworkordertype != null && !oldCompanyemployeeOfCrmworkordertypeCollectionNewCrmworkordertype.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmworkordertypeCollectionNewCrmworkordertype.getCrmworkordertypeCollection().remove(crmworkordertypeCollectionNewCrmworkordertype);
                        oldCompanyemployeeOfCrmworkordertypeCollectionNewCrmworkordertype = em.merge(oldCompanyemployeeOfCrmworkordertypeCollectionNewCrmworkordertype);
                    }
                }
            }
            for (Crmworkordertype crmworkordertypeCollection1NewCrmworkordertype : crmworkordertypeCollection1New) {
                if (!crmworkordertypeCollection1Old.contains(crmworkordertypeCollection1NewCrmworkordertype)) {
                    Companyemployee oldCompanyemployee1OfCrmworkordertypeCollection1NewCrmworkordertype = crmworkordertypeCollection1NewCrmworkordertype.getCompanyemployee1();
                    crmworkordertypeCollection1NewCrmworkordertype.setCompanyemployee1(companyemployee);
                    crmworkordertypeCollection1NewCrmworkordertype = em.merge(crmworkordertypeCollection1NewCrmworkordertype);
                    if (oldCompanyemployee1OfCrmworkordertypeCollection1NewCrmworkordertype != null && !oldCompanyemployee1OfCrmworkordertypeCollection1NewCrmworkordertype.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmworkordertypeCollection1NewCrmworkordertype.getCrmworkordertypeCollection1().remove(crmworkordertypeCollection1NewCrmworkordertype);
                        oldCompanyemployee1OfCrmworkordertypeCollection1NewCrmworkordertype = em.merge(oldCompanyemployee1OfCrmworkordertypeCollection1NewCrmworkordertype);
                    }
                }
            }
            for (Crmlabortype crmlabortypeCollectionNewCrmlabortype : crmlabortypeCollectionNew) {
                if (!crmlabortypeCollectionOld.contains(crmlabortypeCollectionNewCrmlabortype)) {
                    Companyemployee oldCompanyemployeeOfCrmlabortypeCollectionNewCrmlabortype = crmlabortypeCollectionNewCrmlabortype.getCompanyemployee();
                    crmlabortypeCollectionNewCrmlabortype.setCompanyemployee(companyemployee);
                    crmlabortypeCollectionNewCrmlabortype = em.merge(crmlabortypeCollectionNewCrmlabortype);
                    if (oldCompanyemployeeOfCrmlabortypeCollectionNewCrmlabortype != null && !oldCompanyemployeeOfCrmlabortypeCollectionNewCrmlabortype.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmlabortypeCollectionNewCrmlabortype.getCrmlabortypeCollection().remove(crmlabortypeCollectionNewCrmlabortype);
                        oldCompanyemployeeOfCrmlabortypeCollectionNewCrmlabortype = em.merge(oldCompanyemployeeOfCrmlabortypeCollectionNewCrmlabortype);
                    }
                }
            }
            for (Crmlabortype crmlabortypeCollection1NewCrmlabortype : crmlabortypeCollection1New) {
                if (!crmlabortypeCollection1Old.contains(crmlabortypeCollection1NewCrmlabortype)) {
                    Companyemployee oldCompanyemployee1OfCrmlabortypeCollection1NewCrmlabortype = crmlabortypeCollection1NewCrmlabortype.getCompanyemployee1();
                    crmlabortypeCollection1NewCrmlabortype.setCompanyemployee1(companyemployee);
                    crmlabortypeCollection1NewCrmlabortype = em.merge(crmlabortypeCollection1NewCrmlabortype);
                    if (oldCompanyemployee1OfCrmlabortypeCollection1NewCrmlabortype != null && !oldCompanyemployee1OfCrmlabortypeCollection1NewCrmlabortype.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmlabortypeCollection1NewCrmlabortype.getCrmlabortypeCollection1().remove(crmlabortypeCollection1NewCrmlabortype);
                        oldCompanyemployee1OfCrmlabortypeCollection1NewCrmlabortype = em.merge(oldCompanyemployee1OfCrmlabortypeCollection1NewCrmlabortype);
                    }
                }
            }
            for (Crmbillingtype crmbillingtypeCollectionNewCrmbillingtype : crmbillingtypeCollectionNew) {
                if (!crmbillingtypeCollectionOld.contains(crmbillingtypeCollectionNewCrmbillingtype)) {
                    Companyemployee oldCompanyemployeeOfCrmbillingtypeCollectionNewCrmbillingtype = crmbillingtypeCollectionNewCrmbillingtype.getCompanyemployee();
                    crmbillingtypeCollectionNewCrmbillingtype.setCompanyemployee(companyemployee);
                    crmbillingtypeCollectionNewCrmbillingtype = em.merge(crmbillingtypeCollectionNewCrmbillingtype);
                    if (oldCompanyemployeeOfCrmbillingtypeCollectionNewCrmbillingtype != null && !oldCompanyemployeeOfCrmbillingtypeCollectionNewCrmbillingtype.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmbillingtypeCollectionNewCrmbillingtype.getCrmbillingtypeCollection().remove(crmbillingtypeCollectionNewCrmbillingtype);
                        oldCompanyemployeeOfCrmbillingtypeCollectionNewCrmbillingtype = em.merge(oldCompanyemployeeOfCrmbillingtypeCollectionNewCrmbillingtype);
                    }
                }
            }
            for (Crmbillingtype crmbillingtypeCollection1NewCrmbillingtype : crmbillingtypeCollection1New) {
                if (!crmbillingtypeCollection1Old.contains(crmbillingtypeCollection1NewCrmbillingtype)) {
                    Companyemployee oldCompanyemployee1OfCrmbillingtypeCollection1NewCrmbillingtype = crmbillingtypeCollection1NewCrmbillingtype.getCompanyemployee1();
                    crmbillingtypeCollection1NewCrmbillingtype.setCompanyemployee1(companyemployee);
                    crmbillingtypeCollection1NewCrmbillingtype = em.merge(crmbillingtypeCollection1NewCrmbillingtype);
                    if (oldCompanyemployee1OfCrmbillingtypeCollection1NewCrmbillingtype != null && !oldCompanyemployee1OfCrmbillingtypeCollection1NewCrmbillingtype.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmbillingtypeCollection1NewCrmbillingtype.getCrmbillingtypeCollection1().remove(crmbillingtypeCollection1NewCrmbillingtype);
                        oldCompanyemployee1OfCrmbillingtypeCollection1NewCrmbillingtype = em.merge(oldCompanyemployee1OfCrmbillingtypeCollection1NewCrmbillingtype);
                    }
                }
            }
            for (Companyproductcategory companyproductcategoryCollectionNewCompanyproductcategory : companyproductcategoryCollectionNew) {
                if (!companyproductcategoryCollectionOld.contains(companyproductcategoryCollectionNewCompanyproductcategory)) {
                    Companyemployee oldCompanyemployeeOfCompanyproductcategoryCollectionNewCompanyproductcategory = companyproductcategoryCollectionNewCompanyproductcategory.getCompanyemployee();
                    companyproductcategoryCollectionNewCompanyproductcategory.setCompanyemployee(companyemployee);
                    companyproductcategoryCollectionNewCompanyproductcategory = em.merge(companyproductcategoryCollectionNewCompanyproductcategory);
                    if (oldCompanyemployeeOfCompanyproductcategoryCollectionNewCompanyproductcategory != null && !oldCompanyemployeeOfCompanyproductcategoryCollectionNewCompanyproductcategory.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanyproductcategoryCollectionNewCompanyproductcategory.getCompanyproductcategoryCollection().remove(companyproductcategoryCollectionNewCompanyproductcategory);
                        oldCompanyemployeeOfCompanyproductcategoryCollectionNewCompanyproductcategory = em.merge(oldCompanyemployeeOfCompanyproductcategoryCollectionNewCompanyproductcategory);
                    }
                }
            }
            for (Companyproductcategory companyproductcategoryCollection1NewCompanyproductcategory : companyproductcategoryCollection1New) {
                if (!companyproductcategoryCollection1Old.contains(companyproductcategoryCollection1NewCompanyproductcategory)) {
                    Companyemployee oldCompanyemployee1OfCompanyproductcategoryCollection1NewCompanyproductcategory = companyproductcategoryCollection1NewCompanyproductcategory.getCompanyemployee1();
                    companyproductcategoryCollection1NewCompanyproductcategory.setCompanyemployee1(companyemployee);
                    companyproductcategoryCollection1NewCompanyproductcategory = em.merge(companyproductcategoryCollection1NewCompanyproductcategory);
                    if (oldCompanyemployee1OfCompanyproductcategoryCollection1NewCompanyproductcategory != null && !oldCompanyemployee1OfCompanyproductcategoryCollection1NewCompanyproductcategory.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanyproductcategoryCollection1NewCompanyproductcategory.getCompanyproductcategoryCollection1().remove(companyproductcategoryCollection1NewCompanyproductcategory);
                        oldCompanyemployee1OfCompanyproductcategoryCollection1NewCompanyproductcategory = em.merge(oldCompanyemployee1OfCompanyproductcategoryCollection1NewCompanyproductcategory);
                    }
                }
            }
            for (Crmprojecttask crmprojecttaskCollectionNewCrmprojecttask : crmprojecttaskCollectionNew) {
                if (!crmprojecttaskCollectionOld.contains(crmprojecttaskCollectionNewCrmprojecttask)) {
                    Companyemployee oldCompanyemployeeOfCrmprojecttaskCollectionNewCrmprojecttask = crmprojecttaskCollectionNewCrmprojecttask.getCompanyemployee();
                    crmprojecttaskCollectionNewCrmprojecttask.setCompanyemployee(companyemployee);
                    crmprojecttaskCollectionNewCrmprojecttask = em.merge(crmprojecttaskCollectionNewCrmprojecttask);
                    if (oldCompanyemployeeOfCrmprojecttaskCollectionNewCrmprojecttask != null && !oldCompanyemployeeOfCrmprojecttaskCollectionNewCrmprojecttask.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmprojecttaskCollectionNewCrmprojecttask.getCrmprojecttaskCollection().remove(crmprojecttaskCollectionNewCrmprojecttask);
                        oldCompanyemployeeOfCrmprojecttaskCollectionNewCrmprojecttask = em.merge(oldCompanyemployeeOfCrmprojecttaskCollectionNewCrmprojecttask);
                    }
                }
            }
            for (Crmprojecttask crmprojecttaskCollection1NewCrmprojecttask : crmprojecttaskCollection1New) {
                if (!crmprojecttaskCollection1Old.contains(crmprojecttaskCollection1NewCrmprojecttask)) {
                    Companyemployee oldCompanyemployee1OfCrmprojecttaskCollection1NewCrmprojecttask = crmprojecttaskCollection1NewCrmprojecttask.getCompanyemployee1();
                    crmprojecttaskCollection1NewCrmprojecttask.setCompanyemployee1(companyemployee);
                    crmprojecttaskCollection1NewCrmprojecttask = em.merge(crmprojecttaskCollection1NewCrmprojecttask);
                    if (oldCompanyemployee1OfCrmprojecttaskCollection1NewCrmprojecttask != null && !oldCompanyemployee1OfCrmprojecttaskCollection1NewCrmprojecttask.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmprojecttaskCollection1NewCrmprojecttask.getCrmprojecttaskCollection1().remove(crmprojecttaskCollection1NewCrmprojecttask);
                        oldCompanyemployee1OfCrmprojecttaskCollection1NewCrmprojecttask = em.merge(oldCompanyemployee1OfCrmprojecttaskCollection1NewCrmprojecttask);
                    }
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollectionNewCrmcampaignprops : crmcampaignpropsCollectionNew) {
                if (!crmcampaignpropsCollectionOld.contains(crmcampaignpropsCollectionNewCrmcampaignprops)) {
                    Companyemployee oldCompanyemployeeOfCrmcampaignpropsCollectionNewCrmcampaignprops = crmcampaignpropsCollectionNewCrmcampaignprops.getCompanyemployee();
                    crmcampaignpropsCollectionNewCrmcampaignprops.setCompanyemployee(companyemployee);
                    crmcampaignpropsCollectionNewCrmcampaignprops = em.merge(crmcampaignpropsCollectionNewCrmcampaignprops);
                    if (oldCompanyemployeeOfCrmcampaignpropsCollectionNewCrmcampaignprops != null && !oldCompanyemployeeOfCrmcampaignpropsCollectionNewCrmcampaignprops.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmcampaignpropsCollectionNewCrmcampaignprops.getCrmcampaignpropsCollection().remove(crmcampaignpropsCollectionNewCrmcampaignprops);
                        oldCompanyemployeeOfCrmcampaignpropsCollectionNewCrmcampaignprops = em.merge(oldCompanyemployeeOfCrmcampaignpropsCollectionNewCrmcampaignprops);
                    }
                }
            }
            for (Crmcampaignprops crmcampaignpropsCollection1NewCrmcampaignprops : crmcampaignpropsCollection1New) {
                if (!crmcampaignpropsCollection1Old.contains(crmcampaignpropsCollection1NewCrmcampaignprops)) {
                    Companyemployee oldCompanyemployee1OfCrmcampaignpropsCollection1NewCrmcampaignprops = crmcampaignpropsCollection1NewCrmcampaignprops.getCompanyemployee1();
                    crmcampaignpropsCollection1NewCrmcampaignprops.setCompanyemployee1(companyemployee);
                    crmcampaignpropsCollection1NewCrmcampaignprops = em.merge(crmcampaignpropsCollection1NewCrmcampaignprops);
                    if (oldCompanyemployee1OfCrmcampaignpropsCollection1NewCrmcampaignprops != null && !oldCompanyemployee1OfCrmcampaignpropsCollection1NewCrmcampaignprops.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmcampaignpropsCollection1NewCrmcampaignprops.getCrmcampaignpropsCollection1().remove(crmcampaignpropsCollection1NewCrmcampaignprops);
                        oldCompanyemployee1OfCrmcampaignpropsCollection1NewCrmcampaignprops = em.merge(oldCompanyemployee1OfCrmcampaignpropsCollection1NewCrmcampaignprops);
                    }
                }
            }
            for (Customercontactsaddress customercontactsaddressCollectionNewCustomercontactsaddress : customercontactsaddressCollectionNew) {
                if (!customercontactsaddressCollectionOld.contains(customercontactsaddressCollectionNewCustomercontactsaddress)) {
                    Companyemployee oldCompanyemployeeOfCustomercontactsaddressCollectionNewCustomercontactsaddress = customercontactsaddressCollectionNewCustomercontactsaddress.getCompanyemployee();
                    customercontactsaddressCollectionNewCustomercontactsaddress.setCompanyemployee(companyemployee);
                    customercontactsaddressCollectionNewCustomercontactsaddress = em.merge(customercontactsaddressCollectionNewCustomercontactsaddress);
                    if (oldCompanyemployeeOfCustomercontactsaddressCollectionNewCustomercontactsaddress != null && !oldCompanyemployeeOfCustomercontactsaddressCollectionNewCustomercontactsaddress.equals(companyemployee)) {
                        oldCompanyemployeeOfCustomercontactsaddressCollectionNewCustomercontactsaddress.getCustomercontactsaddressCollection().remove(customercontactsaddressCollectionNewCustomercontactsaddress);
                        oldCompanyemployeeOfCustomercontactsaddressCollectionNewCustomercontactsaddress = em.merge(oldCompanyemployeeOfCustomercontactsaddressCollectionNewCustomercontactsaddress);
                    }
                }
            }
            for (Customercontactsaddress customercontactsaddressCollection1NewCustomercontactsaddress : customercontactsaddressCollection1New) {
                if (!customercontactsaddressCollection1Old.contains(customercontactsaddressCollection1NewCustomercontactsaddress)) {
                    Companyemployee oldCompanyemployee1OfCustomercontactsaddressCollection1NewCustomercontactsaddress = customercontactsaddressCollection1NewCustomercontactsaddress.getCompanyemployee1();
                    customercontactsaddressCollection1NewCustomercontactsaddress.setCompanyemployee1(companyemployee);
                    customercontactsaddressCollection1NewCustomercontactsaddress = em.merge(customercontactsaddressCollection1NewCustomercontactsaddress);
                    if (oldCompanyemployee1OfCustomercontactsaddressCollection1NewCustomercontactsaddress != null && !oldCompanyemployee1OfCustomercontactsaddressCollection1NewCustomercontactsaddress.equals(companyemployee)) {
                        oldCompanyemployee1OfCustomercontactsaddressCollection1NewCustomercontactsaddress.getCustomercontactsaddressCollection1().remove(customercontactsaddressCollection1NewCustomercontactsaddress);
                        oldCompanyemployee1OfCustomercontactsaddressCollection1NewCustomercontactsaddress = em.merge(oldCompanyemployee1OfCustomercontactsaddressCollection1NewCustomercontactsaddress);
                    }
                }
            }
            for (Customercategory customercategoryCollectionNewCustomercategory : customercategoryCollectionNew) {
                if (!customercategoryCollectionOld.contains(customercategoryCollectionNewCustomercategory)) {
                    Companyemployee oldCompanyemployeeOfCustomercategoryCollectionNewCustomercategory = customercategoryCollectionNewCustomercategory.getCompanyemployee();
                    customercategoryCollectionNewCustomercategory.setCompanyemployee(companyemployee);
                    customercategoryCollectionNewCustomercategory = em.merge(customercategoryCollectionNewCustomercategory);
                    if (oldCompanyemployeeOfCustomercategoryCollectionNewCustomercategory != null && !oldCompanyemployeeOfCustomercategoryCollectionNewCustomercategory.equals(companyemployee)) {
                        oldCompanyemployeeOfCustomercategoryCollectionNewCustomercategory.getCustomercategoryCollection().remove(customercategoryCollectionNewCustomercategory);
                        oldCompanyemployeeOfCustomercategoryCollectionNewCustomercategory = em.merge(oldCompanyemployeeOfCustomercategoryCollectionNewCustomercategory);
                    }
                }
            }
            for (Customercategory customercategoryCollection1NewCustomercategory : customercategoryCollection1New) {
                if (!customercategoryCollection1Old.contains(customercategoryCollection1NewCustomercategory)) {
                    Companyemployee oldCompanyemployee1OfCustomercategoryCollection1NewCustomercategory = customercategoryCollection1NewCustomercategory.getCompanyemployee1();
                    customercategoryCollection1NewCustomercategory.setCompanyemployee1(companyemployee);
                    customercategoryCollection1NewCustomercategory = em.merge(customercategoryCollection1NewCustomercategory);
                    if (oldCompanyemployee1OfCustomercategoryCollection1NewCustomercategory != null && !oldCompanyemployee1OfCustomercategoryCollection1NewCustomercategory.equals(companyemployee)) {
                        oldCompanyemployee1OfCustomercategoryCollection1NewCustomercategory.getCustomercategoryCollection1().remove(customercategoryCollection1NewCustomercategory);
                        oldCompanyemployee1OfCustomercategoryCollection1NewCustomercategory = em.merge(oldCompanyemployee1OfCustomercategoryCollection1NewCustomercategory);
                    }
                }
            }
            for (Productcomponents productcomponentsCollectionNewProductcomponents : productcomponentsCollectionNew) {
                if (!productcomponentsCollectionOld.contains(productcomponentsCollectionNewProductcomponents)) {
                    Companyemployee oldCompanyemployeeOfProductcomponentsCollectionNewProductcomponents = productcomponentsCollectionNewProductcomponents.getCompanyemployee();
                    productcomponentsCollectionNewProductcomponents.setCompanyemployee(companyemployee);
                    productcomponentsCollectionNewProductcomponents = em.merge(productcomponentsCollectionNewProductcomponents);
                    if (oldCompanyemployeeOfProductcomponentsCollectionNewProductcomponents != null && !oldCompanyemployeeOfProductcomponentsCollectionNewProductcomponents.equals(companyemployee)) {
                        oldCompanyemployeeOfProductcomponentsCollectionNewProductcomponents.getProductcomponentsCollection().remove(productcomponentsCollectionNewProductcomponents);
                        oldCompanyemployeeOfProductcomponentsCollectionNewProductcomponents = em.merge(oldCompanyemployeeOfProductcomponentsCollectionNewProductcomponents);
                    }
                }
            }
            for (Productcomponents productcomponentsCollection1NewProductcomponents : productcomponentsCollection1New) {
                if (!productcomponentsCollection1Old.contains(productcomponentsCollection1NewProductcomponents)) {
                    Companyemployee oldCompanyemployee1OfProductcomponentsCollection1NewProductcomponents = productcomponentsCollection1NewProductcomponents.getCompanyemployee1();
                    productcomponentsCollection1NewProductcomponents.setCompanyemployee1(companyemployee);
                    productcomponentsCollection1NewProductcomponents = em.merge(productcomponentsCollection1NewProductcomponents);
                    if (oldCompanyemployee1OfProductcomponentsCollection1NewProductcomponents != null && !oldCompanyemployee1OfProductcomponentsCollection1NewProductcomponents.equals(companyemployee)) {
                        oldCompanyemployee1OfProductcomponentsCollection1NewProductcomponents.getProductcomponentsCollection1().remove(productcomponentsCollection1NewProductcomponents);
                        oldCompanyemployee1OfProductcomponentsCollection1NewProductcomponents = em.merge(oldCompanyemployee1OfProductcomponentsCollection1NewProductcomponents);
                    }
                }
            }
            for (Crmexpensetype crmexpensetypeCollectionNewCrmexpensetype : crmexpensetypeCollectionNew) {
                if (!crmexpensetypeCollectionOld.contains(crmexpensetypeCollectionNewCrmexpensetype)) {
                    Companyemployee oldCompanyemployeeOfCrmexpensetypeCollectionNewCrmexpensetype = crmexpensetypeCollectionNewCrmexpensetype.getCompanyemployee();
                    crmexpensetypeCollectionNewCrmexpensetype.setCompanyemployee(companyemployee);
                    crmexpensetypeCollectionNewCrmexpensetype = em.merge(crmexpensetypeCollectionNewCrmexpensetype);
                    if (oldCompanyemployeeOfCrmexpensetypeCollectionNewCrmexpensetype != null && !oldCompanyemployeeOfCrmexpensetypeCollectionNewCrmexpensetype.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmexpensetypeCollectionNewCrmexpensetype.getCrmexpensetypeCollection().remove(crmexpensetypeCollectionNewCrmexpensetype);
                        oldCompanyemployeeOfCrmexpensetypeCollectionNewCrmexpensetype = em.merge(oldCompanyemployeeOfCrmexpensetypeCollectionNewCrmexpensetype);
                    }
                }
            }
            for (Crmexpensetype crmexpensetypeCollection1NewCrmexpensetype : crmexpensetypeCollection1New) {
                if (!crmexpensetypeCollection1Old.contains(crmexpensetypeCollection1NewCrmexpensetype)) {
                    Companyemployee oldCompanyemployee1OfCrmexpensetypeCollection1NewCrmexpensetype = crmexpensetypeCollection1NewCrmexpensetype.getCompanyemployee1();
                    crmexpensetypeCollection1NewCrmexpensetype.setCompanyemployee1(companyemployee);
                    crmexpensetypeCollection1NewCrmexpensetype = em.merge(crmexpensetypeCollection1NewCrmexpensetype);
                    if (oldCompanyemployee1OfCrmexpensetypeCollection1NewCrmexpensetype != null && !oldCompanyemployee1OfCrmexpensetypeCollection1NewCrmexpensetype.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmexpensetypeCollection1NewCrmexpensetype.getCrmexpensetypeCollection1().remove(crmexpensetypeCollection1NewCrmexpensetype);
                        oldCompanyemployee1OfCrmexpensetypeCollection1NewCrmexpensetype = em.merge(oldCompanyemployee1OfCrmexpensetypeCollection1NewCrmexpensetype);
                    }
                }
            }
            for (Companypayments companypaymentsCollectionNewCompanypayments : companypaymentsCollectionNew) {
                if (!companypaymentsCollectionOld.contains(companypaymentsCollectionNewCompanypayments)) {
                    Companyemployee oldCompanyemployeeOfCompanypaymentsCollectionNewCompanypayments = companypaymentsCollectionNewCompanypayments.getCompanyemployee();
                    companypaymentsCollectionNewCompanypayments.setCompanyemployee(companyemployee);
                    companypaymentsCollectionNewCompanypayments = em.merge(companypaymentsCollectionNewCompanypayments);
                    if (oldCompanyemployeeOfCompanypaymentsCollectionNewCompanypayments != null && !oldCompanyemployeeOfCompanypaymentsCollectionNewCompanypayments.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanypaymentsCollectionNewCompanypayments.getCompanypaymentsCollection().remove(companypaymentsCollectionNewCompanypayments);
                        oldCompanyemployeeOfCompanypaymentsCollectionNewCompanypayments = em.merge(oldCompanyemployeeOfCompanypaymentsCollectionNewCompanypayments);
                    }
                }
            }
            for (Companypayments companypaymentsCollection1NewCompanypayments : companypaymentsCollection1New) {
                if (!companypaymentsCollection1Old.contains(companypaymentsCollection1NewCompanypayments)) {
                    Companyemployee oldCompanyemployee1OfCompanypaymentsCollection1NewCompanypayments = companypaymentsCollection1NewCompanypayments.getCompanyemployee1();
                    companypaymentsCollection1NewCompanypayments.setCompanyemployee1(companyemployee);
                    companypaymentsCollection1NewCompanypayments = em.merge(companypaymentsCollection1NewCompanypayments);
                    if (oldCompanyemployee1OfCompanypaymentsCollection1NewCompanypayments != null && !oldCompanyemployee1OfCompanypaymentsCollection1NewCompanypayments.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanypaymentsCollection1NewCompanypayments.getCompanypaymentsCollection1().remove(companypaymentsCollection1NewCompanypayments);
                        oldCompanyemployee1OfCompanypaymentsCollection1NewCompanypayments = em.merge(oldCompanyemployee1OfCompanypaymentsCollection1NewCompanypayments);
                    }
                }
            }
            for (Crmworkorderresolution crmworkorderresolutionCollectionNewCrmworkorderresolution : crmworkorderresolutionCollectionNew) {
                if (!crmworkorderresolutionCollectionOld.contains(crmworkorderresolutionCollectionNewCrmworkorderresolution)) {
                    Companyemployee oldCompanyemployeeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution = crmworkorderresolutionCollectionNewCrmworkorderresolution.getCompanyemployee();
                    crmworkorderresolutionCollectionNewCrmworkorderresolution.setCompanyemployee(companyemployee);
                    crmworkorderresolutionCollectionNewCrmworkorderresolution = em.merge(crmworkorderresolutionCollectionNewCrmworkorderresolution);
                    if (oldCompanyemployeeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution != null && !oldCompanyemployeeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution.getCrmworkorderresolutionCollection().remove(crmworkorderresolutionCollectionNewCrmworkorderresolution);
                        oldCompanyemployeeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution = em.merge(oldCompanyemployeeOfCrmworkorderresolutionCollectionNewCrmworkorderresolution);
                    }
                }
            }
            for (Crmworkorderresolution crmworkorderresolutionCollection1NewCrmworkorderresolution : crmworkorderresolutionCollection1New) {
                if (!crmworkorderresolutionCollection1Old.contains(crmworkorderresolutionCollection1NewCrmworkorderresolution)) {
                    Companyemployee oldCompanyemployee1OfCrmworkorderresolutionCollection1NewCrmworkorderresolution = crmworkorderresolutionCollection1NewCrmworkorderresolution.getCompanyemployee1();
                    crmworkorderresolutionCollection1NewCrmworkorderresolution.setCompanyemployee1(companyemployee);
                    crmworkorderresolutionCollection1NewCrmworkorderresolution = em.merge(crmworkorderresolutionCollection1NewCrmworkorderresolution);
                    if (oldCompanyemployee1OfCrmworkorderresolutionCollection1NewCrmworkorderresolution != null && !oldCompanyemployee1OfCrmworkorderresolutionCollection1NewCrmworkorderresolution.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmworkorderresolutionCollection1NewCrmworkorderresolution.getCrmworkorderresolutionCollection1().remove(crmworkorderresolutionCollection1NewCrmworkorderresolution);
                        oldCompanyemployee1OfCrmworkorderresolutionCollection1NewCrmworkorderresolution = em.merge(oldCompanyemployee1OfCrmworkorderresolutionCollection1NewCrmworkorderresolution);
                    }
                }
            }
            for (Company companyCollectionNewCompany : companyCollectionNew) {
                if (!companyCollectionOld.contains(companyCollectionNewCompany)) {
                    Companyemployee oldCompanyemployeeOfCompanyCollectionNewCompany = companyCollectionNewCompany.getCompanyemployee();
                    companyCollectionNewCompany.setCompanyemployee(companyemployee);
                    companyCollectionNewCompany = em.merge(companyCollectionNewCompany);
                    if (oldCompanyemployeeOfCompanyCollectionNewCompany != null && !oldCompanyemployeeOfCompanyCollectionNewCompany.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanyCollectionNewCompany.getCompanyCollection().remove(companyCollectionNewCompany);
                        oldCompanyemployeeOfCompanyCollectionNewCompany = em.merge(oldCompanyemployeeOfCompanyCollectionNewCompany);
                    }
                }
            }
            for (Company companyCollection1NewCompany : companyCollection1New) {
                if (!companyCollection1Old.contains(companyCollection1NewCompany)) {
                    Companyemployee oldCompanyemployee1OfCompanyCollection1NewCompany = companyCollection1NewCompany.getCompanyemployee1();
                    companyCollection1NewCompany.setCompanyemployee1(companyemployee);
                    companyCollection1NewCompany = em.merge(companyCollection1NewCompany);
                    if (oldCompanyemployee1OfCompanyCollection1NewCompany != null && !oldCompanyemployee1OfCompanyCollection1NewCompany.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanyCollection1NewCompany.getCompanyCollection1().remove(companyCollection1NewCompany);
                        oldCompanyemployee1OfCompanyCollection1NewCompany = em.merge(oldCompanyemployee1OfCompanyCollection1NewCompany);
                    }
                }
            }
            for (Companyaddresstype companyaddresstypeCollectionNewCompanyaddresstype : companyaddresstypeCollectionNew) {
                if (!companyaddresstypeCollectionOld.contains(companyaddresstypeCollectionNewCompanyaddresstype)) {
                    Companyemployee oldCompanyemployeeOfCompanyaddresstypeCollectionNewCompanyaddresstype = companyaddresstypeCollectionNewCompanyaddresstype.getCompanyemployee();
                    companyaddresstypeCollectionNewCompanyaddresstype.setCompanyemployee(companyemployee);
                    companyaddresstypeCollectionNewCompanyaddresstype = em.merge(companyaddresstypeCollectionNewCompanyaddresstype);
                    if (oldCompanyemployeeOfCompanyaddresstypeCollectionNewCompanyaddresstype != null && !oldCompanyemployeeOfCompanyaddresstypeCollectionNewCompanyaddresstype.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanyaddresstypeCollectionNewCompanyaddresstype.getCompanyaddresstypeCollection().remove(companyaddresstypeCollectionNewCompanyaddresstype);
                        oldCompanyemployeeOfCompanyaddresstypeCollectionNewCompanyaddresstype = em.merge(oldCompanyemployeeOfCompanyaddresstypeCollectionNewCompanyaddresstype);
                    }
                }
            }
            for (Companyaddresstype companyaddresstypeCollection1NewCompanyaddresstype : companyaddresstypeCollection1New) {
                if (!companyaddresstypeCollection1Old.contains(companyaddresstypeCollection1NewCompanyaddresstype)) {
                    Companyemployee oldCompanyemployee1OfCompanyaddresstypeCollection1NewCompanyaddresstype = companyaddresstypeCollection1NewCompanyaddresstype.getCompanyemployee1();
                    companyaddresstypeCollection1NewCompanyaddresstype.setCompanyemployee1(companyemployee);
                    companyaddresstypeCollection1NewCompanyaddresstype = em.merge(companyaddresstypeCollection1NewCompanyaddresstype);
                    if (oldCompanyemployee1OfCompanyaddresstypeCollection1NewCompanyaddresstype != null && !oldCompanyemployee1OfCompanyaddresstypeCollection1NewCompanyaddresstype.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanyaddresstypeCollection1NewCompanyaddresstype.getCompanyaddresstypeCollection1().remove(companyaddresstypeCollection1NewCompanyaddresstype);
                        oldCompanyemployee1OfCompanyaddresstypeCollection1NewCompanyaddresstype = em.merge(oldCompanyemployee1OfCompanyaddresstypeCollection1NewCompanyaddresstype);
                    }
                }
            }
            for (Crmemployeecontacts crmemployeecontactsCollectionNewCrmemployeecontacts : crmemployeecontactsCollectionNew) {
                if (!crmemployeecontactsCollectionOld.contains(crmemployeecontactsCollectionNewCrmemployeecontacts)) {
                    Companyemployee oldCompanyemployeeOfCrmemployeecontactsCollectionNewCrmemployeecontacts = crmemployeecontactsCollectionNewCrmemployeecontacts.getCompanyemployee();
                    crmemployeecontactsCollectionNewCrmemployeecontacts.setCompanyemployee(companyemployee);
                    crmemployeecontactsCollectionNewCrmemployeecontacts = em.merge(crmemployeecontactsCollectionNewCrmemployeecontacts);
                    if (oldCompanyemployeeOfCrmemployeecontactsCollectionNewCrmemployeecontacts != null && !oldCompanyemployeeOfCrmemployeecontactsCollectionNewCrmemployeecontacts.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmemployeecontactsCollectionNewCrmemployeecontacts.getCrmemployeecontactsCollection().remove(crmemployeecontactsCollectionNewCrmemployeecontacts);
                        oldCompanyemployeeOfCrmemployeecontactsCollectionNewCrmemployeecontacts = em.merge(oldCompanyemployeeOfCrmemployeecontactsCollectionNewCrmemployeecontacts);
                    }
                }
            }
            for (Approval approvalCollectionNewApproval : approvalCollectionNew) {
                if (!approvalCollectionOld.contains(approvalCollectionNewApproval)) {
                    Companyemployee oldCompanyemployeeOfApprovalCollectionNewApproval = approvalCollectionNewApproval.getCompanyemployee();
                    approvalCollectionNewApproval.setCompanyemployee(companyemployee);
                    approvalCollectionNewApproval = em.merge(approvalCollectionNewApproval);
                    if (oldCompanyemployeeOfApprovalCollectionNewApproval != null && !oldCompanyemployeeOfApprovalCollectionNewApproval.equals(companyemployee)) {
                        oldCompanyemployeeOfApprovalCollectionNewApproval.getApprovalCollection().remove(approvalCollectionNewApproval);
                        oldCompanyemployeeOfApprovalCollectionNewApproval = em.merge(oldCompanyemployeeOfApprovalCollectionNewApproval);
                    }
                }
            }
            for (Approval approvalCollection1NewApproval : approvalCollection1New) {
                if (!approvalCollection1Old.contains(approvalCollection1NewApproval)) {
                    Companyemployee oldCompanyemployee1OfApprovalCollection1NewApproval = approvalCollection1NewApproval.getCompanyemployee1();
                    approvalCollection1NewApproval.setCompanyemployee1(companyemployee);
                    approvalCollection1NewApproval = em.merge(approvalCollection1NewApproval);
                    if (oldCompanyemployee1OfApprovalCollection1NewApproval != null && !oldCompanyemployee1OfApprovalCollection1NewApproval.equals(companyemployee)) {
                        oldCompanyemployee1OfApprovalCollection1NewApproval.getApprovalCollection1().remove(approvalCollection1NewApproval);
                        oldCompanyemployee1OfApprovalCollection1NewApproval = em.merge(oldCompanyemployee1OfApprovalCollection1NewApproval);
                    }
                }
            }
            for (Approval approvalCollection2NewApproval : approvalCollection2New) {
                if (!approvalCollection2Old.contains(approvalCollection2NewApproval)) {
                    Companyemployee oldCompanyemployee2OfApprovalCollection2NewApproval = approvalCollection2NewApproval.getCompanyemployee2();
                    approvalCollection2NewApproval.setCompanyemployee2(companyemployee);
                    approvalCollection2NewApproval = em.merge(approvalCollection2NewApproval);
                    if (oldCompanyemployee2OfApprovalCollection2NewApproval != null && !oldCompanyemployee2OfApprovalCollection2NewApproval.equals(companyemployee)) {
                        oldCompanyemployee2OfApprovalCollection2NewApproval.getApprovalCollection2().remove(approvalCollection2NewApproval);
                        oldCompanyemployee2OfApprovalCollection2NewApproval = em.merge(oldCompanyemployee2OfApprovalCollection2NewApproval);
                    }
                }
            }
            for (Workorderinstructions workorderinstructionsCollectionNewWorkorderinstructions : workorderinstructionsCollectionNew) {
                if (!workorderinstructionsCollectionOld.contains(workorderinstructionsCollectionNewWorkorderinstructions)) {
                    Companyemployee oldCompanyemployeeOfWorkorderinstructionsCollectionNewWorkorderinstructions = workorderinstructionsCollectionNewWorkorderinstructions.getCompanyemployee();
                    workorderinstructionsCollectionNewWorkorderinstructions.setCompanyemployee(companyemployee);
                    workorderinstructionsCollectionNewWorkorderinstructions = em.merge(workorderinstructionsCollectionNewWorkorderinstructions);
                    if (oldCompanyemployeeOfWorkorderinstructionsCollectionNewWorkorderinstructions != null && !oldCompanyemployeeOfWorkorderinstructionsCollectionNewWorkorderinstructions.equals(companyemployee)) {
                        oldCompanyemployeeOfWorkorderinstructionsCollectionNewWorkorderinstructions.getWorkorderinstructionsCollection().remove(workorderinstructionsCollectionNewWorkorderinstructions);
                        oldCompanyemployeeOfWorkorderinstructionsCollectionNewWorkorderinstructions = em.merge(oldCompanyemployeeOfWorkorderinstructionsCollectionNewWorkorderinstructions);
                    }
                }
            }
            for (Workorderinstructions workorderinstructionsCollection1NewWorkorderinstructions : workorderinstructionsCollection1New) {
                if (!workorderinstructionsCollection1Old.contains(workorderinstructionsCollection1NewWorkorderinstructions)) {
                    Companyemployee oldCompanyemployee1OfWorkorderinstructionsCollection1NewWorkorderinstructions = workorderinstructionsCollection1NewWorkorderinstructions.getCompanyemployee1();
                    workorderinstructionsCollection1NewWorkorderinstructions.setCompanyemployee1(companyemployee);
                    workorderinstructionsCollection1NewWorkorderinstructions = em.merge(workorderinstructionsCollection1NewWorkorderinstructions);
                    if (oldCompanyemployee1OfWorkorderinstructionsCollection1NewWorkorderinstructions != null && !oldCompanyemployee1OfWorkorderinstructionsCollection1NewWorkorderinstructions.equals(companyemployee)) {
                        oldCompanyemployee1OfWorkorderinstructionsCollection1NewWorkorderinstructions.getWorkorderinstructionsCollection1().remove(workorderinstructionsCollection1NewWorkorderinstructions);
                        oldCompanyemployee1OfWorkorderinstructionsCollection1NewWorkorderinstructions = em.merge(oldCompanyemployee1OfWorkorderinstructionsCollection1NewWorkorderinstructions);
                    }
                }
            }
            for (Companycustomer companycustomerCollectionNewCompanycustomer : companycustomerCollectionNew) {
                if (!companycustomerCollectionOld.contains(companycustomerCollectionNewCompanycustomer)) {
                    Companyemployee oldCompanyemployeeOfCompanycustomerCollectionNewCompanycustomer = companycustomerCollectionNewCompanycustomer.getCompanyemployee();
                    companycustomerCollectionNewCompanycustomer.setCompanyemployee(companyemployee);
                    companycustomerCollectionNewCompanycustomer = em.merge(companycustomerCollectionNewCompanycustomer);
                    if (oldCompanyemployeeOfCompanycustomerCollectionNewCompanycustomer != null && !oldCompanyemployeeOfCompanycustomerCollectionNewCompanycustomer.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanycustomerCollectionNewCompanycustomer.getCompanycustomerCollection().remove(companycustomerCollectionNewCompanycustomer);
                        oldCompanyemployeeOfCompanycustomerCollectionNewCompanycustomer = em.merge(oldCompanyemployeeOfCompanycustomerCollectionNewCompanycustomer);
                    }
                }
            }
            for (Companycustomer companycustomerCollection1NewCompanycustomer : companycustomerCollection1New) {
                if (!companycustomerCollection1Old.contains(companycustomerCollection1NewCompanycustomer)) {
                    Companyemployee oldCompanyemployee1OfCompanycustomerCollection1NewCompanycustomer = companycustomerCollection1NewCompanycustomer.getCompanyemployee1();
                    companycustomerCollection1NewCompanycustomer.setCompanyemployee1(companyemployee);
                    companycustomerCollection1NewCompanycustomer = em.merge(companycustomerCollection1NewCompanycustomer);
                    if (oldCompanyemployee1OfCompanycustomerCollection1NewCompanycustomer != null && !oldCompanyemployee1OfCompanycustomerCollection1NewCompanycustomer.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanycustomerCollection1NewCompanycustomer.getCompanycustomerCollection1().remove(companycustomerCollection1NewCompanycustomer);
                        oldCompanyemployee1OfCompanycustomerCollection1NewCompanycustomer = em.merge(oldCompanyemployee1OfCompanycustomerCollection1NewCompanycustomer);
                    }
                }
            }
            for (Crmforum crmforumCollectionNewCrmforum : crmforumCollectionNew) {
                if (!crmforumCollectionOld.contains(crmforumCollectionNewCrmforum)) {
                    Companyemployee oldCompanyemployeeOfCrmforumCollectionNewCrmforum = crmforumCollectionNewCrmforum.getCompanyemployee();
                    crmforumCollectionNewCrmforum.setCompanyemployee(companyemployee);
                    crmforumCollectionNewCrmforum = em.merge(crmforumCollectionNewCrmforum);
                    if (oldCompanyemployeeOfCrmforumCollectionNewCrmforum != null && !oldCompanyemployeeOfCrmforumCollectionNewCrmforum.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmforumCollectionNewCrmforum.getCrmforumCollection().remove(crmforumCollectionNewCrmforum);
                        oldCompanyemployeeOfCrmforumCollectionNewCrmforum = em.merge(oldCompanyemployeeOfCrmforumCollectionNewCrmforum);
                    }
                }
            }
            for (Crmprojectstatus crmprojectstatusCollectionNewCrmprojectstatus : crmprojectstatusCollectionNew) {
                if (!crmprojectstatusCollectionOld.contains(crmprojectstatusCollectionNewCrmprojectstatus)) {
                    Companyemployee oldCompanyemployeeOfCrmprojectstatusCollectionNewCrmprojectstatus = crmprojectstatusCollectionNewCrmprojectstatus.getCompanyemployee();
                    crmprojectstatusCollectionNewCrmprojectstatus.setCompanyemployee(companyemployee);
                    crmprojectstatusCollectionNewCrmprojectstatus = em.merge(crmprojectstatusCollectionNewCrmprojectstatus);
                    if (oldCompanyemployeeOfCrmprojectstatusCollectionNewCrmprojectstatus != null && !oldCompanyemployeeOfCrmprojectstatusCollectionNewCrmprojectstatus.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmprojectstatusCollectionNewCrmprojectstatus.getCrmprojectstatusCollection().remove(crmprojectstatusCollectionNewCrmprojectstatus);
                        oldCompanyemployeeOfCrmprojectstatusCollectionNewCrmprojectstatus = em.merge(oldCompanyemployeeOfCrmprojectstatusCollectionNewCrmprojectstatus);
                    }
                }
            }
            for (Crmprojectstatus crmprojectstatusCollection1NewCrmprojectstatus : crmprojectstatusCollection1New) {
                if (!crmprojectstatusCollection1Old.contains(crmprojectstatusCollection1NewCrmprojectstatus)) {
                    Companyemployee oldCompanyemployee1OfCrmprojectstatusCollection1NewCrmprojectstatus = crmprojectstatusCollection1NewCrmprojectstatus.getCompanyemployee1();
                    crmprojectstatusCollection1NewCrmprojectstatus.setCompanyemployee1(companyemployee);
                    crmprojectstatusCollection1NewCrmprojectstatus = em.merge(crmprojectstatusCollection1NewCrmprojectstatus);
                    if (oldCompanyemployee1OfCrmprojectstatusCollection1NewCrmprojectstatus != null && !oldCompanyemployee1OfCrmprojectstatusCollection1NewCrmprojectstatus.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmprojectstatusCollection1NewCrmprojectstatus.getCrmprojectstatusCollection1().remove(crmprojectstatusCollection1NewCrmprojectstatus);
                        oldCompanyemployee1OfCrmprojectstatusCollection1NewCrmprojectstatus = em.merge(oldCompanyemployee1OfCrmprojectstatusCollection1NewCrmprojectstatus);
                    }
                }
            }
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionNewCrmschedulerevnttype : crmschedulerevnttypeCollectionNew) {
                if (!crmschedulerevnttypeCollectionOld.contains(crmschedulerevnttypeCollectionNewCrmschedulerevnttype)) {
                    Companyemployee oldCompanyemployeeOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype = crmschedulerevnttypeCollectionNewCrmschedulerevnttype.getCompanyemployee();
                    crmschedulerevnttypeCollectionNewCrmschedulerevnttype.setCompanyemployee(companyemployee);
                    crmschedulerevnttypeCollectionNewCrmschedulerevnttype = em.merge(crmschedulerevnttypeCollectionNewCrmschedulerevnttype);
                    if (oldCompanyemployeeOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype != null && !oldCompanyemployeeOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype.getCrmschedulerevnttypeCollection().remove(crmschedulerevnttypeCollectionNewCrmschedulerevnttype);
                        oldCompanyemployeeOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype = em.merge(oldCompanyemployeeOfCrmschedulerevnttypeCollectionNewCrmschedulerevnttype);
                    }
                }
            }
            for (Crmschedulerevnttype crmschedulerevnttypeCollection1NewCrmschedulerevnttype : crmschedulerevnttypeCollection1New) {
                if (!crmschedulerevnttypeCollection1Old.contains(crmschedulerevnttypeCollection1NewCrmschedulerevnttype)) {
                    Companyemployee oldCompanyemployee1OfCrmschedulerevnttypeCollection1NewCrmschedulerevnttype = crmschedulerevnttypeCollection1NewCrmschedulerevnttype.getCompanyemployee1();
                    crmschedulerevnttypeCollection1NewCrmschedulerevnttype.setCompanyemployee1(companyemployee);
                    crmschedulerevnttypeCollection1NewCrmschedulerevnttype = em.merge(crmschedulerevnttypeCollection1NewCrmschedulerevnttype);
                    if (oldCompanyemployee1OfCrmschedulerevnttypeCollection1NewCrmschedulerevnttype != null && !oldCompanyemployee1OfCrmschedulerevnttypeCollection1NewCrmschedulerevnttype.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmschedulerevnttypeCollection1NewCrmschedulerevnttype.getCrmschedulerevnttypeCollection1().remove(crmschedulerevnttypeCollection1NewCrmschedulerevnttype);
                        oldCompanyemployee1OfCrmschedulerevnttypeCollection1NewCrmschedulerevnttype = em.merge(oldCompanyemployee1OfCrmschedulerevnttypeCollection1NewCrmschedulerevnttype);
                    }
                }
            }
            for (Crmschedulerefcode crmschedulerefcodeCollectionNewCrmschedulerefcode : crmschedulerefcodeCollectionNew) {
                if (!crmschedulerefcodeCollectionOld.contains(crmschedulerefcodeCollectionNewCrmschedulerefcode)) {
                    Companyemployee oldCompanyemployeeOfCrmschedulerefcodeCollectionNewCrmschedulerefcode = crmschedulerefcodeCollectionNewCrmschedulerefcode.getCompanyemployee();
                    crmschedulerefcodeCollectionNewCrmschedulerefcode.setCompanyemployee(companyemployee);
                    crmschedulerefcodeCollectionNewCrmschedulerefcode = em.merge(crmschedulerefcodeCollectionNewCrmschedulerefcode);
                    if (oldCompanyemployeeOfCrmschedulerefcodeCollectionNewCrmschedulerefcode != null && !oldCompanyemployeeOfCrmschedulerefcodeCollectionNewCrmschedulerefcode.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmschedulerefcodeCollectionNewCrmschedulerefcode.getCrmschedulerefcodeCollection().remove(crmschedulerefcodeCollectionNewCrmschedulerefcode);
                        oldCompanyemployeeOfCrmschedulerefcodeCollectionNewCrmschedulerefcode = em.merge(oldCompanyemployeeOfCrmschedulerefcodeCollectionNewCrmschedulerefcode);
                    }
                }
            }
            for (Crmschedulerefcode crmschedulerefcodeCollection1NewCrmschedulerefcode : crmschedulerefcodeCollection1New) {
                if (!crmschedulerefcodeCollection1Old.contains(crmschedulerefcodeCollection1NewCrmschedulerefcode)) {
                    Companyemployee oldCompanyemployee1OfCrmschedulerefcodeCollection1NewCrmschedulerefcode = crmschedulerefcodeCollection1NewCrmschedulerefcode.getCompanyemployee1();
                    crmschedulerefcodeCollection1NewCrmschedulerefcode.setCompanyemployee1(companyemployee);
                    crmschedulerefcodeCollection1NewCrmschedulerefcode = em.merge(crmschedulerefcodeCollection1NewCrmschedulerefcode);
                    if (oldCompanyemployee1OfCrmschedulerefcodeCollection1NewCrmschedulerefcode != null && !oldCompanyemployee1OfCrmschedulerefcodeCollection1NewCrmschedulerefcode.equals(companyemployee)) {
                        oldCompanyemployee1OfCrmschedulerefcodeCollection1NewCrmschedulerefcode.getCrmschedulerefcodeCollection1().remove(crmschedulerefcodeCollection1NewCrmschedulerefcode);
                        oldCompanyemployee1OfCrmschedulerefcodeCollection1NewCrmschedulerefcode = em.merge(oldCompanyemployee1OfCrmschedulerefcodeCollection1NewCrmschedulerefcode);
                    }
                }
            }
            for (Componenttype componenttypeCollectionNewComponenttype : componenttypeCollectionNew) {
                if (!componenttypeCollectionOld.contains(componenttypeCollectionNewComponenttype)) {
                    Companyemployee oldCompanyemployeeOfComponenttypeCollectionNewComponenttype = componenttypeCollectionNewComponenttype.getCompanyemployee();
                    componenttypeCollectionNewComponenttype.setCompanyemployee(companyemployee);
                    componenttypeCollectionNewComponenttype = em.merge(componenttypeCollectionNewComponenttype);
                    if (oldCompanyemployeeOfComponenttypeCollectionNewComponenttype != null && !oldCompanyemployeeOfComponenttypeCollectionNewComponenttype.equals(companyemployee)) {
                        oldCompanyemployeeOfComponenttypeCollectionNewComponenttype.getComponenttypeCollection().remove(componenttypeCollectionNewComponenttype);
                        oldCompanyemployeeOfComponenttypeCollectionNewComponenttype = em.merge(oldCompanyemployeeOfComponenttypeCollectionNewComponenttype);
                    }
                }
            }
            for (Componenttype componenttypeCollection1NewComponenttype : componenttypeCollection1New) {
                if (!componenttypeCollection1Old.contains(componenttypeCollection1NewComponenttype)) {
                    Companyemployee oldCompanyemployee1OfComponenttypeCollection1NewComponenttype = componenttypeCollection1NewComponenttype.getCompanyemployee1();
                    componenttypeCollection1NewComponenttype.setCompanyemployee1(companyemployee);
                    componenttypeCollection1NewComponenttype = em.merge(componenttypeCollection1NewComponenttype);
                    if (oldCompanyemployee1OfComponenttypeCollection1NewComponenttype != null && !oldCompanyemployee1OfComponenttypeCollection1NewComponenttype.equals(companyemployee)) {
                        oldCompanyemployee1OfComponenttypeCollection1NewComponenttype.getComponenttypeCollection1().remove(componenttypeCollection1NewComponenttype);
                        oldCompanyemployee1OfComponenttypeCollection1NewComponenttype = em.merge(oldCompanyemployee1OfComponenttypeCollection1NewComponenttype);
                    }
                }
            }
            for (Companypaymentscheme companypaymentschemeCollectionNewCompanypaymentscheme : companypaymentschemeCollectionNew) {
                if (!companypaymentschemeCollectionOld.contains(companypaymentschemeCollectionNewCompanypaymentscheme)) {
                    Companyemployee oldCompanyemployeeOfCompanypaymentschemeCollectionNewCompanypaymentscheme = companypaymentschemeCollectionNewCompanypaymentscheme.getCompanyemployee();
                    companypaymentschemeCollectionNewCompanypaymentscheme.setCompanyemployee(companyemployee);
                    companypaymentschemeCollectionNewCompanypaymentscheme = em.merge(companypaymentschemeCollectionNewCompanypaymentscheme);
                    if (oldCompanyemployeeOfCompanypaymentschemeCollectionNewCompanypaymentscheme != null && !oldCompanyemployeeOfCompanypaymentschemeCollectionNewCompanypaymentscheme.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanypaymentschemeCollectionNewCompanypaymentscheme.getCompanypaymentschemeCollection().remove(companypaymentschemeCollectionNewCompanypaymentscheme);
                        oldCompanyemployeeOfCompanypaymentschemeCollectionNewCompanypaymentscheme = em.merge(oldCompanyemployeeOfCompanypaymentschemeCollectionNewCompanypaymentscheme);
                    }
                }
            }
            for (Companypaymentscheme companypaymentschemeCollection1NewCompanypaymentscheme : companypaymentschemeCollection1New) {
                if (!companypaymentschemeCollection1Old.contains(companypaymentschemeCollection1NewCompanypaymentscheme)) {
                    Companyemployee oldCompanyemployee1OfCompanypaymentschemeCollection1NewCompanypaymentscheme = companypaymentschemeCollection1NewCompanypaymentscheme.getCompanyemployee1();
                    companypaymentschemeCollection1NewCompanypaymentscheme.setCompanyemployee1(companyemployee);
                    companypaymentschemeCollection1NewCompanypaymentscheme = em.merge(companypaymentschemeCollection1NewCompanypaymentscheme);
                    if (oldCompanyemployee1OfCompanypaymentschemeCollection1NewCompanypaymentscheme != null && !oldCompanyemployee1OfCompanypaymentschemeCollection1NewCompanypaymentscheme.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanypaymentschemeCollection1NewCompanypaymentscheme.getCompanypaymentschemeCollection1().remove(companypaymentschemeCollection1NewCompanypaymentscheme);
                        oldCompanyemployee1OfCompanypaymentschemeCollection1NewCompanypaymentscheme = em.merge(oldCompanyemployee1OfCompanypaymentschemeCollection1NewCompanypaymentscheme);
                    }
                }
            }
            for (Companycontactsaddress companycontactsaddressCollectionNewCompanycontactsaddress : companycontactsaddressCollectionNew) {
                if (!companycontactsaddressCollectionOld.contains(companycontactsaddressCollectionNewCompanycontactsaddress)) {
                    Companyemployee oldCompanyemployeeOfCompanycontactsaddressCollectionNewCompanycontactsaddress = companycontactsaddressCollectionNewCompanycontactsaddress.getCompanyemployee();
                    companycontactsaddressCollectionNewCompanycontactsaddress.setCompanyemployee(companyemployee);
                    companycontactsaddressCollectionNewCompanycontactsaddress = em.merge(companycontactsaddressCollectionNewCompanycontactsaddress);
                    if (oldCompanyemployeeOfCompanycontactsaddressCollectionNewCompanycontactsaddress != null && !oldCompanyemployeeOfCompanycontactsaddressCollectionNewCompanycontactsaddress.equals(companyemployee)) {
                        oldCompanyemployeeOfCompanycontactsaddressCollectionNewCompanycontactsaddress.getCompanycontactsaddressCollection().remove(companycontactsaddressCollectionNewCompanycontactsaddress);
                        oldCompanyemployeeOfCompanycontactsaddressCollectionNewCompanycontactsaddress = em.merge(oldCompanyemployeeOfCompanycontactsaddressCollectionNewCompanycontactsaddress);
                    }
                }
            }
            for (Companycontactsaddress companycontactsaddressCollection1NewCompanycontactsaddress : companycontactsaddressCollection1New) {
                if (!companycontactsaddressCollection1Old.contains(companycontactsaddressCollection1NewCompanycontactsaddress)) {
                    Companyemployee oldCompanyemployee1OfCompanycontactsaddressCollection1NewCompanycontactsaddress = companycontactsaddressCollection1NewCompanycontactsaddress.getCompanyemployee1();
                    companycontactsaddressCollection1NewCompanycontactsaddress.setCompanyemployee1(companyemployee);
                    companycontactsaddressCollection1NewCompanycontactsaddress = em.merge(companycontactsaddressCollection1NewCompanycontactsaddress);
                    if (oldCompanyemployee1OfCompanycontactsaddressCollection1NewCompanycontactsaddress != null && !oldCompanyemployee1OfCompanycontactsaddressCollection1NewCompanycontactsaddress.equals(companyemployee)) {
                        oldCompanyemployee1OfCompanycontactsaddressCollection1NewCompanycontactsaddress.getCompanycontactsaddressCollection1().remove(companycontactsaddressCollection1NewCompanycontactsaddress);
                        oldCompanyemployee1OfCompanycontactsaddressCollection1NewCompanycontactsaddress = em.merge(oldCompanyemployee1OfCompanycontactsaddressCollection1NewCompanycontactsaddress);
                    }
                }
            }
            for (Crmprofilesettings crmprofilesettingsCollectionNewCrmprofilesettings : crmprofilesettingsCollectionNew) {
                if (!crmprofilesettingsCollectionOld.contains(crmprofilesettingsCollectionNewCrmprofilesettings)) {
                    Companyemployee oldCompanyemployeeOfCrmprofilesettingsCollectionNewCrmprofilesettings = crmprofilesettingsCollectionNewCrmprofilesettings.getCompanyemployee();
                    crmprofilesettingsCollectionNewCrmprofilesettings.setCompanyemployee(companyemployee);
                    crmprofilesettingsCollectionNewCrmprofilesettings = em.merge(crmprofilesettingsCollectionNewCrmprofilesettings);
                    if (oldCompanyemployeeOfCrmprofilesettingsCollectionNewCrmprofilesettings != null && !oldCompanyemployeeOfCrmprofilesettingsCollectionNewCrmprofilesettings.equals(companyemployee)) {
                        oldCompanyemployeeOfCrmprofilesettingsCollectionNewCrmprofilesettings.getCrmprofilesettingsCollection().remove(crmprofilesettingsCollectionNewCrmprofilesettings);
                        oldCompanyemployeeOfCrmprofilesettingsCollectionNewCrmprofilesettings = em.merge(oldCompanyemployeeOfCrmprofilesettingsCollectionNewCrmprofilesettings);
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
                CompanyemployeePK id = companyemployee.getCompanyemployeePK();
                if (findCompanyemployee(id) == null) {
                    throw new NonexistentEntityException("The companyemployee with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyemployeePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee;
            try {
                companyemployee = em.getReference(Companyemployee.class, id);
                companyemployee.getCompanyemployeePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyemployee with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Companydepartment companydepartmentOrphanCheck = companyemployee.getCompanydepartment();
            if (companydepartmentOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companydepartment " + companydepartmentOrphanCheck + " in its companydepartment field has a non-nullable companyemployee field.");
            }
            Collection<Crmmessagechannel> crmmessagechannelCollectionOrphanCheck = companyemployee.getCrmmessagechannelCollection();
            for (Crmmessagechannel crmmessagechannelCollectionOrphanCheckCrmmessagechannel : crmmessagechannelCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmmessagechannel " + crmmessagechannelCollectionOrphanCheckCrmmessagechannel + " in its crmmessagechannelCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmmessagechannel> crmmessagechannelCollection1OrphanCheck = companyemployee.getCrmmessagechannelCollection1();
            for (Crmmessagechannel crmmessagechannelCollection1OrphanCheckCrmmessagechannel : crmmessagechannelCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmmessagechannel " + crmmessagechannelCollection1OrphanCheckCrmmessagechannel + " in its crmmessagechannelCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmmessagechannel> crmmessagechannelCollection2OrphanCheck = companyemployee.getCrmmessagechannelCollection2();
            for (Crmmessagechannel crmmessagechannelCollection2OrphanCheckCrmmessagechannel : crmmessagechannelCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmmessagechannel " + crmmessagechannelCollection2OrphanCheckCrmmessagechannel + " in its crmmessagechannelCollection2 field has a non-nullable companyemployee2 field.");
            }
            Collection<Companybank> companybankCollectionOrphanCheck = companyemployee.getCompanybankCollection();
            for (Companybank companybankCollectionOrphanCheckCompanybank : companybankCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companybank " + companybankCollectionOrphanCheckCompanybank + " in its companybankCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companybank> companybankCollection1OrphanCheck = companyemployee.getCompanybankCollection1();
            for (Companybank companybankCollection1OrphanCheckCompanybank : companybankCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companybank " + companybankCollection1OrphanCheckCompanybank + " in its companybankCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollectionOrphanCheck = companyemployee.getCrmmessagechanneltemplateCollection();
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollectionOrphanCheckCrmmessagechanneltemplate : crmmessagechanneltemplateCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmmessagechanneltemplate " + crmmessagechanneltemplateCollectionOrphanCheckCrmmessagechanneltemplate + " in its crmmessagechanneltemplateCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollection1OrphanCheck = companyemployee.getCrmmessagechanneltemplateCollection1();
            for (Crmmessagechanneltemplate crmmessagechanneltemplateCollection1OrphanCheckCrmmessagechanneltemplate : crmmessagechanneltemplateCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmmessagechanneltemplate " + crmmessagechanneltemplateCollection1OrphanCheckCrmmessagechanneltemplate + " in its crmmessagechanneltemplateCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmprojectprops> crmprojectpropsCollectionOrphanCheck = companyemployee.getCrmprojectpropsCollection();
            for (Crmprojectprops crmprojectpropsCollectionOrphanCheckCrmprojectprops : crmprojectpropsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectprops " + crmprojectpropsCollectionOrphanCheckCrmprojectprops + " in its crmprojectpropsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmprojectprops> crmprojectpropsCollection1OrphanCheck = companyemployee.getCrmprojectpropsCollection1();
            for (Crmprojectprops crmprojectpropsCollection1OrphanCheckCrmprojectprops : crmprojectpropsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectprops " + crmprojectpropsCollection1OrphanCheckCrmprojectprops + " in its crmprojectpropsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollectionOrphanCheck = companyemployee.getCrmcampaignreceiverCollection();
            for (Crmcampaignreceiver crmcampaignreceiverCollectionOrphanCheckCrmcampaignreceiver : crmcampaignreceiverCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaignreceiver " + crmcampaignreceiverCollectionOrphanCheckCrmcampaignreceiver + " in its crmcampaignreceiverCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmcampaignreceiver> crmcampaignreceiverCollection1OrphanCheck = companyemployee.getCrmcampaignreceiverCollection1();
            for (Crmcampaignreceiver crmcampaignreceiverCollection1OrphanCheckCrmcampaignreceiver : crmcampaignreceiverCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaignreceiver " + crmcampaignreceiverCollection1OrphanCheckCrmcampaignreceiver + " in its crmcampaignreceiverCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmworkordersettings> crmworkordersettingsCollectionOrphanCheck = companyemployee.getCrmworkordersettingsCollection();
            for (Crmworkordersettings crmworkordersettingsCollectionOrphanCheckCrmworkordersettings : crmworkordersettingsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmworkordersettings " + crmworkordersettingsCollectionOrphanCheckCrmworkordersettings + " in its crmworkordersettingsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Productattachments> productattachmentsCollectionOrphanCheck = companyemployee.getProductattachmentsCollection();
            for (Productattachments productattachmentsCollectionOrphanCheckProductattachments : productattachmentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Productattachments " + productattachmentsCollectionOrphanCheckProductattachments + " in its productattachmentsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Productattachments> productattachmentsCollection1OrphanCheck = companyemployee.getProductattachmentsCollection1();
            for (Productattachments productattachmentsCollection1OrphanCheckProductattachments : productattachmentsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Productattachments " + productattachmentsCollection1OrphanCheckProductattachments + " in its productattachmentsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Productattachments> productattachmentsCollection2OrphanCheck = companyemployee.getProductattachmentsCollection2();
            for (Productattachments productattachmentsCollection2OrphanCheckProductattachments : productattachmentsCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Productattachments " + productattachmentsCollection2OrphanCheckProductattachments + " in its productattachmentsCollection2 field has a non-nullable companyemployee2 field.");
            }
            Collection<Crmcampaignstatus> crmcampaignstatusCollectionOrphanCheck = companyemployee.getCrmcampaignstatusCollection();
            for (Crmcampaignstatus crmcampaignstatusCollectionOrphanCheckCrmcampaignstatus : crmcampaignstatusCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaignstatus " + crmcampaignstatusCollectionOrphanCheckCrmcampaignstatus + " in its crmcampaignstatusCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmcampaignstatus> crmcampaignstatusCollection1OrphanCheck = companyemployee.getCrmcampaignstatusCollection1();
            for (Crmcampaignstatus crmcampaignstatusCollection1OrphanCheckCrmcampaignstatus : crmcampaignstatusCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaignstatus " + crmcampaignstatusCollection1OrphanCheckCrmcampaignstatus + " in its crmcampaignstatusCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmcampaignposition> crmcampaignpositionCollectionOrphanCheck = companyemployee.getCrmcampaignpositionCollection();
            for (Crmcampaignposition crmcampaignpositionCollectionOrphanCheckCrmcampaignposition : crmcampaignpositionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaignposition " + crmcampaignpositionCollectionOrphanCheckCrmcampaignposition + " in its crmcampaignpositionCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmcampaignposition> crmcampaignpositionCollection1OrphanCheck = companyemployee.getCrmcampaignpositionCollection1();
            for (Crmcampaignposition crmcampaignpositionCollection1OrphanCheckCrmcampaignposition : crmcampaignpositionCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaignposition " + crmcampaignpositionCollection1OrphanCheckCrmcampaignposition + " in its crmcampaignpositionCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmforumteammembers> crmforumteammembersCollectionOrphanCheck = companyemployee.getCrmforumteammembersCollection();
            for (Crmforumteammembers crmforumteammembersCollectionOrphanCheckCrmforumteammembers : crmforumteammembersCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmforumteammembers " + crmforumteammembersCollectionOrphanCheckCrmforumteammembers + " in its crmforumteammembersCollection field has a non-nullable companyemployee field.");
            }
            Collection<Customercontacts> customercontactsCollectionOrphanCheck = companyemployee.getCustomercontactsCollection();
            for (Customercontacts customercontactsCollectionOrphanCheckCustomercontacts : customercontactsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Customercontacts " + customercontactsCollectionOrphanCheckCustomercontacts + " in its customercontactsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Customercontacts> customercontactsCollection1OrphanCheck = companyemployee.getCustomercontactsCollection1();
            for (Customercontacts customercontactsCollection1OrphanCheckCustomercontacts : customercontactsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Customercontacts " + customercontactsCollection1OrphanCheckCustomercontacts + " in its customercontactsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Appointment> appointmentCollectionOrphanCheck = companyemployee.getAppointmentCollection();
            for (Appointment appointmentCollectionOrphanCheckAppointment : appointmentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Appointment " + appointmentCollectionOrphanCheckAppointment + " in its appointmentCollection field has a non-nullable companyemployee field.");
            }
            Collection<Appointment> appointmentCollection1OrphanCheck = companyemployee.getAppointmentCollection1();
            for (Appointment appointmentCollection1OrphanCheckAppointment : appointmentCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Appointment " + appointmentCollection1OrphanCheckAppointment + " in its appointmentCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Appointment> appointmentCollection2OrphanCheck = companyemployee.getAppointmentCollection2();
            for (Appointment appointmentCollection2OrphanCheckAppointment : appointmentCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Appointment " + appointmentCollection2OrphanCheckAppointment + " in its appointmentCollection2 field has a non-nullable companyemployee2 field.");
            }
            Collection<Appointment> appointmentCollection3OrphanCheck = companyemployee.getAppointmentCollection3();
            for (Appointment appointmentCollection3OrphanCheckAppointment : appointmentCollection3OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Appointment " + appointmentCollection3OrphanCheckAppointment + " in its appointmentCollection3 field has a non-nullable companyemployee3 field.");
            }
            Collection<Companydepartment> companydepartmentCollectionOrphanCheck = companyemployee.getCompanydepartmentCollection();
            for (Companydepartment companydepartmentCollectionOrphanCheckCompanydepartment : companydepartmentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companydepartment " + companydepartmentCollectionOrphanCheckCompanydepartment + " in its companydepartmentCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companydepartment> companydepartmentCollection1OrphanCheck = companyemployee.getCompanydepartmentCollection1();
            for (Companydepartment companydepartmentCollection1OrphanCheckCompanydepartment : companydepartmentCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companydepartment " + companydepartmentCollection1OrphanCheckCompanydepartment + " in its companydepartmentCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmusermodules> crmusermodulesCollectionOrphanCheck = companyemployee.getCrmusermodulesCollection();
            for (Crmusermodules crmusermodulesCollectionOrphanCheckCrmusermodules : crmusermodulesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmusermodules " + crmusermodulesCollectionOrphanCheckCrmusermodules + " in its crmusermodulesCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmusermodules> crmusermodulesCollection1OrphanCheck = companyemployee.getCrmusermodulesCollection1();
            for (Crmusermodules crmusermodulesCollection1OrphanCheckCrmusermodules : crmusermodulesCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmusermodules " + crmusermodulesCollection1OrphanCheckCrmusermodules + " in its crmusermodulesCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmexpense> crmexpenseCollectionOrphanCheck = companyemployee.getCrmexpenseCollection();
            for (Crmexpense crmexpenseCollectionOrphanCheckCrmexpense : crmexpenseCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmexpense " + crmexpenseCollectionOrphanCheckCrmexpense + " in its crmexpenseCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmexpense> crmexpenseCollection1OrphanCheck = companyemployee.getCrmexpenseCollection1();
            for (Crmexpense crmexpenseCollection1OrphanCheckCrmexpense : crmexpenseCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmexpense " + crmexpenseCollection1OrphanCheckCrmexpense + " in its crmexpenseCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmworkorder> crmworkorderCollectionOrphanCheck = companyemployee.getCrmworkorderCollection();
            for (Crmworkorder crmworkorderCollectionOrphanCheckCrmworkorder : crmworkorderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmworkorder " + crmworkorderCollectionOrphanCheckCrmworkorder + " in its crmworkorderCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmworkorder> crmworkorderCollection1OrphanCheck = companyemployee.getCrmworkorderCollection1();
            for (Crmworkorder crmworkorderCollection1OrphanCheckCrmworkorder : crmworkorderCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmworkorder " + crmworkorderCollection1OrphanCheckCrmworkorder + " in its crmworkorderCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmcampaigndocs> crmcampaigndocsCollectionOrphanCheck = companyemployee.getCrmcampaigndocsCollection();
            for (Crmcampaigndocs crmcampaigndocsCollectionOrphanCheckCrmcampaigndocs : crmcampaigndocsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaigndocs " + crmcampaigndocsCollectionOrphanCheckCrmcampaigndocs + " in its crmcampaigndocsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmcampaigndocs> crmcampaigndocsCollection1OrphanCheck = companyemployee.getCrmcampaigndocsCollection1();
            for (Crmcampaigndocs crmcampaigndocsCollection1OrphanCheckCrmcampaigndocs : crmcampaigndocsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaigndocs " + crmcampaigndocsCollection1OrphanCheckCrmcampaigndocs + " in its crmcampaigndocsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmemployeenote> crmemployeenoteCollectionOrphanCheck = companyemployee.getCrmemployeenoteCollection();
            for (Crmemployeenote crmemployeenoteCollectionOrphanCheckCrmemployeenote : crmemployeenoteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmemployeenote " + crmemployeenoteCollectionOrphanCheckCrmemployeenote + " in its crmemployeenoteCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companycontacts> companycontactsCollectionOrphanCheck = companyemployee.getCompanycontactsCollection();
            for (Companycontacts companycontactsCollectionOrphanCheckCompanycontacts : companycontactsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companycontacts " + companycontactsCollectionOrphanCheckCompanycontacts + " in its companycontactsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companycontacts> companycontactsCollection1OrphanCheck = companyemployee.getCompanycontactsCollection1();
            for (Companycontacts companycontactsCollection1OrphanCheckCompanycontacts : companycontactsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companycontacts " + companycontactsCollection1OrphanCheckCompanycontacts + " in its companycontactsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companyemployee> companyemployeeCollectionOrphanCheck = companyemployee.getCompanyemployeeCollection();
            for (Companyemployee companyemployeeCollectionOrphanCheckCompanyemployee : companyemployeeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyemployee " + companyemployeeCollectionOrphanCheckCompanyemployee + " in its companyemployeeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companyemployee> companyemployeeCollection1OrphanCheck = companyemployee.getCompanyemployeeCollection1();
            for (Companyemployee companyemployeeCollection1OrphanCheckCompanyemployee : companyemployeeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyemployee " + companyemployeeCollection1OrphanCheckCompanyemployee + " in its companyemployeeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companyproducttype> companyproducttypeCollectionOrphanCheck = companyemployee.getCompanyproducttypeCollection();
            for (Companyproducttype companyproducttypeCollectionOrphanCheckCompanyproducttype : companyproducttypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyproducttype " + companyproducttypeCollectionOrphanCheckCompanyproducttype + " in its companyproducttypeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companyproducttype> companyproducttypeCollection1OrphanCheck = companyemployee.getCompanyproducttypeCollection1();
            for (Companyproducttype companyproducttypeCollection1OrphanCheckCompanyproducttype : companyproducttypeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyproducttype " + companyproducttypeCollection1OrphanCheckCompanyproducttype + " in its companyproducttypeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmcampaign> crmcampaignCollectionOrphanCheck = companyemployee.getCrmcampaignCollection();
            for (Crmcampaign crmcampaignCollectionOrphanCheckCrmcampaign : crmcampaignCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaign " + crmcampaignCollectionOrphanCheckCrmcampaign + " in its crmcampaignCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmcampaign> crmcampaignCollection1OrphanCheck = companyemployee.getCrmcampaignCollection1();
            for (Crmcampaign crmcampaignCollection1OrphanCheckCrmcampaign : crmcampaignCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaign " + crmcampaignCollection1OrphanCheckCrmcampaign + " in its crmcampaignCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmmessagehistory> crmmessagehistoryCollectionOrphanCheck = companyemployee.getCrmmessagehistoryCollection();
            for (Crmmessagehistory crmmessagehistoryCollectionOrphanCheckCrmmessagehistory : crmmessagehistoryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmmessagehistory " + crmmessagehistoryCollectionOrphanCheckCrmmessagehistory + " in its crmmessagehistoryCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmmessagehistory> crmmessagehistoryCollection1OrphanCheck = companyemployee.getCrmmessagehistoryCollection1();
            for (Crmmessagehistory crmmessagehistoryCollection1OrphanCheckCrmmessagehistory : crmmessagehistoryCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmmessagehistory " + crmmessagehistoryCollection1OrphanCheckCrmmessagehistory + " in its crmmessagehistoryCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollectionOrphanCheck = companyemployee.getCompanyaccountstackdocsCollection();
            for (Companyaccountstackdocs companyaccountstackdocsCollectionOrphanCheckCompanyaccountstackdocs : companyaccountstackdocsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyaccountstackdocs " + companyaccountstackdocsCollectionOrphanCheckCompanyaccountstackdocs + " in its companyaccountstackdocsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companyaccountstackdocs> companyaccountstackdocsCollection1OrphanCheck = companyemployee.getCompanyaccountstackdocsCollection1();
            for (Companyaccountstackdocs companyaccountstackdocsCollection1OrphanCheckCompanyaccountstackdocs : companyaccountstackdocsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyaccountstackdocs " + companyaccountstackdocsCollection1OrphanCheckCompanyaccountstackdocs + " in its companyaccountstackdocsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmlabor> crmlaborCollectionOrphanCheck = companyemployee.getCrmlaborCollection();
            for (Crmlabor crmlaborCollectionOrphanCheckCrmlabor : crmlaborCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmlabor " + crmlaborCollectionOrphanCheckCrmlabor + " in its crmlaborCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmlabor> crmlaborCollection1OrphanCheck = companyemployee.getCrmlaborCollection1();
            for (Crmlabor crmlaborCollection1OrphanCheckCrmlabor : crmlaborCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmlabor " + crmlaborCollection1OrphanCheckCrmlabor + " in its crmlaborCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Customerbank> customerbankCollectionOrphanCheck = companyemployee.getCustomerbankCollection();
            for (Customerbank customerbankCollectionOrphanCheckCustomerbank : customerbankCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Customerbank " + customerbankCollectionOrphanCheckCustomerbank + " in its customerbankCollection field has a non-nullable companyemployee field.");
            }
            Collection<Customerbank> customerbankCollection1OrphanCheck = companyemployee.getCustomerbankCollection1();
            for (Customerbank customerbankCollection1OrphanCheckCustomerbank : customerbankCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Customerbank " + customerbankCollection1OrphanCheckCustomerbank + " in its customerbankCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollectionOrphanCheck = companyemployee.getCompnaypaymentcurrencyCollection();
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollectionOrphanCheckCompnaypaymentcurrency : compnaypaymentcurrencyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Compnaypaymentcurrency " + compnaypaymentcurrencyCollectionOrphanCheckCompnaypaymentcurrency + " in its compnaypaymentcurrencyCollection field has a non-nullable companyemployee field.");
            }
            Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollection1OrphanCheck = companyemployee.getCompnaypaymentcurrencyCollection1();
            for (Compnaypaymentcurrency compnaypaymentcurrencyCollection1OrphanCheckCompnaypaymentcurrency : compnaypaymentcurrencyCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Compnaypaymentcurrency " + compnaypaymentcurrencyCollection1OrphanCheckCompnaypaymentcurrency + " in its compnaypaymentcurrencyCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmproject> crmprojectCollectionOrphanCheck = companyemployee.getCrmprojectCollection();
            for (Crmproject crmprojectCollectionOrphanCheckCrmproject : crmprojectCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmproject " + crmprojectCollectionOrphanCheckCrmproject + " in its crmprojectCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmproject> crmprojectCollection1OrphanCheck = companyemployee.getCrmprojectCollection1();
            for (Crmproject crmprojectCollection1OrphanCheckCrmproject : crmprojectCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmproject " + crmprojectCollection1OrphanCheckCrmproject + " in its crmprojectCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmproject> crmprojectCollection2OrphanCheck = companyemployee.getCrmprojectCollection2();
            for (Crmproject crmprojectCollection2OrphanCheckCrmproject : crmprojectCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmproject " + crmprojectCollection2OrphanCheckCrmproject + " in its crmprojectCollection2 field has a non-nullable companyemployee2 field.");
            }
            Collection<Companyemployeeaddress> companyemployeeaddressCollectionOrphanCheck = companyemployee.getCompanyemployeeaddressCollection();
            for (Companyemployeeaddress companyemployeeaddressCollectionOrphanCheckCompanyemployeeaddress : companyemployeeaddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyemployeeaddress " + companyemployeeaddressCollectionOrphanCheckCompanyemployeeaddress + " in its companyemployeeaddressCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companyemployeeaddress> companyemployeeaddressCollection1OrphanCheck = companyemployee.getCompanyemployeeaddressCollection1();
            for (Companyemployeeaddress companyemployeeaddressCollection1OrphanCheckCompanyemployeeaddress : companyemployeeaddressCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyemployeeaddress " + companyemployeeaddressCollection1OrphanCheckCompanyemployeeaddress + " in its companyemployeeaddressCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companyemployeeaddress> companyemployeeaddressCollection2OrphanCheck = companyemployee.getCompanyemployeeaddressCollection2();
            for (Companyemployeeaddress companyemployeeaddressCollection2OrphanCheckCompanyemployeeaddress : companyemployeeaddressCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyemployeeaddress " + companyemployeeaddressCollection2OrphanCheckCompanyemployeeaddress + " in its companyemployeeaddressCollection2 field has a non-nullable companyemployee2 field.");
            }
            Collection<Crmforumdocs> crmforumdocsCollectionOrphanCheck = companyemployee.getCrmforumdocsCollection();
            for (Crmforumdocs crmforumdocsCollectionOrphanCheckCrmforumdocs : crmforumdocsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmforumdocs " + crmforumdocsCollectionOrphanCheckCrmforumdocs + " in its crmforumdocsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companyproduct> companyproductCollectionOrphanCheck = companyemployee.getCompanyproductCollection();
            for (Companyproduct companyproductCollectionOrphanCheckCompanyproduct : companyproductCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyproduct " + companyproductCollectionOrphanCheckCompanyproduct + " in its companyproductCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companyproduct> companyproductCollection1OrphanCheck = companyemployee.getCompanyproductCollection1();
            for (Companyproduct companyproductCollection1OrphanCheckCompanyproduct : companyproductCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyproduct " + companyproductCollection1OrphanCheckCompanyproduct + " in its companyproductCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Employeedesignation> employeedesignationCollectionOrphanCheck = companyemployee.getEmployeedesignationCollection();
            for (Employeedesignation employeedesignationCollectionOrphanCheckEmployeedesignation : employeedesignationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Employeedesignation " + employeedesignationCollectionOrphanCheckEmployeedesignation + " in its employeedesignationCollection field has a non-nullable companyemployee field.");
            }
            Collection<Employeedesignation> employeedesignationCollection1OrphanCheck = companyemployee.getEmployeedesignationCollection1();
            for (Employeedesignation employeedesignationCollection1OrphanCheckEmployeedesignation : employeedesignationCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Employeedesignation " + employeedesignationCollection1OrphanCheckEmployeedesignation + " in its employeedesignationCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companyaccountstack> companyaccountstackCollectionOrphanCheck = companyemployee.getCompanyaccountstackCollection();
            for (Companyaccountstack companyaccountstackCollectionOrphanCheckCompanyaccountstack : companyaccountstackCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyaccountstack " + companyaccountstackCollectionOrphanCheckCompanyaccountstack + " in its companyaccountstackCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companyaccountstack> companyaccountstackCollection1OrphanCheck = companyemployee.getCompanyaccountstackCollection1();
            for (Companyaccountstack companyaccountstackCollection1OrphanCheckCompanyaccountstack : companyaccountstackCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyaccountstack " + companyaccountstackCollection1OrphanCheckCompanyaccountstack + " in its companyaccountstackCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmprojectteammembers> crmprojectteammembersCollectionOrphanCheck = companyemployee.getCrmprojectteammembersCollection();
            for (Crmprojectteammembers crmprojectteammembersCollectionOrphanCheckCrmprojectteammembers : crmprojectteammembersCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectteammembers " + crmprojectteammembersCollectionOrphanCheckCrmprojectteammembers + " in its crmprojectteammembersCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmprojectteammembers> crmprojectteammembersCollection1OrphanCheck = companyemployee.getCrmprojectteammembersCollection1();
            for (Crmprojectteammembers crmprojectteammembersCollection1OrphanCheckCrmprojectteammembers : crmprojectteammembersCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectteammembers " + crmprojectteammembersCollection1OrphanCheckCrmprojectteammembers + " in its crmprojectteammembersCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmprojectteammembers> crmprojectteammembersCollection2OrphanCheck = companyemployee.getCrmprojectteammembersCollection2();
            for (Crmprojectteammembers crmprojectteammembersCollection2OrphanCheckCrmprojectteammembers : crmprojectteammembersCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectteammembers " + crmprojectteammembersCollection2OrphanCheckCrmprojectteammembers + " in its crmprojectteammembersCollection2 field has a non-nullable companyemployee2 field.");
            }
            Collection<Componentattachments> componentattachmentsCollectionOrphanCheck = companyemployee.getComponentattachmentsCollection();
            for (Componentattachments componentattachmentsCollectionOrphanCheckComponentattachments : componentattachmentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Componentattachments " + componentattachmentsCollectionOrphanCheckComponentattachments + " in its componentattachmentsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Componentattachments> componentattachmentsCollection1OrphanCheck = companyemployee.getComponentattachmentsCollection1();
            for (Componentattachments componentattachmentsCollection1OrphanCheckComponentattachments : componentattachmentsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Componentattachments " + componentattachmentsCollection1OrphanCheckComponentattachments + " in its componentattachmentsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollectionOrphanCheck = companyemployee.getCrmvisitorcontactsaddressCollection();
            for (Crmvisitorcontactsaddress crmvisitorcontactsaddressCollectionOrphanCheckCrmvisitorcontactsaddress : crmvisitorcontactsaddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmvisitorcontactsaddress " + crmvisitorcontactsaddressCollectionOrphanCheckCrmvisitorcontactsaddress + " in its crmvisitorcontactsaddressCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmvisitorcontacts> crmvisitorcontactsCollectionOrphanCheck = companyemployee.getCrmvisitorcontactsCollection();
            for (Crmvisitorcontacts crmvisitorcontactsCollectionOrphanCheckCrmvisitorcontacts : crmvisitorcontactsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmvisitorcontacts " + crmvisitorcontactsCollectionOrphanCheckCrmvisitorcontacts + " in its crmvisitorcontactsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmvisitorcontacts> crmvisitorcontactsCollection1OrphanCheck = companyemployee.getCrmvisitorcontactsCollection1();
            for (Crmvisitorcontacts crmvisitorcontactsCollection1OrphanCheckCrmvisitorcontacts : crmvisitorcontactsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmvisitorcontacts " + crmvisitorcontactsCollection1OrphanCheckCrmvisitorcontacts + " in its crmvisitorcontactsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companyaccountstackcd> companyaccountstackcdCollectionOrphanCheck = companyemployee.getCompanyaccountstackcdCollection();
            for (Companyaccountstackcd companyaccountstackcdCollectionOrphanCheckCompanyaccountstackcd : companyaccountstackcdCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyaccountstackcd " + companyaccountstackcdCollectionOrphanCheckCompanyaccountstackcd + " in its companyaccountstackcdCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companyaccountstackcd> companyaccountstackcdCollection1OrphanCheck = companyemployee.getCompanyaccountstackcdCollection1();
            for (Companyaccountstackcd companyaccountstackcdCollection1OrphanCheckCompanyaccountstackcd : companyaccountstackcdCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyaccountstackcd " + companyaccountstackcdCollection1OrphanCheckCompanyaccountstackcd + " in its companyaccountstackcdCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollectionOrphanCheck = companyemployee.getCrmprojectticketmanagementCollection();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollectionOrphanCheckCrmprojectticketmanagement : crmprojectticketmanagementCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectticketmanagement " + crmprojectticketmanagementCollectionOrphanCheckCrmprojectticketmanagement + " in its crmprojectticketmanagementCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection1OrphanCheck = companyemployee.getCrmprojectticketmanagementCollection1();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection1OrphanCheckCrmprojectticketmanagement : crmprojectticketmanagementCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectticketmanagement " + crmprojectticketmanagementCollection1OrphanCheckCrmprojectticketmanagement + " in its crmprojectticketmanagementCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection2OrphanCheck = companyemployee.getCrmprojectticketmanagementCollection2();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection2OrphanCheckCrmprojectticketmanagement : crmprojectticketmanagementCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectticketmanagement " + crmprojectticketmanagementCollection2OrphanCheckCrmprojectticketmanagement + " in its crmprojectticketmanagementCollection2 field has a non-nullable companyemployee2 field.");
            }
            Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection3OrphanCheck = companyemployee.getCrmprojectticketmanagementCollection3();
            for (Crmprojectticketmanagement crmprojectticketmanagementCollection3OrphanCheckCrmprojectticketmanagement : crmprojectticketmanagementCollection3OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectticketmanagement " + crmprojectticketmanagementCollection3OrphanCheckCrmprojectticketmanagement + " in its crmprojectticketmanagementCollection3 field has a non-nullable companyemployee3 field.");
            }
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollectionOrphanCheck = companyemployee.getCrmprojectticketnotificationCollection();
            for (Crmprojectticketnotification crmprojectticketnotificationCollectionOrphanCheckCrmprojectticketnotification : crmprojectticketnotificationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectticketnotification " + crmprojectticketnotificationCollectionOrphanCheckCrmprojectticketnotification + " in its crmprojectticketnotificationCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection1OrphanCheck = companyemployee.getCrmprojectticketnotificationCollection1();
            for (Crmprojectticketnotification crmprojectticketnotificationCollection1OrphanCheckCrmprojectticketnotification : crmprojectticketnotificationCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectticketnotification " + crmprojectticketnotificationCollection1OrphanCheckCrmprojectticketnotification + " in its crmprojectticketnotificationCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection2OrphanCheck = companyemployee.getCrmprojectticketnotificationCollection2();
            for (Crmprojectticketnotification crmprojectticketnotificationCollection2OrphanCheckCrmprojectticketnotification : crmprojectticketnotificationCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectticketnotification " + crmprojectticketnotificationCollection2OrphanCheckCrmprojectticketnotification + " in its crmprojectticketnotificationCollection2 field has a non-nullable companyemployee2 field.");
            }
            Collection<Crmvisitor> crmvisitorCollectionOrphanCheck = companyemployee.getCrmvisitorCollection();
            for (Crmvisitor crmvisitorCollectionOrphanCheckCrmvisitor : crmvisitorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmvisitor " + crmvisitorCollectionOrphanCheckCrmvisitor + " in its crmvisitorCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmvisitor> crmvisitorCollection1OrphanCheck = companyemployee.getCrmvisitorCollection1();
            for (Crmvisitor crmvisitorCollection1OrphanCheckCrmvisitor : crmvisitorCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmvisitor " + crmvisitorCollection1OrphanCheckCrmvisitor + " in its crmvisitorCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmworkordertype> crmworkordertypeCollectionOrphanCheck = companyemployee.getCrmworkordertypeCollection();
            for (Crmworkordertype crmworkordertypeCollectionOrphanCheckCrmworkordertype : crmworkordertypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmworkordertype " + crmworkordertypeCollectionOrphanCheckCrmworkordertype + " in its crmworkordertypeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmworkordertype> crmworkordertypeCollection1OrphanCheck = companyemployee.getCrmworkordertypeCollection1();
            for (Crmworkordertype crmworkordertypeCollection1OrphanCheckCrmworkordertype : crmworkordertypeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmworkordertype " + crmworkordertypeCollection1OrphanCheckCrmworkordertype + " in its crmworkordertypeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmlabortype> crmlabortypeCollectionOrphanCheck = companyemployee.getCrmlabortypeCollection();
            for (Crmlabortype crmlabortypeCollectionOrphanCheckCrmlabortype : crmlabortypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmlabortype " + crmlabortypeCollectionOrphanCheckCrmlabortype + " in its crmlabortypeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmlabortype> crmlabortypeCollection1OrphanCheck = companyemployee.getCrmlabortypeCollection1();
            for (Crmlabortype crmlabortypeCollection1OrphanCheckCrmlabortype : crmlabortypeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmlabortype " + crmlabortypeCollection1OrphanCheckCrmlabortype + " in its crmlabortypeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmbillingtype> crmbillingtypeCollectionOrphanCheck = companyemployee.getCrmbillingtypeCollection();
            for (Crmbillingtype crmbillingtypeCollectionOrphanCheckCrmbillingtype : crmbillingtypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmbillingtype " + crmbillingtypeCollectionOrphanCheckCrmbillingtype + " in its crmbillingtypeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmbillingtype> crmbillingtypeCollection1OrphanCheck = companyemployee.getCrmbillingtypeCollection1();
            for (Crmbillingtype crmbillingtypeCollection1OrphanCheckCrmbillingtype : crmbillingtypeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmbillingtype " + crmbillingtypeCollection1OrphanCheckCrmbillingtype + " in its crmbillingtypeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companyproductcategory> companyproductcategoryCollectionOrphanCheck = companyemployee.getCompanyproductcategoryCollection();
            for (Companyproductcategory companyproductcategoryCollectionOrphanCheckCompanyproductcategory : companyproductcategoryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyproductcategory " + companyproductcategoryCollectionOrphanCheckCompanyproductcategory + " in its companyproductcategoryCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companyproductcategory> companyproductcategoryCollection1OrphanCheck = companyemployee.getCompanyproductcategoryCollection1();
            for (Companyproductcategory companyproductcategoryCollection1OrphanCheckCompanyproductcategory : companyproductcategoryCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyproductcategory " + companyproductcategoryCollection1OrphanCheckCompanyproductcategory + " in its companyproductcategoryCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmprojecttask> crmprojecttaskCollectionOrphanCheck = companyemployee.getCrmprojecttaskCollection();
            for (Crmprojecttask crmprojecttaskCollectionOrphanCheckCrmprojecttask : crmprojecttaskCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojecttask " + crmprojecttaskCollectionOrphanCheckCrmprojecttask + " in its crmprojecttaskCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmprojecttask> crmprojecttaskCollection1OrphanCheck = companyemployee.getCrmprojecttaskCollection1();
            for (Crmprojecttask crmprojecttaskCollection1OrphanCheckCrmprojecttask : crmprojecttaskCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojecttask " + crmprojecttaskCollection1OrphanCheckCrmprojecttask + " in its crmprojecttaskCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmcampaignprops> crmcampaignpropsCollectionOrphanCheck = companyemployee.getCrmcampaignpropsCollection();
            for (Crmcampaignprops crmcampaignpropsCollectionOrphanCheckCrmcampaignprops : crmcampaignpropsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaignprops " + crmcampaignpropsCollectionOrphanCheckCrmcampaignprops + " in its crmcampaignpropsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmcampaignprops> crmcampaignpropsCollection1OrphanCheck = companyemployee.getCrmcampaignpropsCollection1();
            for (Crmcampaignprops crmcampaignpropsCollection1OrphanCheckCrmcampaignprops : crmcampaignpropsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmcampaignprops " + crmcampaignpropsCollection1OrphanCheckCrmcampaignprops + " in its crmcampaignpropsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Customercontactsaddress> customercontactsaddressCollectionOrphanCheck = companyemployee.getCustomercontactsaddressCollection();
            for (Customercontactsaddress customercontactsaddressCollectionOrphanCheckCustomercontactsaddress : customercontactsaddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Customercontactsaddress " + customercontactsaddressCollectionOrphanCheckCustomercontactsaddress + " in its customercontactsaddressCollection field has a non-nullable companyemployee field.");
            }
            Collection<Customercontactsaddress> customercontactsaddressCollection1OrphanCheck = companyemployee.getCustomercontactsaddressCollection1();
            for (Customercontactsaddress customercontactsaddressCollection1OrphanCheckCustomercontactsaddress : customercontactsaddressCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Customercontactsaddress " + customercontactsaddressCollection1OrphanCheckCustomercontactsaddress + " in its customercontactsaddressCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Customercategory> customercategoryCollectionOrphanCheck = companyemployee.getCustomercategoryCollection();
            for (Customercategory customercategoryCollectionOrphanCheckCustomercategory : customercategoryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Customercategory " + customercategoryCollectionOrphanCheckCustomercategory + " in its customercategoryCollection field has a non-nullable companyemployee field.");
            }
            Collection<Customercategory> customercategoryCollection1OrphanCheck = companyemployee.getCustomercategoryCollection1();
            for (Customercategory customercategoryCollection1OrphanCheckCustomercategory : customercategoryCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Customercategory " + customercategoryCollection1OrphanCheckCustomercategory + " in its customercategoryCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Productcomponents> productcomponentsCollectionOrphanCheck = companyemployee.getProductcomponentsCollection();
            for (Productcomponents productcomponentsCollectionOrphanCheckProductcomponents : productcomponentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Productcomponents " + productcomponentsCollectionOrphanCheckProductcomponents + " in its productcomponentsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Productcomponents> productcomponentsCollection1OrphanCheck = companyemployee.getProductcomponentsCollection1();
            for (Productcomponents productcomponentsCollection1OrphanCheckProductcomponents : productcomponentsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Productcomponents " + productcomponentsCollection1OrphanCheckProductcomponents + " in its productcomponentsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmexpensetype> crmexpensetypeCollectionOrphanCheck = companyemployee.getCrmexpensetypeCollection();
            for (Crmexpensetype crmexpensetypeCollectionOrphanCheckCrmexpensetype : crmexpensetypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmexpensetype " + crmexpensetypeCollectionOrphanCheckCrmexpensetype + " in its crmexpensetypeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmexpensetype> crmexpensetypeCollection1OrphanCheck = companyemployee.getCrmexpensetypeCollection1();
            for (Crmexpensetype crmexpensetypeCollection1OrphanCheckCrmexpensetype : crmexpensetypeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmexpensetype " + crmexpensetypeCollection1OrphanCheckCrmexpensetype + " in its crmexpensetypeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companypayments> companypaymentsCollectionOrphanCheck = companyemployee.getCompanypaymentsCollection();
            for (Companypayments companypaymentsCollectionOrphanCheckCompanypayments : companypaymentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companypayments " + companypaymentsCollectionOrphanCheckCompanypayments + " in its companypaymentsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companypayments> companypaymentsCollection1OrphanCheck = companyemployee.getCompanypaymentsCollection1();
            for (Companypayments companypaymentsCollection1OrphanCheckCompanypayments : companypaymentsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companypayments " + companypaymentsCollection1OrphanCheckCompanypayments + " in its companypaymentsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionOrphanCheck = companyemployee.getCrmworkorderresolutionCollection();
            for (Crmworkorderresolution crmworkorderresolutionCollectionOrphanCheckCrmworkorderresolution : crmworkorderresolutionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmworkorderresolution " + crmworkorderresolutionCollectionOrphanCheckCrmworkorderresolution + " in its crmworkorderresolutionCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmworkorderresolution> crmworkorderresolutionCollection1OrphanCheck = companyemployee.getCrmworkorderresolutionCollection1();
            for (Crmworkorderresolution crmworkorderresolutionCollection1OrphanCheckCrmworkorderresolution : crmworkorderresolutionCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmworkorderresolution " + crmworkorderresolutionCollection1OrphanCheckCrmworkorderresolution + " in its crmworkorderresolutionCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Company> companyCollectionOrphanCheck = companyemployee.getCompanyCollection();
            for (Company companyCollectionOrphanCheckCompany : companyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Company " + companyCollectionOrphanCheckCompany + " in its companyCollection field has a non-nullable companyemployee field.");
            }
            Collection<Company> companyCollection1OrphanCheck = companyemployee.getCompanyCollection1();
            for (Company companyCollection1OrphanCheckCompany : companyCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Company " + companyCollection1OrphanCheckCompany + " in its companyCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companyaddresstype> companyaddresstypeCollectionOrphanCheck = companyemployee.getCompanyaddresstypeCollection();
            for (Companyaddresstype companyaddresstypeCollectionOrphanCheckCompanyaddresstype : companyaddresstypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyaddresstype " + companyaddresstypeCollectionOrphanCheckCompanyaddresstype + " in its companyaddresstypeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companyaddresstype> companyaddresstypeCollection1OrphanCheck = companyemployee.getCompanyaddresstypeCollection1();
            for (Companyaddresstype companyaddresstypeCollection1OrphanCheckCompanyaddresstype : companyaddresstypeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companyaddresstype " + companyaddresstypeCollection1OrphanCheckCompanyaddresstype + " in its companyaddresstypeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmemployeecontacts> crmemployeecontactsCollectionOrphanCheck = companyemployee.getCrmemployeecontactsCollection();
            for (Crmemployeecontacts crmemployeecontactsCollectionOrphanCheckCrmemployeecontacts : crmemployeecontactsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmemployeecontacts " + crmemployeecontactsCollectionOrphanCheckCrmemployeecontacts + " in its crmemployeecontactsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Approval> approvalCollectionOrphanCheck = companyemployee.getApprovalCollection();
            for (Approval approvalCollectionOrphanCheckApproval : approvalCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Approval " + approvalCollectionOrphanCheckApproval + " in its approvalCollection field has a non-nullable companyemployee field.");
            }
            Collection<Approval> approvalCollection1OrphanCheck = companyemployee.getApprovalCollection1();
            for (Approval approvalCollection1OrphanCheckApproval : approvalCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Approval " + approvalCollection1OrphanCheckApproval + " in its approvalCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Approval> approvalCollection2OrphanCheck = companyemployee.getApprovalCollection2();
            for (Approval approvalCollection2OrphanCheckApproval : approvalCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Approval " + approvalCollection2OrphanCheckApproval + " in its approvalCollection2 field has a non-nullable companyemployee2 field.");
            }
            Collection<Workorderinstructions> workorderinstructionsCollectionOrphanCheck = companyemployee.getWorkorderinstructionsCollection();
            for (Workorderinstructions workorderinstructionsCollectionOrphanCheckWorkorderinstructions : workorderinstructionsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Workorderinstructions " + workorderinstructionsCollectionOrphanCheckWorkorderinstructions + " in its workorderinstructionsCollection field has a non-nullable companyemployee field.");
            }
            Collection<Workorderinstructions> workorderinstructionsCollection1OrphanCheck = companyemployee.getWorkorderinstructionsCollection1();
            for (Workorderinstructions workorderinstructionsCollection1OrphanCheckWorkorderinstructions : workorderinstructionsCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Workorderinstructions " + workorderinstructionsCollection1OrphanCheckWorkorderinstructions + " in its workorderinstructionsCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companycustomer> companycustomerCollectionOrphanCheck = companyemployee.getCompanycustomerCollection();
            for (Companycustomer companycustomerCollectionOrphanCheckCompanycustomer : companycustomerCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companycustomer " + companycustomerCollectionOrphanCheckCompanycustomer + " in its companycustomerCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companycustomer> companycustomerCollection1OrphanCheck = companyemployee.getCompanycustomerCollection1();
            for (Companycustomer companycustomerCollection1OrphanCheckCompanycustomer : companycustomerCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companycustomer " + companycustomerCollection1OrphanCheckCompanycustomer + " in its companycustomerCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmforum> crmforumCollectionOrphanCheck = companyemployee.getCrmforumCollection();
            for (Crmforum crmforumCollectionOrphanCheckCrmforum : crmforumCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmforum " + crmforumCollectionOrphanCheckCrmforum + " in its crmforumCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmprojectstatus> crmprojectstatusCollectionOrphanCheck = companyemployee.getCrmprojectstatusCollection();
            for (Crmprojectstatus crmprojectstatusCollectionOrphanCheckCrmprojectstatus : crmprojectstatusCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectstatus " + crmprojectstatusCollectionOrphanCheckCrmprojectstatus + " in its crmprojectstatusCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmprojectstatus> crmprojectstatusCollection1OrphanCheck = companyemployee.getCrmprojectstatusCollection1();
            for (Crmprojectstatus crmprojectstatusCollection1OrphanCheckCrmprojectstatus : crmprojectstatusCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprojectstatus " + crmprojectstatusCollection1OrphanCheckCrmprojectstatus + " in its crmprojectstatusCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmschedulerevnttype> crmschedulerevnttypeCollectionOrphanCheck = companyemployee.getCrmschedulerevnttypeCollection();
            for (Crmschedulerevnttype crmschedulerevnttypeCollectionOrphanCheckCrmschedulerevnttype : crmschedulerevnttypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmschedulerevnttype " + crmschedulerevnttypeCollectionOrphanCheckCrmschedulerevnttype + " in its crmschedulerevnttypeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmschedulerevnttype> crmschedulerevnttypeCollection1OrphanCheck = companyemployee.getCrmschedulerevnttypeCollection1();
            for (Crmschedulerevnttype crmschedulerevnttypeCollection1OrphanCheckCrmschedulerevnttype : crmschedulerevnttypeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmschedulerevnttype " + crmschedulerevnttypeCollection1OrphanCheckCrmschedulerevnttype + " in its crmschedulerevnttypeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmschedulerefcode> crmschedulerefcodeCollectionOrphanCheck = companyemployee.getCrmschedulerefcodeCollection();
            for (Crmschedulerefcode crmschedulerefcodeCollectionOrphanCheckCrmschedulerefcode : crmschedulerefcodeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmschedulerefcode " + crmschedulerefcodeCollectionOrphanCheckCrmschedulerefcode + " in its crmschedulerefcodeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Crmschedulerefcode> crmschedulerefcodeCollection1OrphanCheck = companyemployee.getCrmschedulerefcodeCollection1();
            for (Crmschedulerefcode crmschedulerefcodeCollection1OrphanCheckCrmschedulerefcode : crmschedulerefcodeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmschedulerefcode " + crmschedulerefcodeCollection1OrphanCheckCrmschedulerefcode + " in its crmschedulerefcodeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Componenttype> componenttypeCollectionOrphanCheck = companyemployee.getComponenttypeCollection();
            for (Componenttype componenttypeCollectionOrphanCheckComponenttype : componenttypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Componenttype " + componenttypeCollectionOrphanCheckComponenttype + " in its componenttypeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Componenttype> componenttypeCollection1OrphanCheck = companyemployee.getComponenttypeCollection1();
            for (Componenttype componenttypeCollection1OrphanCheckComponenttype : componenttypeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Componenttype " + componenttypeCollection1OrphanCheckComponenttype + " in its componenttypeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companypaymentscheme> companypaymentschemeCollectionOrphanCheck = companyemployee.getCompanypaymentschemeCollection();
            for (Companypaymentscheme companypaymentschemeCollectionOrphanCheckCompanypaymentscheme : companypaymentschemeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companypaymentscheme " + companypaymentschemeCollectionOrphanCheckCompanypaymentscheme + " in its companypaymentschemeCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companypaymentscheme> companypaymentschemeCollection1OrphanCheck = companyemployee.getCompanypaymentschemeCollection1();
            for (Companypaymentscheme companypaymentschemeCollection1OrphanCheckCompanypaymentscheme : companypaymentschemeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companypaymentscheme " + companypaymentschemeCollection1OrphanCheckCompanypaymentscheme + " in its companypaymentschemeCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Companycontactsaddress> companycontactsaddressCollectionOrphanCheck = companyemployee.getCompanycontactsaddressCollection();
            for (Companycontactsaddress companycontactsaddressCollectionOrphanCheckCompanycontactsaddress : companycontactsaddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companycontactsaddress " + companycontactsaddressCollectionOrphanCheckCompanycontactsaddress + " in its companycontactsaddressCollection field has a non-nullable companyemployee field.");
            }
            Collection<Companycontactsaddress> companycontactsaddressCollection1OrphanCheck = companyemployee.getCompanycontactsaddressCollection1();
            for (Companycontactsaddress companycontactsaddressCollection1OrphanCheckCompanycontactsaddress : companycontactsaddressCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Companycontactsaddress " + companycontactsaddressCollection1OrphanCheckCompanycontactsaddress + " in its companycontactsaddressCollection1 field has a non-nullable companyemployee1 field.");
            }
            Collection<Crmprofilesettings> crmprofilesettingsCollectionOrphanCheck = companyemployee.getCrmprofilesettingsCollection();
            for (Crmprofilesettings crmprofilesettingsCollectionOrphanCheckCrmprofilesettings : crmprofilesettingsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyemployee (" + companyemployee + ") cannot be destroyed since the Crmprofilesettings " + crmprofilesettingsCollectionOrphanCheckCrmprofilesettings + " in its crmprofilesettingsCollection field has a non-nullable companyemployee field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companyemployee.getCompany();
            if (company != null) {
                company.getCompanyemployeeCollection().remove(companyemployee);
                company = em.merge(company);
            }
            Companyemployee companyemployeeRel = companyemployee.getCompanyemployee();
            if (companyemployeeRel != null) {
                companyemployeeRel.getCompanyemployeeCollection().remove(companyemployee);
                companyemployeeRel = em.merge(companyemployeeRel);
            }
            Companyemployee companyemployee1 = companyemployee.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanyemployeeCollection().remove(companyemployee);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companyemployee);
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

    public List<Companyemployee> findCompanyemployeeEntities() {
        return findCompanyemployeeEntities(true, -1, -1);
    }

    public List<Companyemployee> findCompanyemployeeEntities(int maxResults, int firstResult) {
        return findCompanyemployeeEntities(false, maxResults, firstResult);
    }

    private List<Companyemployee> findCompanyemployeeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companyemployee.class));
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

    public Companyemployee findCompanyemployee(CompanyemployeePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companyemployee.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyemployeeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companyemployee> rt = cq.from(Companyemployee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
