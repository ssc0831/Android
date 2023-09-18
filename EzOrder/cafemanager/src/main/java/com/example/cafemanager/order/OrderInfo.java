package com.example.cafemanager.order;

import com.example.cafemanager.shop.Shop;


import java.util.List;

public class OrderInfo {
    private long orderId;
    private String orderStatus;
    private List<OrderCount> orderList;
    private Shop shop;
    private Member member;
    private int totPrice;

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public OrderInfo(String orderStatus, List<OrderCount> orderList,Shop shop,Member member,int totPrice) {
        this.orderStatus = orderStatus;
        this.orderList = orderList;
        this.shop = shop;
        this.member = member;
        this.totPrice = totPrice;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderCount> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderCount> orderList) {
        this.orderList = orderList;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getTotPrice() {
        return totPrice;
    }

    public void setTotPrice(int totPrice) {
        this.totPrice = totPrice;
    }
}
