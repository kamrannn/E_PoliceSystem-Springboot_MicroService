package com.app.epolice.model.entity.crime;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Criminal.
 */
@Entity
@Data
@Table(name = "t_criminal")
public class Criminal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Criminal name is mandatory")
    private String name;
    @NotBlank(message = "Criminal cnic is mandatory")
    private String cnic;
    @NotNull(message = "Criminal age is mandatory")
    private int age;
    @NotBlank(message = "Criminal city is mandatory")
    private String city;
    @NotBlank(message = "Criminal address is mandatory")
    private String address;
    private String crimeStatus;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    @ManyToMany(targetEntity = CrimeReport.class,fetch = FetchType.LAZY)
    private List<CrimeReport> crimeReports = new ArrayList<>();
}
