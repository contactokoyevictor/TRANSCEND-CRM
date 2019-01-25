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
public class ApprovalPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int approvalid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public ApprovalPK() {
    }

    public ApprovalPK(int approvalid, int pubkey) {
        this.approvalid = approvalid;
        this.pubkey = pubkey;
    }

    public int getApprovalid() {
        return approvalid;
    }

    public void setApprovalid(int approvalid) {
        this.approvalid = approvalid;
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
        hash += (int) approvalid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApprovalPK)) {
            return false;
        }
        ApprovalPK other = (ApprovalPK) object;
        if (this.approvalid != other.approvalid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.ApprovalPK[ approvalid=" + approvalid + ", pubkey=" + pubkey + " ]";
    }

}
