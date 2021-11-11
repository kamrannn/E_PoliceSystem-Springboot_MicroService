package com.app.epolice.model.entity.crime;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Crime report.
 */
@Entity
@Data
@Table(name = "t_crime_report")
public class CrimeReport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Crime time is mandatory")
    private String time;
    @NotBlank(message = "Crime address time is mandatory")
    private String address;
    @NotBlank(message = "Crime city time is mandatory")
    private String city;
    private String possible_suspect;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    private String uuid;
    private String status;
    private boolean active;

    @ManyToMany(targetEntity = CrimeType.class,fetch = FetchType.LAZY)
    private List<CrimeType> crimeTypes = new ArrayList<>();
}
