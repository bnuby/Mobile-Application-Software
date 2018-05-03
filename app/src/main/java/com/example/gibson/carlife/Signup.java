package com.example.gibson.carlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {
EditText account;
EditText password;
EditText email;
EditText phone;
Button canel;
Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        account=findViewById(R.id.account);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        canel=findViewById(R.id.cancel);
        signup=findViewById(R.id.signup);
    }
}
