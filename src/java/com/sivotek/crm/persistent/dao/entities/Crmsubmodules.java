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
    @NamedQuery(name = "Crmsubmodules.findAll", query = "SELECT c FROM Crmsubmodules c"),
    @NamedQuery(name = "Crmsubmodules.findById", query = "SELECT c FROM Crmsubmodules c WHERE c.crmsubmodulesPK.id = :id"),
    @NamedQuery(name = "Crmsubmodules.findByPubkey", query = "SELECT c FROM Crmsubmodules c WHERE c.crmsubmodulesPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmsubmodules.findBySubmoduleUrl", query = "SELECT c FROM Crmsubmodules c WHERE c.submoduleUrl = :submoduleUrl"),
    @NamedQuery(name = "Crmsubmodules.findByCreateddate", query = "SELECT c FROM Crmsubmodules c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmsubmodules.findByCreatedfrom", query = "SELECT c FROM Crmsubmodules c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmsubmodules.findByCreatedby", query = "SELECT c FROM Crmsubmodules c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "Crmsubmodules.findByChangeddate", query = "SELECT c FROM Crmsubmodules c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmsubmodules.findByChangedfrom", query = "SELECT c FROM Crmsubmodules c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmsubmodules.findByChangedby", query = "SELECT c FROM Crmsubmodules c WHERE c.changedby = :changedby")})
public class Crmsubmodules implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmsubmodulesPK crmsubmodulesPK;
    @Size(max = 200)
    @Column(name = "SUBMODULE_URL", length = 200)
    private String submoduleUrl;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Size(max = 150)
    @Column(length = 150)
    private String createdfrom;
    private Integer createdby;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @Size(max = 150)
    @Column(length = 150)
    private String changedfrom;
    private Integer changedby;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmsubmodules")
    private Collection<Crmusermodules> crmusermodulesCollection;
    @JoinColumns({
        @JoinColumn(name = "MAINMODULEID", referencedColumnName = "MODULEID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmmodules crmmodules;

    public Crmsubmodules() {
    }

    public Crmsubmodules(CrmsubmodulesPK crmsubmodulesPK) {
        this.crmsubmodulesPK = crmsubmodulesPK;
    }

    public Crmsubmodules(int id, int pubkey) {
        this.crmsubmodulesPK = new CrmsubmodulesPK(id, pubkey);
    }

    public CrmsubmodulesPK getCrmsubmodulesPK() {
        return crmsubmodulesPK;
    }

    public void setCrmsubmodulesPK(CrmsubmodulesPK crmsubmodulesPK) {
        this.crmsubmodulesPK = crmsubmodulesPK;
    }

    public String getSubmoduleUrl() {
        return submoduleUrl;
    }

    public void setSubmoduleUrl(String submoduleUrl) {
        this.submoduleUrl = submoduleUrl;
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

    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
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

    @XmlTransient
    public Collection<Crmusermodules> getCrmusermodulesCollection() {
        return crmusermodulesCollection;
    }

    public void setCrmusermodulesCollection(Collection<Crmusermodules> crmusermodulesCollection) {
        this.crmusermodulesCollection = crmusermodulesCollection;
    }

    public Crmmodules getCrmmodules() {
        return crmmodules;
    }

    public void setCrmmodules(Crmmodules crmmodules) {
        this.crmmodules = crmmodules;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmsubmodulesPK != null ? crmsubmodulesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmsubmodules)) {
            return false;
        }
        Crmsubmodules other = (Crmsubmodules) object;
        if ((this.crmsubmodulesPK == null && other.crmsubmodulesPK != null) || (this.crmsubmodulesPK != null && !this.crmsubmodulesPK.equals(other.crmsubmodulesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmsubmodules[ crmsubmodulesPK=" + crmsubmodulesPK + " ]";
    }

}
