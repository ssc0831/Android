package com.example.myapp13.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp13.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    ArrayList<Post> postList;

    public PostAdapter(ArrayList<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.txId.setText(Integer.toString(post.getId()));
        holder.txTitle.setText(post.getTitle());
        holder.txUrl.setText(post.getBody());

    }

    @Override
    public int getItemCount() {
        return postList==null?0:postList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView txId, txTitle, txUrl;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            txId = itemView.findViewById(R.id.txId);
            txTitle = itemView.findViewById(R.id.txTitle);
            txUrl = itemView.findViewById(R.id.txUrl);
        }
    }
}
