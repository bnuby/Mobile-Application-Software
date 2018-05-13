package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.gibson.carlife.View.MainShopActivity;

/**
 * Created by user on 2018/5/13.
 */

public class MainGridView1Adapter extends ArrayAdapter<Integer> {
    private Context mCtx;

    public MainGridView1Adapter(Context c, Integer[] imglist) {
        super(c, 0, imglist);
        mCtx = c;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        ImageView iv = new ImageView(mCtx);
        iv.setMaxHeight(60);
//        iv.setMaxWidth(60);
        iv.setMinimumHeight(60);
//        iv.setMaxWidth(60);

        int resid = getItem(position);
        iv.setImageResource(resid);
        iv.setAdjustViewBounds(true);
        return iv;
    }
}
