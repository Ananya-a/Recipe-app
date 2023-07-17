package com.example.miniproj;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class expanded_layout extends AppCompatActivity {
    Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expanded_layout);

        ImageView btn_yt = findViewById(R.id.btn_yt);

        recipe = new Recipe("Recipe Name", "Ingredients", "Instructions", "Image", "videolink");

        btn_yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}