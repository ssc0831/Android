package com.example.myapp06;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity7 extends AppCompatActivity {
    private String name, age, phone;
    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        Button btnDataInput = findViewById(R.id.btnDataInput);
        Button btnDataOutput = findViewById(R.id.btnDataOutput);
        Button btnDataStu = findViewById(R.id.btnDataStu);

        btnDataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity7_input.class);
                startActivityForResult(intent,0);
            }
        });
        btnDataOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity7_output.class);
                intent.putExtra("name",name);
                intent.putExtra("age",age);
                intent.putExtra("phone",phone);
                intent.putExtra("student",student);
                startActivity(intent);
            }
        });
        btnDataStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity7_stu.class);
                startActivityForResult(intent,0);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==10){
            student=(Student) data.getSerializableExtra("student");
            return;
        }
        if(resultCode==RESULT_OK){
            name=data.getStringExtra("editName");
            age=data.getStringExtra("editAge");
            phone=data.getStringExtra("editPhone");
            return;
        }

    }
}