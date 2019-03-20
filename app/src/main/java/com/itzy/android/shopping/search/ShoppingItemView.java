package com.itzy.android.shopping.search;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itzy.android.shopping.R;

public class ShoppingItemView extends LinearLayout {
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private ImageView imageView;

    public ShoppingItemView(Context context) {
        super(context);
        init(context);
    }

    public ShoppingItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.shopping_item, this, true);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView);
    }

    public void setName(String name) {
        textView.setText(Html.fromHtml(name));
    }

    public void setPrice(String price) {
        textView2.setText(price);
    }

    public void setOrigin(String origin) {
        textView3.setText(origin);
    }

    public void setImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
