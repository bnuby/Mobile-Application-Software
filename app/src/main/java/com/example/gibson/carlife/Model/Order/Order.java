package com.example.gibson.carlife.Model.Order;

public class Order {
  public int id;
  public int user_id;
  public OrderStatus status;
  public String address;
  public String refund_message;

  public Order(int id, int user_id, String status, String address) {
    this.id = id;
    this.user_id = user_id;
    this.status = OrderStatus.valueOf(status);
    this.address = address;
  }
}
