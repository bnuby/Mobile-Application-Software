package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gibson.carlife.Model.Product;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

public class ItemGridveiwAdapter extends ArrayAdapter<Product>{

    private ArrayList<Product> products;
    private Context mContext;
//    titleTV = (TextView) v.findViewById(R.id.title2TV);
//    introTV = (TextView) v.findViewById(R.id.intro2TV);
//    priceTV = (TextView) v.findViewById(R.id.price2TV);

    public ItemGridveiwAdapter(@NonNull Context context, ArrayList<Product> products) {
        super(context, R.layout.listview2);
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imageView;
        TextView titleTV, introTV, priceTV;
        imageView = convertView.findViewById(R.id.img);
        titleTV = convertView.findViewById(R.id.titleTV);
        introTV = convertView.findViewById(R.id.introTV);
        priceTV = convertView.findViewById(R.id.priceTV);

        imageView.setImageResource(products.get(position).img);
        titleTV.setText(products.get(position).name);
        introTV.setText(products.get(position).description);
        priceTV.setText(products.get(position).price + "");
        return super.getView(position, convertView, parent);
    }
}
