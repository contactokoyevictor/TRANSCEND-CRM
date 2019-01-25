/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.xsd.jaxb.rules;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author okoyevictor
 */
public class ByteArrayCopy {
    
     public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = ByteArrayCopy.class.getResource("quartz.properties").getPath();
        
        System.out.println("Full path :"+path);
        File file = new File(path);
        System.out.println("Full path2 :"+file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1048576];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
            Logger.getLogger(ByteArrayCopy.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = bos.toByteArray();
        System.out.println("bytes Array \n" +bos+"\n");
        System.out.println("Actual bytes " +bytes);
        //below is the different part
//        File someFile = new File(path);
//        FileOutputStream fos = new FileOutputStream(someFile);
//        fos.write(bytes);
//        fos.flush();
//        fos.close();
    }

}
