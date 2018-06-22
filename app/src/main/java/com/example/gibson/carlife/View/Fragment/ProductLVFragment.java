package com.example.gibson.carlife.View.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gibson.carlife.Adapters.ProductListViewAdapter;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.ProductDetailActivity;

@SuppressLint("ValidFragment")
public class ProductLVFragment extends Fragment implements AdapterView.OnItemClickListener {

  ListView productListView;
  String type, name;

  public ProductLVFragment(String type, String name) {
    this.type = type;
    this.name = name;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.list_view_product, container, false);

    productListView = view.findViewById(R.id.productLV);

    ProductListViewAdapter adapter;

    if(type.equalsIgnoreCase("brand"))
      adapter = new ProductListViewAdapter(getContext(),
              DataManagement.getBrandOfProduct(name),
              R.layout.listview1);
    else
      adapter = new ProductListViewAdapter(getContext(),
              DataManagement.getTypeOfProduct(name),
              R.layout.listview1);

    productListView.setAdapter(adapter);

    productListView.setOnItemClickListener(this);

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
