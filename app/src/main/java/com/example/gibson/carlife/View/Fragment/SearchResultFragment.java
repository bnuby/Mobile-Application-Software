package com.example.gibson.carlife.View.Fragment;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Switch;

import com.example.gibson.carlife.Adapters.ProductListViewAdapter;
import com.example.gibson.carlife.Model.Product.Product;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

public class SearchResultFragment extends Fragment {

    private ListView listView;
    Switch aSwitch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_searchresult, container, false);
        ArrayList<Product> albumlist = new ArrayList<Product>();
//        Product p = new Product("二手破mac","啟聖女用機",1.0);
//        p.setImg(BitmapFactory.decodeResource(getResources(), R.drawable.h01));
//
//        albumlist.add(p);
//        albumlist.add(new Product("班表小幫手","測試用",2.0));

        ProductListViewAdapter adapter =
                new ProductListViewAdapter(getContext(), albumlist,R.layout.listview1);

        listView = view.findViewById(R.id.searchlist);
        listView.setAdapter(adapter);

        return view;
    }


}
