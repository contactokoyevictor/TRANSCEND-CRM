/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Category;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CategoryJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanyPrep {
   private Response response;
   private String status = "";
   private String statusmessage = "";
   private int companyID = 0;
   

   //getters and setters
   public int getCompanyID() 
   {return companyID;}
   public void setCompanyID(int companyID) 
   {this.companyID = companyID;}
   
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
     
   //////////////////////////////////////////
   public  List<Response.Page.Elements.Element> company(List children, int publickey, int companyID){
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

       String companyname = getElementStringValueFromList("companyname", children);
       String companysubname = getElementStringValueFromList("companysubname", children);
       String companyphone = getElementStringValueFromList("companyphone", children);
       String companyfax = getElementStringValueFromList("companyfax", children);
       String companyemail = getElementStringValueFromList("companyemail", children);
       String companyweb = getElementStringValueFromList("companyweb", children);
       String companycategory = getElementStringValueFromList("companycategory", children);
       String companymotto = getElementStringValueFromList("companymotto", children);
       String description = getElementStringValueFromList("description", children);
       String logo = getElementStringValueFromList("companylogo", children);

      
       CategoryJpaController categoryJpaController = new CategoryJpaController();
       Category category = new Category();
       
       category = categoryJpaController.findCategory(Integer.parseInt(companycategory));
       
       if(companyID == 0){
       CompanyJpaController companyJpaController = new CompanyJpaController();
       long bint = System.currentTimeMillis();
       String p = ""+bint;
       
       Company company = new Company();
       CompanyPK companyPK = new CompanyPK();
       companyPK.setPubkey(publickey);
       companyPK.setCompanyid(Integer.parseInt(p.substring(7)));
       
       company.setName(companyname);
       company.setSubName(companysubname);
       company.setPhone(companyphone);
       company.setFax(companyfax);
       company.setEmail(companyemail);
       company.setWeb(companyweb);
       if(category.getId() > 0){
       company.setCategory(category);
       }
       company.setMotto(companymotto);
       
       company.setCompanyPK(companyPK);
       company.setDescription(description);
       company.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.Company.class");
       
       company.setCreateddate(new Date());
       try{
             companyJpaController.create(company);
             //////////////////
             resElement = responseOF.createResponsePageElementsElement();
             resElement.setId("company");
             resElement.setCompanyid(company.getCompanyPK().getCompanyid());
             resElement.setElementstatus("OK");
             resElement.setElementstatusmessage("Success");
             ////
             responseElementList.add(resElement);
       }catch(Exception ex){
             //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("company");
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
       
       }
     }
   
       if(companyID > 0)
       {
            CompanyJpaController companyJpaController = new CompanyJpaController();
            Company company = new Company();
            CompanyPK companyPK = new CompanyPK();
            companyPK.setPubkey(publickey);
            companyPK.setCompanyid(companyID);
            company = companyJpaController.findCompany(companyPK);
            
            company.setName(companyname);
            company.setSubName(companysubname);
            company.setPhone(companyphone);
            company.setFax(companyfax);
            company.setEmail(companyemail);
            company.setWeb(companyweb);
            if(category.getId() > 0){
                company.setCategory(category);
            }
            company.setMotto(companymotto);
       
            company.setCompanyPK(companyPK);
            company.setDescription(description);
            company.setChangedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.Company.class");
            company.setChangeddate(new Date());
           
          try{
             companyJpaController.edit(company);
             //////////////////
             resElement = responseOF.createResponsePageElementsElement();
             resElement.setId("company");
             resElement.setCompanyid(company.getCompanyPK().getCompanyid());
             resElement.setElementstatus("OK");
             resElement.setElementstatusmessage("Success");
             ////
             responseElementList.add(resElement);
             
         }catch(Exception ex){
             //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("company");
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
       
       }
       }
       return responseElementList;
   }
  
  
}
