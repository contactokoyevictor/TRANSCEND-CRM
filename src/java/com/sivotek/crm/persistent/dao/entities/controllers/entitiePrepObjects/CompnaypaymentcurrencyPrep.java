/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */
package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Compnaypaymentcurrency;
import com.sivotek.crm.persistent.dao.entities.CompnaypaymentcurrencyPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompnaypaymentcurrencyJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompnaypaymentcurrencyPrep {
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
     
   public List<Response.Page.Elements.Element> compnaypaymentcurrency(List children, int publickey, int companyID)
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
                String currencyname = getElementStringValueFromList("currencyname", children);
                String currencycode = getElementStringValueFromList("currencycode", children);
                String currencysymbol = getElementStringValueFromList("currencysymbol", children);
                String currencyid = getElementStringValueFromList("currencyid", children);
                
                CompnaypaymentcurrencyJpaController compnaypaymentcurrencyJpaController = new CompnaypaymentcurrencyJpaController();
                Compnaypaymentcurrency compnaypaymentcurrency = new Compnaypaymentcurrency();
                if(currencyname.isEmpty() && currencycode.isEmpty() && currencysymbol.isEmpty() && currencyid == null)
                {
                    if(company.getCompnaypaymentcurrencyCollection().size() >= 0)
                    {
                        Collection<Compnaypaymentcurrency> CompnaypaymentcurrencyColl = company.getCompnaypaymentcurrencyCollection();
                        for(Compnaypaymentcurrency currency : CompnaypaymentcurrencyColl)
                        {
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("compnaypaymentcurrency");
                        resElement.setCurrencyid(currency.getCompnaypaymentcurrencyPK().getId());
                        resElement.setCurrencyname(currency.getCurrencyName());
                        resElement.setCurrencycode(currency.getCurrencyCode());
                        resElement.setCurrencysymbol(currency.getCurrencySymbol());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                        responseElementList.add(resElement);
                        }
                    }
                    
                }
                else if(!currencyname.isEmpty() && !currencycode.isEmpty() && !currencysymbol.isEmpty() && currencyid == null)
                {
                CompnaypaymentcurrencyPK compnaypaymentcurrencyPK = new CompnaypaymentcurrencyPK();
                compnaypaymentcurrencyPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                
                
                
                compnaypaymentcurrencyPK.setId(Integer.parseInt(p.substring(7)));

                compnaypaymentcurrency.setCompnaypaymentcurrencyPK(compnaypaymentcurrencyPK);
                compnaypaymentcurrency.setCompany(company);
                compnaypaymentcurrency.setCurrencyName(currencyname);
                compnaypaymentcurrency.setCurrencyCode(currencycode);
                compnaypaymentcurrency.setCurrencySymbol(currencysymbol);
                compnaypaymentcurrency.setCreateddate(new Date());
                compnaypaymentcurrency.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompnaypaymentcurrencyPrep.class");
                
                compnaypaymentcurrencyJpaController.create(compnaypaymentcurrency);

                
                   //////////////////
                   resElement = responseOF.createResponsePageElementsElement();
                   resElement.setId("compnaypaymentcurrency");
                   resElement.setCurrencyid(compnaypaymentcurrency.getCompnaypaymentcurrencyPK().getId());
                   resElement.setElementstatus("OK");
                   resElement.setElementstatusmessage("Success");
                   ////
                   responseElementList.add(resElement);
              } 
             //edit an existing company currency...
             else if(!currencyname.isEmpty() && !currencycode.isEmpty() && !currencysymbol.isEmpty() && currencyid != null)
             {
                 System.out.println("Editing............");
                 CompnaypaymentcurrencyPK compnaypaymentcurrencyPK_ = new CompnaypaymentcurrencyPK();
                 compnaypaymentcurrencyPK_.setPubkey(publickey);
                 compnaypaymentcurrencyPK_.setId(Integer.parseInt(currencyid));
                 Compnaypaymentcurrency compnaypaymentcurrency_ = new Compnaypaymentcurrency();
                 CompnaypaymentcurrencyJpaController compnaypaymentcurrencyJpaController_ = new CompnaypaymentcurrencyJpaController();
                 compnaypaymentcurrency_ = compnaypaymentcurrencyJpaController_.findCompnaypaymentcurrency(compnaypaymentcurrencyPK_);
                 
                 compnaypaymentcurrency_.setCurrencyCode(currencycode);
                 compnaypaymentcurrency_.setCurrencyName(currencyname);
                 compnaypaymentcurrency_.setCurrencySymbol(currencysymbol);
                 compnaypaymentcurrency_.setChangeddate(new Date());
                 compnaypaymentcurrency_.setChangedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompnaypaymentcurrencyPrep.class");
                 compnaypaymentcurrencyJpaController_.edit(compnaypaymentcurrency_);
                
                 //////////////////
                 resElement = responseOF.createResponsePageElementsElement();
                 resElement.setId("compnaypaymentcurrency");
                 resElement.setCurrencyid(compnaypaymentcurrency_.getCompnaypaymentcurrencyPK().getId());
                 resElement.setElementstatus("OK");
                 resElement.setElementstatusmessage("Success");
                 ////
                 responseElementList.add(resElement);
             }
                
             }
           }catch(Exception ex)
           {
                this.setStatus("ERROR");
                this.setStatusmessage(ex.getMessage());
           }
          
          
        return responseElementList;  
   }
     
}