/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.xsd.jaxb.rules;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
/**
 *
 * @author okoyevictor
 */
public class Json_To_XML {
    
     
      public static void main(String[] args) throws Exception{
        String str = "{\"request\": [{\n" +
"    \"appkey\": \"X929-T98Z-U989-V964\",\n" +
"    \"companyid\": \"0\",\n" +
"    \"elements\": [    {\n" +
"        \"@id\": \"licenseregistration\",\n" +
"        \"appkey\": \"X929-T98Z-U989-V964\"\n" +
"    }]\n" +
"}]}";
        
        JSON json = JSONSerializer.toJSON(str);
        Object obj = (Object) json;
        
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTypeHintsEnabled(false);
        xmlSerializer.setForceTopLevelObject(true);
        String xml = xmlSerializer.write( json );
        
        String level1 = xml.replace("<e>", "");
        String level2 = level1.replace("</e>", "");
        String level3 = level2.replace("<e id", "<element id");
//        String level4 = level3.replace("</employeeid>", "</employeeid></element>");
        String level5 = level3.replace("<o>", "");
        String level6 = level5.replace("</o>", "");
        String level7 = level6.replace("<request>", "<request><page>");
        String level8 = level7.replace("</request>", "</page></request>");
        String level9 = level8.replace("</elements>", "</element></elements>");
        System.out.println(level9);
        
        
    }
 
      
      
  
}
