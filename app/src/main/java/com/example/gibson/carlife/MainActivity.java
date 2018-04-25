package com.example.gibson.carlife;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;


public class MainActivity extends AppCompatActivity {

  public static RequestQueue volleyQueue;

  ViewPager pager;
  CustomAdapter adapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    pager = findViewById(R.id.viewPager);
    adapter = new CustomAdapter(getSupportFragmentManager());
    pager.setAdapter(adapter);
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

    public CustomAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      switch(position) {
        case 0:
          return new LoginActivity();
      }
      return null;
    }

    @Override
    public int getCount() {
      return 1;
    }


  }
}
