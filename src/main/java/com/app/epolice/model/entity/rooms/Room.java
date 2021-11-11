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
@Table(name = "t_room")
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
}
