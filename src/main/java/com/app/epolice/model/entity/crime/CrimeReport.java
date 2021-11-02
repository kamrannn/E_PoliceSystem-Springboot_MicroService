package com.app.epolice.model.entity.crime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private boolean active;

    @ManyToMany(targetEntity = CrimeType.class,fetch = FetchType.LAZY)
    private List<CrimeType> crimeTypes = new ArrayList<>();

    public CrimeReport() {
    }

    public CrimeReport(String time, String address, String city, String possible_suspect, String description, Date createdDate, Date updatedDate, String uuid, boolean active, List<CrimeType> crimeTypes) {
        this.time = time;
        this.address = address;
        this.city = city;
        this.possible_suspect = possible_suspect;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.uuid = uuid;
        this.active = active;
        this.crimeTypes = crimeTypes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPossible_suspect() {
        return possible_suspect;
    }

    public void setPossible_suspect(String possible_suspect) {
        this.possible_suspect = possible_suspect;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<CrimeType> getCrimeTypes() {
        return crimeTypes;
    }

    public void setCrimeTypes(List<CrimeType> crimeTypes) {
        this.crimeTypes = crimeTypes;
    }
}
