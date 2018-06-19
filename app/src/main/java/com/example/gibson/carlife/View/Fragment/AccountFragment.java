package com.example.gibson.carlife.View.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.Order.DataCleanManager;
import com.example.gibson.carlife.Services.UserManagement;
import com.example.gibson.carlife.View.AccountManageActivity;
import com.example.gibson.carlife.View.FavoriteActivity;
import com.example.gibson.carlife.View.HistoryActivity;
import com.example.gibson.carlife.View.LoginActivity;

public class AccountFragment extends Fragment {

  static Button logOut_Btn, accMng_Btn;
  LinearLayout accountInfoLayout;
  static TextView usernameTV;

  Button clrCache_Btn;
  ImageView favorite,history;

  String cacheSize;
  //private static final int REQUEST_CODE = 8;

  public static void toggleLogoutBtn() {
    if (logOut_Btn != null)
      if (UserManagement.isLogin) {
        logOut_Btn.setVisibility(View.VISIBLE);
        accMng_Btn.setVisibility(View.VISIBLE);
      }
      else {
        logOut_Btn.setVisibility(View.INVISIBLE);
        accMng_Btn.setVisibility(View.INVISIBLE);
        usernameTV.setText(R.string.you_are_not_login);
      }
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_account, container, false);

    usernameTV = view.findViewById(R.id.usernameTV);

    accountInfoLayout = view.findViewById(R.id.acc_info);
    accountInfoLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(!UserManagement.isLogin){
          Intent intent = new Intent(getContext(), LoginActivity.class);
          getActivity().startActivityForResult(intent, 200);
          Toast.makeText(getContext(), "asd", Toast.LENGTH_SHORT).show();
        }
      }
    });
    //clear cache button onClickListener
    try {
      cacheSize = DataCleanManager.getTotalCacheSize(getContext());
    } catch (Exception e) {
      e.printStackTrace();
    }
    clrCache_Btn = view.findViewById(R.id.clrCache_Btn);
    clrCache_Btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle("Clear Cache")
                .setMessage("Are you sure you want to clear cache("+cacheSize+")?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                    DataCleanManager.clearAllCache(getContext());
                    MainActivity.longTost("Cache Cleared.");
                  }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            // cancels clear cache action
          }
        }).setIcon(android.R.drawable.ic_dialog_alert)
                .show();
      }
    });
    favorite= view.findViewById(R.id.favoriteIV);
    favorite.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(UserManagement.isLogin){
          Intent intent =new Intent(getContext(), FavoriteActivity.class);
          startActivity(intent);
        }
        else {
          AccountManageActivity.longTost("Please Login.");
        }
      }
    });
    history =view.findViewById(R.id.historyIV);
    history.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(UserManagement.isLogin){
          Intent intent =new Intent(getContext(), HistoryActivity.class);
          startActivity(intent);
        }
        else {
          AccountManageActivity.longTost("Please Login.");
        }
      }
    });

    //account management onClickListener
    accMng_Btn = view.findViewById(R.id.accMng_Btn);
    accMng_Btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Toast.makeText(getContext(),"Manage Account button clicked!",Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(getContext(), AccountManageActivity.class);
        //getActivity().startActivityForResult(intent,8);
        Intent intent = new Intent(getContext(), AccountManageActivity.class);
        getActivity().startActivityForResult(intent, 8);
      }
    });

    //User Log Out onClickListener
    logOut_Btn = view.findViewById(R.id.logOut_Btn);
    logOut_Btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        UserManagement.requestLogout();
      }
    });

    toggleLogoutBtn();
    return view;
  }

  @Override
  public void onStart() {
    super.onStart();

    String username = MainActivity.userObj.username;
    if(!username.equalsIgnoreCase(""))
      usernameTV.setText(MainActivity.userObj.username);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    //super.onActivityResult(requestCode, resultCode, data);
    switch (resultCode) {
      case 8:
        Toast.makeText(getContext(), "8", Toast.LENGTH_SHORT).show();
        break;
      case 200:
        Toast.makeText(getContext(), "200", Toast.LENGTH_SHORT).show();
        break;
    }
  }
/*
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        switch (requestCode) {
            case 8:
                Toast.makeText(getContext(), intent.getStringExtra("phone"), Toast.LENGTH_SHORT).show();
                break;
        }
    }
*/
}
