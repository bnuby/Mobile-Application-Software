package com.example.gibson.carlife.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.gibson.carlife.Adapters.ProductListViewAdapter;
import com.example.gibson.carlife.R;

public class FavoriteyActivity extends AppCompatActivity {
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritey);
        listView.findViewById(R.id.favoriteListview);

    }
}
