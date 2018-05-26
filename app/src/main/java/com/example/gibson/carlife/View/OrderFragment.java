package com.example.gibson.carlife.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gibson.carlife.Adapters.RecycleAdapter;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

public class OrderFragment extends Fragment {

    private RecycleAdapter mAdapter;
    private RecyclerView buyRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_order, container, false);
        ArrayList<String> orderlist = new ArrayList<>();
        orderlist.add("5");
        mAdapter = new RecycleAdapter(getContext(), orderlist);
        buyRecyclerView = (RecyclerView)view.findViewById(R.id.oredlistRV);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        buyRecyclerView.setLayoutManager(layoutManager);
        buyRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
