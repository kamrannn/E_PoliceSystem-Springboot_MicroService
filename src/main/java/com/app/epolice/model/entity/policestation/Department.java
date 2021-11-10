package com.app.epolice.model.entity.policestation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Department.
 */
@Entity
@Table(name = "t_police_department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    /**
     * Instantiates a new Department.
     */
    public Department() {
    }

    /**
     * Instantiates a new Department.
     *
     * @param name        the name
     * @param createdDate the created date
     * @param updatedDate the updated date
     * @param active      the active
     */
    public Department(String name, Date createdDate, Date updatedDate, boolean active) {
        this.name = name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
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
}