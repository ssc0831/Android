package com.example.cafemanager.order;

import com.example.cafemanager.shop.Shop;

public class Menu {
    private long menuId;
    private String menuName;
    private int price;
    private String menuImg;
    private Shop shop;

    public Menu() {
    }


    public Menu(long menuId, String menuName, int price, String menuImg) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.menuImg = menuImg;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(String menuImg) {
        this.menuImg = menuImg;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
