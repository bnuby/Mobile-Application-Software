package com.example.gibson.carlife.View.Fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Order.Order;
import com.example.gibson.carlife.Model.Order.OrderItem;
import com.example.gibson.carlife.Model.Order.OrderStatus;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment implements View.OnClickListener {
  public static String TAG = "OrderFragment";
  static OrderDetailAdapter unpayAdapter, paidAdapter;
  LinearLayout payDetailLL;
  static ListView listView;
  static Context mContext;
  static boolean isPay = false;
  Button payBtn, unPayBtn;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_order, container, false);

    payBtn = view.findViewById(R.id.payBtn);
    unPayBtn = view.findViewById(R.id.unPayBtn);
    listView = view.findViewById(R.id.orderlist);
    payDetailLL = view.findViewById(R.id.payDetailLL);

    mContext = getContext();

    payBtn.setOnClickListener(this);
    unPayBtn.setOnClickListener(this);


    unpayAdapter =
            new OrderDetailAdapter(getContext(),
                    R.layout.order_detail_list_view_item,
                    DataManagement.getOrderCollection().getUnPayOrders(),
                    OrderStatus.unpay
            );

    paidAdapter =
            new OrderDetailAdapter(getContext(),
                    R.layout.order_detail_list_view_item,
                    DataManagement.getOrderCollection().getPaidOrders(),
                    OrderStatus.paid
            );

    if(isPay) {
      payDetailLL.setVisibility(View.GONE);
      listView.setAdapter(paidAdapter);
    } else {
      payDetailLL.setVisibility(View.VISIBLE);
      listView.setAdapter(unpayAdapter);
    }
    return view;
  }

  public static void reloadAdapterAndListView() {

    if(mContext != null) {
      unpayAdapter =
              new OrderDetailAdapter(mContext,
                      R.layout.order_detail_list_view_item,
                      DataManagement.getOrderCollection().getUnPayOrders(),
                      OrderStatus.unpay
              );
    }

    if(mContext != null) {
      paidAdapter =
              new OrderDetailAdapter(mContext,
                      R.layout.order_detail_list_view_item,
                      DataManagement.getOrderCollection().getPaidOrders(),
                      OrderStatus.paid
              );
    }

    if(listView != null) {
      if(isPay)
        listView.setAdapter(paidAdapter);
      else
        listView.setAdapter(unpayAdapter);
      listView.invalidateViews();
    }
  }



  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.payBtn:
        if (!isPay) {
          unPayBtn.setBackground(getActivity().getDrawable(R.drawable.unpay_disable));
          payBtn.setBackground(getActivity().getDrawable(R.drawable.pay_enable));
          listView.setAdapter(paidAdapter);
          payDetailLL.setVisibility(View.GONE);
          isPay = true;
        }
        break;

      case R.id.unPayBtn:
        if (isPay) {
          unPayBtn.setBackground(getActivity().getDrawable(R.drawable.unpay_enable));
          payBtn.setBackground(getActivity().getDrawable(R.drawable.pay_disable));
          listView.setAdapter(unpayAdapter);
          payDetailLL.setVisibility(View.VISIBLE);
          isPay = false;
        }
        break;
    }
    listView.invalidateViews();
//    reloadAdapterAndListView();
  }

  @Override
  public void onResume() {
    super.onResume();
    updateListView();
  }

  public void updateListView() {
    if (isPay) {
      unPayBtn.setBackground(getActivity().getDrawable(R.drawable.unpay_disable));
      payBtn.setBackground(getActivity().getDrawable(R.drawable.pay_enable));
      listView.setAdapter(paidAdapter);
    } else {
      unPayBtn.setBackground(getActivity().getDrawable(R.drawable.unpay_enable));
      payBtn.setBackground(getActivity().getDrawable(R.drawable.pay_disable));
      payDetailLL.setVisibility(View.VISIBLE);
      listView.setAdapter(unpayAdapter);
    }

    listView.invalidateViews();
//    reloadAdapterAndListView();
  }

}

class OrderDetailAdapter extends ArrayAdapter<Order> {

  ListView orderItemLV;
  OrderItemAdapter orderItemAdapter;
  OrderStatus status;
  TextView orderNumberTV, orderAddressTV, statusTV;
  Button deleteBtn;


  public OrderDetailAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects, OrderStatus status) {
    super(context, resource, objects);
    this.status = status;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    if (convertView == null)
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_detail_list_view_item, null);

    orderNumberTV = convertView.findViewById(R.id.orderNumberTV);
    orderAddressTV = convertView.findViewById(R.id.orderAddressTV);
    orderItemLV = convertView.findViewById(R.id.orderItemLV);
    statusTV = convertView.findViewById(R.id.statusTV);
    deleteBtn = convertView.findViewById(R.id.deleteBtn);

    orderNumberTV.setText(String.valueOf(getItem(position).id));
    orderAddressTV.setText(getItem(position).address);
    statusTV.setText(String.valueOf(getItem(position).status));

    ArrayList<OrderItem> orderItems =
            new ArrayList<>();
    switch (status) {
      case unpay:
        orderItems = DataManagement
                .getOrderCollection()
                .getOrderItemsByOrderID(
                        OrderStatus.unpay,
                        getItem(position)
                );
        break;

      case paid:
        orderItems = DataManagement
                .getOrderCollection()
                .getOrderItemsByOrderID(
                        OrderStatus.paid,
                        getItem(position)
                );
        break;
    }

    ViewGroup.LayoutParams params = orderItemLV.getLayoutParams();
    Log.i(OrderFragment.TAG, getCount()+"");

    orderItemAdapter =
            new OrderItemAdapter(
                    getContext(),
                    R.layout.order_item_list_view_item,
                    orderItems
            );

    params.height = (int)(100 * Resources.getSystem().getDisplayMetrics().density * orderItems.size());
    orderItemLV.setAdapter(orderItemAdapter);
    return convertView;
  }

  public void reloadListView() {
    if(orderItemAdapter != null)
      orderItemAdapter.notifyDataSetInvalidated();

    if(orderItemLV != null)
      orderItemLV.invalidateViews();
  }
}

class OrderItemAdapter extends ArrayAdapter<OrderItem> {

  TextView nameTV, qtyTV, priceTV;
  ImageView productIV;

  public OrderItemAdapter(@NonNull Context context, int resource, @NonNull List<OrderItem> objects) {
    super(context, resource, objects);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    if (convertView == null)
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item_list_view_item, null);
    nameTV = convertView.findViewById(R.id.nameTV);
    qtyTV = convertView.findViewById(R.id.qtyTV);
    priceTV = convertView.findViewById(R.id.priceTV);
    productIV = convertView.findViewById(R.id.productIV);

    Product product = DataManagement.getProductsById(getItem(position).product_id);
    String name = product.name;
    double price = product.sale_price * getItem(position).quantity;
    nameTV.setText(name);
    productIV.setImageBitmap(product.img);
    qtyTV.setText(getContext().getString(R.string.qty) + " " + String.valueOf(getItem(position).quantity));
    priceTV.setText(String.format("%s %.0f", getContext().getString(R.string.taiwan), price));

    return convertView;
  }
}
