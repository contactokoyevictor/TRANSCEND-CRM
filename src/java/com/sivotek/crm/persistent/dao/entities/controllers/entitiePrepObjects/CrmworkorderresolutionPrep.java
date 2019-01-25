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
import com.sivotek.crm.persistent.dao.entities.Crmbillingtype;
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.Crmworkorderresolution;
import com.sivotek.crm.persistent.dao.entities.CrmworkorderresolutionPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmworkorderresolutionJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CrmworkorderresolutionPrep {
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
    
    public List<Response.Page.Elements.Element> crmworkorderresolutions(List children, int publickey, int companyID)
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
                String billtypeid = getElementStringValueFromList("billtypeid", children);
                String workperformed = getElementStringValueFromList("workperformed", children);
                String specialinstruction = getElementStringValueFromList("specialinstruction", children);
                String customerfailure = getElementStringValueFromList("customerfailure", children);
                String correctiveaction = getElementStringValueFromList("correctiveaction", children);
                String failurelocation = getElementStringValueFromList("failurelocation", children);
                String failedassembly = getElementStringValueFromList("failedassembly", children);
                String howfixed = getElementStringValueFromList("howfixed", children);
                String symptom = getElementStringValueFromList("symptom", children);
                String rootcause = getElementStringValueFromList("rootcause", children);
                
                Companyemployee companyemployee = new Companyemployee();
                
                Crmworkorder crmworkorder = new Crmworkorder();
                Crmbillingtype crmbillingtype = new Crmbillingtype();
                
                Crmworkorderresolution crmworkorderresolution = new Crmworkorderresolution();
                CrmworkorderresolutionPK crmworkorderresolutionPK = new CrmworkorderresolutionPK();
                crmworkorderresolutionPK.setPubkey(publickey);
                long bint = System.currentTimeMillis();
                String p = ""+bint;
                crmworkorderresolutionPK.setId(Integer.parseInt(p.substring(7)));
                crmworkorderresolution.setCrmworkorderresolutionPK(crmworkorderresolutionPK);
                
                //check for employee from company employee collection
                if(company.getCompanyemployeeCollection().size() > 0)
                {
                    Collection<Companyemployee> companyemployeeColl = company.getCompanyemployeeCollection();
                    for(Companyemployee employee : companyemployeeColl)
                    {
                        if(employee.getCompanyemployeePK().getId() == Integer.parseInt(employeeid))
                        {
                            companyemployee = employee;
                            crmworkorderresolution.setCompanyemployee(companyemployee);
                            break;
                        }
                    }
                    
                    if(companyemployee.getCompanyemployeePK().getId() > 0)
                    {
                       if(workorderid != null && !workorderid.equalsIgnoreCase("0") && !workorderid.isEmpty())
                       {
                           Collection<Crmworkorder> crmworkorderColl = company.getCrmworkorderCollection();
                           for(Crmworkorder workorder : crmworkorderColl)
                           {
                               if(workorder.getCrmworkorderPK().getId() == Integer.parseInt(workorderid))
                               {
                                   crmworkorder = workorder;
                                   crmworkorderresolution.setCrmworkorder(crmworkorder);
                                   break;
                               }
                           }//
                           
                           //check billing type from company billing type collection
                          if(billtypeid != null && !billtypeid.equalsIgnoreCase("0") && !billtypeid.isEmpty())
                          {
                              if(company.getCrmbillingtypeCollection().size() > 0)
                              {
                                  Collection<Crmbillingtype> crmbillingtypeColl = company.getCrmbillingtypeCollection();
                                  for(Crmbillingtype billingtype : crmbillingtypeColl)
                                  {
                                      crmbillingtype = billingtype;
                                      crmworkorderresolution.setCrmbillingtype(crmbillingtype);
                                      break;
                                  }
                              }
                          }
                         
                            crmworkorderresolution.setWorkperformed(workperformed);
                            crmworkorderresolution.setSpecialinstruction(specialinstruction);
                            crmworkorderresolution.setCustomersfailure(customerfailure);
                            crmworkorderresolution.setCorrectiveaction(correctiveaction);
                            crmworkorderresolution.setFailurelocation(failurelocation);
                            crmworkorderresolution.setFailedassembly(failedassembly);
                            crmworkorderresolution.setHowfixed(howfixed);
                            crmworkorderresolution.setSymptom(symptom);
                            crmworkorderresolution.setRootcause(rootcause);
                            crmworkorderresolution.setCompany(company);
                            crmworkorderresolution.setCreateddate(new Date());
                            crmworkorderresolution.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmworkorderresolutionPrep.class");
                            CrmworkorderresolutionJpaController crmworkorderresolutionJpaController = new CrmworkorderresolutionJpaController();
                            crmworkorderresolutionJpaController.create(crmworkorderresolution);
                            //////////////////
                            resElement = responseOF.createResponsePageElementsElement();
                            resElement.setId("crmworkorderresolution");
                            resElement.setWorkorderinstructionid(crmworkorderresolution.getCrmworkorderresolutionPK().getId());
                            resElement.setElementstatus("OK");
                            resElement.setElementstatusmessage("Success");
                            ////
                            responseElementList.add(resElement);
                       } 
                       else if(workorderid != null && workorderid.equalsIgnoreCase("0"))
                       {
                           Collection<Crmworkorderresolution> crmworkorderresolutionColl = company.getCrmworkorderresolutionCollection();
                           for(Crmworkorderresolution resolution : crmworkorderresolutionColl)
                           {
                               //////////////////
                                resElement = responseOF.createResponsePageElementsElement();
                                resElement.setId("crmworkorderresolution");//
                                resElement.setWorkorderresolutionid(resolution.getCrmworkorderresolutionPK().getId());
                                resElement.setWorkperformed(resolution.getWorkperformed());
                                resElement.setSpecialinstruction(resolution.getSpecialinstruction());
                                resElement.setCustomerfailure(resolution.getCustomersfailure());
                                resElement.setCorrectiveaction(resolution.getCorrectiveaction());
                                resElement.setFailurelocation(resolution.getFailurelocation());
                                resElement.setFailedassembly(resolution.getFailedassembly());
                                resElement.setHowfixed(resolution.getHowfixed());
                                resElement.setSymptom(resolution.getSymptom());
                                resElement.setRootcause(resolution.getRootcause());
                                responseElementList.add(resElement);
                           }//
                       }
                }
                   else{
                        //////////////////
                        resElement = responseOF.createResponsePageElementsElement();
                        resElement.setId("crmworkorderresolution");
                        resElement.setElementstatus("FAIL");
                        resElement.setElementstatusmessage("Invalid Employee ID");
                        ////
                        responseElementList.add(resElement);
                     }  
                    
                } 
            }else{
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("crmworkorderresolution");
                    resElement.setElementstatus("FAIL");
                    resElement.setElementstatusmessage("Invalid Company details");
                    ////
                    responseElementList.add(resElement);
            }
         }catch(Exception ex)
         {
             
         }
        return responseElementList;
    }

}
