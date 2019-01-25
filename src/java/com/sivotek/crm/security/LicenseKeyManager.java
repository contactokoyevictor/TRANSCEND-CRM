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
import com.sivotek.crm.persistent.dao.entities.controllers.CrmlicenseperiodityJpaController;
import com.sivotek.crm.persistent.dao.entities.controllers.CrmlicensetypeJpaController;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author okoyevictor
 */
public class LicenseKeyManager {
    String Appname = "SIVOTEK TRANSCEND CRM R1";
    Date date = new Date();
    
    CrmlicensetypeJpaController licensetypecontrol = new CrmlicensetypeJpaController();
    Crmlicensetype crmlicensetype = new Crmlicensetype();
    CrmlicenseperiodityJpaController perioditycontrol = new CrmlicenseperiodityJpaController();
    Crmlicenseperiodity crmlicenseperiodity = new Crmlicenseperiodity();
    
    CrmlicensecodeJpaController crmlicensecodeJpaController;
    Crmlicensecode crmlicensecode;
    
    public void makekeys(){
        createtype();
    }
    
    private void createtype()
    {       
           crmlicensetype.setCreateddate(date);
           crmlicensetype.setName("1 Year Single User");
           crmlicensetype.setCreatedfrom("com.sivotek.crm.security.LicenseManager.class");
           
           try {
                    licensetypecontrol.create(crmlicensetype);
                    crmlicenseperiodity.setTypeid(crmlicensetype);
                    crmlicenseperiodity.setValidfrom(date);
                    crmlicenseperiodity.setValidto(date);
                    
                    crmlicenseperiodity.setCreateddate(date);
                    crmlicenseperiodity.setCreatedfrom("com.sivotek.crm.security.LicenseManager.java");
                    perioditycontrol.create(crmlicenseperiodity);
                    FixedSeedRandoms(Appname.hashCode());
           } catch (Exception ex) {
                    Logger.getLogger(LicenseKeyManager.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    private  void FixedSeedRandoms(int seed)
    {
            Random fixRand = new Random(seed); 
            RunIntRandoms(fixRand);
            
    }

   // Generate random numbers from the specified Random object.  
   private void RunIntRandoms(Random randObj)
   {
            String code = "";
            String message = "new key has been generated";
                
            for (int j = 0; j < 2; j++)
            {
                code = RandomString(randObj) + randObj.nextInt();
                String madekey = MakeKey(code);
            
                try{
                    //creating license code and inserting it into licensecode table..
                    crmlicensecodeJpaController = new CrmlicensecodeJpaController();
                    crmlicensecode = new Crmlicensecode();
                    crmlicensecode.setLicensecode(madekey);
                    crmlicensecode.setCreateddate(date);
                    crmlicensecode.setCreatedfrom("com.sivotek.crm.security.LicenseManager.java");
                    crmlicensecodeJpaController.create(crmlicensecode);
                    
                    
                    //creating license detail and inserting it into crmlicense table..
                    CrmlicenseJpaController crmlicenseJpaController = new CrmlicenseJpaController();
                    Crmlicense crmlicense = new Crmlicense();
                    crmlicense.setCreateddate(date);
                    crmlicense.setPeriodityid(crmlicenseperiodity);
                    crmlicense.setCreatedfrom("com.sivotek.crm.security.LicenseManager.java");
                    crmlicense.setLicensecode(crmlicensecode.getLicensecode());
                    crmlicenseJpaController.create(crmlicense);
                    
                System.out.println(madekey);
                }
                catch(Exception e){
                System.out.println("Exception caught :"+e.getMessage());
                if(e.getMessage().contains("Duplicate entry")){
                message = "Key already exist in the database";
                return;
                }
                }
             
                 
            }
            System.out.println(message);
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
