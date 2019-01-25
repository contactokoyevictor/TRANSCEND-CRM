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
    @NamedQuery(name = "Crmexpense.findAll", query = "SELECT c FROM Crmexpense c"),
    @NamedQuery(name = "Crmexpense.findById", query = "SELECT c FROM Crmexpense c WHERE c.crmexpensePK.id = :id"),
    @NamedQuery(name = "Crmexpense.findByPubkey", query = "SELECT c FROM Crmexpense c WHERE c.crmexpensePK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmexpense.findByLineqty", query = "SELECT c FROM Crmexpense c WHERE c.lineqty = :lineqty"),
    @NamedQuery(name = "Crmexpense.findByEstimatedcost", query = "SELECT c FROM Crmexpense c WHERE c.estimatedcost = :estimatedcost"),
    @NamedQuery(name = "Crmexpense.findByActualcost", query = "SELECT c FROM Crmexpense c WHERE c.actualcost = :actualcost"),
    @NamedQuery(name = "Crmexpense.findByTotalcost", query = "SELECT c FROM Crmexpense c WHERE c.totalcost = :totalcost"),
    @NamedQuery(name = "Crmexpense.findByIsBILLABLE", query = "SELECT c FROM Crmexpense c WHERE c.isBILLABLE = :isBILLABLE"),
    @NamedQuery(name = "Crmexpense.findByCreateddate", query = "SELECT c FROM Crmexpense c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmexpense.findByCreatedfrom", query = "SELECT c FROM Crmexpense c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmexpense.findByChangeddate", query = "SELECT c FROM Crmexpense c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmexpense.findByChangedfrom", query = "SELECT c FROM Crmexpense c WHERE c.changedfrom = :changedfrom")})
public class Crmexpense implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmexpensePK crmexpensePK;
    private Integer lineqty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double estimatedcost;
    @Column(precision = 22)
    private Double actualcost;
    @Column(precision = 22)
    private Double totalcost;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    private Boolean isBILLABLE;
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
        @JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companycustomer companycustomer;
    @JoinColumns({
        @JoinColumn(name = "EXPENSETYPEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmexpensetype crmexpensetype;
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
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;

    public Crmexpense() {
    }

    public Crmexpense(CrmexpensePK crmexpensePK) {
        this.crmexpensePK = crmexpensePK;
    }

    public Crmexpense(int id, int pubkey) {
        this.crmexpensePK = new CrmexpensePK(id, pubkey);
    }

    public CrmexpensePK getCrmexpensePK() {
        return crmexpensePK;
    }

    public void setCrmexpensePK(CrmexpensePK crmexpensePK) {
        this.crmexpensePK = crmexpensePK;
    }

    public Integer getLineqty() {
        return lineqty;
    }

    public void setLineqty(Integer lineqty) {
        this.lineqty = lineqty;
    }

    public Double getEstimatedcost() {
        return estimatedcost;
    }

    public void setEstimatedcost(Double estimatedcost) {
        this.estimatedcost = estimatedcost;
    }

    public Double getActualcost() {
        return actualcost;
    }

    public void setActualcost(Double actualcost) {
        this.actualcost = actualcost;
    }

    public Double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(Double totalcost) {
        this.totalcost = totalcost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsBILLABLE() {
        return isBILLABLE;
    }

    public void setIsBILLABLE(Boolean isBILLABLE) {
        this.isBILLABLE = isBILLABLE;
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

    public Companycustomer getCompanycustomer() {
        return companycustomer;
    }

    public void setCompanycustomer(Companycustomer companycustomer) {
        this.companycustomer = companycustomer;
    }

    public Crmexpensetype getCrmexpensetype() {
        return crmexpensetype;
    }

    public void setCrmexpensetype(Crmexpensetype crmexpensetype) {
        this.crmexpensetype = crmexpensetype;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmexpensePK != null ? crmexpensePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmexpense)) {
            return false;
        }
        Crmexpense other = (Crmexpense) object;
        if ((this.crmexpensePK == null && other.crmexpensePK != null) || (this.crmexpensePK != null && !this.crmexpensePK.equals(other.crmexpensePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmexpense[ crmexpensePK=" + crmexpensePK + " ]";
    }

}
