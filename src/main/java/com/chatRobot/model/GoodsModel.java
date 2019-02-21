package com.chatRobot.model;

import java.util.Date;

public class GoodsModel {
    private Integer modelId;

    private String modelName;

    private String modelFile;

    private String modelType;

    private Date modelCreateTime;

    private Date modelUpdateTime;

    public GoodsModel(Integer modelId, String modelName, String modelFile, String modelType, Date modelCreateTime, Date modelUpdateTime) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelFile = modelFile;
        this.modelType = modelType;
        this.modelCreateTime = modelCreateTime;
        this.modelUpdateTime = modelUpdateTime;
    }

    public GoodsModel() {
        super();
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getModelFile() {
        return modelFile;
    }

    public void setModelFile(String modelFile) {
        this.modelFile = modelFile == null ? null : modelFile.trim();
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType == null ? null : modelType.trim();
    }

    public Date getModelCreateTime() {
        return modelCreateTime;
    }

    public void setModelCreateTime(Date modelCreateTime) {
        this.modelCreateTime = modelCreateTime;
    }

    public Date getModelUpdateTime() {
        return modelUpdateTime;
    }

    public void setModelUpdateTime(Date modelUpdateTime) {
        this.modelUpdateTime = modelUpdateTime;
    }
}