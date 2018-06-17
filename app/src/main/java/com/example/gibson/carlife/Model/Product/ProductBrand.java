package com.example.gibson.carlife.Model.Product;

import android.graphics.Bitmap;

public class ProductBrand {
    public int id;
    public int grade;
    public Bitmap image;
    public String name;
    public String created_at;
    public String updated_at;

    public ProductBrand(int id, int grade, String name, String created_at, String updated_at) {
        this.id = id;
        this.grade = grade;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public void setImg(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }
}
