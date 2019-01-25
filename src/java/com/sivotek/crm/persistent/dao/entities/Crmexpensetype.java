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
    @NamedQuery(name = "Crmexpensetype.findAll", query = "SELECT c FROM Crmexpensetype c"),
    @NamedQuery(name = "Crmexpensetype.findById", query = "SELECT c FROM Crmexpensetype c WHERE c.crmexpensetypePK.id = :id"),
    @NamedQuery(name = "Crmexpensetype.findByPubkey", query = "SELECT c FROM Crmexpensetype c WHERE c.crmexpensetypePK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmexpensetype.findByExpensename", query = "SELECT c FROM Crmexpensetype c WHERE c.expensename = :expensename"),
    @NamedQuery(name = "Crmexpensetype.findByCreateddate", query = "SELECT c FROM Crmexpensetype c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmexpensetype.findByCreatedfrom", query = "SELECT c FROM Crmexpensetype c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmexpensetype.findByChangeddate", query = "SELECT c FROM Crmexpensetype c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmexpensetype.findByChangedfrom", query = "SELECT c FROM Crmexpensetype c WHERE c.changedfrom = :changedfrom")})
public class Crmexpensetype implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmexpensetypePK crmexpensetypePK;
    @Size(max = 255)
    @Column(length = 255)
    private String expensename;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmexpensetype")
    private Collection<Crmexpense> crmexpenseCollection;
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

    public Crmexpensetype() {
    }

    public Crmexpensetype(CrmexpensetypePK crmexpensetypePK) {
        this.crmexpensetypePK = crmexpensetypePK;
    }

    public Crmexpensetype(int id, int pubkey) {
        this.crmexpensetypePK = new CrmexpensetypePK(id, pubkey);
    }

    public CrmexpensetypePK getCrmexpensetypePK() {
        return crmexpensetypePK;
    }

    public void setCrmexpensetypePK(CrmexpensetypePK crmexpensetypePK) {
        this.crmexpensetypePK = crmexpensetypePK;
    }

    public String getExpensename() {
        return expensename;
    }

    public void setExpensename(String expensename) {
        this.expensename = expensename;
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
    public Collection<Crmexpense> getCrmexpenseCollection() {
        return crmexpenseCollection;
    }

    public void setCrmexpenseCollection(Collection<Crmexpense> crmexpenseCollection) {
        this.crmexpenseCollection = crmexpenseCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmexpensetypePK != null ? crmexpensetypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmexpensetype)) {
            return false;
        }
        Crmexpensetype other = (Crmexpensetype) object;
        if ((this.crmexpensetypePK == null && other.crmexpensetypePK != null) || (this.crmexpensetypePK != null && !this.crmexpensetypePK.equals(other.crmexpensetypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmexpensetype[ crmexpensetypePK=" + crmexpensetypePK + " ]";
    }

}
