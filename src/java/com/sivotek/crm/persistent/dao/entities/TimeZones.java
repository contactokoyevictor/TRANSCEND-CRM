/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "TimeZones.findAll", query = "SELECT t FROM TimeZones t"),
    @NamedQuery(name = "TimeZones.findByTimezoneid", query = "SELECT t FROM TimeZones t WHERE t.timezoneid = :timezoneid"),
    @NamedQuery(name = "TimeZones.findByCountryCode", query = "SELECT t FROM TimeZones t WHERE t.countryCode = :countryCode"),
    @NamedQuery(name = "TimeZones.findByCoordinates", query = "SELECT t FROM TimeZones t WHERE t.coordinates = :coordinates"),
    @NamedQuery(name = "TimeZones.findByTimeZone", query = "SELECT t FROM TimeZones t WHERE t.timeZone = :timeZone"),
    @NamedQuery(name = "TimeZones.findByComments", query = "SELECT t FROM TimeZones t WHERE t.comments = :comments"),
    @NamedQuery(name = "TimeZones.findByUTCoffset", query = "SELECT t FROM TimeZones t WHERE t.uTCoffset = :uTCoffset"),
    @NamedQuery(name = "TimeZones.findByUTCDSToffset", query = "SELECT t FROM TimeZones t WHERE t.uTCDSToffset = :uTCDSToffset"),
    @NamedQuery(name = "TimeZones.findByNotes", query = "SELECT t FROM TimeZones t WHERE t.notes = :notes")})
public class TimeZones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer timezoneid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(nullable = false, length = 6)
    private String countryCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(nullable = false, length = 15)
    private String coordinates;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String timeZone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 85)
    @Column(nullable = false, length = 85)
    private String comments;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(nullable = false, length = 8)
    private String uTCoffset;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(nullable = false, length = 8)
    private String uTCDSToffset;
    @Size(max = 79)
    @Column(length = 79)
    private String notes;

    public TimeZones() {
    }

    public TimeZones(Integer timezoneid) {
        this.timezoneid = timezoneid;
    }

    public TimeZones(Integer timezoneid, String countryCode, String coordinates, String timeZone, String comments, String uTCoffset, String uTCDSToffset) {
        this.timezoneid = timezoneid;
        this.countryCode = countryCode;
        this.coordinates = coordinates;
        this.timeZone = timeZone;
        this.comments = comments;
        this.uTCoffset = uTCoffset;
        this.uTCDSToffset = uTCDSToffset;
    }

    public Integer getTimezoneid() {
        return timezoneid;
    }

    public void setTimezoneid(Integer timezoneid) {
        this.timezoneid = timezoneid;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUTCoffset() {
        return uTCoffset;
    }

    public void setUTCoffset(String uTCoffset) {
        this.uTCoffset = uTCoffset;
    }

    public String getUTCDSToffset() {
        return uTCDSToffset;
    }

    public void setUTCDSToffset(String uTCDSToffset) {
        this.uTCDSToffset = uTCDSToffset;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timezoneid != null ? timezoneid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimeZones)) {
            return false;
        }
        TimeZones other = (TimeZones) object;
        if ((this.timezoneid == null && other.timezoneid != null) || (this.timezoneid != null && !this.timezoneid.equals(other.timezoneid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.crm.persistent.dao.entities.TimeZones[ timezoneid=" + timezoneid + " ]";
    }

}
