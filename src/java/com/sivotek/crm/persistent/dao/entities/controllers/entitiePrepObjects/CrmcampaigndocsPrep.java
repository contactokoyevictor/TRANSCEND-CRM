/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Contenttypes;
import com.sivotek.crm.persistent.dao.entities.Crmcampaign;
import com.sivotek.crm.persistent.dao.entities.CrmcampaignPK;
import com.sivotek.crm.persistent.dao.entities.Crmcampaigndocs;
import com.sivotek.crm.persistent.dao.entities.CrmcampaigndocsPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.ContenttypesJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmcampaignJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmcampaigndocsJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author okoyevictor
 */
public class CrmcampaigndocsPrep {
    
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
    
    private XMLGregorianCalendar getElementDateValueFromList(String elementName, List elementList) {
       for (Object elementList1 : elementList) {
           JAXBElement e = (JAXBElement) elementList1;
           if (e.getName().getLocalPart().equals(elementName)) {
               return (XMLGregorianCalendar)e.getValue();
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
    ///
   public List<Response.Page.Elements.Element> crmcampaigndoc(List children, int publickey, int companyID)
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
                Companyemployee companyemployee = new Companyemployee();
                
                String employeeid = getElementStringValueFromList("employeeid", children);
                String campaignid = getElementStringValueFromList("campaignid", children);
                
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
                
                 //if employee found
                 if(companyemployee.getCompanyemployeePK().getId() > 0 && campaignid != null && Integer.parseInt(campaignid) > 0)
                 {
                    
                    Crmcampaign crmcampaign = new Crmcampaign();
                    //check campaign id from company campaign collection
                    if(company.getCrmcampaignCollection().size() > 0)
                    {
                        Collection<Crmcampaign> crmcampaignColl = company.getCrmcampaignCollection();
                        for(Crmcampaign campaign : crmcampaignColl)
                        {
                            if(campaign.getCrmcampaignPK().getCampaignid() == Integer.parseInt(campaignid))
                            {
                                crmcampaign = campaign;
                                break;
                            }
                        }
                    }

                   //if campaign found from company collection
                   if(crmcampaign.getCrmcampaignPK().getCampaignid() > 0)
                    {
                        String name = getElementStringValueFromList("name", children);
                        byte[] file = getElementByteValueFromList("files", children);
                        String contenttypeid = getElementStringValueFromList("contenttype", children);
                        
                     if(file !=null && file.length > 0 && name !=null && !name.isEmpty() && contenttypeid !=null && Integer.parseInt(contenttypeid) != 0)
                     {   
                        ContenttypesJpaController contenttypesJpaController = new ContenttypesJpaController();
                        Contenttypes contenttypes = new Contenttypes();
                        contenttypes = contenttypesJpaController.findContenttypes(Integer.parseInt(contenttypeid));
                        
                        Crmcampaigndocs crmcampaigndocs = new Crmcampaigndocs();
                        CrmcampaigndocsPK crmcampaigndocsPK = new CrmcampaigndocsPK();
                        long bint = System.currentTimeMillis();
                        String p = ""+bint;
                        crmcampaigndocsPK.setDocid(Integer.parseInt(p.substring(7)));
                        crmcampaigndocsPK.setPubkey(publickey);

                        crmcampaigndocs.setCrmcampaigndocsPK(crmcampaigndocsPK);
                        crmcampaigndocs.setName(name);
                        crmcampaigndocs.setCrmcampaign(crmcampaign);
                        crmcampaigndocs.setCompanyemployee(crmcampaign.getCompanyemployee());
                        crmcampaigndocs.setCompanyemployee(companyemployee);
                        byte[] buf = null;
                        if(contenttypes.getId() > 0){
                        //byte[] buf = new byte[1048576];
                         buf = new byte[contenttypes.getMaxsize()];
                         buf = file;
                        }

    //                    BufferedOutputStream bs = null;
    //                    String filename = name;
    //                    
    //                    FileOutputStream fs = new FileOutputStream(new File(filename));
    //                    bs = new BufferedOutputStream(fs);
    //                    bs.write(buf);
    //                    bs.close();
    //                    bs = null;

                        crmcampaigndocs.setFiles(buf);
                        crmcampaigndocs.setCreateddate(new Date());
                        crmcampaigndocs.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmcampaigndocsPrep.class");
                        CrmcampaigndocsJpaController crmcampaigndocsJpaController = new CrmcampaigndocsJpaController();

                        crmcampaigndocsJpaController.create(crmcampaigndocs);
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("crmcampaigndocs");
                        resElement.setCampaignid(crmcampaigndocs.getCrmcampaigndocsPK().getDocid());
                        resElement.setElementstatus("OK");
                        resElement.setElementstatusmessage("Success");
                        ////
                        responseElementList.add(resElement);

                    }   
//                     else if(file == null && name !=null && name.isEmpty() && contenttypeid !=null && Integer.parseInt(contenttypeid) == 0)
//                     {
//                     }
                }
                     
              }
                 else if(campaignid != null && Integer.parseInt(campaignid) == 0)
                 {
                     
                         if(company.getCrmcampaignCollection().size() > 0)
                         {
                             Collection<Crmcampaign> crmcampaignColl = company.getCrmcampaignCollection();
                             for(Crmcampaign campaign : crmcampaignColl)
                             {
                                 if(campaign.getCrmcampaigndocsCollection().size() > 0)
                                 {
                                     Collection<Crmcampaigndocs> crmcampaigndocsColl = campaign.getCrmcampaigndocsCollection();
                                     for(Crmcampaigndocs crmcampaigndocs : crmcampaigndocsColl)
                                     {
                                         //////////////////
                                        resElement = responseOF.createResponsePageElementsElement();
                                        resElement.setId("crmcampaigndocs");
                                        resElement.setCampaignid(crmcampaigndocs.getCrmcampaigndocsPK().getDocid());
                                        resElement.setName(crmcampaigndocs.getName());
                                        resElement.setFiles(crmcampaigndocs.getFiles());
                                        ////
                                        responseElementList.add(resElement);
                                     }
                                 }
                                 else{
                                    //////////////////
                                    resElement = responseOF.createResponsePageElementsElement();
                                    resElement.setId("crmcampaigndocs");
                                    resElement.setElementstatus("FAIL");
                                    resElement.setElementstatusmessage("No crmcampaigndocs record found");
                                    ////
                                    responseElementList.add(resElement);
                                 }
                             }
                         }
                         else{
                                    //////////////////
                                    resElement = responseOF.createResponsePageElementsElement();
                                    resElement.setId("crmcampaigndocs");
                                    resElement.setElementstatus("FAIL");
                                    resElement.setElementstatusmessage("No crmcampaigndocs record found");
                                    ////
                                    responseElementList.add(resElement);
                                 }
                 }
                else{
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("crmcampaigndocs");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("No employee record found");
                        ////
                        responseElementList.add(resElement);
                     }
                
                
            }
            else{
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("crmcampaigndocs");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("No company record found");
                        ////
                        responseElementList.add(resElement);
                     }
           }catch(Exception ex)
           {
               System.out.println("Error :"+ ex.getCause().getMessage());
               //////////////////
                resElement = responseOF.createResponsePageElementsElement();
                resElement.setId("crmcampaigndocs");
                resElement.setElementstatus("ERROR");
                resElement.setElementstatusmessage(ex.getMessage());
                ////
                responseElementList.add(resElement);
           }
        
       
       return responseElementList;
   }
   
   
//   public void crmcampaigndocs(List children, int publickey, int companyID){
//               Company company = new Company();
//               CompanyPK companyPK = new CompanyPK();
//               companyPK.setCompanyid(companyID);
//               companyPK.setPubkey(publickey);
//                 
//             CompanyJpaController companyJpaController = new CompanyJpaController();
//          try{
//               company = companyJpaController.findCompany(companyPK);
//               
//            if(company.getCompanyPK().getCompanyid() > 0){
//                 
//                String campaignid = getElementStringValueFromList("campaignid", children);
//                
//                CrmcampaignPK crmcampaignPK = new CrmcampaignPK();
//                crmcampaignPK.setCampaignid(Integer.parseInt(campaignid));
//                crmcampaignPK.setPubkey(publickey);
//                Crmcampaign crmcampaign = new Crmcampaign();
//                CrmcampaignJpaController crmcampaignJpaController = new CrmcampaignJpaController();
//                crmcampaign = crmcampaignJpaController.findCrmcampaign(crmcampaignPK);
//                
//             if(crmcampaign.getCrmcampaignPK().getCampaignid() > 0)
//                {
//                    String name = getElementStringValueFromList("name", children);
//                    String file = getElementStringValueFromList("files", children);
//                    byte[] file2 = getElementByteValueFromList("files", children);
//                    
//                    String contenttypeid = getElementStringValueFromList("contenttype", children);
//                    
//                    ContenttypesJpaController contenttypesJpaController = new ContenttypesJpaController();
//                    Contenttypes contenttypes = new Contenttypes();
//                    contenttypes = contenttypesJpaController.findContenttypes(Integer.parseInt(contenttypeid));
//                    
//                    
//                    Crmcampaigndocs crmcampaigndocs = new Crmcampaigndocs();
//                    CrmcampaigndocsPK crmcampaigndocsPK = new CrmcampaigndocsPK();
//                    long bint = System.currentTimeMillis();
//                    String p = ""+bint;
//                    crmcampaigndocsPK.setDocid(Integer.parseInt(p.substring(7)));
//                    crmcampaigndocsPK.setPubkey(publickey);
//                    
//                    crmcampaigndocs.setCrmcampaigndocsPK(crmcampaigndocsPK);
//                    crmcampaigndocs.setName(name);
//                    crmcampaigndocs.setCrmcampaign(crmcampaign);
//                    crmcampaigndocs.setCompanyemployee(crmcampaign.getCompanyemployee());
//                    byte[] buf = null;
//                    if(contenttypes.getId() > 0){
//                    //byte[] buf = new byte[1048576];
//                     buf = new byte[contenttypes.getMaxsize()];
//                     buf = file2;
//                    }
//                    
////                    BufferedOutputStream bs = null;
////                    String filename = name;
////                    
////                    FileOutputStream fs = new FileOutputStream(new File(filename));
////                    bs = new BufferedOutputStream(fs);
////                    bs.write(buf);
////                    bs.close();
////                    bs = null;
//                    
//                    crmcampaigndocs.setFiles(buf);
//                    crmcampaigndocs.setCreateddate(new Date());
//                    crmcampaigndocs.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmcampaigndocsPrep.class");
//                    CrmcampaigndocsJpaController crmcampaigndocsJpaController = new CrmcampaigndocsJpaController();
//                
//                    crmcampaigndocsJpaController.create(crmcampaigndocs);
//                    this.setId(crmcampaigndocs.getCrmcampaigndocsPK().getDocid());
//                    this.setStatus("OK");
//                    this.setStatusmessage("Success");
//                    
//                }
//              }  
//       
//           }catch(Exception ex)
//           {
//               
//                System.out.println("Exception here :"+ex.getMessage());
//                this.setStatus("ERROR");
//                this.setStatusmessage(ex.getMessage());
//           }
//     
//} 
         
}
