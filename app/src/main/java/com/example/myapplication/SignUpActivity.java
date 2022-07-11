package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    //private static final int REQ_USER_CONSENT=200;
    EditText username, password, repassword, cuiEdit;
    Button signbtn;
    Switch switch1;
    CheckBox checkBox;
    TextView switchStateText;
    Utilizator utilizator;
    DBHelper DB;
    private FirebaseAuth mAuth;
    private static final String TAG = "SigninActivity";

    DaoUtilizator daoUtilizator = new DaoUtilizator();
    //SmsBroadcastReceiver smsBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.UsernameEdit);
        password = findViewById(R.id.PasswordEdit);
        repassword = findViewById(R.id.RePasswordEdit);
        signbtn = findViewById(R.id.signbtn);
        checkBox=findViewById(R.id.checkbox);
        cuiEdit= findViewById(R.id.cuiEdit);
       //switch1=findViewById(R.id.switch1);

        switchStateText=findViewById(R.id.switchStateText);


        //DB = new DBHelper(this);

       //startSmartUserConsent();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //pe ecran se vede textul de la CheckBox, nu de la TextView
                if(checkBox.isChecked())
                {
                    switchStateText.setText("Manager");
                    cuiEdit.setVisibility(View.VISIBLE);


                }
                else
                {
                    switchStateText.setText("Client");
                    cuiEdit.setVisibility(View.GONE);

                }


            }
        });


/*switch1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true)
                {
                    System.out.println("Manageeeeeeeer");
                    switchStateText.setText("Manager");
                }
                if(b==false)
                {
                    System.out.println("Client");
                    switchStateText.setText("Client");
                }

            }
        });
    }
});*/

        signbtn.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           String user = username.getText().toString();
                                           String pass = password.getText().toString();
                                           String repass = repassword.getText().toString();
                                           String tipCont = switchStateText.getText().toString();
                                           String cui = cuiEdit.getText().toString();

    if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)) {

        Toast.makeText(SignUpActivity.this, "All fields required", Toast.LENGTH_SHORT).show();


    }

        else {
            if (pass.equals(repass)) {

                if (pass.length() >= 6) {

                            if( cuiEdit.getVisibility() !=View.GONE && TextUtils.isEmpty(cui)) {
                                Toast.makeText(SignUpActivity.this, "Codul CUI este obligatoriu pentru manageri", Toast.LENGTH_SHORT).show();
                            }
                            else{
                            signUpUser(mAuth, user, pass);
                            //client sau manager pt constructor Utilizator
                            if(tipCont == "Client"){
                                 utilizator = new Utilizator(user, pass, tipCont);
                            }
                            else {
                                utilizator = new Utilizator(user, pass, tipCont, cui);
                            }

                            daoUtilizator.add(utilizator);}
                        }


                        else {Toast.makeText(SignUpActivity.this, "Passwords must contain at least 6 characters", Toast.LENGTH_SHORT).show();}

                }
                    else{ Toast.makeText(SignUpActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();}
                }
            }
        });

    }

    private void signUpUser(FirebaseAuth mAuth,String user, String pass) {
        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            enterApp();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            if(!user.contains("@") || !user.contains(".com")
                                    || (!user.contains("yahoo") || !user.contains("gmail")) )
                            {
                                Toast.makeText(SignUpActivity.this, "Incorrect email address ", Toast.LENGTH_SHORT).show();
                            }
                            //Toast.makeText(SignUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void enterApp() {

        Intent intentSignUp= new Intent(this,MainActivity.class);
        startActivity(intentSignUp);

    }

    };








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
