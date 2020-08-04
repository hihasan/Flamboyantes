package com.flamboyantes.model.cartfavorite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShoppingCartItem {

    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("shopping_cart_type")
    @Expose
    private Integer shoppingCartType;

    public ShoppingCartItem(String quantity, String productId, String customerId, Integer shoppingCartType) {
        this.quantity = quantity;
        this.productId = productId;
        this.customerId = customerId;
        this.shoppingCartType = shoppingCartType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getShoppingCartType() {
        return shoppingCartType;
    }

    public void setShoppingCartType(Integer shoppingCartType) {
        this.shoppingCartType = shoppingCartType;
    }

}