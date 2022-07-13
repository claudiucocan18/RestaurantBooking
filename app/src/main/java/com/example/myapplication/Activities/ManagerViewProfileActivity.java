package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.auth.FirebaseAuth;


public class ManagerViewProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    MaterialButtonToggleGroup btnGroupManager3;
    Button btnHomeManager3, btnBookingsManager3,btnProfileManager3;
    Button butonSchimbaParolaManager, butonDelogareManager;
    TextView textEmailProfilManager;
    EditText editParolaProfilManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Fooder Manager");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_profile);

        btnGroupManager3 = findViewById(R.id.btnGroupManager3);
        btnHomeManager3 = findViewById(R.id.btnHomeManager3);
        btnBookingsManager3 = findViewById(R.id.btnBookingsManager3);
        btnProfileManager3 = findViewById(R.id.btnProfileManager3);
        butonSchimbaParolaManager = findViewById(R.id.butonSchimbaParolaManager);
        butonDelogareManager=findViewById(R.id.butonDelogareManager);
        editParolaProfilManager = findViewById(R.id.editParolaProfilManager);
        textEmailProfilManager = findViewById(R.id.textEmailProfilManager);

        textEmailProfilManager.setText(mAuth.getCurrentUser().getEmail());

        butonSchimbaParolaManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !editParolaProfilManager.getText().toString().isEmpty()) {

                    if (editParolaProfilManager.getText().length() >= 6) {

                        mAuth.getCurrentUser().updatePassword(editParolaProfilManager.getText().toString());
                        Toast.makeText(ManagerViewProfileActivity.this, "Parola a fost schimbată cu succes", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ManagerViewProfileActivity.this, "Parola trebuie să conțină minim 6 caractere", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ManagerViewProfileActivity.this, "Introduceți noua parolă", Toast.LENGTH_SHORT).show();
                }
            }
        });

        butonDelogareManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intentSignedOut = new Intent(view.getContext(), MainActivity.class);
                startActivity(intentSignedOut);
            }
        });

        btnGroupManager3.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch(checkedId)
                {
                    case R.id.btnBookingsManager3:{

                        Intent BookingsManagerIntent3 = new Intent(group.getContext(), ManagerBookingsActivity.class);
                        BookingsManagerIntent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(BookingsManagerIntent3);
                        break;
                    }
                    case R.id.btnHomeManager3: {

                        Intent ProfileManagerIntent3 = new Intent(group.getContext(), ManagerHomeActivity.class);
                        ProfileManagerIntent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(ProfileManagerIntent3);
                        break;

                    }
                }
            }
        });

    }
}