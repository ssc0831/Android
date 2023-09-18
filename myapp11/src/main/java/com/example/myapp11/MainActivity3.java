package com.example.myapp11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TabLayout tabLayout = findViewById(R.id.layout_tab);
        ViewPager2 viewPager2 = findViewById(R.id.pager_content);
        ContentPagerAdapter contentPagerAdapter = new ContentPagerAdapter(this);

        viewPager2.setAdapter(contentPagerAdapter);
        List<String> tabElement = Arrays.asList("tab1","tab2","tab3");

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(MainActivity3.this);
                textView.setText(tabElement.get(position));
                tab.setCustomView(textView);
            }
        }).attach();
    }
}