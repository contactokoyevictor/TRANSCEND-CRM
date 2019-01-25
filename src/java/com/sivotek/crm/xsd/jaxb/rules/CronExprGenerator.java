/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */

package com.sivotek.crm.xsd.jaxb.rules;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author okoyevictor
 */
public class CronExprGenerator {
     
    private String Seconds = "0"; //only 0-59 are allowed..special characters : (, - * /) Required = Yes
    private String Minutes = "0"; //only 0-59 are allowed..special characters : (, - * /) Required = Yes
    private String Hour = "0"; //only 0-23 are allowed..special characters : (, - * /) Required = Yes
    private String DayOfMonth = "?"; //only 1-31 are allowed..special characters : (, - * ? / L W C) Required = Yes
    private String Month = "*";//only 0-11 or JAN-DEC are allowed..special characters :(, - * /) Required = Yes
    private String Weekdays = "*";//only 1-7 or SUN-SAT are allowed..special characters :(, - * ? / L C #) Required = Yes
    private String Year = "*";//only empty or 1970-2099 are allowed..special characters :(, - * /) Required = No
    private String TimeZoneName = "Africa/Luanda";//Africa/Luanda //US/Eastern //US/Central //US/Pacific //America/Los_Angeles
    private String msg = "";
    private static final Logger _log = Logger.getLogger(CronExprGenerator.class.getName());
  
    public String getSeconds(){ return Seconds; }
    public void setSeconds(String Seconds){ 
        if(Seconds.equalsIgnoreCase("") || Seconds.equalsIgnoreCase(null)){ Seconds = "0";}
          this.Seconds = Seconds; 
    }
    
    public String getMinutes(){ return Minutes; }
    public void setMinutes(String Minutes){ 
        if(Minutes.equalsIgnoreCase("") || Minutes.equalsIgnoreCase(null)){Minutes = "*";}
        this.Minutes = Minutes; 
    }
    
    public String getHour(){ return Hour; }
    public void setHour(String Hour){ 
        if(Hour.equalsIgnoreCase("") || Hour.equalsIgnoreCase(null)){Hour = "*";}
        this.Hour = Hour; 
    }
       
    public String getDayOfMonth(){ return DayOfMonth; }
    public void setDayOfMonth(String DayOfMonth){ 
        if(DayOfMonth.equalsIgnoreCase("") || DayOfMonth.equalsIgnoreCase(null)){DayOfMonth = "?";}
        this.DayOfMonth = DayOfMonth; 
    }
     
    public String getMonth(){ return Month; }
    public void setMonth(String Month){
        if(Month.equalsIgnoreCase("") || Month.equalsIgnoreCase(null)){Month = "*";}
         this.Month = Month; }
     
    public String getWeekdays(){ return Weekdays; }
    public void setWeekdays(String Weekdays){ 
        if(Weekdays.equalsIgnoreCase("") || Weekdays.equalsIgnoreCase(null)){this.DayOfMonth ="*"; Weekdays = "*";}
        this.Weekdays = Weekdays; }
     
    public String getYear(){ return Year; }
    public void setYear(String Year){ this.Year = Year; }
     
    public String getTimeZoneName(){ return TimeZoneName; }
    public void setTimeZoneName(String TimeZoneName){ this.TimeZoneName = TimeZoneName; }
    
    
    public String getCronExpression(String Seconds, String Minutes, String Hour, String DayOfMonth, String Month, String Weekdays, String Year, String TimeZoneName)
    {
        this.setSeconds(Seconds);
        this.setMinutes(Minutes);
        this.setHour(Hour);
        this.setDayOfMonth(DayOfMonth);
        this.setMonth(Month);
        this.setWeekdays(Weekdays);
        this.setYear(Year);
        this.setTimeZoneName(TimeZoneName);
        try {
            _log.log(Level.INFO, "{0} : Expression generated to run in ({1}) TimeZone.", new Object[]{getCronExpression(), getTimeZoneName()});
            msg = getCronExpression();
        } catch (Exception ex) {
             _log.log(Level.WARNING,"Error Information :{1}", ex.getMessage()); 
        }
        return msg;
    }
    
    private String getCronExpression()throws Exception
    {
       String cronExpression = getSeconds()+ " " + getMinutes() + " " + getHour() + " "+ getDayOfMonth() + " "+ getMonth() +" "+ getWeekdays() + " " + getYear();
       return cronExpression;     
    }
}
