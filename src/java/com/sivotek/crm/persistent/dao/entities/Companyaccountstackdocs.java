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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Companyaccountstackdocs.findAll", query = "SELECT c FROM Companyaccountstackdocs c"),
    @NamedQuery(name = "Companyaccountstackdocs.findByAccountdocid", query = "SELECT c FROM Companyaccountstackdocs c WHERE c.companyaccountstackdocsPK.accountdocid = :accountdocid"),
    @NamedQuery(name = "Companyaccountstackdocs.findByPubkey", query = "SELECT c FROM Companyaccountstackdocs c WHERE c.companyaccountstackdocsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companyaccountstackdocs.findByName", query = "SELECT c FROM Companyaccountstackdocs c WHERE c.name = :name"),
    @NamedQuery(name = "Companyaccountstackdocs.findByCreateddate", query = "SELECT c FROM Companyaccountstackdocs c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companyaccountstackdocs.findByCreatedfrom", query = "SELECT c FROM Companyaccountstackdocs c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companyaccountstackdocs.findByChangeddate", query = "SELECT c FROM Companyaccountstackdocs c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companyaccountstackdocs.findByChangedfrom", query = "SELECT c FROM Companyaccountstackdocs c WHERE c.changedfrom = :changedfrom")})
public class Companyaccountstackdocs implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyaccountstackdocsPK companyaccountstackdocsPK;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Lob
    private byte[] files;
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
        @JoinColumn(name = "ACCOUNTID", referencedColumnName = "ACCOUNTSTACKCDID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyaccountstackcd companyaccountstackcd;
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

    public Companyaccountstackdocs() {
    }

    public Companyaccountstackdocs(CompanyaccountstackdocsPK companyaccountstackdocsPK) {
        this.companyaccountstackdocsPK = companyaccountstackdocsPK;
    }

    public Companyaccountstackdocs(int accountdocid, int pubkey) {
        this.companyaccountstackdocsPK = new CompanyaccountstackdocsPK(accountdocid, pubkey);
    }

    public CompanyaccountstackdocsPK getCompanyaccountstackdocsPK() {
        return companyaccountstackdocsPK;
    }

    public void setCompanyaccountstackdocsPK(CompanyaccountstackdocsPK companyaccountstackdocsPK) {
        this.companyaccountstackdocsPK = companyaccountstackdocsPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFiles() {
        return files;
    }

    public void setFiles(byte[] files) {
        this.files = files;
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

    public Companyaccountstackcd getCompanyaccountstackcd() {
        return companyaccountstackcd;
    }

    public void setCompanyaccountstackcd(Companyaccountstackcd companyaccountstackcd) {
        this.companyaccountstackcd = companyaccountstackcd;
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
        hash += (companyaccountstackdocsPK != null ? companyaccountstackdocsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyaccountstackdocs)) {
            return false;
        }
        Companyaccountstackdocs other = (Companyaccountstackdocs) object;
        if ((this.companyaccountstackdocsPK == null && other.companyaccountstackdocsPK != null) || (this.companyaccountstackdocsPK != null && !this.companyaccountstackdocsPK.equals(other.companyaccountstackdocsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companyaccountstackdocs[ companyaccountstackdocsPK=" + companyaccountstackdocsPK + " ]";
    }

}
