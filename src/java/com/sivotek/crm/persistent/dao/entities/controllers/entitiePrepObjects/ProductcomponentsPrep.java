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
import com.sivotek.crm.persistent.dao.entities.Componenttype;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.ProductcomponentsPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.ProductcomponentsJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class ProductcomponentsPrep {
    //
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
   
   public List<Response.Page.Elements.Element> productcomponents(List children, int publickey, int companyID)
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
                String component_name = getElementStringValueFromList("component_name", children);
                String component_typeid = getElementStringValueFromList("component_typeid", children);
                String productid = getElementStringValueFromList("productid", children);
                String estimatedcost = getElementStringValueFromList("estimatedcost", children);
                String actualcost = getElementStringValueFromList("actualcost", children);
                String quantity = getElementStringValueFromList("quantity", children);
                
                
                Companyemployee companyemployee = new Companyemployee();
                Productcomponents productcomponents = new Productcomponents();
                ProductcomponentsPK productcomponentsPK = new ProductcomponentsPK();
                productcomponentsPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                productcomponentsPK.setId(Integer.parseInt(p.substring(7)));
                productcomponents.setProductcomponentsPK(productcomponentsPK);
                
                Componenttype componenttype = new Componenttype();
                Companyproduct companyproduct = new Companyproduct();
                
                //check for employee from company employee collection
                if(company.getCompanyemployeeCollection().size() > 0)
                {
                    Collection<Companyemployee> companyemployeeColl = company.getCompanyemployeeCollection();
                    for(Companyemployee employee : companyemployeeColl)
                    {
                        if(employee.getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
                        {
                            companyemployee = employee;
                            break;
                        }
                    }
                    
                 //if employee found
                 if(companyemployee.getCompanyemployeePK().getId() > 0)
                   {
                       if(component_typeid != null && !component_typeid.isEmpty() && Integer.parseInt(component_typeid) > 0 
                               && productid != null && !productid.isEmpty() && Integer.parseInt(productid) > 0 )
                       {
                       //find component type from company collection
                       if(company.getComponenttypeCollection().size() > 0)
                       {
                           Collection<Componenttype> componenttypeColl = company.getComponenttypeCollection();
                           for(Componenttype component_type : componenttypeColl)
                           {
                               if(component_type.getComponenttypePK().getId() == Integer.parseInt(component_typeid))
                               {
                                   componenttype = component_type;
                                   break;
                               }
                             
                           }
                       }
                      
                      //find productid from company product collection
                       if(company.getCompanyproductCollection().size() > 0)
                       {
                           Collection<Companyproduct> productColl = company.getCompanyproductCollection();
                           for(Companyproduct product : productColl)
                           {
                               if(product.getCompanyproductPK().getId() == Integer.parseInt(productid))
                               {
                                   companyproduct = product;
                                   break;
                               }
                           }
                       }
                       
                       
                    //if product id and component type was found..
                     if(companyproduct.getCompanyproductPK().getId() > 0 && componenttype.getComponenttypePK().getId() > 0)
                     {
                         productcomponents.setCompany(company);
                         productcomponents.setCompanyemployee(companyemployee);
                         productcomponents.setComponenttype(componenttype);
                         productcomponents.setCompanyproduct(companyproduct);
                         productcomponents.setComponentname(component_name);
                         productcomponents.setEstimatedcost(Double.parseDouble(estimatedcost));
                         productcomponents.setActualcost(Double.parseDouble(actualcost));
                         productcomponents.setQuantity(Integer.parseInt(quantity));
                         productcomponents.setCreateddate(new Date());
                         productcomponents.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.ProductcomponentsPrep.class");
                         ProductcomponentsJpaController productcomponentsJpaController = new ProductcomponentsJpaController();
                         productcomponentsJpaController.create(productcomponents);
                         
                         //////////////////
                         resElement = responseOF.createResponsePageElementsElement();
                         resElement.setId("productcomponents");
                         resElement.setComponentid(productcomponents.getProductcomponentsPK().getId());
                         resElement.setElementstatus("OK");
                         resElement.setElementstatusmessage("Success");
                         ////
                         responseElementList.add(resElement);
                         
                     }
                   }
                       
                       //
                       else if(component_typeid != null && !component_typeid.isEmpty() && Integer.parseInt(component_typeid) == 0 
                               && productid != null && !productid.isEmpty() && Integer.parseInt(productid) == 0 )
                       {
                           if(company.getProductcomponentsCollection().size() > 0)
                           {
                               Collection<Productcomponents> componentsColl = company.getProductcomponentsCollection();
                               for(Productcomponents productcomponent : componentsColl)
                               {
                                     //////////////////
                                     resElement = responseOF.createResponsePageElementsElement();
                                     resElement.setId("productcomponents");
                                     resElement.setComponentid(productcomponent.getProductcomponentsPK().getId());
                                     resElement.setComponentName(productcomponent.getComponentname());
                                     resElement.setComponentTypeid(productcomponent.getComponenttype().getComponenttypePK().getId());
                                     resElement.setProductid(productcomponent.getCompanyproduct().getCompanyproductPK().getId());
                                     resElement.setEstimatedcost(productcomponent.getEstimatedcost());
                                     resElement.setActualcost(productcomponent.getActualcost());
                                     resElement.setQuantity(productcomponent.getQuantity());
                                     ////
                                     responseElementList.add(resElement);
                               }
                           }
                           else{
                                    //////////////////
                                    resElement = responseOF.createResponsePageElementsElement();
                                    resElement.setId("productcomponents");
                                    resElement.setElementstatus("FAIL");
                                    resElement.setElementstatusmessage("No product component record found");
                                    ////
                                    responseElementList.add(resElement);
                            }
                           
                       }
                       
                      
                 
                   }
                    
                }
            }
        }catch(Exception ex)
        {
            //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("productcomponents");
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
        }
       return responseElementList;
   }
   

}
