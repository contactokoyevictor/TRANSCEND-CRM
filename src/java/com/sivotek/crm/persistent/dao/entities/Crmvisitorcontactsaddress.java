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
    @NamedQuery(name = "Crmvisitorcontactsaddress.findAll", query = "SELECT c FROM Crmvisitorcontactsaddress c"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByContactaddressid", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.crmvisitorcontactsaddressPK.contactaddressid = :contactaddressid"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByPubkey", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.crmvisitorcontactsaddressPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByFisrtName", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.fisrtName = :fisrtName"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByLastName", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByOtherName", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.otherName = :otherName"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByAddresstype", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.addresstype = :addresstype"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByStreet", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.street = :street"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByZip", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.zip = :zip"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByLocation", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.location = :location"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByCountry", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.country = :country"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByPbox", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.pbox = :pbox"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByCreateddate", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByCreatedfrom", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByChangeddate", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByChangedfrom", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmvisitorcontactsaddress.findByChangedby", query = "SELECT c FROM Crmvisitorcontactsaddress c WHERE c.changedby = :changedby")})
public class Crmvisitorcontactsaddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmvisitorcontactsaddressPK crmvisitorcontactsaddressPK;
    @Size(max = 255)
    @Column(name = "FISRT_NAME", length = 255)
    private String fisrtName;
    @Size(max = 255)
    @Column(name = "LAST_NAME", length = 255)
    private String lastName;
    @Size(max = 255)
    @Column(name = "OTHER_NAME", length = 255)
    private String otherName;
    @Size(max = 150)
    @Column(length = 150)
    private String addresstype;
    @Size(max = 80)
    @Column(length = 80)
    private String street;
    @Size(max = 20)
    @Column(length = 20)
    private String zip;
    @Size(max = 150)
    @Column(length = 150)
    private String location;
    @Size(max = 150)
    @Column(length = 150)
    private String country;
    @Size(max = 150)
    @Column(length = 150)
    private String pbox;
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
    private Integer changedby;
    @JoinColumns({
        @JoinColumn(name = "VISITORID", referencedColumnName = "VISITORID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmvisitor crmvisitor;
    @JoinColumns({
        @JoinColumn(name = "CONTACTID", referencedColumnName = "CONTACTID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmvisitorcontacts crmvisitorcontacts;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;

    public Crmvisitorcontactsaddress() {
    }

    public Crmvisitorcontactsaddress(CrmvisitorcontactsaddressPK crmvisitorcontactsaddressPK) {
        this.crmvisitorcontactsaddressPK = crmvisitorcontactsaddressPK;
    }

    public Crmvisitorcontactsaddress(int contactaddressid, int pubkey) {
        this.crmvisitorcontactsaddressPK = new CrmvisitorcontactsaddressPK(contactaddressid, pubkey);
    }

    public CrmvisitorcontactsaddressPK getCrmvisitorcontactsaddressPK() {
        return crmvisitorcontactsaddressPK;
    }

    public void setCrmvisitorcontactsaddressPK(CrmvisitorcontactsaddressPK crmvisitorcontactsaddressPK) {
        this.crmvisitorcontactsaddressPK = crmvisitorcontactsaddressPK;
    }

    public String getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getAddresstype() {
        return addresstype;
    }

    public void setAddresstype(String addresstype) {
        this.addresstype = addresstype;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPbox() {
        return pbox;
    }

    public void setPbox(String pbox) {
        this.pbox = pbox;
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

    public Integer getChangedby() {
        return changedby;
    }

    public void setChangedby(Integer changedby) {
        this.changedby = changedby;
    }

    public Crmvisitor getCrmvisitor() {
        return crmvisitor;
    }

    public void setCrmvisitor(Crmvisitor crmvisitor) {
        this.crmvisitor = crmvisitor;
    }

    public Crmvisitorcontacts getCrmvisitorcontacts() {
        return crmvisitorcontacts;
    }

    public void setCrmvisitorcontacts(Crmvisitorcontacts crmvisitorcontacts) {
        this.crmvisitorcontacts = crmvisitorcontacts;
    }

    public Companyemployee getCompanyemployee() {
        return companyemployee;
    }

    public void setCompanyemployee(Companyemployee companyemployee) {
        this.companyemployee = companyemployee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmvisitorcontactsaddressPK != null ? crmvisitorcontactsaddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmvisitorcontactsaddress)) {
            return false;
        }
        Crmvisitorcontactsaddress other = (Crmvisitorcontactsaddress) object;
        if ((this.crmvisitorcontactsaddressPK == null && other.crmvisitorcontactsaddressPK != null) || (this.crmvisitorcontactsaddressPK != null && !this.crmvisitorcontactsaddressPK.equals(other.crmvisitorcontactsaddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmvisitorcontactsaddress[ crmvisitorcontactsaddressPK=" + crmvisitorcontactsaddressPK + " ]";
    }

}
