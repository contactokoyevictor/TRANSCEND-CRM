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
import com.sivotek.crm.persistent.dao.entities.Crmcampaign;
import com.sivotek.crm.persistent.dao.entities.CrmcampaignPK;
import com.sivotek.crm.persistent.dao.entities.Crmprojecttask;
import com.sivotek.crm.persistent.dao.entities.CrmprojecttaskPK;
import com.sivotek.crm.persistent.dao.entities.Crmscheduler;
import com.sivotek.crm.persistent.dao.entities.CrmschedulerPK;
import com.sivotek.crm.persistent.dao.entities.Crmschedulerevnttype;
import com.sivotek.crm.persistent.dao.entities.CrmschedulerevnttypePK;
import com.sivotek.crm.persistent.dao.entities.TimeZones;
import com.sivotek.crm.persistent.dao.entities.controllers.AppointmentJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmcampaignJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmprojecttaskJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmschedulerJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmschedulerevnttypeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.TimeZonesJpaController;

import com.sivotek.crm.xsd.jaxb.prep.ejb.TranscendEJB_QUARTZProcessor;
import com.sivotek.crm.xsd.jaxb.rules.CronExprGenerator;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CrmschedulerPrep {
   @javax.ejb.EJB
   private TranscendEJB_QUARTZProcessor transcendEJBProcessor;
   private CronExprGenerator cronGen;
   
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
  
   ///
   public void crmscheduler(List children, int publickey, int companyID)
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
           String quartzscheduleid = getElementStringValueFromList("quartzscheduleid", children);
           String name = getElementStringValueFromList("name", children);
           String scheduleGroup = getElementStringValueFromList("scheduleGroup", children);
           String callbackurl = getElementStringValueFromList("callbackurl", children);
           String callbackaction = getElementStringValueFromList("callbackaction", children);
           String color = getElementStringValueFromList("color", children);
           String description = getElementStringValueFromList("description", children);
           String frequencytype = getElementStringValueFromList("frequencytype", children);
           String activeweekdays = getElementStringValueFromList("activeweekdays", children);
           String schedulestatus = getElementStringValueFromList("schedulestatus", children);
           
           String seconds = getElementStringValueFromList("seconds", children);
           String minutes = getElementStringValueFromList("minutes", children);
           String hour = getElementStringValueFromList("hour", children);
           String dayOfMonth = getElementStringValueFromList("dayOfMonth", children);
           String month = getElementStringValueFromList("month", children);
           
           String year = getElementStringValueFromList("year", children);
           String timeZoneid = getElementStringValueFromList("timeZoneid", children);
           String schedulerevnttypeid = getElementStringValueFromList("schedulerevnttypeid", children);
           String appointmentid = getElementStringValueFromList("appointmentid", children);
           String campaignid = getElementStringValueFromList("campaignid", children);
           String taskid = getElementStringValueFromList("taskid", children);
           String load = getElementStringValueFromList("load", children);
           
          
//          if(load.equalsIgnoreCase("load")){
//         System.out.println("loading schedule details....");
//          //lookup the quartz scheduleid from db
//           Crmscheduler crmscheduler = new Crmscheduler();
//           CrmschedulerJpaController crmschedulerJpaController = new CrmschedulerJpaController();
//           crmscheduler = crmschedulerJpaController.findByQuartzscheduleId(Integer.parseInt(quartzscheduleid));
//          
//           if(crmscheduler.getCompany().getCompanyPK().getCompanyid() == companyID && crmscheduler.getCompanyemployee().getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
//           {
//                this.setId(crmscheduler.getQuartzscheduleId());
//                this.setStatus("OK");
//                this.setStatusmessage("Success");
//           }
//     }
      
           
           
           
           
           //find employee id from database
           Companyemployee companyemployee = new Companyemployee();
           CompanyemployeePK CompanyemployeePK = new CompanyemployeePK();
           CompanyemployeePK.setId(Integer.parseInt(employeeid));
           CompanyemployeePK.setPubkey(publickey);
                   
           CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
           companyemployee = companyemployeeJpaController.findCompanyemployee(CompanyemployeePK);
           
           //Companyemployee companyemployee2 = new Companyemployee();
           
//           CompanyemployeePK.setId(Integer.parseInt(createdby));
//           companyemployee2 = companyemployeeJpaController.findCompanyemployee(CompanyemployeePK);

           //find timezone details
           TimeZones timezones = new TimeZones();
           TimeZonesJpaController timezonesJpaController = new TimeZonesJpaController();
           
           timezones = timezonesJpaController.findTimeZones(Integer.parseInt(timeZoneid));
           System.out.println("Time Zone Is ::"+timezones.getTimeZone());
            ///
           //find schedule event type
           CrmschedulerevnttypeJpaController crmschedulerevnttypeJpaController = new CrmschedulerevnttypeJpaController();
           Crmschedulerevnttype crmschedulerevnttype = new Crmschedulerevnttype();
           CrmschedulerevnttypePK crmschedulerevnttypePK = new CrmschedulerevnttypePK();
           crmschedulerevnttypePK.setEventtypeid(Integer.parseInt(schedulerevnttypeid));
           crmschedulerevnttypePK.setPubkey(publickey);
           
           crmschedulerevnttype = crmschedulerevnttypeJpaController.findCrmschedulerevnttype(crmschedulerevnttypePK);
           ///
           
           CrmschedulerJpaController crmschedulerJpaController = new CrmschedulerJpaController();
           Crmscheduler crmscheduler = new Crmscheduler();
           CrmschedulerPK crmschedulerPK = new CrmschedulerPK();
           
           crmschedulerPK.setPubkey(publickey);
           
           
           long bint = System.currentTimeMillis();
           String p = ""+bint;
           crmschedulerPK.setSchedulerid(Integer.parseInt(p.substring(5)));
             
           String JobDetails = getCronExpression(seconds, minutes, hour, dayOfMonth, month, activeweekdays, year, timezones.getTimeZone());
           System.out.println("Job Details :"+JobDetails);
                        
           transcendEJBProcessor = new TranscendEJB_QUARTZProcessor();
           
           
     //create new schedule if quartz scheduleid is empty...
     if(quartzscheduleid.equalsIgnoreCase("0") || quartzscheduleid.isEmpty()){
           System.out.println("Creating schedule ....");
           int createquartzsched =  transcendEJBProcessor.createSchedule(""+Integer.parseInt(p.substring(7)), JobDetails, timezones.getTimeZone(), scheduleGroup);
            System.out.println("Creating schedule .... :"+createquartzsched);
           if(createquartzsched == 101){
             crmscheduler.setCrmschedulerPK(crmschedulerPK);
             crmscheduler.setCompany(company);
             
             crmscheduler.setCompanyemployee(companyemployee);
             
             crmscheduler.setQuartzscheduleId(Integer.parseInt(p.substring(7)));
             crmscheduler.setScheduleName(name);
             crmscheduler.setScheduleGroup(scheduleGroup);
             crmscheduler.setScheduletype("User");
             crmscheduler.setActiveWeekdays(activeweekdays);
             crmscheduler.setFrequencyType(frequencytype);
             crmscheduler.setScheduleStatus(schedulestatus);
             crmscheduler.setPushUrl(callbackurl);
             crmscheduler.setPushAction(callbackaction);
             crmscheduler.setColor(color);
             crmscheduler.setDescription(description);
             crmscheduler.setCompanyemployee1(companyemployee);
             crmscheduler.setEventType(crmschedulerevnttype.getCrmschedulerevnttypePK().getEventtypeid());
             crmscheduler.setTimezoneid(timezones.getTimezoneid());
             if(!appointmentid.isEmpty() || !appointmentid.equalsIgnoreCase("") && !appointmentid.equalsIgnoreCase("0"))
             {
                 Appointment appointment = new Appointment();
                 AppointmentPK appointmentPK = new AppointmentPK();
                 appointmentPK.setAppointmentid(Integer.parseInt(appointmentid));
                 appointmentPK.setPubkey(publickey);
                 
                 AppointmentJpaController appointmentJpaController = new AppointmentJpaController();
                 appointment = appointmentJpaController.findAppointment(appointmentPK);
                 //
                 crmscheduler.setAppointment(appointment);
                 
             }
              if(!campaignid.isEmpty() && !campaignid.equalsIgnoreCase("") && !campaignid.equalsIgnoreCase("0")){
                 CrmcampaignJpaController crmcampaignJpaController = new CrmcampaignJpaController();
                 Crmcampaign crmcampaign = new Crmcampaign();
                 CrmcampaignPK crmcampaignPK = new CrmcampaignPK();
                 crmcampaignPK.setCampaignid(Integer.parseInt(campaignid));
                 crmcampaignPK.setPubkey(publickey);
                 
                 crmcampaign = crmcampaignJpaController.findCrmcampaign(crmcampaignPK);
                 //
                 crmscheduler.setCrmcampaign(crmcampaign);
                 
             }
              if(!taskid.isEmpty() && !taskid.equalsIgnoreCase("") && !taskid.equalsIgnoreCase("0")) {
                  CrmprojecttaskJpaController crmprojecttaskJpaController = new CrmprojecttaskJpaController();
                  Crmprojecttask crmprojecttask = new Crmprojecttask();
                  CrmprojecttaskPK crmprojecttaskPK = new CrmprojecttaskPK();
                  crmprojecttaskPK.setTaskid(Integer.parseInt(taskid));
                  crmprojecttaskPK.setPubkey(publickey);
                  crmprojecttask = crmprojecttaskJpaController.findCrmprojecttask(crmprojecttaskPK);
                  //
                  crmscheduler.setCrmprojecttask(crmprojecttask);
             }   
             crmscheduler.setCreateddate(new Date());
//             crmscheduler.setCompanyemployee1(companyemployee2);
             crmscheduler.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmschedulerPrep.class");
             crmschedulerJpaController.create(crmscheduler);
             
             this.setId(Integer.parseInt(p.substring(5)));
             this.setStatus("OK");
             this.setStatusmessage("Success");
             
           }
           if(createquartzsched == 405)
           {
             this.setStatus("FAIL");
             this.setStatusmessage("Schedule Already exists..");
           }
      }
     
      
     
     
     //update existing schedule...
     else if(!quartzscheduleid.isEmpty() && !employeeid.isEmpty() && !name.isEmpty() && !scheduleGroup.isEmpty() && !callbackurl.isEmpty() && !callbackaction.isEmpty() && !color.isEmpty())
     {
          String getUpdatedJobDetails = getCronExpression(seconds, minutes, hour, dayOfMonth, month, activeweekdays, year, timezones.getTimeZone());
           //lookup the quartz scheduleid from db
           crmscheduler = new Crmscheduler();
           crmscheduler = crmschedulerJpaController.findByQuartzscheduleId(Integer.parseInt(quartzscheduleid));
          //if found check to make sure this schedule belongs to this employee
          if(crmscheduler.getCompany().getCompanyPK().getCompanyid() == companyID && crmscheduler.getCompanyemployee().getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
           {
             //set values to be updated in the scheduler table
             crmscheduler.setScheduleName(name);
             crmscheduler.setScheduleGroup(scheduleGroup);
             crmscheduler.setActiveWeekdays(activeweekdays);
             crmscheduler.setFrequencyType(frequencytype);
             crmscheduler.setScheduleStatus(schedulestatus);
             crmscheduler.setPushUrl(callbackurl);
             crmscheduler.setPushAction(callbackaction);
             crmscheduler.setColor(color);
             crmscheduler.setDescription(description);
             crmscheduler.setTimezoneid(timezones.getTimezoneid());
             crmscheduler.setChangedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmschedulerPrep.class");
             crmscheduler.setChangeddate(new Date());
             crmscheduler.setCompanyemployee2(companyemployee);
             try{
                 crmschedulerJpaController.edit(crmscheduler);
                int updatequartzsched = transcendEJBProcessor.updateSchedule(""+crmscheduler.getQuartzscheduleId(),getUpdatedJobDetails, timezones.getTimeZone(), scheduleGroup);
                if(updatequartzsched == 101){
                  switch (schedulestatus){
                      case "Paused":
                          int pausesched = transcendEJBProcessor.disableSchedule(""+crmscheduler.getQuartzscheduleId(), scheduleGroup);
                          if(pausesched == 101){
                                this.setId(crmscheduler.getQuartzscheduleId());
                                this.setStatus("OK");
                                this.setStatusmessage("Success");
                          }
                          break;
                          
                      case "Disabled":
                          int disablesched = transcendEJBProcessor.disableSchedule(""+crmscheduler.getQuartzscheduleId(), scheduleGroup);
                          if(disablesched == 101){
                                this.setId(crmscheduler.getQuartzscheduleId());
                                this.setStatus("OK");
                                this.setStatusmessage("Success");
                          }
                          break;
                          
                      case "Cancelled":
                          int cancelsched = transcendEJBProcessor.removeSchedule(""+crmscheduler.getQuartzscheduleId(), scheduleGroup);
                          if(cancelsched == 101){
                                this.setId(crmscheduler.getQuartzscheduleId());
                                this.setStatus("OK");
                                this.setStatusmessage("Success");
                          }
                          break;
                      case "Completed":
                          break;
                      case "Resume":
                          int resume = transcendEJBProcessor.resumeSchedule(""+crmscheduler.getQuartzscheduleId(), scheduleGroup);
                          if(resume == 101){
                                this.setId(crmscheduler.getQuartzscheduleId());
                                this.setStatus("OK");
                                this.setStatusmessage("Schedule resumption was Successful");
                          }
                          break;
                      default:
                          throw new Exception("Invalid schedule status...");
                  }
                  this.setId(crmscheduler.getQuartzscheduleId());
                  this.setStatus("OK");
                  this.setStatusmessage("Success");
                }
                
             }
            catch(Exception ex){ }
           }//end second if statment..
     }//end first if statement for schedule update action...
     
    
     
   }
  }catch(Exception ex)
     {
             this.setStatus("ERROR");
             this.setStatusmessage(ex.getMessage());
              
    }
   }

}
