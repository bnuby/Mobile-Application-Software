package com.example.gibson.carlife.Services.Product;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.Product;
import com.example.gibson.carlife.Model.ProductType;
import com.example.gibson.carlife.Services.RequestManager;
import com.example.gibson.carlife.View.Fragment.MainShopFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductManagement extends RequestManager{

    public static void requestProduct() {
        final String url = host + "/product";

        MainActivity.showLoading("Loading");

        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("response", response);
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i = 0; i < array.length(); i ++) {
                                JSONObject object = array.getJSONObject(i);
                                int id = object.getInt("id");
                                MainActivity.products.add(
                                        new Product(
                                                id,
                                                object.getString("name"),
                                                object.getInt("quantity"),
                                                object.getInt("grade"),
                                                object.getDouble("tax_rate"),
                                                object.getDouble("cost_price"),
                                                object.getDouble("sale_price"),
                                                object.getInt("product_brand_id"),
                                                object.getInt("product_type_id"),
                                                object.getString("tag"),
                                                object.getString("created_at"),
                                                object.getString("updated_at"),
                                                object.getString("description"),
                                                object.getString("product_brand"),
                                                object.getString("product_type")
                                        ));
                                getImage(id ,i);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        MainActivity.dismissLoading();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        MainActivity.dismissLoading();
                    }
                }
        );

        MainActivity.volleyQueue.add(request);
    }
    public static void getImage(int id, final int i) {
        final String url = host + "/product/get_image/" + id;

        ImageRequest request = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        MainActivity.products.get(i).setImg(response);
                        Log.i("onResponse", "imgok: ");
                        MainShopFragment.reloadGridView2();
                    }
                },
                64,
                64,
                ImageView.ScaleType.FIT_CENTER,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("onErrorResponse: ","Fail to get img");
                    }
                }
        );

        MainActivity.volleyQueue.add(request);
    }

}
