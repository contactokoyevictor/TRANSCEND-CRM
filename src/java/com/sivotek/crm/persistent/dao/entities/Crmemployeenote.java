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
    @NamedQuery(name = "Crmemployeenote.findAll", query = "SELECT c FROM Crmemployeenote c"),
    @NamedQuery(name = "Crmemployeenote.findById", query = "SELECT c FROM Crmemployeenote c WHERE c.crmemployeenotePK.id = :id"),
    @NamedQuery(name = "Crmemployeenote.findByPubkey", query = "SELECT c FROM Crmemployeenote c WHERE c.crmemployeenotePK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmemployeenote.findByCreatedby", query = "SELECT c FROM Crmemployeenote c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "Crmemployeenote.findByCreateddate", query = "SELECT c FROM Crmemployeenote c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmemployeenote.findByCreatedfrom", query = "SELECT c FROM Crmemployeenote c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmemployeenote.findByChangeddate", query = "SELECT c FROM Crmemployeenote c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmemployeenote.findByChangedfrom", query = "SELECT c FROM Crmemployeenote c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmemployeenote.findByChangedby", query = "SELECT c FROM Crmemployeenote c WHERE c.changedby = :changedby")})
public class Crmemployeenote implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmemployeenotePK crmemployeenotePK;
    @Lob
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String notespool;
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
        @JoinColumn(name = "EMPLOYEEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;

    public Crmemployeenote() {
    }

    public Crmemployeenote(CrmemployeenotePK crmemployeenotePK) {
        this.crmemployeenotePK = crmemployeenotePK;
    }

    public Crmemployeenote(int id, int pubkey) {
        this.crmemployeenotePK = new CrmemployeenotePK(id, pubkey);
    }

    public CrmemployeenotePK getCrmemployeenotePK() {
        return crmemployeenotePK;
    }

    public void setCrmemployeenotePK(CrmemployeenotePK crmemployeenotePK) {
        this.crmemployeenotePK = crmemployeenotePK;
    }

    public String getNotespool() {
        return notespool;
    }

    public void setNotespool(String notespool) {
        this.notespool = notespool;
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
        hash += (crmemployeenotePK != null ? crmemployeenotePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmemployeenote)) {
            return false;
        }
        Crmemployeenote other = (Crmemployeenote) object;
        if ((this.crmemployeenotePK == null && other.crmemployeenotePK != null) || (this.crmemployeenotePK != null && !this.crmemployeenotePK.equals(other.crmemployeenotePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmemployeenote[ crmemployeenotePK=" + crmemployeenotePK + " ]";
    }

}
