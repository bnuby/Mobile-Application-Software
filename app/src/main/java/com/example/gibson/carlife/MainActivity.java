package com.example.gibson.carlife;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.example.gibson.carlife.Abstract.CustomActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Favorite;
import com.example.gibson.carlife.Model.OrderCollection;
import com.example.gibson.carlife.Model.user.User;
import com.example.gibson.carlife.Services.Product.ProductBrandManagement;
import com.example.gibson.carlife.Services.Product.ProductManagement;
import com.example.gibson.carlife.Services.Product.ProductTypeManagement;
import com.example.gibson.carlife.Services.UserManagement;
import com.example.gibson.carlife.View.Fragment.AccountFragment;
import com.example.gibson.carlife.View.Fragment.CartFragment;
import com.example.gibson.carlife.View.Fragment.MainFragment;
import com.example.gibson.carlife.View.Fragment.OrderFragment;


public class MainActivity extends CustomActivity {

  private static final String mSharedPrefFile = "com.example.gibson.carlife";
  public static RequestQueue volleyQueue;
  public static SharedPreferences mPreferences;
  public static User userObj = new User();
  public static Fragment[] fragments = {new MainFragment(), new CartFragment(), new OrderFragment(), new AccountFragment()};
  ViewPager pager;
  CustomAdapter adapter;
  TabLayout tabLayout;
  private int[] IconResID = {R.drawable.home, R.drawable.shoppingcart, R.drawable.order, R.drawable.account};

  public static Context getContext() {
    return mContext;
  }

  public static void logout() {
    // Clear preferences
    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
    preferencesEditor.clear();
    preferencesEditor.commit();

    UserManagement.isLogin = false;
    MainActivity.userObj = null;
    DataManagement.clear();
    AccountFragment.toggleLogoutBtn();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    pager = findViewById(R.id.viewPager);
    tabLayout = findViewById(R.id.tabLayout);
    adapter = new CustomAdapter(getSupportFragmentManager());

    pager.setAdapter(adapter);
    tabLayout.setupWithViewPager(pager);
    for (int i = 0; i < IconResID.length; i++) {
      tabLayout.getTabAt(i).setIcon(IconResID[i]);
    }
    init_queue();
    mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
    userObj.username = mPreferences.getString("username", "");
    userObj.password = mPreferences.getString("password", "");

    if (!MainActivity.userObj.username.equals("") && !MainActivity.userObj.password.equals("")) {
      UserManagement.requestLogin(userObj.username, userObj.password, false);
    }

    initializeDataManagement();
  }

  public void init_queue() {
    // Instantiate the cache
    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

    // Set up the network to use HttpURLConnection as the HTTP client.
    Network network = new BasicNetwork(new HurlStack());

    volleyQueue = new RequestQueue(cache, network);
    volleyQueue.start();
  }

  public void initializeDataManagement() {
    ProductTypeManagement.requestProductType(this);
    ProductBrandManagement.requestProductBrand(this);
    ProductManagement.requestProduct();
  }

  @Override
  protected void onResume() {
    super.onResume();
    mContext = getContext();
  }

  @Override
  protected void onStart() {
    super.onStart();
    mContext = getContext();
  }

  public class CustomAdapter extends FragmentStatePagerAdapter {

    public CustomAdapter(FragmentManager fm) {
      super(fm);
    }


    @Override
    public Fragment getItem(int position) {
      return fragments[position];
    }

    @Override
    public int getCount() {
      return 4;
    }

  }

}
