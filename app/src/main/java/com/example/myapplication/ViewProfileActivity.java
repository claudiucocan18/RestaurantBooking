package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class ViewProfileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        MaterialButtonToggleGroup btnGroup3;
        Button btnHome3,btnBookings3, btnProfile3;

        btnGroup3 = findViewById(R.id.btnGroup3);
        btnHome3 = findViewById(R.id.btnHome3);
        btnBookings3 = findViewById(R.id.btnBookings3);
        btnProfile3 = findViewById(R.id.btnProfile3);


        btnGroup3.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch(checkedId)
                {
                    case R.id.btnHome3:{

                        Intent HomeIntent3 = new Intent(group.getContext(),ListActivity.class);
                        HomeIntent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(HomeIntent3);
                        break;
                    }
                    case R.id.btnBookings3: {

                        Intent ProfileIntent3 = new Intent(group.getContext(),ViewBookingsActivity.class);
                        ProfileIntent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(ProfileIntent3);
                        break;

                    }

                }
            }
        });


    }
    @Override
    public void onBackPressed() {
        // Do nothing
    }

}