package com.chatRobot.model;

import java.util.Date;

public class Goods {
    private Integer id;

    private String name;

    private String place;

    private Date createTime;

    private Date updateTime;

    private String author;

    public Goods(Integer id, String name, String place, Date createTime, Date updateTime, String author) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.author = author;
    }

    public Goods() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", author='" + author + '\'' +
                '}';
    }
}