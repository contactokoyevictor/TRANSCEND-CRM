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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author okoyevictor
 */
@Entity
@Table(catalog = "sivotekcrm_", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"QUARTZSCHEDULE_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crmscheduler.findAll", query = "SELECT c FROM Crmscheduler c"),
    @NamedQuery(name = "Crmscheduler.findBySchedulerid", query = "SELECT c FROM Crmscheduler c WHERE c.crmschedulerPK.schedulerid = :schedulerid"),
    @NamedQuery(name = "Crmscheduler.findByPubkey", query = "SELECT c FROM Crmscheduler c WHERE c.crmschedulerPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmscheduler.findByQuartzscheduleId", query = "SELECT c FROM Crmscheduler c WHERE c.quartzscheduleId = :quartzscheduleId"),
    @NamedQuery(name = "Crmscheduler.findByScheduleName", query = "SELECT c FROM Crmscheduler c WHERE c.scheduleName = :scheduleName"),
    @NamedQuery(name = "Crmscheduler.findByScheduleGroup", query = "SELECT c FROM Crmscheduler c WHERE c.scheduleGroup = :scheduleGroup"),
    @NamedQuery(name = "Crmscheduler.findByScheduletype", query = "SELECT c FROM Crmscheduler c WHERE c.scheduletype = :scheduletype"),
    @NamedQuery(name = "Crmscheduler.findByActiveWeekdays", query = "SELECT c FROM Crmscheduler c WHERE c.activeWeekdays = :activeWeekdays"),
    @NamedQuery(name = "Crmscheduler.findByFrequencyType", query = "SELECT c FROM Crmscheduler c WHERE c.frequencyType = :frequencyType"),
    @NamedQuery(name = "Crmscheduler.findByScheduleStatus", query = "SELECT c FROM Crmscheduler c WHERE c.scheduleStatus = :scheduleStatus"),
    @NamedQuery(name = "Crmscheduler.findByPushUrl", query = "SELECT c FROM Crmscheduler c WHERE c.pushUrl = :pushUrl"),
    @NamedQuery(name = "Crmscheduler.findByPushAction", query = "SELECT c FROM Crmscheduler c WHERE c.pushAction = :pushAction"),
    @NamedQuery(name = "Crmscheduler.findByColor", query = "SELECT c FROM Crmscheduler c WHERE c.color = :color"),
    @NamedQuery(name = "Crmscheduler.findByStartdate", query = "SELECT c FROM Crmscheduler c WHERE c.startdate = :startdate"),
    @NamedQuery(name = "Crmscheduler.findByEnddate", query = "SELECT c FROM Crmscheduler c WHERE c.enddate = :enddate"),
    @NamedQuery(name = "Crmscheduler.findByTimezoneid", query = "SELECT c FROM Crmscheduler c WHERE c.timezoneid = :timezoneid"),
    @NamedQuery(name = "Crmscheduler.findByLastRun", query = "SELECT c FROM Crmscheduler c WHERE c.lastRun = :lastRun"),
    @NamedQuery(name = "Crmscheduler.findByNextRun", query = "SELECT c FROM Crmscheduler c WHERE c.nextRun = :nextRun"),
    @NamedQuery(name = "Crmscheduler.findByEventType", query = "SELECT c FROM Crmscheduler c WHERE c.eventType = :eventType"),
    @NamedQuery(name = "Crmscheduler.findByCreateddate", query = "SELECT c FROM Crmscheduler c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmscheduler.findByCreatedfrom", query = "SELECT c FROM Crmscheduler c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmscheduler.findByChangeddate", query = "SELECT c FROM Crmscheduler c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmscheduler.findByChangedfrom", query = "SELECT c FROM Crmscheduler c WHERE c.changedfrom = :changedfrom")})
