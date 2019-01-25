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
    @NamedQuery(name = "Companyemployee.findAll", query = "SELECT c FROM Companyemployee c"),
    @NamedQuery(name = "Companyemployee.findById", query = "SELECT c FROM Companyemployee c WHERE c.companyemployeePK.id = :id"),
    @NamedQuery(name = "Companyemployee.findByPubkey", query = "SELECT c FROM Companyemployee c WHERE c.companyemployeePK.pubkey = :pubkey"),
    @NamedQuery(name = "Companyemployee.findByFirstName", query = "SELECT c FROM Companyemployee c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "Companyemployee.findByLastName", query = "SELECT c FROM Companyemployee c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Companyemployee.findByOtherName", query = "SELECT c FROM Companyemployee c WHERE c.otherName = :otherName"),
    @NamedQuery(name = "Companyemployee.findByPhone", query = "SELECT c FROM Companyemployee c WHERE c.phone = :phone"),
    @NamedQuery(name = "Companyemployee.findByFax", query = "SELECT c FROM Companyemployee c WHERE c.fax = :fax"),
    @NamedQuery(name = "Companyemployee.findByEmail", query = "SELECT c FROM Companyemployee c WHERE c.email = :email"),
    @NamedQuery(name = "Companyemployee.findByDesignation", query = "SELECT c FROM Companyemployee c WHERE c.designation = :designation"),
    @NamedQuery(name = "Companyemployee.findByIslock", query = "SELECT c FROM Companyemployee c WHERE c.islock = :islock"),
    @NamedQuery(name = "Companyemployee.findByWeb", query = "SELECT c FROM Companyemployee c WHERE c.web = :web"),
    @NamedQuery(name = "Companyemployee.findByCreateddate", query = "SELECT c FROM Companyemployee c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Companyemployee.findByCreatedfrom", query = "SELECT c FROM Companyemployee c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Companyemployee.findByChangeddate", query = "SELECT c FROM Companyemployee c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Companyemployee.findByChangedfrom", query = "SELECT c FROM Companyemployee c WHERE c.changedfrom = :changedfrom")})
