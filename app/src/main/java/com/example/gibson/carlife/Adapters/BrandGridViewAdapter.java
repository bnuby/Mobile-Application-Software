package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gibson.carlife.Model.Product.ProductBrand;
import com.example.gibson.carlife.Model.Product.ProductType;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

/**
 * Created by user on 2018/5/13.
 */

public class BrandGridViewAdapter extends ArrayAdapter<ProductBrand> {
  private Context mCtx;
  int totalCount;

  public BrandGridViewAdapter(@NonNull Context context, @NonNull ArrayList<ProductBrand> objects) {
    super(context, 0, objects);
    mCtx = context;
  }


  public BrandGridViewAdapter(@NonNull Context context, @NonNull ArrayList<ProductBrand> objects, int totalCount) {
    super(context, 0, objects);
    mCtx = context;
    this.totalCount = totalCount;
  }

  public void setTotalCount(int a) {
    totalCount = a;
  }

  public int getTotalCount() {
    return totalCount;
  }

  @Override
  public int getCount() {
    return super.getCount() >= totalCount ? totalCount : super.getCount();
  }

  @Override
  public View getView(int position, View convertView,
                      ViewGroup parent) {

    if(position < totalCount -1) {
      ImageView iv = new ImageView(mCtx);
      float dp = Resources.getSystem().getDisplayMetrics().density;
      iv.setMaxHeight((int)(80 * dp));
      iv.setMinimumHeight((int)(80 * dp));
      ProductBrand item = getItem(position);
      iv.setImageBitmap(item.image);
      iv.setAdjustViewBounds(true);
      return iv;
    }
    TextView tv = new TextView(mCtx);
    float dp = Resources.getSystem().getDisplayMetrics().density;
    tv.setMaxHeight((int)(80 * dp));
    tv.setMinimumHeight((int)(80 * dp));
    tv.setWidth((int)(40 * dp));
//    tv.setMinimumWidth((int)(20 * dp));
    ProductBrand item = getItem(position);
    tv.setTextSize(20);
    tv.setTextColor(mCtx.getColor(R.color.white));
//    tv.setBackgroundColor(mCtx.getColor(R.color.dark_green));
    tv.setBackground(mCtx.getDrawable(R.drawable.rounded_square));
    tv.setGravity(Gravity.CENTER);
    tv.setText(R.string.more);
    return tv;
  }
}
