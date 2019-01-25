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
    @NamedQuery(name = "Crmworkordertype.findAll", query = "SELECT c FROM Crmworkordertype c"),
    @NamedQuery(name = "Crmworkordertype.findById", query = "SELECT c FROM Crmworkordertype c WHERE c.crmworkordertypePK.id = :id"),
    @NamedQuery(name = "Crmworkordertype.findByPubkey", query = "SELECT c FROM Crmworkordertype c WHERE c.crmworkordertypePK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmworkordertype.findByName", query = "SELECT c FROM Crmworkordertype c WHERE c.name = :name"),
    @NamedQuery(name = "Crmworkordertype.findByCreateddate", query = "SELECT c FROM Crmworkordertype c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmworkordertype.findByCreatedfrom", query = "SELECT c FROM Crmworkordertype c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmworkordertype.findByChangeddate", query = "SELECT c FROM Crmworkordertype c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmworkordertype.findByChangedfrom", query = "SELECT c FROM Crmworkordertype c WHERE c.changedfrom = :changedfrom")})
public class Crmworkordertype implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmworkordertypePK crmworkordertypePK;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmworkordertype")
    private Collection<Crmworkorder> crmworkorderCollection;
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

    public Crmworkordertype() {
    }

    public Crmworkordertype(CrmworkordertypePK crmworkordertypePK) {
        this.crmworkordertypePK = crmworkordertypePK;
    }

    public Crmworkordertype(int id, int pubkey) {
        this.crmworkordertypePK = new CrmworkordertypePK(id, pubkey);
    }

    public CrmworkordertypePK getCrmworkordertypePK() {
        return crmworkordertypePK;
    }

    public void setCrmworkordertypePK(CrmworkordertypePK crmworkordertypePK) {
        this.crmworkordertypePK = crmworkordertypePK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Collection<Crmworkorder> getCrmworkorderCollection() {
        return crmworkorderCollection;
    }

    public void setCrmworkorderCollection(Collection<Crmworkorder> crmworkorderCollection) {
        this.crmworkorderCollection = crmworkorderCollection;
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
        hash += (crmworkordertypePK != null ? crmworkordertypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmworkordertype)) {
            return false;
        }
        Crmworkordertype other = (Crmworkordertype) object;
        if ((this.crmworkordertypePK == null && other.crmworkordertypePK != null) || (this.crmworkordertypePK != null && !this.crmworkordertypePK.equals(other.crmworkordertypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmworkordertype[ crmworkordertypePK=" + crmworkordertypePK + " ]";
    }

}
