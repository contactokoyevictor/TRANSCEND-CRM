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
import com.sivotek.crm.persistent.dao.entities.CrmprojectPK;
import com.sivotek.crm.persistent.dao.entities.Crmprojecttask;
import com.sivotek.crm.persistent.dao.entities.CrmprojecttaskPK;
import com.sivotek.crm.persistent.dao.entities.Crmprojectteammembers;
import com.sivotek.crm.persistent.dao.entities.CrmprojectteammembersPK;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketmanagement;
import com.sivotek.crm.persistent.dao.entities.CrmprojectticketmanagementPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmprojectJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmprojecttaskJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmprojectticketmanagementJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmprojectteammembersJpaController;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author okoyevictor
 */
public class CrmprojectPrep {
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
     
     public void crmproject(List children, int publickey, int companyID){
               Company company = new Company();
               CompanyPK companyPK = new CompanyPK();
               companyPK.setCompanyid(companyID);
               companyPK.setPubkey(publickey);
               CompanyJpaController companyJpaController = new CompanyJpaController();
               company = companyJpaController.findCompany(companyPK);
               int empid = 0;
               int projectmanager = 0;
             if(company.getCompanyPK().getCompanyid() > 0){
                String employeeid = getElementStringValueFromList("employeeid", children);
                String projectnr = getElementStringValueFromList("projectnr", children);
                String name = getElementStringValueFromList("name", children);
                String manager = getElementStringValueFromList("manager", children);
                String budget = getElementStringValueFromList("budget", children);
                String actualcost = getElementStringValueFromList("actualcost", children);
                String remaincost = getElementStringValueFromList("remaincost", children);
                String projectstatus = getElementStringValueFromList("status", children);
                XMLGregorianCalendar datereading1 = getElementDateValueFromList("validfrom", children);
                XMLGregorianCalendar datereading2 = getElementDateValueFromList("validto", children);
                
                java.sql.Date d1 = new java.sql.Date(datereading1.toGregorianCalendar().getTimeInMillis());
                java.sql.Date d2 = new java.sql.Date(datereading2.toGregorianCalendar().getTimeInMillis());
                try{
                    CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                    Companyemployee companyemployee = new Companyemployee();
                    CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                    companyemployeePK.setId(Integer.parseInt(employeeid));
                    companyemployeePK.setPubkey(publickey);
                    
                    companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                   if(companyemployee.getCompanyemployeePK().getId() > 0){
                     empid = Integer.parseInt(employeeid);
                     CrmprojectJpaController crmprojectJpaController = new CrmprojectJpaController();
                     Crmproject crmproject = new Crmproject();
                     CrmprojectPK crmprojectPK = new CrmprojectPK();
                     
                     if(!manager.isEmpty())
                     {
                       projectmanager = Integer.parseInt(manager);
                       Companyemployee companyemployee2 = new Companyemployee();
                       CompanyemployeePK companyemployeePK2 = new CompanyemployeePK();
                       companyemployeePK2.setId(projectmanager);
                       companyemployeePK2.setPubkey(publickey);
                       companyemployee2 = companyemployeeJpaController.findCompanyemployee(companyemployeePK2);
                       if(companyemployee2.getCompanyemployeePK().getId() > 0){
                         crmproject.setCompanyemployee(companyemployee2);
                       }
                     }
                     long bint = System.currentTimeMillis();
                     String p = ""+bint;
                     
                     
                     
                     crmprojectPK.setProjectid(Integer.parseInt(p.substring(7)));
                     crmprojectPK.setPubkey(publickey);
                     crmproject.setCrmprojectPK(crmprojectPK);
                     crmproject.setName(name);
                     crmproject.setProjectnr(projectnr);
                     crmproject.setBudget(Double.parseDouble(budget));
                     crmproject.setActualcost(Double.parseDouble(actualcost));
                     crmproject.setRemaincost(Double.parseDouble(remaincost));
                     //crmproject.setStatus(projectstatus);
                     crmproject.setValidfrom(d1);
                     crmproject.setValidto(d2);
                     crmproject.setCreateddate(new Date());
                     crmproject.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.CrmprojectPrep.class");
                     crmproject.setCompanyemployee1(companyemployee);
                     crmprojectJpaController.create(crmproject);
                         
                     this.setId(crmproject.getCrmprojectPK().getProjectid());
                     this.setStatus("OK");
                     this.setStatusmessage("Success");
                     
                   }
                   else{
                       this.setStatus("FAIL");
                       this.setStatusmessage("Invalid Employee ID");
                   }  
                     
                     
                   
                     
                }catch(Exception ex){
                    this.setStatus("ERROR");
                    this.setStatusmessage(ex.getMessage());
                }
                
               }
   }   
     
