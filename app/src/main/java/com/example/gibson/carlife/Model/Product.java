package com.example.gibson.carlife.Model;

import android.media.Image;

public class Product {
    public int img;
    public String name;
    public String description;
    public double price;

    public Product(int img, String name, String description, double price) {
        this.img = img;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
