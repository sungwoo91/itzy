package com.itzy.android.shopping.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.itzy.android.shopping.R;
import com.itzy.android.shopping.cart.CartActivity;
import com.itzy.android.shopping.compare.CompareActivity;
import com.itzy.android.shopping.data.ShoppingItem;
import com.itzy.android.shopping.search.SearchActivity;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;

    private Button mCategoryButton;

    private Button mCartButton;

    private SearchView mSearchView;

    public static MainFragment newInstance() {
        Bundle arguments = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_frag, container, false);
        setHasOptionsMenu(true);

        mCategoryButton = root.findViewById(R.id.categoryButton);
        mCategoryButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CompareActivity.class);
                startActivity(intent);
            }
        });

        mCartButton = root.findViewById(R.id.cartButton);
        mCartButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        mSearchView = root.findViewById(R.id.searchView);
        mSearchView.setOnSearchClickListener(new SearchView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
