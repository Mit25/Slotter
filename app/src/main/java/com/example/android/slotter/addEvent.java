package com.example.android.slotter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class addEvent extends AppCompatActivity {

    private TextView mDisplaystartDate,mDisplayendDate,mDisplaystatTime,mDisplayendTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener1;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener,mTimeSetListener1;
    DatabaseReference databaseevent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        databaseevent = FirebaseDatabase.getInstance().getReference();

        mDisplaystartDate = (TextView) findViewById(R.id.startDate);
        mDisplayendDate = (TextView) findViewById(R.id.endDate);

        mDisplaystatTime = (TextView) findViewById(R.id.stime);
        mDisplayendTime = (TextView) findViewById(R.id.etime);

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

        mDisplaystatTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(addEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mDisplaystatTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        mDisplayendTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(addEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mDisplayendTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }

    public boolean slotCheccker(String sTime,String eTime,String noSlot,String interval,String slotDu)
    {
        String stime[] = sTime.split(":");
        String etime[] = eTime.split(":");
        int slot = Integer.parseInt(noSlot);
        int inter = Integer.parseInt(interval);
        int duration = Integer.parseInt(slotDu);

        int h = Integer.parseInt(etime[0])-Integer.parseInt(stime[0]);
        int m = Integer.parseInt(etime[1])-Integer.parseInt(stime[1]);

        int total = h+m;
        int need = inter*(slot-1)+slot*duration;
        if(total<need)
            return false;

        return true;
    }

    public void addEvent1(View v)
    {
        EditText eventName = (EditText) findViewById(R.id.ename);
        TextView sdate = (TextView) findViewById(R.id.startDate);
        TextView edate = (TextView) findViewById(R.id.endDate);
        EditText edescription = (EditText) findViewById(R.id.eventDescription);
        EditText ecode = (EditText) findViewById(R.id.ecode);
        EditText noSlot = (EditText) findViewById(R.id.noSlot);
        EditText slotDu = (EditText) findViewById(R.id.slotdur);
        EditText interval = (EditText) findViewById(R.id.slotint);
        TextView sTime = (TextView) findViewById(R.id.stime);
        TextView eTime = (TextView) findViewById(R.id.etime);
        /*sdate.setPaintFlags(sdate.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        edate.setPaintFlags(edate.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        sTime.setPaintFlags(sTime.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        eTime.setPaintFlags(eTime.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);*/

        SharedPreferences sp = getSharedPreferences("key", Context.MODE_PRIVATE);
        String uname = sp.getString("value" , null);

        Log.d("uname",uname);

        if(eventName.getText().toString().length()==0 || sdate.getText().toString().length()==0|| edate.getText().toString().length()==0 || edescription.getText().toString().length()==0
                || ecode.getText().toString().length()==0 || noSlot.getText().toString().length()==0 || slotDu.getText().toString().length()==0 || interval.getText().toString().length()==0
        || sTime.getText().toString().length()==0 || eTime.getText().toString().length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Retry",Toast.LENGTH_SHORT).show();
            noSlot.getText().clear();
            slotDu.getText().clear();
            interval.getText().clear();
            return;
        }
        else
        {
            if(!slotCheccker(sTime.getText().toString(),eTime.getText().toString(),noSlot.getText().toString(),interval.getText().toString(),slotDu.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"Reenter No. of slot",Toast.LENGTH_SHORT).show();
            }
            else{
            Toast.makeText(getApplicationContext(),"Event Added Successfully",Toast.LENGTH_SHORT).show();
            Intent goToNextActivity = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(goToNextActivity);
            }
        }

        Event e = new Event(eventName.getText().toString(),sdate.getText().toString(),edate.getText().toString(),edescription.getText().toString(),ecode.getText().toString(),uname);

        String id = databaseevent.push().getKey();
        databaseevent.child("Event").child(id).setValue(e);


        //IMPLEMENT :: Event code is unique

    }
}
