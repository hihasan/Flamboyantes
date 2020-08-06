package com.flamboyantes.model.cartfavoriteresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalizedName {

    @SerializedName("language_id")
    @Expose
    private Integer languageId;
    @SerializedName("localized_name")
    @Expose
    private String localizedName;

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

}