package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.R;
import com.example.myapplication.entities.Restaurant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;


public class ManagerHomeActivity extends AppCompatActivity {

    private static final String TAG = "ManagerHomeActivity";
    MaterialButtonToggleGroup btnGroupManager;
    Button btnHomeManager, btnBookingsManager,btnProfileManager,addRestaurantButton;
    EditText addRestaurantNameEdit, addOrarEdit, addAdresaEdit, addNumarLocuriEdit  ;
    ImageButton imageSelector;
    TextView hiddenLinkTextView;
    int gasit;


    Uri downloadUriHome;
    Restaurant rp;
    Restaurant restaurantGasit = new Restaurant();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    private FirebaseStorage storage;
    private StorageReference storageReference;


    public Uri imageUri;
    String imageCloudLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Fooder Manager");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);


        mAuth = FirebaseAuth.getInstance();
        storage =FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        btnGroupManager = findViewById(R.id.btnGroupManager);
        btnHomeManager = findViewById(R.id.btnHomeManager);
        btnBookingsManager = findViewById(R.id.btnBookingsManager);
        btnProfileManager = findViewById(R.id.btnProfileManager);
        addRestaurantButton=findViewById(R.id.addRestaurantButton);

        imageSelector = findViewById(R.id.imageSelector);

        addRestaurantNameEdit=findViewById(R.id.addRestaurantNameEdit);
        addOrarEdit=findViewById(R.id.addOrarEdit);
        addAdresaEdit=findViewById(R.id.addAdresaEdit);
        addNumarLocuriEdit=findViewById(R.id.addNumarLocuriEdit);

        hiddenLinkTextView=findViewById(R.id.hiddenLinkTextView);

        String s;
        s = getIntent().getStringExtra("imageCloudLink");
        if(s!=null){

            Picasso.get().load(s)
                    .placeholder(R.mipmap.ic_p_holder_foreground)
                    .into(imageSelector);

        }
        System.out.println("4 " + s);


