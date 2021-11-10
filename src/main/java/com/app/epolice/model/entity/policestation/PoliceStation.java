package com.app.epolice.model.entity.policestation;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.model.entity.user.User;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Police station.
 */
@Entity
@Table(name = "t_police_station")
public class PoliceStation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String city;
    private String address;
    @Column(nullable = false)
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    /**
     * One Police can have multiple reports, but 1 report will be specific to single police station
     */
    @OneToMany(targetEntity = CrimeReport.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "police_station_id", referencedColumnName = "id")
    private List<CrimeReport> crimeReports = new ArrayList<>();

    /**
     * Instantiates a new Police station.
     */
    public PoliceStation() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets crime reports.
     *
     * @return the crime reports
     */
    public List<CrimeReport> getCrimeReports() {
        return crimeReports;
    }

    /**
     * Sets crime reports.
     *
     * @param crimeReports the crime reports
     */
    public void setCrimeReports(List<CrimeReport> crimeReports) {
        this.crimeReports = crimeReports;
    }
}
