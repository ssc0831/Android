package com.example.cafemanager.management;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafemanager.CafeManagerClient;
import com.example.cafemanager.databinding.OrderItemBinding;
import com.example.cafemanager.fcm.FcmClient;
import com.example.cafemanager.fcm.FcmPostService;
import com.example.cafemanager.fcm.NotificationBody;
import com.example.cafemanager.order.Menu;
import com.example.cafemanager.order.OrderCount;
import com.example.cafemanager.order.OrderInfo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private final String TAG = "OrderAdapter";
    private ArrayList<OrderInfo> orderInfoList;

    public OrderAdapter(ArrayList<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }
    //////item 처리//////
    public void addItem(OrderInfo orderInfo){
        orderInfoList.add(orderInfo);
        notifyDataSetChanged();
    }
    public void clear(){
        orderInfoList.clear();
    }
    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderItemBinding binding = OrderItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        OrderInfo orderInfo = orderInfoList.get(position);
        holder.binding.txtOrderId.setText(orderInfo.getOrderId()+"");
        String ordermenu="";
        for(OrderCount orderCount:orderInfo.getOrderList()){
            ordermenu+=orderCount.getMenu().getMenuName()+" "+orderCount.getCount()+"개\n";
        }
        holder.binding.txtMenu.setText(ordermenu);
        if(orderInfo.getOrderStatus().equals("주문완료")){
            holder.binding.orderComBtn.setEnabled(false);
            holder.binding.txtMenu.setTextColor(Color.GRAY);
            holder.binding.txtOrderId.setTextColor(Color.GRAY);
        }
        //주문완료 클릭시 주문자에게 fcm메시지 + db orderstatus 변경
        holder.binding.orderComBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fcm 메시지 전송
                FcmPostService fcmPostService = FcmClient.getInstance().getFcmPostService();
                NotificationBody notificationBody = new NotificationBody(orderInfo.getMember().getFcmToken(),
                        "high",new NotificationBody.NotificationData("주문","음료가 준비됐습니다"),
                        new NotificationBody.NotificationData("주문","음료가 준비됐습니다"));
                Call<ResponseBody> callFCM = fcmPostService.postNotification(notificationBody);
                callFCM.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        holder.binding.orderComBtn.setEnabled(false);
                        //db처리
                        orderStatusChange(orderInfo.getOrderId());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });//fcm메시지 종료
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderInfoList==null?0:orderInfoList.size();
    }
    class OrderViewHolder extends RecyclerView.ViewHolder{
        OrderItemBinding binding;
        public OrderViewHolder(OrderItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
    public void orderStatusChange(long orderId){
        Call<Void> orderCall = CafeManagerClient.getInstance().getOrderService().updateOrderStatus(orderId);
        orderCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "변경 완료");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
