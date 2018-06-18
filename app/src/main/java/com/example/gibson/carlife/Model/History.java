package com.example.gibson.carlife.Model;

public class History {
    public int id;
    public int user_id;
    public int product_id;
    public String created_at;
    public String updated_at;

    public History(int id, int user_id, int product_id, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
