package com.example.foodapp.JsonInterfaceApi;

import com.example.foodapp.ModelAPI.FoodModel2;
import com.example.foodapp.ModelAPI.ReponseApi;
import com.example.foodapp.ModelAPI.User;
import com.example.foodapp.ModelAPI.storeAPI.StoreModelAPI;
import com.example.foodapp.other.DiscountModelReal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonApi {
    //localhost:8080/foodapp/api/
    @POST("register")
    Call<User> createUser (@Body User user);

    @GET("login")
    Call<List<User>> setLogin ();

    @PUT("changePassword")
    Call<ReponseApi> changPassword(@Path("password") String pass,
                                   @Path("id") int id);

   // @GET("cat5")
   // Call<FoodMode> getAllFood(@Query("utm_source") String utm_source,
             //             @Query("utm_medium")  String utm_medium,
               //           @Query("utm_campaign") String utm_campaign
  //  );

    @GET("getAllfood")
    Call<List<FoodModel2>> getAllFood2();

    @GET("getallstore")
    Call<List<StoreModelAPI>> getAllStore();

    @GET("getAllDiscount")
    Call<List<DiscountModelReal>> getAllDiscount();

}
