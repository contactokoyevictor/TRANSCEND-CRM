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
    @NamedQuery(name = "Compnaypaymentcurrency.findAll", query = "SELECT c FROM Compnaypaymentcurrency c"),
    @NamedQuery(name = "Compnaypaymentcurrency.findById", query = "SELECT c FROM Compnaypaymentcurrency c WHERE c.compnaypaymentcurrencyPK.id = :id"),
    @NamedQuery(name = "Compnaypaymentcurrency.findByPubkey", query = "SELECT c FROM Compnaypaymentcurrency c WHERE c.compnaypaymentcurrencyPK.pubkey = :pubkey"),
    @NamedQuery(name = "Compnaypaymentcurrency.findByCurrencyName", query = "SELECT c FROM Compnaypaymentcurrency c WHERE c.currencyName = :currencyName"),
    @NamedQuery(name = "Compnaypaymentcurrency.findByCurrencyCode", query = "SELECT c FROM Compnaypaymentcurrency c WHERE c.currencyCode = :currencyCode"),
    @NamedQuery(name = "Compnaypaymentcurrency.findByCurrencySymbol", query = "SELECT c FROM Compnaypaymentcurrency c WHERE c.currencySymbol = :currencySymbol"),
    @NamedQuery(name = "Compnaypaymentcurrency.findByCreateddate", query = "SELECT c FROM Compnaypaymentcurrency c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Compnaypaymentcurrency.findByCreatedfrom", query = "SELECT c FROM Compnaypaymentcurrency c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Compnaypaymentcurrency.findByChangeddate", query = "SELECT c FROM Compnaypaymentcurrency c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Compnaypaymentcurrency.findByChangedfrom", query = "SELECT c FROM Compnaypaymentcurrency c WHERE c.changedfrom = :changedfrom")})
public class Compnaypaymentcurrency implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompnaypaymentcurrencyPK compnaypaymentcurrencyPK;
    @Size(max = 255)
    @Column(name = "CURRENCY_NAME", length = 255)
    private String currencyName;
    @Size(max = 255)
    @Column(name = "CURRENCY_CODE", length = 255)
    private String currencyCode;
    @Size(max = 5)
    @Column(name = "CURRENCY_SYMBOL", length = 5)
    private String currencySymbol;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compnaypaymentcurrency")
    private Collection<Companypayments> companypaymentsCollection;

    public Compnaypaymentcurrency() {
    }

    public Compnaypaymentcurrency(CompnaypaymentcurrencyPK compnaypaymentcurrencyPK) {
        this.compnaypaymentcurrencyPK = compnaypaymentcurrencyPK;
    }

    public Compnaypaymentcurrency(int id, int pubkey) {
        this.compnaypaymentcurrencyPK = new CompnaypaymentcurrencyPK(id, pubkey);
    }

    public CompnaypaymentcurrencyPK getCompnaypaymentcurrencyPK() {
        return compnaypaymentcurrencyPK;
    }

    public void setCompnaypaymentcurrencyPK(CompnaypaymentcurrencyPK compnaypaymentcurrencyPK) {
        this.compnaypaymentcurrencyPK = compnaypaymentcurrencyPK;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
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
    public Collection<Companypayments> getCompanypaymentsCollection() {
        return companypaymentsCollection;
    }

    public void setCompanypaymentsCollection(Collection<Companypayments> companypaymentsCollection) {
        this.companypaymentsCollection = companypaymentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compnaypaymentcurrencyPK != null ? compnaypaymentcurrencyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compnaypaymentcurrency)) {
            return false;
        }
        Compnaypaymentcurrency other = (Compnaypaymentcurrency) object;
        if ((this.compnaypaymentcurrencyPK == null && other.compnaypaymentcurrencyPK != null) || (this.compnaypaymentcurrencyPK != null && !this.compnaypaymentcurrencyPK.equals(other.compnaypaymentcurrencyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Compnaypaymentcurrency[ compnaypaymentcurrencyPK=" + compnaypaymentcurrencyPK + " ]";
    }

}