        db.collection("restaurante")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null)
                        {
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        gasit=0;
                        for(DocumentChange dc : value.getDocumentChanges())
                        {
                            if(gasit!=0){
                                break;
                            }

                            if(dc.getType() == DocumentChange.Type.ADDED)
                            {
                                rp = dc.getDocument().toObject(Restaurant.class);

                                if(rp.getManager().equals(mAuth.getCurrentUser().getEmail()))
                                {
                                    gasit++;
                                    restaurantGasit=rp;
                                    addRestaurantNameEdit.setText(rp.getNume());
                                    addOrarEdit.setText(rp.getOrar());
                                    addAdresaEdit.setText(rp.getAdresa());
                                    addNumarLocuriEdit.setText(String.valueOf(rp.getNr_locuri()));
                                    if(s==null) {
                                        Picasso.get().load(rp.getImgURL())
                                                .placeholder(R.mipmap.ic_p_holder_foreground)
                                                .into(imageSelector);
                                        hiddenLinkTextView.setText(rp.getImgURL());
                                    }
                                    else{
                                        Picasso.get().load(s)
                                                .placeholder(R.mipmap.ic_p_holder_foreground)
                                                .into(imageSelector);

                                        hiddenLinkTextView.setText(s);

                                        //dc.getDocument().getReference().update("imgURL", s);
                                    }

                                    imageSelector.setScaleType(ImageView.ScaleType.CENTER_CROP);

                                }
                            }
                        }
                    }
                });


        imageSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentImagine = new Intent();
                intentImagine.setType("image/*");
                intentImagine.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intentImagine,1);
            }

        });


        addRestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String strNume = addRestaurantNameEdit.getText().toString();
               String strOrar = addOrarEdit.getText().toString();
               String strAdresa = addAdresaEdit.getText().toString();
               String strNumarLocuri =addNumarLocuriEdit.getText().toString();
               String strlinkImagine = hiddenLinkTextView.getText().toString();
               String strManager = mAuth.getCurrentUser().getEmail();

               System.out.println(strNume+" "+strOrar+strAdresa+" "+strNumarLocuri+" "+strlinkImagine+" "+strManager);
               Log.e("date restaurant",strNume+" "+strOrar+strAdresa+" "+strNumarLocuri+" "+s+" "+strManager);

               if(!(strNume.isEmpty() || strOrar.isEmpty()
                        || strAdresa.isEmpty() || strNumarLocuri.isEmpty())){

                    if(gasit == 0 ) {

                        if (s != null){
                            System.out.println("Nu avem restaurant deja in DB.");
                        System.out.println(strNume + " " + strOrar + strAdresa + " " + strNumarLocuri + " " + strlinkImagine + " " + strManager);

                        Restaurant restaurantNou = new Restaurant(s, strNume,
                                Integer.parseInt(strNumarLocuri), strOrar, strAdresa, strManager);

                        db.collection("restaurante")
                                .add(restaurantNou)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                        Toast.makeText(ManagerHomeActivity.this, "Restaurantul a fost adăugat", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                    }
                                });

                    }else{
                            Toast.makeText(ManagerHomeActivity.this, "Încarcă o imagine", Toast.LENGTH_SHORT).show();

                        }
                    }else if(!strlinkImagine.isEmpty()){
                        System.out.println("Exista restaurantul in DB, editam valorile.");
                        /*rp.setNume(strNume);
                        rp.setOrar(strOrar);
                        rp.setAdresa(strAdresa);
                        rp.setNr_locuri(strNumarLocuri);
                        rp.setImgURL(strlinkImagine);*/

                        //rp.setManager(strManager); Pe manager nu il editam aici, doar in ala nou
                        System.out.println(strNume+" "+strOrar+strAdresa+" "+strNumarLocuri+" "+strlinkImagine+" "+strManager);

                        db.collection("restaurante")
                                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                        if (error != null) {
                                            Log.e("Firestore error", error.getMessage());
                                            return;
                                        }

                                        for (DocumentChange dc : value.getDocumentChanges()) {
                                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                                rp = dc.getDocument().toObject(Restaurant.class);

                                                if (rp.getManager().equals(mAuth.getCurrentUser().getEmail())) {
                                                    dc.getDocument().getReference().update("nume", strNume);
                                                    dc.getDocument().getReference().update("orar", strOrar);
                                                    dc.getDocument().getReference().update("adresa", strAdresa);
                                                    dc.getDocument().getReference().update("nr_locuri", Integer.valueOf(strNumarLocuri));
                                                    dc.getDocument().getReference().update("imgURL", strlinkImagine);
                                                    Toast.makeText(ManagerHomeActivity.this, "Datele au fost salvate", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        }
                                    }
                                });
                    }else{
                        Toast.makeText(ManagerHomeActivity.this, "Încarcă o imagine. Imaginea nu a fost găsită", Toast.LENGTH_SHORT).show();
                    }



                }else{
                    Toast.makeText(ManagerHomeActivity.this,
                            "Introduceți toate datele și adăugați o imagine",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnGroupManager.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch(checkedId)
                {
                    case R.id.btnBookingsManager:{

                        Intent BookingsManagerIntent = new Intent(group.getContext(), ManagerBookingsActivity.class);
                        BookingsManagerIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(BookingsManagerIntent);
                        break;
                    }
                    case R.id.btnProfileManager: {

                        Intent ProfileManagerIntent = new Intent(group.getContext(), ManagerViewProfileActivity.class);
                        ProfileManagerIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(ProfileManagerIntent);
                        break;
                    }
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            //imageSelector.setImageURI(imageUri);
            uploadSelectedPicture();
        }
    }


    private void uploadSelectedPicture(){

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading image...");
        pd.show();
        String randomKey = UUID.randomUUID().toString();
        StorageReference ref = storageReference.child(restaurantGasit.getNume()+" "+randomKey);

        //incarcam imaginea
        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        pd.dismiss();
                        taskSnapshot.getMetadata().getCustomMetadataKeys();
                        Snackbar.make(findViewById(android.R.id.content),"Image uploaded",Snackbar.LENGTH_LONG).show();

//                        Task<Uri> urlTask = ref.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                            @Override
//                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                                if (!task.isSuccessful()) {
//                                    throw task.getException();
//                                }
//
//                                // Continue with the task to get the download URL
//                                return ref.getDownloadUrl();
//                            }
//                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Uri> task) {
//                                if (task.isSuccessful()) {
//                                    Uri downloadUri;
//                                    downloadUri = task.getResult();
//                                    System.out.println(downloadUri);
//                                    System.out.println("A ajuns aici1");
//
//                                    Intent IntentReload = new Intent(getBaseContext() ,ManagerHomeActivity.class);
//                                    IntentReload.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                                    IntentReload.putExtra("link",downloadUri);
//                                    startActivity(IntentReload);
//
//
//                                } else {
//                                    // Handle failures
//                                    // ...
//                                }
//                            }
//                        });
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageCloudLink = uri.toString();

//                                db.collection("restaurante")
//                                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                                            @Override
//                                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                                                if(error != null)
//                                                {
//                                                    Log.e("Firestore error",error.getMessage());
//                                                    return;
//                                                }
//                                                for(DocumentChange dc : value.getDocumentChanges())
//                                                {
//                                                    if(dc.getType() == DocumentChange.Type.ADDED)
//                                                    {
//                                                        Restaurant rp = dc.getDocument().toObject(Restaurant.class);
//
//                                                        if(rp.getManager().equals(mAuth.getCurrentUser().getEmail()))
//                                                        {
//                                                            dc.getDocument().getReference().update("imgURL", imageCloudLink);
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        });

                                Intent reloadIntent = new Intent(getBaseContext(),ManagerHomeActivity.class);
                                reloadIntent.putExtra("imageCloudLink",imageCloudLink);
                                reloadIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(reloadIntent);

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(ManagerHomeActivity.this, "Image upload failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Complet: " + (int) progressPercent + "%");
                    }
                });

        //obtinem url pt adaugarea pe restaurant
    }

}