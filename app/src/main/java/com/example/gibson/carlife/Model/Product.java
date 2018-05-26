package com.example.gibson.carlife.Model;

import android.graphics.Bitmap;
import android.media.Image;

public class Product {
    public Bitmap img;
    public String name;
    public String description;
    public double price;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
