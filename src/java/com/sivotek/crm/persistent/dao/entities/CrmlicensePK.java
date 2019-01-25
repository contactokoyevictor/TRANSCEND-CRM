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
public class CrmlicensePK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int licenseid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmlicensePK() {
    }

    public CrmlicensePK(int licenseid, int pubkey) {
        this.licenseid = licenseid;
        this.pubkey = pubkey;
    }

    public int getLicenseid() {
        return licenseid;
    }

    public void setLicenseid(int licenseid) {
        this.licenseid = licenseid;
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
        hash += (int) licenseid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmlicensePK)) {
            return false;
        }
        CrmlicensePK other = (CrmlicensePK) object;
        if (this.licenseid != other.licenseid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmlicensePK[ licenseid=" + licenseid + ", pubkey=" + pubkey + " ]";
    }

}
