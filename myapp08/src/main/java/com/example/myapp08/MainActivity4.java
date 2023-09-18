package com.example.myapp08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.myapp08.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {
    private PersonAdapter4 personAdapter4 = new PersonAdapter4();
    ActivityMain4Binding binding;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity4.this, RecyclerView.VERTICAL,false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(personAdapter4);

        personAdapter4.setOnItemClickListener(new PersonAdapter4.onItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                position = pos;
                Person4 person4 = personAdapter4.getItem(pos);
                binding.editName.setText(person4.getName());
                binding.editTel.setText(person4.getTel());
                binding.btnUpdate.setEnabled(true);
                binding.btnDelete.setEnabled(true);
            }
        });
        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personAdapter4.addItem(new Person4(binding.editName.getText().toString(),binding.editTel.getText().toString()));
                binding.editName.setText("");
                binding.editTel.setText("");
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personAdapter4.updateItem(new Person4(binding.editName.getText().toString(),binding.editTel.getText().toString()),position);
                binding.editName.setText("");
                binding.editTel.setText("");
                binding.btnUpdate.setEnabled(false);
                binding.btnDelete.setEnabled(false);

            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personAdapter4.removeItem(position);
                binding.editName.setText("");
                binding.editTel.setText("");
                binding.btnUpdate.setEnabled(false);
                binding.btnDelete.setEnabled(false);

            }
        });
    }
}