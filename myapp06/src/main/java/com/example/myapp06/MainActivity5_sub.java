package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp06.databinding.ActivityMainActivity5SubBinding;

public class MainActivity5_sub extends AppCompatActivity {
    ActivityMainActivity5SubBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainActivity5SubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        final int addResult = intent.getIntExtra("num1",0)+intent.getIntExtra("num2",0);

        binding.btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outintent = new Intent(getApplicationContext(), MainActivity5.class);
                outintent.putExtra("addResult",addResult);
                setResult(RESULT_OK,outintent);
                finish();
            }
        });
    }
}