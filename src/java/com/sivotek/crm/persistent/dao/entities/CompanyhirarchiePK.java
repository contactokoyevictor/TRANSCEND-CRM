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
public class CompanyhirarchiePK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int hierarchieid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CompanyhirarchiePK() {
    }

    public CompanyhirarchiePK(int hierarchieid, int pubkey) {
        this.hierarchieid = hierarchieid;
        this.pubkey = pubkey;
    }

    public int getHierarchieid() {
        return hierarchieid;
    }

    public void setHierarchieid(int hierarchieid) {
        this.hierarchieid = hierarchieid;
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
        hash += (int) hierarchieid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyhirarchiePK)) {
            return false;
        }
        CompanyhirarchiePK other = (CompanyhirarchiePK) object;
        if (this.hierarchieid != other.hierarchieid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CompanyhirarchiePK[ hierarchieid=" + hierarchieid + ", pubkey=" + pubkey + " ]";
    }

}
