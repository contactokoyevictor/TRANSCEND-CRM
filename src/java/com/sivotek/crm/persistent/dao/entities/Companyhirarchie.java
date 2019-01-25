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
    @NamedQuery(name = "Companyhirarchie.findAll", query = "SELECT c FROM Companyhirarchie c"),
    @NamedQuery(name = "Companyhirarchie.findByHierarchieid", query = "SELECT c FROM Companyhirarchie c WHERE c.companyhirarchiePK.hierarchieid = :hierarchieid"),
    @NamedQuery(name = "Companyhirarchie.findByPubkey", query = "SELECT c FROM Companyhirarchie c WHERE c.companyhirarchiePK.pubkey = :pubkey"),
    @NamedQuery(name = "Companyhirarchie.findByName", query = "SELECT c FROM Companyhirarchie c WHERE c.name = :name"),
    @NamedQuery(name = "Companyhirarchie.findByCreateddate", query = "SELECT c FROM Companyhirarchie c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companyhirarchie.findByCreatedfrom", query = "SELECT c FROM Companyhirarchie c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companyhirarchie.findByCreatedby", query = "SELECT c FROM Companyhirarchie c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "Companyhirarchie.findByChangeddate", query = "SELECT c FROM Companyhirarchie c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companyhirarchie.findByChangedfrom", query = "SELECT c FROM Companyhirarchie c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Companyhirarchie.findByChangedby", query = "SELECT c FROM Companyhirarchie c WHERE c.changedby = :changedby")})
public class Companyhirarchie implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyhirarchiePK companyhirarchiePK;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Size(max = 150)
    @Column(length = 150)
    private String createdfrom;
    private Integer createdby;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @Size(max = 150)
    @Column(length = 150)
    private String changedfrom;
    private Integer changedby;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "ROOT", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company1;
    @JoinColumns({
        @JoinColumn(name = "PARENT", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company2;

    public Companyhirarchie() {
    }

    public Companyhirarchie(CompanyhirarchiePK companyhirarchiePK) {
        this.companyhirarchiePK = companyhirarchiePK;
    }

    public Companyhirarchie(int hierarchieid, int pubkey) {
        this.companyhirarchiePK = new CompanyhirarchiePK(hierarchieid, pubkey);
    }

    public CompanyhirarchiePK getCompanyhirarchiePK() {
        return companyhirarchiePK;
    }

    public void setCompanyhirarchiePK(CompanyhirarchiePK companyhirarchiePK) {
        this.companyhirarchiePK = companyhirarchiePK;
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

    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
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

    public Integer getChangedby() {
        return changedby;
    }

    public void setChangedby(Integer changedby) {
        this.changedby = changedby;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany1() {
        return company1;
    }

    public void setCompany1(Company company1) {
        this.company1 = company1;
    }

    public Company getCompany2() {
        return company2;
    }

    public void setCompany2(Company company2) {
        this.company2 = company2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyhirarchiePK != null ? companyhirarchiePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyhirarchie)) {
            return false;
        }
        Companyhirarchie other = (Companyhirarchie) object;
        if ((this.companyhirarchiePK == null && other.companyhirarchiePK != null) || (this.companyhirarchiePK != null && !this.companyhirarchiePK.equals(other.companyhirarchiePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companyhirarchie[ companyhirarchiePK=" + companyhirarchiePK + " ]";
    }

}
