package com.example.android.slotter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpcomingEnroll extends AppCompatActivity {

    TextView ename,ecreator,sdate,edate,edes,ecode;
    DatabaseReference myRef ;
    String randkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_enroll);
        ename = (TextView) findViewById(R.id.enameenrl);
        ecreator = (TextView) findViewById(R.id.ecreatorenrl);
        sdate = (TextView) findViewById(R.id.startDateenrl);
        edate = (TextView) findViewById(R.id.endDateenrl);
        ecode = (TextView) findViewById(R.id.ecodeenrl);
        edes = (TextView) findViewById(R.id.eventDescriptionenrl);
        randkey = getIntent().getStringExtra("Event Key");
       // Log.d("ekey !!!",randkey);
        ename.setText("Event Name : " + getIntent().getStringExtra("Event Name"));
        ecreator.setText("Event Creator : " + getIntent().getStringExtra("Event Creator"));
        edes.setText("Event Description : " + getIntent().getStringExtra("Event Description"));
        sdate.setText("Event Start Date : " + getIntent().getStringExtra("Start Date"));
        edate.setText("Event End Date : " + getIntent().getStringExtra("End Date"));
        ecode.setText("Event Code : " + getIntent().getStringExtra("Event Code"));
    }

    public void enrl(View view)
    {
        SharedPreferences sp = getSharedPreferences("key", Context.MODE_PRIVATE);
        String uname = sp.getString("value" , null);
        myRef= FirebaseDatabase.getInstance().getReference().child("user").child(uname).child("REGISTEREVENT");
        String key = myRef.push().getKey();
        myRef.child(key).child("eKey").setValue(randkey);
        myRef.keepSynced(true);


    }
}

