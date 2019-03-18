package com.example.leesungwoo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSearchEvent((EditText) findViewById(R.id.editText));
    }

    private void setSearchEvent(final EditText editText) {
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                switch (i) {
                    case KeyEvent.KEYCODE_ENTER:
                        String text = editText.getText().toString();
                        try {
                            makeListView(new NetworkTask().execute(text).get());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                }
                return true;
            }
        });
    }

    private void makeListView(final JSONObject jsonObject) {
        try {
            final ListView listView = findViewById(R.id.listView);
            final ShoppingAdapter adapter = new ShoppingAdapter(getApplicationContext());
            final JSONArray items = (JSONArray) jsonObject.get("items");

            JSONObject item;
            for (int i = 0; i < items.length(); i++) {
                item = (JSONObject) items.get(i);
                adapter.addItem(new ShoppingItem(
                        (String) item.get(ShoppingItemInfo.IMAGE_URL),
                        (String) item.get(ShoppingItemInfo.TITLE),
                        (String) item.get(ShoppingItemInfo.LOW_PRICE),
                        (String) item.get(ShoppingItemInfo.ORIGIN_MALL_NAME)));
            }
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                    intent.putExtra("view", (ShoppingItem) adapter.getItem(position));
                    startActivity(intent);
                }
            });
        } catch (JSONException e) {
            throw new RuntimeException("error occurred in jsonObject : " + jsonObject.toString(), e);
        }
    }
}


