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
    @NamedQuery(name = "Customerbank.findAll", query = "SELECT c FROM Customerbank c"),
    @NamedQuery(name = "Customerbank.findByBankid", query = "SELECT c FROM Customerbank c WHERE c.customerbankPK.bankid = :bankid"),
    @NamedQuery(name = "Customerbank.findByPubkey", query = "SELECT c FROM Customerbank c WHERE c.customerbankPK.pubkey = :pubkey"),
    @NamedQuery(name = "Customerbank.findByBankname", query = "SELECT c FROM Customerbank c WHERE c.bankname = :bankname"),
    @NamedQuery(name = "Customerbank.findByBankaccount", query = "SELECT c FROM Customerbank c WHERE c.bankaccount = :bankaccount"),
    @NamedQuery(name = "Customerbank.findByBankiban", query = "SELECT c FROM Customerbank c WHERE c.bankiban = :bankiban"),
    @NamedQuery(name = "Customerbank.findByBankcountry", query = "SELECT c FROM Customerbank c WHERE c.bankcountry = :bankcountry"),
    @NamedQuery(name = "Customerbank.findByIsDefault", query = "SELECT c FROM Customerbank c WHERE c.isDefault = :isDefault"),
    @NamedQuery(name = "Customerbank.findByCreateddate", query = "SELECT c FROM Customerbank c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Customerbank.findByCreatedfrom", query = "SELECT c FROM Customerbank c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Customerbank.findByChangeddate", query = "SELECT c FROM Customerbank c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Customerbank.findByChangedfrom", query = "SELECT c FROM Customerbank c WHERE c.changedfrom = :changedfrom")})
public class Customerbank implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomerbankPK customerbankPK;
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
        @JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companycustomer companycustomer;
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

    public Customerbank() {
    }

    public Customerbank(CustomerbankPK customerbankPK) {
        this.customerbankPK = customerbankPK;
    }

    public Customerbank(int bankid, int pubkey) {
        this.customerbankPK = new CustomerbankPK(bankid, pubkey);
    }

    public CustomerbankPK getCustomerbankPK() {
        return customerbankPK;
    }

    public void setCustomerbankPK(CustomerbankPK customerbankPK) {
        this.customerbankPK = customerbankPK;
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

    public Companycustomer getCompanycustomer() {
        return companycustomer;
    }

    public void setCompanycustomer(Companycustomer companycustomer) {
        this.companycustomer = companycustomer;
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
        hash += (customerbankPK != null ? customerbankPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customerbank)) {
            return false;
        }
        Customerbank other = (Customerbank) object;
        if ((this.customerbankPK == null && other.customerbankPK != null) || (this.customerbankPK != null && !this.customerbankPK.equals(other.customerbankPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Customerbank[ customerbankPK=" + customerbankPK + " ]";
    }

}
