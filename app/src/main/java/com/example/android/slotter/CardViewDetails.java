package com.example.android.slotter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CardViewDetails extends AppCompatActivity {

    TextView ename,ecreator,sdate,edate,edes,ecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_details);
        ename = (TextView) findViewById(R.id.ename1);
        ecreator = (TextView) findViewById(R.id.ecreator1);
        sdate = (TextView) findViewById(R.id.startDate1);
        edate = (TextView) findViewById(R.id.endDate1);
        ecode = (TextView) findViewById(R.id.ecode1);
        edes = (TextView) findViewById(R.id.eventDescription1);

        ename.setText("Event Name : " + getIntent().getStringExtra("Event Name"));
        ecreator.setText("Event Creator : " + getIntent().getStringExtra("Event Creator"));
        edes.setText("Event Description : " + getIntent().getStringExtra("Event Description"));
        sdate.setText("Event Start Date : " + getIntent().getStringExtra("Start Date"));
        edate.setText("Event End Date : " + getIntent().getStringExtra("End Date"));
        ecode.setText("Event Code : " + getIntent().getStringExtra("Event Code"));


    }
}
