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
    @NamedQuery(name = "Crmcampaignstatus.findAll", query = "SELECT c FROM Crmcampaignstatus c"),
    @NamedQuery(name = "Crmcampaignstatus.findById", query = "SELECT c FROM Crmcampaignstatus c WHERE c.crmcampaignstatusPK.id = :id"),
    @NamedQuery(name = "Crmcampaignstatus.findByPubkey", query = "SELECT c FROM Crmcampaignstatus c WHERE c.crmcampaignstatusPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmcampaignstatus.findByStatusname", query = "SELECT c FROM Crmcampaignstatus c WHERE c.statusname = :statusname"),
    @NamedQuery(name = "Crmcampaignstatus.findByCreateddate", query = "SELECT c FROM Crmcampaignstatus c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmcampaignstatus.findByCreatedfrom", query = "SELECT c FROM Crmcampaignstatus c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmcampaignstatus.findByChangeddate", query = "SELECT c FROM Crmcampaignstatus c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmcampaignstatus.findByChangedfrom", query = "SELECT c FROM Crmcampaignstatus c WHERE c.changedfrom = :changedfrom")})
public class Crmcampaignstatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmcampaignstatusPK crmcampaignstatusPK;
    @Size(max = 150)
    @Column(length = 150)
    private String statusname;
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
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmcampaignstatus")
    private Collection<Crmcampaign> crmcampaignCollection;

    public Crmcampaignstatus() {
    }

    public Crmcampaignstatus(CrmcampaignstatusPK crmcampaignstatusPK) {
        this.crmcampaignstatusPK = crmcampaignstatusPK;
    }

    public Crmcampaignstatus(int id, int pubkey) {
        this.crmcampaignstatusPK = new CrmcampaignstatusPK(id, pubkey);
    }

    public CrmcampaignstatusPK getCrmcampaignstatusPK() {
        return crmcampaignstatusPK;
    }

    public void setCrmcampaignstatusPK(CrmcampaignstatusPK crmcampaignstatusPK) {
        this.crmcampaignstatusPK = crmcampaignstatusPK;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
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

    @XmlTransient
    public Collection<Crmcampaign> getCrmcampaignCollection() {
        return crmcampaignCollection;
    }

    public void setCrmcampaignCollection(Collection<Crmcampaign> crmcampaignCollection) {
        this.crmcampaignCollection = crmcampaignCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmcampaignstatusPK != null ? crmcampaignstatusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmcampaignstatus)) {
            return false;
        }
        Crmcampaignstatus other = (Crmcampaignstatus) object;
        if ((this.crmcampaignstatusPK == null && other.crmcampaignstatusPK != null) || (this.crmcampaignstatusPK != null && !this.crmcampaignstatusPK.equals(other.crmcampaignstatusPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmcampaignstatus[ crmcampaignstatusPK=" + crmcampaignstatusPK + " ]";
    }

}
