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
    @NamedQuery(name = "Crmworkorder.findAll", query = "SELECT c FROM Crmworkorder c"),
    @NamedQuery(name = "Crmworkorder.findById", query = "SELECT c FROM Crmworkorder c WHERE c.crmworkorderPK.id = :id"),
    @NamedQuery(name = "Crmworkorder.findByPubkey", query = "SELECT c FROM Crmworkorder c WHERE c.crmworkorderPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmworkorder.findByCreateddate", query = "SELECT c FROM Crmworkorder c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmworkorder.findByCreatedfrom", query = "SELECT c FROM Crmworkorder c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmworkorder.findByChangeddate", query = "SELECT c FROM Crmworkorder c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmworkorder.findByChangedfrom", query = "SELECT c FROM Crmworkorder c WHERE c.changedfrom = :changedfrom")})
public class Crmworkorder implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmworkorderPK crmworkorderPK;
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
        @JoinColumn(name = "TYPEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmworkordertype crmworkordertype;
    @JoinColumns({
        @JoinColumn(name = "ISSUEID", referencedColumnName = "ISSUEID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmprojectticketmanagement crmprojectticketmanagement;
    @JoinColumns({
        @JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companycustomer companycustomer;
    @JoinColumns({
        @JoinColumn(name = "PRODUCTID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyproduct companyproduct;
    @JoinColumns({
        @JoinColumn(name = "COMPONENTID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Productcomponents productcomponents;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmworkorder")
    private Collection<Crmworkorderresolution> crmworkorderresolutionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmworkorder")
    private Collection<Workorderinstructions> workorderinstructionsCollection;

    public Crmworkorder() {
    }

    public Crmworkorder(CrmworkorderPK crmworkorderPK) {
        this.crmworkorderPK = crmworkorderPK;
    }

    public Crmworkorder(int id, int pubkey) {
        this.crmworkorderPK = new CrmworkorderPK(id, pubkey);
    }

    public CrmworkorderPK getCrmworkorderPK() {
        return crmworkorderPK;
    }

    public void setCrmworkorderPK(CrmworkorderPK crmworkorderPK) {
        this.crmworkorderPK = crmworkorderPK;
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

    public Crmworkordertype getCrmworkordertype() {
        return crmworkordertype;
    }

    public void setCrmworkordertype(Crmworkordertype crmworkordertype) {
        this.crmworkordertype = crmworkordertype;
    }

    public Crmprojectticketmanagement getCrmprojectticketmanagement() {
        return crmprojectticketmanagement;
    }

    public void setCrmprojectticketmanagement(Crmprojectticketmanagement crmprojectticketmanagement) {
        this.crmprojectticketmanagement = crmprojectticketmanagement;
    }

    public Companycustomer getCompanycustomer() {
        return companycustomer;
    }

    public void setCompanycustomer(Companycustomer companycustomer) {
        this.companycustomer = companycustomer;
    }

    public Companyproduct getCompanyproduct() {
        return companyproduct;
    }

    public void setCompanyproduct(Companyproduct companyproduct) {
        this.companyproduct = companyproduct;
    }

    public Productcomponents getProductcomponents() {
        return productcomponents;
    }

    public void setProductcomponents(Productcomponents productcomponents) {
        this.productcomponents = productcomponents;
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
    public Collection<Crmworkorderresolution> getCrmworkorderresolutionCollection() {
        return crmworkorderresolutionCollection;
    }

    public void setCrmworkorderresolutionCollection(Collection<Crmworkorderresolution> crmworkorderresolutionCollection) {
        this.crmworkorderresolutionCollection = crmworkorderresolutionCollection;
    }

    @XmlTransient
    public Collection<Workorderinstructions> getWorkorderinstructionsCollection() {
        return workorderinstructionsCollection;
    }

    public void setWorkorderinstructionsCollection(Collection<Workorderinstructions> workorderinstructionsCollection) {
        this.workorderinstructionsCollection = workorderinstructionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmworkorderPK != null ? crmworkorderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmworkorder)) {
            return false;
        }
        Crmworkorder other = (Crmworkorder) object;
        if ((this.crmworkorderPK == null && other.crmworkorderPK != null) || (this.crmworkorderPK != null && !this.crmworkorderPK.equals(other.crmworkorderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmworkorder[ crmworkorderPK=" + crmworkorderPK + " ]";
    }

}
