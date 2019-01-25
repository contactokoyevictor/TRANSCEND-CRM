/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */
package com.sivotek.crm.security;

import com.sivotek.crm.persistent.dao.entities.Crmlicense;
import com.sivotek.crm.persistent.dao.entities.Crmlicensecode;
import com.sivotek.crm.persistent.dao.entities.Crmlicenseperiodity;
import com.sivotek.crm.persistent.dao.entities.Crmlicensetype;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmlicenseJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmlicensecodeJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmlicensetypeJpaController;
import com.sivotek.crm.xsd.jaxb.response.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author okoyevictor
 */
public class PublicKeyValidator {

private Response response;
String Appname = "SIVOTEK TRANSCEND CRM R1";
Date date = new Date();
String message = "";

CrmlicenseJpaController crmlicenseJpaController;
Crmlicense crmlicense;

CrmlicensecodeJpaController crmlicensecodeJpaController;
Crmlicensecode crmlicensecode;

public List<Response.Page.Elements.Element> ValidateNgetPublicKey(String licensecode) throws Exception
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
     
    
    int pubkeygen = 0;
    Crmlicensetype crmlicensetype = new Crmlicensetype();
    CrmlicensetypeJpaController crmlicensetypeJpaController = new CrmlicensetypeJpaController();
    List<Crmlicensetype> colls = crmlicensetypeJpaController.findCrmlicensetypeEntities();
    for(Crmlicensetype type : colls)
    {
        crmlicensetype = type;
        pubkeygen = FixedSeedRandoms(Appname.trim().hashCode()+type.getName().trim().hashCode(), licensecode);
        
        if(pubkeygen > 0 && crmlicensetype.getCrmlicenseperiodityCollection().size() >= 0){
            //////////////////
            resElement = responseOF.createResponsePageElementsElement();
            resElement.setId("licenseregistration");
            resElement.setName(crmlicensetype.getName());
            resElement.setPublickey(pubkeygen);
            Collection<Crmlicenseperiodity> period = crmlicensetype.getCrmlicenseperiodityCollection();
            for(Crmlicenseperiodity details : period){
                resElement.setValidityfrom(details.getValidfrom());
                resElement.setValidityto(details.getValidto());
            }
            resElement.setElementstatus("OK");
            resElement.setElementstatusmessage("Success");
            ////
            responseElementList.add(resElement); 
            return responseElementList;
        }
    }
    
    return responseElementList;
   //FixedSeedRandoms(Appname.trim().hashCode()+licensetype.trim().hashCode(), licensekey);
}


  private int FixedSeedRandoms(int seed, String licensecode)
  {
            Random fixRand = new Random(seed);
            int publickey = 0;
            String replace_minus = ""+RunIntRandoms(fixRand, licensecode);
            String formated = replace_minus.replace("-", "");
            
            publickey = Integer.parseInt(formated);
            if(publickey != 0){
               try{
               crmlicensecodeJpaController = new CrmlicensecodeJpaController();
               crmlicensecode = new Crmlicensecode();
               crmlicensecode = crmlicensecodeJpaController.findByLicensecode(licensecode);
               
               if(crmlicensecode.getLicensecode().trim().length() == 19)
               {
                 crmlicensecode.setLicensekey(publickey);
                 crmlicensecode.setChangeddate(date);
                 crmlicensecode.setChangedfrom("com.sivotek.crm.security.PublicKeyvalidator.class");
                 //
                if(crmlicensecodeJpaController.updateByPublickey(crmlicensecode) !=0){
                    crmlicensecodeJpaController.edit(crmlicensecode);
                    
                    crmlicenseJpaController = new CrmlicenseJpaController();
                    crmlicense = new Crmlicense();
                    System.out.println("licensekey :"+crmlicensecode.getLicensekey() );
                    //find and fitch license detail via license key
                    crmlicense = crmlicenseJpaController.findByLicensecode(crmlicensecode);
                    if(crmlicense.getCrmlicensePK().getLicenseid() > 0)
                    {
                     System.out.println("License detail is :"+crmlicense.getLicensecode());
                     //updating info
                     crmlicense.setPublicKey(crmlicensecode.getLicensekey());
                     crmlicense.setChangeddate(date);
                     crmlicense.setChangedfrom("com.sivotek.crm.security.PublicKeyvalidator.class");
                   
                     //executing update...
                     crmlicenseJpaController.updateByPublickey(crmlicensecode);
                     crmlicenseJpaController.edit(crmlicense); 
                    }
//                    if(crmlicense.getCrmlicensePK().getLicenseid() <= 0)
//                    {
//                        crmlicense.setCreateddate(date);
//                        crmlicense.setPeriodityid(crmlicenseperiodity);
//                        crmlicense.setCreatedfrom("com.sivotek.crm.security.LicenseManager.java");
//                        crmlicense.setLicensecode(crmlicensecode.getLicensecode());
//                        crmlicenseJpaController.create(crmlicense);
//                    }
                   
                  
                    return publickey;
                }
                   
               }
  
            }catch(Exception ex){
                System.out.println("Exceptions caught : "+ex.getMessage());
                    }
            }
            return publickey;
  }
    
   // Generate random numbers from the specified Random object.  
   private int RunIntRandoms(Random randObj, String licensekey)
   {
            String code = "";
            int number = 0;
            //message = "This key is an invalid Key..";
            
            for (int j = 0; j < 50000; j++)
            {
                code = RandomString(randObj) + randObj.nextInt();
                String madekey = MakeKey(code);
                if(madekey == null ? licensekey == null : madekey.equals(licensekey))
                {
                    //message = "This key is a valid Key..";
                    number = randObj.nextInt();
                    return number;
                }
                 
            }
            //System.out.println(message);
            return number;
   }
   
        
