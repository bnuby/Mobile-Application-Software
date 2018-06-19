package com.example.gibson.carlife.View.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.gibson.carlife.R;
import com.example.gibson.carlife.View.SearchResultActivity;

public class MainFragment extends Fragment implements View.OnClickListener {

  String TAG = "MainFragment";
  MainShopFragment mainShopFragment;
  TextView homeTV;
  SearchView searchView;
  boolean isChange = false;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);

    homeTV = view.findViewById(R.id.homeTV);
    mainShopFragment = new MainShopFragment();
    getFragmentManager().beginTransaction().add(R.id.linearLayout, mainShopFragment).commit();
    SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
    searchView = view.findViewById(R.id.searchView);
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
    searchView.setOnSearchClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        homeTV.setVisibility(View.GONE);
        searchView.setBackground(getResources().getDrawable(R.drawable.search_bar, null));
      }
    });
    searchView.setOnCloseListener(new SearchView.OnCloseListener() {
      @Override
      public boolean onClose() {
        homeTV.setVisibility(View.VISIBLE);
        searchView.setBackground(getResources().getDrawable(android.R.color.transparent, null));
        return false;
      }
    });

    return view;
  }



  @Override
  public void onClick(View v) {

  }
}
