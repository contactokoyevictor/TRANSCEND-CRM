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
    @NamedQuery(name = "Companypaymentscheme.findAll", query = "SELECT c FROM Companypaymentscheme c"),
    @NamedQuery(name = "Companypaymentscheme.findById", query = "SELECT c FROM Companypaymentscheme c WHERE c.companypaymentschemePK.id = :id"),
    @NamedQuery(name = "Companypaymentscheme.findByPubkey", query = "SELECT c FROM Companypaymentscheme c WHERE c.companypaymentschemePK.pubkey = :pubkey"),
    @NamedQuery(name = "Companypaymentscheme.findBySchemeName", query = "SELECT c FROM Companypaymentscheme c WHERE c.schemeName = :schemeName"),
    @NamedQuery(name = "Companypaymentscheme.findByCreateddate", query = "SELECT c FROM Companypaymentscheme c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companypaymentscheme.findByCreatedfrom", query = "SELECT c FROM Companypaymentscheme c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companypaymentscheme.findByChangeddate", query = "SELECT c FROM Companypaymentscheme c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companypaymentscheme.findByChangedfrom", query = "SELECT c FROM Companypaymentscheme c WHERE c.changedfrom = :changedfrom")})
public class Companypaymentscheme implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanypaymentschemePK companypaymentschemePK;
    @Size(max = 255)
    @Column(name = "SCHEME_NAME", length = 255)
    private String schemeName;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companypaymentscheme")
    private Collection<Companypayments> companypaymentsCollection;
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

    public Companypaymentscheme() {
    }

    public Companypaymentscheme(CompanypaymentschemePK companypaymentschemePK) {
        this.companypaymentschemePK = companypaymentschemePK;
    }

    public Companypaymentscheme(int id, int pubkey) {
        this.companypaymentschemePK = new CompanypaymentschemePK(id, pubkey);
    }

    public CompanypaymentschemePK getCompanypaymentschemePK() {
        return companypaymentschemePK;
    }

    public void setCompanypaymentschemePK(CompanypaymentschemePK companypaymentschemePK) {
        this.companypaymentschemePK = companypaymentschemePK;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public Collection<Companypayments> getCompanypaymentsCollection() {
        return companypaymentsCollection;
    }

    public void setCompanypaymentsCollection(Collection<Companypayments> companypaymentsCollection) {
        this.companypaymentsCollection = companypaymentsCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companypaymentschemePK != null ? companypaymentschemePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companypaymentscheme)) {
            return false;
        }
        Companypaymentscheme other = (Companypaymentscheme) object;
        if ((this.companypaymentschemePK == null && other.companypaymentschemePK != null) || (this.companypaymentschemePK != null && !this.companypaymentschemePK.equals(other.companypaymentschemePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companypaymentscheme[ companypaymentschemePK=" + companypaymentschemePK + " ]";
    }

}
