package com.example.android.slotter;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

public class live extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    ArrayList<Slot> list = new ArrayList<Slot>();
    String eventKey;
    String dtime;
    int cn=0, est;
    TextView uname,sno,sdate,stime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        eventKey= getIntent().getStringExtra("Event Key");
        list = (ArrayList<Slot>) getIntent().getSerializableExtra("List");

        Log.d("heyy event key!!!!",eventKey);

        myRef= FirebaseDatabase.getInstance().getReference();
        myRef.keepSynced(true);

        uname = (TextView) findViewById(R.id.lname);
        sno = (TextView) findViewById(R.id.lsno);
        sdate = (TextView) findViewById(R.id.ldate);
        stime = (TextView) findViewById(R.id.lstime);

        Log.d("check",String.valueOf(list.size()));

        setdata();

    }

    void setdata()
    {
        if(list.size()==0)
        {
            Toast.makeText(getApplicationContext(),"No User Enrolled",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),DashboardActivity.class);
            startActivity(i);
        }
        else
        {
            Slot s = list.get(cn);
            if(cn==0)
            {
                dtime = s.getStime();
                String x= s.getEtime();
                String t[] = dtime.split(":");
                String t1[] = x.split(":");
                int  min1= Integer.parseInt(t[0])*60+Integer.parseInt(t[1]);
                int  min2= Integer.parseInt(t1[0])*60+Integer.parseInt(t1[1]);
                est = min2-min1;

            }
            //Log.d("snloo",s.getsNumber());
            uname.setText(s.getUid());
            sdate.setText(s.getDate());
            stime.setText(s.getStime());
            sno.setText(s.getsNumber()+"");
        }
    }

    public void start(View v)
    {

    }

    public void stop(View v)
    {
        Calendar c = Calendar.getInstance();
        Date d = c.getTime() ;
        DateFormat df = new SimpleDateFormat("HH:mm");
        String time = df.format(d);
        String t[] = time.split(":");
        String t1[] = dtime.split(":");

        int  min1= Integer.parseInt(t[0])*60+Integer.parseInt(t[1]);
        int  min2= Integer.parseInt(t1[0])*60+Integer.parseInt(t1[1]);
        int esti = min1 - min2;


        est = (int)((esti * 0.9) + (0.1 * est));

       String email = "";
       // email = myRef.child("user").child(uname.getText().toString()).child("email").getKey();



        Log.d("your email!!",email);
        String[] recipients = {"nirajvadhaiya@gmail.com"};
        SendEmailAsyncTask emailAsyncTask = new SendEmailAsyncTask();
        //emailAsyncTask.activity = this;
        String id = "slotter2018@gmail.com";
        String pass = "Abcd1234.";
        emailAsyncTask.m = new Mail(id, pass);
        emailAsyncTask.m.set_from(id);
        emailAsyncTask.m.setBody("Hey "+ uname.getText().toString() + " Your Slot will be start within " + esti + " minitues. So Please come on Time!!!!");
        emailAsyncTask.m.set_to(recipients);
        emailAsyncTask.m.set_subject("Registered for i.Fest'17");
        emailAsyncTask.execute();

        //notificatiin set to user:::
        cn++;
        if(cn == list.size())
        {
            Toast.makeText(getApplicationContext(),"Slot Completed",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),DashboardActivity.class);
            startActivity(i);
        }
        else {
            setdata();
        }
    }
    class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
        Mail m;
        //register_new activity;

        public SendEmailAsyncTask() {}

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                if (m.send()) {
                    Log.d("****EMAIL CHECK***", "Email sent.");

                } else {
                    Log.d("****EMAIL CHECK***", "Email failed to send.");
                }

                return true;
            } catch (AuthenticationFailedException e) {
                Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
                e.printStackTrace();
                Log.d("****EMAIL CHECK***", "Authentication failed.");
                return false;
            } catch (MessagingException e) {
                Log.e(SendEmailAsyncTask.class.getName(), "Email failed");
                e.printStackTrace();
                Log.d("****EMAIL CHECK***", "Email failed to send.");
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("****EMAIL CHECK***", "Unexpected error occured.");
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
           // activity.progressDialog.dismiss();
            Log.d("****EMAIL CHECK***", "Progress Dialog dismissed.");
            //activity.successDialog();
            //activity.clearDetails();
        }
    }


}

