/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.quartz.Job;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.TextMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author okoyevictor
 */
public class EmailManager {

     public void sendEmailTextMessage(String sender, Collection<Object> recepients, Collection<Object> CC, String subject, String messagebody, String contentType)
    {
            String errmsg;
            // setup the mail server properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            
            props.setProperty("mail.transport.protocol", "smtp"); 
            props.setProperty("mail.host", "smtp.gmail.com"); 
            props.setProperty("mail.transport.pool-size", "50"); 

            // set up the message
            Session session = Session.getInstance(props);
            //set up transport
            //javax.mail.Transport transport = session.getTransport();
            Message message = new MimeMessage(session);
            try{
                for(Object obj : recepients){
                    // add a TO address
                    message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(obj.toString()));
                }
                for(Object obj : CC)
                {
                  //add a multiple CC addresses
                   message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(obj.toString()));
                }
            
            javax.mail.Address address = new javax.mail.internet.InternetAddress(sender);
            message.setFrom(address);
            message.setSubject(subject);
            message.setContent(messagebody, contentType);
            message.setSentDate(new java.util.Date());

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", 587, "contactokoyevictor@gmail.com", "fernandez213");
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("successfully send email");
            errmsg ="successfully send email";
            }catch(Exception e){
            System.out.println("Email was unsuccessfull..." +e);
            errmsg = e.getMessage();
            }   
    }
     
     
    public void sendWithMultipart()
    {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        java.util.Properties properties = new java.util.Properties(); 
        properties.setProperty("mail.transport.protocol", "smtp"); 
        properties.setProperty("mail.host", "mail1.domain.com,mail2.domain.com"); 
        properties.setProperty("mail.transport.pool-size", "10"); 
        // ...
        String htmlBody = "";        // ...
        byte[] attachmentData = null;  // ...
        
        try {
         Multipart mp = new MimeMultipart();

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(htmlBody, "text/html");
        mp.addBodyPart(htmlPart);

        MimeBodyPart attachment = new MimeBodyPart();
        attachment.setFileName("manual.pdf");
        attachment.setContent(attachmentData, "application/pdf");
        
        mp.addBodyPart(attachment);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
        msg.addRecipient(Message.RecipientType.TO,new InternetAddress("user@example.com", "Mr. User"));
        
        msg.setSubject("Your Example.com account has been activated");
        msg.setContent(mp);
            
        Transport.send(msg);
       } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void emailTextMessages(){}
    public void emailMultipartMessages(){}
    public void emailMultipartHTMLMessages(){}
    
    
    
}
