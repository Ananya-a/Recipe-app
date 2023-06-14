//package com.example.miniproj;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.example.miniproj.R;
//
//public class MainActivity extends AppCompatActivity {
//    Button login1, sign1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        login1 = findViewById(R.id.login1);
//        sign1=findViewById(R.id.sign1);
//        login1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,Login.class);
//
//                startActivity(intent);
//                Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        sign1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Your account will be created soon", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, SignIn.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//}


package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.miniproj.R;

public class MainActivity extends AppCompatActivity {
    Button login1;
    Button sign1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login1 = findViewById(R.id.login1);
        sign1=findViewById(R.id.sign1);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Login.class);

                startActivity(intent);
                Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();

            }
        });
        sign1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Your account will be created soon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SignIn.class);
                startActivity(intent);
            }
        });

    }
}
