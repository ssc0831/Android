package com.example.myapp05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity_contextmenu extends AppCompatActivity {
    private LinearLayout baseLayout;
    private Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contextmenu);
        setTitle("배경색 바꾸기(컨텍스트 메뉴)");
        button1 = findViewById(R.id.button1);
        registerForContextMenu(button1);
        button2 = findViewById(R.id.button2);
        registerForContextMenu(button2);
        baseLayout = findViewById(R.id.baseLayout);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        if(v==button1){
            menu.setHeaderTitle("배경색 변경");
            menuInflater.inflate(R.menu.menu1,menu);
        }else if(v==button2){
            menu.setHeaderTitle("회전");
            menuInflater.inflate(R.menu.menu2,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemRed){
            baseLayout.setBackgroundColor(Color.RED);
        }else if(item.getItemId()==R.id.itemBlue){
            baseLayout.setBackgroundColor(Color.BLUE);
        }else if(item.getItemId()==R.id.itemGreen){
            baseLayout.setBackgroundColor(Color.GREEN);
        } else if (item.getItemId() == R.id.subRotate) {
            button2.setRotation(45);
        } else if (item.getItemId() == R.id.subSize) {
            button2.setScaleX(2);
        }
        return false;
    }
}