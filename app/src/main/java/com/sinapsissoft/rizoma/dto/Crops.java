package com.sinapsissoft.rizoma.dto;

public class Crops {

    private String cropsId;
    private String productId;
    private String userId;
    private String cropsState;
    private String cropsDateStart;
    private String cropsDateEnd;
    private Integer cropsType;

    public Crops() {

    }

    public Crops(String id, String product, String user, String state, String dateStart, String dateEnd, Integer cropsType) {
        this.cropsId = id;
        this.productId = product;
        this.userId = user;
        this.cropsState = state;
        this.cropsDateStart = dateStart;
        this.cropsDateEnd = dateEnd;
        this.cropsType = cropsType;
    }

    public String getCropsId() {
        return cropsId;
    }

    public void setCropsId(String cropsId) {
        this.cropsId = cropsId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
