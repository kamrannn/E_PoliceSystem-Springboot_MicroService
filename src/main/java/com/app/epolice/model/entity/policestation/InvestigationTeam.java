package com.app.epolice.model.entity.policestation;

import com.app.epolice.model.entity.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Investigation team.
 */
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

    /**
     * Instantiates a new Investigation team.
     */
    public InvestigationTeam() {
    }

    /**
     * Instantiates a new Investigation team.
     *
     * @param head          the head
     * @param goal          the goal
     * @param operationCity the operation city
     * @param totalMembers  the total members
     * @param deadlineDate  the deadline date
     * @param createdDate   the created date
     * @param updatedDate   the updated date
     * @param active        the active
     * @param userList      the user list
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets head.
     *
     * @return the head
     */
    public String getHead() {
        return head;
    }

    /**
     * Sets head.
     *
     * @param head the head
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * Gets goal.
     *
     * @return the goal
     */
    public String getGoal() {
        return goal;
    }

    /**
     * Sets goal.
     *
     * @param goal the goal
     */
    public void setGoal(String goal) {
        this.goal = goal;
    }

    /**
     * Gets operation city.
     *
     * @return the operation city
     */
    public String getOperationCity() {
        return operationCity;
    }

    /**
     * Sets operation city.
     *
     * @param operationCity the operation city
     */
    public void setOperationCity(String operationCity) {
        this.operationCity = operationCity;
    }

    /**
     * Gets total members.
     *
     * @return the total members
     */
    public int getTotalMembers() {
        return totalMembers;
    }

    /**
     * Sets total members.
     *
     * @param totalMembers the total members
     */
    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    /**
     * Gets user list.
     *
     * @return the user list
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Sets user list.
     *
     * @param userList the user list
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * Gets deadline date.
     *
     * @return the deadline date
     */
    public java.sql.Date getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Sets deadline date.
     *
     * @param deadlineDate the deadline date
     */
    public void setDeadlineDate(java.sql.Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets updated date.
     *
     * @return the updated date
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate the updated date
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
