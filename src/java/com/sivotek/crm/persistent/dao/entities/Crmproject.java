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
    @NamedQuery(name = "Crmproject.findAll", query = "SELECT c FROM Crmproject c"),
    @NamedQuery(name = "Crmproject.findByProjectid", query = "SELECT c FROM Crmproject c WHERE c.crmprojectPK.projectid = :projectid"),
    @NamedQuery(name = "Crmproject.findByPubkey", query = "SELECT c FROM Crmproject c WHERE c.crmprojectPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmproject.findByCompanyid", query = "SELECT c FROM Crmproject c WHERE c.companyid = :companyid"),
    @NamedQuery(name = "Crmproject.findByProjectnr", query = "SELECT c FROM Crmproject c WHERE c.projectnr = :projectnr"),
    @NamedQuery(name = "Crmproject.findByName", query = "SELECT c FROM Crmproject c WHERE c.name = :name"),
    @NamedQuery(name = "Crmproject.findByBudget", query = "SELECT c FROM Crmproject c WHERE c.budget = :budget"),
    @NamedQuery(name = "Crmproject.findByActualcost", query = "SELECT c FROM Crmproject c WHERE c.actualcost = :actualcost"),
    @NamedQuery(name = "Crmproject.findByRemaincost", query = "SELECT c FROM Crmproject c WHERE c.remaincost = :remaincost"),
    @NamedQuery(name = "Crmproject.findByValidfrom", query = "SELECT c FROM Crmproject c WHERE c.validfrom = :validfrom"),
    @NamedQuery(name = "Crmproject.findByValidto", query = "SELECT c FROM Crmproject c WHERE c.validto = :validto"),
    @NamedQuery(name = "Crmproject.findByCreateddate", query = "SELECT c FROM Crmproject c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmproject.findByCreatedfrom", query = "SELECT c FROM Crmproject c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmproject.findByChangeddate", query = "SELECT c FROM Crmproject c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmproject.findByChangedfrom", query = "SELECT c FROM Crmproject c WHERE c.changedfrom = :changedfrom")})
public class Crmproject implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmprojectPK crmprojectPK;
    private Integer companyid;
    @Size(max = 255)
    @Column(length = 255)
    private String projectnr;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double budget;
    @Column(precision = 22)
    private Double actualcost;
    @Column(precision = 22)
    private Double remaincost;
    @Temporal(TemporalType.TIMESTAMP)
    private Date validfrom;
    @Temporal(TemporalType.TIMESTAMP)
    private Date validto;
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
        @JoinColumn(name = "MANAGER", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "PROJECTSTATUS", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmprojectstatus crmprojectstatus;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmproject")
    private Collection<Crmprojectteammembers> crmprojectteammembersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmproject")
    private Collection<Crmprojecttask> crmprojecttaskCollection;

    public Crmproject() {
    }

    public Crmproject(CrmprojectPK crmprojectPK) {
        this.crmprojectPK = crmprojectPK;
    }

    public Crmproject(int projectid, int pubkey) {
        this.crmprojectPK = new CrmprojectPK(projectid, pubkey);
    }

    public CrmprojectPK getCrmprojectPK() {
        return crmprojectPK;
    }

    public void setCrmprojectPK(CrmprojectPK crmprojectPK) {
        this.crmprojectPK = crmprojectPK;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getProjectnr() {
        return projectnr;
    }

    public void setProjectnr(String projectnr) {
        this.projectnr = projectnr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getActualcost() {
        return actualcost;
    }

    public void setActualcost(Double actualcost) {
        this.actualcost = actualcost;
    }

    public Double getRemaincost() {
        return remaincost;
    }

    public void setRemaincost(Double remaincost) {
        this.remaincost = remaincost;
    }

    public Date getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(Date validfrom) {
        this.validfrom = validfrom;
    }

    public Date getValidto() {
        return validto;
    }

    public void setValidto(Date validto) {
        this.validto = validto;
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

    public Companyemployee getCompanyemployee() {
        return companyemployee;
    }

    public void setCompanyemployee(Companyemployee companyemployee) {
        this.companyemployee = companyemployee;
    }

    public Crmprojectstatus getCrmprojectstatus() {
        return crmprojectstatus;
    }

    public void setCrmprojectstatus(Crmprojectstatus crmprojectstatus) {
        this.crmprojectstatus = crmprojectstatus;
    }

    public Companyemployee getCompanyemployee1() {
        return companyemployee1;
    }

    public void setCompanyemployee1(Companyemployee companyemployee1) {
        this.companyemployee1 = companyemployee1;
    }

    public Companyemployee getCompanyemployee2() {
        return companyemployee2;
    }

    public void setCompanyemployee2(Companyemployee companyemployee2) {
        this.companyemployee2 = companyemployee2;
    }

    @XmlTransient
    public Collection<Crmprojectteammembers> getCrmprojectteammembersCollection() {
        return crmprojectteammembersCollection;
    }

    public void setCrmprojectteammembersCollection(Collection<Crmprojectteammembers> crmprojectteammembersCollection) {
        this.crmprojectteammembersCollection = crmprojectteammembersCollection;
    }

    @XmlTransient
    public Collection<Crmprojecttask> getCrmprojecttaskCollection() {
        return crmprojecttaskCollection;
    }

    public void setCrmprojecttaskCollection(Collection<Crmprojecttask> crmprojecttaskCollection) {
        this.crmprojecttaskCollection = crmprojecttaskCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmprojectPK != null ? crmprojectPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmproject)) {
            return false;
        }
        Crmproject other = (Crmproject) object;
        if ((this.crmprojectPK == null && other.crmprojectPK != null) || (this.crmprojectPK != null && !this.crmprojectPK.equals(other.crmprojectPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmproject[ crmprojectPK=" + crmprojectPK + " ]";
    }

}
