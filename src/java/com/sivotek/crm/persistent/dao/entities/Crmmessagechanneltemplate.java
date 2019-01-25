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
    @NamedQuery(name = "Crmmessagechanneltemplate.findAll", query = "SELECT c FROM Crmmessagechanneltemplate c"),
    @NamedQuery(name = "Crmmessagechanneltemplate.findByChanneltemplateid", query = "SELECT c FROM Crmmessagechanneltemplate c WHERE c.crmmessagechanneltemplatePK.channeltemplateid = :channeltemplateid"),
    @NamedQuery(name = "Crmmessagechanneltemplate.findByPubkey", query = "SELECT c FROM Crmmessagechanneltemplate c WHERE c.crmmessagechanneltemplatePK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmmessagechanneltemplate.findByTemplateName", query = "SELECT c FROM Crmmessagechanneltemplate c WHERE c.templateName = :templateName"),
    @NamedQuery(name = "Crmmessagechanneltemplate.findByCreateddate", query = "SELECT c FROM Crmmessagechanneltemplate c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmmessagechanneltemplate.findByCreatedfrom", query = "SELECT c FROM Crmmessagechanneltemplate c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmmessagechanneltemplate.findByChangeddate", query = "SELECT c FROM Crmmessagechanneltemplate c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmmessagechanneltemplate.findByChangedfrom", query = "SELECT c FROM Crmmessagechanneltemplate c WHERE c.changedfrom = :changedfrom")})
public class Crmmessagechanneltemplate implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmmessagechanneltemplatePK crmmessagechanneltemplatePK;
    @Size(max = 255)
    @Column(name = "TEMPLATE_NAME", length = 255)
    private String templateName;
    @Lob
    @Size(max = 65535)
    @Column(name = "TEMPLATE_DESCRIPTION", length = 65535)
    private String templateDescription;
    @Lob
    @Size(max = 65535)
    @Column(name = "MESSAGE_BODY", length = 65535)
    private String messageBody;
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
        @JoinColumn(name = "MESSAGE_CHANNEL_ID", referencedColumnName = "CHANNELID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmmessagechannel crmmessagechannel;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmmessagechanneltemplate")
    private Collection<Crmcampaignreceiver> crmcampaignreceiverCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmmessagechanneltemplate")
    private Collection<Crmmessagehistory> crmmessagehistoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmmessagechanneltemplate")
    private Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection;

    public Crmmessagechanneltemplate() {
    }

    public Crmmessagechanneltemplate(CrmmessagechanneltemplatePK crmmessagechanneltemplatePK) {
        this.crmmessagechanneltemplatePK = crmmessagechanneltemplatePK;
    }

    public Crmmessagechanneltemplate(int channeltemplateid, int pubkey) {
        this.crmmessagechanneltemplatePK = new CrmmessagechanneltemplatePK(channeltemplateid, pubkey);
    }

    public CrmmessagechanneltemplatePK getCrmmessagechanneltemplatePK() {
        return crmmessagechanneltemplatePK;
    }

    public void setCrmmessagechanneltemplatePK(CrmmessagechanneltemplatePK crmmessagechanneltemplatePK) {
        this.crmmessagechanneltemplatePK = crmmessagechanneltemplatePK;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
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
        hash += (crmmessagechanneltemplatePK != null ? crmmessagechanneltemplatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmmessagechanneltemplate)) {
            return false;
        }
        Crmmessagechanneltemplate other = (Crmmessagechanneltemplate) object;
        if ((this.crmmessagechanneltemplatePK == null && other.crmmessagechanneltemplatePK != null) || (this.crmmessagechanneltemplatePK != null && !this.crmmessagechanneltemplatePK.equals(other.crmmessagechanneltemplatePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmmessagechanneltemplate[ crmmessagechanneltemplatePK=" + crmmessagechanneltemplatePK + " ]";
    }

}
