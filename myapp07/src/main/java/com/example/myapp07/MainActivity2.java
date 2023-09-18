package com.example.myapp07;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.myapp07.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    private String name, age, phone;
    private Student student;
    private ActivityMain2Binding main2Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main2Binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(main2Binding.getRoot());
        setSupportActionBar(main2Binding.toolbar);
        setTitle("양방향intent");

        main2Binding.btnDataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2_input.class);
                startActivityLauncher.launch(intent);
            }
        });
        main2Binding.btnDataOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2_output.class);
                intent.putExtra("name",name);
                intent.putExtra("age",age);
                intent.putExtra("phone",phone);
                intent.putExtra("student",student);
                startActivity(intent);
            }
        });
        main2Binding.btnDataStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2_stu.class);
                studentActivityLauncher.launch(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> startActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                Intent intent = result.getData();
                name = intent.getStringExtra("name");
                age = intent.getStringExtra("age");
                phone = intent.getStringExtra("phone");
                return;
            }
        }
    });
    ActivityResultLauncher<Intent> studentActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK){
                        Intent intent = result.getData();
                        student = (Student) intent.getSerializableExtra("student");
                        return;
                    }
                }
    });
}