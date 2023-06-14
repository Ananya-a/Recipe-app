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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class SignIn extends AppCompatActivity {

    private EditText email, password, phone, username;

    private Button sign2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
//        phone = findViewById(R.id.phone);
//        username = findViewById(R.id.username);
        sign2 = findViewById(R.id.sign2);
        auth = FirebaseAuth.getInstance();

        sign2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1))
                {
                    Toast.makeText(SignIn.this, "Enter both email and password compulsorily", Toast.LENGTH_SHORT).show();
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
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent intent = new Intent(SignIn.this, Login.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(SignIn.this, "Failed to register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}