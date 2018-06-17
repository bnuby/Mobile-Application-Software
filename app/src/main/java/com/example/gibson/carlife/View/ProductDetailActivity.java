package com.example.gibson.carlife.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gibson.carlife.Adapters.ImagePagerAdapter;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.Product.ProductPicturesManagement;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
  private static final String TAG = "ProductDetailActivity";
  //    private static final int[] images = { R.drawable.h01,
//            R.drawable.h02, R.drawable.h03 };
  public static ArrayList<Bitmap> images;
  private static ImagePagerAdapter imagePagerAdapter;
  private ViewPager viewPager;

  public static void addImage(Bitmap bitmap) {
    images.add(bitmap);
    imagePagerAdapter.notifyDataSetChanged();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);

    int position = getIntent().getIntExtra("position", -1);
    Product item = DataManagement.getProducts().get(position);
//        Product item = (Product) getIntent().getSerializableExtra("product");
    viewPager = (ViewPager) findViewById(R.id.productImgs_viewPager);
    TextView type = findViewById(R.id.typeTV);
    TextView quanitlty  = findViewById(R.id.quanitltyTV);
    TextView title = findViewById(R.id.titleTV);
    TextView intro = findViewById(R.id.introTV);
    TextView price = findViewById(R.id.priceTV);
    Button buy = findViewById(R.id.buy);
    Button cart = findViewById(R.id.cart);
    images = new ArrayList<>();
    if(item.product_type!=null)
      type.setText(item.product_type);
    else
      type.setText("Null");
    quanitlty.setText(""+item.quantity);
    title.setText(item.name);
    price.setText("NT:" + item.cost_price);
    intro.setText(item.description);
    ProductPicturesManagement.requestProductImages(item.id);
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
        Toast.makeText(getBaseContext(), "已放入購物車", Toast.LENGTH_LONG).show();
      }
    });

    List<Integer> list = new ArrayList<Integer>();
    imagePagerAdapter = new ImagePagerAdapter(this, images);
    viewPager.setAdapter(imagePagerAdapter);
  }

}
