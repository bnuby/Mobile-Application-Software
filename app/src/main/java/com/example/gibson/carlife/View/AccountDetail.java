package com.example.gibson.carlife.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gibson.carlife.Abstract.CustomActivity;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.Fragment.AccountFragment;

public class AccountDetail extends CustomActivity {

    //TextView username_TV, email_TV, phone_TV, address_TV;
    EditText username_ET, email_ET, phone_ET, address_ET;
    String email = "",phone = "", address = "";
    Button confirm_BTN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_detail);
        //set EditText with the component from layout
        username_ET =email_ET = findViewById(R.id.email_ET);
        phone_ET = findViewById(R.id.phone_ET);
        address_ET = findViewById(R.id.address_ET);
        email_ET = findViewById(R.id.email_ET);
        phone_ET = findViewById(R.id.phone_ET);
        address_ET = findViewById(R.id.address_ET);
        //get user input
        email = email_ET.getText().toString();
        phone = phone_ET.getText().toString();
        address = address_ET.getText().toString();
        //confirm button definition
        confirm_BTN = findViewById(R.id.confirm_BTN);
        confirm_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //update the information of user to server
                //haven do

                // back to AccountFragment.java
                Intent intent = new Intent(AccountDetail.this, AccountFragment.class);
                startActivity(intent);
            }
        });


    }
}