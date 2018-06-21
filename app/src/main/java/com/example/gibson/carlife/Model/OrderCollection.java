package com.example.gibson.carlife.Model;

import com.example.gibson.carlife.Model.Order.Order;
import com.example.gibson.carlife.Model.Order.OrderItem;
import com.example.gibson.carlife.Model.Order.OrderStatus;
import com.example.gibson.carlife.View.Fragment.CartFragment;
import com.example.gibson.carlife.View.Fragment.OrderFragment;

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

  public boolean updateCartQuantity(int product_id, int quantity) {
    for(OrderItem cart : carts)
      if(cart.product_id == product_id) {
        cart.quantity += quantity;
        CartFragment.reloadListView();
        return true;
      }
    return false;
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
    fillOrderListOrderItem();
  }

  public void fillOrderListOrderItem() {
    ArrayList<OrderItem> unpays = DataManagement.getOrderCollection().unpays;
    ArrayList<OrderItem> paids = DataManagement.getOrderCollection().paids;
    ArrayList<OrderItem> pending_refunds = DataManagement.getOrderCollection().pending_refunds;
    ArrayList<OrderItem> cancels = DataManagement.getOrderCollection().cancels;

    putOrderItemIntoOrder(unpays);
    putOrderItemIntoOrder(paids);
    putOrderItemIntoOrder(pending_refunds);
    putOrderItemIntoOrder(cancels);
  }

  void putOrderItemIntoOrder(ArrayList<OrderItem> orderItems) {
    for (OrderItem orderItem : orderItems) {
      Order order = findOrderByID(orderItem.order_id);
      order.orderItems.add(orderItem);
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

  public ArrayList<Order> getUnPayOrders() {
    ArrayList<Order> unpays = new ArrayList<>();

    for(Order order: orders)
      if(order.status == OrderStatus.unpay)
        unpays.add(order);
    return unpays;
  }

  public ArrayList<Order> getPaidOrders() {
    ArrayList<Order> paids = new ArrayList<>();
    for(Order order: orders)
      if(order.status == OrderStatus.paid)
        paids.add(order);
    return paids;
  }

  public ArrayList<Order> getPedingRefundOrders() {
    ArrayList<Order> pendingRefunds = new ArrayList<>();
    for(Order order: orders)
      if(order.status == OrderStatus.pending_refund)
        pendingRefunds.add(order);
    return pendingRefunds;
  }

  public ArrayList<Order> getCancelOrders() {
    ArrayList<Order> cancels = new ArrayList<>();
    for(Order order: orders)
      if(order.status == OrderStatus.cancel)
        cancels.add(order);
    return cancels;
  }

  public ArrayList<OrderItem> getOrderItemsByOrderID(OrderStatus status, Order order) {
    ArrayList<OrderItem> orderItems = new ArrayList<>();

    switch (status) {
      case unpay:
        for(OrderItem orderItem: unpays)
          if(orderItem.order_id == order.id)
            orderItems.add(orderItem);
        break;
      case paid:
        for(OrderItem orderItem: paids)
          if(orderItem.order_id == order.id)
            orderItems.add(orderItem);
        break;
      case cancel:
        for(OrderItem orderItem: cancels)
          if(orderItem.order_id == order.id)
            orderItems.add(orderItem);
        break;
      case pending_refund:
        for(OrderItem orderItem: pending_refunds)
          if(orderItem.order_id == order.id)
            orderItems.add(orderItem);
        break;
    }
    return orderItems;
  }

  public Order findOrderByID(int id) {
    for(Order order : orders) {
      if(order.id == id)
        return order;
    }
    return null;
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
