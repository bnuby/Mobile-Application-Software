package com.example.gibson.carlife.Services;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HistoryManagerment extends RequestManager {
    public static void getHistory() {
        final String url = host + "/history/user/ "+ MainActivity.userObj.userId;
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray res = new JSONArray(response);
                            for (int i=0;i<res.length();i++) {
                                JSONObject object =res.getJSONObject(i);
//                                MainActivity.History.add(
//                                        object.get("id"),
//                                        object.get("user_id"),
//                                        object.get("product_id"),
//                                        object.get("created_at"),
//                                        object.get("updated_at"));
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


    public static void addHistory(final int productId){
        final String url = host + "/history";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object1 = new JSONObject(response);
                            if(object1.getBoolean("status")){
                                MainActivity.longTost(object1.getString("msg"));
                            }
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
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> object = new HashMap<>();
                object.put("address",""+MainActivity.userObj.userId);
                object.put("user_id",""+""+productId);
                return object;
            }
        };
        MainActivity.volleyQueue.add(request);
    }
    public static void delHistory(final int historyId){
        final String url = host + "/history/"+historyId;
        StringRequest request = new StringRequest(
                Request.Method.DELETE,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object1 = new JSONObject(response);
                            if(object1.getBoolean("status")){
                                MainActivity.longTost(object1.getString("msg"));
                            }
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
    }
}
