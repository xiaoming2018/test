package com.chatRobot.model;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private Integer goodsId;

    private String goodsName;

    private BigDecimal goodsPrice;

    private Integer goodsTypeId;

    private Integer goodsModelId;

    private String goodsDesc;

    private Float goodsDiscount;

    private String goodsPicture;

    private Boolean goodsIsnew;

    private Boolean goodsIsrecommend;

    private String goodsStatus;

    private String goodsRemark;

    private Integer goodsAmount;

    private Integer goodsSellAmount;

    private Date goodsCreateTime;

    private Date goodsUpdateTime;

    public Goods(Integer goodsId, String goodsName, BigDecimal goodsPrice, Integer goodsTypeId, Integer goodsModelId, String goodsDesc, Float goodsDiscount, String goodsPicture, Boolean goodsIsnew, Boolean goodsIsrecommend, String goodsStatus, String goodsRemark, Integer goodsAmount, Integer goodsSellAmount, Date goodsCreateTime, Date goodsUpdateTime) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsTypeId = goodsTypeId;
        this.goodsModelId = goodsModelId;
        this.goodsDesc = goodsDesc;
        this.goodsDiscount = goodsDiscount;
        this.goodsPicture = goodsPicture;
        this.goodsIsnew = goodsIsnew;
        this.goodsIsrecommend = goodsIsrecommend;
        this.goodsStatus = goodsStatus;
        this.goodsRemark = goodsRemark;
        this.goodsAmount = goodsAmount;
        this.goodsSellAmount = goodsSellAmount;
        this.goodsCreateTime = goodsCreateTime;
        this.goodsUpdateTime = goodsUpdateTime;
    }

    public Goods() {
        super();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public Integer getGoodsModelId() {
        return goodsModelId;
    }

    public void setGoodsModelId(Integer goodsModelId) {
        this.goodsModelId = goodsModelId;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
    }

    public Float getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(Float goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture == null ? null : goodsPicture.trim();
    }

    public Boolean getGoodsIsnew() {
        return goodsIsnew;
    }

    public void setGoodsIsnew(Boolean goodsIsnew) {
        this.goodsIsnew = goodsIsnew;
    }

    public Boolean getGoodsIsrecommend() {
        return goodsIsrecommend;
    }

    public void setGoodsIsrecommend(Boolean goodsIsrecommend) {
        this.goodsIsrecommend = goodsIsrecommend;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus == null ? null : goodsStatus.trim();
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark == null ? null : goodsRemark.trim();
    }

    public Integer getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Integer goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Integer getGoodsSellAmount() {
        return goodsSellAmount;
    }

    public void setGoodsSellAmount(Integer goodsSellAmount) {
        this.goodsSellAmount = goodsSellAmount;
    }

    public Date getGoodsCreateTime() {
        return goodsCreateTime;
    }

    public void setGoodsCreateTime(Date goodsCreateTime) {
        this.goodsCreateTime = goodsCreateTime;
    }

    public Date getGoodsUpdateTime() {
        return goodsUpdateTime;
    }

    public void setGoodsUpdateTime(Date goodsUpdateTime) {
        this.goodsUpdateTime = goodsUpdateTime;
    }
}