/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstack;
import com.sivotek.crm.persistent.dao.entities.CompanyaccountstackPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyaccountstackJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanyaccountstackPrep {
   
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
    
   public List<Response.Page.Elements.Element> companyaccountstack(List children, int publickey, int companyID)
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
                String accountnr = getElementStringValueFromList("accountnr", children);
                String accounttype = getElementStringValueFromList("accounttype", children);
                String accounttaxtype = getElementStringValueFromList("accounttaxtype", children);
                String accountdebit = getElementStringValueFromList("accountdebit", children);
                String accountcredit = getElementStringValueFromList("accountcredit", children);
                String account_d_name = getElementStringValueFromList("account_d_name", children);
                String account_c_name = getElementStringValueFromList("account_c_name", children);
                String account_d_value = getElementStringValueFromList("account_d_value", children);
                String account_c_value = getElementStringValueFromList("account_c_value", children);
                String accountname = getElementStringValueFromList("accountname", children);
                String accountvalue = getElementStringValueFromList("accountvalue", children);
                String description = getElementStringValueFromList("description", children);
                
                Companyemployee companyemployee = new Companyemployee();
                Companyaccountstack companyaccountstack = new Companyaccountstack();
                CompanyaccountstackPK companyaccountstackPK = new CompanyaccountstackPK();
                companyaccountstackPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                companyaccountstackPK.setAcstackid(Integer.parseInt(p.substring(7)));
                companyaccountstack.setCompanyaccountstackPK(companyaccountstackPK);
                
                
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
                   if(accountnr != null && !accountnr.isEmpty())
                   {
                       companyaccountstack.setCompany(company);
                       companyaccountstack.setCompanyemployee(companyemployee);
                       companyaccountstack.setAccountnr(accountnr);
                       companyaccountstack.setAccountType(Integer.parseInt(accounttype));
                       companyaccountstack.setAccountTaxType(accounttaxtype);
                       companyaccountstack.setAccountDebit(accountdebit);
                       companyaccountstack.setAccountCredit(accountcredit);
                       companyaccountstack.setAccountDName(account_d_name);
                       companyaccountstack.setAccountCName(account_c_name);
                       companyaccountstack.setAccountDValue(Double.parseDouble(account_d_value));
                       companyaccountstack.setAccountCValue(Double.parseDouble(account_c_value));
                       companyaccountstack.setAccountname(accountname);
                       companyaccountstack.setAccountvalue(Double.parseDouble(accountvalue));
                       companyaccountstack.setDescription(description);
                       companyaccountstack.setCreateddate(new Date());
                       companyaccountstack.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyaccountstackPrep.class");
                       CompanyaccountstackJpaController companyaccountstackJpaController = new CompanyaccountstackJpaController();
                       companyaccountstackJpaController.create(companyaccountstack);
                       
                       //////////////////
                       resElement = responseOF.createResponsePageElementsElement();
                       resElement.setId("companyaccountstack");
                       resElement.setAccountstackid(companyaccountstack.getCompanyaccountstackPK().getAcstackid());
                       ////
                       responseElementList.add(resElement);
                   }
                   else if(accountnr != null && accountnr.equalsIgnoreCase(""))
                   {
                       if(company.getCompanyaccountstackCollection().size() > 0)
                       {
                           Collection<Companyaccountstack> accountstackColl = company.getCompanyaccountstackCollection();
                           for(Companyaccountstack accountstack : accountstackColl)
                           {
                               //////////////////
                               resElement = responseOF.createResponsePageElementsElement();
                               resElement.setId("companyaccountstack");
                               resElement.setAccountstackid(accountstack.getCompanyaccountstackPK().getAcstackid());
                               resElement.setAccountnr(accountstack.getAccountnr());
                               resElement.setAccounttype(accountstack.getAccountType());
                               resElement.setAccounttaxtype(accountstack.getAccountTaxType());
                               resElement.setAccountdebit(accountstack.getAccountDebit());
                               resElement.setAccountcredit(accountstack.getAccountCredit());
                               resElement.setAccountDName(accountstack.getAccountDName());
                               resElement.setAccountCName(accountstack.getAccountCName());
                               resElement.setAccountCValue(accountstack.getAccountCValue());
                               resElement.setAccountDValue(accountstack.getAccountDValue());
                               resElement.setAccountname(accountstack.getAccountname());
                               resElement.setAccountvalue(accountstack.getAccountvalue());
                               resElement.setDescription(accountstack.getDescription());
                               ////
                               responseElementList.add(resElement);
                           }
                           
                       }
                   }
                } 
               
                
            }
        }catch(Exception ex)
        {
            //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("companyaccountstack");
            resElement.setElementstatus("ERROR");
            System.out.println(ex.getMessage());
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
        }
       return responseElementList;
   }

}
