package com.example.foodapp.RetrofitApi;

import com.example.foodapp.JsonInterfaceApi.JsonApi;
import com.example.foodapp.ModelAPI.ReponseApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class FoodApi {
    //https://yummie.glitch.me/dishes/cat5?utm_source=zalo&utm_medium=zalo&utm_campaign=zalo
    //http://localhost:8080/foods

    private static JsonApi json;
    private static final String UrlFood= "http://localhost:8080/";

    public JsonApi getJsonFood (){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlFood)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        json = retrofit.create(JsonApi.class);
        return json;
    }



}
