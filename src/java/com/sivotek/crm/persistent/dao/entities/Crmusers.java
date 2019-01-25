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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author okoyevictor
 */
@Entity
@Table(catalog = "sivotekcrm_", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"EMPLOYEEID"}),
    @UniqueConstraint(columnNames = {"CRMUSER"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crmusers.findAll", query = "SELECT c FROM Crmusers c"),
    @NamedQuery(name = "Crmusers.findById", query = "SELECT c FROM Crmusers c WHERE c.crmusersPK.id = :id"),
    @NamedQuery(name = "Crmusers.findByPubkey", query = "SELECT c FROM Crmusers c WHERE c.crmusersPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmusers.findByEmployeeid", query = "SELECT c FROM Crmusers c WHERE c.employeeid = :employeeid"),
    @NamedQuery(name = "Crmusers.findByCrmuser", query = "SELECT c FROM Crmusers c WHERE c.crmuser = :crmuser"),
    @NamedQuery(name = "Crmusers.findByCrmuserPasswd", query = "SELECT c FROM Crmusers c WHERE c.crmuser = :crmuser AND c.passwd = :passwd"),
    
    @NamedQuery(name = "Crmusers.findByPasswd", query = "SELECT c FROM Crmusers c WHERE c.passwd = :passwd"),
    @NamedQuery(name = "Crmusers.findByIsAdmin", query = "SELECT c FROM Crmusers c WHERE c.isAdmin = :isAdmin"),
    @NamedQuery(name = "Crmusers.findByCreateddate", query = "SELECT c FROM Crmusers c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmusers.findByCreatedfrom", query = "SELECT c FROM Crmusers c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmusers.findByCreatedby", query = "SELECT c FROM Crmusers c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "Crmusers.findByChangeddate", query = "SELECT c FROM Crmusers c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmusers.findByChangedfrom", query = "SELECT c FROM Crmusers c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmusers.findByChangedby", query = "SELECT c FROM Crmusers c WHERE c.changedby = :changedby")})
public class Crmusers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmusersPK crmusersPK;
    private Integer employeeid;
    @Size(max = 50)
    @Column(length = 50)
    private String crmuser;
    @Size(max = 255)
    @Column(length = 255)
    private String passwd;
    @Column(name = "IS_ADMIN")
    private Boolean isAdmin;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmusers")
    private Collection<Crmusermodules> crmusermodulesCollection;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;

    public Crmusers() {
    }

    public Crmusers(CrmusersPK crmusersPK) {
        this.crmusersPK = crmusersPK;
    }

    public Crmusers(int id, int pubkey) {
        this.crmusersPK = new CrmusersPK(id, pubkey);
    }

    public CrmusersPK getCrmusersPK() {
        return crmusersPK;
    }

    public void setCrmusersPK(CrmusersPK crmusersPK) {
        this.crmusersPK = crmusersPK;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getCrmuser() {
        return crmuser;
    }

    public void setCrmuser(String crmuser) {
        this.crmuser = crmuser;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
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

    @XmlTransient
    public Collection<Crmusermodules> getCrmusermodulesCollection() {
        return crmusermodulesCollection;
    }

    public void setCrmusermodulesCollection(Collection<Crmusermodules> crmusermodulesCollection) {
        this.crmusermodulesCollection = crmusermodulesCollection;
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
        hash += (crmusersPK != null ? crmusersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmusers)) {
            return false;
        }
        Crmusers other = (Crmusers) object;
        if ((this.crmusersPK == null && other.crmusersPK != null) || (this.crmusersPK != null && !this.crmusersPK.equals(other.crmusersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmusers[ crmusersPK=" + crmusersPK + " ]";
    }

}
