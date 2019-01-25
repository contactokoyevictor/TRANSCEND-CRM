/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstack;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackcd;
import com.sivotek.crm.persistent.dao.entities.CompanyaccountstackcdPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyaccountstackcdJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanyaccountstackcdPrep {
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
    
    public List<Response.Page.Elements.Element> companyaccountstackcd(List children, int publickey, int companyID)
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
                String accountstackid = getElementStringValueFromList("accountstackid", children);
                String creditnumber = getElementStringValueFromList("creditnumber", children);
                String creditname = getElementStringValueFromList("creditname", children);
                String creditvalue = getElementStringValueFromList("creditvalue", children);
                String credittaxtname = getElementStringValueFromList("credittaxtname", children);
                String credittype = getElementStringValueFromList("credittype", children);
                 
                Companyemployee companyemployee = new Companyemployee();
                Companyaccountstackcd companyaccountstackcd = new Companyaccountstackcd();
                CompanyaccountstackcdPK companyaccountstackcdPK = new CompanyaccountstackcdPK();
                companyaccountstackcdPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                companyaccountstackcdPK.setAccountstackcdid(Integer.parseInt(p.substring(7)));
                companyaccountstackcd.setCompanyaccountstackcdPK(companyaccountstackcdPK);
                
                //check for employee from company employee collection
                if(company.getCompanyemployeeCollection().size() > 0)
                {
                    Collection<Companyemployee> companyemployeeColl = company.getCompanyemployeeCollection();
                    for(Companyemployee employee : companyemployeeColl)
                    {
                        if(employee.getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
                        {
                            companyemployee = employee;
                            companyaccountstackcd.setCompanyemployee(companyemployee);
                            break;
                        }
                    }
                    
                   if(companyemployee.getCompanyemployeePK().getId() > 0)
                    {
                       
                        if(accountstackid != null && !accountstackid.isEmpty() && Integer.parseInt(accountstackid) > 0)
                        {
                            
                            Companyaccountstack companyaccountstack = new Companyaccountstack();
                            
                            //find account stack id
                            if(company.getCompanyaccountstackCollection().size() > 0)
                            {
                                Collection<Companyaccountstack> accountstackColl = company.getCompanyaccountstackCollection();
                                for(Companyaccountstack accountstack : accountstackColl)
                                {
                                    if(accountstack.getCompanyaccountstackPK().getAcstackid() == Integer.parseInt(accountstackid))
                                    {
                                        companyaccountstack = accountstack;
                                        break;
                                    }
                                }
                            }
                            if(companyaccountstack.getCompanyaccountstackPK().getAcstackid() > 0)
                            {
                                
                                companyaccountstackcd.setCompany(company);
                                companyaccountstackcd.setCompanyemployee(companyemployee);
                                companyaccountstackcd.setCompanyaccountstack(companyaccountstack);
                                companyaccountstackcd.setCreditdtnumber(creditnumber);
                                companyaccountstackcd.setCreditdtname(creditname);
                                companyaccountstackcd.setCreditdtvalue(Double.parseDouble(creditvalue));
                                companyaccountstackcd.setCreditdttaxtname(credittaxtname);
                                companyaccountstackcd.setCreditdttype(Integer.parseInt(credittype));
                                companyaccountstackcd.setCreateddate(new Date());
                                companyaccountstackcd.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyaccountstackcdPrep.class");
                                
                                CompanyaccountstackcdJpaController companyaccountstackcdJpaController = new CompanyaccountstackcdJpaController();
                                companyaccountstackcdJpaController.create(companyaccountstackcd);
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("companyaccountstackcd");
                                resElement.setAccountstackid(companyaccountstackcd.getCompanyaccountstackcdPK().getAccountstackcdid());
                                resElement.setElementstatus("OK");
                                resElement.setElementstatusmessage("Success");
                                ////
                                responseElementList.add(resElement);
                            }
                        }
                        else if(accountstackid != null && !accountstackid.isEmpty() && Integer.parseInt(accountstackid) == 0)
                        {
                            if(company.getCompanyaccountstackcdCollection().size() > 0)
                            {
                                Collection<Companyaccountstackcd> accountstackcdColl = company.getCompanyaccountstackcdCollection(); 
                                for(Companyaccountstackcd accountstackcd : accountstackcdColl)
                                {
                                  //////////////////
                                    resElement = responseOF.createResponsePageElementsElement();
                                    resElement.setId("companyaccountstackcd");
                                    resElement.setAccountstackid(accountstackcd.getCompanyaccountstack().getCompanyaccountstackPK().getAcstackid());
                                    resElement.setCreditnumber(accountstackcd.getCreditdtnumber());
                                    resElement.setCreditname(accountstackcd.getCreditdtname());
                                    resElement.setCreditvalue(accountstackcd.getCreditdtvalue());
                                    resElement.setCredittaxtname(accountstackcd.getCreditdttaxtname());
                                    resElement.setCredittype(accountstackcd.getCreditdttype());
                                    
                                    ////
                                    responseElementList.add(resElement);
                                }
                            }
                        }
                    }
                    else{
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("companyaccountstackcd");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("Invalid Employee ID");
                        ////
                        responseElementList.add(resElement);
                     }  
                }
                
            }
            else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companyaccountstackcd");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
        }catch(Exception ex)
        {
            
            //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("companyaccountstackcd");
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
        }
        return responseElementList;
    }
        

}
