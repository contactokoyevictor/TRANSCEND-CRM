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
    @NamedQuery(name = "Crmschedulerevnttype.findAll", query = "SELECT c FROM Crmschedulerevnttype c"),
    @NamedQuery(name = "Crmschedulerevnttype.findByEventtypeid", query = "SELECT c FROM Crmschedulerevnttype c WHERE c.crmschedulerevnttypePK.eventtypeid = :eventtypeid"),
    @NamedQuery(name = "Crmschedulerevnttype.findByPubkey", query = "SELECT c FROM Crmschedulerevnttype c WHERE c.crmschedulerevnttypePK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmschedulerevnttype.findByName", query = "SELECT c FROM Crmschedulerevnttype c WHERE c.name = :name"),
    @NamedQuery(name = "Crmschedulerevnttype.findByCreateddate", query = "SELECT c FROM Crmschedulerevnttype c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmschedulerevnttype.findByCreatedfrom", query = "SELECT c FROM Crmschedulerevnttype c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmschedulerevnttype.findByChangeddate", query = "SELECT c FROM Crmschedulerevnttype c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmschedulerevnttype.findByChangedfrom", query = "SELECT c FROM Crmschedulerevnttype c WHERE c.changedfrom = :changedfrom")})
public class Crmschedulerevnttype implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmschedulerevnttypePK crmschedulerevnttypePK;
    @Size(max = 11)
    @Column(length = 11)
    private String name;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmschedulerevnttype")
    private Collection<Appointment> appointmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmschedulerevnttype")
    private Collection<Crmvisitor> crmvisitorCollection;
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

    public Crmschedulerevnttype() {
    }

    public Crmschedulerevnttype(CrmschedulerevnttypePK crmschedulerevnttypePK) {
        this.crmschedulerevnttypePK = crmschedulerevnttypePK;
    }

    public Crmschedulerevnttype(int eventtypeid, int pubkey) {
        this.crmschedulerevnttypePK = new CrmschedulerevnttypePK(eventtypeid, pubkey);
    }

    public CrmschedulerevnttypePK getCrmschedulerevnttypePK() {
        return crmschedulerevnttypePK;
    }

    public void setCrmschedulerevnttypePK(CrmschedulerevnttypePK crmschedulerevnttypePK) {
        this.crmschedulerevnttypePK = crmschedulerevnttypePK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Collection<Appointment> getAppointmentCollection() {
        return appointmentCollection;
    }

    public void setAppointmentCollection(Collection<Appointment> appointmentCollection) {
        this.appointmentCollection = appointmentCollection;
    }

    @XmlTransient
    public Collection<Crmvisitor> getCrmvisitorCollection() {
        return crmvisitorCollection;
    }

    public void setCrmvisitorCollection(Collection<Crmvisitor> crmvisitorCollection) {
        this.crmvisitorCollection = crmvisitorCollection;
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
        hash += (crmschedulerevnttypePK != null ? crmschedulerevnttypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmschedulerevnttype)) {
            return false;
        }
        Crmschedulerevnttype other = (Crmschedulerevnttype) object;
        if ((this.crmschedulerevnttypePK == null && other.crmschedulerevnttypePK != null) || (this.crmschedulerevnttypePK != null && !this.crmschedulerevnttypePK.equals(other.crmschedulerevnttypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmschedulerevnttype[ crmschedulerevnttypePK=" + crmschedulerevnttypePK + " ]";
    }

}
