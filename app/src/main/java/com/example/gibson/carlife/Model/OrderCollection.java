package com.example.gibson.carlife.Model;

import com.example.gibson.carlife.Model.Order.Order;
import com.example.gibson.carlife.Model.Order.OrderStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderCollection {

  public ArrayList<Order> carts;
  public ArrayList<Order> paids;
  public ArrayList<Order> unpays;
  public ArrayList<Order> pending_refunds;
  public ArrayList<Order> cancels;

  public OrderCollection() {
    this.carts = new ArrayList<>();
    this.paids = new ArrayList<>();
    this.unpays = new ArrayList<>();
    this.pending_refunds = new ArrayList<>();
    this.cancels = new ArrayList<>();
  }

  public void fillArrayList (JSONArray array) {
    for(int i = 0; i < array.length(); i++) {
      try {
        Order item = jsonToOrderObject(array.getJSONObject(i));
        switch (item.status) {
          case cart:
            carts.add(item);
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

  Order jsonToOrderObject(JSONObject object) {
    Order order = new Order();
    try {
      order.id = object.getInt("id");
      order.order_id = object.getInt("id");
      order.product_id = object.getInt("id");
      order.user_id = object.getInt("id");
      order.quantity = object.getInt("id");
      order.status = OrderStatus.valueOf(object.getString("status"));
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }
    return order;
  }
}
