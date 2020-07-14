package com.flamboyantes.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.flamboyantes.R;
import com.flamboyantes.util.BaseActivity;
import com.flamboyantes.util.Constants;
import com.flamboyantes.views.auth.LoginActivity;

public class SplashActivity extends BaseActivity {

    AppCompatImageView app_logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView (R.layout.activity_splash);

        initViews();
        initListeners();
    }

    public void initViews(){

    }

    public void initListeners(){
        //Thread Handler for 5 sec.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, Constants.APP_LOAD_TIME);
    }
}
