package com.example.android.slotter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class addEvent extends AppCompatActivity {

    private TextView mDisplaystartDate,mDisplayendDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener1;
    DatabaseReference databaseevent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        databaseevent = FirebaseDatabase.getInstance().getReference();

        mDisplaystartDate = (TextView) findViewById(R.id.startDate);

        mDisplayendDate = (TextView) findViewById(R.id.endDate);

        mDisplaystartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        addEvent.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String date = month + "/" + day + "/" + year;
                mDisplaystartDate.setText(date);
            }
        };

        mDisplayendDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        addEvent.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String date = month + "/" + day + "/" + year;
                mDisplayendDate.setText(date);
            }
        };


    }

    public void addEvent1(View v)
    {
        EditText eventName = (EditText) findViewById(R.id.ename);
        EditText sdate = (EditText) findViewById(R.id.startDate);
        EditText edate = (EditText) findViewById(R.id.endDate);
        EditText edescription = (EditText) findViewById(R.id.eventDescription);
        EditText ecode = (EditText) findViewById(R.id.ecode);
        

        SharedPreferences sp = getSharedPreferences("key", Context.MODE_PRIVATE);
        String uname = sp.getString("value" , null);

        Log.d("uname",uname);

        Event e = new Event(eventName.getText().toString(),sdate.getText().toString(),edate.getText().toString(),edescription.getText().toString(),ecode.getText().toString(),uname);

        String id = databaseevent.push().getKey();
        databaseevent.child("Event").child(id).setValue(e);


        //IMPLEMENT :: Event code is unique

    }
}
