package com.travel.travel.entity;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private Integer userId;

    private String userMail;

    private String userAccount;

    private String userPassword;

    private String userName;

    private String userTel;

    private String userHead;

    private Integer isLock;

    private Integer isDelete;

    private Double userAmount;

    public List<User_action> movieList = new ArrayList<>();

    public List<User_action> getMovieList() {
        return movieList;
    }

    public User_action find(int movieName) {
        for (User_action movie : movieList) {
            if (movie.getLineId()==movieName) {
                return movie;
            }
        }
        return null;
    }
    public void setMovieList(List<User_action> movieList) {
        this.movieList = movieList;
    }

    public Double getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(Double userAmount) {
        this.userAmount = userAmount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}