package com.example.gibson.carlife.Model.Order;

import com.example.gibson.carlife.Model.Product.Product;

public class OrderItem {
  public int id;
  public int order_id;
  public int product_id;
  public int user_id;
  public Product product;
  public int quantity;
  public OrderStatus status;

}
