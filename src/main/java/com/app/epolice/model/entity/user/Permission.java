package com.app.epolice.model.entity.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Permission.
 */
@Data
@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotBlank(message = "Kindly enter the name")
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;
}
