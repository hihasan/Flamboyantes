package com.flamboyantes.views.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.flamboyantes.R;
import com.flamboyantes.api.AuthApi;
import com.flamboyantes.model.auth.Login;
import com.flamboyantes.model.auth.LoginModel;
import com.flamboyantes.model.auth.LoginTest;
import com.flamboyantes.model.auth.UserLogin;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseActivity;
import com.flamboyantes.util.Constants;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.util.Singleton;
import com.flamboyantes.views.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    RelativeLayout login_btn, register_btn;
    AppCompatTextView forget_password_tv;
    TextInputEditText email_tf, password_tf;

    private AuthApi authApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Singleton.getInstance().setContext(this);
        authApi = RetrofitService.createService(AuthApi.class, APIClient.BASE_URL, true);

        initViews();
        initListeners();
    }

    public void initViews(){
        email_tf = findViewById (R.id.email_tf);
        password_tf = findViewById (R.id.password_tf);
        login_btn= findViewById (R.id.login_btn);
        register_btn = findViewById (R.id.register_btn);
        forget_password_tv = findViewById (R.id.forget_password_tv);
    }

    public void initListeners(){
        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
        forget_password_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){

            case R.id.login_btn:
                callLoginApi();
                break;

            case R.id.register_btn:
                i = new Intent (getApplicationContext(), SignupActivity.class);
                startActivity(i);
                break;

            case R.id.forget_password_tv:
                i = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(i);
                break;
        }
    }

    //method for login api
    private void callLoginApi() {


        if (isNetworkAvailable()) {


            dialogUtil.showProgressDialog();
            final String userEmail = email_tf.getText().toString();
            String passwordValue = password_tf.getText().toString();

//            UserLogin userLogin = new UserLogin(userEmail, passwordValue);

            UserLogin userLogin = new UserLogin("admin@yourstore.com", "adminadmin");
            LoginTest log = new LoginTest(userLogin);
            Call<LoginTest> callLogin = authApi.userLogin(log);
            callLogin.enqueue(new Callback<LoginTest>() {
                @Override
                public void onResponse(Call<LoginTest> call, Response<LoginTest> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {


                        if (response.code() == 200) {

                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                           // Toast.makeText(getApplicationContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }

                    }

                    else {
                        Toast.makeText(getApplicationContext(), "Wrong User Id or Password", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<LoginTest> call, Throwable t) {
                    dialogUtil.dismissProgress();
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.tryagain), Toast.LENGTH_LONG).show();
                }
            });


        } else {

            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }

    }
}
