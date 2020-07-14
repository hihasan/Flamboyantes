package com.flamboyantes.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.flamboyantes.views.MainActivity;


public class BaseActivity extends AppCompatActivity {
    //base of all activity class
    public DialogUtil dialogUtil;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize dialog utils class for getting dialog util object
        dialogUtil = new DialogUtil(this);

    }


    //method for network available or not checking
    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();

    }


    //method for fragment calling

    protected void initFragment(Fragment fragment, String id, int resId) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(resId, fragment, id)
                .addToBackStack(null)
                .commit();
    }

    //method for fragment replace
    protected void replaceFragment(Fragment fragment, String id, int resId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(resId, fragment, id)
                .addToBackStack(null)
                .commit();
    }


    //method for show error message form Api
    //No plan to implement right now
//    public <T> void showErrorMessage(int responseCode, Response<T> response) {
//        try {
//            JSONObject jsonObject;
//            try {
//                jsonObject = new JSONObject(response.errorBody().string());
//                String errorMessage = jsonObject.getString("error");
//                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


    //method for going to homepage
    public void gotoHomePage() {

        Intent intent;
        intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);

    }
}
