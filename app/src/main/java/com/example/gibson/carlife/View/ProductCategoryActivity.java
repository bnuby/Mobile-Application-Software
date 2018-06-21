package com.example.gibson.carlife.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.ProductBrand;
import com.example.gibson.carlife.Model.Product.ProductType;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.Fragment.MainShopFragment;
import com.example.gibson.carlife.View.Fragment.ProductGVFragment;
import com.example.gibson.carlife.View.Fragment.ProductLVFragment;

import java.util.ArrayList;

public class ProductCategoryActivity extends AppCompatActivity implements View.OnClickListener {

  ViewSwitch viewSwitch = ViewSwitch.GridView;
  Button gridViewBtn;
  Button listViewBtn;
  Button backBtn;
  TextView titleTV;
  Fragment[] fragments;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_category);
    init();

  }

  void init() {
    String name = getIntent().getStringExtra("name");
    gridViewBtn = findViewById(R.id.gridViewBtn);
    listViewBtn = findViewById(R.id.listViewBtn);
    backBtn = findViewById(R.id.backBtn);
    gridViewBtn.setOnClickListener(this);
    listViewBtn.setOnClickListener(this);
    backBtn.setOnClickListener(this);

    titleTV = findViewById(R.id.titleTV);
    titleTV.setText(name);

    Intent intent = getIntent();
    String type = intent.getStringExtra("type");
    String item = intent.getStringExtra("name");
    fragments = new Fragment[]{new ProductGVFragment(type, item), new ProductLVFragment(type, item)};

    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, fragments[0])
    .add(R.id.frameLayout, fragments[1])
    .hide(fragments[1]).commit();
  }

  @Override
  public void onClick(View v) {
    switch(v.getId()) {
      case R.id.gridViewBtn:
        if(viewSwitch != ViewSwitch.GridView) {
          gridViewBtn.setBackground(getDrawable(R.drawable.gridview_enable));
          listViewBtn.setBackground(getDrawable(R.drawable.listview_disable));
          viewSwitch = ViewSwitch.GridView;
          changeView(viewSwitch);
        }
        break;
      case R.id.listViewBtn:
        if(viewSwitch != ViewSwitch.ListView) {
          gridViewBtn.setBackground(getDrawable(R.drawable.gridview_disable));
          listViewBtn.setBackground(getDrawable(R.drawable.listview_enable));
          viewSwitch = ViewSwitch.ListView;
          changeView(viewSwitch);
        }
        break;

      case R.id.backBtn:
        finish();
        break;
    }
  }

  void changeView(ViewSwitch viewSwitch) {
    switch(viewSwitch) {
      case ListView:
//        transaction.remove(fragments[0]);
//        transaction.replace(R.id.frameLayout, fragments[1]);
        getSupportFragmentManager().beginTransaction()
                .hide(fragments[0])
                .show(fragments[1]).commit();
        break;

      case GridView:
//        transaction.remove(fragments[1]);
//        transaction.replace(R.id.frameLayout, fragments[0]);
        getSupportFragmentManager().beginTransaction()
                .hide(fragments[1])
                .show(fragments[0]).commit();
        break;
    }
  }

  enum ViewSwitch {
    ListView, GridView
  }

  public static void changeActivity(Context context, String type, String name) {
    Intent intent = new Intent(context, ProductCategoryActivity.class);
    intent.putExtra("type", type);
    intent.putExtra("name", name);
    Log.i("product", type);
    Log.i("product", name);
    ((Activity)context).startActivityForResult(intent, 0);
  }
}
