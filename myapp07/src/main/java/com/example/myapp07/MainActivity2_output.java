package com.example.myapp07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapp07.databinding.ActivityMainActivity2OutputBinding;

public class MainActivity2_output extends AppCompatActivity {
    private String name, age, phone;
    private ActivityMainActivity2OutputBinding main2OutputBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main2OutputBinding = ActivityMainActivity2OutputBinding.inflate(getLayoutInflater());
        setContentView(main2OutputBinding.getRoot());

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");
        phone = intent.getStringExtra("phone");

        main2OutputBinding.tvDisplay.setText("이름 : " +name +" 나이 : "+age+" 전화번호 : "+phone);
        Student student = (Student) intent.getSerializableExtra("student");
        if(student !=null){
            main2OutputBinding.tvDisplay2.setText("학생 객체 : " + student.getSno() +", "+student.getName()+", "+student.getMajor());
        }
        main2OutputBinding.btnMainBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}