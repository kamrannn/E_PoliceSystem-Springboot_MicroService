package com.app.epolice.model.entity.policestation;

import com.app.epolice.model.entity.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_investigation_team")
public class InvestigationTeam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String head;
    private String goal;
    private String operationCity;
    private int totalMembers;
    private java.sql.Date deadlineDate;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

    @ManyToMany(targetEntity = User.class,fetch = FetchType.LAZY)
    private List<User> userList = new ArrayList<>();

    public InvestigationTeam() {
    }

    public InvestigationTeam(String head, String goal, String operationCity, int totalMembers, java.sql.Date deadlineDate, Date createdDate, Date updatedDate, boolean active, List<User> userList) {
        this.head = head;
        this.goal = goal;
        this.operationCity = operationCity;
        this.totalMembers = totalMembers;
        this.deadlineDate = deadlineDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
        this.userList = userList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getOperationCity() {
        return operationCity;
    }

    public void setOperationCity(String operationCity) {
        this.operationCity = operationCity;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public java.sql.Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(java.sql.Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
