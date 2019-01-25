/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author okoyevictor
 */
@Embeddable
public class AppointmentPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int appointmentid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public AppointmentPK() {
    }

    public AppointmentPK(int appointmentid, int pubkey) {
        this.appointmentid = appointmentid;
        this.pubkey = pubkey;
    }

    public int getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(int appointmentid) {
        this.appointmentid = appointmentid;
    }

    public int getPubkey() {
        return pubkey;
    }

    public void setPubkey(int pubkey) {
        this.pubkey = pubkey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) appointmentid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppointmentPK)) {
            return false;
        }
        AppointmentPK other = (AppointmentPK) object;
        if (this.appointmentid != other.appointmentid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.AppointmentPK[ appointmentid=" + appointmentid + ", pubkey=" + pubkey + " ]";
    }

}
