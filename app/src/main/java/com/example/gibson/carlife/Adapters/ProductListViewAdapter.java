package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gibson.carlife.Model.Product;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.productDetail;

import java.util.ArrayList;

public class ProductListViewAdapter extends ArrayAdapter<Product>{
    @NonNull
    Context context;
    int type;

    @Override
    public int getCount() {
        if(super.getCount() > 9)
            return 9;
        return super.getCount();
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout itemlayout = null;
        if(convertView == null) {
            itemlayout = (LinearLayout)inflater.inflate(type, null);
        } else {
            itemlayout = (LinearLayout) convertView;
        }
        Product item=(Product)getItem(position);
        TextView title=(TextView)itemlayout.findViewById(R.id.titleTV);
        title.setText(item.name);
        TextView intro=(TextView)itemlayout.findViewById(R.id.introTV);
        intro.setText(item.description);
        TextView price=(TextView)itemlayout.findViewById(R.id.priceTV);
        price.setText(String.valueOf(item.cost_price));
        final ImageView imageView=itemlayout.findViewById(R.id.img);
        imageView.setImageBitmap(item.img);
        return itemlayout;
    }

    public ProductListViewAdapter(@NonNull Context context, ArrayList<Product> items, int type) {
        super(context, 0, items);
        this.type=type;
        this.context=context;
    }
}
