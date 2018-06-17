package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.content.res.Resources;
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

public class TypeGridViewAdapter extends ArrayAdapter<ProductType> {
  private Context mCtx;
  int totalCount;

  public TypeGridViewAdapter(@NonNull Context context, @NonNull ArrayList<ProductType> objects) {
    super(context, 0, objects);
    mCtx = context;
  }

  public TypeGridViewAdapter(@NonNull Context context, @NonNull ArrayList<ProductType> objects, int totalCount) {
    super(context, 0, objects);
    mCtx = context;
    this.totalCount = totalCount;
  }

  @Override
  public int getCount() {
    return super.getCount() >= totalCount ? totalCount : super.getCount();
  }

  public void setTotalCount(int a) {
    totalCount = a;
  }

  public int getTotalCount() {
    return totalCount;
  }


  @Override
  public View getView(int position, View convertView,
                      ViewGroup parent) {

    ImageView iv = new ImageView(mCtx);
    float dp = Resources.getSystem().getDisplayMetrics().density;
    iv.setMaxHeight((int)(80 * dp));
    iv.setMinimumHeight((int)(80 * dp));
    ProductType item = getItem(position);
    iv.setImageBitmap(item.image);
//        int resid = getItem(position);
//        iv.setImageResource(resid);
    iv.setAdjustViewBounds(true);
    return iv;
  }
}
