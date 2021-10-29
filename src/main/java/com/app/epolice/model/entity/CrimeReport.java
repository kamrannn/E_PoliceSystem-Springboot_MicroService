package com.app.epolice.model.entity;

import javax.persistence.*;

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
    private String createdDate;
    private String updatedDate;
    private boolean active;

    public CrimeReport() {
    }

    public CrimeReport(String time, String address, String city, String possible_suspect, String createdDate, String updatedDate, boolean active) {
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
