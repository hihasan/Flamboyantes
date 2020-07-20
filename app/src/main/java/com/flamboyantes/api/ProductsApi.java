package com.flamboyantes.api;

import com.flamboyantes.model.products.AllNewProductArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ProductsApi {
    @GET("newproducts")
    Call<AllNewProductArray> getAllNewProductArray(
            @Header("Authorization") String contentRange
    );

    @GET("newproducts?limit=4")
    Call<AllNewProductArray> getFourAllNewProductArray(
            @Header("Authorization") String contentRange
    );

    @GET("topsellingproduct")
    Call<AllNewProductArray> gettopsellingproduct(
            @Header("Authorization") String contentRange
    );

    @GET("topsellingproductthisweek")
    Call<AllNewProductArray> gettopsellingproductthisweek(
            @Header("Authorization") String contentRange
    );

    @GET("upcomingalbums?limit=4")
    Call<AllNewProductArray> getupcomingalbums4(
            @Header("Authorization") String contentRange
    );

    @GET("upcomingalbums")
    Call<AllNewProductArray> getAllupcomingalbums(
            @Header("Authorization") String contentRange
    );

    @GET("concerttickets")
    Call<AllNewProductArray> getconcerttickets(
            @Header("Authorization") String contentRange
    );
}
