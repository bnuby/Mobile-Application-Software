package com.example.gibson.carlife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gibson.carlife.Services.RequestManager;

/**
 * Created by gibson on 2018/4/25.
 */

public class LoginActivity extends Fragment implements View.OnClickListener {

  public static View view;
  Button loginBtn;
  EditText usernameET;
  EditText passwordET;
  Button signup;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    ViewGroup group = (ViewGroup) inflater.inflate(R.layout.activity_login, container, false);

    view = group.getRootView();

    init(view);

    return group;
  }

  public void init(View view) {
    usernameET = view.findViewById(R.id.usernameET);
    passwordET = view.findViewById(R.id.passwordET);
    loginBtn = view.findViewById(R.id.loginBtn);
    signup=view.findViewById(R.id.signup);
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
        intent.setClass(getActivity().getApplicationContext(),Signup.class);
        startActivity(intent);
    }
  }
}
