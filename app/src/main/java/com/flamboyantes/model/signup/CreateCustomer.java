package com.flamboyantes.model.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateCustomer {

    @SerializedName("customer")
    @Expose
    private SignupCustomer customer;

    public CreateCustomer(SignupCustomer customer){
        this.customer = customer;
    }

    public SignupCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(SignupCustomer customer) {
        this.customer = customer;
    }

}