public class Crmscheduler implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmschedulerPK crmschedulerPK;
    @Column(name = "QUARTZSCHEDULE_ID")
    private Integer quartzscheduleId;
    @Size(max = 255)
    @Column(name = "SCHEDULE_NAME", length = 255)
    private String scheduleName;
    @Size(max = 255)
    @Column(name = "SCHEDULE_GROUP", length = 255)
    private String scheduleGroup;
    @Size(max = 6)
    @Column(length = 6)
    private String scheduletype;
    @Size(max = 255)
    @Column(name = "ACTIVE_WEEKDAYS", length = 255)
    private String activeWeekdays;
    @Size(max = 255)
    @Column(name = "FREQUENCY_TYPE", length = 255)
    private String frequencyType;
    @Size(max = 9)
    @Column(name = "SCHEDULE_STATUS", length = 9)
    private String scheduleStatus;
    @Size(max = 255)
    @Column(name = "PUSH_URL", length = 255)
    private String pushUrl;
    @Size(max = 7)
    @Column(name = "PUSH_ACTION", length = 7)
    private String pushAction;
    @Size(max = 15)
    @Column(length = 15)
    private String color;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    private Integer timezoneid;
    @Column(name = "LAST_RUN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRun;
    @Column(name = "NEXT_RUN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextRun;
    @Column(name = "EVENT_TYPE")
    private Integer eventType;
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
        @JoinColumn(name = "EMPLOYEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "APPOINTMENTID", referencedColumnName = "APPOINTMENTID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Appointment appointment;
    @JoinColumns({
        @JoinColumn(name = "CAMPAIGNID", referencedColumnName = "CAMPAIGNID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmcampaign crmcampaign;
    @JoinColumns({
        @JoinColumn(name = "TASKID", referencedColumnName = "TASKID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmprojecttask crmprojecttask;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmscheduler")
    private Collection<Crmvisitor> crmvisitorCollection;

    public Crmscheduler() {
    }

    public Crmscheduler(CrmschedulerPK crmschedulerPK) {
        this.crmschedulerPK = crmschedulerPK;
    }

    public Crmscheduler(int schedulerid, int pubkey) {
        this.crmschedulerPK = new CrmschedulerPK(schedulerid, pubkey);
    }

    public CrmschedulerPK getCrmschedulerPK() {
        return crmschedulerPK;
    }

    public void setCrmschedulerPK(CrmschedulerPK crmschedulerPK) {
        this.crmschedulerPK = crmschedulerPK;
    }

    public Integer getQuartzscheduleId() {
        return quartzscheduleId;
    }

    public void setQuartzscheduleId(Integer quartzscheduleId) {
        this.quartzscheduleId = quartzscheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleGroup() {
        return scheduleGroup;
    }

    public void setScheduleGroup(String scheduleGroup) {
        this.scheduleGroup = scheduleGroup;
    }

    public String getScheduletype() {
        return scheduletype;
    }

    public void setScheduletype(String scheduletype) {
        this.scheduletype = scheduletype;
    }

    public String getActiveWeekdays() {
        return activeWeekdays;
    }

    public void setActiveWeekdays(String activeWeekdays) {
        this.activeWeekdays = activeWeekdays;
    }

    public String getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(String frequencyType) {
        this.frequencyType = frequencyType;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public String getPushAction() {
        return pushAction;
    }

    public void setPushAction(String pushAction) {
        this.pushAction = pushAction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getTimezoneid() {
        return timezoneid;
    }

    public void setTimezoneid(Integer timezoneid) {
        this.timezoneid = timezoneid;
    }

    public Date getLastRun() {
        return lastRun;
    }

    public void setLastRun(Date lastRun) {
        this.lastRun = lastRun;
    }

    public Date getNextRun() {
        return nextRun;
    }

    public void setNextRun(Date nextRun) {
        this.nextRun = nextRun;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Crmcampaign getCrmcampaign() {
        return crmcampaign;
    }

    public void setCrmcampaign(Crmcampaign crmcampaign) {
        this.crmcampaign = crmcampaign;
    }

    public Crmprojecttask getCrmprojecttask() {
        return crmprojecttask;
    }

    public void setCrmprojecttask(Crmprojecttask crmprojecttask) {
        this.crmprojecttask = crmprojecttask;
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

    @XmlTransient
    public Collection<Crmvisitor> getCrmvisitorCollection() {
        return crmvisitorCollection;
    }

    public void setCrmvisitorCollection(Collection<Crmvisitor> crmvisitorCollection) {
        this.crmvisitorCollection = crmvisitorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmschedulerPK != null ? crmschedulerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmscheduler)) {
            return false;
        }
        Crmscheduler other = (Crmscheduler) object;
        if ((this.crmschedulerPK == null && other.crmschedulerPK != null) || (this.crmschedulerPK != null && !this.crmschedulerPK.equals(other.crmschedulerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmscheduler[ crmschedulerPK=" + crmschedulerPK + " ]";
    }

}
