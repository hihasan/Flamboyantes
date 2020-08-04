package com.flamboyantes.model.cartfavorite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartFavoriteModel {

    @SerializedName("shopping_cart_item")
    @Expose
    private ShoppingCartItem shoppingCartItem;

    public CartFavoriteModel(ShoppingCartItem shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

    public ShoppingCartItem getShoppingCartItem() {
        return shoppingCartItem;
    }

    public void setShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

}

