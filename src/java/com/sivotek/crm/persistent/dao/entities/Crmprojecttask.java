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
    @NamedQuery(name = "Crmprojecttask.findAll", query = "SELECT c FROM Crmprojecttask c"),
    @NamedQuery(name = "Crmprojecttask.findByTaskid", query = "SELECT c FROM Crmprojecttask c WHERE c.crmprojecttaskPK.taskid = :taskid"),
    @NamedQuery(name = "Crmprojecttask.findByPubkey", query = "SELECT c FROM Crmprojecttask c WHERE c.crmprojecttaskPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmprojecttask.findByX", query = "SELECT c FROM Crmprojecttask c WHERE c.x = :x"),
    @NamedQuery(name = "Crmprojecttask.findByY", query = "SELECT c FROM Crmprojecttask c WHERE c.y = :y"),
    @NamedQuery(name = "Crmprojecttask.findByName", query = "SELECT c FROM Crmprojecttask c WHERE c.name = :name"),
    @NamedQuery(name = "Crmprojecttask.findByStatus", query = "SELECT c FROM Crmprojecttask c WHERE c.status = :status"),
    @NamedQuery(name = "Crmprojecttask.findByType", query = "SELECT c FROM Crmprojecttask c WHERE c.type = :type"),
    @NamedQuery(name = "Crmprojecttask.findByDuration", query = "SELECT c FROM Crmprojecttask c WHERE c.duration = :duration"),
    @NamedQuery(name = "Crmprojecttask.findByColor", query = "SELECT c FROM Crmprojecttask c WHERE c.color = :color"),
    @NamedQuery(name = "Crmprojecttask.findByDone", query = "SELECT c FROM Crmprojecttask c WHERE c.done = :done"),
    @NamedQuery(name = "Crmprojecttask.findByCreateddate", query = "SELECT c FROM Crmprojecttask c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmprojecttask.findByCreatedfrom", query = "SELECT c FROM Crmprojecttask c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmprojecttask.findByChangeddate", query = "SELECT c FROM Crmprojecttask c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmprojecttask.findByChangedfrom", query = "SELECT c FROM Crmprojecttask c WHERE c.changedfrom = :changedfrom")})
public class Crmprojecttask implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmprojecttaskPK crmprojecttaskPK;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String parentstaskid;
    private Integer x;
    private Integer y;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Size(max = 255)
    @Column(length = 255)
    private String status;
    @Size(max = 255)
    @Column(length = 255)
    private String type;
    @Size(max = 50)
    @Column(length = 50)
    private String duration;
    @Size(max = 12)
    @Column(length = 12)
    private String color;
    private Integer done;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmprojecttask")
    private Collection<Crmscheduler> crmschedulerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmprojecttask")
    private Collection<Crmprojectteammembers> crmprojectteammembersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmprojecttask")
    private Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmprojecttask1")
    private Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection1;
    @JoinColumns({
        @JoinColumn(name = "PROJECTID", referencedColumnName = "PROJECTID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmproject crmproject;
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

    public Crmprojecttask() {
    }

    public Crmprojecttask(CrmprojecttaskPK crmprojecttaskPK) {
        this.crmprojecttaskPK = crmprojecttaskPK;
    }

    public Crmprojecttask(int taskid, int pubkey) {
        this.crmprojecttaskPK = new CrmprojecttaskPK(taskid, pubkey);
    }

    public CrmprojecttaskPK getCrmprojecttaskPK() {
        return crmprojecttaskPK;
    }

    public void setCrmprojecttaskPK(CrmprojecttaskPK crmprojecttaskPK) {
        this.crmprojecttaskPK = crmprojecttaskPK;
    }

    public String getParentstaskid() {
        return parentstaskid;
    }

    public void setParentstaskid(String parentstaskid) {
        this.parentstaskid = parentstaskid;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
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

    @XmlTransient
    public Collection<Crmscheduler> getCrmschedulerCollection() {
        return crmschedulerCollection;
    }

    public void setCrmschedulerCollection(Collection<Crmscheduler> crmschedulerCollection) {
        this.crmschedulerCollection = crmschedulerCollection;
    }

    @XmlTransient
    public Collection<Crmprojectteammembers> getCrmprojectteammembersCollection() {
        return crmprojectteammembersCollection;
    }

    public void setCrmprojectteammembersCollection(Collection<Crmprojectteammembers> crmprojectteammembersCollection) {
        this.crmprojectteammembersCollection = crmprojectteammembersCollection;
    }

    @XmlTransient
    public Collection<Crmprojectticketmanagement> getCrmprojectticketmanagementCollection() {
        return crmprojectticketmanagementCollection;
    }

    public void setCrmprojectticketmanagementCollection(Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection) {
        this.crmprojectticketmanagementCollection = crmprojectticketmanagementCollection;
    }

    @XmlTransient
    public Collection<Crmprojectticketmanagement> getCrmprojectticketmanagementCollection1() {
        return crmprojectticketmanagementCollection1;
    }

    public void setCrmprojectticketmanagementCollection1(Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection1) {
        this.crmprojectticketmanagementCollection1 = crmprojectticketmanagementCollection1;
    }

    public Crmproject getCrmproject() {
        return crmproject;
    }

    public void setCrmproject(Crmproject crmproject) {
        this.crmproject = crmproject;
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
        hash += (crmprojecttaskPK != null ? crmprojecttaskPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmprojecttask)) {
            return false;
        }
        Crmprojecttask other = (Crmprojecttask) object;
        if ((this.crmprojecttaskPK == null && other.crmprojecttaskPK != null) || (this.crmprojecttaskPK != null && !this.crmprojecttaskPK.equals(other.crmprojecttaskPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmprojecttask[ crmprojecttaskPK=" + crmprojecttaskPK + " ]";
    }

}
