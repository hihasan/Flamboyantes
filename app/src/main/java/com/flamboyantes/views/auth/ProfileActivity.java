package com.flamboyantes.views.auth;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.TaskStackBuilder;

import com.flamboyantes.R;
import com.flamboyantes.util.BaseActivity;

public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_profile);

        initViews();
        initListeners();
    }

    public void goBack (View view){
        if ( view.getId() == R.id.navicon_iv){
            finish();
        }
    }

    public void initViews(){

    }

    public void initListeners(){

    }
}
