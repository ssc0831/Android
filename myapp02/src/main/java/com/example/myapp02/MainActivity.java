package com.example.myapp02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnNaver,btn119,btnGal,btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNaver = findViewById(R.id.btnNaver);
        btn119 = findViewById(R.id.btn119);
        btnGal = findViewById(R.id.btnGal);
        btnEnd = findViewById(R.id.btnEnd);


        btnNaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNaver = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));

                startActivity(intentNaver);
            }
        });

        btn119.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenCall = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:/119"));

                startActivity(intenCall);
            }
        });
        btnGal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"갤러리버튼클릭",Toast.LENGTH_LONG).show();
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}