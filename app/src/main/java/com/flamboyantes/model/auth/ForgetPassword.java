package com.flamboyantes.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPassword {

    PasswordRecovery passwordRecovery;

    public ForgetPassword(PasswordRecovery passwordRecovery) {
        this.passwordRecovery = passwordRecovery;
    }

    public PasswordRecovery getPasswordRecovery() {
        return passwordRecovery;
    }

    public void setPasswordRecovery(PasswordRecovery passwordRecovery) {
        this.passwordRecovery = passwordRecovery;
    }
}

