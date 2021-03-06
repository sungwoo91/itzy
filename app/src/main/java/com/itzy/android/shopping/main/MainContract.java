package com.itzy.android.shopping.main;

import com.itzy.android.shopping.BasePresenter;
import com.itzy.android.shopping.BaseView;

public interface MainContract {
    interface View extends BaseView<MainContract.Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
