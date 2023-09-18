package com.example.cafemanager;

import com.example.cafemanager.menu.MenuService;
import com.example.cafemanager.order.OrderService;
import com.example.cafemanager.shop.ShopService;
import com.example.cafemanager.user.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CafeManagerClient {
    private static CafeManagerClient instance;
    private UserService userService;
    private ShopService shopService;
    private OrderService orderService;
    private MenuService menuService;

    public static CafeManagerClient getInstance(){
        if(instance==null){
            instance=new CafeManagerClient();
        }
        return instance;

    }
    public CafeManagerClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.102.20:8044/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //서비스 추가
        userService = retrofit.create(UserService.class);
        shopService = retrofit.create(ShopService.class);
        orderService = retrofit.create(OrderService.class);
        menuService = retrofit.create(MenuService.class);
    }

    //getter
    public UserService getUserService() {
        return userService;
    }

    public ShopService getShopService() {
        return shopService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public MenuService getMenuService() {
        return menuService;
    }
}
