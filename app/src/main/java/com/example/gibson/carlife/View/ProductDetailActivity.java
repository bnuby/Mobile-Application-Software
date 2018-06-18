package com.example.gibson.carlife.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gibson.carlife.Abstract.CustomActivity;
import com.example.gibson.carlife.Adapters.ImagePagerAdapter;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.Product.ProductPicturesManagement;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends CustomActivity {
  private static final String TAG = "ProductDetailActivity";
  public static ArrayList<Bitmap> images;
  private static ImagePagerAdapter imagePagerAdapter;
  private ViewPager viewPager;
  public TextView type,quanitlty,title,intro,price;
  public Button buy,cart;
  public boolean isFavorite;


  public static void addImage(Bitmap bitmap) {
    images.add(bitmap);
    imagePagerAdapter.notifyDataSetChanged();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);
    isFavorite=false;

    int position = getIntent().getIntExtra("position", -1);
    Product item = DataManagement.getProducts().get(position);
//        Product item = (Product) getIntent().getSerializableExtra("product");
    viewPager = (ViewPager) findViewById(R.id.productImgs_viewPager);
    final ImageView heart = findViewById(R.id.heartIMG);
    type = findViewById(R.id.typeTV);
    quanitlty  = findViewById(R.id.quanitltyTV);
    title = findViewById(R.id.titleTV);
    intro = findViewById(R.id.introTV);
    price = findViewById(R.id.priceTV);
    buy = findViewById(R.id.buy);
    cart = findViewById(R.id.cart);
    images = new ArrayList<>();
    if(item.product_type!=null)
      type.setText(item.product_type);
    else
      type.setText("Null");
    quanitlty.setText(""+item.quantity);
    title.setText(item.name);
    price.setText("NT:" + item.cost_price);
    intro.setText(item.description);
    if(item.imgs.size() == 0)
      ProductPicturesManagement.requestProductImages(item.id);
    else {
      images = item.imgs;
      imagePagerAdapter.notifyDataSetChanged();
    }
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
        ProductDetailActivity.longTost( "已放入購物車");
      }
    });
    heart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(isFavorite){
          heart.setImageResource(R.drawable.white_heart);
          isFavorite=false;
        }
        else {
          heart.setImageResource(R.drawable.white_heart_fill);
          isFavorite=true;
        }

      }
    });

    List<Integer> list = new ArrayList<Integer>();
    imagePagerAdapter = new ImagePagerAdapter(this, images);
    viewPager.setAdapter(imagePagerAdapter);
  }

}
