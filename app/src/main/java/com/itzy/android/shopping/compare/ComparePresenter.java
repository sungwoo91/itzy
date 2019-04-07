package com.itzy.android.shopping.compare;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import com.itzy.android.shopping.data.ShoppingItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ComparePresenter implements CompareContract.Presenter {

    private CompareContract.View mView;

    private ShoppingItem mItem;

    public ComparePresenter(@NonNull ShoppingItem item, @NonNull CompareContract.View view) {
        this.mItem = item;
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        showItem();
    }

    @Override
    public void showItem() {
        mView.showName(mItem.getName());
        mView.showImage(mItem.getImage());
        mView.showPrice(mItem.getPrice());
        mView.showOrigin(mItem.getOrigin());
    }

    @Override
    public void comparePrice(String price) {
        mView.showComparedPrice(Integer.toString(Integer.parseInt(mItem.getPrice()) - Integer.parseInt(price)));
    }

    @Override
    public Bitmap getImageBitmap() {
        try {
            return BitmapFactory.decodeStream((InputStream) new URL(mItem.getImage()).getContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
