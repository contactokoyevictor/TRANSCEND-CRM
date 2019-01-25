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
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmmessagechannelJpaController;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author okoyevictor
 */
public class CrmmessagechannelPrep {
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
   public void crmmessagechannel(List children, int publickey, int companyID)
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
                        String channelname = getElementStringValueFromList("channelname", children);
                        String description = getElementStringValueFromList("description", children);
                        
                        
                        Companyemployee companyemployee = new Companyemployee();
                        CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                        companyemployeePK.setPubkey(publickey);
                        companyemployeePK.setId(Integer.parseInt(employeeid));
                
                        CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                        companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                        if(companyemployee.getCompanyemployeePK().getId() > 0){
                            long bint = System.currentTimeMillis();
                            String p = ""+bint;
                            
                            Crmmessagechannel crmmessagechannel = new Crmmessagechannel();
                            CrmmessagechannelPK crmmessagechannelPK = new CrmmessagechannelPK();
                            crmmessagechannelPK.setPubkey(publickey);
                            crmmessagechannelPK.setChannelid(Integer.parseInt(p.substring(7)));
                            crmmessagechannel.setCrmmessagechannelPK(crmmessagechannelPK);
                            crmmessagechannel.setChannelName(channelname);
                            crmmessagechannel.setChannelDescription(description);
                            crmmessagechannel.setCreateddate(new Date());
                            crmmessagechannel.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmmessagechannelPrep.class");
                            crmmessagechannel.setCompany(company);
                            crmmessagechannel.setCompanyemployee(companyemployee);
                            crmmessagechannel.setCompanyemployee1(companyemployee);
                            CrmmessagechannelJpaController crmmessagechannelJpaController = new CrmmessagechannelJpaController();
                            crmmessagechannelJpaController.create(crmmessagechannel);
                            this.setId(crmmessagechannel.getCrmmessagechannelPK().getChannelid());
                            this.setStatus("OK");
                            this.setStatusmessage("Success");
                
                        }
                   }
               }catch(Exception ex)
               {
                this.setStatus("ERROR");
                this.setStatusmessage(ex.getMessage());
               }
       
   }
}
