/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */
package com.sivotek.crm.xsd.jaxb.prep.ejb;

import com.sivotek.crm.quartz.Job.schedulerJob;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.CronTrigger;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.LoggerFactory;

/**
 *
 * @author okoyevictor
 */
@Startup
@Singleton
@Local(TranscendEJB_QUARTZProcessorLocal.class)
public class TranscendEJB_QUARTZProcessor implements TranscendEJB_QUARTZProcessorLocal {
  //static final String EJB_NAME = "TranscendCRMEJB"; 
   private String scheduleID ="";
   private int response_code = 0;
   private String taskID = "";
   private Date ft = new Date();
   private static final org.slf4j.Logger _log = LoggerFactory.getLogger(TranscendEJB_QUARTZProcessor.class);
    @PostConstruct
    public void PostConstruct(){
        Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "-----EJB PostConstruct reached-------");       
        try{
            
            SchedulerFactory sf = new StdSchedulerFactory("com/sivotek/crm/xsd/jaxb/prep/ejb/quartz.properties");
            Scheduler sched = sf.getScheduler();
            
            if(!sched.isStarted()){
              sched.start(); 
            }
            else if(sched.isInStandbyMode()){
               sched.start();
            }
            else{
                Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "-----Quartz instance is Already running-------");               
            }
        
        }catch(Exception ex){
        }
    
    }//end...
    
   @PreDestroy
   public void PreDestroy()
   {
       Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------------Destroying the EJB Quartz Instance-------------At:{0}", new Date());
       try{
                 
             SchedulerFactory sf = new StdSchedulerFactory("com/sivotek/crm/xsd/jaxb/prep/ejb/quartz.properties");
             Scheduler sched = sf.getScheduler();
             sched.shutdown(true);
             Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "----EJB Quartz PreDestroy Shutdown Completed------At {0}", new Date());

             SchedulerMetaData metaData = sched.getMetaData();
             System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
        }
        catch(Exception e)
        {
            Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "---Error During EJB Quartz Shutdown---{0}", e.getMessage());
        }
   }//end...
   
  
    @Override
    public String getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getStatusmessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
  @Override
  public int createSchedule(String scheduleID, String jobDetail, String Timezone_param, String scheduleGroup)throws java.lang.IllegalArgumentException
  {
           Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------- Building Job -----------------");
           this.scheduleID = scheduleID;
           int jobid = scheduleID.hashCode();
           JobDetail job = newJob(schedulerJob.class)
           .withIdentity(scheduleID, scheduleGroup)
           .usingJobData(scheduleID, jobid)
           .withDescription("The SIVOTEK ERP/CRM Platform job schedule for "+scheduleGroup+" Group With ID :"+scheduleID)
           .storeDurably(true)
           .build(); 
           Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------- Job Build completed-----------------");


           Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------- Building Job Trigger -----------------");
           CronTrigger trigger = newTrigger()
           .withIdentity(scheduleID, scheduleGroup)
           .withSchedule(cronSchedule(jobDetail).inTimeZone(TimeZone.getTimeZone(Timezone_param)))
           .withDescription("The SIVOTEK ERP/CRM Platform trigger schedule for "+scheduleGroup+" Group with ID :"+scheduleID)
           .usingJobData(scheduleID, jobid)
           .build();
           Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------- Trigger Build completed-----------------");
           Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------- Calling run Medthod-----------------");

           //pass initialization parameters into the job
           job.getJobDataMap().put(schedulerJob.SHEDULE_ID, ""+jobid);
           job.getJobDataMap().put(schedulerJob.EXECUTION_COUNT, ""+1);
           int responseCode = 0;
   
   try{
         responseCode = AddToWorkingMemory(job, trigger);
      }
      catch(Exception e)
      {   
          Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.WARNING, "---Create Schedule Error----{0}", e.getMessage());
      }
         return responseCode;
   }//end..
  
   @Override
   public int updateSchedule(String scheduleID, String jobDetail, String Timezone_param, String scheduleGroup) throws IllegalArgumentException
   {   
       try 
       {  
            this.scheduleID = scheduleID;
            int jobid = scheduleID.hashCode();
            JobDetail job = newJob(schedulerJob.class)
            .withIdentity(scheduleID, scheduleGroup)
            .usingJobData(scheduleID, jobid)
            .withDescription("The SIVOTEK ERP/CRM Platform job schedule for "+scheduleGroup+" Group With ID :"+scheduleID)
            .storeDurably(true)
            .build(); 
           
             CronTrigger trigger = newTrigger()
            .withIdentity(scheduleID, scheduleGroup)
            .withSchedule(cronSchedule(jobDetail).inTimeZone(TimeZone.getTimeZone(Timezone_param)))
            .withDescription("Trigger Group name for running job with ID :"+scheduleID)
            //.usingJobData(scheduleID, jobid)
            .build();
            this.taskID = "update";
            this.response_code = AddToWorkingMemory(trigger, job, this.taskID);    
       } 
      catch(Exception e)
      {  
          this.response_code = 500;
          Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "---Update Schedule Error----{0}", e.getMessage()); 
      } 
      return this.response_code;
   }//end...

  
  @Override
     public int disableSchedule(String scheduleID, String scheduleGroup) throws java.lang.IllegalArgumentException
   {   
       try 
       {    
            this.scheduleID = scheduleID;
            int jobid = scheduleID.hashCode();
            JobDetail job = newJob(schedulerJob.class)
            .withIdentity(scheduleID, scheduleGroup)
            .usingJobData(scheduleID, jobid)
            .withDescription("The SIVOTEK ERP/CRM Platform job schedule for "+scheduleGroup+" Group With ID :"+scheduleID)
            .storeDurably(true)
            .build();         
            
   
               CronTrigger trigger = newTrigger()
              .withIdentity(scheduleID, scheduleGroup)
              .withSchedule(cronSchedule("0 15 10 * * ? 2014"))
              .withDescription("The SIVOTEK ERP/CRM Platform trigger schedule for "+scheduleGroup+" Group with ID :"+scheduleID)
              .build();

              this.taskID = "disable";
              this.response_code = AddToWorkingMemory(trigger,job, this.taskID);    
      } 
      catch(Exception e)
      {  
           this.response_code = 500;
           Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "---Update Schedule Error----{0}", e.getMessage()); 
      } 
      return this.response_code;
   }//end..

    

    @Override
    public int resumeSchedule(String scheduleID, String scheduleGroup) throws java.lang.IllegalArgumentException
   {   
       try 
       {          
            this.scheduleID = scheduleID;
            int jobid = scheduleID.hashCode();
            JobDetail job = newJob(schedulerJob.class)
            .withIdentity(scheduleID, scheduleGroup)
            .usingJobData(scheduleID,jobid)
            .withDescription("The SIVOTEK ERP/CRM Platform job schedule for "+scheduleGroup+" Group With ID :"+scheduleID)
            .storeDurably(true)
            .build(); 
           
          CronTrigger trigger = newTrigger()
          .withIdentity(scheduleID, scheduleGroup)
          .withSchedule(cronSchedule("0 15 10 * * ? 2014"))
          .withDescription("The SIVOTEK ERP/CRM Platform Trigger job for "+scheduleGroup+" Group With ID :"+scheduleID)
          .build();
           this.taskID = "resume";
           this.response_code = AddToWorkingMemory(trigger,job, this.taskID);    
      } 
      catch(Exception e)
      {  
          this.response_code = 500;  
      } 
      return this.response_code;
   }//end...
    
    
    @Override
    public int removeSchedule(String scheduleID, String scheduleGroup) throws java.lang.IllegalArgumentException
   {   
       try 
       {   
           this.scheduleID = scheduleID;
           int jobid = scheduleID.hashCode();
           JobDetail job = newJob(schedulerJob.class)
           .withIdentity(scheduleID, scheduleGroup)
           .usingJobData(scheduleID, jobid)
           .withDescription("The SIVOTEK ERP/CRM Platform job schedule for "+scheduleGroup+" Group With ID :"+scheduleID)
           .storeDurably(true)
           .build();
           
          CronTrigger trigger = newTrigger()
          .withIdentity(scheduleID, scheduleGroup)
          .withSchedule(cronSchedule("0 15 10 * * ? 2012"))
          .build();
          this.taskID = "remove";
          this.response_code = AddToWorkingMemory(trigger, job, this.taskID);    
      } 
      catch(Exception e)
      {  
        this.response_code = 500; 
        
      } 
      return this.response_code;
   } //end....
  
   /*
    *-------------------------------------------------------------------------------------||   
    * Polymorphic Function for Adding new job and new trigger to db and working memory...
    * This function is encapsulated from the EJB Local and Remote Interfaces...
    *-------------------------------------------------------------------------------------||
    */
   private int AddToWorkingMemory(JobDetail job, CronTrigger trigger) throws Exception
   {
    Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------- Initializing -------------------");
    try{
             SchedulerFactory sf = new StdSchedulerFactory("com/sivotek/crm/xsd/jaxb/prep/ejb/quartz.properties");
             Scheduler sched = sf.getScheduler();
             Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------- Initialization Complete --------");
             Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------- Scheduling Jobs ----------------");
    
             if(sched.checkExists(trigger.getKey()))
             {
             Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "Reminder ID : {0} Job Already Exist in the working memory", this.scheduleID);

              //System.out.println("Reminder ID : "+this.scheduleID +" Job Already Exist in the working memory");
              this.response_code = 405;//schedule already exists
             }
            else if(!sched.checkExists(trigger.getKey()))
            {
                ft = sched.scheduleJob(job, trigger);
                this.response_code = 101;
                Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "{0} has been scheduled to run at: {1} and repeat based on expression: {2}", new Object[]{job.getKey(), ft, trigger.getCronExpression()});

                //All of the jobs have been added to the scheduler, but none of the jobs
                //will run until the scheduler has been started
                if(!sched.isStarted())
                {
                    Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------- Starting Scheduler ----------------");
                    sched.start();                
                    Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "------- Started Scheduler -----------------");
                    SchedulerMetaData metaData = sched.getMetaData();
                    Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "Executed {0} jobs.", metaData.getNumberOfJobsExecuted());
                    //System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
                }
            }

            else {
                   if(!sched.isStarted())
                   {
                    sched.start();
                   }
                   if(sched.isInStandbyMode()){
                       sched.start();
                   }
                 }
    }
    catch(Exception e)
    {
        
    }
    return this.response_code;
  }//End...
   
  /*
    *--------------------------------------------------------------------------------------||  
    * Polymorphic Function for Updating,Pausing, Removing, Resuming the Quartz Trigger. 
    * This function is encapsulated from the EJB Local and Remote Interfaces
    *--------------------------------------------------------------------------------------||
    */
   private int AddToWorkingMemory(CronTrigger trigger, JobDetail job, String taskID) throws Exception
   {
       try{
       SchedulerFactory sf = new StdSchedulerFactory("com/sivotek/crm/xsd/jaxb/prep/ejb/quartz.properties");
       Scheduler sched = sf.getScheduler();
       
       if(sched.checkExists(trigger.getKey()) && taskID.equalsIgnoreCase("update"))
         {
              TriggerKey tck = trigger.getKey();
              sched.rescheduleJob(tck, trigger);
              this.response_code = 101;
              
          }
       else if(sched.checkExists(trigger.getKey()) && taskID.equalsIgnoreCase("disable"))
       {
           TriggerKey tck = trigger.getKey();
           sched.pauseTrigger(tck);
           this.response_code = 101;
       }
       else if(sched.checkExists(trigger.getKey()) && taskID.equalsIgnoreCase("resume"))
       {
              TriggerKey tck = trigger.getKey();
              sched.resumeTrigger(tck);
              this.response_code = 101;
              
      }
       else if(sched.checkExists(trigger.getKey()) && taskID.equalsIgnoreCase("remove"))
       {
              TriggerKey tck = trigger.getKey();
              JobKey jk = job.getKey();
              sched.unscheduleJob(tck);
              sched.deleteJob(jk);
              this.response_code = 101;
              
       }
       else
       {
           TriggerKey tck = trigger.getKey();
           Logger.getLogger(TranscendEJB_QUARTZProcessor.class.getName()).log(Level.INFO, "-----Trigger with this Key : "+tck+" Does Not Exist ---");

       }
     }catch(Exception e){
           this.response_code = 500;
     }
       return this.response_code;
     
   }//End....
}