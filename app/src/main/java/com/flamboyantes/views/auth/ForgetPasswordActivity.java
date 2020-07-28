package com.flamboyantes.views.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.flamboyantes.R;
import com.flamboyantes.api.AuthApi;
import com.flamboyantes.model.auth.ForgetPassword;
import com.flamboyantes.model.auth.ForgetPasswordData;
import com.flamboyantes.model.auth.LoginData;
import com.flamboyantes.model.auth.LoginTest;
import com.flamboyantes.model.auth.PasswordRecovery;
import com.flamboyantes.model.auth.UserLogin;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseActivity;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.views.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.net.PasswordAuthentication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends BaseActivity  implements View.OnClickListener {
    private TextInputEditText email_tf;
    private AppCompatButton reset_btn;

    private AuthApi authApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_forget_password);
        authApi = RetrofitService.createService(AuthApi.class, APIClient.BASE_URL, true);
        initViews();
        initListeners();
    }

    public void goBack (View view){
        if ( view.getId() == R.id.navicon_iv){
            finish();
        }
    }

    public void initViews(){
        email_tf = findViewById (R.id.email_tf);
        reset_btn = findViewById (R.id.reset_btn);
    }

    public void initListeners(){
        reset_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.reset_btn:
                callForgetPasswordApi();
                break;
        }
    }

    //method for login api
    private void callForgetPasswordApi() {


        if (isNetworkAvailable()) {
            dialogUtil.showProgressDialog();
            String email = email_tf.getText().toString().trim();

            PasswordRecovery passwordRecovery = new PasswordRecovery(email, "1");
            ForgetPassword forgetPassword = new ForgetPassword(passwordRecovery);

            Call<ForgetPasswordData> callLogin = authApi.forgetPassword(forgetPassword);
            callLogin.enqueue(new Callback<ForgetPasswordData>() {
                @Override
                public void onResponse(Call<ForgetPasswordData> call, Response<ForgetPasswordData> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {

                        if (response.code() == 200) {
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                        }

                    }

                    else {
                        Toast.makeText(getApplicationContext(), "No User Found", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<ForgetPasswordData> call, Throwable t) {
                    dialogUtil.dismissProgress();
                    Toast.makeText(ForgetPasswordActivity.this, getResources().getString(R.string.tryagain), Toast.LENGTH_LONG).show();
                }
            });


        } else {

            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }

    }
}
