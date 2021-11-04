package com.app.epolice.model.entity.user;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.model.entity.policestation.Department;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true)
    private String phoneNo;
    private String dob;
    private String gender;
    @Column(unique = true)
    private String cnic;
    @Column(nullable = false)
    private String password;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;
    private String smsToken;
    private String emailToken;

    /**
     * One user can have multiple roles, and one role can have multiple users
     */
    @ManyToMany(targetEntity = Role.class,fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Role> roles = new ArrayList<>();

    /**
     * One user can have multiple reports
     */
    @OneToMany(targetEntity = CrimeReport.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<CrimeReport> crimeReports = new ArrayList<>();

    /**
     * One department will have multiple users while one user will be limited to 1 department
     */
    @ManyToOne(targetEntity = Department.class,fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Department department;

    public User() {
    }

    public User(String firstName, String lastName, String email, String phoneNo, String dob, String gender,
                String cnic, String password, Date createdDate, Date updatedDate, boolean active,
                String smsToken, String emailToken, List<Role> roles,Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.dob = dob;
        this.gender = gender;
        this.cnic = cnic;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
        this.smsToken = smsToken;
        this.emailToken = emailToken;
        this.roles = roles;
        this.department= department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getSmsToken() {
        return smsToken;
    }

    public void setSmsToken(String smsToken) {
        this.smsToken = smsToken;
    }

    public String getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<CrimeReport> getCrimeReports() {
        return crimeReports;
    }

    public void setCrimeReports(List<CrimeReport> crimeReports) {
        this.crimeReports = crimeReports;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
