package com.example.foodapp.ModelAPI;

import com.google.gson.annotations.SerializedName;

public class ReponseApi {
    @SerializedName("message")
    private String result = "";

    public String getResult() {
        return result;
    }
}
