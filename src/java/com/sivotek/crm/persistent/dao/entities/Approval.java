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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Approval.findAll", query = "SELECT a FROM Approval a"),
    @NamedQuery(name = "Approval.findByApprovalid", query = "SELECT a FROM Approval a WHERE a.approvalPK.approvalid = :approvalid"),
    @NamedQuery(name = "Approval.findByPubkey", query = "SELECT a FROM Approval a WHERE a.approvalPK.pubkey = :pubkey"),
    @NamedQuery(name = "Approval.findByAppointmentStatus", query = "SELECT a FROM Approval a WHERE a.appointmentStatus = :appointmentStatus"),
    @NamedQuery(name = "Approval.findByLastUpdate", query = "SELECT a FROM Approval a WHERE a.lastUpdate = :lastUpdate"),
    @NamedQuery(name = "Approval.findByCreateddate", query = "SELECT a FROM Approval a WHERE a.createddate = :createddate"),
    @NamedQuery(name = "Approval.findByCreatedfrom", query = "SELECT a FROM Approval a WHERE a.createdfrom = :createdfrom"),
    @NamedQuery(name = "Approval.findByChangeddate", query = "SELECT a FROM Approval a WHERE a.changeddate = :changeddate"),
    @NamedQuery(name = "Approval.findByChangedfrom", query = "SELECT a FROM Approval a WHERE a.changedfrom = :changedfrom")})
public class Approval implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ApprovalPK approvalPK;
    @Size(max = 50)
    @Column(name = "APPOINTMENT_STATUS", length = 50)
    private String appointmentStatus;
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String comments;
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
        @JoinColumn(name = "APPOINTMENTID", referencedColumnName = "APPOINTMENTID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Appointment appointment;
    @JoinColumns({
        @JoinColumn(name = "EMPLOYEEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
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

    public Approval() {
    }

    public Approval(ApprovalPK approvalPK) {
        this.approvalPK = approvalPK;
    }

    public Approval(int approvalid, int pubkey) {
        this.approvalPK = new ApprovalPK(approvalid, pubkey);
    }

    public ApprovalPK getApprovalPK() {
        return approvalPK;
    }

    public void setApprovalPK(ApprovalPK approvalPK) {
        this.approvalPK = approvalPK;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (approvalPK != null ? approvalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Approval)) {
            return false;
        }
        Approval other = (Approval) object;
        if ((this.approvalPK == null && other.approvalPK != null) || (this.approvalPK != null && !this.approvalPK.equals(other.approvalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Approval[ approvalPK=" + approvalPK + " ]";
    }

}
