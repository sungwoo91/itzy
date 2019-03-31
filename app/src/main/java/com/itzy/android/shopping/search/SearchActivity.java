package com.itzy.android.shopping.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itzy.android.shopping.R;
import com.itzy.android.shopping.util.ActivityUtils;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_act);

        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(searchFragment == null) {
            searchFragment = SearchFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    searchFragment, R.id.contentFrame);
        }
        new SearchPresenter(searchFragment);
    }
}