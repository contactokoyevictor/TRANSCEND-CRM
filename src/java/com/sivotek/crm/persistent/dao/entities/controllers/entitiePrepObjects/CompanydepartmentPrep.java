/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companydepartment;
import com.sivotek.crm.persistent.dao.entities.CompanydepartmentPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.CompanyemployeePK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanydepartmentJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanydepartmentPrep {
   private String status = "";
   private String statusmessage = "";
   private int id = 0;
   private Response response;

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
     
   ///
   public List<Response.Page.Elements.Element> companydepartment(List children, int publickey, int companyID){
               //create response Object Factory
               com.sivotek.crm.xsd.jaxb.response.ObjectFactory responseOF = new com.sivotek.crm.xsd.jaxb.response.ObjectFactory();
               //create response <Page> Object
               com.sivotek.crm.xsd.jaxb.response.Response.Page responsePage = responseOF.createResponsePage();
               //create response <elements> object
               com.sivotek.crm.xsd.jaxb.response.Response.Page.Elements responseElements = responseOF.createResponsePageElements();
               //initialize response object
               response = responseOF.createResponse();
               Response.Page.Elements.Element resElement = responseOF.createResponsePageElementsElement();
               List<Response.Page.Elements.Element> responseElementList = responseElements.getElement();
               Company company = new Company();
               CompanyPK companyPK = new CompanyPK();
               companyPK.setCompanyid(companyID);
               companyPK.setPubkey(publickey);
               CompanyJpaController companyJpaController = new CompanyJpaController();
               company = companyJpaController.findCompany(companyPK);
               int empid = 0;
               int dphead = 0;
               int departid = 0;
             if(company.getCompanyPK().getCompanyid() > 0){
                String name = getElementStringValueFromList("name", children);
                String employeeid = getElementStringValueFromList("employeeid", children);
                String code = getElementStringValueFromList("code", children);
                String heads = getElementStringValueFromList("heads", children);
                String description = getElementStringValueFromList("description", children);
                String departmentid = getElementStringValueFromList("departmentid", children);
                
                CompanyemployeeJpaController companyemployeeJpaController;
                Companyemployee companyemployee = new Companyemployee();
                try{
                     
                     
                     if(!employeeid.equalsIgnoreCase("") && !name.equalsIgnoreCase("") && !code.equalsIgnoreCase("") && !heads.equalsIgnoreCase("") && !description.equalsIgnoreCase("") && departmentid == null)
                     {
                        empid = Integer.parseInt(employeeid);
                        dphead = Integer.parseInt(heads);
                        CompanydepartmentJpaController companydepartmentJpaController = new CompanydepartmentJpaController();
                        Companydepartment companydepartment;
                        Companydepartment companydepartment_ = new Companydepartment();
                        CompanydepartmentPK companydepartmentPK = new CompanydepartmentPK();
                        companydepartmentPK.setPubkey(publickey);
                     
                        if(empid > 0){
                         companyemployeeJpaController = new CompanyemployeeJpaController();
                         companyemployee = new Companyemployee();
                         CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                         companyemployeePK.setPubkey(publickey);
                         companyemployeePK.setId(empid);
                         companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                         companydepartment_.setCompanyemployee(companyemployee);
                       } 
                     
                     
                     long bint = System.currentTimeMillis();
                     String p = ""+bint;
                     companydepartmentPK.setId(Integer.parseInt(p.substring(7)));
                     
                     if(dphead > 0){
                         companydepartment = new Companydepartment();
                         companydepartmentPK.setId(dphead);
                         
                         companydepartment = companydepartmentJpaController.findCompanydepartment(companydepartmentPK);
                         companydepartment_.setDepartmentHeads(companydepartment.getDepartmentHeads());
                     }
                     companydepartment_.setCompany(company);
                     companydepartment_.setCompanydepartmentPK(companydepartmentPK);
                     companydepartment_.setDepartmentName(name);
                     companydepartment_.setDepartmentCode(code);
                     companydepartment_.setDescription(description);
                     companydepartment_.setCreateddate(new Date());
                     companydepartment_.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.CompanydepartmentPrep.class");
                     companydepartmentJpaController.create(companydepartment_);
                         
                     //////////////////
                     resElement = responseOF.createResponsePageElementsElement();
                     resElement.setId("companydepartment");
                     resElement.setDepartmentid(companydepartment_.getCompanydepartmentPK().getId());
                     resElement.setElementstatus("OK");
                     resElement.setElementstatusmessage("Success");
                     ////
                     responseElementList.add(resElement);
                     }
                     
                     else if(empid >= 0 && !name.equalsIgnoreCase("") && !code.equalsIgnoreCase("") && dphead >= 0 && !description.equalsIgnoreCase("") && departmentid != null)
                     {
                        departid = Integer.parseInt(departmentid);
                        empid = Integer.parseInt(employeeid);
                        dphead = Integer.parseInt(heads);
                        CompanydepartmentJpaController companydepartmentJpaController = new CompanydepartmentJpaController();
                        Companydepartment companydepartment_ = new Companydepartment();
                        CompanydepartmentPK companydepartmentPK = new CompanydepartmentPK();
                        companydepartmentPK.setPubkey(publickey);
                        if(departid > 0)
                        {
                            companydepartmentPK.setId(departid);
                            companydepartment_ = companydepartmentJpaController.findCompanydepartment(companydepartmentPK);
                            
                        }
                        if(empid > 0){
                         companyemployeeJpaController = new CompanyemployeeJpaController();
                         Companyemployee companyemployee_ch = new Companyemployee();
                         CompanyemployeePK companyemployee_chPK = new CompanyemployeePK();
                         companyemployee_chPK.setPubkey(publickey);
                         companyemployee_chPK.setId(empid);
                         companyemployee_ch = companyemployeeJpaController.findCompanyemployee(companyemployee_chPK);
                         companydepartment_.setCompanyemployee1(companyemployee_ch);
                       }
                       
                        companydepartment_.setDepartmentName(name);
                        companydepartment_.setDepartmentCode(code);
                        companydepartment_.setDescription(description);
                        companydepartment_.setChangeddate(new Date());
                        companydepartment_.setChangedfrom("com.sivotek.crm.persistent.dao.entities.controllers.CompanydepartmentPrep.class");
                     if(dphead > 0)
                       {
                         CompanyemployeeJpaController companyemployeeJpaController_dp = new CompanyemployeeJpaController();
                         Companyemployee companyemployee_dp = new Companyemployee();
                         CompanyemployeePK companyemployee_dpPK = new CompanyemployeePK();
                         companyemployee_dpPK.setPubkey(publickey);
                         companyemployee_dpPK.setId(dphead);
                         companyemployee_dp = companyemployeeJpaController_dp.findCompanyemployee(companyemployee_dpPK);
                         System.out.println("Department head  :"+companyemployee_dp.getCompanyemployeePK().getId());
                         companydepartment_.setDepartmentHeads(companyemployee_dp.getCompanyemployeePK().getId());
                       }
                     
                     companydepartmentJpaController.edit(companydepartment_);
                     //////////////////
                     resElement = responseOF.createResponsePageElementsElement();
                     resElement.setId("companydepartment");
                     resElement.setDepartmentid(companydepartment_.getCompanydepartmentPK().getId());
                     resElement.setElementstatus("OK");
                     resElement.setElementstatusmessage("Success");
                     ////
                     responseElementList.add(resElement);
                         
                     }
                     
                     else if(empid >= 0 && name.equalsIgnoreCase("") && code.equalsIgnoreCase("") && dphead >= 0 && description.equalsIgnoreCase("") && departmentid == null)
                     {
                        if(company.getCompanydepartmentCollection().size() >= 0 && departid <= 0){
                         Collection<Companydepartment> companydepartmentColl = company.getCompanydepartmentCollection(); 
                         for(Companydepartment department : companydepartmentColl){ 
                          //////////////////
                          resElement = responseOF.createResponsePageElementsElement();
                          resElement.setId("companydepartment");
                          resElement.setName(department.getDepartmentName());
                          resElement.setDepartmentid(department.getCompanydepartmentPK().getId());
                          if(department.getDepartmentHeads() != null)
                          {
                             resElement.setHeads(department.getDepartmentHeads());
                          }
                          resElement.setCode(department.getDepartmentCode());
                          resElement.setHeads(department.getDepartmentHeads());
                          resElement.setDescription(department.getDescription());
                          resElement.setElementstatus("OK");
                          resElement.setElementstatusmessage("Success");
                         ////
                         responseElementList.add(resElement);
                         }
                     }
                     }
                     
                }catch(Exception ex){
                    System.out.println("Exception Here :"+ex.getMessage());
                    this.setStatus("ERROR");
                    this.setStatusmessage(ex.getMessage());
                }
                
               }
             return responseElementList;
   }

}
