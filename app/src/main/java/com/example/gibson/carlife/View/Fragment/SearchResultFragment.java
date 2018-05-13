package com.example.gibson.carlife.View.Fragment;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Switch;
import android.widget.Toast;

import com.example.gibson.carlife.Adapters.RecycleAdapter;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

public class SearchResultFragment extends Fragment {

    private RecycleAdapter mAdapter;
    private RecyclerView mRecyclerView;
    Switch aSwitch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_searchresult, container, false);
        ArrayList<String> myDataset = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            myDataset.add(Integer.toString(i));
        }
        mAdapter = new RecycleAdapter(getContext(), myDataset);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.listsearchresult);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                RecyclerView v = mRecyclerView;
                int scrollPosition = i1;
                Toast.makeText(getContext(), "123", Toast.LENGTH_LONG).show();
                Log.v("asd", view.getHeight()+"");
                Log.v("last", view.getScrollY()+"");
                if(scrollPosition == view.getHeight())
                    Toast.makeText(getContext(), "Last", Toast.LENGTH_LONG).show();
            }
        });

        aSwitch = view.findViewById(R.id.switch1);
        return view;
    }


}
