package com.example.myapp08;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    ArrayList<Friend> friendList = new ArrayList<>();
    //인터페이스
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(int pos);
    }
    //만든 interface쓰기 위해 선언;
    OnItemClickListener onItemClickListener;
    OnItemLongClickListener onItemLongClickListener;

    //setter
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendlist_item,parent,false);
        return new FriendAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder holder, int position) {
        Friend friend = friendList.get(position);
        holder.txtName.setText(friend.getName());
        holder.txtMsg.setText(friend.getMsg());

    }

    @Override
    public int getItemCount() {
        return friendList==null?0:friendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtMsg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtMsg = itemView.findViewById(R.id.txtMsg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    onItemClickListener.onItemClick(position);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    onItemLongClickListener.onItemLongClick(position);
                    return false;
                }
            });
        }
    }
    public void addItem(Friend friend){
        friendList.add(friend);
    }
    //삭제
    public void deleteItem(int pos){
        friendList.remove(pos);
        notifyDataSetChanged();
    }
}

