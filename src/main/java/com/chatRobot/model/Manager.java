package com.chatRobot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Manager {
    private Integer managerId;

    private String managerName;

    private String managerAccount;

    private String managerPassword;

    private String managerEmail;

    private String managerPicture;

    private String managerPhone;

    private Date createTime;

    private Date updateTime;

    public Manager(Integer managerId, String managerName, String managerAccount, String managerPassword, String managerEmail, String managerPicture, String managerPhone, Date createTime, Date updateTime) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.managerAccount = managerAccount;
        this.managerPassword = managerPassword;
        this.managerEmail = managerEmail;
        this.managerPicture = managerPicture;
        this.managerPhone = managerPhone;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Manager() {
        super();
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerAccount() {
        return managerAccount;
    }

    public void setManagerAccount(String managerAccount) {
        this.managerAccount = managerAccount == null ? null : managerAccount.trim();
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword == null ? null : managerPassword.trim();
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail == null ? null : managerEmail.trim();
    }

    public String getManagerPicture() {
        return managerPicture;
    }

    public void setManagerPicture(String managerPicture) {
        this.managerPicture = managerPicture == null ? null : managerPicture.trim();
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone == null ? null : managerPhone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}