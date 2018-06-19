package com.example.gibson.carlife.Services;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.History;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HistoryManagerment extends RequestManager {
    private static final String TAG = "HistoryManagerment";
    public static void getHistory() {
        final String url = host + "/history/user/ "+ MainActivity.userObj.userId;
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject res = new JSONObject(response);
                            if (res.getBoolean("status")){
                                JSONArray historyObjects = res.getJSONArray("msg");
                                for (int i=0;i < historyObjects.length();i++){
                                    DataManagement.getHistorys().add(
                                            new History(historyObjects.getJSONObject(i).getInt("id"),
                                                    historyObjects.getJSONObject(i).getInt("user_id"),
                                                    historyObjects.getJSONObject(i).getInt("product_id"),
                                                    historyObjects.getJSONObject(i).getString("created_at"),
                                                    historyObjects.getJSONObject(i).getString("updated_at")));
                                }
                            }
                            else{
                            }
                        } catch (JSONException e) {
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


    public static void addHistory(final int product_id,final int user_id){
        final String url = host + "/history";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i(TAG, response);
                            JSONObject object1 = new JSONObject(response);
                            if(object1.getBoolean("status")){
                                JSONObject object = object1.getJSONObject("msg");
                                MainActivity.longTost("Done");
                                int curr_id = object.getInt("id");
                                History history = new History(
                                        curr_id,
                                        user_id,
                                        product_id
                                );
                                DataManagement.getHistorys().add(history);
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
                object.put("product_id",""+product_id);
                object.put("user_id",""+user_id);
                return object;
            }
        };
        MainActivity.volleyQueue.add(request);
        UserManagement.requestAddress();
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
        UserManagement.requestAddress();
    }
}
