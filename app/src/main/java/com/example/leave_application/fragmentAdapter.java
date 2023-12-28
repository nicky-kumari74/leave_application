package com.example.leave_application;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class fragmentAdapter extends FragmentStateAdapter {
    public fragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return  new dash_frag();
            case 1:
                return new holiday_frag();
            default:
                return new event_frag();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
