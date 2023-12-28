package com.example.leave_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class loginpage extends AppCompatActivity {
Button login;
RadioButton admin,employee;
String s="2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        login=findViewById(R.id.login);
        admin=findViewById(R.id.admin);
        employee=findViewById(R.id.employee);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.equals("2")){
                Intent i=new Intent(loginpage.this,dashboard.class);
                startActivity(i);
                finish();}
                else{
                    Intent i=new Intent(loginpage.this,admin_activity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    public void upload(View v) {
        s = v.getTag().toString();
        if (s.equals("1")) {
            admin.setTextColor(Color.WHITE);
            employee.setTextColor(Color.parseColor("#797E80"));
        } else {
            employee.setTextColor(Color.WHITE);
            admin.setTextColor(Color.parseColor("#797E80"));

        }
    }
}