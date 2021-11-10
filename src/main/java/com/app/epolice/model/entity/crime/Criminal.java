package com.app.epolice.model.entity.crime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Criminal.
 */
@Entity
@Table(name = "t_criminal")
public class Criminal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cnic;
    private String age;
    private String city;
    private String address;
    private String crimeStatus;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    @ManyToMany(targetEntity = CrimeReport.class,fetch = FetchType.LAZY)
    private List<CrimeReport> crimeReports = new ArrayList<>();

    /**
     * Instantiates a new Criminal.
     */
    public Criminal() {
    }

    /**
     * Instantiates a new Criminal.
     *
     * @param name         the name
     * @param cnic         the cnic
     * @param age          the age
     * @param city         the city
     * @param address      the address
     * @param crimeStatus  the crime status
     * @param createdDate  the created date
     * @param updatedDate  the updated date
     * @param active       the active
     * @param crimeReports the crime reports
     */
    public Criminal(String name, String cnic, String age, String city, String address, String crimeStatus, Date createdDate, Date updatedDate, boolean active, List<CrimeReport> crimeReports) {
        this.name = name;
        this.cnic = cnic;
        this.age = age;
        this.city = city;
        this.address = address;
        this.crimeStatus = crimeStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
        this.crimeReports = crimeReports;
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
     * Gets cnic.
     *
     * @return the cnic
     */
    public String getCnic() {
        return cnic;
    }

    /**
     * Sets cnic.
     *
     * @param cnic the cnic
     */
    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(String age) {
        this.age = age;
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
     * Gets crime status.
     *
     * @return the crime status
     */
    public String getCrimeStatus() {
        return crimeStatus;
    }

    /**
     * Sets crime status.
     *
     * @param status the status
     */
    public void setCrimeStatus(String status) {
        this.crimeStatus = status;
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
