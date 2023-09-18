package com.example.myapp08;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<Person> personList = new ArrayList<>();
    public void addItem(Person person){
        personList.add(person);
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.person_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.setItem(person);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvPhone=itemView.findViewById(R.id.tvPhone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Log.d("position:", position+"");
                    Toast.makeText(view.getContext(),personList.get(position).getName()+"//"+personList.get(position).getMobile(),Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    Log.d("long position:", position+"long");
                    Toast.makeText(view.getContext(),personList.get(position).getName()+"/long/"+personList.get(position).getMobile(),Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
        public void setItem(Person person){
            tvName.setText(person.getName());
            tvPhone.setText(person.getMobile());
        }
    }
}
