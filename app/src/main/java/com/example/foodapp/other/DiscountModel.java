package com.example.foodapp.other;

public class DiscountModel {
    private int imageDiscount;
    private String descDiscount;

    public DiscountModel(int imageDiscount, String descDiscount) {
        this.imageDiscount = imageDiscount;
        this.descDiscount = descDiscount;
    }

    public int getImageDiscount() {
        return imageDiscount;
    }

    public void setImageDiscount(int imageDiscount) {
        this.imageDiscount = imageDiscount;
    }

    public String getDescDiscount() {
        return descDiscount;
    }

    public void setDescDiscount(String descDiscount) {
        this.descDiscount = descDiscount;
    }
}
