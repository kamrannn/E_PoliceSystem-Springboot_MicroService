package com.app.epolice.model.entity.rooms;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Room.
 */
@Entity
@Data
@Table(name = "t_room", indexes = {
        @Index(name = "created_date_index", columnList = "createdDate"),
        @Index(name = "active_index", columnList = "active"),
        @Index(name = "location_index", columnList = "location")
})
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Room number is mandatory")
    private String number;
    @Column(nullable = false)
    @NotBlank(message = "Room location is mandatory")
    private String location;
    @Column(nullable = false)
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    /**
     * One room will have one room type while one room type can have multiple rooms
     */
    @ManyToOne(targetEntity = RoomType.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private RoomType roomType;
}
