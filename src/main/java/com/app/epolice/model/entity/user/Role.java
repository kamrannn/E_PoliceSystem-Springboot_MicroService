package com.app.epolice.model.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "t_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    @ManyToMany(targetEntity = Permission.class,fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Permission> permissions = new ArrayList<>();
}
