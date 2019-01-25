/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @NamedQuery(name = "Crmlicenseperiodity.findAll", query = "SELECT c FROM Crmlicenseperiodity c"),
    @NamedQuery(name = "Crmlicenseperiodity.findByPeriodityid", query = "SELECT c FROM Crmlicenseperiodity c WHERE c.periodityid = :periodityid"),
    @NamedQuery(name = "Crmlicenseperiodity.findByValidfrom", query = "SELECT c FROM Crmlicenseperiodity c WHERE c.validfrom = :validfrom"),
    @NamedQuery(name = "Crmlicenseperiodity.findByValidto", query = "SELECT c FROM Crmlicenseperiodity c WHERE c.validto = :validto"),
    @NamedQuery(name = "Crmlicenseperiodity.findByCreateddate", query = "SELECT c FROM Crmlicenseperiodity c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmlicenseperiodity.findByCreatedfrom", query = "SELECT c FROM Crmlicenseperiodity c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmlicenseperiodity.findByChangeddate", query = "SELECT c FROM Crmlicenseperiodity c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmlicenseperiodity.findByChangedfrom", query = "SELECT c FROM Crmlicenseperiodity c WHERE c.changedfrom = :changedfrom")})
public class Crmlicenseperiodity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer periodityid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date validfrom;
    @Temporal(TemporalType.TIMESTAMP)
    private Date validto;
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
    @OneToMany(mappedBy = "periodityid")
    private Collection<Crmlicense> crmlicenseCollection;
    @JoinColumn(name = "typeid", referencedColumnName = "typeid")
    @ManyToOne
    private Crmlicensetype typeid;

    public Crmlicenseperiodity() {
    }

    public Crmlicenseperiodity(Integer periodityid) {
        this.periodityid = periodityid;
    }

    public Integer getPeriodityid() {
        return periodityid;
    }

    public void setPeriodityid(Integer periodityid) {
        this.periodityid = periodityid;
    }

    public Date getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(Date validfrom) {
        this.validfrom = validfrom;
    }

    public Date getValidto() {
        return validto;
    }

    public void setValidto(Date validto) {
        this.validto = validto;
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
    public Collection<Crmlicense> getCrmlicenseCollection() {
        return crmlicenseCollection;
    }

    public void setCrmlicenseCollection(Collection<Crmlicense> crmlicenseCollection) {
        this.crmlicenseCollection = crmlicenseCollection;
    }

    public Crmlicensetype getTypeid() {
        return typeid;
    }

    public void setTypeid(Crmlicensetype typeid) {
        this.typeid = typeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodityid != null ? periodityid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmlicenseperiodity)) {
            return false;
        }
        Crmlicenseperiodity other = (Crmlicenseperiodity) object;
        if ((this.periodityid == null && other.periodityid != null) || (this.periodityid != null && !this.periodityid.equals(other.periodityid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmlicenseperiodity[ periodityid=" + periodityid + " ]";
    }

}
