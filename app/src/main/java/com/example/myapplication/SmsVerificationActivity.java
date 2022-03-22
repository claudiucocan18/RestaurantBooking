package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsVerificationActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_verification);
/*
        private static final int REQ_USER_CONSENT=200;
        SmsBroadcastReceiver smsBroadcastReceiver;
        EditText code_et =findViewById(R.id.code_et);
        Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        startSmartUserConsent();
        Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
    }




    private void startSmartUserConsent() {
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
            code_et.setText(matcher.group(0));
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
        unregisterReceiver(smsBroadcastReceiver);*/
    }

}