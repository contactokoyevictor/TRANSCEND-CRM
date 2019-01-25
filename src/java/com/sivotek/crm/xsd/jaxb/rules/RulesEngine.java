/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */
package com.sivotek.crm.xsd.jaxb.rules;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Crmlicense;
import com.sivotek.crm.persistent.dao.entities.Crmlicensecode;
import com.sivotek.crm.persistent.dao.entities.Crmschedulerevnttype;
import com.sivotek.crm.persistent.dao.entities.CrmschedulerevnttypePK;
import com.sivotek.crm.persistent.dao.entities.Crmusers;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmlicenseJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmlicensecodeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmschedulerevnttypeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmusersJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.AppointmentPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CategoryPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyaccountstackPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyaccountstackcdPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyaccountstackdocsPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyaddresstypePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanybankPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanycontactsPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanycontactsaddressPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanycustomerPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanydepartmentPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyemployeePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyemployeeaddressPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanypaymentsPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanypaymentschemePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyproductPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyproductcategoryPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyproducttypePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompnaypaymentcurrencyPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.ComponentattachmentsPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.ComponenttypePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.ContenttypesPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmbillingtypePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmcampaignPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmcampaigndocsPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmemployeenotePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmexpensePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmexpensetypePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmlaborPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmlabortypePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmmessagechannelPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmmessagechanneltemplatePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmmodulesPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmprojectPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmschedulerPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmusersPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmvisitorPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmworkorderPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmworkorderresolutionPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmworkordertypePrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CustomerbankPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CustomercategoryPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CustomercontactsPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CustomercontactsaddressPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.EmployeedesignationPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.ProductattachmentsPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.ProductcomponentsPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.TimeZonesPrep;
import com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.WorkorderinstructionsPrep;
import com.sivotek.crm.quartz.Job.DashboardPUSHER;
//import com.sivotek.crm.security.LicenseKeyManager;
import com.sivotek.crm.security.PublicKeyValidator;
import com.sivotek.crm.xsd.jaxb.prep.ejb.TranscendEJB_QUARTZProcessor;
import com.sivotek.crm.xsd.jaxb.request.Request;
import com.sivotek.crm.xsd.jaxb.request.Request.Page;
import com.sivotek.crm.xsd.jaxb.request.Request.Page.Elements;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
/**
 *
 * @author okoyevictor
 */
public class RulesEngine {
   private final Date ft = new Date();
   private final int response_code = 0;
   private String status = "";
   private String statusmessage = "";
   private final String taskID = "";
   private final String scheduleID ="";
   
   private JAXBContext requestContext;
   private JAXBContext responseContext;
   private Unmarshaller unmarshaller;
   private Marshaller marshaller;
   private Schema schema;
   private Response response;
   private Request request;
   private StringWriter responseWriter;
   private int companyID = 0; 
   private final int employeeID = 0;
    
   private CronExprGenerator cronGen;
   private String appkey;

   @javax.ejb.EJB
   private TranscendEJB_QUARTZProcessor transcendEJBProcessor;
    
   CompanyJpaController companyJpaController;
   Company company;
   
   CrmlicenseJpaController crmlicenseJpaController;
   Crmlicense crmlicense;
   
   CrmlicensecodeJpaController crmlicensecodeJpaController;
   Crmlicensecode crmlicensecode;
   
   Crmusers crmusers;
   CrmusersJpaController crmusersJpaController;
   
   Crmlicense crmlicense2 = new Crmlicense();
   /*
   *----------------------------------------------------------------------||
   * This function is used to generate cron expression, 
   * It converts it parameters into a cron representation..
   *----------------------------------------------------------------------||
   */
  public String getCronExpression(String Seconds, String Minutes, String Hour, String DayOfMonth, String Month, String Weekdays, String Year, String TimeZoneName) throws Exception, java.lang.IllegalArgumentException
  {  
      cronGen = new CronExprGenerator();
      String StrcronGen = "";
      try{
          StrcronGen = cronGen.getCronExpression(Seconds, Minutes, Hour, DayOfMonth, Month, Weekdays, Year, TimeZoneName); 
      }catch(Exception e)
      {
          this.setStatus("ERROR");
          this.setStatusmessage(e.getMessage());
      }
      return StrcronGen;
  }//end......  
    
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
   