public class Companyemployee implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyemployeePK companyemployeePK;
    @Size(max = 255)
    @Column(name = "FIRST_NAME", length = 255)
    private String firstName;
    @Size(max = 255)
    @Column(name = "LAST_NAME", length = 255)
    private String lastName;
    @Size(max = 255)
    @Column(name = "OTHER_NAME", length = 255)
    private String otherName;
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
    private Integer designation;
    private Boolean islock;
    @Size(max = 150)
    @Column(length = 150)
    private String web;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmmessagechannel> crmmessagechannelCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmmessagechannel> crmmessagechannelCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee2")
    private Collection<Crmmessagechannel> crmmessagechannelCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companybank> companybankCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companybank> companybankCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmprojectprops> crmprojectpropsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmprojectprops> crmprojectpropsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmcampaignreceiver> crmcampaignreceiverCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmcampaignreceiver> crmcampaignreceiverCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmworkordersettings> crmworkordersettingsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Productattachments> productattachmentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Productattachments> productattachmentsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee2")
    private Collection<Productattachments> productattachmentsCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmcampaignstatus> crmcampaignstatusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmcampaignstatus> crmcampaignstatusCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmcampaignposition> crmcampaignpositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmcampaignposition> crmcampaignpositionCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmforumteammembers> crmforumteammembersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Customercontacts> customercontactsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Customercontacts> customercontactsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Appointment> appointmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Appointment> appointmentCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee2")
    private Collection<Appointment> appointmentCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee3")
    private Collection<Appointment> appointmentCollection3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companydepartment> companydepartmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companydepartment> companydepartmentCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmusermodules> crmusermodulesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmusermodules> crmusermodulesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmexpense> crmexpenseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmexpense> crmexpenseCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmworkorder> crmworkorderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmworkorder> crmworkorderCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmcampaigndocs> crmcampaigndocsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmcampaigndocs> crmcampaigndocsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmemployeenote> crmemployeenoteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companycontacts> companycontactsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companycontacts> companycontactsCollection1;
    @JoinColumns({
        @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumns({
        @JoinColumn(name = "DEPARTMENTID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companydepartment companydepartment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companyemployee> companyemployeeCollection;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companyemployee> companyemployeeCollection1;
    @JoinColumns({
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companyproducttype> companyproducttypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companyproducttype> companyproducttypeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmcampaign> crmcampaignCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmcampaign> crmcampaignCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmmessagehistory> crmmessagehistoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmmessagehistory> crmmessagehistoryCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companyaccountstackdocs> companyaccountstackdocsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companyaccountstackdocs> companyaccountstackdocsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmscheduler> crmschedulerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmscheduler> crmschedulerCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee2")
    private Collection<Crmscheduler> crmschedulerCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmlabor> crmlaborCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmlabor> crmlaborCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Customerbank> customerbankCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Customerbank> customerbankCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmproject> crmprojectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmproject> crmprojectCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee2")
    private Collection<Crmproject> crmprojectCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companyemployeeaddress> companyemployeeaddressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companyemployeeaddress> companyemployeeaddressCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee2")
    private Collection<Companyemployeeaddress> companyemployeeaddressCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmforumdocs> crmforumdocsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companyproduct> companyproductCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companyproduct> companyproductCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Employeedesignation> employeedesignationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Employeedesignation> employeedesignationCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companyaccountstack> companyaccountstackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companyaccountstack> companyaccountstackCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmprojectteammembers> crmprojectteammembersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmprojectteammembers> crmprojectteammembersCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee2")
    private Collection<Crmprojectteammembers> crmprojectteammembersCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Componentattachments> componentattachmentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Componentattachments> componentattachmentsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmvisitorcontacts> crmvisitorcontactsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmvisitorcontacts> crmvisitorcontactsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companyaccountstackcd> companyaccountstackcdCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companyaccountstackcd> companyaccountstackcdCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee2")
    private Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee3")
    private Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee2")
    private Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmvisitor> crmvisitorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmvisitor> crmvisitorCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmworkordertype> crmworkordertypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmworkordertype> crmworkordertypeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmlabortype> crmlabortypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmlabortype> crmlabortypeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmbillingtype> crmbillingtypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmbillingtype> crmbillingtypeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companyproductcategory> companyproductcategoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companyproductcategory> companyproductcategoryCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmprojecttask> crmprojecttaskCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmprojecttask> crmprojecttaskCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmcampaignprops> crmcampaignpropsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmcampaignprops> crmcampaignpropsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Customercontactsaddress> customercontactsaddressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Customercontactsaddress> customercontactsaddressCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Customercategory> customercategoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Customercategory> customercategoryCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Productcomponents> productcomponentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Productcomponents> productcomponentsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmexpensetype> crmexpensetypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmexpensetype> crmexpensetypeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companypayments> companypaymentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companypayments> companypaymentsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmworkorderresolution> crmworkorderresolutionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmworkorderresolution> crmworkorderresolutionCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Company> companyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Company> companyCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companyaddresstype> companyaddresstypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companyaddresstype> companyaddresstypeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmemployeecontacts> crmemployeecontactsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Approval> approvalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Approval> approvalCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee2")
    private Collection<Approval> approvalCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Workorderinstructions> workorderinstructionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Workorderinstructions> workorderinstructionsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companycustomer> companycustomerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companycustomer> companycustomerCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmforum> crmforumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmprojectstatus> crmprojectstatusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmprojectstatus> crmprojectstatusCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmschedulerevnttype> crmschedulerevnttypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmschedulerevnttype> crmschedulerevnttypeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmschedulerefcode> crmschedulerefcodeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Crmschedulerefcode> crmschedulerefcodeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Componenttype> componenttypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Componenttype> componenttypeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companypaymentscheme> companypaymentschemeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companypaymentscheme> companypaymentschemeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Companycontactsaddress> companycontactsaddressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee1")
    private Collection<Companycontactsaddress> companycontactsaddressCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyemployee")
    private Collection<Crmprofilesettings> crmprofilesettingsCollection;

    public Companyemployee() {
    }

    public Companyemployee(CompanyemployeePK companyemployeePK) {
        this.companyemployeePK = companyemployeePK;
    }

    public Companyemployee(int id, int pubkey) {
        this.companyemployeePK = new CompanyemployeePK(id, pubkey);
    }

    public CompanyemployeePK getCompanyemployeePK() {
        return companyemployeePK;
    }

    public void setCompanyemployeePK(CompanyemployeePK companyemployeePK) {
        this.companyemployeePK = companyemployeePK;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
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

    public Integer getDesignation() {
        return designation;
    }

    public void setDesignation(Integer designation) {
        this.designation = designation;
    }

    public Boolean getIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
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
    public Collection<Crmmessagechannel> getCrmmessagechannelCollection() {
        return crmmessagechannelCollection;
    }

    public void setCrmmessagechannelCollection(Collection<Crmmessagechannel> crmmessagechannelCollection) {
        this.crmmessagechannelCollection = crmmessagechannelCollection;
    }

    @XmlTransient
    public Collection<Crmmessagechannel> getCrmmessagechannelCollection1() {
        return crmmessagechannelCollection1;
    }

    public void setCrmmessagechannelCollection1(Collection<Crmmessagechannel> crmmessagechannelCollection1) {
        this.crmmessagechannelCollection1 = crmmessagechannelCollection1;
    }

    @XmlTransient
    public Collection<Crmmessagechannel> getCrmmessagechannelCollection2() {
        return crmmessagechannelCollection2;
    }

    public void setCrmmessagechannelCollection2(Collection<Crmmessagechannel> crmmessagechannelCollection2) {
        this.crmmessagechannelCollection2 = crmmessagechannelCollection2;
    }

    @XmlTransient
    public Collection<Companybank> getCompanybankCollection() {
        return companybankCollection;
    }

    public void setCompanybankCollection(Collection<Companybank> companybankCollection) {
        this.companybankCollection = companybankCollection;
    }

    @XmlTransient
    public Collection<Companybank> getCompanybankCollection1() {
        return companybankCollection1;
    }

    public void setCompanybankCollection1(Collection<Companybank> companybankCollection1) {
        this.companybankCollection1 = companybankCollection1;
    }

    @XmlTransient
    public Collection<Crmmessagechanneltemplate> getCrmmessagechanneltemplateCollection() {
        return crmmessagechanneltemplateCollection;
    }

    public void setCrmmessagechanneltemplateCollection(Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollection) {
        this.crmmessagechanneltemplateCollection = crmmessagechanneltemplateCollection;
    }

    @XmlTransient
    public Collection<Crmmessagechanneltemplate> getCrmmessagechanneltemplateCollection1() {
        return crmmessagechanneltemplateCollection1;
    }

    public void setCrmmessagechanneltemplateCollection1(Collection<Crmmessagechanneltemplate> crmmessagechanneltemplateCollection1) {
        this.crmmessagechanneltemplateCollection1 = crmmessagechanneltemplateCollection1;
    }

    @XmlTransient
    public Collection<Crmprojectprops> getCrmprojectpropsCollection() {
        return crmprojectpropsCollection;
    }

    public void setCrmprojectpropsCollection(Collection<Crmprojectprops> crmprojectpropsCollection) {
        this.crmprojectpropsCollection = crmprojectpropsCollection;
    }

    @XmlTransient
    public Collection<Crmprojectprops> getCrmprojectpropsCollection1() {
        return crmprojectpropsCollection1;
    }

    public void setCrmprojectpropsCollection1(Collection<Crmprojectprops> crmprojectpropsCollection1) {
        this.crmprojectpropsCollection1 = crmprojectpropsCollection1;
    }

    @XmlTransient
    public Collection<Crmcampaignreceiver> getCrmcampaignreceiverCollection() {
        return crmcampaignreceiverCollection;
    }

    public void setCrmcampaignreceiverCollection(Collection<Crmcampaignreceiver> crmcampaignreceiverCollection) {
        this.crmcampaignreceiverCollection = crmcampaignreceiverCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignreceiver> getCrmcampaignreceiverCollection1() {
        return crmcampaignreceiverCollection1;
    }

    public void setCrmcampaignreceiverCollection1(Collection<Crmcampaignreceiver> crmcampaignreceiverCollection1) {
        this.crmcampaignreceiverCollection1 = crmcampaignreceiverCollection1;
    }

    @XmlTransient
    public Collection<Crmworkordersettings> getCrmworkordersettingsCollection() {
        return crmworkordersettingsCollection;
    }

    public void setCrmworkordersettingsCollection(Collection<Crmworkordersettings> crmworkordersettingsCollection) {
        this.crmworkordersettingsCollection = crmworkordersettingsCollection;
    }

    @XmlTransient
    public Collection<Productattachments> getProductattachmentsCollection() {
        return productattachmentsCollection;
    }

    public void setProductattachmentsCollection(Collection<Productattachments> productattachmentsCollection) {
        this.productattachmentsCollection = productattachmentsCollection;
    }

    @XmlTransient
    public Collection<Productattachments> getProductattachmentsCollection1() {
        return productattachmentsCollection1;
    }

    public void setProductattachmentsCollection1(Collection<Productattachments> productattachmentsCollection1) {
        this.productattachmentsCollection1 = productattachmentsCollection1;
    }

    @XmlTransient
    public Collection<Productattachments> getProductattachmentsCollection2() {
        return productattachmentsCollection2;
    }

    public void setProductattachmentsCollection2(Collection<Productattachments> productattachmentsCollection2) {
        this.productattachmentsCollection2 = productattachmentsCollection2;
    }

    @XmlTransient
    public Collection<Crmcampaignstatus> getCrmcampaignstatusCollection() {
        return crmcampaignstatusCollection;
    }

    public void setCrmcampaignstatusCollection(Collection<Crmcampaignstatus> crmcampaignstatusCollection) {
        this.crmcampaignstatusCollection = crmcampaignstatusCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignstatus> getCrmcampaignstatusCollection1() {
        return crmcampaignstatusCollection1;
    }

    public void setCrmcampaignstatusCollection1(Collection<Crmcampaignstatus> crmcampaignstatusCollection1) {
        this.crmcampaignstatusCollection1 = crmcampaignstatusCollection1;
    }

    @XmlTransient
    public Collection<Crmcampaignposition> getCrmcampaignpositionCollection() {
        return crmcampaignpositionCollection;
    }

    public void setCrmcampaignpositionCollection(Collection<Crmcampaignposition> crmcampaignpositionCollection) {
        this.crmcampaignpositionCollection = crmcampaignpositionCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignposition> getCrmcampaignpositionCollection1() {
        return crmcampaignpositionCollection1;
    }

    public void setCrmcampaignpositionCollection1(Collection<Crmcampaignposition> crmcampaignpositionCollection1) {
        this.crmcampaignpositionCollection1 = crmcampaignpositionCollection1;
    }

    @XmlTransient
    public Collection<Crmforumteammembers> getCrmforumteammembersCollection() {
        return crmforumteammembersCollection;
    }

    public void setCrmforumteammembersCollection(Collection<Crmforumteammembers> crmforumteammembersCollection) {
        this.crmforumteammembersCollection = crmforumteammembersCollection;
    }

    @XmlTransient
    public Collection<Customercontacts> getCustomercontactsCollection() {
        return customercontactsCollection;
    }

    public void setCustomercontactsCollection(Collection<Customercontacts> customercontactsCollection) {
        this.customercontactsCollection = customercontactsCollection;
    }

    @XmlTransient
    public Collection<Customercontacts> getCustomercontactsCollection1() {
        return customercontactsCollection1;
    }

    public void setCustomercontactsCollection1(Collection<Customercontacts> customercontactsCollection1) {
        this.customercontactsCollection1 = customercontactsCollection1;
    }

    @XmlTransient
    public Collection<Appointment> getAppointmentCollection() {
        return appointmentCollection;
    }

    public void setAppointmentCollection(Collection<Appointment> appointmentCollection) {
        this.appointmentCollection = appointmentCollection;
    }

    @XmlTransient
    public Collection<Appointment> getAppointmentCollection1() {
        return appointmentCollection1;
    }

    public void setAppointmentCollection1(Collection<Appointment> appointmentCollection1) {
        this.appointmentCollection1 = appointmentCollection1;
    }

    @XmlTransient
    public Collection<Appointment> getAppointmentCollection2() {
        return appointmentCollection2;
    }

    public void setAppointmentCollection2(Collection<Appointment> appointmentCollection2) {
        this.appointmentCollection2 = appointmentCollection2;
    }

    @XmlTransient
    public Collection<Appointment> getAppointmentCollection3() {
        return appointmentCollection3;
    }

    public void setAppointmentCollection3(Collection<Appointment> appointmentCollection3) {
        this.appointmentCollection3 = appointmentCollection3;
    }

    @XmlTransient
    public Collection<Companydepartment> getCompanydepartmentCollection() {
        return companydepartmentCollection;
    }

    public void setCompanydepartmentCollection(Collection<Companydepartment> companydepartmentCollection) {
        this.companydepartmentCollection = companydepartmentCollection;
    }

    @XmlTransient
    public Collection<Companydepartment> getCompanydepartmentCollection1() {
        return companydepartmentCollection1;
    }

    public void setCompanydepartmentCollection1(Collection<Companydepartment> companydepartmentCollection1) {
        this.companydepartmentCollection1 = companydepartmentCollection1;
    }

    @XmlTransient
    public Collection<Crmusermodules> getCrmusermodulesCollection() {
        return crmusermodulesCollection;
    }

    public void setCrmusermodulesCollection(Collection<Crmusermodules> crmusermodulesCollection) {
        this.crmusermodulesCollection = crmusermodulesCollection;
    }

    @XmlTransient
    public Collection<Crmusermodules> getCrmusermodulesCollection1() {
        return crmusermodulesCollection1;
    }

    public void setCrmusermodulesCollection1(Collection<Crmusermodules> crmusermodulesCollection1) {
        this.crmusermodulesCollection1 = crmusermodulesCollection1;
    }

    @XmlTransient
    public Collection<Crmexpense> getCrmexpenseCollection() {
        return crmexpenseCollection;
    }

    public void setCrmexpenseCollection(Collection<Crmexpense> crmexpenseCollection) {
        this.crmexpenseCollection = crmexpenseCollection;
    }

    @XmlTransient
    public Collection<Crmexpense> getCrmexpenseCollection1() {
        return crmexpenseCollection1;
    }

    public void setCrmexpenseCollection1(Collection<Crmexpense> crmexpenseCollection1) {
        this.crmexpenseCollection1 = crmexpenseCollection1;
    }

    @XmlTransient
    public Collection<Crmworkorder> getCrmworkorderCollection() {
        return crmworkorderCollection;
    }

    public void setCrmworkorderCollection(Collection<Crmworkorder> crmworkorderCollection) {
        this.crmworkorderCollection = crmworkorderCollection;
    }

    @XmlTransient
    public Collection<Crmworkorder> getCrmworkorderCollection1() {
        return crmworkorderCollection1;
    }

    public void setCrmworkorderCollection1(Collection<Crmworkorder> crmworkorderCollection1) {
        this.crmworkorderCollection1 = crmworkorderCollection1;
    }

    @XmlTransient
    public Collection<Crmcampaigndocs> getCrmcampaigndocsCollection() {
        return crmcampaigndocsCollection;
    }

    public void setCrmcampaigndocsCollection(Collection<Crmcampaigndocs> crmcampaigndocsCollection) {
        this.crmcampaigndocsCollection = crmcampaigndocsCollection;
    }

    @XmlTransient
    public Collection<Crmcampaigndocs> getCrmcampaigndocsCollection1() {
        return crmcampaigndocsCollection1;
    }

    public void setCrmcampaigndocsCollection1(Collection<Crmcampaigndocs> crmcampaigndocsCollection1) {
        this.crmcampaigndocsCollection1 = crmcampaigndocsCollection1;
    }

    @XmlTransient
    public Collection<Crmemployeenote> getCrmemployeenoteCollection() {
        return crmemployeenoteCollection;
    }

    public void setCrmemployeenoteCollection(Collection<Crmemployeenote> crmemployeenoteCollection) {
        this.crmemployeenoteCollection = crmemployeenoteCollection;
    }

    @XmlTransient
    public Collection<Companycontacts> getCompanycontactsCollection() {
        return companycontactsCollection;
    }

    public void setCompanycontactsCollection(Collection<Companycontacts> companycontactsCollection) {
        this.companycontactsCollection = companycontactsCollection;
    }

    @XmlTransient
    public Collection<Companycontacts> getCompanycontactsCollection1() {
        return companycontactsCollection1;
    }

    public void setCompanycontactsCollection1(Collection<Companycontacts> companycontactsCollection1) {
        this.companycontactsCollection1 = companycontactsCollection1;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Companydepartment getCompanydepartment() {
        return companydepartment;
    }

    public void setCompanydepartment(Companydepartment companydepartment) {
        this.companydepartment = companydepartment;
    }

    @XmlTransient
    public Collection<Companyemployee> getCompanyemployeeCollection() {
        return companyemployeeCollection;
    }

    public void setCompanyemployeeCollection(Collection<Companyemployee> companyemployeeCollection) {
        this.companyemployeeCollection = companyemployeeCollection;
    }

    public Companyemployee getCompanyemployee() {
        return companyemployee;
    }

    public void setCompanyemployee(Companyemployee companyemployee) {
        this.companyemployee = companyemployee;
    }

    @XmlTransient
    public Collection<Companyemployee> getCompanyemployeeCollection1() {
        return companyemployeeCollection1;
    }

    public void setCompanyemployeeCollection1(Collection<Companyemployee> companyemployeeCollection1) {
        this.companyemployeeCollection1 = companyemployeeCollection1;
    }

    public Companyemployee getCompanyemployee1() {
        return companyemployee1;
    }

    public void setCompanyemployee1(Companyemployee companyemployee1) {
        this.companyemployee1 = companyemployee1;
    }

    @XmlTransient
    public Collection<Companyproducttype> getCompanyproducttypeCollection() {
        return companyproducttypeCollection;
    }

    public void setCompanyproducttypeCollection(Collection<Companyproducttype> companyproducttypeCollection) {
        this.companyproducttypeCollection = companyproducttypeCollection;
    }

    @XmlTransient
    public Collection<Companyproducttype> getCompanyproducttypeCollection1() {
        return companyproducttypeCollection1;
    }

    public void setCompanyproducttypeCollection1(Collection<Companyproducttype> companyproducttypeCollection1) {
        this.companyproducttypeCollection1 = companyproducttypeCollection1;
    }

    @XmlTransient
    public Collection<Crmcampaign> getCrmcampaignCollection() {
        return crmcampaignCollection;
    }

    public void setCrmcampaignCollection(Collection<Crmcampaign> crmcampaignCollection) {
        this.crmcampaignCollection = crmcampaignCollection;
    }

    @XmlTransient
    public Collection<Crmcampaign> getCrmcampaignCollection1() {
        return crmcampaignCollection1;
    }

    public void setCrmcampaignCollection1(Collection<Crmcampaign> crmcampaignCollection1) {
        this.crmcampaignCollection1 = crmcampaignCollection1;
    }

    @XmlTransient
    public Collection<Crmmessagehistory> getCrmmessagehistoryCollection() {
        return crmmessagehistoryCollection;
    }

    public void setCrmmessagehistoryCollection(Collection<Crmmessagehistory> crmmessagehistoryCollection) {
        this.crmmessagehistoryCollection = crmmessagehistoryCollection;
    }

    @XmlTransient
    public Collection<Crmmessagehistory> getCrmmessagehistoryCollection1() {
        return crmmessagehistoryCollection1;
    }

    public void setCrmmessagehistoryCollection1(Collection<Crmmessagehistory> crmmessagehistoryCollection1) {
        this.crmmessagehistoryCollection1 = crmmessagehistoryCollection1;
    }

    @XmlTransient
    public Collection<Companyaccountstackdocs> getCompanyaccountstackdocsCollection() {
        return companyaccountstackdocsCollection;
    }

    public void setCompanyaccountstackdocsCollection(Collection<Companyaccountstackdocs> companyaccountstackdocsCollection) {
        this.companyaccountstackdocsCollection = companyaccountstackdocsCollection;
    }

    @XmlTransient
    public Collection<Companyaccountstackdocs> getCompanyaccountstackdocsCollection1() {
        return companyaccountstackdocsCollection1;
    }

    public void setCompanyaccountstackdocsCollection1(Collection<Companyaccountstackdocs> companyaccountstackdocsCollection1) {
        this.companyaccountstackdocsCollection1 = companyaccountstackdocsCollection1;
    }

    @XmlTransient
    public Collection<Crmscheduler> getCrmschedulerCollection() {
        return crmschedulerCollection;
    }

    public void setCrmschedulerCollection(Collection<Crmscheduler> crmschedulerCollection) {
        this.crmschedulerCollection = crmschedulerCollection;
    }

    @XmlTransient
    public Collection<Crmscheduler> getCrmschedulerCollection1() {
        return crmschedulerCollection1;
    }

    public void setCrmschedulerCollection1(Collection<Crmscheduler> crmschedulerCollection1) {
        this.crmschedulerCollection1 = crmschedulerCollection1;
    }

    @XmlTransient
    public Collection<Crmscheduler> getCrmschedulerCollection2() {
        return crmschedulerCollection2;
    }

    public void setCrmschedulerCollection2(Collection<Crmscheduler> crmschedulerCollection2) {
        this.crmschedulerCollection2 = crmschedulerCollection2;
    }

    @XmlTransient
    public Collection<Crmlabor> getCrmlaborCollection() {
        return crmlaborCollection;
    }

    public void setCrmlaborCollection(Collection<Crmlabor> crmlaborCollection) {
        this.crmlaborCollection = crmlaborCollection;
    }

    @XmlTransient
    public Collection<Crmlabor> getCrmlaborCollection1() {
        return crmlaborCollection1;
    }

    public void setCrmlaborCollection1(Collection<Crmlabor> crmlaborCollection1) {
        this.crmlaborCollection1 = crmlaborCollection1;
    }

    @XmlTransient
    public Collection<Customerbank> getCustomerbankCollection() {
        return customerbankCollection;
    }

    public void setCustomerbankCollection(Collection<Customerbank> customerbankCollection) {
        this.customerbankCollection = customerbankCollection;
    }

    @XmlTransient
    public Collection<Customerbank> getCustomerbankCollection1() {
        return customerbankCollection1;
    }

    public void setCustomerbankCollection1(Collection<Customerbank> customerbankCollection1) {
        this.customerbankCollection1 = customerbankCollection1;
    }

    @XmlTransient
    public Collection<Compnaypaymentcurrency> getCompnaypaymentcurrencyCollection() {
        return compnaypaymentcurrencyCollection;
    }

    public void setCompnaypaymentcurrencyCollection(Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollection) {
        this.compnaypaymentcurrencyCollection = compnaypaymentcurrencyCollection;
    }

    @XmlTransient
    public Collection<Compnaypaymentcurrency> getCompnaypaymentcurrencyCollection1() {
        return compnaypaymentcurrencyCollection1;
    }

    public void setCompnaypaymentcurrencyCollection1(Collection<Compnaypaymentcurrency> compnaypaymentcurrencyCollection1) {
        this.compnaypaymentcurrencyCollection1 = compnaypaymentcurrencyCollection1;
    }

    @XmlTransient
    public Collection<Crmproject> getCrmprojectCollection() {
        return crmprojectCollection;
    }

    public void setCrmprojectCollection(Collection<Crmproject> crmprojectCollection) {
        this.crmprojectCollection = crmprojectCollection;
    }

    @XmlTransient
    public Collection<Crmproject> getCrmprojectCollection1() {
        return crmprojectCollection1;
    }

    public void setCrmprojectCollection1(Collection<Crmproject> crmprojectCollection1) {
        this.crmprojectCollection1 = crmprojectCollection1;
    }

    @XmlTransient
    public Collection<Crmproject> getCrmprojectCollection2() {
        return crmprojectCollection2;
    }

    public void setCrmprojectCollection2(Collection<Crmproject> crmprojectCollection2) {
        this.crmprojectCollection2 = crmprojectCollection2;
    }

    @XmlTransient
    public Collection<Companyemployeeaddress> getCompanyemployeeaddressCollection() {
        return companyemployeeaddressCollection;
    }

    public void setCompanyemployeeaddressCollection(Collection<Companyemployeeaddress> companyemployeeaddressCollection) {
        this.companyemployeeaddressCollection = companyemployeeaddressCollection;
    }

    @XmlTransient
    public Collection<Companyemployeeaddress> getCompanyemployeeaddressCollection1() {
        return companyemployeeaddressCollection1;
    }

    public void setCompanyemployeeaddressCollection1(Collection<Companyemployeeaddress> companyemployeeaddressCollection1) {
        this.companyemployeeaddressCollection1 = companyemployeeaddressCollection1;
    }

    @XmlTransient
    public Collection<Companyemployeeaddress> getCompanyemployeeaddressCollection2() {
        return companyemployeeaddressCollection2;
    }

    public void setCompanyemployeeaddressCollection2(Collection<Companyemployeeaddress> companyemployeeaddressCollection2) {
        this.companyemployeeaddressCollection2 = companyemployeeaddressCollection2;
    }

    @XmlTransient
    public Collection<Crmforumdocs> getCrmforumdocsCollection() {
        return crmforumdocsCollection;
    }

    public void setCrmforumdocsCollection(Collection<Crmforumdocs> crmforumdocsCollection) {
        this.crmforumdocsCollection = crmforumdocsCollection;
    }

    @XmlTransient
    public Collection<Companyproduct> getCompanyproductCollection() {
        return companyproductCollection;
    }

    public void setCompanyproductCollection(Collection<Companyproduct> companyproductCollection) {
        this.companyproductCollection = companyproductCollection;
    }

    @XmlTransient
    public Collection<Companyproduct> getCompanyproductCollection1() {
        return companyproductCollection1;
    }

    public void setCompanyproductCollection1(Collection<Companyproduct> companyproductCollection1) {
        this.companyproductCollection1 = companyproductCollection1;
    }

    @XmlTransient
    public Collection<Employeedesignation> getEmployeedesignationCollection() {
        return employeedesignationCollection;
    }

    public void setEmployeedesignationCollection(Collection<Employeedesignation> employeedesignationCollection) {
        this.employeedesignationCollection = employeedesignationCollection;
    }

    @XmlTransient
    public Collection<Employeedesignation> getEmployeedesignationCollection1() {
        return employeedesignationCollection1;
    }

    public void setEmployeedesignationCollection1(Collection<Employeedesignation> employeedesignationCollection1) {
        this.employeedesignationCollection1 = employeedesignationCollection1;
    }

    @XmlTransient
    public Collection<Companyaccountstack> getCompanyaccountstackCollection() {
        return companyaccountstackCollection;
    }

    public void setCompanyaccountstackCollection(Collection<Companyaccountstack> companyaccountstackCollection) {
        this.companyaccountstackCollection = companyaccountstackCollection;
    }

    @XmlTransient
    public Collection<Companyaccountstack> getCompanyaccountstackCollection1() {
        return companyaccountstackCollection1;
    }

    public void setCompanyaccountstackCollection1(Collection<Companyaccountstack> companyaccountstackCollection1) {
        this.companyaccountstackCollection1 = companyaccountstackCollection1;
    }

    @XmlTransient
    public Collection<Crmprojectteammembers> getCrmprojectteammembersCollection() {
        return crmprojectteammembersCollection;
    }

    public void setCrmprojectteammembersCollection(Collection<Crmprojectteammembers> crmprojectteammembersCollection) {
        this.crmprojectteammembersCollection = crmprojectteammembersCollection;
    }

    @XmlTransient
    public Collection<Crmprojectteammembers> getCrmprojectteammembersCollection1() {
        return crmprojectteammembersCollection1;
    }

    public void setCrmprojectteammembersCollection1(Collection<Crmprojectteammembers> crmprojectteammembersCollection1) {
        this.crmprojectteammembersCollection1 = crmprojectteammembersCollection1;
    }

    @XmlTransient
    public Collection<Crmprojectteammembers> getCrmprojectteammembersCollection2() {
        return crmprojectteammembersCollection2;
    }

    public void setCrmprojectteammembersCollection2(Collection<Crmprojectteammembers> crmprojectteammembersCollection2) {
        this.crmprojectteammembersCollection2 = crmprojectteammembersCollection2;
    }

    @XmlTransient
    public Collection<Componentattachments> getComponentattachmentsCollection() {
        return componentattachmentsCollection;
    }

    public void setComponentattachmentsCollection(Collection<Componentattachments> componentattachmentsCollection) {
        this.componentattachmentsCollection = componentattachmentsCollection;
    }

    @XmlTransient
    public Collection<Componentattachments> getComponentattachmentsCollection1() {
        return componentattachmentsCollection1;
    }

    public void setComponentattachmentsCollection1(Collection<Componentattachments> componentattachmentsCollection1) {
        this.componentattachmentsCollection1 = componentattachmentsCollection1;
    }

    @XmlTransient
    public Collection<Crmvisitorcontactsaddress> getCrmvisitorcontactsaddressCollection() {
        return crmvisitorcontactsaddressCollection;
    }

    public void setCrmvisitorcontactsaddressCollection(Collection<Crmvisitorcontactsaddress> crmvisitorcontactsaddressCollection) {
        this.crmvisitorcontactsaddressCollection = crmvisitorcontactsaddressCollection;
    }

    @XmlTransient
    public Collection<Crmvisitorcontacts> getCrmvisitorcontactsCollection() {
        return crmvisitorcontactsCollection;
    }

    public void setCrmvisitorcontactsCollection(Collection<Crmvisitorcontacts> crmvisitorcontactsCollection) {
        this.crmvisitorcontactsCollection = crmvisitorcontactsCollection;
    }

    @XmlTransient
    public Collection<Crmvisitorcontacts> getCrmvisitorcontactsCollection1() {
        return crmvisitorcontactsCollection1;
    }

    public void setCrmvisitorcontactsCollection1(Collection<Crmvisitorcontacts> crmvisitorcontactsCollection1) {
        this.crmvisitorcontactsCollection1 = crmvisitorcontactsCollection1;
    }

    @XmlTransient
    public Collection<Companyaccountstackcd> getCompanyaccountstackcdCollection() {
        return companyaccountstackcdCollection;
    }

    public void setCompanyaccountstackcdCollection(Collection<Companyaccountstackcd> companyaccountstackcdCollection) {
        this.companyaccountstackcdCollection = companyaccountstackcdCollection;
    }

    @XmlTransient
    public Collection<Companyaccountstackcd> getCompanyaccountstackcdCollection1() {
        return companyaccountstackcdCollection1;
    }

    public void setCompanyaccountstackcdCollection1(Collection<Companyaccountstackcd> companyaccountstackcdCollection1) {
        this.companyaccountstackcdCollection1 = companyaccountstackcdCollection1;
    }

    @XmlTransient
    public Collection<Crmprojectticketmanagement> getCrmprojectticketmanagementCollection() {
        return crmprojectticketmanagementCollection;
    }

    public void setCrmprojectticketmanagementCollection(Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection) {
        this.crmprojectticketmanagementCollection = crmprojectticketmanagementCollection;
    }

    @XmlTransient
    public Collection<Crmprojectticketmanagement> getCrmprojectticketmanagementCollection1() {
        return crmprojectticketmanagementCollection1;
    }

    public void setCrmprojectticketmanagementCollection1(Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection1) {
        this.crmprojectticketmanagementCollection1 = crmprojectticketmanagementCollection1;
    }

    @XmlTransient
    public Collection<Crmprojectticketmanagement> getCrmprojectticketmanagementCollection2() {
        return crmprojectticketmanagementCollection2;
    }

    public void setCrmprojectticketmanagementCollection2(Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection2) {
        this.crmprojectticketmanagementCollection2 = crmprojectticketmanagementCollection2;
    }

    @XmlTransient
    public Collection<Crmprojectticketmanagement> getCrmprojectticketmanagementCollection3() {
        return crmprojectticketmanagementCollection3;
    }

    public void setCrmprojectticketmanagementCollection3(Collection<Crmprojectticketmanagement> crmprojectticketmanagementCollection3) {
        this.crmprojectticketmanagementCollection3 = crmprojectticketmanagementCollection3;
    }

    @XmlTransient
    public Collection<Crmprojectticketnotification> getCrmprojectticketnotificationCollection() {
        return crmprojectticketnotificationCollection;
    }

    public void setCrmprojectticketnotificationCollection(Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection) {
        this.crmprojectticketnotificationCollection = crmprojectticketnotificationCollection;
    }

    @XmlTransient
    public Collection<Crmprojectticketnotification> getCrmprojectticketnotificationCollection1() {
        return crmprojectticketnotificationCollection1;
    }

    public void setCrmprojectticketnotificationCollection1(Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection1) {
        this.crmprojectticketnotificationCollection1 = crmprojectticketnotificationCollection1;
    }

    @XmlTransient
    public Collection<Crmprojectticketnotification> getCrmprojectticketnotificationCollection2() {
        return crmprojectticketnotificationCollection2;
    }

    public void setCrmprojectticketnotificationCollection2(Collection<Crmprojectticketnotification> crmprojectticketnotificationCollection2) {
        this.crmprojectticketnotificationCollection2 = crmprojectticketnotificationCollection2;
    }

    @XmlTransient
    public Collection<Crmvisitor> getCrmvisitorCollection() {
        return crmvisitorCollection;
    }

    public void setCrmvisitorCollection(Collection<Crmvisitor> crmvisitorCollection) {
        this.crmvisitorCollection = crmvisitorCollection;
    }

    @XmlTransient
    public Collection<Crmvisitor> getCrmvisitorCollection1() {
        return crmvisitorCollection1;
    }

    public void setCrmvisitorCollection1(Collection<Crmvisitor> crmvisitorCollection1) {
        this.crmvisitorCollection1 = crmvisitorCollection1;
    }

    @XmlTransient
    public Collection<Crmworkordertype> getCrmworkordertypeCollection() {
        return crmworkordertypeCollection;
    }

    public void setCrmworkordertypeCollection(Collection<Crmworkordertype> crmworkordertypeCollection) {
        this.crmworkordertypeCollection = crmworkordertypeCollection;
    }

    @XmlTransient
    public Collection<Crmworkordertype> getCrmworkordertypeCollection1() {
        return crmworkordertypeCollection1;
    }

    public void setCrmworkordertypeCollection1(Collection<Crmworkordertype> crmworkordertypeCollection1) {
        this.crmworkordertypeCollection1 = crmworkordertypeCollection1;
    }

    @XmlTransient
    public Collection<Crmlabortype> getCrmlabortypeCollection() {
        return crmlabortypeCollection;
    }

    public void setCrmlabortypeCollection(Collection<Crmlabortype> crmlabortypeCollection) {
        this.crmlabortypeCollection = crmlabortypeCollection;
    }

    @XmlTransient
    public Collection<Crmlabortype> getCrmlabortypeCollection1() {
        return crmlabortypeCollection1;
    }

    public void setCrmlabortypeCollection1(Collection<Crmlabortype> crmlabortypeCollection1) {
        this.crmlabortypeCollection1 = crmlabortypeCollection1;
    }

    @XmlTransient
    public Collection<Crmbillingtype> getCrmbillingtypeCollection() {
        return crmbillingtypeCollection;
    }

    public void setCrmbillingtypeCollection(Collection<Crmbillingtype> crmbillingtypeCollection) {
        this.crmbillingtypeCollection = crmbillingtypeCollection;
    }

    @XmlTransient
    public Collection<Crmbillingtype> getCrmbillingtypeCollection1() {
        return crmbillingtypeCollection1;
    }

    public void setCrmbillingtypeCollection1(Collection<Crmbillingtype> crmbillingtypeCollection1) {
        this.crmbillingtypeCollection1 = crmbillingtypeCollection1;
    }

    @XmlTransient
    public Collection<Companyproductcategory> getCompanyproductcategoryCollection() {
        return companyproductcategoryCollection;
    }

    public void setCompanyproductcategoryCollection(Collection<Companyproductcategory> companyproductcategoryCollection) {
        this.companyproductcategoryCollection = companyproductcategoryCollection;
    }

    @XmlTransient
    public Collection<Companyproductcategory> getCompanyproductcategoryCollection1() {
        return companyproductcategoryCollection1;
    }

    public void setCompanyproductcategoryCollection1(Collection<Companyproductcategory> companyproductcategoryCollection1) {
        this.companyproductcategoryCollection1 = companyproductcategoryCollection1;
    }

    @XmlTransient
    public Collection<Crmprojecttask> getCrmprojecttaskCollection() {
        return crmprojecttaskCollection;
    }

    public void setCrmprojecttaskCollection(Collection<Crmprojecttask> crmprojecttaskCollection) {
        this.crmprojecttaskCollection = crmprojecttaskCollection;
    }

    @XmlTransient
    public Collection<Crmprojecttask> getCrmprojecttaskCollection1() {
        return crmprojecttaskCollection1;
    }

    public void setCrmprojecttaskCollection1(Collection<Crmprojecttask> crmprojecttaskCollection1) {
        this.crmprojecttaskCollection1 = crmprojecttaskCollection1;
    }

    @XmlTransient
    public Collection<Crmcampaignprops> getCrmcampaignpropsCollection() {
        return crmcampaignpropsCollection;
    }

    public void setCrmcampaignpropsCollection(Collection<Crmcampaignprops> crmcampaignpropsCollection) {
        this.crmcampaignpropsCollection = crmcampaignpropsCollection;
    }

    @XmlTransient
    public Collection<Crmcampaignprops> getCrmcampaignpropsCollection1() {
        return crmcampaignpropsCollection1;
    }

    public void setCrmcampaignpropsCollection1(Collection<Crmcampaignprops> crmcampaignpropsCollection1) {
        this.crmcampaignpropsCollection1 = crmcampaignpropsCollection1;
    }

    @XmlTransient
    public Collection<Customercontactsaddress> getCustomercontactsaddressCollection() {
        return customercontactsaddressCollection;
    }

    public void setCustomercontactsaddressCollection(Collection<Customercontactsaddress> customercontactsaddressCollection) {
        this.customercontactsaddressCollection = customercontactsaddressCollection;
    }

    @XmlTransient
    public Collection<Customercontactsaddress> getCustomercontactsaddressCollection1() {
        return customercontactsaddressCollection1;
    }

    public void setCustomercontactsaddressCollection1(Collection<Customercontactsaddress> customercontactsaddressCollection1) {
        this.customercontactsaddressCollection1 = customercontactsaddressCollection1;
    }

    @XmlTransient
    public Collection<Customercategory> getCustomercategoryCollection() {
        return customercategoryCollection;
    }

    public void setCustomercategoryCollection(Collection<Customercategory> customercategoryCollection) {
        this.customercategoryCollection = customercategoryCollection;
    }

    @XmlTransient
    public Collection<Customercategory> getCustomercategoryCollection1() {
        return customercategoryCollection1;
    }

    public void setCustomercategoryCollection1(Collection<Customercategory> customercategoryCollection1) {
        this.customercategoryCollection1 = customercategoryCollection1;
    }

    @XmlTransient
    public Collection<Productcomponents> getProductcomponentsCollection() {
        return productcomponentsCollection;
    }

    public void setProductcomponentsCollection(Collection<Productcomponents> productcomponentsCollection) {
        this.productcomponentsCollection = productcomponentsCollection;
    }

    @XmlTransient
    public Collection<Productcomponents> getProductcomponentsCollection1() {
        return productcomponentsCollection1;
    }

    public void setProductcomponentsCollection1(Collection<Productcomponents> productcomponentsCollection1) {
        this.productcomponentsCollection1 = productcomponentsCollection1;
    }

    @XmlTransient
    public Collection<Crmexpensetype> getCrmexpensetypeCollection() {
        return crmexpensetypeCollection;
    }

    public void setCrmexpensetypeCollection(Collection<Crmexpensetype> crmexpensetypeCollection) {
        this.crmexpensetypeCollection = crmexpensetypeCollection;
    }

    @XmlTransient
    public Collection<Crmexpensetype> getCrmexpensetypeCollection1() {
        return crmexpensetypeCollection1;
    }

    public void setCrmexpensetypeCollection1(Collection<Crmexpensetype> crmexpensetypeCollection1) {
        this.crmexpensetypeCollection1 = crmexpensetypeCollection1;
    }

    @XmlTransient
    public Collection<Companypayments> getCompanypaymentsCollection() {
        return companypaymentsCollection;
    }

    public void setCompanypaymentsCollection(Collection<Companypayments> companypaymentsCollection) {
        this.companypaymentsCollection = companypaymentsCollection;
    }

    @XmlTransient
    public Collection<Companypayments> getCompanypaymentsCollection1() {
        return companypaymentsCollection1;
    }

    public void setCompanypaymentsCollection1(Collection<Companypayments> companypaymentsCollection1) {
        this.companypaymentsCollection1 = companypaymentsCollection1;
    }

    @XmlTransient
    public Collection<Crmworkorderresolution> getCrmworkorderresolutionCollection() {
        return crmworkorderresolutionCollection;
    }

    public void setCrmworkorderresolutionCollection(Collection<Crmworkorderresolution> crmworkorderresolutionCollection) {
        this.crmworkorderresolutionCollection = crmworkorderresolutionCollection;
    }

    @XmlTransient
    public Collection<Crmworkorderresolution> getCrmworkorderresolutionCollection1() {
        return crmworkorderresolutionCollection1;
    }

    public void setCrmworkorderresolutionCollection1(Collection<Crmworkorderresolution> crmworkorderresolutionCollection1) {
        this.crmworkorderresolutionCollection1 = crmworkorderresolutionCollection1;
    }

    @XmlTransient
    public Collection<Company> getCompanyCollection() {
        return companyCollection;
    }

    public void setCompanyCollection(Collection<Company> companyCollection) {
        this.companyCollection = companyCollection;
    }

    @XmlTransient
    public Collection<Company> getCompanyCollection1() {
        return companyCollection1;
    }

    public void setCompanyCollection1(Collection<Company> companyCollection1) {
        this.companyCollection1 = companyCollection1;
    }

    @XmlTransient
    public Collection<Companyaddresstype> getCompanyaddresstypeCollection() {
        return companyaddresstypeCollection;
    }

    public void setCompanyaddresstypeCollection(Collection<Companyaddresstype> companyaddresstypeCollection) {
        this.companyaddresstypeCollection = companyaddresstypeCollection;
    }

    @XmlTransient
    public Collection<Companyaddresstype> getCompanyaddresstypeCollection1() {
        return companyaddresstypeCollection1;
    }

    public void setCompanyaddresstypeCollection1(Collection<Companyaddresstype> companyaddresstypeCollection1) {
        this.companyaddresstypeCollection1 = companyaddresstypeCollection1;
    }

    @XmlTransient
    public Collection<Crmemployeecontacts> getCrmemployeecontactsCollection() {
        return crmemployeecontactsCollection;
    }

    public void setCrmemployeecontactsCollection(Collection<Crmemployeecontacts> crmemployeecontactsCollection) {
        this.crmemployeecontactsCollection = crmemployeecontactsCollection;
    }

    @XmlTransient
    public Collection<Approval> getApprovalCollection() {
        return approvalCollection;
    }

    public void setApprovalCollection(Collection<Approval> approvalCollection) {
        this.approvalCollection = approvalCollection;
    }

    @XmlTransient
    public Collection<Approval> getApprovalCollection1() {
        return approvalCollection1;
    }

    public void setApprovalCollection1(Collection<Approval> approvalCollection1) {
        this.approvalCollection1 = approvalCollection1;
    }

    @XmlTransient
    public Collection<Approval> getApprovalCollection2() {
        return approvalCollection2;
    }

    public void setApprovalCollection2(Collection<Approval> approvalCollection2) {
        this.approvalCollection2 = approvalCollection2;
    }

    @XmlTransient
    public Collection<Workorderinstructions> getWorkorderinstructionsCollection() {
        return workorderinstructionsCollection;
    }

    public void setWorkorderinstructionsCollection(Collection<Workorderinstructions> workorderinstructionsCollection) {
        this.workorderinstructionsCollection = workorderinstructionsCollection;
    }

    @XmlTransient
    public Collection<Workorderinstructions> getWorkorderinstructionsCollection1() {
        return workorderinstructionsCollection1;
    }

    public void setWorkorderinstructionsCollection1(Collection<Workorderinstructions> workorderinstructionsCollection1) {
        this.workorderinstructionsCollection1 = workorderinstructionsCollection1;
    }

    @XmlTransient
    public Collection<Companycustomer> getCompanycustomerCollection() {
        return companycustomerCollection;
    }

    public void setCompanycustomerCollection(Collection<Companycustomer> companycustomerCollection) {
        this.companycustomerCollection = companycustomerCollection;
    }

    @XmlTransient
    public Collection<Companycustomer> getCompanycustomerCollection1() {
        return companycustomerCollection1;
    }

    public void setCompanycustomerCollection1(Collection<Companycustomer> companycustomerCollection1) {
        this.companycustomerCollection1 = companycustomerCollection1;
    }

    @XmlTransient
    public Collection<Crmforum> getCrmforumCollection() {
        return crmforumCollection;
    }

    public void setCrmforumCollection(Collection<Crmforum> crmforumCollection) {
        this.crmforumCollection = crmforumCollection;
    }

    @XmlTransient
    public Collection<Crmprojectstatus> getCrmprojectstatusCollection() {
        return crmprojectstatusCollection;
    }

    public void setCrmprojectstatusCollection(Collection<Crmprojectstatus> crmprojectstatusCollection) {
        this.crmprojectstatusCollection = crmprojectstatusCollection;
    }

    @XmlTransient
    public Collection<Crmprojectstatus> getCrmprojectstatusCollection1() {
        return crmprojectstatusCollection1;
    }

    public void setCrmprojectstatusCollection1(Collection<Crmprojectstatus> crmprojectstatusCollection1) {
        this.crmprojectstatusCollection1 = crmprojectstatusCollection1;
    }

    @XmlTransient
    public Collection<Crmschedulerevnttype> getCrmschedulerevnttypeCollection() {
        return crmschedulerevnttypeCollection;
    }

    public void setCrmschedulerevnttypeCollection(Collection<Crmschedulerevnttype> crmschedulerevnttypeCollection) {
        this.crmschedulerevnttypeCollection = crmschedulerevnttypeCollection;
    }

    @XmlTransient
    public Collection<Crmschedulerevnttype> getCrmschedulerevnttypeCollection1() {
        return crmschedulerevnttypeCollection1;
    }

    public void setCrmschedulerevnttypeCollection1(Collection<Crmschedulerevnttype> crmschedulerevnttypeCollection1) {
        this.crmschedulerevnttypeCollection1 = crmschedulerevnttypeCollection1;
    }

    @XmlTransient
    public Collection<Crmschedulerefcode> getCrmschedulerefcodeCollection() {
        return crmschedulerefcodeCollection;
    }

    public void setCrmschedulerefcodeCollection(Collection<Crmschedulerefcode> crmschedulerefcodeCollection) {
        this.crmschedulerefcodeCollection = crmschedulerefcodeCollection;
    }

    @XmlTransient
    public Collection<Crmschedulerefcode> getCrmschedulerefcodeCollection1() {
        return crmschedulerefcodeCollection1;
    }

    public void setCrmschedulerefcodeCollection1(Collection<Crmschedulerefcode> crmschedulerefcodeCollection1) {
        this.crmschedulerefcodeCollection1 = crmschedulerefcodeCollection1;
    }

    @XmlTransient
    public Collection<Componenttype> getComponenttypeCollection() {
        return componenttypeCollection;
    }

    public void setComponenttypeCollection(Collection<Componenttype> componenttypeCollection) {
        this.componenttypeCollection = componenttypeCollection;
    }

    @XmlTransient
    public Collection<Componenttype> getComponenttypeCollection1() {
        return componenttypeCollection1;
    }

    public void setComponenttypeCollection1(Collection<Componenttype> componenttypeCollection1) {
        this.componenttypeCollection1 = componenttypeCollection1;
    }

    @XmlTransient
    public Collection<Companypaymentscheme> getCompanypaymentschemeCollection() {
        return companypaymentschemeCollection;
    }

    public void setCompanypaymentschemeCollection(Collection<Companypaymentscheme> companypaymentschemeCollection) {
        this.companypaymentschemeCollection = companypaymentschemeCollection;
    }

    @XmlTransient
    public Collection<Companypaymentscheme> getCompanypaymentschemeCollection1() {
        return companypaymentschemeCollection1;
    }

    public void setCompanypaymentschemeCollection1(Collection<Companypaymentscheme> companypaymentschemeCollection1) {
        this.companypaymentschemeCollection1 = companypaymentschemeCollection1;
    }

    @XmlTransient
    public Collection<Companycontactsaddress> getCompanycontactsaddressCollection() {
        return companycontactsaddressCollection;
    }

    public void setCompanycontactsaddressCollection(Collection<Companycontactsaddress> companycontactsaddressCollection) {
        this.companycontactsaddressCollection = companycontactsaddressCollection;
    }

    @XmlTransient
    public Collection<Companycontactsaddress> getCompanycontactsaddressCollection1() {
        return companycontactsaddressCollection1;
    }

    public void setCompanycontactsaddressCollection1(Collection<Companycontactsaddress> companycontactsaddressCollection1) {
        this.companycontactsaddressCollection1 = companycontactsaddressCollection1;
    }

    @XmlTransient
    public Collection<Crmprofilesettings> getCrmprofilesettingsCollection() {
        return crmprofilesettingsCollection;
    }

    public void setCrmprofilesettingsCollection(Collection<Crmprofilesettings> crmprofilesettingsCollection) {
        this.crmprofilesettingsCollection = crmprofilesettingsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyemployeePK != null ? companyemployeePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyemployee)) {
            return false;
        }
        Companyemployee other = (Companyemployee) object;
        if ((this.companyemployeePK == null && other.companyemployeePK != null) || (this.companyemployeePK != null && !this.companyemployeePK.equals(other.companyemployeePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Companyemployee[ companyemployeePK=" + companyemployeePK + " ]";
    }

}
