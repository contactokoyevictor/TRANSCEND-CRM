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
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Crmforum.findAll", query = "SELECT c FROM Crmforum c"),
    @NamedQuery(name = "Crmforum.findByForumid", query = "SELECT c FROM Crmforum c WHERE c.crmforumPK.forumid = :forumid"),
    @NamedQuery(name = "Crmforum.findByPubkey", query = "SELECT c FROM Crmforum c WHERE c.crmforumPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmforum.findByForumname", query = "SELECT c FROM Crmforum c WHERE c.forumname = :forumname"),
    @NamedQuery(name = "Crmforum.findByCreateddate", query = "SELECT c FROM Crmforum c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmforum.findByCreatedfrom", query = "SELECT c FROM Crmforum c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmforum.findByChangeddate", query = "SELECT c FROM Crmforum c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmforum.findByChangedfrom", query = "SELECT c FROM Crmforum c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Crmforum.findByChangedby", query = "SELECT c FROM Crmforum c WHERE c.changedby = :changedby")})
public class Crmforum implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmforumPK crmforumPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String forumname;
    @Lob
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String messagespool;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmforum")
    private Collection<Crmforumteammembers> crmforumteammembersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crmforum")
    private Collection<Crmforumdocs> crmforumdocsCollection;
    @JoinColumns({
        @JoinColumn(name = "forumadmin", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;

    public Crmforum() {
    }

    public Crmforum(CrmforumPK crmforumPK) {
        this.crmforumPK = crmforumPK;
    }

    public Crmforum(CrmforumPK crmforumPK, String forumname) {
        this.crmforumPK = crmforumPK;
        this.forumname = forumname;
    }

    public Crmforum(int forumid, int pubkey) {
        this.crmforumPK = new CrmforumPK(forumid, pubkey);
    }

    public CrmforumPK getCrmforumPK() {
        return crmforumPK;
    }

    public void setCrmforumPK(CrmforumPK crmforumPK) {
        this.crmforumPK = crmforumPK;
    }

    public String getForumname() {
        return forumname;
    }

    public void setForumname(String forumname) {
        this.forumname = forumname;
    }

    public String getMessagespool() {
        return messagespool;
    }

    public void setMessagespool(String messagespool) {
        this.messagespool = messagespool;
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

    @XmlTransient
    public Collection<Crmforumteammembers> getCrmforumteammembersCollection() {
        return crmforumteammembersCollection;
    }

    public void setCrmforumteammembersCollection(Collection<Crmforumteammembers> crmforumteammembersCollection) {
        this.crmforumteammembersCollection = crmforumteammembersCollection;
    }

    @XmlTransient
    public Collection<Crmforumdocs> getCrmforumdocsCollection() {
        return crmforumdocsCollection;
    }

    public void setCrmforumdocsCollection(Collection<Crmforumdocs> crmforumdocsCollection) {
        this.crmforumdocsCollection = crmforumdocsCollection;
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
        hash += (crmforumPK != null ? crmforumPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmforum)) {
            return false;
        }
        Crmforum other = (Crmforum) object;
        if ((this.crmforumPK == null && other.crmforumPK != null) || (this.crmforumPK != null && !this.crmforumPK.equals(other.crmforumPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmforum[ crmforumPK=" + crmforumPK + " ]";
    }

}
