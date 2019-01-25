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
    @NamedQuery(name = "Crmworkorderresolution.findAll", query = "SELECT c FROM Crmworkorderresolution c"),
    @NamedQuery(name = "Crmworkorderresolution.findById", query = "SELECT c FROM Crmworkorderresolution c WHERE c.crmworkorderresolutionPK.id = :id"),
    @NamedQuery(name = "Crmworkorderresolution.findByPubkey", query = "SELECT c FROM Crmworkorderresolution c WHERE c.crmworkorderresolutionPK.pubkey = :pubkey"),
    @NamedQuery(name = "Crmworkorderresolution.findByCreateddate", query = "SELECT c FROM Crmworkorderresolution c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Crmworkorderresolution.findByCreatedfrom", query = "SELECT c FROM Crmworkorderresolution c WHERE c.createdfrom = :createdfrom"),
    @NamedQuery(name = "Crmworkorderresolution.findByChangeddate", query = "SELECT c FROM Crmworkorderresolution c WHERE c.changeddate = :changeddate"),
    @NamedQuery(name = "Crmworkorderresolution.findByChangedfrom", query = "SELECT c FROM Crmworkorderresolution c WHERE c.changedfrom = :changedfrom")})
public class Crmworkorderresolution implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrmworkorderresolutionPK crmworkorderresolutionPK;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String workperformed;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String specialinstruction;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String customersfailure;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String correctiveaction;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String failurelocation;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String failedassembly;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String howfixed;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String symptom;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String rootcause;
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
        @JoinColumn(name = "WORKORDERID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmworkorder crmworkorder;
    @JoinColumns({
        @JoinColumn(name = "BILLINTYPEID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmbillingtype crmbillingtype;
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

    public Crmworkorderresolution() {
    }

    public Crmworkorderresolution(CrmworkorderresolutionPK crmworkorderresolutionPK) {
        this.crmworkorderresolutionPK = crmworkorderresolutionPK;
    }

    public Crmworkorderresolution(int id, int pubkey) {
        this.crmworkorderresolutionPK = new CrmworkorderresolutionPK(id, pubkey);
    }

    public CrmworkorderresolutionPK getCrmworkorderresolutionPK() {
        return crmworkorderresolutionPK;
    }

    public void setCrmworkorderresolutionPK(CrmworkorderresolutionPK crmworkorderresolutionPK) {
        this.crmworkorderresolutionPK = crmworkorderresolutionPK;
    }

    public String getWorkperformed() {
        return workperformed;
    }

    public void setWorkperformed(String workperformed) {
        this.workperformed = workperformed;
    }

    public String getSpecialinstruction() {
        return specialinstruction;
    }

    public void setSpecialinstruction(String specialinstruction) {
        this.specialinstruction = specialinstruction;
    }

    public String getCustomersfailure() {
        return customersfailure;
    }

    public void setCustomersfailure(String customersfailure) {
        this.customersfailure = customersfailure;
    }

    public String getCorrectiveaction() {
        return correctiveaction;
    }

    public void setCorrectiveaction(String correctiveaction) {
        this.correctiveaction = correctiveaction;
    }

    public String getFailurelocation() {
        return failurelocation;
    }

    public void setFailurelocation(String failurelocation) {
        this.failurelocation = failurelocation;
    }

    public String getFailedassembly() {
        return failedassembly;
    }

    public void setFailedassembly(String failedassembly) {
        this.failedassembly = failedassembly;
    }

    public String getHowfixed() {
        return howfixed;
    }

    public void setHowfixed(String howfixed) {
        this.howfixed = howfixed;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getRootcause() {
        return rootcause;
    }

    public void setRootcause(String rootcause) {
        this.rootcause = rootcause;
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

    public Crmworkorder getCrmworkorder() {
        return crmworkorder;
    }

    public void setCrmworkorder(Crmworkorder crmworkorder) {
        this.crmworkorder = crmworkorder;
    }

    public Crmbillingtype getCrmbillingtype() {
        return crmbillingtype;
    }

    public void setCrmbillingtype(Crmbillingtype crmbillingtype) {
        this.crmbillingtype = crmbillingtype;
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
        hash += (crmworkorderresolutionPK != null ? crmworkorderresolutionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crmworkorderresolution)) {
            return false;
        }
        Crmworkorderresolution other = (Crmworkorderresolution) object;
        if ((this.crmworkorderresolutionPK == null && other.crmworkorderresolutionPK != null) || (this.crmworkorderresolutionPK != null && !this.crmworkorderresolutionPK.equals(other.crmworkorderresolutionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Crmworkorderresolution[ crmworkorderresolutionPK=" + crmworkorderresolutionPK + " ]";
    }

}
