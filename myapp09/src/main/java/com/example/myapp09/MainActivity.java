package com.example.myapp09;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
    MyTapFragment myFrags[] = new MyTapFragment[3];
    ActionBar.Tab tabSong, tabArtist, tabAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tabSong = bar.newTab();
        tabSong.setText("음악별");
        tabSong.setTabListener(this);
        bar.addTab(tabSong);

        tabArtist = bar.newTab();
        tabArtist.setText("가수별");
        tabArtist.setTabListener(this);
        bar.addTab(tabArtist);

        tabAlbum = bar.newTab();
        tabAlbum.setText("앨범별");
        tabAlbum.setTabListener(this);
        bar.addTab(tabAlbum);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTapFragment myTapFragment = null;
        if(myFrags[tab.getPosition()]==null){
            myTapFragment = new MyTapFragment();
            Bundle data = new Bundle();
            data.putString("tabName",tab.getText().toString());

            myTapFragment.setArguments(data);
            myFrags[tab.getPosition()]=myTapFragment;

        }else{
            myTapFragment = myFrags[tab.getPosition()];
        }
        ft.replace(android.R.id.content,myTapFragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }



    public static class MyTapFragment extends Fragment{

        String tabName;
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            tabName = data.getString("tabName");


        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

            LinearLayout baseLayout = new LinearLayout(super.getActivity());
            baseLayout.setOrientation(LinearLayout.VERTICAL);
            baseLayout.setLayoutParams(params);
            if(tabName=="음악별"){
                baseLayout.setBackgroundColor(Color.RED);
            }
            if(tabName=="가수별"){
                baseLayout.setBackgroundColor(Color.GREEN);
            }
            if(tabName=="앨범별"){
                baseLayout.setBackgroundColor(Color.BLUE);
            }
            return baseLayout;
        }
    }
}