package com.itzy.android.shopping.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.itzy.android.shopping.R;
import com.itzy.android.shopping.compare.CompareActivity;
import com.itzy.android.shopping.data.ShoppingItem;
import com.itzy.android.shopping.data.ShoppingItemInfo;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchFragment extends Fragment implements SearchContract.View {

    private SearchContract.Presenter mSearchPresenter;

    private ListView mListView;

    private EditText mEditText;

    private ShoppingAdapter adapter;

    public static SearchFragment newInstance() {
        Bundle arguments = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.mSearchPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSearchPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.search_frag, container, false);
        setHasOptionsMenu(true);

        mEditText = root.findViewById(R.id.editText);

        // 기본 검색 기능
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int event, KeyEvent keyEvent) {
                switch (event) {
                    case KeyEvent.KEYCODE_ENTER:
                        mSearchPresenter.searchItems(((EditText) view).getText().toString());
                }
                return true;
            }
        });

        mListView = root.findViewById(R.id.listView);

        return root;
    }

    @Override
    public void showItems(final JSONObject jsonObject) {
        // TODO : 어댑터 하는 부분을 따로 분리 하긴 해야 하는데...
        try {
            JSONArray items = (JSONArray) jsonObject.get("items");
            adapter = new ShoppingAdapter(getActivity().getApplicationContext());

            JSONObject item;
            for (int i = 0; i < items.length(); i++) {
                item = (JSONObject) items.get(i);
                adapter.addItem(new ShoppingItem(
                        (String) item.get(ShoppingItemInfo.IMAGE_URL),
                        (String) item.get(ShoppingItemInfo.TITLE),
                        (String) item.get(ShoppingItemInfo.LOW_PRICE),
                        (String) item.get(ShoppingItemInfo.ORIGIN_MALL_NAME)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSearchPresenter.getItem(position);
            }
        });
    }

    @Override
    public void showCompareActivity(int position) {
        Intent intent = new Intent(getActivity().getApplicationContext(), CompareActivity.class);
        intent.putExtra("view", (ShoppingItem) adapter.getItem(position));
        startActivity(intent);
    }
}