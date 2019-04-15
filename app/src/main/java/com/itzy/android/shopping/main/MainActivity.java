package com.itzy.android.shopping.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itzy.android.shopping.R;
import com.itzy.android.shopping.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    mainFragment, R.id.contentFrame);
        }

        new MainPresenter(mainFragment);
    }
}
