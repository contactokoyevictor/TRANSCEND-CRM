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
    @NamedQuery(name = "Companycustomer.findAll", query = "SELECT c FROM Companycustomer c"),
    @NamedQuery(name = "Companycustomer.findById", query = "SELECT c FROM Companycustomer c WHERE c.companycustomerPK.id = :id"),
    @NamedQuery(name = "Companycustomer.findByPubkey", query = "SELECT c FROM Companycustomer c WHERE c.companycustomerPK.pubkey = :pubkey"),
    @NamedQuery(name = "Companycustomer.findByName", query = "SELECT c FROM Companycustomer c WHERE c.name = :name"),
    @NamedQuery(name = "Companycustomer.findBySubName", query = "SELECT c FROM Companycustomer c WHERE c.subName = :subName"),
    @NamedQuery(name = "Companycustomer.findByAlias", query = "SELECT c FROM Companycustomer c WHERE c.alias = :alias"),
    @NamedQuery(name = "Companycustomer.findByEmail", query = "SELECT c FROM Companycustomer c WHERE c.email = :email"),
    @NamedQuery(name = "Companycustomer.findByWeb", query = "SELECT c FROM Companycustomer c WHERE c.web = :web"),
    @NamedQuery(name = "Companycustomer.findByMotto", query = "SELECT c FROM Companycustomer c WHERE c.motto = :motto"),
    @NamedQuery(name = "Companycustomer.findByCategory", query = "SELECT c FROM Companycustomer c WHERE c.category = :category"),
    @NamedQuery(name = "Companycustomer.findByIslock", query = "SELECT c FROM Companycustomer c WHERE c.islock = :islock"),
    @NamedQuery(name = "Companycustomer.findByIsactual", query = "SELECT c FROM Companycustomer c WHERE c.isactual = :isactual"),
    @NamedQuery(name = "Companycustomer.findByCreateddate", query = "SELECT c FROM Companycustomer c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companycustomer.findByCreatedfrom", query = "SELECT c FROM Companycustomer c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companycustomer.findByChangeddate", query = "SELECT c FROM Companycustomer c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companycustomer.findByChangedfrom", query = "SELECT c FROM Companycustomer c WHERE c.changedfrom = :changedfrom")})
public class Companycustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanycustomerPK companycustomerPK;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Size(max = 255)
    @Column(name = "SUB_NAME", length = 255)
    private String subName;
    @Size(max = 255)
    @Column(length = 255)
    private String alias;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(length = 50)
    private String email;
    @Size(max = 150)
    @Column(length = 150)
    private String web;
    @Lob
    private byte[] logo;
    @Size(max = 255)
    @Column(length = 255)
    private String motto;
    private Integer category;
    private Boolean islock;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    private Boolean isactual;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companycustomer")
    private Collection<Crmcampaignreceiver> crmcampaignreceiverCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companycustomer")
    private Collection<Customercontacts> customercontactsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companycustomer")
    private Collection<Crmexpense> crmexpenseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companycustomer")
    private Collection<Crmworkorder> crmworkorderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companycustomer")
    private Collection<Crmlabor> crmlaborCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companycustomer")
    private Collection<Customerbank> customerbankCollection;
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

    public Companycustomer() {
    }

    public Companycustomer(CompanycustomerPK companycustomerPK) {
        this.companycustomerPK = companycustomerPK;
    }

    public Companycustomer(int id, int pubkey) {
        this.companycustomerPK = new CompanycustomerPK(id, pubkey);
    }

    public CompanycustomerPK getCompanycustomerPK() {
        return companycustomerPK;
    }

    public void setCompanycustomerPK(CompanycustomerPK companycustomerPK) {
        this.companycustomerPK = companycustomerPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Boolean getIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsactual() {
        return isactual;
    }

    public void setIsactual(Boolean isactual) {
        this.isactual = isactual;
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
    public Collection<Crmcampaignreceiver> getCrmcampaignreceiverCollection() {
        return crmcampaignreceiverCollection;
    }

    public void setCrmcampaignreceiverCollection(Collection<Crmcampaignreceiver> crmcampaignreceiverCollection) {
        this.crmcampaignreceiverCollection = crmcampaignreceiverCollection;
    }

    @XmlTransient
    public Collection<Customercontacts> getCustomercontactsCollection() {
        return customercontactsCollection;
    }

    public void setCustomercontactsCollection(Collection<Customercontacts> customercontactsCollection) {
        this.customercontactsCollection = customercontactsCollection;
    }

    @XmlTransient
    public Collection<Crmexpense> getCrmexpenseCollection() {
        return crmexpenseCollection;
    }

    public void setCrmexpenseCollection(Collection<Crmexpense> crmexpenseCollection) {
        this.crmexpenseCollection = crmexpenseCollection;
    }

    @XmlTransient
    public Collection<Crmworkorder> getCrmworkorderCollection() {
        return crmworkorderCollection;
    }

    public void setCrmworkorderCollection(Collection<Crmworkorder> crmworkorderCollection) {
        this.crmworkorderCollection = crmworkorderCollection;
    }

    @XmlTransient
    public Collection<Crmlabor> getCrmlaborCollection() {
        return crmlaborCollection;
    }

    public void setCrmlaborCollection(Collection<Crmlabor> crmlaborCollection) {
        this.crmlaborCollection = crmlaborCollection;
    }

    @XmlTransient
    public Collection<Customerbank> getCustomerbankCollection() {
        return customerbankCollection;
    }

    public void setCustomerbankCollection(Collection<Customerbank> customerbankCollection) {
        this.customerbankCollection = customerbankCollection;
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
        hash += (companycustomerPK != null ? companycustomerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companycustomer)) {
            return false;
        }
        Companycustomer other = (Companycustomer) object;
        if ((this.companycustomerPK == null && other.companycustomerPK != null) || (this.companycustomerPK != null && !this.companycustomerPK.equals(other.companycustomerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companycustomer[ companycustomerPK=" + companycustomerPK + " ]";
    }

}
