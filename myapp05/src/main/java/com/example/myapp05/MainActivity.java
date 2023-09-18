package com.example.myapp05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.example.myapp05.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    long time = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chronometer1.setBase(SystemClock.elapsedRealtime()+time);
                binding.chronometer1.start();
            }
        });
        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chronometer1.stop();
                time=binding.chronometer1.getBase()-SystemClock.elapsedRealtime();
            }
        });
        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.chronometer1.setBase(SystemClock.elapsedRealtime());
                time=0L;

            }
        });

    }
}