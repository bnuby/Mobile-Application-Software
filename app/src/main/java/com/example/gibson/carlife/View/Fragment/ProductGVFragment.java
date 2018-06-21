package com.example.gibson.carlife.View.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.gibson.carlife.Adapters.ItemGridViewAdapter;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.Model.Product.ProductBrand;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.ProductDetailActivity;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ProductGVFragment extends Fragment implements AdapterView.OnItemClickListener {

  GridView productGV;
  String type;
  String name;

  public ProductGVFragment(String type, String name) {
    this.type = type;
    this.name = name;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.grid_view_product, container, false);

    productGV = view.findViewById(R.id.productGV);
    ItemGridViewAdapter adapter;
    if(type.equalsIgnoreCase("brand")) {
      adapter = new ItemGridViewAdapter(getContext(),
              DataManagement.getBrandOfProduct(name));
    }
    else {
      adapter = new ItemGridViewAdapter(getContext(),
              DataManagement.getTypeOfProduct(name));
    }
    productGV.setAdapter(adapter);
    productGV.setOnItemClickListener(this);

    return view;
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Intent intent = new Intent(getContext(), ProductDetailActivity.class);
    int product_id = ((Product)parent.getAdapter().getItem(position)).id;
    intent.putExtra("position", product_id);
    startActivity(intent);
  }
}
