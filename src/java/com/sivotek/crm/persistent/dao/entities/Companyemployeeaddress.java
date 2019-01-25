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
    @NamedQuery(name = "Companyemployeeaddress.findAll", query = "SELECT c FROM Companyemployeeaddress c"),
    @NamedQuery(name = "Companyemployeeaddress.findById", query = "SELECT c FROM Companyemployeeaddress c WHERE c.companyemployeeaddressPK.id = :id"),
    @NamedQuery(name = "Companyemployeeaddress.findByPubkey", query = "SELECT c FROM Companyemployeeaddress c WHERE c.companyemployeeaddressPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companyemployeeaddress.findByStreet", query = "SELECT c FROM Companyemployeeaddress c WHERE c.street = :street"),
    @NamedQuery(name = "Companyemployeeaddress.findByZip", query = "SELECT c FROM Companyemployeeaddress c WHERE c.zip = :zip"),
    @NamedQuery(name = "Companyemployeeaddress.findByLocation", query = "SELECT c FROM Companyemployeeaddress c WHERE c.location = :location"),
    @NamedQuery(name = "Companyemployeeaddress.findByPbox", query = "SELECT c FROM Companyemployeeaddress c WHERE c.pbox = :pbox"),
    @NamedQuery(name = "Companyemployeeaddress.findByCountry", query = "SELECT c FROM Companyemployeeaddress c WHERE c.country = :country"),
    @NamedQuery(name = "Companyemployeeaddress.findByCreateddate", query = "SELECT c FROM Companyemployeeaddress c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companyemployeeaddress.findByCreatedfrom", query = "SELECT c FROM Companyemployeeaddress c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companyemployeeaddress.findByChangeddate", query = "SELECT c FROM Companyemployeeaddress c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companyemployeeaddress.findByChangedfrom", query = "SELECT c FROM Companyemployeeaddress c WHERE c.changedfrom = :changedfrom")})
public class Companyemployeeaddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyemployeeaddressPK companyemployeeaddressPK;
    @Size(max = 255)
    @Column(length = 255)
    private String street;
    @Size(max = 255)
    @Column(length = 255)
    private String zip;
    @Size(max = 255)
    @Column(length = 255)
    private String location;
    @Size(max = 255)
    @Column(length = 255)
    private String pbox;
    @Size(max = 255)
    @Column(length = 255)
    private String country;
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
        @JoinColumn(name = "EMPLOYEEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "ADDRESSTYPEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyaddresstype companyaddresstype;
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

    public Companyemployeeaddress() {
    }

    public Companyemployeeaddress(CompanyemployeeaddressPK companyemployeeaddressPK) {
        this.companyemployeeaddressPK = companyemployeeaddressPK;
    }

    public Companyemployeeaddress(int id, int pubkey) {
        this.companyemployeeaddressPK = new CompanyemployeeaddressPK(id, pubkey);
    }

    public CompanyemployeeaddressPK getCompanyemployeeaddressPK() {
        return companyemployeeaddressPK;
    }

    public void setCompanyemployeeaddressPK(CompanyemployeeaddressPK companyemployeeaddressPK) {
        this.companyemployeeaddressPK = companyemployeeaddressPK;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPbox() {
        return pbox;
    }

    public void setPbox(String pbox) {
        this.pbox = pbox;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Companyaddresstype getCompanyaddresstype() {
        return companyaddresstype;
    }

    public void setCompanyaddresstype(Companyaddresstype companyaddresstype) {
        this.companyaddresstype = companyaddresstype;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyemployeeaddressPK != null ? companyemployeeaddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyemployeeaddress)) {
            return false;
        }
        Companyemployeeaddress other = (Companyemployeeaddress) object;
        if ((this.companyemployeeaddressPK == null && other.companyemployeeaddressPK != null) || (this.companyemployeeaddressPK != null && !this.companyemployeeaddressPK.equals(other.companyemployeeaddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companyemployeeaddress[ companyemployeeaddressPK=" + companyemployeeaddressPK + " ]";
    }

}
