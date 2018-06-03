package com.example.gibson.carlife.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gibson.carlife.Abstract.CustomActivity;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.RequestManager;
import com.example.gibson.carlife.View.Fragment.AccountFragment;

public class AccountDetail extends CustomActivity implements View.OnClickListener{

    public View view;
    EditText username_ET, email_ET, phone_ET, address_ET;
    String email = "",phone = "", address = "";
    Button confirm_BTN;
    //private static final int REQUEST_CODE = 8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_detail);
        init();
    }

    public void init() {

            //set EditText with the component from layout
            username_ET = findViewById(R.id.username_ET);
            phone_ET = findViewById(R.id.phone_ET);
            address_ET = findViewById(R.id.address_ET);
            email_ET = findViewById(R.id.email_ET);

            //get user input
            email = email_ET.getText().toString();
            Log.i("confirm", email);
            phone = phone_ET.getText().toString();
            Log.i("confirm", phone);
            address = address_ET.getText().toString();
            Log.i("confirm", address);

            //confirm button definition
            confirm_BTN = findViewById(R.id.confirm_BTN);
            confirm_BTN.setOnClickListener(this);
        }
    @Override
    public void onClick(View view) {



        switch(view.getId()) {
            case R.id.confirm_BTN:
                Toast.makeText(view.getContext(), "Confirm", Toast.LENGTH_SHORT).show();
               // Intent intent=new Intent(AccountDetail.this,AccountFragment.class);

                //  need to update the information of user in server
                //  haven do

                //  back to Account Fragment.java
                finish();
                //startActivity(intent);
                break;
            default:
                Toast.makeText(this, "default run", Toast.LENGTH_SHORT).show();
                break;
            }

        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        finish();
    }



}