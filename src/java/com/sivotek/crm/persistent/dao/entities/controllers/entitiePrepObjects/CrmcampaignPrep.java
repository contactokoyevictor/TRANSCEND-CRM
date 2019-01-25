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
import com.sivotek.crm.persistent.dao.entities.Crmcampaign;
import com.sivotek.crm.persistent.dao.entities.CrmcampaignPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmcampaignJpaController;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author okoyevictor
 */
public class CrmcampaignPrep {
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
   public void crmcampaign(List children, int publickey, int companyID){
               Company company = new Company();
               CompanyPK companyPK = new CompanyPK();
               companyPK.setCompanyid(companyID);
               companyPK.setPubkey(publickey);
               
               CompanyJpaController companyJpaController = new CompanyJpaController();
          try{
               company = companyJpaController.findCompany(companyPK);
               
             if(company.getCompanyPK().getCompanyid() > 0){
                String employeeid = getElementStringValueFromList("employeeid", children);
                String name = getElementStringValueFromList("name", children);
                String manager = getElementStringValueFromList("manager", children);
                String Status = getElementStringValueFromList("status", children);
                XMLGregorianCalendar datereading1 = getElementDateValueFromList("validfrom", children);
                XMLGregorianCalendar datereading2 = getElementDateValueFromList("validto", children);
                
                java.sql.Date d1 = new java.sql.Date(datereading1.toGregorianCalendar().getTimeInMillis());
                java.sql.Date d2 = new java.sql.Date(datereading2.toGregorianCalendar().getTimeInMillis());
                
                
                Companyemployee companyemployee = new Companyemployee();
                CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                companyemployeePK.setPubkey(publickey);
                companyemployeePK.setId(Integer.parseInt(employeeid));
                
                CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                if(companyemployee.getCompanyemployeePK().getId() > 0){
                    Crmcampaign crmcampaign = new Crmcampaign();
                    CrmcampaignPK crmcampaignPK = new CrmcampaignPK();
                    long bint = System.currentTimeMillis();
                    String p = ""+bint;
                    crmcampaignPK.setCampaignid(Integer.parseInt(p.substring(7)));
                    crmcampaignPK.setPubkey(publickey);
                    crmcampaign.setCrmcampaignPK(crmcampaignPK);
                    
                    crmcampaign.setName(name);
                    crmcampaign.setCompany(company);
                    //crmcampaign.setStatus(Status);
                    crmcampaign.setValidto(d2);
                    crmcampaign.setValidfrom(d1);
                    
                    crmcampaign.setCreateddate(new Date());
                    crmcampaign.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmcampaignPrep.class");
                    crmcampaign.setCompanyemployee(companyemployee);
                    CrmcampaignJpaController crmcampaignJpaController = new CrmcampaignJpaController();
                    
                    crmcampaignJpaController.create(crmcampaign);
                    this.setId(crmcampaign.getCrmcampaignPK().getCampaignid());
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
