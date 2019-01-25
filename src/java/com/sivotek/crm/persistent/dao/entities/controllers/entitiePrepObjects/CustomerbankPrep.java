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
import com.sivotek.crm.persistent.dao.entities.Customerbank;
import com.sivotek.crm.persistent.dao.entities.CustomerbankPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CustomerbankJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CustomerbankPrep {
   
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
    
   public List<Response.Page.Elements.Element> customerbank(List children, int publickey, int companyID)
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
                String bankname = getElementStringValueFromList("bankname", children);
                String bankaccount = getElementStringValueFromList("bankaccount", children);
                String bankIBAN = getElementStringValueFromList("bankIBAN", children);
                String bankcountry = getElementStringValueFromList("bankcountry", children);
                String isDefault = getElementStringValueFromList("isDefault", children);
                
                Companyemployee companyemployee = new Companyemployee();
                Companycustomer companycustomer = new Companycustomer();
                
                Customerbank customerbank = new Customerbank();
                CustomerbankPK customerbankPK = new CustomerbankPK();
                customerbankPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                customerbankPK.setBankid(Integer.parseInt(p.substring(7)));
                customerbank.setCustomerbankPK(customerbankPK);
                
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
                    if(customerid != null && !customerid.isEmpty() && Integer.parseInt(customerid) > 0)
                    {
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
                        customerbank.setCompanycustomer(companycustomer);
                        customerbank.setCompanyemployee(companyemployee);
                        customerbank.setBankaccount(bankaccount);
                        customerbank.setBankcountry(bankcountry);
                        customerbank.setBankiban(bankIBAN);
                        customerbank.setBankname(bankname);
                        
                        if(Boolean.parseBoolean(isDefault) == true)
                        {
                            if(companycustomer.getCustomerbankCollection().size() > 0)
                            {
                                Collection<Customerbank> coll = companycustomer.getCustomerbankCollection();
                                for(Customerbank bank : coll)
                                {
                                    if(bank.getIsDefault() == true)
                                    {
                                        bank.setIsDefault(Boolean.FALSE);
                                        bank.setChangeddate(new Date());
                                        bank.setChangedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CustomerbankPrep.class");
                                        CustomerbankJpaController customerbankJpaController = new CustomerbankJpaController();
                                        customerbankJpaController.edit(bank);
                                    }
                                }
                            }
                        }
                        
                        customerbank.setIsDefault(Boolean.parseBoolean(isDefault));
                        customerbank.setCreateddate(new Date());
                        customerbank.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CustomerbankPrep.class");
                        CustomerbankJpaController customerbankJpaController = new CustomerbankJpaController();
                        customerbankJpaController.create(customerbank);
                        //////////////////
                       resElement = responseOF.createResponsePageElementsElement();
                       resElement.setId("customerbank");
                       resElement.setBankid(customerbank.getCustomerbankPK().getBankid());
                       ////
                       responseElementList.add(resElement);
                    }
                    
                    }
                    
                    else if(customerid != null && customerid.equalsIgnoreCase("0"))
                    {
                       System.out.println("We are here 1");
                            Collection<Companycustomer> companycustomerColl = company.getCompanycustomerCollection();
                            for(Companycustomer customer : companycustomerColl)
                            {
                                System.out.println("We are here 2");
                                
                                if(customer.getCustomerbankCollection().size() >= 0)
                                {
                                    System.out.println("We are here 3");
                                    
                                    Collection<Customerbank> bankColl = customer.getCustomerbankCollection();
                                    for(Customerbank bank : bankColl)
                                    {
                                        //////////////////
                                       resElement = responseOF.createResponsePageElementsElement();
                                       resElement.setId("customerbank");
                                       resElement.setBankid(bank.getCustomerbankPK().getBankid());
                                       resElement.setCustomerid(bank.getCompanycustomer().getCompanycustomerPK().getId());
                                       resElement.setBankname(bank.getBankname());
                                       resElement.setBankaccount(bank.getBankaccount());
                                       resElement.setBankIBAN(bank.getBankiban());
                                       resElement.setBankcountry(bank.getBankcountry());
                                       resElement.setIsDefault(bank.getIsDefault());
                                       ////
                                       responseElementList.add(resElement); 
                                    }
                                }
                            }
                        
                    }
                }
               else
                 {
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("customerbank");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("Invalid Company details");
                        ////
                        responseElementList.add(resElement);
                 }
            }
            else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("customerbank");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
        }catch(Exception ex)
        {
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("customerbank");
                    resElement.setElementstatus("ERROR");
                    System.out.println(ex.getMessage());
                    resElement.setElementstatusmessage(ex.getMessage());
                    ////
                    responseElementList.add(resElement);
        }
       return responseElementList;
   }
   

}
