package com.flamboyantes.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PasswordModel {

    @SerializedName("changepassword")
    @Expose
    private Changepassword changepassword;

    public PasswordModel(Changepassword changepassword) {
        this.changepassword = changepassword;
    }

    public Changepassword getChangepassword() {
        return changepassword;
    }

    public void setChangepassword(Changepassword changepassword) {
        this.changepassword = changepassword;
    }

}
