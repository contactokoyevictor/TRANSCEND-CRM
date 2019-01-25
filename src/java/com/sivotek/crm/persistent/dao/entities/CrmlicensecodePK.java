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
public class CrmlicensecodePK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmlicensecodePK() {
    }

    public CrmlicensecodePK(int id, int pubkey) {
        this.id = id;
        this.pubkey = pubkey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        hash += (int) id;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmlicensecodePK)) {
            return false;
        }
        CrmlicensecodePK other = (CrmlicensecodePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmlicensecodePK[ id=" + id + ", pubkey=" + pubkey + " ]";
    }

}
