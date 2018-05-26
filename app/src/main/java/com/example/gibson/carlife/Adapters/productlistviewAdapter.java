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
        ImageView img1=(ImageView)itemlayout.findViewById(R.id.img);
        ImageView img2=(ImageView)itemlayout.findViewById(R.id.img2);
        TextView title1=(TextView)itemlayout.findViewById(R.id.titleTV);
        TextView title2=(TextView)itemlayout.findViewById(R.id.titleTV2);
        TextView intro1=(TextView)itemlayout.findViewById(R.id.introTV);
        TextView intro2=(TextView)itemlayout.findViewById(R.id.introTV2);
        TextView price1=(TextView)itemlayout.findViewById(R.id.priceTV);
        TextView price2=(TextView)itemlayout.findViewById(R.id.priceTV2);

        return super.getView(position, convertView, parent);
    }

    public productlistviewAdapter(@NonNull Context context, int resource) {
        super(context, resource);

    }
}
