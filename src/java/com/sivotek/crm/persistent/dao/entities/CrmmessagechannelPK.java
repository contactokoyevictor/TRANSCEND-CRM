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
public class CrmmessagechannelPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int channelid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmmessagechannelPK() {
    }

    public CrmmessagechannelPK(int channelid, int pubkey) {
        this.channelid = channelid;
        this.pubkey = pubkey;
    }

    public int getChannelid() {
        return channelid;
    }

    public void setChannelid(int channelid) {
        this.channelid = channelid;
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
        hash += (int) channelid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmmessagechannelPK)) {
            return false;
        }
        CrmmessagechannelPK other = (CrmmessagechannelPK) object;
        if (this.channelid != other.channelid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmmessagechannelPK[ channelid=" + channelid + ", pubkey=" + pubkey + " ]";
    }

}
