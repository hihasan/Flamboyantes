package com.flamboyantes.model.cartfavoriteresponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopingCartModel {

    @SerializedName("shopping_carts")
    @Expose
    private List<ShoppingCart> shoppingCarts = null;
    @SerializedName("shopping_Total")
    @Expose
    private ShoppingTotal shoppingTotal;

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public ShoppingTotal getShoppingTotal() {
        return shoppingTotal;
    }

    public void setShoppingTotal(ShoppingTotal shoppingTotal) {
        this.shoppingTotal = shoppingTotal;
    }

}

