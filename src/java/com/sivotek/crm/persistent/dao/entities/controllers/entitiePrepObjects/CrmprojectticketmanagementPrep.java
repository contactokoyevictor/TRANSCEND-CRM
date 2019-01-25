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
import com.sivotek.crm.persistent.dao.entities.Crmproject;
import com.sivotek.crm.persistent.dao.entities.Crmprojecttask;
import com.sivotek.crm.persistent.dao.entities.CrmprojecttaskPK;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketmanagement;
import com.sivotek.crm.persistent.dao.entities.CrmprojectticketmanagementPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmprojecttaskJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmprojectticketmanagementJpaController;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author okoyevictor
 */
public class CrmprojectticketmanagementPrep {
    
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
    //
  //
  //processing project crmproject crmprojectticketmanagement....
  public void crmprojectticketmanagement(List children, int publickey, int companyID){
               Company company = new Company();
               CompanyPK companyPK = new CompanyPK();
               companyPK.setCompanyid(companyID);
               companyPK.setPubkey(publickey);
               CompanyJpaController companyJpaController = new CompanyJpaController();
               company = companyJpaController.findCompany(companyPK);
               
             if(company.getCompanyPK().getCompanyid() > 0){
                String employeeid = getElementStringValueFromList("employeeid", children);
                String projectid = getElementStringValueFromList("projectid", children);
                String taskid = getElementStringValueFromList("taskid", children);
                String issuesubject = getElementStringValueFromList("issuesubject", children);
                String description = getElementStringValueFromList("description", children);
                String relatedtaskid = getElementStringValueFromList("relatedtaskid", children);
                String assignedto = getElementStringValueFromList("assignedto", children);
                String ticketstatus = getElementStringValueFromList("ticketstatus", children);
                String environment = getElementStringValueFromList("environment", children);
                String priority = getElementStringValueFromList("priority", children);
                String progress = getElementStringValueFromList("progress", children);
                String lastcomment = getElementStringValueFromList("lastcomment", children);
                
                XMLGregorianCalendar datereading1 = getElementDateValueFromList("targetedresolutiondate", children);
                XMLGregorianCalendar datereading2 = getElementDateValueFromList("actualresolutiondate", children);
                
                java.sql.Date targetedresolutiondate = new java.sql.Date(datereading1.toGregorianCalendar().getTimeInMillis());
                java.sql.Date actualresolutiondate = new java.sql.Date(datereading2.toGregorianCalendar().getTimeInMillis());
                 
              try{
                  //initialize project details
                    Crmproject crmproject = new Crmproject();
                    
                     
                    //fitch ticket creator details
                    CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                    Companyemployee companyemployee = new Companyemployee();
                    CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                    companyemployeePK.setId(Integer.parseInt(employeeid));
                    companyemployeePK.setPubkey(publickey);
                    companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                    //find assignedto employee details...
                    Companyemployee assignedto_companyemployee = new Companyemployee();
                    CompanyemployeePK companyemployeePK1 = new CompanyemployeePK();
                    companyemployeePK1.setId(Integer.parseInt(assignedto));
                    companyemployeePK1.setPubkey(publickey);
                    assignedto_companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK1);
                   
                    
                    
                    //find project tasks and check for taskid
                    Crmprojecttask crmprojecttask = new Crmprojecttask();
                    CrmprojecttaskPK crmprojecttaskPK = new CrmprojecttaskPK();
                    crmprojecttaskPK.setPubkey(publickey);
                    crmprojecttaskPK.setTaskid(Integer.parseInt(taskid));
                    CrmprojecttaskJpaController crmprojecttaskJpaController = new CrmprojecttaskJpaController();
                    
                    crmprojecttask = crmprojecttaskJpaController.findCrmprojecttask(crmprojecttaskPK);
                    crmproject = crmprojecttask.getCrmproject();
                 if(crmproject.getCrmprojectPK().getProjectid() > 0)
                  {
                    if(crmprojecttask.getCrmprojecttaskPK().getTaskid() > 0){
                             Crmprojectticketmanagement crmprojectticketmanagement = new Crmprojectticketmanagement();
                             CrmprojectticketmanagementPK crmprojectticketmanagementPK = new CrmprojectticketmanagementPK();
                             crmprojectticketmanagementPK.setPubkey(publickey);
                             long bint = System.currentTimeMillis();
                             String p = ""+bint;
                             crmprojectticketmanagementPK.setIssueid(Integer.parseInt(p.substring(7)));
                             crmprojectticketmanagement.setCrmprojectticketmanagementPK(crmprojectticketmanagementPK);
                             crmprojectticketmanagement.setIssuesubject(issuesubject);
                             crmprojectticketmanagement.setEnvironment(environment);
                             crmprojectticketmanagement.setPriority(Integer.parseInt(priority));
                             crmprojectticketmanagement.setProgress(progress);
                             crmprojectticketmanagement.setTicketstatus(ticketstatus);
                             crmprojectticketmanagement.setDescription(description);
                             if(!relatedtaskid.isEmpty()){
                                 crmprojecttask.setParentstaskid(relatedtaskid);
                             }
                             
                             crmprojectticketmanagement.setCompanyemployee(companyemployee);
                             crmprojectticketmanagement.setCompanyemployee1(assignedto_companyemployee);
                             crmprojectticketmanagement.setCompanyemployee2(companyemployee);
                             
                             crmprojectticketmanagement.setLastcomment(lastcomment);
                             crmprojectticketmanagement.setTargetedResolutionDate(targetedresolutiondate);
                             crmprojectticketmanagement.setActualResolutionDate(actualresolutiondate);
                             crmprojectticketmanagement.setIdentifieddate(new Date());
                             crmprojectticketmanagement.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.CrmprojectticketmanagementPrep.class");
                             crmprojectticketmanagement.setCreateddate(new Date());
                             
                             crmprojecttask.setCrmproject(crmproject);
                             crmprojecttaskJpaController.edit(crmprojecttask);
                             crmprojectticketmanagement.setCrmprojecttask(crmprojecttask);
                             
                             CrmprojectticketmanagementJpaController crmprojectticketmanagementJpaController = new CrmprojectticketmanagementJpaController();
                             crmprojectticketmanagementJpaController.create(crmprojectticketmanagement);
                             this.setId(crmprojectticketmanagement.getCrmprojectticketmanagementPK().getIssueid());
                             this.setStatus("OK");
                             this.setStatusmessage("Success");
                    
                    }
                    
              }else{
                       this.setStatus("FAIL");
                       this.setStatusmessage("No such project id..");
                 }   
                }catch(Exception ex){
                    this.setStatus("ERROR");
                    this.setStatusmessage(ex.getMessage());
                }
                
               }
   }

}
