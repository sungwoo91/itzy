package com.itzy.android.shopping.search;

import com.itzy.android.shopping.BasePresenter;
import com.itzy.android.shopping.BaseView;

import org.json.JSONObject;

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        // 검색 시
        void showItems(JSONObject jsonObject);

        // 아이템 클릭시
        void showCompareActivity(int position);
    }

    interface Presenter extends BasePresenter {

        // 처음 앱을 시작 했을 때 로직 처리
        void init();

        // 검색 시 로직 처리
        void searchItems(String text);

        // 아이템 클릭시 로직 처리
        void getItem(int position);
    }
}
