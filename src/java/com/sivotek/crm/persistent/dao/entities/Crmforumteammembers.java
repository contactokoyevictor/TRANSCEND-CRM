/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author okoyevictor
 */
@Entity
@Table(catalog = "sivotekcrm_", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"employeeid"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crmforumteammembers.findAll", query = "SELECT c FROM Crmforumteammembers c"),
    @NamedQuery(name = "Crmforumteammembers.findByMemeberid", query = "SELECT c FROM Crmforumteammembers c WHERE c.crmforumteammembersPK.memeberid = :memeberid"),
    @NamedQuery(name = "Crmforumteammembers.findByPubkey", query = "SELECT c FROM Crmforumteammembers c WHERE c.crmforumteammembersPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmforumteammembers.findByDisplayname", query = "SELECT c FROM Crmforumteammembers c WHERE c.displayname = :displayname"),
    @NamedQuery(name = "Crmforumteammembers.findByOptinOptout", query = "SELECT c FROM Crmforumteammembers c WHERE c.optinOptout = :optinOptout"),
    @NamedQuery(name = "Crmforumteammembers.findByCreateddate", query = "SELECT c FROM Crmforumteammembers c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmforumteammembers.findByCreatedfrom", query = "SELECT c FROM Crmforumteammembers c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmforumteammembers.findByChangeddate", query = "SELECT c FROM Crmforumteammembers c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmforumteammembers.findByChangedfrom", query = "SELECT c FROM Crmforumteammembers c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmforumteammembers.findByChangedby", query = "SELECT c FROM Crmforumteammembers c WHERE c.changedby = :changedby")})
public class Crmforumteammembers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmforumteammembersPK crmforumteammembersPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String displayname;
    @Column(name = "optin_optout")
    private Short optinOptout;
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
        @JoinColumn(name = "forumid", referencedColumnName = "forumid"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmforum crmforum;
    @JoinColumns({
        @JoinColumn(name = "employeeid", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;

    public Crmforumteammembers() {
    }

    public Crmforumteammembers(CrmforumteammembersPK crmforumteammembersPK) {
        this.crmforumteammembersPK = crmforumteammembersPK;
    }

    public Crmforumteammembers(CrmforumteammembersPK crmforumteammembersPK, String displayname) {
        this.crmforumteammembersPK = crmforumteammembersPK;
        this.displayname = displayname;
    }

    public Crmforumteammembers(int memeberid, int pubkey) {
        this.crmforumteammembersPK = new CrmforumteammembersPK(memeberid, pubkey);
    }

    public CrmforumteammembersPK getCrmforumteammembersPK() {
        return crmforumteammembersPK;
    }

    public void setCrmforumteammembersPK(CrmforumteammembersPK crmforumteammembersPK) {
        this.crmforumteammembersPK = crmforumteammembersPK;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public Short getOptinOptout() {
        return optinOptout;
    }

    public void setOptinOptout(Short optinOptout) {
        this.optinOptout = optinOptout;
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

    public Crmforum getCrmforum() {
        return crmforum;
    }

    public void setCrmforum(Crmforum crmforum) {
        this.crmforum = crmforum;
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
        hash += (crmforumteammembersPK != null ? crmforumteammembersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmforumteammembers)) {
            return false;
        }
        Crmforumteammembers other = (Crmforumteammembers) object;
        if ((this.crmforumteammembersPK == null && other.crmforumteammembersPK != null) || (this.crmforumteammembersPK != null && !this.crmforumteammembersPK.equals(other.crmforumteammembersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmforumteammembers[ crmforumteammembersPK=" + crmforumteammembersPK + " ]";
    }

}
