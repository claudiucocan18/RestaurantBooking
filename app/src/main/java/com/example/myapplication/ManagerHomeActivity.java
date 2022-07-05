package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class ManagerHomeActivity extends AppCompatActivity {

    MaterialButtonToggleGroup btnGroupManager;
    Button btnHomeManager, btnBookingsManager,btnProfileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Fooder Manager");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);

        btnGroupManager = findViewById(R.id.btnGroupManager);
        btnHomeManager = findViewById(R.id.btnHomeManager);
        btnBookingsManager = findViewById(R.id.btnBookingsManager);
        btnProfileManager = findViewById(R.id.btnProfileManager);


        btnGroupManager.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch(checkedId)
                {
                    case R.id.btnBookingsManager:{

                        Intent BookingsManagerIntent = new Intent(group.getContext(),ManagerBookingsActivity.class);
                        BookingsManagerIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(BookingsManagerIntent);
                        break;
                    }
                    case R.id.btnProfileManager: {

                        Intent ProfileManagerIntent = new Intent(group.getContext(),ManagerViewProfileActivity.class);
                        ProfileManagerIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(ProfileManagerIntent);
                        break;

                    }
                }
            }
        });

    }
}