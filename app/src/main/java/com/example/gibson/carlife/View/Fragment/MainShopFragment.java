package com.example.gibson.carlife.View.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;

import com.example.gibson.carlife.Adapters.BrandGridViewAdapter;
import com.example.gibson.carlife.Adapters.TypeGridViewAdapter;
import com.example.gibson.carlife.Adapters.ProductListViewAdapter;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.DataManagement;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.Model.Product.ProductBrand;
import com.example.gibson.carlife.Model.Product.ProductType;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.ProductDetailActivity;

import java.util.ArrayList;

public class MainShopFragment extends Fragment {

  public static GridView typeGridView;
  public static GridView brandGridView;
  public static GridView productGridView;

  public static void reloadTypeGV() {
    if (typeGridView != null)
      typeGridView.invalidateViews();
  }

  public static void reloadBrandGV() {
    if (brandGridView != null)
      brandGridView.invalidateViews();
  }

  public static void reloadProductGV() {
    if (productGridView != null)
      productGridView.invalidateViews();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Nullable
  @Override
  public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main_shop, container, false);

    Log.v("Create", "View");
    typeGridView = (GridView) view.findViewById(R.id.TypeGV);

    ViewGroup.LayoutParams params = typeGridView.getLayoutParams();
    float dp = Resources.getSystem().getDisplayMetrics().density;
    typeGridView.setLayoutParams(params);
//    typeGridView.setLayoutParams(new GridLayout.LayoutParams());

    final ArrayList<ProductType> productTypes = DataManagement.getProductTypes();

    TypeGridViewAdapter adapter = new TypeGridViewAdapter(getContext(), productTypes, 7);
    typeGridView.setAdapter(adapter);
    typeGridView.setNumColumns(adapter.getTotalCount());
    params.width = (int) (dp * 100 * adapter.getTotalCount());
    typeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MainActivity.shortTost("To search : " + productTypes.get(i).name);
      }
    });

    final ArrayList<ProductBrand> productBrands = DataManagement.getProductBrands();

    brandGridView = (GridView) view.findViewById(R.id.BrandGV);
    params = brandGridView.getLayoutParams();

    BrandGridViewAdapter adapter2 = new BrandGridViewAdapter(getContext(), productBrands, 7);
    params.width = (int)(dp * 100 * adapter2.getTotalCount());
    brandGridView.setNumColumns(adapter2.getTotalCount());
    brandGridView.setAdapter(adapter2);
    brandGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MainActivity.shortTost("To search : " + productBrands.get(i).name);
      }
    });

    final ArrayList<Product> products = DataManagement.getProducts();

    ProductListViewAdapter adapter3 =
            new ProductListViewAdapter(getContext(), products, R.layout.listview2);
    productGridView = (GridView) view.findViewById(R.id.gvProductGV);
    productGridView.setAdapter(adapter3);
    productGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), ProductDetailActivity.class);
//                intent.putExtra("productGridView", (Serializable)MainActivity.products.get(i));
        intent.putExtra("position", i);
        startActivity(intent);
      }
    });
    return view;
  }

}
