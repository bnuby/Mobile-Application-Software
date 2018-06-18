package com.example.gibson.carlife.Services;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.View.Fragment.AccountFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FavoriteManager extends RequestManager {
    public static void getFavorites() {
        if(UserManagement.isLogin){
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
                                    JSONArray favoriteObjects =new JSONArray(res.getJSONObject("msg"));
                                    for (int i=0;i<favoriteObjects.length();i++){
//                                        MainActivity.favorites.add(
//                                                favoriteObjects.getJSONObject(i).get("id"),
//                                                favoriteObjects.getJSONObject(i).get("user_id"),
//                                                favoriteObjects.getJSONObject(i).get("product_id"),
//                                                favoriteObjects.getJSONObject(i).get("created_at"),
//                                                favoriteObjects.getJSONObject(i).get("updated_at"));
                                    }
                                }
                                else{
                                    MainActivity.longTost("No Favorite product.");
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
    }
    public static void addFavorite(final int productId){
        final String url = host + "/favorite";
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
        UserManagement.requestAddress();
    }
    public static void delFavorite(final int favoriteId){
        final String url = host + "/favorite"+favoriteId;
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
