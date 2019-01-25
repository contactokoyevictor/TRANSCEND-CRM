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
    @NamedQuery(name = "Workorderinstructions.findAll", query = "SELECT w FROM Workorderinstructions w"),
    @NamedQuery(name = "Workorderinstructions.findById", query = "SELECT w FROM Workorderinstructions w WHERE w.workorderinstructionsPK.id = :id"),
    @NamedQuery(name = "Workorderinstructions.findByPubkey", query = "SELECT w FROM Workorderinstructions w WHERE w.workorderinstructionsPK.pubkey = :pubkey"),
    @NamedQuery(name = "Workorderinstructions.findByIsDone", query = "SELECT w FROM Workorderinstructions w WHERE w.isDone = :isDone"),
    @NamedQuery(name = "Workorderinstructions.findByCreateddate", query = "SELECT w FROM Workorderinstructions w WHERE w.createddate = :createddate"),
    @NamedQuery(name = "Workorderinstructions.findByCreatedfrom", query = "SELECT w FROM Workorderinstructions w WHERE w.createdfrom = :createdfrom"),
    @NamedQuery(name = "Workorderinstructions.findByChangeddate", query = "SELECT w FROM Workorderinstructions w WHERE w.changeddate = :changeddate"),
    @NamedQuery(name = "Workorderinstructions.findByChangedfrom", query = "SELECT w FROM Workorderinstructions w WHERE w.changedfrom = :changedfrom")})
public class Workorderinstructions implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WorkorderinstructionsPK workorderinstructionsPK;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String instruction;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String comment;
    private Boolean isDone;
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
        @JoinColumn(name = "CREATEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee;
    @JoinColumns({
        @JoinColumn(name = "WORKORDERID", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Crmworkorder crmworkorder;
    @JoinColumns({
        @JoinColumn(name = "CHANGEDBY", referencedColumnName = "ID"),
        @JoinColumn(name = "pubkey", referencedColumnName = "pubkey", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Companyemployee companyemployee1;

    public Workorderinstructions() {
    }

    public Workorderinstructions(WorkorderinstructionsPK workorderinstructionsPK) {
        this.workorderinstructionsPK = workorderinstructionsPK;
    }

    public Workorderinstructions(int id, int pubkey) {
        this.workorderinstructionsPK = new WorkorderinstructionsPK(id, pubkey);
    }

    public WorkorderinstructionsPK getWorkorderinstructionsPK() {
        return workorderinstructionsPK;
    }

    public void setWorkorderinstructionsPK(WorkorderinstructionsPK workorderinstructionsPK) {
        this.workorderinstructionsPK = workorderinstructionsPK;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
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

    public Companyemployee getCompanyemployee() {
        return companyemployee;
    }

    public void setCompanyemployee(Companyemployee companyemployee) {
        this.companyemployee = companyemployee;
    }

    public Crmworkorder getCrmworkorder() {
        return crmworkorder;
    }

    public void setCrmworkorder(Crmworkorder crmworkorder) {
        this.crmworkorder = crmworkorder;
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
        hash += (workorderinstructionsPK != null ? workorderinstructionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Workorderinstructions)) {
            return false;
        }
        Workorderinstructions other = (Workorderinstructions) object;
        if ((this.workorderinstructionsPK == null && other.workorderinstructionsPK != null) || (this.workorderinstructionsPK != null && !this.workorderinstructionsPK.equals(other.workorderinstructionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Workorderinstructions[ workorderinstructionsPK=" + workorderinstructionsPK + " ]";
    }

}
