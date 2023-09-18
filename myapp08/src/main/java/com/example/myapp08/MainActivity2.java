package com.example.myapp08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.StringPrepParseException;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        PersonAdapter personAdapter = new PersonAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        for(int i=0; i<20; i++){
            personAdapter.addItem(new Person("홍길동"+i,"010-1111-2222"));
        }
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(personAdapter);

    }
}