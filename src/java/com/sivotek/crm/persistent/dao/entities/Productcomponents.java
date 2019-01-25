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
    @NamedQuery(name = "Productcomponents.findAll", query = "SELECT p FROM Productcomponents p"),
    @NamedQuery(name = "Productcomponents.findById", query = "SELECT p FROM Productcomponents p WHERE p.productcomponentsPK.id = :id"),
    @NamedQuery(name = "Productcomponents.findByPubkey", query = "SELECT p FROM Productcomponents p WHERE p.productcomponentsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Productcomponents.findByComponentname", query = "SELECT p FROM Productcomponents p WHERE p.componentname = :componentname"),
    @NamedQuery(name = "Productcomponents.findByEstimatedcost", query = "SELECT p FROM Productcomponents p WHERE p.estimatedcost = :estimatedcost"),
    @NamedQuery(name = "Productcomponents.findByActualcost", query = "SELECT p FROM Productcomponents p WHERE p.actualcost = :actualcost"),
    @NamedQuery(name = "Productcomponents.findByQuantity", query = "SELECT p FROM Productcomponents p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Productcomponents.findByTotalcost", query = "SELECT p FROM Productcomponents p WHERE p.totalcost = :totalcost"),
    @NamedQuery(name = "Productcomponents.findByCreateddate", query = "SELECT p FROM Productcomponents p WHERE p.createddate = :createddate"),
    @NamedQuery(name = "Productcomponents.findByCreatedfrom", query = "SELECT p FROM Productcomponents p WHERE p.createdfrom = :createdfrom"),
    @NamedQuery(name = "Productcomponents.findByChangeddate", query = "SELECT p FROM Productcomponents p WHERE p.changeddate = :changeddate"),
    @NamedQuery(name = "Productcomponents.findByChangedfrom", query = "SELECT p FROM Productcomponents p WHERE p.changedfrom = :changedfrom")})
public class Productcomponents implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductcomponentsPK productcomponentsPK;
    @Size(max = 255)
    @Column(length = 255)
    private String componentname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double estimatedcost;
    @Column(precision = 22)
    private Double actualcost;
    private Integer quantity;
    @Column(precision = 22)
    private Double totalcost;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productcomponents")
    private Collection<Crmcampaignposition> crmcampaignpositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productcomponents")
    private Collection<Crmworkorder> crmworkorderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productcomponents")
    private Collection<Componentattachments> componentattachmentsCollection;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "componenttype", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Componenttype componenttype;
    @JoinColumns({
        @JoinColumn(name = "productid", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyproduct companyproduct;
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

    public Productcomponents() {
    }

    public Productcomponents(ProductcomponentsPK productcomponentsPK) {
        this.productcomponentsPK = productcomponentsPK;
    }

    public Productcomponents(int id, int pubkey) {
        this.productcomponentsPK = new ProductcomponentsPK(id, pubkey);
    }

    public ProductcomponentsPK getProductcomponentsPK() {
        return productcomponentsPK;
    }

    public void setProductcomponentsPK(ProductcomponentsPK productcomponentsPK) {
        this.productcomponentsPK = productcomponentsPK;
    }

    public String getComponentname() {
        return componentname;
    }

    public void setComponentname(String componentname) {
        this.componentname = componentname;
    }

    public Double getEstimatedcost() {
        return estimatedcost;
    }

    public void setEstimatedcost(Double estimatedcost) {
        this.estimatedcost = estimatedcost;
    }

    public Double getActualcost() {
        return actualcost;
    }

    public void setActualcost(Double actualcost) {
        this.actualcost = actualcost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(Double totalcost) {
        this.totalcost = totalcost;
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
    public Collection<Crmcampaignposition> getCrmcampaignpositionCollection() {
        return crmcampaignpositionCollection;
    }

    public void setCrmcampaignpositionCollection(Collection<Crmcampaignposition> crmcampaignpositionCollection) {
        this.crmcampaignpositionCollection = crmcampaignpositionCollection;
    }

    @XmlTransient
    public Collection<Crmworkorder> getCrmworkorderCollection() {
        return crmworkorderCollection;
    }

    public void setCrmworkorderCollection(Collection<Crmworkorder> crmworkorderCollection) {
        this.crmworkorderCollection = crmworkorderCollection;
    }

    @XmlTransient
    public Collection<Componentattachments> getComponentattachmentsCollection() {
        return componentattachmentsCollection;
    }

    public void setComponentattachmentsCollection(Collection<Componentattachments> componentattachmentsCollection) {
        this.componentattachmentsCollection = componentattachmentsCollection;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Componenttype getComponenttype() {
        return componenttype;
    }

    public void setComponenttype(Componenttype componenttype) {
        this.componenttype = componenttype;
    }

    public Companyproduct getCompanyproduct() {
        return companyproduct;
    }

    public void setCompanyproduct(Companyproduct companyproduct) {
        this.companyproduct = companyproduct;
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
        hash += (productcomponentsPK != null ? productcomponentsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productcomponents)) {
            return false;
        }
        Productcomponents other = (Productcomponents) object;
        if ((this.productcomponentsPK == null && other.productcomponentsPK != null) || (this.productcomponentsPK != null && !this.productcomponentsPK.equals(other.productcomponentsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Productcomponents[ productcomponentsPK=" + productcomponentsPK + " ]";
    }

}
