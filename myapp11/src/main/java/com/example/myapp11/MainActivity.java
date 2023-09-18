package com.example.myapp11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ViewPager");
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        Button btnToggle = findViewById(R.id.btnToggle);
        ArrayList<DataPage> list = new ArrayList<>();
        list.add(new DataPage(Color.RED,"1 page"));
        list.add(new DataPage(Color.BLUE,"2 page"));
        list.add(new DataPage(Color.GREEN,"3 page"));
        list.add(new DataPage(Color.BLACK,"4 page"));
        list.add(new DataPage(Color.CYAN,"5 page"));
        list.add(new DataPage(Color.YELLOW,"6 page"));
        viewPager2.setAdapter(new ViewPagerAdapter(list));
        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPager2.getOrientation()==ViewPager2.ORIENTATION_VERTICAL){
                    btnToggle.setText("가로로 슬라이드");
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                }else{
                    btnToggle.setText("세로로 슬라이드");
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

                }
            }
        });
    }
}