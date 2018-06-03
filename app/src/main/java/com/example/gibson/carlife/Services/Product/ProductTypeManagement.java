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
import com.example.gibson.carlife.Services.RequestManager;
import com.example.gibson.carlife.View.Fragment.MainShopFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductTypeManagement extends RequestManager  {

  public static void requestProductType() {
    final String url = host + "/product_type";

    MainActivity.showLoading("Loading");

    StringRequest request = new StringRequest(
            host,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                Log.v("response", response);
                try {
                  JSONArray array = new JSONArray(response);
                  for(int i = 0; i < array.length(); i ++) {
                    JSONObject object = array.getJSONObject(i);

                    
//                    MainShopFragment.products.add(new Product(
//                            object.getInt("id"),
//                            object.getString("name"),
//
//                    ))
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


  public static void getImage(int id) {
    final String url = host + "/product_type_img/" + id;

    ImageRequest request = new ImageRequest(
            url,
            new Response.Listener<Bitmap>() {
              @Override
              public void onResponse(Bitmap response) {

              }
            },
            64,
            64,
            ImageView.ScaleType.FIT_CENTER,
            Bitmap.Config.RGB_565,
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

              }
            }
    );

    MainActivity.volleyQueue.add(request);


  }
}
