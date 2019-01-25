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
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.CompanyemployeePK;
import com.sivotek.crm.persistent.dao.entities.Companyemployeeaddress;
import com.sivotek.crm.persistent.dao.entities.CompanyemployeeaddressPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyaddresstypeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeaddressJpaController;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanyemployeeaddressPrep {
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
        for (int i = 0; i < elementList.size(); i++) {
            JAXBElement e = (JAXBElement) elementList.get(i);
            if (e.getName().getLocalPart().equals(elementName)) {
                return e.getValue().toString();
            }
        }
        return null;
    }
     
   ///
   public void companyemployeeaddress(List children, int publickey, int companyID)
   {
       Company company = new Company();
       CompanyPK companyPK = new CompanyPK();
       companyPK.setCompanyid(companyID);
       companyPK.setPubkey(publickey);
       CompanyJpaController companyJpaController = new CompanyJpaController();
          try{
               company = companyJpaController.findCompany(companyPK);
               
             if(company.getCompanyPK().getCompanyid() > 0){
             
                String employeeid = getElementStringValueFromList("employeeid", children);
                String addresstypeid = getElementStringValueFromList("addresstypeid", children);
                String street = getElementStringValueFromList("street", children);
                String zip = getElementStringValueFromList("zip", children);
                String location = getElementStringValueFromList("location", children);
                String pbox = getElementStringValueFromList("pbox", children);
                String country = getElementStringValueFromList("country", children);
                
                Companyaddresstype companyaddresstype = new Companyaddresstype();
                CompanyaddresstypePK companyaddresstypePK = new CompanyaddresstypePK();
                companyaddresstypePK.setId(Integer.parseInt(addresstypeid));
                companyaddresstypePK.setPubkey(publickey);
                        
                CompanyaddresstypeJpaController companyaddresstypeJpaController = new CompanyaddresstypeJpaController();
                
                Companyemployee companyemployee = new Companyemployee();
                CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                companyemployeePK.setId(Integer.parseInt(employeeid));
                companyemployeePK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                //
                CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                
                companyaddresstype = companyaddresstypeJpaController.findCompanyaddresstype(companyaddresstypePK);
                companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                
                Companyemployeeaddress Companyemployeeaddress = new Companyemployeeaddress();
                CompanyemployeeaddressPK companyemployeeaddressPK = new CompanyemployeeaddressPK();
                companyemployeeaddressPK.setPubkey(publickey);
                companyemployeeaddressPK.setId(Integer.parseInt(p.substring(7)));
                Companyemployeeaddress.setCompanyaddresstype(companyaddresstype);
                Companyemployeeaddress.setCompanyemployee(companyemployee);
                Companyemployeeaddress.setCompanyemployeeaddressPK(companyemployeeaddressPK);
                Companyemployeeaddress.setStreet(street);
                Companyemployeeaddress.setZip(zip);
                Companyemployeeaddress.setLocation(location);
                Companyemployeeaddress.setPbox(pbox);
                Companyemployeeaddress.setCountry(country);
                Companyemployeeaddress.setCreateddate(new Date());
                Companyemployeeaddress.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeaddressPrep.class");
                
                
                CompanyemployeeaddressJpaController companyemployeeaddressJpaController = new CompanyemployeeaddressJpaController();
                
                companyemployeeaddressJpaController.create(Companyemployeeaddress);
                this.setId(Companyemployeeaddress.getCompanyemployeeaddressPK().getId());
                this.setStatus("OK");
                this.setStatusmessage("Success");
             }
             
          }catch(Exception ex){
                this.setStatus("ERROR");
                this.setStatusmessage(ex.getMessage());
          }
   }

}
