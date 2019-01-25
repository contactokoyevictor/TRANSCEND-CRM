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
    @NamedQuery(name = "Companyaccountstack.findAll", query = "SELECT c FROM Companyaccountstack c"),
    @NamedQuery(name = "Companyaccountstack.findByAcstackid", query = "SELECT c FROM Companyaccountstack c WHERE c.companyaccountstackPK.acstackid = :acstackid"),
    @NamedQuery(name = "Companyaccountstack.findByPubkey", query = "SELECT c FROM Companyaccountstack c WHERE c.companyaccountstackPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companyaccountstack.findByAccountnr", query = "SELECT c FROM Companyaccountstack c WHERE c.accountnr = :accountnr"),
    @NamedQuery(name = "Companyaccountstack.findByAccountType", query = "SELECT c FROM Companyaccountstack c WHERE c.accountType = :accountType"),
    @NamedQuery(name = "Companyaccountstack.findByAccountTaxType", query = "SELECT c FROM Companyaccountstack c WHERE c.accountTaxType = :accountTaxType"),
    @NamedQuery(name = "Companyaccountstack.findByAccountDebit", query = "SELECT c FROM Companyaccountstack c WHERE c.accountDebit = :accountDebit"),
    @NamedQuery(name = "Companyaccountstack.findByAccountCredit", query = "SELECT c FROM Companyaccountstack c WHERE c.accountCredit = :accountCredit"),
    @NamedQuery(name = "Companyaccountstack.findByAccountDName", query = "SELECT c FROM Companyaccountstack c WHERE c.accountDName = :accountDName"),
    @NamedQuery(name = "Companyaccountstack.findByAccountCName", query = "SELECT c FROM Companyaccountstack c WHERE c.accountCName = :accountCName"),
    @NamedQuery(name = "Companyaccountstack.findByAccountCValue", query = "SELECT c FROM Companyaccountstack c WHERE c.accountCValue = :accountCValue"),
    @NamedQuery(name = "Companyaccountstack.findByAccountDValue", query = "SELECT c FROM Companyaccountstack c WHERE c.accountDValue = :accountDValue"),
    @NamedQuery(name = "Companyaccountstack.findByAccountname", query = "SELECT c FROM Companyaccountstack c WHERE c.accountname = :accountname"),
    @NamedQuery(name = "Companyaccountstack.findByAccountvalue", query = "SELECT c FROM Companyaccountstack c WHERE c.accountvalue = :accountvalue"),
    @NamedQuery(name = "Companyaccountstack.findByCreateddate", query = "SELECT c FROM Companyaccountstack c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companyaccountstack.findByCreatedfrom", query = "SELECT c FROM Companyaccountstack c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companyaccountstack.findByChangeddate", query = "SELECT c FROM Companyaccountstack c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companyaccountstack.findByChangedfrom", query = "SELECT c FROM Companyaccountstack c WHERE c.changedfrom = :changedfrom")})
public class Companyaccountstack implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyaccountstackPK companyaccountstackPK;
    @Size(max = 255)
    @Column(length = 255)
    private String accountnr;
    @Column(name = "ACCOUNT_TYPE")
    private Integer accountType;
    @Size(max = 255)
    @Column(name = "ACCOUNT_TAX_TYPE", length = 255)
    private String accountTaxType;
    @Size(max = 255)
    @Column(name = "ACCOUNT_DEBIT", length = 255)
    private String accountDebit;
    @Size(max = 255)
    @Column(name = "ACCOUNT_CREDIT", length = 255)
    private String accountCredit;
    @Size(max = 255)
    @Column(name = "ACCOUNT_D_NAME", length = 255)
    private String accountDName;
    @Size(max = 255)
    @Column(name = "ACCOUNT_C_NAME", length = 255)
    private String accountCName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ACCOUNT_C_VALUE", precision = 22)
    private Double accountCValue;
    @Column(name = "ACCOUNT_D_VALUE", precision = 22)
    private Double accountDValue;
    @Size(max = 255)
    @Column(length = 255)
    private String accountname;
    @Column(precision = 22)
    private Double accountvalue;
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
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyaccountstack")
    private Collection<Companyaccountstackcd> companyaccountstackcdCollection;

    public Companyaccountstack() {
    }

    public Companyaccountstack(CompanyaccountstackPK companyaccountstackPK) {
        this.companyaccountstackPK = companyaccountstackPK;
    }

    public Companyaccountstack(int acstackid, int pubkey) {
        this.companyaccountstackPK = new CompanyaccountstackPK(acstackid, pubkey);
    }

    public CompanyaccountstackPK getCompanyaccountstackPK() {
        return companyaccountstackPK;
    }

    public void setCompanyaccountstackPK(CompanyaccountstackPK companyaccountstackPK) {
        this.companyaccountstackPK = companyaccountstackPK;
    }

    public String getAccountnr() {
        return accountnr;
    }

    public void setAccountnr(String accountnr) {
        this.accountnr = accountnr;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAccountTaxType() {
        return accountTaxType;
    }

    public void setAccountTaxType(String accountTaxType) {
        this.accountTaxType = accountTaxType;
    }

    public String getAccountDebit() {
        return accountDebit;
    }

    public void setAccountDebit(String accountDebit) {
        this.accountDebit = accountDebit;
    }

    public String getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(String accountCredit) {
        this.accountCredit = accountCredit;
    }

    public String getAccountDName() {
        return accountDName;
    }

    public void setAccountDName(String accountDName) {
        this.accountDName = accountDName;
    }

    public String getAccountCName() {
        return accountCName;
    }

    public void setAccountCName(String accountCName) {
        this.accountCName = accountCName;
    }

    public Double getAccountCValue() {
        return accountCValue;
    }

    public void setAccountCValue(Double accountCValue) {
        this.accountCValue = accountCValue;
    }

    public Double getAccountDValue() {
        return accountDValue;
    }

    public void setAccountDValue(Double accountDValue) {
        this.accountDValue = accountDValue;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public Double getAccountvalue() {
        return accountvalue;
    }

    public void setAccountvalue(Double accountvalue) {
        this.accountvalue = accountvalue;
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
    public Collection<Companyaccountstackcd> getCompanyaccountstackcdCollection() {
        return companyaccountstackcdCollection;
    }

    public void setCompanyaccountstackcdCollection(Collection<Companyaccountstackcd> companyaccountstackcdCollection) {
        this.companyaccountstackcdCollection = companyaccountstackcdCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyaccountstackPK != null ? companyaccountstackPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyaccountstack)) {
            return false;
        }
        Companyaccountstack other = (Companyaccountstack) object;
        if ((this.companyaccountstackPK == null && other.companyaccountstackPK != null) || (this.companyaccountstackPK != null && !this.companyaccountstackPK.equals(other.companyaccountstackPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companyaccountstack[ companyaccountstackPK=" + companyaccountstackPK + " ]";
    }

}
