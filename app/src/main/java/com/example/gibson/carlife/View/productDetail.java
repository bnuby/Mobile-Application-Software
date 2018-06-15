package com.example.gibson.carlife.View;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        Button buy = findViewById(R.id.buy);
        Button cart =findViewById(R.id.cart);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;//沒做
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //少了Volley
                Toast.makeText(getBaseContext(),"已放入購物車",Toast.LENGTH_LONG).show();
            }
        });

        List<Integer> list = new ArrayList<Integer>();
        for(int i : pictures){
            list.add(i);
        }

        viewpagerAdapter = new ViewpagerAdapter(this, list);
        viewPager.setAdapter(viewpagerAdapter);

    }
}
