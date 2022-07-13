package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
    Button logbtn = (Button) findViewById(R.id.logbtn);
    Button signbtn = (Button) findViewById(R.id.signbtn);

    signbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openSignActivity();
        }

    });


     logbtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             openLogActivity();
         }
     });

    }

    void openSignActivity()
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    void openLogActivity()
    {
        Intent intent2 = new Intent(this, LogInActivity.class);
        startActivity(intent2);
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

}