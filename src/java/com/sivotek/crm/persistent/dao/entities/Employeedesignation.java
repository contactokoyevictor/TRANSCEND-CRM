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
    @NamedQuery(name = "Employeedesignation.findAll", query = "SELECT e FROM Employeedesignation e"),
    @NamedQuery(name = "Employeedesignation.findById", query = "SELECT e FROM Employeedesignation e WHERE e.employeedesignationPK.id = :id"),
    @NamedQuery(name = "Employeedesignation.findByPubkey", query = "SELECT e FROM Employeedesignation e WHERE e.employeedesignationPK.pubkey = :pubkey"),
    @NamedQuery(name = "Employeedesignation.findByName", query = "SELECT e FROM Employeedesignation e WHERE e.name = :name"),
    @NamedQuery(name = "Employeedesignation.findByCreateddate", query = "SELECT e FROM Employeedesignation e WHERE e.createddate = :createddate"),
    @NamedQuery(name = "Employeedesignation.findByCreatedfrom", query = "SELECT e FROM Employeedesignation e WHERE e.createdfrom = :createdfrom"),
    @NamedQuery(name = "Employeedesignation.findByChangeddate", query = "SELECT e FROM Employeedesignation e WHERE e.changeddate = :changeddate"),
    @NamedQuery(name = "Employeedesignation.findByChangedfrom", query = "SELECT e FROM Employeedesignation e WHERE e.changedfrom = :changedfrom")})
public class Employeedesignation implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmployeedesignationPK employeedesignationPK;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String descreption;
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
        @JoinColumn(name = "PAYMENTID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companypayments companypayments;
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

    public Employeedesignation() {
    }

    public Employeedesignation(EmployeedesignationPK employeedesignationPK) {
        this.employeedesignationPK = employeedesignationPK;
    }

    public Employeedesignation(int id, int pubkey) {
        this.employeedesignationPK = new EmployeedesignationPK(id, pubkey);
    }

    public EmployeedesignationPK getEmployeedesignationPK() {
        return employeedesignationPK;
    }

    public void setEmployeedesignationPK(EmployeedesignationPK employeedesignationPK) {
        this.employeedesignationPK = employeedesignationPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
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

    public Companypayments getCompanypayments() {
        return companypayments;
    }

    public void setCompanypayments(Companypayments companypayments) {
        this.companypayments = companypayments;
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
        hash += (employeedesignationPK != null ? employeedesignationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employeedesignation)) {
            return false;
        }
        Employeedesignation other = (Employeedesignation) object;
        if ((this.employeedesignationPK == null && other.employeedesignationPK != null) || (this.employeedesignationPK != null && !this.employeedesignationPK.equals(other.employeedesignationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Employeedesignation[ employeedesignationPK=" + employeedesignationPK + " ]";
    }

}
