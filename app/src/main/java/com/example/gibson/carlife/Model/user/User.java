package com.example.gibson.carlife.Model.user;

import android.graphics.Bitmap;

/**
 * Created by user on 2018/6/3.
 */

public class User {
  public Bitmap image;
  public int userId;
  public String username;
  public String password;
  public String phone;
  public String email;
  public String address;
  public int addressId;

  public User() {

  }

  public User(int userId, String username, String phone, String email, String address,int addressId) {
    this.userId = userId;
    this.username = username;
    this.phone = phone;
    this.email = email;
    this.address = address;
    this.addressId=addressId;
  }
}
