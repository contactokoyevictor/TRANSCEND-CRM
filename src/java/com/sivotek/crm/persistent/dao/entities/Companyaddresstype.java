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
    @NamedQuery(name = "Companyaddresstype.findAll", query = "SELECT c FROM Companyaddresstype c"),
    @NamedQuery(name = "Companyaddresstype.findById", query = "SELECT c FROM Companyaddresstype c WHERE c.companyaddresstypePK.id = :id"),
    @NamedQuery(name = "Companyaddresstype.findByPubkey", query = "SELECT c FROM Companyaddresstype c WHERE c.companyaddresstypePK.pubkey = :pubkey"),
    @NamedQuery(name = "Companyaddresstype.findByName", query = "SELECT c FROM Companyaddresstype c WHERE c.name = :name"),
    @NamedQuery(name = "Companyaddresstype.findByCreateddate", query = "SELECT c FROM Companyaddresstype c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companyaddresstype.findByCreatedfrom", query = "SELECT c FROM Companyaddresstype c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companyaddresstype.findByChangeddate", query = "SELECT c FROM Companyaddresstype c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companyaddresstype.findByChangedfrom", query = "SELECT c FROM Companyaddresstype c WHERE c.changedfrom = :changedfrom")})
public class Companyaddresstype implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyaddresstypePK companyaddresstypePK;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyaddresstype")
    private Collection<Companyemployeeaddress> companyemployeeaddressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyaddresstype")
    private Collection<Customercontactsaddress> customercontactsaddressCollection;
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

    public Companyaddresstype() {
    }

    public Companyaddresstype(CompanyaddresstypePK companyaddresstypePK) {
        this.companyaddresstypePK = companyaddresstypePK;
    }

    public Companyaddresstype(int id, int pubkey) {
        this.companyaddresstypePK = new CompanyaddresstypePK(id, pubkey);
    }

    public CompanyaddresstypePK getCompanyaddresstypePK() {
        return companyaddresstypePK;
    }

    public void setCompanyaddresstypePK(CompanyaddresstypePK companyaddresstypePK) {
        this.companyaddresstypePK = companyaddresstypePK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Collection<Companyemployeeaddress> getCompanyemployeeaddressCollection() {
        return companyemployeeaddressCollection;
    }

    public void setCompanyemployeeaddressCollection(Collection<Companyemployeeaddress> companyemployeeaddressCollection) {
        this.companyemployeeaddressCollection = companyemployeeaddressCollection;
    }

    @XmlTransient
    public Collection<Customercontactsaddress> getCustomercontactsaddressCollection() {
        return customercontactsaddressCollection;
    }

    public void setCustomercontactsaddressCollection(Collection<Customercontactsaddress> customercontactsaddressCollection) {
        this.customercontactsaddressCollection = customercontactsaddressCollection;
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
        hash += (companyaddresstypePK != null ? companyaddresstypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyaddresstype)) {
            return false;
        }
        Companyaddresstype other = (Companyaddresstype) object;
        if ((this.companyaddresstypePK == null && other.companyaddresstypePK != null) || (this.companyaddresstypePK != null && !this.companyaddresstypePK.equals(other.companyaddresstypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companyaddresstype[ companyaddresstypePK=" + companyaddresstypePK + " ]";
    }

}
