package com.chatRobot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    private String userNickname;

    private String userEmail;

    private String userPhoneNumber;

    private String userAddress;

    private String userPassword;

    private String userPicture;

    private Date userCreateTime;

    private Date userUpdateTime;

    public User(Integer userId, String userName, String userNickname, String userEmail, String userPhoneNumber, String userAddress, String userPassword, String userPicture, Date userCreateTime, Date userUpdateTime) {
        this.userId = userId;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
        this.userPassword = userPassword;
        this.userPicture = userPicture;
        this.userCreateTime = userCreateTime;
        this.userUpdateTime = userUpdateTime;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber == null ? null : userPhoneNumber.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture == null ? null : userPicture.trim();
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPicture='" + userPicture + '\'' +
                ", userCreateTime=" + userCreateTime +
                ", userUpdateTime=" + userUpdateTime +
                '}';
    }
}