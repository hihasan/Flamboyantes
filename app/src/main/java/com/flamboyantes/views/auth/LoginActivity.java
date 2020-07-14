package com.flamboyantes.views.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.flamboyantes.R;
import com.flamboyantes.util.BaseActivity;
import com.flamboyantes.views.MainActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    RelativeLayout login_btn, register_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
    }

    public void initViews(){
        login_btn= findViewById (R.id.login_btn);
        register_btn = findViewById (R.id.register_btn);
    }

    public void initListeners(){
        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){

            case R.id.login_btn:
                i = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;

            case R.id.register_btn:
                i = new Intent (getApplicationContext(), SignupActivity.class);
                startActivity(i);
        }
    }
}
