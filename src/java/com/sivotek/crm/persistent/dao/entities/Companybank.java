/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author okoyevictor
 */
@Entity
@Table(catalog = "sivotekcrm_", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Companybank.findAll", query = "SELECT c FROM Companybank c"),
    @NamedQuery(name = "Companybank.findByBankid", query = "SELECT c FROM Companybank c WHERE c.companybankPK.bankid = :bankid"),
    @NamedQuery(name = "Companybank.findByPubkey", query = "SELECT c FROM Companybank c WHERE c.companybankPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companybank.findByBankname", query = "SELECT c FROM Companybank c WHERE c.bankname = :bankname"),
    @NamedQuery(name = "Companybank.findByBankaccount", query = "SELECT c FROM Companybank c WHERE c.bankaccount = :bankaccount"),
    @NamedQuery(name = "Companybank.findByBankiban", query = "SELECT c FROM Companybank c WHERE c.bankiban = :bankiban"),
    @NamedQuery(name = "Companybank.findByBankcountry", query = "SELECT c FROM Companybank c WHERE c.bankcountry = :bankcountry"),
    @NamedQuery(name = "Companybank.findByIsDefault", query = "SELECT c FROM Companybank c WHERE c.isDefault = :isDefault"),
    @NamedQuery(name = "Companybank.findByCreateddate", query = "SELECT c FROM Companybank c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companybank.findByCreatedfrom", query = "SELECT c FROM Companybank c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companybank.findByChangeddate", query = "SELECT c FROM Companybank c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companybank.findByChangedfrom", query = "SELECT c FROM Companybank c WHERE c.changedfrom = :changedfrom")})
public class Companybank implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanybankPK companybankPK;
    @Size(max = 255)
    @Column(length = 255)
    private String bankname;
    @Size(max = 255)
    @Column(length = 255)
    private String bankaccount;
    @Size(max = 255)
    @Column(length = 255)
    private String bankiban;
    @Size(max = 150)
    @Column(length = 150)
    private String bankcountry;
    private Boolean isDefault;
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

    public Companybank() {
    }

    public Companybank(CompanybankPK companybankPK) {
        this.companybankPK = companybankPK;
    }

    public Companybank(int bankid, int pubkey) {
        this.companybankPK = new CompanybankPK(bankid, pubkey);
    }

    public CompanybankPK getCompanybankPK() {
        return companybankPK;
    }

    public void setCompanybankPK(CompanybankPK companybankPK) {
        this.companybankPK = companybankPK;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getBankiban() {
        return bankiban;
    }

    public void setBankiban(String bankiban) {
        this.bankiban = bankiban;
    }

    public String getBankcountry() {
        return bankcountry;
    }

    public void setBankcountry(String bankcountry) {
        this.bankcountry = bankcountry;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companybankPK != null ? companybankPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companybank)) {
            return false;
        }
        Companybank other = (Companybank) object;
        if ((this.companybankPK == null && other.companybankPK != null) || (this.companybankPK != null && !this.companybankPK.equals(other.companybankPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companybank[ companybankPK=" + companybankPK + " ]";
    }

}
