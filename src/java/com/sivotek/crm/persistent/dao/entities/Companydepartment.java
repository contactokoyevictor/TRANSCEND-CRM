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
    @NamedQuery(name = "Companydepartment.findAll", query = "SELECT c FROM Companydepartment c"),
    @NamedQuery(name = "Companydepartment.findById", query = "SELECT c FROM Companydepartment c WHERE c.companydepartmentPK.id = :id"),
    @NamedQuery(name = "Companydepartment.findByPubkey", query = "SELECT c FROM Companydepartment c WHERE c.companydepartmentPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companydepartment.findByDepartmentName", query = "SELECT c FROM Companydepartment c WHERE c.departmentName = :departmentName"),
    @NamedQuery(name = "Companydepartment.findByDepartmentCode", query = "SELECT c FROM Companydepartment c WHERE c.departmentCode = :departmentCode"),
    @NamedQuery(name = "Companydepartment.findByDepartmentHeads", query = "SELECT c FROM Companydepartment c WHERE c.departmentHeads = :departmentHeads"),
    @NamedQuery(name = "Companydepartment.findByCreateddate", query = "SELECT c FROM Companydepartment c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companydepartment.findByCreatedfrom", query = "SELECT c FROM Companydepartment c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companydepartment.findByChangeddate", query = "SELECT c FROM Companydepartment c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companydepartment.findByChangedfrom", query = "SELECT c FROM Companydepartment c WHERE c.changedfrom = :changedfrom")})
public class Companydepartment implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanydepartmentPK companydepartmentPK;
    @Size(max = 255)
    @Column(name = "DEPARTMENT_NAME", length = 255)
    private String departmentName;
    @Size(max = 255)
    @Column(name = "DEPARTMENT_CODE", length = 255)
    private String departmentCode;
    @Column(name = "DEPARTMENT_HEADS")
    private Integer departmentHeads;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companydepartment")
    private Collection<Companyemployee> companyemployeeCollection;

    public Companydepartment() {
    }

    public Companydepartment(CompanydepartmentPK companydepartmentPK) {
        this.companydepartmentPK = companydepartmentPK;
    }

    public Companydepartment(int id, int pubkey) {
        this.companydepartmentPK = new CompanydepartmentPK(id, pubkey);
    }

    public CompanydepartmentPK getCompanydepartmentPK() {
        return companydepartmentPK;
    }

    public void setCompanydepartmentPK(CompanydepartmentPK companydepartmentPK) {
        this.companydepartmentPK = companydepartmentPK;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Integer getDepartmentHeads() {
        return departmentHeads;
    }

    public void setDepartmentHeads(Integer departmentHeads) {
        this.departmentHeads = departmentHeads;
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

    @XmlTransient
    public Collection<Companyemployee> getCompanyemployeeCollection() {
        return companyemployeeCollection;
    }

    public void setCompanyemployeeCollection(Collection<Companyemployee> companyemployeeCollection) {
        this.companyemployeeCollection = companyemployeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companydepartmentPK != null ? companydepartmentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companydepartment)) {
            return false;
        }
        Companydepartment other = (Companydepartment) object;
        if ((this.companydepartmentPK == null && other.companydepartmentPK != null) || (this.companydepartmentPK != null && !this.companydepartmentPK.equals(other.companydepartmentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companydepartment[ companydepartmentPK=" + companydepartmentPK + " ]";
    }

}
