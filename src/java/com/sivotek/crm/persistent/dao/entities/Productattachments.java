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
    @NamedQuery(name = "Productattachments.findAll", query = "SELECT p FROM Productattachments p"),
    @NamedQuery(name = "Productattachments.findById", query = "SELECT p FROM Productattachments p WHERE p.productattachmentsPK.id = :id"),
    @NamedQuery(name = "Productattachments.findByPubkey", query = "SELECT p FROM Productattachments p WHERE p.productattachmentsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Productattachments.findByAttachname", query = "SELECT p FROM Productattachments p WHERE p.attachname = :attachname"),
    @NamedQuery(name = "Productattachments.findByFilename", query = "SELECT p FROM Productattachments p WHERE p.filename = :filename"),
    @NamedQuery(name = "Productattachments.findByCreateddate", query = "SELECT p FROM Productattachments p WHERE p.createddate = :createddate"),
    @NamedQuery(name = "Productattachments.findByCreatedfrom", query = "SELECT p FROM Productattachments p WHERE p.createdfrom = :createdfrom"),
    @NamedQuery(name = "Productattachments.findByChangeddate", query = "SELECT p FROM Productattachments p WHERE p.changeddate = :changeddate"),
    @NamedQuery(name = "Productattachments.findByChangedfrom", query = "SELECT p FROM Productattachments p WHERE p.changedfrom = :changedfrom")})
public class Productattachments implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductattachmentsPK productattachmentsPK;
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
        @JoinColumn(name = "productid", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee2;
    @JoinColumn(name = "contenttype", referencedColumnName = "ID")
    @ManyToOne
    private Contenttypes contenttype;

    public Productattachments() {
    }

    public Productattachments(ProductattachmentsPK productattachmentsPK) {
        this.productattachmentsPK = productattachmentsPK;
    }

    public Productattachments(int id, int pubkey) {
        this.productattachmentsPK = new ProductattachmentsPK(id, pubkey);
    }

    public ProductattachmentsPK getProductattachmentsPK() {
        return productattachmentsPK;
    }

    public void setProductattachmentsPK(ProductattachmentsPK productattachmentsPK) {
        this.productattachmentsPK = productattachmentsPK;
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

    public Companyemployee getCompanyemployee2() {
        return companyemployee2;
    }

    public void setCompanyemployee2(Companyemployee companyemployee2) {
        this.companyemployee2 = companyemployee2;
    }

    public Contenttypes getContenttype() {
        return contenttype;
    }

    public void setContenttype(Contenttypes contenttype) {
        this.contenttype = contenttype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productattachmentsPK != null ? productattachmentsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productattachments)) {
            return false;
        }
        Productattachments other = (Productattachments) object;
        if ((this.productattachmentsPK == null && other.productattachmentsPK != null) || (this.productattachmentsPK != null && !this.productattachmentsPK.equals(other.productattachmentsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Productattachments[ productattachmentsPK=" + productattachmentsPK + " ]";
    }

}
