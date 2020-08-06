package com.flamboyantes.views.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.flamboyantes.R;
import com.flamboyantes.adapter.MusicAdapter;
import com.flamboyantes.api.ProductsApi;
import com.flamboyantes.model.ProfileModel;
import com.flamboyantes.model.auth.LoginTest;
import com.flamboyantes.model.auth.UserLogin;
import com.flamboyantes.model.cartfavorite.CartFavoriteData;
import com.flamboyantes.model.cartfavorite.CartFavoriteModel;
import com.flamboyantes.model.cartfavorite.ShoppingCartItem;
import com.flamboyantes.model.customers.Customers;
import com.flamboyantes.model.products.AllMusicModel;
import com.flamboyantes.model.products.AllNewProductArray;
import com.flamboyantes.model.products.Downloaditem;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.Constants;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.util.Singleton;
import com.flamboyantes.util.SqliteDatabaseHelper;
import com.flamboyantes.views.MainActivity;
import com.flamboyantes.views.auth.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.flamboyantes.util.Constants.EMAIL;
import static com.flamboyantes.util.Constants.PASSWORD;

public class ProductPageDetails extends BaseFragment implements View.OnClickListener {
    private View view;
    private AppCompatTextView album_name_tv, update_tv, price_tv;
    private AppCompatImageView album_iv, back, favorite_iv;
    private AppCompatButton add_to_cart;
    private RecyclerView music_recycler;
    private Context context;

    private ProductsApi products;

    private SqliteDatabaseHelper db;
    private ArrayList<ProfileModel> profileModel;
    private int i =0;

    private MusicAdapter musicAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_product_page_details, container, false);
        Singleton.getInstance().setContext(getActivity());

        products = RetrofitService.createService(ProductsApi.class, APIClient.BASE_URL, true);

        initViews();
        initListeners();

        return  view;
    }

    @Override
    public void onResume() {
        Log.e("DEBUG", "onResume of HomeFragment");
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        Log.e("DEBUG", "OnPause of HomeFragment");
        super.onPause();
    }

    public void initViews(){
        db = new SqliteDatabaseHelper(getContext());
        profileModel = new ArrayList<>(db.getProfileId());

        album_name_tv = view.findViewById (R.id.album_name_tv);
        update_tv = view.findViewById (R.id.update_tv);
        price_tv = view.findViewById (R.id.price_tv);

        album_iv = view.findViewById (R.id.album_iv);
        back = view.findViewById (R.id.back);
        favorite_iv = view.findViewById (R.id.favorite_iv);

        add_to_cart = view.findViewById (R.id.add_to_cart);

        music_recycler = view.findViewById (R.id.music_recycler);

        mShimmerViewContainer = view.findViewById (R.id.shimmer_view_container);
    }

    public void initListeners(){
        album_name_tv.setText(Singleton.getInstance().getName());

        String str = Singleton.getInstance().getUpdate_on_utc();
        String[] splitStr = str.split("T", 2);
        update_tv.setText(getResources().getString(R.string.update_on)+": "+splitStr[0]);

        price_tv.setText("$"+" "+Singleton.getInstance().getPrice());

        Glide.with(getActivity())
                .load(Singleton.getInstance().getImage())
                .into(album_iv);

        back.setOnClickListener(this);
        add_to_cart.setOnClickListener(this);
        favorite_iv.setOnClickListener(this);

        callNewProduct();
    }

    @Override
    public void onClick(View view) {
        SqliteDatabaseHelper db = new SqliteDatabaseHelper(getActivity());
        switch (view.getId()){
            case R.id.back:
                replaceFragment(new HomeFragment(), Constants.HomeScreen, Constants.HomeFragment, R.id.fragment_container);
                break;

            case R.id.add_to_cart:
//                Toast.makeText(getActivity(), "Add To Cart", Toast.LENGTH_SHORT).show();
//                db.cart_item(String.valueOf(Singleton.getInstance().getId()), Singleton.getInstance().getName(), Singleton.getInstance().getImage(),
//                        String.valueOf(Singleton.getInstance().getPrice()));
                callCartPostAPi();
                break;

            case R.id.favorite_iv:
                callFavoritePostAPi();
                break;
        }
    }

    private void callNewProduct() {

        if (isNetworkAvailable()) {
//            dialogUtil.showProgressDialog();
            Call<AllMusicModel> callAdvisoryApi = products.getAllMusicModel("Bearer "+ Constants.AUTHKEY, Singleton.getInstance().getId());

            callAdvisoryApi.enqueue(new Callback<AllMusicModel>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<AllMusicModel> call, Response<AllMusicModel> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            songList(response.body().getProductdownloadsamples().getDownloaditems());
                        } else {
                            Toast.makeText(getActivity(), "Failed to Load Data", Toast.LENGTH_SHORT).show();
                        }
                    } else {
//                        showErrorMessage(response.code(), response);
                        Log.e("Error", String.valueOf(response.code()));

                    }
                }


                @Override
                public void onFailure(Call<AllMusicModel> call, Throwable t) {
                    dialogUtil.dismissProgress();
                    Toast.makeText(getActivity(), "failed To Load Data", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void songList(ArrayList<Downloaditem> downloaditems) {
        if (downloaditems == null){

        }else {
            musicAdapter = new MusicAdapter(downloaditems, getContext());
            linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            music_recycler.setAdapter(musicAdapter);
            music_recycler.setLayoutManager(linearLayoutManager);
            music_recycler.setNestedScrollingEnabled(false);
        }


        // Stopping Shimmer Effect's animation after data is loaded to ListView
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    public void callFavoritePostAPi(){
        if (isNetworkAvailable()) {


            dialogUtil.showProgressDialog();

            ShoppingCartItem shoppingCartItem = new ShoppingCartItem("1", String.valueOf(Singleton.getInstance().getId()), profileModel.get(i).getId(), 2);
            CartFavoriteModel cartFavoriteModel = new CartFavoriteModel(shoppingCartItem);


            Call<CartFavoriteData> callLogin = products.cartFavoriteData(cartFavoriteModel);
            callLogin.enqueue(new Callback<CartFavoriteData>() {
                @Override
                public void onResponse(Call<CartFavoriteData> call, Response<CartFavoriteData> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {

                        if (response.code() == 200) {
                            Toast.makeText(getContext(), "Added To Favorite", Toast.LENGTH_SHORT).show();
                        }

                    }

                    else {
                        Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<CartFavoriteData> call, Throwable t) {
                    dialogUtil.dismissProgress();
                    Toast.makeText(getContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_LONG).show();
                }
            });


        }
        else {
            Toast.makeText(getContext(), getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }
    }

    public void callCartPostAPi(){
        if (isNetworkAvailable()) {


            dialogUtil.showProgressDialog();

            ShoppingCartItem shoppingCartItem = new ShoppingCartItem("1", String.valueOf(Singleton.getInstance().getId()), profileModel.get(i).getId(), 1);
            CartFavoriteModel cartFavoriteModel = new CartFavoriteModel(shoppingCartItem);


            Call<CartFavoriteData> callLogin = products.cartFavoriteData(cartFavoriteModel);
            callLogin.enqueue(new Callback<CartFavoriteData>() {
                @Override
                public void onResponse(Call<CartFavoriteData> call, Response<CartFavoriteData> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {

                        if (response.code() == 200) {
                            Toast.makeText(getContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
                        }

                    }

                    else {
                        Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<CartFavoriteData> call, Throwable t) {
                    dialogUtil.dismissProgress();
                    Toast.makeText(getContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_LONG).show();
                }
            });


        }
        else {
            Toast.makeText(getContext(), getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }
    }
}
