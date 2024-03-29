package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Favorite;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends ArrayAdapter<Favorite> {
    public FavoriteAdapter(@NonNull Context context,  @NonNull ArrayList<Favorite> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        if (convertView == null)
            convertView = inflater.inflate(R.layout.listview1, null);
        Log.i("ff", "getView: ");
        Favorite FavoriteItem = DataManagement.getFavorite().get(position);
        Product item = DataManagement.getProductsById(FavoriteItem.product_id);
        TextView title = convertView.findViewById(R.id.titleTV);
        title.setText(item.name);
        TextView price = (TextView) convertView.findViewById(R.id.priceTV);
        price.setText(String.valueOf(item.cost_price));
        final ImageView imageView = convertView.findViewById(R.id.img);
        imageView.setImageBitmap(item.img);
        return convertView;
    }
}