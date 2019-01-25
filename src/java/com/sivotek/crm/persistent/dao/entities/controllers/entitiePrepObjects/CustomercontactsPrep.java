/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Customercontacts;
import com.sivotek.crm.persistent.dao.entities.CustomercontactsPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CustomercontactsJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CustomercontactsPrep {
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
    
    public List<Response.Page.Elements.Element> customercontacts(List children, int publickey, int companyID)
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
            if(company.getCompanyPK().getCompanyid() > 0)
            {
                String employeeid = getElementStringValueFromList("employeeid", children);
                String customerid = getElementStringValueFromList("customerid", children);
                String customer_firstname = getElementStringValueFromList("customer_firstname", children);
                String customer_lastname = getElementStringValueFromList("customer_lastname", children);
                String customer_othername = getElementStringValueFromList("customer_othername", children);
                String customer_phone = getElementStringValueFromList("customer_phone", children);
                String customer_fax = getElementStringValueFromList("customer_fax", children);
                String customer_mobile = getElementStringValueFromList("customer_mobile", children);
                String customer_motto = getElementStringValueFromList("customer_motto", children);
                String customer_email = getElementStringValueFromList("customer_email", children);
                
                Companyemployee companyemployee = new Companyemployee();
                Customercontacts customercontacts = new Customercontacts();
                CustomercontactsPK customercontactsPK = new CustomercontactsPK();
                customercontactsPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                customercontactsPK.setContactid(Integer.parseInt(p.substring(7)));
                customercontacts.setCustomercontactsPK(customercontactsPK);
                
                Companycustomer companycustomer = new Companycustomer();
                
                //find company employee from company employee collection
                if(company.getCompanyemployeeCollection().size() > 0)
                {
                    Collection<Companyemployee> employeeColl = company.getCompanyemployeeCollection();
                    for(Companyemployee employee : employeeColl)
                    {
                        if(employee.getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
                        {
                            companyemployee = employee;
                            break;
                        }
                    }
                }//
                
               //if company employee was found...
               if(companyemployee.getCompanyemployeePK().getId() > 0)
                {
                    if(customer_firstname != null && !customer_firstname.isEmpty() && customerid != null && Integer.parseInt(customerid) > 0)
                    {
                        //find customer details from company customer collection
                        if(company.getCompanycustomerCollection().size() > 0)
                        {
                          Collection<Companycustomer> companycustomerColl = company.getCompanycustomerCollection();
                          for(Companycustomer customer : companycustomerColl)
                          {
                              if(customer.getCompanycustomerPK().getId() == Integer.parseInt(customerid))
                              {
                                  companycustomer = customer;
                                  break;
                              } 
                          }
                        }
                        
                        if(companycustomer.getCompanycustomerPK().getId() > 0)
                        {
                            customercontacts.setCompanycustomer(companycustomer);
                            customercontacts.setCompanyemployee(companyemployee);
                            customercontacts.setFisrtName(customer_firstname);
                            customercontacts.setLastName(customer_lastname);
                            customercontacts.setOtherName(customer_othername);
                            customercontacts.setEmail(customer_email);
                            customercontacts.setFax(customer_fax);
                            customercontacts.setMobile(customer_mobile);
                            customercontacts.setPhone(customer_phone);
                            customercontacts.setCreateddate(new Date());
                            customercontacts.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CustomercontactsPrep.class");
                            CustomercontactsJpaController customercontactsJpaController = new CustomercontactsJpaController();
                            customercontactsJpaController.create(customercontacts);
                            
                           //////////////////
                           resElement = responseOF.createResponsePageElementsElement();
                           resElement.setId("customercontacts");
                           resElement.setContactid(customercontacts.getCustomercontactsPK().getContactid());
                           ////
                           responseElementList.add(resElement);
                        }
                    }
                    else if(customer_firstname != null && customer_firstname.isEmpty() && customerid != null && Integer.parseInt(customerid) == 0)
                    {
                        if(company.getCompanycustomerCollection().size() > 0)
                        {
                            Collection<Companycustomer> companycustomerColl = company.getCompanycustomerCollection();
                            for(Companycustomer customers : companycustomerColl)
                            {
                                if(customers.getCustomercontactsCollection().size() > 0)
                                {
                                    Collection<Customercontacts> customercontactsColl = customers.getCustomercontactsCollection();
                                    for(Customercontacts contact : customercontactsColl)
                                    {
                                         //////////////////
                                         resElement = responseOF.createResponsePageElementsElement();
                                         resElement.setId("customercontacts");
                                         resElement.setContactid(contact.getCustomercontactsPK().getContactid());
                                         resElement.setCustomerid(contact.getCompanycustomer().getCompanycustomerPK().getId());
                                         resElement.setCustomerFirstname(contact.getFisrtName());
                                         resElement.setCustomerLastname(contact.getLastName());
                                         resElement.setCustomerOthername(contact.getOtherName());
                                         resElement.setCustomerPhone(contact.getPhone());
                                         resElement.setCustomerFax(contact.getFax());
                                         resElement.setCustomerMobile(contact.getMobile());
                                         resElement.setCustomerEmail(contact.getEmail());
                                         ////
                                         responseElementList.add(resElement);
                                    }
                                }
                                else
                                 {
                                        //////////////////
                                        resElement = responseOF.createResponsePageElementsElement();
                                        resElement.setId("customercontacts");
                                        resElement.setElementstatus("FAIL");
                                        resElement.setElementstatusmessage("no contacts records");
                                        ////
                                        responseElementList.add(resElement);
                                 }
                                
                            }
                        }
                        else
                         {
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("customercontacts");
                                resElement.setElementstatus("FAIL");
                                resElement.setElementstatusmessage("no customer records");
                                ////
                                responseElementList.add(resElement);
                         }
                    }
                }
               else
                 {
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("customercontacts");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("Invalid Company details");
                        ////
                        responseElementList.add(resElement);
                 }
            }
             else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("customercontacts");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
        }catch(Exception ex)
        {
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("customercontacts");
                    resElement.setElementstatus("ERROR");
                    resElement.setElementstatusmessage(ex.getMessage());
                    ////
                    responseElementList.add(resElement);
        }
        
        return responseElementList;
    }
  
    
}
