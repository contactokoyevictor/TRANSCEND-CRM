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
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findByAppointmentid", query = "SELECT a FROM Appointment a WHERE a.appointmentPK.appointmentid = :appointmentid"),
    @NamedQuery(name = "Appointment.findByPubkey", query = "SELECT a FROM Appointment a WHERE a.appointmentPK.pubkey = :pubkey"),
    @NamedQuery(name = "Appointment.findByAppointmentType", query = "SELECT a FROM Appointment a WHERE a.appointmentType = :appointmentType"),
    @NamedQuery(name = "Appointment.findByTimezoneid", query = "SELECT a FROM Appointment a WHERE a.timezoneid = :timezoneid"),
    @NamedQuery(name = "Appointment.findByStartdatetime", query = "SELECT a FROM Appointment a WHERE a.startdatetime = :startdatetime"),
    @NamedQuery(name = "Appointment.findByEnddatetime", query = "SELECT a FROM Appointment a WHERE a.enddatetime = :enddatetime"),
    @NamedQuery(name = "Appointment.findByCreateddate", query = "SELECT a FROM Appointment a WHERE a.createddate = :createddate"),
    @NamedQuery(name = "Appointment.findByCreatedfrom", query = "SELECT a FROM Appointment a WHERE a.createdfrom = :createdfrom"),
    @NamedQuery(name = "Appointment.findByChangeddate", query = "SELECT a FROM Appointment a WHERE a.changeddate = :changeddate"),
    @NamedQuery(name = "Appointment.findByChangedfrom", query = "SELECT a FROM Appointment a WHERE a.changedfrom = :changedfrom")})
public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AppointmentPK appointmentPK;
    @Column(name = "APPOINTMENT_TYPE")
    private Integer appointmentType;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    private Integer timezoneid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdatetime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddatetime;
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
        @JoinColumn(name = "EVENTTYPEID", referencedColumnName = "EVENTTYPEID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmschedulerevnttype crmschedulerevnttype;
    @JoinColumns({
        @JoinColumn(name = "EMPLOYEEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "RECIPIENT", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee2;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointment")
    private Collection<Crmscheduler> crmschedulerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointment")
    private Collection<Approval> approvalCollection;

    public Appointment() {
    }

    public Appointment(AppointmentPK appointmentPK) {
        this.appointmentPK = appointmentPK;
    }

    public Appointment(int appointmentid, int pubkey) {
        this.appointmentPK = new AppointmentPK(appointmentid, pubkey);
    }

    public AppointmentPK getAppointmentPK() {
        return appointmentPK;
    }

    public void setAppointmentPK(AppointmentPK appointmentPK) {
        this.appointmentPK = appointmentPK;
    }

    public Integer getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(Integer appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTimezoneid() {
        return timezoneid;
    }

    public void setTimezoneid(Integer timezoneid) {
        this.timezoneid = timezoneid;
    }

    public Date getStartdatetime() {
        return startdatetime;
    }

    public void setStartdatetime(Date startdatetime) {
        this.startdatetime = startdatetime;
    }

    public Date getEnddatetime() {
        return enddatetime;
    }

    public void setEnddatetime(Date enddatetime) {
        this.enddatetime = enddatetime;
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

    public Crmschedulerevnttype getCrmschedulerevnttype() {
        return crmschedulerevnttype;
    }

    public void setCrmschedulerevnttype(Crmschedulerevnttype crmschedulerevnttype) {
        this.crmschedulerevnttype = crmschedulerevnttype;
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

    public Companyemployee getCompanyemployee2() {
        return companyemployee2;
    }

    public void setCompanyemployee2(Companyemployee companyemployee2) {
        this.companyemployee2 = companyemployee2;
    }

    public Companyemployee getCompanyemployee3() {
        return companyemployee3;
    }

    public void setCompanyemployee3(Companyemployee companyemployee3) {
        this.companyemployee3 = companyemployee3;
    }

    @XmlTransient
    public Collection<Crmscheduler> getCrmschedulerCollection() {
        return crmschedulerCollection;
    }

    public void setCrmschedulerCollection(Collection<Crmscheduler> crmschedulerCollection) {
        this.crmschedulerCollection = crmschedulerCollection;
    }

    @XmlTransient
    public Collection<Approval> getApprovalCollection() {
        return approvalCollection;
    }

    public void setApprovalCollection(Collection<Approval> approvalCollection) {
        this.approvalCollection = approvalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentPK != null ? appointmentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.appointmentPK == null && other.appointmentPK != null) || (this.appointmentPK != null && !this.appointmentPK.equals(other.appointmentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Appointment[ appointmentPK=" + appointmentPK + " ]";
    }

}
