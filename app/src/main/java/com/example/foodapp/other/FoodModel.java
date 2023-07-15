package com.example.foodapp.other;

public class FoodModel {
    private int imageFood;
    private String nameFood;

    public FoodModel(int imageFood, String nameFood) {
        this.imageFood = imageFood;
        this.nameFood = nameFood;
    }

    public int getImageFood() {
        return imageFood;
    }

    public void setImageFood(int imageFood) {
        this.imageFood = imageFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }
}
