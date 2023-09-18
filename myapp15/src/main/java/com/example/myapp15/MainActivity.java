package com.example.myapp15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);

        Intent intent = new Intent(this,MusicService.class);
        
        //서비스 시작 클릭
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
                Log.d("서비스테스트 btnStart", "startService");
                Toast.makeText(getApplicationContext(),"StartService",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        
        //서비스 종료 클릭
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
                Log.d("서비스테스트 btnEnd", "endService");
                Toast.makeText(getApplicationContext(),"StopService",Toast.LENGTH_SHORT).show();

            }
        });
    }
}