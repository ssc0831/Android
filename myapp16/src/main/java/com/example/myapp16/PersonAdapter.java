package com.example.myapp16;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp16.databinding.AddLayoutBinding;
import com.example.myapp16.databinding.ItemListBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private ArrayList<Person> personList;
    private PersonService personService = PersonClient.getInstance().getPersonService();

    public PersonAdapter(ArrayList<Person> personList) {
        this.personList = personList;
    }
    ////////////////////////
    //item수정
    ////////////////////////
    //추가
    public void addItem(Person p){
        personList.add(p);
        notifyDataSetChanged();
    }
    //삭제
    public void removeItem(int position){
        personList.remove(position);
        notifyDataSetChanged();
    }
    //수정
    public void updateItem(int position,Person updatePerson){
        personList.set(position,updatePerson);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PersonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.PersonViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Person p = personList.get(position);
        holder.binding.txName.setText(p.getName());
        holder.binding.txTel.setText(p.getTel());

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddLayoutBinding binding = AddLayoutBinding.inflate(LayoutInflater.from(view.getContext()));
                Person dialogPerson = personList.get(position);

                binding.etName.setText(dialogPerson.getName());
                binding.etTel.setText(dialogPerson.getTel());

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("수정");
                builder.setView(binding.getRoot());
                //dialog 수정 버튼 클릭
                builder.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Person updatePerson = new Person(binding.etName.getText().toString(),
                                binding.etTel.getText().toString());
                        Call<Person> call = personService.update(dialogPerson.getId(),updatePerson);
                        call.enqueue(new Callback<Person>() {
                            @Override
                            public void onResponse(Call<Person> call, Response<Person> response) {
                                updateItem(position,response.body());
                            }

                            @Override
                            public void onFailure(Call<Person> call, Throwable t) {

                            }
                        });
                    }
                });
                //dialog 삭제 버튼 클릭
                builder.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Call<Void> call = personService.deleteById(dialogPerson.getId());
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                removeItem(position);
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
    class PersonViewHolder extends RecyclerView.ViewHolder{
        ItemListBinding binding;
        public PersonViewHolder(ItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
