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
    @NamedQuery(name = "Crmemployeecontacts.findAll", query = "SELECT c FROM Crmemployeecontacts c"),
    @NamedQuery(name = "Crmemployeecontacts.findByContactid", query = "SELECT c FROM Crmemployeecontacts c WHERE c.crmemployeecontactsPK.contactid = :contactid"),
    @NamedQuery(name = "Crmemployeecontacts.findByPubkey", query = "SELECT c FROM Crmemployeecontacts c WHERE c.crmemployeecontactsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmemployeecontacts.findByFisrtName", query = "SELECT c FROM Crmemployeecontacts c WHERE c.fisrtName = :fisrtName"),
    @NamedQuery(name = "Crmemployeecontacts.findByLastName", query = "SELECT c FROM Crmemployeecontacts c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Crmemployeecontacts.findByOtherName", query = "SELECT c FROM Crmemployeecontacts c WHERE c.otherName = :otherName"),
    @NamedQuery(name = "Crmemployeecontacts.findByMobile", query = "SELECT c FROM Crmemployeecontacts c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "Crmemployeecontacts.findByPhone", query = "SELECT c FROM Crmemployeecontacts c WHERE c.phone = :phone"),
    @NamedQuery(name = "Crmemployeecontacts.findByFax", query = "SELECT c FROM Crmemployeecontacts c WHERE c.fax = :fax"),
    @NamedQuery(name = "Crmemployeecontacts.findByEmail", query = "SELECT c FROM Crmemployeecontacts c WHERE c.email = :email"),
    @NamedQuery(name = "Crmemployeecontacts.findByWebsite", query = "SELECT c FROM Crmemployeecontacts c WHERE c.website = :website"),
    @NamedQuery(name = "Crmemployeecontacts.findByIsDefault", query = "SELECT c FROM Crmemployeecontacts c WHERE c.isDefault = :isDefault"),
    @NamedQuery(name = "Crmemployeecontacts.findByCreatedby", query = "SELECT c FROM Crmemployeecontacts c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "Crmemployeecontacts.findByCreateddate", query = "SELECT c FROM Crmemployeecontacts c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmemployeecontacts.findByCreatedfrom", query = "SELECT c FROM Crmemployeecontacts c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmemployeecontacts.findByChangeddate", query = "SELECT c FROM Crmemployeecontacts c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmemployeecontacts.findByChangedfrom", query = "SELECT c FROM Crmemployeecontacts c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmemployeecontacts.findByChangedby", query = "SELECT c FROM Crmemployeecontacts c WHERE c.changedby = :changedby")})
public class Crmemployeecontacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmemployeecontactsPK crmemployeecontactsPK;
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
    private String mobile;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(length = 30)
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(length = 30)
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(length = 255)
    private String email;
    @Size(max = 255)
    @Column(length = 255)
    private String website;
    private Boolean isDefault;
    private Integer createdby;
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
        @JoinColumn(name = "employeeid", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;

    public Crmemployeecontacts() {
    }

    public Crmemployeecontacts(CrmemployeecontactsPK crmemployeecontactsPK) {
        this.crmemployeecontactsPK = crmemployeecontactsPK;
    }

    public Crmemployeecontacts(int contactid, int pubkey) {
        this.crmemployeecontactsPK = new CrmemployeecontactsPK(contactid, pubkey);
    }

    public CrmemployeecontactsPK getCrmemployeecontactsPK() {
        return crmemployeecontactsPK;
    }

    public void setCrmemployeecontactsPK(CrmemployeecontactsPK crmemployeecontactsPK) {
        this.crmemployeecontactsPK = crmemployeecontactsPK;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
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

    public Companyemployee getCompanyemployee() {
        return companyemployee;
    }

    public void setCompanyemployee(Companyemployee companyemployee) {
        this.companyemployee = companyemployee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmemployeecontactsPK != null ? crmemployeecontactsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmemployeecontacts)) {
            return false;
        }
        Crmemployeecontacts other = (Crmemployeecontacts) object;
        if ((this.crmemployeecontactsPK == null && other.crmemployeecontactsPK != null) || (this.crmemployeecontactsPK != null && !this.crmemployeecontactsPK.equals(other.crmemployeecontactsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmemployeecontacts[ crmemployeecontactsPK=" + crmemployeecontactsPK + " ]";
    }

}
