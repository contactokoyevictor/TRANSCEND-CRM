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
    @NamedQuery(name = "Crmcampaigndocs.findAll", query = "SELECT c FROM Crmcampaigndocs c"),
    @NamedQuery(name = "Crmcampaigndocs.findByDocid", query = "SELECT c FROM Crmcampaigndocs c WHERE c.crmcampaigndocsPK.docid = :docid"),
    @NamedQuery(name = "Crmcampaigndocs.findByPubkey", query = "SELECT c FROM Crmcampaigndocs c WHERE c.crmcampaigndocsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmcampaigndocs.findByName", query = "SELECT c FROM Crmcampaigndocs c WHERE c.name = :name"),
    @NamedQuery(name = "Crmcampaigndocs.findByCreateddate", query = "SELECT c FROM Crmcampaigndocs c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmcampaigndocs.findByCreatedfrom", query = "SELECT c FROM Crmcampaigndocs c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmcampaigndocs.findByChangeddate", query = "SELECT c FROM Crmcampaigndocs c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmcampaigndocs.findByChangedfrom", query = "SELECT c FROM Crmcampaigndocs c WHERE c.changedfrom = :changedfrom")})
public class Crmcampaigndocs implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmcampaigndocsPK crmcampaigndocsPK;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Lob
    private byte[] files;
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
        @JoinColumn(name = "CAMPAIGNID", referencedColumnName = "CAMPAIGNID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmcampaign crmcampaign;
    @JoinColumn(name = "CONTENTTYPEID", referencedColumnName = "ID")
    @ManyToOne
    private Contenttypes contenttypeid;
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

    public Crmcampaigndocs() {
    }

    public Crmcampaigndocs(CrmcampaigndocsPK crmcampaigndocsPK) {
        this.crmcampaigndocsPK = crmcampaigndocsPK;
    }

    public Crmcampaigndocs(int docid, int pubkey) {
        this.crmcampaigndocsPK = new CrmcampaigndocsPK(docid, pubkey);
    }

    public CrmcampaigndocsPK getCrmcampaigndocsPK() {
        return crmcampaigndocsPK;
    }

    public void setCrmcampaigndocsPK(CrmcampaigndocsPK crmcampaigndocsPK) {
        this.crmcampaigndocsPK = crmcampaigndocsPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFiles() {
        return files;
    }

    public void setFiles(byte[] files) {
        this.files = files;
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

    public Crmcampaign getCrmcampaign() {
        return crmcampaign;
    }

    public void setCrmcampaign(Crmcampaign crmcampaign) {
        this.crmcampaign = crmcampaign;
    }

    public Contenttypes getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(Contenttypes contenttypeid) {
        this.contenttypeid = contenttypeid;
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
        hash += (crmcampaigndocsPK != null ? crmcampaigndocsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmcampaigndocs)) {
            return false;
        }
        Crmcampaigndocs other = (Crmcampaigndocs) object;
        if ((this.crmcampaigndocsPK == null && other.crmcampaigndocsPK != null) || (this.crmcampaigndocsPK != null && !this.crmcampaigndocsPK.equals(other.crmcampaigndocsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmcampaigndocs[ crmcampaigndocsPK=" + crmcampaigndocsPK + " ]";
    }

}
