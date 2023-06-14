package com.example.miniproj;


//public class signup_Activity extends AppCompatActivity {
//    EditText username;
//    EditText password;
//    EditText email;
//    EditText phone;
//    Button Register;
//    FirebaseAuth fAuth;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        getSupportActionBar().setTitle("Sign Up");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        username = (EditText) findViewById(R.id.editTextTextPersonName);
//        password = (EditText) findViewById(R.id.editTextTextPassword2);
//        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
//        phone = (EditText) findViewById(R.id.editTextPhone);
//        Register = (Button) findViewById(R.id.button);
//        fAuth = FirebaseAuth.getInstance();
//
//        Register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String user = username.getText().toString();
//                String pass = password.getText().toString();
//                String em = email.getText().toString();
//                String ph = phone.getText().toString();
//
//
//                if (TextUtils.isEmpty(em)) {
//                    email.setError("Email is Required.");
//                    return;
//
//                }
//
//                if (TextUtils.isEmpty(pass)) {
//                    password.setError("Password is Required");
//                    return;
//
//                }
//                if (password.length() < 6) {
//                    password.setError("Password Must be >= 6 Characters");
//
//                }
//
//                fAuth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(signup_Activity.this, "User Created.", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                            FirebaseUser firebaseUser = fAuth.getCurrentUser();
//                            //user data to firebase
//                            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(user, pass, em, ph);
//
//                            //extracting user reference from database for registered users
//                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("registered users");
//                            referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
//
//
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(signup_Activity.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(signup_Activity.this, MainActivity.class));
//                                    } else {
//                                        Toast.makeText(signup_Activity.this, "Registration failed", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//
//                        } else {
//                            Toast.makeText(signup_Activity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        });
//    }
//}



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

public class SignUp extends AppCompatActivity {

    private EditText username, password, email, phone;
    private Button sign2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sign2 = findViewById(R.id.sign2);
        auth = FirebaseAuth.getInstance();

        sign2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = username.getText().toString();
                String password1 = password.getText().toString();
                String email1 = email.getText().toString();
                String phone1 = phone.getText().toString();

                if (TextUtils.isEmpty(email1)) {
                    email.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password1)) {
                    password.setError("Password is Required");
                    return;
                }
                if (password.length() < 6) {
                    password.setError("Password Must be >= 6 Characters");
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
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(SignUp.this, "Failed to register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}