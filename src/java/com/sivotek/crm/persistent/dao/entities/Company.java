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
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByCompanyid", query = "SELECT c FROM Company c WHERE c.companyPK.companyid = :companyid"),
    @NamedQuery(name = "Company.findByPubkey", query = "SELECT c FROM Company c WHERE c.companyPK.pubkey = :pubkey"),
    @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name"),
    @NamedQuery(name = "Company.findBySubName", query = "SELECT c FROM Company c WHERE c.subName = :subName"),
    @NamedQuery(name = "Company.findByAlias", query = "SELECT c FROM Company c WHERE c.alias = :alias"),
    @NamedQuery(name = "Company.findByPhone", query = "SELECT c FROM Company c WHERE c.phone = :phone"),
    @NamedQuery(name = "Company.findByFax", query = "SELECT c FROM Company c WHERE c.fax = :fax"),
    @NamedQuery(name = "Company.findByEmail", query = "SELECT c FROM Company c WHERE c.email = :email"),
    @NamedQuery(name = "Company.findByWeb", query = "SELECT c FROM Company c WHERE c.web = :web"),
    @NamedQuery(name = "Company.findByMotto", query = "SELECT c FROM Company c WHERE c.motto = :motto"),
    @NamedQuery(name = "Company.findByIslock", query = "SELECT c FROM Company c WHERE c.islock = :islock"),
    @NamedQuery(name = "Company.findByCreateddate", query = "SELECT c FROM Company c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Company.findByCreatedfrom", query = "SELECT c FROM Company c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Company.findByChangeddate", query = "SELECT c FROM Company c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Company.findByChangedfrom", query = "SELECT c FROM Company c WHERE c.changedfrom = :changedfrom"),
    @NamedQuery(name = "Company.findByIsactual", query = "SELECT c FROM Company c WHERE c.isactual = :isactual")})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyPK companyPK;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Size(max = 255)
    @Column(name = "SUB_NAME", length = 255)
    private String subName;
    @Size(max = 255)
    @Column(length = 255)
    private String alias;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(length = 50)
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(length = 50)
    private String fax;
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
    private Boolean islock;
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
    private Boolean isactual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmmessagechannel> crmmessagechannelCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companybank> companybankCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmprojectprops> crmprojectpropsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmcampaignreceiver> crmcampaignreceiverCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Productattachments> productattachmentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmcampaignstatus> crmcampaignstatusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmcampaignposition> crmcampaignpositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companydepartment> companydepartmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmusermodules> crmusermodulesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmexpense> crmexpenseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmworkorder> crmworkorderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companycontacts> companycontactsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companyemployee> companyemployeeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companyproducttype> companyproducttypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmlicense> crmlicenseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmcampaign> crmcampaignCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companyhirarchie> companyhirarchieCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company1")
    private Collection<Companyhirarchie> companyhirarchieCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company2")
    private Collection<Companyhirarchie> companyhirarchieCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companyaccountstackdocs> companyaccountstackdocsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmscheduler> crmschedulerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmlabor> crmlaborCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmusers> crmusersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companyproduct> companyproductCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Employeedesignation> employeedesignationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companyaccountstack> companyaccountstackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Componentattachments> componentattachmentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companyaccountstackcd> companyaccountstackcdCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmworkordertype> crmworkordertypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmlabortype> crmlabortypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmbillingtype> crmbillingtypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companyproductcategory> companyproductcategoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmcampaignprops> crmcampaignpropsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Customercategory> customercategoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Productcomponents> productcomponentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmexpensetype> crmexpensetypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companypayments> companypaymentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmworkorderresolution> crmworkorderresolutionCollection;
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
    @JoinColumn(name = "CATEGORY", referencedColumnName = "ID")
    @ManyToOne
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companyaddresstype> companyaddresstypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companycustomer> companycustomerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmprojectstatus> crmprojectstatusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Crmschedulerevnttype> crmschedulerevnttypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Componenttype> componenttypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companypaymentscheme> companypaymentschemeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Companycontactsaddress> companycontactsaddressCollection;

    public Company() {
    }

    public Company(CompanyPK companyPK) {
        this.companyPK = companyPK;
    }

    public Company(int companyid, int pubkey) {
        this.companyPK = new CompanyPK(companyid, pubkey);
    }

    public CompanyPK getCompanyPK() {
        return companyPK;
    }

    public void setCompanyPK(CompanyPK companyPK) {
        this.companyPK = companyPK;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public Boolean getIsactual() {
        return isactual;
    }

    public void setIsactual(Boolean isactual) {
        this.isactual = isactual;
    }

    @XmlTransient
    public Collection<Crmmessagechannel> getCrmmessagechannelCollection() {
        return crmmessagechannelCollection;
    }

    public void setCrmmessagechannelCollection(Collection<Crmmessagechannel> crmmessagechannelCollection) {
        this.crmmessagechannelCollection = crmmessagechannelCollection;
    }

    @XmlTransient
    public Collection<Companybank> getCompanybankCollection() {
        return companybankCollection;
    }

    public void setCompanybankCollection(Collection<Companybank> companybankCollection) {
        this.companybankCollection = companybankCollection;
    }

    @XmlTransient
    public Collection<Crmprojectprops> getCrmprojectpropsCollection() {
        return crmprojectpropsCollection;
    }

    public void setCrmprojectpropsCollection(Collection<Crmprojectprops> crmprojectpropsCollection) {
        this.crmprojectpropsCollection = crmprojectpropsCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignreceiver> getCrmcampaignreceiverCollection() {
        return crmcampaignreceiverCollection;
    }

    public void setCrmcampaignreceiverCollection(Collection<Crmcampaignreceiver> crmcampaignreceiverCollection) {
        this.crmcampaignreceiverCollection = crmcampaignreceiverCollection;
    }

    @XmlTransient
    public Collection<Productattachments> getProductattachmentsCollection() {
        return productattachmentsCollection;
    }

    public void setProductattachmentsCollection(Collection<Productattachments> productattachmentsCollection) {
        this.productattachmentsCollection = productattachmentsCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignstatus> getCrmcampaignstatusCollection() {
        return crmcampaignstatusCollection;
    }

    public void setCrmcampaignstatusCollection(Collection<Crmcampaignstatus> crmcampaignstatusCollection) {
        this.crmcampaignstatusCollection = crmcampaignstatusCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignposition> getCrmcampaignpositionCollection() {
        return crmcampaignpositionCollection;
    }

    public void setCrmcampaignpositionCollection(Collection<Crmcampaignposition> crmcampaignpositionCollection) {
        this.crmcampaignpositionCollection = crmcampaignpositionCollection;
    }

    @XmlTransient
    public Collection<Companydepartment> getCompanydepartmentCollection() {
        return companydepartmentCollection;
    }

    public void setCompanydepartmentCollection(Collection<Companydepartment> companydepartmentCollection) {
        this.companydepartmentCollection = companydepartmentCollection;
    }

    @XmlTransient
    public Collection<Crmusermodules> getCrmusermodulesCollection() {
        return crmusermodulesCollection;
    }

    public void setCrmusermodulesCollection(Collection<Crmusermodules> crmusermodulesCollection) {
        this.crmusermodulesCollection = crmusermodulesCollection;
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
    public Collection<Companycontacts> getCompanycontactsCollection() {
        return companycontactsCollection;
    }

    public void setCompanycontactsCollection(Collection<Companycontacts> companycontactsCollection) {
        this.companycontactsCollection = companycontactsCollection;
    }

    @XmlTransient
    public Collection<Companyemployee> getCompanyemployeeCollection() {
        return companyemployeeCollection;
    }

    public void setCompanyemployeeCollection(Collection<Companyemployee> companyemployeeCollection) {
        this.companyemployeeCollection = companyemployeeCollection;
    }

    @XmlTransient
    public Collection<Companyproducttype> getCompanyproducttypeCollection() {
        return companyproducttypeCollection;
    }

    public void setCompanyproducttypeCollection(Collection<Companyproducttype> companyproducttypeCollection) {
        this.companyproducttypeCollection = companyproducttypeCollection;
    }

    @XmlTransient
    public Collection<Crmlicense> getCrmlicenseCollection() {
        return crmlicenseCollection;
    }

    public void setCrmlicenseCollection(Collection<Crmlicense> crmlicenseCollection) {
        this.crmlicenseCollection = crmlicenseCollection;
    }

    @XmlTransient
    public Collection<Crmcampaign> getCrmcampaignCollection() {
        return crmcampaignCollection;
    }

    public void setCrmcampaignCollection(Collection<Crmcampaign> crmcampaignCollection) {
        this.crmcampaignCollection = crmcampaignCollection;
    }

    @XmlTransient
    public Collection<Companyhirarchie> getCompanyhirarchieCollection() {
        return companyhirarchieCollection;
    }

    public void setCompanyhirarchieCollection(Collection<Companyhirarchie> companyhirarchieCollection) {
        this.companyhirarchieCollection = companyhirarchieCollection;
    }

    @XmlTransient
    public Collection<Companyhirarchie> getCompanyhirarchieCollection1() {
        return companyhirarchieCollection1;
    }

    public void setCompanyhirarchieCollection1(Collection<Companyhirarchie> companyhirarchieCollection1) {
        this.companyhirarchieCollection1 = companyhirarchieCollection1;
    }

    @XmlTransient
    public Collection<Companyhirarchie> getCompanyhirarchieCollection2() {
        return companyhirarchieCollection2;
    }

    public void setCompanyhirarchieCollection2(Collection<Companyhirarchie> companyhirarchieCollection2) {
        this.companyhirarchieCollection2 = companyhirarchieCollection2;
    }

    @XmlTransient
    public Collection<Companyaccountstackdocs> getCompanyaccountstackdocsCollection() {
        return companyaccountstackdocsCollection;
    }

    public void setCompanyaccountstackdocsCollection(Collection<Companyaccountstackdocs> companyaccountstackdocsCollection) {
        this.companyaccountstackdocsCollection = companyaccountstackdocsCollection;
    }

    @XmlTransient
    public Collection<Crmscheduler> getCrmschedulerCollection() {
        return crmschedulerCollection;
    }

    public void setCrmschedulerCollection(Collection<Crmscheduler> crmschedulerCollection) {
        this.crmschedulerCollection = crmschedulerCollection;
    }

    @XmlTransient
    public Collection<Crmlabor> getCrmlaborCollection() {
        return crmlaborCollection;
    }

    public void setCrmlaborCollection(Collection<Crmlabor> crmlaborCollection) {
        this.crmlaborCollection = crmlaborCollection;
    }

    @XmlTransient
    public Collection<Compnaypaymentcurrency> getCompnaypaymentcurrencyCollection() {
        return compnaypaymentcurrencyCollection;
    }

    public void setCompnaypaymentcurrencyCollection(Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollection) {
        this.compnaypaymentcurrencyCollection = compnaypaymentcurrencyCollection;
    }

    @XmlTransient
    public Collection<Crmusers> getCrmusersCollection() {
        return crmusersCollection;
    }

    public void setCrmusersCollection(Collection<Crmusers> crmusersCollection) {
        this.crmusersCollection = crmusersCollection;
    }

    @XmlTransient
    public Collection<Companyproduct> getCompanyproductCollection() {
        return companyproductCollection;
    }

    public void setCompanyproductCollection(Collection<Companyproduct> companyproductCollection) {
        this.companyproductCollection = companyproductCollection;
    }

    @XmlTransient
    public Collection<Employeedesignation> getEmployeedesignationCollection() {
        return employeedesignationCollection;
    }

    public void setEmployeedesignationCollection(Collection<Employeedesignation> employeedesignationCollection) {
        this.employeedesignationCollection = employeedesignationCollection;
    }

    @XmlTransient
    public Collection<Companyaccountstack> getCompanyaccountstackCollection() {
        return companyaccountstackCollection;
    }

    public void setCompanyaccountstackCollection(Collection<Companyaccountstack> companyaccountstackCollection) {
        this.companyaccountstackCollection = companyaccountstackCollection;
    }

    @XmlTransient
    public Collection<Componentattachments> getComponentattachmentsCollection() {
        return componentattachmentsCollection;
    }

    public void setComponentattachmentsCollection(Collection<Componentattachments> componentattachmentsCollection) {
        this.componentattachmentsCollection = componentattachmentsCollection;
    }

    @XmlTransient
    public Collection<Companyaccountstackcd> getCompanyaccountstackcdCollection() {
        return companyaccountstackcdCollection;
    }

    public void setCompanyaccountstackcdCollection(Collection<Companyaccountstackcd> companyaccountstackcdCollection) {
        this.companyaccountstackcdCollection = companyaccountstackcdCollection;
    }

    @XmlTransient
    public Collection<Crmworkordertype> getCrmworkordertypeCollection() {
        return crmworkordertypeCollection;
    }

    public void setCrmworkordertypeCollection(Collection<Crmworkordertype> crmworkordertypeCollection) {
        this.crmworkordertypeCollection = crmworkordertypeCollection;
    }

    @XmlTransient
    public Collection<Crmlabortype> getCrmlabortypeCollection() {
        return crmlabortypeCollection;
    }

    public void setCrmlabortypeCollection(Collection<Crmlabortype> crmlabortypeCollection) {
        this.crmlabortypeCollection = crmlabortypeCollection;
    }

    @XmlTransient
    public Collection<Crmbillingtype> getCrmbillingtypeCollection() {
        return crmbillingtypeCollection;
    }

    public void setCrmbillingtypeCollection(Collection<Crmbillingtype> crmbillingtypeCollection) {
        this.crmbillingtypeCollection = crmbillingtypeCollection;
    }

    @XmlTransient
    public Collection<Companyproductcategory> getCompanyproductcategoryCollection() {
        return companyproductcategoryCollection;
    }

    public void setCompanyproductcategoryCollection(Collection<Companyproductcategory> companyproductcategoryCollection) {
        this.companyproductcategoryCollection = companyproductcategoryCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignprops> getCrmcampaignpropsCollection() {
        return crmcampaignpropsCollection;
    }

    public void setCrmcampaignpropsCollection(Collection<Crmcampaignprops> crmcampaignpropsCollection) {
        this.crmcampaignpropsCollection = crmcampaignpropsCollection;
    }

    @XmlTransient
    public Collection<Customercategory> getCustomercategoryCollection() {
        return customercategoryCollection;
    }

    public void setCustomercategoryCollection(Collection<Customercategory> customercategoryCollection) {
        this.customercategoryCollection = customercategoryCollection;
    }

    @XmlTransient
    public Collection<Productcomponents> getProductcomponentsCollection() {
        return productcomponentsCollection;
    }

    public void setProductcomponentsCollection(Collection<Productcomponents> productcomponentsCollection) {
        this.productcomponentsCollection = productcomponentsCollection;
    }

    @XmlTransient
    public Collection<Crmexpensetype> getCrmexpensetypeCollection() {
        return crmexpensetypeCollection;
    }

    public void setCrmexpensetypeCollection(Collection<Crmexpensetype> crmexpensetypeCollection) {
        this.crmexpensetypeCollection = crmexpensetypeCollection;
    }

    @XmlTransient
    public Collection<Companypayments> getCompanypaymentsCollection() {
        return companypaymentsCollection;
    }

    public void setCompanypaymentsCollection(Collection<Companypayments> companypaymentsCollection) {
        this.companypaymentsCollection = companypaymentsCollection;
    }

    @XmlTransient
    public Collection<Crmworkorderresolution> getCrmworkorderresolutionCollection() {
        return crmworkorderresolutionCollection;
    }

    public void setCrmworkorderresolutionCollection(Collection<Crmworkorderresolution> crmworkorderresolutionCollection) {
        this.crmworkorderresolutionCollection = crmworkorderresolutionCollection;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlTransient
    public Collection<Companyaddresstype> getCompanyaddresstypeCollection() {
        return companyaddresstypeCollection;
    }

    public void setCompanyaddresstypeCollection(Collection<Companyaddresstype> companyaddresstypeCollection) {
        this.companyaddresstypeCollection = companyaddresstypeCollection;
    }

    @XmlTransient
    public Collection<Companycustomer> getCompanycustomerCollection() {
        return companycustomerCollection;
    }

    public void setCompanycustomerCollection(Collection<Companycustomer> companycustomerCollection) {
        this.companycustomerCollection = companycustomerCollection;
    }

    @XmlTransient
    public Collection<Crmprojectstatus> getCrmprojectstatusCollection() {
        return crmprojectstatusCollection;
    }

    public void setCrmprojectstatusCollection(Collection<Crmprojectstatus> crmprojectstatusCollection) {
        this.crmprojectstatusCollection = crmprojectstatusCollection;
    }

    @XmlTransient
    public Collection<Crmschedulerevnttype> getCrmschedulerevnttypeCollection() {
        return crmschedulerevnttypeCollection;
    }

    public void setCrmschedulerevnttypeCollection(Collection<Crmschedulerevnttype> crmschedulerevnttypeCollection) {
        this.crmschedulerevnttypeCollection = crmschedulerevnttypeCollection;
    }

    @XmlTransient
    public Collection<Componenttype> getComponenttypeCollection() {
        return componenttypeCollection;
    }

    public void setComponenttypeCollection(Collection<Componenttype> componenttypeCollection) {
        this.componenttypeCollection = componenttypeCollection;
    }

    @XmlTransient
    public Collection<Companypaymentscheme> getCompanypaymentschemeCollection() {
        return companypaymentschemeCollection;
    }

    public void setCompanypaymentschemeCollection(Collection<Companypaymentscheme> companypaymentschemeCollection) {
        this.companypaymentschemeCollection = companypaymentschemeCollection;
    }

    @XmlTransient
    public Collection<Companycontactsaddress> getCompanycontactsaddressCollection() {
        return companycontactsaddressCollection;
    }

    public void setCompanycontactsaddressCollection(Collection<Companycontactsaddress> companycontactsaddressCollection) {
        this.companycontactsaddressCollection = companycontactsaddressCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyPK != null ? companyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyPK == null && other.companyPK != null) || (this.companyPK != null && !this.companyPK.equals(other.companyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Company[ companyPK=" + companyPK + " ]";
    }

}
