package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.ProductType;
import com.example.gibson.carlife.View.Fragment.MainShopFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/5/13.
 */

public class ClassficationGridViewAdapter extends ArrayAdapter<ProductType> {
    private Context mCtx;

    public ClassficationGridViewAdapter(@NonNull Context context, @NonNull ArrayList<ProductType> objects) {
        super(context, 0, objects);
        mCtx = context;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        ImageView iv = new ImageView(mCtx);
        iv.setMaxHeight(80 * TypedValue.COMPLEX_UNIT_SP);
        iv.setMinimumHeight(80 * TypedValue.COMPLEX_UNIT_SP);
        ProductType item = MainActivity.productTypes.get(position);
        iv.setImageBitmap(item.image);
//        int resid = getItem(position);
//        iv.setImageResource(resid);
        iv.setAdjustViewBounds(true);
        return iv;
    }
}
