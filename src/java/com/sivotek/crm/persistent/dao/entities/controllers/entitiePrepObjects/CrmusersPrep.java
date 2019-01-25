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
import com.sivotek.crm.persistent.dao.entities.Crmusers;
import com.sivotek.crm.persistent.dao.entities.CrmusersPK;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CompanyemployeeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmusersJpaController;
import com.sivotek.crm.security.Encryptor;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author okoyevictor
 */
public class CrmusersPrep {
   private Response response;
   private String status = "";
   private String statusmessage = "";
   private int id = 0;
   
   private String username = "";
   private String Passwd = "";
   private Boolean isAdmin;
   private int appkey; 
   private int companyid;
   private int employeeid;

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
    
    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPasswd() {return Passwd;}

    public void setPasswd(String Passwd) {this.Passwd = Passwd;}

    public Boolean getIsAdmin() {return isAdmin;}

    public void setIsAdmin(Boolean isAdmin) {this.isAdmin = isAdmin;}
     
    public int getAppkey() {return appkey;}

    public void setAppkey(int appkey) {this.appkey = appkey;}

    public int getCompanyid() {return companyid;}

    public void setCompanyid(int companyid) {this.companyid = companyid;}

    public int getEmployeeid() {return employeeid;}

    public void setEmployeeid(int employeeid) {this.employeeid = employeeid;}
   
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
   public void rootaccount(List children, int publickey, int companyID){
           Company company = new Company();
           CompanyPK companyPK = new CompanyPK();
           companyPK.setCompanyid(companyID);
           companyPK.setPubkey(publickey);
           CompanyJpaController companyJpaController = new CompanyJpaController();
         try{
           company = companyJpaController.findCompany(companyPK);
         if(company.getCompanyPK().getCompanyid() > 0){
           String userid = getElementStringValueFromList("userid", children);
           String password = getElementStringValueFromList("password", children);
           String isadmin = getElementStringValueFromList("isadmin", children);
           String employeeid = getElementStringValueFromList("employeeid", children);
           
           if(userid.equalsIgnoreCase("") && password.equalsIgnoreCase(""))
           {
             Collection<Crmusers> crmusersColl =  company.getCrmusersCollection();
             for(Crmusers user : crmusersColl)
             {
//                 if(user.getEmployeeid() == Integer.parseInt(employeeid)){
                   this.setUsername(user.getCrmuser());
                   this.setPasswd(user.getPasswd());
                   this.setIsAdmin(user.getIsAdmin());
                   this.setId(user.getCrmusersPK().getId());
                   this.setStatus("load");
//                 }
             }
           }
           else{
           Crmusers crmusers = new Crmusers();
           CrmusersPK crmusersPK = new CrmusersPK();
           crmusersPK.setPubkey(publickey);
           long bint = System.currentTimeMillis();
           String p = ""+bint;
           
           crmusersPK.setId(Integer.parseInt(p.substring(7)));
           CrmusersJpaController crmusersJpaController = new CrmusersJpaController();
           
           crmusers.setCompany(company);
           crmusers.setPasswd(encryptdata(password));
           crmusers.setIsAdmin(Boolean.parseBoolean(isadmin));
           crmusers.setCrmusersPK(crmusersPK);
           crmusers.setCrmuser(userid);
           crmusers.setCreateddate(new Date());
           crmusers.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmusersPrep.class");
           
           crmusersJpaController.create(crmusers);
           this.setId(crmusers.getCrmusersPK().getId());
           this.setStatus("OK");
           this.setStatusmessage("Success");
           }
         }
         }catch(Exception ex){
                if(ex.getCause().getMessage().contains("Duplicate entry")){
                  this.setStatus("ERROR");
                  this.setStatusmessage("User already exists...");
                }
                else{
                this.setStatus("ERROR");
                this.setStatusmessage(ex.getMessage());
                }
           }
   }
   
