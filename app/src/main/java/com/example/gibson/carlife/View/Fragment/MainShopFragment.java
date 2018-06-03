package com.example.gibson.carlife.View.Fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gibson.carlife.Adapters.ClassficationGridViewAdapter;
import com.example.gibson.carlife.Adapters.productlistviewAdapter;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.Model.Product;
import com.example.gibson.carlife.Model.ProductBrand;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.Services.Product.ProductBrandManagement;
import com.example.gibson.carlife.Services.Product.ProductManagement;
import com.example.gibson.carlife.Services.Product.ProductTypeManagement;

import java.util.ArrayList;

public class MainShopFragment extends Fragment {

    public static GridView gridView;
    public static GridView gridView2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_shop, container, false);
        Integer[] ids = new Integer[6];
        String[] strings = getResources().getStringArray(R.array.type);
        for(int i = 0; i < 6 ; i ++) {
            ids[i] = (getResources().getIdentifier(strings[i], "drawable", getContext().getPackageName()));
        }

//        ProductTypeManagement.requestProductType();
        gridView = (GridView) view.findViewById(R.id.gvType);
        gridView.setNumColumns(3);
        gridView.setAdapter(new ClassficationGridViewAdapter(getContext(), ids));


        productlistviewAdapter adapter =
        new productlistviewAdapter(getContext(), MainActivity.products,R.layout.listview2);

        gridView2 =(GridView)view.findViewById(R.id.gyType);
        gridView2.setAdapter(adapter);
        return view;
    }

    public static void reloadGridView1() {
        if(gridView != null)
            gridView.invalidateViews();
    }

    public static void reloadGridView2() {
        if(gridView2 != null)
            gridView2.invalidateViews();
    }

}
