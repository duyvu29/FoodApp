package com.example.foodapp.ModelAPI;

import com.google.gson.annotations.SerializedName;

public class FoodModel2 {
    @SerializedName("idfood")
    private int idfood;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("description")
    private String description;
    @SerializedName("ratenumber")
    private int ratenumber;
    @SerializedName("idstore")
    private int idstore;
    @SerializedName("idcategory")
    private int idcategory;
    @SerializedName("imagefood")
    private ImageFood2 imagefood;

    public FoodModel2(int idfood, String name, String price, String description, int ratenumber, int idstore, int idcategory, ImageFood2 imagefood) {
        this.idfood = idfood;
        this.name = name;
        this.price = price;
        this.description = description;
        this.ratenumber = ratenumber;
        this.idstore = idstore;
        this.idcategory = idcategory;
        this.imagefood = imagefood;
    }

    public int getIdfood() {
        return idfood;
    }

    public void setIdfood(int idfood) {
        this.idfood = idfood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRatenumber() {
        return ratenumber;
    }

    public void setRatenumber(int ratenumber) {
        this.ratenumber = ratenumber;
    }

    public int getIdstore() {
        return idstore;
    }

    public void setIdstore(int idstore) {
        this.idstore = idstore;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public ImageFood2 getImagefood() {
        return imagefood;
    }

    public void setImagefood(ImageFood2 imagefood) {
        this.imagefood = imagefood;
    }

    @Override
    public String toString() {
        return "FoodModel2{" +
                "idfood=" + idfood +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", ratenumber=" + ratenumber +
                ", idstore=" + idstore +
                ", idcategory=" + idcategory +
                ", imagefood=" + imagefood +
                '}';
    }
}