   ///
   public void crmusers(List children, int publickey, int companyID){
           Company company = new Company();
           CompanyPK companyPK = new CompanyPK();
           companyPK.setPubkey(publickey);
           companyPK.setCompanyid(companyID);
           CompanyJpaController companyJpaController = new CompanyJpaController();
           try{
           company = companyJpaController.findCompany(companyPK);
     if(company.getCompanyPK().getCompanyid() > 0){
           String userid = getElementStringValueFromList("userid", children);
           String password = getElementStringValueFromList("password", children);
           String isadmin = getElementStringValueFromList("isadmin", children);
           String employeeid = getElementStringValueFromList("employeeid", children);
         if(userid.equalsIgnoreCase("") && password.equalsIgnoreCase(""))
           {
             Collection<Crmusers> crmusersColl =  company.getCrmusersCollection();
             for(Crmusers user : crmusersColl)
             {
                 if(user.getEmployeeid() != null && user.getEmployeeid() == Integer.parseInt(employeeid)){
                   this.setUsername(user.getCrmuser());
                   this.setPasswd(user.getPasswd());
                   this.setIsAdmin(user.getIsAdmin());
                   this.setId(user.getCrmusersPK().getId());
                   this.setStatus("load");
                 }
                 
             }
           }
         else if(!userid.equalsIgnoreCase("") && !password.equalsIgnoreCase("")){
           Companyemployee companyemployee = new Companyemployee();
           CompanyemployeePK companyemployeePK = new CompanyemployeePK();
           companyemployeePK.setPubkey(publickey);
           companyemployeePK.setId(Integer.parseInt(employeeid));
           CompanyemployeeJpaController companyemployeeJpaController = new CompanyemployeeJpaController();
           
           companyemployee = companyemployeeJpaController.findCompanyemployee(companyemployeePK);

           Crmusers crmusers = new Crmusers();
           CrmusersPK crmusersPK = new CrmusersPK();
           crmusersPK.setPubkey(publickey);
           companyemployee.setCompanyemployeePK(companyemployeePK);
           CrmusersJpaController crmusersJpaController = new CrmusersJpaController();
           long bint = System.currentTimeMillis();
           String p = ""+bint;
           
           crmusersPK.setId(Integer.parseInt(p.substring(7)));
           crmusers.setCompany(company);
           crmusers.setPasswd(encryptdata(password));
           crmusers.setIsAdmin(Boolean.parseBoolean(isadmin));
           crmusers.setCrmusersPK(crmusersPK);
           crmusers.setCrmuser(userid);
           crmusers.setEmployeeid(companyemployee.getCompanyemployeePK().getId());
           crmusers.setCreateddate(new Date());
           crmusers.setCreatedfrom("com.sivotek.crm.persistent.dao.entities.controllers.entitiePrepObjects.CrmusersPrep.class");
           
           crmusersJpaController.create(crmusers);
           this.setId(crmusers.getCrmusersPK().getId());
           this.setStatus("OK");
           this.setStatusmessage("Success");
        }//end
        else{
                     this.setStatus("FAIL");
                     this.setStatusmessage("User does not exist..");
                 }
           }
         }
         catch(NullPointerException exi){
                    this.setStatus("FAIL");
                    this.setStatusmessage("Employee ID does not exist..");
           } catch (Exception ex) {
               if(ex.getCause().getMessage().contains("Duplicate entry")){
                  this.setStatus("ERROR");
                  this.setStatusmessage("User already exists...");
                }else if(ex.getCause().getMessage().contains("Employee ID does not exist..")){
                  this.setStatus("ERROR");
                  this.setStatusmessage("Employee ID does not exist..");
                }
        Logger.getLogger(CrmusersPrep.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
   
   public List<Response.Page.Elements.Element>logon(List children)
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
               
       
           String userid = getElementStringValueFromList("userid", children);
           String password = getElementStringValueFromList("password", children);
           
           Crmusers crmusers = new Crmusers();
           CrmusersPK crmusersPK = new CrmusersPK();
           crmusers.setCrmusersPK(crmusersPK);
           CrmusersJpaController crmusersJpaController = new CrmusersJpaController();
           try{
               
             crmusers = crmusersJpaController.findByCrmuserPasswd(userid, encryptdata(password));
           //////////////////
             System.out.println(crmusers.getCompany().getCompanyPK().getCompanyid());
           resElement = responseOF.createResponsePageElementsElement();
           resElement.setId("logon");
           resElement.setUserid(crmusers.getCrmusersPK().getId());
           resElement.setCompanyid(crmusers.getCompany().getCompanyPK().getCompanyid());
           resElement.setPassword(decryptdata(crmusers.getPasswd()));
           resElement.setAppkey(crmusers.getCompany().getCompanyPK().getPubkey());
           resElement.setName(userid);
           resElement.setIsadmin(crmusers.getIsAdmin());
           if(crmusers.getEmployeeid() != null){
               resElement.setEmployeeid(crmusers.getEmployeeid());
           }
            resElement.setElementstatus("OK");
            resElement.setElementstatusmessage("Success");
            ////
            responseElementList.add(resElement);
            
           }catch(Exception npe)
           {
            resElement.setElementstatus("ERROR");
            resElement.setElementstatusmessage(npe.getCause().getMessage());
            ////
            responseElementList.add(resElement);
            
//               System.out.println("Exception :"+npe.getCause().getMessage());
//               this.setStatus("ERROR");
//               this.setStatusmessage("User does not exist."+npe.getMessage());
           }
           
           return responseElementList;
   }
   
   
   /**
    * Encrypt CRM Data
    */
   private String encryptdata(String data){
       Encryptor encryptor = new Encryptor("TranscendCRMv0.1.0");
       //Encrypt
       String token = encryptor.encrypt(data);
       encryptor = null;
       return token;
   }
   
     /**
     * Decrypt TRANSCEND-CRM data
     * @param data
     * @return generated token as a string 
     */
    private String decryptdata(String data) {
        Encryptor encryptor = new Encryptor("TranscendCRMv0.1.0");
        // Dencrypt
        String token = encryptor.decrypt(data);
        encryptor = null;
        return token;
    }
}
