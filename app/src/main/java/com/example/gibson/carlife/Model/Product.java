package com.example.gibson.carlife.Model;

import android.graphics.Bitmap;

public class Product {
    public int id;
    public String name;
    public Bitmap img;
    public int quantity;
    public int grade;
    public double tax_rate;
    public double cost_price;
    public double sale_price;
    public int product_brand_id ;
    public int product_type_id;
    public String tag;
    public String created_at;
    public String updated_at;
    public String description;
    public String product_brand;
    public String product_type;


    public Product(int id, String name, int quantity, int grade, double tax_rate, double cost_price, double sale_price, int product_brand_id, int product_type_id, String tag, String created_at, String updated_at, String description, String product_brand, String product_type) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.grade = grade;
        this.tax_rate = tax_rate;
        this.cost_price = cost_price;
        this.sale_price = sale_price;
        this.product_brand_id = product_brand_id;
        this.product_type_id = product_type_id;
        this.tag = tag;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.description = description;
        this.product_brand = product_brand;
        this.product_type = product_type;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
