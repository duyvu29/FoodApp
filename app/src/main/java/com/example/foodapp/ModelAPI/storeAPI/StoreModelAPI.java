package com.example.foodapp.ModelAPI.storeAPI;

import com.google.gson.annotations.SerializedName;

public class StoreModelAPI {
    /**
     "id": 1,
     "name": "Highlands Coffe- Đinh Bộ Lĩnh",
     "address": "07:00-22:00",
     "description": "0586159362",
     "phone": "Đinh Bộ Lĩnh, quận Bình Thạnh, thành phố Hồ Chí Minh",
     "imageStore": {
     "String": "https://images.foody.vn/res/g78/774647/prof/s640x400/image-7a76032a-230524144005.jpeg",
     "Valid": true
     }**/
    @SerializedName("id")
    private int id;
    @SerializedName("address")
    private String address;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("phone")
    private String phone;
    @SerializedName("imageStore")
    private ImageStore imageStore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ImageStore getImageStore() {
        return imageStore;
    }

    public void setImageStore(ImageStore imageStore) {
        this.imageStore = imageStore;
    }

    public StoreModelAPI(int id, String address, String name, String description, String phone, ImageStore imageStore) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.imageStore = imageStore;
    }

    @Override
    public String toString() {
        return "StoreModelAPI{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", imageStore=" + imageStore +
                '}';
    }
}
