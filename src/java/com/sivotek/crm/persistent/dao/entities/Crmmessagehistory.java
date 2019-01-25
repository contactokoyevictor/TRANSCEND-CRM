/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author okoyevictor
 */
@Entity
@Table(catalog = "sivotekcrm_", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crmmessagehistory.findAll", query = "SELECT c FROM Crmmessagehistory c"),
    @NamedQuery(name = "Crmmessagehistory.findById", query = "SELECT c FROM Crmmessagehistory c WHERE c.crmmessagehistoryPK.id = :id"),
    @NamedQuery(name = "Crmmessagehistory.findByPubkey", query = "SELECT c FROM Crmmessagehistory c WHERE c.crmmessagehistoryPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmmessagehistory.findByRecepient", query = "SELECT c FROM Crmmessagehistory c WHERE c.recepient = :recepient"),
    @NamedQuery(name = "Crmmessagehistory.findBySentDate", query = "SELECT c FROM Crmmessagehistory c WHERE c.sentDate = :sentDate"),
    @NamedQuery(name = "Crmmessagehistory.findByCreateddate", query = "SELECT c FROM Crmmessagehistory c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmmessagehistory.findByCreatedfrom", query = "SELECT c FROM Crmmessagehistory c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmmessagehistory.findByChangeddate", query = "SELECT c FROM Crmmessagehistory c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmmessagehistory.findByChangedfrom", query = "SELECT c FROM Crmmessagehistory c WHERE c.changedfrom = :changedfrom")})
public class Crmmessagehistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmmessagehistoryPK crmmessagehistoryPK;
    @Size(max = 255)
    @Column(length = 255)
    private String recepient;
    @Column(name = "SENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Size(max = 150)
    @Column(length = 150)
    private String createdfrom;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @Size(max = 150)
    @Column(length = 150)
    private String changedfrom;
    @JoinColumns({
        @JoinColumn(name = "CHANNEL_ID", referencedColumnName = "CHANNELID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmmessagechannel crmmessagechannel;
    @JoinColumns({
        @JoinColumn(name = "MESSAGE_TEMPLATE_ID", referencedColumnName = "CHANNELTEMPLATEID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmmessagechanneltemplate crmmessagechanneltemplate;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;

    public Crmmessagehistory() {
    }

    public Crmmessagehistory(CrmmessagehistoryPK crmmessagehistoryPK) {
        this.crmmessagehistoryPK = crmmessagehistoryPK;
    }

    public Crmmessagehistory(int id, int pubkey) {
        this.crmmessagehistoryPK = new CrmmessagehistoryPK(id, pubkey);
    }

    public CrmmessagehistoryPK getCrmmessagehistoryPK() {
        return crmmessagehistoryPK;
    }

    public void setCrmmessagehistoryPK(CrmmessagehistoryPK crmmessagehistoryPK) {
        this.crmmessagehistoryPK = crmmessagehistoryPK;
    }

    public String getRecepient() {
        return recepient;
    }

    public void setRecepient(String recepient) {
        this.recepient = recepient;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getCreatedfrom() {
        return createdfrom;
    }

    public void setCreatedfrom(String createdfrom) {
        this.createdfrom = createdfrom;
    }

    public Date getChangeddate() {
        return changeddate;
    }

    public void setChangeddate(Date changeddate) {
        this.changeddate = changeddate;
    }

    public String getChangedfrom() {
        return changedfrom;
    }

    public void setChangedfrom(String changedfrom) {
        this.changedfrom = changedfrom;
    }

    public Crmmessagechannel getCrmmessagechannel() {
        return crmmessagechannel;
    }

    public void setCrmmessagechannel(Crmmessagechannel crmmessagechannel) {
        this.crmmessagechannel = crmmessagechannel;
    }

    public Crmmessagechanneltemplate getCrmmessagechanneltemplate() {
        return crmmessagechanneltemplate;
    }

    public void setCrmmessagechanneltemplate(Crmmessagechanneltemplate crmmessagechanneltemplate) {
        this.crmmessagechanneltemplate = crmmessagechanneltemplate;
    }

    public Companyemployee getCompanyemployee() {
        return companyemployee;
    }

    public void setCompanyemployee(Companyemployee companyemployee) {
        this.companyemployee = companyemployee;
    }

    public Companyemployee getCompanyemployee1() {
        return companyemployee1;
    }

    public void setCompanyemployee1(Companyemployee companyemployee1) {
        this.companyemployee1 = companyemployee1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmmessagehistoryPK != null ? crmmessagehistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmmessagehistory)) {
            return false;
        }
        Crmmessagehistory other = (Crmmessagehistory) object;
        if ((this.crmmessagehistoryPK == null && other.crmmessagehistoryPK != null) || (this.crmmessagehistoryPK != null && !this.crmmessagehistoryPK.equals(other.crmmessagehistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmmessagehistory[ crmmessagehistoryPK=" + crmmessagehistoryPK + " ]";
    }

}
