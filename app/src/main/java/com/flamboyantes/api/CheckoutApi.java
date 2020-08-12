package com.flamboyantes.api;

import com.flamboyantes.model.cartfavorite.CartFavoriteData;
import com.flamboyantes.model.cartfavorite.CartFavoriteModel;
import com.flamboyantes.model.checkout.Checkout;
import com.flamboyantes.model.checkout.CheckoutResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CheckoutApi {

    @POST("requestPayment")
    Call<CheckoutResponse> cartFavoriteData (@Body Checkout checkout);
}
