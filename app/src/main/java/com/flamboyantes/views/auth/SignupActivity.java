package com.flamboyantes.views.auth;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.flamboyantes.R;
import com.flamboyantes.util.BaseActivity;
import com.google.android.material.tabs.TabLayout;

public class SignupActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_signup);

        initViews();
        initListeners();
    }

    public void goBack(View view){
        if (view.getId() == R.id.navicon_iv){
            finish();
        }
    }

    public void initViews(){

    }

    public void initListeners() {
    }
}