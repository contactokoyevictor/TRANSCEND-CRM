/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.xsd.jaxb.rules;
import com.sivotek.crm.xsd.jaxb.request.Request;
import java.io.InputStream;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
/**
 *
 * @author okoyevictor
 */

public class ConvertXMLtoJSON {
    
      	public static void main(String[] args) throws Exception {
//                ConvertXMLtoJSON fdf = new ConvertXMLtoJSON();
//                fdf.sendCrossdomainRequest();
                
                
		InputStream is = ConvertXMLtoJSON.class.getResourceAsStream("sample-xml.xml");
		String xml = IOUtils.toString(is);
            
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		
                xmlSerializer.setArrayName("request");
                xmlSerializer.clearNamespaces();
                xmlSerializer.setForceTopLevelObject(true);
                JSON json = xmlSerializer.read( xml );
                System.out.println( json.toString(4) );
	}
        
        
        public static void processJSON(String json) throws JAXBException, JSONException, XMLStreamException{
     
       String str = "{\"request\": {\n" +
"  \"appkey\": \"X929-T98Z-U989-V964\",\n" +
"  \"companyid\": \"32\",\n" +
"  \"employeeid\": \"323\",\n" +
"  \"elements\": [  {\n" +
"    \"@id\": \"company\",\n" +
"    \"companyname\": \"SIVOTEK Solutions Limited\",\n" +
"    \"companysubname\": \"sivotek solutions\",\n" +
"    \"companyphone\": \"998980980\",\n" +
"    \"companyfax\": \"44444\",\n" +
"    \"companyemail\": \"sales@sivoteksolutions.com\",\n" +
"    \"companyweb\": \"www.sivoteksolutions.com\",\n" +
"    \"companycategory\": \"ICT\",\n" +
"    \"companymotto\": \"We do BIG THINGS\"\n" +
"  }]\n" +
"}}";
        
        JSON json3 = JSONSerializer.toJSON(str);
        Object obj3 = (Object) json3;
            
         Request request = (Request) obj3;   
            
            
            System.out.println("Output  :"+request.getPage().getAppkey());
            
    }

       
        
}
