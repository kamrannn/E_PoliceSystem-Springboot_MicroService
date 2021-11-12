package com.app.epolice.model.entity.crime;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Crime type.
 */
@Entity
@Data
@Table(name = "t_crime_type")
public class CrimeType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Crime type is mandatory")
    private String name;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;
}
