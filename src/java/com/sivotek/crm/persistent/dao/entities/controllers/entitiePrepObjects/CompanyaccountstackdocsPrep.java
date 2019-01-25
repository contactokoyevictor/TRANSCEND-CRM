/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackcd;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackdocs;
import com.sivotek.crm.persistent.dao.entities.CompanyaccountstackdocsPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Contenttypes;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyaccountstackdocsJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.ContenttypesJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanyaccountstackdocsPrep {
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
     
     private byte[] getElementByteValueFromList(String elementName, List elementList) {
       for (Object elementList1 : elementList) {
           JAXBElement e = (JAXBElement) elementList1;
           if (e.getName().getLocalPart().equals(elementName)) {
               return (byte[])e.getValue();
           }
       }
        return null;
    }
   
   public List<Response.Page.Elements.Element> companyaccountstackdocs(List children, int publickey, int companyID)
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
                String accountid = getElementStringValueFromList("accountid", children);
                String contenttypeid = getElementStringValueFromList("contenttypeid", children);
                String name = getElementStringValueFromList("name", children);
                byte[] files = getElementByteValueFromList("files", children);
                
                Companyemployee companyemployee = new Companyemployee();
                Companyaccountstackcd companyaccountstackcd = new Companyaccountstackcd();
                
                //check for employee from company employee collection
                if(company.getCompanyemployeeCollection().size() > 0)
                {
                    Collection<Companyemployee> companyemployeeColl = company.getCompanyemployeeCollection();
                    for(Companyemployee employee : companyemployeeColl)
                    {
                        if(employee.getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
                        {
                            companyemployee = employee;
                            break;
                        }
                    }
                    
                    
                  if(accountid != null && !accountid.isEmpty() && Integer.parseInt(accountid) > 0
                            && contenttypeid != null && !contenttypeid.isEmpty() && Integer.parseInt(contenttypeid) > 0)
                  {
                    //finding account stackcd
                    if(company.getCompanyaccountstackcdCollection().size() > 0)
                    {
                        Collection<Companyaccountstackcd> accountstackcdColl = company.getCompanyaccountstackcdCollection(); 
                        for(Companyaccountstackcd accountstackcd : accountstackcdColl)
                        {
                            if(accountstackcd.getCompanyaccountstackcdPK().getAccountstackcdid() == Integer.parseInt(accountid))
                            {
                                companyaccountstackcd = accountstackcd;
                                break;
                            }
                        }
                    }
                    
                 //if employee and accountstackcd found
                 if(companyemployee.getCompanyemployeePK().getId() > 0 && companyaccountstackcd.getCompanyaccountstackcdPK().getAccountstackcdid() > 0)
                   {
                       ContenttypesJpaController contenttypesJpaController = new ContenttypesJpaController();
                       Contenttypes contenttypes = new Contenttypes();
                       contenttypes = contenttypesJpaController.findContenttypes(Integer.parseInt(contenttypeid));
                        
                       if(companyaccountstackcd.getCompanyaccountstackcdPK().getAccountstackcdid() > 0 && contenttypes.getId() > 0)
                       {
                        long bint = System.currentTimeMillis();
                        String p = ""+bint;
                        Companyaccountstackdocs companyaccountstackdocs = new Companyaccountstackdocs();
                        CompanyaccountstackdocsPK companyaccountstackdocsPK = new CompanyaccountstackdocsPK();
                        companyaccountstackdocsPK.setPubkey(publickey);
                        companyaccountstackdocsPK.setAccountdocid(Integer.parseInt(p.substring(7)));
                        companyaccountstackdocs.setCompanyaccountstackdocsPK(companyaccountstackdocsPK);
                        companyaccountstackdocs.setCompany(company);
                        companyaccountstackdocs.setCompanyemployee(companyemployee);
                        companyaccountstackdocs.setCompanyaccountstackcd(companyaccountstackcd);
                
                        byte[] buf = null;
                        //byte[] buf = new byte[1048576];
                        buf = new byte[contenttypes.getMaxsize()];
                        buf = files;
                        
                        companyaccountstackdocs.setName(name);
                        companyaccountstackdocs.setFiles(files);
                        companyaccountstackdocs.setCreateddate(new Date());
                        companyaccountstackdocs.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyaccountstackdocsPrep.class");
                        CompanyaccountstackdocsJpaController CompanyaccountstackdocsJpaController = new CompanyaccountstackdocsJpaController();
                        CompanyaccountstackdocsJpaController.create(companyaccountstackdocs);
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("companyaccountstackdocs");
                        resElement.setAccountstackid(companyaccountstackdocs.getCompanyaccountstackdocsPK().getAccountdocid());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                        responseElementList.add(resElement);
                        
                       
                   } 
                    else{ 
                                    //////////////////
                                    resElement = responseOF.createResponsePageElementsElement();
                                    resElement.setId("companyaccountstackdocs");
                                    resElement.setElementstatus("FAIL");
                                    resElement.setElementstatusmessage("No accountstackcd record found");
                                    ////
                                    responseElementList.add(resElement);
                         }
                   }
                    else{ 
                                    //////////////////
                                    resElement = responseOF.createResponsePageElementsElement();
                                    resElement.setId("companyaccountstackdocs");
                                    resElement.setElementstatus("FAIL");
                                    resElement.setElementstatusmessage("No employee record found");
                                    ////
                                    responseElementList.add(resElement);
                         }
                }
                  
                  else if(accountid != null && !accountid.isEmpty() && Integer.parseInt(accountid) == 0
                            && contenttypeid != null && !contenttypeid.isEmpty() && Integer.parseInt(contenttypeid) == 0)
                  {
                       if(company.getCompanyaccountstackdocsCollection().size() > 0)
                       {
                           Collection<Companyaccountstackdocs> accountstackdocsColl = company.getCompanyaccountstackdocsCollection();
                           for(Companyaccountstackdocs accountstackdocs : accountstackdocsColl)
                           {
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("companyaccountstackdocs");
                                resElement.setAccountstackid(accountstackdocs.getCompanyaccountstackdocsPK().getAccountdocid());
                                resElement.setAccountid(accountstackdocs.getCompanyaccountstackcd().getCompanyaccountstackcdPK().getAccountstackcdid());
                                resElement.setFiles(accountstackdocs.getFiles());
                                resElement.setName(accountstackdocs.getName());
                                ////
                                responseElementList.add(resElement);
                           }
                       }
                       else{
                                    //////////////////
                                    resElement = responseOF.createResponsePageElementsElement();
                                    resElement.setId("companyaccountstackdocs");
                                    resElement.setElementstatus("FAIL");
                                    resElement.setElementstatusmessage("No accountstackdocs record found");
                                    ////
                                    responseElementList.add(resElement);
                            }
                  }
                }
            }
            else{ 
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companyaccountstackdocs");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("No company record found");
                    ////
                    responseElementList.add(resElement);
                }
        }
        catch(Exception ex)
        {
            //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("companyaccountstackdocs");
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
        }
        
        return responseElementList;
   }
}
