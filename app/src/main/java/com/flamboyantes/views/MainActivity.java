package com.flamboyantes.views;

import android.content.Intent;
import android.os.Bundle;

import com.flamboyantes.R;
import com.flamboyantes.util.BaseActivity;
import com.flamboyantes.util.BottomNavigationBehavior;
import com.flamboyantes.views.auth.ProfileActivity;
import com.flamboyantes.views.cart.CartFragment;
import com.flamboyantes.views.cart.CartMain;
import com.flamboyantes.views.favorite.FavoriteFragment;
import com.flamboyantes.views.favorite.FavoriteMain;
import com.flamboyantes.views.home.HomeFragment;
import com.flamboyantes.views.home.HomeMain;
import com.flamboyantes.views.search.SearchFragment;
import com.flamboyantes.views.search.SearchMain;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

//    NavigationView navigationView;
    BottomNavigationView navigation;
    CircleImageView header_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        initListeners();

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        loadFragment(new HomeMain());
    }

//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
    public void initViews(){
        header_profile = findViewById (R.id.header_profile);
    }

    public void initListeners(){
        header_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile =new Intent (MainActivity.this, ProfileActivity.class);
                startActivity(profile);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeMain();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_search:
                    fragment = new SearchMain();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_favorite:
                    fragment = new FavoriteMain();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_cart:
                    fragment = new CartMain();
                    loadFragment(fragment);
                    return true;

            }

            return false;
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() >1) {
            fm.popBackStack();
        } else if (fm.getBackStackEntryCount() == 1) {
            dialogUtil.showDialogYesNo(getResources().getString(R.string.closeapp_msg)+" "+getResources().getString(R.string.app_name), (dialog, id) -> {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        //finishAffinity();


                    }, (dialog, id) -> dialog.dismiss()

            );
        } else {
            super.onBackPressed();

        }
    }


}
