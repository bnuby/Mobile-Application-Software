package com.example.gibson.carlife;

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
import com.example.gibson.carlife.View.AccountActivity;
import com.example.gibson.carlife.View.LoginActivity;
import com.example.gibson.carlife.View.Fragment.MainShopFragment;
import com.example.gibson.carlife.View.Fragment.SearchResultFragment;
import com.example.gibson.carlife.View.MainFragment;
import com.example.gibson.carlife.View.ShopCartFragment;


public class MainActivity extends CustomActivity {

  public static RequestQueue volleyQueue;


  ViewPager pager;
  CustomAdapter adapter;
  TabLayout tabLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    pager = findViewById(R.id.viewPager);
    tabLayout = findViewById(R.id.tabLayout);
    adapter = new CustomAdapter(getSupportFragmentManager());

//    pager.onInterceptTouchEvent();

    pager.setAdapter(adapter);
    tabLayout.setupWithViewPager(pager);
    init_queue();

  }

  public void init_queue() {
    // Instantiate the cache
    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

    // Set up the network to use HttpURLConnection as the HTTP client.
    Network network = new BasicNetwork(new HurlStack());

    volleyQueue = new RequestQueue(cache, network);
    volleyQueue.start();

  }

  public class CustomAdapter extends FragmentStatePagerAdapter {

    MainFragment mainShopFragment;
    SearchResultFragment orderFragment;
    ShopCartFragment shopCartFragment;
    LoginActivity loginActivity;
    AccountActivity accountActivity;

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
            orderFragment = new SearchResultFragment();
          return orderFragment;
        case 3:
          if(accountActivity == null)
            accountActivity = new AccountActivity();
          return accountActivity;
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
