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
import com.sivotek.crm.persistent.dao.entities.Crmexpense;
import com.sivotek.crm.persistent.dao.entities.CrmexpensePK;
import com.sivotek.crm.persistent.dao.entities.Crmexpensetype;
import com.sivotek.crm.persistent.dao.entities.CrmexpensetypePK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmexpenseJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CrmexpensePrep {
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
    
    public List<Response.Page.Elements.Element> crmexpense(List children, int publickey, int companyID)
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
                String customerid = getElementStringValueFromList("customerid", children);
                String expensetypeid = getElementStringValueFromList("expensetypeid", children);
                String lineqty = getElementStringValueFromList("lineqty", children);
                String estimatedcost = getElementStringValueFromList("estimatedcost", children);
                String actualcost = getElementStringValueFromList("actualcost", children);
                String isBillable = getElementStringValueFromList("isBillable", children);
                
                
                Companyemployee companyemployee = new Companyemployee();
                CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                companyemployeePK.setPubkey(publickey);
                companyemployeePK.setId(Integer.parseInt(employeeid));
                companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                
                
                Companycustomer companycustomer = new Companycustomer();
                CompanycustomerPK companycustomerPK = new CompanycustomerPK();
                companycustomerPK.setPubkey(publickey);
                
                Crmexpensetype crmexpensetype = new Crmexpensetype();
                CrmexpensetypePK crmexpensetypePK = new CrmexpensetypePK();
                crmexpensetypePK.setPubkey(publickey);
                
                
                Crmexpense crmexpense = new Crmexpense();
                CrmexpensePK crmexpensePK = new CrmexpensePK();
                
                
                if(companyemployee.getCompanyemployeePK().getId() > 0)
                {
                    crmexpensePK.setPubkey(publickey);
                    long bint = System.currentTimeMillis();
                    String p = ""+bint;
                    crmexpensePK.setId(Integer.parseInt(p.substring(7)));
                    crmexpense.setCrmexpensePK(crmexpensePK);
                    crmexpense.setCompany(company);
                    crmexpense.setCompanyemployee(companyemployee);
                    
                    if(customerid != null && expensetypeid !=null && Integer.parseInt(customerid) > 0 && Integer.parseInt(expensetypeid) > 0)
                    {
                        companycustomerPK.setId(Integer.parseInt(customerid));
                        crmexpensetypePK.setId(Integer.parseInt(expensetypeid));
                        
                        //find customerid from company customer collection
                        if(company.getCompanycustomerCollection().size() > 0)
                        {
                            Collection<Companycustomer> companycustomerColl = company.getCompanycustomerCollection();
                            for(Companycustomer customer : companycustomerColl)
                            {
                                if(customer.getCompanycustomerPK().equals(companycustomerPK))
                                {
                                   companycustomer = customer;
                                   crmexpense.setCompanycustomer(companycustomer);
                                   break;
                                }
                            }
                        }
                        
                        //find expense type id from company expense type collection
                        if(company.getCrmexpensetypeCollection().size() > 0)
                        {
                           Collection<Crmexpensetype> crmexpensetypeColl = company.getCrmexpensetypeCollection();
                           for(Crmexpensetype expensetype : crmexpensetypeColl)
                           {
                               if(expensetype.getCrmexpensetypePK().equals(crmexpensetypePK))
                               {
                                   crmexpensetype = expensetype;
                                   crmexpense.setCrmexpensetype(crmexpensetype);
                                   break;
                               }
                           }
                        }
                        
                            crmexpense.setLineqty(Integer.parseInt(lineqty));
                            crmexpense.setActualcost(Double.parseDouble(actualcost));
                            crmexpense.setEstimatedcost(Double.parseDouble(estimatedcost));
                            crmexpense.setIsBILLABLE(Boolean.parseBoolean(isBillable));
                            crmexpense.setCreateddate(new Date());
                            crmexpense.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmexpensePrep.class");
                            CrmexpenseJpaController crmexpenseJpaController = new CrmexpenseJpaController();
                            crmexpenseJpaController.create(crmexpense);

                             //////////////////
                            resElement = responseOF.createResponsePageElementsElement();
                            resElement.setId("crmexpense");
                            resElement.setExpensetypeid(crmexpense.getCrmexpensePK().getId());
                            resElement.setElementstatus("OK");
                            resElement.setElementstatusmessage("Success");
                            ////
                            responseElementList.add(resElement);
                    }
                    else if(customerid != null && expensetypeid !=null && customerid.equalsIgnoreCase("") && expensetypeid.equalsIgnoreCase(""))
                    {
                        if(company.getCrmexpenseCollection().size() > 0)
                        {
                            Collection<Crmexpense> crmexpenseColl = company.getCrmexpenseCollection();
                            for(Crmexpense expense : crmexpenseColl)
                            {
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("crmexpense");
                                resElement.setExpensetypeid(expense.getCrmexpensePK().getId());
                                resElement.setCustomerid(expense.getCompanycustomer().getCompanycustomerPK().getId());
                                resElement.setExpensetypeid(expense.getCrmexpensetype().getCrmexpensetypePK().getId());
                                resElement.setLineqty(expense.getLineqty());
                                resElement.setEstimatedcost(expense.getEstimatedcost());
                                resElement.setActualcost(expense.getActualcost());
                                resElement.setIsBillable(expense.getIsBILLABLE());
                                ////
                                responseElementList.add(resElement);
                            }
                        }
                    }
                    
                }
                else{
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("crmexpense");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("Invalid Employee ID");
                        ////
                        responseElementList.add(resElement);
                     }  
            }
            else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("crmexpense");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
        }catch(Exception ex)
        {
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("crmexpense");
                    resElement.setElementstatus("ERROR");
                    resElement.setElementstatusmessage(ex.getMessage());
                    ////
                    responseElementList.add(resElement);
        }
        return responseElementList;
    }
}
