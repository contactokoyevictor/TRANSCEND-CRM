/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyaccountstackcd;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.Contenttypes;
import com.sivotek.crm.persistent.dao.entities.Productattachments;
import com.sivotek.crm.persistent.dao.entities.ProductattachmentsPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.ContenttypesJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.ProductattachmentsJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class ProductattachmentsPrep {
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
     
     public List<Response.Page.Elements.Element> productattachments(List children, int publickey, int companyID)
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
                String productid = getElementStringValueFromList("productid", children);
                String attachment_name = getElementStringValueFromList("attachment_name", children);
                String contenttypeid = getElementStringValueFromList("contenttypeid", children);
                String filename = getElementStringValueFromList("filename", children);
                String description = getElementStringValueFromList("description", children);
                byte[] files = getElementByteValueFromList("files", children);
                
                Companyemployee companyemployee = new Companyemployee();
                Companyproduct companyproduct = new Companyproduct();
                Contenttypes contenttypes = new Contenttypes();
                Productattachments productattachments = new Productattachments();
                ProductattachmentsPK productattachmentsPK = new ProductattachmentsPK();
                productattachmentsPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                productattachmentsPK.setId(Integer.parseInt(p.substring(7)));
                productattachments.setProductattachmentsPK(productattachmentsPK);
                
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
                       if(productid != null && !productid.isEmpty() && Integer.parseInt(productid) >0 
                               && contenttypeid != null && !contenttypeid.isEmpty() && Integer.parseInt(contenttypeid) > 0
                               && filename !=null && !filename.isEmpty()
                               && files != null){
                       //find company product
                       if(company.getCompanyproductCollection().size() > 0)
                       {
                           Collection<Companyproduct> companyproductColl = company.getCompanyproductCollection();
                           for(Companyproduct product : companyproductColl)
                           {
                               if(product.getCompanyproductPK().getId() == Integer.parseInt(productid))
                               {
                                   companyproduct =  product;
                                   break;
                               }
                           }
                       }//
                       
                       //find content type id
                       ContenttypesJpaController contenttypesJpaController = new ContenttypesJpaController();
                       contenttypes = contenttypesJpaController.findContenttypes(Integer.parseInt(contenttypeid));
                       
                       //if both found..
                       if(companyproduct.getCompanyproductPK().getId() > 0 && contenttypes.getId() >0)
                       {
                           productattachments.setCompany(company);
                           productattachments.setCompanyemployee(companyemployee);
                           productattachments.setAttachname(attachment_name);
                           productattachments.setFilename(filename);
                           productattachments.setDescription(description);
                           productattachments.setContenttype(contenttypes);
                           productattachments.setCreateddate(new Date());
                           productattachments.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.ProductattachmentsPrep.class");
                           byte[] buf = null;
                           buf = new byte[contenttypes.getMaxsize()];
                           buf = files;
                           productattachments.setAttachment(buf);
                           ProductattachmentsJpaController productattachmentsJpaController = new ProductattachmentsJpaController();
                           productattachmentsJpaController.create(productattachments);
                           //////////////////
                           resElement = responseOF.createResponsePageElementsElement();
                           resElement.setId("productattachments");
                           resElement.setProductid(productattachments.getProductattachmentsPK().getId());
                           resElement.setElementstatus("OK");
                           resElement.setElementstatusmessage("Success");
                           ////
                           responseElementList.add(resElement);
                       }
                       else{ 
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("productattachments");
                                resElement.setElementstatus("FAIL");
                                resElement.setElementstatusmessage("one of contentypid or productid record not found");
                                ////
                                responseElementList.add(resElement);
                           }
                       
                   }//
                       //load all
                       else if(productid != null && !productid.isEmpty() && Integer.parseInt(productid) == 0 
                               && contenttypeid != null && !contenttypeid.isEmpty() && Integer.parseInt(contenttypeid) == 0
                               && filename !=null && filename.isEmpty())
                       {
                           if(company.getProductattachmentsCollection().size() > 0)
                           {
                              Collection<Productattachments> productattachmentsColl = company.getProductattachmentsCollection();
                              for(Productattachments attachments : productattachmentsColl)
                              {
                                  //////////////////
                                   resElement = responseOF.createResponsePageElementsElement();
                                   resElement.setId("productattachments");
                                   resElement.setProductid(attachments.getProductattachmentsPK().getId());
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
                                resElement.setId("productattachments");
                                resElement.setElementstatus("FAIL");
                                resElement.setElementstatusmessage("No product attachment record found");
                                ////
                                responseElementList.add(resElement);
                            }
                       }
                   }
                  else{ 
                                //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("productattachments");
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
            resElement.setId("productattachments");
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
        }
        return responseElementList;
         
     }
}
