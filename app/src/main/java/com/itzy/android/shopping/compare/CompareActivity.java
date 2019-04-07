package com.itzy.android.shopping.compare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itzy.android.shopping.R;
import com.itzy.android.shopping.data.ShoppingItem;
import com.itzy.android.shopping.util.ActivityUtils;

public class CompareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_act);

        final ShoppingItem item = (ShoppingItem) getIntent().getSerializableExtra("view");

        CompareFragment compareFragment = (CompareFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(compareFragment == null) {
            compareFragment = CompareFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    compareFragment, R.id.contentFrame);
        }

        new ComparePresenter(item, compareFragment);
    }
}
