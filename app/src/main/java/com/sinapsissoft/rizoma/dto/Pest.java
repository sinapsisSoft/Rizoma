package com.sinapsissoft.rizoma.dto;

public class Pest {
    private Integer PestId;
    private Integer PestCtrlId;
    private String PestName;
    private String PestDescription;
    private String PestImg;
    private Integer PestImgId;
    private String PestType;
    private String PestCtrlName;


    public Pest() {
    }

    public Integer getPestImgId() {
        return PestImgId;
    }

    public void setPestImgId(Integer pestImgId) {
        PestImgId = pestImgId;
    }

    public Integer getPestCtrlId() {
        return PestCtrlId;
    }

    public void setPestCtrlId(Integer pestCtrlId) {
        PestCtrlId = pestCtrlId;
    }

    public String getPestCtrlName() {
        return PestCtrlName;
    }

    public void setPestCtrlName(String pestCtrlName) {
        PestCtrlName = pestCtrlName;
    }

    public Integer getPestId() {
        return PestId;
    }

    public void setPestId(Integer pestId) {
        PestId = pestId;
    }

    public String getPestName() {
        return PestName;
    }

    public void setPestName(String pestName) {
        PestName = pestName;
    }

    public String getPestDescription() {
        return PestDescription;
    }

    public void setPestDescription(String pestDescription) {
        PestDescription = pestDescription;
    }

    public String getPestImg() {
        return PestImg;
    }

    public void setPestImg(String pestImg) {
        PestImg = pestImg;
    }

    public String getPestType() {
        return PestType;
    }

    public void setPestType(String pestType) {
        PestType = pestType;
    }
}
