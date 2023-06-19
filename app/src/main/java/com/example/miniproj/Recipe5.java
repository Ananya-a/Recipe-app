package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Recipe5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe5);

        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recipe5.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton likeIcon = findViewById(R.id.imageButton6);
        likeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Change the state and color
                boolean isLiked = !sharedPreferences.getBoolean("liked", false);

                if (isLiked) {
                    likeIcon.setColorFilter(Color.RED);
                } else {
                    likeIcon.clearColorFilter();
                }

                // Save the state and color to SharedPreferences
                editor.putBoolean("liked", isLiked);
                editor.putInt("likeColor", isLiked ? Color.RED : Color.TRANSPARENT);
                editor.apply();
            }
        });
    }
}