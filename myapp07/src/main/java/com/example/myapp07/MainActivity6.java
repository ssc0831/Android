package com.example.myapp07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        EditText editItem = findViewById(R.id.editItem);
        Button btnAdd = findViewById(R.id.btnAdd);
        ListView listView1 = findViewById(R.id.listView1);

        ArrayList<String> arr = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr);
        listView1.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arr.add(editItem.getText().toString());
                adapter.notifyDataSetChanged();
                editItem.setText("");
            }
        });
    }
}