package com.flamboyantes.views.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.flamboyantes.R;
import com.flamboyantes.api.AuthApi;
import com.flamboyantes.model.auth.LoginData;
import com.flamboyantes.model.auth.LoginTest;
import com.flamboyantes.model.auth.UserLogin;
import com.flamboyantes.model.customers.Customer;
import com.flamboyantes.model.customers.Customers;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseActivity;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.util.Singleton;
import com.flamboyantes.util.SqliteDatabaseHelper;
import com.flamboyantes.views.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.flamboyantes.util.Constants.EMAIL;
import static com.flamboyantes.util.Constants.MyPreferences;
import static com.flamboyantes.util.Constants.PASSWORD;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    RelativeLayout login_btn, register_btn;
    AppCompatTextView forget_password_tv;
    TextInputEditText email_tf, password_tf;

    private SharedPreferences sharedPreferences;

    private AuthApi authApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Singleton.getInstance().setContext(this);
        authApi = RetrofitService.createService(AuthApi.class, APIClient.BASE_URL, true);

        sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

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

            UserLogin userLogin = new UserLogin(userEmail, passwordValue);
            LoginTest log = new LoginTest(userLogin);
            Call<Customers> callLogin = authApi.userLogin(log);
            callLogin.enqueue(new Callback<Customers>() {
                @Override
                public void onResponse(Call<Customers> call, Response<Customers> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {

                        if (response.code() == 200) {

                            if (response.body().getStatus() == false){
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                SqliteDatabaseHelper db = new SqliteDatabaseHelper(getApplicationContext());
                                db.customer(String.valueOf(response.body().getData().getCustomer().getId()), response.body().getData().getCustomer().getFirstName(),
                                        response.body().getData().getCustomer().getLastName(), response.body().getData().getCustomer().getEmail());

                                SharedPreferences.Editor editor=sharedPreferences.edit();

                                editor.putString(EMAIL,userEmail);
                                editor.putString(PASSWORD,passwordValue);


                                editor.commit();


                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(i);
                            }

                        }

                    }

                    else {
                        Toast.makeText(getApplicationContext(), "Wrong User id & Password", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<Customers> call, Throwable t) {
                    dialogUtil.dismissProgress();
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.tryagain), Toast.LENGTH_LONG).show();
                }
            });


        }
        else {
            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }

    }
}
