package com.app.epolice.model.entity.policestation;

import com.app.epolice.model.entity.crime.CrimeReport;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Police station.
 */
@Entity
@Data
@Table(name = "t_police_station", indexes = {
        @Index(name = "created_date_index", columnList = "createdDate"),
        @Index(name = "active_index", columnList = "active"),
        @Index(name = "address_index", columnList = "address"),
        @Index(name = "city_index", columnList = "city")
})
public class PoliceStation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Police station name is mandatory")
    private String name;
    @NotBlank(message = "Police station city is mandatory")
    private String city;
    @NotBlank(message = "Police station address is mandatory")
    private String address;
    @Column(nullable = false)
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    /**
     * One Police can have multiple reports, but 1 report will be specific to single police station
     */
    @OneToMany(targetEntity = CrimeReport.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "police_station_id", referencedColumnName = "id")
    private List<CrimeReport> crimeReports = new ArrayList<>();

}
