package com.example.gibson.carlife.Model.user;

import android.graphics.Bitmap;

import java.util.ArrayList;

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
  public ArrayList<Address> addresses;

  public User() {
    this.addresses = new ArrayList();
  }

  public User(int userId, String username, String phone, String email) {
    this.userId = userId;
    this.username = username;
    this.phone = phone;
    this.email = email;
    this.addresses = new ArrayList();
  }

  public class Address {
    public int id;
    public String address;
    public String zipcode;
    public String country;

    public Address(int id, String address) {
      this.id = id;
      this.address = address;
      this.zipcode = "";
      this.country = "";
    }

    public Address(int id, String address, String zipcode, String country) {
      this.id = id;
      this.address = address;
      this.zipcode = zipcode;
      this.country = country;
    }
  }

  public void addAddress(int id, String address) {
    this.addresses.add(
            new Address(id, address)
    );
  }

  public void addAddress(int id, String address, String zipcode, String country) {
    this.addresses.add(
            new Address(id, address, zipcode, country)
    );
  }

  public void addAddresses(ArrayList<Address> addresses) {
    for(Address a: addresses)
      this.addresses.add(a);
  }

  public void updateAddress(int address_id, String address) {
    for(Address a : addresses)
      if(a.id == address_id) {
        a.address = address;
        return;
      }
  }
}
