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
public class CrmmessagechanneltemplatePK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int channeltemplateid;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int pubkey;

    public CrmmessagechanneltemplatePK() {
    }

    public CrmmessagechanneltemplatePK(int channeltemplateid, int pubkey) {
        this.channeltemplateid = channeltemplateid;
        this.pubkey = pubkey;
    }

    public int getChanneltemplateid() {
        return channeltemplateid;
    }

    public void setChanneltemplateid(int channeltemplateid) {
        this.channeltemplateid = channeltemplateid;
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
        hash += (int) channeltemplateid;
        hash += (int) pubkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrmmessagechanneltemplatePK)) {
            return false;
        }
        CrmmessagechanneltemplatePK other = (CrmmessagechanneltemplatePK) object;
        if (this.channeltemplateid != other.channeltemplateid) {
            return false;
        }
        if (this.pubkey != other.pubkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.CrmmessagechanneltemplatePK[ channeltemplateid=" + channeltemplateid + ", pubkey=" + pubkey + " ]";
    }

}
