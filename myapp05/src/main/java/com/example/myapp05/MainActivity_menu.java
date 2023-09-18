package com.example.myapp05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toolbar;

public class MainActivity_menu extends AppCompatActivity {
    private LinearLayout baseLayout;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setTitle("배경색 변경");
        baseLayout = findViewById(R.id.baseLayout);
        button = findViewById(R.id.btn1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemRed){
            baseLayout.setBackgroundColor(Color.RED);
        }else if(item.getItemId()==R.id.itemBlue){
            baseLayout.setBackgroundColor(Color.BLUE);
        }else if(item.getItemId()==R.id.itemGreen){
            baseLayout.setBackgroundColor(Color.GREEN);
        } else if (item.getItemId() == R.id.subRotate) {
            button.setRotation(45);
        } else if (item.getItemId() == R.id.subSize) {
            button.setScaleX(2);
        }
        return false;
    }
}