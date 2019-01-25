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
public class CompanybankPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int bankid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CompanybankPK() {
    }

    public CompanybankPK(int bankid, int pubkey) {
        this.bankid = bankid;
        this.pubkey = pubkey;
    }

    public int getBankid() {
        return bankid;
    }

    public void setBankid(int bankid) {
        this.bankid = bankid;
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
        hash += (int) bankid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanybankPK)) {
            return false;
        }
        CompanybankPK other = (CompanybankPK) object;
        if (this.bankid != other.bankid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CompanybankPK[ bankid=" + bankid + ", pubkey=" + pubkey + " ]";
    }

}
