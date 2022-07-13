package com.example.myapplication.Activities;

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

import com.example.myapplication.R;
import com.example.myapplication.entities.Utilizator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LogInActivity extends AppCompatActivity {


    Button logBtn;
    EditText userText, passText;

    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Utilizator");

    private static final String TAG = "LoginActivity";

    String email, parola, tipCont;
    List<Utilizator> listaUtilizatori = new ArrayList<Utilizator>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();
        logBtn = findViewById(R.id.logButton);
        userText = findViewById(R.id.UsernameEdit);
        passText = findViewById(R.id.PasswordEdit);
        String username = userText.getText().toString();
        String password = passText.getText().toString();

        //user list din live database
        myRef.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot ds : postSnapshot.getChildren()) {
                        switch (ds.getKey()) {
                            case "email": {
                                email = ds.getValue().toString();
                                break;
                            }
                            case "parola": {
                                parola = ds.getValue().toString();
                                break;
                            }
                            case "tipCont": {
                                tipCont = ds.getValue().toString();
                                break;
                            }
                        }
                    }
                    listaUtilizatori.add(new Utilizator(email, parola, tipCont));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("tag", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });


        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(userText.getText().toString()) || TextUtils.isEmpty(passText.getText().toString())) {
                    Toast.makeText(LogInActivity.this, "All fields must be filled in", Toast.LENGTH_SHORT).show();
                } else {

                    trySignIn(userText.getText().toString(), passText.getText().toString());
                }
            }
        });


    }

    private void trySignIn(String userIn, String passIn) {

        mAuth.signInWithEmailAndPassword(userIn, passIn)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            enterAppClient(userIn);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Wrong user or password",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    private void enterAppClient(String userIn) {

        String tipUserIntrodus="";

        for (Utilizator u1 : listaUtilizatori) {
            if (userIn.equals(u1.getEmail())) {
                tipUserIntrodus = u1.getTipCont();
                Log.e("tipUserIntrodus:", tipUserIntrodus);
            }
        }
        Log.e("tipUserIntrodus",tipUserIntrodus);
        if( tipUserIntrodus.equals("Client") ) {
            Intent intentLogIn1 = new Intent(this, ListActivity.class);
            startActivity(intentLogIn1);
        }
        else if( tipUserIntrodus.equals("Manager") ) {
            Intent intentLogIn2 = new Intent(this, ManagerHomeActivity.class);
            startActivity(intentLogIn2);
        }
    }
}