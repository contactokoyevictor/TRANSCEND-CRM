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
public class CrmcampaignPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int campaignid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmcampaignPK() {
    }

    public CrmcampaignPK(int campaignid, int pubkey) {
        this.campaignid = campaignid;
        this.pubkey = pubkey;
    }

    public int getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(int campaignid) {
        this.campaignid = campaignid;
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
        hash += (int) campaignid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmcampaignPK)) {
            return false;
        }
        CrmcampaignPK other = (CrmcampaignPK) object;
        if (this.campaignid != other.campaignid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmcampaignPK[ campaignid=" + campaignid + ", pubkey=" + pubkey + " ]";
    }

}
