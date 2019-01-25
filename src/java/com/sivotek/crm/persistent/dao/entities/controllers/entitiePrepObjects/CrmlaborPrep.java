/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmlabor;
import com.sivotek.crm.persistent.dao.entities.CrmlaborPK;
import com.sivotek.crm.persistent.dao.entities.Crmlabortype;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmlaborJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CrmlaborPrep {
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
     
    public List<Response.Page.Elements.Element> crmlabor(List children, int publickey, int companyID)
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
                String labortypeid = getElementStringValueFromList("labortypeid", children);
                String lineqty = getElementStringValueFromList("lineqty", children);
                String estimatedcost = getElementStringValueFromList("estimatedcost", children);
                String actualcost = getElementStringValueFromList("actualcost", children);
                String isBillable = getElementStringValueFromList("isBillable", children);
                String description = getElementStringValueFromList("description", children);
                
                Companyemployee companyemployee = new Companyemployee();
                Companycustomer companycustomer = new Companycustomer();
                Crmlabortype crmlabortype = new Crmlabortype();
                Crmlabor crmlabor = new Crmlabor();
                CrmlaborPK crmlaborPK = new CrmlaborPK();
                crmlaborPK.setPubkey(publickey);
                
                
                //find company employee from company employee collection
                if(company.getCompanyemployeeCollection().size() > 0)
                {
                    Collection<Companyemployee> employeeColl = company.getCompanyemployeeCollection();
                    for(Companyemployee employee : employeeColl)
                    {
                        if(employee.getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
                        {
                            companyemployee = employee;
                            break;
                        }
                    }
                }
                
               //if company employee was found...
               if(companyemployee.getCompanyemployeePK().getId() > 0)
                {
                 if(customerid != null && !customerid.isEmpty() && Integer.parseInt(customerid) > 0)
                  {
                     //find customer detail from company customer collection
                    if(company.getCompanycustomerCollection().size() > 0)
                    {
                        Collection<Companycustomer> customerColl = company.getCompanycustomerCollection();
                        for(Companycustomer customer : customerColl)
                        {
                            if(customer.getCompanycustomerPK().getId() == Integer.parseInt(customerid))
                            {
                                companycustomer = customer;
                                break;
                            }
                        }
                    }//end
                    
                    //find labortype from company labortype collection
                    if(company.getCrmlabortypeCollection().size() > 0)
                    {
                        Collection<Crmlabortype> labortypeColl = company.getCrmlabortypeCollection();
                        for(Crmlabortype labortype : labortypeColl)
                        {
                            if(labortype.getCrmlabortypePK().getId() == Integer.parseInt(labortypeid))
                            {
                                crmlabortype = labortype;
                                break;
                            }
                        }
                    }//end
                    
                    if(companycustomer.getCompanycustomerPK().getId() > 0 && crmlabortype.getCrmlabortypePK().getId() > 0)
                    {
                        crmlabor.setCompany(company);
                        crmlabor.setCompanycustomer(companycustomer);
                        crmlabor.setCompanycustomer(companycustomer);
                        crmlabor.setCrmlabortype(crmlabortype);
                        long bint = System.currentTimeMillis();
                        String p = ""+bint;
                        crmlaborPK.setId(Integer.parseInt(p.substring(7)));
                        crmlabor.setCrmlaborPK(crmlaborPK);
                        crmlabor.setLineqty(Integer.parseInt(lineqty));
                        crmlabor.setEstimatedcost(Double.parseDouble(estimatedcost));
                        crmlabor.setActualcost(Double.parseDouble(actualcost));
                        crmlabor.setIsBILLABLE(Boolean.parseBoolean(isBillable));
                        crmlabor.setDescription(description);
                        crmlabor.setCreateddate(new Date());
                        crmlabor.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmlaborPrep.class");
                        CrmlaborJpaController crmlaborJpaController = new CrmlaborJpaController();
                        crmlaborJpaController.create(crmlabor);
                         //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("crmlabor");
                        resElement.setLabortypeid(crmlabor.getCrmlaborPK().getId());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                        responseElementList.add(resElement);
                    }
                  }
                 
                 //load company labor fro company labor collection
                 else if(customerid != null && !customerid.isEmpty() && Integer.parseInt(customerid) == 0)
                 {
                     if(company.getCrmlaborCollection().size()>0)
                     {
                         Collection<Crmlabor> crmlaborColl = company.getCrmlaborCollection();
                         for(Crmlabor labor : crmlaborColl)
                         {
                            //////////////////
                            resElement = responseOF.createResponsePageElementsElement();
                            resElement.setId("crmlabor");
                            resElement.setLabortypeid(labor.getCrmlaborPK().getId());
                            resElement.setCustomerid(labor.getCompanycustomer().getCompanycustomerPK().getId());
                            resElement.setLabortypeid(labor.getCrmlabortype().getCrmlabortypePK().getId());
                            resElement.setLineqty(labor.getLineqty());
                            resElement.setEstimatedcost(labor.getEstimatedcost());
                            resElement.setActualcost(labor.getActualcost());
                            resElement.setIsBillable(labor.getIsBILLABLE());
                            resElement.setDescription(labor.getDescription());
                            ////
                            responseElementList.add(resElement);
                         }
                     }
                 }//
                 
               }
                   else{
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("crmlabor");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("Invalid Employee ID");
                        ////
                        responseElementList.add(resElement);
                     } 
               
            }
            else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("crmlabor");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
        }catch(Exception ex)
        {
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("crmlabor");
                    resElement.setElementstatus("ERROR");
                    resElement.setElementstatusmessage(ex.getMessage());
                    ////
                    responseElementList.add(resElement);
        }
        
        return responseElementList;
    }
}
