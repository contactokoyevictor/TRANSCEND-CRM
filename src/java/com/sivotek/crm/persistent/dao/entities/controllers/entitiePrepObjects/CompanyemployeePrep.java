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
public class CompanyemployeePrep {
   private Response response;
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
     
   ///
   public List<Response.Page.Elements.Element> companyemployee(List children, int publickey, int companyID){
       //
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
               CompanyJpaController companyJpaController = new CompanyJpaController();
               CompanyPK companyPK = new CompanyPK();
               companyPK.setPubkey(publickey);
               companyPK.setCompanyid(companyID);
          try{
               company = companyJpaController.findCompany(companyPK);
               
             if(company.getCompanyPK().getCompanyid() > 0){
                String departmentid = getElementStringValueFromList("departmentid", children);
                String firstname = getElementStringValueFromList("firstname", children);
                String lastname = getElementStringValueFromList("lastname", children);
                String othername = getElementStringValueFromList("othername", children);
                String phone = getElementStringValueFromList("phone", children);
                String fax = getElementStringValueFromList("fax", children);
                String email = getElementStringValueFromList("email", children);
                String designation = getElementStringValueFromList("designation", children);
                String islock = getElementStringValueFromList("islock", children);
                String web = getElementStringValueFromList("web", children);
                String description = getElementStringValueFromList("description", children);
                String employeeid = getElementStringValueFromList("employeeid", children);
                 
                int employeeID = 0;
                if(employeeid != null && !employeeid.isEmpty() && !employeeid.equalsIgnoreCase("0"))
                {
                    employeeID = Integer.parseInt(employeeid);
                }
                //find a specific employee
                if(employeeID > 0){
                    CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                    Companyemployee companyemployee = new Companyemployee();
                    CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                    companyemployeePK.setPubkey(publickey);
                    companyemployeePK.setId(employeeID);
                    companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companyemployee");
                    resElement.setEmployeeid(companyemployee.getCompanyemployeePK().getId());
                    resElement.setFirstname(companyemployee.getFirstName());
                    resElement.setLastname(companyemployee.getLastName());
                    resElement.setOthername(companyemployee.getOtherName());
                    resElement.setPhone(companyemployee.getPhone());
                    resElement.setDesignation(companyemployee.getDesignation());
                    resElement.setDescription(companyemployee.getDescription());
                            
                    resElement.setElementstatus("OK");
                    resElement.setElementstatusmessage("Success");
                    ////
                    responseElementList.add(resElement);
                    
                }
                else if(departmentid.equalsIgnoreCase("0") && firstname.isEmpty()
                        && lastname.isEmpty() && othername.isEmpty() && phone.isEmpty()
                        && fax.isEmpty() && email.isEmpty() && designation.equalsIgnoreCase("0")
                        && web.isEmpty() && description.isEmpty())
                {
                    if(company.getCompanyemployeeCollection().size() >= 0)
                    {
                        Collection<Companyemployee> CompanyemployeeColl = company.getCompanyemployeeCollection();
                        for(Companyemployee employee : CompanyemployeeColl)
                        {
                            //////////////////
                            resElement = responseOF.createResponsePageElementsElement();
                            resElement.setId("companyemployee");
                            resElement.setEmployeeid(employee.getCompanyemployeePK().getId());
                            resElement.setFirstname(employee.getFirstName());
                            resElement.setLastname(employee.getLastName());
                            resElement.setOthername(employee.getOtherName());
                            resElement.setPhone(employee.getPhone());
                            resElement.setDesignation(employee.getDesignation());
                            resElement.setDescription(employee.getDescription());
                            
                            resElement.setElementstatus("OK");
                            resElement.setElementstatusmessage("Success");
                            ////
                            responseElementList.add(resElement);
                        }
                    }
                }
                
                
                
                else if(!departmentid.equalsIgnoreCase("0") && !firstname.equalsIgnoreCase("0")
                        && !lastname.isEmpty() && !othername.isEmpty() && !phone.isEmpty()
                        && !fax.isEmpty() && !email.isEmpty() && !designation.equalsIgnoreCase("0")
                        && !web.isEmpty() && !description.isEmpty()){
                CompanydepartmentJpaController companydepartmentJpaController = new CompanydepartmentJpaController();
                Companydepartment companydepartment = new Companydepartment();
                CompanydepartmentPK companydepartmentPK = new CompanydepartmentPK();
                
                companydepartmentPK.setPubkey(publickey);
                companydepartmentPK.setId(Integer.parseInt(departmentid));
                companydepartment = companydepartmentJpaController.findCompanydepartment(companydepartmentPK);
                
             if(companydepartment.getCompanydepartmentPK().getId() > 0){
                CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                Companyemployee companyemployee = new Companyemployee();
                companyemployee.setCompany(company);
                companydepartment = new Companydepartment();
                companydepartment.setCompanydepartmentPK(companydepartmentPK);
                companyemployee.setCompanydepartment(companydepartment);
                companyemployee.setFirstName(firstname);
                companyemployee.setLastName(lastname);
                companyemployee.setOtherName(othername);
                companyemployee.setPhone(phone);
                companyemployee.setFax(fax);
                companyemployee.setEmail(email);
                companyemployee.setDesignation(Integer.parseInt(designation));
                companyemployee.setIslock(Boolean.parseBoolean(islock));
                companyemployee.setWeb(web);
                companyemployee.setDescription(description);
                companyemployee.setCreateddate(new Date());
                companyemployee.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyemployeePrep.class");
                CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                //
                companyemployeePK.setPubkey(publickey);
                companyemployeePK.setId(Integer.parseInt(p.substring(7)));
                companyemployee.setCompanyemployeePK(companyemployeePK);
                //
                companyemployeeJpaController.create(companyemployee);
                
               //////////////////
                 resElement = responseOF.createResponsePageElementsElement();
                 resElement.setId("companyemployee");
                 
                 resElement.setEmployeeid(companyemployee.getCompanyemployeePK().getId());
                 resElement.setElementstatus("OK");
                 resElement.setElementstatusmessage("Success");
                ////
                responseElementList.add(resElement);
                
             }
             }else{
                 this.setStatus("FAIL");
                 this.setStatusmessage("Invalid department id :"+departmentid);
             }
            
                
             }
          }catch(Exception ex){
                this.setStatus("ERROR");
                this.setStatusmessage(ex.getMessage());
          }
         return responseElementList; 
   }
}
