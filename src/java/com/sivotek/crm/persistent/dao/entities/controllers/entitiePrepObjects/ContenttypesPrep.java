/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Contenttypes;
import com.sivotek.crm.persistent.dao.entities.controllers.ContenttypesJpaController;
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
public class ContenttypesPrep {
    
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
     
    public List<Response.Page.Elements.Element> contenttypes(List children)
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
               
               String contenttype = getElementStringValueFromList("contenttype", children);
               String maxsize = getElementStringValueFromList("maxsize", children);
               String minsize = getElementStringValueFromList("minsize", children);
               String description = getElementStringValueFromList("description", children);
               
               Contenttypes contenttypes = new Contenttypes();
               ContenttypesJpaController contenttypesJpaController = new ContenttypesJpaController();
               if(!contenttype.equalsIgnoreCase(""))
               {
                   contenttypes.setContenttype(contenttype);
                   contenttypes.setMaxsize(Integer.parseInt(maxsize));
                   contenttypes.setMinsize(Integer.parseInt(minsize));
                   contenttypes.setDescription(description);
                   try {
                        contenttypesJpaController.create(contenttypes);
                        /////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("contenttypes");
                        resElement.setContenttype(contenttypes.getId());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                        responseElementList.add(resElement);
                    
                   } catch (Exception ex) {
                        
                        resElement.setElementstatus("ERROR");
                        resElement.setElementstatusmessage(ex.getMessage());
                        
                       Logger.getLogger(ContenttypesPrep.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               else{
                   //fitch all content types
                   Collection<Contenttypes> contenttypesColl = contenttypesJpaController.findContenttypesEntities();
                    for(Contenttypes Cont : contenttypesColl)
                    {
                            /////////////////
                            resElement = responseOF.createResponsePageElementsElement();
                            resElement.setId("contenttypes");
                            resElement.setContenttype(Cont.getId());
                            resElement.setName(Cont.getContenttype());
                            resElement.setMaxsize(Cont.getMaxsize());
                            resElement.setMinsize(Cont.getMinsize());
                            resElement.setDescription(Cont.getDescription());
                            resElement.setElementstatus("OK");
                            resElement.setElementstatusmessage("Success");
                            ////
                            responseElementList.add(resElement);

                    }
                   
               }
               
        return responseElementList;
    }

}
