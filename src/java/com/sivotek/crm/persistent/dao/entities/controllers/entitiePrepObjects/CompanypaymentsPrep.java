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
import com.sivotek.crm.persistent.dao.entities.Companypaymentscheme;
import com.sivotek.crm.persistent.dao.entities.CompanypaymentschemePK;
import com.sivotek.crm.persistent.dao.entities.Compnaypaymentcurrency;
import com.sivotek.crm.persistent.dao.entities.CompnaypaymentcurrencyPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanypaymentsJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanypaymentschemeJpaController;
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
public class CompanypaymentsPrep {
   private String status = "";
   private String statusmessage = "";
   private int id = 0;
   private Response response;
   
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
        for (int i = 0; i < elementList.size(); i++) {
            JAXBElement e = (JAXBElement) elementList.get(i);
            if (e.getName().getLocalPart().equals(elementName)) {
                return e.getValue().toString();
            }
        }
        return null;
    }
     
     
    
   ///
   public List<Response.Page.Elements.Element> compnaypaymentcurrency(List children, int publickey, int companyID){
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
               CompanyPK companyPK = new CompanyPK();
               companyPK.setCompanyid(companyID);
               companyPK.setPubkey(publickey);
               CompanyJpaController companyJpaController = new CompanyJpaController();
              
              try{
               company = companyJpaController.findCompany(companyPK);
               
             if(company.getCompanyPK().getCompanyid() > 0){
                String schemeid = getElementStringValueFromList("schemeid", children);
                String currencyid = getElementStringValueFromList("currencyid", children);
                String amount = getElementStringValueFromList("amount", children);
                
                if(schemeid.equalsIgnoreCase("0") && currencyid.equalsIgnoreCase("0") && amount.equalsIgnoreCase("0.00"))
                {
                    if(company.getCompanypaymentsCollection().size() >= 0)
                    {
                        Collection<Companypayments> companypaymentsColl = company.getCompanypaymentsCollection();
                        for(Companypayments payments : companypaymentsColl)
                        {
                             //////////////////
                            resElement = responseOF.createResponsePageElementsElement();
                            resElement.setId("companypayments");
                            resElement.setPaymentid(payments.getCompanypaymentsPK().getId());
                            resElement.setSchemeid(payments.getCompanypaymentscheme().getCompanypaymentschemePK().getId());
                            resElement.setCurrencyid(payments.getCompnaypaymentcurrency().getCompnaypaymentcurrencyPK().getId());
                            resElement.setAmount(payments.getAmount());
                            resElement.setName(payments.getCompanypaymentscheme().getSchemeName());
                            resElement.setElementstatus("OK");
                            resElement.setElementstatusmessage("Success");
                            ////
                            responseElementList.add(resElement);
                        }
                        
                    }
                }
                else if(!schemeid.equalsIgnoreCase("0") && !currencyid.equalsIgnoreCase("0") && !amount.equalsIgnoreCase("0.00"))
                {
                CompnaypaymentcurrencyJpaController compnaypaymentcurrencyJpaController = new CompnaypaymentcurrencyJpaController();
                Compnaypaymentcurrency compnaypaymentcurrency = new Compnaypaymentcurrency();
                CompnaypaymentcurrencyPK compnaypaymentcurrencyPK = new CompnaypaymentcurrencyPK();
                compnaypaymentcurrencyPK.setId(Integer.parseInt(currencyid));
                compnaypaymentcurrencyPK.setPubkey(publickey);
                
                CompanypaymentschemeJpaController companypaymentschemeJpaController = new CompanypaymentschemeJpaController();
                Companypaymentscheme companypaymentscheme = new Companypaymentscheme();
                CompanypaymentschemePK companypaymentschemePK = new CompanypaymentschemePK();
                companypaymentschemePK.setId(Integer.parseInt(schemeid));
                companypaymentschemePK.setPubkey(publickey);
                
                companypaymentscheme = companypaymentschemeJpaController.findCompanypaymentscheme(companypaymentschemePK);
                compnaypaymentcurrency = compnaypaymentcurrencyJpaController.findCompnaypaymentcurrency(compnaypaymentcurrencyPK);
                        
                CompanypaymentsJpaController companypaymentsJpaController = new CompanypaymentsJpaController();
                Companypayments companypayments = new Companypayments();
                CompanypaymentsPK companypaymentsPK = new CompanypaymentsPK();
                companypaymentsPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                companypaymentsPK.setId(Integer.parseInt(p.substring(7)));

                companypayments.setCompanypaymentsPK(companypaymentsPK);
                companypayments.setCompany(company);
                companypayments.setCompanypaymentscheme(companypaymentscheme);
                companypayments.setAmount(Double.parseDouble(amount));
                companypayments.setCompnaypaymentcurrency(compnaypaymentcurrency);
                companypayments.setCreateddate(new Date());
                companypayments.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanypaymentsPrep.class");
                companypaymentsJpaController.create(companypayments);
                
//                this.setId(companypayments.getCompanypaymentsPK().getId());
//                this.setStatus("OK");
//                this.setStatusmessage("Success");
                
                 //////////////////
                 resElement = responseOF.createResponsePageElementsElement();
                 resElement.setId("companypayments");
                 resElement.setPaymentid(companypayments.getCompanypaymentsPK().getId());
                 resElement.setElementstatus("OK");
                 resElement.setElementstatusmessage("Success");
                  ////
                 responseElementList.add(resElement);
                 
                }
             }
          }catch(Exception ex){
                this.setStatus("ERROR");
                this.setStatusmessage(ex.getMessage());
          }
      
    return responseElementList;
   }
}
