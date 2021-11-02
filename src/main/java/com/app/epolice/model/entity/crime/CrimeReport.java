package com.app.epolice.model.entity.crime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_crime_report")
public class CrimeReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    private String address;
    private String city;
    private String possible_suspect;
    private String description;
    @Column(nullable = false)
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    public CrimeReport() {
    }

    public CrimeReport(String time, String address, String city, String possible_suspect, Date createdDate, Date updatedDate, boolean active) {
        this.time = time;
        this.address = address;
        this.city = city;
        this.possible_suspect = possible_suspect;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
