package com.meet.practical.model.entity.request;

import org.parceler.Parcel;

@Parcel
public class SaveInquiryRequest {

    String inquiry_title;
    String inquiry_description;

    public String getInquiry_title() {
        return inquiry_title;
    }

    public void setInquiry_title(String inquiry_title) {
        this.inquiry_title = inquiry_title;
    }

    public String getInquiry_description() {
        return inquiry_description;
    }

    public void setInquiry_description(String inquiry_description) {
        this.inquiry_description = inquiry_description;
    }
}