 public String xmlProccessor(String xml) throws JAXBException, Exception 
      {
      companyJpaController = new CompanyJpaController();
      try 
        {
             requestContext = JAXBContext.newInstance(com.sivotek.crm.xsd.jaxb.request.Request.class);
             schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(RulesEngine.class.getClassLoader().getResource("com/sivotek/crm/xsd/TranscendRequest.xsd"));
             unmarshaller = requestContext.createUnmarshaller();
             unmarshaller.setSchema(schema);
             //read xml request into request object
             request = (Request) unmarshaller.unmarshal(new StringReader(xml));
             
             //create response context object
             responseContext = JAXBContext.newInstance(com.sivotek.crm.xsd.jaxb.response.Response.class);
             marshaller = responseContext.createMarshaller();
             //create response Object Factory
             com.sivotek.crm.xsd.jaxb.response.ObjectFactory responseOF = new com.sivotek.crm.xsd.jaxb.response.ObjectFactory();
             //create response <Page> Object
             com.sivotek.crm.xsd.jaxb.response.Response.Page responsePage = responseOF.createResponsePage();
             //create response <elements> object
             com.sivotek.crm.xsd.jaxb.response.Response.Page.Elements responseElements = responseOF.createResponsePageElements();
             //initialize response object
             response = responseOF.createResponse();
             //create response Elements list
             List<Response.Page.Elements.Element> responseElementList = responseElements.getElement();
             //read request element <Page> into object
             Page requestPage = request.getPage();
             Elements myElements = (Elements) requestPage.getElements();
             List elementList = myElements.getElement();
             try {
                 appkey = requestPage.getAppkey();
                 BigInteger compID = requestPage.getCompanyid();
                 companyID = compID.intValue(); 
                 
             } catch (Exception ex) {
                 throw new Exception("ConsumerID or campaignID is NULL!" + ex.getMessage());
                }
            for (Iterator iter = elementList.iterator(); iter.hasNext();) {
            Request.Page.Elements.Element requestElement = (Request.Page.Elements.Element) iter.next();
            List children = requestElement.getAppkeyOrEmployeeidOrScheduleID();
            
            String s = requestElement.getId();
            Response.Page.Elements.Element resElement = responseOF.createResponsePageElementsElement();
            
            resElement.setElementstatus("OK");
            resElement.setElementstatusmessage("");
                  Date date = new Date();
                  crmlicenseJpaController = new CrmlicenseJpaController();
                  crmlicense = new Crmlicense();
                  int checkpublickey = 0;
            try{
                   
                if(s.equalsIgnoreCase("logon"))
                {
                     CrmusersPrep crmusersPrep = new CrmusersPrep();
                     
                     List<Response.Page.Elements.Element> logonElementList = responseElements.getElement();
                      logonElementList = crmusersPrep.logon(children);
                      for(Response.Page.Elements.Element logonresElement : logonElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(logonresElement);
                      } 
                      resElement.setElementstatus("OK");
                      resElement.setElementstatusmessage("Success");
                      this.setStatus("OK");
                      this.setStatusmessage("Success");
//                      add all response elements to response list..
                      responseElementList.add(resElement);
                }
                
                else if(s.equalsIgnoreCase("companycategory"))
                {
                    CategoryPrep categoryPrep = new CategoryPrep();
                    
                      
                     List<Response.Page.Elements.Element> categoryElementList = responseElements.getElement();
                      categoryElementList = categoryPrep.categoryPrep(children);
                      for(Response.Page.Elements.Element categoryresElement : categoryElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(categoryresElement);
                      } 
                      resElement.setElementstatus("OK");
                      resElement.setElementstatusmessage("Success");
                      this.setStatus("OK");
                      this.setStatusmessage("Success");
                      //add all response elements to response list..
                      responseElementList.add(resElement);
                }
                
                else if(s.equalsIgnoreCase("contenttypes"))
                {
                    ContenttypesPrep contenttypesPrep = new ContenttypesPrep();
                    
                      
                     List<Response.Page.Elements.Element> contentElementList = responseElements.getElement();
                      contentElementList = contenttypesPrep.contenttypes(children);
                      for(Response.Page.Elements.Element contentresElement : contentElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(contentresElement);
                      } 
                      resElement.setElementstatus("OK");
                      resElement.setElementstatusmessage("Success");
                      this.setStatus("OK");
                      this.setStatusmessage("Success");
                      //add all response elements to response list..
                      responseElementList.add(resElement);
                }
                
               //proccessing license key and returning their respective public keys
                else if(s.equalsIgnoreCase("licenseregistration"))
                {
                    
                   resElement.setId(s);
//                   String licensetype = getElementStringValueFromList("licensetype", children);
                   List<Response.Page.Elements.Element> licenseElementList = responseElements.getElement();
                   PublicKeyValidator validator = new PublicKeyValidator();
                   licenseElementList = validator.ValidateNgetPublicKey(appkey);
                     
                   for(Response.Page.Elements.Element licenseEElement : licenseElementList){
                        this.setStatus("OK");
                        this.setStatusmessage("Success");
                        //add all response elements to response list.
                        responseElementList.add(licenseEElement);
                      }   
                    
                   //LicenseKeyManager manager = new LicenseKeyManager();
                   
                   //manager.makekeys();
//                   resElement.setPublickey(validator.ValidateNgetPublicKey(appkey, licensetype));
//                   resElement.setElementstatus("OK");
//                   resElement.setElementstatusmessage("Success");
//                   this.setStatus("OK");
//                   this.setStatusmessage("Success");
//                   //add all response elements to response list..
//                   responseElementList.add(resElement);
                }
               
               
               //proccessing other requests that requires public key               
               else if(!s.equalsIgnoreCase("licenseregistration")){
                   resElement.setId(s);
                   try{
                        //finding request public key from the database...     
                        Crmlicensecode crmlicensecode1 = new Crmlicensecode();
                        crmlicensecode1.setLicensekey(Integer.parseInt(appkey));
                        crmlicensecodeJpaController = new CrmlicensecodeJpaController();
                        crmlicensecode = new Crmlicensecode();
                        
                        try{
                           crmlicensecode = crmlicensecodeJpaController.findByPublicKey(crmlicensecode1);
                           CrmlicenseJpaController crmlicenseJpaController2 = new CrmlicenseJpaController();
                           crmlicense2 = crmlicenseJpaController2.findByLicensecode(crmlicensecode);
                           
                
                        }catch(Exception e){
                        if(e.getMessage().contains("Communications link failure"))
                        {
                            this.setStatus("Communications link failure");
                            
                             resElement.setElementstatus(this.getStatus());
                             resElement.setElementstatusmessage("Communications link failure: Make sure your database engine is up and running..");
                             //add all response elements to response list..
                             responseElementList.add(resElement);
                        }else{
                        this.setStatus("ERROR");
                        this.setStatusmessage(e.getMessage());
                        resElement.setElementstatus(this.getStatus());
                        resElement.setElementstatusmessage(this.getStatusmessage());
                        //add all response elements to response list..
                        responseElementList.add(resElement);
                        }
                        }
                        
                        checkpublickey = crmlicensecode.getLicensekey();
                        
                        
                      } catch(NullPointerException ex){
                          if(ex.getCause().getMessage().contains("Communications link failure") || ex.getCause().getMessage().contains("org.eclipse.persistence.exceptions.DatabaseException"))
                        {
                            this.setStatus("Communications link failure");
                            
                             resElement.setElementstatus(this.getStatus());
                             resElement.setElementstatusmessage("Communications link failure: Make sure your database engine is up and running..");
                             
                             //add all response elements to response list..
                             responseElementList.add(resElement);
                        }else{
                          this.setStatus("ERROR");
                          resElement.setElementstatus(this.getStatus());
                          this.setStatusmessage("Invalid Key ..Please contact SIVOTEK Solutions via web at www.sivoteksolutions.com");
                          resElement.setElementstatusmessage("Invalid Key ..Please contact SIVOTEK Solutions via web at www.sivoteksolutions.com");
                          //add all response elements to response list..
                          responseElementList.add(resElement);
                          }
                      }
                        catch(Exception exs){
                          if(exs.getCause().getMessage().contains("org.eclipse.persistence.exceptions.DatabaseException"))
                        {
                            this.setStatus("Communications link failure");
                            
                             resElement.setElementstatus(this.getStatus());
                             resElement.setElementstatusmessage("Communications link failure: Make sure your database engine is up and running..");
                             
                             //add all response elements to response list..
                             responseElementList.add(resElement);
                        }else{
                          this.setStatus("ERROR");
                          resElement.setElementstatus(this.getStatus());
                          this.setStatusmessage("Invalid Key ..Please contact SIVOTEK Solutions via web at www.sivoteksolutions.com");
                          resElement.setElementstatusmessage("Invalid Key ..Please contact SIVOTEK Solutions via web at www.sivoteksolutions.com");
                          //add all response elements to response list..
                          responseElementList.add(resElement);
                          }
                      }
                   
      //checking and making sure that license details are up to date..
     if(checkpublickey !=0 && date.before(crmlicense2.getPeriodityid().getValidto()))
     {   
         switch (s){
                case "company":
                     resElement.setId(s);
                     List<Response.Page.Elements.Element> companyElementList = responseElements.getElement();
                      CompanyPrep companyPrep = new CompanyPrep();
                      companyElementList = companyPrep.company(children, checkpublickey, companyID);
                      for(Response.Page.Elements.Element companyresElement : companyElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companyresElement);}
                      break;
                                        
                case "companyaddresstype":
                      resElement.setId(s);
                      List<Response.Page.Elements.Element> companyaddresstypeElementList = responseElements.getElement();
                      CompanyaddresstypePrep companyaddresstypePrep = new CompanyaddresstypePrep();
                      companyaddresstypeElementList = companyaddresstypePrep.companyaddresstype(children, checkpublickey, companyID);
                      for(Response.Page.Elements.Element companyaddresstyperesElement : companyaddresstypeElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companyaddresstyperesElement);}
                      break;
                    
                case "companycontacts":
                    resElement.setId(s);
                    List<Response.Page.Elements.Element> companycontactsElementList = responseElements.getElement();
                    CompanycontactsPrep companycontactsPrep = new CompanycontactsPrep();
                    companycontactsElementList = companycontactsPrep.companycontacts(children, checkpublickey, companyID);
                    for(Response.Page.Elements.Element companycontactsresElement : companycontactsElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companycontactsresElement);}
                      break;
                    
                case "companycontactsaddress":
                      resElement.setId(s);
                      CompanycontactsaddressPrep companycontactsaddressPrep = new CompanycontactsaddressPrep();
                      companycontactsaddressPrep.companycontactsaddress(children, checkpublickey, companyID);
                      resElement.setContactsaddressid(companycontactsaddressPrep.getContactsaddressid());
                      this.setStatus(companycontactsaddressPrep.getStatus());
                      this.setStatusmessage(companycontactsaddressPrep.getStatusmessage());
                      resElement.setElementstatus(companycontactsaddressPrep.getStatus());
                      resElement.setElementstatusmessage(companycontactsaddressPrep.getStatusmessage());
                      //add all response elements to response list..
                      responseElementList.add(resElement);
                  
                    break;
                case "crmmodules":
                      resElement.setId(s);
                      CrmmodulesPrep crmmodulesPrep = new CrmmodulesPrep();
                      crmmodulesPrep.companycontactsaddress(children, checkpublickey, companyID);
                      
                      resElement.setModuleid(crmmodulesPrep.getModuleid());
                      resElement.setElementstatus(crmmodulesPrep.getStatus());
                      resElement.setElementstatusmessage(crmmodulesPrep.getStatusmessage());
                      this.setStatus(crmmodulesPrep.getStatus());
                      this.setStatusmessage(crmmodulesPrep.getStatusmessage());
                      //add all response elements to response list..
                      responseElementList.add(resElement);
                    break;
                    
                case "companydepartment":
                      resElement.setId(s);
                      List<Response.Page.Elements.Element> departmentElementList = responseElements.getElement();
                      CompanydepartmentPrep companydepartmentPrep = new CompanydepartmentPrep();
                      departmentElementList = companydepartmentPrep.companydepartment(children, checkpublickey, companyID);
                      for(Response.Page.Elements.Element departmentresElement : departmentElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(departmentresElement);} 
                    break;
                    
                case "companypaymentscheme":
                     resElement.setId(s);
                     List<Response.Page.Elements.Element> paymentschemeElementList = responseElements.getElement();
                     CompanypaymentschemePrep companypaymentschemePrep = new CompanypaymentschemePrep();
                     paymentschemeElementList = companypaymentschemePrep.companypaymentscheme(children, checkpublickey, companyID);
                     for(Response.Page.Elements.Element schemeresElement : paymentschemeElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(schemeresElement);}         
                    break;
                     
                case "timezones":
                     resElement.setId(s);
                     List<Response.Page.Elements.Element> timeZonesElementList = responseElements.getElement();
                     TimeZonesPrep timeZonesPrep = new TimeZonesPrep();
                     timeZonesElementList = timeZonesPrep.TimeZones(children, checkpublickey, companyID);
                     for(Response.Page.Elements.Element timeZonesElement : timeZonesElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(timeZonesElement);}         
                    break;
                
                case "crmemployeenote":
                     resElement.setId(s);
                     List<Response.Page.Elements.Element> crmemployeenoteElementList = responseElements.getElement();
                     CrmemployeenotePrep crmemployeenotePrep = new CrmemployeenotePrep();
                     crmemployeenoteElementList = crmemployeenotePrep.Crmemployeenotes(children, checkpublickey, companyID);
                     for(Response.Page.Elements.Element crmemployeenoteresElement : crmemployeenoteElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(crmemployeenoteresElement);}         
                    break;
                
                    
                case "crmbillingtype":
                     resElement.setId(s);
                     List<Response.Page.Elements.Element> crmbillingtypeElementList = responseElements.getElement();
                     CrmbillingtypePrep crmbillingtypePrep = new CrmbillingtypePrep();
                     crmbillingtypeElementList = crmbillingtypePrep.crmbillingtype(children, checkpublickey, companyID);
                     for(Response.Page.Elements.Element crmbillingtyperesElement : crmbillingtypeElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(crmbillingtyperesElement);}         
                    break;
                 
                case "crmworkordertype":
                     resElement.setId(s);
                     List<Response.Page.Elements.Element> crmworkordertypeElementList = responseElements.getElement();
                     CrmworkordertypePrep crmworkordertypePrep = new CrmworkordertypePrep();
                     crmworkordertypeElementList = crmworkordertypePrep.crmworkordertype(children, checkpublickey, companyID);
                     for(Response.Page.Elements.Element crmworkordertyperesElement : crmworkordertypeElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(crmworkordertyperesElement);}         
                    break;
                        
                case "compnaypaymentcurrency":
                      resElement.setId(s);
                      List<Response.Page.Elements.Element> currencyElementList = responseElements.getElement();
                      CompnaypaymentcurrencyPrep compnaypaymentcurrencyPrep = new CompnaypaymentcurrencyPrep();   
                      currencyElementList = compnaypaymentcurrencyPrep.compnaypaymentcurrency(children, checkpublickey, companyID);
                      for(Response.Page.Elements.Element resElement3 : currencyElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(resElement3);}
                     break;
                     
                case "companypayments":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> paymentsElementList = responseElements.getElement();
                        CompanypaymentsPrep CompanypaymentsPrep = new CompanypaymentsPrep();
                        paymentsElementList = CompanypaymentsPrep.compnaypaymentcurrency(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element companypaymentsresElement : paymentsElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companypaymentsresElement);}
                     break;
                     
                case "crmworkorder":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> crmworkorderElementList = responseElements.getElement();
                        CrmworkorderPrep crmworkorderPrep = new CrmworkorderPrep();
                        crmworkorderElementList = crmworkorderPrep.crmworkorder(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element crmworkorderresElement : crmworkorderElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(crmworkorderresElement);}
                     break;  
                    
                case "workorderinstructions":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> workorderinstructionsElementList = responseElements.getElement();
                        WorkorderinstructionsPrep workorderinstructionsPrep = new WorkorderinstructionsPrep();
                        workorderinstructionsElementList = workorderinstructionsPrep.workorderinstructions(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element workorderinstructionsresElement : workorderinstructionsElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(workorderinstructionsresElement);}
                     break; 
                    
                case "crmworkorderresolution":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> crmworkorderresolutionElementList = responseElements.getElement();
                        CrmworkorderresolutionPrep crmworkorderresolutionPrep = new CrmworkorderresolutionPrep();
                        crmworkorderresolutionElementList = crmworkorderresolutionPrep.crmworkorderresolutions(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element crmworkorderresolutionresElement : crmworkorderresolutionElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(crmworkorderresolutionresElement);}
                     break; 
                        
                case "crmlabor":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> crmlaborElementList = responseElements.getElement();
                        CrmlaborPrep crmlaborPrep = new CrmlaborPrep();
                        crmlaborElementList = crmlaborPrep.crmlabor(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element crmlaborresElement : crmlaborElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(crmlaborresElement);}
                     break; 
                        
                        
                case "companybank":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> companybankElementList = responseElements.getElement();
                        CompanybankPrep companybankPrep = new CompanybankPrep();
                        companybankElementList = companybankPrep.companybank(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element companybankresElement : companybankElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companybankresElement);}
                     break; 
                    
                case "companyaccountstack":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> companyaccountstackElementList = responseElements.getElement();
                        CompanyaccountstackPrep companyaccountstackPrep = new CompanyaccountstackPrep();
                        companyaccountstackElementList = companyaccountstackPrep.companyaccountstack(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element companyaccountstackresElement : companyaccountstackElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companyaccountstackresElement);}
                     break;  
                
                case "companyaccountstackcd":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> companyaccountstackcdElementList = responseElements.getElement();
                        CompanyaccountstackcdPrep companyaccountstackcdPrep = new CompanyaccountstackcdPrep();
                        companyaccountstackcdElementList = companyaccountstackcdPrep.companyaccountstackcd(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element companyaccountstackcdresElement : companyaccountstackcdElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companyaccountstackcdresElement);}
                     break; 
                
