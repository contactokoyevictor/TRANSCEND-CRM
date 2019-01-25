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
import com.sivotek.crm.persistent.dao.entities.CompanyemployeePK;
import com.sivotek.crm.persistent.dao.entities.Crmscheduler;
import com.sivotek.crm.persistent.dao.entities.CrmschedulerPK;
import com.sivotek.crm.persistent.dao.entities.Crmschedulerevnttype;
import com.sivotek.crm.persistent.dao.entities.CrmschedulerevnttypePK;
import com.sivotek.crm.persistent.dao.entities.Crmusers;
import com.sivotek.crm.persistent.dao.entities.CrmusersPK;
import com.sivotek.crm.persistent.dao.entities.Crmvisitor;
import com.sivotek.crm.persistent.dao.entities.CrmvisitorPK;
import com.sivotek.crm.persistent.dao.entities.controllers.AppointmentJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmschedulerJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmschedulerevnttypeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmusersJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmvisitorJpaController;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author okoyevictor
 */
public class CrmvisitorPrep {
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
        for (int i = 0; i < elementList.size(); i++) {
            JAXBElement e = (JAXBElement) elementList.get(i);
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
   public void crmvisitor(List children, int publickey, int companyID)
   {
           Company company = new Company();
           CompanyPK companyPK = new CompanyPK();
           companyPK.setCompanyid(companyID);
           companyPK.setPubkey(publickey);
           CompanyJpaController companyJpaController = new CompanyJpaController();
      try{
           company = companyJpaController.findCompany(companyPK);
         if(company.getCompanyPK().getCompanyid() > 0){
           String employeeid = getElementStringValueFromList("employeeid", children);
           String appointmentid = getElementStringValueFromList("appointmentid", children);
           String schedulerid = getElementStringValueFromList("schedulerid", children);
           String schedulerevnttypeid = getElementStringValueFromList("schedulerevnttypeid", children);
           String gender = getElementStringValueFromList("gender", children);
           String firstname = getElementStringValueFromList("firstname", children);
           String lastname = getElementStringValueFromList("lastname", children);
           String othername = getElementStringValueFromList("othername", children);
           String street = getElementStringValueFromList("street", children);
           String zip = getElementStringValueFromList("zip", children);
           String location = getElementStringValueFromList("location", children);
           String pbox = getElementStringValueFromList("pbox", children);
           String country = getElementStringValueFromList("country", children);
           XMLGregorianCalendar brithday = getElementDateValueFromList("brithday", children);
           java.sql.Date brithday_ = new java.sql.Date(brithday.toGregorianCalendar().getTimeInMillis());
           String phone = getElementStringValueFromList("phone", children);
           String fax = getElementStringValueFromList("fax", children);
           String mobile = getElementStringValueFromList("mobile", children);
           String email = getElementStringValueFromList("email", children);
           String description = getElementStringValueFromList("description", children);
           
           
           Companyemployee companyemployee = new Companyemployee();
           CompanyemployeePK companyemployeePK = new CompanyemployeePK();
           companyemployeePK.setPubkey(publickey);
           companyemployeePK.setId(Integer.parseInt(employeeid));
           CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
           
           
           Crmscheduler crmscheduler = new Crmscheduler();
           CrmschedulerPK crmschedulerPK = new CrmschedulerPK();
           crmschedulerPK.setPubkey(publickey);
           crmschedulerPK.setSchedulerid(Integer.parseInt(schedulerid));
           
           CrmschedulerJpaController crmschedulerJpaController = new CrmschedulerJpaController();
           crmscheduler = crmschedulerJpaController.findCrmscheduler(crmschedulerPK);
           
           companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
           if(companyemployee.getCompanyemployeePK().getId() > 0 )
           {
                Crmschedulerevnttype crmschedulerevnttype = new Crmschedulerevnttype();
                CrmschedulerevnttypePK crmschedulerevnttypePK = new CrmschedulerevnttypePK();
                crmschedulerevnttypePK.setPubkey(publickey);
                crmschedulerevnttypePK.setEventtypeid(Integer.parseInt(schedulerevnttypeid));
                CrmschedulerevnttypeJpaController crmschedulerevnttypeJpaController = new CrmschedulerevnttypeJpaController(); 
                crmschedulerevnttype = crmschedulerevnttypeJpaController.findCrmschedulerevnttype(crmschedulerevnttypePK);
                if(crmschedulerevnttype.getCrmschedulerevnttypePK().getEventtypeid() > 0){
                    Appointment appointment = new Appointment();
                    AppointmentPK appointmentPK = new AppointmentPK();
                    appointmentPK.setPubkey(publickey);
                    appointmentPK.setAppointmentid(Integer.parseInt(appointmentid));
                    AppointmentJpaController appointmentJpaController = new AppointmentJpaController();
                    appointment = appointmentJpaController.findAppointment(appointmentPK);
                    
                    if(appointment.getAppointmentPK().getAppointmentid() > 0){
                        Crmvisitor crmvisitor = new Crmvisitor();
                        CrmvisitorPK crmvisitorPK = new CrmvisitorPK();
                        crmvisitorPK.setPubkey(publickey);
                        long bint = System.currentTimeMillis();
                        String p = ""+bint;
                        crmvisitorPK.setVisitorid(Integer.parseInt(p.substring(7)));
                        
                        crmvisitor.setCrmscheduler(crmscheduler);
                        crmvisitor.setCrmschedulerevnttype(crmschedulerevnttype);
                        crmvisitor.setCrmvisitorPK(crmvisitorPK);
                        crmvisitor.setFisrtName(firstname);
                        crmvisitor.setLastName(lastname);
                        crmvisitor.setOtherName(othername);
                        crmvisitor.setGender(gender);
                        crmvisitor.setStreet(street);
                        crmvisitor.setZip(zip);
                        crmvisitor.setLocation(location);
                        crmvisitor.setPbox(pbox);
                        crmvisitor.setCountry(country);
                        crmvisitor.setBirddate(brithday_);
                        crmvisitor.setPhone(phone);
                        crmvisitor.setFax(fax);
                        crmvisitor.setMobile(mobile);
                        crmvisitor.setEmail(email);
                        crmvisitor.setDescription(description);
                        crmvisitor.setCreateddate(new Date());
                        crmvisitor.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.Crmvisitor.class");
                        crmvisitor.setCompanyemployee(companyemployee);
                        CrmvisitorJpaController crmvisitorJpaController = new CrmvisitorJpaController();
                        crmvisitorJpaController.create(crmvisitor);
                        this.setId(crmvisitor.getCrmvisitorPK().getVisitorid());
                        this.setStatus("OK");
                        this.setStatusmessage("Success");
                    }else{
                        throw new Exception("Invalid appointment id");
                        
                    }
                
                }else{
                    throw new Exception("Invalid event type id");
                }
           }
           else{
               throw new Exception("Invalid employee id");
           }
           
           }
         }catch(Exception ex){
                if(ex.getCause().getMessage().contains("Duplicate entry")){
                  this.setStatus("ERROR");
                  this.setStatusmessage("User already exists...");
                }
                else{
                this.setStatus("ERROR");
                this.setStatusmessage(ex.getMessage());
                }
           }
   }
}
