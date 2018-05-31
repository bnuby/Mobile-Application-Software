package com.example.gibson.carlife.View.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.AccountDetail;
import com.example.gibson.carlife.View.LoginActivity;

public class AccountFragment extends Fragment {

    LinearLayout acc_info;
    Button clrCache_Btn, accMng_Btn, logOut_Btn;
    //private static final int REQUEST_CODE = 8;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_account, container, false);
        acc_info = view.findViewById(R.id.acc_info);
        acc_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                getActivity().startActivityForResult(intent, 200);
            }
        });
        //clear cache button onClickListener
        clrCache_Btn = view.findViewById(R.id.clrCache_Btn);
        clrCache_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                builder.setTitle("Clear Cache")
                        .setMessage("Are you sure you want to clear cache?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with clear cache action
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // cancel clear cache action
                    }
                }).setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        //account management onClickListener
        accMng_Btn = view.findViewById(R.id.accMng_Btn);
        accMng_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Manage Account button clicked!",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getContext(), AccountDetail.class);
                //getActivity().startActivityForResult(intent,8);
                Intent intent = new Intent(getContext(),  AccountDetail.class);
                getActivity().startActivityForResult(intent, 8);
            }
        });

        //user Log Out onClickListener
        logOut_Btn = view.findViewById(R.id.logOut_Btn);
        logOut_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Log Out button clicked!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode){
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
