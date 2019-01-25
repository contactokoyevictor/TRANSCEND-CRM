/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.CompanyproductPK;
import com.sivotek.crm.persistent.dao.entities.Companyproductcategory;
import com.sivotek.crm.persistent.dao.entities.Companyproducttype;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyproductJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CompanyproductPrep {
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
   
   public List<Response.Page.Elements.Element> companyaccountstackcd(List children, int publickey, int companyID)
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
                String product_name = getElementStringValueFromList("product_name", children);
                String product_typeid = getElementStringValueFromList("product_typeid", children);
                String product_categoryid = getElementStringValueFromList("product_categoryid", children);
                String product_manufacturer = getElementStringValueFromList("product_manufacturer", children);
                String product_serialnumber = getElementStringValueFromList("product_serialnumber", children);
                
                
                Companyemployee companyemployee = new Companyemployee();
                Companyproduct companyproduct = new Companyproduct();
                CompanyproductPK companyproductPK = new CompanyproductPK();
                companyproductPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                companyproductPK.setId(Integer.parseInt(p.substring(7)));
                companyproduct.setCompanyproductPK(companyproductPK);
                
                //check for employee from company employee collection
                if(company.getCompanyemployeeCollection().size() > 0)
                {
                    Collection<Companyemployee> companyemployeeColl = company.getCompanyemployeeCollection();
                    for(Companyemployee employee : companyemployeeColl)
                    {
                        if(employee.getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
                        {
                            companyemployee = employee;
                            companyproduct.setCompanyemployee(companyemployee);
                            break;
                        }
                    }
                    
                   if(companyemployee.getCompanyemployeePK().getId() > 0)
                   {
                       if(product_typeid != null && !product_typeid.isEmpty() && Integer.parseInt(product_typeid) > 0 && product_categoryid !=null && !product_categoryid.isEmpty() && Integer.parseInt(product_categoryid) > 0)
                       {
                       Companyproducttype companyproducttype = new Companyproducttype();
                       Companyproductcategory companyproductcategory = new Companyproductcategory();
                       
                       //find product type id
                       if(company.getCompanyproducttypeCollection().size() > 0)
                       {
                           Collection<Companyproducttype> producttypeColl = company.getCompanyproducttypeCollection();
                           for(Companyproducttype producttype : producttypeColl)
                           {
                               if(producttype.getCompanyproducttypePK().getId() == Integer.parseInt(product_typeid))
                               {
                                   companyproducttype = producttype;
                                   break;
                               }
                           }
                           
                       }
                       
                       //find product category id
                       if(company.getCompanyproductcategoryCollection().size() > 0)
                       {
                           Collection<Companyproductcategory> productcategoryColl = company.getCompanyproductcategoryCollection();
                           for(Companyproductcategory category : productcategoryColl)
                           {
                               if(category.getCompanyproductcategoryPK().getId() == Integer.parseInt(product_categoryid))
                               {
                                   companyproductcategory = category;
                                   break;
                               }
                           }
                       }
                       
                       //both were found.. continue
                       if(companyproducttype.getCompanyproducttypePK().getId() > 0 && companyproductcategory.getCompanyproductcategoryPK().getId() > 0)
                       {
                           companyproduct.setCompany(company);
                           companyproduct.setCompanyemployee(companyemployee);
                           companyproduct.setCompanyproducttype(companyproducttype);
                           companyproduct.setCompanyproductcategory(companyproductcategory);
                           companyproduct.setProductname(product_name);
                           companyproduct.setManufacturer(product_manufacturer);
                           companyproduct.setSerialnumber(product_serialnumber);
                           companyproduct.setCreateddate(new Date());
                           companyproduct.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CompanyproductPrep.class");
                           CompanyproductJpaController companyproductJpaController = new CompanyproductJpaController();
                           companyproductJpaController.create(companyproduct);
                           //////////////////
                            resElement = responseOF.createResponsePageElementsElement();
                            resElement.setId("companyproduct");
                            resElement.setProductid(companyproduct.getCompanyproductPK().getId());
                            resElement.setElementstatus("OK");
                            resElement.setElementstatusmessage("Success");
                            ////
                            responseElementList.add(resElement);
                       }
                              else{
                                    //////////////////
                                    resElement = responseOF.createResponsePageElementsElement();
                                    resElement.setId("companyproduct");
                                    resElement.setElementstatus("FAIL");
                                    resElement.setElementstatusmessage("Product details not record found");
                                    ////
                                    responseElementList.add(resElement);
                                 }
                   }
                       else if(product_typeid != null && !product_typeid.isEmpty() && Integer.parseInt(product_typeid) == 0 && product_categoryid !=null && !product_categoryid.isEmpty() && Integer.parseInt(product_categoryid) == 0)
                       {
                           //load all company products records
                           if(company.getCompanyproductCollection().size() > 0)
                           {
                               Collection<Companyproduct> productColl = company.getCompanyproductCollection();
                               for(Companyproduct product : productColl)
                               {
                                    //////////////////
                                    resElement = responseOF.createResponsePageElementsElement();
                                    resElement.setId("companyproduct");
                                    resElement.setProductid(product.getCompanyproductPK().getId());
                                    resElement.setProductName(product.getProductname());
                                    resElement.setProductTypeid(product.getCompanyproducttype().getCompanyproducttypePK().getId());
                                    resElement.setProductCategoryid(product.getCompanyproducttype().getCompanyproducttypePK().getId());
                                    resElement.setProductManufacturer(product.getManufacturer());
                                    resElement.setProductSerialnumber(product.getSerialnumber());
                                    ////
                                    responseElementList.add(resElement);  
                               }
                           }
                              else{
                                    //////////////////
                                    resElement = responseOF.createResponsePageElementsElement();
                                    resElement.setId("companyproduct");
                                    resElement.setElementstatus("FAIL");
                                    resElement.setElementstatusmessage("No product record found");
                                    ////
                                    responseElementList.add(resElement);
                                 }
                       }
                   }
                   else{
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("companyproduct");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("Invalid Employee ID");
                        ////
                        responseElementList.add(resElement);
                     }
                }
                 
            }
            else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companyproduct");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
            
        }catch(Exception ex)
        {
            //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("companyproduct");
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
        }
       return responseElementList;
   }
    

}
