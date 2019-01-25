/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */
package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Appointment;
import com.sivotek.crm.persistent.dao.entities.AppointmentPK;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmschedulerevnttype;
import com.sivotek.crm.persistent.dao.entities.controllers.AppointmentJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
/**
 *
 * @author okoyevictor
 */
public class AppointmentPrep {
   private Response response;
   private String status = "";
   private String statusmessage = "";
   private int id = 0;
   
   //getters and setters
   public int getId() 
   {return id;}
   public void setId(int id) 
   {this.id = id;}
   
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
     
    private XMLGregorianCalendar getElementDateValueFromList(String elementName, List elementList) {
       for (Object elementList1 : elementList) {
           JAXBElement e = (JAXBElement) elementList1;
           if (e.getName().getLocalPart().equals(elementName)) {
               return (XMLGregorianCalendar)e.getValue();
           }
       }
        return null;
    }
     
     ///
    public List<Response.Page.Elements.Element> appointmentPrep(List children, int publickey, int companyID){
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
               
               Company company = new Company();
               CompanyPK companyPK = new CompanyPK();
               companyPK.setCompanyid(companyID);
               companyPK.setPubkey(publickey);
               CompanyJpaController companyJpaController = new CompanyJpaController();
          try{
               company = companyJpaController.findCompany(companyPK);
               
             if(company.getCompanyPK().getCompanyid() > 0){
                String employeeid = getElementStringValueFromList("employeeid", children);
                String scheduletype = getElementStringValueFromList("scheduletype", children);
                String recipient = getElementStringValueFromList("recipient", children);
                String description = getElementStringValueFromList("description", children);
                String timeZoneid = getElementStringValueFromList("timeZoneid", children);
                XMLGregorianCalendar startdatetime = getElementDateValueFromList("startdatetime", children);
                XMLGregorianCalendar enddatetime = getElementDateValueFromList("enddatetime", children);
                java.sql.Date startdatetime_ = null;
                java.sql.Date enddatetime_ = null;
                
                if(startdatetime != null){
                startdatetime_ = new java.sql.Date(startdatetime.toGregorianCalendar().getTimeInMillis());
                }
                if(enddatetime != null){
                enddatetime_ = new java.sql.Date(enddatetime.toGregorianCalendar().getTimeInMillis());
                }
                Companyemployee companyemployee = new Companyemployee();
                Companyemployee companyemployee2 = new Companyemployee();
                
             if(employeeid != null && !employeeid.isEmpty() && Integer.parseInt(employeeid) > 0
                  && scheduletype != null && !scheduletype.isEmpty() && Integer.parseInt(scheduletype) > 0
                  && recipient != null && !recipient.isEmpty() && Integer.parseInt(recipient) > 0
                  && timeZoneid != null && !timeZoneid.isEmpty() && Integer.parseInt(timeZoneid) > 0)
                {
                    
                //check for employee from company employee collection
                if(company.getCompanyemployeeCollection().size() > 0)
                {
                    Collection<Companyemployee> companyemployeeColl = company.getCompanyemployeeCollection();
                    for(Companyemployee employee : companyemployeeColl)
                    {
                        if(employee.getCompanyemployeePK().getId() == Integer.parseInt(recipient))
                        {
                            companyemployee2 = employee;
                            break;
                        }
                    }
                }
                
                //check for employee from company employee collection
                if(company.getCompanyemployeeCollection().size() > 0)
                {
                    Collection<Companyemployee> companyemployeeColl = company.getCompanyemployeeCollection();
                    for(Companyemployee employee : companyemployeeColl)
                    {
                        if(employee.getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
                        {
                            companyemployee = employee;
                            break;
                        }
                    }
                }
                
                Crmschedulerevnttype crmschedulerevnttype = new Crmschedulerevnttype();
                if(company.getCrmschedulerevnttypeCollection().size() > 0)
                {
                    Collection<Crmschedulerevnttype> schedulerevnttypeColl = company.getCrmschedulerevnttypeCollection();
                    for(Crmschedulerevnttype schedulerevnttype : schedulerevnttypeColl)
                    {
                        if(schedulerevnttype.getCrmschedulerevnttypePK().getEventtypeid() == Integer.parseInt(scheduletype))
                        {
                            crmschedulerevnttype = schedulerevnttype;
                            break;
                        }
                    }
                }
                
                if(companyemployee.getCompanyemployeePK().getId() > 0 && companyemployee2.getCompanyemployeePK().getId() > 0 && crmschedulerevnttype.getCrmschedulerevnttypePK().getEventtypeid() > 0)
                {
                    //
                    Appointment appointment = new Appointment();
                    AppointmentPK appointmentPK = new AppointmentPK();
                    long bint = System.currentTimeMillis();
                    String p = ""+bint;
                    appointmentPK.setPubkey(publickey);
                    appointmentPK.setAppointmentid(Integer.parseInt(p.substring(7)));
                    appointment.setAppointmentPK(appointmentPK);
                    appointment.setCompanyemployee(companyemployee);
                    appointment.setCompanyemployee1(companyemployee2);
                    appointment.setDescription(description);
                    
                    appointment.setCrmschedulerevnttype(crmschedulerevnttype);
                    appointment.setAppointmentType(crmschedulerevnttype.getCrmschedulerevnttypePK().getEventtypeid());
                    appointment.setStartdatetime(startdatetime_);
                    appointment.setEnddatetime(enddatetime_);
                    appointment.setTimezoneid(Integer.parseInt(timeZoneid));
                    appointment.setCreateddate(new Date());
                    appointment.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.AppointmentPrep.class");
                    appointment.setCompanyemployee2(companyemployee);
                    AppointmentJpaController appointmentJpaController = new AppointmentJpaController();
                    appointmentJpaController.create(appointment);
                    
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("appointment");
                    resElement.setAppointmentid(appointment.getAppointmentPK().getAppointmentid());
                    resElement.setElementstatus("OK");
                    resElement.setElementstatusmessage("Success");
                    ////
                    responseElementList.add(resElement);
                }
             }
             else if(employeeid != null && !employeeid.isEmpty() && Integer.parseInt(employeeid) > 0
                  && scheduletype != null && !scheduletype.isEmpty() && Integer.parseInt(scheduletype) == 0
                  && recipient != null && !recipient.isEmpty() && Integer.parseInt(recipient) == 0
                  && timeZoneid != null && !timeZoneid.isEmpty() && Integer.parseInt(timeZoneid) == 0)
                 {
                    //check for employee from company employee collection
                    if(company.getCompanyemployeeCollection().size() > 0)
                    {
                        Collection<Companyemployee> companyemployeeColl = company.getCompanyemployeeCollection();
                        for(Companyemployee employee : companyemployeeColl)
                        {
                            if(employee.getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
                            {
                                companyemployee = employee;
                                break;
                            }
                        }
                    }
                    //
                    if(companyemployee.getCompanyemployeePK().getId() > 0)
                    {
                        if(companyemployee.getAppointmentCollection().size() > 0)
                        {
                           Collection<Appointment> appointmentColl = companyemployee.getAppointmentCollection();
                           for(Appointment appointment : appointmentColl)
                           {
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("appointment");
                                resElement.setAppointmentid(appointment.getAppointmentPK().getAppointmentid());
                                resElement.setScheduletype(appointment.getCrmschedulerevnttype().getCrmschedulerevnttypePK().getEventtypeid());
                                resElement.setReceipient(appointment.getCompanyemployee2().getCompanyemployeePK().getId());
                                resElement.setDescription(appointment.getDescription());
                                resElement.setStartdatetime(appointment.getStartdatetime());
                                resElement.setEnddatetime(appointment.getEnddatetime());
                                resElement.setTimeZoneid(appointment.getTimezoneid());
                                ////
                                responseElementList.add(resElement);
                           }
                            
                        }
                    }
                    
                 }
            }  
           }catch(Exception ex)
           {
                //////////////////
                resElement = responseOF.createResponsePageElementsElement();
                resElement.setId("appointment");
                resElement.setElementstatus("ERROR");
                resElement.setElementstatusmessage(ex.getMessage());
                ////
                responseElementList.add(resElement);
           }
          
    return responseElementList;
}   
     
     
}
