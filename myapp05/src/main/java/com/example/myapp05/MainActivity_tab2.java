package com.example.myapp05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity_tab2 extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab2);

        TabHost tabHost = getTabHost();

        TabSpec tabSpecDog = tabHost.newTabSpec("DogImg").setIndicator("강아지");
        tabSpecDog.setContent(R.id.tabDog);
        tabHost.addTab(tabSpecDog);

        TabSpec tabSpecCat = tabHost.newTabSpec("CatImg").setIndicator("고양이");
        tabSpecCat.setContent(R.id.tabCat);
        tabHost.addTab(tabSpecCat);

        TabSpec tabSpecRabbit = tabHost.newTabSpec("RabbitImg").setIndicator("토끼");
        tabSpecRabbit.setContent(R.id.tabRabbit);
        tabHost.addTab(tabSpecRabbit);

        TabSpec tabSpecHorse = tabHost.newTabSpec("HorseImg").setIndicator("말");
        tabSpecHorse.setContent(R.id.tabHorse);
        tabHost.addTab(tabSpecHorse);

        tabHost.setCurrentTab(0);
    }
}