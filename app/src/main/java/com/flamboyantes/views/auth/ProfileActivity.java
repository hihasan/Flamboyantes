package com.flamboyantes.views.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.TaskStackBuilder;

import com.flamboyantes.R;
import com.flamboyantes.util.BaseActivity;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    AppCompatTextView logout;
    AppCompatImageView edit_profile_btn;

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
        logout = findViewById (R.id.logout);
        edit_profile_btn = findViewById (R.id.edit_profile_btn);
    }

    public void initListeners(){
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.logout:
                i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                break;

            case R.id.edit_profile_btn:
                 i = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(i);
                break;
        }
    }
}
