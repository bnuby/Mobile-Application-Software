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
import android.widget.CheckBox;
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
import com.example.gibson.carlife.Services.Order.OrderManagement;
import com.example.gibson.carlife.Services.UserManagement;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment implements View.OnClickListener {
  public static String TAG = "OrderFragment";
  static OrderDetailAdapter unpayAdapter, paidAdapter;
  static CheckBox checkAllItem;
  LinearLayout payDetailLL;
  static ListView listView;
  static Context mContext;
  static LinearLayout notLoginLL;
  static TextView priceTV;
  static boolean isPay = false;
  Button payBtn, unPayBtn, paidBtn;
  ImageView deleteIV;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_order, container, false);

    payBtn = view.findViewById(R.id.payBtn);
    unPayBtn = view.findViewById(R.id.unPayBtn);
    paidBtn = view.findViewById(R.id.paidBtn);
    deleteIV = view.findViewById(R.id.cancelIV);
    listView = view.findViewById(R.id.orderlist);
    payDetailLL = view.findViewById(R.id.payDetailLL);
    notLoginLL = view.findViewById(R.id.notLoginLL);
    checkAllItem = view.findViewById(R.id.checkItem);
    priceTV = view.findViewById(R.id.priceTV);
    mContext = getContext();

    checkAllItem.setChecked(false);
    checkAllItem.setText(String.format("0 %s", getString(R.string.items)));


    checkAllItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        unpayAdapter.toggleCheckAllItem(checkAllItem.isChecked());
      }
    });

    payBtn.setOnClickListener(this);
    unPayBtn.setOnClickListener(this);
    paidBtn.setOnClickListener(this);
    deleteIV.setOnClickListener(this);



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

  @Override
  public void onStart() {
    super.onStart();
    checkNoLogin();
    checkAllItem.setChecked(false);
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

  public static void checkNoLogin() {
    if(notLoginLL != null)
    if(UserManagement.isLogin) {
      notLoginLL.setVisibility(View.GONE);
    } else {
      notLoginLL.setVisibility(View.VISIBLE);
    }
  }

  public static void updateCheckItemStatus() {
    int size = unpayAdapter.orders.size();
    OrderFragment.checkAllItem.setText(size + " " + mContext.getString(R.string.items));
    if(size == 0)
      OrderFragment.checkAllItem.setChecked(false);
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

      case R.id.paidBtn:
        unpayAdapter.payAllOrder();
        updateCheckItemStatus();
        break;

      case R.id.cancelIV:
        unpayAdapter.cancelAllOrder();
        updateCheckItemStatus();
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
  ArrayList<Boolean> booleans;
  ArrayList <Order> orders;

  public OrderDetailAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects, OrderStatus status) {
    super(context, resource, objects);
    this.status = status;
    booleans = new ArrayList<>();
    orders = new ArrayList<>();
    for(int i = 0; i < objects.size(); i++)
      booleans.add(false);
  }

  @NonNull
  @Override
  public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    if (convertView == null)
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_detail_list_view_item, null);

    orderNumberTV = convertView.findViewById(R.id.orderNumberTV);
    orderAddressTV = convertView.findViewById(R.id.orderAddressTV);
    orderItemLV = convertView.findViewById(R.id.orderItemLV);
    statusTV = convertView.findViewById(R.id.statusTV);
    deleteBtn = convertView.findViewById(R.id.deleteBtn);
    final CheckBox checkBox = convertView.findViewById(R.id.checkBox);


    if(booleans.get(position))
      checkBox.setChecked(true);
    else
      checkBox.setChecked(false);

    checkBox.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(checkBox.isChecked())
          orders.add(getItem(position));
        else
          orders.remove(getItem(position));

        if(orders.size() == 0) {
          OrderFragment.priceTV.setText(getContext().getResources().getString(R.string.taiwan) + " 0");
        } else {
          OrderFragment.priceTV.setText(getContext().getResources().getString(R.string.taiwan) + " " + sumOrderPrice(orders));
        }

        OrderFragment.updateCheckItemStatus();
//        OrderFragment.checkAllItem.setText(orders.size() + " " + getContext().getString(R.string.items));
        booleans.set(position, checkBox.isChecked());
        checkCheckItems();
      }
    });

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
        checkBox.setVisibility(View.GONE);
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

  public double sumOrderPrice(ArrayList<Order> orders) {
    double price = 0;
    for(Order order: orders) {
      for(OrderItem orderItem: order.orderItems) {
        Product product = DataManagement.getProductsById(orderItem.product_id);
        price += product.sale_price * orderItem.quantity;
      }
    }
    return price;
  }

  public void toggleCheckAllItem(boolean b) {

    for(int i = 0; i < booleans.size(); i ++) {
      booleans.set(i, b);
      if(b)
        orders.add(getItem(i));
      else {
        orders.clear();
      }
    }

    if(b)
      OrderFragment.priceTV.setText(getContext().getResources().getString(R.string.taiwan) + " " + sumOrderPrice(orders));
    else
      OrderFragment.priceTV.setText(getContext().getResources().getString(R.string.taiwan) + " 0");

    OrderFragment.checkAllItem.setText(orders.size() + " " + getContext().getString(R.string.items));
    notifyDataSetChanged();
  }

  public void reloadListView() {
    if(orderItemAdapter != null)
      orderItemAdapter.notifyDataSetInvalidated();

    if(orderItemLV != null)
      orderItemLV.invalidateViews();
  }


  void checkCheckItems() {
    for(Boolean b : booleans)
      if(b) {
        OrderFragment.checkAllItem.setChecked(true);
        return;
      }
    OrderFragment.checkAllItem.setChecked(false);
  }

  void payAllOrder() {
    for(Order order: orders) {
      ArrayList<OrderItem> orderItems = DataManagement.getOrderCollection().getOrderItemsByOrderID(OrderStatus.unpay, order);
      order.status = OrderStatus.paid;
      OrderManagement.paidOrder(order);
      for(OrderItem orderItem : orderItems) {
        orderItem.status = OrderStatus.paid;
        DataManagement.getOrderCollection().unpays.remove(orderItem);
        DataManagement.getOrderCollection().paids.add(orderItem);
      }
    }
    orders.clear();
    OrderFragment.reloadAdapterAndListView();
  }

  void cancelAllOrder() {
    for(Order order: orders) {
      ArrayList<OrderItem> orderItems = DataManagement.getOrderCollection().getOrderItemsByOrderID(OrderStatus.unpay, order);
      order.status = OrderStatus.cancel;
      OrderManagement.cancelOrder(order);
      for(OrderItem orderItem : orderItems) {
        orderItem.status = OrderStatus.cancel;
        DataManagement.getOrderCollection().unpays.remove(orderItem);
        DataManagement.getOrderCollection().cancels.add(orderItem);
      }
    }
    orders.clear();
    OrderFragment.reloadAdapterAndListView();
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
