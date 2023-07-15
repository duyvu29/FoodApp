package com.example.foodapp.ModelAPI.storeAPI;

import com.google.gson.annotations.SerializedName;

public class ImageStore {
    @SerializedName("String")
    private String String;
    @SerializedName("Valid")
    private Boolean Valid;

    public ImageStore(java.lang.String string, Boolean valid) {
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
        return "ImageStore{" +
                "String='" + String + '\'' +
                ", Valid=" + Valid +
                '}';
    }
}
