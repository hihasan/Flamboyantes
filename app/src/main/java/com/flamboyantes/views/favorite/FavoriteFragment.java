package com.flamboyantes.views.favorite;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.flamboyantes.R;
import com.flamboyantes.adapter.FavoriteAdapter;
import com.flamboyantes.api.FavoriteApi;
import com.flamboyantes.api.ProductsApi;
import com.flamboyantes.model.FavoriteModel;
import com.flamboyantes.model.ProfileModel;
import com.flamboyantes.model.cartfavoriteresponse.ShopingCartModel;
import com.flamboyantes.model.cartfavoriteresponse.ShoppingCart;
import com.flamboyantes.model.products.AllMusicModel;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.Constants;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.util.Singleton;
import com.flamboyantes.util.SqliteDatabaseHelper;
import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends BaseFragment {
    private View view;
    private ShimmerFrameLayout mShimmerViewContainer;
    private RecyclerView favorite_recycler;
    private FavoriteAdapter favoriteAdapter;
    private ArrayList<FavoriteModel> favoriteModel;
    private LinearLayoutManager linearLayoutManager;
    private RelativeLayout layer1;

    private SqliteDatabaseHelper db;

    private FavoriteApi favoriteApi;
    private ArrayList<ProfileModel> profileModelId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_favorite, container, false);

        favoriteApi = RetrofitService.createService(FavoriteApi.class, APIClient.BASE_URL, true);

        initViews();
        initListeners();

        return view;
    }


    public void initViews() {
        favorite_recycler = view.findViewById(R.id.favorite_recycler);
        mShimmerViewContainer = view.findViewById (R.id.shimmer_view_container);
        layer1 = view.findViewById(R.id.layer1);
        db = new SqliteDatabaseHelper(getActivity());
    }

    public void initListeners() {

        profileModelId = new ArrayList<>(db.getProfileId());

        callFavorite();
//        if (favoriteModel.size() == 0){
//            layer1.setVisibility(View.VISIBLE);
//        }else {
//            layer1.setVisibility(View.INVISIBLE);
//            favoriteAdapter = new FavoriteAdapter(favoriteModel, getContext());
//            linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//            favorite_recycler.setLayoutManager(linearLayoutManager);
//            favorite_recycler.setAdapter(favoriteAdapter);
//            favorite_recycler.setNestedScrollingEnabled(false);
//        }

    }

    private void callFavorite() {

        if (isNetworkAvailable()) {
//            dialogUtil.showProgressDialog();
            System.out.println(profileModelId.get(0).getId());
            Call<ShopingCartModel> callAdvisoryApi = favoriteApi.cartFavoriteData(profileModelId.get(0).getId());

            callAdvisoryApi.enqueue(new Callback<ShopingCartModel>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<ShopingCartModel> call, Response<ShopingCartModel> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            for (int i = 0; i < Objects.requireNonNull(response.body()).getShoppingCarts().size(); i++)
                                if (response.body().getShoppingCarts().get(i).getShoppingCartType().trim().equals("Wishlist")) {
                                    System.out.println(response.body().getShoppingCarts().get(i).getShoppingCartType());
                                    onRecycler(response.body().getShoppingCarts());
                                } else {

                                }
                        } else {
                            Toast.makeText(getActivity(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }


                    }
                }


                @Override
                public void onFailure(Call<ShopingCartModel> call, Throwable t) {
                    dialogUtil.dismissProgress();
                    Toast.makeText(getActivity(), "failed To Load Data", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void onRecycler(List<ShoppingCart> shoppingCarts) {



        if (shoppingCarts.size() == 0) {
            layer1.setVisibility(View.VISIBLE);
        } else {
            layer1.setVisibility(View.INVISIBLE);

            favoriteAdapter = new FavoriteAdapter(shoppingCarts, getContext());
            linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            favorite_recycler.setLayoutManager(linearLayoutManager);
            favorite_recycler.setAdapter(favoriteAdapter);
            favorite_recycler.setNestedScrollingEnabled(false);




        }

        // Stopping Shimmer Effect's animation after data is loaded to ListView
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }
}

