package com.app.epolice.model.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "t_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String createdDate;
    private String updatedDate;
    private boolean status;
}
