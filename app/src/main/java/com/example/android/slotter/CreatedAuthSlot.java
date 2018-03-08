package com.example.android.slotter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreatedAuthSlot extends AppCompatActivity {

    TextView ename,ecreator,sdate,edate,edes,ecode;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_auth_slot);
        ename = (TextView) findViewById(R.id.enameauth);
        ecreator = (TextView) findViewById(R.id.ecreatorauth);
        sdate = (TextView) findViewById(R.id.startDateauth);
        edate = (TextView) findViewById(R.id.endDateauth);
        ecode = (TextView) findViewById(R.id.ecodeauth);
        edes = (TextView) findViewById(R.id.eventDescriptionauth);
        key = getIntent().getStringExtra("Event Key");

        ename.setText("Event Name : " + getIntent().getStringExtra("Event Name"));
        ecreator.setText("Event Creator : " + getIntent().getStringExtra("Event Creator"));
        edes.setText("Event Description : " + getIntent().getStringExtra("Event Description"));
        sdate.setText("Event Start Date : " + getIntent().getStringExtra("Start Date"));
        edate.setText("Event End Date : " + getIntent().getStringExtra("End Date"));
        ecode.setText("Event Code : " + getIntent().getStringExtra("Event Code"));

    }

    public void auth(View view)
    {
        Intent i=new Intent(getApplicationContext(),SlotAuthorise.class);
        i.putExtra("Event Key",key);
        startActivity(i);

    }
}
