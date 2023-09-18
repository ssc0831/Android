package com.example.myapp07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ListView listView = findViewById(R.id.listView1);
        String[] mid = {"리스트 뷰 String1","리스트 뷰 String2","리스트 뷰 String3","리스트 뷰 String4",
                "리스트 뷰 String5","리스트 뷰 String6","리스트 뷰 String7","리스트 뷰 String8","리스트 뷰 String9"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mid);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),mid[i],Toast.LENGTH_SHORT).show();
            }
        });
    }
}