/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */

package com.sivotek.crm.quartz.Job;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.TriggerKey;

/**
 *
 * @author okoyevictor
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution

public class schedulerJob implements Job{
    private static final Logger _log = Logger.getLogger(schedulerJob.class.getName());
   /**
    *----------------------------------------------------------||
    * Since Quartz will re-instantiate a class every time it
    * gets executed, members non-static member variables can
    * not be used to maintain state! 
    *----------------------------------------------------------||
    */
    //parameter names specific to this job
    public static final String SHEDULE_ID = "scheduleid";
    public static final String EXECUTION_COUNT = "count";
    
    /**
     *---------------------------------------------------------||
     * Empty constructor for job initialization
     * Quartz requires a public empty constructor so that the
     * scheduler can instantiate the class whenever it needs.
     *---------------------------------------------------------||
     */
    public schedulerJob() {
    }
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException 
    {
        try {
            JobKey jobKey = context.getJobDetail().getKey();
            TriggerKey tk = context.getTrigger().getKey();
            // Grab and print passed parameters
            JobDataMap data = context.getJobDetail().getJobDataMap();
            Date nextFireTime = new Date();
            Date lastFireTime = new Date();
            lastFireTime = context.getFireTime();
            nextFireTime = context.getNextFireTime();
            String scheduleID = data.getString(SHEDULE_ID);
            int count = data.getInt(EXECUTION_COUNT);
            
            
//        System.out.println("schedulerJob: " + jobKey.getName() + " executing at " + new Date() + "\n" +
//        "  schedule id is " + scheduleID + "\n" +
//        "  execution count for job with id:"+jobKey+ " (from job map) is " + count + "\n" ); 
//        System.out.println("Job Unique Key is :" +jobKey+" : and Next Fire Time is :: "+ nextFireTime.toString());
            
            /**
             *----------------------------------------------------------------------------||
             * this is where we make the call that will handle the
             * email/sms/IVR/Others sending and pass job params to it for processing....
             *----------------------------------------------------------------------------||
             */
            
            switch (jobKey.getGroup()){
                case "TranscendCRMReminder":
                    _log.log(Level.INFO, "Runing task :: Shedule Group is :{0}", jobKey.getGroup());
                    remindNotifier(jobKey.getName(), lastFireTime, nextFireTime);
                    break;
                case "":
                    _log.log(Level.INFO, "Shedule Group is :{0}", jobKey.getGroup());
                    break;
                case "TranscendCRMAppointment":
                    _log.log(Level.INFO, "Shedule Group is :{0}", jobKey.getGroup());
                    break;
                case "TranscendCRMCampaign":
                    _log.log(Level.INFO, "Shedule Group is :{0}", jobKey.getGroup());
                    break;
                case "TranscendCRMTask":
                    _log.log(Level.INFO, "Shedule Group is :{0}", jobKey.getGroup());
                    break;
                case "TranscendCRMTicket":
                    _log.log(Level.INFO, "Shedule Group is :{0}", jobKey.getGroup());
                    break;
                 case "TranscendCRMEvent":
                     _log.log(Level.INFO, "Shedule Group is :{0}", jobKey.getGroup());
                    break;
                default:
                    _log.log(Level.INFO, "Schedule Group  :{0} Is not registered in the schedulerJob class and therefore will not be executed....", jobKey.getGroup());
           }

            /**
             *----------------------------------------------------------------------------||
             * increment the count and store it back into the
             * job map so that job state can be properly maintained
             *----------------------------------------------------------------------------||
             */
            count++;
            data.put(EXECUTION_COUNT, count);
        } catch (Exception ex) {
            Logger.getLogger(schedulerJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private void remindNotifier(String sheduleID, Date lastruntime, Date nextruntime)
    {
        try {
             // Create a ConnectionFactory
             ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("admin", "admin", ActiveMQConnection.DEFAULT_BROKER_URL);
             // Create a Connection
             Connection connection = connectionFactory.createConnection();
             connection.start();
             // Create a Session
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             // Create the destination
             Destination destination = session.createQueue("transcendqueue");
             // Create a MessageProducer from the Session to the Queue
             javax.jms.MessageProducer producer = session.createProducer(destination);
             producer.setDeliveryMode(DeliveryMode.PERSISTENT);
             // Create a messages
             TextMessage message = session.createTextMessage("TranscendCRMReminder");
             
             message.setStringProperty("msgType", "urlcallback");
             message.setStringProperty("sheduleID", sheduleID);
             message.setStringProperty("lastruntime", lastruntime.toInstant().toString());
             message.setStringProperty("nextruntime", nextruntime.toInstant().toString());
             
             producer.send(message);
             session.close();
             connection.close();
             Logger.getLogger(schedulerJob.class.getName()).log(Level.INFO, "Message sent");
             
             
         }
         catch (Exception e) {
             Logger.getLogger(schedulerJob.class.getName()).log(Level.WARNING, "Exception {0}", e);
             //e.printStackTrace();
         }
    }
}
