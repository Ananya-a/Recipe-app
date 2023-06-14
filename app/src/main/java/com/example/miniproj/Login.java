package com.example.miniproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText email2, password2;
    private Button login2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email2 = findViewById(R.id.email2);
        password2 = findViewById(R.id.password2);
        login2 = findViewById(R.id.login2);
        auth = FirebaseAuth.getInstance();

        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email2.getText().toString();
                String password1 = password2.getText().toString();

                if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1))
                {
                    Toast.makeText(Login.this, "Enter both Email and password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    register(email1, password1);
                }
            }
        });
    }
    private void register(String email, String password)
    {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(Login.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // Login successful
                Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Homepage.class);
                startActivity(intent);
                finish();
            }

            }).addOnFailureListener(Login.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Login failed
                Toast.makeText(Login.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}