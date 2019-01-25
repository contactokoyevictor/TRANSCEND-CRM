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
    @NamedQuery(name = "Companyaccountstackcd.findAll", query = "SELECT c FROM Companyaccountstackcd c"),
    @NamedQuery(name = "Companyaccountstackcd.findByAccountstackcdid", query = "SELECT c FROM Companyaccountstackcd c WHERE c.companyaccountstackcdPK.accountstackcdid = :accountstackcdid"),
    @NamedQuery(name = "Companyaccountstackcd.findByPubkey", query = "SELECT c FROM Companyaccountstackcd c WHERE c.companyaccountstackcdPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companyaccountstackcd.findByCreditdtnumber", query = "SELECT c FROM Companyaccountstackcd c WHERE c.creditdtnumber = :creditdtnumber"),
    @NamedQuery(name = "Companyaccountstackcd.findByCreditdtname", query = "SELECT c FROM Companyaccountstackcd c WHERE c.creditdtname = :creditdtname"),
    @NamedQuery(name = "Companyaccountstackcd.findByCreditdtvalue", query = "SELECT c FROM Companyaccountstackcd c WHERE c.creditdtvalue = :creditdtvalue"),
    @NamedQuery(name = "Companyaccountstackcd.findByCreditdttaxtname", query = "SELECT c FROM Companyaccountstackcd c WHERE c.creditdttaxtname = :creditdttaxtname"),
    @NamedQuery(name = "Companyaccountstackcd.findByCreditdttype", query = "SELECT c FROM Companyaccountstackcd c WHERE c.creditdttype = :creditdttype"),
    @NamedQuery(name = "Companyaccountstackcd.findByCreateddate", query = "SELECT c FROM Companyaccountstackcd c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companyaccountstackcd.findByCreatedfrom", query = "SELECT c FROM Companyaccountstackcd c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companyaccountstackcd.findByChangeddate", query = "SELECT c FROM Companyaccountstackcd c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companyaccountstackcd.findByChangedfrom", query = "SELECT c FROM Companyaccountstackcd c WHERE c.changedfrom = :changedfrom")})
public class Companyaccountstackcd implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyaccountstackcdPK companyaccountstackcdPK;
    @Size(max = 150)
    @Column(length = 150)
    private String creditdtnumber;
    @Size(max = 255)
    @Column(length = 255)
    private String creditdtname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double creditdtvalue;
    @Size(max = 150)
    @Column(length = 150)
    private String creditdttaxtname;
    private Integer creditdttype;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyaccountstackcd")
    private Collection<Companyaccountstackdocs> companyaccountstackdocsCollection;
    @JoinColumns({
        @JoinColumn(name = "ACCOUNTSTACKID", referencedColumnName = "ACSTACKID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyaccountstack companyaccountstack;
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

    public Companyaccountstackcd() {
    }

    public Companyaccountstackcd(CompanyaccountstackcdPK companyaccountstackcdPK) {
        this.companyaccountstackcdPK = companyaccountstackcdPK;
    }

    public Companyaccountstackcd(int accountstackcdid, int pubkey) {
        this.companyaccountstackcdPK = new CompanyaccountstackcdPK(accountstackcdid, pubkey);
    }

    public CompanyaccountstackcdPK getCompanyaccountstackcdPK() {
        return companyaccountstackcdPK;
    }

    public void setCompanyaccountstackcdPK(CompanyaccountstackcdPK companyaccountstackcdPK) {
        this.companyaccountstackcdPK = companyaccountstackcdPK;
    }

    public String getCreditdtnumber() {
        return creditdtnumber;
    }

    public void setCreditdtnumber(String creditdtnumber) {
        this.creditdtnumber = creditdtnumber;
    }

    public String getCreditdtname() {
        return creditdtname;
    }

    public void setCreditdtname(String creditdtname) {
        this.creditdtname = creditdtname;
    }

    public Double getCreditdtvalue() {
        return creditdtvalue;
    }

    public void setCreditdtvalue(Double creditdtvalue) {
        this.creditdtvalue = creditdtvalue;
    }

    public String getCreditdttaxtname() {
        return creditdttaxtname;
    }

    public void setCreditdttaxtname(String creditdttaxtname) {
        this.creditdttaxtname = creditdttaxtname;
    }

    public Integer getCreditdttype() {
        return creditdttype;
    }

    public void setCreditdttype(Integer creditdttype) {
        this.creditdttype = creditdttype;
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
    public Collection<Companyaccountstackdocs> getCompanyaccountstackdocsCollection() {
        return companyaccountstackdocsCollection;
    }

    public void setCompanyaccountstackdocsCollection(Collection<Companyaccountstackdocs> companyaccountstackdocsCollection) {
        this.companyaccountstackdocsCollection = companyaccountstackdocsCollection;
    }

    public Companyaccountstack getCompanyaccountstack() {
        return companyaccountstack;
    }

    public void setCompanyaccountstack(Companyaccountstack companyaccountstack) {
        this.companyaccountstack = companyaccountstack;
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
        hash += (companyaccountstackcdPK != null ? companyaccountstackcdPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyaccountstackcd)) {
            return false;
        }
        Companyaccountstackcd other = (Companyaccountstackcd) object;
        if ((this.companyaccountstackcdPK == null && other.companyaccountstackcdPK != null) || (this.companyaccountstackcdPK != null && !this.companyaccountstackcdPK.equals(other.companyaccountstackcdPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companyaccountstackcd[ companyaccountstackcdPK=" + companyaccountstackcdPK + " ]";
    }

}
