package com.flamboyantes.views.cart;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flamboyantes.R;
import com.flamboyantes.adapter.CartAdapter;
import com.flamboyantes.adapter.FavoriteAdapter;
import com.flamboyantes.api.CartApi;
import com.flamboyantes.api.FavoriteApi;
import com.flamboyantes.model.CartModel;
import com.flamboyantes.model.ProfileModel;
import com.flamboyantes.model.cartfavoriteresponse.ShopingCartModel;
import com.flamboyantes.model.cartfavoriteresponse.ShoppingCart;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.util.SqliteDatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private RecyclerView cart_item;
    private TextInputEditText discount_tf, gift_tf;
    private AppCompatTextView sub_total_value, tax_value, total_value;

    SqliteDatabaseHelper db;
    private CartAdapter cartAdapter;
    private ArrayList<ProfileModel> profileModelId;
    private LinearLayoutManager linearLayoutManager;

    private CartApi cartApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_cart, container, false);

        cartApi = RetrofitService.createService(CartApi.class, APIClient.BASE_URL, true);

        initViews();
        initListeners();

        return view;
    }

    public void initViews(){
        cart_item = view.findViewById (R.id.cart_item);
        discount_tf = view.findViewById (R.id.discount_tf);
        gift_tf = view.findViewById (R.id.gift_tf);
        sub_total_value = view.findViewById (R.id.sub_total_value);
        tax_value = view.findViewById (R.id.tax_value);
        total_value = view.findViewById (R.id.total_value);

        db = new SqliteDatabaseHelper(getActivity());


    }

    public void initListeners(){
        profileModelId = new ArrayList<>(db.getProfileId());
        callCart();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }

    private void callCart() {

        if (isNetworkAvailable()) {
            dialogUtil.showProgressDialog();
            System.out.println(profileModelId.get(0).getId());
            Call<ShopingCartModel> callAdvisoryApi = cartApi.cartData(profileModelId.get(0).getId());

            callAdvisoryApi.enqueue(new Callback<ShopingCartModel>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<ShopingCartModel> call, Response<ShopingCartModel> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            onRecycler(response.body().getShoppingCarts());
                            sub_total_value.setText(response.body().getShoppingTotal().getSubTotal());
                            tax_value.setText(response.body().getShoppingTotal().getTax());
                            total_value.setText(response.body().getShoppingTotal().getOrderTotal());

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
        cartAdapter = new CartAdapter(shoppingCarts, getContext());
        linearLayoutManager = new LinearLayoutManager (getContext(), RecyclerView.VERTICAL, false);
        cart_item.setAdapter(cartAdapter);
        cart_item.setLayoutManager(linearLayoutManager);
        cart_item.setNestedScrollingEnabled(false);
    }
}
