package com.meet.practical.model.entity.request;

public class CheckOutRequest {
    private String card_name;
    private String card_cvv;
    private String card_no;
    private String card_mm;
    private String card_yy;
    private int address_id,card_id;
    private boolean save_card;
    
    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_cvv() {
        return card_cvv;
    }

    public void setCard_cvv(String card_cvv) {
        this.card_cvv = card_cvv;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getCard_mm() {
        return card_mm;
    }

    public void setCard_mm(String card_mm) {
        this.card_mm = card_mm;
    }

    public String getCard_yy() {
        return card_yy;
    }

    public void setCard_yy(String card_yy) {
        this.card_yy = card_yy;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public boolean isSave_card() {
        return save_card;
    }

    public void setSave_card(boolean save_card) {
        this.save_card = save_card;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }
}
