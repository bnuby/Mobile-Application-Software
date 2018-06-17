package com.example.gibson.carlife.Services.Order;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Services.RequestManager;

import org.json.JSONArray;
import org.json.JSONException;

public class OrderManagement extends RequestManager {
  public static String Tag = "OrderManagement";

  public static void requestOrder() {
    final String url = host + "/order/" + "1" + "/user";
    StringRequest request = new StringRequest(
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                try {
                  Log.i(Tag, response);
                  JSONArray array = new JSONArray(response);
                  DataManagement.getOrderCollection().fillArrayList(array);
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

}
