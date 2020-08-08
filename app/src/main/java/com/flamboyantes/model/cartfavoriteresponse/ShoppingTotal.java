package com.flamboyantes.model.cartfavoriteresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShoppingTotal {

    @SerializedName("sub_total")
    @Expose
    private String subTotal;
    @SerializedName("order_total")
    @Expose
    private String orderTotal;
    @SerializedName("Tax")
    @Expose
    private String tax;

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

}