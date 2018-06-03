package com.example.gibson.carlife.Model;

import android.graphics.Bitmap;
import android.media.Image;

public class Product {
    public String id;
    public String name;
    public Bitmap img;
    public int quanitity;
    public int grade;
    public float tax;
    public float price;
    public float sale_price;
    public int product_brank_id ;
    public int product_type_id;
    public String tag;
    public String creat;
    public String update;
    public String product_brand;
    public String product_type;
    public String description;

    public Product(String id, String name, Bitmap img, int quanitity, int grade, float tax, float price, float sale_price, int product_brank_id, int product_type_id, String tag, String creat, String update, String product_brand, String product_type, String description) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.quanitity = quanitity;
        this.grade = grade;
        this.tax = tax;
        this.price = price;
        this.sale_price = sale_price;
        this.product_brank_id = product_brank_id;
        this.product_type_id = product_type_id;
        this.tag = tag;
        this.creat = creat;
        this.update = update;
        this.product_brand = product_brand;
        this.product_type = product_type;
        this.description = description;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
