package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class expanded_layout extends AppCompatActivity {

//    ImageView btn_liked, btn_yt;
    Recipe recipe; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expanded_layout);

//        ImageView btnLiked = findViewById(R.id.btn1);
        ImageView btn_yt = findViewById(R.id.btn_yt);


        recipe = new Recipe("Recipe Name", "Ingredients", "Instructions", "Image"); // Replace with your actual recipe details

        btn_yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        btnLiked.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                boolean isLiked = !sharedPreferences.getBoolean("liked", false);
//
//                if (isLiked) {
//                    btnLiked.setColorFilter(Color.RED);
//                    // Move the recipe card to the Liked Recipes page
//                    Intent intent = new Intent(expanded_layout.this, Likedrecipesactivity.class);
//                    intent.putExtra("isLiked", true);
//                    intent.putExtra("recipeName", recipe.getName());
//                    intent.putExtra("recipeIngredients", recipe.getIngredients());
//                    intent.putExtra("recipeInstructions", recipe.getInstructions());
//                    startActivity(intent);
//                } else {
//                    btnLiked.clearColorFilter();
//                    // If you want to remove the recipe from the Liked Recipes page, you can implement that logic here
//                }
//
//                // Save the state and color to SharedPreferences
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean("liked", isLiked);
//                editor.putInt("likeColor", isLiked ? Color.RED : Color.TRANSPARENT);
//                editor.apply();
//            }
//        });
    }
}