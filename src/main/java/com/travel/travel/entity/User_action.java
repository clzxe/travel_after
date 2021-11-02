package com.travel.travel.entity;

import java.util.Date;

public class User_action implements Comparable<User_action>{
    private Integer userId;

    private Integer lineId;

    private Integer userAction;

    private Integer themeId;

    private Date time;

    @Override
    public int compareTo(User_action o) {
        return userAction > o.userAction ? -1 : 1;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getUserAction() {
        return userAction;
    }

    public void setUserAction(Integer userAction) {
        this.userAction = userAction;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}