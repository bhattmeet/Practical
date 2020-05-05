package com.meet.practical.model.entity.request;

import org.parceler.Parcel;

@Parcel
public class LoginRequest {
    String email;
    String password;
    int device_type;
    String device_token;
    String device_name;

    String name;
    String social_type;
    String image;
    String social_type_id;


    public LoginRequest(String email, String password, int device_type) {
        this.email = email;
        this.password = password;
        this.device_type = device_type;
    }

    public LoginRequest(String name, String email, String image, String social_type_id, String social_type) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.social_type_id = social_type_id;
        this.social_type = social_type;
    }

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocial_type() {
        return social_type;
    }

    public void setSocial_type(String social_type) {
        this.social_type = social_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSocial_type_id() {
        return social_type_id;
    }

    public void setSocial_type_id(String social_type_id) {
        this.social_type_id = social_type_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }
}
