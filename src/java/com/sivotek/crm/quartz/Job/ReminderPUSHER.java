/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.quartz.Job;


import com.sivotek.crm.persistent.dao.entities.Crmscheduler;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmschedulerJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

/**
 *
 * @author okoyevictor
 */
public class ReminderPUSHER {
   private static final Logger _log = Logger.getLogger(ReminderPUSHER.class.getName());
   int companyid;
   int employeeid;
   int publickey;
   String pushaction;
   String pushurl;
   
   private Marshaller marshaller;
   private JAXBContext responseContext;
   private Response response;
   private StringWriter responseWriter;
   
   private String status = "";
   private String statusmessage = "";
   
   CrmschedulerJpaController crmschedulerJpaController = new CrmschedulerJpaController();
   Crmscheduler crmscheduler = new Crmscheduler();
   
   public void fitchSchedule(String sheduleID, Date lastruntime, Date nextruntime){
          
       
       try{
            
            
            crmscheduler = crmschedulerJpaController.findByQuartzscheduleId(Integer.parseInt(sheduleID));
            setPushurl(crmscheduler.getPushUrl());
            
            String resxml = "";
            try{
                resxml = responsePrep("crmscheduler");
            }catch(Exception  exs){
                Logger.getLogger(ReminderPUSHER.class.getName()).log(Level.WARNING, "Exception at this point :{0}", exs.getMessage());
       
            }
            String jaxbAttr8 = resxml.replace(" xsi:type=\"xs:dateTime\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
            String jaxbAttr9 = jaxbAttr8.replace(" xsi:type=\"xs:dateTime\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
            String jaxbAttr10 = jaxbAttr9.replace("\"@xsi:type\": \"xs:dateTime\",", "");
         
            String jaxbAttr11 = jaxbAttr10.replace("xsi:type=\"xs:int\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
            //Logger.getLogger(ReminderPUSHER.class.getName()).log(Level.INFO, "========Making crossdomain Request===============\n");
       
            sendCrossdomainRequest(ConvertXMLResponseToJSON(jaxbAttr11), crmscheduler.getPushUrl());
            
            Logger.getLogger(ReminderPUSHER.class.getName()).log(Level.INFO, "JSON REQUEST{0}", ConvertXMLResponseToJSON(jaxbAttr11));
       
            crmscheduler.setLastRun(lastruntime);
            crmscheduler.setNextRun(nextruntime);
            crmscheduler.setChangeddate(new Date());
            crmscheduler.setChangedfrom("com.sivotek.crm.quartz.ReminderPUSHER.class");
            //CrmschedulerJpaController Controller = new CrmschedulerJpaController();
             
            crmschedulerJpaController.updateByQuartzscheduleId(crmscheduler.getQuartzscheduleId(), lastruntime, nextruntime, crmscheduler.getChangeddate(), crmscheduler.getChangedfrom());
            //Controller.edit(crmscheduler);
            
            //System.out.println("================Sheddule updated==================== :"+sheduleID);
       }catch(Exception ex){
           //_log.log(Level.WARNING, ex.getMessage());
        
            
       }
       
       
   }

   
   private String responsePrep(String elementId) throws Exception{
            try{
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
             
             Response.Page.Elements.Element resElement = responseOF.createResponsePageElementsElement();
          try{ 
                resElement.setId(elementId);
                resElement.setElementstatus("OK");
                resElement.setElementstatusmessage("SUCCESS");
                
          switch (elementId){
                case "crmscheduler":
                     resElement.setEmployeeid(this.crmscheduler.getCompanyemployee().getCompanyemployeePK().getId());
                    
                     resElement.setSchedulerid(this.crmscheduler.getQuartzscheduleId());
                    
                     resElement.setName(this.crmscheduler.getScheduleName());
                     resElement.setScheduleGroup(this.crmscheduler.getScheduleGroup());
                     resElement.setCallbackurl(this.crmscheduler.getPushUrl());
                     resElement.setCallbackaction(this.crmscheduler.getPushAction());
                     resElement.setColor(this.crmscheduler.getColor());
                     resElement.setDescription(this.crmscheduler.getDescription());
                     resElement.setFrequencytype(this.crmscheduler.getFrequencyType());
                     resElement.setActiveweekdays(this.crmscheduler.getActiveWeekdays());
                     resElement.setSchedulestatus(this.crmscheduler.getScheduleStatus());
                     resElement.setLastrun(this.crmscheduler.getLastRun());
                     resElement.setNextrun(this.crmscheduler.getNextRun());
                     resElement.setTimeZoneid(this.crmscheduler.getTimezoneid());
                     this.setStatus("OK");
                     this.setStatusmessage("SUCCESS");
                     resElement.setElementstatus(this.getStatus());
                     resElement.setElementstatusmessage(this.getStatusmessage());
                    break;
                case "":
                    
                    break;
                case "TranscendCRMAppointment":
                    
                    break;
                case "TranscendCRMCampaign":
                   
                    break;
                case "TranscendCRMTask":
                    
                    break;
                case "TranscendCRMTicket":
                    
                    break;
                 case "TranscendCRMEvent":
                    
                    break;
                default:
                    Logger.getLogger(ReminderPUSHER.class.getName()).log(Level.INFO, "{0} Is not registered in the ReminderPUSHER class and therefore will not be executed.", elementId);
             }
            
            }catch(Exception e){
                    _log.log(Level.WARNING, "Exception caught ::{0}", e.getMessage());
                    resElement.setElementstatus("ERROR");
                    resElement.setElementstatusmessage(e.getMessage());
                    String errorMessage = e.getCause().getMessage();
                    if (errorMessage == null){errorMessage = "Unknown exception thrown while processing element.";}
                    resElement.setElementstatusmessage(errorMessage);
                    
                }
            finally{
                   //add all response elements to response list..
                   responseElementList.add(resElement);
                  }
            responsePage.setElements(responseElements);
            response.setPage(responsePage);
            response.setStatus(getStatus());
            response.setStatusmessage(getStatusmessage());
            responseWriter = new StringWriter();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(response, responseWriter);
            
            }catch (Exception e) {
            responseWriter = new StringWriter();
            responseWriter.write(generateErrorResponse("ERROR: ", e));
        }
        
         String res = responseWriter.toString();
         String jaxbAttr6 = res.replace(" xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
         String jaxbAttr7 = jaxbAttr6.replace(" \"@xmlns:xs\": \"http://www.w3.org/2001/XMLSchema\",", "");
         String jaxbAttr8 = jaxbAttr7.replace("\"@xmlns:xsi\": \"http://www.w3.org/2001/XMLSchema-instance\",", "");
         String jaxbAttr9 = jaxbAttr8.replace(" \"@xsi:type\": \"xs:dateTime\",", "");
         String jaxbAttr10 = jaxbAttr9.replace("\"@xsi:type\": \"xs:dateTime\",", "");
         
         String responsexml = jaxbAttr10.replace("xsi:type=\"xs:int\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
         //System.out.println("Sending Auto response ::"+responsexml);       
        return responsexml;  
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
   
   
   
 
    public String getStatus() 
   {return status;}
   public void setStatus(String status) 
   {this.status = status;}
 
   public String getStatusmessage() 
   {return statusmessage;}
   public void setStatusmessage(String statusmessage) 
   {this.statusmessage = statusmessage;}
 
 
    private void sendCrossdomainRequest(String xmldata, String callbackUrl){
        try {
            String strRequestXml = xmldata;
            String strResponseXml = "";
            String hostName = "";
            try {
                java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
                hostName = localMachine.getHostName();
            } catch (java.net.UnknownHostException uhe) {
                //uhe.printStackTrace();
            }
            HttpURLConnection myHttpReq = (HttpURLConnection) new java.net.URL(callbackUrl).openConnection();
            myHttpReq.setRequestMethod("POST");
            myHttpReq.setUseCaches(false);
            myHttpReq.setDoInput(true);
            myHttpReq.setDoOutput(true);
            myHttpReq.setRequestProperty( "Content-Type", "aplication/xml" );
            try {
                OutputStream os = myHttpReq.getOutputStream();
                os.write(strRequestXml.getBytes());
            } catch (IOException ioe) {
                //TODO
            }
            try {
                int rc = myHttpReq.getResponseCode();
                
                if(rc == 200){
                    _log.log(Level.INFO, "Response code is : {0}", rc);
                   
                }
                else if(rc == 500){
                    _log.log(Level.INFO, "Response code is : {0}", rc);
                   
                }
                else{
                    _log.log(Level.INFO, "Response code is : {0}", rc);
                   
                }
                
                InputStream is = myHttpReq.getInputStream();
                if (is != null) {
                    Writer writer = new StringWriter();
                    char[] buffer = new char[1024];
                    try {
                        Reader reader = new java.io.BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                        int n;
                        while ((n = reader.read(buffer)) != -1) {
                            writer.write(buffer, 0, n);
                        }
                         strResponseXml = writer.toString();
                         _log.log(Level.INFO, "Request Response is : {0}", strResponseXml);
                   
                    } finally {
                        is.close();
                    }
                }
               
            } catch (IOException ioe) {
               _log.log(Level.WARNING, "Exception: '{'0'}'{0}", ioe.getMessage());
                   
                //ioe.printStackTrace();
            }
        }
        catch( IOException e ){
            _log.log(Level.INFO, "Exception: '{'0'}'{0}", e.getMessage());
                    
            //e.printStackTrace();
        }
        catch( Exception ex) {
            _log.log(Level.WARNING, "Exception: '{'0'}'{0}", ex.getMessage());
            //ex.printStackTrace();
        }
    }
    
       
  //convert xml data to json data.....  
   public String ConvertXMLResponseToJSON(String xml)
    {
   		XMLSerializer xmlSerializer = new XMLSerializer(); 
                xmlSerializer.setArrayName("response");
                xmlSerializer.clearNamespaces();
                xmlSerializer.setForceTopLevelObject(true);
                
                String jaxbAttr6 = xml.replace(" xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
                String jaxbAttr7 = jaxbAttr6.replace("xsi:type=\"xs:int\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
                
                String jaxbAttr8 = jaxbAttr7.replace("\"@xmlns:xs\": \"http://www.w3.org/2001/XMLSchema\"", "");
                String jaxbAttr9 = jaxbAttr8.replace("\"@xmlns:xsi\": \"http://www.w3.org/2001/XMLSchema-instance\"", "");
         
                String responsexml = jaxbAttr9.replace("\"@xsi:type\": \"xs:dateTime\"", "");
         
                
                JSON json = xmlSerializer.read(responsexml);
                String jsonString = json.toString(4);
                
        return jsonString;
    }//
   
    public int getCompanyid() {return companyid;}

    public void setCompanyid(int companyid) {this.companyid = companyid;}

    public int getEmployeeid() {return employeeid;}

    public void setEmployeeid(int employeeid) {this.employeeid = employeeid;}

    public int getPublickey() {return publickey;}

    public void setPublickey(int publickey) {this.publickey = publickey;}

    public String getPushaction() {return pushaction;}

    public void setPushaction(String pushaction) {this.pushaction = pushaction;}

    public String getPushurl() {return pushurl;}

    public void setPushurl(String pushurl) {this.pushurl = pushurl;}
    
 
}
