package com.example.gibson.carlife.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Order.OrderItem;
import com.example.gibson.carlife.Model.Order.OrderStatus;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.Order.OrderManagement;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

  private static final String TAG = "CartFragment";
  private static CartListViewAdapter adapter;
  private static ListView listView;
  static CheckBox checkBox;
  static TextView priceTV;
  Button payBtn;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_cart, container, false);

    checkBox = view.findViewById(R.id.allCheckBox);
    checkBox.setText(0 + " " + getResources().getString(R.string.items));
    checkBox.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(checkBox.isChecked())
          adapter.checkAll();
        else
          adapter.uncheckAll();
      }
    });

    priceTV = view.findViewById(R.id.priceTV);
    adapter = new CartListViewAdapter(getContext(), DataManagement.getOrderCollection().carts);
    listView = view.findViewById(R.id.cartLV);
    listView.setAdapter(adapter);

    payBtn = view.findViewById(R.id.payBtn);
    payBtn.setEnabled(false);
    payBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(adapter.orderItems.size() != 0) {
          AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
          View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_address, null);
          Button cancelBtn = view.findViewById(R.id.cancelBtn);
          Button doneBtn = view.findViewById(R.id.doneBtn);
          final EditText addressET = view.findViewById(R.id.addressET);

          builder.setView(view);
          final AlertDialog dialog = builder.create();
          dialog.show();

          doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              adapter.removeOrder();
              OrderManagement.addOrder(
                      MainActivity.userObj.userId,
                      addressET.getText().toString(),
                      "unpay",
                      adapter.orderItems
              );

              for(OrderItem orderItem: adapter.orderItems) {
                orderItem.status = OrderStatus.unpay;
              }
              DataManagement.getOrderCollection().unpays.addAll(adapter.orderItems);
              dialog.cancel();
              dialog.dismiss();
            }
          });

          cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dialog.cancel();
              dialog.dismiss();
            }
          });
        }
      }
    });

    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
          payBtn.setEnabled(true);
        else
          payBtn.setEnabled(false);
      }
    });

    return view;
  }

  static void setPrice(String price) {
    priceTV.setText(price);
  }

  public static void reloadListView() {
    if(adapter != null)
      adapter.notifyDataSetInvalidated();
    if(listView != null)
      listView.invalidateViews();
  }

  public static void setCheckBox(Boolean b) {
    Log.i(TAG, checkBox + "check");
    if(checkBox != null)
      checkBox.setChecked(b);
  }

  class CartListViewAdapter extends ArrayAdapter<OrderItem> implements View.OnClickListener {
    Button minusBtn, plusBtn, deleteBtn;
    TextView productTV, qtyTV, priceTV;
    ImageView productIV;
    CheckBox checkBox;
    ArrayList<Boolean> booleans;
    ArrayList<OrderItem> orderItems;

    public CartListViewAdapter(@NonNull Context context, @NonNull List<OrderItem> objects) {
      super(context, 0, objects);
      booleans = new ArrayList<>();
    }

    @Override
    public int getCount() {
      if(booleans.size() != super.getCount()) {
        booleans.removeAll(booleans);
        for(int i = 0; i < super.getCount(); i ++) {
          booleans.add(false);
        }
      }
      return super.getCount();
    }

    public void uncheckAll() {
      for(int i = 0; i < booleans.size(); i++)
        booleans.set(i, false);
      notifyDataSetInvalidated();
    }

    public void checkAll() {
      for(int i = 0; i < booleans.size(); i++)
        booleans.set(i, true);
      notifyDataSetInvalidated();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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

      plusBtn.setTag(position);
      plusBtn.setTag(position);
      deleteBtn.setTag(position);

      minusBtn.setOnClickListener(this);
      plusBtn.setOnClickListener(this);
      deleteBtn.setOnClickListener(this);

      checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          booleans.set(position, isChecked);
          checkAllFalse();
        }
      });


      Product product = DataManagement.getProductsById(getItem(position).product_id);
      productTV.setText(product.name);
      qtyTV.setText(String.valueOf(getItem(position).quantity));
      priceTV.setText(String.valueOf((int)DataManagement.getProductsById(getItem(position).product_id).sale_price) + " " + getResources().getString(R.string.taiwan));

      productIV.setImageBitmap(product.img);
      initCheckBox(checkBox, position);

      return convertView;
    }

    void initCheckBox(CheckBox checkBox, int position) {
      if(booleans.get(position)) {
        checkBox.setChecked(true);
      }
      else {
        checkBox.setChecked(false);
      }
    }

    void checkAllFalse() {
      int count = countTrueBoolean();
      if(count != 0) {
        CartFragment.checkBox.setText(count + " " + getResources().getString(R.string.items));
        CartFragment.setCheckBox(new Boolean(true));
        return;
      }
      CartFragment.checkBox.setText(0 + " " + getResources().getString(R.string.items));
      CartFragment.setCheckBox(false);
    }

    int countTrueBoolean() {
      int count = 0;
      orderItems = new ArrayList<>();
      for(int i = 0; i < booleans.size(); i++) {
        if(booleans.get(i)) {
          count ++;
          OrderItem orderItem = getItem(i);
          orderItems.add(orderItem);
        }
      }
      calPrice();
      return count;
    }

    void calPrice() {
      double price = 0;
      for(OrderItem orderItem : orderItems) {
        Product product = DataManagement.getProductsById(orderItem.product_id);
        price = product.sale_price * orderItem.quantity;
      }

      CartFragment.setPrice(String.format("%.0f", price));
    }

    void removeOrder() {
      DataManagement.getOrderCollection().carts.removeAll(orderItems);
      notifyDataSetInvalidated();
    }

    @Override
    public void onClick(View v) {
      int position = (int) v.getTag();
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
                OrderManagement.updateCartQty(getItem(position).id, getItem(position).quantity);
                notifyDataSetInvalidated();
              }
              break;
            case R.id.plusBtn:
              if (getItem(position).quantity < DataManagement.getProductsById(getItem(position).product_id).quantity) {
                getItem(position).quantity += 1;
                OrderManagement.updateCartQty(getItem(position).id, getItem(position).quantity);
                notifyDataSetInvalidated();
              }
              break;
          }
      }
    }
  }


}
