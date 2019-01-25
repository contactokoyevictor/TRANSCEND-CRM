/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.CompanycustomerPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.CompanyemployeePK;
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketmanagement;
import com.sivotek.crm.persistent.dao.entities.CrmprojectticketmanagementPK;
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.CrmworkorderPK;
import com.sivotek.crm.persistent.dao.entities.Crmworkordertype;
import com.sivotek.crm.persistent.dao.entities.CrmworkordertypePK;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanycustomerJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmprojectticketmanagementJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmworkorderJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmworkordertypeJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CrmworkorderPrep {
    
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
     
    public List<Response.Page.Elements.Element> crmworkorder(List children, int publickey, int companyID)
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
                String workordertypeid = getElementStringValueFromList("workordertypeid", children);
                String issueid = getElementStringValueFromList("issueid", children);
                String customerid = getElementStringValueFromList("customerid", children);
                String productid = getElementStringValueFromList("productid", children);
                String componentid = getElementStringValueFromList("componentid", children);
                String description = getElementStringValueFromList("description", children);
                
                Companyemployee companyemployee = new Companyemployee();
                CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                companyemployeePK.setPubkey(publickey);
                companyemployeePK.setId(Integer.parseInt(employeeid));
                companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                
                if(companyemployee.getCompanyemployeePK().getId() > 0)
                {
                    
                    if(workordertypeid != null && !workordertypeid.equalsIgnoreCase("0") && !workordertypeid.isEmpty())
                    {
                        //instanciate workorker entity class alonside it's JPA Controller
                        Crmworkorder crmworkorder = new Crmworkorder();
                        CrmworkorderPK CrmworkorderPK = new CrmworkorderPK();
                        CrmworkorderPK.setPubkey(publickey);
                        long bint = System.currentTimeMillis();
                        String p = ""+bint;
                        CrmworkorderPK.setId(Integer.parseInt(p.substring(7)));
                        crmworkorder.setCrmworkorderPK(CrmworkorderPK);
                        crmworkorder.setDescription(description);
                        crmworkorder.setCompany(company);
                        crmworkorder.setCompanyemployee(companyemployee);
                        crmworkorder.setCreateddate(new Date());
                        crmworkorder.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmworkorderPrep.class");
                        
                        //find workorder type using workordertypeid
                        Crmworkordertype crmworkordertype = new Crmworkordertype();
                        CrmworkordertypePK crmworkordertypePK = new CrmworkordertypePK();
                        crmworkordertypePK.setPubkey(publickey);
                        crmworkordertypePK.setId(Integer.parseInt(workordertypeid));
                        CrmworkordertypeJpaController crmworkordertypeJpaController = new CrmworkordertypeJpaController();
                        crmworkordertype = crmworkordertypeJpaController.findCrmworkordertype(crmworkordertypePK);
                        //if workorder type id was found
                        if(crmworkordertype.getCrmworkordertypePK().getId() > 0)
                        {
                            //find ticket deatial
                            Crmprojectticketmanagement crmprojectticketmanagement = new Crmprojectticketmanagement();
                            CrmprojectticketmanagementPK crmprojectticketmanagementPK = new CrmprojectticketmanagementPK();
                            crmprojectticketmanagementPK.setPubkey(publickey);
                            crmprojectticketmanagementPK.setIssueid(Integer.parseInt(issueid));
                            CrmprojectticketmanagementJpaController crmprojectticketmanagementJpaController = new CrmprojectticketmanagementJpaController();
                            crmprojectticketmanagement = crmprojectticketmanagementJpaController.findCrmprojectticketmanagement(crmprojectticketmanagementPK);
                            //if found...
                            if(crmprojectticketmanagement.getCrmprojectticketmanagementPK().getIssueid() > 0)
                            {
                                //set workorder ticket
                                crmworkorder.setCrmprojectticketmanagement(crmprojectticketmanagement);
                                
                                //check for cutomer details
                                Companycustomer companycustomer = new Companycustomer();
                                CompanycustomerPK companycustomerPK = new CompanycustomerPK();
                                companycustomerPK.setPubkey(publickey);
                                companycustomerPK.setId(Integer.parseInt(customerid));
                                CompanycustomerJpaController companycustomerJpaController = new CompanycustomerJpaController();
                                companycustomer = companycustomerJpaController.findCompanycustomer(companycustomerPK);
                                //if customer id found...
                                if(companycustomer.getCompanycustomerPK().getId() > 0)
                                {
                                    crmworkorder.setCompanycustomer(companycustomer);

                                }
                                
                                //check product id
                                if(productid != null && !productid.isEmpty() && !productid.equalsIgnoreCase("0"))
                                {
                                   if(company.getCompanyproductCollection().size() > 0)
                                   {
                                    Collection<Companyproduct> companyproductColl = company.getCompanyproductCollection();
                                    for(Companyproduct companyproduct : companyproductColl)
                                    {
                                        if(companyproduct.getCompanyproductPK().getId() == Integer.parseInt(productid))
                                        {
                                            crmworkorder.setCompanyproduct(companyproduct);
                                            break;
                                        }
                                    }//end of loop
                                  }
                                }//
                                
                                //check componentid
                                if(componentid != null && !componentid.isEmpty() && !componentid.equalsIgnoreCase("0"))
                                {
                                  if(company.getProductcomponentsCollection().size() > 0)
                                  {
                                    Collection<Productcomponents> productcomponentsColl = company.getProductcomponentsCollection();
                                    for(Productcomponents productcomponents : productcomponentsColl)
                                    {
                                        if(productcomponents.getProductcomponentsPK().getId() == Integer.parseInt(componentid))
                                        {
                                            crmworkorder.setProductcomponents(productcomponents);
                                            break;
                                        }
                                    }//end of loop
                                  }
                                }//
                                
                                
                            }
                            
                              CrmworkorderJpaController CrmworkorderJpaController = new CrmworkorderJpaController();
                              CrmworkorderJpaController.create(crmworkorder);
                              //////////////////
                              resElement = responseOF.createResponsePageElementsElement();
                              resElement.setId("crmworkorder");
                              resElement.setWorkorderid(crmworkorder.getCrmworkorderPK().getId());
                              resElement.setElementstatus("OK");
                              resElement.setElementstatusmessage("Success");
                              ////
                              responseElementList.add(resElement); 
              
                        }
                   
                   }
                    else if(workordertypeid !=null && workordertypeid.equalsIgnoreCase("0"))
                    {
                      if(company.getCrmworkorderCollection().size() > 0)
                      {
                        Collection<Crmworkorder> crmworkorderColl = company.getCrmworkorderCollection();
                        for(Crmworkorder workorder : crmworkorderColl)
                        {
                              //////////////////
                              resElement = responseOF.createResponsePageElementsElement();
                              resElement.setId("crmworkorder");
                              resElement.setWorkorderid(workorder.getCrmworkorderPK().getId());
                              if(workorder.getCrmworkordertype() != null)
                              {
                                  resElement.setWorkordertypeid(workorder.getCrmworkordertype().getCrmworkordertypePK().getId());
                              }
                          
                              resElement.setIssueid(workorder.getCrmprojectticketmanagement().getCrmprojectticketmanagementPK().getIssueid());
                              resElement.setCustomerid(workorder.getCompanycustomer().getCompanycustomerPK().getId());
                              if(workorder.getCompanyproduct() != null)
                              {
                                  resElement.setProductid(workorder.getCompanyproduct().getCompanyproductPK().getId());
                              
                              }
                              if(workorder.getProductcomponents() != null)
                              {
                                  resElement.setComponentid(workorder.getProductcomponents().getProductcomponentsPK().getId());
                              }
                              
                              resElement.setDescription(workorder.getDescription());
                              ////
                              responseElementList.add(resElement); 
                        }
                      }
                    }
                    
                }
                else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("crmworkorder");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Employee ID");
                    ////
                    responseElementList.add(resElement);
                }
            }
            else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("crmworkorder");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
        }catch(Exception ex)
        {
            //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("crmworkorder");
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
        }
        
        return responseElementList;
    }
     

}
