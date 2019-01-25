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
    @UniqueConstraint(columnNames = {"licensecode"}),
    @UniqueConstraint(columnNames = {"licensekey"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crmlicensecode.findAll", query = "SELECT c FROM Crmlicensecode c"),
    @NamedQuery(name = "Crmlicensecode.findById", query = "SELECT c FROM Crmlicensecode c WHERE c.crmlicensecodePK.id = :id"),
    @NamedQuery(name = "Crmlicensecode.findByPubkey", query = "SELECT c FROM Crmlicensecode c WHERE c.crmlicensecodePK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmlicensecode.findByLicensecode", query = "SELECT c FROM Crmlicensecode c WHERE c.licensecode = :licensecode"),
    @NamedQuery(name = "Crmlicensecode.findByLicensekey", query = "SELECT c FROM Crmlicensecode c WHERE c.licensekey = :licensekey"),
    @NamedQuery(name = "Crmlicensecode.findByCreateddate", query = "SELECT c FROM Crmlicensecode c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmlicensecode.findByCreatedfrom", query = "SELECT c FROM Crmlicensecode c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmlicensecode.findByChangeddate", query = "SELECT c FROM Crmlicensecode c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmlicensecode.findByChangedfrom", query = "SELECT c FROM Crmlicensecode c WHERE c.changedfrom = :changedfrom")})
public class Crmlicensecode implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmlicensecodePK crmlicensecodePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(nullable = false, length = 60)
    private String licensecode;
    private Integer licensekey;
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

    public Crmlicensecode() {
    }

    public Crmlicensecode(CrmlicensecodePK crmlicensecodePK) {
        this.crmlicensecodePK = crmlicensecodePK;
    }

    public Crmlicensecode(CrmlicensecodePK crmlicensecodePK, String licensecode) {
        this.crmlicensecodePK = crmlicensecodePK;
        this.licensecode = licensecode;
    }

    public Crmlicensecode(int id, int pubkey) {
        this.crmlicensecodePK = new CrmlicensecodePK(id, pubkey);
    }

    public CrmlicensecodePK getCrmlicensecodePK() {
        return crmlicensecodePK;
    }

    public void setCrmlicensecodePK(CrmlicensecodePK crmlicensecodePK) {
        this.crmlicensecodePK = crmlicensecodePK;
    }

    public String getLicensecode() {
        return licensecode;
    }

    public void setLicensecode(String licensecode) {
        this.licensecode = licensecode;
    }

    public Integer getLicensekey() {
        return licensekey;
    }

    public void setLicensekey(Integer licensekey) {
        this.licensekey = licensekey;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmlicensecodePK != null ? crmlicensecodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmlicensecode)) {
            return false;
        }
        Crmlicensecode other = (Crmlicensecode) object;
        if ((this.crmlicensecodePK == null && other.crmlicensecodePK != null) || (this.crmlicensecodePK != null && !this.crmlicensecodePK.equals(other.crmlicensecodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmlicensecode[ crmlicensecodePK=" + crmlicensecodePK + " ]";
    }

}
