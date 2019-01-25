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
public class CustomercontactsPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int contactid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CustomercontactsPK() {
    }

    public CustomercontactsPK(int contactid, int pubkey) {
        this.contactid = contactid;
        this.pubkey = pubkey;
    }

    public int getContactid() {
        return contactid;
    }

    public void setContactid(int contactid) {
        this.contactid = contactid;
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
        hash += (int) contactid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomercontactsPK)) {
            return false;
        }
        CustomercontactsPK other = (CustomercontactsPK) object;
        if (this.contactid != other.contactid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CustomercontactsPK[ contactid=" + contactid + ", pubkey=" + pubkey + " ]";
    }

}
