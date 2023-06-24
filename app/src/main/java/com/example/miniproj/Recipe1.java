package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Recipe1 extends AppCompatActivity {

//    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe1);

        ImageView back = findViewById(R.id.back);
        ImageButton likeIcon = findViewById(R.id.imageButton7);
        ImageButton veg1 = findViewById(R.id.veg1);

        veg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.youtube.com/watch?v=95BCU1n268w");
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recipe1.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });





        likeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int flag=0;
                // Change the state and color
                boolean isLiked = !sharedPreferences.getBoolean("liked", false);

                if (isLiked) {
                    flag=1;
                    likeIcon.setColorFilter(Color.RED);
                } else {
                    flag=0;
                    likeIcon.clearColorFilter();
                }

                    // Save the state and color to SharedPreferences
                    editor.putBoolean("liked", isLiked);
                    editor.putInt("likeColor", isLiked ? Color.RED : Color.TRANSPARENT);
                    editor.apply();

            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}