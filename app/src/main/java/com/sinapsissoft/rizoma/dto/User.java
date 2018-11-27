package com.sinapsissoft.rizoma.dto;

public class User {

    private String userId ;
    private String userName;
    private String userSurname;
    private String userMail ;
    private String userPassword;
    private String userImg;
    private String idCrops;

    public User() {

    }

    public User(String userId, String userName, String userSurname, String userMail, String userPassword, String userImg) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userImg = userImg;
    }

    public String getIdCrops() {
        return idCrops;
    }

    public void setIdCrops(String idCrops) {
        this.idCrops = idCrops;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return userName ;
    }
}
