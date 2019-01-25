/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects;

import com.sivotek.crm.persistent.dao.entities.Crmmodules;
import com.sivotek.crm.persistent.dao.entities.CrmmodulesPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmmodulesJpaController;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CrmmodulesPrep {
private String status = "";
   private String statusmessage = "";
   private int moduleid = 0;
   

   //getters and setters
   public int getModuleid() 
   {return moduleid;}
   public void setModuleid(int moduleid) 
   {this.moduleid = moduleid;}
   
   public String getStatus() 
   {return status;}
   public void setStatus(String status) 
   {this.status = status;}
 
   public String getStatusmessage() 
   {return statusmessage;}
   public void setStatusmessage(String statusmessage) 
   {this.statusmessage = statusmessage;}
   
     private String getElementStringValueFromList(String elementName, List elementList) {
        for (int i = 0; i < elementList.size(); i++) {
            JAXBElement e = (JAXBElement) elementList.get(i);
            if (e.getName().getLocalPart().equals(elementName)) {
                return e.getValue().toString();
            }
        }
        return null;
    }
     
   ///
   public void companycontactsaddress(List children, int publickey, int companyID){
           String name = getElementStringValueFromList("name", children);
           String moduleurl = getElementStringValueFromList("moduleurl", children);
           String description = getElementStringValueFromList("description", children);
                      
           CrmmodulesJpaController crmmodulesJpaController = new CrmmodulesJpaController();
           Crmmodules crmmodules = new Crmmodules();
           CrmmodulesPK crmmodulesPK = new CrmmodulesPK();
           
           crmmodulesPK.setPubkey(publickey);
           long bint = System.currentTimeMillis();
           String p = ""+bint;
           crmmodulesPK.setModuleid(Integer.parseInt(p.substring(7)));
           crmmodules.setCrmmodulesPK(crmmodulesPK);
           crmmodules.setModuleName(name);
           crmmodules.setModuleUrl(moduleurl);
           crmmodules.setDescription(description);
           crmmodules.setCreateddate(new Date());
           crmmodules.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmmodulesPrep.class");
           try{
           crmmodulesJpaController.create(crmmodules);
           this.setModuleid(crmmodules.getCrmmodulesPK().getModuleid());
           this.setStatus("OK");
           this.setStatusmessage("Success");
           }catch(Exception ex){
               this.setStatus("ERROR");
               this.setStatusmessage(ex.getMessage());
           
           }
   }
}
