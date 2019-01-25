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
public class CompanyaccountstackcdPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int accountstackcdid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CompanyaccountstackcdPK() {
    }

    public CompanyaccountstackcdPK(int accountstackcdid, int pubkey) {
        this.accountstackcdid = accountstackcdid;
        this.pubkey = pubkey;
    }

    public int getAccountstackcdid() {
        return accountstackcdid;
    }

    public void setAccountstackcdid(int accountstackcdid) {
        this.accountstackcdid = accountstackcdid;
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
        hash += (int) accountstackcdid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyaccountstackcdPK)) {
            return false;
        }
        CompanyaccountstackcdPK other = (CompanyaccountstackcdPK) object;
        if (this.accountstackcdid != other.accountstackcdid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CompanyaccountstackcdPK[ accountstackcdid=" + accountstackcdid + ", pubkey=" + pubkey + " ]";
    }

}
