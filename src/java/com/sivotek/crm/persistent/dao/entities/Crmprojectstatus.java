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
    @NamedQuery(name = "Crmprojectstatus.findAll", query = "SELECT c FROM Crmprojectstatus c"),
    @NamedQuery(name = "Crmprojectstatus.findById", query = "SELECT c FROM Crmprojectstatus c WHERE c.crmprojectstatusPK.id = :id"),
    @NamedQuery(name = "Crmprojectstatus.findByPubkey", query = "SELECT c FROM Crmprojectstatus c WHERE c.crmprojectstatusPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmprojectstatus.findByStatusname", query = "SELECT c FROM Crmprojectstatus c WHERE c.statusname = :statusname"),
    @NamedQuery(name = "Crmprojectstatus.findByCreateddate", query = "SELECT c FROM Crmprojectstatus c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmprojectstatus.findByCreatedfrom", query = "SELECT c FROM Crmprojectstatus c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmprojectstatus.findByChangeddate", query = "SELECT c FROM Crmprojectstatus c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmprojectstatus.findByChangedfrom", query = "SELECT c FROM Crmprojectstatus c WHERE c.changedfrom = :changedfrom")})
public class Crmprojectstatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmprojectstatusPK crmprojectstatusPK;
    @Size(max = 255)
    @Column(length = 255)
    private String statusname;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmprojectstatus")
    private Collection<Crmproject> crmprojectCollection;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;

    public Crmprojectstatus() {
    }

    public Crmprojectstatus(CrmprojectstatusPK crmprojectstatusPK) {
        this.crmprojectstatusPK = crmprojectstatusPK;
    }

    public Crmprojectstatus(int id, int pubkey) {
        this.crmprojectstatusPK = new CrmprojectstatusPK(id, pubkey);
    }

    public CrmprojectstatusPK getCrmprojectstatusPK() {
        return crmprojectstatusPK;
    }

    public void setCrmprojectstatusPK(CrmprojectstatusPK crmprojectstatusPK) {
        this.crmprojectstatusPK = crmprojectstatusPK;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
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
    public Collection<Crmproject> getCrmprojectCollection() {
        return crmprojectCollection;
    }

    public void setCrmprojectCollection(Collection<Crmproject> crmprojectCollection) {
        this.crmprojectCollection = crmprojectCollection;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crmprojectstatusPK != null ? crmprojectstatusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmprojectstatus)) {
            return false;
        }
        Crmprojectstatus other = (Crmprojectstatus) object;
        if ((this.crmprojectstatusPK == null && other.crmprojectstatusPK != null) || (this.crmprojectstatusPK != null && !this.crmprojectstatusPK.equals(other.crmprojectstatusPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmprojectstatus[ crmprojectstatusPK=" + crmprojectstatusPK + " ]";
    }

}
