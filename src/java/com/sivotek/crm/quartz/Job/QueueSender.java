/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */
package com.sivotek.crm.quartz.Job;

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

/**
 *
 * @author okoyevictor
 */

public class QueueSender {


    public void sendEmail() {
     try {
             // Create a ConnectionFactory
             ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("admin", "admin", ActiveMQConnection.DEFAULT_BROKER_URL);
             // Create a Connection
             Connection connection = connectionFactory.createConnection();
             connection.start();
             // Create a Session
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             // Create the destination
             Destination destination = session.createQueue("transcendmailqueue");
             // Create a MessageProducer from the Session to the Queue
             javax.jms.MessageProducer producer = session.createProducer(destination);
             producer.setDeliveryMode(DeliveryMode.PERSISTENT);
             
             // Create a messages
             TextMessage message = session.createTextMessage("A message from Message Queue Sender");
             message.setStringProperty("name", "SIVOTEK TRANSCEND CRM QuartzJobsExecutor");
             producer.send(message);
             session.close();
             connection.close();
             Logger.getLogger(QueueSender.class.getName()).log(Level.INFO, "Message sent");
             
             
         }
         catch (Exception e) {
             Logger.getLogger(QueueSender.class.getName()).log(Level.WARNING, "Exception ", e);
             e.printStackTrace();
         }
    }
    public void emailTextMessages(){}
    public void emailMultipartMessages(){}
    public void emailMultipartHTMLMessages(){}
    public void smsMessages(){}
    public void urlCallbackMessages(){}
    
}
