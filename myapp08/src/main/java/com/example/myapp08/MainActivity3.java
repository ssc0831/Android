package com.example.myapp08;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity3 extends AppCompatActivity {
    PhoneAdapter3 phoneAdapter3 = new PhoneAdapter3();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnList = findViewById(R.id.btnList);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatBtn);
        for(int i=0; i<10; i++){
            phoneAdapter3.addItem(new Phone3("홍길동"+i,"010-1111-2222"));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity3.this,RecyclerView.VERTICAL,false);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(phoneAdapter3);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_add3,null);
                EditText etname = dialogView.findViewById(R.id.etname);
                EditText ettel = dialogView.findViewById(R.id.ettel);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity3.this);
                dlg.setTitle("등록");
                dlg.setView(dialogView);

                dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Phone3 p = new Phone3(etname.getText().toString(),ettel.getText().toString());
                       phoneAdapter3.addItem(p);
                    }
                });
                dlg.show();
            }
        });

    }
}