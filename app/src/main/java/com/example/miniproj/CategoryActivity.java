package com.example.miniproj;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private DatabaseReference categoryRef;
    private String selectedCategory;

    private ListView listView;
    private CustomAdapter customAdapter;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Retrieve the selected category from the intent extras
        selectedCategory = getIntent().getStringExtra("selectedCategory");

        // Access the Firebase Realtime Database instance and create a reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        categoryRef = database.getReference("categories");

        // Initialize the list view and adapter
        listView = findViewById(R.id.listView);
        recipeList = new ArrayList<>();
        customAdapter = new CustomAdapter(this, recipeList);
        listView.setAdapter(customAdapter);

        // Retrieve the data for the selected category
        retrieveCategoryData();
    }

    private void retrieveCategoryData() {
        categoryRef.child(selectedCategory).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve the recipe details
                    String imageName = recipeSnapshot.child("image").getValue(String.class);
                    String name = recipeSnapshot.child("name").getValue(String.class);
                    String ingredients = recipeSnapshot.child("ingredients").getValue(String.class);
                    String instructions = recipeSnapshot.child("instructions").getValue(String.class);
                    // Create a Recipe object with the retrieved data
                    Recipe recipe = new Recipe(name, ingredients,instructions, imageName);
                    recipeList.add(recipe);
                }

                // Notify the adapter that the data has changed
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Error reading the data
                Log.e("Realtime Database", "Error retrieving data: " + error.getMessage());
                // Handle the error scenario
            }
        });
    }
}