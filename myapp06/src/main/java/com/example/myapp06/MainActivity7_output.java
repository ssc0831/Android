package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity7_output extends AppCompatActivity {
    private String name,age,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_activity7_output);

        TextView tvDisplay = findViewById(R.id.tvDisplay);
        TextView tvDisplay2 = findViewById(R.id.tvDisplay2);
        Button btnMainBack = findViewById(R.id.btnMainBack);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");
        phone = intent.getStringExtra("phone");


        tvDisplay.setText("이름 : " +name +" 나이 : "+age+" 전화번호 : "+phone);
        Student student = (Student) intent.getSerializableExtra("student");
        if(student !=null){
            tvDisplay2.setText("학생 객체 : " + student.getSno() +", "+student.getName()+", "+student.getMajor());
        }
        btnMainBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}