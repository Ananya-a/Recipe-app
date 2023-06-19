package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class expanded_layout extends AppCompatActivity {

    ImageView btn_liked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expanded_layout);

        ImageView btnLiked = findViewById(R.id.btn_liked);
        btnLiked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the outline color to red
                btnLiked.setBackgroundColor(Color.RED);
            }
        });

    }
}