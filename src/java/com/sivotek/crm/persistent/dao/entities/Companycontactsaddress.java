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
    @NamedQuery(name = "Companycontactsaddress.findAll", query = "SELECT c FROM Companycontactsaddress c"),
    @NamedQuery(name = "Companycontactsaddress.findById", query = "SELECT c FROM Companycontactsaddress c WHERE c.companycontactsaddressPK.id = :id"),
    @NamedQuery(name = "Companycontactsaddress.findByPubkey", query = "SELECT c FROM Companycontactsaddress c WHERE c.companycontactsaddressPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companycontactsaddress.findByAddresstypeid", query = "SELECT c FROM Companycontactsaddress c WHERE c.addresstypeid = :addresstypeid"),
    @NamedQuery(name = "Companycontactsaddress.findByStreet", query = "SELECT c FROM Companycontactsaddress c WHERE c.street = :street"),
    @NamedQuery(name = "Companycontactsaddress.findByZip", query = "SELECT c FROM Companycontactsaddress c WHERE c.zip = :zip"),
    @NamedQuery(name = "Companycontactsaddress.findByLocation", query = "SELECT c FROM Companycontactsaddress c WHERE c.location = :location"),
    @NamedQuery(name = "Companycontactsaddress.findByPbox", query = "SELECT c FROM Companycontactsaddress c WHERE c.pbox = :pbox"),
    @NamedQuery(name = "Companycontactsaddress.findByCountry", query = "SELECT c FROM Companycontactsaddress c WHERE c.country = :country"),
    @NamedQuery(name = "Companycontactsaddress.findByCreateddate", query = "SELECT c FROM Companycontactsaddress c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companycontactsaddress.findByCreatedfrom", query = "SELECT c FROM Companycontactsaddress c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companycontactsaddress.findByChangeddate", query = "SELECT c FROM Companycontactsaddress c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companycontactsaddress.findByChangedfrom", query = "SELECT c FROM Companycontactsaddress c WHERE c.changedfrom = :changedfrom")})
public class Companycontactsaddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanycontactsaddressPK companycontactsaddressPK;
    private Integer addresstypeid;
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
    @JoinColumns({
        @JoinColumn(name = "CONTACTID", referencedColumnName = "CONTACTID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companycontacts companycontacts;

    public Companycontactsaddress() {
    }

    public Companycontactsaddress(CompanycontactsaddressPK companycontactsaddressPK) {
        this.companycontactsaddressPK = companycontactsaddressPK;
    }

    public Companycontactsaddress(int id, int pubkey) {
        this.companycontactsaddressPK = new CompanycontactsaddressPK(id, pubkey);
    }

    public CompanycontactsaddressPK getCompanycontactsaddressPK() {
        return companycontactsaddressPK;
    }

    public void setCompanycontactsaddressPK(CompanycontactsaddressPK companycontactsaddressPK) {
        this.companycontactsaddressPK = companycontactsaddressPK;
    }

    public Integer getAddresstypeid() {
        return addresstypeid;
    }

    public void setAddresstypeid(Integer addresstypeid) {
        this.addresstypeid = addresstypeid;
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

    public Companycontacts getCompanycontacts() {
        return companycontacts;
    }

    public void setCompanycontacts(Companycontacts companycontacts) {
        this.companycontacts = companycontacts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companycontactsaddressPK != null ? companycontactsaddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companycontactsaddress)) {
            return false;
        }
        Companycontactsaddress other = (Companycontactsaddress) object;
        if ((this.companycontactsaddressPK == null && other.companycontactsaddressPK != null) || (this.companycontactsaddressPK != null && !this.companycontactsaddressPK.equals(other.companycontactsaddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companycontactsaddress[ companycontactsaddressPK=" + companycontactsaddressPK + " ]";
    }

}
