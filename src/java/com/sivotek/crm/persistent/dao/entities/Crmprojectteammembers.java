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
    @NamedQuery(name = "Crmprojectteammembers.findAll", query = "SELECT c FROM Crmprojectteammembers c"),
    @NamedQuery(name = "Crmprojectteammembers.findById", query = "SELECT c FROM Crmprojectteammembers c WHERE c.crmprojectteammembersPK.id = :id"),
    @NamedQuery(name = "Crmprojectteammembers.findByPubkey", query = "SELECT c FROM Crmprojectteammembers c WHERE c.crmprojectteammembersPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmprojectteammembers.findByCreateddate", query = "SELECT c FROM Crmprojectteammembers c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmprojectteammembers.findByCreatedfrom", query = "SELECT c FROM Crmprojectteammembers c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmprojectteammembers.findByChangeddate", query = "SELECT c FROM Crmprojectteammembers c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmprojectteammembers.findByChangedfrom", query = "SELECT c FROM Crmprojectteammembers c WHERE c.changedfrom = :changedfrom")})
public class Crmprojectteammembers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmprojectteammembersPK crmprojectteammembersPK;
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
        @JoinColumn(name = "PROJECTID", referencedColumnName = "PROJECTID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmproject crmproject;
    @JoinColumns({
        @JoinColumn(name = "TASKID", referencedColumnName = "TASKID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmprojecttask crmprojecttask;
    @JoinColumns({
        @JoinColumn(name = "EMPLOYEEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee2;

    public Crmprojectteammembers() {
    }

    public Crmprojectteammembers(CrmprojectteammembersPK crmprojectteammembersPK) {
        this.crmprojectteammembersPK = crmprojectteammembersPK;
    }

    public Crmprojectteammembers(int id, int pubkey) {
        this.crmprojectteammembersPK = new CrmprojectteammembersPK(id, pubkey);
    }

    public CrmprojectteammembersPK getCrmprojectteammembersPK() {
        return crmprojectteammembersPK;
    }

    public void setCrmprojectteammembersPK(CrmprojectteammembersPK crmprojectteammembersPK) {
        this.crmprojectteammembersPK = crmprojectteammembersPK;
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

    public Crmproject getCrmproject() {
        return crmproject;
    }

    public void setCrmproject(Crmproject crmproject) {
        this.crmproject = crmproject;
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
        hash += (crmprojectteammembersPK != null ? crmprojectteammembersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmprojectteammembers)) {
            return false;
        }
        Crmprojectteammembers other = (Crmprojectteammembers) object;
        if ((this.crmprojectteammembersPK == null && other.crmprojectteammembersPK != null) || (this.crmprojectteammembersPK != null && !this.crmprojectteammembersPK.equals(other.crmprojectteammembersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmprojectteammembers[ crmprojectteammembersPK=" + crmprojectteammembersPK + " ]";
    }

}
