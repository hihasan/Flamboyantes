package com.flamboyantes.views.auth;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.TaskStackBuilder;

import com.flamboyantes.R;
import com.flamboyantes.api.AuthApi;
import com.flamboyantes.model.ProfileModel;
import com.flamboyantes.model.auth.Changepassword;
import com.flamboyantes.model.auth.Changepasswordmodel;
import com.flamboyantes.model.auth.LoginTest;
import com.flamboyantes.model.auth.PasswordModel;
import com.flamboyantes.model.auth.UserLogin;
import com.flamboyantes.model.customers.Customer;
import com.flamboyantes.model.customers.Customers;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseActivity;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.util.SqliteDatabaseHelper;
import com.flamboyantes.views.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.flamboyantes.util.Constants.EMAIL;
import static com.flamboyantes.util.Constants.PASSWORD;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    AppCompatTextView logout, name, email;
    RelativeLayout change_password_layout;
    private List<Customer> customer = new ArrayList<>();
    private SqliteDatabaseHelper db;
    private ArrayList<ProfileModel> profileModel;
    private ArrayList<ProfileModel> profileModelId;
    private int i =0;
    private Context context = this;

    private AuthApi authApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_profile);

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

        db = new SqliteDatabaseHelper(getApplicationContext());
        profileModel = new ArrayList<>(db.getProfile());
        profileModelId = new ArrayList<>(db.getProfileId());

        logout = findViewById (R.id.logout);
        name = findViewById (R.id.name);
        email = findViewById (R.id.email);

        change_password_layout = findViewById (R.id.change_password_layout);
    }

    public void initListeners(){
        logout.setOnClickListener(this);
        change_password_layout.setOnClickListener(this);
        int i =0;
        String fname = profileModel.get(i).getFname();
        String lname = profileModel.get(i).getLname();

        name.setText(fname+" "+lname);
        email.setText(profileModel.get(i).getEmail());


    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.logout:
                i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                break;

            case R.id.change_password_layout:
                dialogMethod();
                break;


        }


    }

    private void dialogMethod(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.change_password_layout);
        dialog.setTitle("Title...");

        //String id =

        TextInputEditText password_tf = dialog.findViewById (R.id.password_tf);
        TextInputEditText new_password_tf = dialog.findViewById (R.id.new_password_tf);
        TextInputEditText confirm_password_tf = dialog.findViewById (R.id.confirm_password_tf);
        AppCompatButton change_password_btn = dialog.findViewById(R.id.change_password_btn);

        change_password_btn.setOnClickListener(view -> {
            if (isNetworkAvailable()) {
                dialogUtil.showProgressDialog();

                Changepassword changepassword = new Changepassword(profileModelId.get(0).getId(), password_tf.getText().toString(), new_password_tf.getText().toString(), new_password_tf.getText().toString());
                PasswordModel passwordModel = new PasswordModel(changepassword);

                Call<Changepasswordmodel> callLogin = authApi.changePassword(passwordModel);
                callLogin.enqueue(new Callback<Changepasswordmodel>() {
                    @Override
                    public void onResponse(Call<Changepasswordmodel> call, Response<Changepasswordmodel> response) {
                        dialogUtil.dismissProgress();
                        if (response.isSuccessful()) {

                            if (response.code() == 200) {
                                if (response.body().getStatus().equals("true")){
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
//                                    finish();
                                }
                                else {
                                   Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }

                        }

                        else {
                            Toast.makeText(getApplicationContext(), "Wrong User id & Password", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<Changepasswordmodel> call, Throwable t) {
                        dialogUtil.dismissProgress();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else {
                Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }
}
