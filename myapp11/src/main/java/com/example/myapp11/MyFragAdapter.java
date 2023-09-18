package com.example.myapp11;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

//FragmentStateAdapter = recyclerview가 내장되어있는 Adapter
public class MyFragAdapter extends FragmentStateAdapter {
    private int mCount;

    public MyFragAdapter(@NonNull FragmentActivity fragmentActivity, int mCount) {
        super(fragmentActivity);
        this.mCount = mCount;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        if(index==0) return new OneFragment();
        else if(index==1) return new TwoFragment();
        else if(index==2) return new ThreeFragment();
        else return new FourFragment();
    }

    @Override
    public int getItemCount() {
        return 200;
    }

    private int getRealPosition(int position) {
        return position%mCount;
    }
}
