package com.meet.practical.model.entity.response;

import org.parceler.Parcel;

@Parcel
public class LoginResponse extends BaseResponse {

    LoginDetail responseMap;

    public LoginResponse() {
        // blank constructure
    }

    public LoginDetail getResponseMap() {
        return responseMap;
    }

    public void setResponseMap(LoginDetail responseMap) {
        this.responseMap = responseMap;
    }
}
