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
    @NamedQuery(name = "Crmusermodules.findAll", query = "SELECT c FROM Crmusermodules c"),
    @NamedQuery(name = "Crmusermodules.findById", query = "SELECT c FROM Crmusermodules c WHERE c.crmusermodulesPK.id = :id"),
    @NamedQuery(name = "Crmusermodules.findByPubkey", query = "SELECT c FROM Crmusermodules c WHERE c.crmusermodulesPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmusermodules.findByCanread", query = "SELECT c FROM Crmusermodules c WHERE c.canread = :canread"),
    @NamedQuery(name = "Crmusermodules.findByCanwrite", query = "SELECT c FROM Crmusermodules c WHERE c.canwrite = :canwrite"),
    @NamedQuery(name = "Crmusermodules.findByCandelete", query = "SELECT c FROM Crmusermodules c WHERE c.candelete = :candelete"),
    @NamedQuery(name = "Crmusermodules.findByCreateddate", query = "SELECT c FROM Crmusermodules c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmusermodules.findByCreatedfrom", query = "SELECT c FROM Crmusermodules c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmusermodules.findByChangeddate", query = "SELECT c FROM Crmusermodules c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmusermodules.findByChangedfrom", query = "SELECT c FROM Crmusermodules c WHERE c.changedfrom = :changedfrom")})
public class Crmusermodules implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmusermodulesPK crmusermodulesPK;
    private Boolean canread;
    private Boolean canwrite;
    private Boolean candelete;
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
        @JoinColumn(name = "SUBMODULEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmsubmodules crmsubmodules;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "USERID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmusers crmusers;
    @JoinColumns({
        @JoinColumn(name = "MODULEID", referencedColumnName = "MODULEID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmmodules crmmodules;
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

    public Crmusermodules() {
    }

    public Crmusermodules(CrmusermodulesPK crmusermodulesPK) {
        this.crmusermodulesPK = crmusermodulesPK;
    }

    public Crmusermodules(int id, int pubkey) {
        this.crmusermodulesPK = new CrmusermodulesPK(id, pubkey);
    }

    public CrmusermodulesPK getCrmusermodulesPK() {
        return crmusermodulesPK;
    }

    public void setCrmusermodulesPK(CrmusermodulesPK crmusermodulesPK) {
        this.crmusermodulesPK = crmusermodulesPK;
    }

    public Boolean getCanread() {
        return canread;
    }

    public void setCanread(Boolean canread) {
        this.canread = canread;
    }

    public Boolean getCanwrite() {
        return canwrite;
    }

    public void setCanwrite(Boolean canwrite) {
        this.canwrite = canwrite;
    }

    public Boolean getCandelete() {
        return candelete;
    }

    public void setCandelete(Boolean candelete) {
        this.candelete = candelete;
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

    public Crmsubmodules getCrmsubmodules() {
        return crmsubmodules;
    }

    public void setCrmsubmodules(Crmsubmodules crmsubmodules) {
        this.crmsubmodules = crmsubmodules;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Crmusers getCrmusers() {
        return crmusers;
    }

    public void setCrmusers(Crmusers crmusers) {
        this.crmusers = crmusers;
    }

    public Crmmodules getCrmmodules() {
        return crmmodules;
    }

    public void setCrmmodules(Crmmodules crmmodules) {
        this.crmmodules = crmmodules;
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
        hash += (crmusermodulesPK != null ? crmusermodulesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmusermodules)) {
            return false;
        }
        Crmusermodules other = (Crmusermodules) object;
        if ((this.crmusermodulesPK == null && other.crmusermodulesPK != null) || (this.crmusermodulesPK != null && !this.crmusermodulesPK.equals(other.crmusermodulesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmusermodules[ crmusermodulesPK=" + crmusermodulesPK + " ]";
    }

}
