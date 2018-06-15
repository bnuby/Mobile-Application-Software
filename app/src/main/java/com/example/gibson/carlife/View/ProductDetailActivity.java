package com.example.gibson.carlife.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gibson.carlife.Adapters.ImagePagerAdapter;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.Product;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.Product.ProductPictures;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    private static final String TAG = "ProductDetailActivity";

    private ViewPager viewPager;
    private static ImagePagerAdapter imagePagerAdapter;
//    private static final int[] images = { R.drawable.h01,
//            R.drawable.h02, R.drawable.h03 };
    public static ArrayList<Bitmap> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",-1);
        Product item = MainActivity.products.get(position);
        viewPager = (ViewPager) findViewById(R.id.productImgs_viewPager);
        TextView title =findViewById(R.id.titleTV);
        TextView intro = findViewById(R.id.introTV);
        TextView price =findViewById(R.id.priceTV);
        Button buy = findViewById(R.id.buy);
        Button cart = findViewById(R.id.cart);
        images = new ArrayList<>();

        title.setText(item.name);
        price.setText(""+item.cost_price);
        intro.setText(item.description);
        ProductPictures.requestProductImages(item.id);
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
//        for(int i : images){
//            list.add(i);
//        }
        imagePagerAdapter = new ImagePagerAdapter(this, images);
        viewPager.setAdapter(imagePagerAdapter);
    }

    public static void addImage(Bitmap bitmap) {
        images.add(bitmap);
        imagePagerAdapter.notifyDataSetChanged();
    }

}
