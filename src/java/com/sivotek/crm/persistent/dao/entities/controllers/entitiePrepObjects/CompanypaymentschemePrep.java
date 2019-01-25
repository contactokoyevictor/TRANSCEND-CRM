/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companypaymentscheme;
import com.sivotek.crm.persistent.dao.entities.CompanypaymentschemePK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanypaymentschemeJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanypaymentschemePrep {
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
       for (Object elementList1 : elementList) {
           JAXBElement e = (JAXBElement) elementList1;
           if (e.getName().getLocalPart().equals(elementName)) {
               return e.getValue().toString();
           }
       }
        return null;
    }
  
  public List<Response.Page.Elements.Element> companypaymentscheme(List children, int publickey, int companyID)
  {//
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
                String name = getElementStringValueFromList("name", children);
                String description = getElementStringValueFromList("description", children);
                String schemeid = getElementStringValueFromList("schemeid", children);
                
                CompanypaymentschemeJpaController companypaymentschemeJpaController = new CompanypaymentschemeJpaController();
                Companypaymentscheme companypaymentscheme = new Companypaymentscheme();
                //load all company schemes
                if(name.isEmpty() && description.isEmpty() && schemeid == null){
                    if(company.getCompanypaymentschemeCollection().size() >= 0)
                    {
                        Collection<Companypaymentscheme> companypaymentschemeColl = company.getCompanypaymentschemeCollection();
                        for(Companypaymentscheme scheme : companypaymentschemeColl)
                        {
                            //////////////////
                            resElement = responseOF.createResponsePageElementsElement();
                            resElement.setId("companypaymentscheme");
                            resElement.setSchemeid(scheme.getCompanypaymentschemePK().getId());
                            resElement.setName(scheme.getSchemeName());
                            resElement.setDescription(scheme.getDescription());
                            resElement.setElementstatus("OK");
                            resElement.setElementstatusmessage("Success");
                            ////
                            responseElementList.add(resElement);
                        }
                    }
                }
                //create a new scheme
                else if(!name.isEmpty() && !description.isEmpty() && schemeid == null){
                CompanypaymentschemePK companypaymentschemePK = new CompanypaymentschemePK();
                companypaymentschemePK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                companypaymentschemePK.setId(Integer.parseInt(p.substring(7)));
                companypaymentscheme.setCompanypaymentschemePK(companypaymentschemePK);
                companypaymentscheme.setSchemeName(name);
                companypaymentscheme.setDescription(description);
                companypaymentscheme.setCompany(company);
                companypaymentscheme.setCreateddate(new Date());
                companypaymentscheme.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanypaymentschemePrep.class");
                
                companypaymentschemeJpaController.create(companypaymentscheme);
                
                //////////////////
                resElement = responseOF.createResponsePageElementsElement();
                resElement.setId("companypaymentscheme");
                resElement.setSchemeid(companypaymentscheme.getCompanypaymentschemePK().getId());
                resElement.setElementstatus("OK");
                resElement.setElementstatusmessage("Success");
                ////
                responseElementList.add(resElement);
                }
               //edit an existing scheme details
               else if(!name.isEmpty() && !description.isEmpty() && schemeid != null)
               {
                 CompanypaymentschemePK companypaymentschemePK = new CompanypaymentschemePK();
                 companypaymentschemePK.setPubkey(publickey);  
                 companypaymentschemePK.setId(Integer.parseInt(schemeid));
                 companypaymentscheme = new Companypaymentscheme();
                 companypaymentschemeJpaController = new CompanypaymentschemeJpaController();
                 companypaymentscheme = companypaymentschemeJpaController.findCompanypaymentscheme(companypaymentschemePK);
                 companypaymentscheme.setSchemeName(name);
                 companypaymentscheme.setDescription(description);
                 companypaymentscheme.setChangeddate(new Date());
                 companypaymentscheme.setChangedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanypaymentschemePrep.class");
                 
                 companypaymentschemeJpaController.edit(companypaymentscheme);
                 //////////////////
                resElement = responseOF.createResponsePageElementsElement();
                resElement.setId("companypaymentscheme");
                resElement.setSchemeid(companypaymentscheme.getCompanypaymentschemePK().getId());
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
  
   ///
//   public void companypaymentscheme(List children, int publickey, int companyID){
//               Company company = new Company();
//               CompanyPK companyPK = new CompanyPK();
//               companyPK.setCompanyid(companyID);
//               companyPK.setPubkey(publickey);
//               
//               CompanyJpaController companyJpaController = new CompanyJpaController();
//           try{
//               company = companyJpaController.findCompany(companyPK);
//               
//             if(company.getCompanyPK().getCompanyid() > 0){
//                String name = getElementStringValueFromList("name", children);
//                String description = getElementStringValueFromList("description", children);
//                
//                CompanypaymentschemeJpaController companypaymentschemeJpaController = new CompanypaymentschemeJpaController();
//                Companypaymentscheme companypaymentscheme = new Companypaymentscheme();
//                CompanypaymentschemePK companypaymentschemePK = new CompanypaymentschemePK();
//                companypaymentschemePK.setPubkey(publickey);
//                long bint = System.currentTimeMillis();
//                String p = ""+bint;
//                companypaymentschemePK.setId(Integer.parseInt(p.substring(5)));
//                companypaymentscheme.setCompanypaymentschemePK(companypaymentschemePK);
//                companypaymentscheme.setSchemeName(name);
//                companypaymentscheme.setDescription(description);
//                companypaymentscheme.setCompany(company);
//                companypaymentscheme.setCreateddate(new Date());
//                companypaymentscheme.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanypaymentschemePrep.class");
//                
//                companypaymentschemeJpaController.create(companypaymentscheme);
//                
//                this.setId(companypaymentscheme.getCompanypaymentschemePK().getId());
//                this.setStatus("OK");
//                this.setStatusmessage("Success");
//                
//                }
//             }catch(Exception ex){
//                this.setStatus("ERROR");
//                this.setStatusmessage(ex.getMessage());
//             }
//       
//   }//end...
    
    
    
    
}
