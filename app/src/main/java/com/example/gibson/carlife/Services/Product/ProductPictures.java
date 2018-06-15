package com.example.gibson.carlife.Services.Product;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Services.RequestManager;
import com.example.gibson.carlife.View.ProductDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ProductPictures extends RequestManager {
    private static final String TAG = "ProductPictures";
    public static void requestProductImages(final int id) {
        final String url = host + "/product/get_images/" + id;
        MainActivity.showLoading("Loading");
        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);
                            ArrayList<Integer> ids = new ArrayList<>();

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                int curr_id = object.getInt("id");
                                Log.v(TAG, "" + object.getInt("id"));
                                requestImageAndAddPicture(curr_id);

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

    public static void requestImageAndAddPicture(int id) {
        final String url = host + "/product_images/" + id;

        ImageRequest request = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Log.i(TAG, "onResponse: ");
                        ProductDetailActivity.addImage(response);
                    }
                },
                200,
                250,
                ImageView.ScaleType.FIT_CENTER,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("onErrorResponse: ", "Fail to get img");
                    }
                }
        );
        MainActivity.volleyQueue.add(request);
    }
}


