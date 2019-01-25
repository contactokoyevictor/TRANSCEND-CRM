/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author okoyevictor
 */
@Entity
@Table(catalog = "sivotekcrm_", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"licensecode"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crmlicense.findAll", query = "SELECT c FROM Crmlicense c"),
    @NamedQuery(name = "Crmlicense.findByLicenseid", query = "SELECT c FROM Crmlicense c WHERE c.crmlicensePK.licenseid = :licenseid"),
    @NamedQuery(name = "Crmlicense.findByPubkey", query = "SELECT c FROM Crmlicense c WHERE c.crmlicensePK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmlicense.findByLicensecode", query = "SELECT c FROM Crmlicense c WHERE c.licensecode = :licensecode"),
    @NamedQuery(name = "Crmlicense.findByPublicKey", query = "SELECT c FROM Crmlicense c WHERE c.publicKey = :publicKey"),
    @NamedQuery(name = "Crmlicense.findByPreciousKey", query = "SELECT c FROM Crmlicense c WHERE c.preciousKey = :preciousKey"),
    @NamedQuery(name = "Crmlicense.findByCreateddate", query = "SELECT c FROM Crmlicense c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmlicense.findByCreatedfrom", query = "SELECT c FROM Crmlicense c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmlicense.findByChangeddate", query = "SELECT c FROM Crmlicense c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmlicense.findByChangedfrom", query = "SELECT c FROM Crmlicense c WHERE c.changedfrom = :changedfrom")})
public class Crmlicense implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmlicensePK crmlicensePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String licensecode;
    @Column(name = "public_key")
    private Integer publicKey;
    @Column(name = "precious_key")
    private Integer preciousKey;
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
    @JoinColumn(name = "periodityid", referencedColumnName = "periodityid")
    @ManyToOne
    private Crmlicenseperiodity periodityid;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;

    public Crmlicense() {
    }

    public Crmlicense(CrmlicensePK crmlicensePK) {
        this.crmlicensePK = crmlicensePK;
    }

    public Crmlicense(CrmlicensePK crmlicensePK, String licensecode) {
        this.crmlicensePK = crmlicensePK;
        this.licensecode = licensecode;
    }

    public Crmlicense(int licenseid, int pubkey) {
        this.crmlicensePK = new CrmlicensePK(licenseid, pubkey);
    }

    public CrmlicensePK getCrmlicensePK() {
        return crmlicensePK;
    }

    public void setCrmlicensePK(CrmlicensePK crmlicensePK) {
        this.crmlicensePK = crmlicensePK;
    }

    public String getLicensecode() {
        return licensecode;
    }

    public void setLicensecode(String licensecode) {
        this.licensecode = licensecode;
    }

    public Integer getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(Integer publicKey) {
        this.publicKey = publicKey;
    }

    public Integer getPreciousKey() {
        return preciousKey;
    }

    public void setPreciousKey(Integer preciousKey) {
        this.preciousKey = preciousKey;
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

    public Crmlicenseperiodity getPeriodityid() {
        return periodityid;
    }

    public void setPeriodityid(Crmlicenseperiodity periodityid) {
        this.periodityid = periodityid;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmlicensePK != null ? crmlicensePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmlicense)) {
            return false;
        }
        Crmlicense other = (Crmlicense) object;
        if ((this.crmlicensePK == null && other.crmlicensePK != null) || (this.crmlicensePK != null && !this.crmlicensePK.equals(other.crmlicensePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmlicense[ crmlicensePK=" + crmlicensePK + " ]";
    }

}