  //processing project tasks....
  public void crmprojecttask(List children, int publickey, int companyID){
               Company company = new Company();
               CompanyPK companyPK = new CompanyPK();
               companyPK.setCompanyid(companyID);
               companyPK.setPubkey(publickey);
               CompanyJpaController companyJpaController = new CompanyJpaController();
               company = companyJpaController.findCompany(companyPK);
               
             if(company.getCompanyPK().getCompanyid() > 0)
             {
                String employeeid = getElementStringValueFromList("employeeid", children);
                String projectid = getElementStringValueFromList("projectid", children);
                String parenttaskid = getElementStringValueFromList("parenttaskid", children);
                String X = getElementStringValueFromList("x", children);
                String Y = getElementStringValueFromList("y", children);
                String name = getElementStringValueFromList("name", children);
                String taskstatus = getElementStringValueFromList("status", children);
                String tasktype = getElementStringValueFromList("tasktype", children);
                String color = getElementStringValueFromList("color", children);
                String done = getElementStringValueFromList("done", children);
                String duration = getElementStringValueFromList("duration", children);
                String description = getElementStringValueFromList("description", children);
                
              try{
                    CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                    Companyemployee companyemployee = new Companyemployee();
                    CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                    companyemployeePK.setId(Integer.parseInt(employeeid));
                    companyemployeePK.setPubkey(publickey);
                    
                    companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                   if(companyemployee.getCompanyemployeePK().getId() > 0){
                     Crmprojecttask crmprojecttask = new Crmprojecttask();
                     CrmprojecttaskPK crmprojecttaskPK = new CrmprojecttaskPK(); 
                     CrmprojecttaskJpaController crmprojecttaskJpaController = new CrmprojecttaskJpaController();
                         
                       
                     //initialize project details
                     CrmprojectJpaController crmprojectJpaController = new CrmprojectJpaController();
                     Crmproject crmproject = new Crmproject();
                     CrmprojectPK crmprojectPK = new CrmprojectPK();
                     crmprojectPK.setPubkey(publickey);
                     crmprojectPK.setProjectid(Integer.parseInt(projectid));
                     //find project details
                     crmproject = crmprojectJpaController.findCrmproject(crmprojectPK);
                     
                     
                     //initialize parent task details
                     Crmprojecttask crmprojecttask2 = new Crmprojecttask();
                     CrmprojecttaskPK crmprojecttaskPK2 = new CrmprojecttaskPK();
                     int parentprojecttask = 0; 
                     if(!parenttaskid.isEmpty() && !parenttaskid.equalsIgnoreCase(""))
                     {
                         parentprojecttask = Integer.parseInt(parenttaskid);
                         
                     }
                     
                     if(parentprojecttask > 0){
                         crmprojecttaskPK2.setPubkey(publickey);
                         crmprojecttaskPK2.setTaskid(parentprojecttask);
                         crmprojecttask2 = crmprojecttaskJpaController.findCrmprojecttask(crmprojecttaskPK2);
                         //if parent task exists...the assign as parent task
                        if(crmprojecttask.getCrmprojecttaskPK().getTaskid() > 0)
                        {
                            crmprojecttask.setParentstaskid(""+crmprojecttask2.getCrmprojecttaskPK().getTaskid());
                           
                        }
                     }
                     crmprojecttask.setCrmproject(crmproject);
                     crmprojecttask.setX(Integer.parseInt(X));
                     crmprojecttask.setY(Integer.parseInt(Y));
                     crmprojecttask.setName(name);
                     crmprojecttask.setStatus(taskstatus);
                     crmprojecttask.setType(tasktype);
                     crmprojecttask.setColor(color);
                     crmprojecttask.setDone(Integer.parseInt(done));
                     crmprojecttask.setDuration(duration);
                     crmprojecttask.setDescription(description);
                     
                     
                     long bint = System.currentTimeMillis();
                     String p = ""+bint;
                     
                     crmprojecttaskPK.setPubkey(publickey);
                     crmprojecttaskPK.setTaskid(Integer.parseInt(p.substring(7)));
                     crmprojecttask.setCrmprojecttaskPK(crmprojecttaskPK);
                     crmprojecttask.setCompanyemployee1(companyemployee);
                     crmprojecttask.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.CrmprojectPrep.class");
                     crmprojecttask.setCreateddate(new Date());
                     crmprojecttaskJpaController.create(crmprojecttask);
                     
                         
                     this.setId(crmprojecttask.getCrmprojecttaskPK().getTaskid());
                     this.setStatus("OK");
                     this.setStatusmessage("Success");
                     
                   }
                   else{
                       this.setStatus("FAIL");
                       this.setStatusmessage("Invalid Employee ID");
                   }  
                     
                }catch(Exception ex){
                    this.setStatus("ERROR");
                    this.setStatusmessage(ex.getMessage());
                }
                
               }
   }   
  
  
  //
  //processing project crmproject teammembers....
  public void crmprojectteammembers(List children, int publickey, int companyID){
               Company company = new Company();
               CompanyPK companyPK = new CompanyPK();
               companyPK.setCompanyid(companyID);
               companyPK.setPubkey(publickey);
               CompanyJpaController companyJpaController = new CompanyJpaController();
               company = companyJpaController.findCompany(companyPK);
               
             if(company.getCompanyPK().getCompanyid() > 0){
                String employeeid = getElementStringValueFromList("employeeid", children);
                String projectid = getElementStringValueFromList("projectid", children);
                String teammemberid = getElementStringValueFromList("teammemberid", children);
                
              try{
                  //initialize project details
                     CrmprojectJpaController crmprojectJpaController = new CrmprojectJpaController();
                     Crmproject crmproject = new Crmproject();
                     CrmprojectPK crmprojectPK = new CrmprojectPK();
                     crmprojectPK.setPubkey(publickey);
                     crmprojectPK.setProjectid(Integer.parseInt(projectid));
                     //find project details
                     crmproject = crmprojectJpaController.findCrmproject(crmprojectPK);
                 if(crmproject.getCrmprojectPK().getProjectid() > 0)
                  {
                    CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                    Companyemployee companyemployee = new Companyemployee();
                    CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                    companyemployeePK.setId(Integer.parseInt(employeeid));
                    companyemployeePK.setPubkey(publickey);
                    //find employee details
                    companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                    
                    //find teammber employee details
                    Companyemployee companyemployee2 = new Companyemployee();
                    companyemployeePK.setId(Integer.parseInt(teammemberid));
                    companyemployee2 = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                    if(companyemployee.getCompanyemployeePK().getId() > 0 && companyemployee2.getCompanyemployeePK().getId() > 0){
                     CrmprojectteammembersJpaController crmprojectteammembersJpaController = new CrmprojectteammembersJpaController();
                     Crmprojectteammembers crmprojectteammembers = new Crmprojectteammembers();
                     CrmprojectteammembersPK crmprojectteammembersPK = new CrmprojectteammembersPK();
                     long bint = System.currentTimeMillis();
                     String p = ""+bint;
                     crmprojectteammembersPK.setPubkey(publickey);
                     crmprojectteammembersPK.setId(Integer.parseInt(p.substring(5)));
                     crmprojectteammembers.setCrmproject(crmproject);
                     crmprojectteammembers.setCompanyemployee(companyemployee2);
                     crmprojectteammembers.setCompanyemployee2(companyemployee);
                     crmprojectteammembers.setCrmprojectteammembersPK(crmprojectteammembersPK);
                     crmprojectteammembers.setCreateddate(new Date());
                     crmprojectteammembers.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.CrmprojectPrep.class");
                     
                     crmprojectteammembersJpaController.create(crmprojectteammembers);
                         
                     this.setId(crmprojectteammembers.getCrmprojectteammembersPK().getId());
                     this.setStatus("OK");
                     this.setStatusmessage("Success");
                     
                   }
                   else{
                       this.setStatus("FAIL");
                       this.setStatusmessage("Invalid Employee ID");
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
                             crmprojectticketmanagementPK.setIssueid(Integer.parseInt(p.substring(5)));
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
                             crmprojectticketmanagement.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.CrmprojectPrep.class");
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
