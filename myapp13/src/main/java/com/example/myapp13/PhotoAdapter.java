package com.example.myapp13;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private ArrayList<Photo> photoList;

    public PhotoAdapter(ArrayList<Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.txId.setText(Integer.toString(photo.getId()));
        holder.txTitle.setText(photo.getTitle());
        holder.txUrl.setText(photo.getUrl());
        Glide.with(holder.itemView).load(photo.getThumbnailUrl()).into(holder.imageView);
        //holder.imageView.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return photoList==null?0:photoList.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder{
        TextView txId,txTitle,txUrl;
        ImageView imageView;
        View itemView;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.txId = itemView.findViewById(R.id.txId);
            this.txTitle = itemView.findViewById(R.id.txTitle);
            this.txUrl = itemView.findViewById(R.id.txUrl);
            this.imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
