package com.app.epolice.model.entity.policestation;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Department.
 */
@Entity
@Data
@Table(name = "t_police_department", indexes = {
        @Index(name = "created_date_index", columnList = "createdDate"),
        @Index(name = "active_index", columnList = "active")
})
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotBlank(message = "Department name is mandatory")
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;
}