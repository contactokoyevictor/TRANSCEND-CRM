/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyaddresstype;
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Customercontacts;
import com.sivotek.crm.persistent.dao.entities.Customercontactsaddress;
import com.sivotek.crm.persistent.dao.entities.CustomercontactsaddressPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CustomercontactsaddressJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CustomercontactsaddressPrep {
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
    
   public List<Response.Page.Elements.Element> customercontactsaddress(List children, int publickey, int companyID)
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
                String customer_contactid = getElementStringValueFromList("customer_contactid", children);
                String customer_addresstypeid = getElementStringValueFromList("customer_addresstypeid", children);
                String customer_street = getElementStringValueFromList("customer_street", children);
                String customer_zip = getElementStringValueFromList("customer_zip", children);
                String customer_location = getElementStringValueFromList("customer_location", children);
                String customer_pbox = getElementStringValueFromList("customer_pbox", children);
                String customer_country = getElementStringValueFromList("customer_country", children);
                
                Companyemployee companyemployee = new Companyemployee();
                Customercontacts customercontacts = new Customercontacts();
                
                Companyaddresstype companyaddresstype = new Companyaddresstype();
                Customercontactsaddress customercontactsaddress = new Customercontactsaddress();
                CustomercontactsaddressPK customercontactsaddressPK = new CustomercontactsaddressPK();
                customercontactsaddressPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                customercontactsaddressPK.setId(Integer.parseInt(p.substring(7)));
                customercontactsaddress.setCustomercontactsaddressPK(customercontactsaddressPK);
                
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
                    if(customer_contactid != null && !customer_contactid.isEmpty() && Integer.parseInt(customer_contactid) > 0 && customer_addresstypeid != null && !customer_addresstypeid.isEmpty() && Integer.parseInt(customer_addresstypeid) > 0)
                    {
                        if(company.getCompanyaddresstypeCollection().size() > 0)
                        {
                            Collection<Companyaddresstype> companyaddresstypeColl = company.getCompanyaddresstypeCollection();
                            for(Companyaddresstype addresstype : companyaddresstypeColl)
                            {
                                if(addresstype.getCompanyaddresstypePK().getId() == Integer.parseInt(customer_addresstypeid))
                                 {
                                    companyaddresstype = addresstype;
                                    break;
                                 }
                            }
                        }//
                        
                        if(company.getCompanycustomerCollection().size() > 0)
                        {
                           Collection<Companycustomer>  companycustomerColl = company.getCompanycustomerCollection();
                           for(Companycustomer customer : companycustomerColl)
                           {
                               if(customer.getCustomercontactsCollection().size() > 0)
                               {
                                   Collection<Customercontacts> customercontactsColl = customer.getCustomercontactsCollection();
                                   for(Customercontacts contacts : customercontactsColl)
                                   {
                                       if(contacts.getCustomercontactsPK().getContactid() == Integer.parseInt(customer_contactid))
                                       {
                                           customercontacts = contacts;
                                           break;
                                       }
                                   }//
                                   
                                   if(customercontacts.getCustomercontactsPK().getContactid() > 0)
                                   {
                                       break;
                                   }
                               }//
                            }//
                           
                        }//
                        
                       if(customercontacts.getCustomercontactsPK().getContactid() > 0 && companyaddresstype.getCompanyaddresstypePK().getId() > 0)
                       {
                           customercontactsaddress.setCompanyemployee(companyemployee);
                           customercontactsaddress.setCompanyaddresstype(companyaddresstype);
                           customercontactsaddress.setCustomercontacts(customercontacts);
                           customercontactsaddress.setStreet(customer_street);
                           customercontactsaddress.setZip(customer_zip);
                           customercontactsaddress.setLocation(customer_location);
                           customercontactsaddress.setPbox(customer_pbox);
                           customercontactsaddress.setLocation(customer_country);
                           customercontactsaddress.setCreateddate(new Date());
                           customercontactsaddress.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CustomercontactsaddressPrep.class");
                           CustomercontactsaddressJpaController customercontactsaddressJpaController = new CustomercontactsaddressJpaController();
                           customercontactsaddressJpaController.create(customercontactsaddress);
                           
                           //////////////////
                           resElement = responseOF.createResponsePageElementsElement();
                           resElement.setId("customercontactsaddress");
                           resElement.setContactsaddressid(customercontactsaddress.getCustomercontactsaddressPK().getId());
                           ////
                           responseElementList.add(resElement);
                        }
                    }
                    else if(customer_contactid != null && !customer_contactid.isEmpty() && Integer.parseInt(customer_contactid) == 0 && customer_addresstypeid != null && !customer_addresstypeid.isEmpty() && Integer.parseInt(customer_addresstypeid) == 0)
                    {
                        if(company.getCompanycustomerCollection().size() > 0)
                        {
                            Collection<Companycustomer> CompanycustomerColl = company.getCompanycustomerCollection();
                            for(Companycustomer companycustomer : CompanycustomerColl)
                            {
                                if(companycustomer.getCustomercontactsCollection().size() > 0)
                                {
                                    Collection<Customercontacts> CustomercontactsColl = companycustomer.getCustomercontactsCollection();
                                    for(Customercontacts Customercontacts : CustomercontactsColl)
                                    {
                                        if(Customercontacts.getCustomercontactsaddressCollection().size() > 0)
                                        {
                                            Collection<Customercontactsaddress> CustomercontactsaddressColl = Customercontacts.getCustomercontactsaddressCollection();
                                            for(Customercontactsaddress contactsaddress : CustomercontactsaddressColl)
                                            {
                                                //////////////////
                                               resElement = responseOF.createResponsePageElementsElement();
                                               resElement.setId("customercontactsaddress");
                                               resElement.setContactsaddressid(customercontactsaddress.getCustomercontactsaddressPK().getId());
                                               resElement.setCustomerContactid(contactsaddress.getCustomercontacts().getCustomercontactsPK().getContactid());
                                               resElement.setCustomerAddresstypeid(contactsaddress.getCompanyaddresstype().getCompanyaddresstypePK().getId());
                                               resElement.setCustomerStreet(contactsaddress.getStreet());
                                               resElement.setCustomerZip(contactsaddress.getZip());
                                               resElement.setCustomerLocation(contactsaddress.getLocation());
                                               resElement.setCustomerPbox(contactsaddress.getPbox());
                                               resElement.setCustomerCountry(contactsaddress.getCountry());
                                               ////
                                               responseElementList.add(resElement);
                                            }//
                                        }//
                                    }//
                                    
                                }
                            }
                        }
                    }
                    
                }
               else
                 {
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("customercontactsaddress");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("Invalid Company details");
                        ////
                        responseElementList.add(resElement);
                 }
                
            }
            else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("customercontactsaddress");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
                   
        }catch(Exception ex)
        {
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("customercontactsaddress");
                    resElement.setElementstatus("ERROR");
                    resElement.setElementstatusmessage(ex.getMessage());
                    ////
                    responseElementList.add(resElement);
        }
       return responseElementList;
   }
}
