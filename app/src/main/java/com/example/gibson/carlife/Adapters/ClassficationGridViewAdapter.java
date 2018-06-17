package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.ProductType;

import java.util.ArrayList;

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
    ProductType item = DataManagement.getProductTypes().get(position);
    iv.setImageBitmap(item.image);
//        int resid = getItem(position);
//        iv.setImageResource(resid);
    iv.setAdjustViewBounds(true);
    return iv;
  }
}
