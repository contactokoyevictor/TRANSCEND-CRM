/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sivotek.crm.xsd.jaxb.prep.ejb;

import javax.ejb.Local;

/**
 *
 * @author okoyevictor
 */
@Local
public interface TranscendEJB_QUARTZProcessorLocal {
    
    public int createSchedule(java.lang.String scheduleID, java.lang.String jobDetail, java.lang.String Timezone_param, java.lang.String scheduleGroup) throws java.lang.IllegalArgumentException;

    public int updateSchedule(java.lang.String scheduleID, java.lang.String jobDetail, java.lang.String Timezone_param, java.lang.String scheduleGroup) throws java.lang.IllegalArgumentException;

    public int disableSchedule(java.lang.String scheduleID, java.lang.String scheduleGroup)throws java.lang.IllegalArgumentException;

    public int removeSchedule(java.lang.String scheduleID, java.lang.String scheduleGroup) throws java.lang.IllegalArgumentException;

    public int resumeSchedule(java.lang.String scheduleID, java.lang.String scheduleGroup) throws java.lang.IllegalArgumentException;

    public java.lang.String getStatus();

    public java.lang.String getStatusmessage();
}
