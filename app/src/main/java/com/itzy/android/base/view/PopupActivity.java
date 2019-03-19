package com.itzy.android.base.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.itzy.android.base.R;
import com.itzy.android.base.model.ShoppingItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        Intent intent = getIntent();
        final ShoppingItem item = (ShoppingItem) intent.getSerializableExtra("view");

        ((TextView) findViewById(R.id.textView)).setText(Html.fromHtml(item.getName()));
        ((TextView) findViewById(R.id.textView2)).setText(item.getPrice());
        ((TextView) findViewById(R.id.textView3)).setText(item.getOrigin());

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
                ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);
            }
        }.execute();
//        Log.d("title", intent.getStringExtra("title"));

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final EditText priceEditText = findViewById(R.id.priceEditText);
        priceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int price = Integer.parseInt(priceEditText.getText().toString());
                ((TextView) findViewById(R.id.comparedPriceTextView)).setText(Integer.toString(Integer.parseInt(item.getPrice()) - price));
            }
        });
    }
}
