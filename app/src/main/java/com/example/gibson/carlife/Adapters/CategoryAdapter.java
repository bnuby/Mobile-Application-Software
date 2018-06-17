package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.Model.Product.ProductBrand;
import com.example.gibson.carlife.Model.Product.ProductType;
import com.example.gibson.carlife.R;

public class CategoryAdapter extends ArrayAdapter<Object> {

  Class type;

  public CategoryAdapter(@NonNull Context context, int resource, @NonNull Object[] objects, Class type) {
    super(context, resource, objects);
    this.type = type;
  }



  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    if(convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_list_item, parent, false);
    }
    ImageView iv = convertView.findViewById(R.id.categoryImg);
    TextView tv = convertView.findViewById(R.id.categoryTV);

    if(this.type == ProductType.class) {
      ProductType type = (ProductType) getItem(position);
      iv.setImageBitmap(type.image);
      tv.setText(type.name);
    } else {
      ProductBrand brand = (ProductBrand) getItem(position);
      iv.setImageBitmap(brand.image);
      tv.setText(brand.name);
    }

    return convertView;
  }
}
