package com.example.gibson.carlife.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gibson.carlife.Adapters.ProductListViewAdapter;
import com.example.gibson.carlife.Adapters.RecycleAdapter;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Order.Order;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.Order.OrderManagement;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

  private RecycleAdapter mAdapter;
  private ListView listView;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_cart, container, false);

    CartListViewAdapter adapter =
            new CartListViewAdapter(getContext(), DataManagement.getOrderCollection().carts);
    listView = view.findViewById(R.id.cartLV);
    listView.setAdapter(adapter);


    return view;
  }

  class CartListViewAdapter extends ArrayAdapter<Order> implements View.OnClickListener {
    Button minusBtn, plusBtn, deleteBtn;
    TextView productTV, qtyTV, priceTV;
    ImageView productIV;
    CheckBox checkBox;
    ArrayList<Boolean> booleans;

    public CartListViewAdapter(@NonNull Context context, @NonNull List<Order> objects) {
      super(context, 0, objects);
      booleans = new ArrayList<>(objects.size());
      for(Boolean b : booleans) {
        b = false;
      }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

      if(convertView == null)
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview3, parent, false);

      minusBtn = convertView.findViewById(R.id.minusBtn);
      plusBtn = convertView.findViewById(R.id.plusBtn);
      deleteBtn = convertView.findViewById(R.id.deleteBtn);
      productTV = convertView.findViewById(R.id.productTV);
      qtyTV = convertView.findViewById(R.id.qtyTV);
      productIV = convertView.findViewById(R.id.productIV);
      priceTV = convertView.findViewById(R.id.priceTV);
      checkBox = convertView.findViewById(R.id.checkBox);

      minusBtn.setTag(position);
      minusBtn.setTag(0, qtyTV);

      plusBtn.setTag(position);
      plusBtn.setTag(position);

      deleteBtn.setTag(position);

      minusBtn.setOnClickListener(this);
      plusBtn.setOnClickListener(this);
      deleteBtn.setOnClickListener(this);

      Product product = DataManagement.getProductsById(getItem(position).product_id);
      productTV.setText(product.name);
      qtyTV.setText(String.valueOf(getItem(position).quantity));
      priceTV.setText(String.valueOf(DataManagement.getProductsById(getItem(position).product_id).sale_price));

      productIV.setImageBitmap(product.img);
      setCheckBox(checkBox, position);


      return super.getView(position, convertView, parent);
    }

    void setCheckBox(CheckBox checkBox, int position) {
      if(booleans.get(position))
        checkBox.setChecked(true);
      else
        checkBox.setChecked(false);
    }

    @Override
    public void onClick(View v) {
      int position = (int) v.getTag();
      TextView qtyTV = (TextView) v.getTag(0);
      switch (v.getId()) {
        case R.id.deleteBtn:
          OrderManagement.deleteOrder(getItem(position).id);
          DataManagement.getOrderCollection().deleteCartByID(getItem(position).id);
          notifyDataSetInvalidated();
          break;
        default:
          switch (v.getId()) {
            case R.id.minusBtn:
              if (getItem(position).quantity > 0) {
                getItem(position).quantity -= 1;
                qtyTV.setText(getItem(position).quantity);
                OrderManagement.updateCartQty(getItem(position).id, getItem(position).quantity);
              }
              break;
            case R.id.plusBtn:
              if (getItem(position).quantity < DataManagement.getProductsById(getItem(position).product_id).quantity) {
                getItem(position).quantity += 1;
                qtyTV.setText(getItem(position).quantity);
                OrderManagement.updateCartQty(getItem(position).id, getItem(position).quantity);
              }
              break;
          }
      }
    }

  }


}
