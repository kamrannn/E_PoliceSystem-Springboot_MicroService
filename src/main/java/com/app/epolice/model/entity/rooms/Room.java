package com.app.epolice.model.entity.rooms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Room.
 */
@Entity
@Table(name = "t_room")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String number;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private Date createdDate;
    private Date updatedDate;

    /**
     * Instantiates a new Room.
     */
    public Room() {
    }

    /**
     * Instantiates a new Room.
     *
     * @param number      the number
     * @param location    the location
     * @param createdDate the created date
     * @param updatedDate the updated date
     */
    public Room(String number, String location, Date createdDate, Date updatedDate) {
        this.number = number;
        this.location = location;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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
     * Gets number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
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
}
