package com.example.gibson.carlife.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.gibson.carlife.Adapters.ClassficationGridViewAdapter;
import com.example.gibson.carlife.Adapters.ProductListViewAdapter;
import com.example.gibson.carlife.MainActivity;
import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.ProductDetailActivity;

public class MainShopFragment extends Fragment {

    public static GridView gridView;
    public static GridView gridView2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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


        ProductListViewAdapter adapter =
        new ProductListViewAdapter(getContext(), MainActivity.products,R.layout.listview2);

        gridView2 =(GridView)view.findViewById(R.id.gyType);
        gridView2.setAdapter(adapter);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getContext(),ProductDetailActivity.class);
                intent.putExtra("position",i);
                startActivity(intent);
            }
        });
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
