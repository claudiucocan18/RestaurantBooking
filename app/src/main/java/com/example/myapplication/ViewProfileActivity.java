package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.auth.FirebaseAuth;

public class ViewProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);


        MaterialButtonToggleGroup btnGroup3;
        Button btnHome3,btnBookings3, btnProfile3, butonDelogare, butonSchimbaEmail, butonSchimbaParola;
        EditText editParolaProfil, editEmailProfil;
        TextView textEmailProfil;


        btnGroup3 = findViewById(R.id.btnGroup3);
        btnHome3 = findViewById(R.id.btnHome3);
        btnBookings3 = findViewById(R.id.btnBookings3);
        btnProfile3 = findViewById(R.id.btnProfile3);
        butonDelogare = findViewById(R.id.butonDelogare);
        butonSchimbaEmail = findViewById(R.id.butonSchimbaEmail);
        butonSchimbaParola = findViewById(R.id.butonSchimbaParola);
        textEmailProfil = findViewById(R.id.textEmailProfil);
        editParolaProfil = findViewById(R.id.editParolaProfil);
        editEmailProfil = findViewById(R.id.editEmailProfil);

        textEmailProfil.setText(mAuth.getCurrentUser().getEmail());

        butonSchimbaEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !editEmailProfil.getText().toString().isEmpty()) {
                    /*if (editEmailProfil.getText().toString().contains("@")
                            && !editEmailProfil.getText().toString().contains(".com")
                            && (!editEmailProfil.getText().toString().contains("yahoo")
                            || !editEmailProfil.getText().toString().contains("gmail"))) {*/

                        mAuth.getCurrentUser().updateEmail(editEmailProfil.getText().toString());
                        textEmailProfil.setText(mAuth.getCurrentUser().getEmail());
                        //textEmailProfil.refreshDrawableState();
                        Toast.makeText(ViewProfileActivity.this, "Adresa de e-mail a fost schimbată", Toast.LENGTH_SHORT).show();
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                    /*}*/
                }
                else {
                    Toast.makeText(ViewProfileActivity.this, "Introduceți noua adresă de e-mail", Toast.LENGTH_SHORT).show();
                }
            }
        });

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