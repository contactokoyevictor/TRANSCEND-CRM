/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.CompanycustomerPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Customercategory;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanycustomerJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanycustomerPrep {
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
   
  public List<Response.Page.Elements.Element> companycustomer(List children, int publickey, int companyID)
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
                String customer_name = getElementStringValueFromList("customer_name", children);
                String customer_subname = getElementStringValueFromList("customer_subname", children);
                String customer_alias = getElementStringValueFromList("customer_alias", children);
                String customer_email = getElementStringValueFromList("customer_email", children);
                String customer_web = getElementStringValueFromList("customer_web", children);
                //String customer_logo = getElementStringValueFromList("customer_logo", children);
                String customer_motto = getElementStringValueFromList("customer_motto", children);
                String customer_categoryid = getElementStringValueFromList("customer_categoryid", children);
                String description = getElementStringValueFromList("description", children);
                
                Companyemployee companyemployee = new Companyemployee();
                
                Companycustomer companycustomer = new Companycustomer();
                CompanycustomerPK companycustomerPK = new CompanycustomerPK();
                companycustomerPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                companycustomerPK.setId(Integer.parseInt(p.substring(7)));
                
                Customercategory customercategory = new Customercategory();
                
               
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
                    if(customer_name != null && !customer_name.isEmpty() && customer_categoryid != null && Integer.parseInt(customer_categoryid) > 0)
                    {
                        //find customer category from company customer category collection
                        if(company.getCustomercategoryCollection().size() > 0)
                        {
                           Collection<Customercategory> categoryColl = company.getCustomercategoryCollection();
                           for(Customercategory customercate : categoryColl)
                           {
                               if(customercate.getCustomercategoryPK().getId() == Integer.parseInt(customer_categoryid))
                               {
                                   customercategory = customercate;
                                   break;
                               }
                           }//
                           if(customercategory.getCustomercategoryPK().getId() > 0)
                           {
                               companycustomer.setCompany(company);
                               companycustomer.setCompanyemployee(companyemployee);
                               companycustomer.setCompanycustomerPK(companycustomerPK);
                               companycustomer.setCategory(customercategory.getCustomercategoryPK().getId());
                               //companycustomer.setLogo(logo);
                               
                               companycustomer.setIslock(Boolean.FALSE);
                               companycustomer.setName(customer_name);
                               companycustomer.setSubName(customer_subname);
                               companycustomer.setAlias(customer_alias);
                               companycustomer.setMotto(customer_motto);
                               companycustomer.setWeb(customer_web);
                               companycustomer.setEmail(customer_email);
                               companycustomer.setDescription(description);
                               companycustomer.setCreateddate(new Date());
                               companycustomer.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanycustomerPrep.class");
                               CompanycustomerJpaController companycustomerJpaController = new CompanycustomerJpaController();
                               companycustomerJpaController.create(companycustomer);
                               //////////////////
                               resElement = responseOF.createResponsePageElementsElement();
                               resElement.setId("companycustomer");
                               resElement.setCustomerid(companycustomer.getCompanycustomerPK().getId());
                               ////
                               responseElementList.add(resElement);
                               
                           }
                        }
                    }
                    else if(customer_name != null && customer_name.isEmpty())
                    {
                        if(company.getCompanycustomerCollection().size() > 0)
                        {
                            Collection<Companycustomer> companycustomerColl = company.getCompanycustomerCollection();
                            for(Companycustomer customer : companycustomerColl)
                            {
                                 //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("companycustomer");
                                resElement.setCustomerid(customer.getCompanycustomerPK().getId());
                                resElement.setCustomerName(customer.getName());
                                resElement.setCustomerSubname(customer.getSubName());
                                resElement.setCustomerAlias(customer.getAlias());
                                resElement.setCustomerMotto(customer.getMotto());
                                resElement.setCustomerWeb(customer.getWeb());
                                resElement.setCustomerEmail(customer.getEmail());
                                resElement.setDescription(customer.getDescription());
                                ////
                                responseElementList.add(resElement);
                            }
                        }
                        else
                         {
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("companycustomer");
                                resElement.setElementstatus("FAIL");
                                resElement.setElementstatusmessage("no customer records");
                                ////
                                responseElementList.add(resElement);
                         }
                    }
                }
               else{
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("companycustomer");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("Invalid Employee ID");
                        ////
                        responseElementList.add(resElement);
                     } 
            }
            else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companycustomer");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
        }
        catch(Exception ex)
        {
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companycustomer");
                    resElement.setElementstatus("ERROR");
                    resElement.setElementstatusmessage(ex.getMessage());
                    ////
                    responseElementList.add(resElement); 
        }
                
      return responseElementList;
  }
    

}
