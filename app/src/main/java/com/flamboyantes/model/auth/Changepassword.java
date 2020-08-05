package com.flamboyantes.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Changepassword {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("oldpassword")
    @Expose
    private String oldpassword;
    @SerializedName("newpassword")
    @Expose
    private String newpassword;
    @SerializedName("confirmnewpassword")
    @Expose
    private String confirmnewpassword;

    public Changepassword(String id, String oldpassword, String newpassword, String confirmnewpassword) {
        this.id = id;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
        this.confirmnewpassword = confirmnewpassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getConfirmnewpassword() {
        return confirmnewpassword;
    }

    public void setConfirmnewpassword(String confirmnewpassword) {
        this.confirmnewpassword = confirmnewpassword;
    }

}