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
    @NamedQuery(name = "Crmlabortype.findAll", query = "SELECT c FROM Crmlabortype c"),
    @NamedQuery(name = "Crmlabortype.findById", query = "SELECT c FROM Crmlabortype c WHERE c.crmlabortypePK.id = :id"),
    @NamedQuery(name = "Crmlabortype.findByPubkey", query = "SELECT c FROM Crmlabortype c WHERE c.crmlabortypePK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmlabortype.findByLaborname", query = "SELECT c FROM Crmlabortype c WHERE c.laborname = :laborname"),
    @NamedQuery(name = "Crmlabortype.findByCreateddate", query = "SELECT c FROM Crmlabortype c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmlabortype.findByCreatedfrom", query = "SELECT c FROM Crmlabortype c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmlabortype.findByChangeddate", query = "SELECT c FROM Crmlabortype c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmlabortype.findByChangedfrom", query = "SELECT c FROM Crmlabortype c WHERE c.changedfrom = :changedfrom")})
public class Crmlabortype implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmlabortypePK crmlabortypePK;
    @Size(max = 255)
    @Column(length = 255)
    private String laborname;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmlabortype")
    private Collection<Crmlabor> crmlaborCollection;
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

    public Crmlabortype() {
    }

    public Crmlabortype(CrmlabortypePK crmlabortypePK) {
        this.crmlabortypePK = crmlabortypePK;
    }

    public Crmlabortype(int id, int pubkey) {
        this.crmlabortypePK = new CrmlabortypePK(id, pubkey);
    }

    public CrmlabortypePK getCrmlabortypePK() {
        return crmlabortypePK;
    }

    public void setCrmlabortypePK(CrmlabortypePK crmlabortypePK) {
        this.crmlabortypePK = crmlabortypePK;
    }

    public String getLaborname() {
        return laborname;
    }

    public void setLaborname(String laborname) {
        this.laborname = laborname;
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
    public Collection<Crmlabor> getCrmlaborCollection() {
        return crmlaborCollection;
    }

    public void setCrmlaborCollection(Collection<Crmlabor> crmlaborCollection) {
        this.crmlaborCollection = crmlaborCollection;
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
        hash += (crmlabortypePK != null ? crmlabortypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmlabortype)) {
            return false;
        }
        Crmlabortype other = (Crmlabortype) object;
        if ((this.crmlabortypePK == null && other.crmlabortypePK != null) || (this.crmlabortypePK != null && !this.crmlabortypePK.equals(other.crmlabortypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmlabortype[ crmlabortypePK=" + crmlabortypePK + " ]";
    }

}
