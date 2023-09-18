package com.example.cafemanager.order;

public class OrderCount {
    private Long orderCountid;
    private Menu menu;
    private int count;
    private OrderInfo orderInfo;

    public OrderCount(Menu menu, int count, OrderInfo orderInfo) {
        this.menu = menu;
        this.count = count;
        this.orderInfo = orderInfo;
    }

    public Long getOrderCountid() {
        return orderCountid;
    }

    public void setOrderCountid(Long orderCountid) {
        this.orderCountid = orderCountid;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
