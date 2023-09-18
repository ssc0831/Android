package com.example.ezorder.order;

public class Shop {
    private long shopId;
    private String shopName;
    private double latitude;
    private double longitude;
    private String shopImg;
    private String token;

    public Shop() {
    }

    public Shop(long shopId) {
        this.shopId = shopId;
    }

    public Shop(long shopId, String shopName, double latitude, double longitude, String shopImg) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.shopImg = shopImg;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
