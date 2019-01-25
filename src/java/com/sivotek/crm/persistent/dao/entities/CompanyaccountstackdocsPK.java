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
public class CompanyaccountstackdocsPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int accountdocid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CompanyaccountstackdocsPK() {
    }

    public CompanyaccountstackdocsPK(int accountdocid, int pubkey) {
        this.accountdocid = accountdocid;
        this.pubkey = pubkey;
    }

    public int getAccountdocid() {
        return accountdocid;
    }

    public void setAccountdocid(int accountdocid) {
        this.accountdocid = accountdocid;
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
        hash += (int) accountdocid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyaccountstackdocsPK)) {
            return false;
        }
        CompanyaccountstackdocsPK other = (CompanyaccountstackdocsPK) object;
        if (this.accountdocid != other.accountdocid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CompanyaccountstackdocsPK[ accountdocid=" + accountdocid + ", pubkey=" + pubkey + " ]";
    }

}
