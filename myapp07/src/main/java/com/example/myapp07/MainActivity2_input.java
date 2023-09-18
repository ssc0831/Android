package com.example.myapp07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp07.databinding.ActivityMainActivity2InputBinding;

public class MainActivity2_input extends AppCompatActivity {
    private ActivityMainActivity2InputBinding main2InputBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main2InputBinding = ActivityMainActivity2InputBinding.inflate(getLayoutInflater());
        setContentView(main2InputBinding.getRoot());

        main2InputBinding.btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity2.class);
                outIntent.putExtra("name",main2InputBinding.editName.getText().toString());
                outIntent.putExtra("age",main2InputBinding.editAge.getText().toString());
                outIntent.putExtra("phone",main2InputBinding.editPhone.getText().toString());
                setResult(RESULT_OK,outIntent);
                finish();
            }
        });
    }
}