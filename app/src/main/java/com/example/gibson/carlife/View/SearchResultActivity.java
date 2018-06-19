package com.example.gibson.carlife.View;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.gibson.carlife.Adapters.ProductListViewAdapter;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {

  final String TAG = "SearchResultActivity";

  private ListView listView;
  Button backBtn;
  SearchView searchBar;
  Toolbar toolbar;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_search_result);
    final Intent intent = getIntent();
    String query = intent.getStringExtra(SearchManager.QUERY);

    ArrayList<Product> products = DataManagement.getMapByKey(query);

    ProductListViewAdapter adapter =
            new ProductListViewAdapter(this, products, R.layout.listview1);


    listView = findViewById(R.id.searchlist);
    listView.setAdapter(adapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent1 = new Intent(getBaseContext(), ProductDetailActivity.class);
        int product_id = ((Product)parent.getAdapter().getItem(position)).id;
        intent1.putExtra("position", product_id);
        startActivity(intent1);
      }
    });


    backBtn = findViewById(R.id.backBtn);
    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });



    searchBar = findViewById(R.id.searchBar);
    searchBar.setQuery(query, false);
    searchBar.setIconifiedByDefault(false);
    searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {

        ArrayList<Product> products = DataManagement.getMapByKey(query);

        ProductListViewAdapter adapter =
                new ProductListViewAdapter(getBaseContext(), products, R.layout.listview1);
        listView.setAdapter(adapter);
        listView.invalidateViews();

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchBar.getWindowToken(), 0);
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }
    });


  }
}
