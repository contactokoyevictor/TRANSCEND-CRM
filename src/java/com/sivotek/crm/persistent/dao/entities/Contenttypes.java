/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NamedQuery(name = "Contenttypes.findAll", query = "SELECT c FROM Contenttypes c"),
    @NamedQuery(name = "Contenttypes.findById", query = "SELECT c FROM Contenttypes c WHERE c.id = :id"),
    @NamedQuery(name = "Contenttypes.findByContenttype", query = "SELECT c FROM Contenttypes c WHERE c.contenttype = :contenttype"),
    @NamedQuery(name = "Contenttypes.findByMinsize", query = "SELECT c FROM Contenttypes c WHERE c.minsize = :minsize"),
    @NamedQuery(name = "Contenttypes.findByMaxsize", query = "SELECT c FROM Contenttypes c WHERE c.maxsize = :maxsize")})
public class Contenttypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(length = 255)
    private String contenttype;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    private Integer minsize;
    private Integer maxsize;
    @OneToMany(mappedBy = "contenttype")
    private Collection<Productattachments> productattachmentsCollection;
    @OneToMany(mappedBy = "contenttypeid")
    private Collection<Crmcampaigndocs> crmcampaigndocsCollection;
    @OneToMany(mappedBy = "contenttype")
    private Collection<Componentattachments> componentattachmentsCollection;

    public Contenttypes() {
    }

    public Contenttypes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinsize() {
        return minsize;
    }

    public void setMinsize(Integer minsize) {
        this.minsize = minsize;
    }

    public Integer getMaxsize() {
        return maxsize;
    }

    public void setMaxsize(Integer maxsize) {
        this.maxsize = maxsize;
    }

    @XmlTransient
    public Collection<Productattachments> getProductattachmentsCollection() {
        return productattachmentsCollection;
    }

    public void setProductattachmentsCollection(Collection<Productattachments> productattachmentsCollection) {
        this.productattachmentsCollection = productattachmentsCollection;
    }

    @XmlTransient
    public Collection<Crmcampaigndocs> getCrmcampaigndocsCollection() {
        return crmcampaigndocsCollection;
    }

    public void setCrmcampaigndocsCollection(Collection<Crmcampaigndocs> crmcampaigndocsCollection) {
        this.crmcampaigndocsCollection = crmcampaigndocsCollection;
    }

    @XmlTransient
    public Collection<Componentattachments> getComponentattachmentsCollection() {
        return componentattachmentsCollection;
    }

    public void setComponentattachmentsCollection(Collection<Componentattachments> componentattachmentsCollection) {
        this.componentattachmentsCollection = componentattachmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contenttypes)) {
            return false;
        }
        Contenttypes other = (Contenttypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.Contenttypes[ id=" + id + " ]";
    }

}
