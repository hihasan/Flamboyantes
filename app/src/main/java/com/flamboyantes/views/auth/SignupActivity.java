package com.flamboyantes.views.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import com.flamboyantes.R;
import com.flamboyantes.api.AuthApi;
import com.flamboyantes.model.auth.LoginTest;
import com.flamboyantes.model.auth.UserLogin;
import com.flamboyantes.model.customers.Customers;
import com.flamboyantes.model.signup.CreateCustomer;
import com.flamboyantes.model.signup.SignupCustomer;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseActivity;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.util.Singleton;
import com.flamboyantes.util.SqliteDatabaseHelper;
import com.flamboyantes.views.MainActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.flamboyantes.util.Constants.EMAIL;
import static com.flamboyantes.util.Constants.PASSWORD;

public class SignupActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText fname_tf, lname_tf, email_tf, confirmemail_tf, password_tf, confirmpassword_tf;
    private AppCompatButton register_btn;
    private AuthApi authApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_signup);
        Singleton.getInstance().setContext(this);
        authApi = RetrofitService.createService(AuthApi.class, APIClient.BASE_URL, true);

        initViews();
        initListeners();
    }

    public void goBack(View view){
        if (view.getId() == R.id.navicon_iv){
            finish();
        }
    }

    public void initViews(){
        register_btn = findViewById (R.id.register_btn);
        fname_tf = findViewById (R.id.fname_tf);
        lname_tf = findViewById (R.id.lname_tf);
        email_tf = findViewById (R.id.email_tf);
        confirmemail_tf = findViewById (R.id.confirmemail_tf);
        password_tf = findViewById (R.id.password_tf);
        confirmpassword_tf = findViewById (R.id.confirmpassword_tf);

    }

    public void initListeners() {
        register_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.register_btn:
                callSignupApi();
                break;
        }

    }

    //method for login api
    private void callSignupApi() {


        if (isNetworkAvailable()) {


            dialogUtil.showProgressDialog();
            String fname = fname_tf.getText().toString();
            String lname = lname_tf.getText().toString();
            String email = email_tf.getText().toString();
            String confirm_email = confirmemail_tf.getText().toString();
            String password = password_tf.getText().toString();
            String confirm_password = confirmpassword_tf.getText().toString();

            if (fname.isEmpty()){
                Toast.makeText(getApplicationContext(), "First Name Needed", Toast.LENGTH_SHORT).show();
            }

            if (lname.isEmpty()){
                Toast.makeText(getApplicationContext(), "Last Name Needed", Toast.LENGTH_SHORT).show();
            }

            if (email.isEmpty()){
                Toast.makeText(getApplicationContext(), "Email Needed", Toast.LENGTH_SHORT).show();
            }

            if (password.isEmpty()){
                Toast.makeText(getApplicationContext(), "Password Needed", Toast.LENGTH_SHORT).show();
            }

//            if (email.equals(confirm_email) == false){
//                Toast.makeText(getApplicationContext(), "Email is not matched", Toast.LENGTH_SHORT).show();
//            }
//
//            if (password.equals(confirm_password) == false){
//                Toast.makeText(getApplicationContext(), "Password is not matched", Toast.LENGTH_SHORT).show();
//            }

            ArrayList<Integer> customerId = new ArrayList<>() ;
            customerId.add(3);

            SignupCustomer signupCustomer = new SignupCustomer(fname, lname, email, password, customerId);
            CreateCustomer createCustomers = new CreateCustomer(signupCustomer);
            Call<CreateCustomer> callLogin = authApi.customerSignup(createCustomers);
            callLogin.enqueue(new Callback<CreateCustomer>() {
                @Override
                public void onResponse(Call<CreateCustomer> call, Response<CreateCustomer> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {

                        if (response.code() == 200) {
                            Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(i);

                        }

                    }

                    else {
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<CreateCustomer> call, Throwable t) {
                    dialogUtil.dismissProgress();
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_LONG).show();
                }
            });


        } else {

            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }

    }
}
