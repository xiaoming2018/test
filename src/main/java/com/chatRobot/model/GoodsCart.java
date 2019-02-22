package com.chatRobot.model;

import java.util.Date;

public class GoodsCart {
    private Integer cartid;

    private Integer userId;

    private Integer goodsId;

    private Integer goodsAmount;

    private Date createTime;

    private Date updateTime;

    public GoodsCart(Integer cartid, Integer userId, Integer goodsId, Integer goodsAmount, Date createTime, Date updateTime) {
        this.cartid = cartid;
        this.userId = userId;
        this.goodsId = goodsId;
        this.goodsAmount = goodsAmount;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public GoodsCart() {
        super();
    }

    public Integer getCartid() {
        return cartid;
    }

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Integer goodsAmount) {
        this.goodsAmount = goodsAmount;
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
}