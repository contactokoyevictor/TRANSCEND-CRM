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
    @NamedQuery(name = "Customercontactsaddress.findAll", query = "SELECT c FROM Customercontactsaddress c"),
    @NamedQuery(name = "Customercontactsaddress.findById", query = "SELECT c FROM Customercontactsaddress c WHERE c.customercontactsaddressPK.id = :id"),
    @NamedQuery(name = "Customercontactsaddress.findByPubkey", query = "SELECT c FROM Customercontactsaddress c WHERE c.customercontactsaddressPK.pubkey = :pubkey"),
    @NamedQuery(name = "Customercontactsaddress.findByStreet", query = "SELECT c FROM Customercontactsaddress c WHERE c.street = :street"),
    @NamedQuery(name = "Customercontactsaddress.findByZip", query = "SELECT c FROM Customercontactsaddress c WHERE c.zip = :zip"),
    @NamedQuery(name = "Customercontactsaddress.findByLocation", query = "SELECT c FROM Customercontactsaddress c WHERE c.location = :location"),
    @NamedQuery(name = "Customercontactsaddress.findByPbox", query = "SELECT c FROM Customercontactsaddress c WHERE c.pbox = :pbox"),
    @NamedQuery(name = "Customercontactsaddress.findByCountry", query = "SELECT c FROM Customercontactsaddress c WHERE c.country = :country"),
    @NamedQuery(name = "Customercontactsaddress.findByCreateddate", query = "SELECT c FROM Customercontactsaddress c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Customercontactsaddress.findByCreatedfrom", query = "SELECT c FROM Customercontactsaddress c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Customercontactsaddress.findByChangeddate", query = "SELECT c FROM Customercontactsaddress c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Customercontactsaddress.findByChangedfrom", query = "SELECT c FROM Customercontactsaddress c WHERE c.changedfrom = :changedfrom")})
public class Customercontactsaddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomercontactsaddressPK customercontactsaddressPK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customercontactsaddress")
    private Collection<Crmcampaignreceiver> crmcampaignreceiverCollection;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "C_CONTACTID", referencedColumnName = "CONTACTID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Customercontacts customercontacts;
    @JoinColumns({
        @JoinColumn(name = "C_ADDRESSTYPEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyaddresstype companyaddresstype;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;

    public Customercontactsaddress() {
    }

    public Customercontactsaddress(CustomercontactsaddressPK customercontactsaddressPK) {
        this.customercontactsaddressPK = customercontactsaddressPK;
    }

    public Customercontactsaddress(int id, int pubkey) {
        this.customercontactsaddressPK = new CustomercontactsaddressPK(id, pubkey);
    }

    public CustomercontactsaddressPK getCustomercontactsaddressPK() {
        return customercontactsaddressPK;
    }

    public void setCustomercontactsaddressPK(CustomercontactsaddressPK customercontactsaddressPK) {
        this.customercontactsaddressPK = customercontactsaddressPK;
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

    @XmlTransient
    public Collection<Crmcampaignreceiver> getCrmcampaignreceiverCollection() {
        return crmcampaignreceiverCollection;
    }

    public void setCrmcampaignreceiverCollection(Collection<Crmcampaignreceiver> crmcampaignreceiverCollection) {
        this.crmcampaignreceiverCollection = crmcampaignreceiverCollection;
    }

    public Companyemployee getCompanyemployee() {
        return companyemployee;
    }

    public void setCompanyemployee(Companyemployee companyemployee) {
        this.companyemployee = companyemployee;
    }

    public Customercontacts getCustomercontacts() {
        return customercontacts;
    }

    public void setCustomercontacts(Customercontacts customercontacts) {
        this.customercontacts = customercontacts;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customercontactsaddressPK != null ? customercontactsaddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customercontactsaddress)) {
            return false;
        }
        Customercontactsaddress other = (Customercontactsaddress) object;
        if ((this.customercontactsaddressPK == null && other.customercontactsaddressPK != null) || (this.customercontactsaddressPK != null && !this.customercontactsaddressPK.equals(other.customercontactsaddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Customercontactsaddress[ customercontactsaddressPK=" + customercontactsaddressPK + " ]";
    }

}
