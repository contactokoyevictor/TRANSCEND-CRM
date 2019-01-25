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
    @NamedQuery(name = "Crmcampaign.findAll", query = "SELECT c FROM Crmcampaign c"),
    @NamedQuery(name = "Crmcampaign.findByCampaignid", query = "SELECT c FROM Crmcampaign c WHERE c.crmcampaignPK.campaignid = :campaignid"),
    @NamedQuery(name = "Crmcampaign.findByPubkey", query = "SELECT c FROM Crmcampaign c WHERE c.crmcampaignPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmcampaign.findByName", query = "SELECT c FROM Crmcampaign c WHERE c.name = :name"),
    @NamedQuery(name = "Crmcampaign.findByValidfrom", query = "SELECT c FROM Crmcampaign c WHERE c.validfrom = :validfrom"),
    @NamedQuery(name = "Crmcampaign.findByValidto", query = "SELECT c FROM Crmcampaign c WHERE c.validto = :validto"),
    @NamedQuery(name = "Crmcampaign.findByCreateddate", query = "SELECT c FROM Crmcampaign c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmcampaign.findByCreatedfrom", query = "SELECT c FROM Crmcampaign c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmcampaign.findByChangeddate", query = "SELECT c FROM Crmcampaign c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmcampaign.findByChangedfrom", query = "SELECT c FROM Crmcampaign c WHERE c.changedfrom = :changedfrom")})
public class Crmcampaign implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmcampaignPK crmcampaignPK;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date validfrom;
    @Temporal(TemporalType.TIMESTAMP)
    private Date validto;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmcampaign")
    private Collection<Crmcampaignreceiver> crmcampaignreceiverCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmcampaign")
    private Collection<Crmcampaignposition> crmcampaignpositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmcampaign")
    private Collection<Crmcampaigndocs> crmcampaigndocsCollection;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "CAMPAIGNSTATUS", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmcampaignstatus crmcampaignstatus;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmcampaign")
    private Collection<Crmscheduler> crmschedulerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmcampaign")
    private Collection<Crmcampaignprops> crmcampaignpropsCollection;

    public Crmcampaign() {
    }

    public Crmcampaign(CrmcampaignPK crmcampaignPK) {
        this.crmcampaignPK = crmcampaignPK;
    }

    public Crmcampaign(int campaignid, int pubkey) {
        this.crmcampaignPK = new CrmcampaignPK(campaignid, pubkey);
    }

    public CrmcampaignPK getCrmcampaignPK() {
        return crmcampaignPK;
    }

    public void setCrmcampaignPK(CrmcampaignPK crmcampaignPK) {
        this.crmcampaignPK = crmcampaignPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(Date validfrom) {
        this.validfrom = validfrom;
    }

    public Date getValidto() {
        return validto;
    }

    public void setValidto(Date validto) {
        this.validto = validto;
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

    @XmlTransient
    public Collection<Crmcampaignreceiver> getCrmcampaignreceiverCollection() {
        return crmcampaignreceiverCollection;
    }

    public void setCrmcampaignreceiverCollection(Collection<Crmcampaignreceiver> crmcampaignreceiverCollection) {
        this.crmcampaignreceiverCollection = crmcampaignreceiverCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignposition> getCrmcampaignpositionCollection() {
        return crmcampaignpositionCollection;
    }

    public void setCrmcampaignpositionCollection(Collection<Crmcampaignposition> crmcampaignpositionCollection) {
        this.crmcampaignpositionCollection = crmcampaignpositionCollection;
    }

    @XmlTransient
    public Collection<Crmcampaigndocs> getCrmcampaigndocsCollection() {
        return crmcampaigndocsCollection;
    }

    public void setCrmcampaigndocsCollection(Collection<Crmcampaigndocs> crmcampaigndocsCollection) {
        this.crmcampaigndocsCollection = crmcampaigndocsCollection;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Crmcampaignstatus getCrmcampaignstatus() {
        return crmcampaignstatus;
    }

    public void setCrmcampaignstatus(Crmcampaignstatus crmcampaignstatus) {
        this.crmcampaignstatus = crmcampaignstatus;
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
    public Collection<Crmscheduler> getCrmschedulerCollection() {
        return crmschedulerCollection;
    }

    public void setCrmschedulerCollection(Collection<Crmscheduler> crmschedulerCollection) {
        this.crmschedulerCollection = crmschedulerCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignprops> getCrmcampaignpropsCollection() {
        return crmcampaignpropsCollection;
    }

    public void setCrmcampaignpropsCollection(Collection<Crmcampaignprops> crmcampaignpropsCollection) {
        this.crmcampaignpropsCollection = crmcampaignpropsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmcampaignPK != null ? crmcampaignPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmcampaign)) {
            return false;
        }
        Crmcampaign other = (Crmcampaign) object;
        if ((this.crmcampaignPK == null && other.crmcampaignPK != null) || (this.crmcampaignPK != null && !this.crmcampaignPK.equals(other.crmcampaignPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmcampaign[ crmcampaignPK=" + crmcampaignPK + " ]";
    }

}
