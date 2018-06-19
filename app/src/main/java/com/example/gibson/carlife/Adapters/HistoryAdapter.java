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

import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.History;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<History> {
    public HistoryAdapter(@NonNull Context context, @NonNull ArrayList<History> objects) {
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
        History HistoryItem = DataManagement.getHistories().get(position);
        Product item = DataManagement.getProductsById(HistoryItem.product_id);
        TextView title = convertView.findViewById(R.id.titleTV);
        title.setText(item.name);
        TextView price = (TextView) convertView.findViewById(R.id.priceTV);
        price.setText(String.valueOf(item.cost_price));
        final ImageView imageView = convertView.findViewById(R.id.img);
        imageView.setImageBitmap(item.img);
        return convertView;
    }
}
