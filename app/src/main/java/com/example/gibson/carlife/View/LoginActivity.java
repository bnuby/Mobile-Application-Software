package com.example.gibson.carlife.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gibson.carlife.Abstract.CustomActivity;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.RequestManager;

/**
 * Created by gibson on 2018/4/25.
 */

public class LoginActivity extends CustomActivity implements View.OnClickListener {

  public static View view;
  Button loginBtn;
  EditText usernameET;
  EditText passwordET;
  Button signup;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    init();

  }

  public void init() {
    usernameET = findViewById(R.id.usernameET);
    passwordET = findViewById(R.id.passwordET);

    loginBtn = findViewById(R.id.loginBtn);
    signup = findViewById(R.id.signup);

    loginBtn.setOnClickListener(this);
    signup.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch(view.getId()) {
      case R.id.loginBtn:
        Toast.makeText(view.getContext(), "Login", Toast.LENGTH_SHORT).show();
        RequestManager.requestLogin(usernameET.getText().toString(), passwordET.getText().toString());
        break;
      case R.id.signup:
        Intent intent=new Intent();
        intent.setClass(getApplicationContext(),SignupActivity.class);
        startActivityForResult(intent, 200);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    finish();
  }

   int[5] a =  {0,1,2,3,4};

  class CustomAdapter extends BaseAdapter {
    @Override
    public int getCount() {
      return a.size();
    }

    @Override
    public Object getItem(int i) {
      return contact;
    }

    @Override
    public long getItemId(int i) {
      return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      return null;
    }
  }

}
