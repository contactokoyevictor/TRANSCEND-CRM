/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.CompanyPK;
import com.sivotek.crm.persistent.dao.entities.TimeZones;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.TimeZonesJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class TimeZonesPrep {
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
     
   public List<Response.Page.Elements.Element> TimeZones(List children, int publickey, int companyID) 
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
    try
    {
        company = companyJpaController.findCompany(companyPK);
        if(company.getCompanyPK().getCompanyid() > 0)
        {
           String timeZoneid = getElementStringValueFromList("timeZoneid", children);
           if(timeZoneid.equalsIgnoreCase("0"))
           {
               
              TimeZonesJpaController timeZonesJpaController = new TimeZonesJpaController(); 
              //System.out.println("Counts :"+timeZonesJpaController.findTimeZonesEntities().size());
              Collection<TimeZones> timeZonesColl = timeZonesJpaController.findTimeZonesEntities();
              for(TimeZones timeZone : timeZonesColl)
              {
                  //////////////////
                 resElement = responseOF.createResponsePageElementsElement();
                 resElement.setId("timeZones");
                 resElement.setTimeZoneid(timeZone.getTimezoneid());
                 resElement.setCode(timeZone.getCountryCode());
                 resElement.setCoordinates(timeZone.getCoordinates());
                 resElement.setTimezone(timeZone.getTimeZone());
                 resElement.setComments(timeZone.getComments());
                 resElement.setUTCoffset(timeZone.getUTCoffset());
                 resElement.setNotes(timeZone.getNotes());
                 ////
                 responseElementList.add(resElement);
              }
           }
           else{
               int zoneid = Integer.parseInt(timeZoneid);
               if(zoneid > 0){
                   TimeZones timeZones = new TimeZones();
                   TimeZonesJpaController timeZonesJpaController = new TimeZonesJpaController(); 
                   timeZones = timeZonesJpaController.findTimeZones(zoneid);
                   if(timeZones.getTimezoneid() > 0)
                   {
                    //////////////////
                    resElement = responseOF.createResponsePageElementsElement();
                    resElement.setId("timeZone");
                    resElement.setTimeZoneid(timeZones.getTimezoneid());
                    resElement.setCode(timeZones.getCountryCode());
                    resElement.setCoordinates(timeZones.getCoordinates());
                    resElement.setTimezone(timeZones.getTimeZone());
                    resElement.setComments(timeZones.getComments());
                    resElement.setUTCoffset(timeZones.getUTCoffset());
                    resElement.setNotes(timeZones.getNotes());
                    ////
                    responseElementList.add(resElement);
                   }
               }
           }
                
        }
    }catch(Exception ex)
    {
        
    }
              
       return responseElementList;
   }
     
}
