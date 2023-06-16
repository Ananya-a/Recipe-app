package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class upload_recipe extends AppCompatActivity {

    ImageButton btn_back2;
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText name,desc,ingre;
    private StorageReference storageRef;
    ImageView ivDisp;

    Button btnAdd,btnUpload;
    Uri selectedImageUri;

    private void UploadToDB (String imageName) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

// Access Firestore instance and create a reference to the collection
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String collectionName = firebaseAuth.getCurrentUser().getUid(); // Change this to your desired collection name

// Create a new document for the current user
        String userId = currentUser.getUid();
        DocumentReference userDocRef = firestore.collection(collectionName).document(userId);

// Create a map with the details to be stored
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("image", imageName);
        userMap.put("name", name.getText().toString());
        userMap.put("description", desc.getText().toString());
        userMap.put("ingredients", ingre.getText().toString());

// Set the document with the user details
        userDocRef.set(userMap)
                .addOnSuccessListener(aVoid -> {
                    // Document created successfully
                    Log.e("firebase","Success");
                    // Handle success scenario
                })
                .addOnFailureListener(e -> {
                    // Error creating the document
                    Log.e("Firestore", "Error creating document: " + e.getMessage());
                    // Handle failure scenario
                });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    private void uploadImage() {
        if(selectedImageUri!=null) {
            // Generate a unique image name using UUID
            String imageName = UUID.randomUUID().toString() + ".jpg";

            // Create a file reference in Firebase Storage
            StorageReference imageRef = storageRef.child("images/" + imageName);
            UploadToDB(imageName);

            // Upload the file to Firebase Storage
            imageRef.putFile(selectedImageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Image uploaded successfully
                        // Retrieve the download URL for the uploaded image
                        imageRef.getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    // Handle the download URL as needed
                                    String imageUrl = uri.toString();
                                    // Do something with the image URL (e.g., save it to a database)
                                })
                                .addOnFailureListener(exception -> {
                                    // Handle any errors that occurred during getting the download URL
                                });
                    })
                    .addOnFailureListener(exception -> {
                        // Handle any errors that occurred during the upload
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Retrieve the image URI from the intent
            selectedImageUri = data.getData();

            ivDisp.setImageURI(selectedImageUri);

        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe);

        btnAdd  = findViewById(R.id.btnSelectImg);
        btnUpload = findViewById(R.id.btnUpload);
        ImageView btn_back2 = findViewById(R.id.btn_back2);
        ivDisp = findViewById(R.id.imageView);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        name = findViewById(R.id.text_recipe_name);
        desc = findViewById(R.id.text_description);
        ingre = findViewById(R.id.text_ingredients);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open an image picker or gallery intent
                openGallery();

            }
        });


        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(upload_recipe.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });


    }
}