package com.example.myapp04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TimePicker;

import com.example.myapp04.databinding.ActivityMain9Binding;

public class MainActivity9 extends AppCompatActivity {
    private ActivityMain9Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        binding = ActivityMain9Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.calendarView.setVisibility(View.INVISIBLE);
        binding.timePicker1.setVisibility(View.INVISIBLE);

        binding.rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calendarView.setVisibility(View.VISIBLE);
                binding.timePicker1.setVisibility(View.INVISIBLE);
            }
        });
        binding.rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calendarView.setVisibility(View.INVISIBLE);
                binding.timePicker1.setVisibility(View.VISIBLE);
            }
        });
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chronometer1.setBase(SystemClock.elapsedRealtime());
                binding.chronometer1.start();
                binding.chronometer1.setTextColor(Color.RED);
            }
        });
        binding.btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chronometer1.stop();
                binding.chronometer1.setTextColor(Color.BLUE);
            }
        });
        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                binding.tvYear.setText(Integer.toString(i));
                binding.tvMonth.setText(Integer.toString(i1+1));
                binding.tvDay.setText(Integer.toString(i2));
            }
        });
        binding.timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                binding.tvHour.setText(Integer.toString(i));
                binding.tvMinute.setText(Integer.toString(i1));
            }
        });
    }
}