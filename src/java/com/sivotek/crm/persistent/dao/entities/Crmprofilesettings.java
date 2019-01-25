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
    @NamedQuery(name = "Crmprofilesettings.findAll", query = "SELECT c FROM Crmprofilesettings c"),
    @NamedQuery(name = "Crmprofilesettings.findById", query = "SELECT c FROM Crmprofilesettings c WHERE c.crmprofilesettingsPK.id = :id"),
    @NamedQuery(name = "Crmprofilesettings.findByPubkey", query = "SELECT c FROM Crmprofilesettings c WHERE c.crmprofilesettingsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmprofilesettings.findByCreatedby", query = "SELECT c FROM Crmprofilesettings c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "Crmprofilesettings.findByCreateddate", query = "SELECT c FROM Crmprofilesettings c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmprofilesettings.findByCreatedfrom", query = "SELECT c FROM Crmprofilesettings c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmprofilesettings.findByChangeddate", query = "SELECT c FROM Crmprofilesettings c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmprofilesettings.findByChangedfrom", query = "SELECT c FROM Crmprofilesettings c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmprofilesettings.findByChangedby", query = "SELECT c FROM Crmprofilesettings c WHERE c.changedby = :changedby")})
public class Crmprofilesettings implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmprofilesettingsPK crmprofilesettingsPK;
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

    public Crmprofilesettings() {
    }

    public Crmprofilesettings(CrmprofilesettingsPK crmprofilesettingsPK) {
        this.crmprofilesettingsPK = crmprofilesettingsPK;
    }

    public Crmprofilesettings(int id, int pubkey) {
        this.crmprofilesettingsPK = new CrmprofilesettingsPK(id, pubkey);
    }

    public CrmprofilesettingsPK getCrmprofilesettingsPK() {
        return crmprofilesettingsPK;
    }

    public void setCrmprofilesettingsPK(CrmprofilesettingsPK crmprofilesettingsPK) {
        this.crmprofilesettingsPK = crmprofilesettingsPK;
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
        hash += (crmprofilesettingsPK != null ? crmprofilesettingsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmprofilesettings)) {
            return false;
        }
        Crmprofilesettings other = (Crmprofilesettings) object;
        if ((this.crmprofilesettingsPK == null && other.crmprofilesettingsPK != null) || (this.crmprofilesettingsPK != null && !this.crmprofilesettingsPK.equals(other.crmprofilesettingsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmprofilesettings[ crmprofilesettingsPK=" + crmprofilesettingsPK + " ]";
    }

}
