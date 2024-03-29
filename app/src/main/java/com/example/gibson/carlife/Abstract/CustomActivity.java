package com.example.gibson.carlife.Abstract;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gibson.carlife.R;

public abstract class CustomActivity extends AppCompatActivity {


  protected static Context mContext;
  static AlertDialog dialog;

//  public static void showLoading(String message) {
//
//    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//    //    View view
//    View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.dialog_loading, null);
//
//    TextView textView = view.findViewById(R.id.messageTV);
//    textView.setText(message);
//
//    builder.setView(view);
//    builder.setCancelable(false);
//    dialog = builder.create();
//    dialog.show();
//  }

  public static void longTost(String toastSring) {
    Toast.makeText(mContext, toastSring, Toast.LENGTH_LONG).show();
  }

  public static void shortTost(String toastSring) {
    Toast.makeText(mContext, toastSring, Toast.LENGTH_SHORT).show();
  }

//  public static void dismissLoading() {
//    dialog.dismiss();
//  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = this;
  }
}