//gets the first part of random string
        static String RandomString(Random randObj)
        {
            StringBuilder builder = new StringBuilder();
            char ch;
            for (int i = 0; i < 8; i++)
            {
                double floor = Math.floor(26 * randObj.nextDouble() + 65);
                ch = (char) floor;
                builder.append(ch);
            }
            return builder.toString();
        }
        
        
        private static String MakeKey(String pkey) //Generating the topup key
        {
            
            char[] key1 = new char[19];

            key1[0] = GetChar(pkey.toCharArray()[8]);
            key1[1] = GetNum(pkey.toCharArray()[4]);
            key1[2] = GetNum(pkey.toCharArray()[1]);
            key1[3] = '9';
            key1[4] = '-';
            key1[5] = GetChar(pkey.toCharArray()[9]);
            key1[6] = '9';
            key1[7] = GetNum(pkey.toCharArray()[2]);
            key1[8] = GetChar(pkey.toCharArray()[10]);
            key1[9] = '-';
            key1[10] = GetChar(pkey.toCharArray()[12]);
            key1[11] = GetNum(pkey.toCharArray()[3]);
            key1[12] = GetNum(pkey.toCharArray()[6]);
            key1[13] = '9';
            key1[14] = '-';
            key1[15] = GetChar(pkey.toCharArray()[11]);
            key1[16] = GetNum(pkey.toCharArray()[0]);
            key1[17] = GetNum(pkey.toCharArray()[7]);
            key1[18] = GetNum(pkey.toCharArray()[5]);
            String skey = "";
            int i;

            for (i = 0; i < 19; i++)
                skey += key1[i];

            return skey;
        }
        
        
        // key generation

        static char GetChar(char num) //gets the char for every numner passed (coding first part of the random string)
        {
            char ret = 'F';

            if (num == '1')
                ret = 'O';
            if (num == '2')
                ret = 'W';
            if (num == '3')
                ret = 'U';
            if (num == '4')
                ret = 'R';
            if (num == '5')
                ret = 'V';
            if (num == '6')
                ret = 'X';
            if (num == '7')
                ret = 'S';
            if (num == '8')
                ret = 'T';
            if (num == '9')
                ret = 'N';
            if (num == '0')
                ret = 'Z';
         

            return ret;
        }

        static char GetNum(char str) // gets the numbers for every char passed (coding second part of the random string)
        {
            char ret = '3';

            if (str == 'A')
                ret = '2';
            if (str == 'B')
                ret = '6';
            if (str == 'C')
                ret = '7';
            if (str == 'D')
                ret = '2';
            if (str == 'E')
                ret = '1';
            if (str == 'F')
                ret = '7';
            if (str == 'G')
                ret = '8';
            if (str == 'H')
                ret = '9';
            if (str == 'I')
                ret = '4';
            if (str == 'J')
                ret = '4';
            if (str == 'K')
                ret = '3';
            if (str == 'L')
                ret = '9';
            if (str == 'M')
                ret = '9';
            if (str == 'N')
                ret = '3';
            if (str == 'O')
                ret = '4';
            if (str == 'P')
                ret = '9';
            if (str == 'Q')
                ret = '7';
            if (str == 'R')
                ret = '6';
            if (str == 'S')
                ret = '9';
            if (str == 'T')
                ret = '9';
            if (str == 'U')
                ret = '9';
            if (str == 'V')
                ret = '5';
            if (str == 'W')
                ret = '4';
            if (str == 'X')
                ret = '2';
            if (str == 'Y')
                ret = '8';
            if (str == 'Z')
                ret = '2';

            return ret;
        }


}
