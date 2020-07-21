package com.flamboyantes.model.products;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Productdownloadsamples {

    @SerializedName("productid")
    @Expose
    private Integer productid;
    @SerializedName("downloaditems")
    @Expose
    private ArrayList<Downloaditem> downloaditems = null;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public ArrayList<Downloaditem> getDownloaditems() {
        return downloaditems;
    }

    public void setDownloaditems(ArrayList<Downloaditem> downloaditems) {
        this.downloaditems = downloaditems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}