package com.example.gibson.carlife.View;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gibson.carlife.R;

public class AccountActivity extends Fragment {

    LinearLayout acc_info;

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
        return view;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        switch (requestCode) {
            case 200:
                Toast.makeText(getContext(), intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
