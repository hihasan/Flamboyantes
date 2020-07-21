package com.flamboyantes.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Downloaditem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sample_downloadid")
    @Expose
    private Integer sampleDownloadid;
    @SerializedName("sample_downloadurl")
    @Expose
    private String sampleDownloadurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSampleDownloadid() {
        return sampleDownloadid;
    }

    public void setSampleDownloadid(Integer sampleDownloadid) {
        this.sampleDownloadid = sampleDownloadid;
    }

    public String getSampleDownloadurl() {
        return sampleDownloadurl;
    }

    public void setSampleDownloadurl(String sampleDownloadurl) {
        this.sampleDownloadurl = sampleDownloadurl;
    }

}
