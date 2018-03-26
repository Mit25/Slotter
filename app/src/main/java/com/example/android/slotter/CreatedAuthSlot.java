package com.example.android.slotter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CreatedAuthSlot extends AppCompatActivity {

    TextView ename,ecreator,sdate,edate,edes,ecode;
    String key,time;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    ArrayList<Slot> list = new ArrayList<Slot>();

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

        Calendar c = Calendar.getInstance();
        Date d = c.getTime() ;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        time = df.format(d);

        myRef= FirebaseDatabase.getInstance().getReference().child("Event");
        myRef.keepSynced(true);


        preparedata();

    }

    void preparedata()
    {
        Log.d("heyy event key!!!!", key);

        final Query getPass = myRef.child(key).child("SLOTDETAILS");

        Log.d("heyy event key!!!!",key);
        //Event e = new Event("a","b","c","d","g","h");
        //list.add(e);
        getPass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //list = new ArrayList<Event>();

                Log.d("HIii","asdasd");
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    //==Slot value = dataSnapshot1.getValue(Slot.class);
                    Slot fire = new Slot();

                    int sNumber= dataSnapshot1.child("sNumber").getValue(Integer.class);
                    String date = dataSnapshot1.child("date").getValue(String.class);
                    String stime= dataSnapshot1.child("stime").getValue(String.class);
                    String etime= dataSnapshot1.child("etime").getValue(String.class);
                    String uid = dataSnapshot1.child("uid").getValue(String.class);
                    Boolean isView = dataSnapshot1.child("viewToUser").getValue(Boolean.class);
                    boolean isAuth= dataSnapshot1.child("auth").getValue(Boolean.class);


                    fire.setsNumber(sNumber);
                    fire.setDate(date);
                    fire.setStime(stime);
                    fire.setEtime(etime);
                    fire.setUid(uid);
                    fire.setViewToUser(isView);
                    fire.setAuth(isAuth);
                    Log.d("heyy event name!!!!",uid);

                    if(!uid.equals("") && isView ) {
                        if(!time.equals(fire.getDate())) // must clear not from here
                            list.add(fire);
                    }
                    Log.d("size(",String.valueOf(list.size()));

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

        //Log.d("size(",String.valueOf(list.size()));
    }


    public void auth(View view)
    {
        Intent i=new Intent(getApplicationContext(),SlotAuthorise.class);
        i.putExtra("Event Key",key);
        startActivity(i);

    }

    public  void live(View v)
    {
        if(list.size()==0)
        {
            Toast.makeText(getApplicationContext(),"Today is Not session date!!!",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i =new Intent(getApplicationContext(),live.class);
            i.putExtra("Event Key",key);
            i.putExtra("List", list);
            Log.d("check", String.valueOf(list.size()));
            startActivity(i);
        }
    }
}
