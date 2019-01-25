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
    @NamedQuery(name = "Crmcampaignreceiver.findAll", query = "SELECT c FROM Crmcampaignreceiver c"),
    @NamedQuery(name = "Crmcampaignreceiver.findByReceiverid", query = "SELECT c FROM Crmcampaignreceiver c WHERE c.crmcampaignreceiverPK.receiverid = :receiverid"),
    @NamedQuery(name = "Crmcampaignreceiver.findByPubkey", query = "SELECT c FROM Crmcampaignreceiver c WHERE c.crmcampaignreceiverPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmcampaignreceiver.findByCreateddate", query = "SELECT c FROM Crmcampaignreceiver c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmcampaignreceiver.findByCreatedfrom", query = "SELECT c FROM Crmcampaignreceiver c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmcampaignreceiver.findByChangeddate", query = "SELECT c FROM Crmcampaignreceiver c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmcampaignreceiver.findByChangedfrom", query = "SELECT c FROM Crmcampaignreceiver c WHERE c.changedfrom = :changedfrom")})
public class Crmcampaignreceiver implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmcampaignreceiverPK crmcampaignreceiverPK;
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
        @JoinColumn(name = "CAMPAIGNID", referencedColumnName = "CAMPAIGNID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmcampaign crmcampaign;
    @JoinColumns({
        @JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companycustomer companycustomer;
    @JoinColumns({
        @JoinColumn(name = "MESSAGECHANNELID", referencedColumnName = "CHANNELID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmmessagechannel crmmessagechannel;
    @JoinColumns({
        @JoinColumn(name = "MESSAGECHANNELTEMPL", referencedColumnName = "CHANNELTEMPLATEID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmmessagechanneltemplate crmmessagechanneltemplate;
    @JoinColumns({
        @JoinColumn(name = "CUSTOMERCONTACTID", referencedColumnName = "CONTACTID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Customercontacts customercontacts;
    @JoinColumns({
        @JoinColumn(name = "CUSTOMERCONTACTADDRESSID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Customercontactsaddress customercontactsaddress;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;

    public Crmcampaignreceiver() {
    }

    public Crmcampaignreceiver(CrmcampaignreceiverPK crmcampaignreceiverPK) {
        this.crmcampaignreceiverPK = crmcampaignreceiverPK;
    }

    public Crmcampaignreceiver(int receiverid, int pubkey) {
        this.crmcampaignreceiverPK = new CrmcampaignreceiverPK(receiverid, pubkey);
    }

    public CrmcampaignreceiverPK getCrmcampaignreceiverPK() {
        return crmcampaignreceiverPK;
    }

    public void setCrmcampaignreceiverPK(CrmcampaignreceiverPK crmcampaignreceiverPK) {
        this.crmcampaignreceiverPK = crmcampaignreceiverPK;
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

    public Crmcampaign getCrmcampaign() {
        return crmcampaign;
    }

    public void setCrmcampaign(Crmcampaign crmcampaign) {
        this.crmcampaign = crmcampaign;
    }

    public Companycustomer getCompanycustomer() {
        return companycustomer;
    }

    public void setCompanycustomer(Companycustomer companycustomer) {
        this.companycustomer = companycustomer;
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

    public Customercontacts getCustomercontacts() {
        return customercontacts;
    }

    public void setCustomercontacts(Customercontacts customercontacts) {
        this.customercontacts = customercontacts;
    }

    public Customercontactsaddress getCustomercontactsaddress() {
        return customercontactsaddress;
    }

    public void setCustomercontactsaddress(Customercontactsaddress customercontactsaddress) {
        this.customercontactsaddress = customercontactsaddress;
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
        hash += (crmcampaignreceiverPK != null ? crmcampaignreceiverPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmcampaignreceiver)) {
            return false;
        }
        Crmcampaignreceiver other = (Crmcampaignreceiver) object;
        if ((this.crmcampaignreceiverPK == null && other.crmcampaignreceiverPK != null) || (this.crmcampaignreceiverPK != null && !this.crmcampaignreceiverPK.equals(other.crmcampaignreceiverPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmcampaignreceiver[ crmcampaignreceiverPK=" + crmcampaignreceiverPK + " ]";
    }

}
