package com.example.myapp14.person;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp14.databinding.ItemListBinding;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    ArrayList<Person> personList;
    private SetOnItemClickListener setOnItemClickListener;
    private SetOnItemLongClickListener setOnItemLongClickListener;

    //생성자
    public PersonAdapter(ArrayList<Person> personList) {
        this.personList = personList;
    }

    //clicklistener setter
    public void setSetOnItemClickListener(SetOnItemClickListener setOnItemClickListener) {
        this.setOnItemClickListener = setOnItemClickListener;
    }

    public void setSetOnItemLongClickListener(SetOnItemLongClickListener setOnItemLongClickListener) {
        this.setOnItemLongClickListener = setOnItemLongClickListener;
    }

    ///////////////////////////////Click ineterface//////////////////////
    public interface SetOnItemClickListener{
        void onItemClick(int pos);
    }
    public interface SetOnItemLongClickListener{
        void onItemLongClick(int pos);
    }
    //////////////////////////////////////////////////////////
    //item추가
    public void addItem(Person person){
        personList.add(person);
        notifyDataSetChanged();
    }
    //item삭제
    public void removeItem(int pos){
        personList.remove(pos);
        notifyDataSetChanged();
    }
    //item수정
    public void updateItem(int pos,Person person){
        personList.set(pos,person);
        notifyDataSetChanged();
    }
    //findByPosition
    public Person getItem(int pos){
        return personList.get(pos);
    }
    ///////////////////////////////////////////////////

    @NonNull
    @Override
    public PersonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.MyViewHolder holder, int position) {
        Person p = personList.get(position);
        holder.binding.txName.setText(p.getName());
        holder.binding.txTel.setText(p.getTel());

    }

    @Override
    public int getItemCount() {
        return personList==null?0:personList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ItemListBinding binding;
        public MyViewHolder(ItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    setOnItemLongClickListener.onItemLongClick(getAdapterPosition());
                    return false;
                }
            });
        }
    }
}
