package com.meet.practical.model.entity.response;

import org.parceler.Parcel;

@Parcel
public class LoginDetail {

    int userId;
    boolean status;
    boolean showGallery;
    long mobileNo;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isShowGallery() {
        return showGallery;
    }

    public void setShowGallery(boolean showGallery) {
        this.showGallery = showGallery;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }
}
