package com.app.epolice.model.entity.crime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Crime report.
 */
@Entity
@Table(name = "t_crime_report")
public class CrimeReport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    private String address;
    private String city;
    private String possible_suspect;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    private String uuid;
    private String status;
    private boolean active;

    @ManyToMany(targetEntity = CrimeType.class,fetch = FetchType.LAZY)
    private List<CrimeType> crimeTypes = new ArrayList<>();

    /**
     * Instantiates a new Crime report.
     */
    public CrimeReport() {
    }

    /**
     * Instantiates a new Crime report.
     *
     * @param time             the time
     * @param address          the address
     * @param city             the city
     * @param possible_suspect the possible suspect
     * @param description      the description
     * @param createdDate      the created date
     * @param updatedDate      the updated date
     * @param uuid             the uuid
     * @param status           the status
     * @param active           the active
     * @param crimeTypes       the crime types
     */
    public CrimeReport(String time, String address, String city, String possible_suspect, String description, Date createdDate, Date updatedDate, String uuid, String status, boolean active, List<CrimeType> crimeTypes) {
        this.time = time;
        this.address = address;
        this.city = city;
        this.possible_suspect = possible_suspect;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.uuid = uuid;
        this.status = status;
        this.active = active;
        this.crimeTypes = crimeTypes;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(String time) {
        this.time = time;
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
     * Gets possible suspect.
     *
     * @return the possible suspect
     */
    public String getPossible_suspect() {
        return possible_suspect;
    }

    /**
     * Sets possible suspect.
     *
     * @param possible_suspect the possible suspect
     */
    public void setPossible_suspect(String possible_suspect) {
        this.possible_suspect = possible_suspect;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets uuid.
     *
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets uuid.
     *
     * @param uuid the uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets crime types.
     *
     * @return the crime types
     */
    public List<CrimeType> getCrimeTypes() {
        return crimeTypes;
    }

    /**
     * Sets crime types.
     *
     * @param crimeTypes the crime types
     */
    public void setCrimeTypes(List<CrimeType> crimeTypes) {
        this.crimeTypes = crimeTypes;
    }

}
