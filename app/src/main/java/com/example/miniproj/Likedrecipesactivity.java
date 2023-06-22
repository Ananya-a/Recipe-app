package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Likedrecipesactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_recipes);

//                likedRecipesLayout = findViewById(R.id.likedRecipesLayout);
//
//                Intent intent = getIntent();
//                boolean isLiked = intent.getBooleanExtra("isLiked", false);
//
//                if (isLiked) {
//                    String recipeName = intent.getStringExtra("recipeName");
//                    String recipeIngredients = intent.getStringExtra("recipeIngredients");
//                    String recipeInstructions = intent.getStringExtra("recipeInstructions");
//
//                    // Create a new layout for the liked recipe
//                    View likedRecipeView = getLayoutInflater().inflate(R.layout.liked_recipe_item, null);
//
//                    TextView recipeNameTextView = likedRecipeView.findViewById(R.id.expandedNameTextView);
//                    TextView recipeIngredientsTextView = likedRecipeView.findViewById(R.id.expandedIngredientsTextView);
//                    TextView recipeInstructionsTextView = likedRecipeView.findViewById(R.id.expandedInstructionsTextView);
//
//                    recipeNameTextView.setText(recipeName);
//                    recipeIngredientsTextView.setText(recipeIngredients);
//                    recipeInstructionsTextView.setText(recipeInstructions);
//
//                    // Add the liked recipe layout to the main layout
//                    likedRecipesLayout.addView(likedRecipeView);
//                }
//        }

    }
}