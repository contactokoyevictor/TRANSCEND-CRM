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
    @NamedQuery(name = "Crmmodules.findAll", query = "SELECT c FROM Crmmodules c"),
    @NamedQuery(name = "Crmmodules.findByModuleid", query = "SELECT c FROM Crmmodules c WHERE c.crmmodulesPK.moduleid = :moduleid"),
    @NamedQuery(name = "Crmmodules.findByPubkey", query = "SELECT c FROM Crmmodules c WHERE c.crmmodulesPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmmodules.findByModuleName", query = "SELECT c FROM Crmmodules c WHERE c.moduleName = :moduleName"),
    @NamedQuery(name = "Crmmodules.findByModuleUrl", query = "SELECT c FROM Crmmodules c WHERE c.moduleUrl = :moduleUrl"),
    @NamedQuery(name = "Crmmodules.findByCreateddate", query = "SELECT c FROM Crmmodules c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmmodules.findByCreatedfrom", query = "SELECT c FROM Crmmodules c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmmodules.findByCreatedby", query = "SELECT c FROM Crmmodules c WHERE c.createdby = :createdby"),
    @NamedQuery(name = "Crmmodules.findByChangeddate", query = "SELECT c FROM Crmmodules c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmmodules.findByChangedfrom", query = "SELECT c FROM Crmmodules c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmmodules.findByChangedby", query = "SELECT c FROM Crmmodules c WHERE c.changedby = :changedby")})
public class Crmmodules implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmmodulesPK crmmodulesPK;
    @Size(max = 255)
    @Column(name = "MODULE_NAME", length = 255)
    private String moduleName;
    @Size(max = 255)
    @Column(name = "MODULE_URL", length = 255)
    private String moduleUrl;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmmodules")
    private Collection<Crmusermodules> crmusermodulesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmmodules")
    private Collection<Crmsubmodules> crmsubmodulesCollection;

    public Crmmodules() {
    }

    public Crmmodules(CrmmodulesPK crmmodulesPK) {
        this.crmmodulesPK = crmmodulesPK;
    }

    public Crmmodules(int moduleid, int pubkey) {
        this.crmmodulesPK = new CrmmodulesPK(moduleid, pubkey);
    }

    public CrmmodulesPK getCrmmodulesPK() {
        return crmmodulesPK;
    }

    public void setCrmmodulesPK(CrmmodulesPK crmmodulesPK) {
        this.crmmodulesPK = crmmodulesPK;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
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

    @XmlTransient
    public Collection<Crmsubmodules> getCrmsubmodulesCollection() {
        return crmsubmodulesCollection;
    }

    public void setCrmsubmodulesCollection(Collection<Crmsubmodules> crmsubmodulesCollection) {
        this.crmsubmodulesCollection = crmsubmodulesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmmodulesPK != null ? crmmodulesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmmodules)) {
            return false;
        }
        Crmmodules other = (Crmmodules) object;
        if ((this.crmmodulesPK == null && other.crmmodulesPK != null) || (this.crmmodulesPK != null && !this.crmmodulesPK.equals(other.crmmodulesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmmodules[ crmmodulesPK=" + crmmodulesPK + " ]";
    }

}
