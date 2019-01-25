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
public class CrmvisitorcontactsaddressPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int contactaddressid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmvisitorcontactsaddressPK() {
    }

    public CrmvisitorcontactsaddressPK(int contactaddressid, int pubkey) {
        this.contactaddressid = contactaddressid;
        this.pubkey = pubkey;
    }

    public int getContactaddressid() {
        return contactaddressid;
    }

    public void setContactaddressid(int contactaddressid) {
        this.contactaddressid = contactaddressid;
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
        hash += (int) contactaddressid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmvisitorcontactsaddressPK)) {
            return false;
        }
        CrmvisitorcontactsaddressPK other = (CrmvisitorcontactsaddressPK) object;
        if (this.contactaddressid != other.contactaddressid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmvisitorcontactsaddressPK[ contactaddressid=" + contactaddressid + ", pubkey=" + pubkey + " ]";
    }

}
