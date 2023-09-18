package com.example.ezorder.order;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ezorder.databinding.ActivityOrderBinding;
import com.example.ezorder.databinding.MenuItemBinding;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private ArrayList<Menu> menuList;
    private OnItemClickListener onItemClickListener;

    //setter
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //생성자
    public MenuAdapter(ArrayList<Menu> menuList) {
        this.menuList = menuList;
    }

    //clicklistener
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

    ////////
    //추가
    public void addItem(Menu menu){
        menuList.add(menu);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuItemBinding binding = MenuItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MenuViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Menu menu = menuList.get(position);
        holder.binding.txtMenuName.setText(menu.getMenuName());
        holder.binding.txtMenuPrice.setText(Integer.toString(menu.getPrice()));

        //이미지 testurl 변경필요
        String imgUrl = "http://10.100.102.20:8044/image/"+menu.getMenuImg();
        Glide.with(holder.binding.getRoot()).load(imgUrl).into(holder.binding.imageViewMenu);
        
        //view 클릭
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
    class MenuViewHolder extends RecyclerView.ViewHolder{
        MenuItemBinding binding;
        public MenuViewHolder(MenuItemBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
