package com.example.gibson.carlife.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.gibson.carlife.Adapters.ItemGridViewAdapter;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.ProductDetailActivity;

public class ProductGVFragment extends Fragment implements AdapterView.OnItemClickListener {

  GridView productGV;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.grid_view_product, container, false);

    productGV = view.findViewById(R.id.productGV);
    ItemGridViewAdapter adapter = new ItemGridViewAdapter(getContext(), DataManagement.getProducts());
    productGV.setAdapter(adapter);
    productGV.setOnItemClickListener(this);

    return view;
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Intent intent = new Intent(getContext(), ProductDetailActivity.class);
    intent.putExtra("position", position);
    startActivity(intent);
  }
}
