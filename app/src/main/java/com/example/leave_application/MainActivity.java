package com.example.leave_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
Spinner spinner;
TextInputEditText from_date,to_date,days;
ArrayList<String> al=new ArrayList<>();
TextView filename,submit;
ImageView img;
int week;
RadioButton first_half,second_half,both,full_day,no_doc,upload;
int cu_year,cu_month,cu_day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        from_date=findViewById(R.id.from_date);
        to_date=findViewById(R.id.to_date);
        days=findViewById(R.id.days);
        spinner=findViewById(R.id.spinner);
        first_half=findViewById(R.id.first_half);
        full_day=findViewById(R.id.full_day);
        second_half=findViewById(R.id.second_half);
        both=findViewById(R.id.both);
        no_doc=findViewById(R.id.no_doc);
        upload=findViewById(R.id.upload);
        filename=findViewById(R.id.filename);
        submit=findViewById(R.id.submit);
        al.add("Leave");
        al.add("Sick Leave");
        al.add("Emergency Leave");
        al.add("Casual Leave");
        al.add("Leave Without Pay");
        ArrayAdapter ad=new ArrayAdapter(getApplicationContext(),R.layout.selected,al);
        ad.setDropDownViewResource(R.layout.dropdown);
        spinner.setAdapter(ad);
        Calendar calendar=Calendar.getInstance();
        cu_year=calendar.get(Calendar.YEAR);
        cu_month=calendar.get(Calendar.MONTH);
        cu_day=calendar.get(Calendar.DAY_OF_MONTH);
        from_date.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialogs=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        CharSequence charSequences= DateFormat.format("dd MMM yyyy",calendar);
                        from_date.setText(charSequences);
                        cu_year=year;cu_month=month;cu_day=dayOfMonth;
                    }
                },cu_year,cu_month,cu_day);
                datePickerDialogs.show();
            }
        });
        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar1=Calendar.getInstance();
                        calendar1.set(Calendar.YEAR,year);
                        calendar1.set(Calendar.MONTH,month);
                        calendar1.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        CharSequence charSequence= DateFormat.format("dd MMM yyyy",calendar1);
                    to_date.setText(charSequence);
                   if(dayOfMonth<cu_day){
                        dayOfMonth+=31;
                        if(month==4|| month==6 || month==9 || month==11){dayOfMonth-=1;}
                        else if(month==2){dayOfMonth-=2;}
                        month--;
                    }
                    if(month<cu_month){
                        month+=12;
                        year--;
                    }
                        int d=(dayOfMonth-cu_day)+1;
                        int m=month-cu_month;

                        while(m>0){
                            d=d+30;
                            m--;
                        }
                        week=calendar.get(Calendar.DAY_OF_WEEK);
                        int dmi=d;
                        if(week==1){d--;dmi-=1;}
                        else if(week==2){if(d>=7){d--;dmi-=7;}}
                        else if(week==3){if(d>=6){d--;dmi-=6;}}
                        else if(week==4){if(d>=5){d--;dmi-=5;}}
                        else if(week==5){if(d>=4){d--;dmi-=4;}}
                        else if(week==6){if(d>=3){d--;dmi-=3;}}
                        else{if(d>=2){d--;dmi=dmi-2;}}
                        while(dmi>=7){
                            dmi-=7;
                            d--;
                        }
                        days.setText(String.valueOf(d));
                    }
                },cu_year,cu_month,cu_day);
                datePickerDialog.show();
                Log.e("week", String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)));

            }
        });
submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(MainActivity.this,leave_detail.class);
        startActivity(i);
    }
});
        //DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("users");
        //ref.push().setValue("nicky");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            String[] filepath={MediaStore.Images.Media.DATA};
            Cursor cursor=getContentResolver().query(uri,filepath,null,null,null);
            cursor.moveToFirst();
            int columnindex=cursor.getColumnIndex(filepath[0]);
            String picpath=cursor.getString(columnindex);
            cursor.close();
            String fn=picpath.substring(picpath.lastIndexOf("/")+1);
            filename.setText(fn);

        }
    }

    public void checks(View v){
    String s=v.getTag().toString();
    if(s.equals("1")){
        first_half.setTextColor(Color.WHITE);
        second_half.setTextColor(Color.parseColor("#6895AA"));
        both.setTextColor(Color.parseColor("#6895AA"));
        full_day.setTextColor(Color.parseColor("#6895AA"));
    }
    else if(s.equals("2")){
        second_half.setTextColor(Color.WHITE);
        first_half.setTextColor(Color.parseColor("#6895AA"));
        both.setTextColor(Color.parseColor("#6895AA"));
        full_day.setTextColor(Color.parseColor("#6895AA"));
    }
    else if(s.equals("3")){
        both.setTextColor(Color.WHITE);
        first_half.setTextColor(Color.parseColor("#6895AA"));
        second_half.setTextColor(Color.parseColor("#6895AA"));
        full_day.setTextColor(Color.parseColor("#6895AA"));
    }
    else{
        full_day.setTextColor(Color.WHITE);
        first_half.setTextColor(Color.parseColor("#6895AA"));
        second_half.setTextColor(Color.parseColor("#6895AA"));
        both.setTextColor(Color.parseColor("#6895AA"));
    }
}
public void upload(View v){
    String s=v.getTag().toString();
    if(s.equals("1")){
        no_doc.setTextColor(Color.WHITE);
        upload.setTextColor(Color.parseColor("#797E80"));
    }
    else{
        upload.setTextColor(Color.WHITE);
        no_doc.setTextColor(Color.parseColor("#797E80"));
        Intent gallery=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery,1);
    }
}
}