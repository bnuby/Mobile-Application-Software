package com.example.gibson.carlife.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.gibson.carlife.Abstract.CustomActivity;
import com.example.gibson.carlife.Adapters.FavoriteAdapter;
import com.example.gibson.carlife.Adapters.ProductListViewAdapter;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.FavoriteManagerment;

public class FavoriteyActivity extends CustomActivity {
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritey);
        listView =findViewById(R.id.favoriteListview);
        Log.i("ggggggg", "onCreate: "+DataManagement.getFavorite().get(0).product_id);

        FavoriteAdapter adapter = new FavoriteAdapter(this, DataManagement.getFavorite());
        listView.setAdapter(adapter);
    }
}
