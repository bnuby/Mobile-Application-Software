package com.example.gibson.carlife.Services;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Favorite;
import com.example.gibson.carlife.Services.Order.DataCleanManager;
import com.example.gibson.carlife.View.Fragment.AccountFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FavoriteManagerment extends RequestManager {

  public static final String TAG = "FavoriteManagement";


    public static void getFavorites() {
      DataManagement.getFavorite();
            final String url = host + "/favorite/user/"+MainActivity.userObj.userId;
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject res = new JSONObject(response);
                                if (res.getBoolean("status")){
                                    JSONArray favoriteObjects = res.getJSONArray("msg");
                                    for (int i=0;i < favoriteObjects.length();i++){
                                        DataManagement.getFavorite().add(
                                                new Favorite(favoriteObjects.getJSONObject(i).getInt("id"),
                                                        favoriteObjects.getJSONObject(i).getInt("user_id"),
                                                        favoriteObjects.getJSONObject(i).getInt("product_id"),
                                                        favoriteObjects.getJSONObject(i).getString("created_at"),
                                                        favoriteObjects.getJSONObject(i).getString("updated_at")));
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

    public static void addFavorite(final int product_id, final int user_id){
        final String url = host + "/favorite";
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
                                Favorite favorite = new Favorite(
                                        curr_id,
                                        user_id,
                                        product_id
                                );
                                DataManagement.getFavorite().add(favorite);
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
    }
    public static void delFavorite(final int favoriteId){
        final String url = host + "/favorite/"+favoriteId;
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
