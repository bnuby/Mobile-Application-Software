package com.example.gibson.carlife;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.UserManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.example.gibson.carlife.Abstract.CustomActivity;
import com.example.gibson.carlife.Model.Product;
import com.example.gibson.carlife.Model.ProductBrand;
import com.example.gibson.carlife.Model.ProductType;
import com.example.gibson.carlife.Services.Product.ProductManagement;
import com.example.gibson.carlife.Model.User;
import com.example.gibson.carlife.Services.UserManagement;
import com.example.gibson.carlife.View.Fragment.AccountFragment;
import com.example.gibson.carlife.View.LoginActivity;
import com.example.gibson.carlife.View.Fragment.MainFragment;
import com.example.gibson.carlife.View.Fragment.OrderFragment;
import com.example.gibson.carlife.View.Fragment.ShopCartFragment;

import java.util.ArrayList;


public class MainActivity extends CustomActivity {

  public static ArrayList<Product> products;
  public static ArrayList<ProductBrand> productbrands;
  public static ArrayList<ProductType> productTypes;
  public static RequestQueue volleyQueue;

  public static Context getContext() {
    return mContext;
  }

  ViewPager pager;
  CustomAdapter adapter;
  TabLayout tabLayout;

  public static SharedPreferences mPreferences;
  private static final String mSharedPrefFile = "com.example.gibson.carlife";
  public static User userObj = new User();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    pager = findViewById(R.id.viewPager);
    tabLayout = findViewById(R.id.tabLayout);
    adapter = new CustomAdapter(getSupportFragmentManager());

    pager.setAdapter(adapter);
    tabLayout.setupWithViewPager(pager);
    init_queue();

    mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
    userObj.username = mPreferences.getString("username","");
    userObj.password = mPreferences.getString("password","");

    if(!MainActivity.userObj.username.equals("") && !MainActivity.userObj.password.equals("")){
      UserManagement.requestLogin(userObj.username, userObj.password, false);
    }

//    pager.onInterceptTouchEvent();
    products = new ArrayList<>();
    productbrands = new ArrayList<>();
    productTypes = new ArrayList<>();
    ProductManagement.requestProduct();
  }

  public void init_queue() {
    // Instantiate the cache
    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

    // Set up the network to use HttpURLConnection as the HTTP client.
    Network network = new BasicNetwork(new HurlStack());

    volleyQueue = new RequestQueue(cache, network);
    volleyQueue.start();

  }

  public static void logout() {
    // Clear preferences
    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
    preferencesEditor.clear();
    preferencesEditor.commit();
//    preferencesEditor.apply();
  }

  @Override
  protected void onResume() {
    super.onResume();
    mContext = getContext();
  }

  public class CustomAdapter extends FragmentStatePagerAdapter {

    MainFragment mainShopFragment;
    OrderFragment orderFragment;
    ShopCartFragment shopCartFragment;
    LoginActivity loginActivity;
    AccountFragment accountFragment;

    public CustomAdapter(FragmentManager fm) {
      super(fm);
    }


    @Override
    public Fragment getItem(int position) {
      switch(position) {
        case 0:
          if(mainShopFragment == null)
            mainShopFragment = new MainFragment();

          return mainShopFragment;
        case 1:
          if(shopCartFragment == null)
            shopCartFragment = new ShopCartFragment();
          return shopCartFragment;
        case 2:
          if(orderFragment == null)
            orderFragment = new OrderFragment();
          return orderFragment;
        case 3:
          if(accountFragment == null)
            accountFragment = new AccountFragment();
          return accountFragment;
    }
      return null;
    }

    @Override
    public int getCount() {
      return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      String[] title = new String[] {
              "Shop",
              "Cart",
              "Order",
              "Account"
      };
      return title[position];
    }
  }

}
