package com.sinapsissoft.rizoma.dto;

public class Crops {

    private Integer cropsId;
    private Integer productId;
    private Integer userId;
    private String cropsState;
    private String cropsDateStart;
    private String cropsDateEnd;
    private Integer cropsType;

    public Crops() {

    }

    public Crops(Integer id, Integer product, Integer user, String state, String dateStart, String dateEnd, Integer cropsType) {
        this.cropsId = id;
        this.productId = product;
        this.userId = user;
        this.cropsState = state;
        this.cropsDateStart = dateStart;
        this.cropsDateEnd = dateEnd;
        this.cropsType = cropsType;
    }

    public Integer getCropsId() {
        return cropsId;
    }

    public void setCropsId(Integer cropsId) {
        this.cropsId = cropsId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCropsState() {
        return cropsState;
    }

    public void setCropsState(String cropsState) {
        this.cropsState = cropsState;
    }

    public String getCropsDateStart() {
        return cropsDateStart;
    }

    public void setCropsDateStart(String cropsDateStart) {
        this.cropsDateStart = cropsDateStart;
    }

    public String getCropsDateEnd() {
        return cropsDateEnd;
    }

    public void setCropsDateEnd(String cropsDateEnd) {
        this.cropsDateEnd = cropsDateEnd;
    }

    public Integer getCropsType() {
        return cropsType;
    }

    public void setCropsType(Integer cropsType) {
        this.cropsType = cropsType;
    }
}
