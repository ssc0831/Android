package com.example.myapp14;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> {
    private List<Phone> phoneList;

    //클릭interface
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(int pos);
    }
    OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    //추가
    public void addItem(Phone phone){
        phoneList.add(phone);
        notifyDataSetChanged();
    }
    //수정
    public void updateItem(int pos,Phone phone){
        phoneList.set(pos,phone);
        notifyDataSetChanged();
    }
    //삭제
    public void deleteItem(int pos){
        phoneList.remove(pos);
        notifyDataSetChanged();
    }
    public Phone getItem(int position){
        return phoneList.get(position);
    }


    public PhoneAdapter(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    @NonNull
    @Override
    public PhoneAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.MyViewHolder holder, int position) {
        Phone phone = phoneList.get(position);
        holder.txName.setText(phone.getName());
        holder.txTel.setText(phone.getTel());
    }

    @Override
    public int getItemCount() {
        return phoneList==null?0:phoneList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txName, txTel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txName = itemView.findViewById(R.id.txName);
            txTel = itemView.findViewById(R.id.txTel);

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
}
