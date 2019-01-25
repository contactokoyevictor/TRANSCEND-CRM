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
public class CrmschedulerPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int schedulerid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmschedulerPK() {
    }

    public CrmschedulerPK(int schedulerid, int pubkey) {
        this.schedulerid = schedulerid;
        this.pubkey = pubkey;
    }

    public int getSchedulerid() {
        return schedulerid;
    }

    public void setSchedulerid(int schedulerid) {
        this.schedulerid = schedulerid;
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
        hash += (int) schedulerid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmschedulerPK)) {
            return false;
        }
        CrmschedulerPK other = (CrmschedulerPK) object;
        if (this.schedulerid != other.schedulerid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmschedulerPK[ schedulerid=" + schedulerid + ", pubkey=" + pubkey + " ]";
    }

}
