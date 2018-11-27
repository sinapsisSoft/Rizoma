package com.sinapsissoft.rizoma.dto;

public class Crops {

    private String cropId;
    private String cropState;
    private String cropDateStart;
    private String cropDateEnd;
    private String cropType;
    private String cropName;
    private String cropDescription;
    private String cropCondition;
    private String cropNameScientific;
    private String cropUtilization;
    private String cropImg;
    private String cropImgId;

    public Crops() {
    }

    public Crops(String cropId, String cropState, String cropDateStart, String cropDateEnd, String cropType, String cropName, String cropDescription, String cropCondition, String cropNameScientific, String cropUtilization, String cropImg, String cropImgId) {
        this.cropId = cropId;
        this.cropState = cropState;
        this.cropDateStart = cropDateStart;
        this.cropDateEnd = cropDateEnd;
        this.cropType = cropType;
        this.cropName = cropName;
        this.cropDescription = cropDescription;
        this.cropCondition = cropCondition;
        this.cropNameScientific = cropNameScientific;
        this.cropUtilization = cropUtilization;
        this.cropImg = cropImg;
        this.cropImgId = cropImgId;
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }

    public String getCropState() {
        return cropState;
    }

    public void setCropState(String cropState) {
        this.cropState = cropState;
    }

    public String getCropDateStart() {
        return cropDateStart;
    }

    public void setCropDateStart(String cropDateStart) {
        this.cropDateStart = cropDateStart;
    }

    public String getCropDateEnd() {
        return cropDateEnd;
    }

    public void setCropDateEnd(String cropDateEnd) {
        this.cropDateEnd = cropDateEnd;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropDescription() {
        return cropDescription;
    }

    public void setCropDescription(String cropDescription) {
        this.cropDescription = cropDescription;
    }

    public String getCropCondition() {
        return cropCondition;
    }

    public void setCropCondition(String cropCondition) {
        this.cropCondition = cropCondition;
    }

    public String getCropNameScientific() {
        return cropNameScientific;
    }

    public void setCropNameScientific(String cropNameScientific) {
        this.cropNameScientific = cropNameScientific;
    }

    public String getCropUtilization() {
        return cropUtilization;
    }

    public void setCropUtilization(String cropUtilization) {
        this.cropUtilization = cropUtilization;
    }

    public String getCropImg() {
        return cropImg;
    }

    public void setCropImg(String cropImg) {
        this.cropImg = cropImg;
    }

    public String getCropImgId() {
        return cropImgId;
    }

    public void setCropImgId(String cropImgId) {
        this.cropImgId = cropImgId;
    }
}
