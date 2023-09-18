package com.example.myapp07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp07.databinding.ActivityMainActivity2StuBinding;

public class MainActivity2_stu extends AppCompatActivity {
    private ActivityMainActivity2StuBinding main2Stubinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main2Stubinding = ActivityMainActivity2StuBinding.inflate(getLayoutInflater());
        setContentView(main2Stubinding.getRoot());

        main2Stubinding.btnStuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setSno(Integer.parseInt(main2Stubinding.editStuNum.getText().toString()));
                student.setName(main2Stubinding.editStuName.getText().toString());
                student.setMajor(main2Stubinding.editStuMajor.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("student",student);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}