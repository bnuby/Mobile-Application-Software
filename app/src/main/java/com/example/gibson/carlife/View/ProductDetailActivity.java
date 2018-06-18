package com.example.gibson.carlife.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gibson.carlife.Abstract.CustomActivity;
import com.example.gibson.carlife.Adapters.ImagePagerAdapter;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.Order.OrderManagement;
import com.example.gibson.carlife.Services.Product.ProductPicturesManagement;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends CustomActivity {
  private static final String TAG = "ProductDetailActivity";
  public static ArrayList<Bitmap> images;
  private static ImagePagerAdapter imagePagerAdapter;
  private ViewPager viewPager;
  public TextView typeTV, quantityTV, titleTV, introTV, priceTV;
  public Button cartBtn;
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
    final Product item = DataManagement.getProducts().get(position);

    viewPager = (ViewPager) findViewById(R.id.productImgs_viewPager);
    final ImageView heart = findViewById(R.id.heartIMG);
    typeTV = findViewById(R.id.typeTV);
    quantityTV = findViewById(R.id.quanitltyTV);
    titleTV = findViewById(R.id.titleTV);
    introTV = findViewById(R.id.introTV);
    priceTV = findViewById(R.id.priceTV);
    cartBtn = findViewById(R.id.cart);

    images = new ArrayList<>();
    if(item.product_type!=null)
      typeTV.setText(item.product_type);
    else
      typeTV.setText("Null");
    quantityTV.setText(""+item.quantity);
    titleTV.setText(item.name);
    priceTV.setText("NT:" + item.cost_price);
    introTV.setText(item.description);

    if(item.imgs.size() == 0)
      ProductPicturesManagement.requestProductImages(item.id);
    else {
      images = item.imgs;
      imagePagerAdapter.notifyDataSetChanged();
    }

    cartBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //少了Volley
        ProductDetailActivity.longTost( "已放入購物車");
        OrderManagement.addProductToCart(item.id, 1);
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
