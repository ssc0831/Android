package com.example.ezorder.orderstatus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ezorder.EzOrderClient;
import com.example.ezorder.R;
import com.example.ezorder.databinding.ActivityOrderStatusBinding;
import com.example.ezorder.order.OrderInfo;
import com.example.ezorder.order.OrderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderStatusActivity extends AppCompatActivity {
    private ActivityOrderStatusBinding binding;
    private String memberName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderStatusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //주문상태 가게사진 가게이름 주문내용 총가격
        OrderStatusAdapter orderStatusAdapter = new OrderStatusAdapter(this);
        OrderService orderService = EzOrderClient.getInstance().getOrderService();
        SharedPreferences preferences = getSharedPreferences("MemberInfo", MODE_PRIVATE);
        memberName = preferences.getString("memberName","");

        //recyclerView setting
        binding.recyclerViewOrder.setLayoutManager(new LinearLayoutManager(OrderStatusActivity.this, RecyclerView.VERTICAL,false));
        binding.recyclerViewOrder.setAdapter(orderStatusAdapter);

        Call<List<OrderInfo>> call = orderService.OrderInfoListByMemberName(memberName);
        call.enqueue(new Callback<List<OrderInfo>>() {
            @Override
            public void onResponse(Call<List<OrderInfo>> call, Response<List<OrderInfo>> response) {
                for(OrderInfo o : response.body()){
                    orderStatusAdapter.addItem(o);
                }
            }

            @Override
            public void onFailure(Call<List<OrderInfo>> call, Throwable t) {

            }
        });
    }
}