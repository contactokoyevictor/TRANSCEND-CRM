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
    @NamedQuery(name = "Companypayments.findAll", query = "SELECT c FROM Companypayments c"),
    @NamedQuery(name = "Companypayments.findById", query = "SELECT c FROM Companypayments c WHERE c.companypaymentsPK.id = :id"),
    @NamedQuery(name = "Companypayments.findByPubkey", query = "SELECT c FROM Companypayments c WHERE c.companypaymentsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companypayments.findByAmount", query = "SELECT c FROM Companypayments c WHERE c.amount = :amount"),
    @NamedQuery(name = "Companypayments.findByCreateddate", query = "SELECT c FROM Companypayments c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companypayments.findByCreatedfrom", query = "SELECT c FROM Companypayments c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companypayments.findByChangeddate", query = "SELECT c FROM Companypayments c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companypayments.findByChangedfrom", query = "SELECT c FROM Companypayments c WHERE c.changedfrom = :changedfrom")})
public class Companypayments implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanypaymentsPK companypaymentsPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double amount;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companypayments")
    private Collection<Employeedesignation> employeedesignationCollection;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "SCHEMEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companypaymentscheme companypaymentscheme;
    @JoinColumns({
        @JoinColumn(name = "CURRENCYID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Compnaypaymentcurrency compnaypaymentcurrency;
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

    public Companypayments() {
    }

    public Companypayments(CompanypaymentsPK companypaymentsPK) {
        this.companypaymentsPK = companypaymentsPK;
    }

    public Companypayments(int id, int pubkey) {
        this.companypaymentsPK = new CompanypaymentsPK(id, pubkey);
    }

    public CompanypaymentsPK getCompanypaymentsPK() {
        return companypaymentsPK;
    }

    public void setCompanypaymentsPK(CompanypaymentsPK companypaymentsPK) {
        this.companypaymentsPK = companypaymentsPK;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
    public Collection<Employeedesignation> getEmployeedesignationCollection() {
        return employeedesignationCollection;
    }

    public void setEmployeedesignationCollection(Collection<Employeedesignation> employeedesignationCollection) {
        this.employeedesignationCollection = employeedesignationCollection;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Companypaymentscheme getCompanypaymentscheme() {
        return companypaymentscheme;
    }

    public void setCompanypaymentscheme(Companypaymentscheme companypaymentscheme) {
        this.companypaymentscheme = companypaymentscheme;
    }

    public Compnaypaymentcurrency getCompnaypaymentcurrency() {
        return compnaypaymentcurrency;
    }

    public void setCompnaypaymentcurrency(Compnaypaymentcurrency compnaypaymentcurrency) {
        this.compnaypaymentcurrency = compnaypaymentcurrency;
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
        hash += (companypaymentsPK != null ? companypaymentsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companypayments)) {
            return false;
        }
        Companypayments other = (Companypayments) object;
        if ((this.companypaymentsPK == null && other.companypaymentsPK != null) || (this.companypaymentsPK != null && !this.companypaymentsPK.equals(other.companypaymentsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companypayments[ companypaymentsPK=" + companypaymentsPK + " ]";
    }

}
