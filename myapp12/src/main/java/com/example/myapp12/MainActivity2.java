package com.example.myapp12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setTitle("seekbar,prgressbar");

        progressBar = findViewById(R.id.progressBar2);
        Button btnInc = findViewById(R.id.btnInc);
        Button btnDec = findViewById(R.id.btnDec);
        SeekBar seekBar = findViewById(R.id.seekBar2);
        TextView tvSeek = findViewById(R.id.tvSeek);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //progressBar.incrementProgressBy(10);
                makeThread();
                Toast.makeText(getApplicationContext(),progressBar.getProgress()+"",Toast.LENGTH_SHORT).show();

            }
        });
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.incrementProgressBy(-10);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvSeek.setText("진행률 "+i+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void makeThread(){
        new Thread(){
            @Override
            public void run() {
                while(progressBar.getProgress()<progressBar.getMax()){
                    progressBar.incrementProgressBy(10);
                    SystemClock.sleep(1000);
                }
            }
        }.start();
    }
}