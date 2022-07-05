package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class ManagerBookingsActivity extends AppCompatActivity {

    MaterialButtonToggleGroup btnGroupManager2;
    Button btnHomeManager2, btnBookingsManager2,btnProfileManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Fooder Manager");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_bookings);

        btnGroupManager2 = findViewById(R.id.btnGroupManager2);
        btnHomeManager2 = findViewById(R.id.btnHomeManager2);
        btnBookingsManager2 = findViewById(R.id.btnBookingsManager2);
        btnProfileManager2 = findViewById(R.id.btnProfileManager2);


        btnGroupManager2.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch(checkedId)
                {
                    case R.id.btnHomeManager2:{

                        Intent BookingsManagerIntent2 = new Intent(group.getContext(),ManagerHomeActivity.class);
                        BookingsManagerIntent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(BookingsManagerIntent2);
                        break;
                    }
                    case R.id.btnProfileManager2: {

                        Intent ProfileManagerIntent2 = new Intent(group.getContext(),ManagerViewProfileActivity.class);
                        ProfileManagerIntent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(ProfileManagerIntent2);
                        break;

                    }
                }
            }
        });

    }
}