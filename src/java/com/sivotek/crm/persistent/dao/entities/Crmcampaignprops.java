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
    @NamedQuery(name = "Crmcampaignprops.findAll", query = "SELECT c FROM Crmcampaignprops c"),
    @NamedQuery(name = "Crmcampaignprops.findById", query = "SELECT c FROM Crmcampaignprops c WHERE c.crmcampaignpropsPK.id = :id"),
    @NamedQuery(name = "Crmcampaignprops.findByPubkey", query = "SELECT c FROM Crmcampaignprops c WHERE c.crmcampaignpropsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmcampaignprops.findByName", query = "SELECT c FROM Crmcampaignprops c WHERE c.name = :name"),
    @NamedQuery(name = "Crmcampaignprops.findByValue", query = "SELECT c FROM Crmcampaignprops c WHERE c.value = :value"),
    @NamedQuery(name = "Crmcampaignprops.findByCreateddate", query = "SELECT c FROM Crmcampaignprops c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmcampaignprops.findByCreatedfrom", query = "SELECT c FROM Crmcampaignprops c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmcampaignprops.findByChangeddate", query = "SELECT c FROM Crmcampaignprops c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmcampaignprops.findByChangedfrom", query = "SELECT c FROM Crmcampaignprops c WHERE c.changedfrom = :changedfrom")})
public class Crmcampaignprops implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmcampaignpropsPK crmcampaignpropsPK;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Size(max = 255)
    @Column(length = 255)
    private String value;
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
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;

    public Crmcampaignprops() {
    }

    public Crmcampaignprops(CrmcampaignpropsPK crmcampaignpropsPK) {
        this.crmcampaignpropsPK = crmcampaignpropsPK;
    }

    public Crmcampaignprops(int id, int pubkey) {
        this.crmcampaignpropsPK = new CrmcampaignpropsPK(id, pubkey);
    }

    public CrmcampaignpropsPK getCrmcampaignpropsPK() {
        return crmcampaignpropsPK;
    }

    public void setCrmcampaignpropsPK(CrmcampaignpropsPK crmcampaignpropsPK) {
        this.crmcampaignpropsPK = crmcampaignpropsPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        hash += (crmcampaignpropsPK != null ? crmcampaignpropsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmcampaignprops)) {
            return false;
        }
        Crmcampaignprops other = (Crmcampaignprops) object;
        if ((this.crmcampaignpropsPK == null && other.crmcampaignpropsPK != null) || (this.crmcampaignpropsPK != null && !this.crmcampaignpropsPK.equals(other.crmcampaignpropsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmcampaignprops[ crmcampaignpropsPK=" + crmcampaignpropsPK + " ]";
    }

}
