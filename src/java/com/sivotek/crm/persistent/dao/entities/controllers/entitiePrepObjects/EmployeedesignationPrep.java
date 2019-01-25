/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companypayments;
import com.sivotek.crm.persistent.dao.entities.CompanypaymentsPK;
import com.sivotek.crm.persistent.dao.entities.Employeedesignation;
import com.sivotek.crm.persistent.dao.entities.EmployeedesignationPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanypaymentsJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.EmployeedesignationJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class EmployeedesignationPrep {
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
   public List<Response.Page.Elements.Element> employeedesignation(List children, int publickey, int companyID) 
   {            
              
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
          try{
               company = companyJpaController.findCompany(companyPK);
               
             if(company.getCompanyPK().getCompanyid() > 0){
                String name = getElementStringValueFromList("name", children);
                String paymentid = getElementStringValueFromList("paymentid", children);
                String description = getElementStringValueFromList("description", children);
                
                if(name.equalsIgnoreCase("") && paymentid.equalsIgnoreCase("0") && description.equalsIgnoreCase(""))
                {
                    if(company.getEmployeedesignationCollection().size() >= 0)
                    {
                        System.out.println("Found records :"+company.getEmployeedesignationCollection().size());
                        Collection<Employeedesignation> employeedesignationColl = company.getEmployeedesignationCollection();
                        for(Employeedesignation designation : employeedesignationColl)
                        {
                             //////////////////
                            resElement = responseOF.createResponsePageElementsElement();
                            resElement.setId("employeedesignation");
                            resElement.setEmployeeid(designation.getEmployeedesignationPK().getId());
                            resElement.setName(designation.getName());
                            resElement.setDesignationid(designation.getEmployeedesignationPK().getId());
                            resElement.setPaymentid(designation.getCompanypayments().getCompanypaymentsPK().getId());
                            resElement.setDescription(designation.getDescreption());
                            resElement.setElementstatus("OK");
                            resElement.setElementstatusmessage("Success");
                            ////
                            responseElementList.add(resElement);
                        }
                        
                    }
                }
                
                else if(!name.isEmpty() && !paymentid.equalsIgnoreCase("0") && !description.isEmpty())
                {
                Companypayments companypayments = new Companypayments();
                CompanypaymentsPK companypaymentsPK = new CompanypaymentsPK();
                companypaymentsPK.setPubkey(publickey);
                companypaymentsPK.setId(Integer.parseInt(paymentid));
                CompanypaymentsJpaController companypaymentsJpaController = new CompanypaymentsJpaController();
                
                companypayments = companypaymentsJpaController.findCompanypayments(companypaymentsPK);
                        
                EmployeedesignationJpaController employeedesignationJpaController = new EmployeedesignationJpaController();
                Employeedesignation employeedesignation = new Employeedesignation();
                EmployeedesignationPK employeedesignationPK = new EmployeedesignationPK();
               
                employeedesignationPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                employeedesignationPK.setId(Integer.parseInt(p.substring(7)));
                
                employeedesignation.setEmployeedesignationPK(employeedesignationPK);
                employeedesignation.setCompany(company);
                employeedesignation.setName(name);
                employeedesignation.setDescreption(description);
                employeedesignation.setCompanypayments(companypayments);
                employeedesignation.setCreateddate(new Date());
                employeedesignation.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.EmployeedesignationPrep.class");
                
                employeedesignationJpaController.create(employeedesignation);
                 //////////////////
                 resElement = responseOF.createResponsePageElementsElement();
                 resElement.setId("employeedesignation");
                 resElement.setEmployeeid(employeedesignation.getEmployeedesignationPK().getId());
                 resElement.setElementstatus("OK");
                 resElement.setElementstatusmessage("Success");
                 ////
                 responseElementList.add(resElement);
                       
                }
                
 
             }else{
                 this.setStatus("FAIL");
                 this.setStatusmessage("FAIL");
             }
          }catch(Exception ex)
           {
                this.setStatus("ERROR");
                this.setStatusmessage(ex.getMessage());
           }    
          
          return responseElementList;
   }
    
   

}
