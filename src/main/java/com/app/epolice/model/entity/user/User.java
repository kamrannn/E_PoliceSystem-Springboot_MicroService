package com.app.epolice.model.entity.user;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.model.entity.policestation.Department;
import com.app.epolice.model.entity.policestation.PoliceStation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type User.
 */
@Data
@Entity
@Table(name = "t_user", indexes = {
        @Index(name = "created_date_index", columnList = "createdDate"),
        @Index(name = "active_index", columnList = "active")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    @Column(unique = true)
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Column(unique = true)
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 11, max = 14, message = "Minimum size should be 11 and maximum size should be 14")
    private String phoneNo;
    @NotBlank(message = "Date of birth is mandatory")
    private String dob;
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    @Column(unique = true)
    @NotBlank(message = "Cnic is mandatory")
    @Size(min = 13, max = 15, message = "Minimum size should be 13 and maximum size should be 15")
    private String cnic;
    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;
    @JsonIgnore
    private String smsToken;
    @JsonIgnore
    private String emailToken;

    /**
     * One user can have multiple roles, and one role can have multiple users
     */
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Role> roles = new ArrayList<>();

    /**
     * One user can have multiple reports
     */
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(targetEntity = CrimeReport.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<CrimeReport> crimeReports = new ArrayList<>();

    /**
     * One department will have multiple users while one user will be limited to 1 department
     */
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Department department;

    /**
     * One Police station will have multiple users while one user will be limited to 1 police station
     */
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = PoliceStation.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private PoliceStation policeStation;
}
