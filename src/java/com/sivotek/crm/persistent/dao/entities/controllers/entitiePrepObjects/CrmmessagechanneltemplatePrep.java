/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.CompanyemployeePK;
import com.sivotek.crm.persistent.dao.entities.Crmmessagechannel;
import com.sivotek.crm.persistent.dao.entities.CrmmessagechannelPK;
import com.sivotek.crm.persistent.dao.entities.Crmmessagechanneltemplate;
import com.sivotek.crm.persistent.dao.entities.CrmmessagechanneltemplatePK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmmessagechannelJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmmessagechanneltemplateJpaController;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author okoyevictor
 */
public class CrmmessagechanneltemplatePrep {
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
   public void crmmessagechanneltemplate(List children, int publickey, int companyID)
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
                        String channelid = getElementStringValueFromList("channelid", children);
                        String templatename = getElementStringValueFromList("templatename", children);
                        String templatedescription = getElementStringValueFromList("templatedescription", children);
                        String messagebody = getElementStringValueFromList("messagebody", children);
                        
                        
                        Companyemployee companyemployee = new Companyemployee();
                        CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                        companyemployeePK.setPubkey(publickey);
                        companyemployeePK.setId(Integer.parseInt(employeeid));
                
                        CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                        companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                        if(companyemployee.getCompanyemployeePK().getId() > 0){
                            
                            
                            Crmmessagechannel crmmessagechannel = new Crmmessagechannel();
                            CrmmessagechannelPK crmmessagechannelPK = new CrmmessagechannelPK();
                            crmmessagechannelPK.setPubkey(publickey);
                            crmmessagechannelPK.setChannelid(Integer.parseInt(channelid));
                            CrmmessagechannelJpaController crmmessagechannelJpaController = new CrmmessagechannelJpaController();
                            crmmessagechannel = crmmessagechannelJpaController.findCrmmessagechannel(crmmessagechannelPK);
                            if(crmmessagechannel.getCrmmessagechannelPK().getChannelid() > 0)
                            {
                                long bint = System.currentTimeMillis();
                                String p = ""+bint;
                                Crmmessagechanneltemplate crmmessagechanneltemplate = new Crmmessagechanneltemplate();
                                CrmmessagechanneltemplatePK crmmessagechanneltemplatePK = new CrmmessagechanneltemplatePK();
                                crmmessagechanneltemplatePK.setPubkey(publickey);
                                crmmessagechanneltemplatePK.setChanneltemplateid(Integer.parseInt(p.substring(7)));
                                crmmessagechanneltemplate.setCrmmessagechanneltemplatePK(crmmessagechanneltemplatePK);
                                crmmessagechanneltemplate.setCrmmessagechannel(crmmessagechannel);
                                crmmessagechanneltemplate.setTemplateName(templatename);
                                crmmessagechanneltemplate.setTemplateDescription(templatedescription);
                                crmmessagechanneltemplate.setMessageBody(messagebody);
                                crmmessagechanneltemplate.setCompanyemployee1(companyemployee);
                                crmmessagechanneltemplate.setCreateddate(new Date());
                                crmmessagechanneltemplate.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmmessagechanneltemplatePrep.class");
                                CrmmessagechanneltemplateJpaController crmmessagechanneltemplateJpaController = new CrmmessagechanneltemplateJpaController();
                                crmmessagechanneltemplateJpaController.create(crmmessagechanneltemplate);
                                this.setId(crmmessagechanneltemplate.getCrmmessagechanneltemplatePK().getChanneltemplateid());
                                this.setStatus("OK");
                                this.setStatusmessage("Success");
                            
                            }
                            else{
                                this.setStatus("FAIL");
                                this.setStatusmessage("Invalid message channel id..");
                            }
                            
                        }
                   }
               }catch(Exception ex)
               {
                this.setStatus("ERROR");
                this.setStatusmessage(ex.getMessage());
               }
       
   }

}
