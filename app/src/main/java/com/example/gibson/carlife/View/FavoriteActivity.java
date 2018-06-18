package com.example.gibson.carlife.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.gibson.carlife.Abstract.CustomActivity;
import com.example.gibson.carlife.Adapters.FavoriteAdapter;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.R;

public class FavoriteActivity extends CustomActivity {
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        listView =findViewById(R.id.favoriteListview);
        Log.i("ggggggg", "onCreate: "+DataManagement.getFavorite());

        FavoriteAdapter adapter = new FavoriteAdapter(this, DataManagement.getFavorite());
        listView.setAdapter(adapter);
    }
}
