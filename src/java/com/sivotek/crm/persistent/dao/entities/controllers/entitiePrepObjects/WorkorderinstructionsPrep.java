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
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.CrmworkorderPK;
import com.sivotek.crm.persistent.dao.entities.Workorderinstructions;
import com.sivotek.crm.persistent.dao.entities.WorkorderinstructionsPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.WorkorderinstructionsJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class WorkorderinstructionsPrep {
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
     
    public List<Response.Page.Elements.Element> workorderinstructions(List children, int publickey, int companyID)
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
                String workorderid = getElementStringValueFromList("workorderid", children);
                String instruction = getElementStringValueFromList("instruction", children);
                String comment = getElementStringValueFromList("comment", children);
                String isDone = getElementStringValueFromList("isDone", children);
                
                Companyemployee companyemployee = new Companyemployee();
                CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
                CompanyemployeePK companyemployeePK = new CompanyemployeePK();
                companyemployeePK.setPubkey(publickey);
                companyemployeePK.setId(Integer.parseInt(employeeid));
                companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);
                
                if(companyemployee.getCompanyemployeePK().getId() > 0)
                {
                   if(workorderid != null && !workorderid.equalsIgnoreCase("0") && !workorderid.isEmpty())
                   {
                       Crmworkorder crmworkorder = new Crmworkorder();
                       
                       if(company.getCrmworkorderCollection().size() > 0)
                       {
                           Collection<Crmworkorder> crmworkorderColl = company.getCrmworkorderCollection();
                           for(Crmworkorder workorder : crmworkorderColl)
                           {
                               if(workorder.getCrmworkorderPK().getId() == Integer.parseInt(workorderid))
                               {
                                   crmworkorder = workorder;
                                   break;
                               }
                           }
                           
                          if(crmworkorder.getCrmworkorderPK().getId() > 0)
                          {
                            Workorderinstructions workorderinstructions = new Workorderinstructions();   
                            WorkorderinstructionsPK workorderinstructionsPK = new WorkorderinstructionsPK();   
                            workorderinstructionsPK.setPubkey(publickey);
                            long bint = System.currentTimeMillis();
                            String p = ""+bint;
                            workorderinstructionsPK.setId(Integer.parseInt(p.substring(7)));
                            workorderinstructions.setWorkorderinstructionsPK(workorderinstructionsPK);
                            workorderinstructions.setCrmworkorder(crmworkorder);
                            workorderinstructions.setCompanyemployee(companyemployee);
                            workorderinstructions.setIsDone(Boolean.parseBoolean(isDone));
                            workorderinstructions.setInstruction(instruction);
                            workorderinstructions.setComment(comment);
                            workorderinstructions.setCompanyemployee(companyemployee);
                            workorderinstructions.setCreateddate(new Date());
                            workorderinstructions.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.WorkorderinstructionsPrep.class");
                            WorkorderinstructionsJpaController workorderinstructionsJpaController = new WorkorderinstructionsJpaController();
                            
                            workorderinstructionsJpaController.create(workorderinstructions);
                            //////////////////
                            resElement = responseOF.createResponsePageElementsElement();
                            resElement.setId("workorderinstructions");
                            resElement.setWorkorderinstructionid(workorderinstructions.getWorkorderinstructionsPK().getId());
                            resElement.setElementstatus("OK");
                            resElement.setElementstatusmessage("Success");
                            ////
                           responseElementList.add(resElement);
                           }
                       }
                   }
                   else if(workorderid != null && workorderid.equalsIgnoreCase("0"))
                   {  
                         
                       if(company.getCrmworkorderCollection().size() > 0)
                       {
                           Collection<Crmworkorder> crmworkorderColl = company.getCrmworkorderCollection();
                           for(Crmworkorder crmworkorder : crmworkorderColl)
                           {
                            
                            Collection<Workorderinstructions> instructionsColl = crmworkorder.getWorkorderinstructionsCollection();
                            for(Workorderinstructions instructs : instructionsColl)
                            {
                                 //////////////////
                                 resElement = responseOF.createResponsePageElementsElement();
                                 resElement.setId("workorderinstructions");
                                 resElement.setEmployeeid(crmworkorder.getCompanyemployee().getCompanyemployeePK().getId());
                                 resElement.setWorkorderid(crmworkorder.getCrmworkorderPK().getId());

                                 resElement.setWorkorderinstructionid(instructs.getWorkorderinstructionsPK().getId());
                                 resElement.setInstruction(instructs.getInstruction());
                                 resElement.setComment(instructs.getComment());
                                 resElement.setIsDone(instructs.getIsDone());
                                 resElement.setStartdatetime(instructs.getChangeddate());
                                 responseElementList.add(resElement);
                            }
                            
//                            resElement.setElementstatus("OK");
//                            resElement.setElementstatusmessage("Success");
//                            ////
                           
                           }
                       }
                   }
                }
                 else{
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("workorderinstructions");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("Invalid Employee ID");
                        ////
                        responseElementList.add(resElement);
                     }
            }
            else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("workorderinstructions");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
        }catch(Exception ex)
        {
            //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("workorderinstructions");
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(ex.getMessage());
            ////
            responseElementList.add(resElement);
        }
        
        return responseElementList;
    }
}
