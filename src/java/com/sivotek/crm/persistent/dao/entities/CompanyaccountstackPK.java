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
public class CompanyaccountstackPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int acstackid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CompanyaccountstackPK() {
    }

    public CompanyaccountstackPK(int acstackid, int pubkey) {
        this.acstackid = acstackid;
        this.pubkey = pubkey;
    }

    public int getAcstackid() {
        return acstackid;
    }

    public void setAcstackid(int acstackid) {
        this.acstackid = acstackid;
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
        hash += (int) acstackid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyaccountstackPK)) {
            return false;
        }
        CompanyaccountstackPK other = (CompanyaccountstackPK) object;
        if (this.acstackid != other.acstackid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CompanyaccountstackPK[ acstackid=" + acstackid + ", pubkey=" + pubkey + " ]";
    }

}
