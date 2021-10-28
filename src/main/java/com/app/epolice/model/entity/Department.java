package com.app.epolice.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_police_departments")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true)
    private String name;
    private String createdDate;
    private String updatedDate;
    private boolean active;
}
