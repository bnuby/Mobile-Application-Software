package com.example.gibson.carlife.Services;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.View.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gibson on 2018/4/25.
 */

public class UserManagement extends RequestManager {
    String token;

    public static void requestLogin(final String username, final String password) {
        final String url = host + "/user/login/";
        LoginActivity.showLoading("Sign");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("login", response.toString());
                        LoginActivity.dismissLoading();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("login", error.getMessage());
                        LoginActivity.dismissLoading();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> post = new HashMap<>();

                post.put("username", username);
                post.put("password", password);

                return post;
            }
        };

        MainActivity.volleyQueue.add(request);
    }

    public static void requestRegister(String username, String password, String email, String name, String phone) {
        final String url = host + "/user/";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("email", email);
            jsonObject.put("name", name);
            jsonObject.put("phone", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("register", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("register", error.getMessage());
                    }
                }
        );

        MainActivity.volleyQueue.add(request);
    }



    public static void requestToken(Response.Listener listener) {
//    String url = "http://18.219.196.79/get_token";
//    StringRequest request = new StringRequest(Request.Method.GET,
//            url,
//            listener,
//            new Response.ErrorListener() {
//              @Override
//              public void onErrorResponse(VolleyError error) {
//               Log.e("requestToken", error.getMessage());
//              }
//            });
//    MainActivity.volleyQueue.add(request);
    }


}
