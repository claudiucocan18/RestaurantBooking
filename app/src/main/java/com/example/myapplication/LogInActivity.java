package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {


    Button logBtn;
    EditText user, pass;
    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();
        logBtn = findViewById(R.id.logButton);
        user= findViewById(R.id.UsernameEdit);
        pass= findViewById(R.id.PasswordEdit);
        String username = user.getText().toString();
        String password = pass.getText().toString();

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(user.getText().toString()) || TextUtils.isEmpty(pass.getText().toString()))
                {
                    Toast.makeText(LogInActivity.this, "All fields must be filled in", Toast.LENGTH_SHORT).show();
                }
                else {

                    trySignIn(user.getText().toString(),pass.getText().toString() );
                }
            }
        });


    }

    private void trySignIn( String userIn, String passIn) {

        mAuth.signInWithEmailAndPassword(userIn, passIn)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            enterApp();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Wrong user or password",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    private void enterApp() {
        Intent intentLogIn= new Intent(this,ListActivity.class);
        startActivity(intentLogIn);
    }
}