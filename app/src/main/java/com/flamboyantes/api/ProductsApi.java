package com.flamboyantes.api;

import com.flamboyantes.model.products.AllMusicModel;
import com.flamboyantes.model.products.AllNewProductArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ProductsApi {
    @GET("products")
    Call<AllNewProductArray> getProducts(
            @Header("Authorization") String contentRange
    );

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

    @GET("productdownloadsample/{id}")
    Call<AllMusicModel> getAllMusicModel(
            @Header("Authorization") String contentRange,
            @Path("id") int id
    );
}
