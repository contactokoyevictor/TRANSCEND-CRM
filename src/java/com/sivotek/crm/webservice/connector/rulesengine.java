/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */
package com.sivotek.crm.webservice.connector;

import com.sivotek.crm.xsd.jaxb.rules.RulesEngine;
import com.sivotek.crm.xsd.jaxb.rules.JSONBusinessRulesProccesor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author okoyevictor
 */
public class rulesengine extends HttpServlet 
{
   private static final Logger _log = Logger.getLogger(rulesengine.class.getName());
   
   JSONBusinessRulesProccesor JSONPrep;
   RulesEngine Prep;
    public String processRequest(HttpServletRequest request) throws Exception, java.lang.IllegalArgumentException, IOException
    {
       String Response = "";
       String xmljson = "";
       xmljson = readRequest(request);
        
       JSONPrep = new JSONBusinessRulesProccesor();
       Prep = new RulesEngine();
       
        try {
             if(request.getContentType().contains("text/json") || request.getContentType().contains("text/json; charset=UTF-8")){
                 //_log.log(Level.INFO, "ContentType is :{0}\n{1}", new Object[]{request.getContentType(), xmljson});
                  Response = JSONPrep.JSONProccesor(xmljson);
             }
             else if(request.getContentType().contains("application/x-www-form-urlencoded; charset=UTF-8"))
             {
                 //_log.log(Level.INFO, "ContentType is :{0}\n{1}", new Object[]{request.getContentType(), xmljson});
                 
                 Response = JSONPrep.JSONProccesor(xmljson);
             }
             else
             {
                 //_log.log(Level.INFO, "ContentType is :{0}\n{1}", new Object[]{request.getContentType(), xmljson});
                 Response = Prep.xmlProccessor(xmljson);
             }
     } catch (Exception e) 
     {
         _log.log(Level.WARNING, "Exception Occoured:\n{0}", e.getMessage());
         
     }
        return Response;
    }
    
    public String readRequest(HttpServletRequest httprequest)throws IOException
    {
        String Response = "";
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("");
	BufferedReader reader = httprequest.getReader();
        String line;
	while((line = reader.readLine()) != null){
              strBuilder.append(line);
	}
        try{
            Response = strBuilder.toString();
        }catch(Exception e){
            _log.log(Level.WARNING, "Exception Occoured during request reading :\n{0}", e.getMessage());
        
        }
        //_log.log(Level.INFO, Response);
        return Response;
    }   
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        processRequest(request, response);
//    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "1000");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Custom-Header, X-Requested-With");
        
        response.setHeader("Access-Control-Request-Method", "POST");
        response.setHeader("Access-Control-Request-Headers", "Content-Type, text/json");
        
        response.setHeader("Content-Type", "text/json");
        if(request.getContentType().contains("text/json") || request.getContentType().contains("text/json; charset=UTF-8")){
                 response.setHeader("Content-Type", "text/json");
             }
             else if(request.getContentType().contains("application/x-www-form-urlencoded; charset=UTF-8"))
             {
                 response.setHeader("Content-Type", "text/json");
             }
             else
             {
                 response.setHeader("Content-Type", "text/xml");
             }
        
        PrintWriter out = response.getWriter();
         String resp = "";
        try {
            resp = processRequest(request);
        } catch (Exception ex) {
       
            resp = ex.getMessage();
            _log.log(Level.WARNING, ex.getMessage());
            //Logger.getLogger(rulesengine.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.println(resp);
        } finally {            
            out.close();
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    
}
