package com.flamboyantes.api;

import com.flamboyantes.model.cartfavorite.CartFavoriteData;
import com.flamboyantes.model.cartfavorite.CartFavoriteModel;
import com.flamboyantes.model.cartfavoriteresponse.ShopingCartModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FavoriteApi {

    @Headers({
            "authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1OTQ5NzM5OTksImV4cCI6MTkxMDMzMzk5OSwiaXNzIjoiaHR0cHM6Ly9mbGFtYm95YW50ZXMubmV0IiwiYXVkIjpbImh0dHBzOi8vZmxhbWJveWFudGVzLm5ldC9yZXNvdXJjZXMiLCJub3BfYXBpIl0sImNsaWVudF9pZCI6IjZiMjNiZjY3LTU5ZDMtNDJjNC1iYWY0LTAxYzdjNDMzZTdmZiIsInN1YiI6IjZiMjNiZjY3LTU5ZDMtNDJjNC1iYWY0LTAxYzdjNDMzZTdmZiIsImF1dGhfdGltZSI6MTU5NDk3Mzk5OCwiaWRwIjoibG9jYWwiLCJzY29wZSI6WyJub3BfYXBpIiwib2ZmbGluZV9hY2Nlc3MiXSwiYW1yIjpbInB3ZCJdfQ.qE_uD06T8z7JfIw4iM8SgIfN9TGqp--6B5jVA2uMm3v0t5ZsgkW5rcYiqgfwTMBAeLy-f4lSK1-097tdSQOkXWwktiGJjHS3auaVgmIaAhZrcY-aMbdykJKe9tcIUnXFIOYpOldp-gabH1R-7QOkWCYuLUCjy3dTgjn-eA0o3qsCo97xYF9GqAo2xhik2JW20jlVAGjnmTT2q6dtNWRvDbe_DgB-tCl0gFKXTP2lzEhR1xbMPzTizwKrLrabNIqJzzg-m2j3xn888dbf3PyXhgVqyu3o3jkrXDU-HH7g0xj8Cc2xFC6SjrZ_A5A3MtsIMg2q7kEpjH70xiqi8Ro_Bg"
    })
    @GET("shopping_cart_items/{id}")
    Call<ShopingCartModel> cartFavoriteData (@Path("id") String id);
}
