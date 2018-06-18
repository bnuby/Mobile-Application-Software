package com.example.gibson.carlife.Services.Order;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Order.Order;
import com.example.gibson.carlife.Model.Order.OrderItem;
import com.example.gibson.carlife.Services.RequestManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OrderManagement extends RequestManager {
  public static String TAG = "OrderManagement";

  public static void requestOrder(final int user_id) {
    final String url = host + "/order/" + user_id + "/user";
    Log.i(TAG, "" + user_id);
    StringRequest request = new StringRequest(
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                try {
                  Log.i(TAG, response);
                  JSONArray array = new JSONArray(response);
                  DataManagement.getOrderCollection().jsonToOrderObject(array);
                  OrderManagement.requestOrderItem(user_id);
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

  public static void requestOrderItem(int id) {
    final String url = host + "/orderitems/" + id + "/user";
    Log.i(TAG, "" + id);
    StringRequest request = new StringRequest(
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                try {
                  Log.i(TAG, response);
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

  public static void updateCartQty(int id, int qty) {
    final String url = host + "/orderitems?quantity=" + qty + "&id=" + id;

    StringRequest request = new StringRequest(
            Request.Method.PUT,
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                Log.i(TAG, response);
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.getMessage());
              }
            }
    );
    MainActivity.volleyQueue.add(request);
  }

  public static void addProductToCart(final int product_id, final int quantity) {
    final String url = host + "/orderitems";

    StringRequest request = new StringRequest(
            Request.Method.POST,
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {

                Log.i(TAG, response);
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.getMessage());
              }
            }
    ) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("product_id", String.valueOf(product_id));
        hashMap.put("quantity", String.valueOf(quantity));
        hashMap.put("status", "cartBtn");
        hashMap.put("user_id", String.valueOf(MainActivity.userObj.userId));
        return hashMap;
      }
    };

    MainActivity.volleyQueue.add(request);
  }

  public static void addOrder(final int user_id, final String address, final String status, final ArrayList<OrderItem> orderItems) {
    final String url = host + "/order";
    StringRequest request = new StringRequest(
            Request.Method.POST,
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                try {
                  JSONObject object = new JSONObject(response);
                  if (object.getBoolean("status")) {
                    Order order = new Order(
                            object.getJSONObject("msg").getInt("id"),
                            object.getJSONObject("msg").getInt("user_id"),
                            object.getJSONObject("msg").getString("status"),
                            object.getJSONObject("msg").getString("address")
                    );
                    DataManagement.getOrderCollection().orders.add(order);
                    for (OrderItem orderItem : orderItems) {
                      updateStatus(order.id, String.valueOf(order.status), orderItem);
                    }
                  }
                } catch (JSONException e) {
                  e.printStackTrace();
                }
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.getMessage());
              }
            }
    ) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> map = new TreeMap<>();
        map.put("user_id", String.valueOf(user_id));
        map.put("status", status);
        map.put("address", address);
        return map;
      }
    };
    MainActivity.volleyQueue.add(request);
  }

  public static void updateStatus(int order_id, String status, OrderItem orderItem) {
    final String url = host + "/orderitems_status/" + orderItem.id + "?status=" + status + "&quantity=" + orderItem.quantity + "&order_id=" + order_id;

    StringRequest request = new StringRequest(
            Request.Method.PUT,
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                Log.i(TAG, response);
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.getMessage());
              }
            }
    );

    MainActivity.volleyQueue.add(request);
  }

  public static void deleteOrder(int id) {
    final String url = host + "/orderitems/" + id;

    StringRequest request = new StringRequest(
            Request.Method.DELETE,
            url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                Log.v(TAG, response);
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                Log.v(TAG, error.getMessage());
              }
            }
    );

    MainActivity.volleyQueue.add(request);

  }

}
