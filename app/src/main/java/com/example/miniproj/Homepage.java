package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Homepage extends AppCompatActivity {

    ImageButton non1, veg1, nav_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ImageButton non1 = findViewById(R.id.non1);
        ImageButton veg1 = findViewById(R.id.veg1);
        ImageButton mex1 = findViewById(R.id.mex1);
        ImageButton ita1 = findViewById(R.id.ita1);
        ImageButton fes1 = findViewById(R.id.fes1);
        ImageButton nav_out = findViewById(R.id.nav_out);

        non1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Recipe1.class);
                startActivity(intent);
                finish();
            }
        });

        veg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Recipe2.class);
                startActivity(intent);
                finish();
            }
        });

        mex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Recipe3.class);
                startActivity(intent);
                finish();
            }
        });
        ita1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Recipe4.class);
                startActivity(intent);
                finish();
            }
        });
        fes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Recipe5.class);
                startActivity(intent);
                finish();
            }
        });

        nav_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear user session or credentials
                // For example, if using SharedPreferences to store login state:
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Navigate to the login screen or any other appropriate screen
                Intent intent = new Intent(Homepage.this, Login.class);
                startActivity(intent);
                finish(); // Optionally, call finish() to remove the current activity from the back stack
            }
        });
    }
}