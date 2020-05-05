package com.meet.practical.model.entity.request;

import org.parceler.Parcel;

@Parcel
public class ResetPasswordRequest {

    String cur_password;
    String password;
    String confirm_password;

    public String getCur_password() {
        return cur_password;
    }

    public void setCur_password(String cur_password) {
        this.cur_password = cur_password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}
