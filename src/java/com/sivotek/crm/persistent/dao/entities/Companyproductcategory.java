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
    @NamedQuery(name = "Companyproductcategory.findAll", query = "SELECT c FROM Companyproductcategory c"),
    @NamedQuery(name = "Companyproductcategory.findById", query = "SELECT c FROM Companyproductcategory c WHERE c.companyproductcategoryPK.id = :id"),
    @NamedQuery(name = "Companyproductcategory.findByPubkey", query = "SELECT c FROM Companyproductcategory c WHERE c.companyproductcategoryPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companyproductcategory.findByProductcategory", query = "SELECT c FROM Companyproductcategory c WHERE c.productcategory = :productcategory"),
    @NamedQuery(name = "Companyproductcategory.findByCreateddate", query = "SELECT c FROM Companyproductcategory c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companyproductcategory.findByCreatedfrom", query = "SELECT c FROM Companyproductcategory c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companyproductcategory.findByChangeddate", query = "SELECT c FROM Companyproductcategory c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companyproductcategory.findByChangedfrom", query = "SELECT c FROM Companyproductcategory c WHERE c.changedfrom = :changedfrom")})
public class Companyproductcategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyproductcategoryPK companyproductcategoryPK;
    @Size(max = 255)
    @Column(length = 255)
    private String productcategory;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyproductcategory")
    private Collection<Companyproduct> companyproductCollection;
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

    public Companyproductcategory() {
    }

    public Companyproductcategory(CompanyproductcategoryPK companyproductcategoryPK) {
        this.companyproductcategoryPK = companyproductcategoryPK;
    }

    public Companyproductcategory(int id, int pubkey) {
        this.companyproductcategoryPK = new CompanyproductcategoryPK(id, pubkey);
    }

    public CompanyproductcategoryPK getCompanyproductcategoryPK() {
        return companyproductcategoryPK;
    }

    public void setCompanyproductcategoryPK(CompanyproductcategoryPK companyproductcategoryPK) {
        this.companyproductcategoryPK = companyproductcategoryPK;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
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
    public Collection<Companyproduct> getCompanyproductCollection() {
        return companyproductCollection;
    }

    public void setCompanyproductCollection(Collection<Companyproduct> companyproductCollection) {
        this.companyproductCollection = companyproductCollection;
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
        hash += (companyproductcategoryPK != null ? companyproductcategoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyproductcategory)) {
            return false;
        }
        Companyproductcategory other = (Companyproductcategory) object;
        if ((this.companyproductcategoryPK == null && other.companyproductcategoryPK != null) || (this.companyproductcategoryPK != null && !this.companyproductcategoryPK.equals(other.companyproductcategoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companyproductcategory[ companyproductcategoryPK=" + companyproductcategoryPK + " ]";
    }

}
