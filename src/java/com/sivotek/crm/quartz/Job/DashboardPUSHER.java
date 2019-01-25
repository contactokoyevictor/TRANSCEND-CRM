/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.quartz.Job;

import com.sivotek.crm.persistent.dao.entities.Appointment;
import com.sivotek.crm.persistent.dao.entities.Approval;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyaddresstype;
import com.sivotek.crm.persistent.dao.entities.Companydepartment;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.CompanyemployeePK;
import com.sivotek.crm.persistent.dao.entities.Companyemployeeaddress;
import com.sivotek.crm.persistent.dao.entities.Companypayments;
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.Compnaypaymentcurrency;
import com.sivotek.crm.persistent.dao.entities.Crmcampaign;
import com.sivotek.crm.persistent.dao.entities.Crmmessagechannel;
import com.sivotek.crm.persistent.dao.entities.Crmmessagechanneltemplate;
import com.sivotek.crm.persistent.dao.entities.Crmproject;
import com.sivotek.crm.persistent.dao.entities.Crmprojecttask;
import com.sivotek.crm.persistent.dao.entities.Crmprojectteammembers;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketmanagement;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketnotification;
import com.sivotek.crm.persistent.dao.entities.Crmscheduler;
import com.sivotek.crm.persistent.dao.entities.Crmusers;
import com.sivotek.crm.persistent.dao.entities.Crmvisitor;
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.Crmworkorderresolution;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.Workorderinstructions;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.security.Encryptor;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class DashboardPUSHER {
    private static final Logger _log = Logger.getLogger(DashboardPUSHER.class.getName());
   private String status = "";
   private String statusmessage = "";
   private int companyID = 0;
   private Response response;

   //getters and setters
   public int getCompanyID() 
   {return companyID;}
   public void setCompanyID(int companyID) 
   {this.companyID = companyID;}
   
   public String getStatus() 
   {return status;}
   public void setStatus(String status) 
   {this.status = status;}
 
   public String getStatusmessage() 
   {return statusmessage;}
   public void setStatusmessage(String statusmessage) 
   {this.statusmessage = statusmessage;}
   
   private String getElementStringValueFromList(String elementName, List elementList) {
       for (Object elementList1 : elementList) {
           JAXBElement e = (JAXBElement) elementList1;
           if (e.getName().getLocalPart().equals(elementName)) {
               return e.getValue().toString();
           }
       }
        return null;
    }
     
     //////////////////////////////////////////
   public List<Response.Page.Elements.Element> dashboard(List children, int publickey, int companyID){
          Company company = new Company();
          CompanyPK companyPK = new CompanyPK();
          companyPK.setCompanyid(companyID);
          companyPK.setPubkey(publickey);
          //
          String employeeid = getElementStringValueFromList("employeeid", children);
//          String callbackurl = getElementStringValueFromList("callbackurl", children);
//          String callbackaction = getElementStringValueFromList("callbackaction", children);
//          String sessionid = getElementStringValueFromList("sessionid", children);
        
          Companyemployee companyemployee = new Companyemployee();
          CompanyemployeePK companyemployeePK = new CompanyemployeePK();
          companyemployeePK.setPubkey(publickey);
          companyemployeePK.setId(Integer.parseInt(employeeid));
          CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
          CompanyJpaController companyJpaController = new CompanyJpaController();
          
          //create response Object Factory
          com.sivotek.crm.xsd.jaxb.response.ObjectFactory responseOF = new com.sivotek.crm.xsd.jaxb.response.ObjectFactory();
          //create response <Page> Object
          com.sivotek.crm.xsd.jaxb.response.Response.Page responsePage = responseOF.createResponsePage();
          //create response <elements> object
          com.sivotek.crm.xsd.jaxb.response.Response.Page.Elements responseElements = responseOF.createResponsePageElements();
          //initialize response object
          response = responseOF.createResponse();
          Response.Page.Elements.Element resElement = responseOF.createResponsePageElementsElement();
          List<Response.Page.Elements.Element> responseElementList = responseElements.getElement();
       
          try{
               company = companyJpaController.findCompany(companyPK);
               companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
               
              //fitch employee detail...
              if(company.getCompanyPK().getCompanyid() > 0 && companyemployee.getCompanyemployeePK().getId() > 0)
              {
               
                 resElement = responseOF.createResponsePageElementsElement();
                 resElement.setId("companyemployee");
                 resElement.setEmployeeid(companyemployee.getCompanyemployeePK().getId());
                 resElement.setDepartmentid(companyemployee.getCompanydepartment().getCompanydepartmentPK().getId());
                 resElement.setFirstname(companyemployee.getFirstName());
                 resElement.setLastname(companyemployee.getLastName());
                 resElement.setOthername(companyemployee.getOtherName());
                 resElement.setPhone(companyemployee.getPhone());
                 resElement.setFax(companyemployee.getFax());
                 resElement.setEmail(companyemployee.getEmail());
                 resElement.setDesignation(companyemployee.getDesignation());
                 resElement.setCompanyweb(companyemployee.getWeb());
                 resElement.setDescription(companyemployee.getDescription());
                 resElement.setElementstatus("OK");
                 resElement.setElementstatusmessage("Success");
                 //element to list
                 responseElementList.add(resElement);
                 
                 
                 //fitch company employee companyaddresstype
                if(company.getCompanyaddresstypeCollection().size() >= 0)
                {
                    Collection<Companyaddresstype> companyaddresstypeColl = company.getCompanyaddresstypeCollection();
                    for(Companyaddresstype addresstype : companyaddresstypeColl)
                    {
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("companyaddresstype");
                        resElement.setAddresstypeid(addresstype.getCompanyaddresstypePK().getId());
                        resElement.setAddresstypename(addresstype.getName());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                       responseElementList.add(resElement);
                    }
                     
                }//end employee companyaddresstype
                
                 //fitch company employee companydepartment
                if(company.getCompanydepartmentCollection().size() >= 0)
                {
                    Collection<Companydepartment> companydepartmentColl = company.getCompanydepartmentCollection();
                    for(Companydepartment companydepartment : companydepartmentColl)
                    {
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("companydepartment");
                        resElement.setDepartmentid(companydepartment.getCompanydepartmentPK().getId());
                        resElement.setName(companydepartment.getDepartmentName());
                        resElement.setCode(companydepartment.getDepartmentCode());
                        resElement.setDescription(companydepartment.getDescription());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                       responseElementList.add(resElement);
                    }
                     
                }//end employee companydepartment
                
                //fitch company employee compnaypaymentcurrency
                if(company.getCompnaypaymentcurrencyCollection().size() >= 0)
                {
                    Collection<Compnaypaymentcurrency> compnaypaymentcurrencyColl = company.getCompnaypaymentcurrencyCollection();
                    for(Compnaypaymentcurrency currency : compnaypaymentcurrencyColl)
                    {
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("compnaypaymentcurrency");
                        resElement.setCurrencyid(currency.getCompnaypaymentcurrencyPK().getId());
                        resElement.setCurrencyname(currency.getCurrencyName());
                        resElement.setCurrencycode(currency.getCurrencyCode());
                        resElement.setCurrencysymbol(currency.getCurrencySymbol());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                       responseElementList.add(resElement);
                    }
                     
                }//end employee compnaypaymentcurrency
                
                //fitch company employee companypayments
                if(company.getCompanypaymentsCollection().size() >= 0)
                {
                    Collection<Companypayments> CompanypaymentsColl = company.getCompanypaymentsCollection();
                    for(Companypayments payments : CompanypaymentsColl)
                    {
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("companypayments");
                        resElement.setPaymentid(payments.getCompanypaymentsPK().getId());
                        resElement.setCurrencyid(payments.getCompnaypaymentcurrency().getCompnaypaymentcurrencyPK().getId());
                        resElement.setAmount(payments.getAmount());
                        resElement.setName(payments.getCompanypaymentscheme().getSchemeName());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                       responseElementList.add(resElement);
                    }
                     
                }//end employee companypayments
                //
                //fitch company employee address
                if(companyemployee.getCompanyemployeeaddressCollection().size() >= 0)
                {
                    Collection<Companyemployeeaddress> companyemployeeaddressColl = companyemployee.getCompanyemployeeaddressCollection();
                    for(Companyemployeeaddress address : companyemployeeaddressColl)
                    {
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("companyemployeeaddress");
                        resElement.setEmployeeid(address.getCompanyemployee().getCompanyemployeePK().getId());
                        resElement.setAddresstypeid(address.getCompanyaddresstype().getCompanyaddresstypePK().getId());
                        resElement.setStreet(address.getStreet());
                        resElement.setZip(address.getZip());
                        resElement.setLocation(address.getLocation());
                        resElement.setPbox(address.getPbox());
                        resElement.setCountry(address.getCountry());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                       responseElementList.add(resElement);
                    }
                     
                }//end employee address
                
                //fitch company employee user details
               if(companyemployee.getCompany().getCrmusersCollection().size() >= 0)
               {
                   Collection<Crmusers> crmusersColl = companyemployee.getCompany().getCrmusersCollection();
                   for(Crmusers users : crmusersColl){
                      //////////////////
                     //
                    if(users.getEmployeeid() != null && users.getEmployeeid() == companyemployee.getCompanyemployeePK().getId())
                     { 
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("Users");
                        resElement.setUserid(users.getCrmusersPK().getId());
                        resElement.setEmployeeid(users.getEmployeeid());
                        resElement.setName(users.getCrmuser());
                        resElement.setPassword(this.decryptdata(users.getPasswd()));
                        resElement.setIsadmin(users.getIsAdmin());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                       ////
                       responseElementList.add(resElement);
                     }
                   }
               }//end..
               
              //fitch company employee appointments
              if(companyemployee.getAppointmentCollection().size() >= 0)
              {
                 Collection<Appointment> AppointmentColl = companyemployee.getAppointmentCollection();
                 for(Appointment appoint : AppointmentColl){
                     //////////////////
                     resElement = responseOF.createResponsePageElementsElement();
                     resElement.setId("Appoitment");
                     resElement.setAppointmentid(appoint.getAppointmentPK().getAppointmentid());
                     resElement.setEmployeeid(appoint.getCompanyemployee().getCompanyemployeePK().getId());
                     resElement.setReceipient(appoint.getCompanyemployee1().getCompanyemployeePK().getId());
                     resElement.setScheduletype(appoint.getCrmschedulerevnttype().getCrmschedulerevnttypePK().getEventtypeid());
                     resElement.setDescription(appoint.getDescription());
                     resElement.setTimeZoneid(appoint.getTimezoneid());
                     resElement.setStartdatetime(appoint.getStartdatetime());
                     resElement.setEnddatetime(appoint.getEnddatetime());
                     resElement.setElementstatus("OK");
                     resElement.setElementstatusmessage("Success");
                     ////
                     responseElementList.add(resElement);
                 }
                
              }//end of fitch company employee appointments
             
            //fitch company employee appointment Approval  
            if(companyemployee.getApprovalCollection().size() >= 0)
            {
                Collection<Approval> approvalColl = companyemployee.getApprovalCollection();
                for(Approval approv : approvalColl){
                    ////////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("Approval");
                    resElement.setAppointmentid(approv.getAppointment().getAppointmentPK().getAppointmentid());
                    resElement.setApprovedby(approv.getCompanyemployee().getCompanyemployeePK().getId());
                    resElement.setLastupdate(approv.getLastUpdate());
                    resElement.setComments(approv.getComments());
                    resElement.setElementstatus("OK");
                    resElement.setElementstatusmessage("Success");
                    ////
                    responseElementList.add(resElement);
                }
            }//end of fitch company employee appointment Approval   
            
            
            //fitch company employee message channels
            if(company.getCrmmessagechannelCollection().size() >= 0)
            {
               Collection<Crmmessagechannel> crmmessagechannelColl = company.getCrmmessagechannelCollection();
               for(Crmmessagechannel crmmessagechan : crmmessagechannelColl)
               {
                   ////////////////////
                      resElement = responseOF.createResponsePageElementsElement();
                      resElement.setId("Crmmessagechannel");
                      resElement.setChannelid(crmmessagechan.getCrmmessagechannelPK().getChannelid());
                      resElement.setChannelname(crmmessagechan.getChannelName());
                      resElement.setDescription(crmmessagechan.getChannelDescription());
                       
                      resElement.setElementstatus("OK");
                      resElement.setElementstatusmessage("Success");
                      ////
                      responseElementList.add(resElement);
              
               }
            }//end
            
            
            //fitch company employee message channel templates...
            if(companyemployee.getCrmmessagechanneltemplateCollection().size() >=0)
            {
                Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateColl = companyemployee.getCrmmessagechanneltemplateCollection();
                for(Crmmessagechanneltemplate crmmessagechanneltemplate : crmmessagechanneltemplateColl)
                {
                    ////////////////////
                      resElement = responseOF.createResponsePageElementsElement();
                      resElement.setId("Crmmessagechanneltemplate");
                      resElement.setChanneltemplateid(crmmessagechanneltemplate.getCrmmessagechanneltemplatePK().getChanneltemplateid());
                      resElement.setTemplatename(crmmessagechanneltemplate.getTemplateName());
                      resElement.setTemplatedescription(crmmessagechanneltemplate.getTemplateDescription());
                      resElement.setMessagebody(crmmessagechanneltemplate.getMessageBody());
                      resElement.setElementstatus("OK");
                      resElement.setElementstatusmessage("Success");
                      ////
                      responseElementList.add(resElement);
                }
            }
            
            
            
            //fitch company employee project ticket management...tickets assigned to this employee
            if(companyemployee.getCrmprojectticketmanagementCollection().size() >= 0)
            {
                Collection<Crmprojectticketmanagement> ticketmanagementColl = companyemployee.getCrmprojectticketmanagementCollection();
                for(Crmprojectticketmanagement  ticketmanagement : ticketmanagementColl){
                    ////////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("crmprojectticket");
                    resElement.setTaskid(ticketmanagement.getCrmprojecttask().getCrmprojecttaskPK().getTaskid());
                    resElement.setProjectid(ticketmanagement.getCrmprojecttask().getCrmproject().getCrmprojectPK().getProjectid());
                    resElement.setEnvironment(ticketmanagement.getEnvironment());
                    resElement.setTicketstatus(ticketmanagement.getTicketstatus());
                    resElement.setPriority(ticketmanagement.getPriority());
                    resElement.setIdentifiedbyemployeeid(ticketmanagement.getCompanyemployee2().getCompanyemployeePK().getId());
                    resElement.setIssuesubject(ticketmanagement.getIssuesubject());
                    resElement.setTargetedresolutiondate(ticketmanagement.getTargetedResolutionDate());
                    resElement.setLastcomment(ticketmanagement.getLastcomment());
                    resElement.setAssignedto(ticketmanagement.getCompanyemployee1().getCompanyemployeePK().getId());
                    resElement.setProgress(ticketmanagement.getProgress());
                    
                    resElement.setElementstatus("OK");
                    resElement.setElementstatusmessage("Success");
                    ////
                    responseElementList.add(resElement);
                }
            }//end..
            
            //add workorder to the dashboard
            if(companyemployee.getCrmworkorderCollection().size() >= 0)
            {
              Collection<Crmworkorder> crmworkorderColl = companyemployee.getCrmworkorderCollection();
              for(Crmworkorder works : crmworkorderColl)
              {
                   ////////////////////
                   resElement = responseOF.createResponsePageElementsElement();
                   resElement.setId("crmworkorder");
                   resElement.setWorkorderid(works.getCrmworkorderPK().getId());
                   resElement.setEmployeeid(works.getCompanyemployee());
                   resElement.setIssueid(works.getCrmprojectticketmanagement().getCrmprojectticketmanagementPK().getIssueid());
                   resElement.setCustomerid(works.getCompanycustomer().getCompanycustomerPK().getId());
                   resElement.setProductid(works.getCompanyproduct().getCompanyproductPK().getId());
                   resElement.setComponentid(works.getProductcomponents().getProductcomponentsPK().getId());
                   resElement.setDescription(works.getDescription());
                   
                   //fitch workorder instructions for this workorder
                   if(works.getWorkorderinstructionsCollection().size() >= 0)
                   {
                       Collection<Workorderinstructions> instructionsColl = works.getWorkorderinstructionsCollection();
                       for(Workorderinstructions instruct : instructionsColl){
                           resElement.setInstruction(instruct.getInstruction());
                           resElement.setIsDone(instruct.getIsDone());
                           resElement.setComment(instruct.getComment());
                           resElement.setEmployeeid(instruct.getCompanyemployee());
                       }
                   }//end instructions
                   
              }
                
            }
            
            if(companyemployee.getCrmworkorderCollection1().size() >= 0)
            {
                Collection<Crmworkorder> crmworkorderColl = companyemployee.getCrmworkorderCollection1();
              for(Crmworkorder works : crmworkorderColl)
              {
                   ////////////////////
                   resElement = responseOF.createResponsePageElementsElement();
                   resElement.setId("crmworkorder");
                   resElement.setWorkorderid(works.getCrmworkorderPK().getId());
                   resElement.setEmployeeid(works.getCompanyemployee());
                   resElement.setIssueid(works.getCrmprojectticketmanagement().getCrmprojectticketmanagementPK().getIssueid());
                   resElement.setCustomerid(works.getCompanycustomer().getCompanycustomerPK().getId());
                   resElement.setProductid(works.getCompanyproduct().getCompanyproductPK().getId());
                   resElement.setComponentid(works.getProductcomponents().getProductcomponentsPK().getId());
                   resElement.setDescription(works.getDescription());
                   
                   //fitch workorder instructions for this workorder
                   if(works.getWorkorderinstructionsCollection().size() >= 0)
                   {
                       Collection<Workorderinstructions> instructionsColl = works.getWorkorderinstructionsCollection();
                       for(Workorderinstructions instruct : instructionsColl){
                           resElement.setInstruction(instruct.getInstruction());
                           resElement.setIsDone(instruct.getIsDone());
                           resElement.setComment(instruct.getComment());
                           resElement.setEmployeeid(instruct.getCompanyemployee());
                           ////
                           responseElementList.add(resElement); 
                       }
                   }//end instructions
                  
                  resElement.setElementstatus("OK");
                  resElement.setElementstatusmessage("Success");
                  ////
                 responseElementList.add(resElement); 
              }
            }
            
            //add workorder resolutions to the dashboard
            if(companyemployee.getCrmworkorderresolutionCollection().size() >= 0)
            {
                Collection<Crmworkorderresolution> resolutionColl = companyemployee.getCrmworkorderresolutionCollection();
                for(Crmworkorderresolution resolution : resolutionColl)
                {
                    ////////////////////
                   resElement = responseOF.createResponsePageElementsElement();
                   resElement.setId("crmworkorderresolution");
                   resElement.setEmployeeid(resolution.getCompanyemployee());
                   resElement.setWorkorderid(resolution.getCrmworkorder().getCrmworkorderPK().getId());
                   resElement.setBilltypeid(resolution.getCrmbillingtype().getCrmbillingtypePK().getId());
                   resElement.setWorkperformed(resolution.getWorkperformed());
                   resElement.setSpecialinstruction(resolution.getSpecialinstruction());
                   resElement.setCustomerfailure(resolution.getCustomersfailure());
                   resElement.setCorrectiveaction(resolution.getCorrectiveaction());
                   resElement.setFailurelocation(resolution.getFailurelocation());
                   resElement.setFailedassembly(resolution.getFailedassembly());
                   resElement.setHowfixed(resolution.getHowfixed());
                   resElement.setSymptom(resolution.getSymptom());
                   resElement.setRootcause(resolution.getRootcause());
                   resElement.setElementstatus("OK");
                   resElement.setElementstatusmessage("Success");
                   ////
                   responseElementList.add(resElement);  
                }
            }
            
            if(companyemployee.getCrmworkorderresolutionCollection1().size() >= 0)
            {
                Collection<Crmworkorderresolution> resolutionColl = companyemployee.getCrmworkorderresolutionCollection();
                for(Crmworkorderresolution resolution : resolutionColl)
                {
                    ////////////////////
                   resElement = responseOF.createResponsePageElementsElement();
                   resElement.setId("crmworkorderresolution");
                   resElement.setEmployeeid(resolution.getCompanyemployee());
                   resElement.setWorkorderid(resolution.getCrmworkorder().getCrmworkorderPK().getId());
                   resElement.setBilltypeid(resolution.getCrmbillingtype().getCrmbillingtypePK().getId());
                   resElement.setWorkperformed(resolution.getWorkperformed());
                   resElement.setSpecialinstruction(resolution.getSpecialinstruction());
                   resElement.setCustomerfailure(resolution.getCustomersfailure());
                   resElement.setCorrectiveaction(resolution.getCorrectiveaction());
                   resElement.setFailurelocation(resolution.getFailurelocation());
                   resElement.setFailedassembly(resolution.getFailedassembly());
                   resElement.setHowfixed(resolution.getHowfixed());
                   resElement.setSymptom(resolution.getSymptom());
                   resElement.setRootcause(resolution.getRootcause());
                   resElement.setElementstatus("OK");
                   resElement.setElementstatusmessage("Success");
                   ////
                   responseElementList.add(resElement);  
                }
            }
            
            //add company products to the dashboard
            if(company.getCompanyproductCollection().size() >= 0)
            {
                Collection<Companyproduct> productColl = company.getCompanyproductCollection();
                for(Companyproduct product : productColl)
                {
                    ////////////////////
                   resElement = responseOF.createResponsePageElementsElement();
                   resElement.setId("companyproducts"); 
                   resElement.setProductid(product.getCompanyproductPK().getId());
                   resElement.setName(product.getProductname());
                   resElement.setCategoryid(product.getCompanyproductcategory().getCompanyproductcategoryPK().getId());
                   
                   
                   resElement.setElementstatus("OK");
                   resElement.setElementstatusmessage("Success");
                   ////
                   responseElementList.add(resElement); 
                }
            }
            
            
            //add company products components to the dashboard
            if(company.getProductcomponentsCollection().size() >= 0)
            {
                Collection<Productcomponents> componentColl = company.getProductcomponentsCollection();
                for(Productcomponents component : componentColl)
                {
                    ////////////////////
                   resElement = responseOF.createResponsePageElementsElement();
                   resElement.setId("productcomponents"); 
                   resElement.setComponentid(component.getProductcomponentsPK().getId());
                   resElement.setName(component.getComponentname());
                   resElement.setCategoryid(component.getComponenttype().getComponenttypePK().getId());
                   
                   
                   resElement.setElementstatus("OK");
                   resElement.setElementstatusmessage("Success");
                   ////
                   responseElementList.add(resElement); 
                }
            }
            
            
            
            
            //fitch company employee project ticket management...tickets created by this employee
            if(companyemployee.getCrmprojectticketmanagementCollection1().size() >= 0)
            {
                Collection<Crmprojectticketmanagement> ticketmanagementColl = companyemployee.getCrmprojectticketmanagementCollection1();
                for(Crmprojectticketmanagement  ticketmanagement : ticketmanagementColl){
                    ////////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("crmprojectticket");
                    resElement.setTaskid(ticketmanagement.getCrmprojecttask().getCrmprojecttaskPK().getTaskid());
                    resElement.setProjectid(ticketmanagement.getCrmprojecttask().getCrmproject().getCrmprojectPK().getProjectid());
                    resElement.setEnvironment(ticketmanagement.getEnvironment());
                    resElement.setTicketstatus(ticketmanagement.getTicketstatus());
                    resElement.setDuration(ticketmanagement.getCrmprojecttask().getDuration());
                    resElement.setStatus(ticketmanagement.getCrmprojecttask().getStatus());
                    resElement.setDescription(ticketmanagement.getCrmprojecttask().getDescription());
                    resElement.setPriority(ticketmanagement.getPriority());
                    resElement.setIdentifiedbyemployeeid(ticketmanagement.getCompanyemployee2().getCompanyemployeePK().getId());
                    resElement.setIssuesubject(ticketmanagement.getIssuesubject());
                    resElement.setTargetedresolutiondate(ticketmanagement.getTargetedResolutionDate());
                    resElement.setLastcomment(ticketmanagement.getLastcomment());
                    resElement.setAssignedto(ticketmanagement.getCompanyemployee1().getCompanyemployeePK().getId());
                    resElement.setProgress(ticketmanagement.getProgress());
                    
                    resElement.setElementstatus("OK");
                    resElement.setElementstatusmessage("Success");
                    ////
                    responseElementList.add(resElement);
                }
            }//end..
            
            //fitch company employee project team members 
            if(companyemployee.getCrmprojectteammembersCollection().size() >= 0)
            {
                
                Collection<Crmprojectteammembers> crmprojectteammembersColls = companyemployee.getCrmprojectteammembersCollection();
                   for(Crmprojectteammembers mates : crmprojectteammembersColls)
                   {
                       System.out.println("Project team member id :::"+mates.getCrmprojectteammembersPK().getId());
                       resElement = responseOF.createResponsePageElementsElement();
                       resElement.setId("projectteammember");
                       resElement.setTeammemberid(mates.getCrmprojectteammembersPK().getId());
                       resElement.setProjectid(mates.getCrmproject().getCrmprojectPK().getProjectid());
                       resElement.setEmployeeid(mates.getCompanyemployee().getCompanyemployeePK().getId());
                       resElement.setElementstatus("OK");
                       resElement.setElementstatusmessage("Success");
                       responseElementList.add(resElement);
                   }
                
                
            }//end of fitch company employee project team members 
            
            //fitch company employee schedules
            if(companyemployee.getCrmschedulerCollection().size() >= 0){
                Collection<Crmscheduler> crmschedulerColl = companyemployee.getCrmschedulerCollection();
                for(Crmscheduler crmscheduler : crmschedulerColl){
                    
                    if(crmscheduler.getCompany().getCrmcampaignCollection().size() >= 0){
                        Collection<Crmcampaign> crmcampaignColl = crmscheduler.getCompany().getCrmcampaignCollection();
                        for(Crmcampaign crmcampaign : crmcampaignColl){
                            
                        if(crmcampaign.getCompany().getCompanyPK().getCompanyid() != 0 && crmcampaign.getCompany().getCompanyPK().getCompanyid() == companyemployee.getCompany().getCompanyPK().getCompanyid())
                        {   
                        ////////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("crmcampaign");
                        resElement.setCampaignid(crmcampaign.getCrmcampaignPK().getCampaignid());
                        resElement.setCampaignname(crmcampaign.getName());
                        resElement.setCampaignstatus(crmcampaign.getCrmcampaignstatus().getStatusname());
                        resElement.setValidfrom(crmcampaign.getValidfrom());
                        resElement.setValidto(crmcampaign.getValidto());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                        responseElementList.add(resElement);
                        }
                        
                      }
                    }
                    ////////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("Crmscheduler");
                    resElement.setEmployeeid(crmscheduler.getCompanyemployee().getCompanyemployeePK().getId());
                    resElement.setName(crmscheduler.getScheduleName());
                    resElement.setSchedulerid(crmscheduler.getCrmschedulerPK().getSchedulerid());
                    resElement.setScheduleGroup(crmscheduler.getScheduleGroup());
                    resElement.setSchedulerevnttypeid(crmscheduler.getEventType());
                    resElement.setActiveweekdays(crmscheduler.getActiveWeekdays());
                    resElement.setFrequencytype(crmscheduler.getFrequencyType());
                    resElement.setSchedulestatus(crmscheduler.getScheduleStatus());
                    resElement.setCallbackurl(crmscheduler.getPushUrl());
                    resElement.setCallbackaction(crmscheduler.getPushAction());
                    resElement.setColor(crmscheduler.getColor());
                    resElement.setLastrun(crmscheduler.getLastRun());
                    resElement.setNextrun(crmscheduler.getNextRun());
                    resElement.setDescription(crmscheduler.getDescription());
                    resElement.setElementstatus("OK");
                    resElement.setElementstatusmessage("Success");
                    ////
                    responseElementList.add(resElement);
                    ////
                    
                    Collection<Crmvisitor> crmvisitorColl = crmscheduler.getCrmvisitorCollection();
                    for(Crmvisitor crmvisitor : crmvisitorColl){
                         ////////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("crmvisitor");
                        resElement.setScheduleID(crmvisitor.getCrmvisitorPK().getVisitorid());
                        resElement.setVisitorid(crmvisitor.getCrmvisitorPK().getVisitorid());
                        resElement.setFirstname(crmvisitor.getFisrtName());
                        resElement.setLastname(crmvisitor.getLastName());
                        resElement.setOthername(crmvisitor.getOtherName());
                        resElement.setZip(crmvisitor.getZip());
                        resElement.setLocation(crmvisitor.getLocation());
                        resElement.setPbox(crmvisitor.getPbox());
                        resElement.setCountry(crmvisitor.getCountry());
                        resElement.setBrithday(crmvisitor.getBirddate());
                        resElement.setMobile(crmvisitor.getMobile());
                        resElement.setEmail(crmvisitor.getEmail());
                        resElement.setDescription(crmvisitor.getDescription());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                        responseElementList.add(resElement);
                        ////
                    }
                    
                }
                
            }//end of fitch company employee schedules
            
            //fitch company employee project ticket notification
            if(companyemployee.getCrmprojectticketnotificationCollection().size() >= 0)
            {
                Collection<Crmprojectticketnotification> crmprojectticketnotificationColl = companyemployee.getCrmprojectticketnotificationCollection();
                for(Crmprojectticketnotification notification : crmprojectticketnotificationColl){
                     ////////////////////
                     resElement = responseOF.createResponsePageElementsElement();
                     resElement.setId("crmprojectticketnotification");
                     resElement.setNotificationid(notification.getCrmprojectticketnotificationPK().getId());
                     resElement.setNotificationheader(notification.getNotificationheader());
                     resElement.setNotificationbody(notification.getNotificationbody());
                     resElement.setElementstatus("OK");
                     resElement.setElementstatusmessage("Success");
                     ////
                     responseElementList.add(resElement);
                }
                
            }//end..
            
            
            //fitch company employee project  
            if(companyemployee.getCrmprojectCollection().size() >= 0)
            {
                System.out.println("Crmproject :"+companyemployee.getCrmprojectCollection().size());
                Collection<Crmproject> crmprojectColl = companyemployee.getCrmprojectCollection();
                for(Crmproject crmproject : crmprojectColl){
                    ////////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("Crmproject");
                    resElement.setProjectid(crmproject.getCrmprojectPK().getProjectid());
                    resElement.setProjectnr(crmproject.getProjectnr());
                    resElement.setName(crmproject.getName());
                    resElement.setManager(crmproject.getCompanyemployee1().getCompanyemployeePK().getId());
                    resElement.setBudget(crmproject.getBudget());
                    resElement.setActualcost(crmproject.getActualcost());
                    resElement.setRemaincost(crmproject.getRemaincost());
                    resElement.setStartdate(crmproject.getValidfrom());
                    resElement.setEnddate(crmproject.getValidto());
                    
                    resElement.setElementstatus("OK");
                    resElement.setElementstatusmessage("Success");
                    ////
                    responseElementList.add(resElement);
                    
                 //fitch company employee project tasks 
                 if(crmproject.getCrmprojecttaskCollection().size() >=0)
                 {
                     Collection<Crmprojecttask> crmprojecttaskColl = crmproject.getCrmprojecttaskCollection();
                     for(Crmprojecttask crmprojecttask : crmprojecttaskColl)
                     {
                        ////////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("crmprojecttask"); 
                        resElement.setTaskid(crmprojecttask.getCrmprojecttaskPK().getTaskid());
                        resElement.setTasktype(crmprojecttask.getType());
                        resElement.setDuration(crmprojecttask.getDuration());
                        resElement.setDescription(crmprojecttask.getDescription());
                        resElement.setColor(crmprojecttask.getColor());
                        resElement.setName(crmprojecttask.getName());
                        resElement.setStatus(crmprojecttask.getStatus());
                        
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                        responseElementList.add(resElement);
                        
                     }
                 }
          }
                
      }//end of fitch company employee project  
     }
              
             
          }catch(Exception exs){
              _log.log(Level.WARNING, "Exception Occoured :(0)", exs.getMessage());
          }

       return responseElementList;
   }
   
     
    /**
     * Decrypt TRANSCEND-CRM data
     * @param data
     * @return generated token as a string 
     */
    private String decryptdata(String data) {
        Encryptor encryptor = new Encryptor("TranscendCRMv0.1.0");
        // Dencrypt
        String token = encryptor.decrypt(data);
        encryptor = null;
        return token;
    }
}