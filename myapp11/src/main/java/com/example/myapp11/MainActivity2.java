package com.example.myapp11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {
    private int num_page=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("ViewPager(Fragment)");
        ViewPager2 viewpager = findViewById(R.id.viewpager);
        FragmentStateAdapter fragmentStateAdapter = new MyFragAdapter(this,num_page);
        viewpager.setAdapter(fragmentStateAdapter);
    }
}