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
    @NamedQuery(name = "Customercontacts.findAll", query = "SELECT c FROM Customercontacts c"),
    @NamedQuery(name = "Customercontacts.findByContactid", query = "SELECT c FROM Customercontacts c WHERE c.customercontactsPK.contactid = :contactid"),
    @NamedQuery(name = "Customercontacts.findByPubkey", query = "SELECT c FROM Customercontacts c WHERE c.customercontactsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Customercontacts.findByFisrtName", query = "SELECT c FROM Customercontacts c WHERE c.fisrtName = :fisrtName"),
    @NamedQuery(name = "Customercontacts.findByLastName", query = "SELECT c FROM Customercontacts c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Customercontacts.findByOtherName", query = "SELECT c FROM Customercontacts c WHERE c.otherName = :otherName"),
    @NamedQuery(name = "Customercontacts.findByPhone", query = "SELECT c FROM Customercontacts c WHERE c.phone = :phone"),
    @NamedQuery(name = "Customercontacts.findByFax", query = "SELECT c FROM Customercontacts c WHERE c.fax = :fax"),
    @NamedQuery(name = "Customercontacts.findByMobile", query = "SELECT c FROM Customercontacts c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "Customercontacts.findByEmail", query = "SELECT c FROM Customercontacts c WHERE c.email = :email"),
    @NamedQuery(name = "Customercontacts.findByCreateddate", query = "SELECT c FROM Customercontacts c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Customercontacts.findByCreatedfrom", query = "SELECT c FROM Customercontacts c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Customercontacts.findByChangeddate", query = "SELECT c FROM Customercontacts c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Customercontacts.findByChangedfrom", query = "SELECT c FROM Customercontacts c WHERE c.changedfrom = :changedfrom")})
public class Customercontacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomercontactsPK customercontactsPK;
    @Size(max = 255)
    @Column(name = "FISRT_NAME", length = 255)
    private String fisrtName;
    @Size(max = 255)
    @Column(name = "LAST_NAME", length = 255)
    private String lastName;
    @Size(max = 255)
    @Column(name = "OTHER_NAME", length = 255)
    private String otherName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customercontacts")
    private Collection<Crmcampaignreceiver> crmcampaignreceiverCollection;
    @JoinColumns({
        @JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companycustomer companycustomer;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customercontacts")
    private Collection<Customercontactsaddress> customercontactsaddressCollection;

    public Customercontacts() {
    }

    public Customercontacts(CustomercontactsPK customercontactsPK) {
        this.customercontactsPK = customercontactsPK;
    }

    public Customercontacts(int contactid, int pubkey) {
        this.customercontactsPK = new CustomercontactsPK(contactid, pubkey);
    }

    public CustomercontactsPK getCustomercontactsPK() {
        return customercontactsPK;
    }

    public void setCustomercontactsPK(CustomercontactsPK customercontactsPK) {
        this.customercontactsPK = customercontactsPK;
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

    public Companycustomer getCompanycustomer() {
        return companycustomer;
    }

    public void setCompanycustomer(Companycustomer companycustomer) {
        this.companycustomer = companycustomer;
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

    @XmlTransient
    public Collection<Customercontactsaddress> getCustomercontactsaddressCollection() {
        return customercontactsaddressCollection;
    }

    public void setCustomercontactsaddressCollection(Collection<Customercontactsaddress> customercontactsaddressCollection) {
        this.customercontactsaddressCollection = customercontactsaddressCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customercontactsPK != null ? customercontactsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customercontacts)) {
            return false;
        }
        Customercontacts other = (Customercontacts) object;
        if ((this.customercontactsPK == null && other.customercontactsPK != null) || (this.customercontactsPK != null && !this.customercontactsPK.equals(other.customercontactsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Customercontacts[ customercontactsPK=" + customercontactsPK + " ]";
    }

}
