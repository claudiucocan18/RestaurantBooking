package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class ConfirmationActivity extends AppCompatActivity {

    Button butonInapoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        butonInapoi = findViewById(R.id.butonInapoi);

        butonInapoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ReturnIntent = new Intent(view.getContext(), ListActivity.class);
                startActivity(ReturnIntent);
            }
        });

    }
}