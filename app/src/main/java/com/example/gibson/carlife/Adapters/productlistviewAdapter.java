package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gibson.carlife.Model.Product;
import com.example.gibson.carlife.R;

public class productlistviewAdapter extends ArrayAdapter<Product>{
    @NonNull
    Context context;
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout itemlayout = null;
        if(convertView == null) {
            itemlayout = (LinearLayout)inflater.inflate(R.layout.listview2, null);
        } else {
            itemlayout = (LinearLayout) convertView;
        }
        Product item=(Product)getItem(position);
        ImageView img=(ImageView)itemlayout.findViewById(R.id.img);
        img.setImageResource(item.img);
        TextView title=(TextView)itemlayout.findViewById(R.id.titleTV);
        title.setText(item.name);
        TextView intro=(TextView)itemlayout.findViewById(R.id.introTV);
        intro.setText(item.description);
        TextView price=(TextView)itemlayout.findViewById(R.id.priceTV);
        price.setText(String.valueOf(item.price));
        return super.getView(position, convertView, parent);
    }

    public productlistviewAdapter(@NonNull Context context, int resource) {
        super(context, resource);

    }
}
