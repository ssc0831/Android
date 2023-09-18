package com.example.cafemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cafemanager.databinding.ActivitySelectBinding;
import com.example.cafemanager.management.ManagementActivity;
import com.example.cafemanager.menu.MenuActivity;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySelectBinding binding = ActivitySelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.menusaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        binding.orderListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, ManagementActivity.class);
                startActivity(intent);
            }
        });
    }
}