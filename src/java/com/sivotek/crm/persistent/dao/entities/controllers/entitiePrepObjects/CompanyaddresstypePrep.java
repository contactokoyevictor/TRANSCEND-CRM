/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyaddresstype;
import com.sivotek.crm.persistent.dao.entities.CompanyaddresstypePK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyaddresstypeJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanyaddresstypePrep {
   private Response response;
   private String status = "";
   private String statusmessage = "";
   private int addresstypeid = 0;
   

   //getters and setters
   public int getAddresstypeid() 
   {return addresstypeid;}
   public void setAddresstypeid(int addresstypeid) 
   {this.addresstypeid = addresstypeid;}
   
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
   public List<Response.Page.Elements.Element> companyaddresstype(List children, int publickey, int companyID){
           
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

           CompanyJpaController companyJpaController = new CompanyJpaController();
           Company company = new Company();
           CompanyPK companyPK = new CompanyPK();
           companyPK.setCompanyid(companyID);
           companyPK.setPubkey(publickey);
           
           try{
                Companyaddresstype companyaddresstype = new Companyaddresstype();
                company = companyJpaController.findCompany(companyPK);
           if(company.getCompanyPK().getCompanyid() > 0)
           {
               String name = getElementStringValueFromList("name", children);
               if(name != null && !name.isEmpty())
               {
                   CompanyaddresstypeJpaController companyaddresstypeJpaController = new CompanyaddresstypeJpaController();
                   CompanyaddresstypePK companyaddresstypePK = new CompanyaddresstypePK();
                   companyaddresstypePK.setPubkey(publickey);

                   companyaddresstype.setName(name);
                   companyaddresstype.setCompanyaddresstypePK(companyaddresstypePK);
                   companyaddresstype.setCompany(company);
                   companyaddresstype.setCreateddate(new Date());
                   companyaddresstype.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyaddresstypePrep.class");


                   long bint = System.currentTimeMillis();
                   String p = ""+bint;
                   companyaddresstypePK.setId(Integer.parseInt(p.substring(7)));
                   companyaddresstypeJpaController.create(companyaddresstype);
                    
                       //////////////////
                   resElement = responseOF.createResponsePageElementsElement();
                   resElement.setId("companyaddresstype");
                   resElement.setAddresstypeid(companyaddresstype.getCompanyaddresstypePK().getId());
                   resElement.setElementstatus("OK");
                   resElement.setElementstatusmessage("Success");
                   ////
                   responseElementList.add(resElement);
               }
               else if(name != null && name.isEmpty())
               {
                   if(company.getCompanyaddresstypeCollection().size() > 0)
                   {
                       Collection<Companyaddresstype> companyaddresstypeColl = company.getCompanyaddresstypeCollection();
                       for(Companyaddresstype addresstype : companyaddresstypeColl)
                       {
                           //////////////////
                           resElement = responseOF.createResponsePageElementsElement();
                           resElement.setId("companyaddresstype");
                           resElement.setAddresstypeid(addresstype.getCompanyaddresstypePK().getId());
                           resElement.setName(addresstype.getName());
                           ////
                           responseElementList.add(resElement);  
                       }
                   }
                   else
                   {
                       //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companyaddresstype");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("No company address type record found");
                    ////
                    responseElementList.add(resElement);
                   }
               }
               
               
               }
               else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companyaddresstype");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid company ID");
                    ////
                    responseElementList.add(resElement);
                    
               }
               }catch(Exception ex)
               {
                   //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companyaddresstype");
                    resElement.setElementstatus("ERROR");
                    resElement.setElementstatusmessage(ex.getMessage());
                    ////
                    responseElementList.add(resElement);
               }
                     
           return responseElementList;
   }

}
