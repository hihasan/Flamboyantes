package com.flamboyantes.model.checkout;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckoutResponse {

    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("merchantRef")
    @Expose
    private String merchantRef;
    @SerializedName("systemRef")
    @Expose
    private String systemRef;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMerchantRef() {
        return merchantRef;
    }

    public void setMerchantRef(String merchantRef) {
        this.merchantRef = merchantRef;
    }

    public String getSystemRef() {
        return systemRef;
    }

    public void setSystemRef(String systemRef) {
        this.systemRef = systemRef;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}