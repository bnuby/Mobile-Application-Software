package com.example.gibson.carlife.Model.Product;

import android.graphics.Bitmap;

public class ProductType {
    public int id;
    public Bitmap image;
    public String name;


    public ProductType(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void setImg(Bitmap image) {
        this.image = image;
    }
}
