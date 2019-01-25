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
public class CrmforumPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int forumid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmforumPK() {
    }

    public CrmforumPK(int forumid, int pubkey) {
        this.forumid = forumid;
        this.pubkey = pubkey;
    }

    public int getForumid() {
        return forumid;
    }

    public void setForumid(int forumid) {
        this.forumid = forumid;
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
        hash += (int) forumid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmforumPK)) {
            return false;
        }
        CrmforumPK other = (CrmforumPK) object;
        if (this.forumid != other.forumid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmforumPK[ forumid=" + forumid + ", pubkey=" + pubkey + " ]";
    }

}
