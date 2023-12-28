package com.example.leave_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class dash_frag extends Fragment {

CardView leave,leave_details,leave_balance;
Activity activity;

    public dash_frag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_dash_frag, container, false);
        activity=getActivity();
        leave=v.findViewById(R.id.leave);
        leave_details=v.findViewById(R.id.leave_details);
        leave_balance=v.findViewById(R.id.leave_balance);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity,MainActivity.class);
                startActivity(i);
            }
        });
        leave_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity,leave_detail.class);
                startActivity(i);
            }
        });
        leave_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity,Leave_balance.class);
                startActivity(i);
            }
        });
        return v;
    }
}