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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Crmlicensetype.findAll", query = "SELECT c FROM Crmlicensetype c"),
    @NamedQuery(name = "Crmlicensetype.findByTypeid", query = "SELECT c FROM Crmlicensetype c WHERE c.typeid = :typeid"),
    @NamedQuery(name = "Crmlicensetype.findByName", query = "SELECT c FROM Crmlicensetype c WHERE c.name = :name"),
    @NamedQuery(name = "Crmlicensetype.findByCode", query = "SELECT c FROM Crmlicensetype c WHERE c.code = :code"),
    @NamedQuery(name = "Crmlicensetype.findByCreateddate", query = "SELECT c FROM Crmlicensetype c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmlicensetype.findByCreatedfrom", query = "SELECT c FROM Crmlicensetype c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmlicensetype.findByChangeddate", query = "SELECT c FROM Crmlicensetype c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmlicensetype.findByChangedfrom", query = "SELECT c FROM Crmlicensetype c WHERE c.changedfrom = :changedfrom")})
public class Crmlicensetype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer typeid;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Size(max = 255)
    @Column(length = 255)
    private String code;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
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
    @OneToMany(mappedBy = "typeid")
    private Collection<Crmlicenseperiodity> crmlicenseperiodityCollection;

    public Crmlicensetype() {
    }

    public Crmlicensetype(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public Collection<Crmlicenseperiodity> getCrmlicenseperiodityCollection() {
        return crmlicenseperiodityCollection;
    }

    public void setCrmlicenseperiodityCollection(Collection<Crmlicenseperiodity> crmlicenseperiodityCollection) {
        this.crmlicenseperiodityCollection = crmlicenseperiodityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeid != null ? typeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmlicensetype)) {
            return false;
        }
        Crmlicensetype other = (Crmlicensetype) object;
        if ((this.typeid == null && other.typeid != null) || (this.typeid != null && !this.typeid.equals(other.typeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmlicensetype[ typeid=" + typeid + " ]";
    }

}
