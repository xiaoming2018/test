package com.chatRobot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Order {
    private Integer orderId;

    private Integer userId;

    private Integer goodsId;

    private Integer goodsAmount;

    private String orderStatus;

    private Date orderTime;

    private Date orderCreateTime;

    private Date orderUpdateTime;

    public Order(Integer orderId, Integer userId, Integer goodsId, Integer goodsAmount, String orderStatus, Date orderTime, Date orderCreateTime, Date orderUpdateTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.goodsId = goodsId;
        this.goodsAmount = goodsAmount;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
        this.orderCreateTime = orderCreateTime;
        this.orderUpdateTime = orderUpdateTime;
    }

    public Order() {
        super();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderUpdateTime() {
        return orderUpdateTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setOrderUpdateTime(Date orderUpdateTime) {
        this.orderUpdateTime = orderUpdateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", goodsAmount=" + goodsAmount +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderTime=" + orderTime +
                ", orderCreateTime=" + orderCreateTime +
                ", orderUpdateTime=" + orderUpdateTime +
                '}';
    }
}