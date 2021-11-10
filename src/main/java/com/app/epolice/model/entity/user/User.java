package com.app.epolice.model.entity.user;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.model.entity.policestation.Department;
import com.app.epolice.model.entity.policestation.PoliceStation;
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
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Column(unique = true)
    @Size(min=11, max=14)
    private String phoneNo;
    @NotBlank(message = "Date of birth is mandatory")
    private String dob;
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    @Column(unique = true)
    @NotBlank(message = "Cnic is mandatory")
    @Size(min=13, max=15)
    private String cnic;
    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
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

    /**
     * One Police station will have multiple users while one user will be limited to 1 police station
     */
    @ManyToOne(targetEntity = PoliceStation.class,fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private PoliceStation policeStation;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param email       the email
     * @param phoneNo     the phone no
     * @param dob         the dob
     * @param gender      the gender
     * @param cnic        the cnic
     * @param password    the password
     * @param createdDate the created date
     * @param updatedDate the updated date
     * @param active      the active
     * @param smsToken    the sms token
     * @param emailToken  the email token
     * @param roles       the roles
     * @param department  the department
     */
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
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone no.
     *
     * @return the phone no
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets phone no.
     *
     * @param phoneNo the phone no
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Gets dob.
     *
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets dob.
     *
     * @param dob the dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Gets sms token.
     *
     * @return the sms token
     */
    public String getSmsToken() {
        return smsToken;
    }

    /**
     * Sets sms token.
     *
     * @param smsToken the sms token
     */
    public void setSmsToken(String smsToken) {
        this.smsToken = smsToken;
    }

    /**
     * Gets email token.
     *
     * @return the email token
     */
    public String getEmailToken() {
        return emailToken;
    }

    /**
     * Sets email token.
     *
     * @param emailToken the email token
     */
    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    /**
     * Gets department.
     *
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets department.
     *
     * @param department the department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Gets police station.
     *
     * @return the police station
     */
    public PoliceStation getPoliceStation() {
        return policeStation;
    }

    /**
     * Sets police station.
     *
     * @param policeStation the police station
     */
    public void setPoliceStation(PoliceStation policeStation) {
        this.policeStation = policeStation;
    }
}
