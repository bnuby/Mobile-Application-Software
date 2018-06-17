package com.example.gibson.carlife.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gibson.carlife.Adapters.ProductListViewAdapter;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

public class OrderFragment extends Fragment {

    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        ArrayList<Product> albumlist = new ArrayList<Product>();
//        Product p = new Product("二手破mac","啟聖女用機",1.0);
//        p.setImg(BitmapFactory.decodeResource(getResources(), R.drawable.h01));
//
//        albumlist.add(p);
//        Product p = new Product("二手破mac","啟聖女用機",1.0);
        ProductListViewAdapter adapter =
                new ProductListViewAdapter(getContext(), albumlist,R.layout.listview1);

       listView = view.findViewById(R.id.orderlist);
       listView.setAdapter(adapter);

//        ArrayList<String> orderlist = new ArrayList<>();
//        orderlist.add("5");
//        mAdapter = new RecycleAdapter(getContext(), orderlist);
//        buyRecyclerView = (RecyclerView)view.findViewById(R.id.oredlistRV);
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        buyRecyclerView.setLayoutManager(layoutManager);
//        buyRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
