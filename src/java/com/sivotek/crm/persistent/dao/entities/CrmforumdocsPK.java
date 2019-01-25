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
public class CrmforumdocsPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int docid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmforumdocsPK() {
    }

    public CrmforumdocsPK(int docid, int pubkey) {
        this.docid = docid;
        this.pubkey = pubkey;
    }

    public int getDocid() {
        return docid;
    }

    public void setDocid(int docid) {
        this.docid = docid;
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
        hash += (int) docid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmforumdocsPK)) {
            return false;
        }
        CrmforumdocsPK other = (CrmforumdocsPK) object;
        if (this.docid != other.docid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmforumdocsPK[ docid=" + docid + ", pubkey=" + pubkey + " ]";
    }

}
