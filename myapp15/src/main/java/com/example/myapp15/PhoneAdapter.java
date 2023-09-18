package com.example.myapp15;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder> {

    private ArrayList<Phone> phoneList = new ArrayList<>();
    private int selectedPosition = RecyclerView.NO_POSITION;

    // ViewHolder 클래스 생성
    class PhoneViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvTel;

        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTel = itemView.findViewById(R.id.tvTel);

            // 아이템 클릭 시 선택된 위치 업데이트
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    // ViewHolder 생성 메서드
    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phone, parent, false);
        return new PhoneViewHolder(view);
    }

    // ViewHolder 바인딩 메서드
    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        Phone phone = phoneList.get(position);
        holder.tvName.setText(phone.getName());
        holder.tvTel.setText(phone.getTel());

        // 선택된 아이템 표시 설정
        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    // 아이템 개수 반환 메서드
    @Override
    public int getItemCount() {
        return phoneList.size();
    }

    // 아이템 추가 메서드
    public void addItem(Phone phone) {
        phoneList.add(phone);
        notifyDataSetChanged();
    }

    // 아이템 수정 메서드
    public void updateItem(int position, Phone updatedPhone) {
        if (position >= 0 && position < phoneList.size()) {
            phoneList.set(position, updatedPhone);
            notifyItemChanged(position);
        }
    }

    // 아이템 삭제 메서드
    public void removeItem(int position) {
        if (position >= 0 && position < phoneList.size()) {
            phoneList.remove(position);
            notifyItemRemoved(position);
            selectedPosition = RecyclerView.NO_POSITION;
        }
    }

    // 선택된 아이템의 인덱스 반환 메서드
    public int getSelectedPosition() {
        return selectedPosition;
    }
}
