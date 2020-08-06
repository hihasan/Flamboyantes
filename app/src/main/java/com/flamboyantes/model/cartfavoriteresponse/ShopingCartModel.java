package com.flamboyantes.model.cartfavoriteresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopingCartModel {

    @SerializedName("shopping_carts")
    @Expose
    private List<ShoppingCart> shoppingCarts = null;

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

}