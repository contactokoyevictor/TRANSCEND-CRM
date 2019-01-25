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
    @NamedQuery(name = "Crmschedulerefcode.findAll", query = "SELECT c FROM Crmschedulerefcode c"),
    @NamedQuery(name = "Crmschedulerefcode.findById", query = "SELECT c FROM Crmschedulerefcode c WHERE c.crmschedulerefcodePK.id = :id"),
    @NamedQuery(name = "Crmschedulerefcode.findByPubkey", query = "SELECT c FROM Crmschedulerefcode c WHERE c.crmschedulerefcodePK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmschedulerefcode.findByCompanyid", query = "SELECT c FROM Crmschedulerefcode c WHERE c.companyid = :companyid"),
    @NamedQuery(name = "Crmschedulerefcode.findByAppointmentid", query = "SELECT c FROM Crmschedulerefcode c WHERE c.appointmentid = :appointmentid"),
    @NamedQuery(name = "Crmschedulerefcode.findByCampaignid", query = "SELECT c FROM Crmschedulerefcode c WHERE c.campaignid = :campaignid"),
    @NamedQuery(name = "Crmschedulerefcode.findByIssueid", query = "SELECT c FROM Crmschedulerefcode c WHERE c.issueid = :issueid"),
    @NamedQuery(name = "Crmschedulerefcode.findByProjectid", query = "SELECT c FROM Crmschedulerefcode c WHERE c.projectid = :projectid"),
    @NamedQuery(name = "Crmschedulerefcode.findByTaskid", query = "SELECT c FROM Crmschedulerefcode c WHERE c.taskid = :taskid"),
    @NamedQuery(name = "Crmschedulerefcode.findByRefCodeid", query = "SELECT c FROM Crmschedulerefcode c WHERE c.refCodeid = :refCodeid"),
    @NamedQuery(name = "Crmschedulerefcode.findByCreateddate", query = "SELECT c FROM Crmschedulerefcode c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmschedulerefcode.findByCreatedfrom", query = "SELECT c FROM Crmschedulerefcode c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmschedulerefcode.findByChangeddate", query = "SELECT c FROM Crmschedulerefcode c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmschedulerefcode.findByChangedfrom", query = "SELECT c FROM Crmschedulerefcode c WHERE c.changedfrom = :changedfrom")})
public class Crmschedulerefcode implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmschedulerefcodePK crmschedulerefcodePK;
    private Integer companyid;
    private Integer appointmentid;
    private Integer campaignid;
    private Integer issueid;
    private Integer projectid;
    private Integer taskid;
    @Column(name = "REF_CODEID")
    private Integer refCodeid;
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

    public Crmschedulerefcode() {
    }

    public Crmschedulerefcode(CrmschedulerefcodePK crmschedulerefcodePK) {
        this.crmschedulerefcodePK = crmschedulerefcodePK;
    }

    public Crmschedulerefcode(int id, int pubkey) {
        this.crmschedulerefcodePK = new CrmschedulerefcodePK(id, pubkey);
    }

    public CrmschedulerefcodePK getCrmschedulerefcodePK() {
        return crmschedulerefcodePK;
    }

    public void setCrmschedulerefcodePK(CrmschedulerefcodePK crmschedulerefcodePK) {
        this.crmschedulerefcodePK = crmschedulerefcodePK;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(Integer appointmentid) {
        this.appointmentid = appointmentid;
    }

    public Integer getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    public Integer getIssueid() {
        return issueid;
    }

    public void setIssueid(Integer issueid) {
        this.issueid = issueid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Integer getRefCodeid() {
        return refCodeid;
    }

    public void setRefCodeid(Integer refCodeid) {
        this.refCodeid = refCodeid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmschedulerefcodePK != null ? crmschedulerefcodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmschedulerefcode)) {
            return false;
        }
        Crmschedulerefcode other = (Crmschedulerefcode) object;
        if ((this.crmschedulerefcodePK == null && other.crmschedulerefcodePK != null) || (this.crmschedulerefcodePK != null && !this.crmschedulerefcodePK.equals(other.crmschedulerefcodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmschedulerefcode[ crmschedulerefcodePK=" + crmschedulerefcodePK + " ]";
    }

}
