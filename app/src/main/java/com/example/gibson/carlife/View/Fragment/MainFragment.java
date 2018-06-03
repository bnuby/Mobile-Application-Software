package com.example.gibson.carlife.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.Fragment.MainShopFragment;
import com.example.gibson.carlife.View.Fragment.SearchResultFragment;

public class MainFragment extends Fragment {

    Button searchBtn;
    MainShopFragment mainShopFragment;
    SearchResultFragment searchResultFragment;
    boolean isChange = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mainShopFragment = new MainShopFragment();
        getFragmentManager().beginTransaction().add(R.id.linearLayout, mainShopFragment).addToBackStack(null).commit();

        searchBtn = v.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "asdas", Toast.LENGTH_LONG).show();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.add(R.id.mainshopLL, new SearchResultFragment());
//                transaction.commit();

                // Second run conditional
                if(isChange) {

                    // Hide Search Result Fragment and Show main Shop Fragment
//                    getFragmentManager().beginTransaction().hide(searchResultFragment);
//                    getFragmentManager().beginTransaction().show(mainShopFragment);
                    getFragmentManager().beginTransaction().replace(R.id.linearLayout, mainShopFragment).commit();

                } else {
                    // Hide main Shop Fragmen  and Show Search Result Fragment
//                    getFragmentManager().beginTransaction().hide(mainShopFragment);
                    if(searchResultFragment == null)
                        searchResultFragment = new SearchResultFragment();
//                        getFragmentManager().beginTransaction().add(R.id.linearLayout, searchResultFragment).addToBackStack(null).commit();
//                    } else {
//                        getFragmentManager().beginTransaction().show(searchResultFragment);
//                    }
                    getFragmentManager().beginTransaction().replace(R.id.linearLayout, searchResultFragment).commit();
                }
                isChange = !isChange;
            }
        });
        return v;
    }
}
