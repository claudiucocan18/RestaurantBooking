package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class ViewBookingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        MaterialButtonToggleGroup btnGroup2;
        Button btnHome2,btnBookings2, btnProfile2;

        btnGroup2 = findViewById(R.id.btnGroup2);
        btnHome2 = findViewById(R.id.btnHome2);
        btnBookings2 = findViewById(R.id.btnBookings2);
        btnProfile2 = findViewById(R.id.btnProfile2);

        btnGroup2.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch(checkedId)
                {
                    case R.id.btnHome2:{

                        Intent HomeIntent2 = new Intent(group.getContext(),ListActivity.class);
                        HomeIntent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(HomeIntent2);
                        break;
                    }
                    case R.id.btnProfile2: {

                        Intent ProfileIntent2 = new Intent(group.getContext(),ViewProfileActivity.class);
                        ProfileIntent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(ProfileIntent2);
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