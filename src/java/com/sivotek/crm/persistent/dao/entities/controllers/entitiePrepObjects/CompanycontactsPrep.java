/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyaddresstype;
import com.sivotek.crm.persistent.dao.entities.Companycontacts;
import com.sivotek.crm.persistent.dao.entities.CompanycontactsPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanycontactsJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanycontactsPrep {
 
   private Response response;
   private String status = "";
   private String statusmessage = "";
   private int contactid = 0;
   

   //getters and setters
   public int getContactid() 
   {return contactid;}
   public void setContactid(int contactid) 
   {this.contactid = contactid;}
   
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
   public List<Response.Page.Elements.Element> companycontacts(List children, int publickey, int companyID){
           
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
           companyPK.setPubkey(publickey);
           companyPK.setCompanyid(companyID);
           
           long bint = System.currentTimeMillis();
           String p = ""+bint;
           
           try{
           company = companyJpaController.findCompany(companyPK);
           if(company.getCompanyPK() != null && company.getCompanyPK().getCompanyid() > 0){
               
               String firstname = getElementStringValueFromList("firstname", children);
               String lastname = getElementStringValueFromList("lastname", children);
               String othername = getElementStringValueFromList("othername", children);
               String phone = getElementStringValueFromList("phone", children);
               String fax = getElementStringValueFromList("fax", children);
               String mobile = getElementStringValueFromList("mobile", children);
               String email = getElementStringValueFromList("email", children);
               
             if(firstname != null && !firstname.isEmpty() && lastname != null && !lastname.isEmpty())
             { 
               CompanycontactsJpaController companycontactsJpaController = new CompanycontactsJpaController();
               Companycontacts companycontacts = new Companycontacts();
               CompanycontactsPK companycontactsPK = new CompanycontactsPK();
               
               
               companycontactsPK.setPubkey(publickey);
               companycontactsPK.setContactid(Integer.parseInt(p.substring(7)));
               companycontacts.setCompanycontactsPK(companycontactsPK);
               companycontacts.setCompany(company);
               companycontacts.setFisrtName(firstname);
               companycontacts.setLastName(lastname);
               companycontacts.setOtherName(othername);
               companycontacts.setPhone(phone);
               companycontacts.setFax(fax);
               companycontacts.setMobile(mobile);
               companycontacts.setEmail(email);
               companycontacts.setCreateddate(new Date());
               
               companycontacts.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanycontactsPrep.class");
               companycontactsJpaController.create(companycontacts);
                     
             
               //////////////////
               resElement = responseOF.createResponsePageElementsElement();
               resElement.setId("companycontacts");
               resElement.setContactid(companycontacts.getCompanycontactsPK().getContactid());
               resElement.setElementstatus("OK");
               resElement.setElementstatusmessage("Success");
               ////
               responseElementList.add(resElement);
             }
             //fitch all company contacts
             else if(firstname != null && firstname.isEmpty() && lastname != null && lastname.isEmpty() && othername != null && othername.isEmpty())
             {
                 if(company.getCompanycontactsCollection().size() > 0)
                 {
                     Collection<Companycontacts> contactsColl = company.getCompanycontactsCollection();
                     for(Companycontacts contact : contactsColl)
                     {
                         //////////////////
                         resElement = responseOF.createResponsePageElementsElement();
                         resElement.setId("companycontacts");
                         resElement.setContactid(contact.getCompanycontactsPK().getContactid());
                         resElement.setFirstname(contact.getFisrtName());
                         resElement.setLastname(contact.getLastName());
                         resElement.setOthername(contact.getOtherName());
                         resElement.setFax(contact.getFax());
                         resElement.setPhone(contact.getPhone());
                         resElement.setMobile(contact.getMobile());
                         resElement.setEmail(contact.getEmail());
                         ////
                         responseElementList.add(resElement);
                     }
                 }
                 else
                 {
                       //////////////////
                       resElement = responseOF.createResponsePageElementsElement();
                       resElement.setId("companycontacts");
                       resElement.setElementstatus("FAIL");
                       resElement.setElementstatusmessage("No contact records found");
                       ////
                       responseElementList.add(resElement);
                 }
             }
             
           }else{
               //////////////////
               resElement = responseOF.createResponsePageElementsElement();
               resElement.setId("companycontacts");
               resElement.setElementstatus("FAIL");
               resElement.setElementstatusmessage("Invalid company ID");
               ////
               responseElementList.add(resElement);
           }
           }catch(Exception ex){
               //////////////////
               resElement = responseOF.createResponsePageElementsElement();
               resElement.setId("companycontacts");
               resElement.setElementstatus("ERROR");
               String errmsg = "";
               errmsg += ex.getMessage();
               
               if(errmsg.equalsIgnoreCase("null"))
               {
                  resElement.setElementstatusmessage("Nullpointer exception.. please verify data entry"); 
                  responseElementList.add(resElement);
               }
               else{
                   resElement.setElementstatusmessage(""+ex.getMessage());
                   //
                   responseElementList.add(resElement);
               }
           }
         
           return responseElementList;
   }
}
