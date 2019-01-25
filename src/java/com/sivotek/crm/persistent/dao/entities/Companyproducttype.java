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
    @NamedQuery(name = "Companyproducttype.findAll", query = "SELECT c FROM Companyproducttype c"),
    @NamedQuery(name = "Companyproducttype.findById", query = "SELECT c FROM Companyproducttype c WHERE c.companyproducttypePK.id = :id"),
    @NamedQuery(name = "Companyproducttype.findByPubkey", query = "SELECT c FROM Companyproducttype c WHERE c.companyproducttypePK.pubkey = :pubkey"),
    @NamedQuery(name = "Companyproducttype.findByProducttype", query = "SELECT c FROM Companyproducttype c WHERE c.producttype = :producttype"),
    @NamedQuery(name = "Companyproducttype.findByCreateddate", query = "SELECT c FROM Companyproducttype c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companyproducttype.findByCreatedfrom", query = "SELECT c FROM Companyproducttype c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companyproducttype.findByChangeddate", query = "SELECT c FROM Companyproducttype c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companyproducttype.findByChangedfrom", query = "SELECT c FROM Companyproducttype c WHERE c.changedfrom = :changedfrom")})
public class Companyproducttype implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyproducttypePK companyproducttypePK;
    @Size(max = 255)
    @Column(length = 255)
    private String producttype;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyproducttype")
    private Collection<Companyproduct> companyproductCollection;

    public Companyproducttype() {
    }

    public Companyproducttype(CompanyproducttypePK companyproducttypePK) {
        this.companyproducttypePK = companyproducttypePK;
    }

    public Companyproducttype(int id, int pubkey) {
        this.companyproducttypePK = new CompanyproducttypePK(id, pubkey);
    }

    public CompanyproducttypePK getCompanyproducttypePK() {
        return companyproducttypePK;
    }

    public void setCompanyproducttypePK(CompanyproducttypePK companyproducttypePK) {
        this.companyproducttypePK = companyproducttypePK;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
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
    public Collection<Companyproduct> getCompanyproductCollection() {
        return companyproductCollection;
    }

    public void setCompanyproductCollection(Collection<Companyproduct> companyproductCollection) {
        this.companyproductCollection = companyproductCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyproducttypePK != null ? companyproducttypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyproducttype)) {
            return false;
        }
        Companyproducttype other = (Companyproducttype) object;
        if ((this.companyproducttypePK == null && other.companyproducttypePK != null) || (this.companyproducttypePK != null && !this.companyproducttypePK.equals(other.companyproducttypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companyproducttype[ companyproducttypePK=" + companyproducttypePK + " ]";
    }

}
