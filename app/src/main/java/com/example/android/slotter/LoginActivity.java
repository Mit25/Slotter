package com.example.android.slotter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public boolean validateCredentials(String uname, String pwd){
        if(uname.length()==0){
            Toast.makeText(getApplicationContext(),"Enter username",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(pwd.length()==0){
            Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if ( pwd.length() < 4 || pwd.length() > 10) {
            Toast.makeText(getApplicationContext(),"Enter password between 4 and 10 alphanumeric characters",Toast.LENGTH_SHORT).show();
            return false;
        }

        //Retrive password from database given username
        //SELECT password FROM User WHERE usernams=uname;

        if(uname.equals("admin") && pwd.equals("123456")){
            Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void loginFailed(Button login){
        Toast.makeText(getApplicationContext(),"Retry",Toast.LENGTH_SHORT).show();
        login.setEnabled(true);
    }

    public void showProgressRing(){
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Logging...");
        progressDialog.setTitle("ProgressDialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    public void Login(View v){
        Button login=(Button) findViewById(R.id.btn_login);
        EditText uname=(EditText) findViewById(R.id.etxt_uname);
        EditText pwd=(EditText) findViewById(R.id.etxt_pwd);

        login.setEnabled(false);

        if(!validateCredentials(uname.getText().toString(),pwd.getText().toString())){
            loginFailed(login);
            return;
        }

        //show progressbar
        showProgressRing();
        //redirect to dashboard activity

        Intent goToNextActivity = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(goToNextActivity);
    }

    public void signup(View v){
        Intent goToNextActivity = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(goToNextActivity);
    }
}
