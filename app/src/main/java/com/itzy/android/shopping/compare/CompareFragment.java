package com.itzy.android.shopping.compare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.itzy.android.shopping.R;
import com.itzy.android.shopping.cart.CartActivity;

public class CompareFragment extends Fragment implements CompareContract.View {

    private CompareContract.Presenter mPresenter;

    private TextView textView;

    private TextView textView2;

    private TextView textView3;

    private ImageView imageView;

    private Button addButton;

    private Button returnButton;

    private EditText priceEditText;

    private TextView comparedPriceTextView;

    public static CompareFragment newInstance() {
        Bundle arguments = new Bundle();
        CompareFragment fragment = new CompareFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(CompareContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.compare_frag, container, false);

        textView = root.findViewById(R.id.textView);
        textView2 = root.findViewById(R.id.textView2);
        textView3 = root.findViewById(R.id.textView3);
        imageView = root.findViewById(R.id.imageView);

        addButton = root.findViewById(R.id.addButton);
        addButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCartActivity();
            }
        });

        returnButton = root.findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        priceEditText = root.findViewById(R.id.priceEditText);
        priceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.comparePrice(priceEditText.getText().toString());
            }
        });

        comparedPriceTextView = root.findViewById(R.id.comparedPriceTextView);

        return root;
    }

    @Override
    public void showName(String name) {
        textView.setText(Html.fromHtml(name));
    }

    @Override
    public void showImage(String url) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                return mPresenter.getImageBitmap();
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                imageView.setImageBitmap(bitmap);
            }
        }.execute();
    }

    @Override
    public void showPrice(String price) {
        textView2.setText(price);
    }

    @Override
    public void showOrigin(String origin) {
        textView3.setText(origin);
    }

    @Override
    public void showComparedPrice(String price) {
        comparedPriceTextView.setText(price);
    }

    @Override
    public void showCartActivity() {
        Intent intent = new Intent(getActivity().getApplicationContext(), CartActivity.class);
//        intent.putExtra("view", (ShoppingItem) adapter.getItem(position));
        startActivity(intent);
    }
}
