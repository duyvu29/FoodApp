package com.example.foodapp.ModelAPI;

import com.google.gson.annotations.SerializedName;

public class ImageFood2 {
    @SerializedName("String")
    private String String;
    @SerializedName("Valid")
    private Boolean Valid;

    public ImageFood2(java.lang.String string, Boolean valid) {
        String = string;
        Valid = valid;
    }

    public java.lang.String getString() {
        return String;
    }

    public void setString(java.lang.String string) {
        String = string;
    }

    public Boolean getValid() {
        return Valid;
    }

    public void setValid(Boolean valid) {
        Valid = valid;
    }

    @Override
    public java.lang.String toString() {
        return "ImageFood2{" +
                "String='" + String + '\'' +
                ", Valid=" + Valid +
                '}';
    }
}
