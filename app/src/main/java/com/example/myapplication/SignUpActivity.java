package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    //private static final int REQ_USER_CONSENT=200;
    EditText username, password, repassword;
    Button signbtn;
    DBHelper DB;
    //SmsBroadcastReceiver smsBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.UsernameEdit);
        password = findViewById(R.id.PasswordEdit);
        repassword = findViewById(R.id.RePasswordEdit);
        signbtn = findViewById(R.id.signbtn);
        DB = new DBHelper(this);

       //startSmartUserConsent();

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String user= username.getText().toString();
            String pass= password.getText().toString();
            String repass= repassword.getText().toString();

                //openSmsActivity();



            if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(repass))
            {
                Toast.makeText(SignUpActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
            }

            else {
                if (pass.equals(repass)) {
                    Boolean checkuser = DB.checkusername(user);
                    if (checkuser == false) {
                        Boolean insert = DB.insertData(user, pass, "Default");
                        if (insert == true) {
                            Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                            //openSmsActivity();

                        } else {
                            Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                        else {
                                Toast.makeText(SignUpActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                             }
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    }
            }
            }
        });


    }

    /*private void openSmsActivity() {

        Intent intent5 = new Intent(this,SmsVerificationActivity.class);

        startActivity(intent5);

    }*/














    /*private void startSmartUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsUserConsent(null);


    }
    private void registerBroadcastReciever()
    {
        smsBroadcastReceiver = new SmsBroadcastReceiver();
        smsBroadcastReceiver.smsBroadcastReceiverListener = new SmsBroadcastReceiver.SmsBroadcastReceiverListener() {
            @Override
            public void onSuccess(Intent intent) {

                startActivityForResult(intent, REQ_USER_CONSENT);
            }

            @Override
            public void onFailure() {

            }
        };
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_USER_CONSENT)
        {
            if((resultCode == RESULT_OK) && (data != null))
            {
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                getOtpFromMessage(message);
            }
        }
    }

    private void getOtpFromMessage(String message) {
        Pattern otpPattern= Pattern.compile("(|^)\\d{6}");
        Matcher matcher = otpPattern.matcher(message);
        if(matcher.find()){
           // etOTP.setText(matcher.group(0));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReciever();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsBroadcastReceiver);
    }*/
}