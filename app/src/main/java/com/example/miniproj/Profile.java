package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    ImageView back_btn;
    Button btn_plan, btn_upload;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn_plan = findViewById(R.id.btn_plan);
        btn_upload = findViewById(R.id.btn_upload);
        back_btn = findViewById(R.id.back_btn);

        String uid = null;
        if (user != null) {
            uid = user.getEmail();
            // Display or use the UID as per your requirements
        } else {
            // User is not signed in
        }

        TextView textViewUid = findViewById(R.id.id);
        textViewUid.setText(uid);

        btn_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, meal_planner.class);
                startActivity(intent);
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, upload_recipe.class);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}