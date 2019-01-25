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
    @NamedQuery(name = "Crmprojectticketmanagement.findAll", query = "SELECT c FROM Crmprojectticketmanagement c"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByIssueid", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.crmprojectticketmanagementPK.issueid = :issueid"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByPubkey", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.crmprojectticketmanagementPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByIssuesubject", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.issuesubject = :issuesubject"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByIdentifieddate", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.identifieddate = :identifieddate"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByTicketstatus", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.ticketstatus = :ticketstatus"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByEnvironment", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.environment = :environment"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByPriority", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.priority = :priority"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByTargetedResolutionDate", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.targetedResolutionDate = :targetedResolutionDate"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByActualResolutionDate", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.actualResolutionDate = :actualResolutionDate"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByCreateddate", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByCreatedfrom", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByChangeddate", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmprojectticketmanagement.findByChangedfrom", query = "SELECT c FROM Crmprojectticketmanagement c WHERE c.changedfrom = :changedfrom")})
public class Crmprojectticketmanagement implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmprojectticketmanagementPK crmprojectticketmanagementPK;
    @Size(max = 255)
    @Column(length = 255)
    private String issuesubject;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date identifieddate;
    @Size(max = 30)
    @Column(length = 30)
    private String ticketstatus;
    @Size(max = 300)
    @Column(length = 300)
    private String environment;
    private Integer priority;
    @Column(name = "TARGETED_RESOLUTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date targetedResolutionDate;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String progress;
    @Column(name = "ACTUAL_RESOLUTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualResolutionDate;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String lastcomment;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmprojectticketmanagement")
    private Collection<Crmworkorder> crmworkorderCollection;
    @JoinColumns({
        @JoinColumn(name = "TASKID", referencedColumnName = "TASKID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmprojecttask crmprojecttask;
    @JoinColumns({
        @JoinColumn(name = "IDENTIFIED_BY_EMPLOYEEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "RELATEDTASKID", referencedColumnName = "TASKID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmprojecttask crmprojecttask1;
    @JoinColumns({
        @JoinColumn(name = "ASSIGNEDTO", referencedColumnName = "ID"),
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmprojectticketmanagement")
    private Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection;

    public Crmprojectticketmanagement() {
    }

    public Crmprojectticketmanagement(CrmprojectticketmanagementPK crmprojectticketmanagementPK) {
        this.crmprojectticketmanagementPK = crmprojectticketmanagementPK;
    }

    public Crmprojectticketmanagement(int issueid, int pubkey) {
        this.crmprojectticketmanagementPK = new CrmprojectticketmanagementPK(issueid, pubkey);
    }

    public CrmprojectticketmanagementPK getCrmprojectticketmanagementPK() {
        return crmprojectticketmanagementPK;
    }

    public void setCrmprojectticketmanagementPK(CrmprojectticketmanagementPK crmprojectticketmanagementPK) {
        this.crmprojectticketmanagementPK = crmprojectticketmanagementPK;
    }

    public String getIssuesubject() {
        return issuesubject;
    }

    public void setIssuesubject(String issuesubject) {
        this.issuesubject = issuesubject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getIdentifieddate() {
        return identifieddate;
    }

    public void setIdentifieddate(Date identifieddate) {
        this.identifieddate = identifieddate;
    }

    public String getTicketstatus() {
        return ticketstatus;
    }

    public void setTicketstatus(String ticketstatus) {
        this.ticketstatus = ticketstatus;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getTargetedResolutionDate() {
        return targetedResolutionDate;
    }

    public void setTargetedResolutionDate(Date targetedResolutionDate) {
        this.targetedResolutionDate = targetedResolutionDate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Date getActualResolutionDate() {
        return actualResolutionDate;
    }

    public void setActualResolutionDate(Date actualResolutionDate) {
        this.actualResolutionDate = actualResolutionDate;
    }

    public String getLastcomment() {
        return lastcomment;
    }

    public void setLastcomment(String lastcomment) {
        this.lastcomment = lastcomment;
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
    public Collection<Crmworkorder> getCrmworkorderCollection() {
        return crmworkorderCollection;
    }

    public void setCrmworkorderCollection(Collection<Crmworkorder> crmworkorderCollection) {
        this.crmworkorderCollection = crmworkorderCollection;
    }

    public Crmprojecttask getCrmprojecttask() {
        return crmprojecttask;
    }

    public void setCrmprojecttask(Crmprojecttask crmprojecttask) {
        this.crmprojecttask = crmprojecttask;
    }

    public Companyemployee getCompanyemployee() {
        return companyemployee;
    }

    public void setCompanyemployee(Companyemployee companyemployee) {
        this.companyemployee = companyemployee;
    }

    public Crmprojecttask getCrmprojecttask1() {
        return crmprojecttask1;
    }

    public void setCrmprojecttask1(Crmprojecttask crmprojecttask1) {
        this.crmprojecttask1 = crmprojecttask1;
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
    public Collection<Crmprojectticketnotification> getCrmprojectticketnotificationCollection() {
        return crmprojectticketnotificationCollection;
    }

    public void setCrmprojectticketnotificationCollection(Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection) {
        this.crmprojectticketnotificationCollection = crmprojectticketnotificationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmprojectticketmanagementPK != null ? crmprojectticketmanagementPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmprojectticketmanagement)) {
            return false;
        }
        Crmprojectticketmanagement other = (Crmprojectticketmanagement) object;
        if ((this.crmprojectticketmanagementPK == null && other.crmprojectticketmanagementPK != null) || (this.crmprojectticketmanagementPK != null && !this.crmprojectticketmanagementPK.equals(other.crmprojectticketmanagementPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmprojectticketmanagement[ crmprojectticketmanagementPK=" + crmprojectticketmanagementPK + " ]";
    }

}
