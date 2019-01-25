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
public class CrmschedulerevnttypePK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int eventtypeid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmschedulerevnttypePK() {
    }

    public CrmschedulerevnttypePK(int eventtypeid, int pubkey) {
        this.eventtypeid = eventtypeid;
        this.pubkey = pubkey;
    }

    public int getEventtypeid() {
        return eventtypeid;
    }

    public void setEventtypeid(int eventtypeid) {
        this.eventtypeid = eventtypeid;
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
        hash += (int) eventtypeid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmschedulerevnttypePK)) {
            return false;
        }
        CrmschedulerevnttypePK other = (CrmschedulerevnttypePK) object;
        if (this.eventtypeid != other.eventtypeid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmschedulerevnttypePK[ eventtypeid=" + eventtypeid + ", pubkey=" + pubkey + " ]";
    }

}
