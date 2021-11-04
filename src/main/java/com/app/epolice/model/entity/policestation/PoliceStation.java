package com.app.epolice.model.entity.policestation;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.model.entity.crime.CrimeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public PoliceStation() {
    }

    public PoliceStation(String name, String city, String address, Date createdDate, Date updatedDate, boolean active) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

/*    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }*/
}
