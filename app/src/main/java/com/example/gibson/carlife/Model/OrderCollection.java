package com.example.gibson.carlife.Model;

import com.example.gibson.carlife.Model.Order.Order;
import com.example.gibson.carlife.Model.Order.OrderItem;
import com.example.gibson.carlife.Model.Order.OrderStatus;
import com.example.gibson.carlife.View.Fragment.CartFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderCollection {

  public ArrayList<Order> orders;
  public ArrayList<OrderItem> carts;
  public ArrayList<OrderItem> paids;
  public ArrayList<OrderItem> unpays;
  public ArrayList<OrderItem> pending_refunds;
  public ArrayList<OrderItem> cancels;

  public OrderCollection() {
    this.orders = new ArrayList<>();
    this.carts = new ArrayList<>();
    this.paids = new ArrayList<>();
    this.unpays = new ArrayList<>();
    this.pending_refunds = new ArrayList<>();
    this.cancels = new ArrayList<>();
  }

  public void jsonToOrderObject(JSONArray array) {
    for(int i = 0; i < array.length(); i ++) {
      try {
        JSONObject object = array.getJSONObject(i);
        orders.add(new Order(
                object.getInt("id"),
                object.getInt("user_id"),
                object.getString("status"),
                object.getString("address")
        ));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

  }

  public void fillArrayList(JSONArray array) {
    for (int i = 0; i < array.length(); i++) {
      try {
        OrderItem item = jsonToOrderItemObject(array.getJSONObject(i));
        switch (item.status) {
          case cart:
            carts.add(item);
            CartFragment.reloadListView();
            break;
          case paid:
            paids.add(item);
            break;
          case unpay:
            unpays.add(item);
            break;
          case pending_refund:
            pending_refunds.add(item);
            break;
          case cancel:
            cancels.add(item);
            break;
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  OrderItem jsonToOrderItemObject(JSONObject object) {
    OrderItem orderItem = new OrderItem();
    try {
      orderItem.id = object.getInt("id");
      orderItem.status = OrderStatus.valueOf(object.getString("status"));
      if(orderItem.status != OrderStatus.cart)
        orderItem.order_id = object.getInt("order_id");
      orderItem.product_id = object.getInt("product_id");
      orderItem.user_id = object.getInt("user_id");
      orderItem.quantity = object.getInt("quantity");

    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }
    return orderItem;
  }

  public OrderItem findCartByID(int id) {
    for(OrderItem orderItem : carts) {
      if(orderItem.id == id)
        return orderItem;
    }
    return null;
  }

  public void deleteCartByID(int id) {
    carts.remove(findCartByID(id));
  }
}
