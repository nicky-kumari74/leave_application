package com.example.leave_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class dashboard extends AppCompatActivity {
ViewPager2 viewPager2;
TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        viewPager2=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tablayout);
        viewPager2.setAdapter(new fragmentAdapter(this));
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:{
                        tab.setText("Dashboard");
                        tab.setIcon(getResources().getDrawable(R.drawable.ic_baseline_dashboard_24));
                        break;
                    }
                    case 1:{
                        tab.setText("Holiday");
                        tab.setIcon(getResources().getDrawable(R.drawable.ic_baseline_message_24));
                        break;
                    }
                    default:{
                        tab.setText("Event");
                        tab.setIcon(getResources().getDrawable(R.drawable.ic_baseline_notifications_24));

                    }
                }
            }
        });tabLayoutMediator.attach();
    }
}