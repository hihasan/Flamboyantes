package com.flamboyantes.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Checkout{

    @SerializedName("merchantId")
    @Expose
    private Integer merchantId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("merchantRef")
    @Expose
    private String merchantRef;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("successUrl")
    @Expose
    private String successUrl;
    @SerializedName("failedUrl")
    @Expose
    private String failedUrl;
    @SerializedName("cancelledUrl")
    @Expose
    private String cancelledUrl;
    @SerializedName("redirectUrl")
    @Expose
    private String redirectUrl;

    public Checkout(Integer merchantId, String description, String language, String merchantRef, String currency, Integer amount, String successUrl, String failedUrl, String cancelledUrl, String redirectUrl) {
        this.merchantId = merchantId;
        this.description = description;
        this.language = language;
        this.merchantRef = merchantRef;
        this.currency = currency;
        this.amount = amount;
        this.successUrl = successUrl;
        this.failedUrl = failedUrl;
        this.cancelledUrl = cancelledUrl;
        this.redirectUrl = redirectUrl;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMerchantRef() {
        return merchantRef;
    }

    public void setMerchantRef(String merchantRef) {
        this.merchantRef = merchantRef;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailedUrl() {
        return failedUrl;
    }

    public void setFailedUrl(String failedUrl) {
        this.failedUrl = failedUrl;
    }

    public String getCancelledUrl() {
        return cancelledUrl;
    }

    public void setCancelledUrl(String cancelledUrl) {
        this.cancelledUrl = cancelledUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

}