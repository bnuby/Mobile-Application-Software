package com.example.gibson.carlife.View;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gibson.carlife.Adapters.ViewpagerAdapter;
import com.example.gibson.carlife.R;

import java.util.ArrayList;
import java.util.List;

public class productDetail extends AppCompatActivity {


    private ViewPager viewPager;
    private ViewpagerAdapter viewpagerAdapter;

    private static final int[] pictures = { R.drawable.h01,
            R.drawable.h02, R.drawable.h03 };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        viewPager = (ViewPager) findViewById(R.id.productImgs_viewPager);

        List<Integer> list = new ArrayList<Integer>();
        for(int i : pictures){
            list.add(i);
        }

        viewpagerAdapter = new ViewpagerAdapter(this, list);
        viewPager.setAdapter(viewpagerAdapter);

    }
}
