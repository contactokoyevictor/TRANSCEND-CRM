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
import com.sivotek.crm.persistent.dao.entities.Companycontacts;
import com.sivotek.crm.persistent.dao.entities.CompanycontactsPK;
import com.sivotek.crm.persistent.dao.entities.Companycontactsaddress;
import com.sivotek.crm.persistent.dao.entities.CompanycontactsaddressPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyaddresstypeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanycontactsJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanycontactsaddressJpaController;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanycontactsaddressPrep {
   private String status = "";
   private String statusmessage = "";
   private int contactsaddressid = 0;
   

   //getters and setters
   public int getContactsaddressid() 
   {return contactsaddressid;}
   public void setContactsaddressid(int contactsaddressid) 
   {this.contactsaddressid = contactsaddressid;}
   
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
   public void companycontactsaddress(List children, int publickey, int companyID){
             Company company = new Company();
             CompanyPK companyPK = new CompanyPK();
             CompanyJpaController companyJpaController = new CompanyJpaController();
             Companyaddresstype companyaddresstype = new Companyaddresstype();
             CompanyaddresstypePK companyaddresstypePK = new CompanyaddresstypePK();
             CompanyaddresstypeJpaController companyaddresstypeJpaController = new CompanyaddresstypeJpaController();
             
             companyPK.setCompanyid(companyID);
             companyPK.setPubkey(publickey);
             
             companyaddresstypePK.setPubkey(publickey);
             
             try{
                company = companyJpaController.findCompany(companyPK);
               if(company.getCompanyPK().getCompanyid() > 0)
                {
                   String addresstypeid = getElementStringValueFromList("addresstypeid", children);
                   String contactid = getElementStringValueFromList("contactid", children);
                   
                   String street = getElementStringValueFromList("street", children);
                   String zip = getElementStringValueFromList("zip", children);
                   String location = getElementStringValueFromList("location", children);
                   String pbox = getElementStringValueFromList("pbox", children);
                   String country = getElementStringValueFromList("country", children);
                   
                   
                   Companycontacts companycontacts = new Companycontacts();
                   CompanycontactsPK companycontactsPK = new CompanycontactsPK();
                   companycontactsPK.setPubkey(publickey);
                   companycontactsPK.setContactid(Integer.parseInt(contactid));
                   CompanycontactsJpaController companycontactsJpaController = new CompanycontactsJpaController();
                   
                   companycontacts = companycontactsJpaController.findCompanycontacts(companycontactsPK);
                   
                   companyaddresstypePK.setId(Integer.parseInt(addresstypeid));
                   companyaddresstype = companyaddresstypeJpaController.findCompanyaddresstype(companyaddresstypePK);
                 if(companyaddresstype.getCompanyaddresstypePK().getId() > 0){
                   CompanycontactsaddressJpaController companycontactsaddressJpaController = new CompanycontactsaddressJpaController();
                   Companycontactsaddress companycontactsaddress = new Companycontactsaddress(); 
                   CompanycontactsaddressPK companycontactsaddressPK = new CompanycontactsaddressPK();
                   companycontactsaddressPK.setPubkey(publickey);
                   long bint = System.currentTimeMillis();
                   String p = ""+bint;
                   companycontactsaddressPK.setId(Integer.parseInt(p.substring(7)));
                   
                   companycontactsaddress.setCompany(company);
                   companycontactsaddress.setCompanycontactsaddressPK(companycontactsaddressPK);
                   
                   companycontactsaddress.setAddresstypeid(companyaddresstype.getCompanyaddresstypePK().getId());
                   companycontactsaddress.setCompanycontacts(companycontacts);
                   
                   companycontactsaddress.setStreet(street);
                   companycontactsaddress.setZip(zip);
                   companycontactsaddress.setLocation(location);
                   companycontactsaddress.setPbox(pbox);
                   companycontactsaddress.setCountry(country);
                   companycontactsaddress.setCreateddate(new Date());
                   companycontactsaddress.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanycontactsaddressPrep.class");
                   companycontactsaddressJpaController.create(companycontactsaddress);
                   
                   this.setContactsaddressid(companycontactsaddress.getCompanycontactsaddressPK().getId());
                   this.setStatus("OK");
                   this.setStatusmessage("Success");
                   
                   }else{
                      this.setStatus("FAIL");
                      this.setStatusmessage("Invalid address type id..");
                   }   
                }else{
                  this.setStatus("FAIL");
                  this.setStatusmessage("Invalid company id..");
                }
             }catch(Exception ex){
                  this.setStatus("ERROR");
                  this.setStatusmessage(ex.getMessage());
             }
   }
}
