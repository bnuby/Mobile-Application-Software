package com.example.gibson.carlife.Services;

import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.Order.OrderManagement;
import com.example.gibson.carlife.View.AccountManageActivity;
import com.example.gibson.carlife.View.Fragment.AccountFragment;
import com.example.gibson.carlife.View.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gibson on 2018/4/25.
 */

public class UserManagement extends RequestManager {
  public static boolean isLogin = false;
  String token;

  public static void requestLogin(final String username, final String password, final boolean show) {
    final String url = host + "/user/login/";
    if (show)
      LoginActivity.showLoading("Login");
    if (username.equals("") || password.equals("")) {
      LoginActivity.longTost("Please input username and password.");
      LoginActivity.dismissLoading();
      return;
    }
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
                try {
                  JSONObject res = new JSONObject(response);
                  if (res.getString("status").equalsIgnoreCase("true")) {
                    JSONObject user = res.getJSONObject("data");
                    MainActivity.userObj.userId = user.getInt("id");
                    MainActivity.userObj.username = user.getString("username");
                    MainActivity.userObj.email = user.getString("email");
                    MainActivity.userObj.phone = user.getString("phone");
                    requestAddress();
                    OrderManagement.requestOrderItem(MainActivity.userObj.userId);
                    OrderManagement.requestOrder(MainActivity.userObj.userId);

                    // save user to preferences
                    SharedPreferences.Editor editor = MainActivity.mPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.commit();
                    isLogin = true;
                    AccountFragment.toggleLogoutBtn();
                    if (show)
                      LoginActivity.activityFinish();
                  } else {
                    if (show)
                      LoginActivity.longTost(res.getString("msg").toString());
                  }
                } catch (JSONException e) {
                  e.printStackTrace();
                }
                if (show)
                  LoginActivity.dismissLoading();
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                LoginActivity.longTost("Login Fail.");
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

  public static void requestLogout() {
    final String url = host + "/logout/" + MainActivity.userObj.userId;
    MainActivity.longTost(MainActivity.getContext().getResources().getString(R.string.logout));
    StringRequest request = new StringRequest(
            Request.Method.GET,
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                Log.v("logout", response.toString());
                try {
                  JSONObject res = new JSONObject(response);
                  if (res.getString("status").equalsIgnoreCase("true")) {
                    AccountFragment.toggleLogoutBtn();
                    isLogin = false;
                    MainActivity.logout();
                  }
                } catch (JSONException e) {
                  e.printStackTrace();
                }
//                        LoginActivity.dismissLoading();
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                LoginActivity.longTost(MainActivity.getContext().getResources().getString(R.string.error));
                Log.v("logout", error.getMessage());
//                        LoginActivity.dismissLoading();
              }
            }
    );
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

  public static void requestAddress() {
    final String url = host + "/address/user/"+MainActivity.userObj.userId;
    StringRequest request = new StringRequest(
            Request.Method.GET,
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                Log.v("logout", response.toString());
                try {
                  JSONObject res = new JSONObject(response);
                  JSONArray array = new JSONArray(res.getString("msg"));
                  String address;
                  int addressId;
                  try {
                    address = array.getJSONObject(0).getString("address");
                    addressId=array.getJSONObject(0).getInt("id");
                  }
                  catch (Exception e){
                    address ="";
                    addressId=0;
                  }
                  MainActivity.userObj.address = address;
                  MainActivity.userObj.addressId=addressId;
                } catch (JSONException e) {
                  e.printStackTrace();
                }
//                        LoginActivity.dismissLoading();
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

//                        LoginActivity.dismissLoading();
              }
            }
    );
    MainActivity.volleyQueue.add(request);
  }
  public static void updateAddress(String newAddress){
    final JSONObject object = new JSONObject();
    try{
    object.put("address",newAddress);
    }catch (JSONException e){

    }
    final String url = host + "/address/"+MainActivity.userObj.addressId;
    StringRequest request = new StringRequest(
            Request.Method.PUT,
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                try {
                  JSONObject object1 = new JSONObject(response);
                  AccountManageActivity.longTost(object1.getString("msg"));
                }catch (JSONException e){
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
    requestAddress();
  }

  public static void addAddress(final String newAddress){
//    final JSONObject object = new JSONObject();
//    try{
//      object.put("address",newAddress);
//      object.put("user_id",MainActivity.userObj.userId);
//      object.put("zipcode","test");
//      object.put("country","1");
//    }catch (JSONException e){
//
//    }
    final String url = host + "/address";
    StringRequest request = new StringRequest(
            Request.Method.POST,
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                try {
                  JSONObject object1 = new JSONObject(response);
                  AccountManageActivity.longTost(object1.getString("msg"));
                }catch (JSONException e){
                }
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
              }
            }
    ) {
      @Override
      public Map<String, String>  getParams() throws AuthFailureError {
        String userId=""+MainActivity.userObj.userId;
        HashMap<String, String> object = new HashMap<>();
        object.put("address",newAddress);
        object.put("user_id",userId);
        object.put("zipcode","test");
        object.put("country","1");
        return object;
      }
    };
    MainActivity.volleyQueue.add(request);
    UserManagement.requestAddress();

  }

}