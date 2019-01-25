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
    @NamedQuery(name = "Componentattachments.findAll", query = "SELECT c FROM Componentattachments c"),
    @NamedQuery(name = "Componentattachments.findById", query = "SELECT c FROM Componentattachments c WHERE c.componentattachmentsPK.id = :id"),
    @NamedQuery(name = "Componentattachments.findByPubkey", query = "SELECT c FROM Componentattachments c WHERE c.componentattachmentsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Componentattachments.findByAttachname", query = "SELECT c FROM Componentattachments c WHERE c.attachname = :attachname"),
    @NamedQuery(name = "Componentattachments.findByFilename", query = "SELECT c FROM Componentattachments c WHERE c.filename = :filename"),
    @NamedQuery(name = "Componentattachments.findByCreateddate", query = "SELECT c FROM Componentattachments c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Componentattachments.findByCreatedfrom", query = "SELECT c FROM Componentattachments c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Componentattachments.findByChangeddate", query = "SELECT c FROM Componentattachments c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Componentattachments.findByChangedfrom", query = "SELECT c FROM Componentattachments c WHERE c.changedfrom = :changedfrom")})
public class Componentattachments implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComponentattachmentsPK componentattachmentsPK;
    @Size(max = 255)
    @Column(length = 255)
    private String attachname;
    @Lob
    private byte[] attachment;
    @Size(max = 255)
    @Column(length = 255)
    private String filename;
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
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "componentid", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Productcomponents productcomponents;
    @JoinColumn(name = "contenttype", referencedColumnName = "ID")
    @ManyToOne
    private Contenttypes contenttype;
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

    public Componentattachments() {
    }

    public Componentattachments(ComponentattachmentsPK componentattachmentsPK) {
        this.componentattachmentsPK = componentattachmentsPK;
    }

    public Componentattachments(int id, int pubkey) {
        this.componentattachmentsPK = new ComponentattachmentsPK(id, pubkey);
    }

    public ComponentattachmentsPK getComponentattachmentsPK() {
        return componentattachmentsPK;
    }

    public void setComponentattachmentsPK(ComponentattachmentsPK componentattachmentsPK) {
        this.componentattachmentsPK = componentattachmentsPK;
    }

    public String getAttachname() {
        return attachname;
    }

    public void setAttachname(String attachname) {
        this.attachname = attachname;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Productcomponents getProductcomponents() {
        return productcomponents;
    }

    public void setProductcomponents(Productcomponents productcomponents) {
        this.productcomponents = productcomponents;
    }

    public Contenttypes getContenttype() {
        return contenttype;
    }

    public void setContenttype(Contenttypes contenttype) {
        this.contenttype = contenttype;
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
        hash += (componentattachmentsPK != null ? componentattachmentsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componentattachments)) {
            return false;
        }
        Componentattachments other = (Componentattachments) object;
        if ((this.componentattachmentsPK == null && other.componentattachmentsPK != null) || (this.componentattachmentsPK != null && !this.componentattachmentsPK.equals(other.componentattachmentsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Componentattachments[ componentattachmentsPK=" + componentattachmentsPK + " ]";
    }

}
