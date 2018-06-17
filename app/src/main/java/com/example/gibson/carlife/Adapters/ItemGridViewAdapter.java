package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

public class ItemGridViewAdapter extends ArrayAdapter<Product> {

//  private ArrayList<Product> products;
  private Context mContext;
//    titleTV = (TextView) v.findViewById(R.id.title2TV);
//    introTV = (TextView) v.findViewById(R.id.intro2TV);
//    priceTV = (TextView) v.findViewById(R.id.price2TV);

  public ItemGridViewAdapter(@NonNull Context context, ArrayList<Product> products) {
    super(context, R.layout.gridview_item, products);
//    this.products = products;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ImageView imageView;
    TextView titleTV, introTV, priceTV;
    if(convertView == null)
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item, parent, false);

    imageView = convertView.findViewById(R.id.img);
    titleTV = convertView.findViewById(R.id.titleTV);
//    introTV = convertView.findViewById(R.id.introTV);
    priceTV = convertView.findViewById(R.id.priceTV);

    imageView.setImageBitmap(getItem(position).img);
    titleTV.setText(getItem(position).name);
//    introTV.setText(products.get(position).description);
    priceTV.setText(getItem(position).cost_price + "");
    return convertView;
  }
}
