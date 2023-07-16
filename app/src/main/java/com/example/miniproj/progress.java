package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

public class progress extends AppCompatActivity {

    ProgressBar progressBar;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressBar = findViewById(R.id.progressBar);
        handler = new Handler();

        // Start a progress animation that runs for 4 seconds
        int animationDuration = 4000;
        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animator.setDuration(animationDuration);
        animator.start();

        // After the animation finishes, finish the activity
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(progress.this, upload_recipe.class);
                startActivity(intent);
                finish();
            }
        }, animationDuration);

//        Toast.makeText(progress.this, "Recipe uploaded successfully!", Toast.LENGTH_SHORT).show();
    }
}
