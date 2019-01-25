/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "Crmcampaignposition.findAll", query = "SELECT c FROM Crmcampaignposition c"),
    @NamedQuery(name = "Crmcampaignposition.findByPositionid", query = "SELECT c FROM Crmcampaignposition c WHERE c.crmcampaignpositionPK.positionid = :positionid"),
    @NamedQuery(name = "Crmcampaignposition.findByPubkey", query = "SELECT c FROM Crmcampaignposition c WHERE c.crmcampaignpositionPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmcampaignposition.findByDeduction", query = "SELECT c FROM Crmcampaignposition c WHERE c.deduction = :deduction"),
    @NamedQuery(name = "Crmcampaignposition.findByQuantity", query = "SELECT c FROM Crmcampaignposition c WHERE c.quantity = :quantity"),
    @NamedQuery(name = "Crmcampaignposition.findByNetamount", query = "SELECT c FROM Crmcampaignposition c WHERE c.netamount = :netamount"),
    @NamedQuery(name = "Crmcampaignposition.findByPretax", query = "SELECT c FROM Crmcampaignposition c WHERE c.pretax = :pretax"),
    @NamedQuery(name = "Crmcampaignposition.findByCreateddate", query = "SELECT c FROM Crmcampaignposition c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmcampaignposition.findByCreatedfrom", query = "SELECT c FROM Crmcampaignposition c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmcampaignposition.findByChangeddate", query = "SELECT c FROM Crmcampaignposition c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmcampaignposition.findByChangedfrom", query = "SELECT c FROM Crmcampaignposition c WHERE c.changedfrom = :changedfrom")})
public class Crmcampaignposition implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmcampaignpositionPK crmcampaignpositionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double deduction;
    private BigInteger quantity;
    @Column(precision = 22)
    private Double netamount;
    @Column(precision = 22)
    private Double pretax;
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
        @JoinColumn(name = "CAMPAIGNID", referencedColumnName = "CAMPAIGNID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmcampaign crmcampaign;
    @JoinColumns({
        @JoinColumn(name = "PRODUCTID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyproduct companyproduct;
    @JoinColumns({
        @JoinColumn(name = "COMPONENTTID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Productcomponents productcomponents;
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

    public Crmcampaignposition() {
    }

    public Crmcampaignposition(CrmcampaignpositionPK crmcampaignpositionPK) {
        this.crmcampaignpositionPK = crmcampaignpositionPK;
    }

    public Crmcampaignposition(int positionid, int pubkey) {
        this.crmcampaignpositionPK = new CrmcampaignpositionPK(positionid, pubkey);
    }

    public CrmcampaignpositionPK getCrmcampaignpositionPK() {
        return crmcampaignpositionPK;
    }

    public void setCrmcampaignpositionPK(CrmcampaignpositionPK crmcampaignpositionPK) {
        this.crmcampaignpositionPK = crmcampaignpositionPK;
    }

    public Double getDeduction() {
        return deduction;
    }

    public void setDeduction(Double deduction) {
        this.deduction = deduction;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public Double getNetamount() {
        return netamount;
    }

    public void setNetamount(Double netamount) {
        this.netamount = netamount;
    }

    public Double getPretax() {
        return pretax;
    }

    public void setPretax(Double pretax) {
        this.pretax = pretax;
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

    public Crmcampaign getCrmcampaign() {
        return crmcampaign;
    }

    public void setCrmcampaign(Crmcampaign crmcampaign) {
        this.crmcampaign = crmcampaign;
    }

    public Companyproduct getCompanyproduct() {
        return companyproduct;
    }

    public void setCompanyproduct(Companyproduct companyproduct) {
        this.companyproduct = companyproduct;
    }

    public Productcomponents getProductcomponents() {
        return productcomponents;
    }

    public void setProductcomponents(Productcomponents productcomponents) {
        this.productcomponents = productcomponents;
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
        hash += (crmcampaignpositionPK != null ? crmcampaignpositionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmcampaignposition)) {
            return false;
        }
        Crmcampaignposition other = (Crmcampaignposition) object;
        if ((this.crmcampaignpositionPK == null && other.crmcampaignpositionPK != null) || (this.crmcampaignpositionPK != null && !this.crmcampaignpositionPK.equals(other.crmcampaignpositionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmcampaignposition[ crmcampaignpositionPK=" + crmcampaignpositionPK + " ]";
    }

}
