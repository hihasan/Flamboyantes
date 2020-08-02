package com.flamboyantes.views.auth;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.TaskStackBuilder;

import com.flamboyantes.R;
import com.flamboyantes.model.ProfileModel;
import com.flamboyantes.model.customers.Customer;
import com.flamboyantes.util.BaseActivity;
import com.flamboyantes.util.SqliteDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    AppCompatTextView logout, name, email;
    AppCompatImageView edit_profile_btn;
    private List<Customer> customer = new ArrayList<>();
    private SqliteDatabaseHelper db;
    private ArrayList<ProfileModel> profileModel;
    private int i =0;

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

        db = new SqliteDatabaseHelper(getApplicationContext());
        profileModel = new ArrayList<>(db.getProfile());

        logout = findViewById (R.id.logout);
        name = findViewById (R.id.name);
        email = findViewById (R.id.email);
        edit_profile_btn = findViewById (R.id.edit_profile_btn);
    }

    public void initListeners(){
        logout.setOnClickListener(this);
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

            case R.id.edit_profile_btn:
                 i = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(i);
                break;
        }
    }

//    private void getUserDetails() {
//
//        class SaleType extends AsyncTask<Void, Void, List<Customer>> {
//
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//            }
//
//            @Override
//            protected List<Customer> doInBackground(Void... voids) {
//                return db.getCustomerData();
//            }
//
//            @Override
//            protected void onPostExecute(List<Customer> tasks) {
//                super.onPostExecute(tasks);
//                customer.clear();
//                customer.add((Customer) tasks);
//                for (int i =0; i<customer.size(); i++){
//                   name.setText(customer.get(i).getFirstName()+" "+customer.get(i).getLastName());
//                   email.setText(customer.get(i).getEmail());
//                }
//
//
//            }
//        }
//
//    }
}
