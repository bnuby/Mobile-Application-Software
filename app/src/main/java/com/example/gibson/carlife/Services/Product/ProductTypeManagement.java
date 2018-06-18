package com.example.gibson.carlife.Services.Product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.ProductType;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.RequestManager;
import com.example.gibson.carlife.View.Fragment.MainShopFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductTypeManagement extends RequestManager {

  public static String TAG = "ProductTypeManagement";

  public static void requestProductType(final Context mCtx) {
    final String url = host + "/product_type";

//    MainActivity.showLoading("Loading");

    StringRequest request = new StringRequest(
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                Log.i(TAG, response);
                try {
                  JSONArray array = new JSONArray(response);
                  for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    int currentId = object.getInt("id");
                    DataManagement.getProductTypes().add(
                            new ProductType(
                                    currentId,
                                    object.getString("name"),
                                    BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.default_image)
                            ));
                    MainShopFragment.reloadTypeGV();
                    getImage(i, currentId);
                  }
                } catch (JSONException e) {
                  e.printStackTrace();
                }
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

              }
            }
    );

    MainActivity.volleyQueue.add(request);
  }


  public static void getImage(final int position, int id) {
    final String url = host + "/product_type_img/" + id;

    ImageRequest request = new ImageRequest(
            url,
            new Response.Listener<Bitmap>() {
              @Override
              public void onResponse(Bitmap response) {
                Log.i(TAG, "get Img");
                DataManagement.getProductTypes().get(position).setImg(response);
                MainShopFragment.reloadTypeGV();
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
