package com.example.gibson.carlife.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.RequestManager;

public class SignupActivity extends AppCompatActivity {
    EditText usernameET;
    EditText passwordET;
    EditText nameET;
    EditText confirm_passwordET;
    EditText emailET;
    EditText phoneET;
    Button cancelBtn;
    Button signUpBtn;

    View.OnClickListener signUpListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.cancel:
                    finish();
                    break;

                case R.id.signup:
                    String username = usernameET.getText().toString();
                    String password = passwordET.getText().toString();
                    String name = nameET.getText().toString();
                    String confirm_password = confirm_passwordET.getText().toString();
                    String email = emailET.getText().toString();
                    String phone = phoneET.getText().toString();

                    if(username.isEmpty() || password.isEmpty() ||
                            confirm_password.isEmpty() || name.isEmpty() ||
                            email.isEmpty() || phone.isEmpty()) {
                        Toast.makeText(SignupActivity.this, "All Must Not be Empty!", Toast.LENGTH_SHORT).show();
                    } else if (!password.equals(confirm_password)) {
                        Toast.makeText(SignupActivity.this, "Password Not Same!", Toast.LENGTH_SHORT).show();
                    } else {
                        RequestManager.requestRegister(username, password, email, name, phone);
                    }


                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        usernameET = findViewById(R.id.username);
        nameET = findViewById(R.id.name);
        passwordET = findViewById(R.id.password);
        confirm_passwordET = findViewById(R.id.password2);
        emailET = findViewById(R.id.email);
        phoneET = findViewById(R.id.phone);


        // Button
        cancelBtn = findViewById(R.id.cancel);
        signUpBtn = findViewById(R.id.signup);

        cancelBtn.setOnClickListener(signUpListener);
        signUpBtn.setOnClickListener(signUpListener);
    }

}
