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
    @NamedQuery(name = "Customercategory.findAll", query = "SELECT c FROM Customercategory c"),
    @NamedQuery(name = "Customercategory.findById", query = "SELECT c FROM Customercategory c WHERE c.customercategoryPK.id = :id"),
    @NamedQuery(name = "Customercategory.findByPubkey", query = "SELECT c FROM Customercategory c WHERE c.customercategoryPK.pubkey = :pubkey"),
    @NamedQuery(name = "Customercategory.findByName", query = "SELECT c FROM Customercategory c WHERE c.name = :name"),
    @NamedQuery(name = "Customercategory.findByCreateddate", query = "SELECT c FROM Customercategory c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Customercategory.findByCreatedfrom", query = "SELECT c FROM Customercategory c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Customercategory.findByChangeddate", query = "SELECT c FROM Customercategory c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Customercategory.findByChangedfrom", query = "SELECT c FROM Customercategory c WHERE c.changedfrom = :changedfrom")})
public class Customercategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomercategoryPK customercategoryPK;
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

    public Customercategory() {
    }

    public Customercategory(CustomercategoryPK customercategoryPK) {
        this.customercategoryPK = customercategoryPK;
    }

    public Customercategory(int id, int pubkey) {
        this.customercategoryPK = new CustomercategoryPK(id, pubkey);
    }

    public CustomercategoryPK getCustomercategoryPK() {
        return customercategoryPK;
    }

    public void setCustomercategoryPK(CustomercategoryPK customercategoryPK) {
        this.customercategoryPK = customercategoryPK;
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
        hash += (customercategoryPK != null ? customercategoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customercategory)) {
            return false;
        }
        Customercategory other = (Customercategory) object;
        if ((this.customercategoryPK == null && other.customercategoryPK != null) || (this.customercategoryPK != null && !this.customercategoryPK.equals(other.customercategoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Customercategory[ customercategoryPK=" + customercategoryPK + " ]";
    }

}
