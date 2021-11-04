package com.app.epolice.model.entity.crime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Criminal() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrimeStatus() {
        return crimeStatus;
    }

    public void setCrimeStatus(String status) {
        this.crimeStatus = status;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<CrimeReport> getCrimeReports() {
        return crimeReports;
    }

    public void setCrimeReports(List<CrimeReport> crimeReports) {
        this.crimeReports = crimeReports;
    }
}
