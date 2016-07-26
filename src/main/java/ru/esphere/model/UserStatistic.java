package ru.esphere.model;

import java.util.Date;

public class UserStatistic {
    private Long id;
    private Long userId;
    private Date actionTime;
    private String action;
    private String description;

    public UserStatistic(Long userId, Date actionTime, String action, String description) {
        this.userId = userId;
        this.actionTime = actionTime;
        this.action = action;
        this.description = description;
    }

    public UserStatistic(Long id, Long userId, Date actionTime, String action, String description) {
        this.id = id;
        this.userId = userId;
        this.actionTime = actionTime;
        this.action = action;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
