package com.example.gibson.carlife.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gibson.carlife.Adapters.MainGridView1;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

public class MainShopActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_shop, container, false);

        ArrayList<Integer> ids = new ArrayList<>();
        String[] strings = getResources().getStringArray(R.array.type);
        for(int i = 0; i < 6 ; i ++) {
            ids.add(getResources().getIdentifier(strings[i], "drawable", getContext().getPackageName()));
        }
        ids.toArray();


        GridView gv = (GridView) view.findViewById(R.id.gvType);
        gv.setNumColumns(3);
        gv.setAdapter(new MainGridView1(getContext(), (Integer[]) ids.toArray()));
        return view;
    }

}
