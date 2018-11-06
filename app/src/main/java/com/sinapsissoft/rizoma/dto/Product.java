package com.sinapsissoft.rizoma.dto;

public class Product {
    private Integer productId;
    private Integer cropsId;
    private String productName;
    private String productDescription;
    private String productType;
    private String productCondition;
    private String productNameScientific;
    private String productUtilization;
    private String productImg;

    public Product(Integer productId, Integer cropsId, String productName, String producDescription, String productType, String productCondition, String productNameScientific, String productUtilization, String productImg) {
        this.productId = productId;
        this.cropsId = cropsId;
        this.productName = productName;
        this.productDescription = producDescription;
        this.productType = productType;
        this.productCondition = productCondition;
        this.productNameScientific = productNameScientific;
        this.productUtilization = productUtilization;
        this.productImg = productImg;
    }

    public Product() {

    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCropsId() {
        return cropsId;
    }

    public void setCropsId(Integer cropsId) {
        this.cropsId = cropsId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProducDescription() {
        return productDescription;
    }

    public void setProducDescription(String producDescription) {
        this.productDescription = producDescription;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public String getProductNameScientific() {
        return productNameScientific;
    }

    public void setProductNameScientific(String productNameScientific) {
        this.productNameScientific = productNameScientific;
    }

    public String getProductUtilization() {
        return productUtilization;
    }

    public void setProductUtilization(String productUtilization) {
        this.productUtilization = productUtilization;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
