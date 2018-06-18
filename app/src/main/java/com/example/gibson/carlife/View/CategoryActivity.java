package com.example.gibson.carlife.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gibson.carlife.Adapters.CategoryAdapter;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.ProductBrand;
import com.example.gibson.carlife.Model.Product.ProductType;
import com.example.gibson.carlife.R;

public class CategoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

  ListView categoryLV;
  TextView titleTV;
  Button backBtn;
  String type;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category);

    type = getIntent().getStringExtra("typeTV");
    CategoryAdapter adapter;

    categoryLV = findViewById(R.id.categoryLV);
    titleTV = findViewById(R.id.titleTV);
    backBtn = findViewById(R.id.backBtn);

    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

    if(type.equalsIgnoreCase("brand")) {
      adapter = new CategoryAdapter(this, R.layout.category_list_item, DataManagement.getProductBrands().toArray(), ProductBrand.class);
      titleTV.setText(R.string.product_brand);
    }
    else {
      adapter = new CategoryAdapter(this, R.layout.category_list_item, DataManagement.getProductTypes().toArray(), ProductType.class);
      titleTV.setText(R.string.product_type);
    }
    categoryLV.setAdapter(adapter);
    categoryLV.setOnItemClickListener(this);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    String type = getIntent().getStringExtra("typeTV");
    String name;
    Intent intent = new Intent(this, ProductCategoryActivity.class);
    intent.putExtra("typeTV", type);
    if(type.equalsIgnoreCase("brand")) {
      Toast.makeText(this, ((ProductBrand)parent.getAdapter().getItem(position)).name, Toast.LENGTH_SHORT).show();
      ProductBrand brand = (ProductBrand) parent.getAdapter().getItem(position);
      name = brand.name;
    }
    else {
      Toast.makeText(this, ((ProductType)parent.getAdapter().getItem(position)).name, Toast.LENGTH_SHORT).show();
      ProductType productType = (ProductType) parent.getAdapter().getItem(position);
      name = productType.name;
    }
    ProductCategoryActivity.changeActivity(this, type, name);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    finish();
    super.onActivityResult(requestCode, resultCode, data);
  }
}
