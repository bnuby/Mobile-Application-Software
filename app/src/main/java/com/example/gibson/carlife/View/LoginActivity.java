package com.example.gibson.carlife.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gibson.carlife.Abstract.CustomActivity;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.UserManagement;

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
        UserManagement.requestLogin(usernameET.getText().toString(), passwordET.getText().toString(), true);
        break;
      case R.id.signup:
        Intent intent=new Intent();
        intent.setClass(getApplicationContext(),SignupActivity.class);
        startActivityForResult(intent, 200);
        break;
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  }

  public static void activityFinish() {
    if(mContext == null)
      return;
    ((Activity)mContext).finish();

    }
   int[] a =  new int[]{0,1,2,3,4};

  class CustomAdapter extends BaseAdapter {
    @Override
    public int getCount() {
      return a.length;
    }

    @Override
    public Object getItem(int i) {
      return a[i];
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
