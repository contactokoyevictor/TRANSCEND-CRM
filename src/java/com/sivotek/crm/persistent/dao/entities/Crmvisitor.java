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
    @NamedQuery(name = "Crmvisitor.findAll", query = "SELECT c FROM Crmvisitor c"),
    @NamedQuery(name = "Crmvisitor.findByVisitorid", query = "SELECT c FROM Crmvisitor c WHERE c.crmvisitorPK.visitorid = :visitorid"),
    @NamedQuery(name = "Crmvisitor.findByPubkey", query = "SELECT c FROM Crmvisitor c WHERE c.crmvisitorPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmvisitor.findByGender", query = "SELECT c FROM Crmvisitor c WHERE c.gender = :gender"),
    @NamedQuery(name = "Crmvisitor.findByFisrtName", query = "SELECT c FROM Crmvisitor c WHERE c.fisrtName = :fisrtName"),
    @NamedQuery(name = "Crmvisitor.findByLastName", query = "SELECT c FROM Crmvisitor c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Crmvisitor.findByOtherName", query = "SELECT c FROM Crmvisitor c WHERE c.otherName = :otherName"),
    @NamedQuery(name = "Crmvisitor.findByStreet", query = "SELECT c FROM Crmvisitor c WHERE c.street = :street"),
    @NamedQuery(name = "Crmvisitor.findByZip", query = "SELECT c FROM Crmvisitor c WHERE c.zip = :zip"),
    @NamedQuery(name = "Crmvisitor.findByLocation", query = "SELECT c FROM Crmvisitor c WHERE c.location = :location"),
    @NamedQuery(name = "Crmvisitor.findByPbox", query = "SELECT c FROM Crmvisitor c WHERE c.pbox = :pbox"),
    @NamedQuery(name = "Crmvisitor.findByCountry", query = "SELECT c FROM Crmvisitor c WHERE c.country = :country"),
    @NamedQuery(name = "Crmvisitor.findByBirddate", query = "SELECT c FROM Crmvisitor c WHERE c.birddate = :birddate"),
    @NamedQuery(name = "Crmvisitor.findByPhone", query = "SELECT c FROM Crmvisitor c WHERE c.phone = :phone"),
    @NamedQuery(name = "Crmvisitor.findByFax", query = "SELECT c FROM Crmvisitor c WHERE c.fax = :fax"),
    @NamedQuery(name = "Crmvisitor.findByMobile", query = "SELECT c FROM Crmvisitor c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "Crmvisitor.findByEmail", query = "SELECT c FROM Crmvisitor c WHERE c.email = :email"),
    @NamedQuery(name = "Crmvisitor.findByCreateddate", query = "SELECT c FROM Crmvisitor c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmvisitor.findByCreatedfrom", query = "SELECT c FROM Crmvisitor c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmvisitor.findByChangeddate", query = "SELECT c FROM Crmvisitor c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmvisitor.findByChangedfrom", query = "SELECT c FROM Crmvisitor c WHERE c.changedfrom = :changedfrom")})
public class Crmvisitor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmvisitorPK crmvisitorPK;
    @Size(max = 20)
    @Column(length = 20)
    private String gender;
    @Size(max = 255)
    @Column(name = "FISRT_NAME", length = 255)
    private String fisrtName;
    @Size(max = 255)
    @Column(name = "LAST_NAME", length = 255)
    private String lastName;
    @Size(max = 255)
    @Column(name = "OTHER_NAME", length = 255)
    private String otherName;
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
    private Date birddate;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(length = 30)
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(length = 30)
    private String fax;
    @Size(max = 30)
    @Column(length = 30)
    private String mobile;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(length = 50)
    private String email;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmvisitor")
    private Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmvisitor")
    private Collection<Crmvisitorcontacts> crmvisitorcontactsCollection;
    @JoinColumns({
        @JoinColumn(name = "EVENTTYPEID", referencedColumnName = "EVENTTYPEID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmschedulerevnttype crmschedulerevnttype;
    @JoinColumns({
        @JoinColumn(name = "SCHEDULERID", referencedColumnName = "SCHEDULERID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmscheduler crmscheduler;
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

    public Crmvisitor() {
    }

    public Crmvisitor(CrmvisitorPK crmvisitorPK) {
        this.crmvisitorPK = crmvisitorPK;
    }

    public Crmvisitor(int visitorid, int pubkey) {
        this.crmvisitorPK = new CrmvisitorPK(visitorid, pubkey);
    }

    public CrmvisitorPK getCrmvisitorPK() {
        return crmvisitorPK;
    }

    public void setCrmvisitorPK(CrmvisitorPK crmvisitorPK) {
        this.crmvisitorPK = crmvisitorPK;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Date getBirddate() {
        return birddate;
    }

    public void setBirddate(Date birddate) {
        this.birddate = birddate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public Collection<Crmvisitorcontactsaddress> getCrmvisitorcontactsaddressCollection() {
        return crmvisitorcontactsaddressCollection;
    }

    public void setCrmvisitorcontactsaddressCollection(Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollection) {
        this.crmvisitorcontactsaddressCollection = crmvisitorcontactsaddressCollection;
    }

    @XmlTransient
    public Collection<Crmvisitorcontacts> getCrmvisitorcontactsCollection() {
        return crmvisitorcontactsCollection;
    }

    public void setCrmvisitorcontactsCollection(Collection<Crmvisitorcontacts> crmvisitorcontactsCollection) {
        this.crmvisitorcontactsCollection = crmvisitorcontactsCollection;
    }

    public Crmschedulerevnttype getCrmschedulerevnttype() {
        return crmschedulerevnttype;
    }

    public void setCrmschedulerevnttype(Crmschedulerevnttype crmschedulerevnttype) {
        this.crmschedulerevnttype = crmschedulerevnttype;
    }

    public Crmscheduler getCrmscheduler() {
        return crmscheduler;
    }

    public void setCrmscheduler(Crmscheduler crmscheduler) {
        this.crmscheduler = crmscheduler;
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
        hash += (crmvisitorPK != null ? crmvisitorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmvisitor)) {
            return false;
        }
        Crmvisitor other = (Crmvisitor) object;
        if ((this.crmvisitorPK == null && other.crmvisitorPK != null) || (this.crmvisitorPK != null && !this.crmvisitorPK.equals(other.crmvisitorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmvisitor[ crmvisitorPK=" + crmvisitorPK + " ]";
    }

}
