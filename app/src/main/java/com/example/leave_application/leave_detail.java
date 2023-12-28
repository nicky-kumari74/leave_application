package com.example.leave_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class leave_detail extends AppCompatActivity {
RecyclerView rv;
ImageView back;
Spinner leave_spinner,year_spinner;
ArrayList<String> list1=new ArrayList<>();
ArrayList<String> list2=new ArrayList<>();
ArrayList<String> list3=new ArrayList<>();
ArrayList<String> list4=new ArrayList<>();
String[] st={"2023","2022","2021"};
String[] st2={"Leave"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_detail);
        rv=findViewById(R.id.rv);
        back=findViewById(R.id.back);
        leave_spinner=findViewById(R.id.leave_spinner);
        year_spinner=findViewById(R.id.year_spinner);
        ArrayAdapter ad=new ArrayAdapter(getApplicationContext(),R.layout.selected,st);
        ad.setDropDownViewResource(R.layout.dropdown);
        year_spinner.setAdapter(ad);
        ArrayAdapter ad2=new ArrayAdapter(getApplicationContext(),R.layout.selected,st2);
        ad.setDropDownViewResource(R.layout.dropdown);
        leave_spinner.setAdapter(ad2);
        list1.add("SHL");list1.add("Co");list1.add("EL");
        list2.add("25-Jul-2023");list2.add("08-Jun-2023");list2.add("12-Aug-2023");
        list3.add("29-Juy-2023");list3.add("15-Jun-2023");list3.add("20-Aug-2023");
        list4.add("Approved");list4.add("Approved");list4.add("Approved");
        rv.setLayoutManager(new LinearLayoutManager(this));
        myadapter myadapter=new myadapter(this,list1,list2,list3,list4);
        rv.setAdapter(myadapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(leave_detail.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}