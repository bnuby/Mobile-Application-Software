package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by user on 2018/5/13.
 */

public class ClassficationGridViewAdapter extends ArrayAdapter<Integer> {
    private Context mCtx;

    public ClassficationGridViewAdapter(Context c, Integer[] imglist) {
        super(c, 0, imglist);
        mCtx = c;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        ImageView iv = new ImageView(mCtx);
        iv.setMaxHeight(60 * TypedValue.COMPLEX_UNIT_SP);
//        iv.setMaxWidth(60);
        iv.setMinimumHeight(60 * TypedValue.COMPLEX_UNIT_SP);
//        iv.setMaxWidth(60);

        int resid = getItem(position);
        iv.setImageResource(resid);
        iv.setAdjustViewBounds(true);
        return iv;
    }
}
