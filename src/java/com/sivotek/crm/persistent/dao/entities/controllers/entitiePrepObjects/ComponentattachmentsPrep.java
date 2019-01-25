/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */
package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Componentattachments;
import com.sivotek.crm.persistent.dao.entities.ComponentattachmentsPK;
import com.sivotek.crm.persistent.dao.entities.Contenttypes;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.ComponentattachmentsJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.ContenttypesJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
/**
 *
 * @author okoyevictor
 */
public class ComponentattachmentsPrep {
   
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
     
     private byte[] getElementByteValueFromList(String elementName, List elementList) {
       for (Object elementList1 : elementList) {
           JAXBElement e = (JAXBElement) elementList1;
           if (e.getName().getLocalPart().equals(elementName)) {
               return (byte[])e.getValue();
           }
       }
        return null;
    }
     
     public List<Response.Page.Elements.Element> componentattachments(List children, int publickey, int companyID)
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
                String componentid = getElementStringValueFromList("componentid", children);
                String attachment_name = getElementStringValueFromList("attachment_name", children);
                String contenttypeid = getElementStringValueFromList("contenttypeid", children);
                String filename = getElementStringValueFromList("filename", children);
                String description = getElementStringValueFromList("description", children);
                byte[] files = getElementByteValueFromList("files", children);
                
                Companyemployee companyemployee = new Companyemployee();
                Contenttypes contenttypes = new Contenttypes();
                Componentattachments componentattachments = new Componentattachments();
                ComponentattachmentsPK componentattachmentsPK = new ComponentattachmentsPK();
                componentattachmentsPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                componentattachmentsPK.setId(Integer.parseInt(p.substring(7)));
                componentattachments.setComponentattachmentsPK(componentattachmentsPK);
                
                Productcomponents productcomponents = new Productcomponents();
                
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
                }
                  //if employee were found
                  if(companyemployee.getCompanyemployeePK().getId() > 0)
                   {
                       if(componentid != null && !componentid.isEmpty() && Integer.parseInt(componentid) >0 
                               && contenttypeid != null && !contenttypeid.isEmpty() && Integer.parseInt(contenttypeid) > 0
                               && filename !=null && !filename.isEmpty()
                               && files != null){
                       //find company product component
                       if(company.getProductcomponentsCollection().size() > 0)
                       {
                           Collection<Productcomponents> productcomponentsColl = company.getProductcomponentsCollection();
                           for(Productcomponents component : productcomponentsColl)
                           {
                               if(component.getProductcomponentsPK().getId() == Integer.parseInt(componentid))
                               {
                                   productcomponents =  component;
                                   break;
                               }
                           }
                       }//
                       
                       //find content type id
                       ContenttypesJpaController contenttypesJpaController = new ContenttypesJpaController();
                       contenttypes = contenttypesJpaController.findContenttypes(Integer.parseInt(contenttypeid));
                       
                       //if both found..
                       if(productcomponents.getProductcomponentsPK().getId() > 0 && contenttypes.getId() >0)
                       {
                           componentattachments.setCompany(company);
                           componentattachments.setCompanyemployee(companyemployee);
                           componentattachments.setProductcomponents(productcomponents);
                           componentattachments.setAttachname(attachment_name);
                           componentattachments.setFilename(filename);
                           componentattachments.setDescription(description);
                           componentattachments.setContenttype(contenttypes);
                           componentattachments.setCreateddate(new Date());
                           componentattachments.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.ProductattachmentsPrep.class");
                           byte[] buf = null;
                           buf = new byte[contenttypes.getMaxsize()];
                           buf = files;
                           componentattachments.setAttachment(buf);
                           
                           ComponentattachmentsJpaController componentattachmentsJpaController = new ComponentattachmentsJpaController();
                           componentattachmentsJpaController.create(componentattachments);
                           //////////////////
                           resElement = responseOF.createResponsePageElementsElement();
                           resElement.setId("componentattachments");
                           resElement.setProductid(componentattachments.getComponentattachmentsPK().getId());
                           resElement.setElementstatus("OK");
                           resElement.setElementstatusmessage("Success");
                           ////
                           responseElementList.add(resElement);
                       }
                       else{ 
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("componentattachments");
                                resElement.setElementstatus("FAIL");
                                resElement.setElementstatusmessage("one of contentypid or productid record not found");
                                ////
                                responseElementList.add(resElement);
                           }
                       
                   }//
                       //load all
                       else if(componentid != null && !componentid.isEmpty() && Integer.parseInt(componentid) == 0 
                               && contenttypeid != null && !contenttypeid.isEmpty() && Integer.parseInt(contenttypeid) == 0
                               && filename !=null && filename.isEmpty())
                       {
                           if(company.getComponentattachmentsCollection().size() > 0)
                           {
                              Collection<Componentattachments> componentattachmentsColl = company.getComponentattachmentsCollection();
                              for(Componentattachments attachments : componentattachmentsColl)
                              {
                                   //////////////////
                                   resElement = responseOF.createResponsePageElementsElement();
                                   resElement.setId("componentattachments");
                                   resElement.setProductid(attachments.getComponentattachmentsPK().getId());
                                   resElement.setAttachmentName(attachments.getAttachname());
                                   resElement.setContenttypeid(attachments.getContenttype().getId());
                                   resElement.setAttachment(attachments.getAttachment());
                                   resElement.setFilename(attachments.getFilename());
                                   resElement.setDescription(attachments.getDescription());
                                   ////
                                   responseElementList.add(resElement);
                              }
                           }
                           else{ 
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("componentattachments");
                                resElement.setElementstatus("FAIL");
                                resElement.setElementstatusmessage("No component attachment record found");
                                ////
                                responseElementList.add(resElement);
                            }
                           
                       }
                       
                       
                   }
                  else{ 
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("componentattachments");
                                resElement.setElementstatus("FAIL");
                                resElement.setElementstatusmessage("No employee record found");
                                ////
                                responseElementList.add(resElement);
                       }
            }
        }catch(Exception ex)
        {
             //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("componentattachments");
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
        }
        return responseElementList; 
     }

}
