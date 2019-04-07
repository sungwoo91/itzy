package com.itzy.android.shopping.compare;

import android.graphics.Bitmap;

import com.itzy.android.shopping.BasePresenter;
import com.itzy.android.shopping.BaseView;

public interface CompareContract {

    interface View extends BaseView<Presenter> {

        void showName(String name);

        void showImage(String url);

        void showPrice(String price);

        void showOrigin(String origin);

        void showComparedPrice(String price);

        void showCartActivity();
    }

    interface Presenter extends BasePresenter {

        void showItem();

        void comparePrice(String price);

        Bitmap getImageBitmap();
    }
}
