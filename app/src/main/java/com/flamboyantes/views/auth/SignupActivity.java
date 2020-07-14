package com.flamboyantes.views.auth;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.flamboyantes.R;
import com.flamboyantes.adapter.SignUpAdapter;
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
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    public void initListeners(){
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.user)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.artist)));

        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));

//        final MarketPlaceHistoryAdapter adapter = new MarketPlaceHistoryAdapter(this,getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        final SignUpAdapter adapter = new SignUpAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
