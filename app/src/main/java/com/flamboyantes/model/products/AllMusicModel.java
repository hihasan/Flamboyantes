package com.flamboyantes.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllMusicModel {

    @SerializedName("productdownloadsamples")
    @Expose
    private Productdownloadsamples productdownloadsamples;

    public Productdownloadsamples getProductdownloadsamples() {
        return productdownloadsamples;
    }

    public void setProductdownloadsamples(Productdownloadsamples productdownloadsamples) {
        this.productdownloadsamples = productdownloadsamples;
    }

}