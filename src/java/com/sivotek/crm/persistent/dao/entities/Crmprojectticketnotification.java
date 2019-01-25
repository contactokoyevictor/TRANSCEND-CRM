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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Crmprojectticketnotification.findAll", query = "SELECT c FROM Crmprojectticketnotification c"),
    @NamedQuery(name = "Crmprojectticketnotification.findById", query = "SELECT c FROM Crmprojectticketnotification c WHERE c.crmprojectticketnotificationPK.id = :id"),
    @NamedQuery(name = "Crmprojectticketnotification.findByPubkey", query = "SELECT c FROM Crmprojectticketnotification c WHERE c.crmprojectticketnotificationPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmprojectticketnotification.findByCreateddate", query = "SELECT c FROM Crmprojectticketnotification c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmprojectticketnotification.findByCreatedfrom", query = "SELECT c FROM Crmprojectticketnotification c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmprojectticketnotification.findByChangeddate", query = "SELECT c FROM Crmprojectticketnotification c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmprojectticketnotification.findByChangedfrom", query = "SELECT c FROM Crmprojectticketnotification c WHERE c.changedfrom = :changedfrom")})
public class Crmprojectticketnotification implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmprojectticketnotificationPK crmprojectticketnotificationPK;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String notificationheader;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String notificationbody;
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
        @JoinColumn(name = "ISSUEID", referencedColumnName = "ISSUEID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmprojectticketmanagement crmprojectticketmanagement;
    @JoinColumns({
        @JoinColumn(name = "NOTIFYEMPLOYEEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "MESSAGE_CHANNELID", referencedColumnName = "CHANNELID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmmessagechannel crmmessagechannel;
    @JoinColumns({
        @JoinColumn(name = "MESSAGE_CHANNEL_TEMPLATEID", referencedColumnName = "CHANNELTEMPLATEID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmmessagechanneltemplate crmmessagechanneltemplate;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee2;

    public Crmprojectticketnotification() {
    }

    public Crmprojectticketnotification(CrmprojectticketnotificationPK crmprojectticketnotificationPK) {
        this.crmprojectticketnotificationPK = crmprojectticketnotificationPK;
    }

    public Crmprojectticketnotification(int id, int pubkey) {
        this.crmprojectticketnotificationPK = new CrmprojectticketnotificationPK(id, pubkey);
    }

    public CrmprojectticketnotificationPK getCrmprojectticketnotificationPK() {
        return crmprojectticketnotificationPK;
    }

    public void setCrmprojectticketnotificationPK(CrmprojectticketnotificationPK crmprojectticketnotificationPK) {
        this.crmprojectticketnotificationPK = crmprojectticketnotificationPK;
    }

    public String getNotificationheader() {
        return notificationheader;
    }

    public void setNotificationheader(String notificationheader) {
        this.notificationheader = notificationheader;
    }

    public String getNotificationbody() {
        return notificationbody;
    }

    public void setNotificationbody(String notificationbody) {
        this.notificationbody = notificationbody;
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

    public Crmprojectticketmanagement getCrmprojectticketmanagement() {
        return crmprojectticketmanagement;
    }

    public void setCrmprojectticketmanagement(Crmprojectticketmanagement crmprojectticketmanagement) {
        this.crmprojectticketmanagement = crmprojectticketmanagement;
    }

    public Companyemployee getCompanyemployee() {
        return companyemployee;
    }

    public void setCompanyemployee(Companyemployee companyemployee) {
        this.companyemployee = companyemployee;
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

    public Companyemployee getCompanyemployee1() {
        return companyemployee1;
    }

    public void setCompanyemployee1(Companyemployee companyemployee1) {
        this.companyemployee1 = companyemployee1;
    }

    public Companyemployee getCompanyemployee2() {
        return companyemployee2;
    }

    public void setCompanyemployee2(Companyemployee companyemployee2) {
        this.companyemployee2 = companyemployee2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmprojectticketnotificationPK != null ? crmprojectticketnotificationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmprojectticketnotification)) {
            return false;
        }
        Crmprojectticketnotification other = (Crmprojectticketnotification) object;
        if ((this.crmprojectticketnotificationPK == null && other.crmprojectticketnotificationPK != null) || (this.crmprojectticketnotificationPK != null && !this.crmprojectticketnotificationPK.equals(other.crmprojectticketnotificationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmprojectticketnotification[ crmprojectticketnotificationPK=" + crmprojectticketnotificationPK + " ]";
    }

}
