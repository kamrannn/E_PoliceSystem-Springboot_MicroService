package com.app.epolice.model.entity.policestation;

import com.app.epolice.model.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Investigation team.
 */
@Entity
@Data
@Table(name = "t_investigation_team", indexes = {
        @Index(name = "created_date_index", columnList = "createdDate"),
        @Index(name = "active_index", columnList = "active"),
        @Index(name = "operation_ity_index", columnList = "operationCity"),
        @Index(name = "deadline_date_index", columnList = "deadlineDate")
})
public class InvestigationTeam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Team name is mandatory")
    @Column(unique = true)
    private String name;
    @NotBlank(message = "Team goal is mandatory")
    private String goal;
    @NotBlank(message = "Team Operation city is mandatory")
    private String operationCity;
    @NotBlank(message = "Team total members is mandatory")
    private int totalMembers;
    private java.sql.Date deadlineDate;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    private List<User> userList = new ArrayList<>();
}
