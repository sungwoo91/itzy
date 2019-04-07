package com.itzy.android.shopping.search;

import android.support.annotation.NonNull;
import android.util.Log;

import com.itzy.android.shopping.util.NetworkTask;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mSearchView;

    public SearchPresenter(@NonNull SearchContract.View searchView) {
        mSearchView = searchView;
        mSearchView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void searchItems(String text) {
        JSONObject jsonObject;
        try {
            jsonObject = new NetworkTask().execute(text).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        mSearchView.showItems(jsonObject);
    }

    @Override
    public void getItem(int position) {
        mSearchView.showCompareActivity(position);
    }
}
