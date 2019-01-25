/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */
package com.sivotek.crm.eai.connector.jms.mdb;

import com.sivotek.crm.quartz.Job.EmailManager;
import com.sivotek.crm.quartz.Job.ReminderPUSHER;
import com.sivotek.crm.quartz.Job.SMSManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author okoyevictor
 */

@MessageDriven(mappedName="transcendqueue", activationConfig = { @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue")})
public class MessageDrivenBean implements MessageListener{
    private static final  Logger _log = Logger.getLogger(MessageDrivenBean.class.getName());
    
    private String contentType;//text/plain, text/json, text/xml, text/html, etc...
    private String msgType;//sms, email, urlcallback IVR etc..
    private String msgSubject;//message subject.
    private String msgSender;//employee name
    private Collection<Object> msgRecepient;//email addresses or phone numbers
    private Collection<Object> CC;// CC
    private String url;
    private String msgBody;//message body...
    private int employeeid; //employee ID
    private int companyid;//company ID
    private int pubkey;//public key
    private int msgid;//message unique ID
    private String htmlBody;//email template html body..
    private byte[] attachmentData;//single attchment...
    private Collection<ArrayList> attachmentsData;//multiple attachments for multipart email message
    private Multipart multipart = null;//multipart message object
    private String multipartContentType;//multipart email contentype
    private MimeBodyPart htmlPart = null;//html part
    private MimeBodyPart attachment = null;//Mime Body part..
    private String sheduleID;
    private Date lastruntime;
    private Date nextruntime;
    
    @Override
    public void onMessage(Message message)
    {
        _log.log(Level.INFO, "Message received...");
       TextMessage msg = null; 
        try{
            if (message instanceof TextMessage) 
             {
              msg = (TextMessage) message;
              setMsgType(message.getStringProperty("msgType"));
              //
               switch (getMsgType()){
                 case "emailText":
                     Logger.getLogger(MessageDrivenBean.class.getName()).log(Level.INFO, "email text message processing..");
                     setContentType(message.getStringProperty("contentType"));
                     setMsgSender(message.getStringProperty("sender"));
                     setMsgRecepient((Collection<Object>) message.getObjectProperty("recepients"));
                     setCC((Collection<Object>) message.getObjectProperty("CC"));
                     setMsgSubject(message.getStringProperty("subject"));
                     setMsgBody(msg.getText());
                     EmailManager emailsender = new EmailManager();
                     emailsender.sendEmailTextMessage(getMsgSender(), getMsgRecepient(), getCC(), getMsgSubject(), getMsgBody(), getContentType());
                     
                    break;
                 case "urlcallback":
                     Logger.getLogger(MessageDrivenBean.class.getName()).log(Level.INFO, "urlcallback processing..");
                     setMsgBody(msg.getText());
                     url = message.getStringProperty("url");
                     sheduleID = message.getStringProperty("sheduleID");
                     Object objelast = message.getStringProperty("lastruntime");
                     Object objenext = message.getStringProperty("nextruntime");
                     
                     java.util.Calendar cal1 = DatatypeConverter.parseDateTime(objelast.toString());
                     java.util.Calendar cal2 = DatatypeConverter.parseDateTime(objenext.toString());
                     
                     java.sql.Date dt1 = new java.sql.Date(cal1.getTimeInMillis());
                     java.sql.Date dt2 = new java.sql.Date(cal2.getTimeInMillis());
                     
                     ReminderPUSHER reminderPUSHER = new ReminderPUSHER();
                     reminderPUSHER.fitchSchedule(sheduleID, dt1, dt2);
                     
                    break;
                 case "sms":
                     _log.log(Level.INFO, "sms message processing..");
                     SMSManager smssender = new SMSManager();
                     smssender.smsMessages();
                    break;
                 default:
                    _log.log(Level.INFO, "Unregistered Message contentType: {0}", message.getClass().getName());
                   
              }
            } 
            else {
                _log.log(Level.WARNING, "Message of wrong type: {0}", message.getClass().getName());    
            }
            
        }
        catch (JMSException e) {
            _log.log(Level.WARNING, "JMSException in onMessage(): {0}", e.toString());
            } 
        
        
    }

    
    public String getContentType() {return contentType;}

    public void setContentType(String contentType) {this.contentType = contentType;}

    public String getMsgType() {return msgType;}

    public void setMsgType(String msgType) {this.msgType = msgType;}

    public String getMsgSubject() {return msgSubject;}

    public void setMsgSubject(String msgSubject) {this.msgSubject = msgSubject;}

    public String getMsgSender() {return msgSender;}

    public void setMsgSender(String msgSender) {this.msgSender = msgSender;}

    public Collection<Object> getMsgRecepient() {return msgRecepient;}

    public void setMsgRecepient(Collection<Object> msgRecepient) {this.msgRecepient = msgRecepient;}

    public String getMsgBody() {return msgBody;}

    public void setMsgBody(String msgBody) {this.msgBody = msgBody;}

    public int getEmployeeid() {return employeeid;}

    public void setEmployeeid(int employeeid) {this.employeeid = employeeid;}

    public int getCompanyid() {return companyid;}

    public void setCompanyid(int companyid) {this.companyid = companyid;}

    public int getPubkey() {return pubkey;}

    public void setPubkey(int pubkey) {this.pubkey = pubkey;}

    public int getMsgid() {return msgid;}

    public void setMsgid(int msgid) {this.msgid = msgid;}

    public String getHtmlBody() {return htmlBody;}

    public void setHtmlBody(String htmlBody) {this.htmlBody = htmlBody;}

    public byte[] getAttachmentData() {return attachmentData;}

    public void setAttachmentData(byte[] attachmentData) {this.attachmentData = attachmentData;}

    public Multipart getMultipart() {return multipart;}

    public void setMultipart(Multipart multipart) {this.multipart = multipart;}

    public MimeBodyPart getHtmlPart() {return htmlPart;}

    public void setHtmlPart(MimeBodyPart htmlPart) {this.htmlPart = htmlPart;}

    public MimeBodyPart getAttachment() {return attachment;}

    public void setAttachment(MimeBodyPart attachment) {this.attachment = attachment;}
    
    public Collection<Object> getCC() { return CC;}
    public void setCC(Collection<Object> CC) {this.CC = CC;}

   
}
