/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Crmforumdocs.findAll", query = "SELECT c FROM Crmforumdocs c"),
    @NamedQuery(name = "Crmforumdocs.findByDocid", query = "SELECT c FROM Crmforumdocs c WHERE c.crmforumdocsPK.docid = :docid"),
    @NamedQuery(name = "Crmforumdocs.findByPubkey", query = "SELECT c FROM Crmforumdocs c WHERE c.crmforumdocsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmforumdocs.findByDocname", query = "SELECT c FROM Crmforumdocs c WHERE c.docname = :docname"),
    @NamedQuery(name = "Crmforumdocs.findByCreateddate", query = "SELECT c FROM Crmforumdocs c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmforumdocs.findByCreatedfrom", query = "SELECT c FROM Crmforumdocs c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmforumdocs.findByChangeddate", query = "SELECT c FROM Crmforumdocs c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmforumdocs.findByChangedfrom", query = "SELECT c FROM Crmforumdocs c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmforumdocs.findByChangedby", query = "SELECT c FROM Crmforumdocs c WHERE c.changedby = :changedby")})
public class Crmforumdocs implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmforumdocsPK crmforumdocsPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String docname;
    @Lob
    private byte[] file;
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
        @JoinColumn(name = "forumid", referencedColumnName = "forumid"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmforum crmforum;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;

    public Crmforumdocs() {
    }

    public Crmforumdocs(CrmforumdocsPK crmforumdocsPK) {
        this.crmforumdocsPK = crmforumdocsPK;
    }

    public Crmforumdocs(CrmforumdocsPK crmforumdocsPK, String docname) {
        this.crmforumdocsPK = crmforumdocsPK;
        this.docname = docname;
    }

    public Crmforumdocs(int docid, int pubkey) {
        this.crmforumdocsPK = new CrmforumdocsPK(docid, pubkey);
    }

    public CrmforumdocsPK getCrmforumdocsPK() {
        return crmforumdocsPK;
    }

    public void setCrmforumdocsPK(CrmforumdocsPK crmforumdocsPK) {
        this.crmforumdocsPK = crmforumdocsPK;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
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

    public Crmforum getCrmforum() {
        return crmforum;
    }

    public void setCrmforum(Crmforum crmforum) {
        this.crmforum = crmforum;
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
        hash += (crmforumdocsPK != null ? crmforumdocsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmforumdocs)) {
            return false;
        }
        Crmforumdocs other = (Crmforumdocs) object;
        if ((this.crmforumdocsPK == null && other.crmforumdocsPK != null) || (this.crmforumdocsPK != null && !this.crmforumdocsPK.equals(other.crmforumdocsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmforumdocs[ crmforumdocsPK=" + crmforumdocsPK + " ]";
    }

}
