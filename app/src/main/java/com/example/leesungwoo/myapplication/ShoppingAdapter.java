package com.example.leesungwoo.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShoppingAdapter extends BaseAdapter {
    private List<ShoppingItem> items = new ArrayList<>();
    private Context context;

    public ShoppingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(ShoppingItem item) {
        items.add(item);
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ShoppingItemView shoppingView = new ShoppingItemView(this.context);
        final ShoppingItem item = items.get(position);
        shoppingView.setName(item.getName());
        shoppingView.setPrice(item.getPrice());
        shoppingView.setOrigin(item.getOrigin());

        // 이미지 URL을 통해 이미지를 읽어오고 이미지뷰에 저장
        // TODO : 따로 클래스 빼내기

        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    return BitmapFactory.decodeStream((InputStream) new URL(item.getImage()).getContent());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                shoppingView.setImage(bitmap);
            }
        }.execute();

        return shoppingView;
    }
}