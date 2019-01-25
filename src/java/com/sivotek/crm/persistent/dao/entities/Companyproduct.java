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
    @NamedQuery(name = "Companyproduct.findAll", query = "SELECT c FROM Companyproduct c"),
    @NamedQuery(name = "Companyproduct.findById", query = "SELECT c FROM Companyproduct c WHERE c.companyproductPK.id = :id"),
    @NamedQuery(name = "Companyproduct.findByPubkey", query = "SELECT c FROM Companyproduct c WHERE c.companyproductPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companyproduct.findByProductname", query = "SELECT c FROM Companyproduct c WHERE c.productname = :productname"),
    @NamedQuery(name = "Companyproduct.findByManufacturer", query = "SELECT c FROM Companyproduct c WHERE c.manufacturer = :manufacturer"),
    @NamedQuery(name = "Companyproduct.findBySerialnumber", query = "SELECT c FROM Companyproduct c WHERE c.serialnumber = :serialnumber"),
    @NamedQuery(name = "Companyproduct.findByCreateddate", query = "SELECT c FROM Companyproduct c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companyproduct.findByCreatedfrom", query = "SELECT c FROM Companyproduct c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companyproduct.findByChangeddate", query = "SELECT c FROM Companyproduct c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companyproduct.findByChangedfrom", query = "SELECT c FROM Companyproduct c WHERE c.changedfrom = :changedfrom")})
public class Companyproduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyproductPK companyproductPK;
    @Size(max = 255)
    @Column(length = 255)
    private String productname;
    @Size(max = 255)
    @Column(length = 255)
    private String manufacturer;
    @Size(max = 255)
    @Column(length = 255)
    private String serialnumber;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyproduct")
    private Collection<Crmcampaignposition> crmcampaignpositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyproduct")
    private Collection<Crmworkorder> crmworkorderCollection;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "typeid", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyproducttype companyproducttype;
    @JoinColumns({
        @JoinColumn(name = "categoryid", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyproductcategory companyproductcategory;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyproduct")
    private Collection<Productcomponents> productcomponentsCollection;

    public Companyproduct() {
    }

    public Companyproduct(CompanyproductPK companyproductPK) {
        this.companyproductPK = companyproductPK;
    }

    public Companyproduct(int id, int pubkey) {
        this.companyproductPK = new CompanyproductPK(id, pubkey);
    }

    public CompanyproductPK getCompanyproductPK() {
        return companyproductPK;
    }

    public void setCompanyproductPK(CompanyproductPK companyproductPK) {
        this.companyproductPK = companyproductPK;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
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
    public Collection<Crmcampaignposition> getCrmcampaignpositionCollection() {
        return crmcampaignpositionCollection;
    }

    public void setCrmcampaignpositionCollection(Collection<Crmcampaignposition> crmcampaignpositionCollection) {
        this.crmcampaignpositionCollection = crmcampaignpositionCollection;
    }

    @XmlTransient
    public Collection<Crmworkorder> getCrmworkorderCollection() {
        return crmworkorderCollection;
    }

    public void setCrmworkorderCollection(Collection<Crmworkorder> crmworkorderCollection) {
        this.crmworkorderCollection = crmworkorderCollection;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Companyproducttype getCompanyproducttype() {
        return companyproducttype;
    }

    public void setCompanyproducttype(Companyproducttype companyproducttype) {
        this.companyproducttype = companyproducttype;
    }

    public Companyproductcategory getCompanyproductcategory() {
        return companyproductcategory;
    }

    public void setCompanyproductcategory(Companyproductcategory companyproductcategory) {
        this.companyproductcategory = companyproductcategory;
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
    public Collection<Productcomponents> getProductcomponentsCollection() {
        return productcomponentsCollection;
    }

    public void setProductcomponentsCollection(Collection<Productcomponents> productcomponentsCollection) {
        this.productcomponentsCollection = productcomponentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyproductPK != null ? companyproductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyproduct)) {
            return false;
        }
        Companyproduct other = (Companyproduct) object;
        if ((this.companyproductPK == null && other.companyproductPK != null) || (this.companyproductPK != null && !this.companyproductPK.equals(other.companyproductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companyproduct[ companyproductPK=" + companyproductPK + " ]";
    }

}
