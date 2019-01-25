/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Category;
import com.sivotek.crm.persistent.dao.entities.controllers.CategoryJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CategoryPrep {
   private Response response;
   private String status = "";
   private String statusmessage = "";
   private int companyID = 0;
   

   //getters and setters
   public int getCompanyID() 
   {return companyID;}
   public void setCompanyID(int companyID) 
   {this.companyID = companyID;}
   
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
    public List<Response.Page.Elements.Element> categoryPrep(List children)
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
               
        
        String name = getElementStringValueFromList("name", children);
        String description = getElementStringValueFromList("description", children);
        Category category = new Category();
        CategoryJpaController categoryJpaController = new CategoryJpaController();
        //System.out.println("we do dey here:");
        if(!name.equalsIgnoreCase("")){
            category.setName(name);
            category.setDescription(description);
           try {
                  categoryJpaController.create(category);
                    /////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companycategory");
                    resElement.setCategoryid(category.getId());
                    resElement.setName(category.getName());
                    resElement.setDescription(category.getDescription());
                    resElement.setElementstatus("OK");
                    resElement.setElementstatusmessage("Success");
                    ////
                    responseElementList.add(resElement);
                    
                
            } catch (Exception ex) {
            Logger.getLogger(CategoryPrep.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else{
            Collection<Category> categoryColl = categoryJpaController.findCategoryEntities();
            for(Category Cate : categoryColl)
            {
                    /////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("companycategory");
                    resElement.setCategoryid(Cate.getId());
                    resElement.setName(Cate.getName());
                    resElement.setDescription(Cate.getDescription());
                    resElement.setElementstatus("OK");
                    resElement.setElementstatusmessage("Success");
                    ////
                    responseElementList.add(resElement);
                
            }
        }
        
        return responseElementList;
    }
     
}