                case "companyaccountstackdocs":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> companyaccountstackdocsElementList = responseElements.getElement();
                        CompanyaccountstackdocsPrep companyaccountstackdocsPrep = new CompanyaccountstackdocsPrep();
                        companyaccountstackdocsElementList = companyaccountstackdocsPrep.companyaccountstackdocs(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element companyaccountstackdocsresElement : companyaccountstackdocsElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companyaccountstackdocsresElement);}
                     break; 
                //
                    
                case "companycustomer":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> companycustomerElementList = responseElements.getElement();
                        CompanycustomerPrep companycustomerPrep = new CompanycustomerPrep();
                        companycustomerElementList = companycustomerPrep.companycustomer(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element companycustomerresElement : companycustomerElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companycustomerresElement);}
                     break; 
                     
                case "customerbank":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> customerbankElementList = responseElements.getElement();
                        CustomerbankPrep customerbankPrep = new CustomerbankPrep();
                        customerbankElementList = customerbankPrep.customerbank(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element customerbankresElement : customerbankElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(customerbankresElement);}
                     break; 
                  
                 case "customercontacts":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> customercontactsElementList = responseElements.getElement();
                        CustomercontactsPrep customercontactsPrep = new CustomercontactsPrep();
                        customercontactsElementList = customercontactsPrep.customercontacts(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element customercontactsresElement : customercontactsElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(customercontactsresElement);}
                     break;
                
                case "customercontactsaddress":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> customercontactsaddressElementList = responseElements.getElement();
                        CustomercontactsaddressPrep customercontactsaddressPrep = new CustomercontactsaddressPrep();
                        customercontactsaddressElementList = customercontactsaddressPrep.customercontactsaddress(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element customercontactsaddressresElement : customercontactsaddressElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(customercontactsaddressresElement);}
                     break;
                    
                 case "employeedesignation":
                      resElement.setId(s);
                      List<Response.Page.Elements.Element> designationElementList = responseElements.getElement();
                      EmployeedesignationPrep employeedesignationPrep = new EmployeedesignationPrep();
                      designationElementList = employeedesignationPrep.employeedesignation(children, checkpublickey, companyID);
                      for(Response.Page.Elements.Element designationresElement : designationElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(designationresElement);}
                     break;
                     
                 case "companyemployee":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> employeeElementList = responseElements.getElement();
                       CompanyemployeePrep companyemployeePrep = new CompanyemployeePrep();
                       employeeElementList = companyemployeePrep.companyemployee(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element employeeresElement : employeeElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(employeeresElement);}
                       break;
                
                 case "crmexpensetype":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> crmexpensetypeElementList = responseElements.getElement();
                       CrmexpensetypePrep crmexpensetypePrep = new CrmexpensetypePrep();
                       crmexpensetypeElementList = crmexpensetypePrep.crmexpensetype(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element crmexpensetyperesElement : crmexpensetypeElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(crmexpensetyperesElement);}
                       break;    
                 
                case "crmexpense":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> crmexpenseElementList = responseElements.getElement();
                       CrmexpensePrep crmexpensePrep = new CrmexpensePrep();
                       crmexpenseElementList = crmexpensePrep.crmexpense(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element crmexpenseresElement : crmexpenseElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(crmexpenseresElement);}
                       break;  
                    
                    
                case "crmlabortype":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> crmlabortypeElementList = responseElements.getElement();
                       CrmlabortypePrep crmlabortypePrep = new CrmlabortypePrep();
                       crmlabortypeElementList = crmlabortypePrep.crmlabortype(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element crmlabortyperesElement : crmlabortypeElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(crmlabortyperesElement);}
                       break; 
                    
                 case "customercategory":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> customercategoryElementList = responseElements.getElement();
                       CustomercategoryPrep customercategoryPrep = new CustomercategoryPrep();
                       customercategoryElementList = customercategoryPrep.customercategory(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element customercategoryresElement : customercategoryElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(customercategoryresElement);}
                       break;
                
                 case "companyproductcategory":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> companyproductcategoryElementList = responseElements.getElement();
                       CompanyproductcategoryPrep companyproductcategoryPrep = new CompanyproductcategoryPrep();
                       companyproductcategoryElementList = companyproductcategoryPrep.companyproductcategory(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element companyproductcategoryresElement : companyproductcategoryElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companyproductcategoryresElement);}
                       break;
                
                case "companyproducttype":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> companyproducttypeElementList = responseElements.getElement();
                       CompanyproducttypePrep companyproducttypePrep = new CompanyproducttypePrep();
                       companyproducttypeElementList = companyproducttypePrep.companyproducttype(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element companyproducttyperesElement : companyproducttypeElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companyproducttyperesElement);}
                       break;
             
                case "companyproduct":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> companyproductElementList = responseElements.getElement();
                       CompanyproductPrep companyproductPrep = new CompanyproductPrep();
                       companyproductElementList = companyproductPrep.companyaccountstackcd(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element companyproductresElement : companyproductElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(companyproductresElement);}
                       break;
                
                    
                case "componenttype":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> componenttypeElementList = responseElements.getElement();
                       ComponenttypePrep componenttypePrep = new ComponenttypePrep();
                       componenttypeElementList = componenttypePrep.componenttype(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element componenttyperesElement : componenttypeElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(componenttyperesElement);}
                       break;
                 
                case "productcomponents":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> productcomponentsElementList = responseElements.getElement();
                       ProductcomponentsPrep productcomponentsPrep = new ProductcomponentsPrep();
                       productcomponentsElementList = productcomponentsPrep.productcomponents(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element productcomponentsresElement : productcomponentsElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(productcomponentsresElement);}
                       break;
                    
                case "componentattachments":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> componentattachmentsElementList = responseElements.getElement();
                       ComponentattachmentsPrep componentattachmentsPrep = new ComponentattachmentsPrep();
                       componentattachmentsElementList = componentattachmentsPrep.componentattachments(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element componentattachmentsresElement : componentattachmentsElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(componentattachmentsresElement);}
                       break;
                    
                 case "companyemployeeaddress":
                       resElement.setId(s);
                       CompanyemployeeaddressPrep companyemployeeaddressPrep = new CompanyemployeeaddressPrep();
                       companyemployeeaddressPrep.companyemployeeaddress(children, checkpublickey, companyID);
                       resElement.setEmployeeaddressid(""+companyemployeeaddressPrep.getId());
                       resElement.setElementstatus(companyemployeeaddressPrep.getStatus());
                       resElement.setElementstatusmessage(companyemployeeaddressPrep.getStatusmessage());
                       this.setStatus(companyemployeeaddressPrep.getStatus());
                       this.setStatusmessage(companyemployeeaddressPrep.getStatusmessage());
                       //add all response elements to response list..
                       responseElementList.add(resElement);
                   break;
                     
                case "crmusers":
                     resElement.setId(s);
                      CrmusersPrep crmusersPrep = new CrmusersPrep();
                  try{
                      crmusersPrep.crmusers(children, checkpublickey, companyID);
                      if(crmusersPrep.getStatus().equalsIgnoreCase("load") && crmusersPrep.getStatusmessage().equalsIgnoreCase(""))
                      {
                         resElement.setUserid(crmusersPrep.getId());
                         resElement.setName(crmusersPrep.getUsername());
                         resElement.setPassword(crmusersPrep.getPasswd());
                         resElement.setIsadmin(crmusersPrep.getIsAdmin());
                          //add all response elements to response list..
                         responseElementList.add(resElement);
                      }
                      else{
                      resElement.setUserid(""+crmusersPrep.getId());
                      resElement.setElementstatus(crmusersPrep.getStatus());
                      resElement.setElementstatusmessage(crmusersPrep.getStatusmessage());
                      this.setStatus(crmusersPrep.getStatus());
                      this.setStatusmessage(crmusersPrep.getStatusmessage());  
                      //add all response elements to response list..
                      responseElementList.add(resElement);
                      }
                      
                     }catch(Exception exe){
                        this.setStatus("FAIL");
                        this.setStatusmessage(exe.getMessage());
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage(crmusersPrep.getStatusmessage());
                        //add all response elements to response list..
                            responseElementList.add(resElement);
                     }
                     break;
                    
                case "crmscheduler":
                        resElement.setId(s);
                        CrmschedulerPrep crmschedulerPrep = new CrmschedulerPrep();
                        crmschedulerPrep.crmscheduler(children, checkpublickey, companyID);
                        resElement.setScheduleID(""+crmschedulerPrep.getId());
                        resElement.setElementstatus(crmschedulerPrep.getStatus());
                        resElement.setElementstatusmessage(crmschedulerPrep.getStatusmessage());
                        this.setStatus(crmschedulerPrep.getStatus());
                        this.setStatusmessage(crmschedulerPrep.getStatusmessage());
                        //add all response elements to response list..
                        responseElementList.add(resElement);
                   break;
                    
                case "crmproject":
                       resElement.setId(s);
                       CrmprojectPrep crmprojectPrep = new CrmprojectPrep();
                       crmprojectPrep.crmproject(children, checkpublickey, companyID);
                       resElement.setProjectid(""+crmprojectPrep.getId());
                       resElement.setElementstatus(crmprojectPrep.getStatus());
                       resElement.setElementstatusmessage(crmprojectPrep.getStatusmessage());
                       this.setStatus(crmprojectPrep.getStatus());
                       this.setStatusmessage(crmprojectPrep.getStatusmessage());
                       //add all response elements to response list..
                       responseElementList.add(resElement);
                     break;
                     
                case "crmprojecttask":
                       resElement.setId(s);
                       CrmprojectPrep crmprojecttask = new CrmprojectPrep();
                       crmprojecttask.crmprojecttask(children, checkpublickey, companyID);
                       resElement.setTaskid(""+crmprojecttask.getId());
                       resElement.setElementstatus(crmprojecttask.getStatus());
                       resElement.setElementstatusmessage(crmprojecttask.getStatusmessage());
                       this.setStatus(crmprojecttask.getStatus());
                       this.setStatusmessage(crmprojecttask.getStatusmessage());
                       //add all response elements to response list..
                       responseElementList.add(resElement);
                     break;
                    
                case "crmprojectteammembers":
                       resElement.setId(s);
                       CrmprojectPrep crmprojectteammembers = new CrmprojectPrep();
                       crmprojectteammembers.crmprojectteammembers(children, checkpublickey, companyID);
                       resElement.setTeammemberid(""+crmprojectteammembers.getId());
                       resElement.setElementstatus(crmprojectteammembers.getStatus());
                       resElement.setElementstatusmessage(crmprojectteammembers.getStatusmessage());
                       this.setStatus(crmprojectteammembers.getStatus());
                       this.setStatusmessage(crmprojectteammembers.getStatusmessage());
                       //add all response elements to response list..
                       responseElementList.add(resElement);
                     break;
                         
                case "crmprojectticketmanagement":
                       resElement.setId(s);
                       CrmprojectPrep crmprojectticketmanagement = new CrmprojectPrep();
                       crmprojectticketmanagement.crmprojectticketmanagement(children, checkpublickey, companyID);
                       resElement.setIssueid(""+crmprojectticketmanagement.getId());
                       resElement.setElementstatus(crmprojectticketmanagement.getStatus());
                       resElement.setElementstatusmessage(crmprojectticketmanagement.getStatusmessage());
                       this.setStatus(crmprojectticketmanagement.getStatus());
                       this.setStatusmessage(crmprojectticketmanagement.getStatusmessage());
                       //add all response elements to response list..
                       responseElementList.add(resElement);
                     break;
                     
                case "crmcampaign":
                      resElement.setId(s);
                      CrmcampaignPrep crmcampaignPrep = new CrmcampaignPrep();
                      crmcampaignPrep.crmcampaign(children, checkpublickey, companyID);
                      resElement.setCampaignid(""+crmcampaignPrep.getId());
                      resElement.setElementstatus(crmcampaignPrep.getStatus());
                      resElement.setElementstatusmessage(crmcampaignPrep.getStatusmessage());
                      this.setStatus(crmcampaignPrep.getStatus());
                      this.setStatusmessage(crmcampaignPrep.getStatusmessage());
                      //add all response elements to response list..
                      responseElementList.add(resElement);
                  break;
                     
                case "crmcampaigndocs":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> crmcampaigndocsElementList = responseElements.getElement();
                        CrmcampaigndocsPrep crmcampaigndocsPrep = new CrmcampaigndocsPrep();
                        crmcampaigndocsElementList = crmcampaigndocsPrep.crmcampaigndoc(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element crmcampaigndocsresElement : crmcampaigndocsElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(crmcampaigndocsresElement);}
                     break; 
                
                case "productattachments":
                        resElement.setId(s);
                        List<Response.Page.Elements.Element> productattachmentsElementList = responseElements.getElement();
                        ProductattachmentsPrep productattachmentsPrep = new ProductattachmentsPrep();
                        productattachmentsElementList = productattachmentsPrep.productattachments(children, checkpublickey, companyID);
                        for(Response.Page.Elements.Element productattachmentsresElement : productattachmentsElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(productattachmentsresElement);}
                     break; 
                    
             case "appointment":
                       resElement.setId(s);
                       List<Response.Page.Elements.Element> appointmentElementList = responseElements.getElement();
                       AppointmentPrep appointmentPrep = new AppointmentPrep();
                       appointmentElementList = appointmentPrep.appointmentPrep(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element appointmentresElement : appointmentElementList){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(appointmentresElement);}
                       break;  
                
                    
                case "crmvisitor":
                       resElement.setId(s);
                       CrmvisitorPrep crmvisitorPrep = new CrmvisitorPrep();
                       crmvisitorPrep.crmvisitor(children, checkpublickey, companyID);
                       resElement.setVisitorid(""+crmvisitorPrep.getId());
                       resElement.setElementstatus(crmvisitorPrep.getStatus());
                       resElement.setElementstatusmessage(crmvisitorPrep.getStatusmessage());
                       this.setStatus(crmvisitorPrep.getStatus());
                       this.setStatusmessage(crmvisitorPrep.getStatusmessage());
                       //add all response elements to response list..
                       responseElementList.add(resElement);
                     break;
                    
                case "rootaccount":
                      resElement.setId(s);
                      //proccessing root account task 
                      crmusersPrep = new CrmusersPrep();
                      crmusersPrep.rootaccount(children, checkpublickey, companyID);
                      if(crmusersPrep.getStatus().equalsIgnoreCase("load") && crmusersPrep.getStatusmessage().equalsIgnoreCase(""))
                      {
                         
                         resElement.setUserid(crmusersPrep.getId());
                         resElement.setName(crmusersPrep.getUsername());
                         resElement.setPassword(crmusersPrep.getPasswd());
                         resElement.setIsadmin(crmusersPrep.getIsAdmin());
                         //add all response elements to response list..
                         responseElementList.add(resElement);
                      }
                      else{
                      resElement.setUserid(""+crmusersPrep.getId());
                      resElement.setElementstatus(crmusersPrep.getStatus());
                      resElement.setElementstatusmessage(crmusersPrep.getStatusmessage());
                      this.setStatus(crmusersPrep.getStatus());
                      this.setStatusmessage(crmusersPrep.getStatusmessage());  
                      //add all response elements to response list..
                      responseElementList.add(resElement);
                      }
                     break;
                    
                 case "crmmessagechannel":
                       resElement.setId(s);
                       CrmmessagechannelPrep crmmessagechannelPrep = new CrmmessagechannelPrep();
                       crmmessagechannelPrep.crmmessagechannel(children, checkpublickey, companyID);
                       resElement.setChannelid(""+crmmessagechannelPrep.getId());
                       resElement.setElementstatus(crmmessagechannelPrep.getStatus());
                       resElement.setElementstatusmessage(crmmessagechannelPrep.getStatusmessage());
                       this.setStatus(crmmessagechannelPrep.getStatus());
                       this.setStatusmessage(crmmessagechannelPrep.getStatusmessage());
                       //add all response elements to response list..
                       responseElementList.add(resElement);
                     break;
                     
                case "crmmessagechanneltemplate":
                       resElement.setId(s);
                       CrmmessagechanneltemplatePrep crmmessagechanneltemplatePrep = new CrmmessagechanneltemplatePrep();
                       crmmessagechanneltemplatePrep.crmmessagechanneltemplate(children, checkpublickey, companyID);
                       resElement.setChannelid(""+crmmessagechanneltemplatePrep.getId());
                       resElement.setElementstatus(crmmessagechanneltemplatePrep.getStatus());
                       resElement.setElementstatusmessage(crmmessagechanneltemplatePrep.getStatusmessage());
                       this.setStatus(crmmessagechanneltemplatePrep.getStatus());
                       this.setStatusmessage(crmmessagechanneltemplatePrep.getStatusmessage());
                       //add all response elements to response list..
                       responseElementList.add(resElement);
                     break;
                     
                case "crmschedulerevnttype":
                     resElement.setId(s);
                     //proccessing create schedule event type task
                     int companyid = companyID;
                     String publickey = appkey;
                     String name = getElementStringValueFromList("name", children);
                     Company company_ = new Company();
                     CompanyPK companyPK = new CompanyPK();
                     companyPK.setCompanyid(companyID);
                     companyPK.setPubkey(Integer.parseInt(publickey));
                     company_.setCompanyPK(companyPK);
                     
                     CrmschedulerevnttypePK crmschedulerevnttypePK = new CrmschedulerevnttypePK();
                     long bint = System.currentTimeMillis();
                     String p = ""+bint;
                     crmschedulerevnttypePK.setPubkey(checkpublickey);
                     crmschedulerevnttypePK.setEventtypeid(Integer.parseInt(p.substring(7)));
                     
                     CrmschedulerevnttypeJpaController crmschedulerevnttypeJpaController = new CrmschedulerevnttypeJpaController();
                     Crmschedulerevnttype crmschedulerevnttype = new Crmschedulerevnttype(); 
                     crmschedulerevnttype.setName(name);
                     crmschedulerevnttype.setCrmschedulerevnttypePK(crmschedulerevnttypePK);
                     crmschedulerevnttype.setCompany(company_);
                     crmschedulerevnttype.setCreateddate(date);
                     crmschedulerevnttype.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.Crmusers.class");
                     
                     crmschedulerevnttypeJpaController.create(crmschedulerevnttype);
                     resElement.setEventtypeid(crmschedulerevnttype.getCrmschedulerevnttypePK().getEventtypeid());
                     resElement.setElementstatus("OK");
                     resElement.setElementstatusmessage("Success");
                     this.setStatus("OK");
                     this.setStatusmessage("Success");
                     //add all response elements to response list..
                     responseElementList.add(resElement);
                     break;
                  
                case "dashboard":
                       //proccessing dashboard tasks
                       DashboardPUSHER dashboardPUSHER = new DashboardPUSHER();
                       List<Response.Page.Elements.Element> responseElementList2 = responseElements.getElement();
             
                       responseElementList2 = dashboardPUSHER.dashboard(children, checkpublickey, companyID);
                       for(Response.Page.Elements.Element resElement2 : responseElementList2){
                           this.setStatus("OK");
                           this.setStatusmessage("Success");
                           responseElementList.add(resElement2);
                       }
                       
                        
                     break;
                case "CreateReminder":
                        resElement.setId(s);
                        //proccessing createReminders task
                        String ScheduleID = getElementStringValueFromList("scheduleID", children);
                        String scheduleGroup = getElementStringValueFromList("scheduleGroup", children);
                        String Seconds = getElementStringValueFromList("seconds", children);
                        String Minutes = getElementStringValueFromList("minutes", children);
                        String Hour = getElementStringValueFromList("hour", children);
                        String DayOfMonth = getElementStringValueFromList("dayOfMonth", children);
                        String Month = getElementStringValueFromList("month", children);
                        String Weekdays = getElementStringValueFromList("weekdays", children);
                        String Year = getElementStringValueFromList("year", children);
                        String TimeZoneName = getElementStringValueFromList("timeZoneName", children);
                        
                        if(!scheduleGroup.isEmpty()){
                        String JobDetails = getCronExpression(Seconds, Minutes, Hour, DayOfMonth, Month, Weekdays, Year, TimeZoneName);
                        System.out.println("Job Details :"+JobDetails);
                        transcendEJBProcessor = new TranscendEJB_QUARTZProcessor();
                        int send =  transcendEJBProcessor.createSchedule(ScheduleID, JobDetails, TimeZoneName, scheduleGroup);
//                       
                        if(send == 101){
                            resElement.setElementstatus("Schedule Created...");
                            resElement.setElementstatusmessage("Success");
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                       }
                       else if(send == 0){
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                       }else{
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                       }    //add all response elements to response list..
                            responseElementList.add(resElement);
                      }
                        else{
                            resElement.setElementstatus("ERROR");
                            resElement.setElementstatusmessage("scheduleType field is Empty...");
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                        }  
                       //end of CreateReminder
                     break;
                    
                case "UpdateReminder":
                    resElement.setId(s);
                     //proccessing update reminder task
                        ScheduleID = getElementStringValueFromList("scheduleID", children);
                        scheduleGroup = getElementStringValueFromList("scheduleGroup", children);
                        Seconds = getElementStringValueFromList("seconds", children);
                        Minutes = getElementStringValueFromList("minutes", children);
                        Hour = getElementStringValueFromList("hour", children);
                        DayOfMonth = getElementStringValueFromList("dayOfMonth", children);
                        Month = getElementStringValueFromList("month", children);
                        Weekdays = getElementStringValueFromList("weekdays", children);
                        Year = getElementStringValueFromList("year", children);
                        TimeZoneName = getElementStringValueFromList("timeZoneName", children);
                        
                        transcendEJBProcessor = new TranscendEJB_QUARTZProcessor();
                        String JobDetails = getCronExpression(Seconds, Minutes, Hour, DayOfMonth, Month, Weekdays, Year, TimeZoneName);
                        
                        int send = transcendEJBProcessor.updateSchedule(ScheduleID, JobDetails, TimeZoneName, scheduleGroup);//transcendEJBProcessor.updateSchedule(ScheduleID, JobDetails, TimeZoneName, scheduleGroup);
                       
                       if(send == 101){
                            this.setStatus("OK");
                            this.setStatusmessage("Updated successfully");
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                       }
                       else if(send == 0){
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                            
                       }else{
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                            
                       }
                        
                    //end UpdateReminder
                    break;
                    
                case "DisableReminder":
                      //proccesing DisableReminder tasks..
                       scheduleGroup = getElementStringValueFromList("scheduleGroup", children);
                       ScheduleID = getElementStringValueFromList("scheduleID", children);
                       transcendEJBProcessor = new TranscendEJB_QUARTZProcessor();
                       
                       send = transcendEJBProcessor.disableSchedule(ScheduleID, scheduleGroup);
                       
                       if(send == 101){
                            this.setStatus("OK");
                            this.setStatusmessage("Reminder with ID :"+ ScheduleID + " has been successfully disabled"); 
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                       }
                       else if(send == 0){
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                       }else{
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                       }//end DisableReminder...
                     break;
                    
                case "EnableReminder":
                      resElement.setId(s);
                      //proccessing EnableReminder tasks...
                       ScheduleID = getElementStringValueFromList("scheduleID", children);
                       scheduleGroup = getElementStringValueFromList("scheduleGroup", children);
                       transcendEJBProcessor = new TranscendEJB_QUARTZProcessor();
                       send = transcendEJBProcessor.resumeSchedule(ScheduleID, scheduleGroup);//transcendEJBProcessor.resumeSchedule(ScheduleID, scheduleGroup);
                        
                       if(send == 101){
                           this.setStatus("OK");
                           this.setStatusmessage("Reminder with ID : "+ScheduleID+" has been successfully resumed");
              
                           resElement.setElementstatus(getStatus());
                           resElement.setElementstatusmessage(getStatusmessage());
                           //add all response elements to response list..
                            responseElementList.add(resElement);
                       }
                       else if(send == 0){
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                       }else{
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                        
                       }//
                     break;
                    
                case "RemoveReminder":
                       resElement.setId(s);
                      //proccessing RemoveReminder tasks
                       ScheduleID = getElementStringValueFromList("scheduleID", children);
                       scheduleGroup = getElementStringValueFromList("scheduleGroup", children);
                       transcendEJBProcessor = new TranscendEJB_QUARTZProcessor();
                       
                       send = transcendEJBProcessor.removeSchedule(ScheduleID, scheduleGroup);//transcendEJBProcessor.removeSchedule(ScheduleID, scheduleGroup);
                        
                       if(send == 101){
                            
                            this.setStatus("OK");
                            this.setStatusmessage("Reminder with ID : "+ScheduleID+" has been successfully removed");
              
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                       }
                       else if(send == 0){
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                        
                       }else{
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
                        
                       }
                     break;
                default:
                            this.setStatus("FAIL");
                            this.setStatusmessage("Please contact SIVOTEK SOLUTIONS via web at www.sivoteksolutions.com");
                            resElement.setElementstatus(getStatus());
                            resElement.setElementstatusmessage(getStatusmessage());
                            //add all response elements to response list..
                            responseElementList.add(resElement);
            }

           }

         }
               }catch(Exception e){
                    System.out.println("Exception caught :"+e.getCause().getMessage());
                    resElement.setElementstatus("ERROR");
                    resElement.setElementstatusmessage(e.getMessage());
                    String errorMessage = e.getCause().getMessage();
                    if (errorMessage == null){errorMessage = "Unknown exception thrown while processing element.";}
                    resElement.setElementstatusmessage(errorMessage);
                    //add all response elements to response list..
                   responseElementList.add(resElement);
                    
                }
           }
            responsePage.setElements(responseElements);
            response.setPage(responsePage);
            response.setStatus(getStatus());
            response.setStatusmessage(getStatusmessage());
            responseWriter = new StringWriter();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(response, responseWriter);
            
        } catch (Exception e) {
            responseWriter = new StringWriter();
            responseWriter.write(generateErrorResponse("ERROR: ", e));
        }
        //System.out.println("Response ::::::"+responseWriter.toString());
        return responseWriter.toString();    
}
 
 
 //function for generating Error Responses
 private String generateErrorResponse(String errtype, Exception e) throws Exception, JAXBException 
        {
         com.sivotek.crm.xsd.jaxb.response.ObjectFactory responseOF = new com.sivotek.crm.xsd.jaxb.response.ObjectFactory();
         com.sivotek.crm.xsd.jaxb.response.Response errResponse = new com.sivotek.crm.xsd.jaxb.response.Response();
         com.sivotek.crm.xsd.jaxb.response.Response.Page.Elements responseElements = responseOF.createResponsePageElements();
         Response.Page resPage = responseOF.createResponsePage();
         responseContext = JAXBContext.newInstance(com.sivotek.crm.xsd.jaxb.response.Response.class);
         marshaller = responseContext.createMarshaller();
         resPage.setElements(responseElements);
         errResponse.setPage(resPage);
         errResponse.setStatus(errtype);
         errResponse.setStatusmessage(e.toString());
         responseWriter = new StringWriter();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         marshaller.marshal(errResponse, responseWriter);
         return responseWriter.toString();
    }//generateErrorResponse..
 
 
}
