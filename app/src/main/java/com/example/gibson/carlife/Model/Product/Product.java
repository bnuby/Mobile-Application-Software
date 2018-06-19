package com.example.gibson.carlife.Model.Product;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class Product {
  public int id;
  public String name;
  public Bitmap img;
  public ArrayList<Bitmap> imgs;
  public int quantity;
  public int grade;
  public double tax_rate;
  public double cost_price;
  public double sale_price;
  public int product_brand_id;
  public int product_type_id;
  public ArrayList<String> tags;
  public String created_at;
  public String updated_at;
  public String description;
  public String product_brand;
  public String product_type;


  public Product(int id, String name, int quantity, int grade, double tax_rate, double cost_price, double sale_price, int product_brand_id, int product_type_id, JSONArray tags, String created_at, String updated_at, String description, String product_brand, String product_type) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.grade = grade;
    this.tax_rate = tax_rate;
    this.cost_price = cost_price;
    this.sale_price = sale_price;
    this.product_brand_id = product_brand_id;
    this.product_type_id = product_type_id;
    this.tags = JSONArrayToArrayList(tags);
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.description = description;
    this.product_brand = product_brand;
    this.product_type = product_type;
    this.imgs = new ArrayList<>();
  }

  ArrayList<String> JSONArrayToArrayList(JSONArray array) {
    ArrayList<String> tags = new ArrayList<>();
    try{
      for(int i = 0; i < array.length(); i++)
        tags.add(array.getString(i));
    } catch (JSONException ex) {
      Log.e("Product", ex.getMessage());
    }
    return tags;
  }

  public void setImg(Bitmap img) {
    this.img = img;
  }

  public void addImgToImgs(Bitmap img) {
    this.imgs.add(img);
  }
}
