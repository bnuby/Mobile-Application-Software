package com.example.gibson.carlife.Model;

public class Favorite {
    public int id;
    public int user_id;
    public int product_id;
    public String created_at;
    public String updated_at;

    public Favorite() {
    }

    public Favorite(int id, int user_id, int product_id) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public Favorite(int id, int user_id, int product_id, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
