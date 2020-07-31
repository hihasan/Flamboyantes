package com.flamboyantes.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.flamboyantes.util.Constants.EMAIL;
import static com.flamboyantes.util.Constants.MyPreferences;

public class SplashActivity extends BaseActivity {

    AppCompatImageView app_logo;
    private SharedPreferences sharedPreferences;

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
        sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
    }

    public void initListeners(){

//        String email =sharedPreferences.getString(EMAIL, "");

        if (sharedPreferences.getString(EMAIL, "").isEmpty()){
            loginMethod();
        }else {
            mainMethod();
        }



    }

    public void loginMethod(){
        //Thread Handler for 5 sec.
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, Constants.APP_LOAD_TIME);
    }

    public void mainMethod(){
        //Thread Handler for 5 sec.
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, Constants.APP_LOAD_TIME);
    }
}
