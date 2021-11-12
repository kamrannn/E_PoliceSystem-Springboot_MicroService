package com.app.epolice.model.entity.rooms;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Room type.
 */
@Entity
@Data
@Table(name = "t_room_type")
public class RoomType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Room type is mandatory")
    private String type;
    private String description;
    @Column(nullable = false)
    private Date createdDate;
    private Date updatedDate;
    private boolean active;
}
