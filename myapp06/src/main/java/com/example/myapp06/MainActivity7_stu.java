package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity7_stu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity7_stu);
        EditText editStuName = findViewById(R.id.editStuName);
        EditText editStuNum = findViewById(R.id.editStuNum);
        EditText editStuMajor = findViewById(R.id.editStuMajor);
        Button btnStuHome = findViewById(R.id.btnStuHome);

        btnStuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setSno(Integer.parseInt(editStuNum.getText().toString()));
                student.setName(editStuName.getText().toString());
                student.setMajor(editStuMajor.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity7.class);
                intent.putExtra("student",student);
                setResult(10,intent);
                finish();
            }
        });
    }
}