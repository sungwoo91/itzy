package com.itzy.android.shopping.search;

import com.itzy.android.shopping.BasePresenter;
import com.itzy.android.shopping.BaseView;

import org.json.JSONObject;

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        void searchItem();

        // 검색시
        void showItems(JSONObject jsonObject);

        // 아이템 클릭시
        void showItemPopup();

        void startCompareActivity(int position);
    }

    interface Presenter extends BasePresenter {

        void searchItem(String text);

        void getItem(int position);
    }
}
