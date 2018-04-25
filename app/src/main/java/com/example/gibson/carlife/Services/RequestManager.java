package com.example.gibson.carlife.Services;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;

/**
 * Created by gibson on 2018/4/25.
 */

public class RequestManager {
  public static boolean requestLogin(String username, String password) {
    String url = "http://18.219.196.79/user/validate/" + username + "/" +password;

    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        Log.v("login", response);
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Log.v("login", error.toString());

      }
    });

    MainActivity.volleyQueue.add(request);

    return true;
  }


}
