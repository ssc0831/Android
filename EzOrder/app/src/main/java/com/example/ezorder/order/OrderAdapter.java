package com.example.ezorder.order;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezorder.databinding.OrderItemBinding;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private ArrayList<OrderCount> orderList;
    private OnRemoveBtnClickListener onRemoveBtnClickListener;

    //생성자
    public OrderAdapter(ArrayList<OrderCount> orderList) {
        this.orderList = orderList;
    }

    //setter
    public void setOnRemoveBtnClickListener(OnRemoveBtnClickListener onRemoveBtnClickListener) {
        this.onRemoveBtnClickListener = onRemoveBtnClickListener;
    }

    //getter
    public ArrayList<OrderCount> getOrderList() {
        return orderList;
    }

    //clicklistener interface
    public interface OnRemoveBtnClickListener{
        void onRemoveBtnClick(int position, int price);
    }

    /////////////////
    //추가
    public void addItem(OrderCount orderCount){
        orderList.add(orderCount);
        notifyDataSetChanged();
    }
    //수정
    public void updateItem(OrderCount orderCount,int pos){
        orderList.set(pos,orderCount);
        notifyDataSetChanged();
    }
    //삭제
    public void removeItem(int pos){
        orderList.remove(pos);
        notifyDataSetChanged();
    }
    //클리어
    public void clearItem(){
        orderList.clear();
        notifyDataSetChanged();
    }

    //oncreate
    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderItemBinding binidng = OrderItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OrderViewHolder(binidng);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder,@SuppressLint("RecyclerView") int position) {
        OrderCount orderCount = orderList.get(position);
        holder.binding.txtMenuName.setText(orderList.get(position).getMenu().getMenuName());
        holder.binding.txtCount.setText(Integer.toString(orderList.get(position).getCount()));
        holder.binding.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRemoveBtnClickListener.onRemoveBtnClick(position,orderList.get(position).getMenu().getPrice());
                if(orderCount.getCount()==1){
                    removeItem(position);
                }else{
                    orderList.get(position).setCount(orderCount.getCount()-1);
                    holder.binding.txtCount.setText(Integer.toString(orderList.get(position).getCount()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList==null?0:orderList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        OrderItemBinding binding;
        public OrderViewHolder(OrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
