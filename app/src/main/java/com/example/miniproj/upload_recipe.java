package com.example.miniproj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class upload_recipe extends AppCompatActivity {

    ImageButton btn_back2;
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText name,desc,  instructionEditText;
    private StorageReference storageRef;
    ImageView ivDisp;
    private EditText ingredientEditText;
    private EditText quantityEditText;
    private Button addButton;
    private Spinner category_spinner;

    private TextView ingredientsTextView;
    private ArrayList<String> ingredientsList;
    Button btnSelectImage,btnUpload;
    Uri selectedImageUri;


    private void UploadToDB(String imageName) {
        // Access the Firebase Realtime Database instance and create a reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference categoryRef = database.getReference("categories"); // Change "categories" to your desired reference name

        // Create a unique key for the data based on the selected category
        String selectedCategory = category_spinner.getSelectedItem().toString();
        DatabaseReference categoryChildRef = categoryRef.child(selectedCategory).push();

        // Create a map with the details to be stored
        Map<String, Object> recipeMap = new HashMap<>();
        recipeMap.put("image", imageName);
        recipeMap.put("name", name.getText().toString());
        recipeMap.put("description", desc.getText().toString());
        recipeMap.put("ingredients", ingredientsTextView.getText().toString());
        recipeMap.put("category", selectedCategory);
        recipeMap.put("instructions", instructionEditText.getText().toString());

        // Set the recipe data in the Realtime Database under the selected category
        categoryChildRef.setValue(recipeMap)
                .addOnSuccessListener(aVoid -> {
                    // Data set successfully
                    Log.e("firebase", "Success");
                    // Handle success scenario
                })
                .addOnFailureListener(e -> {
                    // Error setting the data
                    Log.e("Realtime Database", "Error setting data: " + e.getMessage());
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

    private void updateIngredientsTextView() {
        StringBuilder builder = new StringBuilder();
        for (String ingredient: ingredientsList) {
            builder.append(ingredient).append("\n");
        }
        ingredientsTextView.setText(builder.toString());
    }

    private void clearFields() {
        ingredientEditText.setText("");
        quantityEditText.setText("");
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe);

        btnSelectImage  = findViewById(R.id.btnSelectImg);
        btnUpload = findViewById(R.id.btnUpload);
        ImageView btn_back2 = findViewById(R.id.btn_back2);
        ivDisp = findViewById(R.id.imageView);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        name = findViewById(R.id.text_recipe_name);
        desc = findViewById(R.id.text_description);
        ingredientEditText = findViewById(R.id.ingredientEditText);
        quantityEditText=findViewById(R.id.quantityEditText);
        category_spinner = findViewById(R.id.category_spinner);
        instructionEditText = findViewById(R.id.instructionEditText);


        addButton = findViewById(R.id.addButton);
        ingredientsTextView = findViewById(R.id.ingredientsTextView);
        Spinner categorySpinner = findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories_array, // This should be a string array resource with your category options
                android.R.layout.simple_spinner_item
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        ingredientsList = new ArrayList<>();

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open an image picker or gallery intent
                openGallery();
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                // Do something with the selected category
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredient = ingredientEditText.getText().toString().trim();
                String quantity = quantityEditText.getText().toString().trim();

                if (!ingredient.isEmpty() && !quantity.isEmpty()) {
                    String ingredientQuantity = ingredient + " - " + quantity;
                    ingredientsList.add(ingredientQuantity);

                    updateIngredientsTextView();
                    clearFields();
                }
            }
        });
    }
}