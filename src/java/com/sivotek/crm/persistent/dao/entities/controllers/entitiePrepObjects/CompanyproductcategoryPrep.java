/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.CompanyemployeePK;
import com.sivotek.crm.persistent.dao.entities.Companyproductcategory;
import com.sivotek.crm.persistent.dao.entities.CompanyproductcategoryPK;
import com.sivotek.crm.persistent.dao.entities.Customercategory;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyproductcategoryJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanyproductcategoryPrep {
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
     
     public List<Response.Page.Elements.Element> companyproductcategory(List children, int publickey, int companyID)
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
            String name = getElementStringValueFromList("name", children);
            String description = getElementStringValueFromList("description", children);
            Companyemployee companyemployee = new Companyemployee();
            CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
            CompanyemployeePK companyemployeePK = new CompanyemployeePK();
            companyemployeePK.setPubkey(publickey);
            companyemployeePK.setId(Integer.parseInt(employeeid));
            companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
            
            if(companyemployee.getCompanyemployeePK().getId() > 0)
                {
                    if(!name.equalsIgnoreCase(""))
                    {
                        Companyproductcategory companyproductcategory = new Companyproductcategory();
                        CompanyproductcategoryPK companyproductcategoryPK = new CompanyproductcategoryPK();
                        companyproductcategoryPK.setPubkey(publickey);
                        long bint = System.currentTimeMillis();
                        String p = ""+bint;
                        companyproductcategoryPK.setId(Integer.parseInt(p.substring(7)));
                        companyproductcategory.setCompanyproductcategoryPK(companyproductcategoryPK);
                        companyproductcategory.setCompany(company);
                        companyproductcategory.setCompanyemployee(companyemployee);
                        companyproductcategory.setProductcategory(name);
                        companyproductcategory.setDescription(description);
                        companyproductcategory.setCreateddate(new Date());
                        companyproductcategory.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.Companyproductcategory.class");
                        CompanyproductcategoryJpaController companyproductcategoryJpaController = new CompanyproductcategoryJpaController();
                        companyproductcategoryJpaController.create(companyproductcategory);
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("companyproductcategory");
                        resElement.setProductCategoryid(companyproductcategory.getCompanyproductcategoryPK().getId());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                        responseElementList.add(resElement); 
                    }
                     else
                    {
                        if(company.getCompanyproductcategoryCollection().size() > 0)
                        {
                            Collection<Companyproductcategory> companyproductcategoryColl = company.getCompanyproductcategoryCollection();
                            for(Companyproductcategory companyproductcategory : companyproductcategoryColl)
                            {
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("companyproductcategory");
                                resElement.setProductCategoryid(companyproductcategory.getCompanyproductcategoryPK().getId());
                                resElement.setName(companyproductcategory.getProductcategory());
                                resElement.setDescription(companyproductcategory.getDescription());
                                ////
                                responseElementList.add(resElement);
                            }
                        }
                    }
                }
                   else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companyproductcategory");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Employee ID");
                    ////
                    responseElementList.add(resElement);
                }
            }else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companyproductcategory");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
       }catch(Exception ex){}
                
        return responseElementList;
     }
     
}
