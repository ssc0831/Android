package com.example.myapp07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity_sub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sub);

        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        int num1 = intent.getIntExtra("num1",0);
        int num2 = intent.getIntExtra("num2",0);
        int sum = num1+num2;

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outintent = new Intent(getApplicationContext(),MainActivity.class);
                outintent.putExtra("sum",sum);
                setResult(RESULT_OK,outintent);
                finish();
            }
        });

    }
}