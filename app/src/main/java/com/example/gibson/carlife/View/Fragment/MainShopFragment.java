package com.example.gibson.carlife.View.Fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gibson.carlife.Adapters.ClassficationGridViewAdapter;
import com.example.gibson.carlife.Adapters.productlistviewAdapter;
import com.example.gibson.carlife.Model.Product;
import com.example.gibson.carlife.R;

import java.util.ArrayList;

public class MainShopFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_shop, container, false);
        Integer[] ids = new Integer[6];
        String[] strings = getResources().getStringArray(R.array.type);
        for(int i = 0; i < 6 ; i ++) {
            ids[i] = (getResources().getIdentifier(strings[i], "drawable", getContext().getPackageName()));
        }

        GridView gv = (GridView) view.findViewById(R.id.gvType);
        gv.setNumColumns(3);
        gv.setAdapter(new ClassficationGridViewAdapter(getContext(), ids));

        ArrayList<Product> albumlist = new ArrayList<Product>();
        Product p = new Product("二手破mac","啟聖女用機",1.0);
        p.setImg(BitmapFactory.decodeResource(getResources(), R.drawable.h01));

        albumlist.add(p);
        albumlist.add(new Product("班表小幫手","測試用",2.0));

        productlistviewAdapter adapter =
                new productlistviewAdapter(getContext(), albumlist,R.layout.listview2);

        GridView lv =(GridView)view.findViewById(R.id.gyType);
        lv.setAdapter(adapter);
        return view;
    }

}
