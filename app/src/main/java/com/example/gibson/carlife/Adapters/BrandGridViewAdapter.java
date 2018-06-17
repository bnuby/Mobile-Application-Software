package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.Product.ProductBrand;

import java.util.ArrayList;

/**
 * Created by user on 2018/5/13.
 */

public class BrandGridViewAdapter extends ArrayAdapter<ProductBrand> {
    private Context mCtx;

    public BrandGridViewAdapter(@NonNull Context context, @NonNull ArrayList<ProductBrand> objects) {
        super(context, 0, objects);
        mCtx = context;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        ImageView iv = new ImageView(mCtx);
        iv.setMaxHeight(80 * TypedValue.COMPLEX_UNIT_SP);
        iv.setMinimumHeight(80 * TypedValue.COMPLEX_UNIT_SP);
        ProductBrand item = MainActivity.productbrands.get(position);
        iv.setImageBitmap(item.image);
//        int resid = getItem(position);
//        iv.setImageResource(resid);
        iv.setAdjustViewBounds(true);
        return iv;
    }
}
