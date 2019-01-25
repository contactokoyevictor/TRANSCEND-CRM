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
public class CrmforumteammembersPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int memeberid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmforumteammembersPK() {
    }

    public CrmforumteammembersPK(int memeberid, int pubkey) {
        this.memeberid = memeberid;
        this.pubkey = pubkey;
    }

    public int getMemeberid() {
        return memeberid;
    }

    public void setMemeberid(int memeberid) {
        this.memeberid = memeberid;
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
        hash += (int) memeberid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmforumteammembersPK)) {
            return false;
        }
        CrmforumteammembersPK other = (CrmforumteammembersPK) object;
        if (this.memeberid != other.memeberid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmforumteammembersPK[ memeberid=" + memeberid + ", pubkey=" + pubkey + " ]";
    }

}
