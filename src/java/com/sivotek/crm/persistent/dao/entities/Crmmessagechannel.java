/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author okoyevictor
 */
@Entity
@Table(catalog = "sivotekcrm_", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crmmessagechannel.findAll", query = "SELECT c FROM Crmmessagechannel c"),
    @NamedQuery(name = "Crmmessagechannel.findByChannelid", query = "SELECT c FROM Crmmessagechannel c WHERE c.crmmessagechannelPK.channelid = :channelid"),
    @NamedQuery(name = "Crmmessagechannel.findByPubkey", query = "SELECT c FROM Crmmessagechannel c WHERE c.crmmessagechannelPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmmessagechannel.findByChannelName", query = "SELECT c FROM Crmmessagechannel c WHERE c.channelName = :channelName"),
    @NamedQuery(name = "Crmmessagechannel.findByCreateddate", query = "SELECT c FROM Crmmessagechannel c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmmessagechannel.findByCreatedfrom", query = "SELECT c FROM Crmmessagechannel c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmmessagechannel.findByChangeddate", query = "SELECT c FROM Crmmessagechannel c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmmessagechannel.findByChangedfrom", query = "SELECT c FROM Crmmessagechannel c WHERE c.changedfrom = :changedfrom")})
public class Crmmessagechannel implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmmessagechannelPK crmmessagechannelPK;
    @Size(max = 255)
    @Column(name = "CHANNEL_NAME", length = 255)
    private String channelName;
    @Lob
    @Size(max = 65535)
    @Column(name = "CHANNEL_DESCRIPTION", length = 65535)
    private String channelDescription;
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
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "EMPLOYEEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmmessagechannel")
    private Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmmessagechannel")
    private Collection<Crmcampaignreceiver> crmcampaignreceiverCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmmessagechannel")
    private Collection<Crmmessagehistory> crmmessagehistoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmmessagechannel")
    private Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection;

    public Crmmessagechannel() {
    }

    public Crmmessagechannel(CrmmessagechannelPK crmmessagechannelPK) {
        this.crmmessagechannelPK = crmmessagechannelPK;
    }

    public Crmmessagechannel(int channelid, int pubkey) {
        this.crmmessagechannelPK = new CrmmessagechannelPK(channelid, pubkey);
    }

    public CrmmessagechannelPK getCrmmessagechannelPK() {
        return crmmessagechannelPK;
    }

    public void setCrmmessagechannelPK(CrmmessagechannelPK crmmessagechannelPK) {
        this.crmmessagechannelPK = crmmessagechannelPK;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public Companyemployee getCompanyemployee2() {
        return companyemployee2;
    }

    public void setCompanyemployee2(Companyemployee companyemployee2) {
        this.companyemployee2 = companyemployee2;
    }

    @XmlTransient
    public Collection<Crmmessagechanneltemplate> getCrmmessagechanneltemplateCollection() {
        return crmmessagechanneltemplateCollection;
    }

    public void setCrmmessagechanneltemplateCollection(Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollection) {
        this.crmmessagechanneltemplateCollection = crmmessagechanneltemplateCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignreceiver> getCrmcampaignreceiverCollection() {
        return crmcampaignreceiverCollection;
    }

    public void setCrmcampaignreceiverCollection(Collection<Crmcampaignreceiver> crmcampaignreceiverCollection) {
        this.crmcampaignreceiverCollection = crmcampaignreceiverCollection;
    }

    @XmlTransient
    public Collection<Crmmessagehistory> getCrmmessagehistoryCollection() {
        return crmmessagehistoryCollection;
    }

    public void setCrmmessagehistoryCollection(Collection<Crmmessagehistory> crmmessagehistoryCollection) {
        this.crmmessagehistoryCollection = crmmessagehistoryCollection;
    }

    @XmlTransient
    public Collection<Crmprojectticketnotification> getCrmprojectticketnotificationCollection() {
        return crmprojectticketnotificationCollection;
    }

    public void setCrmprojectticketnotificationCollection(Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection) {
        this.crmprojectticketnotificationCollection = crmprojectticketnotificationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmmessagechannelPK != null ? crmmessagechannelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmmessagechannel)) {
            return false;
        }
        Crmmessagechannel other = (Crmmessagechannel) object;
        if ((this.crmmessagechannelPK == null && other.crmmessagechannelPK != null) || (this.crmmessagechannelPK != null && !this.crmmessagechannelPK.equals(other.crmmessagechannelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmmessagechannel[ crmmessagechannelPK=" + crmmessagechannelPK + " ]";
    }

}
