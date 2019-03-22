package com.chatRobot.model;

import java.util.Date;

public class GoodsType {
    private Integer goodstypeId;

    private String goodstypeName;

    private Date goodstypeCreatetime;

    private Date goodstypeUpdatatime;

    public GoodsType(Integer goodstypeId, String goodstypeName, Date goodstypeCreatetime, Date goodstypeUpdatatime) {
        this.goodstypeId = goodstypeId;
        this.goodstypeName = goodstypeName;
        this.goodstypeCreatetime = goodstypeCreatetime;
        this.goodstypeUpdatatime = goodstypeUpdatatime;
    }

    public GoodsType() {
        super();
    }

    public Integer getGoodstypeId() {
        return goodstypeId;
    }

    public void setGoodstypeId(Integer goodstypeId) {
        this.goodstypeId = goodstypeId;
    }

    public String getGoodstypeName() {
        return goodstypeName;
    }

    public void setGoodstypeName(String goodstypeName) {
        this.goodstypeName = goodstypeName == null ? null : goodstypeName.trim();
    }

    public Date getGoodstypeCreatetime() {
        return goodstypeCreatetime;
    }

    public void setGoodstypeCreatetime(Date goodstypeCreatetime) {
        this.goodstypeCreatetime = goodstypeCreatetime;
    }

    public Date getGoodstypeUpdatatime() {
        return goodstypeUpdatatime;
    }

    public void setGoodstypeUpdatatime(Date goodstypeUpdatatime) {
        this.goodstypeUpdatatime = goodstypeUpdatatime;
    }
}