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
    @NamedQuery(name = "Crmworkordersettings.findAll", query = "SELECT c FROM Crmworkordersettings c"),
    @NamedQuery(name = "Crmworkordersettings.findById", query = "SELECT c FROM Crmworkordersettings c WHERE c.crmworkordersettingsPK.id = :id"),
    @NamedQuery(name = "Crmworkordersettings.findByPubkey", query = "SELECT c FROM Crmworkordersettings c WHERE c.crmworkordersettingsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmworkordersettings.findByCreatedby", query = "SELECT c FROM Crmworkordersettings c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "Crmworkordersettings.findByCreateddate", query = "SELECT c FROM Crmworkordersettings c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmworkordersettings.findByCreatedfrom", query = "SELECT c FROM Crmworkordersettings c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmworkordersettings.findByChangeddate", query = "SELECT c FROM Crmworkordersettings c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmworkordersettings.findByChangedfrom", query = "SELECT c FROM Crmworkordersettings c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmworkordersettings.findByChangedby", query = "SELECT c FROM Crmworkordersettings c WHERE c.changedby = :changedby")})
public class Crmworkordersettings implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmworkordersettingsPK crmworkordersettingsPK;
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

    public Crmworkordersettings() {
    }

    public Crmworkordersettings(CrmworkordersettingsPK crmworkordersettingsPK) {
        this.crmworkordersettingsPK = crmworkordersettingsPK;
    }

    public Crmworkordersettings(int id, int pubkey) {
        this.crmworkordersettingsPK = new CrmworkordersettingsPK(id, pubkey);
    }

    public CrmworkordersettingsPK getCrmworkordersettingsPK() {
        return crmworkordersettingsPK;
    }

    public void setCrmworkordersettingsPK(CrmworkordersettingsPK crmworkordersettingsPK) {
        this.crmworkordersettingsPK = crmworkordersettingsPK;
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
        hash += (crmworkordersettingsPK != null ? crmworkordersettingsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmworkordersettings)) {
            return false;
        }
        Crmworkordersettings other = (Crmworkordersettings) object;
        if ((this.crmworkordersettingsPK == null && other.crmworkordersettingsPK != null) || (this.crmworkordersettingsPK != null && !this.crmworkordersettingsPK.equals(other.crmworkordersettingsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmworkordersettings[ crmworkordersettingsPK=" + crmworkordersettingsPK + " ]";
    }

}
