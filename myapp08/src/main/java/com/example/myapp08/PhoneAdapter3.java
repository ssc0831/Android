package com.example.myapp08;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhoneAdapter3 extends RecyclerView.Adapter<PhoneAdapter3.PhoneViewHolder> {
    ArrayList<Phone3> phoneList = new ArrayList<>();
    //추가
    public void addItem(Phone3 phone3){
        phoneList.add(phone3);
        notifyDataSetChanged();
    }
    //삭제
    public void removeItem(int position){
        phoneList.remove(position);
        notifyDataSetChanged();
    }
    //수정
    public void updateItem(int position,Phone3 phone3){
        phoneList.set(position,phone3);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PhoneAdapter3.PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_phone3,parent,false);
        return new PhoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter3.PhoneViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Phone3 phone3 = phoneList.get(position);
        holder.setItem(phone3);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                View dialogView = View.inflate(view.getContext(),R.layout.layout_add3,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                EditText etname = dialogView.findViewById(R.id.etname);
                EditText ettel = dialogView.findViewById(R.id.ettel);

                dlg.setTitle("수정");
                dlg.setView(dialogView);
                dlg.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    Phone3 p = new Phone3(etname.getText().toString(),ettel.getText().toString());
                    updateItem(position,p);
                    }
                });
                dlg.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeItem(position);
                    }
                });
                dlg.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return phoneList == null ? 0 : phoneList.size();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        TextView txtmain,txtTel;
        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmain = itemView.findViewById(R.id.txtmain);
            txtTel = itemView.findViewById(R.id.txtTel);
        }
        public void setItem(Phone3 phone3){

            txtmain.setText(phone3.getName());
            txtTel.setText(phone3.getTel());
        }
    }

}
