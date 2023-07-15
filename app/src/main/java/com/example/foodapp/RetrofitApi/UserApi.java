package com.example.foodapp.RetrofitApi;

import com.example.foodapp.JsonInterfaceApi.JsonApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApi {
    private static JsonApi json;
    private static final String Url= "http://172.21.137.67:8080/foodapp/api/";

    public JsonApi getJson (){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        json = retrofit.create(JsonApi.class);
        return json;
    }


}
