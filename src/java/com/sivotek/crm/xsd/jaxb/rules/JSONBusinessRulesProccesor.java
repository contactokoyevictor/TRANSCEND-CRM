/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.xsd.jaxb.rules;

import com.sivotek.crm.xsd.jaxb.request.Request;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import javax.xml.bind.*;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.*;

/**
 *
 * @author okoyevictor
 */
public class JSONBusinessRulesProccesor {
    RulesEngine PrepObject = new RulesEngine();
    
    public String JSONProccesor(String JSON) throws Exception
    {
        JSON json = JSONSerializer.toJSON(JSON);
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTypeHintsEnabled(false);
        xmlSerializer.setForceTopLevelObject(true);
        String xml = xmlSerializer.write( json );
        
        String level1 = xml.replace("<e>", "");
        String level2 = level1.replace("</e>", "");
        String level3 = level2.replace("<e id", "<element id");
        String level5 = level3.replace("<o>", "");
        String level6 = level5.replace("</o>", "");
        String level7 = level6.replace("<request>", "<request><page>");
        String level8 = level7.replace("</request>", "</page></request>");
        String level9 = level8.replace("</elements>", "</element></elements>");
        xml = level9;
        String response = PrepObject.xmlProccessor(xml);
        
        return ConvertXMLResponseToJSON(response);
    }
    
    

    public String ConvertXMLResponseToJSON(String xml)
    {
                
   		XMLSerializer xmlSerializer = new XMLSerializer(); 
                xmlSerializer.setArrayName("request");
                xmlSerializer.clearNamespaces();
                xmlSerializer.setForceTopLevelObject(true);
                
                String jaxbAttr6 = xml.replace(" xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
                String jaxbAttr7 = jaxbAttr6.replace("xsi:type=\"xs:int\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
                String jaxbAttr8 = jaxbAttr7.replace("xsi:type=\"xs:dateTime\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"","");
                String jaxbAttr9 = jaxbAttr8.replace(" xsi:type=\"xs:double\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
                String jaxbAttr10 = jaxbAttr9.replace(" xsi:type=\"xs:boolean\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
                String jaxbAttr11 = jaxbAttr10.replace(" xsi:type=\"xs:base64Binary\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
                
//                System.out.println("XML Response :\n"+jaxbAttr10);
                JSON json = xmlSerializer.read(jaxbAttr11);
                String jsonString = json.toString(4);
                
        return jsonString;
    }
    
    public void processJSON(String json) throws JAXBException, JSONException, XMLStreamException{
     
        JAXBContext jc = JAXBContext.newInstance(com.sivotek.crm.xsd.jaxb.request.Request.class);
        JSONObject obj = new JSONObject(json);
        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);
 
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Request request = (Request) unmarshaller.unmarshal(xmlStreamReader);
 
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(request, System.out);
    }
}
